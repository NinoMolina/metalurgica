/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Ejecucionprocesocalidad;
import entity.EjecucionprocesocalidadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadoejecucionprocesocalidad;
import entity.Procesocalidad;
import entity.Detalleejecucionplanificacioncalidad;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EjecucionprocesocalidadJpaController {

    public EjecucionprocesocalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionprocesocalidad ejecucionprocesocalidad) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (ejecucionprocesocalidad.getEjecucionprocesocalidadPK() == null) {
            ejecucionprocesocalidad.setEjecucionprocesocalidadPK(new EjecucionprocesocalidadPK());
        }
        if (ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet() == null) {
            ejecucionprocesocalidad.setDetalleejecucionplanificacioncalidadSet(new HashSet<Detalleejecucionplanificacioncalidad>());
        }
        ejecucionprocesocalidad.getEjecucionprocesocalidadPK().setIdprocesocalidad(ejecucionprocesocalidad.getProcesocalidad().getIdprocesocalidad());
        List<String> illegalOrphanMessages = null;
        Procesocalidad procesocalidadOrphanCheck = ejecucionprocesocalidad.getProcesocalidad();
        if (procesocalidadOrphanCheck != null) {
            Ejecucionprocesocalidad oldEjecucionprocesocalidadOfProcesocalidad = procesocalidadOrphanCheck.getEjecucionprocesocalidad();
            if (oldEjecucionprocesocalidadOfProcesocalidad != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Procesocalidad " + procesocalidadOrphanCheck + " already has an item of type Ejecucionprocesocalidad whose procesocalidad column cannot be null. Please make another selection for the procesocalidad field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecucionprocesocalidad estado = ejecucionprocesocalidad.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionprocesocalidad.setEstado(estado);
            }
            Procesocalidad procesocalidad = ejecucionprocesocalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad = em.getReference(procesocalidad.getClass(), procesocalidad.getIdprocesocalidad());
                ejecucionprocesocalidad.setProcesocalidad(procesocalidad);
            }
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSet = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach : ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet()) {
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSet.add(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach);
            }
            ejecucionprocesocalidad.setDetalleejecucionplanificacioncalidadSet(attachedDetalleejecucionplanificacioncalidadSet);
            em.persist(ejecucionprocesocalidad);
            if (estado != null) {
                estado.getEjecucionprocesocalidadSet().add(ejecucionprocesocalidad);
                estado = em.merge(estado);
            }
            if (procesocalidad != null) {
                procesocalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad : ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet()) {
                Ejecucionprocesocalidad oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                if (oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad != null) {
                    oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                    oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEjecucionprocesocalidad(ejecucionprocesocalidad.getEjecucionprocesocalidadPK()) != null) {
                throw new PreexistingEntityException("Ejecucionprocesocalidad " + ejecucionprocesocalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejecucionprocesocalidad ejecucionprocesocalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        ejecucionprocesocalidad.getEjecucionprocesocalidadPK().setIdprocesocalidad(ejecucionprocesocalidad.getProcesocalidad().getIdprocesocalidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionprocesocalidad persistentEjecucionprocesocalidad = em.find(Ejecucionprocesocalidad.class, ejecucionprocesocalidad.getEjecucionprocesocalidadPK());
            Estadoejecucionprocesocalidad estadoOld = persistentEjecucionprocesocalidad.getEstado();
            Estadoejecucionprocesocalidad estadoNew = ejecucionprocesocalidad.getEstado();
            Procesocalidad procesocalidadOld = persistentEjecucionprocesocalidad.getProcesocalidad();
            Procesocalidad procesocalidadNew = ejecucionprocesocalidad.getProcesocalidad();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetOld = persistentEjecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetNew = ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet();
            List<String> illegalOrphanMessages = null;
            if (procesocalidadNew != null && !procesocalidadNew.equals(procesocalidadOld)) {
                Ejecucionprocesocalidad oldEjecucionprocesocalidadOfProcesocalidad = procesocalidadNew.getEjecucionprocesocalidad();
                if (oldEjecucionprocesocalidadOfProcesocalidad != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Procesocalidad " + procesocalidadNew + " already has an item of type Ejecucionprocesocalidad whose procesocalidad column cannot be null. Please make another selection for the procesocalidad field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionprocesocalidad.setEstado(estadoNew);
            }
            if (procesocalidadNew != null) {
                procesocalidadNew = em.getReference(procesocalidadNew.getClass(), procesocalidadNew.getIdprocesocalidad());
                ejecucionprocesocalidad.setProcesocalidad(procesocalidadNew);
            }
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSetNew = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadSetNew) {
                detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSetNew.add(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadSetNew = attachedDetalleejecucionplanificacioncalidadSetNew;
            ejecucionprocesocalidad.setDetalleejecucionplanificacioncalidadSet(detalleejecucionplanificacioncalidadSetNew);
            ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionprocesocalidadSet().remove(ejecucionprocesocalidad);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionprocesocalidadSet().add(ejecucionprocesocalidad);
                estadoNew = em.merge(estadoNew);
            }
            if (procesocalidadOld != null && !procesocalidadOld.equals(procesocalidadNew)) {
                procesocalidadOld.setEjecucionprocesocalidad(null);
                procesocalidadOld = em.merge(procesocalidadOld);
            }
            if (procesocalidadNew != null && !procesocalidadNew.equals(procesocalidadOld)) {
                procesocalidadNew.setEjecucionprocesocalidad(ejecucionprocesocalidad);
                procesocalidadNew = em.merge(procesocalidadNew);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSetOld) {
                if (!detalleejecucionplanificacioncalidadSetNew.contains(detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad)) {
                    detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(null);
                    detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSetNew) {
                if (!detalleejecucionplanificacioncalidadSetOld.contains(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad)) {
                    Ejecucionprocesocalidad oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
                    detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);
                    detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                    if (oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad != null && !oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.equals(ejecucionprocesocalidad)) {
                        oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                        oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionprocesocalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EjecucionprocesocalidadPK id = ejecucionprocesocalidad.getEjecucionprocesocalidadPK();
                if (findEjecucionprocesocalidad(id) == null) {
                    throw new NonexistentEntityException("The ejecucionprocesocalidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EjecucionprocesocalidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionprocesocalidad ejecucionprocesocalidad;
            try {
                ejecucionprocesocalidad = em.getReference(Ejecucionprocesocalidad.class, id);
                ejecucionprocesocalidad.getEjecucionprocesocalidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejecucionprocesocalidad with id " + id + " no longer exists.", enfe);
            }
            Estadoejecucionprocesocalidad estado = ejecucionprocesocalidad.getEstado();
            if (estado != null) {
                estado.getEjecucionprocesocalidadSet().remove(ejecucionprocesocalidad);
                estado = em.merge(estado);
            }
            Procesocalidad procesocalidad = ejecucionprocesocalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad.setEjecucionprocesocalidad(null);
                procesocalidad = em.merge(procesocalidad);
            }
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet = ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSet) {
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(null);
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
            }
            em.remove(ejecucionprocesocalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejecucionprocesocalidad> findEjecucionprocesocalidadEntities() {
        return findEjecucionprocesocalidadEntities(true, -1, -1);
    }

    public List<Ejecucionprocesocalidad> findEjecucionprocesocalidadEntities(int maxResults, int firstResult) {
        return findEjecucionprocesocalidadEntities(false, maxResults, firstResult);
    }

    private List<Ejecucionprocesocalidad> findEjecucionprocesocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejecucionprocesocalidad.class));
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

    public Ejecucionprocesocalidad findEjecucionprocesocalidad(EjecucionprocesocalidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejecucionprocesocalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjecucionprocesocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejecucionprocesocalidad> rt = cq.from(Ejecucionprocesocalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
