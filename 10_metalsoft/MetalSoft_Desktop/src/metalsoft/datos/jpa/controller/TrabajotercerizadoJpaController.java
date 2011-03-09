/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Estadotrabajotercerizado;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Reclamoempresametalurgica;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Trabajotercerizado;

/**
 *
 * @author Nino
 */
public class TrabajotercerizadoJpaController {

    public TrabajotercerizadoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabajotercerizado trabajotercerizado) throws PreexistingEntityException, Exception {
        if (trabajotercerizado.getReclamoempresametalurgicaList() == null) {
            trabajotercerizado.setReclamoempresametalurgicaList(new ArrayList<Reclamoempresametalurgica>());
        }
        if (trabajotercerizado.getDetalletrabajotercerizadoList() == null) {
            trabajotercerizado.setDetalletrabajotercerizadoList(new ArrayList<Detalletrabajotercerizado>());
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
            List<Reclamoempresametalurgica> attachedReclamoempresametalurgicaList = new ArrayList<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgicaToAttach : trabajotercerizado.getReclamoempresametalurgicaList()) {
                reclamoempresametalurgicaListReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaListReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaListReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaList.add(reclamoempresametalurgicaListReclamoempresametalurgicaToAttach);
            }
            trabajotercerizado.setReclamoempresametalurgicaList(attachedReclamoempresametalurgicaList);
            List<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoList = new ArrayList<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach : trabajotercerizado.getDetalletrabajotercerizadoList()) {
                detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoList.add(detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach);
            }
            trabajotercerizado.setDetalletrabajotercerizadoList(attachedDetalletrabajotercerizadoList);
            em.persist(trabajotercerizado);
            if (empresa != null) {
                empresa.getTrabajotercerizadoList().add(trabajotercerizado);
                empresa = em.merge(empresa);
            }
            if (estado != null) {
                estado.getTrabajotercerizadoList().add(trabajotercerizado);
                estado = em.merge(estado);
            }
            if (pedido != null) {
                pedido.getTrabajotercerizadoList().add(trabajotercerizado);
                pedido = em.merge(pedido);
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgica : trabajotercerizado.getReclamoempresametalurgicaList()) {
                Trabajotercerizado oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica = reclamoempresametalurgicaListReclamoempresametalurgica.getTrabajotercerizado();
                reclamoempresametalurgicaListReclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
                reclamoempresametalurgicaListReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListReclamoempresametalurgica);
                if (oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica != null) {
                    oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica.getReclamoempresametalurgicaList().remove(reclamoempresametalurgicaListReclamoempresametalurgica);
                    oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica = em.merge(oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizado : trabajotercerizado.getDetalletrabajotercerizadoList()) {
                Trabajotercerizado oldTrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado = detalletrabajotercerizadoListDetalletrabajotercerizado.getTrabajotercerizado();
                detalletrabajotercerizadoListDetalletrabajotercerizado.setTrabajotercerizado(trabajotercerizado);
                detalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListDetalletrabajotercerizado);
                if (oldTrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado != null) {
                    oldTrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizadoListDetalletrabajotercerizado);
                    oldTrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(oldTrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado);
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
            List<Reclamoempresametalurgica> reclamoempresametalurgicaListOld = persistentTrabajotercerizado.getReclamoempresametalurgicaList();
            List<Reclamoempresametalurgica> reclamoempresametalurgicaListNew = trabajotercerizado.getReclamoempresametalurgicaList();
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListOld = persistentTrabajotercerizado.getDetalletrabajotercerizadoList();
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListNew = trabajotercerizado.getDetalletrabajotercerizadoList();
            List<String> illegalOrphanMessages = null;
            for (Detalletrabajotercerizado detalletrabajotercerizadoListOldDetalletrabajotercerizado : detalletrabajotercerizadoListOld) {
                if (!detalletrabajotercerizadoListNew.contains(detalletrabajotercerizadoListOldDetalletrabajotercerizado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalletrabajotercerizado " + detalletrabajotercerizadoListOldDetalletrabajotercerizado + " since its trabajotercerizado field is not nullable.");
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
            List<Reclamoempresametalurgica> attachedReclamoempresametalurgicaListNew = new ArrayList<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaListNew) {
                reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaListNew.add(reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaListNew = attachedReclamoempresametalurgicaListNew;
            trabajotercerizado.setReclamoempresametalurgicaList(reclamoempresametalurgicaListNew);
            List<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoListNew = new ArrayList<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoListNew) {
                detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoListNew.add(detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoListNew = attachedDetalletrabajotercerizadoListNew;
            trabajotercerizado.setDetalletrabajotercerizadoList(detalletrabajotercerizadoListNew);
            trabajotercerizado = em.merge(trabajotercerizado);
            if (empresaOld != null && !empresaOld.equals(empresaNew)) {
                empresaOld.getTrabajotercerizadoList().remove(trabajotercerizado);
                empresaOld = em.merge(empresaOld);
            }
            if (empresaNew != null && !empresaNew.equals(empresaOld)) {
                empresaNew.getTrabajotercerizadoList().add(trabajotercerizado);
                empresaNew = em.merge(empresaNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getTrabajotercerizadoList().remove(trabajotercerizado);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getTrabajotercerizadoList().add(trabajotercerizado);
                estadoNew = em.merge(estadoNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getTrabajotercerizadoList().remove(trabajotercerizado);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getTrabajotercerizadoList().add(trabajotercerizado);
                pedidoNew = em.merge(pedidoNew);
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListOldReclamoempresametalurgica : reclamoempresametalurgicaListOld) {
                if (!reclamoempresametalurgicaListNew.contains(reclamoempresametalurgicaListOldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaListOldReclamoempresametalurgica.setTrabajotercerizado(null);
                    reclamoempresametalurgicaListOldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListOldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListNewReclamoempresametalurgica : reclamoempresametalurgicaListNew) {
                if (!reclamoempresametalurgicaListOld.contains(reclamoempresametalurgicaListNewReclamoempresametalurgica)) {
                    Trabajotercerizado oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica = reclamoempresametalurgicaListNewReclamoempresametalurgica.getTrabajotercerizado();
                    reclamoempresametalurgicaListNewReclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
                    reclamoempresametalurgicaListNewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListNewReclamoempresametalurgica);
                    if (oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica != null && !oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica.equals(trabajotercerizado)) {
                        oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica.getReclamoempresametalurgicaList().remove(reclamoempresametalurgicaListNewReclamoempresametalurgica);
                        oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica = em.merge(oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica);
                    }
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoListNewDetalletrabajotercerizado : detalletrabajotercerizadoListNew) {
                if (!detalletrabajotercerizadoListOld.contains(detalletrabajotercerizadoListNewDetalletrabajotercerizado)) {
                    Trabajotercerizado oldTrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado = detalletrabajotercerizadoListNewDetalletrabajotercerizado.getTrabajotercerizado();
                    detalletrabajotercerizadoListNewDetalletrabajotercerizado.setTrabajotercerizado(trabajotercerizado);
                    detalletrabajotercerizadoListNewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListNewDetalletrabajotercerizado);
                    if (oldTrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado != null && !oldTrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado.equals(trabajotercerizado)) {
                        oldTrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizadoListNewDetalletrabajotercerizado);
                        oldTrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado = em.merge(oldTrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado);
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
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListOrphanCheck = trabajotercerizado.getDetalletrabajotercerizadoList();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListOrphanCheckDetalletrabajotercerizado : detalletrabajotercerizadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Trabajotercerizado (" + trabajotercerizado + ") cannot be destroyed since the Detalletrabajotercerizado " + detalletrabajotercerizadoListOrphanCheckDetalletrabajotercerizado + " in its detalletrabajotercerizadoList field has a non-nullable trabajotercerizado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresametalurgica empresa = trabajotercerizado.getEmpresa();
            if (empresa != null) {
                empresa.getTrabajotercerizadoList().remove(trabajotercerizado);
                empresa = em.merge(empresa);
            }
            Estadotrabajotercerizado estado = trabajotercerizado.getEstado();
            if (estado != null) {
                estado.getTrabajotercerizadoList().remove(trabajotercerizado);
                estado = em.merge(estado);
            }
            Pedido pedido = trabajotercerizado.getPedido();
            if (pedido != null) {
                pedido.getTrabajotercerizadoList().remove(trabajotercerizado);
                pedido = em.merge(pedido);
            }
            List<Reclamoempresametalurgica> reclamoempresametalurgicaList = trabajotercerizado.getReclamoempresametalurgicaList();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgica : reclamoempresametalurgicaList) {
                reclamoempresametalurgicaListReclamoempresametalurgica.setTrabajotercerizado(null);
                reclamoempresametalurgicaListReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListReclamoempresametalurgica);
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
