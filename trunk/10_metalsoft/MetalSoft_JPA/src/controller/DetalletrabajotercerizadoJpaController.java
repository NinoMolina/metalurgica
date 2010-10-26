/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalletrabajotercerizado;
import entity.DetalletrabajotercerizadoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadodetalletrabajotercerizado;
import entity.Etapadeproduccion;
import entity.Trabajotercerizado;

/**
 *
 * @author Nino
 */
public class DetalletrabajotercerizadoJpaController {

    public DetalletrabajotercerizadoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalletrabajotercerizado detalletrabajotercerizado) throws PreexistingEntityException, Exception {
        if (detalletrabajotercerizado.getDetalletrabajotercerizadoPK() == null) {
            detalletrabajotercerizado.setDetalletrabajotercerizadoPK(new DetalletrabajotercerizadoPK());
        }
        detalletrabajotercerizado.getDetalletrabajotercerizadoPK().setIdtrabajotercerizado(detalletrabajotercerizado.getTrabajotercerizado().getIdtrabajo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodetalletrabajotercerizado estado = detalletrabajotercerizado.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                detalletrabajotercerizado.setEstado(estado);
            }
            Etapadeproduccion proceso = detalletrabajotercerizado.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso(proceso);
            }
            Trabajotercerizado trabajotercerizado = detalletrabajotercerizado.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado = em.getReference(trabajotercerizado.getClass(), trabajotercerizado.getIdtrabajo());
                detalletrabajotercerizado.setTrabajotercerizado(trabajotercerizado);
            }
            em.persist(detalletrabajotercerizado);
            if (estado != null) {
                estado.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                estado = em.merge(estado);
            }
            if (proceso != null) {
                proceso.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                proceso = em.merge(proceso);
            }
            if (trabajotercerizado != null) {
                trabajotercerizado.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalletrabajotercerizado(detalletrabajotercerizado.getDetalletrabajotercerizadoPK()) != null) {
                throw new PreexistingEntityException("Detalletrabajotercerizado " + detalletrabajotercerizado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalletrabajotercerizado detalletrabajotercerizado) throws NonexistentEntityException, Exception {
        detalletrabajotercerizado.getDetalletrabajotercerizadoPK().setIdtrabajotercerizado(detalletrabajotercerizado.getTrabajotercerizado().getIdtrabajo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletrabajotercerizado persistentDetalletrabajotercerizado = em.find(Detalletrabajotercerizado.class, detalletrabajotercerizado.getDetalletrabajotercerizadoPK());
            Estadodetalletrabajotercerizado estadoOld = persistentDetalletrabajotercerizado.getEstado();
            Estadodetalletrabajotercerizado estadoNew = detalletrabajotercerizado.getEstado();
            Etapadeproduccion procesoOld = persistentDetalletrabajotercerizado.getProceso();
            Etapadeproduccion procesoNew = detalletrabajotercerizado.getProceso();
            Trabajotercerizado trabajotercerizadoOld = persistentDetalletrabajotercerizado.getTrabajotercerizado();
            Trabajotercerizado trabajotercerizadoNew = detalletrabajotercerizado.getTrabajotercerizado();
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                detalletrabajotercerizado.setEstado(estadoNew);
            }
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso(procesoNew);
            }
            if (trabajotercerizadoNew != null) {
                trabajotercerizadoNew = em.getReference(trabajotercerizadoNew.getClass(), trabajotercerizadoNew.getIdtrabajo());
                detalletrabajotercerizado.setTrabajotercerizado(trabajotercerizadoNew);
            }
            detalletrabajotercerizado = em.merge(detalletrabajotercerizado);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                estadoNew = em.merge(estadoNew);
            }
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                procesoNew = em.merge(procesoNew);
            }
            if (trabajotercerizadoOld != null && !trabajotercerizadoOld.equals(trabajotercerizadoNew)) {
                trabajotercerizadoOld.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                trabajotercerizadoOld = em.merge(trabajotercerizadoOld);
            }
            if (trabajotercerizadoNew != null && !trabajotercerizadoNew.equals(trabajotercerizadoOld)) {
                trabajotercerizadoNew.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                trabajotercerizadoNew = em.merge(trabajotercerizadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalletrabajotercerizadoPK id = detalletrabajotercerizado.getDetalletrabajotercerizadoPK();
                if (findDetalletrabajotercerizado(id) == null) {
                    throw new NonexistentEntityException("The detalletrabajotercerizado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalletrabajotercerizadoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletrabajotercerizado detalletrabajotercerizado;
            try {
                detalletrabajotercerizado = em.getReference(Detalletrabajotercerizado.class, id);
                detalletrabajotercerizado.getDetalletrabajotercerizadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalletrabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            Estadodetalletrabajotercerizado estado = detalletrabajotercerizado.getEstado();
            if (estado != null) {
                estado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                estado = em.merge(estado);
            }
            Etapadeproduccion proceso = detalletrabajotercerizado.getProceso();
            if (proceso != null) {
                proceso.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                proceso = em.merge(proceso);
            }
            Trabajotercerizado trabajotercerizado = detalletrabajotercerizado.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            em.remove(detalletrabajotercerizado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalletrabajotercerizado> findDetalletrabajotercerizadoEntities() {
        return findDetalletrabajotercerizadoEntities(true, -1, -1);
    }

    public List<Detalletrabajotercerizado> findDetalletrabajotercerizadoEntities(int maxResults, int firstResult) {
        return findDetalletrabajotercerizadoEntities(false, maxResults, firstResult);
    }

    private List<Detalletrabajotercerizado> findDetalletrabajotercerizadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalletrabajotercerizado.class));
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

    public Detalletrabajotercerizado findDetalletrabajotercerizado(DetalletrabajotercerizadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalletrabajotercerizado.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalletrabajotercerizadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalletrabajotercerizado> rt = cq.from(Detalletrabajotercerizado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
