/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Factura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadofactura;
import entity.Formadepago;
import entity.Tipoiva;
import entity.Usuario;
import entity.Pedido;
import java.util.HashSet;
import java.util.Set;
import entity.Detallefactura;
import entity.Comprobantepago;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class FacturaJpaController {

    public FacturaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) throws PreexistingEntityException, Exception {
        if (factura.getPedidoSet() == null) {
            factura.setPedidoSet(new HashSet<Pedido>());
        }
        if (factura.getDetallefacturaSet() == null) {
            factura.setDetallefacturaSet(new HashSet<Detallefactura>());
        }
        if (factura.getComprobantepagoSet() == null) {
            factura.setComprobantepagoSet(new HashSet<Comprobantepago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadofactura estado = factura.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                factura.setEstado(estado);
            }
            Formadepago formapago = factura.getFormapago();
            if (formapago != null) {
                formapago = em.getReference(formapago.getClass(), formapago.getIdformapago());
                factura.setFormapago(formapago);
            }
            Tipoiva tipoiva = factura.getTipoiva();
            if (tipoiva != null) {
                tipoiva = em.getReference(tipoiva.getClass(), tipoiva.getIdtipoiva());
                factura.setTipoiva(tipoiva);
            }
            Usuario usuario = factura.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                factura.setUsuario(usuario);
            }
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : factura.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            factura.setPedidoSet(attachedPedidoSet);
            Set<Detallefactura> attachedDetallefacturaSet = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSetDetallefacturaToAttach : factura.getDetallefacturaSet()) {
                detallefacturaSetDetallefacturaToAttach = em.getReference(detallefacturaSetDetallefacturaToAttach.getClass(), detallefacturaSetDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSet.add(detallefacturaSetDetallefacturaToAttach);
            }
            factura.setDetallefacturaSet(attachedDetallefacturaSet);
            Set<Comprobantepago> attachedComprobantepagoSet = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetComprobantepagoToAttach : factura.getComprobantepagoSet()) {
                comprobantepagoSetComprobantepagoToAttach = em.getReference(comprobantepagoSetComprobantepagoToAttach.getClass(), comprobantepagoSetComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet.add(comprobantepagoSetComprobantepagoToAttach);
            }
            factura.setComprobantepagoSet(attachedComprobantepagoSet);
            em.persist(factura);
            if (estado != null) {
                estado.getFacturaSet().add(factura);
                estado = em.merge(estado);
            }
            if (formapago != null) {
                formapago.getFacturaSet().add(factura);
                formapago = em.merge(formapago);
            }
            if (tipoiva != null) {
                tipoiva.getFacturaSet().add(factura);
                tipoiva = em.merge(tipoiva);
            }
            if (usuario != null) {
                usuario.getFacturaSet().add(factura);
                usuario = em.merge(usuario);
            }
            for (Pedido pedidoSetPedido : factura.getPedidoSet()) {
                Factura oldFacturaOfPedidoSetPedido = pedidoSetPedido.getFactura();
                pedidoSetPedido.setFactura(factura);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldFacturaOfPedidoSetPedido != null) {
                    oldFacturaOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldFacturaOfPedidoSetPedido = em.merge(oldFacturaOfPedidoSetPedido);
                }
            }
            for (Detallefactura detallefacturaSetDetallefactura : factura.getDetallefacturaSet()) {
                Factura oldFacturaOfDetallefacturaSetDetallefactura = detallefacturaSetDetallefactura.getFactura();
                detallefacturaSetDetallefactura.setFactura(factura);
                detallefacturaSetDetallefactura = em.merge(detallefacturaSetDetallefactura);
                if (oldFacturaOfDetallefacturaSetDetallefactura != null) {
                    oldFacturaOfDetallefacturaSetDetallefactura.getDetallefacturaSet().remove(detallefacturaSetDetallefactura);
                    oldFacturaOfDetallefacturaSetDetallefactura = em.merge(oldFacturaOfDetallefacturaSetDetallefactura);
                }
            }
            for (Comprobantepago comprobantepagoSetComprobantepago : factura.getComprobantepagoSet()) {
                Factura oldFacturaOfComprobantepagoSetComprobantepago = comprobantepagoSetComprobantepago.getFactura();
                comprobantepagoSetComprobantepago.setFactura(factura);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
                if (oldFacturaOfComprobantepagoSetComprobantepago != null) {
                    oldFacturaOfComprobantepagoSetComprobantepago.getComprobantepagoSet().remove(comprobantepagoSetComprobantepago);
                    oldFacturaOfComprobantepagoSetComprobantepago = em.merge(oldFacturaOfComprobantepagoSetComprobantepago);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFactura(factura.getIdfactura()) != null) {
                throw new PreexistingEntityException("Factura " + factura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdfactura());
            Estadofactura estadoOld = persistentFactura.getEstado();
            Estadofactura estadoNew = factura.getEstado();
            Formadepago formapagoOld = persistentFactura.getFormapago();
            Formadepago formapagoNew = factura.getFormapago();
            Tipoiva tipoivaOld = persistentFactura.getTipoiva();
            Tipoiva tipoivaNew = factura.getTipoiva();
            Usuario usuarioOld = persistentFactura.getUsuario();
            Usuario usuarioNew = factura.getUsuario();
            Set<Pedido> pedidoSetOld = persistentFactura.getPedidoSet();
            Set<Pedido> pedidoSetNew = factura.getPedidoSet();
            Set<Detallefactura> detallefacturaSetOld = persistentFactura.getDetallefacturaSet();
            Set<Detallefactura> detallefacturaSetNew = factura.getDetallefacturaSet();
            Set<Comprobantepago> comprobantepagoSetOld = persistentFactura.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSetNew = factura.getComprobantepagoSet();
            List<String> illegalOrphanMessages = null;
            for (Detallefactura detallefacturaSetOldDetallefactura : detallefacturaSetOld) {
                if (!detallefacturaSetNew.contains(detallefacturaSetOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaSetOldDetallefactura + " since its factura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                factura.setEstado(estadoNew);
            }
            if (formapagoNew != null) {
                formapagoNew = em.getReference(formapagoNew.getClass(), formapagoNew.getIdformapago());
                factura.setFormapago(formapagoNew);
            }
            if (tipoivaNew != null) {
                tipoivaNew = em.getReference(tipoivaNew.getClass(), tipoivaNew.getIdtipoiva());
                factura.setTipoiva(tipoivaNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                factura.setUsuario(usuarioNew);
            }
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            factura.setPedidoSet(pedidoSetNew);
            Set<Detallefactura> attachedDetallefacturaSetNew = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSetNewDetallefacturaToAttach : detallefacturaSetNew) {
                detallefacturaSetNewDetallefacturaToAttach = em.getReference(detallefacturaSetNewDetallefacturaToAttach.getClass(), detallefacturaSetNewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSetNew.add(detallefacturaSetNewDetallefacturaToAttach);
            }
            detallefacturaSetNew = attachedDetallefacturaSetNew;
            factura.setDetallefacturaSet(detallefacturaSetNew);
            Set<Comprobantepago> attachedComprobantepagoSetNew = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetNewComprobantepagoToAttach : comprobantepagoSetNew) {
                comprobantepagoSetNewComprobantepagoToAttach = em.getReference(comprobantepagoSetNewComprobantepagoToAttach.getClass(), comprobantepagoSetNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSetNew.add(comprobantepagoSetNewComprobantepagoToAttach);
            }
            comprobantepagoSetNew = attachedComprobantepagoSetNew;
            factura.setComprobantepagoSet(comprobantepagoSetNew);
            factura = em.merge(factura);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getFacturaSet().remove(factura);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getFacturaSet().add(factura);
                estadoNew = em.merge(estadoNew);
            }
            if (formapagoOld != null && !formapagoOld.equals(formapagoNew)) {
                formapagoOld.getFacturaSet().remove(factura);
                formapagoOld = em.merge(formapagoOld);
            }
            if (formapagoNew != null && !formapagoNew.equals(formapagoOld)) {
                formapagoNew.getFacturaSet().add(factura);
                formapagoNew = em.merge(formapagoNew);
            }
            if (tipoivaOld != null && !tipoivaOld.equals(tipoivaNew)) {
                tipoivaOld.getFacturaSet().remove(factura);
                tipoivaOld = em.merge(tipoivaOld);
            }
            if (tipoivaNew != null && !tipoivaNew.equals(tipoivaOld)) {
                tipoivaNew.getFacturaSet().add(factura);
                tipoivaNew = em.merge(tipoivaNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getFacturaSet().remove(factura);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getFacturaSet().add(factura);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setFactura(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Factura oldFacturaOfPedidoSetNewPedido = pedidoSetNewPedido.getFactura();
                    pedidoSetNewPedido.setFactura(factura);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldFacturaOfPedidoSetNewPedido != null && !oldFacturaOfPedidoSetNewPedido.equals(factura)) {
                        oldFacturaOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldFacturaOfPedidoSetNewPedido = em.merge(oldFacturaOfPedidoSetNewPedido);
                    }
                }
            }
            for (Detallefactura detallefacturaSetNewDetallefactura : detallefacturaSetNew) {
                if (!detallefacturaSetOld.contains(detallefacturaSetNewDetallefactura)) {
                    Factura oldFacturaOfDetallefacturaSetNewDetallefactura = detallefacturaSetNewDetallefactura.getFactura();
                    detallefacturaSetNewDetallefactura.setFactura(factura);
                    detallefacturaSetNewDetallefactura = em.merge(detallefacturaSetNewDetallefactura);
                    if (oldFacturaOfDetallefacturaSetNewDetallefactura != null && !oldFacturaOfDetallefacturaSetNewDetallefactura.equals(factura)) {
                        oldFacturaOfDetallefacturaSetNewDetallefactura.getDetallefacturaSet().remove(detallefacturaSetNewDetallefactura);
                        oldFacturaOfDetallefacturaSetNewDetallefactura = em.merge(oldFacturaOfDetallefacturaSetNewDetallefactura);
                    }
                }
            }
            for (Comprobantepago comprobantepagoSetOldComprobantepago : comprobantepagoSetOld) {
                if (!comprobantepagoSetNew.contains(comprobantepagoSetOldComprobantepago)) {
                    comprobantepagoSetOldComprobantepago.setFactura(null);
                    comprobantepagoSetOldComprobantepago = em.merge(comprobantepagoSetOldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoSetNewComprobantepago : comprobantepagoSetNew) {
                if (!comprobantepagoSetOld.contains(comprobantepagoSetNewComprobantepago)) {
                    Factura oldFacturaOfComprobantepagoSetNewComprobantepago = comprobantepagoSetNewComprobantepago.getFactura();
                    comprobantepagoSetNewComprobantepago.setFactura(factura);
                    comprobantepagoSetNewComprobantepago = em.merge(comprobantepagoSetNewComprobantepago);
                    if (oldFacturaOfComprobantepagoSetNewComprobantepago != null && !oldFacturaOfComprobantepagoSetNewComprobantepago.equals(factura)) {
                        oldFacturaOfComprobantepagoSetNewComprobantepago.getComprobantepagoSet().remove(comprobantepagoSetNewComprobantepago);
                        oldFacturaOfComprobantepagoSetNewComprobantepago = em.merge(oldFacturaOfComprobantepagoSetNewComprobantepago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = factura.getIdfactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdfactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallefactura> detallefacturaSetOrphanCheck = factura.getDetallefacturaSet();
            for (Detallefactura detallefacturaSetOrphanCheckDetallefactura : detallefacturaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Detallefactura " + detallefacturaSetOrphanCheckDetallefactura + " in its detallefacturaSet field has a non-nullable factura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadofactura estado = factura.getEstado();
            if (estado != null) {
                estado.getFacturaSet().remove(factura);
                estado = em.merge(estado);
            }
            Formadepago formapago = factura.getFormapago();
            if (formapago != null) {
                formapago.getFacturaSet().remove(factura);
                formapago = em.merge(formapago);
            }
            Tipoiva tipoiva = factura.getTipoiva();
            if (tipoiva != null) {
                tipoiva.getFacturaSet().remove(factura);
                tipoiva = em.merge(tipoiva);
            }
            Usuario usuario = factura.getUsuario();
            if (usuario != null) {
                usuario.getFacturaSet().remove(factura);
                usuario = em.merge(usuario);
            }
            Set<Pedido> pedidoSet = factura.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setFactura(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Comprobantepago> comprobantepagoSet = factura.getComprobantepagoSet();
            for (Comprobantepago comprobantepagoSetComprobantepago : comprobantepagoSet) {
                comprobantepagoSetComprobantepago.setFactura(null);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
