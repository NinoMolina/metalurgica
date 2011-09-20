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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class MantenimientopreventivoJpaController implements Serializable {

    public MantenimientopreventivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimientopreventivo mantenimientopreventivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientopreventivo detallemantenimientopreventivo = mantenimientopreventivo.getDetallemantenimientopreventivo();
            if (detallemantenimientopreventivo != null) {
                detallemantenimientopreventivo = em.getReference(detallemantenimientopreventivo.getClass(), detallemantenimientopreventivo.getDetallemantenimientopreventivoPK());
                mantenimientopreventivo.setDetallemantenimientopreventivo(detallemantenimientopreventivo);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientopreventivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento = em.getReference(proveedormantenimiento.getClass(), proveedormantenimiento.getIdproveedormantenimiento());
                mantenimientopreventivo.setProveedormantenimiento(proveedormantenimiento);
            }
            em.persist(mantenimientopreventivo);
            if (detallemantenimientopreventivo != null) {
                Mantenimientopreventivo oldMantenimientopreventivoOfDetallemantenimientopreventivo = detallemantenimientopreventivo.getMantenimientopreventivo();
                if (oldMantenimientopreventivoOfDetallemantenimientopreventivo != null) {
                    oldMantenimientopreventivoOfDetallemantenimientopreventivo.setDetallemantenimientopreventivo(null);
                    oldMantenimientopreventivoOfDetallemantenimientopreventivo = em.merge(oldMantenimientopreventivoOfDetallemantenimientopreventivo);
                }
                detallemantenimientopreventivo.setMantenimientopreventivo(mantenimientopreventivo);
                detallemantenimientopreventivo = em.merge(detallemantenimientopreventivo);
            }
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientopreventivoList().add(mantenimientopreventivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mantenimientopreventivo mantenimientopreventivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimientopreventivo persistentMantenimientopreventivo = em.find(Mantenimientopreventivo.class, mantenimientopreventivo.getIdmantenimientopreventivo());
            Detallemantenimientopreventivo detallemantenimientopreventivoOld = persistentMantenimientopreventivo.getDetallemantenimientopreventivo();
            Detallemantenimientopreventivo detallemantenimientopreventivoNew = mantenimientopreventivo.getDetallemantenimientopreventivo();
            Proveedormantenimientomaquina proveedormantenimientoOld = persistentMantenimientopreventivo.getProveedormantenimiento();
            Proveedormantenimientomaquina proveedormantenimientoNew = mantenimientopreventivo.getProveedormantenimiento();
            List<String> illegalOrphanMessages = null;
            if (detallemantenimientopreventivoOld != null && !detallemantenimientopreventivoOld.equals(detallemantenimientopreventivoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detallemantenimientopreventivo " + detallemantenimientopreventivoOld + " since its mantenimientopreventivo field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (detallemantenimientopreventivoNew != null) {
                detallemantenimientopreventivoNew = em.getReference(detallemantenimientopreventivoNew.getClass(), detallemantenimientopreventivoNew.getDetallemantenimientopreventivoPK());
                mantenimientopreventivo.setDetallemantenimientopreventivo(detallemantenimientopreventivoNew);
            }
            if (proveedormantenimientoNew != null) {
                proveedormantenimientoNew = em.getReference(proveedormantenimientoNew.getClass(), proveedormantenimientoNew.getIdproveedormantenimiento());
                mantenimientopreventivo.setProveedormantenimiento(proveedormantenimientoNew);
            }
            mantenimientopreventivo = em.merge(mantenimientopreventivo);
            if (detallemantenimientopreventivoNew != null && !detallemantenimientopreventivoNew.equals(detallemantenimientopreventivoOld)) {
                Mantenimientopreventivo oldMantenimientopreventivoOfDetallemantenimientopreventivo = detallemantenimientopreventivoNew.getMantenimientopreventivo();
                if (oldMantenimientopreventivoOfDetallemantenimientopreventivo != null) {
                    oldMantenimientopreventivoOfDetallemantenimientopreventivo.setDetallemantenimientopreventivo(null);
                    oldMantenimientopreventivoOfDetallemantenimientopreventivo = em.merge(oldMantenimientopreventivoOfDetallemantenimientopreventivo);
                }
                detallemantenimientopreventivoNew.setMantenimientopreventivo(mantenimientopreventivo);
                detallemantenimientopreventivoNew = em.merge(detallemantenimientopreventivoNew);
            }
            if (proveedormantenimientoOld != null && !proveedormantenimientoOld.equals(proveedormantenimientoNew)) {
                proveedormantenimientoOld.getMantenimientopreventivoList().remove(mantenimientopreventivo);
                proveedormantenimientoOld = em.merge(proveedormantenimientoOld);
            }
            if (proveedormantenimientoNew != null && !proveedormantenimientoNew.equals(proveedormantenimientoOld)) {
                proveedormantenimientoNew.getMantenimientopreventivoList().add(mantenimientopreventivo);
                proveedormantenimientoNew = em.merge(proveedormantenimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mantenimientopreventivo.getIdmantenimientopreventivo();
                if (findMantenimientopreventivo(id) == null) {
                    throw new NonexistentEntityException("The mantenimientopreventivo with id " + id + " no longer exists.");
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
            Mantenimientopreventivo mantenimientopreventivo;
            try {
                mantenimientopreventivo = em.getReference(Mantenimientopreventivo.class, id);
                mantenimientopreventivo.getIdmantenimientopreventivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimientopreventivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Detallemantenimientopreventivo detallemantenimientopreventivoOrphanCheck = mantenimientopreventivo.getDetallemantenimientopreventivo();
            if (detallemantenimientopreventivoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mantenimientopreventivo (" + mantenimientopreventivo + ") cannot be destroyed since the Detallemantenimientopreventivo " + detallemantenimientopreventivoOrphanCheck + " in its detallemantenimientopreventivo field has a non-nullable mantenimientopreventivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientopreventivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientopreventivoList().remove(mantenimientopreventivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            em.remove(mantenimientopreventivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mantenimientopreventivo> findMantenimientopreventivoEntities() {
        return findMantenimientopreventivoEntities(true, -1, -1);
    }

    public List<Mantenimientopreventivo> findMantenimientopreventivoEntities(int maxResults, int firstResult) {
        return findMantenimientopreventivoEntities(false, maxResults, firstResult);
    }

    private List<Mantenimientopreventivo> findMantenimientopreventivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mantenimientopreventivo.class));
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

    public Mantenimientopreventivo findMantenimientopreventivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mantenimientopreventivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMantenimientopreventivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mantenimientopreventivo> rt = cq.from(Mantenimientopreventivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
