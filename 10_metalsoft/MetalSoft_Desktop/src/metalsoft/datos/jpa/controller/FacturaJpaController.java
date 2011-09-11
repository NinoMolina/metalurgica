/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Factura;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Tipoiva;
import metalsoft.datos.jpa.entity.Formadepago;
import metalsoft.datos.jpa.entity.Estadofactura;
import metalsoft.datos.jpa.entity.Pedido;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.Comprobantepago;

/**
 *
 * @author Nino
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) throws PreexistingEntityException, Exception {
        if (factura.getPedidoList() == null) {
            factura.setPedidoList(new ArrayList<Pedido>());
        }
        if (factura.getDetallefacturaList() == null) {
            factura.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        if (factura.getComprobantepagoList() == null) {
            factura.setComprobantepagoList(new ArrayList<Comprobantepago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = factura.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                factura.setUsuario(usuario);
            }
            Tipoiva tipoiva = factura.getTipoiva();
            if (tipoiva != null) {
                tipoiva = em.getReference(tipoiva.getClass(), tipoiva.getIdtipoiva());
                factura.setTipoiva(tipoiva);
            }
            Formadepago formapago = factura.getFormapago();
            if (formapago != null) {
                formapago = em.getReference(formapago.getClass(), formapago.getIdformapago());
                factura.setFormapago(formapago);
            }
            Estadofactura estado = factura.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                factura.setEstado(estado);
            }
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : factura.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            factura.setPedidoList(attachedPedidoList);
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : factura.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            factura.setDetallefacturaList(attachedDetallefacturaList);
            List<Comprobantepago> attachedComprobantepagoList = new ArrayList<Comprobantepago>();
            for (Comprobantepago comprobantepagoListComprobantepagoToAttach : factura.getComprobantepagoList()) {
                comprobantepagoListComprobantepagoToAttach = em.getReference(comprobantepagoListComprobantepagoToAttach.getClass(), comprobantepagoListComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoList.add(comprobantepagoListComprobantepagoToAttach);
            }
            factura.setComprobantepagoList(attachedComprobantepagoList);
            em.persist(factura);
            if (usuario != null) {
                usuario.getFacturaList().add(factura);
                usuario = em.merge(usuario);
            }
            if (tipoiva != null) {
                tipoiva.getFacturaList().add(factura);
                tipoiva = em.merge(tipoiva);
            }
            if (formapago != null) {
                formapago.getFacturaList().add(factura);
                formapago = em.merge(formapago);
            }
            if (estado != null) {
                estado.getFacturaList().add(factura);
                estado = em.merge(estado);
            }
            for (Pedido pedidoListPedido : factura.getPedidoList()) {
                Factura oldFacturaOfPedidoListPedido = pedidoListPedido.getFactura();
                pedidoListPedido.setFactura(factura);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldFacturaOfPedidoListPedido != null) {
                    oldFacturaOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldFacturaOfPedidoListPedido = em.merge(oldFacturaOfPedidoListPedido);
                }
            }
            for (Detallefactura detallefacturaListDetallefactura : factura.getDetallefacturaList()) {
                Factura oldFacturaOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getFactura();
                detallefacturaListDetallefactura.setFactura(factura);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldFacturaOfDetallefacturaListDetallefactura != null) {
                    oldFacturaOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldFacturaOfDetallefacturaListDetallefactura = em.merge(oldFacturaOfDetallefacturaListDetallefactura);
                }
            }
            for (Comprobantepago comprobantepagoListComprobantepago : factura.getComprobantepagoList()) {
                Factura oldFacturaOfComprobantepagoListComprobantepago = comprobantepagoListComprobantepago.getFactura();
                comprobantepagoListComprobantepago.setFactura(factura);
                comprobantepagoListComprobantepago = em.merge(comprobantepagoListComprobantepago);
                if (oldFacturaOfComprobantepagoListComprobantepago != null) {
                    oldFacturaOfComprobantepagoListComprobantepago.getComprobantepagoList().remove(comprobantepagoListComprobantepago);
                    oldFacturaOfComprobantepagoListComprobantepago = em.merge(oldFacturaOfComprobantepagoListComprobantepago);
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
            Usuario usuarioOld = persistentFactura.getUsuario();
            Usuario usuarioNew = factura.getUsuario();
            Tipoiva tipoivaOld = persistentFactura.getTipoiva();
            Tipoiva tipoivaNew = factura.getTipoiva();
            Formadepago formapagoOld = persistentFactura.getFormapago();
            Formadepago formapagoNew = factura.getFormapago();
            Estadofactura estadoOld = persistentFactura.getEstado();
            Estadofactura estadoNew = factura.getEstado();
            List<Pedido> pedidoListOld = persistentFactura.getPedidoList();
            List<Pedido> pedidoListNew = factura.getPedidoList();
            List<Detallefactura> detallefacturaListOld = persistentFactura.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = factura.getDetallefacturaList();
            List<Comprobantepago> comprobantepagoListOld = persistentFactura.getComprobantepagoList();
            List<Comprobantepago> comprobantepagoListNew = factura.getComprobantepagoList();
            List<String> illegalOrphanMessages = null;
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaListOldDetallefactura + " since its factura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                factura.setUsuario(usuarioNew);
            }
            if (tipoivaNew != null) {
                tipoivaNew = em.getReference(tipoivaNew.getClass(), tipoivaNew.getIdtipoiva());
                factura.setTipoiva(tipoivaNew);
            }
            if (formapagoNew != null) {
                formapagoNew = em.getReference(formapagoNew.getClass(), formapagoNew.getIdformapago());
                factura.setFormapago(formapagoNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                factura.setEstado(estadoNew);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdpedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            factura.setPedidoList(pedidoListNew);
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            factura.setDetallefacturaList(detallefacturaListNew);
            List<Comprobantepago> attachedComprobantepagoListNew = new ArrayList<Comprobantepago>();
            for (Comprobantepago comprobantepagoListNewComprobantepagoToAttach : comprobantepagoListNew) {
                comprobantepagoListNewComprobantepagoToAttach = em.getReference(comprobantepagoListNewComprobantepagoToAttach.getClass(), comprobantepagoListNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoListNew.add(comprobantepagoListNewComprobantepagoToAttach);
            }
            comprobantepagoListNew = attachedComprobantepagoListNew;
            factura.setComprobantepagoList(comprobantepagoListNew);
            factura = em.merge(factura);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getFacturaList().remove(factura);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getFacturaList().add(factura);
                usuarioNew = em.merge(usuarioNew);
            }
            if (tipoivaOld != null && !tipoivaOld.equals(tipoivaNew)) {
                tipoivaOld.getFacturaList().remove(factura);
                tipoivaOld = em.merge(tipoivaOld);
            }
            if (tipoivaNew != null && !tipoivaNew.equals(tipoivaOld)) {
                tipoivaNew.getFacturaList().add(factura);
                tipoivaNew = em.merge(tipoivaNew);
            }
            if (formapagoOld != null && !formapagoOld.equals(formapagoNew)) {
                formapagoOld.getFacturaList().remove(factura);
                formapagoOld = em.merge(formapagoOld);
            }
            if (formapagoNew != null && !formapagoNew.equals(formapagoOld)) {
                formapagoNew.getFacturaList().add(factura);
                formapagoNew = em.merge(formapagoNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getFacturaList().remove(factura);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getFacturaList().add(factura);
                estadoNew = em.merge(estadoNew);
            }
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setFactura(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Factura oldFacturaOfPedidoListNewPedido = pedidoListNewPedido.getFactura();
                    pedidoListNewPedido.setFactura(factura);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldFacturaOfPedidoListNewPedido != null && !oldFacturaOfPedidoListNewPedido.equals(factura)) {
                        oldFacturaOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldFacturaOfPedidoListNewPedido = em.merge(oldFacturaOfPedidoListNewPedido);
                    }
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Factura oldFacturaOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getFactura();
                    detallefacturaListNewDetallefactura.setFactura(factura);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldFacturaOfDetallefacturaListNewDetallefactura != null && !oldFacturaOfDetallefacturaListNewDetallefactura.equals(factura)) {
                        oldFacturaOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldFacturaOfDetallefacturaListNewDetallefactura = em.merge(oldFacturaOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            for (Comprobantepago comprobantepagoListOldComprobantepago : comprobantepagoListOld) {
                if (!comprobantepagoListNew.contains(comprobantepagoListOldComprobantepago)) {
                    comprobantepagoListOldComprobantepago.setFactura(null);
                    comprobantepagoListOldComprobantepago = em.merge(comprobantepagoListOldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoListNewComprobantepago : comprobantepagoListNew) {
                if (!comprobantepagoListOld.contains(comprobantepagoListNewComprobantepago)) {
                    Factura oldFacturaOfComprobantepagoListNewComprobantepago = comprobantepagoListNewComprobantepago.getFactura();
                    comprobantepagoListNewComprobantepago.setFactura(factura);
                    comprobantepagoListNewComprobantepago = em.merge(comprobantepagoListNewComprobantepago);
                    if (oldFacturaOfComprobantepagoListNewComprobantepago != null && !oldFacturaOfComprobantepagoListNewComprobantepago.equals(factura)) {
                        oldFacturaOfComprobantepagoListNewComprobantepago.getComprobantepagoList().remove(comprobantepagoListNewComprobantepago);
                        oldFacturaOfComprobantepagoListNewComprobantepago = em.merge(oldFacturaOfComprobantepagoListNewComprobantepago);
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
            List<Detallefactura> detallefacturaListOrphanCheck = factura.getDetallefacturaList();
            for (Detallefactura detallefacturaListOrphanCheckDetallefactura : detallefacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Detallefactura " + detallefacturaListOrphanCheckDetallefactura + " in its detallefacturaList field has a non-nullable factura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuario = factura.getUsuario();
            if (usuario != null) {
                usuario.getFacturaList().remove(factura);
                usuario = em.merge(usuario);
            }
            Tipoiva tipoiva = factura.getTipoiva();
            if (tipoiva != null) {
                tipoiva.getFacturaList().remove(factura);
                tipoiva = em.merge(tipoiva);
            }
            Formadepago formapago = factura.getFormapago();
            if (formapago != null) {
                formapago.getFacturaList().remove(factura);
                formapago = em.merge(formapago);
            }
            Estadofactura estado = factura.getEstado();
            if (estado != null) {
                estado.getFacturaList().remove(factura);
                estado = em.merge(estado);
            }
            List<Pedido> pedidoList = factura.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setFactura(null);
                pedidoListPedido = em.merge(pedidoListPedido);
            }
            List<Comprobantepago> comprobantepagoList = factura.getComprobantepagoList();
            for (Comprobantepago comprobantepagoListComprobantepago : comprobantepagoList) {
                comprobantepagoListComprobantepago.setFactura(null);
                comprobantepagoListComprobantepago = em.merge(comprobantepagoListComprobantepago);
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
