/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadoremito;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Remito;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadoremitoJpaController {

    public EstadoremitoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoremito estadoremito) throws PreexistingEntityException, Exception {
        if (estadoremito.getRemitoSet() == null) {
            estadoremito.setRemitoSet(new HashSet<Remito>());
        }
        if (estadoremito.getRemitoSet1() == null) {
            estadoremito.setRemitoSet1(new HashSet<Remito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Remito> attachedRemitoSet = new HashSet<Remito>();
            for (Remito remitoSetRemitoToAttach : estadoremito.getRemitoSet()) {
                remitoSetRemitoToAttach = em.getReference(remitoSetRemitoToAttach.getClass(), remitoSetRemitoToAttach.getIdremito());
                attachedRemitoSet.add(remitoSetRemitoToAttach);
            }
            estadoremito.setRemitoSet(attachedRemitoSet);
            Set<Remito> attachedRemitoSet1 = new HashSet<Remito>();
            for (Remito remitoSet1RemitoToAttach : estadoremito.getRemitoSet1()) {
                remitoSet1RemitoToAttach = em.getReference(remitoSet1RemitoToAttach.getClass(), remitoSet1RemitoToAttach.getIdremito());
                attachedRemitoSet1.add(remitoSet1RemitoToAttach);
            }
            estadoremito.setRemitoSet1(attachedRemitoSet1);
            em.persist(estadoremito);
            for (Remito remitoSetRemito : estadoremito.getRemitoSet()) {
                Estadoremito oldEstadoOfRemitoSetRemito = remitoSetRemito.getEstado();
                remitoSetRemito.setEstado(estadoremito);
                remitoSetRemito = em.merge(remitoSetRemito);
                if (oldEstadoOfRemitoSetRemito != null) {
                    oldEstadoOfRemitoSetRemito.getRemitoSet().remove(remitoSetRemito);
                    oldEstadoOfRemitoSetRemito = em.merge(oldEstadoOfRemitoSetRemito);
                }
            }
            for (Remito remitoSet1Remito : estadoremito.getRemitoSet1()) {
                Estadoremito oldEstado1OfRemitoSet1Remito = remitoSet1Remito.getEstado1();
                remitoSet1Remito.setEstado1(estadoremito);
                remitoSet1Remito = em.merge(remitoSet1Remito);
                if (oldEstado1OfRemitoSet1Remito != null) {
                    oldEstado1OfRemitoSet1Remito.getRemitoSet1().remove(remitoSet1Remito);
                    oldEstado1OfRemitoSet1Remito = em.merge(oldEstado1OfRemitoSet1Remito);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoremito(estadoremito.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoremito " + estadoremito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoremito estadoremito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoremito persistentEstadoremito = em.find(Estadoremito.class, estadoremito.getIdestado());
            Set<Remito> remitoSetOld = persistentEstadoremito.getRemitoSet();
            Set<Remito> remitoSetNew = estadoremito.getRemitoSet();
            Set<Remito> remitoSet1Old = persistentEstadoremito.getRemitoSet1();
            Set<Remito> remitoSet1New = estadoremito.getRemitoSet1();
            Set<Remito> attachedRemitoSetNew = new HashSet<Remito>();
            for (Remito remitoSetNewRemitoToAttach : remitoSetNew) {
                remitoSetNewRemitoToAttach = em.getReference(remitoSetNewRemitoToAttach.getClass(), remitoSetNewRemitoToAttach.getIdremito());
                attachedRemitoSetNew.add(remitoSetNewRemitoToAttach);
            }
            remitoSetNew = attachedRemitoSetNew;
            estadoremito.setRemitoSet(remitoSetNew);
            Set<Remito> attachedRemitoSet1New = new HashSet<Remito>();
            for (Remito remitoSet1NewRemitoToAttach : remitoSet1New) {
                remitoSet1NewRemitoToAttach = em.getReference(remitoSet1NewRemitoToAttach.getClass(), remitoSet1NewRemitoToAttach.getIdremito());
                attachedRemitoSet1New.add(remitoSet1NewRemitoToAttach);
            }
            remitoSet1New = attachedRemitoSet1New;
            estadoremito.setRemitoSet1(remitoSet1New);
            estadoremito = em.merge(estadoremito);
            for (Remito remitoSetOldRemito : remitoSetOld) {
                if (!remitoSetNew.contains(remitoSetOldRemito)) {
                    remitoSetOldRemito.setEstado(null);
                    remitoSetOldRemito = em.merge(remitoSetOldRemito);
                }
            }
            for (Remito remitoSetNewRemito : remitoSetNew) {
                if (!remitoSetOld.contains(remitoSetNewRemito)) {
                    Estadoremito oldEstadoOfRemitoSetNewRemito = remitoSetNewRemito.getEstado();
                    remitoSetNewRemito.setEstado(estadoremito);
                    remitoSetNewRemito = em.merge(remitoSetNewRemito);
                    if (oldEstadoOfRemitoSetNewRemito != null && !oldEstadoOfRemitoSetNewRemito.equals(estadoremito)) {
                        oldEstadoOfRemitoSetNewRemito.getRemitoSet().remove(remitoSetNewRemito);
                        oldEstadoOfRemitoSetNewRemito = em.merge(oldEstadoOfRemitoSetNewRemito);
                    }
                }
            }
            for (Remito remitoSet1OldRemito : remitoSet1Old) {
                if (!remitoSet1New.contains(remitoSet1OldRemito)) {
                    remitoSet1OldRemito.setEstado1(null);
                    remitoSet1OldRemito = em.merge(remitoSet1OldRemito);
                }
            }
            for (Remito remitoSet1NewRemito : remitoSet1New) {
                if (!remitoSet1Old.contains(remitoSet1NewRemito)) {
                    Estadoremito oldEstado1OfRemitoSet1NewRemito = remitoSet1NewRemito.getEstado1();
                    remitoSet1NewRemito.setEstado1(estadoremito);
                    remitoSet1NewRemito = em.merge(remitoSet1NewRemito);
                    if (oldEstado1OfRemitoSet1NewRemito != null && !oldEstado1OfRemitoSet1NewRemito.equals(estadoremito)) {
                        oldEstado1OfRemitoSet1NewRemito.getRemitoSet1().remove(remitoSet1NewRemito);
                        oldEstado1OfRemitoSet1NewRemito = em.merge(oldEstado1OfRemitoSet1NewRemito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoremito.getIdestado();
                if (findEstadoremito(id) == null) {
                    throw new NonexistentEntityException("The estadoremito with id " + id + " no longer exists.");
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
            Estadoremito estadoremito;
            try {
                estadoremito = em.getReference(Estadoremito.class, id);
                estadoremito.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoremito with id " + id + " no longer exists.", enfe);
            }
            Set<Remito> remitoSet = estadoremito.getRemitoSet();
            for (Remito remitoSetRemito : remitoSet) {
                remitoSetRemito.setEstado(null);
                remitoSetRemito = em.merge(remitoSetRemito);
            }
            Set<Remito> remitoSet1 = estadoremito.getRemitoSet1();
            for (Remito remitoSet1Remito : remitoSet1) {
                remitoSet1Remito.setEstado1(null);
                remitoSet1Remito = em.merge(remitoSet1Remito);
            }
            em.remove(estadoremito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoremito> findEstadoremitoEntities() {
        return findEstadoremitoEntities(true, -1, -1);
    }

    public List<Estadoremito> findEstadoremitoEntities(int maxResults, int firstResult) {
        return findEstadoremitoEntities(false, maxResults, firstResult);
    }

    private List<Estadoremito> findEstadoremitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoremito.class));
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

    public Estadoremito findEstadoremito(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoremito.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoremitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoremito> rt = cq.from(Estadoremito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
