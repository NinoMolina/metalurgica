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
        if (productoreal.getDetalleproductorealSet1() == null) {
            productoreal.setDetalleproductorealSet1(new HashSet<Detalleproductoreal>());
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
            Codigodebarra codigobarra1 = productoreal.getCodigobarra1();
            if (codigobarra1 != null) {
                codigobarra1 = em.getReference(codigobarra1.getClass(), codigobarra1.getIdcodigo());
                productoreal.setCodigobarra1(codigobarra1);
            }
            Estadoproductoreal estado = productoreal.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                productoreal.setEstado(estado);
            }
            Estadoproductoreal estado1 = productoreal.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                productoreal.setEstado1(estado1);
            }
            Pedido idpedido = productoreal.getIdpedido();
            if (idpedido != null) {
                idpedido = em.getReference(idpedido.getClass(), idpedido.getIdpedido());
                productoreal.setIdpedido(idpedido);
            }
            Pedido idpedido1 = productoreal.getIdpedido1();
            if (idpedido1 != null) {
                idpedido1 = em.getReference(idpedido1.getClass(), idpedido1.getIdpedido());
                productoreal.setIdpedido1(idpedido1);
            }
            Set<Detalleproductoreal> attachedDetalleproductorealSet = new HashSet<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealSetDetalleproductorealToAttach : productoreal.getDetalleproductorealSet()) {
                detalleproductorealSetDetalleproductorealToAttach = em.getReference(detalleproductorealSetDetalleproductorealToAttach.getClass(), detalleproductorealSetDetalleproductorealToAttach.getDetalleproductorealPK());
                attachedDetalleproductorealSet.add(detalleproductorealSetDetalleproductorealToAttach);
            }
            productoreal.setDetalleproductorealSet(attachedDetalleproductorealSet);
            Set<Detalleproductoreal> attachedDetalleproductorealSet1 = new HashSet<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealSet1DetalleproductorealToAttach : productoreal.getDetalleproductorealSet1()) {
                detalleproductorealSet1DetalleproductorealToAttach = em.getReference(detalleproductorealSet1DetalleproductorealToAttach.getClass(), detalleproductorealSet1DetalleproductorealToAttach.getDetalleproductorealPK());
                attachedDetalleproductorealSet1.add(detalleproductorealSet1DetalleproductorealToAttach);
            }
            productoreal.setDetalleproductorealSet1(attachedDetalleproductorealSet1);
            em.persist(productoreal);
            if (codigobarra != null) {
                codigobarra.getProductorealSet().add(productoreal);
                codigobarra = em.merge(codigobarra);
            }
            if (codigobarra1 != null) {
                codigobarra1.getProductorealSet().add(productoreal);
                codigobarra1 = em.merge(codigobarra1);
            }
            if (estado != null) {
                estado.getProductorealSet().add(productoreal);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getProductorealSet().add(productoreal);
                estado1 = em.merge(estado1);
            }
            if (idpedido != null) {
                idpedido.getProductorealSet().add(productoreal);
                idpedido = em.merge(idpedido);
            }
            if (idpedido1 != null) {
                idpedido1.getProductorealSet().add(productoreal);
                idpedido1 = em.merge(idpedido1);
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
            for (Detalleproductoreal detalleproductorealSet1Detalleproductoreal : productoreal.getDetalleproductorealSet1()) {
                Productoreal oldProductoreal1OfDetalleproductorealSet1Detalleproductoreal = detalleproductorealSet1Detalleproductoreal.getProductoreal1();
                detalleproductorealSet1Detalleproductoreal.setProductoreal1(productoreal);
                detalleproductorealSet1Detalleproductoreal = em.merge(detalleproductorealSet1Detalleproductoreal);
                if (oldProductoreal1OfDetalleproductorealSet1Detalleproductoreal != null) {
                    oldProductoreal1OfDetalleproductorealSet1Detalleproductoreal.getDetalleproductorealSet1().remove(detalleproductorealSet1Detalleproductoreal);
                    oldProductoreal1OfDetalleproductorealSet1Detalleproductoreal = em.merge(oldProductoreal1OfDetalleproductorealSet1Detalleproductoreal);
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
            Codigodebarra codigobarra1Old = persistentProductoreal.getCodigobarra1();
            Codigodebarra codigobarra1New = productoreal.getCodigobarra1();
            Estadoproductoreal estadoOld = persistentProductoreal.getEstado();
            Estadoproductoreal estadoNew = productoreal.getEstado();
            Estadoproductoreal estado1Old = persistentProductoreal.getEstado1();
            Estadoproductoreal estado1New = productoreal.getEstado1();
            Pedido idpedidoOld = persistentProductoreal.getIdpedido();
            Pedido idpedidoNew = productoreal.getIdpedido();
            Pedido idpedido1Old = persistentProductoreal.getIdpedido1();
            Pedido idpedido1New = productoreal.getIdpedido1();
            Set<Detalleproductoreal> detalleproductorealSetOld = persistentProductoreal.getDetalleproductorealSet();
            Set<Detalleproductoreal> detalleproductorealSetNew = productoreal.getDetalleproductorealSet();
            Set<Detalleproductoreal> detalleproductorealSet1Old = persistentProductoreal.getDetalleproductorealSet1();
            Set<Detalleproductoreal> detalleproductorealSet1New = productoreal.getDetalleproductorealSet1();
            List<String> illegalOrphanMessages = null;
            for (Detalleproductoreal detalleproductorealSetOldDetalleproductoreal : detalleproductorealSetOld) {
                if (!detalleproductorealSetNew.contains(detalleproductorealSetOldDetalleproductoreal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleproductoreal " + detalleproductorealSetOldDetalleproductoreal + " since its productoreal field is not nullable.");
                }
            }
            for (Detalleproductoreal detalleproductorealSet1OldDetalleproductoreal : detalleproductorealSet1Old) {
                if (!detalleproductorealSet1New.contains(detalleproductorealSet1OldDetalleproductoreal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleproductoreal " + detalleproductorealSet1OldDetalleproductoreal + " since its productoreal1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codigobarraNew != null) {
                codigobarraNew = em.getReference(codigobarraNew.getClass(), codigobarraNew.getIdcodigo());
                productoreal.setCodigobarra(codigobarraNew);
            }
            if (codigobarra1New != null) {
                codigobarra1New = em.getReference(codigobarra1New.getClass(), codigobarra1New.getIdcodigo());
                productoreal.setCodigobarra1(codigobarra1New);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                productoreal.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                productoreal.setEstado1(estado1New);
            }
            if (idpedidoNew != null) {
                idpedidoNew = em.getReference(idpedidoNew.getClass(), idpedidoNew.getIdpedido());
                productoreal.setIdpedido(idpedidoNew);
            }
            if (idpedido1New != null) {
                idpedido1New = em.getReference(idpedido1New.getClass(), idpedido1New.getIdpedido());
                productoreal.setIdpedido1(idpedido1New);
            }
            Set<Detalleproductoreal> attachedDetalleproductorealSetNew = new HashSet<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealSetNewDetalleproductorealToAttach : detalleproductorealSetNew) {
                detalleproductorealSetNewDetalleproductorealToAttach = em.getReference(detalleproductorealSetNewDetalleproductorealToAttach.getClass(), detalleproductorealSetNewDetalleproductorealToAttach.getDetalleproductorealPK());
                attachedDetalleproductorealSetNew.add(detalleproductorealSetNewDetalleproductorealToAttach);
            }
            detalleproductorealSetNew = attachedDetalleproductorealSetNew;
            productoreal.setDetalleproductorealSet(detalleproductorealSetNew);
            Set<Detalleproductoreal> attachedDetalleproductorealSet1New = new HashSet<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealSet1NewDetalleproductorealToAttach : detalleproductorealSet1New) {
                detalleproductorealSet1NewDetalleproductorealToAttach = em.getReference(detalleproductorealSet1NewDetalleproductorealToAttach.getClass(), detalleproductorealSet1NewDetalleproductorealToAttach.getDetalleproductorealPK());
                attachedDetalleproductorealSet1New.add(detalleproductorealSet1NewDetalleproductorealToAttach);
            }
            detalleproductorealSet1New = attachedDetalleproductorealSet1New;
            productoreal.setDetalleproductorealSet1(detalleproductorealSet1New);
            productoreal = em.merge(productoreal);
            if (codigobarraOld != null && !codigobarraOld.equals(codigobarraNew)) {
                codigobarraOld.getProductorealSet().remove(productoreal);
                codigobarraOld = em.merge(codigobarraOld);
            }
            if (codigobarraNew != null && !codigobarraNew.equals(codigobarraOld)) {
                codigobarraNew.getProductorealSet().add(productoreal);
                codigobarraNew = em.merge(codigobarraNew);
            }
            if (codigobarra1Old != null && !codigobarra1Old.equals(codigobarra1New)) {
                codigobarra1Old.getProductorealSet().remove(productoreal);
                codigobarra1Old = em.merge(codigobarra1Old);
            }
            if (codigobarra1New != null && !codigobarra1New.equals(codigobarra1Old)) {
                codigobarra1New.getProductorealSet().add(productoreal);
                codigobarra1New = em.merge(codigobarra1New);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getProductorealSet().remove(productoreal);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getProductorealSet().add(productoreal);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getProductorealSet().remove(productoreal);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getProductorealSet().add(productoreal);
                estado1New = em.merge(estado1New);
            }
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getProductorealSet().remove(productoreal);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getProductorealSet().add(productoreal);
                idpedidoNew = em.merge(idpedidoNew);
            }
            if (idpedido1Old != null && !idpedido1Old.equals(idpedido1New)) {
                idpedido1Old.getProductorealSet().remove(productoreal);
                idpedido1Old = em.merge(idpedido1Old);
            }
            if (idpedido1New != null && !idpedido1New.equals(idpedido1Old)) {
                idpedido1New.getProductorealSet().add(productoreal);
                idpedido1New = em.merge(idpedido1New);
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
            for (Detalleproductoreal detalleproductorealSet1NewDetalleproductoreal : detalleproductorealSet1New) {
                if (!detalleproductorealSet1Old.contains(detalleproductorealSet1NewDetalleproductoreal)) {
                    Productoreal oldProductoreal1OfDetalleproductorealSet1NewDetalleproductoreal = detalleproductorealSet1NewDetalleproductoreal.getProductoreal1();
                    detalleproductorealSet1NewDetalleproductoreal.setProductoreal1(productoreal);
                    detalleproductorealSet1NewDetalleproductoreal = em.merge(detalleproductorealSet1NewDetalleproductoreal);
                    if (oldProductoreal1OfDetalleproductorealSet1NewDetalleproductoreal != null && !oldProductoreal1OfDetalleproductorealSet1NewDetalleproductoreal.equals(productoreal)) {
                        oldProductoreal1OfDetalleproductorealSet1NewDetalleproductoreal.getDetalleproductorealSet1().remove(detalleproductorealSet1NewDetalleproductoreal);
                        oldProductoreal1OfDetalleproductorealSet1NewDetalleproductoreal = em.merge(oldProductoreal1OfDetalleproductorealSet1NewDetalleproductoreal);
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
            Set<Detalleproductoreal> detalleproductorealSet1OrphanCheck = productoreal.getDetalleproductorealSet1();
            for (Detalleproductoreal detalleproductorealSet1OrphanCheckDetalleproductoreal : detalleproductorealSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productoreal (" + productoreal + ") cannot be destroyed since the Detalleproductoreal " + detalleproductorealSet1OrphanCheckDetalleproductoreal + " in its detalleproductorealSet1 field has a non-nullable productoreal1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codigodebarra codigobarra = productoreal.getCodigobarra();
            if (codigobarra != null) {
                codigobarra.getProductorealSet().remove(productoreal);
                codigobarra = em.merge(codigobarra);
            }
            Codigodebarra codigobarra1 = productoreal.getCodigobarra1();
            if (codigobarra1 != null) {
                codigobarra1.getProductorealSet().remove(productoreal);
                codigobarra1 = em.merge(codigobarra1);
            }
            Estadoproductoreal estado = productoreal.getEstado();
            if (estado != null) {
                estado.getProductorealSet().remove(productoreal);
                estado = em.merge(estado);
            }
            Estadoproductoreal estado1 = productoreal.getEstado1();
            if (estado1 != null) {
                estado1.getProductorealSet().remove(productoreal);
                estado1 = em.merge(estado1);
            }
            Pedido idpedido = productoreal.getIdpedido();
            if (idpedido != null) {
                idpedido.getProductorealSet().remove(productoreal);
                idpedido = em.merge(idpedido);
            }
            Pedido idpedido1 = productoreal.getIdpedido1();
            if (idpedido1 != null) {
                idpedido1.getProductorealSet().remove(productoreal);
                idpedido1 = em.merge(idpedido1);
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
