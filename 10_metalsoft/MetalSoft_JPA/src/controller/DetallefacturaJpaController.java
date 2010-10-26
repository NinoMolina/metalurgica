/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallefactura;
import entity.DetallefacturaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Factura;
import entity.Pedido;

/**
 *
 * @author Nino
 */
public class DetallefacturaJpaController {

    public DetallefacturaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefactura detallefactura) throws PreexistingEntityException, Exception {
        if (detallefactura.getDetallefacturaPK() == null) {
            detallefactura.setDetallefacturaPK(new DetallefacturaPK());
        }
        detallefactura.getDetallefacturaPK().setIdfactura(detallefactura.getFactura().getIdfactura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura = detallefactura.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                detallefactura.setFactura(factura);
            }
            Pedido idpedido = detallefactura.getIdpedido();
            if (idpedido != null) {
                idpedido = em.getReference(idpedido.getClass(), idpedido.getIdpedido());
                detallefactura.setIdpedido(idpedido);
            }
            em.persist(detallefactura);
            if (factura != null) {
                factura.getDetallefacturaSet().add(detallefactura);
                factura = em.merge(factura);
            }
            if (idpedido != null) {
                idpedido.getDetallefacturaSet().add(detallefactura);
                idpedido = em.merge(idpedido);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallefactura(detallefactura.getDetallefacturaPK()) != null) {
                throw new PreexistingEntityException("Detallefactura " + detallefactura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefactura detallefactura) throws NonexistentEntityException, Exception {
        detallefactura.getDetallefacturaPK().setIdfactura(detallefactura.getFactura().getIdfactura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getDetallefacturaPK());
            Factura facturaOld = persistentDetallefactura.getFactura();
            Factura facturaNew = detallefactura.getFactura();
            Pedido idpedidoOld = persistentDetallefactura.getIdpedido();
            Pedido idpedidoNew = detallefactura.getIdpedido();
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                detallefactura.setFactura(facturaNew);
            }
            if (idpedidoNew != null) {
                idpedidoNew = em.getReference(idpedidoNew.getClass(), idpedidoNew.getIdpedido());
                detallefactura.setIdpedido(idpedidoNew);
            }
            detallefactura = em.merge(detallefactura);
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getDetallefacturaSet().remove(detallefactura);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getDetallefacturaSet().add(detallefactura);
                facturaNew = em.merge(facturaNew);
            }
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getDetallefacturaSet().remove(detallefactura);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getDetallefacturaSet().add(detallefactura);
                idpedidoNew = em.merge(idpedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallefacturaPK id = detallefactura.getDetallefacturaPK();
                if (findDetallefactura(id) == null) {
                    throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallefacturaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura detallefactura;
            try {
                detallefactura = em.getReference(Detallefactura.class, id);
                detallefactura.getDetallefacturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.", enfe);
            }
            Factura factura = detallefactura.getFactura();
            if (factura != null) {
                factura.getDetallefacturaSet().remove(detallefactura);
                factura = em.merge(factura);
            }
            Pedido idpedido = detallefactura.getIdpedido();
            if (idpedido != null) {
                idpedido.getDetallefacturaSet().remove(detallefactura);
                idpedido = em.merge(idpedido);
            }
            em.remove(detallefactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefactura> findDetallefacturaEntities() {
        return findDetallefacturaEntities(true, -1, -1);
    }

    public List<Detallefactura> findDetallefacturaEntities(int maxResults, int firstResult) {
        return findDetallefacturaEntities(false, maxResults, firstResult);
    }

    private List<Detallefactura> findDetallefacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefactura.class));
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

    public Detallefactura findDetallefactura(DetallefacturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefactura> rt = cq.from(Detallefactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
