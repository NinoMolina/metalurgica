/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Codigodebarra;
import metalsoft.datos.jpa.entity.Estadoproductoreal;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Detalleproductoreal;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Productoreal;

/**
 *
 * @author Nino
 */
public class ProductorealJpaController {

    public ProductorealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productoreal productoreal) throws PreexistingEntityException, Exception {
        if (productoreal.getDetalleproductorealList() == null) {
            productoreal.setDetalleproductorealList(new ArrayList<Detalleproductoreal>());
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
            List<Detalleproductoreal> attachedDetalleproductorealList = new ArrayList<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealListDetalleproductorealToAttach : productoreal.getDetalleproductorealList()) {
                detalleproductorealListDetalleproductorealToAttach = em.getReference(detalleproductorealListDetalleproductorealToAttach.getClass(), detalleproductorealListDetalleproductorealToAttach.getDetalleproductorealPK());
                attachedDetalleproductorealList.add(detalleproductorealListDetalleproductorealToAttach);
            }
            productoreal.setDetalleproductorealList(attachedDetalleproductorealList);
            em.persist(productoreal);
            if (codigobarra != null) {
                codigobarra.getProductorealList().add(productoreal);
                codigobarra = em.merge(codigobarra);
            }
            if (estado != null) {
                estado.getProductorealList().add(productoreal);
                estado = em.merge(estado);
            }
            if (idpedido != null) {
                idpedido.getProductorealList().add(productoreal);
                idpedido = em.merge(idpedido);
            }
            for (Detalleproductoreal detalleproductorealListDetalleproductoreal : productoreal.getDetalleproductorealList()) {
                Productoreal oldProductorealOfDetalleproductorealListDetalleproductoreal = detalleproductorealListDetalleproductoreal.getProductoreal();
                detalleproductorealListDetalleproductoreal.setProductoreal(productoreal);
                detalleproductorealListDetalleproductoreal = em.merge(detalleproductorealListDetalleproductoreal);
                if (oldProductorealOfDetalleproductorealListDetalleproductoreal != null) {
                    oldProductorealOfDetalleproductorealListDetalleproductoreal.getDetalleproductorealList().remove(detalleproductorealListDetalleproductoreal);
                    oldProductorealOfDetalleproductorealListDetalleproductoreal = em.merge(oldProductorealOfDetalleproductorealListDetalleproductoreal);
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
            List<Detalleproductoreal> detalleproductorealListOld = persistentProductoreal.getDetalleproductorealList();
            List<Detalleproductoreal> detalleproductorealListNew = productoreal.getDetalleproductorealList();
            List<String> illegalOrphanMessages = null;
            for (Detalleproductoreal detalleproductorealListOldDetalleproductoreal : detalleproductorealListOld) {
                if (!detalleproductorealListNew.contains(detalleproductorealListOldDetalleproductoreal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleproductoreal " + detalleproductorealListOldDetalleproductoreal + " since its productoreal field is not nullable.");
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
            List<Detalleproductoreal> attachedDetalleproductorealListNew = new ArrayList<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealListNewDetalleproductorealToAttach : detalleproductorealListNew) {
                detalleproductorealListNewDetalleproductorealToAttach = em.getReference(detalleproductorealListNewDetalleproductorealToAttach.getClass(), detalleproductorealListNewDetalleproductorealToAttach.getDetalleproductorealPK());
                attachedDetalleproductorealListNew.add(detalleproductorealListNewDetalleproductorealToAttach);
            }
            detalleproductorealListNew = attachedDetalleproductorealListNew;
            productoreal.setDetalleproductorealList(detalleproductorealListNew);
            productoreal = em.merge(productoreal);
            if (codigobarraOld != null && !codigobarraOld.equals(codigobarraNew)) {
                codigobarraOld.getProductorealList().remove(productoreal);
                codigobarraOld = em.merge(codigobarraOld);
            }
            if (codigobarraNew != null && !codigobarraNew.equals(codigobarraOld)) {
                codigobarraNew.getProductorealList().add(productoreal);
                codigobarraNew = em.merge(codigobarraNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getProductorealList().remove(productoreal);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getProductorealList().add(productoreal);
                estadoNew = em.merge(estadoNew);
            }
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getProductorealList().remove(productoreal);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getProductorealList().add(productoreal);
                idpedidoNew = em.merge(idpedidoNew);
            }
            for (Detalleproductoreal detalleproductorealListNewDetalleproductoreal : detalleproductorealListNew) {
                if (!detalleproductorealListOld.contains(detalleproductorealListNewDetalleproductoreal)) {
                    Productoreal oldProductorealOfDetalleproductorealListNewDetalleproductoreal = detalleproductorealListNewDetalleproductoreal.getProductoreal();
                    detalleproductorealListNewDetalleproductoreal.setProductoreal(productoreal);
                    detalleproductorealListNewDetalleproductoreal = em.merge(detalleproductorealListNewDetalleproductoreal);
                    if (oldProductorealOfDetalleproductorealListNewDetalleproductoreal != null && !oldProductorealOfDetalleproductorealListNewDetalleproductoreal.equals(productoreal)) {
                        oldProductorealOfDetalleproductorealListNewDetalleproductoreal.getDetalleproductorealList().remove(detalleproductorealListNewDetalleproductoreal);
                        oldProductorealOfDetalleproductorealListNewDetalleproductoreal = em.merge(oldProductorealOfDetalleproductorealListNewDetalleproductoreal);
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
            List<Detalleproductoreal> detalleproductorealListOrphanCheck = productoreal.getDetalleproductorealList();
            for (Detalleproductoreal detalleproductorealListOrphanCheckDetalleproductoreal : detalleproductorealListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productoreal (" + productoreal + ") cannot be destroyed since the Detalleproductoreal " + detalleproductorealListOrphanCheckDetalleproductoreal + " in its detalleproductorealList field has a non-nullable productoreal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codigodebarra codigobarra = productoreal.getCodigobarra();
            if (codigobarra != null) {
                codigobarra.getProductorealList().remove(productoreal);
                codigobarra = em.merge(codigobarra);
            }
            Estadoproductoreal estado = productoreal.getEstado();
            if (estado != null) {
                estado.getProductorealList().remove(productoreal);
                estado = em.merge(estado);
            }
            Pedido idpedido = productoreal.getIdpedido();
            if (idpedido != null) {
                idpedido.getProductorealList().remove(productoreal);
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
