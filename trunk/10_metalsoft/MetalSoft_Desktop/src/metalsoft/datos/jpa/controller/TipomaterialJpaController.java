/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Materiaprima;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Tipomaterial;

/**
 *
 * @author Nino
 */
public class TipomaterialJpaController {

    public TipomaterialJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipomaterial tipomaterial) throws PreexistingEntityException, Exception {
        if (tipomaterial.getMateriaprimaList() == null) {
            tipomaterial.setMateriaprimaList(new ArrayList<Materiaprima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Materiaprima> attachedMateriaprimaList = new ArrayList<Materiaprima>();
            for (Materiaprima materiaprimaListMateriaprimaToAttach : tipomaterial.getMateriaprimaList()) {
                materiaprimaListMateriaprimaToAttach = em.getReference(materiaprimaListMateriaprimaToAttach.getClass(), materiaprimaListMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaList.add(materiaprimaListMateriaprimaToAttach);
            }
            tipomaterial.setMateriaprimaList(attachedMateriaprimaList);
            em.persist(tipomaterial);
            for (Materiaprima materiaprimaListMateriaprima : tipomaterial.getMateriaprimaList()) {
                Tipomaterial oldTipomaterialOfMateriaprimaListMateriaprima = materiaprimaListMateriaprima.getTipomaterial();
                materiaprimaListMateriaprima.setTipomaterial(tipomaterial);
                materiaprimaListMateriaprima = em.merge(materiaprimaListMateriaprima);
                if (oldTipomaterialOfMateriaprimaListMateriaprima != null) {
                    oldTipomaterialOfMateriaprimaListMateriaprima.getMateriaprimaList().remove(materiaprimaListMateriaprima);
                    oldTipomaterialOfMateriaprimaListMateriaprima = em.merge(oldTipomaterialOfMateriaprimaListMateriaprima);
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
            List<Materiaprima> materiaprimaListOld = persistentTipomaterial.getMateriaprimaList();
            List<Materiaprima> materiaprimaListNew = tipomaterial.getMateriaprimaList();
            List<Materiaprima> attachedMateriaprimaListNew = new ArrayList<Materiaprima>();
            for (Materiaprima materiaprimaListNewMateriaprimaToAttach : materiaprimaListNew) {
                materiaprimaListNewMateriaprimaToAttach = em.getReference(materiaprimaListNewMateriaprimaToAttach.getClass(), materiaprimaListNewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaListNew.add(materiaprimaListNewMateriaprimaToAttach);
            }
            materiaprimaListNew = attachedMateriaprimaListNew;
            tipomaterial.setMateriaprimaList(materiaprimaListNew);
            tipomaterial = em.merge(tipomaterial);
            for (Materiaprima materiaprimaListOldMateriaprima : materiaprimaListOld) {
                if (!materiaprimaListNew.contains(materiaprimaListOldMateriaprima)) {
                    materiaprimaListOldMateriaprima.setTipomaterial(null);
                    materiaprimaListOldMateriaprima = em.merge(materiaprimaListOldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaListNewMateriaprima : materiaprimaListNew) {
                if (!materiaprimaListOld.contains(materiaprimaListNewMateriaprima)) {
                    Tipomaterial oldTipomaterialOfMateriaprimaListNewMateriaprima = materiaprimaListNewMateriaprima.getTipomaterial();
                    materiaprimaListNewMateriaprima.setTipomaterial(tipomaterial);
                    materiaprimaListNewMateriaprima = em.merge(materiaprimaListNewMateriaprima);
                    if (oldTipomaterialOfMateriaprimaListNewMateriaprima != null && !oldTipomaterialOfMateriaprimaListNewMateriaprima.equals(tipomaterial)) {
                        oldTipomaterialOfMateriaprimaListNewMateriaprima.getMateriaprimaList().remove(materiaprimaListNewMateriaprima);
                        oldTipomaterialOfMateriaprimaListNewMateriaprima = em.merge(oldTipomaterialOfMateriaprimaListNewMateriaprima);
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
            List<Materiaprima> materiaprimaList = tipomaterial.getMateriaprimaList();
            for (Materiaprima materiaprimaListMateriaprima : materiaprimaList) {
                materiaprimaListMateriaprima.setTipomaterial(null);
                materiaprimaListMateriaprima = em.merge(materiaprimaListMateriaprima);
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
