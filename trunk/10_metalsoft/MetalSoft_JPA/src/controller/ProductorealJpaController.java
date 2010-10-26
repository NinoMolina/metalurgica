/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Productoreal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Codigodebarra;
import entity.Estadoproductoreal;
import entity.Pedido;
import entity.Detalleproductoreal;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ProductorealJpaController {

    public ProductorealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productoreal productoreal) throws PreexistingEntityException, Exception {
        if (productoreal.getDetalleproductorealSet() == null) {
            productoreal.setDetalleproductorealSet(new HashSet<Detalleproductoreal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codigodebarra codigobarra = productoreal.getCodigobarra();
            if (codigobarra != null) {
                codigobarra = em.getReference(codigobarra.getClass(), codigobarra.getIdcodigo());
                productoreal.setCodigobarra(codigobarra);
            }
            Estadoproductoreal estado = productoreal.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                productoreal.setEstado(estado);
            }
            Pedido idpedido = productoreal.getIdpedido();
            if (idpedido != null) {
                idpedido = em.getReference(idpedido.getClass(), idpedido.getIdpedido());
                productoreal.setIdpedido(idpedido);
            }
            Set<Detalleproductoreal> attachedDetalleproductorealSet = new HashSet<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealSetDetalleproductorealToAttach : productoreal.getDetalleproductorealSet()) {
                detalleproductorealSetDetalleproductorealToAttach = em.getReference(detalleproductorealSetDetalleproductorealToAttach.getClass(), detalleproductorealSetDetalleproductorealToAttach.getDetalleproductorealPK());
                attachedDetalleproductorealSet.add(detalleproductorealSetDetalleproductorealToAttach);
            }
            productoreal.setDetalleproductorealSet(attachedDetalleproductorealSet);
            em.persist(productoreal);
            if (codigobarra != null) {
                codigobarra.getProductorealSet().add(productoreal);
                codigobarra = em.merge(codigobarra);
            }
            if (estado != null) {
                estado.getProductorealSet().add(productoreal);
                estado = em.merge(estado);
            }
            if (idpedido != null) {
                idpedido.getProductorealSet().add(productoreal);
                idpedido = em.merge(idpedido);
            }
            for (Detalleproductoreal detalleproductorealSetDetalleproductoreal : productoreal.getDetalleproductorealSet()) {
                Productoreal oldProductorealOfDetalleproductorealSetDetalleproductoreal = detalleproductorealSetDetalleproductoreal.getProductoreal();
                detalleproductorealSetDetalleproductoreal.setProductoreal(productoreal);
                detalleproductorealSetDetalleproductoreal = em.merge(detalleproductorealSetDetalleproductoreal);
                if (oldProductorealOfDetalleproductorealSetDetalleproductoreal != null) {
                    oldProductorealOfDetalleproductorealSetDetalleproductoreal.getDetalleproductorealSet().remove(detalleproductorealSetDetalleproductoreal);
                    oldProductorealOfDetalleproductorealSetDetalleproductoreal = em.merge(oldProductorealOfDetalleproductorealSetDetalleproductoreal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductoreal(productoreal.getIdproductoreal()) != null) {
                throw new PreexistingEntityException("Productoreal " + productoreal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productoreal productoreal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productoreal persistentProductoreal = em.find(Productoreal.class, productoreal.getIdproductoreal());
            Codigodebarra codigobarraOld = persistentProductoreal.getCodigobarra();
            Codigodebarra codigobarraNew = productoreal.getCodigobarra();
            Estadoproductoreal estadoOld = persistentProductoreal.getEstado();
            Estadoproductoreal estadoNew = productoreal.getEstado();
            Pedido idpedidoOld = persistentProductoreal.getIdpedido();
            Pedido idpedidoNew = productoreal.getIdpedido();
            Set<Detalleproductoreal> detalleproductorealSetOld = persistentProductoreal.getDetalleproductorealSet();
            Set<Detalleproductoreal> detalleproductorealSetNew = productoreal.getDetalleproductorealSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleproductoreal detalleproductorealSetOldDetalleproductoreal : detalleproductorealSetOld) {
                if (!detalleproductorealSetNew.contains(detalleproductorealSetOldDetalleproductoreal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleproductoreal " + detalleproductorealSetOldDetalleproductoreal + " since its productoreal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codigobarraNew != null) {
                codigobarraNew = em.getReference(codigobarraNew.getClass(), codigobarraNew.getIdcodigo());
                productoreal.setCodigobarra(codigobarraNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                productoreal.setEstado(estadoNew);
            }
            if (idpedidoNew != null) {
                idpedidoNew = em.getReference(idpedidoNew.getClass(), idpedidoNew.getIdpedido());
                productoreal.setIdpedido(idpedidoNew);
            }
            Set<Detalleproductoreal> attachedDetalleproductorealSetNew = new HashSet<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealSetNewDetalleproductorealToAttach : detalleproductorealSetNew) {
                detalleproductorealSetNewDetalleproductorealToAttach = em.getReference(detalleproductorealSetNewDetalleproductorealToAttach.getClass(), detalleproductorealSetNewDetalleproductorealToAttach.getDetalleproductorealPK());
                attachedDetalleproductorealSetNew.add(detalleproductorealSetNewDetalleproductorealToAttach);
            }
            detalleproductorealSetNew = attachedDetalleproductorealSetNew;
            productoreal.setDetalleproductorealSet(detalleproductorealSetNew);
            productoreal = em.merge(productoreal);
            if (codigobarraOld != null && !codigobarraOld.equals(codigobarraNew)) {
                codigobarraOld.getProductorealSet().remove(productoreal);
                codigobarraOld = em.merge(codigobarraOld);
            }
            if (codigobarraNew != null && !codigobarraNew.equals(codigobarraOld)) {
                codigobarraNew.getProductorealSet().add(productoreal);
                codigobarraNew = em.merge(codigobarraNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getProductorealSet().remove(productoreal);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getProductorealSet().add(productoreal);
                estadoNew = em.merge(estadoNew);
            }
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getProductorealSet().remove(productoreal);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getProductorealSet().add(productoreal);
                idpedidoNew = em.merge(idpedidoNew);
            }
            for (Detalleproductoreal detalleproductorealSetNewDetalleproductoreal : detalleproductorealSetNew) {
                if (!detalleproductorealSetOld.contains(detalleproductorealSetNewDetalleproductoreal)) {
                    Productoreal oldProductorealOfDetalleproductorealSetNewDetalleproductoreal = detalleproductorealSetNewDetalleproductoreal.getProductoreal();
                    detalleproductorealSetNewDetalleproductoreal.setProductoreal(productoreal);
                    detalleproductorealSetNewDetalleproductoreal = em.merge(detalleproductorealSetNewDetalleproductoreal);
                    if (oldProductorealOfDetalleproductorealSetNewDetalleproductoreal != null && !oldProductorealOfDetalleproductorealSetNewDetalleproductoreal.equals(productoreal)) {
                        oldProductorealOfDetalleproductorealSetNewDetalleproductoreal.getDetalleproductorealSet().remove(detalleproductorealSetNewDetalleproductoreal);
                        oldProductorealOfDetalleproductorealSetNewDetalleproductoreal = em.merge(oldProductorealOfDetalleproductorealSetNewDetalleproductoreal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = productoreal.getIdproductoreal();
                if (findProductoreal(id) == null) {
                    throw new NonexistentEntityException("The productoreal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productoreal productoreal;
            try {
                productoreal = em.getReference(Productoreal.class, id);
                productoreal.getIdproductoreal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoreal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleproductoreal> detalleproductorealSetOrphanCheck = productoreal.getDetalleproductorealSet();
            for (Detalleproductoreal detalleproductorealSetOrphanCheckDetalleproductoreal : detalleproductorealSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productoreal (" + productoreal + ") cannot be destroyed since the Detalleproductoreal " + detalleproductorealSetOrphanCheckDetalleproductoreal + " in its detalleproductorealSet field has a non-nullable productoreal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codigodebarra codigobarra = productoreal.getCodigobarra();
            if (codigobarra != null) {
                codigobarra.getProductorealSet().remove(productoreal);
                codigobarra = em.merge(codigobarra);
            }
            Estadoproductoreal estado = productoreal.getEstado();
            if (estado != null) {
                estado.getProductorealSet().remove(productoreal);
                estado = em.merge(estado);
            }
            Pedido idpedido = productoreal.getIdpedido();
            if (idpedido != null) {
                idpedido.getProductorealSet().remove(productoreal);
                idpedido = em.merge(idpedido);
            }
            em.remove(productoreal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productoreal> findProductorealEntities() {
        return findProductorealEntities(true, -1, -1);
    }

    public List<Productoreal> findProductorealEntities(int maxResults, int firstResult) {
        return findProductorealEntities(false, maxResults, firstResult);
    }

    private List<Productoreal> findProductorealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productoreal.class));
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

    public Productoreal findProductoreal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productoreal.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductorealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productoreal> rt = cq.from(Productoreal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
