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
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Estadodetalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Detallereclamoempresamantenimiento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class DetalletrabajotercerizadoJpaController implements Serializable {

    public DetalletrabajotercerizadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalletrabajotercerizado detalletrabajotercerizado) throws PreexistingEntityException, Exception {
        if (detalletrabajotercerizado.getDetallereclamoempresamantenimientoList() == null) {
            detalletrabajotercerizado.setDetallereclamoempresamantenimientoList(new ArrayList<Detallereclamoempresamantenimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajotercerizado idtrabajotercerizado = detalletrabajotercerizado.getIdtrabajotercerizado();
            if (idtrabajotercerizado != null) {
                idtrabajotercerizado = em.getReference(idtrabajotercerizado.getClass(), idtrabajotercerizado.getIdtrabajo());
                detalletrabajotercerizado.setIdtrabajotercerizado(idtrabajotercerizado);
            }
            Etapadeproduccion proceso = detalletrabajotercerizado.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso(proceso);
            }
            Estadodetalletrabajotercerizado estado = detalletrabajotercerizado.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                detalletrabajotercerizado.setEstado(estado);
            }
            List<Detallereclamoempresamantenimiento> attachedDetallereclamoempresamantenimientoList = new ArrayList<Detallereclamoempresamantenimiento>();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach : detalletrabajotercerizado.getDetallereclamoempresamantenimientoList()) {
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach = em.getReference(detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach.getClass(), detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach.getIddetalle());
                attachedDetallereclamoempresamantenimientoList.add(detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach);
            }
            detalletrabajotercerizado.setDetallereclamoempresamantenimientoList(attachedDetallereclamoempresamantenimientoList);
            em.persist(detalletrabajotercerizado);
            if (idtrabajotercerizado != null) {
                idtrabajotercerizado.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                idtrabajotercerizado = em.merge(idtrabajotercerizado);
            }
            if (proceso != null) {
                proceso.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                proceso = em.merge(proceso);
            }
            if (estado != null) {
                estado.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                estado = em.merge(estado);
            }
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento : detalletrabajotercerizado.getDetallereclamoempresamantenimientoList()) {
                Detalletrabajotercerizado oldIddetalletrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.getIddetalletrabajo();
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.setIddetalletrabajo(detalletrabajotercerizado);
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                if (oldIddetalletrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento != null) {
                    oldIddetalletrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                    oldIddetalletrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = em.merge(oldIddetalletrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalletrabajotercerizado(detalletrabajotercerizado.getIddetalle()) != null) {
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletrabajotercerizado persistentDetalletrabajotercerizado = em.find(Detalletrabajotercerizado.class, detalletrabajotercerizado.getIddetalle());
            Trabajotercerizado idtrabajotercerizadoOld = persistentDetalletrabajotercerizado.getIdtrabajotercerizado();
            Trabajotercerizado idtrabajotercerizadoNew = detalletrabajotercerizado.getIdtrabajotercerizado();
            Etapadeproduccion procesoOld = persistentDetalletrabajotercerizado.getProceso();
            Etapadeproduccion procesoNew = detalletrabajotercerizado.getProceso();
            Estadodetalletrabajotercerizado estadoOld = persistentDetalletrabajotercerizado.getEstado();
            Estadodetalletrabajotercerizado estadoNew = detalletrabajotercerizado.getEstado();
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoListOld = persistentDetalletrabajotercerizado.getDetallereclamoempresamantenimientoList();
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoListNew = detalletrabajotercerizado.getDetallereclamoempresamantenimientoList();
            if (idtrabajotercerizadoNew != null) {
                idtrabajotercerizadoNew = em.getReference(idtrabajotercerizadoNew.getClass(), idtrabajotercerizadoNew.getIdtrabajo());
                detalletrabajotercerizado.setIdtrabajotercerizado(idtrabajotercerizadoNew);
            }
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso(procesoNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                detalletrabajotercerizado.setEstado(estadoNew);
            }
            List<Detallereclamoempresamantenimiento> attachedDetallereclamoempresamantenimientoListNew = new ArrayList<Detallereclamoempresamantenimiento>();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach : detallereclamoempresamantenimientoListNew) {
                detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach = em.getReference(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach.getClass(), detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach.getIddetalle());
                attachedDetallereclamoempresamantenimientoListNew.add(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach);
            }
            detallereclamoempresamantenimientoListNew = attachedDetallereclamoempresamantenimientoListNew;
            detalletrabajotercerizado.setDetallereclamoempresamantenimientoList(detallereclamoempresamantenimientoListNew);
            detalletrabajotercerizado = em.merge(detalletrabajotercerizado);
            if (idtrabajotercerizadoOld != null && !idtrabajotercerizadoOld.equals(idtrabajotercerizadoNew)) {
                idtrabajotercerizadoOld.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                idtrabajotercerizadoOld = em.merge(idtrabajotercerizadoOld);
            }
            if (idtrabajotercerizadoNew != null && !idtrabajotercerizadoNew.equals(idtrabajotercerizadoOld)) {
                idtrabajotercerizadoNew.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                idtrabajotercerizadoNew = em.merge(idtrabajotercerizadoNew);
            }
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                procesoNew = em.merge(procesoNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                estadoNew = em.merge(estadoNew);
            }
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoListOld) {
                if (!detallereclamoempresamantenimientoListNew.contains(detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento)) {
                    detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento.setIddetalletrabajo(null);
                    detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento);
                }
            }
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoListNew) {
                if (!detallereclamoempresamantenimientoListOld.contains(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento)) {
                    Detalletrabajotercerizado oldIddetalletrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.getIddetalletrabajo();
                    detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.setIddetalletrabajo(detalletrabajotercerizado);
                    detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                    if (oldIddetalletrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento != null && !oldIddetalletrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.equals(detalletrabajotercerizado)) {
                        oldIddetalletrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                        oldIddetalletrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = em.merge(oldIddetalletrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalletrabajotercerizado.getIddetalle();
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

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletrabajotercerizado detalletrabajotercerizado;
            try {
                detalletrabajotercerizado = em.getReference(Detalletrabajotercerizado.class, id);
                detalletrabajotercerizado.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalletrabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            Trabajotercerizado idtrabajotercerizado = detalletrabajotercerizado.getIdtrabajotercerizado();
            if (idtrabajotercerizado != null) {
                idtrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                idtrabajotercerizado = em.merge(idtrabajotercerizado);
            }
            Etapadeproduccion proceso = detalletrabajotercerizado.getProceso();
            if (proceso != null) {
                proceso.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                proceso = em.merge(proceso);
            }
            Estadodetalletrabajotercerizado estado = detalletrabajotercerizado.getEstado();
            if (estado != null) {
                estado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                estado = em.merge(estado);
            }
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoList = detalletrabajotercerizado.getDetallereclamoempresamantenimientoList();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoList) {
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.setIddetalletrabajo(null);
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
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

    public Detalletrabajotercerizado findDetalletrabajotercerizado(Long id) {
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
