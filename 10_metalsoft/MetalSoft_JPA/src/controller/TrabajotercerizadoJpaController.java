/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Trabajotercerizado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empresametalurgica;
import entity.Estadotrabajotercerizado;
import entity.Pedido;
import entity.Reclamoempresametalurgica;
import java.util.HashSet;
import java.util.Set;
import entity.Detalletrabajotercerizado;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class TrabajotercerizadoJpaController {

    public TrabajotercerizadoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabajotercerizado trabajotercerizado) throws PreexistingEntityException, Exception {
        if (trabajotercerizado.getReclamoempresametalurgicaSet() == null) {
            trabajotercerizado.setReclamoempresametalurgicaSet(new HashSet<Reclamoempresametalurgica>());
        }
        if (trabajotercerizado.getReclamoempresametalurgicaSet1() == null) {
            trabajotercerizado.setReclamoempresametalurgicaSet1(new HashSet<Reclamoempresametalurgica>());
        }
        if (trabajotercerizado.getDetalletrabajotercerizadoSet() == null) {
            trabajotercerizado.setDetalletrabajotercerizadoSet(new HashSet<Detalletrabajotercerizado>());
        }
        if (trabajotercerizado.getDetalletrabajotercerizadoSet1() == null) {
            trabajotercerizado.setDetalletrabajotercerizadoSet1(new HashSet<Detalletrabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresametalurgica empresa = trabajotercerizado.getEmpresa();
            if (empresa != null) {
                empresa = em.getReference(empresa.getClass(), empresa.getIdempresametalurgica());
                trabajotercerizado.setEmpresa(empresa);
            }
            Empresametalurgica empresa1 = trabajotercerizado.getEmpresa1();
            if (empresa1 != null) {
                empresa1 = em.getReference(empresa1.getClass(), empresa1.getIdempresametalurgica());
                trabajotercerizado.setEmpresa1(empresa1);
            }
            Estadotrabajotercerizado estado = trabajotercerizado.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                trabajotercerizado.setEstado(estado);
            }
            Estadotrabajotercerizado estado1 = trabajotercerizado.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                trabajotercerizado.setEstado1(estado1);
            }
            Pedido pedido = trabajotercerizado.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                trabajotercerizado.setPedido(pedido);
            }
            Pedido pedido1 = trabajotercerizado.getPedido1();
            if (pedido1 != null) {
                pedido1 = em.getReference(pedido1.getClass(), pedido1.getIdpedido());
                trabajotercerizado.setPedido1(pedido1);
            }
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSet = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach : trabajotercerizado.getReclamoempresametalurgicaSet()) {
                reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSet.add(reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach);
            }
            trabajotercerizado.setReclamoempresametalurgicaSet(attachedReclamoempresametalurgicaSet);
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSet1 = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach : trabajotercerizado.getReclamoempresametalurgicaSet1()) {
                reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSet1.add(reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach);
            }
            trabajotercerizado.setReclamoempresametalurgicaSet1(attachedReclamoempresametalurgicaSet1);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach : trabajotercerizado.getDetalletrabajotercerizadoSet()) {
                detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet.add(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach);
            }
            trabajotercerizado.setDetalletrabajotercerizadoSet(attachedDetalletrabajotercerizadoSet);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet1 = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach : trabajotercerizado.getDetalletrabajotercerizadoSet1()) {
                detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet1.add(detalletrabajotercerizadoSet1DetalletrabajotercerizadoToAttach);
            }
            trabajotercerizado.setDetalletrabajotercerizadoSet1(attachedDetalletrabajotercerizadoSet1);
            em.persist(trabajotercerizado);
            if (empresa != null) {
                empresa.getTrabajotercerizadoSet().add(trabajotercerizado);
                empresa = em.merge(empresa);
            }
            if (empresa1 != null) {
                empresa1.getTrabajotercerizadoSet().add(trabajotercerizado);
                empresa1 = em.merge(empresa1);
            }
            if (estado != null) {
                estado.getTrabajotercerizadoSet().add(trabajotercerizado);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getTrabajotercerizadoSet().add(trabajotercerizado);
                estado1 = em.merge(estado1);
            }
            if (pedido != null) {
                pedido.getTrabajotercerizadoSet().add(trabajotercerizado);
                pedido = em.merge(pedido);
            }
            if (pedido1 != null) {
                pedido1.getTrabajotercerizadoSet().add(trabajotercerizado);
                pedido1 = em.merge(pedido1);
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgica : trabajotercerizado.getReclamoempresametalurgicaSet()) {
                Trabajotercerizado oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica = reclamoempresametalurgicaSetReclamoempresametalurgica.getTrabajotercerizado();
                reclamoempresametalurgicaSetReclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
                reclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetReclamoempresametalurgica);
                if (oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica != null) {
                    oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgicaSetReclamoempresametalurgica);
                    oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(oldTrabajotercerizadoOfReclamoempresametalurgicaSetReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1Reclamoempresametalurgica : trabajotercerizado.getReclamoempresametalurgicaSet1()) {
                Trabajotercerizado oldTrabajotercerizado1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica = reclamoempresametalurgicaSet1Reclamoempresametalurgica.getTrabajotercerizado1();
                reclamoempresametalurgicaSet1Reclamoempresametalurgica.setTrabajotercerizado1(trabajotercerizado);
                reclamoempresametalurgicaSet1Reclamoempresametalurgica = em.merge(reclamoempresametalurgicaSet1Reclamoempresametalurgica);
                if (oldTrabajotercerizado1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica != null) {
                    oldTrabajotercerizado1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica.getReclamoempresametalurgicaSet1().remove(reclamoempresametalurgicaSet1Reclamoempresametalurgica);
                    oldTrabajotercerizado1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica = em.merge(oldTrabajotercerizado1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizado : trabajotercerizado.getDetalletrabajotercerizadoSet()) {
                Trabajotercerizado oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado = detalletrabajotercerizadoSetDetalletrabajotercerizado.getTrabajotercerizado();
                detalletrabajotercerizadoSetDetalletrabajotercerizado.setTrabajotercerizado(trabajotercerizado);
                detalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetDetalletrabajotercerizado);
                if (oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado != null) {
                    oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizadoSetDetalletrabajotercerizado);
                    oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(oldTrabajotercerizadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1Detalletrabajotercerizado : trabajotercerizado.getDetalletrabajotercerizadoSet1()) {
                Trabajotercerizado oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado = detalletrabajotercerizadoSet1Detalletrabajotercerizado.getTrabajotercerizado1();
                detalletrabajotercerizadoSet1Detalletrabajotercerizado.setTrabajotercerizado1(trabajotercerizado);
                detalletrabajotercerizadoSet1Detalletrabajotercerizado = em.merge(detalletrabajotercerizadoSet1Detalletrabajotercerizado);
                if (oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado != null) {
                    oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado.getDetalletrabajotercerizadoSet1().remove(detalletrabajotercerizadoSet1Detalletrabajotercerizado);
                    oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado = em.merge(oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1Detalletrabajotercerizado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTrabajotercerizado(trabajotercerizado.getIdtrabajo()) != null) {
                throw new PreexistingEntityException("Trabajotercerizado " + trabajotercerizado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trabajotercerizado trabajotercerizado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajotercerizado persistentTrabajotercerizado = em.find(Trabajotercerizado.class, trabajotercerizado.getIdtrabajo());
            Empresametalurgica empresaOld = persistentTrabajotercerizado.getEmpresa();
            Empresametalurgica empresaNew = trabajotercerizado.getEmpresa();
            Empresametalurgica empresa1Old = persistentTrabajotercerizado.getEmpresa1();
            Empresametalurgica empresa1New = trabajotercerizado.getEmpresa1();
            Estadotrabajotercerizado estadoOld = persistentTrabajotercerizado.getEstado();
            Estadotrabajotercerizado estadoNew = trabajotercerizado.getEstado();
            Estadotrabajotercerizado estado1Old = persistentTrabajotercerizado.getEstado1();
            Estadotrabajotercerizado estado1New = trabajotercerizado.getEstado1();
            Pedido pedidoOld = persistentTrabajotercerizado.getPedido();
            Pedido pedidoNew = trabajotercerizado.getPedido();
            Pedido pedido1Old = persistentTrabajotercerizado.getPedido1();
            Pedido pedido1New = trabajotercerizado.getPedido1();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSetOld = persistentTrabajotercerizado.getReclamoempresametalurgicaSet();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSetNew = trabajotercerizado.getReclamoempresametalurgicaSet();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1Old = persistentTrabajotercerizado.getReclamoempresametalurgicaSet1();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1New = trabajotercerizado.getReclamoempresametalurgicaSet1();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetOld = persistentTrabajotercerizado.getDetalletrabajotercerizadoSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetNew = trabajotercerizado.getDetalletrabajotercerizadoSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet1Old = persistentTrabajotercerizado.getDetalletrabajotercerizadoSet1();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet1New = trabajotercerizado.getDetalletrabajotercerizadoSet1();
            List<String> illegalOrphanMessages = null;
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetOldDetalletrabajotercerizado : detalletrabajotercerizadoSetOld) {
                if (!detalletrabajotercerizadoSetNew.contains(detalletrabajotercerizadoSetOldDetalletrabajotercerizado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalletrabajotercerizado " + detalletrabajotercerizadoSetOldDetalletrabajotercerizado + " since its trabajotercerizado field is not nullable.");
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1OldDetalletrabajotercerizado : detalletrabajotercerizadoSet1Old) {
                if (!detalletrabajotercerizadoSet1New.contains(detalletrabajotercerizadoSet1OldDetalletrabajotercerizado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalletrabajotercerizado " + detalletrabajotercerizadoSet1OldDetalletrabajotercerizado + " since its trabajotercerizado1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empresaNew != null) {
                empresaNew = em.getReference(empresaNew.getClass(), empresaNew.getIdempresametalurgica());
                trabajotercerizado.setEmpresa(empresaNew);
            }
            if (empresa1New != null) {
                empresa1New = em.getReference(empresa1New.getClass(), empresa1New.getIdempresametalurgica());
                trabajotercerizado.setEmpresa1(empresa1New);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                trabajotercerizado.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                trabajotercerizado.setEstado1(estado1New);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                trabajotercerizado.setPedido(pedidoNew);
            }
            if (pedido1New != null) {
                pedido1New = em.getReference(pedido1New.getClass(), pedido1New.getIdpedido());
                trabajotercerizado.setPedido1(pedido1New);
            }
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSetNew = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaSetNew) {
                reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSetNew.add(reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaSetNew = attachedReclamoempresametalurgicaSetNew;
            trabajotercerizado.setReclamoempresametalurgicaSet(reclamoempresametalurgicaSetNew);
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSet1New = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaSet1New) {
                reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSet1New.add(reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaSet1New = attachedReclamoempresametalurgicaSet1New;
            trabajotercerizado.setReclamoempresametalurgicaSet1(reclamoempresametalurgicaSet1New);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSetNew = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoSetNew) {
                detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSetNew.add(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoSetNew = attachedDetalletrabajotercerizadoSetNew;
            trabajotercerizado.setDetalletrabajotercerizadoSet(detalletrabajotercerizadoSetNew);
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet1New = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoSet1New) {
                detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet1New.add(detalletrabajotercerizadoSet1NewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoSet1New = attachedDetalletrabajotercerizadoSet1New;
            trabajotercerizado.setDetalletrabajotercerizadoSet1(detalletrabajotercerizadoSet1New);
            trabajotercerizado = em.merge(trabajotercerizado);
            if (empresaOld != null && !empresaOld.equals(empresaNew)) {
                empresaOld.getTrabajotercerizadoSet().remove(trabajotercerizado);
                empresaOld = em.merge(empresaOld);
            }
            if (empresaNew != null && !empresaNew.equals(empresaOld)) {
                empresaNew.getTrabajotercerizadoSet().add(trabajotercerizado);
                empresaNew = em.merge(empresaNew);
            }
            if (empresa1Old != null && !empresa1Old.equals(empresa1New)) {
                empresa1Old.getTrabajotercerizadoSet().remove(trabajotercerizado);
                empresa1Old = em.merge(empresa1Old);
            }
            if (empresa1New != null && !empresa1New.equals(empresa1Old)) {
                empresa1New.getTrabajotercerizadoSet().add(trabajotercerizado);
                empresa1New = em.merge(empresa1New);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getTrabajotercerizadoSet().remove(trabajotercerizado);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getTrabajotercerizadoSet().add(trabajotercerizado);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getTrabajotercerizadoSet().remove(trabajotercerizado);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getTrabajotercerizadoSet().add(trabajotercerizado);
                estado1New = em.merge(estado1New);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getTrabajotercerizadoSet().remove(trabajotercerizado);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getTrabajotercerizadoSet().add(trabajotercerizado);
                pedidoNew = em.merge(pedidoNew);
            }
            if (pedido1Old != null && !pedido1Old.equals(pedido1New)) {
                pedido1Old.getTrabajotercerizadoSet().remove(trabajotercerizado);
                pedido1Old = em.merge(pedido1Old);
            }
            if (pedido1New != null && !pedido1New.equals(pedido1Old)) {
                pedido1New.getTrabajotercerizadoSet().add(trabajotercerizado);
                pedido1New = em.merge(pedido1New);
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetOldReclamoempresametalurgica : reclamoempresametalurgicaSetOld) {
                if (!reclamoempresametalurgicaSetNew.contains(reclamoempresametalurgicaSetOldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaSetOldReclamoempresametalurgica.setTrabajotercerizado(null);
                    reclamoempresametalurgicaSetOldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetOldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetNewReclamoempresametalurgica : reclamoempresametalurgicaSetNew) {
                if (!reclamoempresametalurgicaSetOld.contains(reclamoempresametalurgicaSetNewReclamoempresametalurgica)) {
                    Trabajotercerizado oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica = reclamoempresametalurgicaSetNewReclamoempresametalurgica.getTrabajotercerizado();
                    reclamoempresametalurgicaSetNewReclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
                    reclamoempresametalurgicaSetNewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetNewReclamoempresametalurgica);
                    if (oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica != null && !oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica.equals(trabajotercerizado)) {
                        oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgicaSetNewReclamoempresametalurgica);
                        oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica = em.merge(oldTrabajotercerizadoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica);
                    }
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1OldReclamoempresametalurgica : reclamoempresametalurgicaSet1Old) {
                if (!reclamoempresametalurgicaSet1New.contains(reclamoempresametalurgicaSet1OldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaSet1OldReclamoempresametalurgica.setTrabajotercerizado1(null);
                    reclamoempresametalurgicaSet1OldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSet1OldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1NewReclamoempresametalurgica : reclamoempresametalurgicaSet1New) {
                if (!reclamoempresametalurgicaSet1Old.contains(reclamoempresametalurgicaSet1NewReclamoempresametalurgica)) {
                    Trabajotercerizado oldTrabajotercerizado1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica = reclamoempresametalurgicaSet1NewReclamoempresametalurgica.getTrabajotercerizado1();
                    reclamoempresametalurgicaSet1NewReclamoempresametalurgica.setTrabajotercerizado1(trabajotercerizado);
                    reclamoempresametalurgicaSet1NewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSet1NewReclamoempresametalurgica);
                    if (oldTrabajotercerizado1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica != null && !oldTrabajotercerizado1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica.equals(trabajotercerizado)) {
                        oldTrabajotercerizado1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica.getReclamoempresametalurgicaSet1().remove(reclamoempresametalurgicaSet1NewReclamoempresametalurgica);
                        oldTrabajotercerizado1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica = em.merge(oldTrabajotercerizado1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica);
                    }
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizado : detalletrabajotercerizadoSetNew) {
                if (!detalletrabajotercerizadoSetOld.contains(detalletrabajotercerizadoSetNewDetalletrabajotercerizado)) {
                    Trabajotercerizado oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado = detalletrabajotercerizadoSetNewDetalletrabajotercerizado.getTrabajotercerizado();
                    detalletrabajotercerizadoSetNewDetalletrabajotercerizado.setTrabajotercerizado(trabajotercerizado);
                    detalletrabajotercerizadoSetNewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                    if (oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado != null && !oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado.equals(trabajotercerizado)) {
                        oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                        oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado = em.merge(oldTrabajotercerizadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                    }
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1NewDetalletrabajotercerizado : detalletrabajotercerizadoSet1New) {
                if (!detalletrabajotercerizadoSet1Old.contains(detalletrabajotercerizadoSet1NewDetalletrabajotercerizado)) {
                    Trabajotercerizado oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado = detalletrabajotercerizadoSet1NewDetalletrabajotercerizado.getTrabajotercerizado1();
                    detalletrabajotercerizadoSet1NewDetalletrabajotercerizado.setTrabajotercerizado1(trabajotercerizado);
                    detalletrabajotercerizadoSet1NewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSet1NewDetalletrabajotercerizado);
                    if (oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado != null && !oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado.equals(trabajotercerizado)) {
                        oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado.getDetalletrabajotercerizadoSet1().remove(detalletrabajotercerizadoSet1NewDetalletrabajotercerizado);
                        oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado = em.merge(oldTrabajotercerizado1OfDetalletrabajotercerizadoSet1NewDetalletrabajotercerizado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = trabajotercerizado.getIdtrabajo();
                if (findTrabajotercerizado(id) == null) {
                    throw new NonexistentEntityException("The trabajotercerizado with id " + id + " no longer exists.");
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
            Trabajotercerizado trabajotercerizado;
            try {
                trabajotercerizado = em.getReference(Trabajotercerizado.class, id);
                trabajotercerizado.getIdtrabajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetOrphanCheck = trabajotercerizado.getDetalletrabajotercerizadoSet();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetOrphanCheckDetalletrabajotercerizado : detalletrabajotercerizadoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Trabajotercerizado (" + trabajotercerizado + ") cannot be destroyed since the Detalletrabajotercerizado " + detalletrabajotercerizadoSetOrphanCheckDetalletrabajotercerizado + " in its detalletrabajotercerizadoSet field has a non-nullable trabajotercerizado field.");
            }
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet1OrphanCheck = trabajotercerizado.getDetalletrabajotercerizadoSet1();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSet1OrphanCheckDetalletrabajotercerizado : detalletrabajotercerizadoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Trabajotercerizado (" + trabajotercerizado + ") cannot be destroyed since the Detalletrabajotercerizado " + detalletrabajotercerizadoSet1OrphanCheckDetalletrabajotercerizado + " in its detalletrabajotercerizadoSet1 field has a non-nullable trabajotercerizado1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empresametalurgica empresa = trabajotercerizado.getEmpresa();
            if (empresa != null) {
                empresa.getTrabajotercerizadoSet().remove(trabajotercerizado);
                empresa = em.merge(empresa);
            }
            Empresametalurgica empresa1 = trabajotercerizado.getEmpresa1();
            if (empresa1 != null) {
                empresa1.getTrabajotercerizadoSet().remove(trabajotercerizado);
                empresa1 = em.merge(empresa1);
            }
            Estadotrabajotercerizado estado = trabajotercerizado.getEstado();
            if (estado != null) {
                estado.getTrabajotercerizadoSet().remove(trabajotercerizado);
                estado = em.merge(estado);
            }
            Estadotrabajotercerizado estado1 = trabajotercerizado.getEstado1();
            if (estado1 != null) {
                estado1.getTrabajotercerizadoSet().remove(trabajotercerizado);
                estado1 = em.merge(estado1);
            }
            Pedido pedido = trabajotercerizado.getPedido();
            if (pedido != null) {
                pedido.getTrabajotercerizadoSet().remove(trabajotercerizado);
                pedido = em.merge(pedido);
            }
            Pedido pedido1 = trabajotercerizado.getPedido1();
            if (pedido1 != null) {
                pedido1.getTrabajotercerizadoSet().remove(trabajotercerizado);
                pedido1 = em.merge(pedido1);
            }
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet = trabajotercerizado.getReclamoempresametalurgicaSet();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgica : reclamoempresametalurgicaSet) {
                reclamoempresametalurgicaSetReclamoempresametalurgica.setTrabajotercerizado(null);
                reclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetReclamoempresametalurgica);
            }
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1 = trabajotercerizado.getReclamoempresametalurgicaSet1();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1Reclamoempresametalurgica : reclamoempresametalurgicaSet1) {
                reclamoempresametalurgicaSet1Reclamoempresametalurgica.setTrabajotercerizado1(null);
                reclamoempresametalurgicaSet1Reclamoempresametalurgica = em.merge(reclamoempresametalurgicaSet1Reclamoempresametalurgica);
            }
            em.remove(trabajotercerizado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trabajotercerizado> findTrabajotercerizadoEntities() {
        return findTrabajotercerizadoEntities(true, -1, -1);
    }

    public List<Trabajotercerizado> findTrabajotercerizadoEntities(int maxResults, int firstResult) {
        return findTrabajotercerizadoEntities(false, maxResults, firstResult);
    }

    private List<Trabajotercerizado> findTrabajotercerizadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabajotercerizado.class));
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

    public Trabajotercerizado findTrabajotercerizado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabajotercerizado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabajotercerizadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabajotercerizado> rt = cq.from(Trabajotercerizado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
