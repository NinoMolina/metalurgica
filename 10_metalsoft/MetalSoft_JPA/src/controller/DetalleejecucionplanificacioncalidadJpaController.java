/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleejecucionplanificacioncalidad;
import entity.DetalleejecucionplanificacioncalidadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionplanificacioncalidad;
import entity.Ejecucionprocesocalidad;
import entity.Detalleplanificacioncalidad;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class DetalleejecucionplanificacioncalidadJpaController {

    public DetalleejecucionplanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) throws PreexistingEntityException, Exception {
        if (detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK() == null) {
            detalleejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadPK(new DetalleejecucionplanificacioncalidadPK());
        }
        if (detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet() == null) {
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadSet(new HashSet<Detalleplanificacioncalidad>());
        }
        detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK().setIdplanificacioncalidad(detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad().getEjecucionplanificacioncalidadPK().getIdplanificacioncalidad());
        detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK().setIdejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad().getEjecucionplanificacioncalidadPK().getIdejecucion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad = em.getReference(ejecucionplanificacioncalidad.getClass(), ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad = em.getReference(ejecucionprocesocalidad.getClass(), ejecucionprocesocalidad.getEjecucionprocesocalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);
            }
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach : detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet()) {
                detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet.add(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadSet(attachedDetalleplanificacioncalidadSet);
            em.persist(detalleejecucionplanificacioncalidad);
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            }
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidad : detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet()) {
                Detalleejecucionplanificacioncalidad oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad = detalleplanificacioncalidadSetDetalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
                detalleplanificacioncalidadSetDetalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad);
                detalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
                if (oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad != null) {
                    oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
                    oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK()) != null) {
                throw new PreexistingEntityException("Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK().setIdplanificacioncalidad(detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad().getEjecucionplanificacioncalidadPK().getIdplanificacioncalidad());
        detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK().setIdejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad().getEjecucionplanificacioncalidadPK().getIdejecucion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleejecucionplanificacioncalidad persistentDetalleejecucionplanificacioncalidad = em.find(Detalleejecucionplanificacioncalidad.class, detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK());
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadOld = persistentDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadNew = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            Ejecucionprocesocalidad ejecucionprocesocalidadOld = persistentDetalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            Ejecucionprocesocalidad ejecucionprocesocalidadNew = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOld = persistentDetalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetNew = detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetOldDetalleplanificacioncalidad : detalleplanificacioncalidadSetOld) {
                if (!detalleplanificacioncalidadSetNew.contains(detalleplanificacioncalidadSetOldDetalleplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacioncalidad " + detalleplanificacioncalidadSetOldDetalleplanificacioncalidad + " since its detalleejecucionplanificacioncalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ejecucionplanificacioncalidadNew != null) {
                ejecucionplanificacioncalidadNew = em.getReference(ejecucionplanificacioncalidadNew.getClass(), ejecucionplanificacioncalidadNew.getEjecucionplanificacioncalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidadNew);
            }
            if (ejecucionprocesocalidadNew != null) {
                ejecucionprocesocalidadNew = em.getReference(ejecucionprocesocalidadNew.getClass(), ejecucionprocesocalidadNew.getEjecucionprocesocalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(ejecucionprocesocalidadNew);
            }
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSetNew = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSetNew) {
                detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSetNew.add(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSetNew = attachedDetalleplanificacioncalidadSetNew;
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadSet(detalleplanificacioncalidadSetNew);
            detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidad);
            if (ejecucionplanificacioncalidadOld != null && !ejecucionplanificacioncalidadOld.equals(ejecucionplanificacioncalidadNew)) {
                ejecucionplanificacioncalidadOld.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidadOld = em.merge(ejecucionplanificacioncalidadOld);
            }
            if (ejecucionplanificacioncalidadNew != null && !ejecucionplanificacioncalidadNew.equals(ejecucionplanificacioncalidadOld)) {
                ejecucionplanificacioncalidadNew.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidadNew = em.merge(ejecucionplanificacioncalidadNew);
            }
            if (ejecucionprocesocalidadOld != null && !ejecucionprocesocalidadOld.equals(ejecucionprocesocalidadNew)) {
                ejecucionprocesocalidadOld.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidadOld = em.merge(ejecucionprocesocalidadOld);
            }
            if (ejecucionprocesocalidadNew != null && !ejecucionprocesocalidadNew.equals(ejecucionprocesocalidadOld)) {
                ejecucionprocesocalidadNew.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidadNew = em.merge(ejecucionprocesocalidadNew);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidad : detalleplanificacioncalidadSetNew) {
                if (!detalleplanificacioncalidadSetOld.contains(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad)) {
                    Detalleejecucionplanificacioncalidad oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad = detalleplanificacioncalidadSetNewDetalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
                    detalleplanificacioncalidadSetNewDetalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad);
                    detalleplanificacioncalidadSetNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                    if (oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad != null && !oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad.equals(detalleejecucionplanificacioncalidad)) {
                        oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                        oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad = em.merge(oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleejecucionplanificacioncalidadPK id = detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK();
                if (findDetalleejecucionplanificacioncalidad(id) == null) {
                    throw new NonexistentEntityException("The detalleejecucionplanificacioncalidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleejecucionplanificacioncalidadPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad;
            try {
                detalleejecucionplanificacioncalidad = em.getReference(Detalleejecucionplanificacioncalidad.class, id);
                detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleejecucionplanificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOrphanCheck = detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetOrphanCheckDetalleplanificacioncalidad : detalleplanificacioncalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detalleejecucionplanificacioncalidad (" + detalleejecucionplanificacioncalidad + ") cannot be destroyed since the Detalleplanificacioncalidad " + detalleplanificacioncalidadSetOrphanCheckDetalleplanificacioncalidad + " in its detalleplanificacioncalidadSet field has a non-nullable detalleejecucionplanificacioncalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            }
            em.remove(detalleejecucionplanificacioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleejecucionplanificacioncalidad> findDetalleejecucionplanificacioncalidadEntities() {
        return findDetalleejecucionplanificacioncalidadEntities(true, -1, -1);
    }

    public List<Detalleejecucionplanificacioncalidad> findDetalleejecucionplanificacioncalidadEntities(int maxResults, int firstResult) {
        return findDetalleejecucionplanificacioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Detalleejecucionplanificacioncalidad> findDetalleejecucionplanificacioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleejecucionplanificacioncalidad.class));
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

    public Detalleejecucionplanificacioncalidad findDetalleejecucionplanificacioncalidad(DetalleejecucionplanificacioncalidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleejecucionplanificacioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleejecucionplanificacioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleejecucionplanificacioncalidad> rt = cq.from(Detalleejecucionplanificacioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
