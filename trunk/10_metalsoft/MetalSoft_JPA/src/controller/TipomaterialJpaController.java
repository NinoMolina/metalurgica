/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Tipomaterial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class TipomaterialJpaController {

    public TipomaterialJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipomaterial tipomaterial) throws PreexistingEntityException, Exception {
        if (tipomaterial.getMateriaprimaSet() == null) {
            tipomaterial.setMateriaprimaSet(new HashSet<Materiaprima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Materiaprima> attachedMateriaprimaSet = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetMateriaprimaToAttach : tipomaterial.getMateriaprimaSet()) {
                materiaprimaSetMateriaprimaToAttach = em.getReference(materiaprimaSetMateriaprimaToAttach.getClass(), materiaprimaSetMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet.add(materiaprimaSetMateriaprimaToAttach);
            }
            tipomaterial.setMateriaprimaSet(attachedMateriaprimaSet);
            em.persist(tipomaterial);
            for (Materiaprima materiaprimaSetMateriaprima : tipomaterial.getMateriaprimaSet()) {
                Tipomaterial oldTipomaterialOfMateriaprimaSetMateriaprima = materiaprimaSetMateriaprima.getTipomaterial();
                materiaprimaSetMateriaprima.setTipomaterial(tipomaterial);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
                if (oldTipomaterialOfMateriaprimaSetMateriaprima != null) {
                    oldTipomaterialOfMateriaprimaSetMateriaprima.getMateriaprimaSet().remove(materiaprimaSetMateriaprima);
                    oldTipomaterialOfMateriaprimaSetMateriaprima = em.merge(oldTipomaterialOfMateriaprimaSetMateriaprima);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipomaterial(tipomaterial.getIdtipomaterial()) != null) {
                throw new PreexistingEntityException("Tipomaterial " + tipomaterial + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipomaterial tipomaterial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipomaterial persistentTipomaterial = em.find(Tipomaterial.class, tipomaterial.getIdtipomaterial());
            Set<Materiaprima> materiaprimaSetOld = persistentTipomaterial.getMateriaprimaSet();
            Set<Materiaprima> materiaprimaSetNew = tipomaterial.getMateriaprimaSet();
            Set<Materiaprima> attachedMateriaprimaSetNew = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetNewMateriaprimaToAttach : materiaprimaSetNew) {
                materiaprimaSetNewMateriaprimaToAttach = em.getReference(materiaprimaSetNewMateriaprimaToAttach.getClass(), materiaprimaSetNewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSetNew.add(materiaprimaSetNewMateriaprimaToAttach);
            }
            materiaprimaSetNew = attachedMateriaprimaSetNew;
            tipomaterial.setMateriaprimaSet(materiaprimaSetNew);
            tipomaterial = em.merge(tipomaterial);
            for (Materiaprima materiaprimaSetOldMateriaprima : materiaprimaSetOld) {
                if (!materiaprimaSetNew.contains(materiaprimaSetOldMateriaprima)) {
                    materiaprimaSetOldMateriaprima.setTipomaterial(null);
                    materiaprimaSetOldMateriaprima = em.merge(materiaprimaSetOldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaSetNewMateriaprima : materiaprimaSetNew) {
                if (!materiaprimaSetOld.contains(materiaprimaSetNewMateriaprima)) {
                    Tipomaterial oldTipomaterialOfMateriaprimaSetNewMateriaprima = materiaprimaSetNewMateriaprima.getTipomaterial();
                    materiaprimaSetNewMateriaprima.setTipomaterial(tipomaterial);
                    materiaprimaSetNewMateriaprima = em.merge(materiaprimaSetNewMateriaprima);
                    if (oldTipomaterialOfMateriaprimaSetNewMateriaprima != null && !oldTipomaterialOfMateriaprimaSetNewMateriaprima.equals(tipomaterial)) {
                        oldTipomaterialOfMateriaprimaSetNewMateriaprima.getMateriaprimaSet().remove(materiaprimaSetNewMateriaprima);
                        oldTipomaterialOfMateriaprimaSetNewMateriaprima = em.merge(oldTipomaterialOfMateriaprimaSetNewMateriaprima);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipomaterial.getIdtipomaterial();
                if (findTipomaterial(id) == null) {
                    throw new NonexistentEntityException("The tipomaterial with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipomaterial tipomaterial;
            try {
                tipomaterial = em.getReference(Tipomaterial.class, id);
                tipomaterial.getIdtipomaterial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipomaterial with id " + id + " no longer exists.", enfe);
            }
            Set<Materiaprima> materiaprimaSet = tipomaterial.getMateriaprimaSet();
            for (Materiaprima materiaprimaSetMateriaprima : materiaprimaSet) {
                materiaprimaSetMateriaprima.setTipomaterial(null);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
            }
            em.remove(tipomaterial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipomaterial> findTipomaterialEntities() {
        return findTipomaterialEntities(true, -1, -1);
    }

    public List<Tipomaterial> findTipomaterialEntities(int maxResults, int firstResult) {
        return findTipomaterialEntities(false, maxResults, firstResult);
    }

    private List<Tipomaterial> findTipomaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipomaterial.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tipomaterial findTipomaterial(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipomaterial.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipomaterialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipomaterial> rt = cq.from(Tipomaterial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
