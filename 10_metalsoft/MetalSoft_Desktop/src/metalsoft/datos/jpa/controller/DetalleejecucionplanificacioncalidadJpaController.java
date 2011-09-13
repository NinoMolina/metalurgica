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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.DetalleejecucionplanificacioncalidadPK;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionprocesocalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class DetalleejecucionplanificacioncalidadJpaController {

    public DetalleejecucionplanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) throws PreexistingEntityException, Exception {
        if (detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK() == null) {
            detalleejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadPK(new DetalleejecucionplanificacioncalidadPK());
        }
        if (detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadList() == null) {
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadList(new ArrayList<Detalleplanificacioncalidad>());
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
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadList = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach : detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadList()) {
                detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadList.add(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadList(attachedDetalleplanificacioncalidadList);
            em.persist(detalleejecucionplanificacioncalidad);
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            }
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadList()) {
                Detalleejecucionplanificacioncalidad oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = detalleplanificacioncalidadListDetalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                if (oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad != null) {
                    oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                    oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad);
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
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOld = persistentDetalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListNew = detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadList();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOldDetalleplanificacioncalidad : detalleplanificacioncalidadListOld) {
                if (!detalleplanificacioncalidadListNew.contains(detalleplanificacioncalidadListOldDetalleplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacioncalidad " + detalleplanificacioncalidadListOldDetalleplanificacioncalidad + " since its detalleejecucionplanificacioncalidad field is not nullable.");
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
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadListNew = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadListNew) {
                detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadListNew.add(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadListNew = attachedDetalleplanificacioncalidadListNew;
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadList(detalleplanificacioncalidadListNew);
            detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidad);
            if (ejecucionplanificacioncalidadOld != null && !ejecucionplanificacioncalidadOld.equals(ejecucionplanificacioncalidadNew)) {
                ejecucionplanificacioncalidadOld.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidadOld = em.merge(ejecucionplanificacioncalidadOld);
            }
            if (ejecucionplanificacioncalidadNew != null && !ejecucionplanificacioncalidadNew.equals(ejecucionplanificacioncalidadOld)) {
                ejecucionplanificacioncalidadNew.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidadNew = em.merge(ejecucionplanificacioncalidadNew);
            }
            if (ejecucionprocesocalidadOld != null && !ejecucionprocesocalidadOld.equals(ejecucionprocesocalidadNew)) {
                ejecucionprocesocalidadOld.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidadOld = em.merge(ejecucionprocesocalidadOld);
            }
            if (ejecucionprocesocalidadNew != null && !ejecucionprocesocalidadNew.equals(ejecucionprocesocalidadOld)) {
                ejecucionprocesocalidadNew.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidadNew = em.merge(ejecucionprocesocalidadNew);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidad : detalleplanificacioncalidadListNew) {
                if (!detalleplanificacioncalidadListOld.contains(detalleplanificacioncalidadListNewDetalleplanificacioncalidad)) {
                    Detalleejecucionplanificacioncalidad oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = detalleplanificacioncalidadListNewDetalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad);
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    if (oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad != null && !oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.equals(detalleejecucionplanificacioncalidad)) {
                        oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                        oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(oldDetalleejecucionplanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad);
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
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOrphanCheck = detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadList();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOrphanCheckDetalleplanificacioncalidad : detalleplanificacioncalidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detalleejecucionplanificacioncalidad (" + detalleejecucionplanificacioncalidad + ") cannot be destroyed since the Detalleplanificacioncalidad " + detalleplanificacioncalidadListOrphanCheckDetalleplanificacioncalidad + " in its detalleplanificacioncalidadList field has a non-nullable detalleejecucionplanificacioncalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
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
