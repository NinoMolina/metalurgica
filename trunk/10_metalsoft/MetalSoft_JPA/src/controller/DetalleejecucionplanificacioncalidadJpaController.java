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
        if (detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet1() == null) {
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadSet1(new HashSet<Detalleplanificacioncalidad>());
        }
        detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK().setIdplanificacioncalidad(detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1().getEjecucionplanificacioncalidadPK().getIdplanificacioncalidad());
        detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK().setIdejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1().getEjecucionplanificacioncalidadPK().getIdejecucion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad = em.getReference(ejecucionplanificacioncalidad.getClass(), ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
            }
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1 = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1();
            if (ejecucionplanificacioncalidad1 != null) {
                ejecucionplanificacioncalidad1 = em.getReference(ejecucionplanificacioncalidad1.getClass(), ejecucionplanificacioncalidad1.getEjecucionplanificacioncalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad1(ejecucionplanificacioncalidad1);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad = em.getReference(ejecucionprocesocalidad.getClass(), ejecucionprocesocalidad.getEjecucionprocesocalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad1 = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad1();
            if (ejecucionprocesocalidad1 != null) {
                ejecucionprocesocalidad1 = em.getReference(ejecucionprocesocalidad1.getClass(), ejecucionprocesocalidad1.getEjecucionprocesocalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad1(ejecucionprocesocalidad1);
            }
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach : detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet()) {
                detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet.add(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadSet(attachedDetalleplanificacioncalidadSet);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet1 = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach : detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet1()) {
                detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet1.add(detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadSet1(attachedDetalleplanificacioncalidadSet1);
            em.persist(detalleejecucionplanificacioncalidad);
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            }
            if (ejecucionplanificacioncalidad1 != null) {
                ejecucionplanificacioncalidad1.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad1 = em.merge(ejecucionplanificacioncalidad1);
            }
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            }
            if (ejecucionprocesocalidad1 != null) {
                ejecucionprocesocalidad1.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad1 = em.merge(ejecucionprocesocalidad1);
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
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1Detalleplanificacioncalidad : detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet1()) {
                Detalleejecucionplanificacioncalidad oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad = detalleplanificacioncalidadSet1Detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad1();
                detalleplanificacioncalidadSet1Detalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad1(detalleejecucionplanificacioncalidad);
                detalleplanificacioncalidadSet1Detalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSet1Detalleplanificacioncalidad);
                if (oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad != null) {
                    oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad.getDetalleplanificacioncalidadSet1().remove(detalleplanificacioncalidadSet1Detalleplanificacioncalidad);
                    oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad = em.merge(oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad);
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
        detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK().setIdplanificacioncalidad(detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1().getEjecucionplanificacioncalidadPK().getIdplanificacioncalidad());
        detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK().setIdejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1().getEjecucionplanificacioncalidadPK().getIdejecucion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleejecucionplanificacioncalidad persistentDetalleejecucionplanificacioncalidad = em.find(Detalleejecucionplanificacioncalidad.class, detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK());
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadOld = persistentDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadNew = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1Old = persistentDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1New = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1();
            Ejecucionprocesocalidad ejecucionprocesocalidadOld = persistentDetalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            Ejecucionprocesocalidad ejecucionprocesocalidadNew = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            Ejecucionprocesocalidad ejecucionprocesocalidad1Old = persistentDetalleejecucionplanificacioncalidad.getEjecucionprocesocalidad1();
            Ejecucionprocesocalidad ejecucionprocesocalidad1New = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad1();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOld = persistentDetalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetNew = detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1Old = persistentDetalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet1();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1New = detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet1();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetOldDetalleplanificacioncalidad : detalleplanificacioncalidadSetOld) {
                if (!detalleplanificacioncalidadSetNew.contains(detalleplanificacioncalidadSetOldDetalleplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacioncalidad " + detalleplanificacioncalidadSetOldDetalleplanificacioncalidad + " since its detalleejecucionplanificacioncalidad field is not nullable.");
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad : detalleplanificacioncalidadSet1Old) {
                if (!detalleplanificacioncalidadSet1New.contains(detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacioncalidad " + detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad + " since its detalleejecucionplanificacioncalidad1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ejecucionplanificacioncalidadNew != null) {
                ejecucionplanificacioncalidadNew = em.getReference(ejecucionplanificacioncalidadNew.getClass(), ejecucionplanificacioncalidadNew.getEjecucionplanificacioncalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidadNew);
            }
            if (ejecucionplanificacioncalidad1New != null) {
                ejecucionplanificacioncalidad1New = em.getReference(ejecucionplanificacioncalidad1New.getClass(), ejecucionplanificacioncalidad1New.getEjecucionplanificacioncalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad1(ejecucionplanificacioncalidad1New);
            }
            if (ejecucionprocesocalidadNew != null) {
                ejecucionprocesocalidadNew = em.getReference(ejecucionprocesocalidadNew.getClass(), ejecucionprocesocalidadNew.getEjecucionprocesocalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(ejecucionprocesocalidadNew);
            }
            if (ejecucionprocesocalidad1New != null) {
                ejecucionprocesocalidad1New = em.getReference(ejecucionprocesocalidad1New.getClass(), ejecucionprocesocalidad1New.getEjecucionprocesocalidadPK());
                detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad1(ejecucionprocesocalidad1New);
            }
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSetNew = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSetNew) {
                detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSetNew.add(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSetNew = attachedDetalleplanificacioncalidadSetNew;
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadSet(detalleplanificacioncalidadSetNew);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet1New = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSet1New) {
                detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet1New.add(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSet1New = attachedDetalleplanificacioncalidadSet1New;
            detalleejecucionplanificacioncalidad.setDetalleplanificacioncalidadSet1(detalleplanificacioncalidadSet1New);
            detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidad);
            if (ejecucionplanificacioncalidadOld != null && !ejecucionplanificacioncalidadOld.equals(ejecucionplanificacioncalidadNew)) {
                ejecucionplanificacioncalidadOld.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidadOld = em.merge(ejecucionplanificacioncalidadOld);
            }
            if (ejecucionplanificacioncalidadNew != null && !ejecucionplanificacioncalidadNew.equals(ejecucionplanificacioncalidadOld)) {
                ejecucionplanificacioncalidadNew.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidadNew = em.merge(ejecucionplanificacioncalidadNew);
            }
            if (ejecucionplanificacioncalidad1Old != null && !ejecucionplanificacioncalidad1Old.equals(ejecucionplanificacioncalidad1New)) {
                ejecucionplanificacioncalidad1Old.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad1Old = em.merge(ejecucionplanificacioncalidad1Old);
            }
            if (ejecucionplanificacioncalidad1New != null && !ejecucionplanificacioncalidad1New.equals(ejecucionplanificacioncalidad1Old)) {
                ejecucionplanificacioncalidad1New.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad1New = em.merge(ejecucionplanificacioncalidad1New);
            }
            if (ejecucionprocesocalidadOld != null && !ejecucionprocesocalidadOld.equals(ejecucionprocesocalidadNew)) {
                ejecucionprocesocalidadOld.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidadOld = em.merge(ejecucionprocesocalidadOld);
            }
            if (ejecucionprocesocalidadNew != null && !ejecucionprocesocalidadNew.equals(ejecucionprocesocalidadOld)) {
                ejecucionprocesocalidadNew.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidadNew = em.merge(ejecucionprocesocalidadNew);
            }
            if (ejecucionprocesocalidad1Old != null && !ejecucionprocesocalidad1Old.equals(ejecucionprocesocalidad1New)) {
                ejecucionprocesocalidad1Old.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad1Old = em.merge(ejecucionprocesocalidad1Old);
            }
            if (ejecucionprocesocalidad1New != null && !ejecucionprocesocalidad1New.equals(ejecucionprocesocalidad1Old)) {
                ejecucionprocesocalidad1New.getDetalleejecucionplanificacioncalidadSet().add(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad1New = em.merge(ejecucionprocesocalidad1New);
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
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad : detalleplanificacioncalidadSet1New) {
                if (!detalleplanificacioncalidadSet1Old.contains(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad)) {
                    Detalleejecucionplanificacioncalidad oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad1();
                    detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad1(detalleejecucionplanificacioncalidad);
                    detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
                    if (oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad != null && !oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.equals(detalleejecucionplanificacioncalidad)) {
                        oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet1().remove(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
                        oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = em.merge(oldDetalleejecucionplanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
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
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1OrphanCheck = detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet1();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1OrphanCheckDetalleplanificacioncalidad : detalleplanificacioncalidadSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detalleejecucionplanificacioncalidad (" + detalleejecucionplanificacioncalidad + ") cannot be destroyed since the Detalleplanificacioncalidad " + detalleplanificacioncalidadSet1OrphanCheckDetalleplanificacioncalidad + " in its detalleplanificacioncalidadSet1 field has a non-nullable detalleejecucionplanificacioncalidad1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            }
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1 = detalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad1();
            if (ejecucionplanificacioncalidad1 != null) {
                ejecucionplanificacioncalidad1.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionplanificacioncalidad1 = em.merge(ejecucionplanificacioncalidad1);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad();
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad1 = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad1();
            if (ejecucionprocesocalidad1 != null) {
                ejecucionprocesocalidad1.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidad);
                ejecucionprocesocalidad1 = em.merge(ejecucionprocesocalidad1);
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
