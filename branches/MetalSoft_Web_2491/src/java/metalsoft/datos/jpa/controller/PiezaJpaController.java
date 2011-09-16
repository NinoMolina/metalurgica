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
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Unidadmedida;
import metalsoft.datos.jpa.entity.Matriz;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Piezareal;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;

/**
 *
 * @author Nino
 */
public class PiezaJpaController implements Serializable {

    public PiezaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pieza pieza) throws PreexistingEntityException, Exception {
        if (pieza.getDetalleplanificacionproduccionList() == null) {
            pieza.setDetalleplanificacionproduccionList(new ArrayList<Detalleplanificacionproduccion>());
        }
        if (pieza.getPiezarealList() == null) {
            pieza.setPiezarealList(new ArrayList<Piezareal>());
        }
        if (pieza.getDetalleejecucionplanificacionList() == null) {
            pieza.setDetalleejecucionplanificacionList(new ArrayList<Detalleejecucionplanificacion>());
        }
        if (pieza.getDetalleplanificacioncalidadList() == null) {
            pieza.setDetalleplanificacioncalidadList(new ArrayList<Detalleplanificacioncalidad>());
        }
        if (pieza.getDetalleejecucionplanificacioncalidadList() == null) {
            pieza.setDetalleejecucionplanificacioncalidadList(new ArrayList<Detalleejecucionplanificacioncalidad>());
        }
        if (pieza.getDetalleproductopresupuestoList() == null) {
            pieza.setDetalleproductopresupuestoList(new ArrayList<Detalleproductopresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidadmedida unidadmedida = pieza.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida = em.getReference(unidadmedida.getClass(), unidadmedida.getIdunidadmedida());
                pieza.setUnidadmedida(unidadmedida);
            }
            Matriz matriz = pieza.getMatriz();
            if (matriz != null) {
                matriz = em.getReference(matriz.getClass(), matriz.getIdmatriz());
                pieza.setMatriz(matriz);
            }
            Materiaprima materiaprima = pieza.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                pieza.setMateriaprima(materiaprima);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionList = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach : pieza.getDetalleplanificacionproduccionList()) {
                detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionList.add(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach);
            }
            pieza.setDetalleplanificacionproduccionList(attachedDetalleplanificacionproduccionList);
            List<Piezareal> attachedPiezarealList = new ArrayList<Piezareal>();
            for (Piezareal piezarealListPiezarealToAttach : pieza.getPiezarealList()) {
                piezarealListPiezarealToAttach = em.getReference(piezarealListPiezarealToAttach.getClass(), piezarealListPiezarealToAttach.getIdpiezareal());
                attachedPiezarealList.add(piezarealListPiezarealToAttach);
            }
            pieza.setPiezarealList(attachedPiezarealList);
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionList = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach : pieza.getDetalleejecucionplanificacionList()) {
                detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionList.add(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach);
            }
            pieza.setDetalleejecucionplanificacionList(attachedDetalleejecucionplanificacionList);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadList = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach : pieza.getDetalleplanificacioncalidadList()) {
                detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadList.add(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach);
            }
            pieza.setDetalleplanificacioncalidadList(attachedDetalleplanificacioncalidadList);
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadList = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach : pieza.getDetalleejecucionplanificacioncalidadList()) {
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleejecucionplanificacioncalidadList.add(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach);
            }
            pieza.setDetalleejecucionplanificacioncalidadList(attachedDetalleejecucionplanificacioncalidadList);
            List<Detalleproductopresupuesto> attachedDetalleproductopresupuestoList = new ArrayList<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuestoToAttach : pieza.getDetalleproductopresupuestoList()) {
                detalleproductopresupuestoListDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoListDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoListDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoList.add(detalleproductopresupuestoListDetalleproductopresupuestoToAttach);
            }
            pieza.setDetalleproductopresupuestoList(attachedDetalleproductopresupuestoList);
            em.persist(pieza);
            if (unidadmedida != null) {
                unidadmedida.getPiezaList().add(pieza);
                unidadmedida = em.merge(unidadmedida);
            }
            if (matriz != null) {
                matriz.getPiezaList().add(pieza);
                matriz = em.merge(matriz);
            }
            if (materiaprima != null) {
                materiaprima.getPiezaList().add(pieza);
                materiaprima = em.merge(materiaprima);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : pieza.getDetalleplanificacionproduccionList()) {
                Pieza oldIdpiezaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = detalleplanificacionproduccionListDetalleplanificacionproduccion.getIdpieza();
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdpieza(pieza);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                if (oldIdpiezaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion != null) {
                    oldIdpiezaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                    oldIdpiezaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(oldIdpiezaOfDetalleplanificacionproduccionListDetalleplanificacionproduccion);
                }
            }
            for (Piezareal piezarealListPiezareal : pieza.getPiezarealList()) {
                Pieza oldIdpiezaOfPiezarealListPiezareal = piezarealListPiezareal.getIdpieza();
                piezarealListPiezareal.setIdpieza(pieza);
                piezarealListPiezareal = em.merge(piezarealListPiezareal);
                if (oldIdpiezaOfPiezarealListPiezareal != null) {
                    oldIdpiezaOfPiezarealListPiezareal.getPiezarealList().remove(piezarealListPiezareal);
                    oldIdpiezaOfPiezarealListPiezareal = em.merge(oldIdpiezaOfPiezarealListPiezareal);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : pieza.getDetalleejecucionplanificacionList()) {
                Pieza oldPiezaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = detalleejecucionplanificacionListDetalleejecucionplanificacion.getPieza();
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setPieza(pieza);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                if (oldPiezaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion != null) {
                    oldPiezaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                    oldPiezaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(oldPiezaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : pieza.getDetalleplanificacioncalidadList()) {
                Pieza oldPiezaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = detalleplanificacioncalidadListDetalleplanificacioncalidad.getPieza();
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setPieza(pieza);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                if (oldPiezaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad != null) {
                    oldPiezaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                    oldPiezaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(oldPiezaOfDetalleplanificacioncalidadListDetalleplanificacioncalidad);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad : pieza.getDetalleejecucionplanificacioncalidadList()) {
                Pieza oldPiezaOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getPieza();
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.setPieza(pieza);
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                if (oldPiezaOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad != null) {
                    oldPiezaOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                    oldPiezaOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(oldPiezaOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuesto : pieza.getDetalleproductopresupuestoList()) {
                Pieza oldIdpiezaOfDetalleproductopresupuestoListDetalleproductopresupuesto = detalleproductopresupuestoListDetalleproductopresupuesto.getIdpieza();
                detalleproductopresupuestoListDetalleproductopresupuesto.setIdpieza(pieza);
                detalleproductopresupuestoListDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListDetalleproductopresupuesto);
                if (oldIdpiezaOfDetalleproductopresupuestoListDetalleproductopresupuesto != null) {
                    oldIdpiezaOfDetalleproductopresupuestoListDetalleproductopresupuesto.getDetalleproductopresupuestoList().remove(detalleproductopresupuestoListDetalleproductopresupuesto);
                    oldIdpiezaOfDetalleproductopresupuestoListDetalleproductopresupuesto = em.merge(oldIdpiezaOfDetalleproductopresupuestoListDetalleproductopresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPieza(pieza.getIdpieza()) != null) {
                throw new PreexistingEntityException("Pieza " + pieza + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pieza pieza) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pieza persistentPieza = em.find(Pieza.class, pieza.getIdpieza());
            Unidadmedida unidadmedidaOld = persistentPieza.getUnidadmedida();
            Unidadmedida unidadmedidaNew = pieza.getUnidadmedida();
            Matriz matrizOld = persistentPieza.getMatriz();
            Matriz matrizNew = pieza.getMatriz();
            Materiaprima materiaprimaOld = persistentPieza.getMateriaprima();
            Materiaprima materiaprimaNew = pieza.getMateriaprima();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOld = persistentPieza.getDetalleplanificacionproduccionList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListNew = pieza.getDetalleplanificacionproduccionList();
            List<Piezareal> piezarealListOld = persistentPieza.getPiezarealList();
            List<Piezareal> piezarealListNew = pieza.getPiezarealList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListOld = persistentPieza.getDetalleejecucionplanificacionList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListNew = pieza.getDetalleejecucionplanificacionList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOld = persistentPieza.getDetalleplanificacioncalidadList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListNew = pieza.getDetalleplanificacioncalidadList();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListOld = persistentPieza.getDetalleejecucionplanificacioncalidadList();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListNew = pieza.getDetalleejecucionplanificacioncalidadList();
            List<Detalleproductopresupuesto> detalleproductopresupuestoListOld = persistentPieza.getDetalleproductopresupuestoList();
            List<Detalleproductopresupuesto> detalleproductopresupuestoListNew = pieza.getDetalleproductopresupuestoList();
            List<String> illegalOrphanMessages = null;
            for (Piezareal piezarealListOldPiezareal : piezarealListOld) {
                if (!piezarealListNew.contains(piezarealListOldPiezareal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Piezareal " + piezarealListOldPiezareal + " since its idpieza field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unidadmedidaNew != null) {
                unidadmedidaNew = em.getReference(unidadmedidaNew.getClass(), unidadmedidaNew.getIdunidadmedida());
                pieza.setUnidadmedida(unidadmedidaNew);
            }
            if (matrizNew != null) {
                matrizNew = em.getReference(matrizNew.getClass(), matrizNew.getIdmatriz());
                pieza.setMatriz(matrizNew);
            }
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                pieza.setMateriaprima(materiaprimaNew);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionListNew = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionListNew) {
                detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionListNew.add(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionListNew = attachedDetalleplanificacionproduccionListNew;
            pieza.setDetalleplanificacionproduccionList(detalleplanificacionproduccionListNew);
            List<Piezareal> attachedPiezarealListNew = new ArrayList<Piezareal>();
            for (Piezareal piezarealListNewPiezarealToAttach : piezarealListNew) {
                piezarealListNewPiezarealToAttach = em.getReference(piezarealListNewPiezarealToAttach.getClass(), piezarealListNewPiezarealToAttach.getIdpiezareal());
                attachedPiezarealListNew.add(piezarealListNewPiezarealToAttach);
            }
            piezarealListNew = attachedPiezarealListNew;
            pieza.setPiezarealList(piezarealListNew);
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionListNew = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionListNew) {
                detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionListNew.add(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionListNew = attachedDetalleejecucionplanificacionListNew;
            pieza.setDetalleejecucionplanificacionList(detalleejecucionplanificacionListNew);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadListNew = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadListNew) {
                detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadListNew.add(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadListNew = attachedDetalleplanificacioncalidadListNew;
            pieza.setDetalleplanificacioncalidadList(detalleplanificacioncalidadListNew);
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadListNew = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadListNew) {
                detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleejecucionplanificacioncalidadListNew.add(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadListNew = attachedDetalleejecucionplanificacioncalidadListNew;
            pieza.setDetalleejecucionplanificacioncalidadList(detalleejecucionplanificacioncalidadListNew);
            List<Detalleproductopresupuesto> attachedDetalleproductopresupuestoListNew = new ArrayList<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoListNew) {
                detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoListNew.add(detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoListNew = attachedDetalleproductopresupuestoListNew;
            pieza.setDetalleproductopresupuestoList(detalleproductopresupuestoListNew);
            pieza = em.merge(pieza);
            if (unidadmedidaOld != null && !unidadmedidaOld.equals(unidadmedidaNew)) {
                unidadmedidaOld.getPiezaList().remove(pieza);
                unidadmedidaOld = em.merge(unidadmedidaOld);
            }
            if (unidadmedidaNew != null && !unidadmedidaNew.equals(unidadmedidaOld)) {
                unidadmedidaNew.getPiezaList().add(pieza);
                unidadmedidaNew = em.merge(unidadmedidaNew);
            }
            if (matrizOld != null && !matrizOld.equals(matrizNew)) {
                matrizOld.getPiezaList().remove(pieza);
                matrizOld = em.merge(matrizOld);
            }
            if (matrizNew != null && !matrizNew.equals(matrizOld)) {
                matrizNew.getPiezaList().add(pieza);
                matrizNew = em.merge(matrizNew);
            }
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getPiezaList().remove(pieza);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getPiezaList().add(pieza);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListOldDetalleplanificacionproduccion : detalleplanificacionproduccionListOld) {
                if (!detalleplanificacionproduccionListNew.contains(detalleplanificacionproduccionListOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion.setIdpieza(null);
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccion : detalleplanificacionproduccionListNew) {
                if (!detalleplanificacionproduccionListOld.contains(detalleplanificacionproduccionListNewDetalleplanificacionproduccion)) {
                    Pieza oldIdpiezaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = detalleplanificacionproduccionListNewDetalleplanificacionproduccion.getIdpieza();
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion.setIdpieza(pieza);
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    if (oldIdpiezaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion != null && !oldIdpiezaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.equals(pieza)) {
                        oldIdpiezaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                        oldIdpiezaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(oldIdpiezaOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Piezareal piezarealListNewPiezareal : piezarealListNew) {
                if (!piezarealListOld.contains(piezarealListNewPiezareal)) {
                    Pieza oldIdpiezaOfPiezarealListNewPiezareal = piezarealListNewPiezareal.getIdpieza();
                    piezarealListNewPiezareal.setIdpieza(pieza);
                    piezarealListNewPiezareal = em.merge(piezarealListNewPiezareal);
                    if (oldIdpiezaOfPiezarealListNewPiezareal != null && !oldIdpiezaOfPiezarealListNewPiezareal.equals(pieza)) {
                        oldIdpiezaOfPiezarealListNewPiezareal.getPiezarealList().remove(piezarealListNewPiezareal);
                        oldIdpiezaOfPiezarealListNewPiezareal = em.merge(oldIdpiezaOfPiezarealListNewPiezareal);
                    }
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListOldDetalleejecucionplanificacion : detalleejecucionplanificacionListOld) {
                if (!detalleejecucionplanificacionListNew.contains(detalleejecucionplanificacionListOldDetalleejecucionplanificacion)) {
                    detalleejecucionplanificacionListOldDetalleejecucionplanificacion.setPieza(null);
                    detalleejecucionplanificacionListOldDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListOldDetalleejecucionplanificacion);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacion : detalleejecucionplanificacionListNew) {
                if (!detalleejecucionplanificacionListOld.contains(detalleejecucionplanificacionListNewDetalleejecucionplanificacion)) {
                    Pieza oldPiezaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = detalleejecucionplanificacionListNewDetalleejecucionplanificacion.getPieza();
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion.setPieza(pieza);
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    if (oldPiezaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion != null && !oldPiezaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.equals(pieza)) {
                        oldPiezaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                        oldPiezaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(oldPiezaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    }
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOldDetalleplanificacioncalidad : detalleplanificacioncalidadListOld) {
                if (!detalleplanificacioncalidadListNew.contains(detalleplanificacioncalidadListOldDetalleplanificacioncalidad)) {
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad.setPieza(null);
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListOldDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidad : detalleplanificacioncalidadListNew) {
                if (!detalleplanificacioncalidadListOld.contains(detalleplanificacioncalidadListNewDetalleplanificacioncalidad)) {
                    Pieza oldPiezaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = detalleplanificacioncalidadListNewDetalleplanificacioncalidad.getPieza();
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad.setPieza(pieza);
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    if (oldPiezaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad != null && !oldPiezaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.equals(pieza)) {
                        oldPiezaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                        oldPiezaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(oldPiezaOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    }
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListOld) {
                if (!detalleejecucionplanificacioncalidadListNew.contains(detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad)) {
                    detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad.setPieza(null);
                    detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListNew) {
                if (!detalleejecucionplanificacioncalidadListOld.contains(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad)) {
                    Pieza oldPiezaOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getPieza();
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.setPieza(pieza);
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                    if (oldPiezaOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad != null && !oldPiezaOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.equals(pieza)) {
                        oldPiezaOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                        oldPiezaOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(oldPiezaOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                    }
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListOldDetalleproductopresupuesto : detalleproductopresupuestoListOld) {
                if (!detalleproductopresupuestoListNew.contains(detalleproductopresupuestoListOldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoListOldDetalleproductopresupuesto.setIdpieza(null);
                    detalleproductopresupuestoListOldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListOldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListNewDetalleproductopresupuesto : detalleproductopresupuestoListNew) {
                if (!detalleproductopresupuestoListOld.contains(detalleproductopresupuestoListNewDetalleproductopresupuesto)) {
                    Pieza oldIdpiezaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto = detalleproductopresupuestoListNewDetalleproductopresupuesto.getIdpieza();
                    detalleproductopresupuestoListNewDetalleproductopresupuesto.setIdpieza(pieza);
                    detalleproductopresupuestoListNewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListNewDetalleproductopresupuesto);
                    if (oldIdpiezaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto != null && !oldIdpiezaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto.equals(pieza)) {
                        oldIdpiezaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto.getDetalleproductopresupuestoList().remove(detalleproductopresupuestoListNewDetalleproductopresupuesto);
                        oldIdpiezaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto = em.merge(oldIdpiezaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pieza.getIdpieza();
                if (findPieza(id) == null) {
                    throw new NonexistentEntityException("The pieza with id " + id + " no longer exists.");
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
            Pieza pieza;
            try {
                pieza = em.getReference(Pieza.class, id);
                pieza.getIdpieza();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pieza with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Piezareal> piezarealListOrphanCheck = pieza.getPiezarealList();
            for (Piezareal piezarealListOrphanCheckPiezareal : piezarealListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pieza (" + pieza + ") cannot be destroyed since the Piezareal " + piezarealListOrphanCheckPiezareal + " in its piezarealList field has a non-nullable idpieza field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Unidadmedida unidadmedida = pieza.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida.getPiezaList().remove(pieza);
                unidadmedida = em.merge(unidadmedida);
            }
            Matriz matriz = pieza.getMatriz();
            if (matriz != null) {
                matriz.getPiezaList().remove(pieza);
                matriz = em.merge(matriz);
            }
            Materiaprima materiaprima = pieza.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getPiezaList().remove(pieza);
                materiaprima = em.merge(materiaprima);
            }
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionList = pieza.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : detalleplanificacionproduccionList) {
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdpieza(null);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
            }
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionList = pieza.getDetalleejecucionplanificacionList();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : detalleejecucionplanificacionList) {
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setPieza(null);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
            }
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadList = pieza.getDetalleplanificacioncalidadList();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : detalleplanificacioncalidadList) {
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setPieza(null);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
            }
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList = pieza.getDetalleejecucionplanificacioncalidadList();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadList) {
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.setPieza(null);
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
            }
            List<Detalleproductopresupuesto> detalleproductopresupuestoList = pieza.getDetalleproductopresupuestoList();
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuesto : detalleproductopresupuestoList) {
                detalleproductopresupuestoListDetalleproductopresupuesto.setIdpieza(null);
                detalleproductopresupuestoListDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListDetalleproductopresupuesto);
            }
            em.remove(pieza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pieza> findPiezaEntities() {
        return findPiezaEntities(true, -1, -1);
    }

    public List<Pieza> findPiezaEntities(int maxResults, int firstResult) {
        return findPiezaEntities(false, maxResults, firstResult);
    }

    private List<Pieza> findPiezaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pieza.class));
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

    public Pieza findPieza(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pieza.class, id);
        } finally {
            em.close();
        }
    }

    public int getPiezaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pieza> rt = cq.from(Pieza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
