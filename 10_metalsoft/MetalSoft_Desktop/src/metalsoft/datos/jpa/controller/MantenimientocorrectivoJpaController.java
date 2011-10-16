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
import metalsoft.datos.jpa.entity.Mantenimientocorrectivo;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class MantenimientocorrectivoJpaController implements Serializable {

    public MantenimientocorrectivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimientocorrectivo mantenimientocorrectivo) {
        if (mantenimientocorrectivo.getDetallemantenimientocorrectivoList() == null) {
            mantenimientocorrectivo.setDetallemantenimientocorrectivoList(new ArrayList<Detallemantenimientocorrectivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientocorrectivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento = em.getReference(proveedormantenimiento.getClass(), proveedormantenimiento.getIdproveedormantenimiento());
                mantenimientocorrectivo.setProveedormantenimiento(proveedormantenimiento);
            }
            Empleado empleado = mantenimientocorrectivo.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                mantenimientocorrectivo.setEmpleado(empleado);
            }
            List<Detallemantenimientocorrectivo> attachedDetallemantenimientocorrectivoList = new ArrayList<Detallemantenimientocorrectivo>();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach : mantenimientocorrectivo.getDetallemantenimientocorrectivoList()) {
                detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach = em.getReference(detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach.getClass(), detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach.getIddetalle());
                attachedDetallemantenimientocorrectivoList.add(detallemantenimientocorrectivoListDetallemantenimientocorrectivoToAttach);
            }
            mantenimientocorrectivo.setDetallemantenimientocorrectivoList(attachedDetallemantenimientocorrectivoList);
            em.persist(mantenimientocorrectivo);
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientocorrectivoList().add(mantenimientocorrectivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            if (empleado != null) {
                empleado.getMantenimientocorrectivoList().add(mantenimientocorrectivo);
                empleado = em.merge(empleado);
            }
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListDetallemantenimientocorrectivo : mantenimientocorrectivo.getDetallemantenimientocorrectivoList()) {
                Mantenimientocorrectivo oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo = detallemantenimientocorrectivoListDetallemantenimientocorrectivo.getIdmantenimientocorrectivo();
                detallemantenimientocorrectivoListDetallemantenimientocorrectivo.setIdmantenimientocorrectivo(mantenimientocorrectivo);
                detallemantenimientocorrectivoListDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoListDetallemantenimientocorrectivo);
                if (oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo != null) {
                    oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivoListDetallemantenimientocorrectivo);
                    oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo = em.merge(oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListDetallemantenimientocorrectivo);
                }
            }
            em.getTransaction().commit();
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
            Proveedormantenimientomaquina proveedormantenimientoOld = persistentMantenimientocorrectivo.getProveedormantenimiento();
            Proveedormantenimientomaquina proveedormantenimientoNew = mantenimientocorrectivo.getProveedormantenimiento();
            Empleado empleadoOld = persistentMantenimientocorrectivo.getEmpleado();
            Empleado empleadoNew = mantenimientocorrectivo.getEmpleado();
            List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoListOld = persistentMantenimientocorrectivo.getDetallemantenimientocorrectivoList();
            List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoListNew = mantenimientocorrectivo.getDetallemantenimientocorrectivoList();
            List<String> illegalOrphanMessages = null;
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListOldDetallemantenimientocorrectivo : detallemantenimientocorrectivoListOld) {
                if (!detallemantenimientocorrectivoListNew.contains(detallemantenimientocorrectivoListOldDetallemantenimientocorrectivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallemantenimientocorrectivo " + detallemantenimientocorrectivoListOldDetallemantenimientocorrectivo + " since its idmantenimientocorrectivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedormantenimientoNew != null) {
                proveedormantenimientoNew = em.getReference(proveedormantenimientoNew.getClass(), proveedormantenimientoNew.getIdproveedormantenimiento());
                mantenimientocorrectivo.setProveedormantenimiento(proveedormantenimientoNew);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                mantenimientocorrectivo.setEmpleado(empleadoNew);
            }
            List<Detallemantenimientocorrectivo> attachedDetallemantenimientocorrectivoListNew = new ArrayList<Detallemantenimientocorrectivo>();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach : detallemantenimientocorrectivoListNew) {
                detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach = em.getReference(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach.getClass(), detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach.getIddetalle());
                attachedDetallemantenimientocorrectivoListNew.add(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivoToAttach);
            }
            detallemantenimientocorrectivoListNew = attachedDetallemantenimientocorrectivoListNew;
            mantenimientocorrectivo.setDetallemantenimientocorrectivoList(detallemantenimientocorrectivoListNew);
            mantenimientocorrectivo = em.merge(mantenimientocorrectivo);
            if (proveedormantenimientoOld != null && !proveedormantenimientoOld.equals(proveedormantenimientoNew)) {
                proveedormantenimientoOld.getMantenimientocorrectivoList().remove(mantenimientocorrectivo);
                proveedormantenimientoOld = em.merge(proveedormantenimientoOld);
            }
            if (proveedormantenimientoNew != null && !proveedormantenimientoNew.equals(proveedormantenimientoOld)) {
                proveedormantenimientoNew.getMantenimientocorrectivoList().add(mantenimientocorrectivo);
                proveedormantenimientoNew = em.merge(proveedormantenimientoNew);
            }
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getMantenimientocorrectivoList().remove(mantenimientocorrectivo);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getMantenimientocorrectivoList().add(mantenimientocorrectivo);
                empleadoNew = em.merge(empleadoNew);
            }
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo : detallemantenimientocorrectivoListNew) {
                if (!detallemantenimientocorrectivoListOld.contains(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo)) {
                    Mantenimientocorrectivo oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo = detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo.getIdmantenimientocorrectivo();
                    detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo.setIdmantenimientocorrectivo(mantenimientocorrectivo);
                    detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo);
                    if (oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo != null && !oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo.equals(mantenimientocorrectivo)) {
                        oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivoListNewDetallemantenimientocorrectivo);
                        oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo = em.merge(oldIdmantenimientocorrectivoOfDetallemantenimientocorrectivoListNewDetallemantenimientocorrectivo);
                    }
                }
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
            List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoListOrphanCheck = mantenimientocorrectivo.getDetallemantenimientocorrectivoList();
            for (Detallemantenimientocorrectivo detallemantenimientocorrectivoListOrphanCheckDetallemantenimientocorrectivo : detallemantenimientocorrectivoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mantenimientocorrectivo (" + mantenimientocorrectivo + ") cannot be destroyed since the Detallemantenimientocorrectivo " + detallemantenimientocorrectivoListOrphanCheckDetallemantenimientocorrectivo + " in its detallemantenimientocorrectivoList field has a non-nullable idmantenimientocorrectivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientocorrectivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientocorrectivoList().remove(mantenimientocorrectivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            Empleado empleado = mantenimientocorrectivo.getEmpleado();
            if (empleado != null) {
                empleado.getMantenimientocorrectivoList().remove(mantenimientocorrectivo);
                empleado = em.merge(empleado);
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
