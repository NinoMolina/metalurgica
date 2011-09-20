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
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import metalsoft.datos.jpa.entity.DetallemantenimientopreventivoPK;
import metalsoft.datos.jpa.entity.Servicio;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class DetallemantenimientopreventivoJpaController implements Serializable {

    public DetallemantenimientopreventivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallemantenimientopreventivo detallemantenimientopreventivo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (detallemantenimientopreventivo.getDetallemantenimientopreventivoPK() == null) {
            detallemantenimientopreventivo.setDetallemantenimientopreventivoPK(new DetallemantenimientopreventivoPK());
        }
        detallemantenimientopreventivo.getDetallemantenimientopreventivoPK().setIdmantenimientopreventivo(detallemantenimientopreventivo.getMantenimientopreventivo().getIdmantenimientopreventivo());
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
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicio = detallemantenimientopreventivo.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getIdservicio());
                detallemantenimientopreventivo.setServicio(servicio);
            }
            Mantenimientopreventivo mantenimientopreventivo = detallemantenimientopreventivo.getMantenimientopreventivo();
            if (mantenimientopreventivo != null) {
                mantenimientopreventivo = em.getReference(mantenimientopreventivo.getClass(), mantenimientopreventivo.getIdmantenimientopreventivo());
                detallemantenimientopreventivo.setMantenimientopreventivo(mantenimientopreventivo);
            }
            em.persist(detallemantenimientopreventivo);
            if (servicio != null) {
                servicio.getDetallemantenimientopreventivoList().add(detallemantenimientopreventivo);
                servicio = em.merge(servicio);
            }
            if (mantenimientopreventivo != null) {
                mantenimientopreventivo.setDetallemantenimientopreventivo(detallemantenimientopreventivo);
                mantenimientopreventivo = em.merge(mantenimientopreventivo);
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
        detallemantenimientopreventivo.getDetallemantenimientopreventivoPK().setIdmantenimientopreventivo(detallemantenimientopreventivo.getMantenimientopreventivo().getIdmantenimientopreventivo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientopreventivo persistentDetallemantenimientopreventivo = em.find(Detallemantenimientopreventivo.class, detallemantenimientopreventivo.getDetallemantenimientopreventivoPK());
            Servicio servicioOld = persistentDetallemantenimientopreventivo.getServicio();
            Servicio servicioNew = detallemantenimientopreventivo.getServicio();
            Mantenimientopreventivo mantenimientopreventivoOld = persistentDetallemantenimientopreventivo.getMantenimientopreventivo();
            Mantenimientopreventivo mantenimientopreventivoNew = detallemantenimientopreventivo.getMantenimientopreventivo();
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
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getIdservicio());
                detallemantenimientopreventivo.setServicio(servicioNew);
            }
            if (mantenimientopreventivoNew != null) {
                mantenimientopreventivoNew = em.getReference(mantenimientopreventivoNew.getClass(), mantenimientopreventivoNew.getIdmantenimientopreventivo());
                detallemantenimientopreventivo.setMantenimientopreventivo(mantenimientopreventivoNew);
            }
            detallemantenimientopreventivo = em.merge(detallemantenimientopreventivo);
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivo);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getDetallemantenimientopreventivoList().add(detallemantenimientopreventivo);
                servicioNew = em.merge(servicioNew);
            }
            if (mantenimientopreventivoOld != null && !mantenimientopreventivoOld.equals(mantenimientopreventivoNew)) {
                mantenimientopreventivoOld.setDetallemantenimientopreventivo(null);
                mantenimientopreventivoOld = em.merge(mantenimientopreventivoOld);
            }
            if (mantenimientopreventivoNew != null && !mantenimientopreventivoNew.equals(mantenimientopreventivoOld)) {
                mantenimientopreventivoNew.setDetallemantenimientopreventivo(detallemantenimientopreventivo);
                mantenimientopreventivoNew = em.merge(mantenimientopreventivoNew);
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
            Servicio servicio = detallemantenimientopreventivo.getServicio();
            if (servicio != null) {
                servicio.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivo);
                servicio = em.merge(servicio);
            }
            Mantenimientopreventivo mantenimientopreventivo = detallemantenimientopreventivo.getMantenimientopreventivo();
            if (mantenimientopreventivo != null) {
                mantenimientopreventivo.setDetallemantenimientopreventivo(null);
                mantenimientopreventivo = em.merge(mantenimientopreventivo);
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
