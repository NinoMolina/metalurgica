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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima idmateriaprima = detallempasignada.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima = em.getReference(idmateriaprima.getClass(), idmateriaprima.getIdmateriaprima());
                detallempasignada.setIdmateriaprima(idmateriaprima);
            }
            Planificacionproduccion idplanificacionproduccion = detallempasignada.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion = em.getReference(idplanificacionproduccion.getClass(), idplanificacionproduccion.getIdplanificacionproduccion());
                detallempasignada.setIdplanificacionproduccion(idplanificacionproduccion);
            }
            Set<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealSet = new HashSet<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach : detallempasignada.getMpasignadaxpiezarealSet()) {
                mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealSet.add(mpasignadaxpiezarealSetMpasignadaxpiezarealToAttach);
            }
            detallempasignada.setMpasignadaxpiezarealSet(attachedMpasignadaxpiezarealSet);
            em.persist(detallempasignada);
            if (idmateriaprima != null) {
                idmateriaprima.getDetallempasignadaSet().add(detallempasignada);
                idmateriaprima = em.merge(idmateriaprima);
            }
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetallempasignadaSet().add(detallempasignada);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
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
            Planificacionproduccion idplanificacionproduccionOld = persistentDetallempasignada.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccionNew = detallempasignada.getIdplanificacionproduccion();
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSetOld = persistentDetallempasignada.getMpasignadaxpiezarealSet();
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSetNew = detallempasignada.getMpasignadaxpiezarealSet();
            if (idmateriaprimaNew != null) {
                idmateriaprimaNew = em.getReference(idmateriaprimaNew.getClass(), idmateriaprimaNew.getIdmateriaprima());
                detallempasignada.setIdmateriaprima(idmateriaprimaNew);
            }
            if (idplanificacionproduccionNew != null) {
                idplanificacionproduccionNew = em.getReference(idplanificacionproduccionNew.getClass(), idplanificacionproduccionNew.getIdplanificacionproduccion());
                detallempasignada.setIdplanificacionproduccion(idplanificacionproduccionNew);
            }
            Set<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealSetNew = new HashSet<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach : mpasignadaxpiezarealSetNew) {
                mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealSetNew.add(mpasignadaxpiezarealSetNewMpasignadaxpiezarealToAttach);
            }
            mpasignadaxpiezarealSetNew = attachedMpasignadaxpiezarealSetNew;
            detallempasignada.setMpasignadaxpiezarealSet(mpasignadaxpiezarealSetNew);
            detallempasignada = em.merge(detallempasignada);
            if (idmateriaprimaOld != null && !idmateriaprimaOld.equals(idmateriaprimaNew)) {
                idmateriaprimaOld.getDetallempasignadaSet().remove(detallempasignada);
                idmateriaprimaOld = em.merge(idmateriaprimaOld);
            }
            if (idmateriaprimaNew != null && !idmateriaprimaNew.equals(idmateriaprimaOld)) {
                idmateriaprimaNew.getDetallempasignadaSet().add(detallempasignada);
                idmateriaprimaNew = em.merge(idmateriaprimaNew);
            }
            if (idplanificacionproduccionOld != null && !idplanificacionproduccionOld.equals(idplanificacionproduccionNew)) {
                idplanificacionproduccionOld.getDetallempasignadaSet().remove(detallempasignada);
                idplanificacionproduccionOld = em.merge(idplanificacionproduccionOld);
            }
            if (idplanificacionproduccionNew != null && !idplanificacionproduccionNew.equals(idplanificacionproduccionOld)) {
                idplanificacionproduccionNew.getDetallempasignadaSet().add(detallempasignada);
                idplanificacionproduccionNew = em.merge(idplanificacionproduccionNew);
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
            Planificacionproduccion idplanificacionproduccion = detallempasignada.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetallempasignadaSet().remove(detallempasignada);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet = detallempasignada.getMpasignadaxpiezarealSet();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealSetMpasignadaxpiezareal : mpasignadaxpiezarealSet) {
                mpasignadaxpiezarealSetMpasignadaxpiezareal.setIddetallempasignada(null);
                mpasignadaxpiezarealSetMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealSetMpasignadaxpiezareal);
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
