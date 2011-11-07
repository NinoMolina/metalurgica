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
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Estadopedido;
import metalsoft.datos.jpa.entity.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadopedidoJpaController implements Serializable {

    public EstadopedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadopedido estadopedido) throws PreexistingEntityException, Exception {
        if (estadopedido.getPedidoList() == null) {
            estadopedido.setPedidoList(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : estadopedido.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            estadopedido.setPedidoList(attachedPedidoList);
            em.persist(estadopedido);
            for (Pedido pedidoListPedido : estadopedido.getPedidoList()) {
                Estadopedido oldEstadoOfPedidoListPedido = pedidoListPedido.getEstado();
                pedidoListPedido.setEstado(estadopedido);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldEstadoOfPedidoListPedido != null) {
                    oldEstadoOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldEstadoOfPedidoListPedido = em.merge(oldEstadoOfPedidoListPedido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadopedido(estadopedido.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadopedido " + estadopedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadopedido estadopedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopedido persistentEstadopedido = em.find(Estadopedido.class, estadopedido.getIdestado());
            List<Pedido> pedidoListOld = persistentEstadopedido.getPedidoList();
            List<Pedido> pedidoListNew = estadopedido.getPedidoList();
            List<String> illegalOrphanMessages = null;
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoListOldPedido + " since its estado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdpedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            estadopedido.setPedidoList(pedidoListNew);
            estadopedido = em.merge(estadopedido);
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Estadopedido oldEstadoOfPedidoListNewPedido = pedidoListNewPedido.getEstado();
                    pedidoListNewPedido.setEstado(estadopedido);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldEstadoOfPedidoListNewPedido != null && !oldEstadoOfPedidoListNewPedido.equals(estadopedido)) {
                        oldEstadoOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldEstadoOfPedidoListNewPedido = em.merge(oldEstadoOfPedidoListNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadopedido.getIdestado();
                if (findEstadopedido(id) == null) {
                    throw new NonexistentEntityException("The estadopedido with id " + id + " no longer exists.");
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
            Estadopedido estadopedido;
            try {
                estadopedido = em.getReference(Estadopedido.class, id);
                estadopedido.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadopedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pedido> pedidoListOrphanCheck = estadopedido.getPedidoList();
            for (Pedido pedidoListOrphanCheckPedido : pedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estadopedido (" + estadopedido + ") cannot be destroyed since the Pedido " + pedidoListOrphanCheckPedido + " in its pedidoList field has a non-nullable estado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadopedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadopedido> findEstadopedidoEntities() {
        return findEstadopedidoEntities(true, -1, -1);
    }

    public List<Estadopedido> findEstadopedidoEntities(int maxResults, int firstResult) {
        return findEstadopedidoEntities(false, maxResults, firstResult);
    }

    private List<Estadopedido> findEstadopedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadopedido.class));
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

    public Estadopedido findEstadopedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadopedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadopedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadopedido> rt = cq.from(Estadopedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
