/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Condicioniva;
import entity.Domicilio;
import entity.Estadocliente;
import entity.Prioridad;
import entity.Responsable;
import entity.Usuario;
import entity.Pedido;
import java.util.HashSet;
import java.util.Set;
import entity.Reclamocliente;

/**
 *
 * @author Nino
 */
public class ClienteJpaController {

    public ClienteJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getPedidoSet() == null) {
            cliente.setPedidoSet(new HashSet<Pedido>());
        }
        if (cliente.getPedidoSet1() == null) {
            cliente.setPedidoSet1(new HashSet<Pedido>());
        }
        if (cliente.getReclamoclienteSet() == null) {
            cliente.setReclamoclienteSet(new HashSet<Reclamocliente>());
        }
        if (cliente.getReclamoclienteSet1() == null) {
            cliente.setReclamoclienteSet1(new HashSet<Reclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condicioniva condicioniva = cliente.getCondicioniva();
            if (condicioniva != null) {
                condicioniva = em.getReference(condicioniva.getClass(), condicioniva.getIdcondicioniva());
                cliente.setCondicioniva(condicioniva);
            }
            Condicioniva condicioniva1 = cliente.getCondicioniva1();
            if (condicioniva1 != null) {
                condicioniva1 = em.getReference(condicioniva1.getClass(), condicioniva1.getIdcondicioniva());
                cliente.setCondicioniva1(condicioniva1);
            }
            Domicilio domicilio = cliente.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                cliente.setDomicilio(domicilio);
            }
            Domicilio domicilio1 = cliente.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1 = em.getReference(domicilio1.getClass(), domicilio1.getIddomicilio());
                cliente.setDomicilio1(domicilio1);
            }
            Estadocliente estado = cliente.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                cliente.setEstado(estado);
            }
            Estadocliente estado1 = cliente.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                cliente.setEstado1(estado1);
            }
            Prioridad prioridad = cliente.getPrioridad();
            if (prioridad != null) {
                prioridad = em.getReference(prioridad.getClass(), prioridad.getIdprioridad());
                cliente.setPrioridad(prioridad);
            }
            Prioridad prioridad1 = cliente.getPrioridad1();
            if (prioridad1 != null) {
                prioridad1 = em.getReference(prioridad1.getClass(), prioridad1.getIdprioridad());
                cliente.setPrioridad1(prioridad1);
            }
            Responsable responsable = cliente.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                cliente.setResponsable(responsable);
            }
            Responsable responsable1 = cliente.getResponsable1();
            if (responsable1 != null) {
                responsable1 = em.getReference(responsable1.getClass(), responsable1.getIdresponsable());
                cliente.setResponsable1(responsable1);
            }
            Usuario usuario = cliente.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                cliente.setUsuario(usuario);
            }
            Usuario usuario1 = cliente.getUsuario1();
            if (usuario1 != null) {
                usuario1 = em.getReference(usuario1.getClass(), usuario1.getIdusuario());
                cliente.setUsuario1(usuario1);
            }
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : cliente.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            cliente.setPedidoSet(attachedPedidoSet);
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : cliente.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            cliente.setPedidoSet1(attachedPedidoSet1);
            Set<Reclamocliente> attachedReclamoclienteSet = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSetReclamoclienteToAttach : cliente.getReclamoclienteSet()) {
                reclamoclienteSetReclamoclienteToAttach = em.getReference(reclamoclienteSetReclamoclienteToAttach.getClass(), reclamoclienteSetReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSet.add(reclamoclienteSetReclamoclienteToAttach);
            }
            cliente.setReclamoclienteSet(attachedReclamoclienteSet);
            Set<Reclamocliente> attachedReclamoclienteSet1 = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSet1ReclamoclienteToAttach : cliente.getReclamoclienteSet1()) {
                reclamoclienteSet1ReclamoclienteToAttach = em.getReference(reclamoclienteSet1ReclamoclienteToAttach.getClass(), reclamoclienteSet1ReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSet1.add(reclamoclienteSet1ReclamoclienteToAttach);
            }
            cliente.setReclamoclienteSet1(attachedReclamoclienteSet1);
            em.persist(cliente);
            if (condicioniva != null) {
                condicioniva.getClienteSet().add(cliente);
                condicioniva = em.merge(condicioniva);
            }
            if (condicioniva1 != null) {
                condicioniva1.getClienteSet().add(cliente);
                condicioniva1 = em.merge(condicioniva1);
            }
            if (domicilio != null) {
                domicilio.getClienteSet().add(cliente);
                domicilio = em.merge(domicilio);
            }
            if (domicilio1 != null) {
                domicilio1.getClienteSet().add(cliente);
                domicilio1 = em.merge(domicilio1);
            }
            if (estado != null) {
                estado.getClienteSet().add(cliente);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getClienteSet().add(cliente);
                estado1 = em.merge(estado1);
            }
            if (prioridad != null) {
                prioridad.getClienteSet().add(cliente);
                prioridad = em.merge(prioridad);
            }
            if (prioridad1 != null) {
                prioridad1.getClienteSet().add(cliente);
                prioridad1 = em.merge(prioridad1);
            }
            if (responsable != null) {
                responsable.getClienteSet().add(cliente);
                responsable = em.merge(responsable);
            }
            if (responsable1 != null) {
                responsable1.getClienteSet().add(cliente);
                responsable1 = em.merge(responsable1);
            }
            if (usuario != null) {
                usuario.getClienteSet().add(cliente);
                usuario = em.merge(usuario);
            }
            if (usuario1 != null) {
                usuario1.getClienteSet().add(cliente);
                usuario1 = em.merge(usuario1);
            }
            for (Pedido pedidoSetPedido : cliente.getPedidoSet()) {
                Cliente oldClienteOfPedidoSetPedido = pedidoSetPedido.getCliente();
                pedidoSetPedido.setCliente(cliente);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldClienteOfPedidoSetPedido != null) {
                    oldClienteOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldClienteOfPedidoSetPedido = em.merge(oldClienteOfPedidoSetPedido);
                }
            }
            for (Pedido pedidoSet1Pedido : cliente.getPedidoSet1()) {
                Cliente oldCliente1OfPedidoSet1Pedido = pedidoSet1Pedido.getCliente1();
                pedidoSet1Pedido.setCliente1(cliente);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldCliente1OfPedidoSet1Pedido != null) {
                    oldCliente1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldCliente1OfPedidoSet1Pedido = em.merge(oldCliente1OfPedidoSet1Pedido);
                }
            }
            for (Reclamocliente reclamoclienteSetReclamocliente : cliente.getReclamoclienteSet()) {
                Cliente oldClienteOfReclamoclienteSetReclamocliente = reclamoclienteSetReclamocliente.getCliente();
                reclamoclienteSetReclamocliente.setCliente(cliente);
                reclamoclienteSetReclamocliente = em.merge(reclamoclienteSetReclamocliente);
                if (oldClienteOfReclamoclienteSetReclamocliente != null) {
                    oldClienteOfReclamoclienteSetReclamocliente.getReclamoclienteSet().remove(reclamoclienteSetReclamocliente);
                    oldClienteOfReclamoclienteSetReclamocliente = em.merge(oldClienteOfReclamoclienteSetReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteSet1Reclamocliente : cliente.getReclamoclienteSet1()) {
                Cliente oldCliente1OfReclamoclienteSet1Reclamocliente = reclamoclienteSet1Reclamocliente.getCliente1();
                reclamoclienteSet1Reclamocliente.setCliente1(cliente);
                reclamoclienteSet1Reclamocliente = em.merge(reclamoclienteSet1Reclamocliente);
                if (oldCliente1OfReclamoclienteSet1Reclamocliente != null) {
                    oldCliente1OfReclamoclienteSet1Reclamocliente.getReclamoclienteSet1().remove(reclamoclienteSet1Reclamocliente);
                    oldCliente1OfReclamoclienteSet1Reclamocliente = em.merge(oldCliente1OfReclamoclienteSet1Reclamocliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getIdcliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            Condicioniva condicionivaOld = persistentCliente.getCondicioniva();
            Condicioniva condicionivaNew = cliente.getCondicioniva();
            Condicioniva condicioniva1Old = persistentCliente.getCondicioniva1();
            Condicioniva condicioniva1New = cliente.getCondicioniva1();
            Domicilio domicilioOld = persistentCliente.getDomicilio();
            Domicilio domicilioNew = cliente.getDomicilio();
            Domicilio domicilio1Old = persistentCliente.getDomicilio1();
            Domicilio domicilio1New = cliente.getDomicilio1();
            Estadocliente estadoOld = persistentCliente.getEstado();
            Estadocliente estadoNew = cliente.getEstado();
            Estadocliente estado1Old = persistentCliente.getEstado1();
            Estadocliente estado1New = cliente.getEstado1();
            Prioridad prioridadOld = persistentCliente.getPrioridad();
            Prioridad prioridadNew = cliente.getPrioridad();
            Prioridad prioridad1Old = persistentCliente.getPrioridad1();
            Prioridad prioridad1New = cliente.getPrioridad1();
            Responsable responsableOld = persistentCliente.getResponsable();
            Responsable responsableNew = cliente.getResponsable();
            Responsable responsable1Old = persistentCliente.getResponsable1();
            Responsable responsable1New = cliente.getResponsable1();
            Usuario usuarioOld = persistentCliente.getUsuario();
            Usuario usuarioNew = cliente.getUsuario();
            Usuario usuario1Old = persistentCliente.getUsuario1();
            Usuario usuario1New = cliente.getUsuario1();
            Set<Pedido> pedidoSetOld = persistentCliente.getPedidoSet();
            Set<Pedido> pedidoSetNew = cliente.getPedidoSet();
            Set<Pedido> pedidoSet1Old = persistentCliente.getPedidoSet1();
            Set<Pedido> pedidoSet1New = cliente.getPedidoSet1();
            Set<Reclamocliente> reclamoclienteSetOld = persistentCliente.getReclamoclienteSet();
            Set<Reclamocliente> reclamoclienteSetNew = cliente.getReclamoclienteSet();
            Set<Reclamocliente> reclamoclienteSet1Old = persistentCliente.getReclamoclienteSet1();
            Set<Reclamocliente> reclamoclienteSet1New = cliente.getReclamoclienteSet1();
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                cliente.setCondicioniva(condicionivaNew);
            }
            if (condicioniva1New != null) {
                condicioniva1New = em.getReference(condicioniva1New.getClass(), condicioniva1New.getIdcondicioniva());
                cliente.setCondicioniva1(condicioniva1New);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                cliente.setDomicilio(domicilioNew);
            }
            if (domicilio1New != null) {
                domicilio1New = em.getReference(domicilio1New.getClass(), domicilio1New.getIddomicilio());
                cliente.setDomicilio1(domicilio1New);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                cliente.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                cliente.setEstado1(estado1New);
            }
            if (prioridadNew != null) {
                prioridadNew = em.getReference(prioridadNew.getClass(), prioridadNew.getIdprioridad());
                cliente.setPrioridad(prioridadNew);
            }
            if (prioridad1New != null) {
                prioridad1New = em.getReference(prioridad1New.getClass(), prioridad1New.getIdprioridad());
                cliente.setPrioridad1(prioridad1New);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                cliente.setResponsable(responsableNew);
            }
            if (responsable1New != null) {
                responsable1New = em.getReference(responsable1New.getClass(), responsable1New.getIdresponsable());
                cliente.setResponsable1(responsable1New);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                cliente.setUsuario(usuarioNew);
            }
            if (usuario1New != null) {
                usuario1New = em.getReference(usuario1New.getClass(), usuario1New.getIdusuario());
                cliente.setUsuario1(usuario1New);
            }
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            cliente.setPedidoSet(pedidoSetNew);
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            cliente.setPedidoSet1(pedidoSet1New);
            Set<Reclamocliente> attachedReclamoclienteSetNew = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSetNewReclamoclienteToAttach : reclamoclienteSetNew) {
                reclamoclienteSetNewReclamoclienteToAttach = em.getReference(reclamoclienteSetNewReclamoclienteToAttach.getClass(), reclamoclienteSetNewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSetNew.add(reclamoclienteSetNewReclamoclienteToAttach);
            }
            reclamoclienteSetNew = attachedReclamoclienteSetNew;
            cliente.setReclamoclienteSet(reclamoclienteSetNew);
            Set<Reclamocliente> attachedReclamoclienteSet1New = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSet1NewReclamoclienteToAttach : reclamoclienteSet1New) {
                reclamoclienteSet1NewReclamoclienteToAttach = em.getReference(reclamoclienteSet1NewReclamoclienteToAttach.getClass(), reclamoclienteSet1NewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSet1New.add(reclamoclienteSet1NewReclamoclienteToAttach);
            }
            reclamoclienteSet1New = attachedReclamoclienteSet1New;
            cliente.setReclamoclienteSet1(reclamoclienteSet1New);
            cliente = em.merge(cliente);
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getClienteSet().remove(cliente);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getClienteSet().add(cliente);
                condicionivaNew = em.merge(condicionivaNew);
            }
            if (condicioniva1Old != null && !condicioniva1Old.equals(condicioniva1New)) {
                condicioniva1Old.getClienteSet().remove(cliente);
                condicioniva1Old = em.merge(condicioniva1Old);
            }
            if (condicioniva1New != null && !condicioniva1New.equals(condicioniva1Old)) {
                condicioniva1New.getClienteSet().add(cliente);
                condicioniva1New = em.merge(condicioniva1New);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getClienteSet().remove(cliente);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getClienteSet().add(cliente);
                domicilioNew = em.merge(domicilioNew);
            }
            if (domicilio1Old != null && !domicilio1Old.equals(domicilio1New)) {
                domicilio1Old.getClienteSet().remove(cliente);
                domicilio1Old = em.merge(domicilio1Old);
            }
            if (domicilio1New != null && !domicilio1New.equals(domicilio1Old)) {
                domicilio1New.getClienteSet().add(cliente);
                domicilio1New = em.merge(domicilio1New);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getClienteSet().remove(cliente);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getClienteSet().add(cliente);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getClienteSet().remove(cliente);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getClienteSet().add(cliente);
                estado1New = em.merge(estado1New);
            }
            if (prioridadOld != null && !prioridadOld.equals(prioridadNew)) {
                prioridadOld.getClienteSet().remove(cliente);
                prioridadOld = em.merge(prioridadOld);
            }
            if (prioridadNew != null && !prioridadNew.equals(prioridadOld)) {
                prioridadNew.getClienteSet().add(cliente);
                prioridadNew = em.merge(prioridadNew);
            }
            if (prioridad1Old != null && !prioridad1Old.equals(prioridad1New)) {
                prioridad1Old.getClienteSet().remove(cliente);
                prioridad1Old = em.merge(prioridad1Old);
            }
            if (prioridad1New != null && !prioridad1New.equals(prioridad1Old)) {
                prioridad1New.getClienteSet().add(cliente);
                prioridad1New = em.merge(prioridad1New);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getClienteSet().remove(cliente);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getClienteSet().add(cliente);
                responsableNew = em.merge(responsableNew);
            }
            if (responsable1Old != null && !responsable1Old.equals(responsable1New)) {
                responsable1Old.getClienteSet().remove(cliente);
                responsable1Old = em.merge(responsable1Old);
            }
            if (responsable1New != null && !responsable1New.equals(responsable1Old)) {
                responsable1New.getClienteSet().add(cliente);
                responsable1New = em.merge(responsable1New);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getClienteSet().remove(cliente);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getClienteSet().add(cliente);
                usuarioNew = em.merge(usuarioNew);
            }
            if (usuario1Old != null && !usuario1Old.equals(usuario1New)) {
                usuario1Old.getClienteSet().remove(cliente);
                usuario1Old = em.merge(usuario1Old);
            }
            if (usuario1New != null && !usuario1New.equals(usuario1Old)) {
                usuario1New.getClienteSet().add(cliente);
                usuario1New = em.merge(usuario1New);
            }
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setCliente(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Cliente oldClienteOfPedidoSetNewPedido = pedidoSetNewPedido.getCliente();
                    pedidoSetNewPedido.setCliente(cliente);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldClienteOfPedidoSetNewPedido != null && !oldClienteOfPedidoSetNewPedido.equals(cliente)) {
                        oldClienteOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldClienteOfPedidoSetNewPedido = em.merge(oldClienteOfPedidoSetNewPedido);
                    }
                }
            }
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    pedidoSet1OldPedido.setCliente1(null);
                    pedidoSet1OldPedido = em.merge(pedidoSet1OldPedido);
                }
            }
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Cliente oldCliente1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getCliente1();
                    pedidoSet1NewPedido.setCliente1(cliente);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldCliente1OfPedidoSet1NewPedido != null && !oldCliente1OfPedidoSet1NewPedido.equals(cliente)) {
                        oldCliente1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldCliente1OfPedidoSet1NewPedido = em.merge(oldCliente1OfPedidoSet1NewPedido);
                    }
                }
            }
            for (Reclamocliente reclamoclienteSetOldReclamocliente : reclamoclienteSetOld) {
                if (!reclamoclienteSetNew.contains(reclamoclienteSetOldReclamocliente)) {
                    reclamoclienteSetOldReclamocliente.setCliente(null);
                    reclamoclienteSetOldReclamocliente = em.merge(reclamoclienteSetOldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteSetNewReclamocliente : reclamoclienteSetNew) {
                if (!reclamoclienteSetOld.contains(reclamoclienteSetNewReclamocliente)) {
                    Cliente oldClienteOfReclamoclienteSetNewReclamocliente = reclamoclienteSetNewReclamocliente.getCliente();
                    reclamoclienteSetNewReclamocliente.setCliente(cliente);
                    reclamoclienteSetNewReclamocliente = em.merge(reclamoclienteSetNewReclamocliente);
                    if (oldClienteOfReclamoclienteSetNewReclamocliente != null && !oldClienteOfReclamoclienteSetNewReclamocliente.equals(cliente)) {
                        oldClienteOfReclamoclienteSetNewReclamocliente.getReclamoclienteSet().remove(reclamoclienteSetNewReclamocliente);
                        oldClienteOfReclamoclienteSetNewReclamocliente = em.merge(oldClienteOfReclamoclienteSetNewReclamocliente);
                    }
                }
            }
            for (Reclamocliente reclamoclienteSet1OldReclamocliente : reclamoclienteSet1Old) {
                if (!reclamoclienteSet1New.contains(reclamoclienteSet1OldReclamocliente)) {
                    reclamoclienteSet1OldReclamocliente.setCliente1(null);
                    reclamoclienteSet1OldReclamocliente = em.merge(reclamoclienteSet1OldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteSet1NewReclamocliente : reclamoclienteSet1New) {
                if (!reclamoclienteSet1Old.contains(reclamoclienteSet1NewReclamocliente)) {
                    Cliente oldCliente1OfReclamoclienteSet1NewReclamocliente = reclamoclienteSet1NewReclamocliente.getCliente1();
                    reclamoclienteSet1NewReclamocliente.setCliente1(cliente);
                    reclamoclienteSet1NewReclamocliente = em.merge(reclamoclienteSet1NewReclamocliente);
                    if (oldCliente1OfReclamoclienteSet1NewReclamocliente != null && !oldCliente1OfReclamoclienteSet1NewReclamocliente.equals(cliente)) {
                        oldCliente1OfReclamoclienteSet1NewReclamocliente.getReclamoclienteSet1().remove(reclamoclienteSet1NewReclamocliente);
                        oldCliente1OfReclamoclienteSet1NewReclamocliente = em.merge(oldCliente1OfReclamoclienteSet1NewReclamocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Condicioniva condicioniva = cliente.getCondicioniva();
            if (condicioniva != null) {
                condicioniva.getClienteSet().remove(cliente);
                condicioniva = em.merge(condicioniva);
            }
            Condicioniva condicioniva1 = cliente.getCondicioniva1();
            if (condicioniva1 != null) {
                condicioniva1.getClienteSet().remove(cliente);
                condicioniva1 = em.merge(condicioniva1);
            }
            Domicilio domicilio = cliente.getDomicilio();
            if (domicilio != null) {
                domicilio.getClienteSet().remove(cliente);
                domicilio = em.merge(domicilio);
            }
            Domicilio domicilio1 = cliente.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1.getClienteSet().remove(cliente);
                domicilio1 = em.merge(domicilio1);
            }
            Estadocliente estado = cliente.getEstado();
            if (estado != null) {
                estado.getClienteSet().remove(cliente);
                estado = em.merge(estado);
            }
            Estadocliente estado1 = cliente.getEstado1();
            if (estado1 != null) {
                estado1.getClienteSet().remove(cliente);
                estado1 = em.merge(estado1);
            }
            Prioridad prioridad = cliente.getPrioridad();
            if (prioridad != null) {
                prioridad.getClienteSet().remove(cliente);
                prioridad = em.merge(prioridad);
            }
            Prioridad prioridad1 = cliente.getPrioridad1();
            if (prioridad1 != null) {
                prioridad1.getClienteSet().remove(cliente);
                prioridad1 = em.merge(prioridad1);
            }
            Responsable responsable = cliente.getResponsable();
            if (responsable != null) {
                responsable.getClienteSet().remove(cliente);
                responsable = em.merge(responsable);
            }
            Responsable responsable1 = cliente.getResponsable1();
            if (responsable1 != null) {
                responsable1.getClienteSet().remove(cliente);
                responsable1 = em.merge(responsable1);
            }
            Usuario usuario = cliente.getUsuario();
            if (usuario != null) {
                usuario.getClienteSet().remove(cliente);
                usuario = em.merge(usuario);
            }
            Usuario usuario1 = cliente.getUsuario1();
            if (usuario1 != null) {
                usuario1.getClienteSet().remove(cliente);
                usuario1 = em.merge(usuario1);
            }
            Set<Pedido> pedidoSet = cliente.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setCliente(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Pedido> pedidoSet1 = cliente.getPedidoSet1();
            for (Pedido pedidoSet1Pedido : pedidoSet1) {
                pedidoSet1Pedido.setCliente1(null);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
            }
            Set<Reclamocliente> reclamoclienteSet = cliente.getReclamoclienteSet();
            for (Reclamocliente reclamoclienteSetReclamocliente : reclamoclienteSet) {
                reclamoclienteSetReclamocliente.setCliente(null);
                reclamoclienteSetReclamocliente = em.merge(reclamoclienteSetReclamocliente);
            }
            Set<Reclamocliente> reclamoclienteSet1 = cliente.getReclamoclienteSet1();
            for (Reclamocliente reclamoclienteSet1Reclamocliente : reclamoclienteSet1) {
                reclamoclienteSet1Reclamocliente.setCliente1(null);
                reclamoclienteSet1Reclamocliente = em.merge(reclamoclienteSet1Reclamocliente);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
