/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Responsable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Domicilio;
import entity.Tipodocumento;
import entity.Empresametalurgica;
import java.util.HashSet;
import java.util.Set;
import entity.Cliente;
import entity.Proveedor;
import entity.Proveedormantenimientomaquina;

/**
 *
 * @author Nino
 */
public class ResponsableJpaController {

    public ResponsableJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Responsable responsable) throws PreexistingEntityException, Exception {
        if (responsable.getEmpresametalurgicaSet() == null) {
            responsable.setEmpresametalurgicaSet(new HashSet<Empresametalurgica>());
        }
        if (responsable.getEmpresametalurgicaSet1() == null) {
            responsable.setEmpresametalurgicaSet1(new HashSet<Empresametalurgica>());
        }
        if (responsable.getClienteSet() == null) {
            responsable.setClienteSet(new HashSet<Cliente>());
        }
        if (responsable.getClienteSet1() == null) {
            responsable.setClienteSet1(new HashSet<Cliente>());
        }
        if (responsable.getProveedorSet() == null) {
            responsable.setProveedorSet(new HashSet<Proveedor>());
        }
        if (responsable.getProveedorSet1() == null) {
            responsable.setProveedorSet1(new HashSet<Proveedor>());
        }
        if (responsable.getProveedormantenimientomaquinaSet() == null) {
            responsable.setProveedormantenimientomaquinaSet(new HashSet<Proveedormantenimientomaquina>());
        }
        if (responsable.getProveedormantenimientomaquinaSet1() == null) {
            responsable.setProveedormantenimientomaquinaSet1(new HashSet<Proveedormantenimientomaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domicilio domicilio = responsable.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                responsable.setDomicilio(domicilio);
            }
            Domicilio domicilio1 = responsable.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1 = em.getReference(domicilio1.getClass(), domicilio1.getIddomicilio());
                responsable.setDomicilio1(domicilio1);
            }
            Tipodocumento tipodocumento = responsable.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento = em.getReference(tipodocumento.getClass(), tipodocumento.getIdtipodocumento());
                responsable.setTipodocumento(tipodocumento);
            }
            Tipodocumento tipodocumento1 = responsable.getTipodocumento1();
            if (tipodocumento1 != null) {
                tipodocumento1 = em.getReference(tipodocumento1.getClass(), tipodocumento1.getIdtipodocumento());
                responsable.setTipodocumento1(tipodocumento1);
            }
            Set<Empresametalurgica> attachedEmpresametalurgicaSet = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgicaToAttach : responsable.getEmpresametalurgicaSet()) {
                empresametalurgicaSetEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet.add(empresametalurgicaSetEmpresametalurgicaToAttach);
            }
            responsable.setEmpresametalurgicaSet(attachedEmpresametalurgicaSet);
            Set<Empresametalurgica> attachedEmpresametalurgicaSet1 = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSet1EmpresametalurgicaToAttach : responsable.getEmpresametalurgicaSet1()) {
                empresametalurgicaSet1EmpresametalurgicaToAttach = em.getReference(empresametalurgicaSet1EmpresametalurgicaToAttach.getClass(), empresametalurgicaSet1EmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet1.add(empresametalurgicaSet1EmpresametalurgicaToAttach);
            }
            responsable.setEmpresametalurgicaSet1(attachedEmpresametalurgicaSet1);
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : responsable.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            responsable.setClienteSet(attachedClienteSet);
            Set<Cliente> attachedClienteSet1 = new HashSet<Cliente>();
            for (Cliente clienteSet1ClienteToAttach : responsable.getClienteSet1()) {
                clienteSet1ClienteToAttach = em.getReference(clienteSet1ClienteToAttach.getClass(), clienteSet1ClienteToAttach.getIdcliente());
                attachedClienteSet1.add(clienteSet1ClienteToAttach);
            }
            responsable.setClienteSet1(attachedClienteSet1);
            Set<Proveedor> attachedProveedorSet = new HashSet<Proveedor>();
            for (Proveedor proveedorSetProveedorToAttach : responsable.getProveedorSet()) {
                proveedorSetProveedorToAttach = em.getReference(proveedorSetProveedorToAttach.getClass(), proveedorSetProveedorToAttach.getIdproveedor());
                attachedProveedorSet.add(proveedorSetProveedorToAttach);
            }
            responsable.setProveedorSet(attachedProveedorSet);
            Set<Proveedor> attachedProveedorSet1 = new HashSet<Proveedor>();
            for (Proveedor proveedorSet1ProveedorToAttach : responsable.getProveedorSet1()) {
                proveedorSet1ProveedorToAttach = em.getReference(proveedorSet1ProveedorToAttach.getClass(), proveedorSet1ProveedorToAttach.getIdproveedor());
                attachedProveedorSet1.add(proveedorSet1ProveedorToAttach);
            }
            responsable.setProveedorSet1(attachedProveedorSet1);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach : responsable.getProveedormantenimientomaquinaSet()) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet.add(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach);
            }
            responsable.setProveedormantenimientomaquinaSet(attachedProveedormantenimientomaquinaSet);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet1 = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach : responsable.getProveedormantenimientomaquinaSet1()) {
                proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet1.add(proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach);
            }
            responsable.setProveedormantenimientomaquinaSet1(attachedProveedormantenimientomaquinaSet1);
            em.persist(responsable);
            if (domicilio != null) {
                domicilio.getResponsableSet().add(responsable);
                domicilio = em.merge(domicilio);
            }
            if (domicilio1 != null) {
                domicilio1.getResponsableSet().add(responsable);
                domicilio1 = em.merge(domicilio1);
            }
            if (tipodocumento != null) {
                tipodocumento.getResponsableSet().add(responsable);
                tipodocumento = em.merge(tipodocumento);
            }
            if (tipodocumento1 != null) {
                tipodocumento1.getResponsableSet().add(responsable);
                tipodocumento1 = em.merge(tipodocumento1);
            }
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : responsable.getEmpresametalurgicaSet()) {
                Responsable oldResponsableOfEmpresametalurgicaSetEmpresametalurgica = empresametalurgicaSetEmpresametalurgica.getResponsable();
                empresametalurgicaSetEmpresametalurgica.setResponsable(responsable);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
                if (oldResponsableOfEmpresametalurgicaSetEmpresametalurgica != null) {
                    oldResponsableOfEmpresametalurgicaSetEmpresametalurgica.getEmpresametalurgicaSet().remove(empresametalurgicaSetEmpresametalurgica);
                    oldResponsableOfEmpresametalurgicaSetEmpresametalurgica = em.merge(oldResponsableOfEmpresametalurgicaSetEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSet1Empresametalurgica : responsable.getEmpresametalurgicaSet1()) {
                Responsable oldResponsable1OfEmpresametalurgicaSet1Empresametalurgica = empresametalurgicaSet1Empresametalurgica.getResponsable1();
                empresametalurgicaSet1Empresametalurgica.setResponsable1(responsable);
                empresametalurgicaSet1Empresametalurgica = em.merge(empresametalurgicaSet1Empresametalurgica);
                if (oldResponsable1OfEmpresametalurgicaSet1Empresametalurgica != null) {
                    oldResponsable1OfEmpresametalurgicaSet1Empresametalurgica.getEmpresametalurgicaSet1().remove(empresametalurgicaSet1Empresametalurgica);
                    oldResponsable1OfEmpresametalurgicaSet1Empresametalurgica = em.merge(oldResponsable1OfEmpresametalurgicaSet1Empresametalurgica);
                }
            }
            for (Cliente clienteSetCliente : responsable.getClienteSet()) {
                Responsable oldResponsableOfClienteSetCliente = clienteSetCliente.getResponsable();
                clienteSetCliente.setResponsable(responsable);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldResponsableOfClienteSetCliente != null) {
                    oldResponsableOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldResponsableOfClienteSetCliente = em.merge(oldResponsableOfClienteSetCliente);
                }
            }
            for (Cliente clienteSet1Cliente : responsable.getClienteSet1()) {
                Responsable oldResponsable1OfClienteSet1Cliente = clienteSet1Cliente.getResponsable1();
                clienteSet1Cliente.setResponsable1(responsable);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
                if (oldResponsable1OfClienteSet1Cliente != null) {
                    oldResponsable1OfClienteSet1Cliente.getClienteSet1().remove(clienteSet1Cliente);
                    oldResponsable1OfClienteSet1Cliente = em.merge(oldResponsable1OfClienteSet1Cliente);
                }
            }
            for (Proveedor proveedorSetProveedor : responsable.getProveedorSet()) {
                Responsable oldResponsableOfProveedorSetProveedor = proveedorSetProveedor.getResponsable();
                proveedorSetProveedor.setResponsable(responsable);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
                if (oldResponsableOfProveedorSetProveedor != null) {
                    oldResponsableOfProveedorSetProveedor.getProveedorSet().remove(proveedorSetProveedor);
                    oldResponsableOfProveedorSetProveedor = em.merge(oldResponsableOfProveedorSetProveedor);
                }
            }
            for (Proveedor proveedorSet1Proveedor : responsable.getProveedorSet1()) {
                Responsable oldResponsable1OfProveedorSet1Proveedor = proveedorSet1Proveedor.getResponsable1();
                proveedorSet1Proveedor.setResponsable1(responsable);
                proveedorSet1Proveedor = em.merge(proveedorSet1Proveedor);
                if (oldResponsable1OfProveedorSet1Proveedor != null) {
                    oldResponsable1OfProveedorSet1Proveedor.getProveedorSet1().remove(proveedorSet1Proveedor);
                    oldResponsable1OfProveedorSet1Proveedor = em.merge(oldResponsable1OfProveedorSet1Proveedor);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : responsable.getProveedormantenimientomaquinaSet()) {
                Responsable oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = proveedormantenimientomaquinaSetProveedormantenimientomaquina.getResponsable();
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setResponsable(responsable);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                if (oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina != null) {
                    oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                    oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1Proveedormantenimientomaquina : responsable.getProveedormantenimientomaquinaSet1()) {
                Responsable oldResponsable1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina = proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.getResponsable1();
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.setResponsable1(responsable);
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                if (oldResponsable1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina != null) {
                    oldResponsable1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina.getProveedormantenimientomaquinaSet1().remove(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                    oldResponsable1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(oldResponsable1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResponsable(responsable.getIdresponsable()) != null) {
                throw new PreexistingEntityException("Responsable " + responsable + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Responsable responsable) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsable persistentResponsable = em.find(Responsable.class, responsable.getIdresponsable());
            Domicilio domicilioOld = persistentResponsable.getDomicilio();
            Domicilio domicilioNew = responsable.getDomicilio();
            Domicilio domicilio1Old = persistentResponsable.getDomicilio1();
            Domicilio domicilio1New = responsable.getDomicilio1();
            Tipodocumento tipodocumentoOld = persistentResponsable.getTipodocumento();
            Tipodocumento tipodocumentoNew = responsable.getTipodocumento();
            Tipodocumento tipodocumento1Old = persistentResponsable.getTipodocumento1();
            Tipodocumento tipodocumento1New = responsable.getTipodocumento1();
            Set<Empresametalurgica> empresametalurgicaSetOld = persistentResponsable.getEmpresametalurgicaSet();
            Set<Empresametalurgica> empresametalurgicaSetNew = responsable.getEmpresametalurgicaSet();
            Set<Empresametalurgica> empresametalurgicaSet1Old = persistentResponsable.getEmpresametalurgicaSet1();
            Set<Empresametalurgica> empresametalurgicaSet1New = responsable.getEmpresametalurgicaSet1();
            Set<Cliente> clienteSetOld = persistentResponsable.getClienteSet();
            Set<Cliente> clienteSetNew = responsable.getClienteSet();
            Set<Cliente> clienteSet1Old = persistentResponsable.getClienteSet1();
            Set<Cliente> clienteSet1New = responsable.getClienteSet1();
            Set<Proveedor> proveedorSetOld = persistentResponsable.getProveedorSet();
            Set<Proveedor> proveedorSetNew = responsable.getProveedorSet();
            Set<Proveedor> proveedorSet1Old = persistentResponsable.getProveedorSet1();
            Set<Proveedor> proveedorSet1New = responsable.getProveedorSet1();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetOld = persistentResponsable.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetNew = responsable.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1Old = persistentResponsable.getProveedormantenimientomaquinaSet1();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1New = responsable.getProveedormantenimientomaquinaSet1();
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                responsable.setDomicilio(domicilioNew);
            }
            if (domicilio1New != null) {
                domicilio1New = em.getReference(domicilio1New.getClass(), domicilio1New.getIddomicilio());
                responsable.setDomicilio1(domicilio1New);
            }
            if (tipodocumentoNew != null) {
                tipodocumentoNew = em.getReference(tipodocumentoNew.getClass(), tipodocumentoNew.getIdtipodocumento());
                responsable.setTipodocumento(tipodocumentoNew);
            }
            if (tipodocumento1New != null) {
                tipodocumento1New = em.getReference(tipodocumento1New.getClass(), tipodocumento1New.getIdtipodocumento());
                responsable.setTipodocumento1(tipodocumento1New);
            }
            Set<Empresametalurgica> attachedEmpresametalurgicaSetNew = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgicaToAttach : empresametalurgicaSetNew) {
                empresametalurgicaSetNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSetNew.add(empresametalurgicaSetNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSetNew = attachedEmpresametalurgicaSetNew;
            responsable.setEmpresametalurgicaSet(empresametalurgicaSetNew);
            Set<Empresametalurgica> attachedEmpresametalurgicaSet1New = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSet1NewEmpresametalurgicaToAttach : empresametalurgicaSet1New) {
                empresametalurgicaSet1NewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSet1NewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSet1NewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet1New.add(empresametalurgicaSet1NewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSet1New = attachedEmpresametalurgicaSet1New;
            responsable.setEmpresametalurgicaSet1(empresametalurgicaSet1New);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            responsable.setClienteSet(clienteSetNew);
            Set<Cliente> attachedClienteSet1New = new HashSet<Cliente>();
            for (Cliente clienteSet1NewClienteToAttach : clienteSet1New) {
                clienteSet1NewClienteToAttach = em.getReference(clienteSet1NewClienteToAttach.getClass(), clienteSet1NewClienteToAttach.getIdcliente());
                attachedClienteSet1New.add(clienteSet1NewClienteToAttach);
            }
            clienteSet1New = attachedClienteSet1New;
            responsable.setClienteSet1(clienteSet1New);
            Set<Proveedor> attachedProveedorSetNew = new HashSet<Proveedor>();
            for (Proveedor proveedorSetNewProveedorToAttach : proveedorSetNew) {
                proveedorSetNewProveedorToAttach = em.getReference(proveedorSetNewProveedorToAttach.getClass(), proveedorSetNewProveedorToAttach.getIdproveedor());
                attachedProveedorSetNew.add(proveedorSetNewProveedorToAttach);
            }
            proveedorSetNew = attachedProveedorSetNew;
            responsable.setProveedorSet(proveedorSetNew);
            Set<Proveedor> attachedProveedorSet1New = new HashSet<Proveedor>();
            for (Proveedor proveedorSet1NewProveedorToAttach : proveedorSet1New) {
                proveedorSet1NewProveedorToAttach = em.getReference(proveedorSet1NewProveedorToAttach.getClass(), proveedorSet1NewProveedorToAttach.getIdproveedor());
                attachedProveedorSet1New.add(proveedorSet1NewProveedorToAttach);
            }
            proveedorSet1New = attachedProveedorSet1New;
            responsable.setProveedorSet1(proveedorSet1New);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSetNew = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSetNew) {
                proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSetNew.add(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSetNew = attachedProveedormantenimientomaquinaSetNew;
            responsable.setProveedormantenimientomaquinaSet(proveedormantenimientomaquinaSetNew);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet1New = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSet1New) {
                proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet1New.add(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSet1New = attachedProveedormantenimientomaquinaSet1New;
            responsable.setProveedormantenimientomaquinaSet1(proveedormantenimientomaquinaSet1New);
            responsable = em.merge(responsable);
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getResponsableSet().remove(responsable);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getResponsableSet().add(responsable);
                domicilioNew = em.merge(domicilioNew);
            }
            if (domicilio1Old != null && !domicilio1Old.equals(domicilio1New)) {
                domicilio1Old.getResponsableSet().remove(responsable);
                domicilio1Old = em.merge(domicilio1Old);
            }
            if (domicilio1New != null && !domicilio1New.equals(domicilio1Old)) {
                domicilio1New.getResponsableSet().add(responsable);
                domicilio1New = em.merge(domicilio1New);
            }
            if (tipodocumentoOld != null && !tipodocumentoOld.equals(tipodocumentoNew)) {
                tipodocumentoOld.getResponsableSet().remove(responsable);
                tipodocumentoOld = em.merge(tipodocumentoOld);
            }
            if (tipodocumentoNew != null && !tipodocumentoNew.equals(tipodocumentoOld)) {
                tipodocumentoNew.getResponsableSet().add(responsable);
                tipodocumentoNew = em.merge(tipodocumentoNew);
            }
            if (tipodocumento1Old != null && !tipodocumento1Old.equals(tipodocumento1New)) {
                tipodocumento1Old.getResponsableSet().remove(responsable);
                tipodocumento1Old = em.merge(tipodocumento1Old);
            }
            if (tipodocumento1New != null && !tipodocumento1New.equals(tipodocumento1Old)) {
                tipodocumento1New.getResponsableSet().add(responsable);
                tipodocumento1New = em.merge(tipodocumento1New);
            }
            for (Empresametalurgica empresametalurgicaSetOldEmpresametalurgica : empresametalurgicaSetOld) {
                if (!empresametalurgicaSetNew.contains(empresametalurgicaSetOldEmpresametalurgica)) {
                    empresametalurgicaSetOldEmpresametalurgica.setResponsable(null);
                    empresametalurgicaSetOldEmpresametalurgica = em.merge(empresametalurgicaSetOldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgica : empresametalurgicaSetNew) {
                if (!empresametalurgicaSetOld.contains(empresametalurgicaSetNewEmpresametalurgica)) {
                    Responsable oldResponsableOfEmpresametalurgicaSetNewEmpresametalurgica = empresametalurgicaSetNewEmpresametalurgica.getResponsable();
                    empresametalurgicaSetNewEmpresametalurgica.setResponsable(responsable);
                    empresametalurgicaSetNewEmpresametalurgica = em.merge(empresametalurgicaSetNewEmpresametalurgica);
                    if (oldResponsableOfEmpresametalurgicaSetNewEmpresametalurgica != null && !oldResponsableOfEmpresametalurgicaSetNewEmpresametalurgica.equals(responsable)) {
                        oldResponsableOfEmpresametalurgicaSetNewEmpresametalurgica.getEmpresametalurgicaSet().remove(empresametalurgicaSetNewEmpresametalurgica);
                        oldResponsableOfEmpresametalurgicaSetNewEmpresametalurgica = em.merge(oldResponsableOfEmpresametalurgicaSetNewEmpresametalurgica);
                    }
                }
            }
            for (Empresametalurgica empresametalurgicaSet1OldEmpresametalurgica : empresametalurgicaSet1Old) {
                if (!empresametalurgicaSet1New.contains(empresametalurgicaSet1OldEmpresametalurgica)) {
                    empresametalurgicaSet1OldEmpresametalurgica.setResponsable1(null);
                    empresametalurgicaSet1OldEmpresametalurgica = em.merge(empresametalurgicaSet1OldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSet1NewEmpresametalurgica : empresametalurgicaSet1New) {
                if (!empresametalurgicaSet1Old.contains(empresametalurgicaSet1NewEmpresametalurgica)) {
                    Responsable oldResponsable1OfEmpresametalurgicaSet1NewEmpresametalurgica = empresametalurgicaSet1NewEmpresametalurgica.getResponsable1();
                    empresametalurgicaSet1NewEmpresametalurgica.setResponsable1(responsable);
                    empresametalurgicaSet1NewEmpresametalurgica = em.merge(empresametalurgicaSet1NewEmpresametalurgica);
                    if (oldResponsable1OfEmpresametalurgicaSet1NewEmpresametalurgica != null && !oldResponsable1OfEmpresametalurgicaSet1NewEmpresametalurgica.equals(responsable)) {
                        oldResponsable1OfEmpresametalurgicaSet1NewEmpresametalurgica.getEmpresametalurgicaSet1().remove(empresametalurgicaSet1NewEmpresametalurgica);
                        oldResponsable1OfEmpresametalurgicaSet1NewEmpresametalurgica = em.merge(oldResponsable1OfEmpresametalurgicaSet1NewEmpresametalurgica);
                    }
                }
            }
            for (Cliente clienteSetOldCliente : clienteSetOld) {
                if (!clienteSetNew.contains(clienteSetOldCliente)) {
                    clienteSetOldCliente.setResponsable(null);
                    clienteSetOldCliente = em.merge(clienteSetOldCliente);
                }
            }
            for (Cliente clienteSetNewCliente : clienteSetNew) {
                if (!clienteSetOld.contains(clienteSetNewCliente)) {
                    Responsable oldResponsableOfClienteSetNewCliente = clienteSetNewCliente.getResponsable();
                    clienteSetNewCliente.setResponsable(responsable);
                    clienteSetNewCliente = em.merge(clienteSetNewCliente);
                    if (oldResponsableOfClienteSetNewCliente != null && !oldResponsableOfClienteSetNewCliente.equals(responsable)) {
                        oldResponsableOfClienteSetNewCliente.getClienteSet().remove(clienteSetNewCliente);
                        oldResponsableOfClienteSetNewCliente = em.merge(oldResponsableOfClienteSetNewCliente);
                    }
                }
            }
            for (Cliente clienteSet1OldCliente : clienteSet1Old) {
                if (!clienteSet1New.contains(clienteSet1OldCliente)) {
                    clienteSet1OldCliente.setResponsable1(null);
                    clienteSet1OldCliente = em.merge(clienteSet1OldCliente);
                }
            }
            for (Cliente clienteSet1NewCliente : clienteSet1New) {
                if (!clienteSet1Old.contains(clienteSet1NewCliente)) {
                    Responsable oldResponsable1OfClienteSet1NewCliente = clienteSet1NewCliente.getResponsable1();
                    clienteSet1NewCliente.setResponsable1(responsable);
                    clienteSet1NewCliente = em.merge(clienteSet1NewCliente);
                    if (oldResponsable1OfClienteSet1NewCliente != null && !oldResponsable1OfClienteSet1NewCliente.equals(responsable)) {
                        oldResponsable1OfClienteSet1NewCliente.getClienteSet1().remove(clienteSet1NewCliente);
                        oldResponsable1OfClienteSet1NewCliente = em.merge(oldResponsable1OfClienteSet1NewCliente);
                    }
                }
            }
            for (Proveedor proveedorSetOldProveedor : proveedorSetOld) {
                if (!proveedorSetNew.contains(proveedorSetOldProveedor)) {
                    proveedorSetOldProveedor.setResponsable(null);
                    proveedorSetOldProveedor = em.merge(proveedorSetOldProveedor);
                }
            }
            for (Proveedor proveedorSetNewProveedor : proveedorSetNew) {
                if (!proveedorSetOld.contains(proveedorSetNewProveedor)) {
                    Responsable oldResponsableOfProveedorSetNewProveedor = proveedorSetNewProveedor.getResponsable();
                    proveedorSetNewProveedor.setResponsable(responsable);
                    proveedorSetNewProveedor = em.merge(proveedorSetNewProveedor);
                    if (oldResponsableOfProveedorSetNewProveedor != null && !oldResponsableOfProveedorSetNewProveedor.equals(responsable)) {
                        oldResponsableOfProveedorSetNewProveedor.getProveedorSet().remove(proveedorSetNewProveedor);
                        oldResponsableOfProveedorSetNewProveedor = em.merge(oldResponsableOfProveedorSetNewProveedor);
                    }
                }
            }
            for (Proveedor proveedorSet1OldProveedor : proveedorSet1Old) {
                if (!proveedorSet1New.contains(proveedorSet1OldProveedor)) {
                    proveedorSet1OldProveedor.setResponsable1(null);
                    proveedorSet1OldProveedor = em.merge(proveedorSet1OldProveedor);
                }
            }
            for (Proveedor proveedorSet1NewProveedor : proveedorSet1New) {
                if (!proveedorSet1Old.contains(proveedorSet1NewProveedor)) {
                    Responsable oldResponsable1OfProveedorSet1NewProveedor = proveedorSet1NewProveedor.getResponsable1();
                    proveedorSet1NewProveedor.setResponsable1(responsable);
                    proveedorSet1NewProveedor = em.merge(proveedorSet1NewProveedor);
                    if (oldResponsable1OfProveedorSet1NewProveedor != null && !oldResponsable1OfProveedorSet1NewProveedor.equals(responsable)) {
                        oldResponsable1OfProveedorSet1NewProveedor.getProveedorSet1().remove(proveedorSet1NewProveedor);
                        oldResponsable1OfProveedorSet1NewProveedor = em.merge(oldResponsable1OfProveedorSet1NewProveedor);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetOldProveedormantenimientomaquina : proveedormantenimientomaquinaSetOld) {
                if (!proveedormantenimientomaquinaSetNew.contains(proveedormantenimientomaquinaSetOldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaSetOldProveedormantenimientomaquina.setResponsable(null);
                    proveedormantenimientomaquinaSetOldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetOldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquina : proveedormantenimientomaquinaSetNew) {
                if (!proveedormantenimientomaquinaSetOld.contains(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina)) {
                    Responsable oldResponsableOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina = proveedormantenimientomaquinaSetNewProveedormantenimientomaquina.getResponsable();
                    proveedormantenimientomaquinaSetNewProveedormantenimientomaquina.setResponsable(responsable);
                    proveedormantenimientomaquinaSetNewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                    if (oldResponsableOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina != null && !oldResponsableOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina.equals(responsable)) {
                        oldResponsableOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                        oldResponsableOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina = em.merge(oldResponsableOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina : proveedormantenimientomaquinaSet1Old) {
                if (!proveedormantenimientomaquinaSet1New.contains(proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina.setResponsable1(null);
                    proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina : proveedormantenimientomaquinaSet1New) {
                if (!proveedormantenimientomaquinaSet1Old.contains(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina)) {
                    Responsable oldResponsable1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.getResponsable1();
                    proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.setResponsable1(responsable);
                    proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                    if (oldResponsable1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina != null && !oldResponsable1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.equals(responsable)) {
                        oldResponsable1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.getProveedormantenimientomaquinaSet1().remove(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                        oldResponsable1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = em.merge(oldResponsable1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = responsable.getIdresponsable();
                if (findResponsable(id) == null) {
                    throw new NonexistentEntityException("The responsable with id " + id + " no longer exists.");
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
            Responsable responsable;
            try {
                responsable = em.getReference(Responsable.class, id);
                responsable.getIdresponsable();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The responsable with id " + id + " no longer exists.", enfe);
            }
            Domicilio domicilio = responsable.getDomicilio();
            if (domicilio != null) {
                domicilio.getResponsableSet().remove(responsable);
                domicilio = em.merge(domicilio);
            }
            Domicilio domicilio1 = responsable.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1.getResponsableSet().remove(responsable);
                domicilio1 = em.merge(domicilio1);
            }
            Tipodocumento tipodocumento = responsable.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento.getResponsableSet().remove(responsable);
                tipodocumento = em.merge(tipodocumento);
            }
            Tipodocumento tipodocumento1 = responsable.getTipodocumento1();
            if (tipodocumento1 != null) {
                tipodocumento1.getResponsableSet().remove(responsable);
                tipodocumento1 = em.merge(tipodocumento1);
            }
            Set<Empresametalurgica> empresametalurgicaSet = responsable.getEmpresametalurgicaSet();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : empresametalurgicaSet) {
                empresametalurgicaSetEmpresametalurgica.setResponsable(null);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
            }
            Set<Empresametalurgica> empresametalurgicaSet1 = responsable.getEmpresametalurgicaSet1();
            for (Empresametalurgica empresametalurgicaSet1Empresametalurgica : empresametalurgicaSet1) {
                empresametalurgicaSet1Empresametalurgica.setResponsable1(null);
                empresametalurgicaSet1Empresametalurgica = em.merge(empresametalurgicaSet1Empresametalurgica);
            }
            Set<Cliente> clienteSet = responsable.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setResponsable(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Cliente> clienteSet1 = responsable.getClienteSet1();
            for (Cliente clienteSet1Cliente : clienteSet1) {
                clienteSet1Cliente.setResponsable1(null);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
            }
            Set<Proveedor> proveedorSet = responsable.getProveedorSet();
            for (Proveedor proveedorSetProveedor : proveedorSet) {
                proveedorSetProveedor.setResponsable(null);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
            }
            Set<Proveedor> proveedorSet1 = responsable.getProveedorSet1();
            for (Proveedor proveedorSet1Proveedor : proveedorSet1) {
                proveedorSet1Proveedor.setResponsable1(null);
                proveedorSet1Proveedor = em.merge(proveedorSet1Proveedor);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet = responsable.getProveedormantenimientomaquinaSet();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : proveedormantenimientomaquinaSet) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setResponsable(null);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1 = responsable.getProveedormantenimientomaquinaSet1();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1Proveedormantenimientomaquina : proveedormantenimientomaquinaSet1) {
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.setResponsable1(null);
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
            }
            em.remove(responsable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Responsable> findResponsableEntities() {
        return findResponsableEntities(true, -1, -1);
    }

    public List<Responsable> findResponsableEntities(int maxResults, int firstResult) {
        return findResponsableEntities(false, maxResults, firstResult);
    }

    private List<Responsable> findResponsableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Responsable.class));
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

    public Responsable findResponsable(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Responsable.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsableCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Responsable> rt = cq.from(Responsable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
