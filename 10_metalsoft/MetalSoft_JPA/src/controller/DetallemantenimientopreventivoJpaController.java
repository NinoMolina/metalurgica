/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallemantenimientopreventivo;
import entity.DetallemantenimientopreventivoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Mantenimientopreventivo;
import entity.Servicio;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class DetallemantenimientopreventivoJpaController {

    public DetallemantenimientopreventivoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallemantenimientopreventivo detallemantenimientopreventivo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (detallemantenimientopreventivo.getDetallemantenimientopreventivoPK() == null) {
            detallemantenimientopreventivo.setDetallemantenimientopreventivoPK(new DetallemantenimientopreventivoPK());
        }
        detallemantenimientopreventivo.getDetallemantenimientopreventivoPK().setIdmantenimientopreventivo(detallemantenimientopreventivo.getMantenimientopreventivo1().getIdmantenimientopreventivo());
        List<String> illegalOrphanMessages = null;
        Mantenimientopreventivo mantenimientopreventivoOrphanCheck = detallemantenimientopreventivo.getMantenimientopreventivo();
        if (mantenimientopreventivoOrphanCheck != null) {
            Detallemantenimientopreventivo oldDetallemantenimientopreventivoOfMantenimientopreventivo = mantenimientopreventivoOrphanCheck.getDetallemantenimientopreventivo();
            if (oldDetallemantenimientopreventivoOfMantenimientopreventivo != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Mantenimientopreventivo " + mantenimientopreventivoOrphanCheck + " already has an item of type Detallemantenimientopreventivo whose mantenimientopreventivo column cannot be null. Please make another selection for the mantenimientopreventivo field.");
            }
        }
        Mantenimientopreventivo mantenimientopreventivo1OrphanCheck = detallemantenimientopreventivo.getMantenimientopreventivo1();
        if (mantenimientopreventivo1OrphanCheck != null) {
            Detallemantenimientopreventivo oldDetallemantenimientopreventivoOfMantenimientopreventivo1 = mantenimientopreventivo1OrphanCheck.getDetallemantenimientopreventivo();
            if (oldDetallemantenimientopreventivoOfMantenimientopreventivo1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Mantenimientopreventivo " + mantenimientopreventivo1OrphanCheck + " already has an item of type Detallemantenimientopreventivo whose mantenimientopreventivo1 column cannot be null. Please make another selection for the mantenimientopreventivo1 field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimientopreventivo mantenimientopreventivo = detallemantenimientopreventivo.getMantenimientopreventivo();
            if (mantenimientopreventivo != null) {
                mantenimientopreventivo = em.getReference(mantenimientopreventivo.getClass(), mantenimientopreventivo.getIdmantenimientopreventivo());
                detallemantenimientopreventivo.setMantenimientopreventivo(mantenimientopreventivo);
            }
            Mantenimientopreventivo mantenimientopreventivo1 = detallemantenimientopreventivo.getMantenimientopreventivo1();
            if (mantenimientopreventivo1 != null) {
                mantenimientopreventivo1 = em.getReference(mantenimientopreventivo1.getClass(), mantenimientopreventivo1.getIdmantenimientopreventivo());
                detallemantenimientopreventivo.setMantenimientopreventivo1(mantenimientopreventivo1);
            }
            Servicio servicio = detallemantenimientopreventivo.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getIdservicio());
                detallemantenimientopreventivo.setServicio(servicio);
            }
            Servicio servicio1 = detallemantenimientopreventivo.getServicio1();
            if (servicio1 != null) {
                servicio1 = em.getReference(servicio1.getClass(), servicio1.getIdservicio());
                detallemantenimientopreventivo.setServicio1(servicio1);
            }
            em.persist(detallemantenimientopreventivo);
            if (mantenimientopreventivo != null) {
                mantenimientopreventivo.setDetallemantenimientopreventivo(detallemantenimientopreventivo);
                mantenimientopreventivo = em.merge(mantenimientopreventivo);
            }
            if (mantenimientopreventivo1 != null) {
                mantenimientopreventivo1.setDetallemantenimientopreventivo(detallemantenimientopreventivo);
                mantenimientopreventivo1 = em.merge(mantenimientopreventivo1);
            }
            if (servicio != null) {
                servicio.getDetallemantenimientopreventivoSet().add(detallemantenimientopreventivo);
                servicio = em.merge(servicio);
            }
            if (servicio1 != null) {
                servicio1.getDetallemantenimientopreventivoSet().add(detallemantenimientopreventivo);
                servicio1 = em.merge(servicio1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallemantenimientopreventivo(detallemantenimientopreventivo.getDetallemantenimientopreventivoPK()) != null) {
                throw new PreexistingEntityException("Detallemantenimientopreventivo " + detallemantenimientopreventivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallemantenimientopreventivo detallemantenimientopreventivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        detallemantenimientopreventivo.getDetallemantenimientopreventivoPK().setIdmantenimientopreventivo(detallemantenimientopreventivo.getMantenimientopreventivo1().getIdmantenimientopreventivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientopreventivo persistentDetallemantenimientopreventivo = em.find(Detallemantenimientopreventivo.class, detallemantenimientopreventivo.getDetallemantenimientopreventivoPK());
            Mantenimientopreventivo mantenimientopreventivoOld = persistentDetallemantenimientopreventivo.getMantenimientopreventivo();
            Mantenimientopreventivo mantenimientopreventivoNew = detallemantenimientopreventivo.getMantenimientopreventivo();
            Mantenimientopreventivo mantenimientopreventivo1Old = persistentDetallemantenimientopreventivo.getMantenimientopreventivo1();
            Mantenimientopreventivo mantenimientopreventivo1New = detallemantenimientopreventivo.getMantenimientopreventivo1();
            Servicio servicioOld = persistentDetallemantenimientopreventivo.getServicio();
            Servicio servicioNew = detallemantenimientopreventivo.getServicio();
            Servicio servicio1Old = persistentDetallemantenimientopreventivo.getServicio1();
            Servicio servicio1New = detallemantenimientopreventivo.getServicio1();
            List<String> illegalOrphanMessages = null;
            if (mantenimientopreventivoNew != null && !mantenimientopreventivoNew.equals(mantenimientopreventivoOld)) {
                Detallemantenimientopreventivo oldDetallemantenimientopreventivoOfMantenimientopreventivo = mantenimientopreventivoNew.getDetallemantenimientopreventivo();
                if (oldDetallemantenimientopreventivoOfMantenimientopreventivo != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Mantenimientopreventivo " + mantenimientopreventivoNew + " already has an item of type Detallemantenimientopreventivo whose mantenimientopreventivo column cannot be null. Please make another selection for the mantenimientopreventivo field.");
                }
            }
            if (mantenimientopreventivo1New != null && !mantenimientopreventivo1New.equals(mantenimientopreventivo1Old)) {
                Detallemantenimientopreventivo oldDetallemantenimientopreventivoOfMantenimientopreventivo1 = mantenimientopreventivo1New.getDetallemantenimientopreventivo();
                if (oldDetallemantenimientopreventivoOfMantenimientopreventivo1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Mantenimientopreventivo " + mantenimientopreventivo1New + " already has an item of type Detallemantenimientopreventivo whose mantenimientopreventivo1 column cannot be null. Please make another selection for the mantenimientopreventivo1 field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (mantenimientopreventivoNew != null) {
                mantenimientopreventivoNew = em.getReference(mantenimientopreventivoNew.getClass(), mantenimientopreventivoNew.getIdmantenimientopreventivo());
                detallemantenimientopreventivo.setMantenimientopreventivo(mantenimientopreventivoNew);
            }
            if (mantenimientopreventivo1New != null) {
                mantenimientopreventivo1New = em.getReference(mantenimientopreventivo1New.getClass(), mantenimientopreventivo1New.getIdmantenimientopreventivo());
                detallemantenimientopreventivo.setMantenimientopreventivo1(mantenimientopreventivo1New);
            }
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getIdservicio());
                detallemantenimientopreventivo.setServicio(servicioNew);
            }
            if (servicio1New != null) {
                servicio1New = em.getReference(servicio1New.getClass(), servicio1New.getIdservicio());
                detallemantenimientopreventivo.setServicio1(servicio1New);
            }
            detallemantenimientopreventivo = em.merge(detallemantenimientopreventivo);
            if (mantenimientopreventivoOld != null && !mantenimientopreventivoOld.equals(mantenimientopreventivoNew)) {
                mantenimientopreventivoOld.setDetallemantenimientopreventivo(null);
                mantenimientopreventivoOld = em.merge(mantenimientopreventivoOld);
            }
            if (mantenimientopreventivoNew != null && !mantenimientopreventivoNew.equals(mantenimientopreventivoOld)) {
                mantenimientopreventivoNew.setDetallemantenimientopreventivo(detallemantenimientopreventivo);
                mantenimientopreventivoNew = em.merge(mantenimientopreventivoNew);
            }
            if (mantenimientopreventivo1Old != null && !mantenimientopreventivo1Old.equals(mantenimientopreventivo1New)) {
                mantenimientopreventivo1Old.setDetallemantenimientopreventivo(null);
                mantenimientopreventivo1Old = em.merge(mantenimientopreventivo1Old);
            }
            if (mantenimientopreventivo1New != null && !mantenimientopreventivo1New.equals(mantenimientopreventivo1Old)) {
                mantenimientopreventivo1New.setDetallemantenimientopreventivo(detallemantenimientopreventivo);
                mantenimientopreventivo1New = em.merge(mantenimientopreventivo1New);
            }
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getDetallemantenimientopreventivoSet().remove(detallemantenimientopreventivo);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getDetallemantenimientopreventivoSet().add(detallemantenimientopreventivo);
                servicioNew = em.merge(servicioNew);
            }
            if (servicio1Old != null && !servicio1Old.equals(servicio1New)) {
                servicio1Old.getDetallemantenimientopreventivoSet().remove(detallemantenimientopreventivo);
                servicio1Old = em.merge(servicio1Old);
            }
            if (servicio1New != null && !servicio1New.equals(servicio1Old)) {
                servicio1New.getDetallemantenimientopreventivoSet().add(detallemantenimientopreventivo);
                servicio1New = em.merge(servicio1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallemantenimientopreventivoPK id = detallemantenimientopreventivo.getDetallemantenimientopreventivoPK();
                if (findDetallemantenimientopreventivo(id) == null) {
                    throw new NonexistentEntityException("The detallemantenimientopreventivo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallemantenimientopreventivoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientopreventivo detallemantenimientopreventivo;
            try {
                detallemantenimientopreventivo = em.getReference(Detallemantenimientopreventivo.class, id);
                detallemantenimientopreventivo.getDetallemantenimientopreventivoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallemantenimientopreventivo with id " + id + " no longer exists.", enfe);
            }
            Mantenimientopreventivo mantenimientopreventivo = detallemantenimientopreventivo.getMantenimientopreventivo();
            if (mantenimientopreventivo != null) {
                mantenimientopreventivo.setDetallemantenimientopreventivo(null);
                mantenimientopreventivo = em.merge(mantenimientopreventivo);
            }
            Mantenimientopreventivo mantenimientopreventivo1 = detallemantenimientopreventivo.getMantenimientopreventivo1();
            if (mantenimientopreventivo1 != null) {
                mantenimientopreventivo1.setDetallemantenimientopreventivo(null);
                mantenimientopreventivo1 = em.merge(mantenimientopreventivo1);
            }
            Servicio servicio = detallemantenimientopreventivo.getServicio();
            if (servicio != null) {
                servicio.getDetallemantenimientopreventivoSet().remove(detallemantenimientopreventivo);
                servicio = em.merge(servicio);
            }
            Servicio servicio1 = detallemantenimientopreventivo.getServicio1();
            if (servicio1 != null) {
                servicio1.getDetallemantenimientopreventivoSet().remove(detallemantenimientopreventivo);
                servicio1 = em.merge(servicio1);
            }
            em.remove(detallemantenimientopreventivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallemantenimientopreventivo> findDetallemantenimientopreventivoEntities() {
        return findDetallemantenimientopreventivoEntities(true, -1, -1);
    }

    public List<Detallemantenimientopreventivo> findDetallemantenimientopreventivoEntities(int maxResults, int firstResult) {
        return findDetallemantenimientopreventivoEntities(false, maxResults, firstResult);
    }

    private List<Detallemantenimientopreventivo> findDetallemantenimientopreventivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallemantenimientopreventivo.class));
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

    public Detallemantenimientopreventivo findDetallemantenimientopreventivo(DetallemantenimientopreventivoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallemantenimientopreventivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallemantenimientopreventivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallemantenimientopreventivo> rt = cq.from(Detallemantenimientopreventivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
