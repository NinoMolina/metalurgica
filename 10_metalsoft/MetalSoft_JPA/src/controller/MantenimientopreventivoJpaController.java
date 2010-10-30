/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Mantenimientopreventivo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detallemantenimientopreventivo;
import entity.Proveedormantenimientomaquina;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class MantenimientopreventivoJpaController {

    public MantenimientopreventivoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimientopreventivo mantenimientopreventivo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientopreventivo detallemantenimientopreventivo = mantenimientopreventivo.getDetallemantenimientopreventivo();
            if (detallemantenimientopreventivo != null) {
                detallemantenimientopreventivo = em.getReference(detallemantenimientopreventivo.getClass(), detallemantenimientopreventivo.getDetallemantenimientopreventivoPK());
                mantenimientopreventivo.setDetallemantenimientopreventivo(detallemantenimientopreventivo);
            }
            Detallemantenimientopreventivo detallemantenimientopreventivo1 = mantenimientopreventivo.getDetallemantenimientopreventivo1();
            if (detallemantenimientopreventivo1 != null) {
                detallemantenimientopreventivo1 = em.getReference(detallemantenimientopreventivo1.getClass(), detallemantenimientopreventivo1.getDetallemantenimientopreventivoPK());
                mantenimientopreventivo.setDetallemantenimientopreventivo1(detallemantenimientopreventivo1);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientopreventivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento = em.getReference(proveedormantenimiento.getClass(), proveedormantenimiento.getIdproveedormantenimiento());
                mantenimientopreventivo.setProveedormantenimiento(proveedormantenimiento);
            }
            Proveedormantenimientomaquina proveedormantenimiento1 = mantenimientopreventivo.getProveedormantenimiento1();
            if (proveedormantenimiento1 != null) {
                proveedormantenimiento1 = em.getReference(proveedormantenimiento1.getClass(), proveedormantenimiento1.getIdproveedormantenimiento());
                mantenimientopreventivo.setProveedormantenimiento1(proveedormantenimiento1);
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
            if (detallemantenimientopreventivo1 != null) {
                Mantenimientopreventivo oldMantenimientopreventivo1OfDetallemantenimientopreventivo1 = detallemantenimientopreventivo1.getMantenimientopreventivo1();
                if (oldMantenimientopreventivo1OfDetallemantenimientopreventivo1 != null) {
                    oldMantenimientopreventivo1OfDetallemantenimientopreventivo1.setDetallemantenimientopreventivo1(null);
                    oldMantenimientopreventivo1OfDetallemantenimientopreventivo1 = em.merge(oldMantenimientopreventivo1OfDetallemantenimientopreventivo1);
                }
                detallemantenimientopreventivo1.setMantenimientopreventivo1(mantenimientopreventivo);
                detallemantenimientopreventivo1 = em.merge(detallemantenimientopreventivo1);
            }
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientopreventivoSet().add(mantenimientopreventivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            if (proveedormantenimiento1 != null) {
                proveedormantenimiento1.getMantenimientopreventivoSet().add(mantenimientopreventivo);
                proveedormantenimiento1 = em.merge(proveedormantenimiento1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMantenimientopreventivo(mantenimientopreventivo.getIdmantenimientopreventivo()) != null) {
                throw new PreexistingEntityException("Mantenimientopreventivo " + mantenimientopreventivo + " already exists.", ex);
            }
            throw ex;
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
            Detallemantenimientopreventivo detallemantenimientopreventivo1Old = persistentMantenimientopreventivo.getDetallemantenimientopreventivo1();
            Detallemantenimientopreventivo detallemantenimientopreventivo1New = mantenimientopreventivo.getDetallemantenimientopreventivo1();
            Proveedormantenimientomaquina proveedormantenimientoOld = persistentMantenimientopreventivo.getProveedormantenimiento();
            Proveedormantenimientomaquina proveedormantenimientoNew = mantenimientopreventivo.getProveedormantenimiento();
            Proveedormantenimientomaquina proveedormantenimiento1Old = persistentMantenimientopreventivo.getProveedormantenimiento1();
            Proveedormantenimientomaquina proveedormantenimiento1New = mantenimientopreventivo.getProveedormantenimiento1();
            List<String> illegalOrphanMessages = null;
            if (detallemantenimientopreventivoOld != null && !detallemantenimientopreventivoOld.equals(detallemantenimientopreventivoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detallemantenimientopreventivo " + detallemantenimientopreventivoOld + " since its mantenimientopreventivo field is not nullable.");
            }
            if (detallemantenimientopreventivo1Old != null && !detallemantenimientopreventivo1Old.equals(detallemantenimientopreventivo1New)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detallemantenimientopreventivo " + detallemantenimientopreventivo1Old + " since its mantenimientopreventivo1 field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (detallemantenimientopreventivoNew != null) {
                detallemantenimientopreventivoNew = em.getReference(detallemantenimientopreventivoNew.getClass(), detallemantenimientopreventivoNew.getDetallemantenimientopreventivoPK());
                mantenimientopreventivo.setDetallemantenimientopreventivo(detallemantenimientopreventivoNew);
            }
            if (detallemantenimientopreventivo1New != null) {
                detallemantenimientopreventivo1New = em.getReference(detallemantenimientopreventivo1New.getClass(), detallemantenimientopreventivo1New.getDetallemantenimientopreventivoPK());
                mantenimientopreventivo.setDetallemantenimientopreventivo1(detallemantenimientopreventivo1New);
            }
            if (proveedormantenimientoNew != null) {
                proveedormantenimientoNew = em.getReference(proveedormantenimientoNew.getClass(), proveedormantenimientoNew.getIdproveedormantenimiento());
                mantenimientopreventivo.setProveedormantenimiento(proveedormantenimientoNew);
            }
            if (proveedormantenimiento1New != null) {
                proveedormantenimiento1New = em.getReference(proveedormantenimiento1New.getClass(), proveedormantenimiento1New.getIdproveedormantenimiento());
                mantenimientopreventivo.setProveedormantenimiento1(proveedormantenimiento1New);
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
            if (detallemantenimientopreventivo1New != null && !detallemantenimientopreventivo1New.equals(detallemantenimientopreventivo1Old)) {
                Mantenimientopreventivo oldMantenimientopreventivo1OfDetallemantenimientopreventivo1 = detallemantenimientopreventivo1New.getMantenimientopreventivo1();
                if (oldMantenimientopreventivo1OfDetallemantenimientopreventivo1 != null) {
                    oldMantenimientopreventivo1OfDetallemantenimientopreventivo1.setDetallemantenimientopreventivo1(null);
                    oldMantenimientopreventivo1OfDetallemantenimientopreventivo1 = em.merge(oldMantenimientopreventivo1OfDetallemantenimientopreventivo1);
                }
                detallemantenimientopreventivo1New.setMantenimientopreventivo1(mantenimientopreventivo);
                detallemantenimientopreventivo1New = em.merge(detallemantenimientopreventivo1New);
            }
            if (proveedormantenimientoOld != null && !proveedormantenimientoOld.equals(proveedormantenimientoNew)) {
                proveedormantenimientoOld.getMantenimientopreventivoSet().remove(mantenimientopreventivo);
                proveedormantenimientoOld = em.merge(proveedormantenimientoOld);
            }
            if (proveedormantenimientoNew != null && !proveedormantenimientoNew.equals(proveedormantenimientoOld)) {
                proveedormantenimientoNew.getMantenimientopreventivoSet().add(mantenimientopreventivo);
                proveedormantenimientoNew = em.merge(proveedormantenimientoNew);
            }
            if (proveedormantenimiento1Old != null && !proveedormantenimiento1Old.equals(proveedormantenimiento1New)) {
                proveedormantenimiento1Old.getMantenimientopreventivoSet().remove(mantenimientopreventivo);
                proveedormantenimiento1Old = em.merge(proveedormantenimiento1Old);
            }
            if (proveedormantenimiento1New != null && !proveedormantenimiento1New.equals(proveedormantenimiento1Old)) {
                proveedormantenimiento1New.getMantenimientopreventivoSet().add(mantenimientopreventivo);
                proveedormantenimiento1New = em.merge(proveedormantenimiento1New);
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
            Detallemantenimientopreventivo detallemantenimientopreventivo1OrphanCheck = mantenimientopreventivo.getDetallemantenimientopreventivo1();
            if (detallemantenimientopreventivo1OrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mantenimientopreventivo (" + mantenimientopreventivo + ") cannot be destroyed since the Detallemantenimientopreventivo " + detallemantenimientopreventivo1OrphanCheck + " in its detallemantenimientopreventivo1 field has a non-nullable mantenimientopreventivo1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedormantenimientomaquina proveedormantenimiento = mantenimientopreventivo.getProveedormantenimiento();
            if (proveedormantenimiento != null) {
                proveedormantenimiento.getMantenimientopreventivoSet().remove(mantenimientopreventivo);
                proveedormantenimiento = em.merge(proveedormantenimiento);
            }
            Proveedormantenimientomaquina proveedormantenimiento1 = mantenimientopreventivo.getProveedormantenimiento1();
            if (proveedormantenimiento1 != null) {
                proveedormantenimiento1.getMantenimientopreventivoSet().remove(mantenimientopreventivo);
                proveedormantenimiento1 = em.merge(proveedormantenimiento1);
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
