/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Plano;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Pedido;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class PlanoJpaController {

    public PlanoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Plano plano) throws PreexistingEntityException, Exception {
        if (plano.getPedidoSet() == null) {
            plano.setPedidoSet(new HashSet<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = plano.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                plano.setPedido(pedido);
            }
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : plano.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            plano.setPedidoSet(attachedPedidoSet);
            em.persist(plano);
            if (pedido != null) {
                Plano oldPlanoOfPedido = pedido.getPlano();
                if (oldPlanoOfPedido != null) {
                    oldPlanoOfPedido.setPedido(null);
                    oldPlanoOfPedido = em.merge(oldPlanoOfPedido);
                }
                pedido.setPlano(plano);
                pedido = em.merge(pedido);
            }
            for (Pedido pedidoSetPedido : plano.getPedidoSet()) {
                Plano oldPlanoOfPedidoSetPedido = pedidoSetPedido.getPlano();
                pedidoSetPedido.setPlano(plano);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldPlanoOfPedidoSetPedido != null) {
                    oldPlanoOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldPlanoOfPedidoSetPedido = em.merge(oldPlanoOfPedidoSetPedido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlano(plano.getIdplano()) != null) {
                throw new PreexistingEntityException("Plano " + plano + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Plano plano) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plano persistentPlano = em.find(Plano.class, plano.getIdplano());
            Pedido pedidoOld = persistentPlano.getPedido();
            Pedido pedidoNew = plano.getPedido();
            Set<Pedido> pedidoSetOld = persistentPlano.getPedidoSet();
            Set<Pedido> pedidoSetNew = plano.getPedidoSet();
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                plano.setPedido(pedidoNew);
            }
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            plano.setPedidoSet(pedidoSetNew);
            plano = em.merge(plano);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.setPlano(null);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                Plano oldPlanoOfPedido = pedidoNew.getPlano();
                if (oldPlanoOfPedido != null) {
                    oldPlanoOfPedido.setPedido(null);
                    oldPlanoOfPedido = em.merge(oldPlanoOfPedido);
                }
                pedidoNew.setPlano(plano);
                pedidoNew = em.merge(pedidoNew);
            }
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setPlano(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Plano oldPlanoOfPedidoSetNewPedido = pedidoSetNewPedido.getPlano();
                    pedidoSetNewPedido.setPlano(plano);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldPlanoOfPedidoSetNewPedido != null && !oldPlanoOfPedidoSetNewPedido.equals(plano)) {
                        oldPlanoOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldPlanoOfPedidoSetNewPedido = em.merge(oldPlanoOfPedidoSetNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = plano.getIdplano();
                if (findPlano(id) == null) {
                    throw new NonexistentEntityException("The plano with id " + id + " no longer exists.");
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
            Plano plano;
            try {
                plano = em.getReference(Plano.class, id);
                plano.getIdplano();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plano with id " + id + " no longer exists.", enfe);
            }
            Pedido pedido = plano.getPedido();
            if (pedido != null) {
                pedido.setPlano(null);
                pedido = em.merge(pedido);
            }
            Set<Pedido> pedidoSet = plano.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPlano(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            em.remove(plano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Plano> findPlanoEntities() {
        return findPlanoEntities(true, -1, -1);
    }

    public List<Plano> findPlanoEntities(int maxResults, int firstResult) {
        return findPlanoEntities(false, maxResults, firstResult);
    }

    private List<Plano> findPlanoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Plano.class));
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

    public Plano findPlano(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Plano.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Plano> rt = cq.from(Plano.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
