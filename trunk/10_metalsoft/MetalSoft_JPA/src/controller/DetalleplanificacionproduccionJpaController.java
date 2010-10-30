/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleplanificacionproduccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detalleejecucionplanificacion;
import entity.Empleado;
import entity.Etapadeproduccion;
import entity.Maquina;
import entity.Pieza;
import entity.Planificacionproduccion;

/**
 *
 * @author Nino
 */
public class DetalleplanificacionproduccionJpaController {

    public DetalleplanificacionproduccionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleplanificacionproduccion detalleplanificacionproduccion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleejecucionplanificacion iddetalleejecucionplanificacion = detalleplanificacionproduccion.getIddetalleejecucionplanificacion();
            if (iddetalleejecucionplanificacion != null) {
                iddetalleejecucionplanificacion = em.getReference(iddetalleejecucionplanificacion.getClass(), iddetalleejecucionplanificacion.getId());
                detalleplanificacionproduccion.setIddetalleejecucionplanificacion(iddetalleejecucionplanificacion);
            }
            Detalleejecucionplanificacion iddetalleejecucionplanificacion1 = detalleplanificacionproduccion.getIddetalleejecucionplanificacion1();
            if (iddetalleejecucionplanificacion1 != null) {
                iddetalleejecucionplanificacion1 = em.getReference(iddetalleejecucionplanificacion1.getClass(), iddetalleejecucionplanificacion1.getId());
                detalleplanificacionproduccion.setIddetalleejecucionplanificacion1(iddetalleejecucionplanificacion1);
            }
            Empleado idempleado = detalleplanificacionproduccion.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                detalleplanificacionproduccion.setIdempleado(idempleado);
            }
            Empleado idempleado1 = detalleplanificacionproduccion.getIdempleado1();
            if (idempleado1 != null) {
                idempleado1 = em.getReference(idempleado1.getClass(), idempleado1.getIdempleado());
                detalleplanificacionproduccion.setIdempleado1(idempleado1);
            }
            Etapadeproduccion idetapaproduccion = detalleplanificacionproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion = em.getReference(idetapaproduccion.getClass(), idetapaproduccion.getIdetapaproduccion());
                detalleplanificacionproduccion.setIdetapaproduccion(idetapaproduccion);
            }
            Etapadeproduccion idetapaproduccion1 = detalleplanificacionproduccion.getIdetapaproduccion1();
            if (idetapaproduccion1 != null) {
                idetapaproduccion1 = em.getReference(idetapaproduccion1.getClass(), idetapaproduccion1.getIdetapaproduccion());
                detalleplanificacionproduccion.setIdetapaproduccion1(idetapaproduccion1);
            }
            Maquina idmaquina = detalleplanificacionproduccion.getIdmaquina();
            if (idmaquina != null) {
                idmaquina = em.getReference(idmaquina.getClass(), idmaquina.getIdmaquina());
                detalleplanificacionproduccion.setIdmaquina(idmaquina);
            }
            Maquina idmaquina1 = detalleplanificacionproduccion.getIdmaquina1();
            if (idmaquina1 != null) {
                idmaquina1 = em.getReference(idmaquina1.getClass(), idmaquina1.getIdmaquina());
                detalleplanificacionproduccion.setIdmaquina1(idmaquina1);
            }
            Pieza idpieza = detalleplanificacionproduccion.getIdpieza();
            if (idpieza != null) {
                idpieza = em.getReference(idpieza.getClass(), idpieza.getIdpieza());
                detalleplanificacionproduccion.setIdpieza(idpieza);
            }
            Pieza idpieza1 = detalleplanificacionproduccion.getIdpieza1();
            if (idpieza1 != null) {
                idpieza1 = em.getReference(idpieza1.getClass(), idpieza1.getIdpieza());
                detalleplanificacionproduccion.setIdpieza1(idpieza1);
            }
            Planificacionproduccion idplanificacionproduccion = detalleplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion = em.getReference(idplanificacionproduccion.getClass(), idplanificacionproduccion.getIdplanificacionproduccion());
                detalleplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccion);
            }
            Planificacionproduccion idplanificacionproduccion1 = detalleplanificacionproduccion.getIdplanificacionproduccion1();
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1 = em.getReference(idplanificacionproduccion1.getClass(), idplanificacionproduccion1.getIdplanificacionproduccion());
                detalleplanificacionproduccion.setIdplanificacionproduccion1(idplanificacionproduccion1);
            }
            em.persist(detalleplanificacionproduccion);
            if (iddetalleejecucionplanificacion != null) {
                iddetalleejecucionplanificacion.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                iddetalleejecucionplanificacion = em.merge(iddetalleejecucionplanificacion);
            }
            if (iddetalleejecucionplanificacion1 != null) {
                iddetalleejecucionplanificacion1.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                iddetalleejecucionplanificacion1 = em.merge(iddetalleejecucionplanificacion1);
            }
            if (idempleado != null) {
                idempleado.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idempleado = em.merge(idempleado);
            }
            if (idempleado1 != null) {
                idempleado1.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idempleado1 = em.merge(idempleado1);
            }
            if (idetapaproduccion != null) {
                idetapaproduccion.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            if (idetapaproduccion1 != null) {
                idetapaproduccion1.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idetapaproduccion1 = em.merge(idetapaproduccion1);
            }
            if (idmaquina != null) {
                idmaquina.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idmaquina = em.merge(idmaquina);
            }
            if (idmaquina1 != null) {
                idmaquina1.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idmaquina1 = em.merge(idmaquina1);
            }
            if (idpieza != null) {
                idpieza.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idpieza = em.merge(idpieza);
            }
            if (idpieza1 != null) {
                idpieza1.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idpieza1 = em.merge(idpieza1);
            }
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idplanificacionproduccion1 = em.merge(idplanificacionproduccion1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleplanificacionproduccion(detalleplanificacionproduccion.getId()) != null) {
                throw new PreexistingEntityException("Detalleplanificacionproduccion " + detalleplanificacionproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleplanificacionproduccion detalleplanificacionproduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanificacionproduccion persistentDetalleplanificacionproduccion = em.find(Detalleplanificacionproduccion.class, detalleplanificacionproduccion.getId());
            Detalleejecucionplanificacion iddetalleejecucionplanificacionOld = persistentDetalleplanificacionproduccion.getIddetalleejecucionplanificacion();
            Detalleejecucionplanificacion iddetalleejecucionplanificacionNew = detalleplanificacionproduccion.getIddetalleejecucionplanificacion();
            Detalleejecucionplanificacion iddetalleejecucionplanificacion1Old = persistentDetalleplanificacionproduccion.getIddetalleejecucionplanificacion1();
            Detalleejecucionplanificacion iddetalleejecucionplanificacion1New = detalleplanificacionproduccion.getIddetalleejecucionplanificacion1();
            Empleado idempleadoOld = persistentDetalleplanificacionproduccion.getIdempleado();
            Empleado idempleadoNew = detalleplanificacionproduccion.getIdempleado();
            Empleado idempleado1Old = persistentDetalleplanificacionproduccion.getIdempleado1();
            Empleado idempleado1New = detalleplanificacionproduccion.getIdempleado1();
            Etapadeproduccion idetapaproduccionOld = persistentDetalleplanificacionproduccion.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccionNew = detalleplanificacionproduccion.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccion1Old = persistentDetalleplanificacionproduccion.getIdetapaproduccion1();
            Etapadeproduccion idetapaproduccion1New = detalleplanificacionproduccion.getIdetapaproduccion1();
            Maquina idmaquinaOld = persistentDetalleplanificacionproduccion.getIdmaquina();
            Maquina idmaquinaNew = detalleplanificacionproduccion.getIdmaquina();
            Maquina idmaquina1Old = persistentDetalleplanificacionproduccion.getIdmaquina1();
            Maquina idmaquina1New = detalleplanificacionproduccion.getIdmaquina1();
            Pieza idpiezaOld = persistentDetalleplanificacionproduccion.getIdpieza();
            Pieza idpiezaNew = detalleplanificacionproduccion.getIdpieza();
            Pieza idpieza1Old = persistentDetalleplanificacionproduccion.getIdpieza1();
            Pieza idpieza1New = detalleplanificacionproduccion.getIdpieza1();
            Planificacionproduccion idplanificacionproduccionOld = persistentDetalleplanificacionproduccion.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccionNew = detalleplanificacionproduccion.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccion1Old = persistentDetalleplanificacionproduccion.getIdplanificacionproduccion1();
            Planificacionproduccion idplanificacionproduccion1New = detalleplanificacionproduccion.getIdplanificacionproduccion1();
            if (iddetalleejecucionplanificacionNew != null) {
                iddetalleejecucionplanificacionNew = em.getReference(iddetalleejecucionplanificacionNew.getClass(), iddetalleejecucionplanificacionNew.getId());
                detalleplanificacionproduccion.setIddetalleejecucionplanificacion(iddetalleejecucionplanificacionNew);
            }
            if (iddetalleejecucionplanificacion1New != null) {
                iddetalleejecucionplanificacion1New = em.getReference(iddetalleejecucionplanificacion1New.getClass(), iddetalleejecucionplanificacion1New.getId());
                detalleplanificacionproduccion.setIddetalleejecucionplanificacion1(iddetalleejecucionplanificacion1New);
            }
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                detalleplanificacionproduccion.setIdempleado(idempleadoNew);
            }
            if (idempleado1New != null) {
                idempleado1New = em.getReference(idempleado1New.getClass(), idempleado1New.getIdempleado());
                detalleplanificacionproduccion.setIdempleado1(idempleado1New);
            }
            if (idetapaproduccionNew != null) {
                idetapaproduccionNew = em.getReference(idetapaproduccionNew.getClass(), idetapaproduccionNew.getIdetapaproduccion());
                detalleplanificacionproduccion.setIdetapaproduccion(idetapaproduccionNew);
            }
            if (idetapaproduccion1New != null) {
                idetapaproduccion1New = em.getReference(idetapaproduccion1New.getClass(), idetapaproduccion1New.getIdetapaproduccion());
                detalleplanificacionproduccion.setIdetapaproduccion1(idetapaproduccion1New);
            }
            if (idmaquinaNew != null) {
                idmaquinaNew = em.getReference(idmaquinaNew.getClass(), idmaquinaNew.getIdmaquina());
                detalleplanificacionproduccion.setIdmaquina(idmaquinaNew);
            }
            if (idmaquina1New != null) {
                idmaquina1New = em.getReference(idmaquina1New.getClass(), idmaquina1New.getIdmaquina());
                detalleplanificacionproduccion.setIdmaquina1(idmaquina1New);
            }
            if (idpiezaNew != null) {
                idpiezaNew = em.getReference(idpiezaNew.getClass(), idpiezaNew.getIdpieza());
                detalleplanificacionproduccion.setIdpieza(idpiezaNew);
            }
            if (idpieza1New != null) {
                idpieza1New = em.getReference(idpieza1New.getClass(), idpieza1New.getIdpieza());
                detalleplanificacionproduccion.setIdpieza1(idpieza1New);
            }
            if (idplanificacionproduccionNew != null) {
                idplanificacionproduccionNew = em.getReference(idplanificacionproduccionNew.getClass(), idplanificacionproduccionNew.getIdplanificacionproduccion());
                detalleplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccionNew);
            }
            if (idplanificacionproduccion1New != null) {
                idplanificacionproduccion1New = em.getReference(idplanificacionproduccion1New.getClass(), idplanificacionproduccion1New.getIdplanificacionproduccion());
                detalleplanificacionproduccion.setIdplanificacionproduccion1(idplanificacionproduccion1New);
            }
            detalleplanificacionproduccion = em.merge(detalleplanificacionproduccion);
            if (iddetalleejecucionplanificacionOld != null && !iddetalleejecucionplanificacionOld.equals(iddetalleejecucionplanificacionNew)) {
                iddetalleejecucionplanificacionOld.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                iddetalleejecucionplanificacionOld = em.merge(iddetalleejecucionplanificacionOld);
            }
            if (iddetalleejecucionplanificacionNew != null && !iddetalleejecucionplanificacionNew.equals(iddetalleejecucionplanificacionOld)) {
                iddetalleejecucionplanificacionNew.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                iddetalleejecucionplanificacionNew = em.merge(iddetalleejecucionplanificacionNew);
            }
            if (iddetalleejecucionplanificacion1Old != null && !iddetalleejecucionplanificacion1Old.equals(iddetalleejecucionplanificacion1New)) {
                iddetalleejecucionplanificacion1Old.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                iddetalleejecucionplanificacion1Old = em.merge(iddetalleejecucionplanificacion1Old);
            }
            if (iddetalleejecucionplanificacion1New != null && !iddetalleejecucionplanificacion1New.equals(iddetalleejecucionplanificacion1Old)) {
                iddetalleejecucionplanificacion1New.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                iddetalleejecucionplanificacion1New = em.merge(iddetalleejecucionplanificacion1New);
            }
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idempleadoNew = em.merge(idempleadoNew);
            }
            if (idempleado1Old != null && !idempleado1Old.equals(idempleado1New)) {
                idempleado1Old.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idempleado1Old = em.merge(idempleado1Old);
            }
            if (idempleado1New != null && !idempleado1New.equals(idempleado1Old)) {
                idempleado1New.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idempleado1New = em.merge(idempleado1New);
            }
            if (idetapaproduccionOld != null && !idetapaproduccionOld.equals(idetapaproduccionNew)) {
                idetapaproduccionOld.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idetapaproduccionOld = em.merge(idetapaproduccionOld);
            }
            if (idetapaproduccionNew != null && !idetapaproduccionNew.equals(idetapaproduccionOld)) {
                idetapaproduccionNew.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idetapaproduccionNew = em.merge(idetapaproduccionNew);
            }
            if (idetapaproduccion1Old != null && !idetapaproduccion1Old.equals(idetapaproduccion1New)) {
                idetapaproduccion1Old.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idetapaproduccion1Old = em.merge(idetapaproduccion1Old);
            }
            if (idetapaproduccion1New != null && !idetapaproduccion1New.equals(idetapaproduccion1Old)) {
                idetapaproduccion1New.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idetapaproduccion1New = em.merge(idetapaproduccion1New);
            }
            if (idmaquinaOld != null && !idmaquinaOld.equals(idmaquinaNew)) {
                idmaquinaOld.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idmaquinaOld = em.merge(idmaquinaOld);
            }
            if (idmaquinaNew != null && !idmaquinaNew.equals(idmaquinaOld)) {
                idmaquinaNew.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idmaquinaNew = em.merge(idmaquinaNew);
            }
            if (idmaquina1Old != null && !idmaquina1Old.equals(idmaquina1New)) {
                idmaquina1Old.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idmaquina1Old = em.merge(idmaquina1Old);
            }
            if (idmaquina1New != null && !idmaquina1New.equals(idmaquina1Old)) {
                idmaquina1New.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idmaquina1New = em.merge(idmaquina1New);
            }
            if (idpiezaOld != null && !idpiezaOld.equals(idpiezaNew)) {
                idpiezaOld.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idpiezaOld = em.merge(idpiezaOld);
            }
            if (idpiezaNew != null && !idpiezaNew.equals(idpiezaOld)) {
                idpiezaNew.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idpiezaNew = em.merge(idpiezaNew);
            }
            if (idpieza1Old != null && !idpieza1Old.equals(idpieza1New)) {
                idpieza1Old.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idpieza1Old = em.merge(idpieza1Old);
            }
            if (idpieza1New != null && !idpieza1New.equals(idpieza1Old)) {
                idpieza1New.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idpieza1New = em.merge(idpieza1New);
            }
            if (idplanificacionproduccionOld != null && !idplanificacionproduccionOld.equals(idplanificacionproduccionNew)) {
                idplanificacionproduccionOld.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idplanificacionproduccionOld = em.merge(idplanificacionproduccionOld);
            }
            if (idplanificacionproduccionNew != null && !idplanificacionproduccionNew.equals(idplanificacionproduccionOld)) {
                idplanificacionproduccionNew.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idplanificacionproduccionNew = em.merge(idplanificacionproduccionNew);
            }
            if (idplanificacionproduccion1Old != null && !idplanificacionproduccion1Old.equals(idplanificacionproduccion1New)) {
                idplanificacionproduccion1Old.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idplanificacionproduccion1Old = em.merge(idplanificacionproduccion1Old);
            }
            if (idplanificacionproduccion1New != null && !idplanificacionproduccion1New.equals(idplanificacionproduccion1Old)) {
                idplanificacionproduccion1New.getDetalleplanificacionproduccionSet().add(detalleplanificacionproduccion);
                idplanificacionproduccion1New = em.merge(idplanificacionproduccion1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleplanificacionproduccion.getId();
                if (findDetalleplanificacionproduccion(id) == null) {
                    throw new NonexistentEntityException("The detalleplanificacionproduccion with id " + id + " no longer exists.");
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
            Detalleplanificacionproduccion detalleplanificacionproduccion;
            try {
                detalleplanificacionproduccion = em.getReference(Detalleplanificacionproduccion.class, id);
                detalleplanificacionproduccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleplanificacionproduccion with id " + id + " no longer exists.", enfe);
            }
            Detalleejecucionplanificacion iddetalleejecucionplanificacion = detalleplanificacionproduccion.getIddetalleejecucionplanificacion();
            if (iddetalleejecucionplanificacion != null) {
                iddetalleejecucionplanificacion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                iddetalleejecucionplanificacion = em.merge(iddetalleejecucionplanificacion);
            }
            Detalleejecucionplanificacion iddetalleejecucionplanificacion1 = detalleplanificacionproduccion.getIddetalleejecucionplanificacion1();
            if (iddetalleejecucionplanificacion1 != null) {
                iddetalleejecucionplanificacion1.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                iddetalleejecucionplanificacion1 = em.merge(iddetalleejecucionplanificacion1);
            }
            Empleado idempleado = detalleplanificacionproduccion.getIdempleado();
            if (idempleado != null) {
                idempleado.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idempleado = em.merge(idempleado);
            }
            Empleado idempleado1 = detalleplanificacionproduccion.getIdempleado1();
            if (idempleado1 != null) {
                idempleado1.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idempleado1 = em.merge(idempleado1);
            }
            Etapadeproduccion idetapaproduccion = detalleplanificacionproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            Etapadeproduccion idetapaproduccion1 = detalleplanificacionproduccion.getIdetapaproduccion1();
            if (idetapaproduccion1 != null) {
                idetapaproduccion1.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idetapaproduccion1 = em.merge(idetapaproduccion1);
            }
            Maquina idmaquina = detalleplanificacionproduccion.getIdmaquina();
            if (idmaquina != null) {
                idmaquina.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idmaquina = em.merge(idmaquina);
            }
            Maquina idmaquina1 = detalleplanificacionproduccion.getIdmaquina1();
            if (idmaquina1 != null) {
                idmaquina1.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idmaquina1 = em.merge(idmaquina1);
            }
            Pieza idpieza = detalleplanificacionproduccion.getIdpieza();
            if (idpieza != null) {
                idpieza.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idpieza = em.merge(idpieza);
            }
            Pieza idpieza1 = detalleplanificacionproduccion.getIdpieza1();
            if (idpieza1 != null) {
                idpieza1.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idpieza1 = em.merge(idpieza1);
            }
            Planificacionproduccion idplanificacionproduccion = detalleplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            Planificacionproduccion idplanificacionproduccion1 = detalleplanificacionproduccion.getIdplanificacionproduccion1();
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccion);
                idplanificacionproduccion1 = em.merge(idplanificacionproduccion1);
            }
            em.remove(detalleplanificacionproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleplanificacionproduccion> findDetalleplanificacionproduccionEntities() {
        return findDetalleplanificacionproduccionEntities(true, -1, -1);
    }

    public List<Detalleplanificacionproduccion> findDetalleplanificacionproduccionEntities(int maxResults, int firstResult) {
        return findDetalleplanificacionproduccionEntities(false, maxResults, firstResult);
    }

    private List<Detalleplanificacionproduccion> findDetalleplanificacionproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleplanificacionproduccion.class));
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

    public Detalleplanificacionproduccion findDetalleplanificacionproduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleplanificacionproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleplanificacionproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleplanificacionproduccion> rt = cq.from(Detalleplanificacionproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
