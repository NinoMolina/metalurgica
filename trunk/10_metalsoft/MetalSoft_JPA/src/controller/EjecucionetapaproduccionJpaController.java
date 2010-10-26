/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Ejecucionetapaproduccion;
import entity.EjecucionetapaproduccionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empleado;
import entity.Estadoejecetapaprod;
import entity.Etapadeproduccion;
import entity.Detalleejecucionplanificacion;
import java.util.HashSet;
import java.util.Set;
import entity.Maquinaxejecucionetapaproduccion;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EjecucionetapaproduccionJpaController {

    public EjecucionetapaproduccionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionetapaproduccion ejecucionetapaproduccion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (ejecucionetapaproduccion.getEjecucionetapaproduccionPK() == null) {
            ejecucionetapaproduccion.setEjecucionetapaproduccionPK(new EjecucionetapaproduccionPK());
        }
        if (ejecucionetapaproduccion.getDetalleejecucionplanificacionSet() == null) {
            ejecucionetapaproduccion.setDetalleejecucionplanificacionSet(new HashSet<Detalleejecucionplanificacion>());
        }
        if (ejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet() == null) {
            ejecucionetapaproduccion.setMaquinaxejecucionetapaproduccionSet(new HashSet<Maquinaxejecucionetapaproduccion>());
        }
        ejecucionetapaproduccion.getEjecucionetapaproduccionPK().setIdetapaproduccion(ejecucionetapaproduccion.getEtapadeproduccion().getIdetapaproduccion());
        List<String> illegalOrphanMessages = null;
        Etapadeproduccion etapadeproduccionOrphanCheck = ejecucionetapaproduccion.getEtapadeproduccion();
        if (etapadeproduccionOrphanCheck != null) {
            Ejecucionetapaproduccion oldEjecucionetapaproduccionOfEtapadeproduccion = etapadeproduccionOrphanCheck.getEjecucionetapaproduccion();
            if (oldEjecucionetapaproduccionOfEtapadeproduccion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Etapadeproduccion " + etapadeproduccionOrphanCheck + " already has an item of type Ejecucionetapaproduccion whose etapadeproduccion column cannot be null. Please make another selection for the etapadeproduccion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = ejecucionetapaproduccion.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                ejecucionetapaproduccion.setEmpleado(empleado);
            }
            Estadoejecetapaprod estado = ejecucionetapaproduccion.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionetapaproduccion.setEstado(estado);
            }
            Etapadeproduccion etapadeproduccion = ejecucionetapaproduccion.getEtapadeproduccion();
            if (etapadeproduccion != null) {
                etapadeproduccion = em.getReference(etapadeproduccion.getClass(), etapadeproduccion.getIdetapaproduccion());
                ejecucionetapaproduccion.setEtapadeproduccion(etapadeproduccion);
            }
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSet = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach : ejecucionetapaproduccion.getDetalleejecucionplanificacionSet()) {
                detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSet.add(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach);
            }
            ejecucionetapaproduccion.setDetalleejecucionplanificacionSet(attachedDetalleejecucionplanificacionSet);
            Set<Maquinaxejecucionetapaproduccion> attachedMaquinaxejecucionetapaproduccionSet = new HashSet<Maquinaxejecucionetapaproduccion>();
            for (Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccionToAttach : ejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet()) {
                maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccionToAttach = em.getReference(maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccionToAttach.getClass(), maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccionToAttach.getMaquinaxejecucionetapaproduccionPK());
                attachedMaquinaxejecucionetapaproduccionSet.add(maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccion.setMaquinaxejecucionetapaproduccionSet(attachedMaquinaxejecucionetapaproduccionSet);
            em.persist(ejecucionetapaproduccion);
            if (empleado != null) {
                empleado.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                empleado = em.merge(empleado);
            }
            if (estado != null) {
                estado.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                estado = em.merge(estado);
            }
            if (etapadeproduccion != null) {
                etapadeproduccion.setEjecucionetapaproduccion(ejecucionetapaproduccion);
                etapadeproduccion = em.merge(etapadeproduccion);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacion : ejecucionetapaproduccion.getDetalleejecucionplanificacionSet()) {
                Ejecucionetapaproduccion oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = detalleejecucionplanificacionSetDetalleejecucionplanificacion.getEjecucionetapaproduccion();
                detalleejecucionplanificacionSetDetalleejecucionplanificacion.setEjecucionetapaproduccion(ejecucionetapaproduccion);
                detalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                if (oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion != null) {
                    oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                    oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion);
                }
            }
            for (Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion : ejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet()) {
                Ejecucionetapaproduccion oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion = maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion.getEjecucionetapaproduccion();
                maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion.setEjecucionetapaproduccion(ejecucionetapaproduccion);
                maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion = em.merge(maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion);
                if (oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion != null) {
                    oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet().remove(maquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion);
                    oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion = em.merge(oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetMaquinaxejecucionetapaproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEjecucionetapaproduccion(ejecucionetapaproduccion.getEjecucionetapaproduccionPK()) != null) {
                throw new PreexistingEntityException("Ejecucionetapaproduccion " + ejecucionetapaproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejecucionetapaproduccion ejecucionetapaproduccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        ejecucionetapaproduccion.getEjecucionetapaproduccionPK().setIdetapaproduccion(ejecucionetapaproduccion.getEtapadeproduccion().getIdetapaproduccion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionetapaproduccion persistentEjecucionetapaproduccion = em.find(Ejecucionetapaproduccion.class, ejecucionetapaproduccion.getEjecucionetapaproduccionPK());
            Empleado empleadoOld = persistentEjecucionetapaproduccion.getEmpleado();
            Empleado empleadoNew = ejecucionetapaproduccion.getEmpleado();
            Estadoejecetapaprod estadoOld = persistentEjecucionetapaproduccion.getEstado();
            Estadoejecetapaprod estadoNew = ejecucionetapaproduccion.getEstado();
            Etapadeproduccion etapadeproduccionOld = persistentEjecucionetapaproduccion.getEtapadeproduccion();
            Etapadeproduccion etapadeproduccionNew = ejecucionetapaproduccion.getEtapadeproduccion();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOld = persistentEjecucionetapaproduccion.getDetalleejecucionplanificacionSet();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetNew = ejecucionetapaproduccion.getDetalleejecucionplanificacionSet();
            Set<Maquinaxejecucionetapaproduccion> maquinaxejecucionetapaproduccionSetOld = persistentEjecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet();
            Set<Maquinaxejecucionetapaproduccion> maquinaxejecucionetapaproduccionSetNew = ejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet();
            List<String> illegalOrphanMessages = null;
            if (etapadeproduccionNew != null && !etapadeproduccionNew.equals(etapadeproduccionOld)) {
                Ejecucionetapaproduccion oldEjecucionetapaproduccionOfEtapadeproduccion = etapadeproduccionNew.getEjecucionetapaproduccion();
                if (oldEjecucionetapaproduccionOfEtapadeproduccion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Etapadeproduccion " + etapadeproduccionNew + " already has an item of type Ejecucionetapaproduccion whose etapadeproduccion column cannot be null. Please make another selection for the etapadeproduccion field.");
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetOldDetalleejecucionplanificacion : detalleejecucionplanificacionSetOld) {
                if (!detalleejecucionplanificacionSetNew.contains(detalleejecucionplanificacionSetOldDetalleejecucionplanificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacion " + detalleejecucionplanificacionSetOldDetalleejecucionplanificacion + " since its ejecucionetapaproduccion field is not nullable.");
                }
            }
            for (Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccionSetOldMaquinaxejecucionetapaproduccion : maquinaxejecucionetapaproduccionSetOld) {
                if (!maquinaxejecucionetapaproduccionSetNew.contains(maquinaxejecucionetapaproduccionSetOldMaquinaxejecucionetapaproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Maquinaxejecucionetapaproduccion " + maquinaxejecucionetapaproduccionSetOldMaquinaxejecucionetapaproduccion + " since its ejecucionetapaproduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                ejecucionetapaproduccion.setEmpleado(empleadoNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionetapaproduccion.setEstado(estadoNew);
            }
            if (etapadeproduccionNew != null) {
                etapadeproduccionNew = em.getReference(etapadeproduccionNew.getClass(), etapadeproduccionNew.getIdetapaproduccion());
                ejecucionetapaproduccion.setEtapadeproduccion(etapadeproduccionNew);
            }
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSetNew = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionSetNew) {
                detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSetNew.add(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionSetNew = attachedDetalleejecucionplanificacionSetNew;
            ejecucionetapaproduccion.setDetalleejecucionplanificacionSet(detalleejecucionplanificacionSetNew);
            Set<Maquinaxejecucionetapaproduccion> attachedMaquinaxejecucionetapaproduccionSetNew = new HashSet<Maquinaxejecucionetapaproduccion>();
            for (Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccionToAttach : maquinaxejecucionetapaproduccionSetNew) {
                maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccionToAttach = em.getReference(maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccionToAttach.getClass(), maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccionToAttach.getMaquinaxejecucionetapaproduccionPK());
                attachedMaquinaxejecucionetapaproduccionSetNew.add(maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccionToAttach);
            }
            maquinaxejecucionetapaproduccionSetNew = attachedMaquinaxejecucionetapaproduccionSetNew;
            ejecucionetapaproduccion.setMaquinaxejecucionetapaproduccionSet(maquinaxejecucionetapaproduccionSetNew);
            ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                empleadoNew = em.merge(empleadoNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                estadoNew = em.merge(estadoNew);
            }
            if (etapadeproduccionOld != null && !etapadeproduccionOld.equals(etapadeproduccionNew)) {
                etapadeproduccionOld.setEjecucionetapaproduccion(null);
                etapadeproduccionOld = em.merge(etapadeproduccionOld);
            }
            if (etapadeproduccionNew != null && !etapadeproduccionNew.equals(etapadeproduccionOld)) {
                etapadeproduccionNew.setEjecucionetapaproduccion(ejecucionetapaproduccion);
                etapadeproduccionNew = em.merge(etapadeproduccionNew);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacion : detalleejecucionplanificacionSetNew) {
                if (!detalleejecucionplanificacionSetOld.contains(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion)) {
                    Ejecucionetapaproduccion oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getEjecucionetapaproduccion();
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.setEjecucionetapaproduccion(ejecucionetapaproduccion);
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    if (oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion != null && !oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.equals(ejecucionetapaproduccion)) {
                        oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                        oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(oldEjecucionetapaproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    }
                }
            }
            for (Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion : maquinaxejecucionetapaproduccionSetNew) {
                if (!maquinaxejecucionetapaproduccionSetOld.contains(maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion)) {
                    Ejecucionetapaproduccion oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion = maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion.getEjecucionetapaproduccion();
                    maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion.setEjecucionetapaproduccion(ejecucionetapaproduccion);
                    maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion = em.merge(maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion);
                    if (oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion != null && !oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion.equals(ejecucionetapaproduccion)) {
                        oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet().remove(maquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion);
                        oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion = em.merge(oldEjecucionetapaproduccionOfMaquinaxejecucionetapaproduccionSetNewMaquinaxejecucionetapaproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EjecucionetapaproduccionPK id = ejecucionetapaproduccion.getEjecucionetapaproduccionPK();
                if (findEjecucionetapaproduccion(id) == null) {
                    throw new NonexistentEntityException("The ejecucionetapaproduccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EjecucionetapaproduccionPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionetapaproduccion ejecucionetapaproduccion;
            try {
                ejecucionetapaproduccion = em.getReference(Ejecucionetapaproduccion.class, id);
                ejecucionetapaproduccion.getEjecucionetapaproduccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejecucionetapaproduccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOrphanCheck = ejecucionetapaproduccion.getDetalleejecucionplanificacionSet();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetOrphanCheckDetalleejecucionplanificacion : detalleejecucionplanificacionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionetapaproduccion (" + ejecucionetapaproduccion + ") cannot be destroyed since the Detalleejecucionplanificacion " + detalleejecucionplanificacionSetOrphanCheckDetalleejecucionplanificacion + " in its detalleejecucionplanificacionSet field has a non-nullable ejecucionetapaproduccion field.");
            }
            Set<Maquinaxejecucionetapaproduccion> maquinaxejecucionetapaproduccionSetOrphanCheck = ejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet();
            for (Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccionSetOrphanCheckMaquinaxejecucionetapaproduccion : maquinaxejecucionetapaproduccionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionetapaproduccion (" + ejecucionetapaproduccion + ") cannot be destroyed since the Maquinaxejecucionetapaproduccion " + maquinaxejecucionetapaproduccionSetOrphanCheckMaquinaxejecucionetapaproduccion + " in its maquinaxejecucionetapaproduccionSet field has a non-nullable ejecucionetapaproduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado empleado = ejecucionetapaproduccion.getEmpleado();
            if (empleado != null) {
                empleado.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                empleado = em.merge(empleado);
            }
            Estadoejecetapaprod estado = ejecucionetapaproduccion.getEstado();
            if (estado != null) {
                estado.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                estado = em.merge(estado);
            }
            Etapadeproduccion etapadeproduccion = ejecucionetapaproduccion.getEtapadeproduccion();
            if (etapadeproduccion != null) {
                etapadeproduccion.setEjecucionetapaproduccion(null);
                etapadeproduccion = em.merge(etapadeproduccion);
            }
            em.remove(ejecucionetapaproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejecucionetapaproduccion> findEjecucionetapaproduccionEntities() {
        return findEjecucionetapaproduccionEntities(true, -1, -1);
    }

    public List<Ejecucionetapaproduccion> findEjecucionetapaproduccionEntities(int maxResults, int firstResult) {
        return findEjecucionetapaproduccionEntities(false, maxResults, firstResult);
    }

    private List<Ejecucionetapaproduccion> findEjecucionetapaproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejecucionetapaproduccion.class));
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

    public Ejecucionetapaproduccion findEjecucionetapaproduccion(EjecucionetapaproduccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejecucionetapaproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjecucionetapaproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejecucionetapaproduccion> rt = cq.from(Ejecucionetapaproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
