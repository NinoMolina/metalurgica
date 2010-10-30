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
        if (factura.getPedidoSet1() == null) {
            factura.setPedidoSet1(new HashSet<Pedido>());
        }
        if (factura.getDetallefacturaSet() == null) {
            factura.setDetallefacturaSet(new HashSet<Detallefactura>());
        }
        if (factura.getDetallefacturaSet1() == null) {
            factura.setDetallefacturaSet1(new HashSet<Detallefactura>());
        }
        if (factura.getComprobantepagoSet() == null) {
            factura.setComprobantepagoSet(new HashSet<Comprobantepago>());
        }
        if (factura.getComprobantepagoSet1() == null) {
            factura.setComprobantepagoSet1(new HashSet<Comprobantepago>());
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
            Estadofactura estado1 = factura.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                factura.setEstado1(estado1);
            }
            Formadepago formapago = factura.getFormapago();
            if (formapago != null) {
                formapago = em.getReference(formapago.getClass(), formapago.getIdformapago());
                factura.setFormapago(formapago);
            }
            Formadepago formapago1 = factura.getFormapago1();
            if (formapago1 != null) {
                formapago1 = em.getReference(formapago1.getClass(), formapago1.getIdformapago());
                factura.setFormapago1(formapago1);
            }
            Tipoiva tipoiva = factura.getTipoiva();
            if (tipoiva != null) {
                tipoiva = em.getReference(tipoiva.getClass(), tipoiva.getIdtipoiva());
                factura.setTipoiva(tipoiva);
            }
            Tipoiva tipoiva1 = factura.getTipoiva1();
            if (tipoiva1 != null) {
                tipoiva1 = em.getReference(tipoiva1.getClass(), tipoiva1.getIdtipoiva());
                factura.setTipoiva1(tipoiva1);
            }
            Usuario usuario = factura.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                factura.setUsuario(usuario);
            }
            Usuario usuario1 = factura.getUsuario1();
            if (usuario1 != null) {
                usuario1 = em.getReference(usuario1.getClass(), usuario1.getIdusuario());
                factura.setUsuario1(usuario1);
            }
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : factura.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            factura.setPedidoSet(attachedPedidoSet);
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : factura.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            factura.setPedidoSet1(attachedPedidoSet1);
            Set<Detallefactura> attachedDetallefacturaSet = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSetDetallefacturaToAttach : factura.getDetallefacturaSet()) {
                detallefacturaSetDetallefacturaToAttach = em.getReference(detallefacturaSetDetallefacturaToAttach.getClass(), detallefacturaSetDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSet.add(detallefacturaSetDetallefacturaToAttach);
            }
            factura.setDetallefacturaSet(attachedDetallefacturaSet);
            Set<Detallefactura> attachedDetallefacturaSet1 = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSet1DetallefacturaToAttach : factura.getDetallefacturaSet1()) {
                detallefacturaSet1DetallefacturaToAttach = em.getReference(detallefacturaSet1DetallefacturaToAttach.getClass(), detallefacturaSet1DetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSet1.add(detallefacturaSet1DetallefacturaToAttach);
            }
            factura.setDetallefacturaSet1(attachedDetallefacturaSet1);
            Set<Comprobantepago> attachedComprobantepagoSet = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetComprobantepagoToAttach : factura.getComprobantepagoSet()) {
                comprobantepagoSetComprobantepagoToAttach = em.getReference(comprobantepagoSetComprobantepagoToAttach.getClass(), comprobantepagoSetComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet.add(comprobantepagoSetComprobantepagoToAttach);
            }
            factura.setComprobantepagoSet(attachedComprobantepagoSet);
            Set<Comprobantepago> attachedComprobantepagoSet1 = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSet1ComprobantepagoToAttach : factura.getComprobantepagoSet1()) {
                comprobantepagoSet1ComprobantepagoToAttach = em.getReference(comprobantepagoSet1ComprobantepagoToAttach.getClass(), comprobantepagoSet1ComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet1.add(comprobantepagoSet1ComprobantepagoToAttach);
            }
            factura.setComprobantepagoSet1(attachedComprobantepagoSet1);
            em.persist(factura);
            if (estado != null) {
                estado.getFacturaSet().add(factura);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getFacturaSet().add(factura);
                estado1 = em.merge(estado1);
            }
            if (formapago != null) {
                formapago.getFacturaSet().add(factura);
                formapago = em.merge(formapago);
            }
            if (formapago1 != null) {
                formapago1.getFacturaSet().add(factura);
                formapago1 = em.merge(formapago1);
            }
            if (tipoiva != null) {
                tipoiva.getFacturaSet().add(factura);
                tipoiva = em.merge(tipoiva);
            }
            if (tipoiva1 != null) {
                tipoiva1.getFacturaSet().add(factura);
                tipoiva1 = em.merge(tipoiva1);
            }
            if (usuario != null) {
                usuario.getFacturaSet().add(factura);
                usuario = em.merge(usuario);
            }
            if (usuario1 != null) {
                usuario1.getFacturaSet().add(factura);
                usuario1 = em.merge(usuario1);
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
            for (Pedido pedidoSet1Pedido : factura.getPedidoSet1()) {
                Factura oldFactura1OfPedidoSet1Pedido = pedidoSet1Pedido.getFactura1();
                pedidoSet1Pedido.setFactura1(factura);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldFactura1OfPedidoSet1Pedido != null) {
                    oldFactura1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldFactura1OfPedidoSet1Pedido = em.merge(oldFactura1OfPedidoSet1Pedido);
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
            for (Detallefactura detallefacturaSet1Detallefactura : factura.getDetallefacturaSet1()) {
                Factura oldFactura1OfDetallefacturaSet1Detallefactura = detallefacturaSet1Detallefactura.getFactura1();
                detallefacturaSet1Detallefactura.setFactura1(factura);
                detallefacturaSet1Detallefactura = em.merge(detallefacturaSet1Detallefactura);
                if (oldFactura1OfDetallefacturaSet1Detallefactura != null) {
                    oldFactura1OfDetallefacturaSet1Detallefactura.getDetallefacturaSet1().remove(detallefacturaSet1Detallefactura);
                    oldFactura1OfDetallefacturaSet1Detallefactura = em.merge(oldFactura1OfDetallefacturaSet1Detallefactura);
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
            for (Comprobantepago comprobantepagoSet1Comprobantepago : factura.getComprobantepagoSet1()) {
                Factura oldFactura1OfComprobantepagoSet1Comprobantepago = comprobantepagoSet1Comprobantepago.getFactura1();
                comprobantepagoSet1Comprobantepago.setFactura1(factura);
                comprobantepagoSet1Comprobantepago = em.merge(comprobantepagoSet1Comprobantepago);
                if (oldFactura1OfComprobantepagoSet1Comprobantepago != null) {
                    oldFactura1OfComprobantepagoSet1Comprobantepago.getComprobantepagoSet1().remove(comprobantepagoSet1Comprobantepago);
                    oldFactura1OfComprobantepagoSet1Comprobantepago = em.merge(oldFactura1OfComprobantepagoSet1Comprobantepago);
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
            Estadofactura estado1Old = persistentFactura.getEstado1();
            Estadofactura estado1New = factura.getEstado1();
            Formadepago formapagoOld = persistentFactura.getFormapago();
            Formadepago formapagoNew = factura.getFormapago();
            Formadepago formapago1Old = persistentFactura.getFormapago1();
            Formadepago formapago1New = factura.getFormapago1();
            Tipoiva tipoivaOld = persistentFactura.getTipoiva();
            Tipoiva tipoivaNew = factura.getTipoiva();
            Tipoiva tipoiva1Old = persistentFactura.getTipoiva1();
            Tipoiva tipoiva1New = factura.getTipoiva1();
            Usuario usuarioOld = persistentFactura.getUsuario();
            Usuario usuarioNew = factura.getUsuario();
            Usuario usuario1Old = persistentFactura.getUsuario1();
            Usuario usuario1New = factura.getUsuario1();
            Set<Pedido> pedidoSetOld = persistentFactura.getPedidoSet();
            Set<Pedido> pedidoSetNew = factura.getPedidoSet();
            Set<Pedido> pedidoSet1Old = persistentFactura.getPedidoSet1();
            Set<Pedido> pedidoSet1New = factura.getPedidoSet1();
            Set<Detallefactura> detallefacturaSetOld = persistentFactura.getDetallefacturaSet();
            Set<Detallefactura> detallefacturaSetNew = factura.getDetallefacturaSet();
            Set<Detallefactura> detallefacturaSet1Old = persistentFactura.getDetallefacturaSet1();
            Set<Detallefactura> detallefacturaSet1New = factura.getDetallefacturaSet1();
            Set<Comprobantepago> comprobantepagoSetOld = persistentFactura.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSetNew = factura.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSet1Old = persistentFactura.getComprobantepagoSet1();
            Set<Comprobantepago> comprobantepagoSet1New = factura.getComprobantepagoSet1();
            List<String> illegalOrphanMessages = null;
            for (Detallefactura detallefacturaSetOldDetallefactura : detallefacturaSetOld) {
                if (!detallefacturaSetNew.contains(detallefacturaSetOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaSetOldDetallefactura + " since its factura field is not nullable.");
                }
            }
            for (Detallefactura detallefacturaSet1OldDetallefactura : detallefacturaSet1Old) {
                if (!detallefacturaSet1New.contains(detallefacturaSet1OldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaSet1OldDetallefactura + " since its factura1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                factura.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                factura.setEstado1(estado1New);
            }
            if (formapagoNew != null) {
                formapagoNew = em.getReference(formapagoNew.getClass(), formapagoNew.getIdformapago());
                factura.setFormapago(formapagoNew);
            }
            if (formapago1New != null) {
                formapago1New = em.getReference(formapago1New.getClass(), formapago1New.getIdformapago());
                factura.setFormapago1(formapago1New);
            }
            if (tipoivaNew != null) {
                tipoivaNew = em.getReference(tipoivaNew.getClass(), tipoivaNew.getIdtipoiva());
                factura.setTipoiva(tipoivaNew);
            }
            if (tipoiva1New != null) {
                tipoiva1New = em.getReference(tipoiva1New.getClass(), tipoiva1New.getIdtipoiva());
                factura.setTipoiva1(tipoiva1New);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                factura.setUsuario(usuarioNew);
            }
            if (usuario1New != null) {
                usuario1New = em.getReference(usuario1New.getClass(), usuario1New.getIdusuario());
                factura.setUsuario1(usuario1New);
            }
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            factura.setPedidoSet(pedidoSetNew);
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            factura.setPedidoSet1(pedidoSet1New);
            Set<Detallefactura> attachedDetallefacturaSetNew = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSetNewDetallefacturaToAttach : detallefacturaSetNew) {
                detallefacturaSetNewDetallefacturaToAttach = em.getReference(detallefacturaSetNewDetallefacturaToAttach.getClass(), detallefacturaSetNewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSetNew.add(detallefacturaSetNewDetallefacturaToAttach);
            }
            detallefacturaSetNew = attachedDetallefacturaSetNew;
            factura.setDetallefacturaSet(detallefacturaSetNew);
            Set<Detallefactura> attachedDetallefacturaSet1New = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSet1NewDetallefacturaToAttach : detallefacturaSet1New) {
                detallefacturaSet1NewDetallefacturaToAttach = em.getReference(detallefacturaSet1NewDetallefacturaToAttach.getClass(), detallefacturaSet1NewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSet1New.add(detallefacturaSet1NewDetallefacturaToAttach);
            }
            detallefacturaSet1New = attachedDetallefacturaSet1New;
            factura.setDetallefacturaSet1(detallefacturaSet1New);
            Set<Comprobantepago> attachedComprobantepagoSetNew = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetNewComprobantepagoToAttach : comprobantepagoSetNew) {
                comprobantepagoSetNewComprobantepagoToAttach = em.getReference(comprobantepagoSetNewComprobantepagoToAttach.getClass(), comprobantepagoSetNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSetNew.add(comprobantepagoSetNewComprobantepagoToAttach);
            }
            comprobantepagoSetNew = attachedComprobantepagoSetNew;
            factura.setComprobantepagoSet(comprobantepagoSetNew);
            Set<Comprobantepago> attachedComprobantepagoSet1New = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSet1NewComprobantepagoToAttach : comprobantepagoSet1New) {
                comprobantepagoSet1NewComprobantepagoToAttach = em.getReference(comprobantepagoSet1NewComprobantepagoToAttach.getClass(), comprobantepagoSet1NewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet1New.add(comprobantepagoSet1NewComprobantepagoToAttach);
            }
            comprobantepagoSet1New = attachedComprobantepagoSet1New;
            factura.setComprobantepagoSet1(comprobantepagoSet1New);
            factura = em.merge(factura);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getFacturaSet().remove(factura);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getFacturaSet().add(factura);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getFacturaSet().remove(factura);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getFacturaSet().add(factura);
                estado1New = em.merge(estado1New);
            }
            if (formapagoOld != null && !formapagoOld.equals(formapagoNew)) {
                formapagoOld.getFacturaSet().remove(factura);
                formapagoOld = em.merge(formapagoOld);
            }
            if (formapagoNew != null && !formapagoNew.equals(formapagoOld)) {
                formapagoNew.getFacturaSet().add(factura);
                formapagoNew = em.merge(formapagoNew);
            }
            if (formapago1Old != null && !formapago1Old.equals(formapago1New)) {
                formapago1Old.getFacturaSet().remove(factura);
                formapago1Old = em.merge(formapago1Old);
            }
            if (formapago1New != null && !formapago1New.equals(formapago1Old)) {
                formapago1New.getFacturaSet().add(factura);
                formapago1New = em.merge(formapago1New);
            }
            if (tipoivaOld != null && !tipoivaOld.equals(tipoivaNew)) {
                tipoivaOld.getFacturaSet().remove(factura);
                tipoivaOld = em.merge(tipoivaOld);
            }
            if (tipoivaNew != null && !tipoivaNew.equals(tipoivaOld)) {
                tipoivaNew.getFacturaSet().add(factura);
                tipoivaNew = em.merge(tipoivaNew);
            }
            if (tipoiva1Old != null && !tipoiva1Old.equals(tipoiva1New)) {
                tipoiva1Old.getFacturaSet().remove(factura);
                tipoiva1Old = em.merge(tipoiva1Old);
            }
            if (tipoiva1New != null && !tipoiva1New.equals(tipoiva1Old)) {
                tipoiva1New.getFacturaSet().add(factura);
                tipoiva1New = em.merge(tipoiva1New);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getFacturaSet().remove(factura);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getFacturaSet().add(factura);
                usuarioNew = em.merge(usuarioNew);
            }
            if (usuario1Old != null && !usuario1Old.equals(usuario1New)) {
                usuario1Old.getFacturaSet().remove(factura);
                usuario1Old = em.merge(usuario1Old);
            }
            if (usuario1New != null && !usuario1New.equals(usuario1Old)) {
                usuario1New.getFacturaSet().add(factura);
                usuario1New = em.merge(usuario1New);
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
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    pedidoSet1OldPedido.setFactura1(null);
                    pedidoSet1OldPedido = em.merge(pedidoSet1OldPedido);
                }
            }
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Factura oldFactura1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getFactura1();
                    pedidoSet1NewPedido.setFactura1(factura);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldFactura1OfPedidoSet1NewPedido != null && !oldFactura1OfPedidoSet1NewPedido.equals(factura)) {
                        oldFactura1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldFactura1OfPedidoSet1NewPedido = em.merge(oldFactura1OfPedidoSet1NewPedido);
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
            for (Detallefactura detallefacturaSet1NewDetallefactura : detallefacturaSet1New) {
                if (!detallefacturaSet1Old.contains(detallefacturaSet1NewDetallefactura)) {
                    Factura oldFactura1OfDetallefacturaSet1NewDetallefactura = detallefacturaSet1NewDetallefactura.getFactura1();
                    detallefacturaSet1NewDetallefactura.setFactura1(factura);
                    detallefacturaSet1NewDetallefactura = em.merge(detallefacturaSet1NewDetallefactura);
                    if (oldFactura1OfDetallefacturaSet1NewDetallefactura != null && !oldFactura1OfDetallefacturaSet1NewDetallefactura.equals(factura)) {
                        oldFactura1OfDetallefacturaSet1NewDetallefactura.getDetallefacturaSet1().remove(detallefacturaSet1NewDetallefactura);
                        oldFactura1OfDetallefacturaSet1NewDetallefactura = em.merge(oldFactura1OfDetallefacturaSet1NewDetallefactura);
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
            for (Comprobantepago comprobantepagoSet1OldComprobantepago : comprobantepagoSet1Old) {
                if (!comprobantepagoSet1New.contains(comprobantepagoSet1OldComprobantepago)) {
                    comprobantepagoSet1OldComprobantepago.setFactura1(null);
                    comprobantepagoSet1OldComprobantepago = em.merge(comprobantepagoSet1OldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoSet1NewComprobantepago : comprobantepagoSet1New) {
                if (!comprobantepagoSet1Old.contains(comprobantepagoSet1NewComprobantepago)) {
                    Factura oldFactura1OfComprobantepagoSet1NewComprobantepago = comprobantepagoSet1NewComprobantepago.getFactura1();
                    comprobantepagoSet1NewComprobantepago.setFactura1(factura);
                    comprobantepagoSet1NewComprobantepago = em.merge(comprobantepagoSet1NewComprobantepago);
                    if (oldFactura1OfComprobantepagoSet1NewComprobantepago != null && !oldFactura1OfComprobantepagoSet1NewComprobantepago.equals(factura)) {
                        oldFactura1OfComprobantepagoSet1NewComprobantepago.getComprobantepagoSet1().remove(comprobantepagoSet1NewComprobantepago);
                        oldFactura1OfComprobantepagoSet1NewComprobantepago = em.merge(oldFactura1OfComprobantepagoSet1NewComprobantepago);
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
            Set<Detallefactura> detallefacturaSet1OrphanCheck = factura.getDetallefacturaSet1();
            for (Detallefactura detallefacturaSet1OrphanCheckDetallefactura : detallefacturaSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Detallefactura " + detallefacturaSet1OrphanCheckDetallefactura + " in its detallefacturaSet1 field has a non-nullable factura1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadofactura estado = factura.getEstado();
            if (estado != null) {
                estado.getFacturaSet().remove(factura);
                estado = em.merge(estado);
            }
            Estadofactura estado1 = factura.getEstado1();
            if (estado1 != null) {
                estado1.getFacturaSet().remove(factura);
                estado1 = em.merge(estado1);
            }
            Formadepago formapago = factura.getFormapago();
            if (formapago != null) {
                formapago.getFacturaSet().remove(factura);
                formapago = em.merge(formapago);
            }
            Formadepago formapago1 = factura.getFormapago1();
            if (formapago1 != null) {
                formapago1.getFacturaSet().remove(factura);
                formapago1 = em.merge(formapago1);
            }
            Tipoiva tipoiva = factura.getTipoiva();
            if (tipoiva != null) {
                tipoiva.getFacturaSet().remove(factura);
                tipoiva = em.merge(tipoiva);
            }
            Tipoiva tipoiva1 = factura.getTipoiva1();
            if (tipoiva1 != null) {
                tipoiva1.getFacturaSet().remove(factura);
                tipoiva1 = em.merge(tipoiva1);
            }
            Usuario usuario = factura.getUsuario();
            if (usuario != null) {
                usuario.getFacturaSet().remove(factura);
                usuario = em.merge(usuario);
            }
            Usuario usuario1 = factura.getUsuario1();
            if (usuario1 != null) {
                usuario1.getFacturaSet().remove(factura);
                usuario1 = em.merge(usuario1);
            }
            Set<Pedido> pedidoSet = factura.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setFactura(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Pedido> pedidoSet1 = factura.getPedidoSet1();
            for (Pedido pedidoSet1Pedido : pedidoSet1) {
                pedidoSet1Pedido.setFactura1(null);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
            }
            Set<Comprobantepago> comprobantepagoSet = factura.getComprobantepagoSet();
            for (Comprobantepago comprobantepagoSetComprobantepago : comprobantepagoSet) {
                comprobantepagoSetComprobantepago.setFactura(null);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
            }
            Set<Comprobantepago> comprobantepagoSet1 = factura.getComprobantepagoSet1();
            for (Comprobantepago comprobantepagoSet1Comprobantepago : comprobantepagoSet1) {
                comprobantepagoSet1Comprobantepago.setFactura1(null);
                comprobantepagoSet1Comprobantepago = em.merge(comprobantepagoSet1Comprobantepago);
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
