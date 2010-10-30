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
            Empleado empleado1 = mantenimientocorrectivo.getEmpleado1();
            if (empleado1 != null) {
                empleado1 = em.getReference(empleado1.getClass(), empleado1.getIdempleado());
                mantenimientocorrectivo.setEmpleado1(empleado1);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientocorrectivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento = em.getReference(proveedormantenimiento.getClass(), proveedormantenimiento.getIdproveedormantenimiento());
                mantenimientocorrectivo.setProveedormantenimiento(proveedormantenimiento);
            }
            Proveedormantenimientomaquina proveedormantenimiento1 = mantenimientocorrectivo.getProveedormantenimiento1();
            if (proveedormantenimiento1 != null) {
                proveedormantenimiento1 = em.getReference(proveedormantenimiento1.getClass(), proveedormantenimiento1.getIdproveedormantenimiento());
                mantenimientocorrectivo.setProveedormantenimiento1(proveedormantenimiento1);
            }
            Detallemantenimientocorrectivo detallemantenimientocorrectivo = mantenimientocorrectivo.getDetallemantenimientocorrectivo();
            if (detallemantenimientocorrectivo != null) {
                detallemantenimientocorrectivo = em.getReference(detallemantenimientocorrectivo.getClass(), detallemantenimientocorrectivo.getDetallemantenimientocorrectivoPK());
                mantenimientocorrectivo.setDetallemantenimientocorrectivo(detallemantenimientocorrectivo);
            }
            Detallemantenimientocorrectivo detallemantenimientocorrectivo1 = mantenimientocorrectivo.getDetallemantenimientocorrectivo1();
            if (detallemantenimientocorrectivo1 != null) {
                detallemantenimientocorrectivo1 = em.getReference(detallemantenimientocorrectivo1.getClass(), detallemantenimientocorrectivo1.getDetallemantenimientocorrectivoPK());
                mantenimientocorrectivo.setDetallemantenimientocorrectivo1(detallemantenimientocorrectivo1);
            }
            em.persist(mantenimientocorrectivo);
            if (empleado != null) {
                empleado.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                empleado = em.merge(empleado);
            }
            if (empleado1 != null) {
                empleado1.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                empleado1 = em.merge(empleado1);
            }
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            if (proveedormantenimiento1 != null) {
                proveedormantenimiento1.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                proveedormantenimiento1 = em.merge(proveedormantenimiento1);
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
            if (detallemantenimientocorrectivo1 != null) {
                Mantenimientocorrectivo oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1 = detallemantenimientocorrectivo1.getMantenimientocorrectivo1();
                if (oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1 != null) {
                    oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1.setDetallemantenimientocorrectivo1(null);
                    oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1 = em.merge(oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1);
                }
                detallemantenimientocorrectivo1.setMantenimientocorrectivo1(mantenimientocorrectivo);
                detallemantenimientocorrectivo1 = em.merge(detallemantenimientocorrectivo1);
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
            Empleado empleado1Old = persistentMantenimientocorrectivo.getEmpleado1();
            Empleado empleado1New = mantenimientocorrectivo.getEmpleado1();
            Proveedormantenimientomaquina proveedormantenimientoOld = persistentMantenimientocorrectivo.getProveedormantenimiento();
            Proveedormantenimientomaquina proveedormantenimientoNew = mantenimientocorrectivo.getProveedormantenimiento();
            Proveedormantenimientomaquina proveedormantenimiento1Old = persistentMantenimientocorrectivo.getProveedormantenimiento1();
            Proveedormantenimientomaquina proveedormantenimiento1New = mantenimientocorrectivo.getProveedormantenimiento1();
            Detallemantenimientocorrectivo detallemantenimientocorrectivoOld = persistentMantenimientocorrectivo.getDetallemantenimientocorrectivo();
            Detallemantenimientocorrectivo detallemantenimientocorrectivoNew = mantenimientocorrectivo.getDetallemantenimientocorrectivo();
            Detallemantenimientocorrectivo detallemantenimientocorrectivo1Old = persistentMantenimientocorrectivo.getDetallemantenimientocorrectivo1();
            Detallemantenimientocorrectivo detallemantenimientocorrectivo1New = mantenimientocorrectivo.getDetallemantenimientocorrectivo1();
            List<String> illegalOrphanMessages = null;
            if (detallemantenimientocorrectivoOld != null && !detallemantenimientocorrectivoOld.equals(detallemantenimientocorrectivoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detallemantenimientocorrectivo " + detallemantenimientocorrectivoOld + " since its mantenimientocorrectivo field is not nullable.");
            }
            if (detallemantenimientocorrectivo1Old != null && !detallemantenimientocorrectivo1Old.equals(detallemantenimientocorrectivo1New)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detallemantenimientocorrectivo " + detallemantenimientocorrectivo1Old + " since its mantenimientocorrectivo1 field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                mantenimientocorrectivo.setEmpleado(empleadoNew);
            }
            if (empleado1New != null) {
                empleado1New = em.getReference(empleado1New.getClass(), empleado1New.getIdempleado());
                mantenimientocorrectivo.setEmpleado1(empleado1New);
            }
            if (proveedormantenimientoNew != null) {
                proveedormantenimientoNew = em.getReference(proveedormantenimientoNew.getClass(), proveedormantenimientoNew.getIdproveedormantenimiento());
                mantenimientocorrectivo.setProveedormantenimiento(proveedormantenimientoNew);
            }
            if (proveedormantenimiento1New != null) {
                proveedormantenimiento1New = em.getReference(proveedormantenimiento1New.getClass(), proveedormantenimiento1New.getIdproveedormantenimiento());
                mantenimientocorrectivo.setProveedormantenimiento1(proveedormantenimiento1New);
            }
            if (detallemantenimientocorrectivoNew != null) {
                detallemantenimientocorrectivoNew = em.getReference(detallemantenimientocorrectivoNew.getClass(), detallemantenimientocorrectivoNew.getDetallemantenimientocorrectivoPK());
                mantenimientocorrectivo.setDetallemantenimientocorrectivo(detallemantenimientocorrectivoNew);
            }
            if (detallemantenimientocorrectivo1New != null) {
                detallemantenimientocorrectivo1New = em.getReference(detallemantenimientocorrectivo1New.getClass(), detallemantenimientocorrectivo1New.getDetallemantenimientocorrectivoPK());
                mantenimientocorrectivo.setDetallemantenimientocorrectivo1(detallemantenimientocorrectivo1New);
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
            if (empleado1Old != null && !empleado1Old.equals(empleado1New)) {
                empleado1Old.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                empleado1Old = em.merge(empleado1Old);
            }
            if (empleado1New != null && !empleado1New.equals(empleado1Old)) {
                empleado1New.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                empleado1New = em.merge(empleado1New);
            }
            if (proveedormantenimientoOld != null && !proveedormantenimientoOld.equals(proveedormantenimientoNew)) {
                proveedormantenimientoOld.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                proveedormantenimientoOld = em.merge(proveedormantenimientoOld);
            }
            if (proveedormantenimientoNew != null && !proveedormantenimientoNew.equals(proveedormantenimientoOld)) {
                proveedormantenimientoNew.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                proveedormantenimientoNew = em.merge(proveedormantenimientoNew);
            }
            if (proveedormantenimiento1Old != null && !proveedormantenimiento1Old.equals(proveedormantenimiento1New)) {
                proveedormantenimiento1Old.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                proveedormantenimiento1Old = em.merge(proveedormantenimiento1Old);
            }
            if (proveedormantenimiento1New != null && !proveedormantenimiento1New.equals(proveedormantenimiento1Old)) {
                proveedormantenimiento1New.getMantenimientocorrectivoSet().add(mantenimientocorrectivo);
                proveedormantenimiento1New = em.merge(proveedormantenimiento1New);
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
            if (detallemantenimientocorrectivo1New != null && !detallemantenimientocorrectivo1New.equals(detallemantenimientocorrectivo1Old)) {
                Mantenimientocorrectivo oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1 = detallemantenimientocorrectivo1New.getMantenimientocorrectivo1();
                if (oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1 != null) {
                    oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1.setDetallemantenimientocorrectivo1(null);
                    oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1 = em.merge(oldMantenimientocorrectivo1OfDetallemantenimientocorrectivo1);
                }
                detallemantenimientocorrectivo1New.setMantenimientocorrectivo1(mantenimientocorrectivo);
                detallemantenimientocorrectivo1New = em.merge(detallemantenimientocorrectivo1New);
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
            Detallemantenimientocorrectivo detallemantenimientocorrectivo1OrphanCheck = mantenimientocorrectivo.getDetallemantenimientocorrectivo1();
            if (detallemantenimientocorrectivo1OrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mantenimientocorrectivo (" + mantenimientocorrectivo + ") cannot be destroyed since the Detallemantenimientocorrectivo " + detallemantenimientocorrectivo1OrphanCheck + " in its detallemantenimientocorrectivo1 field has a non-nullable mantenimientocorrectivo1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado empleado = mantenimientocorrectivo.getEmpleado();
            if (empleado != null) {
                empleado.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                empleado = em.merge(empleado);
            }
            Empleado empleado1 = mantenimientocorrectivo.getEmpleado1();
            if (empleado1 != null) {
                empleado1.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                empleado1 = em.merge(empleado1);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientocorrectivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            Proveedormantenimientomaquina proveedormantenimiento1 = mantenimientocorrectivo.getProveedormantenimiento1();
            if (proveedormantenimiento1 != null) {
                proveedormantenimiento1.getMantenimientocorrectivoSet().remove(mantenimientocorrectivo);
                proveedormantenimiento1 = em.merge(proveedormantenimiento1);
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
