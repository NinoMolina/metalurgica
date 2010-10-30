/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Etapadeproduccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Unidadmedida;
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;
import entity.Ejecucionetapaproduccion;
import entity.Piezaxetapadeproduccion;
import entity.Detallepiezapresupuesto;
import entity.Detalleplanprocedimientos;
import entity.Detalletrabajotercerizado;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EtapadeproduccionJpaController {

    public EtapadeproduccionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Etapadeproduccion etapadeproduccion) throws PreexistingEntityException, Exception {
        if (etapadeproduccion.getDetalleplanificacionproduccionSet() == null) {
            etapadeproduccion.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        if (etapadeproduccion.getDetalleplanificacionproduccionSet1() == null) {
            etapadeproduccion.setDetalleplanificacionproduccionSet1(new HashSet<Detalleplanificacionproduccion>());
        }
        if (etapadeproduccion.getEjecucionetapaproduccionSet() == null) {
            etapadeproduccion.setEjecucionetapaproduccionSet(new HashSet<Ejecucionetapaproduccion>());
        }
        if (etapadeproduccion.getEjecucionetapaproduccionSet1() == null) {
            etapadeproduccion.setEjecucionetapaproduccionSet1(new HashSet<Ejecucionetapaproduccion>());
        }
        if (etapadeproduccion.getPiezaxetapadeproduccionSet() == null) {
            etapadeproduccion.setPiezaxetapadeproduccionSet(new HashSet<Piezaxetapadeproduccion>());
        }
        if (etapadeproduccion.getPiezaxetapadeproduccionSet1() == null) {
            etapadeproduccion.setPiezaxetapadeproduccionSet1(new HashSet<Piezaxetapadeproduccion>());
        }
        if (etapadeproduccion.getDetallepiezapresupuestoSet() == null) {
            etapadeproduccion.setDetallepiezapresupuestoSet(new HashSet<Detallepiezapresupuesto>());
        }
        if (etapadeproduccion.getDetallepiezapresupuestoSet1() == null) {
            etapadeproduccion.setDetallepiezapresupuestoSet1(new HashSet<Detallepiezapresupuesto>());
        }
        if (etapadeproduccion.getDetalleplanprocedimientosSet() == null) {
            etapadeproduccion.setDetalleplanprocedimientosSet(new HashSet<Detalleplanprocedimientos>());
        }
        if (etapadeproduccion.getDetalleplanprocedimientosSet1() == null) {
            etapadeproduccion.setDetalleplanprocedimientosSet1(new HashSet<Detalleplanprocedimientos>());
        }
        if (etapadeproduccion.getDetalletrabajotercerizadoSet() == null) {
            etapadeproduccion.setDetalletrabajotercerizadoSet(new HashSet<Detalletrabajotercerizado>());
        }
        if (etapadeproduccion.getDetalletrabajotercerizadoSet1() == null) {
            etapadeproduccion.setDetalletrabajotercerizadoSet1(new HashSet<Detalletrabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidadmedida unidaddemedida = etapadeproduccion.getUnidaddemedida();
            if (unidaddemedida != null) {
                unidaddemedida = em.getReference(unidaddemedida.getClass(), unidaddemedida.getIdunidadmedida());
                etapadeproduccion.setUnidaddemedida(unidaddemedida);
            }
            Unidadmedida unidaddemedida1 = etapadeproduccion.getUnidaddemedida1();
            if (unidaddemedida1 != null) {
                unidaddemedida1 = em.getReference(unidaddemedida1.getClass(), unidaddemedida1.getIdunidadmedida());
                etapadeproduccion.setUnidaddemedida1(unidaddemedida1);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : etapadeproduccion.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            etapadeproduccion.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1 = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach : etapadeproduccion.getDetalleplanificacionproduccionSet1()) {
                detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1.add(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach);
            }
            etapadeproduccion.setDetalleplanificacionproduccionSet1(attachedDetalleplanificacionproduccionSet1);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach : etapadeproduccion.getEjecucionetapaproduccionSet()) {
                ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet.add(ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach);
            }
            etapadeproduccion.setEjecucionetapaproduccionSet(attachedEjecucionetapaproduccionSet);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet1 = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach : etapadeproduccion.getEjecucionetapaproduccionSet1()) {
                ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet1.add(ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach);
            }
            etapadeproduccion.setEjecucionetapaproduccionSet1(attachedEjecucionetapaproduccionSet1);
            Set<Piezaxetapadeproduccion> attachedPiezaxetapadeproduccionSet = new HashSet<Piezaxetapadeproduccion>();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach : etapadeproduccion.getPiezaxetapadeproduccionSet()) {
                piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach = em.getReference(piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach.getClass(), piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach.getPiezaxetapadeproduccionPK());
                attachedPiezaxetapadeproduccionSet.add(piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach);
            }
            etapadeproduccion.setPiezaxetapadeproduccionSet(attachedPiezaxetapadeproduccionSet);
            Set<Piezaxetapadeproduccion> attachedPiezaxetapadeproduccionSet1 = new HashSet<Piezaxetapadeproduccion>();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSet1PiezaxetapadeproduccionToAttach : etapadeproduccion.getPiezaxetapadeproduccionSet1()) {
                piezaxetapadeproduccionSet1PiezaxetapadeproduccionToAttach = em.getReference(piezaxetapadeproduccionSet1PiezaxetapadeproduccionToAttach.getClass(), piezaxetapadeproduccionSet1PiezaxetapadeproduccionToAttach.getPiezaxetapadeproduccionPK());
                attachedPiezaxetapadeproduccionSet1.add(piezaxetapadeproduccionSet1PiezaxetapadeproduccionToAttach);
            }
            etapadeproduccion.setPiezaxetapadeproduccionSet1(attachedPiezaxetapadeproduccionSet1);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSet = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuestoToAttach : etapadeproduccion.getDetallepiezapresupuestoSet()) {
                detallepiezapresupuestoSetDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSetDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSetDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSet.add(detallepiezapresupuestoSetDetallepiezapresupuestoToAttach);
            }
            etapadeproduccion.setDetallepiezapresupuestoSet(attachedDetallepiezapresupuestoSet);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSet1 = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach : etapadeproduccion.getDetallepiezapresupuestoSet1()) {
                detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSet1.add(detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach);
            }
            etapadeproduccion.setDetallepiezapresupuestoSet1(attachedDetallepiezapresupuestoSet1);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSet = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientosToAttach : etapadeproduccion.getDetalleplanprocedimientosSet()) {
                detalleplanprocedimientosSetDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSetDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSetDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSet.add(detalleplanprocedimientosSetDetalleplanprocedimientosToAttach);
            }
            etapadeproduccion.setDetalleplanprocedimientosSet(attachedDetalleplanprocedimientosSet);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSet1 = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach : etapadeproduccion.getDetalleplanprocedimientosSet1()) {
                detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSet1.add(detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach);
            }
            etapadeproduccion.setDetalleplanprocedimientosSet1(attachedDetalleplanprocedimientosSet1);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach : etapadeproduccion.getDetalletrabajotercerizadoSet()) {
                detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet.add(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach);
            }
            etapadeproduccion.setDetalletrabajotercerizadoSet(attachedDetalletrabajotercerizadoSet);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet1 = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach : etapadeproduccion.getDetalletrabajotercerizadoSet1()) {
                detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet1.add(detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach);
            }
            etapadeproduccion.setDetalletrabajotercerizadoSet1(attachedDetalletrabajotercerizadoSet1);
            em.persist(etapadeproduccion);
            if (unidaddemedida != null) {
                unidaddemedida.getEtapadeproduccionSet().add(etapadeproduccion);
                unidaddemedida = em.merge(unidaddemedida);
            }
            if (unidaddemedida1 != null) {
                unidaddemedida1.getEtapadeproduccionSet().add(etapadeproduccion);
                unidaddemedida1 = em.merge(unidaddemedida1);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : etapadeproduccion.getDetalleplanificacionproduccionSet()) {
                Etapadeproduccion oldIdetapaproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = detalleplanificacionproduccionSetDetalleplanificacionproduccion.getIdetapaproduccion();
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdetapaproduccion(etapadeproduccion);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                if (oldIdetapaproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion != null) {
                    oldIdetapaproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                    oldIdetapaproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(oldIdetapaproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : etapadeproduccion.getDetalleplanificacionproduccionSet1()) {
                Etapadeproduccion oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = detalleplanificacionproduccionSet1Detalleplanificacionproduccion.getIdetapaproduccion1();
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdetapaproduccion1(etapadeproduccion);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                if (oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion != null) {
                    oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                    oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccion : etapadeproduccion.getEjecucionetapaproduccionSet()) {
                Etapadeproduccion oldIdetapaproduccionOfEjecucionetapaproduccionSetEjecucionetapaproduccion = ejecucionetapaproduccionSetEjecucionetapaproduccion.getIdetapaproduccion();
                ejecucionetapaproduccionSetEjecucionetapaproduccion.setIdetapaproduccion(etapadeproduccion);
                ejecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetEjecucionetapaproduccion);
                if (oldIdetapaproduccionOfEjecucionetapaproduccionSetEjecucionetapaproduccion != null) {
                    oldIdetapaproduccionOfEjecucionetapaproduccionSetEjecucionetapaproduccion.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccionSetEjecucionetapaproduccion);
                    oldIdetapaproduccionOfEjecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(oldIdetapaproduccionOfEjecucionetapaproduccionSetEjecucionetapaproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1Ejecucionetapaproduccion : etapadeproduccion.getEjecucionetapaproduccionSet1()) {
                Etapadeproduccion oldIdetapaproduccion1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion = ejecucionetapaproduccionSet1Ejecucionetapaproduccion.getIdetapaproduccion1();
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion.setIdetapaproduccion1(etapadeproduccion);
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1Ejecucionetapaproduccion);
                if (oldIdetapaproduccion1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion != null) {
                    oldIdetapaproduccion1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion.getEjecucionetapaproduccionSet1().remove(ejecucionetapaproduccionSet1Ejecucionetapaproduccion);
                    oldIdetapaproduccion1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion = em.merge(oldIdetapaproduccion1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion);
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetPiezaxetapadeproduccion : etapadeproduccion.getPiezaxetapadeproduccionSet()) {
                Etapadeproduccion oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion = piezaxetapadeproduccionSetPiezaxetapadeproduccion.getEtapadeproduccion();
                piezaxetapadeproduccionSetPiezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccion);
                piezaxetapadeproduccionSetPiezaxetapadeproduccion = em.merge(piezaxetapadeproduccionSetPiezaxetapadeproduccion);
                if (oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion != null) {
                    oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion.getPiezaxetapadeproduccionSet().remove(piezaxetapadeproduccionSetPiezaxetapadeproduccion);
                    oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion = em.merge(oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion);
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSet1Piezaxetapadeproduccion : etapadeproduccion.getPiezaxetapadeproduccionSet1()) {
                Etapadeproduccion oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1Piezaxetapadeproduccion = piezaxetapadeproduccionSet1Piezaxetapadeproduccion.getEtapadeproduccion1();
                piezaxetapadeproduccionSet1Piezaxetapadeproduccion.setEtapadeproduccion1(etapadeproduccion);
                piezaxetapadeproduccionSet1Piezaxetapadeproduccion = em.merge(piezaxetapadeproduccionSet1Piezaxetapadeproduccion);
                if (oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1Piezaxetapadeproduccion != null) {
                    oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1Piezaxetapadeproduccion.getPiezaxetapadeproduccionSet1().remove(piezaxetapadeproduccionSet1Piezaxetapadeproduccion);
                    oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1Piezaxetapadeproduccion = em.merge(oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1Piezaxetapadeproduccion);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuesto : etapadeproduccion.getDetallepiezapresupuestoSet()) {
                Etapadeproduccion oldIdetapaOfDetallepiezapresupuestoSetDetallepiezapresupuesto = detallepiezapresupuestoSetDetallepiezapresupuesto.getIdetapa();
                detallepiezapresupuestoSetDetallepiezapresupuesto.setIdetapa(etapadeproduccion);
                detallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetDetallepiezapresupuesto);
                if (oldIdetapaOfDetallepiezapresupuestoSetDetallepiezapresupuesto != null) {
                    oldIdetapaOfDetallepiezapresupuestoSetDetallepiezapresupuesto.getDetallepiezapresupuestoSet().remove(detallepiezapresupuestoSetDetallepiezapresupuesto);
                    oldIdetapaOfDetallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(oldIdetapaOfDetallepiezapresupuestoSetDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1Detallepiezapresupuesto : etapadeproduccion.getDetallepiezapresupuestoSet1()) {
                Etapadeproduccion oldIdetapa1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto = detallepiezapresupuestoSet1Detallepiezapresupuesto.getIdetapa1();
                detallepiezapresupuestoSet1Detallepiezapresupuesto.setIdetapa1(etapadeproduccion);
                detallepiezapresupuestoSet1Detallepiezapresupuesto = em.merge(detallepiezapresupuestoSet1Detallepiezapresupuesto);
                if (oldIdetapa1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto != null) {
                    oldIdetapa1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto.getDetallepiezapresupuestoSet1().remove(detallepiezapresupuestoSet1Detallepiezapresupuesto);
                    oldIdetapa1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto = em.merge(oldIdetapa1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto);
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientos : etapadeproduccion.getDetalleplanprocedimientosSet()) {
                Etapadeproduccion oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos = detalleplanprocedimientosSetDetalleplanprocedimientos.getIdetapaproduccion();
                detalleplanprocedimientosSetDetalleplanprocedimientos.setIdetapaproduccion(etapadeproduccion);
                detalleplanprocedimientosSetDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSetDetalleplanprocedimientos);
                if (oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos != null) {
                    oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos.getDetalleplanprocedimientosSet().remove(detalleplanprocedimientosSetDetalleplanprocedimientos);
                    oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos = em.merge(oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos);
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1Detalleplanprocedimientos : etapadeproduccion.getDetalleplanprocedimientosSet1()) {
                Etapadeproduccion oldIdetapaproduccion1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos = detalleplanprocedimientosSet1Detalleplanprocedimientos.getIdetapaproduccion1();
                detalleplanprocedimientosSet1Detalleplanprocedimientos.setIdetapaproduccion1(etapadeproduccion);
                detalleplanprocedimientosSet1Detalleplanprocedimientos = em.merge(detalleplanprocedimientosSet1Detalleplanprocedimientos);
                if (oldIdetapaproduccion1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos != null) {
                    oldIdetapaproduccion1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos.getDetalleplanprocedimientosSet1().remove(detalleplanprocedimientosSet1Detalleplanprocedimientos);
                    oldIdetapaproduccion1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos = em.merge(oldIdetapaproduccion1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizado : etapadeproduccion.getDetalletrabajotercerizadoSet()) {
                Etapadeproduccion oldProcesoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado = detalletrabajotercerizadoSetDetalletrabajotercerizado.getProceso();
                detalletrabajotercerizadoSetDetalletrabajotercerizado.setProceso(etapadeproduccion);
                detalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetDetalletrabajotercerizado);
                if (oldProcesoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado != null) {
                    oldProcesoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizadoSetDetalletrabajotercerizado);
                    oldProcesoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(oldProcesoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1Detalletrabajotercerizado : etapadeproduccion.getDetalletrabajotercerizadoSet1()) {
                Etapadeproduccion oldProceso1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado = detalletrabajotercerizadoSet1Detalletrabajotercerizado.getProceso1();
                detalletrabajotercerizadoSet1Detalletrabajotercerizado.setProceso1(etapadeproduccion);
                detalletrabajotercerizadoSet1Detalletrabajotercerizado = em.merge(detalletrabajotercerizadoSet1Detalletrabajotercerizado);
                if (oldProceso1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado != null) {
                    oldProceso1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado.getDetalletrabajotercerizadoSet1().remove(detalletrabajotercerizadoSet1Detalletrabajotercerizado);
                    oldProceso1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado = em.merge(oldProceso1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEtapadeproduccion(etapadeproduccion.getIdetapaproduccion()) != null) {
                throw new PreexistingEntityException("Etapadeproduccion " + etapadeproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Etapadeproduccion etapadeproduccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Etapadeproduccion persistentEtapadeproduccion = em.find(Etapadeproduccion.class, etapadeproduccion.getIdetapaproduccion());
            Unidadmedida unidaddemedidaOld = persistentEtapadeproduccion.getUnidaddemedida();
            Unidadmedida unidaddemedidaNew = etapadeproduccion.getUnidaddemedida();
            Unidadmedida unidaddemedida1Old = persistentEtapadeproduccion.getUnidaddemedida1();
            Unidadmedida unidaddemedida1New = etapadeproduccion.getUnidaddemedida1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentEtapadeproduccion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = etapadeproduccion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1Old = persistentEtapadeproduccion.getDetalleplanificacionproduccionSet1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1New = etapadeproduccion.getDetalleplanificacionproduccionSet1();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetOld = persistentEtapadeproduccion.getEjecucionetapaproduccionSet();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetNew = etapadeproduccion.getEjecucionetapaproduccionSet();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1Old = persistentEtapadeproduccion.getEjecucionetapaproduccionSet1();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1New = etapadeproduccion.getEjecucionetapaproduccionSet1();
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSetOld = persistentEtapadeproduccion.getPiezaxetapadeproduccionSet();
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSetNew = etapadeproduccion.getPiezaxetapadeproduccionSet();
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSet1Old = persistentEtapadeproduccion.getPiezaxetapadeproduccionSet1();
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSet1New = etapadeproduccion.getPiezaxetapadeproduccionSet1();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSetOld = persistentEtapadeproduccion.getDetallepiezapresupuestoSet();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSetNew = etapadeproduccion.getDetallepiezapresupuestoSet();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet1Old = persistentEtapadeproduccion.getDetallepiezapresupuestoSet1();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet1New = etapadeproduccion.getDetallepiezapresupuestoSet1();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetOld = persistentEtapadeproduccion.getDetalleplanprocedimientosSet();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetNew = etapadeproduccion.getDetalleplanprocedimientosSet();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSet1Old = persistentEtapadeproduccion.getDetalleplanprocedimientosSet1();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSet1New = etapadeproduccion.getDetalleplanprocedimientosSet1();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetOld = persistentEtapadeproduccion.getDetalletrabajotercerizadoSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetNew = etapadeproduccion.getDetalletrabajotercerizadoSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet1Old = persistentEtapadeproduccion.getDetalletrabajotercerizadoSet1();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet1New = etapadeproduccion.getDetalletrabajotercerizadoSet1();
            List<String> illegalOrphanMessages = null;
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetOldEjecucionetapaproduccion : ejecucionetapaproduccionSetOld) {
                if (!ejecucionetapaproduccionSetNew.contains(ejecucionetapaproduccionSetOldEjecucionetapaproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ejecucionetapaproduccion " + ejecucionetapaproduccionSetOldEjecucionetapaproduccion + " since its idetapaproduccion field is not nullable.");
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1OldEjecucionetapaproduccion : ejecucionetapaproduccionSet1Old) {
                if (!ejecucionetapaproduccionSet1New.contains(ejecucionetapaproduccionSet1OldEjecucionetapaproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ejecucionetapaproduccion " + ejecucionetapaproduccionSet1OldEjecucionetapaproduccion + " since its idetapaproduccion1 field is not nullable.");
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetOldPiezaxetapadeproduccion : piezaxetapadeproduccionSetOld) {
                if (!piezaxetapadeproduccionSetNew.contains(piezaxetapadeproduccionSetOldPiezaxetapadeproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Piezaxetapadeproduccion " + piezaxetapadeproduccionSetOldPiezaxetapadeproduccion + " since its etapadeproduccion field is not nullable.");
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSet1OldPiezaxetapadeproduccion : piezaxetapadeproduccionSet1Old) {
                if (!piezaxetapadeproduccionSet1New.contains(piezaxetapadeproduccionSet1OldPiezaxetapadeproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Piezaxetapadeproduccion " + piezaxetapadeproduccionSet1OldPiezaxetapadeproduccion + " since its etapadeproduccion1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unidaddemedidaNew != null) {
                unidaddemedidaNew = em.getReference(unidaddemedidaNew.getClass(), unidaddemedidaNew.getIdunidadmedida());
                etapadeproduccion.setUnidaddemedida(unidaddemedidaNew);
            }
            if (unidaddemedida1New != null) {
                unidaddemedida1New = em.getReference(unidaddemedida1New.getClass(), unidaddemedida1New.getIdunidadmedida());
                etapadeproduccion.setUnidaddemedida1(unidaddemedida1New);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            etapadeproduccion.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1New = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSet1New) {
                detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1New.add(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSet1New = attachedDetalleplanificacionproduccionSet1New;
            etapadeproduccion.setDetalleplanificacionproduccionSet1(detalleplanificacionproduccionSet1New);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSetNew = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionSetNew) {
                ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSetNew.add(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionSetNew = attachedEjecucionetapaproduccionSetNew;
            etapadeproduccion.setEjecucionetapaproduccionSet(ejecucionetapaproduccionSetNew);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet1New = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionSet1New) {
                ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet1New.add(ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionSet1New = attachedEjecucionetapaproduccionSet1New;
            etapadeproduccion.setEjecucionetapaproduccionSet1(ejecucionetapaproduccionSet1New);
            Set<Piezaxetapadeproduccion> attachedPiezaxetapadeproduccionSetNew = new HashSet<Piezaxetapadeproduccion>();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach : piezaxetapadeproduccionSetNew) {
                piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach = em.getReference(piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach.getClass(), piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach.getPiezaxetapadeproduccionPK());
                attachedPiezaxetapadeproduccionSetNew.add(piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach);
            }
            piezaxetapadeproduccionSetNew = attachedPiezaxetapadeproduccionSetNew;
            etapadeproduccion.setPiezaxetapadeproduccionSet(piezaxetapadeproduccionSetNew);
            Set<Piezaxetapadeproduccion> attachedPiezaxetapadeproduccionSet1New = new HashSet<Piezaxetapadeproduccion>();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSet1NewPiezaxetapadeproduccionToAttach : piezaxetapadeproduccionSet1New) {
                piezaxetapadeproduccionSet1NewPiezaxetapadeproduccionToAttach = em.getReference(piezaxetapadeproduccionSet1NewPiezaxetapadeproduccionToAttach.getClass(), piezaxetapadeproduccionSet1NewPiezaxetapadeproduccionToAttach.getPiezaxetapadeproduccionPK());
                attachedPiezaxetapadeproduccionSet1New.add(piezaxetapadeproduccionSet1NewPiezaxetapadeproduccionToAttach);
            }
            piezaxetapadeproduccionSet1New = attachedPiezaxetapadeproduccionSet1New;
            etapadeproduccion.setPiezaxetapadeproduccionSet1(piezaxetapadeproduccionSet1New);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSetNew = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach : detallepiezapresupuestoSetNew) {
                detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSetNew.add(detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach);
            }
            detallepiezapresupuestoSetNew = attachedDetallepiezapresupuestoSetNew;
            etapadeproduccion.setDetallepiezapresupuestoSet(detallepiezapresupuestoSetNew);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSet1New = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach : detallepiezapresupuestoSet1New) {
                detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSet1New.add(detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach);
            }
            detallepiezapresupuestoSet1New = attachedDetallepiezapresupuestoSet1New;
            etapadeproduccion.setDetallepiezapresupuestoSet1(detallepiezapresupuestoSet1New);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSetNew = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach : detalleplanprocedimientosSetNew) {
                detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSetNew.add(detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach);
            }
            detalleplanprocedimientosSetNew = attachedDetalleplanprocedimientosSetNew;
            etapadeproduccion.setDetalleplanprocedimientosSet(detalleplanprocedimientosSetNew);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSet1New = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach : detalleplanprocedimientosSet1New) {
                detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSet1New.add(detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach);
            }
            detalleplanprocedimientosSet1New = attachedDetalleplanprocedimientosSet1New;
            etapadeproduccion.setDetalleplanprocedimientosSet1(detalleplanprocedimientosSet1New);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSetNew = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoSetNew) {
                detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSetNew.add(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoSetNew = attachedDetalletrabajotercerizadoSetNew;
            etapadeproduccion.setDetalletrabajotercerizadoSet(detalletrabajotercerizadoSetNew);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet1New = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoSet1New) {
                detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet1New.add(detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoSet1New = attachedDetalletrabajotercerizadoSet1New;
            etapadeproduccion.setDetalletrabajotercerizadoSet1(detalletrabajotercerizadoSet1New);
            etapadeproduccion = em.merge(etapadeproduccion);
            if (unidaddemedidaOld != null && !unidaddemedidaOld.equals(unidaddemedidaNew)) {
                unidaddemedidaOld.getEtapadeproduccionSet().remove(etapadeproduccion);
                unidaddemedidaOld = em.merge(unidaddemedidaOld);
            }
            if (unidaddemedidaNew != null && !unidaddemedidaNew.equals(unidaddemedidaOld)) {
                unidaddemedidaNew.getEtapadeproduccionSet().add(etapadeproduccion);
                unidaddemedidaNew = em.merge(unidaddemedidaNew);
            }
            if (unidaddemedida1Old != null && !unidaddemedida1Old.equals(unidaddemedida1New)) {
                unidaddemedida1Old.getEtapadeproduccionSet().remove(etapadeproduccion);
                unidaddemedida1Old = em.merge(unidaddemedida1Old);
            }
            if (unidaddemedida1New != null && !unidaddemedida1New.equals(unidaddemedida1Old)) {
                unidaddemedida1New.getEtapadeproduccionSet().add(etapadeproduccion);
                unidaddemedida1New = em.merge(unidaddemedida1New);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion.setIdetapaproduccion(null);
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccion : detalleplanificacionproduccionSetNew) {
                if (!detalleplanificacionproduccionSetOld.contains(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion)) {
                    Etapadeproduccion oldIdetapaproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getIdetapaproduccion();
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.setIdetapaproduccion(etapadeproduccion);
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    if (oldIdetapaproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion != null && !oldIdetapaproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.equals(etapadeproduccion)) {
                        oldIdetapaproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                        oldIdetapaproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(oldIdetapaproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion : detalleplanificacionproduccionSet1Old) {
                if (!detalleplanificacionproduccionSet1New.contains(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion.setIdetapaproduccion1(null);
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion : detalleplanificacionproduccionSet1New) {
                if (!detalleplanificacionproduccionSet1Old.contains(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion)) {
                    Etapadeproduccion oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getIdetapaproduccion1();
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.setIdetapaproduccion1(etapadeproduccion);
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    if (oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion != null && !oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.equals(etapadeproduccion)) {
                        oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                        oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(oldIdetapaproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetNewEjecucionetapaproduccion : ejecucionetapaproduccionSetNew) {
                if (!ejecucionetapaproduccionSetOld.contains(ejecucionetapaproduccionSetNewEjecucionetapaproduccion)) {
                    Etapadeproduccion oldIdetapaproduccionOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion = ejecucionetapaproduccionSetNewEjecucionetapaproduccion.getIdetapaproduccion();
                    ejecucionetapaproduccionSetNewEjecucionetapaproduccion.setIdetapaproduccion(etapadeproduccion);
                    ejecucionetapaproduccionSetNewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetNewEjecucionetapaproduccion);
                    if (oldIdetapaproduccionOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion != null && !oldIdetapaproduccionOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion.equals(etapadeproduccion)) {
                        oldIdetapaproduccionOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccionSetNewEjecucionetapaproduccion);
                        oldIdetapaproduccionOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion = em.merge(oldIdetapaproduccionOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion);
                    }
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1NewEjecucionetapaproduccion : ejecucionetapaproduccionSet1New) {
                if (!ejecucionetapaproduccionSet1Old.contains(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion)) {
                    Etapadeproduccion oldIdetapaproduccion1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion = ejecucionetapaproduccionSet1NewEjecucionetapaproduccion.getIdetapaproduccion1();
                    ejecucionetapaproduccionSet1NewEjecucionetapaproduccion.setIdetapaproduccion1(etapadeproduccion);
                    ejecucionetapaproduccionSet1NewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion);
                    if (oldIdetapaproduccion1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion != null && !oldIdetapaproduccion1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion.equals(etapadeproduccion)) {
                        oldIdetapaproduccion1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion.getEjecucionetapaproduccionSet1().remove(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion);
                        oldIdetapaproduccion1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion = em.merge(oldIdetapaproduccion1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion);
                    }
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetNewPiezaxetapadeproduccion : piezaxetapadeproduccionSetNew) {
                if (!piezaxetapadeproduccionSetOld.contains(piezaxetapadeproduccionSetNewPiezaxetapadeproduccion)) {
                    Etapadeproduccion oldEtapadeproduccionOfPiezaxetapadeproduccionSetNewPiezaxetapadeproduccion = piezaxetapadeproduccionSetNewPiezaxetapadeproduccion.getEtapadeproduccion();
                    piezaxetapadeproduccionSetNewPiezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccion);
                    piezaxetapadeproduccionSetNewPiezaxetapadeproduccion = em.merge(piezaxetapadeproduccionSetNewPiezaxetapadeproduccion);
                    if (oldEtapadeproduccionOfPiezaxetapadeproduccionSetNewPiezaxetapadeproduccion != null && !oldEtapadeproduccionOfPiezaxetapadeproduccionSetNewPiezaxetapadeproduccion.equals(etapadeproduccion)) {
                        oldEtapadeproduccionOfPiezaxetapadeproduccionSetNewPiezaxetapadeproduccion.getPiezaxetapadeproduccionSet().remove(piezaxetapadeproduccionSetNewPiezaxetapadeproduccion);
                        oldEtapadeproduccionOfPiezaxetapadeproduccionSetNewPiezaxetapadeproduccion = em.merge(oldEtapadeproduccionOfPiezaxetapadeproduccionSetNewPiezaxetapadeproduccion);
                    }
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSet1NewPiezaxetapadeproduccion : piezaxetapadeproduccionSet1New) {
                if (!piezaxetapadeproduccionSet1Old.contains(piezaxetapadeproduccionSet1NewPiezaxetapadeproduccion)) {
                    Etapadeproduccion oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1NewPiezaxetapadeproduccion = piezaxetapadeproduccionSet1NewPiezaxetapadeproduccion.getEtapadeproduccion1();
                    piezaxetapadeproduccionSet1NewPiezaxetapadeproduccion.setEtapadeproduccion1(etapadeproduccion);
                    piezaxetapadeproduccionSet1NewPiezaxetapadeproduccion = em.merge(piezaxetapadeproduccionSet1NewPiezaxetapadeproduccion);
                    if (oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1NewPiezaxetapadeproduccion != null && !oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1NewPiezaxetapadeproduccion.equals(etapadeproduccion)) {
                        oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1NewPiezaxetapadeproduccion.getPiezaxetapadeproduccionSet1().remove(piezaxetapadeproduccionSet1NewPiezaxetapadeproduccion);
                        oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1NewPiezaxetapadeproduccion = em.merge(oldEtapadeproduccion1OfPiezaxetapadeproduccionSet1NewPiezaxetapadeproduccion);
                    }
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetOldDetallepiezapresupuesto : detallepiezapresupuestoSetOld) {
                if (!detallepiezapresupuestoSetNew.contains(detallepiezapresupuestoSetOldDetallepiezapresupuesto)) {
                    detallepiezapresupuestoSetOldDetallepiezapresupuesto.setIdetapa(null);
                    detallepiezapresupuestoSetOldDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetOldDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetNewDetallepiezapresupuesto : detallepiezapresupuestoSetNew) {
                if (!detallepiezapresupuestoSetOld.contains(detallepiezapresupuestoSetNewDetallepiezapresupuesto)) {
                    Etapadeproduccion oldIdetapaOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto = detallepiezapresupuestoSetNewDetallepiezapresupuesto.getIdetapa();
                    detallepiezapresupuestoSetNewDetallepiezapresupuesto.setIdetapa(etapadeproduccion);
                    detallepiezapresupuestoSetNewDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetNewDetallepiezapresupuesto);
                    if (oldIdetapaOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto != null && !oldIdetapaOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto.equals(etapadeproduccion)) {
                        oldIdetapaOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto.getDetallepiezapresupuestoSet().remove(detallepiezapresupuestoSetNewDetallepiezapresupuesto);
                        oldIdetapaOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto = em.merge(oldIdetapaOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto);
                    }
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1OldDetallepiezapresupuesto : detallepiezapresupuestoSet1Old) {
                if (!detallepiezapresupuestoSet1New.contains(detallepiezapresupuestoSet1OldDetallepiezapresupuesto)) {
                    detallepiezapresupuestoSet1OldDetallepiezapresupuesto.setIdetapa1(null);
                    detallepiezapresupuestoSet1OldDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSet1OldDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1NewDetallepiezapresupuesto : detallepiezapresupuestoSet1New) {
                if (!detallepiezapresupuestoSet1Old.contains(detallepiezapresupuestoSet1NewDetallepiezapresupuesto)) {
                    Etapadeproduccion oldIdetapa1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto = detallepiezapresupuestoSet1NewDetallepiezapresupuesto.getIdetapa1();
                    detallepiezapresupuestoSet1NewDetallepiezapresupuesto.setIdetapa1(etapadeproduccion);
                    detallepiezapresupuestoSet1NewDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSet1NewDetallepiezapresupuesto);
                    if (oldIdetapa1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto != null && !oldIdetapa1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto.equals(etapadeproduccion)) {
                        oldIdetapa1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto.getDetallepiezapresupuestoSet1().remove(detallepiezapresupuestoSet1NewDetallepiezapresupuesto);
                        oldIdetapa1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto = em.merge(oldIdetapa1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto);
                    }
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSetOldDetalleplanprocedimientos : detalleplanprocedimientosSetOld) {
                if (!detalleplanprocedimientosSetNew.contains(detalleplanprocedimientosSetOldDetalleplanprocedimientos)) {
                    detalleplanprocedimientosSetOldDetalleplanprocedimientos.setIdetapaproduccion(null);
                    detalleplanprocedimientosSetOldDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSetOldDetalleplanprocedimientos);
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSetNewDetalleplanprocedimientos : detalleplanprocedimientosSetNew) {
                if (!detalleplanprocedimientosSetOld.contains(detalleplanprocedimientosSetNewDetalleplanprocedimientos)) {
                    Etapadeproduccion oldIdetapaproduccionOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos = detalleplanprocedimientosSetNewDetalleplanprocedimientos.getIdetapaproduccion();
                    detalleplanprocedimientosSetNewDetalleplanprocedimientos.setIdetapaproduccion(etapadeproduccion);
                    detalleplanprocedimientosSetNewDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSetNewDetalleplanprocedimientos);
                    if (oldIdetapaproduccionOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos != null && !oldIdetapaproduccionOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos.equals(etapadeproduccion)) {
                        oldIdetapaproduccionOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos.getDetalleplanprocedimientosSet().remove(detalleplanprocedimientosSetNewDetalleplanprocedimientos);
                        oldIdetapaproduccionOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos = em.merge(oldIdetapaproduccionOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos);
                    }
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1OldDetalleplanprocedimientos : detalleplanprocedimientosSet1Old) {
                if (!detalleplanprocedimientosSet1New.contains(detalleplanprocedimientosSet1OldDetalleplanprocedimientos)) {
                    detalleplanprocedimientosSet1OldDetalleplanprocedimientos.setIdetapaproduccion1(null);
                    detalleplanprocedimientosSet1OldDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSet1OldDetalleplanprocedimientos);
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1NewDetalleplanprocedimientos : detalleplanprocedimientosSet1New) {
                if (!detalleplanprocedimientosSet1Old.contains(detalleplanprocedimientosSet1NewDetalleplanprocedimientos)) {
                    Etapadeproduccion oldIdetapaproduccion1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos = detalleplanprocedimientosSet1NewDetalleplanprocedimientos.getIdetapaproduccion1();
                    detalleplanprocedimientosSet1NewDetalleplanprocedimientos.setIdetapaproduccion1(etapadeproduccion);
                    detalleplanprocedimientosSet1NewDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSet1NewDetalleplanprocedimientos);
                    if (oldIdetapaproduccion1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos != null && !oldIdetapaproduccion1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos.equals(etapadeproduccion)) {
                        oldIdetapaproduccion1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos.getDetalleplanprocedimientosSet1().remove(detalleplanprocedimientosSet1NewDetalleplanprocedimientos);
                        oldIdetapaproduccion1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos = em.merge(oldIdetapaproduccion1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos);
                    }
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetOldDetalletrabajotercerizado : detalletrabajotercerizadoSetOld) {
                if (!detalletrabajotercerizadoSetNew.contains(detalletrabajotercerizadoSetOldDetalletrabajotercerizado)) {
                    detalletrabajotercerizadoSetOldDetalletrabajotercerizado.setProceso(null);
                    detalletrabajotercerizadoSetOldDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetOldDetalletrabajotercerizado);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizado : detalletrabajotercerizadoSetNew) {
                if (!detalletrabajotercerizadoSetOld.contains(detalletrabajotercerizadoSetNewDetalletrabajotercerizado)) {
                    Etapadeproduccion oldProcesoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado = detalletrabajotercerizadoSetNewDetalletrabajotercerizado.getProceso();
                    detalletrabajotercerizadoSetNewDetalletrabajotercerizado.setProceso(etapadeproduccion);
                    detalletrabajotercerizadoSetNewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                    if (oldProcesoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado != null && !oldProcesoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado.equals(etapadeproduccion)) {
                        oldProcesoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                        oldProcesoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado = em.merge(oldProcesoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                    }
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1OldDetalletrabajotercerizado : detalletrabajotercerizadoSet1Old) {
                if (!detalletrabajotercerizadoSet1New.contains(detalletrabajotercerizadoSet1OldDetalletrabajotercerizado)) {
                    detalletrabajotercerizadoSet1OldDetalletrabajotercerizado.setProceso1(null);
                    detalletrabajotercerizadoSet1OldDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSet1OldDetalletrabajotercerizado);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1NewDetalletrabajotercerizado : detalletrabajotercerizadoSet1New) {
                if (!detalletrabajotercerizadoSet1Old.contains(detalletrabajotercerizadoSet1NewDetalletrabajotercerizado)) {
                    Etapadeproduccion oldProceso1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado = detalletrabajotercerizadoSet1NewDetalletrabajotercerizado.getProceso1();
                    detalletrabajotercerizadoSet1NewDetalletrabajotercerizado.setProceso1(etapadeproduccion);
                    detalletrabajotercerizadoSet1NewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSet1NewDetalletrabajotercerizado);
                    if (oldProceso1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado != null && !oldProceso1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado.equals(etapadeproduccion)) {
                        oldProceso1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado.getDetalletrabajotercerizadoSet1().remove(detalletrabajotercerizadoSet1NewDetalletrabajotercerizado);
                        oldProceso1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado = em.merge(oldProceso1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = etapadeproduccion.getIdetapaproduccion();
                if (findEtapadeproduccion(id) == null) {
                    throw new NonexistentEntityException("The etapadeproduccion with id " + id + " no longer exists.");
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
            Etapadeproduccion etapadeproduccion;
            try {
                etapadeproduccion = em.getReference(Etapadeproduccion.class, id);
                etapadeproduccion.getIdetapaproduccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The etapadeproduccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetOrphanCheck = etapadeproduccion.getEjecucionetapaproduccionSet();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetOrphanCheckEjecucionetapaproduccion : ejecucionetapaproduccionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Etapadeproduccion (" + etapadeproduccion + ") cannot be destroyed since the Ejecucionetapaproduccion " + ejecucionetapaproduccionSetOrphanCheckEjecucionetapaproduccion + " in its ejecucionetapaproduccionSet field has a non-nullable idetapaproduccion field.");
            }
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1OrphanCheck = etapadeproduccion.getEjecucionetapaproduccionSet1();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1OrphanCheckEjecucionetapaproduccion : ejecucionetapaproduccionSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Etapadeproduccion (" + etapadeproduccion + ") cannot be destroyed since the Ejecucionetapaproduccion " + ejecucionetapaproduccionSet1OrphanCheckEjecucionetapaproduccion + " in its ejecucionetapaproduccionSet1 field has a non-nullable idetapaproduccion1 field.");
            }
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSetOrphanCheck = etapadeproduccion.getPiezaxetapadeproduccionSet();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetOrphanCheckPiezaxetapadeproduccion : piezaxetapadeproduccionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Etapadeproduccion (" + etapadeproduccion + ") cannot be destroyed since the Piezaxetapadeproduccion " + piezaxetapadeproduccionSetOrphanCheckPiezaxetapadeproduccion + " in its piezaxetapadeproduccionSet field has a non-nullable etapadeproduccion field.");
            }
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSet1OrphanCheck = etapadeproduccion.getPiezaxetapadeproduccionSet1();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSet1OrphanCheckPiezaxetapadeproduccion : piezaxetapadeproduccionSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Etapadeproduccion (" + etapadeproduccion + ") cannot be destroyed since the Piezaxetapadeproduccion " + piezaxetapadeproduccionSet1OrphanCheckPiezaxetapadeproduccion + " in its piezaxetapadeproduccionSet1 field has a non-nullable etapadeproduccion1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Unidadmedida unidaddemedida = etapadeproduccion.getUnidaddemedida();
            if (unidaddemedida != null) {
                unidaddemedida.getEtapadeproduccionSet().remove(etapadeproduccion);
                unidaddemedida = em.merge(unidaddemedida);
            }
            Unidadmedida unidaddemedida1 = etapadeproduccion.getUnidaddemedida1();
            if (unidaddemedida1 != null) {
                unidaddemedida1.getEtapadeproduccionSet().remove(etapadeproduccion);
                unidaddemedida1 = em.merge(unidaddemedida1);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = etapadeproduccion.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdetapaproduccion(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1 = etapadeproduccion.getDetalleplanificacionproduccionSet1();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : detalleplanificacionproduccionSet1) {
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdetapaproduccion1(null);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
            }
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet = etapadeproduccion.getDetallepiezapresupuestoSet();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuesto : detallepiezapresupuestoSet) {
                detallepiezapresupuestoSetDetallepiezapresupuesto.setIdetapa(null);
                detallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetDetallepiezapresupuesto);
            }
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet1 = etapadeproduccion.getDetallepiezapresupuestoSet1();
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1Detallepiezapresupuesto : detallepiezapresupuestoSet1) {
                detallepiezapresupuestoSet1Detallepiezapresupuesto.setIdetapa1(null);
                detallepiezapresupuestoSet1Detallepiezapresupuesto = em.merge(detallepiezapresupuestoSet1Detallepiezapresupuesto);
            }
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSet = etapadeproduccion.getDetalleplanprocedimientosSet();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientos : detalleplanprocedimientosSet) {
                detalleplanprocedimientosSetDetalleplanprocedimientos.setIdetapaproduccion(null);
                detalleplanprocedimientosSetDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSetDetalleplanprocedimientos);
            }
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSet1 = etapadeproduccion.getDetalleplanprocedimientosSet1();
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1Detalleplanprocedimientos : detalleplanprocedimientosSet1) {
                detalleplanprocedimientosSet1Detalleplanprocedimientos.setIdetapaproduccion1(null);
                detalleplanprocedimientosSet1Detalleplanprocedimientos = em.merge(detalleplanprocedimientosSet1Detalleplanprocedimientos);
            }
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet = etapadeproduccion.getDetalletrabajotercerizadoSet();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizado : detalletrabajotercerizadoSet) {
                detalletrabajotercerizadoSetDetalletrabajotercerizado.setProceso(null);
                detalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetDetalletrabajotercerizado);
            }
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet1 = etapadeproduccion.getDetalletrabajotercerizadoSet1();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1Detalletrabajotercerizado : detalletrabajotercerizadoSet1) {
                detalletrabajotercerizadoSet1Detalletrabajotercerizado.setProceso1(null);
                detalletrabajotercerizadoSet1Detalletrabajotercerizado = em.merge(detalletrabajotercerizadoSet1Detalletrabajotercerizado);
            }
            em.remove(etapadeproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Etapadeproduccion> findEtapadeproduccionEntities() {
        return findEtapadeproduccionEntities(true, -1, -1);
    }

    public List<Etapadeproduccion> findEtapadeproduccionEntities(int maxResults, int firstResult) {
        return findEtapadeproduccionEntities(false, maxResults, firstResult);
    }

    private List<Etapadeproduccion> findEtapadeproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Etapadeproduccion.class));
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

    public Etapadeproduccion findEtapadeproduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Etapadeproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEtapadeproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Etapadeproduccion> rt = cq.from(Etapadeproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
