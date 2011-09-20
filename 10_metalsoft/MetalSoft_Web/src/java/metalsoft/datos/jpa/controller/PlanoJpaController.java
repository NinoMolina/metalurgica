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
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Plano;

/**
 *
 * @author Nino
 */
public class PlanoJpaController implements Serializable {

    public PlanoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Plano plano) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = plano.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                plano.setPedido(pedido);
            }
            em.persist(plano);
            if (pedido != null) {
                pedido.getPlanoList().add(plano);
                pedido = em.merge(pedido);
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
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                plano.setPedido(pedidoNew);
            }
            plano = em.merge(plano);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getPlanoList().remove(plano);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getPlanoList().add(plano);
                pedidoNew = em.merge(pedidoNew);
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
                pedido.getPlanoList().remove(plano);
                pedido = em.merge(pedido);
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
