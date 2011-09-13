/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleproducto;
import metalsoft.datos.jpa.entity.Producto;

/**
 *
 * @author Nino
 */
public class DetalleproductoJpaController {

    public DetalleproductoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleproducto detalleproducto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idproducto = detalleproducto.getIdproducto();
            if (idproducto != null) {
                idproducto = em.getReference(idproducto.getClass(), idproducto.getIdproducto());
                detalleproducto.setIdproducto(idproducto);
            }
            em.persist(detalleproducto);
            if (idproducto != null) {
                idproducto.getDetalleproductoList().add(detalleproducto);
                idproducto = em.merge(idproducto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleproducto(detalleproducto.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detalleproducto " + detalleproducto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleproducto detalleproducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproducto persistentDetalleproducto = em.find(Detalleproducto.class, detalleproducto.getIddetalle());
            Producto idproductoOld = persistentDetalleproducto.getIdproducto();
            Producto idproductoNew = detalleproducto.getIdproducto();
            if (idproductoNew != null) {
                idproductoNew = em.getReference(idproductoNew.getClass(), idproductoNew.getIdproducto());
                detalleproducto.setIdproducto(idproductoNew);
            }
            detalleproducto = em.merge(detalleproducto);
            if (idproductoOld != null && !idproductoOld.equals(idproductoNew)) {
                idproductoOld.getDetalleproductoList().remove(detalleproducto);
                idproductoOld = em.merge(idproductoOld);
            }
            if (idproductoNew != null && !idproductoNew.equals(idproductoOld)) {
                idproductoNew.getDetalleproductoList().add(detalleproducto);
                idproductoNew = em.merge(idproductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleproducto.getIddetalle();
                if (findDetalleproducto(id) == null) {
                    throw new NonexistentEntityException("The detalleproducto with id " + id + " no longer exists.");
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
            Detalleproducto detalleproducto;
            try {
                detalleproducto = em.getReference(Detalleproducto.class, id);
                detalleproducto.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleproducto with id " + id + " no longer exists.", enfe);
            }
            Producto idproducto = detalleproducto.getIdproducto();
            if (idproducto != null) {
                idproducto.getDetalleproductoList().remove(detalleproducto);
                idproducto = em.merge(idproducto);
            }
            em.remove(detalleproducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleproducto> findDetalleproductoEntities() {
        return findDetalleproductoEntities(true, -1, -1);
    }

    public List<Detalleproducto> findDetalleproductoEntities(int maxResults, int firstResult) {
        return findDetalleproductoEntities(false, maxResults, firstResult);
    }

    private List<Detalleproducto> findDetalleproductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleproducto.class));
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

    public Detalleproducto findDetalleproducto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleproducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleproductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleproducto> rt = cq.from(Detalleproducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
