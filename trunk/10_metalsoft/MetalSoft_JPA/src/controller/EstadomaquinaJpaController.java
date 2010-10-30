/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadomaquina;
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
public class EstadomaquinaJpaController {

    public EstadomaquinaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadomaquina estadomaquina) throws PreexistingEntityException, Exception {
        if (estadomaquina.getMaquinaSet() == null) {
            estadomaquina.setMaquinaSet(new HashSet<Maquina>());
        }
        if (estadomaquina.getMaquinaSet1() == null) {
            estadomaquina.setMaquinaSet1(new HashSet<Maquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Maquina> attachedMaquinaSet = new HashSet<Maquina>();
            for (Maquina maquinaSetMaquinaToAttach : estadomaquina.getMaquinaSet()) {
                maquinaSetMaquinaToAttach = em.getReference(maquinaSetMaquinaToAttach.getClass(), maquinaSetMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet.add(maquinaSetMaquinaToAttach);
            }
            estadomaquina.setMaquinaSet(attachedMaquinaSet);
            Set<Maquina> attachedMaquinaSet1 = new HashSet<Maquina>();
            for (Maquina maquinaSet1MaquinaToAttach : estadomaquina.getMaquinaSet1()) {
                maquinaSet1MaquinaToAttach = em.getReference(maquinaSet1MaquinaToAttach.getClass(), maquinaSet1MaquinaToAttach.getIdmaquina());
                attachedMaquinaSet1.add(maquinaSet1MaquinaToAttach);
            }
            estadomaquina.setMaquinaSet1(attachedMaquinaSet1);
            em.persist(estadomaquina);
            for (Maquina maquinaSetMaquina : estadomaquina.getMaquinaSet()) {
                Estadomaquina oldEstadoOfMaquinaSetMaquina = maquinaSetMaquina.getEstado();
                maquinaSetMaquina.setEstado(estadomaquina);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
                if (oldEstadoOfMaquinaSetMaquina != null) {
                    oldEstadoOfMaquinaSetMaquina.getMaquinaSet().remove(maquinaSetMaquina);
                    oldEstadoOfMaquinaSetMaquina = em.merge(oldEstadoOfMaquinaSetMaquina);
                }
            }
            for (Maquina maquinaSet1Maquina : estadomaquina.getMaquinaSet1()) {
                Estadomaquina oldEstado1OfMaquinaSet1Maquina = maquinaSet1Maquina.getEstado1();
                maquinaSet1Maquina.setEstado1(estadomaquina);
                maquinaSet1Maquina = em.merge(maquinaSet1Maquina);
                if (oldEstado1OfMaquinaSet1Maquina != null) {
                    oldEstado1OfMaquinaSet1Maquina.getMaquinaSet1().remove(maquinaSet1Maquina);
                    oldEstado1OfMaquinaSet1Maquina = em.merge(oldEstado1OfMaquinaSet1Maquina);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadomaquina(estadomaquina.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadomaquina " + estadomaquina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadomaquina estadomaquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadomaquina persistentEstadomaquina = em.find(Estadomaquina.class, estadomaquina.getIdestado());
            Set<Maquina> maquinaSetOld = persistentEstadomaquina.getMaquinaSet();
            Set<Maquina> maquinaSetNew = estadomaquina.getMaquinaSet();
            Set<Maquina> maquinaSet1Old = persistentEstadomaquina.getMaquinaSet1();
            Set<Maquina> maquinaSet1New = estadomaquina.getMaquinaSet1();
            Set<Maquina> attachedMaquinaSetNew = new HashSet<Maquina>();
            for (Maquina maquinaSetNewMaquinaToAttach : maquinaSetNew) {
                maquinaSetNewMaquinaToAttach = em.getReference(maquinaSetNewMaquinaToAttach.getClass(), maquinaSetNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSetNew.add(maquinaSetNewMaquinaToAttach);
            }
            maquinaSetNew = attachedMaquinaSetNew;
            estadomaquina.setMaquinaSet(maquinaSetNew);
            Set<Maquina> attachedMaquinaSet1New = new HashSet<Maquina>();
            for (Maquina maquinaSet1NewMaquinaToAttach : maquinaSet1New) {
                maquinaSet1NewMaquinaToAttach = em.getReference(maquinaSet1NewMaquinaToAttach.getClass(), maquinaSet1NewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet1New.add(maquinaSet1NewMaquinaToAttach);
            }
            maquinaSet1New = attachedMaquinaSet1New;
            estadomaquina.setMaquinaSet1(maquinaSet1New);
            estadomaquina = em.merge(estadomaquina);
            for (Maquina maquinaSetOldMaquina : maquinaSetOld) {
                if (!maquinaSetNew.contains(maquinaSetOldMaquina)) {
                    maquinaSetOldMaquina.setEstado(null);
                    maquinaSetOldMaquina = em.merge(maquinaSetOldMaquina);
                }
            }
            for (Maquina maquinaSetNewMaquina : maquinaSetNew) {
                if (!maquinaSetOld.contains(maquinaSetNewMaquina)) {
                    Estadomaquina oldEstadoOfMaquinaSetNewMaquina = maquinaSetNewMaquina.getEstado();
                    maquinaSetNewMaquina.setEstado(estadomaquina);
                    maquinaSetNewMaquina = em.merge(maquinaSetNewMaquina);
                    if (oldEstadoOfMaquinaSetNewMaquina != null && !oldEstadoOfMaquinaSetNewMaquina.equals(estadomaquina)) {
                        oldEstadoOfMaquinaSetNewMaquina.getMaquinaSet().remove(maquinaSetNewMaquina);
                        oldEstadoOfMaquinaSetNewMaquina = em.merge(oldEstadoOfMaquinaSetNewMaquina);
                    }
                }
            }
            for (Maquina maquinaSet1OldMaquina : maquinaSet1Old) {
                if (!maquinaSet1New.contains(maquinaSet1OldMaquina)) {
                    maquinaSet1OldMaquina.setEstado1(null);
                    maquinaSet1OldMaquina = em.merge(maquinaSet1OldMaquina);
                }
            }
            for (Maquina maquinaSet1NewMaquina : maquinaSet1New) {
                if (!maquinaSet1Old.contains(maquinaSet1NewMaquina)) {
                    Estadomaquina oldEstado1OfMaquinaSet1NewMaquina = maquinaSet1NewMaquina.getEstado1();
                    maquinaSet1NewMaquina.setEstado1(estadomaquina);
                    maquinaSet1NewMaquina = em.merge(maquinaSet1NewMaquina);
                    if (oldEstado1OfMaquinaSet1NewMaquina != null && !oldEstado1OfMaquinaSet1NewMaquina.equals(estadomaquina)) {
                        oldEstado1OfMaquinaSet1NewMaquina.getMaquinaSet1().remove(maquinaSet1NewMaquina);
                        oldEstado1OfMaquinaSet1NewMaquina = em.merge(oldEstado1OfMaquinaSet1NewMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadomaquina.getIdestado();
                if (findEstadomaquina(id) == null) {
                    throw new NonexistentEntityException("The estadomaquina with id " + id + " no longer exists.");
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
            Estadomaquina estadomaquina;
            try {
                estadomaquina = em.getReference(Estadomaquina.class, id);
                estadomaquina.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadomaquina with id " + id + " no longer exists.", enfe);
            }
            Set<Maquina> maquinaSet = estadomaquina.getMaquinaSet();
            for (Maquina maquinaSetMaquina : maquinaSet) {
                maquinaSetMaquina.setEstado(null);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
            }
            Set<Maquina> maquinaSet1 = estadomaquina.getMaquinaSet1();
            for (Maquina maquinaSet1Maquina : maquinaSet1) {
                maquinaSet1Maquina.setEstado1(null);
                maquinaSet1Maquina = em.merge(maquinaSet1Maquina);
            }
            em.remove(estadomaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadomaquina> findEstadomaquinaEntities() {
        return findEstadomaquinaEntities(true, -1, -1);
    }

    public List<Estadomaquina> findEstadomaquinaEntities(int maxResults, int firstResult) {
        return findEstadomaquinaEntities(false, maxResults, firstResult);
    }

    private List<Estadomaquina> findEstadomaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadomaquina.class));
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

    public Estadomaquina findEstadomaquina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadomaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadomaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadomaquina> rt = cq.from(Estadomaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
