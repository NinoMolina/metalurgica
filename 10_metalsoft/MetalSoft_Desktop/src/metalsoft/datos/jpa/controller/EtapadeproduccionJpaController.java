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
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Unidadmedida;
import metalsoft.datos.jpa.entity.Tipomaquina;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Piezaxetapadeproduccion;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detallepiezapresupuesto;
import metalsoft.datos.jpa.entity.Detalleplanprocedimientos;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;

/**
 *
 * @author Nino
 */
public class EtapadeproduccionJpaController implements Serializable {

    public EtapadeproduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Etapadeproduccion etapadeproduccion) {
        if (etapadeproduccion.getDetalleplanificacionproduccionList() == null) {
            etapadeproduccion.setDetalleplanificacionproduccionList(new ArrayList<Detalleplanificacionproduccion>());
        }
        if (etapadeproduccion.getEjecucionetapaproduccionList() == null) {
            etapadeproduccion.setEjecucionetapaproduccionList(new ArrayList<Ejecucionetapaproduccion>());
        }
        if (etapadeproduccion.getPiezaxetapadeproduccionList() == null) {
            etapadeproduccion.setPiezaxetapadeproduccionList(new ArrayList<Piezaxetapadeproduccion>());
        }
        if (etapadeproduccion.getDetalleejecucionplanificacionList() == null) {
            etapadeproduccion.setDetalleejecucionplanificacionList(new ArrayList<Detalleejecucionplanificacion>());
        }
        if (etapadeproduccion.getDetallepiezapresupuestoList() == null) {
            etapadeproduccion.setDetallepiezapresupuestoList(new ArrayList<Detallepiezapresupuesto>());
        }
        if (etapadeproduccion.getDetalleplanprocedimientosList() == null) {
            etapadeproduccion.setDetalleplanprocedimientosList(new ArrayList<Detalleplanprocedimientos>());
        }
        if (etapadeproduccion.getDetalletrabajotercerizadoList() == null) {
            etapadeproduccion.setDetalletrabajotercerizadoList(new ArrayList<Detalletrabajotercerizado>());
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
            Tipomaquina tipomaquina = etapadeproduccion.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina = em.getReference(tipomaquina.getClass(), tipomaquina.getIdtipomaquina());
                etapadeproduccion.setTipomaquina(tipomaquina);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionList = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach : etapadeproduccion.getDetalleplanificacionproduccionList()) {
                detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionList.add(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach);
            }
            etapadeproduccion.setDetalleplanificacionproduccionList(attachedDetalleplanificacionproduccionList);
            List<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionList = new ArrayList<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListEjecucionetapaproduccionToAttach : etapadeproduccion.getEjecucionetapaproduccionList()) {
                ejecucionetapaproduccionListEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionListEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionListEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionList.add(ejecucionetapaproduccionListEjecucionetapaproduccionToAttach);
            }
            etapadeproduccion.setEjecucionetapaproduccionList(attachedEjecucionetapaproduccionList);
            List<Piezaxetapadeproduccion> attachedPiezaxetapadeproduccionList = new ArrayList<Piezaxetapadeproduccion>();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionListPiezaxetapadeproduccionToAttach : etapadeproduccion.getPiezaxetapadeproduccionList()) {
                piezaxetapadeproduccionListPiezaxetapadeproduccionToAttach = em.getReference(piezaxetapadeproduccionListPiezaxetapadeproduccionToAttach.getClass(), piezaxetapadeproduccionListPiezaxetapadeproduccionToAttach.getPiezaxetapadeproduccionPK());
                attachedPiezaxetapadeproduccionList.add(piezaxetapadeproduccionListPiezaxetapadeproduccionToAttach);
            }
            etapadeproduccion.setPiezaxetapadeproduccionList(attachedPiezaxetapadeproduccionList);
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionList = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach : etapadeproduccion.getDetalleejecucionplanificacionList()) {
                detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionList.add(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach);
            }
            etapadeproduccion.setDetalleejecucionplanificacionList(attachedDetalleejecucionplanificacionList);
            List<Detallepiezapresupuesto> attachedDetallepiezapresupuestoList = new ArrayList<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoListDetallepiezapresupuestoToAttach : etapadeproduccion.getDetallepiezapresupuestoList()) {
                detallepiezapresupuestoListDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoListDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoListDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoList.add(detallepiezapresupuestoListDetallepiezapresupuestoToAttach);
            }
            etapadeproduccion.setDetallepiezapresupuestoList(attachedDetallepiezapresupuestoList);
            List<Detalleplanprocedimientos> attachedDetalleplanprocedimientosList = new ArrayList<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosListDetalleplanprocedimientosToAttach : etapadeproduccion.getDetalleplanprocedimientosList()) {
                detalleplanprocedimientosListDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosListDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosListDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosList.add(detalleplanprocedimientosListDetalleplanprocedimientosToAttach);
            }
            etapadeproduccion.setDetalleplanprocedimientosList(attachedDetalleplanprocedimientosList);
            List<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoList = new ArrayList<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach : etapadeproduccion.getDetalletrabajotercerizadoList()) {
                detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach.getIddetalle());
                attachedDetalletrabajotercerizadoList.add(detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach);
            }
            etapadeproduccion.setDetalletrabajotercerizadoList(attachedDetalletrabajotercerizadoList);
            em.persist(etapadeproduccion);
            if (unidaddemedida != null) {
                unidaddemedida.getEtapadeproduccionList().add(etapadeproduccion);
                unidaddemedida = em.merge(unidaddemedida);
            }
            if (tipomaquina != null) {
                tipomaquina.getEtapadeproduccionList().add(etapadeproduccion);
                tipomaquina = em.merge(tipomaquina);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : etapadeproduccion.getDetalleplanificacionproduccionList()) {
                Etapadeproduccion oldIdetapaproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = detalleplanificacionproduccionListDetalleplanificacionproduccion.getIdetapaproduccion();
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdetapaproduccion(etapadeproduccion);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                if (oldIdetapaproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion != null) {
                    oldIdetapaproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                    oldIdetapaproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(oldIdetapaproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListEjecucionetapaproduccion : etapadeproduccion.getEjecucionetapaproduccionList()) {
                Etapadeproduccion oldIdetapaproduccionOfEjecucionetapaproduccionListEjecucionetapaproduccion = ejecucionetapaproduccionListEjecucionetapaproduccion.getIdetapaproduccion();
                ejecucionetapaproduccionListEjecucionetapaproduccion.setIdetapaproduccion(etapadeproduccion);
                ejecucionetapaproduccionListEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListEjecucionetapaproduccion);
                if (oldIdetapaproduccionOfEjecucionetapaproduccionListEjecucionetapaproduccion != null) {
                    oldIdetapaproduccionOfEjecucionetapaproduccionListEjecucionetapaproduccion.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccionListEjecucionetapaproduccion);
                    oldIdetapaproduccionOfEjecucionetapaproduccionListEjecucionetapaproduccion = em.merge(oldIdetapaproduccionOfEjecucionetapaproduccionListEjecucionetapaproduccion);
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionListPiezaxetapadeproduccion : etapadeproduccion.getPiezaxetapadeproduccionList()) {
                Etapadeproduccion oldEtapadeproduccionOfPiezaxetapadeproduccionListPiezaxetapadeproduccion = piezaxetapadeproduccionListPiezaxetapadeproduccion.getEtapadeproduccion();
                piezaxetapadeproduccionListPiezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccion);
                piezaxetapadeproduccionListPiezaxetapadeproduccion = em.merge(piezaxetapadeproduccionListPiezaxetapadeproduccion);
                if (oldEtapadeproduccionOfPiezaxetapadeproduccionListPiezaxetapadeproduccion != null) {
                    oldEtapadeproduccionOfPiezaxetapadeproduccionListPiezaxetapadeproduccion.getPiezaxetapadeproduccionList().remove(piezaxetapadeproduccionListPiezaxetapadeproduccion);
                    oldEtapadeproduccionOfPiezaxetapadeproduccionListPiezaxetapadeproduccion = em.merge(oldEtapadeproduccionOfPiezaxetapadeproduccionListPiezaxetapadeproduccion);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : etapadeproduccion.getDetalleejecucionplanificacionList()) {
                Etapadeproduccion oldIdetapaproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = detalleejecucionplanificacionListDetalleejecucionplanificacion.getIdetapaproduccion();
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setIdetapaproduccion(etapadeproduccion);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                if (oldIdetapaproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion != null) {
                    oldIdetapaproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                    oldIdetapaproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(oldIdetapaproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoListDetallepiezapresupuesto : etapadeproduccion.getDetallepiezapresupuestoList()) {
                Etapadeproduccion oldIdetapaOfDetallepiezapresupuestoListDetallepiezapresupuesto = detallepiezapresupuestoListDetallepiezapresupuesto.getIdetapa();
                detallepiezapresupuestoListDetallepiezapresupuesto.setIdetapa(etapadeproduccion);
                detallepiezapresupuestoListDetallepiezapresupuesto = em.merge(detallepiezapresupuestoListDetallepiezapresupuesto);
                if (oldIdetapaOfDetallepiezapresupuestoListDetallepiezapresupuesto != null) {
                    oldIdetapaOfDetallepiezapresupuestoListDetallepiezapresupuesto.getDetallepiezapresupuestoList().remove(detallepiezapresupuestoListDetallepiezapresupuesto);
                    oldIdetapaOfDetallepiezapresupuestoListDetallepiezapresupuesto = em.merge(oldIdetapaOfDetallepiezapresupuestoListDetallepiezapresupuesto);
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosListDetalleplanprocedimientos : etapadeproduccion.getDetalleplanprocedimientosList()) {
                Etapadeproduccion oldIdetapaproduccionOfDetalleplanprocedimientosListDetalleplanprocedimientos = detalleplanprocedimientosListDetalleplanprocedimientos.getIdetapaproduccion();
                detalleplanprocedimientosListDetalleplanprocedimientos.setIdetapaproduccion(etapadeproduccion);
                detalleplanprocedimientosListDetalleplanprocedimientos = em.merge(detalleplanprocedimientosListDetalleplanprocedimientos);
                if (oldIdetapaproduccionOfDetalleplanprocedimientosListDetalleplanprocedimientos != null) {
                    oldIdetapaproduccionOfDetalleplanprocedimientosListDetalleplanprocedimientos.getDetalleplanprocedimientosList().remove(detalleplanprocedimientosListDetalleplanprocedimientos);
                    oldIdetapaproduccionOfDetalleplanprocedimientosListDetalleplanprocedimientos = em.merge(oldIdetapaproduccionOfDetalleplanprocedimientosListDetalleplanprocedimientos);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizado : etapadeproduccion.getDetalletrabajotercerizadoList()) {
                Etapadeproduccion oldProcesoOfDetalletrabajotercerizadoListDetalletrabajotercerizado = detalletrabajotercerizadoListDetalletrabajotercerizado.getProceso();
                detalletrabajotercerizadoListDetalletrabajotercerizado.setProceso(etapadeproduccion);
                detalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListDetalletrabajotercerizado);
                if (oldProcesoOfDetalletrabajotercerizadoListDetalletrabajotercerizado != null) {
                    oldProcesoOfDetalletrabajotercerizadoListDetalletrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizadoListDetalletrabajotercerizado);
                    oldProcesoOfDetalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(oldProcesoOfDetalletrabajotercerizadoListDetalletrabajotercerizado);
                }
            }
            em.getTransaction().commit();
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
            Tipomaquina tipomaquinaOld = persistentEtapadeproduccion.getTipomaquina();
            Tipomaquina tipomaquinaNew = etapadeproduccion.getTipomaquina();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOld = persistentEtapadeproduccion.getDetalleplanificacionproduccionList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListNew = etapadeproduccion.getDetalleplanificacionproduccionList();
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionListOld = persistentEtapadeproduccion.getEjecucionetapaproduccionList();
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionListNew = etapadeproduccion.getEjecucionetapaproduccionList();
            List<Piezaxetapadeproduccion> piezaxetapadeproduccionListOld = persistentEtapadeproduccion.getPiezaxetapadeproduccionList();
            List<Piezaxetapadeproduccion> piezaxetapadeproduccionListNew = etapadeproduccion.getPiezaxetapadeproduccionList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListOld = persistentEtapadeproduccion.getDetalleejecucionplanificacionList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListNew = etapadeproduccion.getDetalleejecucionplanificacionList();
            List<Detallepiezapresupuesto> detallepiezapresupuestoListOld = persistentEtapadeproduccion.getDetallepiezapresupuestoList();
            List<Detallepiezapresupuesto> detallepiezapresupuestoListNew = etapadeproduccion.getDetallepiezapresupuestoList();
            List<Detalleplanprocedimientos> detalleplanprocedimientosListOld = persistentEtapadeproduccion.getDetalleplanprocedimientosList();
            List<Detalleplanprocedimientos> detalleplanprocedimientosListNew = etapadeproduccion.getDetalleplanprocedimientosList();
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListOld = persistentEtapadeproduccion.getDetalletrabajotercerizadoList();
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListNew = etapadeproduccion.getDetalletrabajotercerizadoList();
            List<String> illegalOrphanMessages = null;
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListOldEjecucionetapaproduccion : ejecucionetapaproduccionListOld) {
                if (!ejecucionetapaproduccionListNew.contains(ejecucionetapaproduccionListOldEjecucionetapaproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ejecucionetapaproduccion " + ejecucionetapaproduccionListOldEjecucionetapaproduccion + " since its idetapaproduccion field is not nullable.");
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionListOldPiezaxetapadeproduccion : piezaxetapadeproduccionListOld) {
                if (!piezaxetapadeproduccionListNew.contains(piezaxetapadeproduccionListOldPiezaxetapadeproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Piezaxetapadeproduccion " + piezaxetapadeproduccionListOldPiezaxetapadeproduccion + " since its etapadeproduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unidaddemedidaNew != null) {
                unidaddemedidaNew = em.getReference(unidaddemedidaNew.getClass(), unidaddemedidaNew.getIdunidadmedida());
                etapadeproduccion.setUnidaddemedida(unidaddemedidaNew);
            }
            if (tipomaquinaNew != null) {
                tipomaquinaNew = em.getReference(tipomaquinaNew.getClass(), tipomaquinaNew.getIdtipomaquina());
                etapadeproduccion.setTipomaquina(tipomaquinaNew);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionListNew = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionListNew) {
                detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionListNew.add(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionListNew = attachedDetalleplanificacionproduccionListNew;
            etapadeproduccion.setDetalleplanificacionproduccionList(detalleplanificacionproduccionListNew);
            List<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionListNew = new ArrayList<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionListNew) {
                ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionListNew.add(ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionListNew = attachedEjecucionetapaproduccionListNew;
            etapadeproduccion.setEjecucionetapaproduccionList(ejecucionetapaproduccionListNew);
            List<Piezaxetapadeproduccion> attachedPiezaxetapadeproduccionListNew = new ArrayList<Piezaxetapadeproduccion>();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionListNewPiezaxetapadeproduccionToAttach : piezaxetapadeproduccionListNew) {
                piezaxetapadeproduccionListNewPiezaxetapadeproduccionToAttach = em.getReference(piezaxetapadeproduccionListNewPiezaxetapadeproduccionToAttach.getClass(), piezaxetapadeproduccionListNewPiezaxetapadeproduccionToAttach.getPiezaxetapadeproduccionPK());
                attachedPiezaxetapadeproduccionListNew.add(piezaxetapadeproduccionListNewPiezaxetapadeproduccionToAttach);
            }
            piezaxetapadeproduccionListNew = attachedPiezaxetapadeproduccionListNew;
            etapadeproduccion.setPiezaxetapadeproduccionList(piezaxetapadeproduccionListNew);
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionListNew = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionListNew) {
                detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionListNew.add(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionListNew = attachedDetalleejecucionplanificacionListNew;
            etapadeproduccion.setDetalleejecucionplanificacionList(detalleejecucionplanificacionListNew);
            List<Detallepiezapresupuesto> attachedDetallepiezapresupuestoListNew = new ArrayList<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach : detallepiezapresupuestoListNew) {
                detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoListNew.add(detallepiezapresupuestoListNewDetallepiezapresupuestoToAttach);
            }
            detallepiezapresupuestoListNew = attachedDetallepiezapresupuestoListNew;
            etapadeproduccion.setDetallepiezapresupuestoList(detallepiezapresupuestoListNew);
            List<Detalleplanprocedimientos> attachedDetalleplanprocedimientosListNew = new ArrayList<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach : detalleplanprocedimientosListNew) {
                detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosListNew.add(detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach);
            }
            detalleplanprocedimientosListNew = attachedDetalleplanprocedimientosListNew;
            etapadeproduccion.setDetalleplanprocedimientosList(detalleplanprocedimientosListNew);
            List<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoListNew = new ArrayList<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoListNew) {
                detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach.getIddetalle());
                attachedDetalletrabajotercerizadoListNew.add(detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoListNew = attachedDetalletrabajotercerizadoListNew;
            etapadeproduccion.setDetalletrabajotercerizadoList(detalletrabajotercerizadoListNew);
            etapadeproduccion = em.merge(etapadeproduccion);
            if (unidaddemedidaOld != null && !unidaddemedidaOld.equals(unidaddemedidaNew)) {
                unidaddemedidaOld.getEtapadeproduccionList().remove(etapadeproduccion);
                unidaddemedidaOld = em.merge(unidaddemedidaOld);
            }
            if (unidaddemedidaNew != null && !unidaddemedidaNew.equals(unidaddemedidaOld)) {
                unidaddemedidaNew.getEtapadeproduccionList().add(etapadeproduccion);
                unidaddemedidaNew = em.merge(unidaddemedidaNew);
            }
            if (tipomaquinaOld != null && !tipomaquinaOld.equals(tipomaquinaNew)) {
                tipomaquinaOld.getEtapadeproduccionList().remove(etapadeproduccion);
                tipomaquinaOld = em.merge(tipomaquinaOld);
            }
            if (tipomaquinaNew != null && !tipomaquinaNew.equals(tipomaquinaOld)) {
                tipomaquinaNew.getEtapadeproduccionList().add(etapadeproduccion);
                tipomaquinaNew = em.merge(tipomaquinaNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListOldDetalleplanificacionproduccion : detalleplanificacionproduccionListOld) {
                if (!detalleplanificacionproduccionListNew.contains(detalleplanificacionproduccionListOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion.setIdetapaproduccion(null);
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccion : detalleplanificacionproduccionListNew) {
                if (!detalleplanificacionproduccionListOld.contains(detalleplanificacionproduccionListNewDetalleplanificacionproduccion)) {
                    Etapadeproduccion oldIdetapaproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = detalleplanificacionproduccionListNewDetalleplanificacionproduccion.getIdetapaproduccion();
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion.setIdetapaproduccion(etapadeproduccion);
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    if (oldIdetapaproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion != null && !oldIdetapaproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.equals(etapadeproduccion)) {
                        oldIdetapaproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                        oldIdetapaproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(oldIdetapaproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListNewEjecucionetapaproduccion : ejecucionetapaproduccionListNew) {
                if (!ejecucionetapaproduccionListOld.contains(ejecucionetapaproduccionListNewEjecucionetapaproduccion)) {
                    Etapadeproduccion oldIdetapaproduccionOfEjecucionetapaproduccionListNewEjecucionetapaproduccion = ejecucionetapaproduccionListNewEjecucionetapaproduccion.getIdetapaproduccion();
                    ejecucionetapaproduccionListNewEjecucionetapaproduccion.setIdetapaproduccion(etapadeproduccion);
                    ejecucionetapaproduccionListNewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListNewEjecucionetapaproduccion);
                    if (oldIdetapaproduccionOfEjecucionetapaproduccionListNewEjecucionetapaproduccion != null && !oldIdetapaproduccionOfEjecucionetapaproduccionListNewEjecucionetapaproduccion.equals(etapadeproduccion)) {
                        oldIdetapaproduccionOfEjecucionetapaproduccionListNewEjecucionetapaproduccion.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccionListNewEjecucionetapaproduccion);
                        oldIdetapaproduccionOfEjecucionetapaproduccionListNewEjecucionetapaproduccion = em.merge(oldIdetapaproduccionOfEjecucionetapaproduccionListNewEjecucionetapaproduccion);
                    }
                }
            }
            for (Piezaxetapadeproduccion piezaxetapadeproduccionListNewPiezaxetapadeproduccion : piezaxetapadeproduccionListNew) {
                if (!piezaxetapadeproduccionListOld.contains(piezaxetapadeproduccionListNewPiezaxetapadeproduccion)) {
                    Etapadeproduccion oldEtapadeproduccionOfPiezaxetapadeproduccionListNewPiezaxetapadeproduccion = piezaxetapadeproduccionListNewPiezaxetapadeproduccion.getEtapadeproduccion();
                    piezaxetapadeproduccionListNewPiezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccion);
                    piezaxetapadeproduccionListNewPiezaxetapadeproduccion = em.merge(piezaxetapadeproduccionListNewPiezaxetapadeproduccion);
                    if (oldEtapadeproduccionOfPiezaxetapadeproduccionListNewPiezaxetapadeproduccion != null && !oldEtapadeproduccionOfPiezaxetapadeproduccionListNewPiezaxetapadeproduccion.equals(etapadeproduccion)) {
                        oldEtapadeproduccionOfPiezaxetapadeproduccionListNewPiezaxetapadeproduccion.getPiezaxetapadeproduccionList().remove(piezaxetapadeproduccionListNewPiezaxetapadeproduccion);
                        oldEtapadeproduccionOfPiezaxetapadeproduccionListNewPiezaxetapadeproduccion = em.merge(oldEtapadeproduccionOfPiezaxetapadeproduccionListNewPiezaxetapadeproduccion);
                    }
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListOldDetalleejecucionplanificacion : detalleejecucionplanificacionListOld) {
                if (!detalleejecucionplanificacionListNew.contains(detalleejecucionplanificacionListOldDetalleejecucionplanificacion)) {
                    detalleejecucionplanificacionListOldDetalleejecucionplanificacion.setIdetapaproduccion(null);
                    detalleejecucionplanificacionListOldDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListOldDetalleejecucionplanificacion);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacion : detalleejecucionplanificacionListNew) {
                if (!detalleejecucionplanificacionListOld.contains(detalleejecucionplanificacionListNewDetalleejecucionplanificacion)) {
                    Etapadeproduccion oldIdetapaproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = detalleejecucionplanificacionListNewDetalleejecucionplanificacion.getIdetapaproduccion();
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion.setIdetapaproduccion(etapadeproduccion);
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    if (oldIdetapaproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion != null && !oldIdetapaproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.equals(etapadeproduccion)) {
                        oldIdetapaproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                        oldIdetapaproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(oldIdetapaproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    }
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoListOldDetallepiezapresupuesto : detallepiezapresupuestoListOld) {
                if (!detallepiezapresupuestoListNew.contains(detallepiezapresupuestoListOldDetallepiezapresupuesto)) {
                    detallepiezapresupuestoListOldDetallepiezapresupuesto.setIdetapa(null);
                    detallepiezapresupuestoListOldDetallepiezapresupuesto = em.merge(detallepiezapresupuestoListOldDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoListNewDetallepiezapresupuesto : detallepiezapresupuestoListNew) {
                if (!detallepiezapresupuestoListOld.contains(detallepiezapresupuestoListNewDetallepiezapresupuesto)) {
                    Etapadeproduccion oldIdetapaOfDetallepiezapresupuestoListNewDetallepiezapresupuesto = detallepiezapresupuestoListNewDetallepiezapresupuesto.getIdetapa();
                    detallepiezapresupuestoListNewDetallepiezapresupuesto.setIdetapa(etapadeproduccion);
                    detallepiezapresupuestoListNewDetallepiezapresupuesto = em.merge(detallepiezapresupuestoListNewDetallepiezapresupuesto);
                    if (oldIdetapaOfDetallepiezapresupuestoListNewDetallepiezapresupuesto != null && !oldIdetapaOfDetallepiezapresupuestoListNewDetallepiezapresupuesto.equals(etapadeproduccion)) {
                        oldIdetapaOfDetallepiezapresupuestoListNewDetallepiezapresupuesto.getDetallepiezapresupuestoList().remove(detallepiezapresupuestoListNewDetallepiezapresupuesto);
                        oldIdetapaOfDetallepiezapresupuestoListNewDetallepiezapresupuesto = em.merge(oldIdetapaOfDetallepiezapresupuestoListNewDetallepiezapresupuesto);
                    }
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosListOldDetalleplanprocedimientos : detalleplanprocedimientosListOld) {
                if (!detalleplanprocedimientosListNew.contains(detalleplanprocedimientosListOldDetalleplanprocedimientos)) {
                    detalleplanprocedimientosListOldDetalleplanprocedimientos.setIdetapaproduccion(null);
                    detalleplanprocedimientosListOldDetalleplanprocedimientos = em.merge(detalleplanprocedimientosListOldDetalleplanprocedimientos);
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosListNewDetalleplanprocedimientos : detalleplanprocedimientosListNew) {
                if (!detalleplanprocedimientosListOld.contains(detalleplanprocedimientosListNewDetalleplanprocedimientos)) {
                    Etapadeproduccion oldIdetapaproduccionOfDetalleplanprocedimientosListNewDetalleplanprocedimientos = detalleplanprocedimientosListNewDetalleplanprocedimientos.getIdetapaproduccion();
                    detalleplanprocedimientosListNewDetalleplanprocedimientos.setIdetapaproduccion(etapadeproduccion);
                    detalleplanprocedimientosListNewDetalleplanprocedimientos = em.merge(detalleplanprocedimientosListNewDetalleplanprocedimientos);
                    if (oldIdetapaproduccionOfDetalleplanprocedimientosListNewDetalleplanprocedimientos != null && !oldIdetapaproduccionOfDetalleplanprocedimientosListNewDetalleplanprocedimientos.equals(etapadeproduccion)) {
                        oldIdetapaproduccionOfDetalleplanprocedimientosListNewDetalleplanprocedimientos.getDetalleplanprocedimientosList().remove(detalleplanprocedimientosListNewDetalleplanprocedimientos);
                        oldIdetapaproduccionOfDetalleplanprocedimientosListNewDetalleplanprocedimientos = em.merge(oldIdetapaproduccionOfDetalleplanprocedimientosListNewDetalleplanprocedimientos);
                    }
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoListOldDetalletrabajotercerizado : detalletrabajotercerizadoListOld) {
                if (!detalletrabajotercerizadoListNew.contains(detalletrabajotercerizadoListOldDetalletrabajotercerizado)) {
                    detalletrabajotercerizadoListOldDetalletrabajotercerizado.setProceso(null);
                    detalletrabajotercerizadoListOldDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListOldDetalletrabajotercerizado);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoListNewDetalletrabajotercerizado : detalletrabajotercerizadoListNew) {
                if (!detalletrabajotercerizadoListOld.contains(detalletrabajotercerizadoListNewDetalletrabajotercerizado)) {
                    Etapadeproduccion oldProcesoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado = detalletrabajotercerizadoListNewDetalletrabajotercerizado.getProceso();
                    detalletrabajotercerizadoListNewDetalletrabajotercerizado.setProceso(etapadeproduccion);
                    detalletrabajotercerizadoListNewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListNewDetalletrabajotercerizado);
                    if (oldProcesoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado != null && !oldProcesoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado.equals(etapadeproduccion)) {
                        oldProcesoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizadoListNewDetalletrabajotercerizado);
                        oldProcesoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado = em.merge(oldProcesoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado);
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
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionListOrphanCheck = etapadeproduccion.getEjecucionetapaproduccionList();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListOrphanCheckEjecucionetapaproduccion : ejecucionetapaproduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Etapadeproduccion (" + etapadeproduccion + ") cannot be destroyed since the Ejecucionetapaproduccion " + ejecucionetapaproduccionListOrphanCheckEjecucionetapaproduccion + " in its ejecucionetapaproduccionList field has a non-nullable idetapaproduccion field.");
            }
            List<Piezaxetapadeproduccion> piezaxetapadeproduccionListOrphanCheck = etapadeproduccion.getPiezaxetapadeproduccionList();
            for (Piezaxetapadeproduccion piezaxetapadeproduccionListOrphanCheckPiezaxetapadeproduccion : piezaxetapadeproduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Etapadeproduccion (" + etapadeproduccion + ") cannot be destroyed since the Piezaxetapadeproduccion " + piezaxetapadeproduccionListOrphanCheckPiezaxetapadeproduccion + " in its piezaxetapadeproduccionList field has a non-nullable etapadeproduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Unidadmedida unidaddemedida = etapadeproduccion.getUnidaddemedida();
            if (unidaddemedida != null) {
                unidaddemedida.getEtapadeproduccionList().remove(etapadeproduccion);
                unidaddemedida = em.merge(unidaddemedida);
            }
            Tipomaquina tipomaquina = etapadeproduccion.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina.getEtapadeproduccionList().remove(etapadeproduccion);
                tipomaquina = em.merge(tipomaquina);
            }
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionList = etapadeproduccion.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : detalleplanificacionproduccionList) {
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdetapaproduccion(null);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
            }
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionList = etapadeproduccion.getDetalleejecucionplanificacionList();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : detalleejecucionplanificacionList) {
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setIdetapaproduccion(null);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
            }
            List<Detallepiezapresupuesto> detallepiezapresupuestoList = etapadeproduccion.getDetallepiezapresupuestoList();
            for (Detallepiezapresupuesto detallepiezapresupuestoListDetallepiezapresupuesto : detallepiezapresupuestoList) {
                detallepiezapresupuestoListDetallepiezapresupuesto.setIdetapa(null);
                detallepiezapresupuestoListDetallepiezapresupuesto = em.merge(detallepiezapresupuestoListDetallepiezapresupuesto);
            }
            List<Detalleplanprocedimientos> detalleplanprocedimientosList = etapadeproduccion.getDetalleplanprocedimientosList();
            for (Detalleplanprocedimientos detalleplanprocedimientosListDetalleplanprocedimientos : detalleplanprocedimientosList) {
                detalleplanprocedimientosListDetalleplanprocedimientos.setIdetapaproduccion(null);
                detalleplanprocedimientosListDetalleplanprocedimientos = em.merge(detalleplanprocedimientosListDetalleplanprocedimientos);
            }
            List<Detalletrabajotercerizado> detalletrabajotercerizadoList = etapadeproduccion.getDetalletrabajotercerizadoList();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizado : detalletrabajotercerizadoList) {
                detalletrabajotercerizadoListDetalletrabajotercerizado.setProceso(null);
                detalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListDetalletrabajotercerizado);
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
