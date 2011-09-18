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
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.datos.jpa.entity.Procesocalidad;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.datos.jpa.entity.Empleado;

/**
 *
 * @author Nino
 */
public class DetalleplanificacioncalidadJpaController implements Serializable {

    public DetalleplanificacioncalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleplanificacioncalidad detalleplanificacioncalidad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = detalleplanificacioncalidad.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdproducto());
                detalleplanificacioncalidad.setProducto(producto);
            }
            Procesocalidad procesocalidad = detalleplanificacioncalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad = em.getReference(procesocalidad.getClass(), procesocalidad.getIdprocesocalidad());
                detalleplanificacioncalidad.setProcesocalidad(procesocalidad);
            }
            Planificacioncalidad idplanificacioncalidad = detalleplanificacioncalidad.getIdplanificacioncalidad();
            if (idplanificacioncalidad != null) {
                idplanificacioncalidad = em.getReference(idplanificacioncalidad.getClass(), idplanificacioncalidad.getIdplanificacion());
                detalleplanificacioncalidad.setIdplanificacioncalidad(idplanificacioncalidad);
            }
            Pieza pieza = detalleplanificacioncalidad.getPieza();
            if (pieza != null) {
                pieza = em.getReference(pieza.getClass(), pieza.getIdpieza());
                detalleplanificacioncalidad.setPieza(pieza);
            }
            Maquina maquina = detalleplanificacioncalidad.getMaquina();
            if (maquina != null) {
                maquina = em.getReference(maquina.getClass(), maquina.getIdmaquina());
                detalleplanificacioncalidad.setMaquina(maquina);
            }
            Empleado empleado = detalleplanificacioncalidad.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                detalleplanificacioncalidad.setEmpleado(empleado);
            }
            em.persist(detalleplanificacioncalidad);
            if (producto != null) {
                producto.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                producto = em.merge(producto);
            }
            if (procesocalidad != null) {
                procesocalidad.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            if (idplanificacioncalidad != null) {
                idplanificacioncalidad.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                idplanificacioncalidad = em.merge(idplanificacioncalidad);
            }
            if (pieza != null) {
                pieza.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                pieza = em.merge(pieza);
            }
            if (maquina != null) {
                maquina.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                maquina = em.merge(maquina);
            }
            if (empleado != null) {
                empleado.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                empleado = em.merge(empleado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleplanificacioncalidad detalleplanificacioncalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanificacioncalidad persistentDetalleplanificacioncalidad = em.find(Detalleplanificacioncalidad.class, detalleplanificacioncalidad.getIddetalle());
            Producto productoOld = persistentDetalleplanificacioncalidad.getProducto();
            Producto productoNew = detalleplanificacioncalidad.getProducto();
            Procesocalidad procesocalidadOld = persistentDetalleplanificacioncalidad.getProcesocalidad();
            Procesocalidad procesocalidadNew = detalleplanificacioncalidad.getProcesocalidad();
            Planificacioncalidad idplanificacioncalidadOld = persistentDetalleplanificacioncalidad.getIdplanificacioncalidad();
            Planificacioncalidad idplanificacioncalidadNew = detalleplanificacioncalidad.getIdplanificacioncalidad();
            Pieza piezaOld = persistentDetalleplanificacioncalidad.getPieza();
            Pieza piezaNew = detalleplanificacioncalidad.getPieza();
            Maquina maquinaOld = persistentDetalleplanificacioncalidad.getMaquina();
            Maquina maquinaNew = detalleplanificacioncalidad.getMaquina();
            Empleado empleadoOld = persistentDetalleplanificacioncalidad.getEmpleado();
            Empleado empleadoNew = detalleplanificacioncalidad.getEmpleado();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdproducto());
                detalleplanificacioncalidad.setProducto(productoNew);
            }
            if (procesocalidadNew != null) {
                procesocalidadNew = em.getReference(procesocalidadNew.getClass(), procesocalidadNew.getIdprocesocalidad());
                detalleplanificacioncalidad.setProcesocalidad(procesocalidadNew);
            }
            if (idplanificacioncalidadNew != null) {
                idplanificacioncalidadNew = em.getReference(idplanificacioncalidadNew.getClass(), idplanificacioncalidadNew.getIdplanificacion());
                detalleplanificacioncalidad.setIdplanificacioncalidad(idplanificacioncalidadNew);
            }
            if (piezaNew != null) {
                piezaNew = em.getReference(piezaNew.getClass(), piezaNew.getIdpieza());
                detalleplanificacioncalidad.setPieza(piezaNew);
            }
            if (maquinaNew != null) {
                maquinaNew = em.getReference(maquinaNew.getClass(), maquinaNew.getIdmaquina());
                detalleplanificacioncalidad.setMaquina(maquinaNew);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                detalleplanificacioncalidad.setEmpleado(empleadoNew);
            }
            detalleplanificacioncalidad = em.merge(detalleplanificacioncalidad);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                productoNew = em.merge(productoNew);
            }
            if (procesocalidadOld != null && !procesocalidadOld.equals(procesocalidadNew)) {
                procesocalidadOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                procesocalidadOld = em.merge(procesocalidadOld);
            }
            if (procesocalidadNew != null && !procesocalidadNew.equals(procesocalidadOld)) {
                procesocalidadNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                procesocalidadNew = em.merge(procesocalidadNew);
            }
            if (idplanificacioncalidadOld != null && !idplanificacioncalidadOld.equals(idplanificacioncalidadNew)) {
                idplanificacioncalidadOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                idplanificacioncalidadOld = em.merge(idplanificacioncalidadOld);
            }
            if (idplanificacioncalidadNew != null && !idplanificacioncalidadNew.equals(idplanificacioncalidadOld)) {
                idplanificacioncalidadNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                idplanificacioncalidadNew = em.merge(idplanificacioncalidadNew);
            }
            if (piezaOld != null && !piezaOld.equals(piezaNew)) {
                piezaOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                piezaOld = em.merge(piezaOld);
            }
            if (piezaNew != null && !piezaNew.equals(piezaOld)) {
                piezaNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                piezaNew = em.merge(piezaNew);
            }
            if (maquinaOld != null && !maquinaOld.equals(maquinaNew)) {
                maquinaOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                maquinaOld = em.merge(maquinaOld);
            }
            if (maquinaNew != null && !maquinaNew.equals(maquinaOld)) {
                maquinaNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                maquinaNew = em.merge(maquinaNew);
            }
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                empleadoNew = em.merge(empleadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleplanificacioncalidad.getIddetalle();
                if (findDetalleplanificacioncalidad(id) == null) {
                    throw new NonexistentEntityException("The detalleplanificacioncalidad with id " + id + " no longer exists.");
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
            Detalleplanificacioncalidad detalleplanificacioncalidad;
            try {
                detalleplanificacioncalidad = em.getReference(Detalleplanificacioncalidad.class, id);
                detalleplanificacioncalidad.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleplanificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            Producto producto = detalleplanificacioncalidad.getProducto();
            if (producto != null) {
                producto.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                producto = em.merge(producto);
            }
            Procesocalidad procesocalidad = detalleplanificacioncalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            Planificacioncalidad idplanificacioncalidad = detalleplanificacioncalidad.getIdplanificacioncalidad();
            if (idplanificacioncalidad != null) {
                idplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                idplanificacioncalidad = em.merge(idplanificacioncalidad);
            }
            Pieza pieza = detalleplanificacioncalidad.getPieza();
            if (pieza != null) {
                pieza.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                pieza = em.merge(pieza);
            }
            Maquina maquina = detalleplanificacioncalidad.getMaquina();
            if (maquina != null) {
                maquina.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                maquina = em.merge(maquina);
            }
            Empleado empleado = detalleplanificacioncalidad.getEmpleado();
            if (empleado != null) {
                empleado.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                empleado = em.merge(empleado);
            }
            em.remove(detalleplanificacioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleplanificacioncalidad> findDetalleplanificacioncalidadEntities() {
        return findDetalleplanificacioncalidadEntities(true, -1, -1);
    }

    public List<Detalleplanificacioncalidad> findDetalleplanificacioncalidadEntities(int maxResults, int firstResult) {
        return findDetalleplanificacioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Detalleplanificacioncalidad> findDetalleplanificacioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleplanificacioncalidad.class));
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

    public Detalleplanificacioncalidad findDetalleplanificacioncalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleplanificacioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleplanificacioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleplanificacioncalidad> rt = cq.from(Detalleplanificacioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
