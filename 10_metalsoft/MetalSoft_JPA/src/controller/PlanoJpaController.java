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
        if (plano.getPedidoSet1() == null) {
            plano.setPedidoSet1(new HashSet<Pedido>());
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
            Pedido pedido1 = plano.getPedido1();
            if (pedido1 != null) {
                pedido1 = em.getReference(pedido1.getClass(), pedido1.getIdpedido());
                plano.setPedido1(pedido1);
            }
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : plano.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            plano.setPedidoSet(attachedPedidoSet);
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : plano.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            plano.setPedidoSet1(attachedPedidoSet1);
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
            if (pedido1 != null) {
                Plano oldPlanoOfPedido1 = pedido1.getPlano();
                if (oldPlanoOfPedido1 != null) {
                    oldPlanoOfPedido1.setPedido1(null);
                    oldPlanoOfPedido1 = em.merge(oldPlanoOfPedido1);
                }
                pedido1.setPlano(plano);
                pedido1 = em.merge(pedido1);
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
            for (Pedido pedidoSet1Pedido : plano.getPedidoSet1()) {
                Plano oldPlano1OfPedidoSet1Pedido = pedidoSet1Pedido.getPlano1();
                pedidoSet1Pedido.setPlano1(plano);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldPlano1OfPedidoSet1Pedido != null) {
                    oldPlano1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldPlano1OfPedidoSet1Pedido = em.merge(oldPlano1OfPedidoSet1Pedido);
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
            Pedido pedido1Old = persistentPlano.getPedido1();
            Pedido pedido1New = plano.getPedido1();
            Set<Pedido> pedidoSetOld = persistentPlano.getPedidoSet();
            Set<Pedido> pedidoSetNew = plano.getPedidoSet();
            Set<Pedido> pedidoSet1Old = persistentPlano.getPedidoSet1();
            Set<Pedido> pedidoSet1New = plano.getPedidoSet1();
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                plano.setPedido(pedidoNew);
            }
            if (pedido1New != null) {
                pedido1New = em.getReference(pedido1New.getClass(), pedido1New.getIdpedido());
                plano.setPedido1(pedido1New);
            }
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            plano.setPedidoSet(pedidoSetNew);
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            plano.setPedidoSet1(pedidoSet1New);
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
            if (pedido1Old != null && !pedido1Old.equals(pedido1New)) {
                pedido1Old.setPlano(null);
                pedido1Old = em.merge(pedido1Old);
            }
            if (pedido1New != null && !pedido1New.equals(pedido1Old)) {
                Plano oldPlanoOfPedido1 = pedido1New.getPlano();
                if (oldPlanoOfPedido1 != null) {
                    oldPlanoOfPedido1.setPedido1(null);
                    oldPlanoOfPedido1 = em.merge(oldPlanoOfPedido1);
                }
                pedido1New.setPlano(plano);
                pedido1New = em.merge(pedido1New);
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
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    pedidoSet1OldPedido.setPlano1(null);
                    pedidoSet1OldPedido = em.merge(pedidoSet1OldPedido);
                }
            }
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Plano oldPlano1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getPlano1();
                    pedidoSet1NewPedido.setPlano1(plano);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldPlano1OfPedidoSet1NewPedido != null && !oldPlano1OfPedidoSet1NewPedido.equals(plano)) {
                        oldPlano1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldPlano1OfPedidoSet1NewPedido = em.merge(oldPlano1OfPedidoSet1NewPedido);
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
            Pedido pedido1 = plano.getPedido1();
            if (pedido1 != null) {
                pedido1.setPlano(null);
                pedido1 = em.merge(pedido1);
            }
            Set<Pedido> pedidoSet = plano.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPlano(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Pedido> pedidoSet1 = plano.getPedidoSet1();
            for (Pedido pedidoSet1Pedido : pedidoSet1) {
                pedidoSet1Pedido.setPlano1(null);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
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
