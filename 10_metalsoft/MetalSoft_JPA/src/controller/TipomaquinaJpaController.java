/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Tipomaquina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Maquina;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class TipomaquinaJpaController {

    public TipomaquinaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipomaquina tipomaquina) throws PreexistingEntityException, Exception {
        if (tipomaquina.getMaquinaSet() == null) {
            tipomaquina.setMaquinaSet(new HashSet<Maquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Maquina> attachedMaquinaSet = new HashSet<Maquina>();
            for (Maquina maquinaSetMaquinaToAttach : tipomaquina.getMaquinaSet()) {
                maquinaSetMaquinaToAttach = em.getReference(maquinaSetMaquinaToAttach.getClass(), maquinaSetMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet.add(maquinaSetMaquinaToAttach);
            }
            tipomaquina.setMaquinaSet(attachedMaquinaSet);
            em.persist(tipomaquina);
            for (Maquina maquinaSetMaquina : tipomaquina.getMaquinaSet()) {
                Tipomaquina oldTipomaquinaOfMaquinaSetMaquina = maquinaSetMaquina.getTipomaquina();
                maquinaSetMaquina.setTipomaquina(tipomaquina);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
                if (oldTipomaquinaOfMaquinaSetMaquina != null) {
                    oldTipomaquinaOfMaquinaSetMaquina.getMaquinaSet().remove(maquinaSetMaquina);
                    oldTipomaquinaOfMaquinaSetMaquina = em.merge(oldTipomaquinaOfMaquinaSetMaquina);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipomaquina(tipomaquina.getIdtipomaquina()) != null) {
                throw new PreexistingEntityException("Tipomaquina " + tipomaquina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipomaquina tipomaquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipomaquina persistentTipomaquina = em.find(Tipomaquina.class, tipomaquina.getIdtipomaquina());
            Set<Maquina> maquinaSetOld = persistentTipomaquina.getMaquinaSet();
            Set<Maquina> maquinaSetNew = tipomaquina.getMaquinaSet();
            Set<Maquina> attachedMaquinaSetNew = new HashSet<Maquina>();
            for (Maquina maquinaSetNewMaquinaToAttach : maquinaSetNew) {
                maquinaSetNewMaquinaToAttach = em.getReference(maquinaSetNewMaquinaToAttach.getClass(), maquinaSetNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSetNew.add(maquinaSetNewMaquinaToAttach);
            }
            maquinaSetNew = attachedMaquinaSetNew;
            tipomaquina.setMaquinaSet(maquinaSetNew);
            tipomaquina = em.merge(tipomaquina);
            for (Maquina maquinaSetOldMaquina : maquinaSetOld) {
                if (!maquinaSetNew.contains(maquinaSetOldMaquina)) {
                    maquinaSetOldMaquina.setTipomaquina(null);
                    maquinaSetOldMaquina = em.merge(maquinaSetOldMaquina);
                }
            }
            for (Maquina maquinaSetNewMaquina : maquinaSetNew) {
                if (!maquinaSetOld.contains(maquinaSetNewMaquina)) {
                    Tipomaquina oldTipomaquinaOfMaquinaSetNewMaquina = maquinaSetNewMaquina.getTipomaquina();
                    maquinaSetNewMaquina.setTipomaquina(tipomaquina);
                    maquinaSetNewMaquina = em.merge(maquinaSetNewMaquina);
                    if (oldTipomaquinaOfMaquinaSetNewMaquina != null && !oldTipomaquinaOfMaquinaSetNewMaquina.equals(tipomaquina)) {
                        oldTipomaquinaOfMaquinaSetNewMaquina.getMaquinaSet().remove(maquinaSetNewMaquina);
                        oldTipomaquinaOfMaquinaSetNewMaquina = em.merge(oldTipomaquinaOfMaquinaSetNewMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipomaquina.getIdtipomaquina();
                if (findTipomaquina(id) == null) {
                    throw new NonexistentEntityException("The tipomaquina with id " + id + " no longer exists.");
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
            Tipomaquina tipomaquina;
            try {
                tipomaquina = em.getReference(Tipomaquina.class, id);
                tipomaquina.getIdtipomaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipomaquina with id " + id + " no longer exists.", enfe);
            }
            Set<Maquina> maquinaSet = tipomaquina.getMaquinaSet();
            for (Maquina maquinaSetMaquina : maquinaSet) {
                maquinaSetMaquina.setTipomaquina(null);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
            }
            em.remove(tipomaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipomaquina> findTipomaquinaEntities() {
        return findTipomaquinaEntities(true, -1, -1);
    }

    public List<Tipomaquina> findTipomaquinaEntities(int maxResults, int firstResult) {
        return findTipomaquinaEntities(false, maxResults, firstResult);
    }

    private List<Tipomaquina> findTipomaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipomaquina.class));
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

    public Tipomaquina findTipomaquina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipomaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipomaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipomaquina> rt = cq.from(Tipomaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
