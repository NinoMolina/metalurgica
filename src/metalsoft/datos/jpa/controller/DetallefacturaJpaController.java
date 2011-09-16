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
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.DetallefacturaPK;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Factura;

/**
 *
 * @author Nino
 */
public class DetallefacturaJpaController implements Serializable {

    public DetallefacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
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
            Pedido idpedido = detallefactura.getIdpedido();
            if (idpedido != null) {
                idpedido = em.getReference(idpedido.getClass(), idpedido.getIdpedido());
                detallefactura.setIdpedido(idpedido);
            }
            Factura factura = detallefactura.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                detallefactura.setFactura(factura);
            }
            em.persist(detallefactura);
            if (idpedido != null) {
                idpedido.getDetallefacturaList().add(detallefactura);
                idpedido = em.merge(idpedido);
            }
            if (factura != null) {
                factura.getDetallefacturaList().add(detallefactura);
                factura = em.merge(factura);
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
            Pedido idpedidoOld = persistentDetallefactura.getIdpedido();
            Pedido idpedidoNew = detallefactura.getIdpedido();
            Factura facturaOld = persistentDetallefactura.getFactura();
            Factura facturaNew = detallefactura.getFactura();
            if (idpedidoNew != null) {
                idpedidoNew = em.getReference(idpedidoNew.getClass(), idpedidoNew.getIdpedido());
                detallefactura.setIdpedido(idpedidoNew);
            }
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                detallefactura.setFactura(facturaNew);
            }
            detallefactura = em.merge(detallefactura);
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getDetallefacturaList().remove(detallefactura);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getDetallefacturaList().add(detallefactura);
                idpedidoNew = em.merge(idpedidoNew);
            }
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getDetallefacturaList().remove(detallefactura);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getDetallefacturaList().add(detallefactura);
                facturaNew = em.merge(facturaNew);
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
            Pedido idpedido = detallefactura.getIdpedido();
            if (idpedido != null) {
                idpedido.getDetallefacturaList().remove(detallefactura);
                idpedido = em.merge(idpedido);
            }
            Factura factura = detallefactura.getFactura();
            if (factura != null) {
                factura.getDetallefacturaList().remove(detallefactura);
                factura = em.merge(factura);
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
