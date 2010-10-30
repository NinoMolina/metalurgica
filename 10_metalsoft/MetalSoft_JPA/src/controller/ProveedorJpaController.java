/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Proveedor;
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
import entity.Responsable;
import entity.Compra;
import java.util.HashSet;
import java.util.Set;
import entity.Proveedorxmateriaprima;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ProveedorJpaController {

    public ProveedorJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) throws PreexistingEntityException, Exception {
        if (proveedor.getCompraSet() == null) {
            proveedor.setCompraSet(new HashSet<Compra>());
        }
        if (proveedor.getCompraSet1() == null) {
            proveedor.setCompraSet1(new HashSet<Compra>());
        }
        if (proveedor.getProveedorxmateriaprimaSet() == null) {
            proveedor.setProveedorxmateriaprimaSet(new HashSet<Proveedorxmateriaprima>());
        }
        if (proveedor.getProveedorxmateriaprimaSet1() == null) {
            proveedor.setProveedorxmateriaprimaSet1(new HashSet<Proveedorxmateriaprima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condicioniva condicion = proveedor.getCondicion();
            if (condicion != null) {
                condicion = em.getReference(condicion.getClass(), condicion.getIdcondicioniva());
                proveedor.setCondicion(condicion);
            }
            Condicioniva condicion1 = proveedor.getCondicion1();
            if (condicion1 != null) {
                condicion1 = em.getReference(condicion1.getClass(), condicion1.getIdcondicioniva());
                proveedor.setCondicion1(condicion1);
            }
            Domicilio domicilio = proveedor.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                proveedor.setDomicilio(domicilio);
            }
            Domicilio domicilio1 = proveedor.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1 = em.getReference(domicilio1.getClass(), domicilio1.getIddomicilio());
                proveedor.setDomicilio1(domicilio1);
            }
            Responsable responsable = proveedor.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                proveedor.setResponsable(responsable);
            }
            Responsable responsable1 = proveedor.getResponsable1();
            if (responsable1 != null) {
                responsable1 = em.getReference(responsable1.getClass(), responsable1.getIdresponsable());
                proveedor.setResponsable1(responsable1);
            }
            Set<Compra> attachedCompraSet = new HashSet<Compra>();
            for (Compra compraSetCompraToAttach : proveedor.getCompraSet()) {
                compraSetCompraToAttach = em.getReference(compraSetCompraToAttach.getClass(), compraSetCompraToAttach.getIdcompra());
                attachedCompraSet.add(compraSetCompraToAttach);
            }
            proveedor.setCompraSet(attachedCompraSet);
            Set<Compra> attachedCompraSet1 = new HashSet<Compra>();
            for (Compra compraSet1CompraToAttach : proveedor.getCompraSet1()) {
                compraSet1CompraToAttach = em.getReference(compraSet1CompraToAttach.getClass(), compraSet1CompraToAttach.getIdcompra());
                attachedCompraSet1.add(compraSet1CompraToAttach);
            }
            proveedor.setCompraSet1(attachedCompraSet1);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSet = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach : proveedor.getProveedorxmateriaprimaSet()) {
                proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSet.add(proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach);
            }
            proveedor.setProveedorxmateriaprimaSet(attachedProveedorxmateriaprimaSet);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSet1 = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach : proveedor.getProveedorxmateriaprimaSet1()) {
                proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSet1.add(proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach);
            }
            proveedor.setProveedorxmateriaprimaSet1(attachedProveedorxmateriaprimaSet1);
            em.persist(proveedor);
            if (condicion != null) {
                condicion.getProveedorSet().add(proveedor);
                condicion = em.merge(condicion);
            }
            if (condicion1 != null) {
                condicion1.getProveedorSet().add(proveedor);
                condicion1 = em.merge(condicion1);
            }
            if (domicilio != null) {
                domicilio.getProveedorSet().add(proveedor);
                domicilio = em.merge(domicilio);
            }
            if (domicilio1 != null) {
                domicilio1.getProveedorSet().add(proveedor);
                domicilio1 = em.merge(domicilio1);
            }
            if (responsable != null) {
                responsable.getProveedorSet().add(proveedor);
                responsable = em.merge(responsable);
            }
            if (responsable1 != null) {
                responsable1.getProveedorSet().add(proveedor);
                responsable1 = em.merge(responsable1);
            }
            for (Compra compraSetCompra : proveedor.getCompraSet()) {
                Proveedor oldProveedorOfCompraSetCompra = compraSetCompra.getProveedor();
                compraSetCompra.setProveedor(proveedor);
                compraSetCompra = em.merge(compraSetCompra);
                if (oldProveedorOfCompraSetCompra != null) {
                    oldProveedorOfCompraSetCompra.getCompraSet().remove(compraSetCompra);
                    oldProveedorOfCompraSetCompra = em.merge(oldProveedorOfCompraSetCompra);
                }
            }
            for (Compra compraSet1Compra : proveedor.getCompraSet1()) {
                Proveedor oldProveedor1OfCompraSet1Compra = compraSet1Compra.getProveedor1();
                compraSet1Compra.setProveedor1(proveedor);
                compraSet1Compra = em.merge(compraSet1Compra);
                if (oldProveedor1OfCompraSet1Compra != null) {
                    oldProveedor1OfCompraSet1Compra.getCompraSet1().remove(compraSet1Compra);
                    oldProveedor1OfCompraSet1Compra = em.merge(oldProveedor1OfCompraSet1Compra);
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetProveedorxmateriaprima : proveedor.getProveedorxmateriaprimaSet()) {
                Proveedor oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima = proveedorxmateriaprimaSetProveedorxmateriaprima.getProveedor();
                proveedorxmateriaprimaSetProveedorxmateriaprima.setProveedor(proveedor);
                proveedorxmateriaprimaSetProveedorxmateriaprima = em.merge(proveedorxmateriaprimaSetProveedorxmateriaprima);
                if (oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima != null) {
                    oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprimaSetProveedorxmateriaprima);
                    oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima = em.merge(oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima);
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1Proveedorxmateriaprima : proveedor.getProveedorxmateriaprimaSet1()) {
                Proveedor oldProveedor1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima = proveedorxmateriaprimaSet1Proveedorxmateriaprima.getProveedor1();
                proveedorxmateriaprimaSet1Proveedorxmateriaprima.setProveedor1(proveedor);
                proveedorxmateriaprimaSet1Proveedorxmateriaprima = em.merge(proveedorxmateriaprimaSet1Proveedorxmateriaprima);
                if (oldProveedor1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima != null) {
                    oldProveedor1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima.getProveedorxmateriaprimaSet1().remove(proveedorxmateriaprimaSet1Proveedorxmateriaprima);
                    oldProveedor1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima = em.merge(oldProveedor1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedor(proveedor.getIdproveedor()) != null) {
                throw new PreexistingEntityException("Proveedor " + proveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getIdproveedor());
            Condicioniva condicionOld = persistentProveedor.getCondicion();
            Condicioniva condicionNew = proveedor.getCondicion();
            Condicioniva condicion1Old = persistentProveedor.getCondicion1();
            Condicioniva condicion1New = proveedor.getCondicion1();
            Domicilio domicilioOld = persistentProveedor.getDomicilio();
            Domicilio domicilioNew = proveedor.getDomicilio();
            Domicilio domicilio1Old = persistentProveedor.getDomicilio1();
            Domicilio domicilio1New = proveedor.getDomicilio1();
            Responsable responsableOld = persistentProveedor.getResponsable();
            Responsable responsableNew = proveedor.getResponsable();
            Responsable responsable1Old = persistentProveedor.getResponsable1();
            Responsable responsable1New = proveedor.getResponsable1();
            Set<Compra> compraSetOld = persistentProveedor.getCompraSet();
            Set<Compra> compraSetNew = proveedor.getCompraSet();
            Set<Compra> compraSet1Old = persistentProveedor.getCompraSet1();
            Set<Compra> compraSet1New = proveedor.getCompraSet1();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetOld = persistentProveedor.getProveedorxmateriaprimaSet();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetNew = proveedor.getProveedorxmateriaprimaSet();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet1Old = persistentProveedor.getProveedorxmateriaprimaSet1();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet1New = proveedor.getProveedorxmateriaprimaSet1();
            List<String> illegalOrphanMessages = null;
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetOldProveedorxmateriaprima : proveedorxmateriaprimaSetOld) {
                if (!proveedorxmateriaprimaSetNew.contains(proveedorxmateriaprimaSetOldProveedorxmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proveedorxmateriaprima " + proveedorxmateriaprimaSetOldProveedorxmateriaprima + " since its proveedor field is not nullable.");
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1OldProveedorxmateriaprima : proveedorxmateriaprimaSet1Old) {
                if (!proveedorxmateriaprimaSet1New.contains(proveedorxmateriaprimaSet1OldProveedorxmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proveedorxmateriaprima " + proveedorxmateriaprimaSet1OldProveedorxmateriaprima + " since its proveedor1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (condicionNew != null) {
                condicionNew = em.getReference(condicionNew.getClass(), condicionNew.getIdcondicioniva());
                proveedor.setCondicion(condicionNew);
            }
            if (condicion1New != null) {
                condicion1New = em.getReference(condicion1New.getClass(), condicion1New.getIdcondicioniva());
                proveedor.setCondicion1(condicion1New);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                proveedor.setDomicilio(domicilioNew);
            }
            if (domicilio1New != null) {
                domicilio1New = em.getReference(domicilio1New.getClass(), domicilio1New.getIddomicilio());
                proveedor.setDomicilio1(domicilio1New);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                proveedor.setResponsable(responsableNew);
            }
            if (responsable1New != null) {
                responsable1New = em.getReference(responsable1New.getClass(), responsable1New.getIdresponsable());
                proveedor.setResponsable1(responsable1New);
            }
            Set<Compra> attachedCompraSetNew = new HashSet<Compra>();
            for (Compra compraSetNewCompraToAttach : compraSetNew) {
                compraSetNewCompraToAttach = em.getReference(compraSetNewCompraToAttach.getClass(), compraSetNewCompraToAttach.getIdcompra());
                attachedCompraSetNew.add(compraSetNewCompraToAttach);
            }
            compraSetNew = attachedCompraSetNew;
            proveedor.setCompraSet(compraSetNew);
            Set<Compra> attachedCompraSet1New = new HashSet<Compra>();
            for (Compra compraSet1NewCompraToAttach : compraSet1New) {
                compraSet1NewCompraToAttach = em.getReference(compraSet1NewCompraToAttach.getClass(), compraSet1NewCompraToAttach.getIdcompra());
                attachedCompraSet1New.add(compraSet1NewCompraToAttach);
            }
            compraSet1New = attachedCompraSet1New;
            proveedor.setCompraSet1(compraSet1New);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSetNew = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach : proveedorxmateriaprimaSetNew) {
                proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSetNew.add(proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach);
            }
            proveedorxmateriaprimaSetNew = attachedProveedorxmateriaprimaSetNew;
            proveedor.setProveedorxmateriaprimaSet(proveedorxmateriaprimaSetNew);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSet1New = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach : proveedorxmateriaprimaSet1New) {
                proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSet1New.add(proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach);
            }
            proveedorxmateriaprimaSet1New = attachedProveedorxmateriaprimaSet1New;
            proveedor.setProveedorxmateriaprimaSet1(proveedorxmateriaprimaSet1New);
            proveedor = em.merge(proveedor);
            if (condicionOld != null && !condicionOld.equals(condicionNew)) {
                condicionOld.getProveedorSet().remove(proveedor);
                condicionOld = em.merge(condicionOld);
            }
            if (condicionNew != null && !condicionNew.equals(condicionOld)) {
                condicionNew.getProveedorSet().add(proveedor);
                condicionNew = em.merge(condicionNew);
            }
            if (condicion1Old != null && !condicion1Old.equals(condicion1New)) {
                condicion1Old.getProveedorSet().remove(proveedor);
                condicion1Old = em.merge(condicion1Old);
            }
            if (condicion1New != null && !condicion1New.equals(condicion1Old)) {
                condicion1New.getProveedorSet().add(proveedor);
                condicion1New = em.merge(condicion1New);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getProveedorSet().remove(proveedor);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getProveedorSet().add(proveedor);
                domicilioNew = em.merge(domicilioNew);
            }
            if (domicilio1Old != null && !domicilio1Old.equals(domicilio1New)) {
                domicilio1Old.getProveedorSet().remove(proveedor);
                domicilio1Old = em.merge(domicilio1Old);
            }
            if (domicilio1New != null && !domicilio1New.equals(domicilio1Old)) {
                domicilio1New.getProveedorSet().add(proveedor);
                domicilio1New = em.merge(domicilio1New);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getProveedorSet().remove(proveedor);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getProveedorSet().add(proveedor);
                responsableNew = em.merge(responsableNew);
            }
            if (responsable1Old != null && !responsable1Old.equals(responsable1New)) {
                responsable1Old.getProveedorSet().remove(proveedor);
                responsable1Old = em.merge(responsable1Old);
            }
            if (responsable1New != null && !responsable1New.equals(responsable1Old)) {
                responsable1New.getProveedorSet().add(proveedor);
                responsable1New = em.merge(responsable1New);
            }
            for (Compra compraSetOldCompra : compraSetOld) {
                if (!compraSetNew.contains(compraSetOldCompra)) {
                    compraSetOldCompra.setProveedor(null);
                    compraSetOldCompra = em.merge(compraSetOldCompra);
                }
            }
            for (Compra compraSetNewCompra : compraSetNew) {
                if (!compraSetOld.contains(compraSetNewCompra)) {
                    Proveedor oldProveedorOfCompraSetNewCompra = compraSetNewCompra.getProveedor();
                    compraSetNewCompra.setProveedor(proveedor);
                    compraSetNewCompra = em.merge(compraSetNewCompra);
                    if (oldProveedorOfCompraSetNewCompra != null && !oldProveedorOfCompraSetNewCompra.equals(proveedor)) {
                        oldProveedorOfCompraSetNewCompra.getCompraSet().remove(compraSetNewCompra);
                        oldProveedorOfCompraSetNewCompra = em.merge(oldProveedorOfCompraSetNewCompra);
                    }
                }
            }
            for (Compra compraSet1OldCompra : compraSet1Old) {
                if (!compraSet1New.contains(compraSet1OldCompra)) {
                    compraSet1OldCompra.setProveedor1(null);
                    compraSet1OldCompra = em.merge(compraSet1OldCompra);
                }
            }
            for (Compra compraSet1NewCompra : compraSet1New) {
                if (!compraSet1Old.contains(compraSet1NewCompra)) {
                    Proveedor oldProveedor1OfCompraSet1NewCompra = compraSet1NewCompra.getProveedor1();
                    compraSet1NewCompra.setProveedor1(proveedor);
                    compraSet1NewCompra = em.merge(compraSet1NewCompra);
                    if (oldProveedor1OfCompraSet1NewCompra != null && !oldProveedor1OfCompraSet1NewCompra.equals(proveedor)) {
                        oldProveedor1OfCompraSet1NewCompra.getCompraSet1().remove(compraSet1NewCompra);
                        oldProveedor1OfCompraSet1NewCompra = em.merge(oldProveedor1OfCompraSet1NewCompra);
                    }
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetNewProveedorxmateriaprima : proveedorxmateriaprimaSetNew) {
                if (!proveedorxmateriaprimaSetOld.contains(proveedorxmateriaprimaSetNewProveedorxmateriaprima)) {
                    Proveedor oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima = proveedorxmateriaprimaSetNewProveedorxmateriaprima.getProveedor();
                    proveedorxmateriaprimaSetNewProveedorxmateriaprima.setProveedor(proveedor);
                    proveedorxmateriaprimaSetNewProveedorxmateriaprima = em.merge(proveedorxmateriaprimaSetNewProveedorxmateriaprima);
                    if (oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima != null && !oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima.equals(proveedor)) {
                        oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprimaSetNewProveedorxmateriaprima);
                        oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima = em.merge(oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima);
                    }
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1NewProveedorxmateriaprima : proveedorxmateriaprimaSet1New) {
                if (!proveedorxmateriaprimaSet1Old.contains(proveedorxmateriaprimaSet1NewProveedorxmateriaprima)) {
                    Proveedor oldProveedor1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima = proveedorxmateriaprimaSet1NewProveedorxmateriaprima.getProveedor1();
                    proveedorxmateriaprimaSet1NewProveedorxmateriaprima.setProveedor1(proveedor);
                    proveedorxmateriaprimaSet1NewProveedorxmateriaprima = em.merge(proveedorxmateriaprimaSet1NewProveedorxmateriaprima);
                    if (oldProveedor1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima != null && !oldProveedor1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima.equals(proveedor)) {
                        oldProveedor1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima.getProveedorxmateriaprimaSet1().remove(proveedorxmateriaprimaSet1NewProveedorxmateriaprima);
                        oldProveedor1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima = em.merge(oldProveedor1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proveedor.getIdproveedor();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdproveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetOrphanCheck = proveedor.getProveedorxmateriaprimaSet();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetOrphanCheckProveedorxmateriaprima : proveedorxmateriaprimaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Proveedorxmateriaprima " + proveedorxmateriaprimaSetOrphanCheckProveedorxmateriaprima + " in its proveedorxmateriaprimaSet field has a non-nullable proveedor field.");
            }
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet1OrphanCheck = proveedor.getProveedorxmateriaprimaSet1();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1OrphanCheckProveedorxmateriaprima : proveedorxmateriaprimaSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Proveedorxmateriaprima " + proveedorxmateriaprimaSet1OrphanCheckProveedorxmateriaprima + " in its proveedorxmateriaprimaSet1 field has a non-nullable proveedor1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Condicioniva condicion = proveedor.getCondicion();
            if (condicion != null) {
                condicion.getProveedorSet().remove(proveedor);
                condicion = em.merge(condicion);
            }
            Condicioniva condicion1 = proveedor.getCondicion1();
            if (condicion1 != null) {
                condicion1.getProveedorSet().remove(proveedor);
                condicion1 = em.merge(condicion1);
            }
            Domicilio domicilio = proveedor.getDomicilio();
            if (domicilio != null) {
                domicilio.getProveedorSet().remove(proveedor);
                domicilio = em.merge(domicilio);
            }
            Domicilio domicilio1 = proveedor.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1.getProveedorSet().remove(proveedor);
                domicilio1 = em.merge(domicilio1);
            }
            Responsable responsable = proveedor.getResponsable();
            if (responsable != null) {
                responsable.getProveedorSet().remove(proveedor);
                responsable = em.merge(responsable);
            }
            Responsable responsable1 = proveedor.getResponsable1();
            if (responsable1 != null) {
                responsable1.getProveedorSet().remove(proveedor);
                responsable1 = em.merge(responsable1);
            }
            Set<Compra> compraSet = proveedor.getCompraSet();
            for (Compra compraSetCompra : compraSet) {
                compraSetCompra.setProveedor(null);
                compraSetCompra = em.merge(compraSetCompra);
            }
            Set<Compra> compraSet1 = proveedor.getCompraSet1();
            for (Compra compraSet1Compra : compraSet1) {
                compraSet1Compra.setProveedor1(null);
                compraSet1Compra = em.merge(compraSet1Compra);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
