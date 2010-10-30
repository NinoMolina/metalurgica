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
        if (ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet1() == null) {
            ejecucionprocesocalidad.setDetalleejecucionplanificacioncalidadSet1(new HashSet<Detalleejecucionplanificacioncalidad>());
        }
        ejecucionprocesocalidad.getEjecucionprocesocalidadPK().setIdprocesocalidad(ejecucionprocesocalidad.getProcesocalidad1().getIdprocesocalidad());
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
        Procesocalidad procesocalidad1OrphanCheck = ejecucionprocesocalidad.getProcesocalidad1();
        if (procesocalidad1OrphanCheck != null) {
            Ejecucionprocesocalidad oldEjecucionprocesocalidadOfProcesocalidad1 = procesocalidad1OrphanCheck.getEjecucionprocesocalidad();
            if (oldEjecucionprocesocalidadOfProcesocalidad1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Procesocalidad " + procesocalidad1OrphanCheck + " already has an item of type Ejecucionprocesocalidad whose procesocalidad1 column cannot be null. Please make another selection for the procesocalidad1 field.");
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
            Estadoejecucionprocesocalidad estado1 = ejecucionprocesocalidad.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                ejecucionprocesocalidad.setEstado1(estado1);
            }
            Procesocalidad procesocalidad = ejecucionprocesocalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad = em.getReference(procesocalidad.getClass(), procesocalidad.getIdprocesocalidad());
                ejecucionprocesocalidad.setProcesocalidad(procesocalidad);
            }
            Procesocalidad procesocalidad1 = ejecucionprocesocalidad.getProcesocalidad1();
            if (procesocalidad1 != null) {
                procesocalidad1 = em.getReference(procesocalidad1.getClass(), procesocalidad1.getIdprocesocalidad());
                ejecucionprocesocalidad.setProcesocalidad1(procesocalidad1);
            }
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSet = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach : ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet()) {
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSet.add(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach);
            }
            ejecucionprocesocalidad.setDetalleejecucionplanificacioncalidadSet(attachedDetalleejecucionplanificacioncalidadSet);
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSet1 = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach : ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet1()) {
                detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSet1.add(detalleejecucionplanificacioncalidadSet1DetalleejecucionplanificacioncalidadToAttach);
            }
            ejecucionprocesocalidad.setDetalleejecucionplanificacioncalidadSet1(attachedDetalleejecucionplanificacioncalidadSet1);
            em.persist(ejecucionprocesocalidad);
            if (estado != null) {
                estado.getEjecucionprocesocalidadSet().add(ejecucionprocesocalidad);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getEjecucionprocesocalidadSet().add(ejecucionprocesocalidad);
                estado1 = em.merge(estado1);
            }
            if (procesocalidad != null) {
                procesocalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            if (procesocalidad1 != null) {
                procesocalidad1.setEjecucionprocesocalidad(ejecucionprocesocalidad);
                procesocalidad1 = em.merge(procesocalidad1);
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
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad : ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet1()) {
                Ejecucionprocesocalidad oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad1();
                detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad1(ejecucionprocesocalidad);
                detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad);
                if (oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad != null) {
                    oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1().remove(detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad);
                    oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad = em.merge(oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad);
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
        ejecucionprocesocalidad.getEjecucionprocesocalidadPK().setIdprocesocalidad(ejecucionprocesocalidad.getProcesocalidad1().getIdprocesocalidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionprocesocalidad persistentEjecucionprocesocalidad = em.find(Ejecucionprocesocalidad.class, ejecucionprocesocalidad.getEjecucionprocesocalidadPK());
            Estadoejecucionprocesocalidad estadoOld = persistentEjecucionprocesocalidad.getEstado();
            Estadoejecucionprocesocalidad estadoNew = ejecucionprocesocalidad.getEstado();
            Estadoejecucionprocesocalidad estado1Old = persistentEjecucionprocesocalidad.getEstado1();
            Estadoejecucionprocesocalidad estado1New = ejecucionprocesocalidad.getEstado1();
            Procesocalidad procesocalidadOld = persistentEjecucionprocesocalidad.getProcesocalidad();
            Procesocalidad procesocalidadNew = ejecucionprocesocalidad.getProcesocalidad();
            Procesocalidad procesocalidad1Old = persistentEjecucionprocesocalidad.getProcesocalidad1();
            Procesocalidad procesocalidad1New = ejecucionprocesocalidad.getProcesocalidad1();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetOld = persistentEjecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetNew = ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet1Old = persistentEjecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet1();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet1New = ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet1();
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
            if (procesocalidad1New != null && !procesocalidad1New.equals(procesocalidad1Old)) {
                Ejecucionprocesocalidad oldEjecucionprocesocalidadOfProcesocalidad1 = procesocalidad1New.getEjecucionprocesocalidad();
                if (oldEjecucionprocesocalidadOfProcesocalidad1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Procesocalidad " + procesocalidad1New + " already has an item of type Ejecucionprocesocalidad whose procesocalidad1 column cannot be null. Please make another selection for the procesocalidad1 field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionprocesocalidad.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                ejecucionprocesocalidad.setEstado1(estado1New);
            }
            if (procesocalidadNew != null) {
                procesocalidadNew = em.getReference(procesocalidadNew.getClass(), procesocalidadNew.getIdprocesocalidad());
                ejecucionprocesocalidad.setProcesocalidad(procesocalidadNew);
            }
            if (procesocalidad1New != null) {
                procesocalidad1New = em.getReference(procesocalidad1New.getClass(), procesocalidad1New.getIdprocesocalidad());
                ejecucionprocesocalidad.setProcesocalidad1(procesocalidad1New);
            }
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSetNew = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadSetNew) {
                detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSetNew.add(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadSetNew = attachedDetalleejecucionplanificacioncalidadSetNew;
            ejecucionprocesocalidad.setDetalleejecucionplanificacioncalidadSet(detalleejecucionplanificacioncalidadSetNew);
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSet1New = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadSet1New) {
                detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSet1New.add(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadSet1New = attachedDetalleejecucionplanificacioncalidadSet1New;
            ejecucionprocesocalidad.setDetalleejecucionplanificacioncalidadSet1(detalleejecucionplanificacioncalidadSet1New);
            ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionprocesocalidadSet().remove(ejecucionprocesocalidad);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionprocesocalidadSet().add(ejecucionprocesocalidad);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getEjecucionprocesocalidadSet().remove(ejecucionprocesocalidad);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getEjecucionprocesocalidadSet().add(ejecucionprocesocalidad);
                estado1New = em.merge(estado1New);
            }
            if (procesocalidadOld != null && !procesocalidadOld.equals(procesocalidadNew)) {
                procesocalidadOld.setEjecucionprocesocalidad(null);
                procesocalidadOld = em.merge(procesocalidadOld);
            }
            if (procesocalidadNew != null && !procesocalidadNew.equals(procesocalidadOld)) {
                procesocalidadNew.setEjecucionprocesocalidad(ejecucionprocesocalidad);
                procesocalidadNew = em.merge(procesocalidadNew);
            }
            if (procesocalidad1Old != null && !procesocalidad1Old.equals(procesocalidad1New)) {
                procesocalidad1Old.setEjecucionprocesocalidad(null);
                procesocalidad1Old = em.merge(procesocalidad1Old);
            }
            if (procesocalidad1New != null && !procesocalidad1New.equals(procesocalidad1Old)) {
                procesocalidad1New.setEjecucionprocesocalidad(ejecucionprocesocalidad);
                procesocalidad1New = em.merge(procesocalidad1New);
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
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1OldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSet1Old) {
                if (!detalleejecucionplanificacioncalidadSet1New.contains(detalleejecucionplanificacioncalidadSet1OldDetalleejecucionplanificacioncalidad)) {
                    detalleejecucionplanificacioncalidadSet1OldDetalleejecucionplanificacioncalidad.setEjecucionprocesocalidad1(null);
                    detalleejecucionplanificacioncalidadSet1OldDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSet1OldDetalleejecucionplanificacioncalidad);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSet1New) {
                if (!detalleejecucionplanificacioncalidadSet1Old.contains(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad)) {
                    Ejecucionprocesocalidad oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad.getEjecucionprocesocalidad1();
                    detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad.setEjecucionprocesocalidad1(ejecucionprocesocalidad);
                    detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad);
                    if (oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad != null && !oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad.equals(ejecucionprocesocalidad)) {
                        oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet1().remove(detalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad);
                        oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionprocesocalidad1OfDetalleejecucionplanificacioncalidadSet1NewDetalleejecucionplanificacioncalidad);
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
            Estadoejecucionprocesocalidad estado1 = ejecucionprocesocalidad.getEstado1();
            if (estado1 != null) {
                estado1.getEjecucionprocesocalidadSet().remove(ejecucionprocesocalidad);
                estado1 = em.merge(estado1);
            }
            Procesocalidad procesocalidad = ejecucionprocesocalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad.setEjecucionprocesocalidad(null);
                procesocalidad = em.merge(procesocalidad);
            }
            Procesocalidad procesocalidad1 = ejecucionprocesocalidad.getProcesocalidad1();
            if (procesocalidad1 != null) {
                procesocalidad1.setEjecucionprocesocalidad(null);
                procesocalidad1 = em.merge(procesocalidad1);
            }
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet = ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSet) {
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(null);
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
            }
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet1 = ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet1();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSet1) {
                detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad1(null);
                detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSet1Detalleejecucionplanificacioncalidad);
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
