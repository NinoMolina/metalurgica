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
import entity.Ejecucionetapaproduccion;
import entity.Unidadmedida;
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;
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
        if (etapadeproduccion.getPiezaxetapadeproduccionSet() == null) {
            etapadeproduccion.setPiezaxetapadeproduccionSet(new HashSet<Piezaxetapadeproduccion>());
        }
        if (etapadeproduccion.getDetallepiezapresupuestoSet() == null) {
            etapadeproduccion.setDetallepiezapresupuestoSet(new HashSet<Detallepiezapresupuesto>());
        }
        if (etapadeproduccion.getDetalleplanprocedimientosSet() == null) {
            etapadeproduccion.setDetalleplanprocedimientosSet(new HashSet<Detalleplanprocedimientos>());
        }
        if (etapadeproduccion.getDetalletrabajotercerizadoSet() == null) {
            etapadeproduccion.setDetalletrabajotercerizadoSet(new HashSet<Detalletrabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionetapaproduccion ejecucionetapaproduccion = etapadeproduccion.getEjecucionetapaproduccion();
            if (ejecucionetapaproduccion != null) {
                ejecucionetapaproduccion = em.getReference(ejecucionetapaproduccion.getClass(), ejecucionetapaproduccion.getEjecucionetapaproduccionPK());
                etapadeproduccion.setEjecucionetapaproduccion(ejecucionetapaproduccion);
            }
            Unidadmedida unidaddemedida = etapadeproduccion.getUnidaddemedida();
            if (unidaddemedida != null) {
                unidaddemedida = em.getReference(unidaddemedida.getClass(), unidaddemedida.getIdunidadmedida());
                etapadeproduccion.setUnidaddemedida(unidaddemedida);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : etapadeproduccion.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            etapadeproduccion.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Piezaxetapadeproduccion> attachedPiezaxetapadeproduccionSet = new HashSet<Piezaxetapadeproduccion>();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach : etapadeproduccion.getPiezaxetapadeproduccionSet()) {
                piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach = em.getReference(piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach.getClass(), piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach.getPiezaxetapadeproduccionPK());
                attachedPiezaxetapadeproduccionSet.add(piezaxetapadeproduccionSetPiezaxetapadeproduccionToAttach);
            }
            etapadeproduccion.setPiezaxetapadeproduccionSet(attachedPiezaxetapadeproduccionSet);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSet = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuestoToAttach : etapadeproduccion.getDetallepiezapresupuestoSet()) {
                detallepiezapresupuestoSetDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSetDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSetDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSet.add(detallepiezapresupuestoSetDetallepiezapresupuestoToAttach);
            }
            etapadeproduccion.setDetallepiezapresupuestoSet(attachedDetallepiezapresupuestoSet);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSet = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientosToAttach : etapadeproduccion.getDetalleplanprocedimientosSet()) {
                detalleplanprocedimientosSetDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSetDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSetDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSet.add(detalleplanprocedimientosSetDetalleplanprocedimientosToAttach);
            }
            etapadeproduccion.setDetalleplanprocedimientosSet(attachedDetalleplanprocedimientosSet);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach : etapadeproduccion.getDetalletrabajotercerizadoSet()) {
                detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet.add(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach);
            }
            etapadeproduccion.setDetalletrabajotercerizadoSet(attachedDetalletrabajotercerizadoSet);
            em.persist(etapadeproduccion);
            if (ejecucionetapaproduccion != null) {
                Etapadeproduccion oldEtapadeproduccionOfEjecucionetapaproduccion = ejecucionetapaproduccion.getEtapadeproduccion();
                if (oldEtapadeproduccionOfEjecucionetapaproduccion != null) {
                    oldEtapadeproduccionOfEjecucionetapaproduccion.setEjecucionetapaproduccion(null);
                    oldEtapadeproduccionOfEjecucionetapaproduccion = em.merge(oldEtapadeproduccionOfEjecucionetapaproduccion);
                }
                ejecucionetapaproduccion.setEtapadeproduccion(etapadeproduccion);
                ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            }
            if (unidaddemedida != null) {
                unidaddemedida.getEtapadeproduccionSet().add(etapadeproduccion);
                unidaddemedida = em.merge(unidaddemedida);
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
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetPiezaxetapadeproduccion : etapadeproduccion.getPiezaxetapadeproduccionSet()) {
                Etapadeproduccion oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion = piezaxetapadeproduccionSetPiezaxetapadeproduccion.getEtapadeproduccion();
                piezaxetapadeproduccionSetPiezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccion);
                piezaxetapadeproduccionSetPiezaxetapadeproduccion = em.merge(piezaxetapadeproduccionSetPiezaxetapadeproduccion);
                if (oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion != null) {
                    oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion.getPiezaxetapadeproduccionSet().remove(piezaxetapadeproduccionSetPiezaxetapadeproduccion);
                    oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion = em.merge(oldEtapadeproduccionOfPiezaxetapadeproduccionSetPiezaxetapadeproduccion);
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
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientos : etapadeproduccion.getDetalleplanprocedimientosSet()) {
                Etapadeproduccion oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos = detalleplanprocedimientosSetDetalleplanprocedimientos.getIdetapaproduccion();
                detalleplanprocedimientosSetDetalleplanprocedimientos.setIdetapaproduccion(etapadeproduccion);
                detalleplanprocedimientosSetDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSetDetalleplanprocedimientos);
                if (oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos != null) {
                    oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos.getDetalleplanprocedimientosSet().remove(detalleplanprocedimientosSetDetalleplanprocedimientos);
                    oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos = em.merge(oldIdetapaproduccionOfDetalleplanprocedimientosSetDetalleplanprocedimientos);
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
            Ejecucionetapaproduccion ejecucionetapaproduccionOld = persistentEtapadeproduccion.getEjecucionetapaproduccion();
            Ejecucionetapaproduccion ejecucionetapaproduccionNew = etapadeproduccion.getEjecucionetapaproduccion();
            Unidadmedida unidaddemedidaOld = persistentEtapadeproduccion.getUnidaddemedida();
            Unidadmedida unidaddemedidaNew = etapadeproduccion.getUnidaddemedida();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentEtapadeproduccion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = etapadeproduccion.getDetalleplanificacionproduccionSet();
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSetOld = persistentEtapadeproduccion.getPiezaxetapadeproduccionSet();
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSetNew = etapadeproduccion.getPiezaxetapadeproduccionSet();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSetOld = persistentEtapadeproduccion.getDetallepiezapresupuestoSet();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSetNew = etapadeproduccion.getDetallepiezapresupuestoSet();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetOld = persistentEtapadeproduccion.getDetalleplanprocedimientosSet();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetNew = etapadeproduccion.getDetalleplanprocedimientosSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetOld = persistentEtapadeproduccion.getDetalletrabajotercerizadoSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetNew = etapadeproduccion.getDetalletrabajotercerizadoSet();
            List<String> illegalOrphanMessages = null;
            if (ejecucionetapaproduccionOld != null && !ejecucionetapaproduccionOld.equals(ejecucionetapaproduccionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ejecucionetapaproduccion " + ejecucionetapaproduccionOld + " since its etapadeproduccion field is not nullable.");
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetOldPiezaxetapadeproduccion : piezaxetapadeproduccionSetOld) {
                if (!piezaxetapadeproduccionSetNew.contains(piezaxetapadeproduccionSetOldPiezaxetapadeproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Piezaxetapadeproduccion " + piezaxetapadeproduccionSetOldPiezaxetapadeproduccion + " since its etapadeproduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ejecucionetapaproduccionNew != null) {
                ejecucionetapaproduccionNew = em.getReference(ejecucionetapaproduccionNew.getClass(), ejecucionetapaproduccionNew.getEjecucionetapaproduccionPK());
                etapadeproduccion.setEjecucionetapaproduccion(ejecucionetapaproduccionNew);
            }
            if (unidaddemedidaNew != null) {
                unidaddemedidaNew = em.getReference(unidaddemedidaNew.getClass(), unidaddemedidaNew.getIdunidadmedida());
                etapadeproduccion.setUnidaddemedida(unidaddemedidaNew);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            etapadeproduccion.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Piezaxetapadeproduccion> attachedPiezaxetapadeproduccionSetNew = new HashSet<Piezaxetapadeproduccion>();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach : piezaxetapadeproduccionSetNew) {
                piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach = em.getReference(piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach.getClass(), piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach.getPiezaxetapadeproduccionPK());
                attachedPiezaxetapadeproduccionSetNew.add(piezaxetapadeproduccionSetNewPiezaxetapadeproduccionToAttach);
            }
            piezaxetapadeproduccionSetNew = attachedPiezaxetapadeproduccionSetNew;
            etapadeproduccion.setPiezaxetapadeproduccionSet(piezaxetapadeproduccionSetNew);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSetNew = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach : detallepiezapresupuestoSetNew) {
                detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSetNew.add(detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach);
            }
            detallepiezapresupuestoSetNew = attachedDetallepiezapresupuestoSetNew;
            etapadeproduccion.setDetallepiezapresupuestoSet(detallepiezapresupuestoSetNew);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSetNew = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach : detalleplanprocedimientosSetNew) {
                detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSetNew.add(detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach);
            }
            detalleplanprocedimientosSetNew = attachedDetalleplanprocedimientosSetNew;
            etapadeproduccion.setDetalleplanprocedimientosSet(detalleplanprocedimientosSetNew);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSetNew = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoSetNew) {
                detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSetNew.add(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoSetNew = attachedDetalletrabajotercerizadoSetNew;
            etapadeproduccion.setDetalletrabajotercerizadoSet(detalletrabajotercerizadoSetNew);
            etapadeproduccion = em.merge(etapadeproduccion);
            if (ejecucionetapaproduccionNew != null && !ejecucionetapaproduccionNew.equals(ejecucionetapaproduccionOld)) {
                Etapadeproduccion oldEtapadeproduccionOfEjecucionetapaproduccion = ejecucionetapaproduccionNew.getEtapadeproduccion();
                if (oldEtapadeproduccionOfEjecucionetapaproduccion != null) {
                    oldEtapadeproduccionOfEjecucionetapaproduccion.setEjecucionetapaproduccion(null);
                    oldEtapadeproduccionOfEjecucionetapaproduccion = em.merge(oldEtapadeproduccionOfEjecucionetapaproduccion);
                }
                ejecucionetapaproduccionNew.setEtapadeproduccion(etapadeproduccion);
                ejecucionetapaproduccionNew = em.merge(ejecucionetapaproduccionNew);
            }
            if (unidaddemedidaOld != null && !unidaddemedidaOld.equals(unidaddemedidaNew)) {
                unidaddemedidaOld.getEtapadeproduccionSet().remove(etapadeproduccion);
                unidaddemedidaOld = em.merge(unidaddemedidaOld);
            }
            if (unidaddemedidaNew != null && !unidaddemedidaNew.equals(unidaddemedidaOld)) {
                unidaddemedidaNew.getEtapadeproduccionSet().add(etapadeproduccion);
                unidaddemedidaNew = em.merge(unidaddemedidaNew);
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
            Ejecucionetapaproduccion ejecucionetapaproduccionOrphanCheck = etapadeproduccion.getEjecucionetapaproduccion();
            if (ejecucionetapaproduccionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Etapadeproduccion (" + etapadeproduccion + ") cannot be destroyed since the Ejecucionetapaproduccion " + ejecucionetapaproduccionOrphanCheck + " in its ejecucionetapaproduccion field has a non-nullable etapadeproduccion field.");
            }
            Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSetOrphanCheck = etapadeproduccion.getPiezaxetapadeproduccionSet();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionSetOrphanCheckPiezaxetapadeproduccion : piezaxetapadeproduccionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Etapadeproduccion (" + etapadeproduccion + ") cannot be destroyed since the Piezaxetapadeproduccion " + piezaxetapadeproduccionSetOrphanCheckPiezaxetapadeproduccion + " in its piezaxetapadeproduccionSet field has a non-nullable etapadeproduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Unidadmedida unidaddemedida = etapadeproduccion.getUnidaddemedida();
            if (unidaddemedida != null) {
                unidaddemedida.getEtapadeproduccionSet().remove(etapadeproduccion);
                unidaddemedida = em.merge(unidaddemedida);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = etapadeproduccion.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdetapaproduccion(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet = etapadeproduccion.getDetallepiezapresupuestoSet();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuesto : detallepiezapresupuestoSet) {
                detallepiezapresupuestoSetDetallepiezapresupuesto.setIdetapa(null);
                detallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetDetallepiezapresupuesto);
            }
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSet = etapadeproduccion.getDetalleplanprocedimientosSet();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientos : detalleplanprocedimientosSet) {
                detalleplanprocedimientosSetDetalleplanprocedimientos.setIdetapaproduccion(null);
                detalleplanprocedimientosSetDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSetDetalleplanprocedimientos);
            }
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet = etapadeproduccion.getDetalletrabajotercerizadoSet();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizado : detalletrabajotercerizadoSet) {
                detalletrabajotercerizadoSetDetalletrabajotercerizado.setProceso(null);
                detalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetDetalletrabajotercerizado);
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
