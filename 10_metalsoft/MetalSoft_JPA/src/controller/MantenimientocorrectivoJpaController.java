/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Mantenimientocorrectivo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empleado;
import entity.Proveedormantenimientomaquina;
import entity.Detallemantenimientocorrectivo;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class MantenimientocorrectivoJpaController {

    public MantenimientocorrectivoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimientocorrectivo mantenimientocorrectivo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = mantenimientocorrectivo.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                mantenimientocorrectivo.setEmpleado(empleado);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientocorrectivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento = em.getReference(proveedormantenimiento.getClass(), proveedormantenimiento.getIdproveedormantenimiento());
                mantenimientocorrectivo.setProveedormantenimiento(proveedormantenimiento);
            }
            Detallemantenimientocorrectivo detallemantenimientocorrectivo = mantenimientocorrectivo.getDetallemantenimientocorrectivo();
            if (detallemantenimientocorrectivo != null) {
                detallemantenimientocorrectivo = em.getReference(detallemantenimientocorrectivo.getClass(), detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK());
                mantenimientocorrectivo.setDetallemantenimientocorrectivo(detallemantenimientocorrectivo);
            }
            em.persist(mantenimientocorrectivo);
            if (empleado != null) {
                empleado.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                empleado = em.merge(empleado);
            }
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            if (detallemantenimientocorrectivo != null) {
                Mantenimientocorrectivo oldMantenimientocorrectivoOfDetallemantenimientocorrectivo = detallemantenimientocorrectivo.getMantenimientocorrectivo();
                if (oldMantenimientocorrectivoOfDetallemantenimientocorrectivo != null) {
                    oldMantenimientocorrectivoOfDetallemantenimientocorrectivo.setDetallemantenimientocorrectivo(null);
                    oldMantenimientocorrectivoOfDetallemantenimientocorrectivo = em.merge(oldMantenimientocorrectivoOfDetallemantenimientocorrectivo);
                }
                detallemantenimientocorrectivo.setMantenimientocorrectivo(mantenimientocorrectivo);
                detallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMantenimientocorrectivo(mantenimientocorrectivo.getIdmantenimientocorrectivo()) != null) {
                throw new PreexistingEntityException("Mantenimientocorrectivo " + mantenimientocorrectivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mantenimientocorrectivo mantenimientocorrectivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimientocorrectivo persistentMantenimientocorrectivo = em.find(Mantenimientocorrectivo.class, mantenimientocorrectivo.getIdmantenimientocorrectivo());
            Empleado empleadoOld = persistentMantenimientocorrectivo.getEmpleado();
            Empleado empleadoNew = mantenimientocorrectivo.getEmpleado();
            Proveedormantenimientomaquina proveedormantenimientoOld = persistentMantenimientocorrectivo.getProveedormantenimiento();
            Proveedormantenimientomaquina proveedormantenimientoNew = mantenimientocorrectivo.getProveedormantenimiento();
            Detallemantenimientocorrectivo detallemantenimientocorrectivoOld = persistentMantenimientocorrectivo.getDetallemantenimientocorrectivo();
            Detallemantenimientocorrectivo detallemantenimientocorrectivoNew = mantenimientocorrectivo.getDetallemantenimientocorrectivo();
            List<String> illegalOrphanMessages = null;
            if (detallemantenimientocorrectivoOld != null && !detallemantenimientocorrectivoOld.equals(detallemantenimientocorrectivoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detallemantenimientocorrectivo " + detallemantenimientocorrectivoOld + " since its mantenimientocorrectivo field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                mantenimientocorrectivo.setEmpleado(empleadoNew);
            }
            if (proveedormantenimientoNew != null) {
                proveedormantenimientoNew = em.getReference(proveedormantenimientoNew.getClass(), proveedormantenimientoNew.getIdproveedormantenimiento());
                mantenimientocorrectivo.setProveedormantenimiento(proveedormantenimientoNew);
            }
            if (detallemantenimientocorrectivoNew != null) {
                detallemantenimientocorrectivoNew = em.getReference(detallemantenimientocorrectivoNew.getClass(), detallemantenimientocorrectivoNew.getDetallemantenimientocorrectivoPK());
                mantenimientocorrectivo.setDetallemantenimientocorrectivo(detallemantenimientocorrectivoNew);
            }
            mantenimientocorrectivo = em.merge(mantenimientocorrectivo);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                empleadoNew = em.merge(empleadoNew);
            }
            if (proveedormantenimientoOld != null && !proveedormantenimientoOld.equals(proveedormantenimientoNew)) {
                proveedormantenimientoOld.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                proveedormantenimientoOld = em.merge(proveedormantenimientoOld);
            }
            if (proveedormantenimientoNew != null && !proveedormantenimientoNew.equals(proveedormantenimientoOld)) {
                proveedormantenimientoNew.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                proveedormantenimientoNew = em.merge(proveedormantenimientoNew);
            }
            if (detallemantenimientocorrectivoNew != null && !detallemantenimientocorrectivoNew.equals(detallemantenimientocorrectivoOld)) {
                Mantenimientocorrectivo oldMantenimientocorrectivoOfDetallemantenimientocorrectivo = detallemantenimientocorrectivoNew.getMantenimientocorrectivo();
                if (oldMantenimientocorrectivoOfDetallemantenimientocorrectivo != null) {
                    oldMantenimientocorrectivoOfDetallemantenimientocorrectivo.setDetallemantenimientocorrectivo(null);
                    oldMantenimientocorrectivoOfDetallemantenimientocorrectivo = em.merge(oldMantenimientocorrectivoOfDetallemantenimientocorrectivo);
                }
                detallemantenimientocorrectivoNew.setMantenimientocorrectivo(mantenimientocorrectivo);
                detallemantenimientocorrectivoNew = em.merge(detallemantenimientocorrectivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mantenimientocorrectivo.getIdmantenimientocorrectivo();
                if (findMantenimientocorrectivo(id) == null) {
                    throw new NonexistentEntityException("The mantenimientocorrectivo with id " + id + " no longer exists.");
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
            Mantenimientocorrectivo mantenimientocorrectivo;
            try {
                mantenimientocorrectivo = em.getReference(Mantenimientocorrectivo.class, id);
                mantenimientocorrectivo.getIdmantenimientocorrectivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimientocorrectivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Detallemantenimientocorrectivo detallemantenimientocorrectivoOrphanCheck = mantenimientocorrectivo.getDetallemantenimientocorrectivo();
            if (detallemantenimientocorrectivoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mantenimientocorrectivo (" + mantenimientocorrectivo + ") cannot be destroyed since the Detallemantenimientocorrectivo " + detallemantenimientocorrectivoOrphanCheck + " in its detallemantenimientocorrectivo field has a non-nullable mantenimientocorrectivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado empleado = mantenimientocorrectivo.getEmpleado();
            if (empleado != null) {
                empleado.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                empleado = em.merge(empleado);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientocorrectivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            em.remove(mantenimientocorrectivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mantenimientocorrectivo> findMantenimientocorrectivoEntities() {
        return findMantenimientocorrectivoEntities(true, -1, -1);
    }

    public List<Mantenimientocorrectivo> findMantenimientocorrectivoEntities(int maxResults, int firstResult) {
        return findMantenimientocorrectivoEntities(false, maxResults, firstResult);
    }

    private List<Mantenimientocorrectivo> findMantenimientocorrectivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mantenimientocorrectivo.class));
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

    public Mantenimientocorrectivo findMantenimientocorrectivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mantenimientocorrectivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMantenimientocorrectivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mantenimientocorrectivo> rt = cq.from(Mantenimientocorrectivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
