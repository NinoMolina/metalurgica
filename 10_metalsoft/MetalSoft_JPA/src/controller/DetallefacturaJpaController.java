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
        detallefactura.getDetallefacturaPK().setIdfactura(detallefactura.getFactura1().getIdfactura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura = detallefactura.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                detallefactura.setFactura(factura);
            }
            Factura factura1 = detallefactura.getFactura1();
            if (factura1 != null) {
                factura1 = em.getReference(factura1.getClass(), factura1.getIdfactura());
                detallefactura.setFactura1(factura1);
            }
            Pedido idpedido = detallefactura.getIdpedido();
            if (idpedido != null) {
                idpedido = em.getReference(idpedido.getClass(), idpedido.getIdpedido());
                detallefactura.setIdpedido(idpedido);
            }
            Pedido idpedido1 = detallefactura.getIdpedido1();
            if (idpedido1 != null) {
                idpedido1 = em.getReference(idpedido1.getClass(), idpedido1.getIdpedido());
                detallefactura.setIdpedido1(idpedido1);
            }
            em.persist(detallefactura);
            if (factura != null) {
                factura.getDetallefacturaSet().add(detallefactura);
                factura = em.merge(factura);
            }
            if (factura1 != null) {
                factura1.getDetallefacturaSet().add(detallefactura);
                factura1 = em.merge(factura1);
            }
            if (idpedido != null) {
                idpedido.getDetallefacturaSet().add(detallefactura);
                idpedido = em.merge(idpedido);
            }
            if (idpedido1 != null) {
                idpedido1.getDetallefacturaSet().add(detallefactura);
                idpedido1 = em.merge(idpedido1);
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
        detallefactura.getDetallefacturaPK().setIdfactura(detallefactura.getFactura1().getIdfactura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getDetallefacturaPK());
            Factura facturaOld = persistentDetallefactura.getFactura();
            Factura facturaNew = detallefactura.getFactura();
            Factura factura1Old = persistentDetallefactura.getFactura1();
            Factura factura1New = detallefactura.getFactura1();
            Pedido idpedidoOld = persistentDetallefactura.getIdpedido();
            Pedido idpedidoNew = detallefactura.getIdpedido();
            Pedido idpedido1Old = persistentDetallefactura.getIdpedido1();
            Pedido idpedido1New = detallefactura.getIdpedido1();
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                detallefactura.setFactura(facturaNew);
            }
            if (factura1New != null) {
                factura1New = em.getReference(factura1New.getClass(), factura1New.getIdfactura());
                detallefactura.setFactura1(factura1New);
            }
            if (idpedidoNew != null) {
                idpedidoNew = em.getReference(idpedidoNew.getClass(), idpedidoNew.getIdpedido());
                detallefactura.setIdpedido(idpedidoNew);
            }
            if (idpedido1New != null) {
                idpedido1New = em.getReference(idpedido1New.getClass(), idpedido1New.getIdpedido());
                detallefactura.setIdpedido1(idpedido1New);
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
            if (factura1Old != null && !factura1Old.equals(factura1New)) {
                factura1Old.getDetallefacturaSet().remove(detallefactura);
                factura1Old = em.merge(factura1Old);
            }
            if (factura1New != null && !factura1New.equals(factura1Old)) {
                factura1New.getDetallefacturaSet().add(detallefactura);
                factura1New = em.merge(factura1New);
            }
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getDetallefacturaSet().remove(detallefactura);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getDetallefacturaSet().add(detallefactura);
                idpedidoNew = em.merge(idpedidoNew);
            }
            if (idpedido1Old != null && !idpedido1Old.equals(idpedido1New)) {
                idpedido1Old.getDetallefacturaSet().remove(detallefactura);
                idpedido1Old = em.merge(idpedido1Old);
            }
            if (idpedido1New != null && !idpedido1New.equals(idpedido1Old)) {
                idpedido1New.getDetallefacturaSet().add(detallefactura);
                idpedido1New = em.merge(idpedido1New);
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
            Factura factura1 = detallefactura.getFactura1();
            if (factura1 != null) {
                factura1.getDetallefacturaSet().remove(detallefactura);
                factura1 = em.merge(factura1);
            }
            Pedido idpedido = detallefactura.getIdpedido();
            if (idpedido != null) {
                idpedido.getDetallefacturaSet().remove(detallefactura);
                idpedido = em.merge(idpedido);
            }
            Pedido idpedido1 = detallefactura.getIdpedido1();
            if (idpedido1 != null) {
                idpedido1.getDetallefacturaSet().remove(detallefactura);
                idpedido1 = em.merge(idpedido1);
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
