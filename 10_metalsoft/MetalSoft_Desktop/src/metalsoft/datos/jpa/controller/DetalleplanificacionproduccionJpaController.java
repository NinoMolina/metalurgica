/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;

/**
 *
 * @author Nino
 */
public class DetalleplanificacionproduccionJpaController implements Serializable {

    public DetalleplanificacionproduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
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
            Producto idproducto = detalleplanificacionproduccion.getIdproducto();
            if (idproducto != null) {
                idproducto = em.getReference(idproducto.getClass(), idproducto.getIdproducto());
                detalleplanificacionproduccion.setIdproducto(idproducto);
            }
            Planificacionproduccion idplanificacionproduccion = detalleplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion = em.getReference(idplanificacionproduccion.getClass(), idplanificacionproduccion.getIdplanificacionproduccion());
                detalleplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccion);
            }
            Pieza idpieza = detalleplanificacionproduccion.getIdpieza();
            if (idpieza != null) {
                idpieza = em.getReference(idpieza.getClass(), idpieza.getIdpieza());
                detalleplanificacionproduccion.setIdpieza(idpieza);
            }
            Maquina idmaquina = detalleplanificacionproduccion.getIdmaquina();
            if (idmaquina != null) {
                idmaquina = em.getReference(idmaquina.getClass(), idmaquina.getIdmaquina());
                detalleplanificacionproduccion.setIdmaquina(idmaquina);
            }
            Etapadeproduccion idetapaproduccion = detalleplanificacionproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion = em.getReference(idetapaproduccion.getClass(), idetapaproduccion.getIdetapaproduccion());
                detalleplanificacionproduccion.setIdetapaproduccion(idetapaproduccion);
            }
            Empleado idempleado = detalleplanificacionproduccion.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                detalleplanificacionproduccion.setIdempleado(idempleado);
            }
            Detalleejecucionplanificacion iddetalleejecucionplanificacion = detalleplanificacionproduccion.getIddetalleejecucionplanificacion();
            if (iddetalleejecucionplanificacion != null) {
                iddetalleejecucionplanificacion = em.getReference(iddetalleejecucionplanificacion.getClass(), iddetalleejecucionplanificacion.getId());
                detalleplanificacionproduccion.setIddetalleejecucionplanificacion(iddetalleejecucionplanificacion);
            }
            em.persist(detalleplanificacionproduccion);
            if (idproducto != null) {
                idproducto.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idproducto = em.merge(idproducto);
            }
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            if (idpieza != null) {
                idpieza.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idpieza = em.merge(idpieza);
            }
            if (idmaquina != null) {
                idmaquina.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idmaquina = em.merge(idmaquina);
            }
            if (idetapaproduccion != null) {
                idetapaproduccion.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            if (idempleado != null) {
                idempleado.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idempleado = em.merge(idempleado);
            }
            if (iddetalleejecucionplanificacion != null) {
                iddetalleejecucionplanificacion.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                iddetalleejecucionplanificacion = em.merge(iddetalleejecucionplanificacion);
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
            Producto idproductoOld = persistentDetalleplanificacionproduccion.getIdproducto();
            Producto idproductoNew = detalleplanificacionproduccion.getIdproducto();
            Planificacionproduccion idplanificacionproduccionOld = persistentDetalleplanificacionproduccion.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccionNew = detalleplanificacionproduccion.getIdplanificacionproduccion();
            Pieza idpiezaOld = persistentDetalleplanificacionproduccion.getIdpieza();
            Pieza idpiezaNew = detalleplanificacionproduccion.getIdpieza();
            Maquina idmaquinaOld = persistentDetalleplanificacionproduccion.getIdmaquina();
            Maquina idmaquinaNew = detalleplanificacionproduccion.getIdmaquina();
            Etapadeproduccion idetapaproduccionOld = persistentDetalleplanificacionproduccion.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccionNew = detalleplanificacionproduccion.getIdetapaproduccion();
            Empleado idempleadoOld = persistentDetalleplanificacionproduccion.getIdempleado();
            Empleado idempleadoNew = detalleplanificacionproduccion.getIdempleado();
            Detalleejecucionplanificacion iddetalleejecucionplanificacionOld = persistentDetalleplanificacionproduccion.getIddetalleejecucionplanificacion();
            Detalleejecucionplanificacion iddetalleejecucionplanificacionNew = detalleplanificacionproduccion.getIddetalleejecucionplanificacion();
            if (idproductoNew != null) {
                idproductoNew = em.getReference(idproductoNew.getClass(), idproductoNew.getIdproducto());
                detalleplanificacionproduccion.setIdproducto(idproductoNew);
            }
            if (idplanificacionproduccionNew != null) {
                idplanificacionproduccionNew = em.getReference(idplanificacionproduccionNew.getClass(), idplanificacionproduccionNew.getIdplanificacionproduccion());
                detalleplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccionNew);
            }
            if (idpiezaNew != null) {
                idpiezaNew = em.getReference(idpiezaNew.getClass(), idpiezaNew.getIdpieza());
                detalleplanificacionproduccion.setIdpieza(idpiezaNew);
            }
            if (idmaquinaNew != null) {
                idmaquinaNew = em.getReference(idmaquinaNew.getClass(), idmaquinaNew.getIdmaquina());
                detalleplanificacionproduccion.setIdmaquina(idmaquinaNew);
            }
            if (idetapaproduccionNew != null) {
                idetapaproduccionNew = em.getReference(idetapaproduccionNew.getClass(), idetapaproduccionNew.getIdetapaproduccion());
                detalleplanificacionproduccion.setIdetapaproduccion(idetapaproduccionNew);
            }
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                detalleplanificacionproduccion.setIdempleado(idempleadoNew);
            }
            if (iddetalleejecucionplanificacionNew != null) {
                iddetalleejecucionplanificacionNew = em.getReference(iddetalleejecucionplanificacionNew.getClass(), iddetalleejecucionplanificacionNew.getId());
                detalleplanificacionproduccion.setIddetalleejecucionplanificacion(iddetalleejecucionplanificacionNew);
            }
            detalleplanificacionproduccion = em.merge(detalleplanificacionproduccion);
            if (idproductoOld != null && !idproductoOld.equals(idproductoNew)) {
                idproductoOld.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idproductoOld = em.merge(idproductoOld);
            }
            if (idproductoNew != null && !idproductoNew.equals(idproductoOld)) {
                idproductoNew.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idproductoNew = em.merge(idproductoNew);
            }
            if (idplanificacionproduccionOld != null && !idplanificacionproduccionOld.equals(idplanificacionproduccionNew)) {
                idplanificacionproduccionOld.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idplanificacionproduccionOld = em.merge(idplanificacionproduccionOld);
            }
            if (idplanificacionproduccionNew != null && !idplanificacionproduccionNew.equals(idplanificacionproduccionOld)) {
                idplanificacionproduccionNew.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idplanificacionproduccionNew = em.merge(idplanificacionproduccionNew);
            }
            if (idpiezaOld != null && !idpiezaOld.equals(idpiezaNew)) {
                idpiezaOld.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idpiezaOld = em.merge(idpiezaOld);
            }
            if (idpiezaNew != null && !idpiezaNew.equals(idpiezaOld)) {
                idpiezaNew.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idpiezaNew = em.merge(idpiezaNew);
            }
            if (idmaquinaOld != null && !idmaquinaOld.equals(idmaquinaNew)) {
                idmaquinaOld.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idmaquinaOld = em.merge(idmaquinaOld);
            }
            if (idmaquinaNew != null && !idmaquinaNew.equals(idmaquinaOld)) {
                idmaquinaNew.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idmaquinaNew = em.merge(idmaquinaNew);
            }
            if (idetapaproduccionOld != null && !idetapaproduccionOld.equals(idetapaproduccionNew)) {
                idetapaproduccionOld.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idetapaproduccionOld = em.merge(idetapaproduccionOld);
            }
            if (idetapaproduccionNew != null && !idetapaproduccionNew.equals(idetapaproduccionOld)) {
                idetapaproduccionNew.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idetapaproduccionNew = em.merge(idetapaproduccionNew);
            }
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                idempleadoNew = em.merge(idempleadoNew);
            }
            if (iddetalleejecucionplanificacionOld != null && !iddetalleejecucionplanificacionOld.equals(iddetalleejecucionplanificacionNew)) {
                iddetalleejecucionplanificacionOld.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                iddetalleejecucionplanificacionOld = em.merge(iddetalleejecucionplanificacionOld);
            }
            if (iddetalleejecucionplanificacionNew != null && !iddetalleejecucionplanificacionNew.equals(iddetalleejecucionplanificacionOld)) {
                iddetalleejecucionplanificacionNew.getDetalleplanificacionproduccionList().add(detalleplanificacionproduccion);
                iddetalleejecucionplanificacionNew = em.merge(iddetalleejecucionplanificacionNew);
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
            Producto idproducto = detalleplanificacionproduccion.getIdproducto();
            if (idproducto != null) {
                idproducto.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idproducto = em.merge(idproducto);
            }
            Planificacionproduccion idplanificacionproduccion = detalleplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            Pieza idpieza = detalleplanificacionproduccion.getIdpieza();
            if (idpieza != null) {
                idpieza.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idpieza = em.merge(idpieza);
            }
            Maquina idmaquina = detalleplanificacionproduccion.getIdmaquina();
            if (idmaquina != null) {
                idmaquina.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idmaquina = em.merge(idmaquina);
            }
            Etapadeproduccion idetapaproduccion = detalleplanificacionproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            Empleado idempleado = detalleplanificacionproduccion.getIdempleado();
            if (idempleado != null) {
                idempleado.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                idempleado = em.merge(idempleado);
            }
            Detalleejecucionplanificacion iddetalleejecucionplanificacion = detalleplanificacionproduccion.getIddetalleejecucionplanificacion();
            if (iddetalleejecucionplanificacion != null) {
                iddetalleejecucionplanificacion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccion);
                iddetalleejecucionplanificacion = em.merge(iddetalleejecucionplanificacion);
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
