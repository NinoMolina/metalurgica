/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Pieza;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import entity.Matriz;
import entity.Unidadmedida;
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;
import entity.Detalleejecucionplanificacion;
import entity.Detalleproductopresupuesto;

/**
 *
 * @author Nino
 */
public class PiezaJpaController {

    public PiezaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pieza pieza) throws PreexistingEntityException, Exception {
        if (pieza.getDetalleplanificacionproduccionSet() == null) {
            pieza.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        if (pieza.getDetalleplanificacionproduccionSet1() == null) {
            pieza.setDetalleplanificacionproduccionSet1(new HashSet<Detalleplanificacionproduccion>());
        }
        if (pieza.getDetalleejecucionplanificacionSet() == null) {
            pieza.setDetalleejecucionplanificacionSet(new HashSet<Detalleejecucionplanificacion>());
        }
        if (pieza.getDetalleproductopresupuestoSet() == null) {
            pieza.setDetalleproductopresupuestoSet(new HashSet<Detalleproductopresupuesto>());
        }
        if (pieza.getDetalleproductopresupuestoSet1() == null) {
            pieza.setDetalleproductopresupuestoSet1(new HashSet<Detalleproductopresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima materiaprima = pieza.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                pieza.setMateriaprima(materiaprima);
            }
            Materiaprima materiaprima1 = pieza.getMateriaprima1();
            if (materiaprima1 != null) {
                materiaprima1 = em.getReference(materiaprima1.getClass(), materiaprima1.getIdmateriaprima());
                pieza.setMateriaprima1(materiaprima1);
            }
            Matriz matriz = pieza.getMatriz();
            if (matriz != null) {
                matriz = em.getReference(matriz.getClass(), matriz.getIdmatriz());
                pieza.setMatriz(matriz);
            }
            Matriz matriz1 = pieza.getMatriz1();
            if (matriz1 != null) {
                matriz1 = em.getReference(matriz1.getClass(), matriz1.getIdmatriz());
                pieza.setMatriz1(matriz1);
            }
            Unidadmedida unidadmedida = pieza.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida = em.getReference(unidadmedida.getClass(), unidadmedida.getIdunidadmedida());
                pieza.setUnidadmedida(unidadmedida);
            }
            Unidadmedida unidadmedida1 = pieza.getUnidadmedida1();
            if (unidadmedida1 != null) {
                unidadmedida1 = em.getReference(unidadmedida1.getClass(), unidadmedida1.getIdunidadmedida());
                pieza.setUnidadmedida1(unidadmedida1);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : pieza.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            pieza.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1 = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach : pieza.getDetalleplanificacionproduccionSet1()) {
                detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1.add(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach);
            }
            pieza.setDetalleplanificacionproduccionSet1(attachedDetalleplanificacionproduccionSet1);
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSet = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach : pieza.getDetalleejecucionplanificacionSet()) {
                detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSet.add(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach);
            }
            pieza.setDetalleejecucionplanificacionSet(attachedDetalleejecucionplanificacionSet);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuestoToAttach : pieza.getDetalleproductopresupuestoSet()) {
                detalleproductopresupuestoSetDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet.add(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach);
            }
            pieza.setDetalleproductopresupuestoSet(attachedDetalleproductopresupuestoSet);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet1 = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach : pieza.getDetalleproductopresupuestoSet1()) {
                detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet1.add(detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach);
            }
            pieza.setDetalleproductopresupuestoSet1(attachedDetalleproductopresupuestoSet1);
            em.persist(pieza);
            if (materiaprima != null) {
                materiaprima.getPiezaSet().add(pieza);
                materiaprima = em.merge(materiaprima);
            }
            if (materiaprima1 != null) {
                materiaprima1.getPiezaSet().add(pieza);
                materiaprima1 = em.merge(materiaprima1);
            }
            if (matriz != null) {
                matriz.getPiezaSet().add(pieza);
                matriz = em.merge(matriz);
            }
            if (matriz1 != null) {
                matriz1.getPiezaSet().add(pieza);
                matriz1 = em.merge(matriz1);
            }
            if (unidadmedida != null) {
                unidadmedida.getPiezaSet().add(pieza);
                unidadmedida = em.merge(unidadmedida);
            }
            if (unidadmedida1 != null) {
                unidadmedida1.getPiezaSet().add(pieza);
                unidadmedida1 = em.merge(unidadmedida1);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : pieza.getDetalleplanificacionproduccionSet()) {
                Pieza oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = detalleplanificacionproduccionSetDetalleplanificacionproduccion.getIdpieza();
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdpieza(pieza);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                if (oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion != null) {
                    oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                    oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(oldIdpiezaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : pieza.getDetalleplanificacionproduccionSet1()) {
                Pieza oldIdpieza1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = detalleplanificacionproduccionSet1Detalleplanificacionproduccion.getIdpieza1();
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdpieza1(pieza);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                if (oldIdpieza1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion != null) {
                    oldIdpieza1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                    oldIdpieza1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(oldIdpieza1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacion : pieza.getDetalleejecucionplanificacionSet()) {
                Pieza oldPiezaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = detalleejecucionplanificacionSetDetalleejecucionplanificacion.getPieza();
                detalleejecucionplanificacionSetDetalleejecucionplanificacion.setPieza(pieza);
                detalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                if (oldPiezaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion != null) {
                    oldPiezaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                    oldPiezaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(oldPiezaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : pieza.getDetalleproductopresupuestoSet()) {
                Pieza oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto = detalleproductopresupuestoSetDetalleproductopresupuesto.getIdpieza();
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIdpieza(pieza);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
                if (oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto != null) {
                    oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuestoSetDetalleproductopresupuesto);
                    oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(oldIdpiezaOfDetalleproductopresupuestoSetDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1Detalleproductopresupuesto : pieza.getDetalleproductopresupuestoSet1()) {
                Pieza oldIdpieza1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto = detalleproductopresupuestoSet1Detalleproductopresupuesto.getIdpieza1();
                detalleproductopresupuestoSet1Detalleproductopresupuesto.setIdpieza1(pieza);
                detalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1Detalleproductopresupuesto);
                if (oldIdpieza1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto != null) {
                    oldIdpieza1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto.getDetalleproductopresupuestoSet1().remove(detalleproductopresupuestoSet1Detalleproductopresupuesto);
                    oldIdpieza1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(oldIdpieza1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto);
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

    public void edit(Pieza pieza) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pieza persistentPieza = em.find(Pieza.class, pieza.getIdpieza());
            Materiaprima materiaprimaOld = persistentPieza.getMateriaprima();
            Materiaprima materiaprimaNew = pieza.getMateriaprima();
            Materiaprima materiaprima1Old = persistentPieza.getMateriaprima1();
            Materiaprima materiaprima1New = pieza.getMateriaprima1();
            Matriz matrizOld = persistentPieza.getMatriz();
            Matriz matrizNew = pieza.getMatriz();
            Matriz matriz1Old = persistentPieza.getMatriz1();
            Matriz matriz1New = pieza.getMatriz1();
            Unidadmedida unidadmedidaOld = persistentPieza.getUnidadmedida();
            Unidadmedida unidadmedidaNew = pieza.getUnidadmedida();
            Unidadmedida unidadmedida1Old = persistentPieza.getUnidadmedida1();
            Unidadmedida unidadmedida1New = pieza.getUnidadmedida1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentPieza.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = pieza.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1Old = persistentPieza.getDetalleplanificacionproduccionSet1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1New = pieza.getDetalleplanificacionproduccionSet1();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOld = persistentPieza.getDetalleejecucionplanificacionSet();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetNew = pieza.getDetalleejecucionplanificacionSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetOld = persistentPieza.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetNew = pieza.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1Old = persistentPieza.getDetalleproductopresupuestoSet1();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1New = pieza.getDetalleproductopresupuestoSet1();
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                pieza.setMateriaprima(materiaprimaNew);
            }
            if (materiaprima1New != null) {
                materiaprima1New = em.getReference(materiaprima1New.getClass(), materiaprima1New.getIdmateriaprima());
                pieza.setMateriaprima1(materiaprima1New);
            }
            if (matrizNew != null) {
                matrizNew = em.getReference(matrizNew.getClass(), matrizNew.getIdmatriz());
                pieza.setMatriz(matrizNew);
            }
            if (matriz1New != null) {
                matriz1New = em.getReference(matriz1New.getClass(), matriz1New.getIdmatriz());
                pieza.setMatriz1(matriz1New);
            }
            if (unidadmedidaNew != null) {
                unidadmedidaNew = em.getReference(unidadmedidaNew.getClass(), unidadmedidaNew.getIdunidadmedida());
                pieza.setUnidadmedida(unidadmedidaNew);
            }
            if (unidadmedida1New != null) {
                unidadmedida1New = em.getReference(unidadmedida1New.getClass(), unidadmedida1New.getIdunidadmedida());
                pieza.setUnidadmedida1(unidadmedida1New);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            pieza.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1New = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSet1New) {
                detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1New.add(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSet1New = attachedDetalleplanificacionproduccionSet1New;
            pieza.setDetalleplanificacionproduccionSet1(detalleplanificacionproduccionSet1New);
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSetNew = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionSetNew) {
                detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSetNew.add(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionSetNew = attachedDetalleejecucionplanificacionSetNew;
            pieza.setDetalleejecucionplanificacionSet(detalleejecucionplanificacionSetNew);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSetNew = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSetNew) {
                detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSetNew.add(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSetNew = attachedDetalleproductopresupuestoSetNew;
            pieza.setDetalleproductopresupuestoSet(detalleproductopresupuestoSetNew);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet1New = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSet1New) {
                detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet1New.add(detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSet1New = attachedDetalleproductopresupuestoSet1New;
            pieza.setDetalleproductopresupuestoSet1(detalleproductopresupuestoSet1New);
            pieza = em.merge(pieza);
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getPiezaSet().remove(pieza);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getPiezaSet().add(pieza);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            if (materiaprima1Old != null && !materiaprima1Old.equals(materiaprima1New)) {
                materiaprima1Old.getPiezaSet().remove(pieza);
                materiaprima1Old = em.merge(materiaprima1Old);
            }
            if (materiaprima1New != null && !materiaprima1New.equals(materiaprima1Old)) {
                materiaprima1New.getPiezaSet().add(pieza);
                materiaprima1New = em.merge(materiaprima1New);
            }
            if (matrizOld != null && !matrizOld.equals(matrizNew)) {
                matrizOld.getPiezaSet().remove(pieza);
                matrizOld = em.merge(matrizOld);
            }
            if (matrizNew != null && !matrizNew.equals(matrizOld)) {
                matrizNew.getPiezaSet().add(pieza);
                matrizNew = em.merge(matrizNew);
            }
            if (matriz1Old != null && !matriz1Old.equals(matriz1New)) {
                matriz1Old.getPiezaSet().remove(pieza);
                matriz1Old = em.merge(matriz1Old);
            }
            if (matriz1New != null && !matriz1New.equals(matriz1Old)) {
                matriz1New.getPiezaSet().add(pieza);
                matriz1New = em.merge(matriz1New);
            }
            if (unidadmedidaOld != null && !unidadmedidaOld.equals(unidadmedidaNew)) {
                unidadmedidaOld.getPiezaSet().remove(pieza);
                unidadmedidaOld = em.merge(unidadmedidaOld);
            }
            if (unidadmedidaNew != null && !unidadmedidaNew.equals(unidadmedidaOld)) {
                unidadmedidaNew.getPiezaSet().add(pieza);
                unidadmedidaNew = em.merge(unidadmedidaNew);
            }
            if (unidadmedida1Old != null && !unidadmedida1Old.equals(unidadmedida1New)) {
                unidadmedida1Old.getPiezaSet().remove(pieza);
                unidadmedida1Old = em.merge(unidadmedida1Old);
            }
            if (unidadmedida1New != null && !unidadmedida1New.equals(unidadmedida1Old)) {
                unidadmedida1New.getPiezaSet().add(pieza);
                unidadmedida1New = em.merge(unidadmedida1New);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion.setIdpieza(null);
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccion : detalleplanificacionproduccionSetNew) {
                if (!detalleplanificacionproduccionSetOld.contains(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion)) {
                    Pieza oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getIdpieza();
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.setIdpieza(pieza);
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    if (oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion != null && !oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.equals(pieza)) {
                        oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                        oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(oldIdpiezaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion : detalleplanificacionproduccionSet1Old) {
                if (!detalleplanificacionproduccionSet1New.contains(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion.setIdpieza1(null);
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion : detalleplanificacionproduccionSet1New) {
                if (!detalleplanificacionproduccionSet1Old.contains(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion)) {
                    Pieza oldIdpieza1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getIdpieza1();
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.setIdpieza1(pieza);
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    if (oldIdpieza1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion != null && !oldIdpieza1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.equals(pieza)) {
                        oldIdpieza1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                        oldIdpieza1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(oldIdpieza1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetOldDetalleejecucionplanificacion : detalleejecucionplanificacionSetOld) {
                if (!detalleejecucionplanificacionSetNew.contains(detalleejecucionplanificacionSetOldDetalleejecucionplanificacion)) {
                    detalleejecucionplanificacionSetOldDetalleejecucionplanificacion.setPieza(null);
                    detalleejecucionplanificacionSetOldDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetOldDetalleejecucionplanificacion);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacion : detalleejecucionplanificacionSetNew) {
                if (!detalleejecucionplanificacionSetOld.contains(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion)) {
                    Pieza oldPiezaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getPieza();
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.setPieza(pieza);
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    if (oldPiezaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion != null && !oldPiezaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.equals(pieza)) {
                        oldPiezaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                        oldPiezaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(oldPiezaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    }
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetOldDetalleproductopresupuesto : detalleproductopresupuestoSetOld) {
                if (!detalleproductopresupuestoSetNew.contains(detalleproductopresupuestoSetOldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoSetOldDetalleproductopresupuesto.setIdpieza(null);
                    detalleproductopresupuestoSetOldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetOldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuesto : detalleproductopresupuestoSetNew) {
                if (!detalleproductopresupuestoSetOld.contains(detalleproductopresupuestoSetNewDetalleproductopresupuesto)) {
                    Pieza oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto = detalleproductopresupuestoSetNewDetalleproductopresupuesto.getIdpieza();
                    detalleproductopresupuestoSetNewDetalleproductopresupuesto.setIdpieza(pieza);
                    detalleproductopresupuestoSetNewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetNewDetalleproductopresupuesto);
                    if (oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto != null && !oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto.equals(pieza)) {
                        oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuestoSetNewDetalleproductopresupuesto);
                        oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto = em.merge(oldIdpiezaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto);
                    }
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1OldDetalleproductopresupuesto : detalleproductopresupuestoSet1Old) {
                if (!detalleproductopresupuestoSet1New.contains(detalleproductopresupuestoSet1OldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoSet1OldDetalleproductopresupuesto.setIdpieza1(null);
                    detalleproductopresupuestoSet1OldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1OldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1NewDetalleproductopresupuesto : detalleproductopresupuestoSet1New) {
                if (!detalleproductopresupuestoSet1Old.contains(detalleproductopresupuestoSet1NewDetalleproductopresupuesto)) {
                    Pieza oldIdpieza1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto = detalleproductopresupuestoSet1NewDetalleproductopresupuesto.getIdpieza1();
                    detalleproductopresupuestoSet1NewDetalleproductopresupuesto.setIdpieza1(pieza);
                    detalleproductopresupuestoSet1NewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1NewDetalleproductopresupuesto);
                    if (oldIdpieza1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto != null && !oldIdpieza1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto.equals(pieza)) {
                        oldIdpieza1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto.getDetalleproductopresupuestoSet1().remove(detalleproductopresupuestoSet1NewDetalleproductopresupuesto);
                        oldIdpieza1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto = em.merge(oldIdpieza1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto);
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            Materiaprima materiaprima = pieza.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getPiezaSet().remove(pieza);
                materiaprima = em.merge(materiaprima);
            }
            Materiaprima materiaprima1 = pieza.getMateriaprima1();
            if (materiaprima1 != null) {
                materiaprima1.getPiezaSet().remove(pieza);
                materiaprima1 = em.merge(materiaprima1);
            }
            Matriz matriz = pieza.getMatriz();
            if (matriz != null) {
                matriz.getPiezaSet().remove(pieza);
                matriz = em.merge(matriz);
            }
            Matriz matriz1 = pieza.getMatriz1();
            if (matriz1 != null) {
                matriz1.getPiezaSet().remove(pieza);
                matriz1 = em.merge(matriz1);
            }
            Unidadmedida unidadmedida = pieza.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida.getPiezaSet().remove(pieza);
                unidadmedida = em.merge(unidadmedida);
            }
            Unidadmedida unidadmedida1 = pieza.getUnidadmedida1();
            if (unidadmedida1 != null) {
                unidadmedida1.getPiezaSet().remove(pieza);
                unidadmedida1 = em.merge(unidadmedida1);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = pieza.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdpieza(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1 = pieza.getDetalleplanificacionproduccionSet1();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : detalleplanificacionproduccionSet1) {
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdpieza1(null);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
            }
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSet = pieza.getDetalleejecucionplanificacionSet();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacion : detalleejecucionplanificacionSet) {
                detalleejecucionplanificacionSetDetalleejecucionplanificacion.setPieza(null);
                detalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet = pieza.getDetalleproductopresupuestoSet();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : detalleproductopresupuestoSet) {
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIdpieza(null);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1 = pieza.getDetalleproductopresupuestoSet1();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1Detalleproductopresupuesto : detalleproductopresupuestoSet1) {
                detalleproductopresupuestoSet1Detalleproductopresupuesto.setIdpieza1(null);
                detalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1Detalleproductopresupuesto);
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
