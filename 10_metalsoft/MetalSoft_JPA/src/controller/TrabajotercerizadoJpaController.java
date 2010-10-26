/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Trabajotercerizado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empresametalurgica;
import entity.Estadotrabajotercerizado;
import entity.Pedido;
import entity.Reclamoempresametalurgica;
import java.util.HashSet;
import java.util.Set;
import entity.Detalletrabajotercerizado;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class TrabajotercerizadoJpaController {

    public TrabajotercerizadoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabajotercerizado trabajotercerizado) throws PreexistingEntityException, Exception {
        if (trabajotercerizado.getReclamoempresametalurgicaSet() == null) {
            trabajotercerizado.setReclamoempresametalurgicaSet(new HashSet<Reclamoempresametalurgica>());
        }
        if (trabajotercerizado.getDetalletrabajotercerizadoSet() == null) {
            trabajotercerizado.setDetalletrabajotercerizadoSet(new HashSet<Detalletrabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresametalurgica empresa = trabajotercerizado.getEmpresa();
            if (empresa != null) {
                empresa = em.getReference(empresa.getClass(), empresa.getIdempresametalurgica());
                trabajotercerizado.setEmpresa(empresa);
            }
            Estadotrabajotercerizado estado = trabajotercerizado.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                trabajotercerizado.setEstado(estado);
            }
            Pedido pedido = trabajotercerizado.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                trabajotercerizado.setPedido(pedido);
            }
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSet = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach : trabajotercerizado.getReclamoempresametalurgicaSet()) {
                reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSet.add(reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach);
            }
            trabajotercerizado.setReclamoempresametalurgicaSet(attachedReclamoempresametalurgicaSet);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach : trabajotercerizado.getDetalletrabajotercerizadoSet()) {
                detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet.add(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach);
            }
            trabajotercerizado.setDetalletrabajotercerizadoSet(attachedDetalletrabajotercerizadoSet);
            em.persist(trabajotercerizado);
            if (empresa != null) {
                empresa.getTrabajotercerizadoSet().add(trabajotercerizado);
                empresa = em.merge(empresa);
            }
            if (estado != null) {
                estado.getTrabajotercerizadoSet().add(trabajotercerizado);
                estado = em.merge(estado);
            }
            if (pedido != null) {
                pedido.getTrabajotercerizadoSet().add(trabajotercerizado);
                pedido = em.merge(pedido);
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgica : trabajotercerizado.getReclamoempresametalurgicaSet()) {
                Trabajotercerizado oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica = reclamoempresametalurgicaSetReclamoempresametalurgica.getTrabajotercerizado();
                reclamoempresametalurgicaSetReclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
                reclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetReclamoempresametalurgica);
                if (oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica != null) {
                    oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgicaSetReclamoempresametalurgica);
                    oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizado : trabajotercerizado.getDetalletrabajotercerizadoSet()) {
                Trabajotercerizado oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado = detalletrabajotercerizadoSetDetalletrabajotercerizado.getTrabajotercerizado();
                detalletrabajotercerizadoSetDetalletrabajotercerizado.setTrabajotercerizado(trabajotercerizado);
                detalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetDetalletrabajotercerizado);
                if (oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado != null) {
                    oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizadoSetDetalletrabajotercerizado);
                    oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTrabajotercerizado(trabajotercerizado.getIdtrabajo()) != null) {
                throw new PreexistingEntityException("Trabajotercerizado " + trabajotercerizado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trabajotercerizado trabajotercerizado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajotercerizado persistentTrabajotercerizado = em.find(Trabajotercerizado.class, trabajotercerizado.getIdtrabajo());
            Empresametalurgica empresaOld = persistentTrabajotercerizado.getEmpresa();
            Empresametalurgica empresaNew = trabajotercerizado.getEmpresa();
            Estadotrabajotercerizado estadoOld = persistentTrabajotercerizado.getEstado();
            Estadotrabajotercerizado estadoNew = trabajotercerizado.getEstado();
            Pedido pedidoOld = persistentTrabajotercerizado.getPedido();
            Pedido pedidoNew = trabajotercerizado.getPedido();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSetOld = persistentTrabajotercerizado.getReclamoempresametalurgicaSet();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSetNew = trabajotercerizado.getReclamoempresametalurgicaSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetOld = persistentTrabajotercerizado.getDetalletrabajotercerizadoSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetNew = trabajotercerizado.getDetalletrabajotercerizadoSet();
            List<String> illegalOrphanMessages = null;
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetOldDetalletrabajotercerizado : detalletrabajotercerizadoSetOld) {
                if (!detalletrabajotercerizadoSetNew.contains(detalletrabajotercerizadoSetOldDetalletrabajotercerizado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalletrabajotercerizado " + detalletrabajotercerizadoSetOldDetalletrabajotercerizado + " since its trabajotercerizado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empresaNew != null) {
                empresaNew = em.getReference(empresaNew.getClass(), empresaNew.getIdempresametalurgica());
                trabajotercerizado.setEmpresa(empresaNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                trabajotercerizado.setEstado(estadoNew);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                trabajotercerizado.setPedido(pedidoNew);
            }
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSetNew = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaSetNew) {
                reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSetNew.add(reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaSetNew = attachedReclamoempresametalurgicaSetNew;
            trabajotercerizado.setReclamoempresametalurgicaSet(reclamoempresametalurgicaSetNew);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSetNew = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoSetNew) {
                detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSetNew.add(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoSetNew = attachedDetalletrabajotercerizadoSetNew;
            trabajotercerizado.setDetalletrabajotercerizadoSet(detalletrabajotercerizadoSetNew);
            trabajotercerizado = em.merge(trabajotercerizado);
            if (empresaOld != null && !empresaOld.equals(empresaNew)) {
                empresaOld.getTrabajotercerizadoSet().remove(trabajotercerizado);
                empresaOld = em.merge(empresaOld);
            }
            if (empresaNew != null && !empresaNew.equals(empresaOld)) {
                empresaNew.getTrabajotercerizadoSet().add(trabajotercerizado);
                empresaNew = em.merge(empresaNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getTrabajotercerizadoSet().remove(trabajotercerizado);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getTrabajotercerizadoSet().add(trabajotercerizado);
                estadoNew = em.merge(estadoNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getTrabajotercerizadoSet().remove(trabajotercerizado);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getTrabajotercerizadoSet().add(trabajotercerizado);
                pedidoNew = em.merge(pedidoNew);
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetOldReclamoempresametalurgica : reclamoempresametalurgicaSetOld) {
                if (!reclamoempresametalurgicaSetNew.contains(reclamoempresametalurgicaSetOldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaSetOldReclamoempresametalurgica.setTrabajotercerizado(null);
                    reclamoempresametalurgicaSetOldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetOldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetNewReclamoempresametalurgica : reclamoempresametalurgicaSetNew) {
                if (!reclamoempresametalurgicaSetOld.contains(reclamoempresametalurgicaSetNewReclamoempresametalurgica)) {
                    Trabajotercerizado oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica = reclamoempresametalurgicaSetNewReclamoempresametalurgica.getTrabajotercerizado();
                    reclamoempresametalurgicaSetNewReclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
                    reclamoempresametalurgicaSetNewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetNewReclamoempresametalurgica);
                    if (oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica != null && !oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica.equals(trabajotercerizado)) {
                        oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgicaSetNewReclamoempresametalurgica);
                        oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica = em.merge(oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica);
                    }
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizado : detalletrabajotercerizadoSetNew) {
                if (!detalletrabajotercerizadoSetOld.contains(detalletrabajotercerizadoSetNewDetalletrabajotercerizado)) {
                    Trabajotercerizado oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado = detalletrabajotercerizadoSetNewDetalletrabajotercerizado.getTrabajotercerizado();
                    detalletrabajotercerizadoSetNewDetalletrabajotercerizado.setTrabajotercerizado(trabajotercerizado);
                    detalletrabajotercerizadoSetNewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                    if (oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado != null && !oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado.equals(trabajotercerizado)) {
                        oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                        oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado = em.merge(oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = trabajotercerizado.getIdtrabajo();
                if (findTrabajotercerizado(id) == null) {
                    throw new NonexistentEntityException("The trabajotercerizado with id " + id + " no longer exists.");
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
            Trabajotercerizado trabajotercerizado;
            try {
                trabajotercerizado = em.getReference(Trabajotercerizado.class, id);
                trabajotercerizado.getIdtrabajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetOrphanCheck = trabajotercerizado.getDetalletrabajotercerizadoSet();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetOrphanCheckDetalletrabajotercerizado : detalletrabajotercerizadoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Trabajotercerizado (" + trabajotercerizado + ") cannot be destroyed since the Detalletrabajotercerizado " + detalletrabajotercerizadoSetOrphanCheckDetalletrabajotercerizado + " in its detalletrabajotercerizadoSet field has a non-nullable trabajotercerizado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresametalurgica empresa = trabajotercerizado.getEmpresa();
            if (empresa != null) {
                empresa.getTrabajotercerizadoSet().remove(trabajotercerizado);
                empresa = em.merge(empresa);
            }
            Estadotrabajotercerizado estado = trabajotercerizado.getEstado();
            if (estado != null) {
                estado.getTrabajotercerizadoSet().remove(trabajotercerizado);
                estado = em.merge(estado);
            }
            Pedido pedido = trabajotercerizado.getPedido();
            if (pedido != null) {
                pedido.getTrabajotercerizadoSet().remove(trabajotercerizado);
                pedido = em.merge(pedido);
            }
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet = trabajotercerizado.getReclamoempresametalurgicaSet();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgica : reclamoempresametalurgicaSet) {
                reclamoempresametalurgicaSetReclamoempresametalurgica.setTrabajotercerizado(null);
                reclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetReclamoempresametalurgica);
            }
            em.remove(trabajotercerizado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trabajotercerizado> findTrabajotercerizadoEntities() {
        return findTrabajotercerizadoEntities(true, -1, -1);
    }

    public List<Trabajotercerizado> findTrabajotercerizadoEntities(int maxResults, int firstResult) {
        return findTrabajotercerizadoEntities(false, maxResults, firstResult);
    }

    private List<Trabajotercerizado> findTrabajotercerizadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabajotercerizado.class));
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

    public Trabajotercerizado findTrabajotercerizado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabajotercerizado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabajotercerizadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabajotercerizado> rt = cq.from(Trabajotercerizado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
