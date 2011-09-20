/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Estadoejecplancalidad;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EjecucionplanificacioncalidadJpaController implements Serializable {

    public EjecucionplanificacioncalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList() == null) {
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadList(new ArrayList<Detalleejecucionplanificacioncalidad>());
        }
        List<String> illegalOrphanMessages = null;
        Planificacioncalidad idplanificacioncalidadOrphanCheck = ejecucionplanificacioncalidad.getIdplanificacioncalidad();
        if (idplanificacioncalidadOrphanCheck != null) {
            Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfIdplanificacioncalidad = idplanificacioncalidadOrphanCheck.getEjecucionplanificacioncalidad();
            if (oldEjecucionplanificacioncalidadOfIdplanificacioncalidad != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Planificacioncalidad " + idplanificacioncalidadOrphanCheck + " already has an item of type Ejecucionplanificacioncalidad whose idplanificacioncalidad column cannot be null. Please make another selection for the idplanificacioncalidad field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacioncalidad idplanificacioncalidad = ejecucionplanificacioncalidad.getIdplanificacioncalidad();
            if (idplanificacioncalidad != null) {
                idplanificacioncalidad = em.getReference(idplanificacioncalidad.getClass(), idplanificacioncalidad.getIdplanificacion());
                ejecucionplanificacioncalidad.setIdplanificacioncalidad(idplanificacioncalidad);
            }
            Estadoejecplancalidad estado = ejecucionplanificacioncalidad.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionplanificacioncalidad.setEstado(estado);
            }
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadList = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList()) {
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleejecucionplanificacioncalidadList.add(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach);
            }
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadList(attachedDetalleejecucionplanificacioncalidadList);
            em.persist(ejecucionplanificacioncalidad);
            if (idplanificacioncalidad != null) {
                idplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                idplanificacioncalidad = em.merge(idplanificacioncalidad);
            }
            if (estado != null) {
                estado.getEjecucionplanificacioncalidadList().add(ejecucionplanificacioncalidad);
                estado = em.merge(estado);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList()) {
                Ejecucionplanificacioncalidad oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad();
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.setIdejecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                if (oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad != null) {
                    oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                    oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEjecucionplanificacioncalidad(ejecucionplanificacioncalidad.getIdejecucion()) != null) {
                throw new PreexistingEntityException("Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad persistentEjecucionplanificacioncalidad = em.find(Ejecucionplanificacioncalidad.class, ejecucionplanificacioncalidad.getIdejecucion());
            Planificacioncalidad idplanificacioncalidadOld = persistentEjecucionplanificacioncalidad.getIdplanificacioncalidad();
            Planificacioncalidad idplanificacioncalidadNew = ejecucionplanificacioncalidad.getIdplanificacioncalidad();
            Estadoejecplancalidad estadoOld = persistentEjecucionplanificacioncalidad.getEstado();
            Estadoejecplancalidad estadoNew = ejecucionplanificacioncalidad.getEstado();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListOld = persistentEjecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListNew = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList();
            List<String> illegalOrphanMessages = null;
            if (idplanificacioncalidadNew != null && !idplanificacioncalidadNew.equals(idplanificacioncalidadOld)) {
                Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfIdplanificacioncalidad = idplanificacioncalidadNew.getEjecucionplanificacioncalidad();
                if (oldEjecucionplanificacioncalidadOfIdplanificacioncalidad != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Planificacioncalidad " + idplanificacioncalidadNew + " already has an item of type Ejecucionplanificacioncalidad whose idplanificacioncalidad column cannot be null. Please make another selection for the idplanificacioncalidad field.");
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListOld) {
                if (!detalleejecucionplanificacioncalidadListNew.contains(detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad + " since its idejecucionplanificacioncalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idplanificacioncalidadNew != null) {
                idplanificacioncalidadNew = em.getReference(idplanificacioncalidadNew.getClass(), idplanificacioncalidadNew.getIdplanificacion());
                ejecucionplanificacioncalidad.setIdplanificacioncalidad(idplanificacioncalidadNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionplanificacioncalidad.setEstado(estadoNew);
            }
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadListNew = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadListNew) {
                detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleejecucionplanificacioncalidadListNew.add(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadListNew = attachedDetalleejecucionplanificacioncalidadListNew;
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadList(detalleejecucionplanificacioncalidadListNew);
            ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            if (idplanificacioncalidadOld != null && !idplanificacioncalidadOld.equals(idplanificacioncalidadNew)) {
                idplanificacioncalidadOld.setEjecucionplanificacioncalidad(null);
                idplanificacioncalidadOld = em.merge(idplanificacioncalidadOld);
            }
            if (idplanificacioncalidadNew != null && !idplanificacioncalidadNew.equals(idplanificacioncalidadOld)) {
                idplanificacioncalidadNew.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                idplanificacioncalidadNew = em.merge(idplanificacioncalidadNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionplanificacioncalidadList().remove(ejecucionplanificacioncalidad);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionplanificacioncalidadList().add(ejecucionplanificacioncalidad);
                estadoNew = em.merge(estadoNew);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListNew) {
                if (!detalleejecucionplanificacioncalidadListOld.contains(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad)) {
                    Ejecucionplanificacioncalidad oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad();
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.setIdejecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                    if (oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad != null && !oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.equals(ejecucionplanificacioncalidad)) {
                        oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                        oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(oldIdejecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ejecucionplanificacioncalidad.getIdejecucion();
                if (findEjecucionplanificacioncalidad(id) == null) {
                    throw new NonexistentEntityException("The ejecucionplanificacioncalidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad;
            try {
                ejecucionplanificacioncalidad = em.getReference(Ejecucionplanificacioncalidad.class, id);
                ejecucionplanificacioncalidad.getIdejecucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejecucionplanificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListOrphanCheck = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListOrphanCheckDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionplanificacioncalidad (" + ejecucionplanificacioncalidad + ") cannot be destroyed since the Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadListOrphanCheckDetalleejecucionplanificacioncalidad + " in its detalleejecucionplanificacioncalidadList field has a non-nullable idejecucionplanificacioncalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Planificacioncalidad idplanificacioncalidad = ejecucionplanificacioncalidad.getIdplanificacioncalidad();
            if (idplanificacioncalidad != null) {
                idplanificacioncalidad.setEjecucionplanificacioncalidad(null);
                idplanificacioncalidad = em.merge(idplanificacioncalidad);
            }
            Estadoejecplancalidad estado = ejecucionplanificacioncalidad.getEstado();
            if (estado != null) {
                estado.getEjecucionplanificacioncalidadList().remove(ejecucionplanificacioncalidad);
                estado = em.merge(estado);
            }
            em.remove(ejecucionplanificacioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejecucionplanificacioncalidad> findEjecucionplanificacioncalidadEntities() {
        return findEjecucionplanificacioncalidadEntities(true, -1, -1);
    }

    public List<Ejecucionplanificacioncalidad> findEjecucionplanificacioncalidadEntities(int maxResults, int firstResult) {
        return findEjecucionplanificacioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Ejecucionplanificacioncalidad> findEjecucionplanificacioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejecucionplanificacioncalidad.class));
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

    public Ejecucionplanificacioncalidad findEjecucionplanificacioncalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejecucionplanificacioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjecucionplanificacioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejecucionplanificacioncalidad> rt = cq.from(Ejecucionplanificacioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
