/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallempasignada;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import entity.Planificacionproduccion;
import entity.Mpasignadaxpiezareal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class DetallempasignadaJpaController {

    public DetallempasignadaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallempasignada detallempasignada) throws PreexistingEntityException, Exception {
        if (detallempasignada.getMpasignadaxpiezarealSet() == null) {
            detallempasignada.setMpasignadaxpiezarealSet(new HashSet<Mpasignadaxpiezareal>());
        }
        if (detallempasignada.getMpasignadaxpiezarealSet1() == null) {
            detallempasignada.setMpasignadaxpiezarealSet1(new HashSet<Mpasignadaxpiezareal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima idmateriaprima = detallempasignada.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima = em.getReference(idmateriaprima.getClass(), idmateriaprima.getIdmateriaprima());
                detallempasignada.setIdmateriaprima(idmateriaprima);
            }
            Materiaprima idmateriaprima1 = detallempasignada.getIdmateriaprima1();
            if (idmateriaprima1 != null) {
                idmateriaprima1 = em.getReference(idmateriaprima1.getClass(), idmateriaprima1.getIdmateriaprima());
                detallempasignada.setIdmateriaprima1(idmateriaprima1);
            }
            Planificacionproduccion idplanificacionproduccion = detallempasignada.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion = em.getReference(idplanificacionproduccion.getClass(), idplanificacionproduccion.getIdplanificacionproduccion());
                detallempasignada.setIdplanificacionproduccion(idplanificacionproduccion);
            }
            Planificacionproduccion idplanificacionproduccion1 = detallempasignada.getIdplanificacionproduccion1();
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1 = em.getReference(idplanificacionproduccion1.getClass(), idplanificacionproduccion1.getIdplanificacionproduccion());
                detallempasignada.setIdplanificacionproduccion1(idplanificacionproduccion1);
            }
            Set<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealSet = new HashSet<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach : detallempasignada.getMpasignadaxpiezarealSet()) {
                mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealSet.add(mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach);
            }
            detallempasignada.setMpasignadaxpiezarealSet(attachedMpasignadaxpiezarealSet);
            Set<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealSet1 = new HashSet<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSet1MpasignadaxpiezarealToAttach : detallempasignada.getMpasignadaxpiezarealSet1()) {
                mpasignadaxpiezarealSet1MpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealSet1MpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealSet1MpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealSet1.add(mpasignadaxpiezarealSet1MpasignadaxpiezarealToAttach);
            }
            detallempasignada.setMpasignadaxpiezarealSet1(attachedMpasignadaxpiezarealSet1);
            em.persist(detallempasignada);
            if (idmateriaprima != null) {
                idmateriaprima.getDetallempasignadaSet().add(detallempasignada);
                idmateriaprima = em.merge(idmateriaprima);
            }
            if (idmateriaprima1 != null) {
                idmateriaprima1.getDetallempasignadaSet().add(detallempasignada);
                idmateriaprima1 = em.merge(idmateriaprima1);
            }
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetallempasignadaSet().add(detallempasignada);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1.getDetallempasignadaSet().add(detallempasignada);
                idplanificacionproduccion1 = em.merge(idplanificacionproduccion1);
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetMpasignadaxpiezareal : detallempasignada.getMpasignadaxpiezarealSet()) {
                Detallempasignada oldIddetallempasignadaOfMpasignadaxpiezarealSetMpasignadaxpiezareal = mpasignadaxpiezarealSetMpasignadaxpiezareal.getIddetallempasignada();
                mpasignadaxpiezarealSetMpasignadaxpiezareal.setIddetallempasignada(detallempasignada);
                mpasignadaxpiezarealSetMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSetMpasignadaxpiezareal);
                if (oldIddetallempasignadaOfMpasignadaxpiezarealSetMpasignadaxpiezareal != null) {
                    oldIddetallempasignadaOfMpasignadaxpiezarealSetMpasignadaxpiezareal.getMpasignadaxpiezarealSet().remove(mpasignadaxpiezarealSetMpasignadaxpiezareal);
                    oldIddetallempasignadaOfMpasignadaxpiezarealSetMpasignadaxpiezareal = em.merge(oldIddetallempasignadaOfMpasignadaxpiezarealSetMpasignadaxpiezareal);
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSet1Mpasignadaxpiezareal : detallempasignada.getMpasignadaxpiezarealSet1()) {
                Detallempasignada oldIddetallempasignada1OfMpasignadaxpiezarealSet1Mpasignadaxpiezareal = mpasignadaxpiezarealSet1Mpasignadaxpiezareal.getIddetallempasignada1();
                mpasignadaxpiezarealSet1Mpasignadaxpiezareal.setIddetallempasignada1(detallempasignada);
                mpasignadaxpiezarealSet1Mpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSet1Mpasignadaxpiezareal);
                if (oldIddetallempasignada1OfMpasignadaxpiezarealSet1Mpasignadaxpiezareal != null) {
                    oldIddetallempasignada1OfMpasignadaxpiezarealSet1Mpasignadaxpiezareal.getMpasignadaxpiezarealSet1().remove(mpasignadaxpiezarealSet1Mpasignadaxpiezareal);
                    oldIddetallempasignada1OfMpasignadaxpiezarealSet1Mpasignadaxpiezareal = em.merge(oldIddetallempasignada1OfMpasignadaxpiezarealSet1Mpasignadaxpiezareal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallempasignada(detallempasignada.getId()) != null) {
                throw new PreexistingEntityException("Detallempasignada " + detallempasignada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallempasignada detallempasignada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallempasignada persistentDetallempasignada = em.find(Detallempasignada.class, detallempasignada.getId());
            Materiaprima idmateriaprimaOld = persistentDetallempasignada.getIdmateriaprima();
            Materiaprima idmateriaprimaNew = detallempasignada.getIdmateriaprima();
            Materiaprima idmateriaprima1Old = persistentDetallempasignada.getIdmateriaprima1();
            Materiaprima idmateriaprima1New = detallempasignada.getIdmateriaprima1();
            Planificacionproduccion idplanificacionproduccionOld = persistentDetallempasignada.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccionNew = detallempasignada.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccion1Old = persistentDetallempasignada.getIdplanificacionproduccion1();
            Planificacionproduccion idplanificacionproduccion1New = detallempasignada.getIdplanificacionproduccion1();
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSetOld = persistentDetallempasignada.getMpasignadaxpiezarealSet();
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSetNew = detallempasignada.getMpasignadaxpiezarealSet();
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet1Old = persistentDetallempasignada.getMpasignadaxpiezarealSet1();
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet1New = detallempasignada.getMpasignadaxpiezarealSet1();
            if (idmateriaprimaNew != null) {
                idmateriaprimaNew = em.getReference(idmateriaprimaNew.getClass(), idmateriaprimaNew.getIdmateriaprima());
                detallempasignada.setIdmateriaprima(idmateriaprimaNew);
            }
            if (idmateriaprima1New != null) {
                idmateriaprima1New = em.getReference(idmateriaprima1New.getClass(), idmateriaprima1New.getIdmateriaprima());
                detallempasignada.setIdmateriaprima1(idmateriaprima1New);
            }
            if (idplanificacionproduccionNew != null) {
                idplanificacionproduccionNew = em.getReference(idplanificacionproduccionNew.getClass(), idplanificacionproduccionNew.getIdplanificacionproduccion());
                detallempasignada.setIdplanificacionproduccion(idplanificacionproduccionNew);
            }
            if (idplanificacionproduccion1New != null) {
                idplanificacionproduccion1New = em.getReference(idplanificacionproduccion1New.getClass(), idplanificacionproduccion1New.getIdplanificacionproduccion());
                detallempasignada.setIdplanificacionproduccion1(idplanificacionproduccion1New);
            }
            Set<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealSetNew = new HashSet<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach : mpasignadaxpiezarealSetNew) {
                mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealSetNew.add(mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach);
            }
            mpasignadaxpiezarealSetNew = attachedMpasignadaxpiezarealSetNew;
            detallempasignada.setMpasignadaxpiezarealSet(mpasignadaxpiezarealSetNew);
            Set<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealSet1New = new HashSet<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSet1NewMpasignadaxpiezarealToAttach : mpasignadaxpiezarealSet1New) {
                mpasignadaxpiezarealSet1NewMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealSet1NewMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealSet1NewMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealSet1New.add(mpasignadaxpiezarealSet1NewMpasignadaxpiezarealToAttach);
            }
            mpasignadaxpiezarealSet1New = attachedMpasignadaxpiezarealSet1New;
            detallempasignada.setMpasignadaxpiezarealSet1(mpasignadaxpiezarealSet1New);
            detallempasignada = em.merge(detallempasignada);
            if (idmateriaprimaOld != null && !idmateriaprimaOld.equals(idmateriaprimaNew)) {
                idmateriaprimaOld.getDetallempasignadaSet().remove(detallempasignada);
                idmateriaprimaOld = em.merge(idmateriaprimaOld);
            }
            if (idmateriaprimaNew != null && !idmateriaprimaNew.equals(idmateriaprimaOld)) {
                idmateriaprimaNew.getDetallempasignadaSet().add(detallempasignada);
                idmateriaprimaNew = em.merge(idmateriaprimaNew);
            }
            if (idmateriaprima1Old != null && !idmateriaprima1Old.equals(idmateriaprima1New)) {
                idmateriaprima1Old.getDetallempasignadaSet().remove(detallempasignada);
                idmateriaprima1Old = em.merge(idmateriaprima1Old);
            }
            if (idmateriaprima1New != null && !idmateriaprima1New.equals(idmateriaprima1Old)) {
                idmateriaprima1New.getDetallempasignadaSet().add(detallempasignada);
                idmateriaprima1New = em.merge(idmateriaprima1New);
            }
            if (idplanificacionproduccionOld != null && !idplanificacionproduccionOld.equals(idplanificacionproduccionNew)) {
                idplanificacionproduccionOld.getDetallempasignadaSet().remove(detallempasignada);
                idplanificacionproduccionOld = em.merge(idplanificacionproduccionOld);
            }
            if (idplanificacionproduccionNew != null && !idplanificacionproduccionNew.equals(idplanificacionproduccionOld)) {
                idplanificacionproduccionNew.getDetallempasignadaSet().add(detallempasignada);
                idplanificacionproduccionNew = em.merge(idplanificacionproduccionNew);
            }
            if (idplanificacionproduccion1Old != null && !idplanificacionproduccion1Old.equals(idplanificacionproduccion1New)) {
                idplanificacionproduccion1Old.getDetallempasignadaSet().remove(detallempasignada);
                idplanificacionproduccion1Old = em.merge(idplanificacionproduccion1Old);
            }
            if (idplanificacionproduccion1New != null && !idplanificacionproduccion1New.equals(idplanificacionproduccion1Old)) {
                idplanificacionproduccion1New.getDetallempasignadaSet().add(detallempasignada);
                idplanificacionproduccion1New = em.merge(idplanificacionproduccion1New);
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetOldMpasignadaxpiezareal : mpasignadaxpiezarealSetOld) {
                if (!mpasignadaxpiezarealSetNew.contains(mpasignadaxpiezarealSetOldMpasignadaxpiezareal)) {
                    mpasignadaxpiezarealSetOldMpasignadaxpiezareal.setIddetallempasignada(null);
                    mpasignadaxpiezarealSetOldMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSetOldMpasignadaxpiezareal);
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetNewMpasignadaxpiezareal : mpasignadaxpiezarealSetNew) {
                if (!mpasignadaxpiezarealSetOld.contains(mpasignadaxpiezarealSetNewMpasignadaxpiezareal)) {
                    Detallempasignada oldIddetallempasignadaOfMpasignadaxpiezarealSetNewMpasignadaxpiezareal = mpasignadaxpiezarealSetNewMpasignadaxpiezareal.getIddetallempasignada();
                    mpasignadaxpiezarealSetNewMpasignadaxpiezareal.setIddetallempasignada(detallempasignada);
                    mpasignadaxpiezarealSetNewMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSetNewMpasignadaxpiezareal);
                    if (oldIddetallempasignadaOfMpasignadaxpiezarealSetNewMpasignadaxpiezareal != null && !oldIddetallempasignadaOfMpasignadaxpiezarealSetNewMpasignadaxpiezareal.equals(detallempasignada)) {
                        oldIddetallempasignadaOfMpasignadaxpiezarealSetNewMpasignadaxpiezareal.getMpasignadaxpiezarealSet().remove(mpasignadaxpiezarealSetNewMpasignadaxpiezareal);
                        oldIddetallempasignadaOfMpasignadaxpiezarealSetNewMpasignadaxpiezareal = em.merge(oldIddetallempasignadaOfMpasignadaxpiezarealSetNewMpasignadaxpiezareal);
                    }
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSet1OldMpasignadaxpiezareal : mpasignadaxpiezarealSet1Old) {
                if (!mpasignadaxpiezarealSet1New.contains(mpasignadaxpiezarealSet1OldMpasignadaxpiezareal)) {
                    mpasignadaxpiezarealSet1OldMpasignadaxpiezareal.setIddetallempasignada1(null);
                    mpasignadaxpiezarealSet1OldMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSet1OldMpasignadaxpiezareal);
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSet1NewMpasignadaxpiezareal : mpasignadaxpiezarealSet1New) {
                if (!mpasignadaxpiezarealSet1Old.contains(mpasignadaxpiezarealSet1NewMpasignadaxpiezareal)) {
                    Detallempasignada oldIddetallempasignada1OfMpasignadaxpiezarealSet1NewMpasignadaxpiezareal = mpasignadaxpiezarealSet1NewMpasignadaxpiezareal.getIddetallempasignada1();
                    mpasignadaxpiezarealSet1NewMpasignadaxpiezareal.setIddetallempasignada1(detallempasignada);
                    mpasignadaxpiezarealSet1NewMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSet1NewMpasignadaxpiezareal);
                    if (oldIddetallempasignada1OfMpasignadaxpiezarealSet1NewMpasignadaxpiezareal != null && !oldIddetallempasignada1OfMpasignadaxpiezarealSet1NewMpasignadaxpiezareal.equals(detallempasignada)) {
                        oldIddetallempasignada1OfMpasignadaxpiezarealSet1NewMpasignadaxpiezareal.getMpasignadaxpiezarealSet1().remove(mpasignadaxpiezarealSet1NewMpasignadaxpiezareal);
                        oldIddetallempasignada1OfMpasignadaxpiezarealSet1NewMpasignadaxpiezareal = em.merge(oldIddetallempasignada1OfMpasignadaxpiezarealSet1NewMpasignadaxpiezareal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallempasignada.getId();
                if (findDetallempasignada(id) == null) {
                    throw new NonexistentEntityException("The detallempasignada with id " + id + " no longer exists.");
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
            Detallempasignada detallempasignada;
            try {
                detallempasignada = em.getReference(Detallempasignada.class, id);
                detallempasignada.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallempasignada with id " + id + " no longer exists.", enfe);
            }
            Materiaprima idmateriaprima = detallempasignada.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima.getDetallempasignadaSet().remove(detallempasignada);
                idmateriaprima = em.merge(idmateriaprima);
            }
            Materiaprima idmateriaprima1 = detallempasignada.getIdmateriaprima1();
            if (idmateriaprima1 != null) {
                idmateriaprima1.getDetallempasignadaSet().remove(detallempasignada);
                idmateriaprima1 = em.merge(idmateriaprima1);
            }
            Planificacionproduccion idplanificacionproduccion = detallempasignada.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetallempasignadaSet().remove(detallempasignada);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            Planificacionproduccion idplanificacionproduccion1 = detallempasignada.getIdplanificacionproduccion1();
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1.getDetallempasignadaSet().remove(detallempasignada);
                idplanificacionproduccion1 = em.merge(idplanificacionproduccion1);
            }
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet = detallempasignada.getMpasignadaxpiezarealSet();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetMpasignadaxpiezareal : mpasignadaxpiezarealSet) {
                mpasignadaxpiezarealSetMpasignadaxpiezareal.setIddetallempasignada(null);
                mpasignadaxpiezarealSetMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSetMpasignadaxpiezareal);
            }
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet1 = detallempasignada.getMpasignadaxpiezarealSet1();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSet1Mpasignadaxpiezareal : mpasignadaxpiezarealSet1) {
                mpasignadaxpiezarealSet1Mpasignadaxpiezareal.setIddetallempasignada1(null);
                mpasignadaxpiezarealSet1Mpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSet1Mpasignadaxpiezareal);
            }
            em.remove(detallempasignada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallempasignada> findDetallempasignadaEntities() {
        return findDetallempasignadaEntities(true, -1, -1);
    }

    public List<Detallempasignada> findDetallempasignadaEntities(int maxResults, int firstResult) {
        return findDetallempasignadaEntities(false, maxResults, firstResult);
    }

    private List<Detallempasignada> findDetallempasignadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallempasignada.class));
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

    public Detallempasignada findDetallempasignada(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallempasignada.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallempasignadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallempasignada> rt = cq.from(Detallempasignada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
