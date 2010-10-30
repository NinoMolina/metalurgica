/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Pedidomatriz;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Matriz;

/**
 *
 * @author Nino
 */
public class PedidomatrizJpaController {

    public PedidomatrizJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedidomatriz pedidomatriz) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matriz idmatriz = pedidomatriz.getIdmatriz();
            if (idmatriz != null) {
                idmatriz = em.getReference(idmatriz.getClass(), idmatriz.getIdmatriz());
                pedidomatriz.setIdmatriz(idmatriz);
            }
            Matriz idmatriz1 = pedidomatriz.getIdmatriz1();
            if (idmatriz1 != null) {
                idmatriz1 = em.getReference(idmatriz1.getClass(), idmatriz1.getIdmatriz());
                pedidomatriz.setIdmatriz1(idmatriz1);
            }
            em.persist(pedidomatriz);
            if (idmatriz != null) {
                idmatriz.getPedidomatrizSet().add(pedidomatriz);
                idmatriz = em.merge(idmatriz);
            }
            if (idmatriz1 != null) {
                idmatriz1.getPedidomatrizSet().add(pedidomatriz);
                idmatriz1 = em.merge(idmatriz1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPedidomatriz(pedidomatriz.getIdpedidomatriz()) != null) {
                throw new PreexistingEntityException("Pedidomatriz " + pedidomatriz + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedidomatriz pedidomatriz) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidomatriz persistentPedidomatriz = em.find(Pedidomatriz.class, pedidomatriz.getIdpedidomatriz());
            Matriz idmatrizOld = persistentPedidomatriz.getIdmatriz();
            Matriz idmatrizNew = pedidomatriz.getIdmatriz();
            Matriz idmatriz1Old = persistentPedidomatriz.getIdmatriz1();
            Matriz idmatriz1New = pedidomatriz.getIdmatriz1();
            if (idmatrizNew != null) {
                idmatrizNew = em.getReference(idmatrizNew.getClass(), idmatrizNew.getIdmatriz());
                pedidomatriz.setIdmatriz(idmatrizNew);
            }
            if (idmatriz1New != null) {
                idmatriz1New = em.getReference(idmatriz1New.getClass(), idmatriz1New.getIdmatriz());
                pedidomatriz.setIdmatriz1(idmatriz1New);
            }
            pedidomatriz = em.merge(pedidomatriz);
            if (idmatrizOld != null && !idmatrizOld.equals(idmatrizNew)) {
                idmatrizOld.getPedidomatrizSet().remove(pedidomatriz);
                idmatrizOld = em.merge(idmatrizOld);
            }
            if (idmatrizNew != null && !idmatrizNew.equals(idmatrizOld)) {
                idmatrizNew.getPedidomatrizSet().add(pedidomatriz);
                idmatrizNew = em.merge(idmatrizNew);
            }
            if (idmatriz1Old != null && !idmatriz1Old.equals(idmatriz1New)) {
                idmatriz1Old.getPedidomatrizSet().remove(pedidomatriz);
                idmatriz1Old = em.merge(idmatriz1Old);
            }
            if (idmatriz1New != null && !idmatriz1New.equals(idmatriz1Old)) {
                idmatriz1New.getPedidomatrizSet().add(pedidomatriz);
                idmatriz1New = em.merge(idmatriz1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pedidomatriz.getIdpedidomatriz();
                if (findPedidomatriz(id) == null) {
                    throw new NonexistentEntityException("The pedidomatriz with id " + id + " no longer exists.");
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
            Pedidomatriz pedidomatriz;
            try {
                pedidomatriz = em.getReference(Pedidomatriz.class, id);
                pedidomatriz.getIdpedidomatriz();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidomatriz with id " + id + " no longer exists.", enfe);
            }
            Matriz idmatriz = pedidomatriz.getIdmatriz();
            if (idmatriz != null) {
                idmatriz.getPedidomatrizSet().remove(pedidomatriz);
                idmatriz = em.merge(idmatriz);
            }
            Matriz idmatriz1 = pedidomatriz.getIdmatriz1();
            if (idmatriz1 != null) {
                idmatriz1.getPedidomatrizSet().remove(pedidomatriz);
                idmatriz1 = em.merge(idmatriz1);
            }
            em.remove(pedidomatriz);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedidomatriz> findPedidomatrizEntities() {
        return findPedidomatrizEntities(true, -1, -1);
    }

    public List<Pedidomatriz> findPedidomatrizEntities(int maxResults, int firstResult) {
        return findPedidomatrizEntities(false, maxResults, firstResult);
    }

    private List<Pedidomatriz> findPedidomatrizEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedidomatriz.class));
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

    public Pedidomatriz findPedidomatriz(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedidomatriz.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidomatrizCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedidomatriz> rt = cq.from(Pedidomatriz.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
