/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Matriz;
import metalsoft.datos.jpa.entity.Pedidomatriz;

/**
 *
 * @author Nino
 */
public class PedidomatrizJpaController implements Serializable {

    public PedidomatrizJpaController(EntityManagerFactory emf) {
        this.emf = emf;
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
            em.persist(pedidomatriz);
            if (idmatriz != null) {
                idmatriz.getPedidomatrizList().add(pedidomatriz);
                idmatriz = em.merge(idmatriz);
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
            if (idmatrizNew != null) {
                idmatrizNew = em.getReference(idmatrizNew.getClass(), idmatrizNew.getIdmatriz());
                pedidomatriz.setIdmatriz(idmatrizNew);
            }
            pedidomatriz = em.merge(pedidomatriz);
            if (idmatrizOld != null && !idmatrizOld.equals(idmatrizNew)) {
                idmatrizOld.getPedidomatrizList().remove(pedidomatriz);
                idmatrizOld = em.merge(idmatrizOld);
            }
            if (idmatrizNew != null && !idmatrizNew.equals(idmatrizOld)) {
                idmatrizNew.getPedidomatrizList().add(pedidomatriz);
                idmatrizNew = em.merge(idmatrizNew);
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
                idmatriz.getPedidomatrizList().remove(pedidomatriz);
                idmatriz = em.merge(idmatriz);
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
