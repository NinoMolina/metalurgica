/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Ejecucionplanificacioncalidad;
import entity.EjecucionplanificacioncalidadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadoejecplancalidad;
import entity.Planificacioncalidad;
import entity.Detalleejecucionplanificacioncalidad;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EjecucionplanificacioncalidadJpaController {

    public EjecucionplanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK() == null) {
            ejecucionplanificacioncalidad.setEjecucionplanificacioncalidadPK(new EjecucionplanificacioncalidadPK());
        }
        if (ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet() == null) {
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet(new HashSet<Detalleejecucionplanificacioncalidad>());
        }
        if (ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1() == null) {
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet1(new HashSet<Detalleejecucionplanificacioncalidad>());
        }
        ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK().setIdplanificacioncalidad(ejecucionplanificacioncalidad.getPlanificacioncalidad1().getIdplanificacion());
        List<String> illegalOrphanMessages = null;
        Planificacioncalidad planificacioncalidadOrphanCheck = ejecucionplanificacioncalidad.getPlanificacioncalidad();
        if (planificacioncalidadOrphanCheck != null) {
            Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfPlanificacioncalidad = planificacioncalidadOrphanCheck.getEjecucionplanificacioncalidad();
            if (oldEjecucionplanificacioncalidadOfPlanificacioncalidad != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Planificacioncalidad " + planificacioncalidadOrphanCheck + " already has an item of type Ejecucionplanificacioncalidad whose planificacioncalidad column cannot be null. Please make another selection for the planificacioncalidad field.");
            }
        }
        Planificacioncalidad planificacioncalidad1OrphanCheck = ejecucionplanificacioncalidad.getPlanificacioncalidad1();
        if (planificacioncalidad1OrphanCheck != null) {
            Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfPlanificacioncalidad1 = planificacioncalidad1OrphanCheck.getEjecucionplanificacioncalidad();
            if (oldEjecucionplanificacioncalidadOfPlanificacioncalidad1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Planificacioncalidad " + planificacioncalidad1OrphanCheck + " already has an item of type Ejecucionplanificacioncalidad whose planificacioncalidad1 column cannot be null. Please make another selection for the planificacioncalidad1 field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecplancalidad estado = ejecucionplanificacioncalidad.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionplanificacioncalidad.setEstado(estado);
            }
            Estadoejecplancalidad estado1 = ejecucionplanificacioncalidad.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                ejecucionplanificacioncalidad.setEstado1(estado1);
            }
            Planificacioncalidad planificacioncalidad = ejecucionplanificacioncalidad.getPlanificacioncalidad();
            if (planificacioncalidad != null) {
                planificacioncalidad = em.getReference(planificacioncalidad.getClass(), planificacioncalidad.getIdplanificacion());
                ejecucionplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
            }
            Planificacioncalidad planificacioncalidad1 = ejecucionplanificacioncalidad.getPlanificacioncalidad1();
            if (planificacioncalidad1 != null) {
                planificacioncalidad1 = em.getReference(planificacioncalidad1.getClass(), planificacioncalidad1.getIdplanificacion());
                ejecucionplanificacioncalidad.setPlanificacioncalidad1(planificacioncalidad1);
            }
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSet = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet()) {
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSet.add(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach);
            }
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet(attachedDetalleejecucionplanificacioncalidadSet);
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSet1 = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1()) {
                detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSet1.add(detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach);
            }
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet1(attachedDetalleejecucionplanificacioncalidadSet1);
            em.persist(ejecucionplanificacioncalidad);
            if (estado != null) {
                estado.getEjecucionplanificacioncalidadSet().add(ejecucionplanificacioncalidad);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getEjecucionplanificacioncalidadSet().add(ejecucionplanificacioncalidad);
                estado1 = em.merge(estado1);
            }
            if (planificacioncalidad != null) {
                planificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            if (planificacioncalidad1 != null) {
                planificacioncalidad1.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                planificacioncalidad1 = em.merge(planificacioncalidad1);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet()) {
                Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                if (oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad != null) {
                    oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                    oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1()) {
                Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1();
                detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad1(ejecucionplanificacioncalidad);
                detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad);
                if (oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad != null) {
                    oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1().remove(detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad);
                    oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad = em.merge(oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEjecucionplanificacioncalidad(ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK()) != null) {
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
        ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK().setIdplanificacioncalidad(ejecucionplanificacioncalidad.getPlanificacioncalidad1().getIdplanificacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad persistentEjecucionplanificacioncalidad = em.find(Ejecucionplanificacioncalidad.class, ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK());
            Estadoejecplancalidad estadoOld = persistentEjecucionplanificacioncalidad.getEstado();
            Estadoejecplancalidad estadoNew = ejecucionplanificacioncalidad.getEstado();
            Estadoejecplancalidad estado1Old = persistentEjecucionplanificacioncalidad.getEstado1();
            Estadoejecplancalidad estado1New = ejecucionplanificacioncalidad.getEstado1();
            Planificacioncalidad planificacioncalidadOld = persistentEjecucionplanificacioncalidad.getPlanificacioncalidad();
            Planificacioncalidad planificacioncalidadNew = ejecucionplanificacioncalidad.getPlanificacioncalidad();
            Planificacioncalidad planificacioncalidad1Old = persistentEjecucionplanificacioncalidad.getPlanificacioncalidad1();
            Planificacioncalidad planificacioncalidad1New = ejecucionplanificacioncalidad.getPlanificacioncalidad1();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetOld = persistentEjecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetNew = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet1Old = persistentEjecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet1New = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1();
            List<String> illegalOrphanMessages = null;
            if (planificacioncalidadNew != null && !planificacioncalidadNew.equals(planificacioncalidadOld)) {
                Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfPlanificacioncalidad = planificacioncalidadNew.getEjecucionplanificacioncalidad();
                if (oldEjecucionplanificacioncalidadOfPlanificacioncalidad != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Planificacioncalidad " + planificacioncalidadNew + " already has an item of type Ejecucionplanificacioncalidad whose planificacioncalidad column cannot be null. Please make another selection for the planificacioncalidad field.");
                }
            }
            if (planificacioncalidad1New != null && !planificacioncalidad1New.equals(planificacioncalidad1Old)) {
                Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfPlanificacioncalidad1 = planificacioncalidad1New.getEjecucionplanificacioncalidad();
                if (oldEjecucionplanificacioncalidadOfPlanificacioncalidad1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Planificacioncalidad " + planificacioncalidad1New + " already has an item of type Ejecucionplanificacioncalidad whose planificacioncalidad1 column cannot be null. Please make another selection for the planificacioncalidad1 field.");
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSetOld) {
                if (!detalleejecucionplanificacioncalidadSetNew.contains(detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad + " since its ejecucionplanificacioncalidad field is not nullable.");
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1OldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSet1Old) {
                if (!detalleejecucionplanificacioncalidadSet1New.contains(detalleejecucionplanificacioncalidadSet1OldDetalleejecucionplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadSet1OldDetalleejecucionplanificacioncalidad + " since its ejecucionplanificacioncalidad1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionplanificacioncalidad.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                ejecucionplanificacioncalidad.setEstado1(estado1New);
            }
            if (planificacioncalidadNew != null) {
                planificacioncalidadNew = em.getReference(planificacioncalidadNew.getClass(), planificacioncalidadNew.getIdplanificacion());
                ejecucionplanificacioncalidad.setPlanificacioncalidad(planificacioncalidadNew);
            }
            if (planificacioncalidad1New != null) {
                planificacioncalidad1New = em.getReference(planificacioncalidad1New.getClass(), planificacioncalidad1New.getIdplanificacion());
                ejecucionplanificacioncalidad.setPlanificacioncalidad1(planificacioncalidad1New);
            }
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSetNew = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadSetNew) {
                detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSetNew.add(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadSetNew = attachedDetalleejecucionplanificacioncalidadSetNew;
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet(detalleejecucionplanificacioncalidadSetNew);
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSet1New = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadSet1New) {
                detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSet1New.add(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadSet1New = attachedDetalleejecucionplanificacioncalidadSet1New;
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet1(detalleejecucionplanificacioncalidadSet1New);
            ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionplanificacioncalidadSet().remove(ejecucionplanificacioncalidad);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionplanificacioncalidadSet().add(ejecucionplanificacioncalidad);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getEjecucionplanificacioncalidadSet().remove(ejecucionplanificacioncalidad);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getEjecucionplanificacioncalidadSet().add(ejecucionplanificacioncalidad);
                estado1New = em.merge(estado1New);
            }
            if (planificacioncalidadOld != null && !planificacioncalidadOld.equals(planificacioncalidadNew)) {
                planificacioncalidadOld.setEjecucionplanificacioncalidad(null);
                planificacioncalidadOld = em.merge(planificacioncalidadOld);
            }
            if (planificacioncalidadNew != null && !planificacioncalidadNew.equals(planificacioncalidadOld)) {
                planificacioncalidadNew.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                planificacioncalidadNew = em.merge(planificacioncalidadNew);
            }
            if (planificacioncalidad1Old != null && !planificacioncalidad1Old.equals(planificacioncalidad1New)) {
                planificacioncalidad1Old.setEjecucionplanificacioncalidad(null);
                planificacioncalidad1Old = em.merge(planificacioncalidad1Old);
            }
            if (planificacioncalidad1New != null && !planificacioncalidad1New.equals(planificacioncalidad1Old)) {
                planificacioncalidad1New.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                planificacioncalidad1New = em.merge(planificacioncalidad1New);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSetNew) {
                if (!detalleejecucionplanificacioncalidadSetOld.contains(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad)) {
                    Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
                    detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                    detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                    if (oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad != null && !oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.equals(ejecucionplanificacioncalidad)) {
                        oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                        oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                    }
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSet1New) {
                if (!detalleejecucionplanificacioncalidadSet1Old.contains(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad)) {
                    Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1();
                    detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad1(ejecucionplanificacioncalidad);
                    detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad);
                    if (oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad != null && !oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad.equals(ejecucionplanificacioncalidad)) {
                        oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1().remove(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad);
                        oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionplanificacioncalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EjecucionplanificacioncalidadPK id = ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK();
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

    public void destroy(EjecucionplanificacioncalidadPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad;
            try {
                ejecucionplanificacioncalidad = em.getReference(Ejecucionplanificacioncalidad.class, id);
                ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejecucionplanificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetOrphanCheck = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetOrphanCheckDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionplanificacioncalidad (" + ejecucionplanificacioncalidad + ") cannot be destroyed since the Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadSetOrphanCheckDetalleejecucionplanificacioncalidad + " in its detalleejecucionplanificacioncalidadSet field has a non-nullable ejecucionplanificacioncalidad field.");
            }
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet1OrphanCheck = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1OrphanCheckDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionplanificacioncalidad (" + ejecucionplanificacioncalidad + ") cannot be destroyed since the Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadSet1OrphanCheckDetalleejecucionplanificacioncalidad + " in its detalleejecucionplanificacioncalidadSet1 field has a non-nullable ejecucionplanificacioncalidad1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadoejecplancalidad estado = ejecucionplanificacioncalidad.getEstado();
            if (estado != null) {
                estado.getEjecucionplanificacioncalidadSet().remove(ejecucionplanificacioncalidad);
                estado = em.merge(estado);
            }
            Estadoejecplancalidad estado1 = ejecucionplanificacioncalidad.getEstado1();
            if (estado1 != null) {
                estado1.getEjecucionplanificacioncalidadSet().remove(ejecucionplanificacioncalidad);
                estado1 = em.merge(estado1);
            }
            Planificacioncalidad planificacioncalidad = ejecucionplanificacioncalidad.getPlanificacioncalidad();
            if (planificacioncalidad != null) {
                planificacioncalidad.setEjecucionplanificacioncalidad(null);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            Planificacioncalidad planificacioncalidad1 = ejecucionplanificacioncalidad.getPlanificacioncalidad1();
            if (planificacioncalidad1 != null) {
                planificacioncalidad1.setEjecucionplanificacioncalidad(null);
                planificacioncalidad1 = em.merge(planificacioncalidad1);
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

    public Ejecucionplanificacioncalidad findEjecucionplanificacioncalidad(EjecucionplanificacioncalidadPK id) {
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
