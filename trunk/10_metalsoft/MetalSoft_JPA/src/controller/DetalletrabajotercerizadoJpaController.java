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
        detalletrabajotercerizado.getDetalletrabajotercerizadoPK().setIdtrabajotercerizado(detalletrabajotercerizado.getTrabajotercerizado1().getIdtrabajo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodetalletrabajotercerizado estado = detalletrabajotercerizado.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                detalletrabajotercerizado.setEstado(estado);
            }
            Estadodetalletrabajotercerizado estado1 = detalletrabajotercerizado.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                detalletrabajotercerizado.setEstado1(estado1);
            }
            Etapadeproduccion proceso = detalletrabajotercerizado.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso(proceso);
            }
            Etapadeproduccion proceso1 = detalletrabajotercerizado.getProceso1();
            if (proceso1 != null) {
                proceso1 = em.getReference(proceso1.getClass(), proceso1.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso1(proceso1);
            }
            Trabajotercerizado trabajotercerizado = detalletrabajotercerizado.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado = em.getReference(trabajotercerizado.getClass(), trabajotercerizado.getIdtrabajo());
                detalletrabajotercerizado.setTrabajotercerizado(trabajotercerizado);
            }
            Trabajotercerizado trabajotercerizado1 = detalletrabajotercerizado.getTrabajotercerizado1();
            if (trabajotercerizado1 != null) {
                trabajotercerizado1 = em.getReference(trabajotercerizado1.getClass(), trabajotercerizado1.getIdtrabajo());
                detalletrabajotercerizado.setTrabajotercerizado1(trabajotercerizado1);
            }
            em.persist(detalletrabajotercerizado);
            if (estado != null) {
                estado.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                estado1 = em.merge(estado1);
            }
            if (proceso != null) {
                proceso.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                proceso = em.merge(proceso);
            }
            if (proceso1 != null) {
                proceso1.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                proceso1 = em.merge(proceso1);
            }
            if (trabajotercerizado != null) {
                trabajotercerizado.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            if (trabajotercerizado1 != null) {
                trabajotercerizado1.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                trabajotercerizado1 = em.merge(trabajotercerizado1);
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
        detalletrabajotercerizado.getDetalletrabajotercerizadoPK().setIdtrabajotercerizado(detalletrabajotercerizado.getTrabajotercerizado1().getIdtrabajo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletrabajotercerizado persistentDetalletrabajotercerizado = em.find(Detalletrabajotercerizado.class, detalletrabajotercerizado.getDetalletrabajotercerizadoPK());
            Estadodetalletrabajotercerizado estadoOld = persistentDetalletrabajotercerizado.getEstado();
            Estadodetalletrabajotercerizado estadoNew = detalletrabajotercerizado.getEstado();
            Estadodetalletrabajotercerizado estado1Old = persistentDetalletrabajotercerizado.getEstado1();
            Estadodetalletrabajotercerizado estado1New = detalletrabajotercerizado.getEstado1();
            Etapadeproduccion procesoOld = persistentDetalletrabajotercerizado.getProceso();
            Etapadeproduccion procesoNew = detalletrabajotercerizado.getProceso();
            Etapadeproduccion proceso1Old = persistentDetalletrabajotercerizado.getProceso1();
            Etapadeproduccion proceso1New = detalletrabajotercerizado.getProceso1();
            Trabajotercerizado trabajotercerizadoOld = persistentDetalletrabajotercerizado.getTrabajotercerizado();
            Trabajotercerizado trabajotercerizadoNew = detalletrabajotercerizado.getTrabajotercerizado();
            Trabajotercerizado trabajotercerizado1Old = persistentDetalletrabajotercerizado.getTrabajotercerizado1();
            Trabajotercerizado trabajotercerizado1New = detalletrabajotercerizado.getTrabajotercerizado1();
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                detalletrabajotercerizado.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                detalletrabajotercerizado.setEstado1(estado1New);
            }
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso(procesoNew);
            }
            if (proceso1New != null) {
                proceso1New = em.getReference(proceso1New.getClass(), proceso1New.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso1(proceso1New);
            }
            if (trabajotercerizadoNew != null) {
                trabajotercerizadoNew = em.getReference(trabajotercerizadoNew.getClass(), trabajotercerizadoNew.getIdtrabajo());
                detalletrabajotercerizado.setTrabajotercerizado(trabajotercerizadoNew);
            }
            if (trabajotercerizado1New != null) {
                trabajotercerizado1New = em.getReference(trabajotercerizado1New.getClass(), trabajotercerizado1New.getIdtrabajo());
                detalletrabajotercerizado.setTrabajotercerizado1(trabajotercerizado1New);
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
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                estado1New = em.merge(estado1New);
            }
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                procesoNew = em.merge(procesoNew);
            }
            if (proceso1Old != null && !proceso1Old.equals(proceso1New)) {
                proceso1Old.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                proceso1Old = em.merge(proceso1Old);
            }
            if (proceso1New != null && !proceso1New.equals(proceso1Old)) {
                proceso1New.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                proceso1New = em.merge(proceso1New);
            }
            if (trabajotercerizadoOld != null && !trabajotercerizadoOld.equals(trabajotercerizadoNew)) {
                trabajotercerizadoOld.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                trabajotercerizadoOld = em.merge(trabajotercerizadoOld);
            }
            if (trabajotercerizadoNew != null && !trabajotercerizadoNew.equals(trabajotercerizadoOld)) {
                trabajotercerizadoNew.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                trabajotercerizadoNew = em.merge(trabajotercerizadoNew);
            }
            if (trabajotercerizado1Old != null && !trabajotercerizado1Old.equals(trabajotercerizado1New)) {
                trabajotercerizado1Old.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                trabajotercerizado1Old = em.merge(trabajotercerizado1Old);
            }
            if (trabajotercerizado1New != null && !trabajotercerizado1New.equals(trabajotercerizado1Old)) {
                trabajotercerizado1New.getDetalletrabajotercerizadoSet().add(detalletrabajotercerizado);
                trabajotercerizado1New = em.merge(trabajotercerizado1New);
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
            Estadodetalletrabajotercerizado estado1 = detalletrabajotercerizado.getEstado1();
            if (estado1 != null) {
                estado1.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                estado1 = em.merge(estado1);
            }
            Etapadeproduccion proceso = detalletrabajotercerizado.getProceso();
            if (proceso != null) {
                proceso.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                proceso = em.merge(proceso);
            }
            Etapadeproduccion proceso1 = detalletrabajotercerizado.getProceso1();
            if (proceso1 != null) {
                proceso1.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                proceso1 = em.merge(proceso1);
            }
            Trabajotercerizado trabajotercerizado = detalletrabajotercerizado.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            Trabajotercerizado trabajotercerizado1 = detalletrabajotercerizado.getTrabajotercerizado1();
            if (trabajotercerizado1 != null) {
                trabajotercerizado1.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizado);
                trabajotercerizado1 = em.merge(trabajotercerizado1);
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
