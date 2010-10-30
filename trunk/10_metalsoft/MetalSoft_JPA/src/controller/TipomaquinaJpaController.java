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
        if (tipomaquina.getMaquinaSet1() == null) {
            tipomaquina.setMaquinaSet1(new HashSet<Maquina>());
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
            Set<Maquina> attachedMaquinaSet1 = new HashSet<Maquina>();
            for (Maquina maquinaSet1MaquinaToAttach : tipomaquina.getMaquinaSet1()) {
                maquinaSet1MaquinaToAttach = em.getReference(maquinaSet1MaquinaToAttach.getClass(), maquinaSet1MaquinaToAttach.getIdmaquina());
                attachedMaquinaSet1.add(maquinaSet1MaquinaToAttach);
            }
            tipomaquina.setMaquinaSet1(attachedMaquinaSet1);
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
            for (Maquina maquinaSet1Maquina : tipomaquina.getMaquinaSet1()) {
                Tipomaquina oldTipomaquina1OfMaquinaSet1Maquina = maquinaSet1Maquina.getTipomaquina1();
                maquinaSet1Maquina.setTipomaquina1(tipomaquina);
                maquinaSet1Maquina = em.merge(maquinaSet1Maquina);
                if (oldTipomaquina1OfMaquinaSet1Maquina != null) {
                    oldTipomaquina1OfMaquinaSet1Maquina.getMaquinaSet1().remove(maquinaSet1Maquina);
                    oldTipomaquina1OfMaquinaSet1Maquina = em.merge(oldTipomaquina1OfMaquinaSet1Maquina);
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
            Set<Maquina> maquinaSet1Old = persistentTipomaquina.getMaquinaSet1();
            Set<Maquina> maquinaSet1New = tipomaquina.getMaquinaSet1();
            Set<Maquina> attachedMaquinaSetNew = new HashSet<Maquina>();
            for (Maquina maquinaSetNewMaquinaToAttach : maquinaSetNew) {
                maquinaSetNewMaquinaToAttach = em.getReference(maquinaSetNewMaquinaToAttach.getClass(), maquinaSetNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSetNew.add(maquinaSetNewMaquinaToAttach);
            }
            maquinaSetNew = attachedMaquinaSetNew;
            tipomaquina.setMaquinaSet(maquinaSetNew);
            Set<Maquina> attachedMaquinaSet1New = new HashSet<Maquina>();
            for (Maquina maquinaSet1NewMaquinaToAttach : maquinaSet1New) {
                maquinaSet1NewMaquinaToAttach = em.getReference(maquinaSet1NewMaquinaToAttach.getClass(), maquinaSet1NewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet1New.add(maquinaSet1NewMaquinaToAttach);
            }
            maquinaSet1New = attachedMaquinaSet1New;
            tipomaquina.setMaquinaSet1(maquinaSet1New);
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
            for (Maquina maquinaSet1OldMaquina : maquinaSet1Old) {
                if (!maquinaSet1New.contains(maquinaSet1OldMaquina)) {
                    maquinaSet1OldMaquina.setTipomaquina1(null);
                    maquinaSet1OldMaquina = em.merge(maquinaSet1OldMaquina);
                }
            }
            for (Maquina maquinaSet1NewMaquina : maquinaSet1New) {
                if (!maquinaSet1Old.contains(maquinaSet1NewMaquina)) {
                    Tipomaquina oldTipomaquina1OfMaquinaSet1NewMaquina = maquinaSet1NewMaquina.getTipomaquina1();
                    maquinaSet1NewMaquina.setTipomaquina1(tipomaquina);
                    maquinaSet1NewMaquina = em.merge(maquinaSet1NewMaquina);
                    if (oldTipomaquina1OfMaquinaSet1NewMaquina != null && !oldTipomaquina1OfMaquinaSet1NewMaquina.equals(tipomaquina)) {
                        oldTipomaquina1OfMaquinaSet1NewMaquina.getMaquinaSet1().remove(maquinaSet1NewMaquina);
                        oldTipomaquina1OfMaquinaSet1NewMaquina = em.merge(oldTipomaquina1OfMaquinaSet1NewMaquina);
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
            Set<Maquina> maquinaSet1 = tipomaquina.getMaquinaSet1();
            for (Maquina maquinaSet1Maquina : maquinaSet1) {
                maquinaSet1Maquina.setTipomaquina1(null);
                maquinaSet1Maquina = em.merge(maquinaSet1Maquina);
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
