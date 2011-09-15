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
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Pedido;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Plano;

/**
 *
 * @author Nino
 */
public class PlanoJpaController {

    public PlanoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Plano plano) throws PreexistingEntityException, Exception {
        if (plano.getPedidoList() == null) {
            plano.setPedidoList(new ArrayList<Pedido>());
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
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : plano.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            plano.setPedidoList(attachedPedidoList);
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
            for (Pedido pedidoListPedido : plano.getPedidoList()) {
                Plano oldPlanoOfPedidoListPedido = pedidoListPedido.getPlano();
                pedidoListPedido.setPlano(plano);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldPlanoOfPedidoListPedido != null) {
                    oldPlanoOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldPlanoOfPedidoListPedido = em.merge(oldPlanoOfPedidoListPedido);
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
            List<Pedido> pedidoListOld = persistentPlano.getPedidoList();
            List<Pedido> pedidoListNew = plano.getPedidoList();
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                plano.setPedido(pedidoNew);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdpedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            plano.setPedidoList(pedidoListNew);
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
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setPlano(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Plano oldPlanoOfPedidoListNewPedido = pedidoListNewPedido.getPlano();
                    pedidoListNewPedido.setPlano(plano);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldPlanoOfPedidoListNewPedido != null && !oldPlanoOfPedidoListNewPedido.equals(plano)) {
                        oldPlanoOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldPlanoOfPedidoListNewPedido = em.merge(oldPlanoOfPedidoListNewPedido);
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
            List<Pedido> pedidoList = plano.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setPlano(null);
                pedidoListPedido = em.merge(pedidoListPedido);
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
