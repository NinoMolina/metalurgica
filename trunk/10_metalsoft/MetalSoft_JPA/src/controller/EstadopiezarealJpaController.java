/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadopiezareal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Piezareal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadopiezarealJpaController {

    public EstadopiezarealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadopiezareal estadopiezareal) throws PreexistingEntityException, Exception {
        if (estadopiezareal.getPiezarealSet() == null) {
            estadopiezareal.setPiezarealSet(new HashSet<Piezareal>());
        }
        if (estadopiezareal.getPiezarealSet1() == null) {
            estadopiezareal.setPiezarealSet1(new HashSet<Piezareal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Piezareal> attachedPiezarealSet = new HashSet<Piezareal>();
            for (Piezareal piezarealSetPiezarealToAttach : estadopiezareal.getPiezarealSet()) {
                piezarealSetPiezarealToAttach = em.getReference(piezarealSetPiezarealToAttach.getClass(), piezarealSetPiezarealToAttach.getIdpiezareal());
                attachedPiezarealSet.add(piezarealSetPiezarealToAttach);
            }
            estadopiezareal.setPiezarealSet(attachedPiezarealSet);
            Set<Piezareal> attachedPiezarealSet1 = new HashSet<Piezareal>();
            for (Piezareal piezarealSet1PiezarealToAttach : estadopiezareal.getPiezarealSet1()) {
                piezarealSet1PiezarealToAttach = em.getReference(piezarealSet1PiezarealToAttach.getClass(), piezarealSet1PiezarealToAttach.getIdpiezareal());
                attachedPiezarealSet1.add(piezarealSet1PiezarealToAttach);
            }
            estadopiezareal.setPiezarealSet1(attachedPiezarealSet1);
            em.persist(estadopiezareal);
            for (Piezareal piezarealSetPiezareal : estadopiezareal.getPiezarealSet()) {
                Estadopiezareal oldEstadoOfPiezarealSetPiezareal = piezarealSetPiezareal.getEstado();
                piezarealSetPiezareal.setEstado(estadopiezareal);
                piezarealSetPiezareal = em.merge(piezarealSetPiezareal);
                if (oldEstadoOfPiezarealSetPiezareal != null) {
                    oldEstadoOfPiezarealSetPiezareal.getPiezarealSet().remove(piezarealSetPiezareal);
                    oldEstadoOfPiezarealSetPiezareal = em.merge(oldEstadoOfPiezarealSetPiezareal);
                }
            }
            for (Piezareal piezarealSet1Piezareal : estadopiezareal.getPiezarealSet1()) {
                Estadopiezareal oldEstado1OfPiezarealSet1Piezareal = piezarealSet1Piezareal.getEstado1();
                piezarealSet1Piezareal.setEstado1(estadopiezareal);
                piezarealSet1Piezareal = em.merge(piezarealSet1Piezareal);
                if (oldEstado1OfPiezarealSet1Piezareal != null) {
                    oldEstado1OfPiezarealSet1Piezareal.getPiezarealSet1().remove(piezarealSet1Piezareal);
                    oldEstado1OfPiezarealSet1Piezareal = em.merge(oldEstado1OfPiezarealSet1Piezareal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadopiezareal(estadopiezareal.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadopiezareal " + estadopiezareal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadopiezareal estadopiezareal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopiezareal persistentEstadopiezareal = em.find(Estadopiezareal.class, estadopiezareal.getIdestado());
            Set<Piezareal> piezarealSetOld = persistentEstadopiezareal.getPiezarealSet();
            Set<Piezareal> piezarealSetNew = estadopiezareal.getPiezarealSet();
            Set<Piezareal> piezarealSet1Old = persistentEstadopiezareal.getPiezarealSet1();
            Set<Piezareal> piezarealSet1New = estadopiezareal.getPiezarealSet1();
            Set<Piezareal> attachedPiezarealSetNew = new HashSet<Piezareal>();
            for (Piezareal piezarealSetNewPiezarealToAttach : piezarealSetNew) {
                piezarealSetNewPiezarealToAttach = em.getReference(piezarealSetNewPiezarealToAttach.getClass(), piezarealSetNewPiezarealToAttach.getIdpiezareal());
                attachedPiezarealSetNew.add(piezarealSetNewPiezarealToAttach);
            }
            piezarealSetNew = attachedPiezarealSetNew;
            estadopiezareal.setPiezarealSet(piezarealSetNew);
            Set<Piezareal> attachedPiezarealSet1New = new HashSet<Piezareal>();
            for (Piezareal piezarealSet1NewPiezarealToAttach : piezarealSet1New) {
                piezarealSet1NewPiezarealToAttach = em.getReference(piezarealSet1NewPiezarealToAttach.getClass(), piezarealSet1NewPiezarealToAttach.getIdpiezareal());
                attachedPiezarealSet1New.add(piezarealSet1NewPiezarealToAttach);
            }
            piezarealSet1New = attachedPiezarealSet1New;
            estadopiezareal.setPiezarealSet1(piezarealSet1New);
            estadopiezareal = em.merge(estadopiezareal);
            for (Piezareal piezarealSetOldPiezareal : piezarealSetOld) {
                if (!piezarealSetNew.contains(piezarealSetOldPiezareal)) {
                    piezarealSetOldPiezareal.setEstado(null);
                    piezarealSetOldPiezareal = em.merge(piezarealSetOldPiezareal);
                }
            }
            for (Piezareal piezarealSetNewPiezareal : piezarealSetNew) {
                if (!piezarealSetOld.contains(piezarealSetNewPiezareal)) {
                    Estadopiezareal oldEstadoOfPiezarealSetNewPiezareal = piezarealSetNewPiezareal.getEstado();
                    piezarealSetNewPiezareal.setEstado(estadopiezareal);
                    piezarealSetNewPiezareal = em.merge(piezarealSetNewPiezareal);
                    if (oldEstadoOfPiezarealSetNewPiezareal != null && !oldEstadoOfPiezarealSetNewPiezareal.equals(estadopiezareal)) {
                        oldEstadoOfPiezarealSetNewPiezareal.getPiezarealSet().remove(piezarealSetNewPiezareal);
                        oldEstadoOfPiezarealSetNewPiezareal = em.merge(oldEstadoOfPiezarealSetNewPiezareal);
                    }
                }
            }
            for (Piezareal piezarealSet1OldPiezareal : piezarealSet1Old) {
                if (!piezarealSet1New.contains(piezarealSet1OldPiezareal)) {
                    piezarealSet1OldPiezareal.setEstado1(null);
                    piezarealSet1OldPiezareal = em.merge(piezarealSet1OldPiezareal);
                }
            }
            for (Piezareal piezarealSet1NewPiezareal : piezarealSet1New) {
                if (!piezarealSet1Old.contains(piezarealSet1NewPiezareal)) {
                    Estadopiezareal oldEstado1OfPiezarealSet1NewPiezareal = piezarealSet1NewPiezareal.getEstado1();
                    piezarealSet1NewPiezareal.setEstado1(estadopiezareal);
                    piezarealSet1NewPiezareal = em.merge(piezarealSet1NewPiezareal);
                    if (oldEstado1OfPiezarealSet1NewPiezareal != null && !oldEstado1OfPiezarealSet1NewPiezareal.equals(estadopiezareal)) {
                        oldEstado1OfPiezarealSet1NewPiezareal.getPiezarealSet1().remove(piezarealSet1NewPiezareal);
                        oldEstado1OfPiezarealSet1NewPiezareal = em.merge(oldEstado1OfPiezarealSet1NewPiezareal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadopiezareal.getIdestado();
                if (findEstadopiezareal(id) == null) {
                    throw new NonexistentEntityException("The estadopiezareal with id " + id + " no longer exists.");
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
            Estadopiezareal estadopiezareal;
            try {
                estadopiezareal = em.getReference(Estadopiezareal.class, id);
                estadopiezareal.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadopiezareal with id " + id + " no longer exists.", enfe);
            }
            Set<Piezareal> piezarealSet = estadopiezareal.getPiezarealSet();
            for (Piezareal piezarealSetPiezareal : piezarealSet) {
                piezarealSetPiezareal.setEstado(null);
                piezarealSetPiezareal = em.merge(piezarealSetPiezareal);
            }
            Set<Piezareal> piezarealSet1 = estadopiezareal.getPiezarealSet1();
            for (Piezareal piezarealSet1Piezareal : piezarealSet1) {
                piezarealSet1Piezareal.setEstado1(null);
                piezarealSet1Piezareal = em.merge(piezarealSet1Piezareal);
            }
            em.remove(estadopiezareal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadopiezareal> findEstadopiezarealEntities() {
        return findEstadopiezarealEntities(true, -1, -1);
    }

    public List<Estadopiezareal> findEstadopiezarealEntities(int maxResults, int firstResult) {
        return findEstadopiezarealEntities(false, maxResults, firstResult);
    }

    private List<Estadopiezareal> findEstadopiezarealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadopiezareal.class));
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

    public Estadopiezareal findEstadopiezareal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadopiezareal.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadopiezarealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadopiezareal> rt = cq.from(Estadopiezareal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
