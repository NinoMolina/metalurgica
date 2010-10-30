/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Condicioniva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class CondicionivaJpaController {

    public CondicionivaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Condicioniva condicioniva) throws PreexistingEntityException, Exception {
        if (condicioniva.getEmpresametalurgicaSet() == null) {
            condicioniva.setEmpresametalurgicaSet(new HashSet<Empresametalurgica>());
        }
        if (condicioniva.getEmpresametalurgicaSet1() == null) {
            condicioniva.setEmpresametalurgicaSet1(new HashSet<Empresametalurgica>());
        }
        if (condicioniva.getClienteSet() == null) {
            condicioniva.setClienteSet(new HashSet<Cliente>());
        }
        if (condicioniva.getClienteSet1() == null) {
            condicioniva.setClienteSet1(new HashSet<Cliente>());
        }
        if (condicioniva.getProveedorSet() == null) {
            condicioniva.setProveedorSet(new HashSet<Proveedor>());
        }
        if (condicioniva.getProveedorSet1() == null) {
            condicioniva.setProveedorSet1(new HashSet<Proveedor>());
        }
        if (condicioniva.getProveedormantenimientomaquinaSet() == null) {
            condicioniva.setProveedormantenimientomaquinaSet(new HashSet<Proveedormantenimientomaquina>());
        }
        if (condicioniva.getProveedormantenimientomaquinaSet1() == null) {
            condicioniva.setProveedormantenimientomaquinaSet1(new HashSet<Proveedormantenimientomaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Empresametalurgica> attachedEmpresametalurgicaSet = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgicaToAttach : condicioniva.getEmpresametalurgicaSet()) {
                empresametalurgicaSetEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet.add(empresametalurgicaSetEmpresametalurgicaToAttach);
            }
            condicioniva.setEmpresametalurgicaSet(attachedEmpresametalurgicaSet);
            Set<Empresametalurgica> attachedEmpresametalurgicaSet1 = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSet1EmpresametalurgicaToAttach : condicioniva.getEmpresametalurgicaSet1()) {
                empresametalurgicaSet1EmpresametalurgicaToAttach = em.getReference(empresametalurgicaSet1EmpresametalurgicaToAttach.getClass(), empresametalurgicaSet1EmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet1.add(empresametalurgicaSet1EmpresametalurgicaToAttach);
            }
            condicioniva.setEmpresametalurgicaSet1(attachedEmpresametalurgicaSet1);
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : condicioniva.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            condicioniva.setClienteSet(attachedClienteSet);
            Set<Cliente> attachedClienteSet1 = new HashSet<Cliente>();
            for (Cliente clienteSet1ClienteToAttach : condicioniva.getClienteSet1()) {
                clienteSet1ClienteToAttach = em.getReference(clienteSet1ClienteToAttach.getClass(), clienteSet1ClienteToAttach.getIdcliente());
                attachedClienteSet1.add(clienteSet1ClienteToAttach);
            }
            condicioniva.setClienteSet1(attachedClienteSet1);
            Set<Proveedor> attachedProveedorSet = new HashSet<Proveedor>();
            for (Proveedor proveedorSetProveedorToAttach : condicioniva.getProveedorSet()) {
                proveedorSetProveedorToAttach = em.getReference(proveedorSetProveedorToAttach.getClass(), proveedorSetProveedorToAttach.getIdproveedor());
                attachedProveedorSet.add(proveedorSetProveedorToAttach);
            }
            condicioniva.setProveedorSet(attachedProveedorSet);
            Set<Proveedor> attachedProveedorSet1 = new HashSet<Proveedor>();
            for (Proveedor proveedorSet1ProveedorToAttach : condicioniva.getProveedorSet1()) {
                proveedorSet1ProveedorToAttach = em.getReference(proveedorSet1ProveedorToAttach.getClass(), proveedorSet1ProveedorToAttach.getIdproveedor());
                attachedProveedorSet1.add(proveedorSet1ProveedorToAttach);
            }
            condicioniva.setProveedorSet1(attachedProveedorSet1);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach : condicioniva.getProveedormantenimientomaquinaSet()) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet.add(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach);
            }
            condicioniva.setProveedormantenimientomaquinaSet(attachedProveedormantenimientomaquinaSet);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet1 = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach : condicioniva.getProveedormantenimientomaquinaSet1()) {
                proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet1.add(proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach);
            }
            condicioniva.setProveedormantenimientomaquinaSet1(attachedProveedormantenimientomaquinaSet1);
            em.persist(condicioniva);
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : condicioniva.getEmpresametalurgicaSet()) {
                Condicioniva oldCondicionivaOfEmpresametalurgicaSetEmpresametalurgica = empresametalurgicaSetEmpresametalurgica.getCondicioniva();
                empresametalurgicaSetEmpresametalurgica.setCondicioniva(condicioniva);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
                if (oldCondicionivaOfEmpresametalurgicaSetEmpresametalurgica != null) {
                    oldCondicionivaOfEmpresametalurgicaSetEmpresametalurgica.getEmpresametalurgicaSet().remove(empresametalurgicaSetEmpresametalurgica);
                    oldCondicionivaOfEmpresametalurgicaSetEmpresametalurgica = em.merge(oldCondicionivaOfEmpresametalurgicaSetEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSet1Empresametalurgica : condicioniva.getEmpresametalurgicaSet1()) {
                Condicioniva oldCondicioniva1OfEmpresametalurgicaSet1Empresametalurgica = empresametalurgicaSet1Empresametalurgica.getCondicioniva1();
                empresametalurgicaSet1Empresametalurgica.setCondicioniva1(condicioniva);
                empresametalurgicaSet1Empresametalurgica = em.merge(empresametalurgicaSet1Empresametalurgica);
                if (oldCondicioniva1OfEmpresametalurgicaSet1Empresametalurgica != null) {
                    oldCondicioniva1OfEmpresametalurgicaSet1Empresametalurgica.getEmpresametalurgicaSet1().remove(empresametalurgicaSet1Empresametalurgica);
                    oldCondicioniva1OfEmpresametalurgicaSet1Empresametalurgica = em.merge(oldCondicioniva1OfEmpresametalurgicaSet1Empresametalurgica);
                }
            }
            for (Cliente clienteSetCliente : condicioniva.getClienteSet()) {
                Condicioniva oldCondicionivaOfClienteSetCliente = clienteSetCliente.getCondicioniva();
                clienteSetCliente.setCondicioniva(condicioniva);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldCondicionivaOfClienteSetCliente != null) {
                    oldCondicionivaOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldCondicionivaOfClienteSetCliente = em.merge(oldCondicionivaOfClienteSetCliente);
                }
            }
            for (Cliente clienteSet1Cliente : condicioniva.getClienteSet1()) {
                Condicioniva oldCondicioniva1OfClienteSet1Cliente = clienteSet1Cliente.getCondicioniva1();
                clienteSet1Cliente.setCondicioniva1(condicioniva);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
                if (oldCondicioniva1OfClienteSet1Cliente != null) {
                    oldCondicioniva1OfClienteSet1Cliente.getClienteSet1().remove(clienteSet1Cliente);
                    oldCondicioniva1OfClienteSet1Cliente = em.merge(oldCondicioniva1OfClienteSet1Cliente);
                }
            }
            for (Proveedor proveedorSetProveedor : condicioniva.getProveedorSet()) {
                Condicioniva oldCondicionOfProveedorSetProveedor = proveedorSetProveedor.getCondicion();
                proveedorSetProveedor.setCondicion(condicioniva);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
                if (oldCondicionOfProveedorSetProveedor != null) {
                    oldCondicionOfProveedorSetProveedor.getProveedorSet().remove(proveedorSetProveedor);
                    oldCondicionOfProveedorSetProveedor = em.merge(oldCondicionOfProveedorSetProveedor);
                }
            }
            for (Proveedor proveedorSet1Proveedor : condicioniva.getProveedorSet1()) {
                Condicioniva oldCondicion1OfProveedorSet1Proveedor = proveedorSet1Proveedor.getCondicion1();
                proveedorSet1Proveedor.setCondicion1(condicioniva);
                proveedorSet1Proveedor = em.merge(proveedorSet1Proveedor);
                if (oldCondicion1OfProveedorSet1Proveedor != null) {
                    oldCondicion1OfProveedorSet1Proveedor.getProveedorSet1().remove(proveedorSet1Proveedor);
                    oldCondicion1OfProveedorSet1Proveedor = em.merge(oldCondicion1OfProveedorSet1Proveedor);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : condicioniva.getProveedormantenimientomaquinaSet()) {
                Condicioniva oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = proveedormantenimientomaquinaSetProveedormantenimientomaquina.getCondicioniva();
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setCondicioniva(condicioniva);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                if (oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina != null) {
                    oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                    oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1Proveedormantenimientomaquina : condicioniva.getProveedormantenimientomaquinaSet1()) {
                Condicioniva oldCondicioniva1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina = proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.getCondicioniva1();
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.setCondicioniva1(condicioniva);
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                if (oldCondicioniva1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina != null) {
                    oldCondicioniva1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina.getProveedormantenimientomaquinaSet1().remove(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                    oldCondicioniva1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(oldCondicioniva1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCondicioniva(condicioniva.getIdcondicioniva()) != null) {
                throw new PreexistingEntityException("Condicioniva " + condicioniva + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Condicioniva condicioniva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condicioniva persistentCondicioniva = em.find(Condicioniva.class, condicioniva.getIdcondicioniva());
            Set<Empresametalurgica> empresametalurgicaSetOld = persistentCondicioniva.getEmpresametalurgicaSet();
            Set<Empresametalurgica> empresametalurgicaSetNew = condicioniva.getEmpresametalurgicaSet();
            Set<Empresametalurgica> empresametalurgicaSet1Old = persistentCondicioniva.getEmpresametalurgicaSet1();
            Set<Empresametalurgica> empresametalurgicaSet1New = condicioniva.getEmpresametalurgicaSet1();
            Set<Cliente> clienteSetOld = persistentCondicioniva.getClienteSet();
            Set<Cliente> clienteSetNew = condicioniva.getClienteSet();
            Set<Cliente> clienteSet1Old = persistentCondicioniva.getClienteSet1();
            Set<Cliente> clienteSet1New = condicioniva.getClienteSet1();
            Set<Proveedor> proveedorSetOld = persistentCondicioniva.getProveedorSet();
            Set<Proveedor> proveedorSetNew = condicioniva.getProveedorSet();
            Set<Proveedor> proveedorSet1Old = persistentCondicioniva.getProveedorSet1();
            Set<Proveedor> proveedorSet1New = condicioniva.getProveedorSet1();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetOld = persistentCondicioniva.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetNew = condicioniva.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1Old = persistentCondicioniva.getProveedormantenimientomaquinaSet1();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1New = condicioniva.getProveedormantenimientomaquinaSet1();
            Set<Empresametalurgica> attachedEmpresametalurgicaSetNew = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgicaToAttach : empresametalurgicaSetNew) {
                empresametalurgicaSetNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSetNew.add(empresametalurgicaSetNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSetNew = attachedEmpresametalurgicaSetNew;
            condicioniva.setEmpresametalurgicaSet(empresametalurgicaSetNew);
            Set<Empresametalurgica> attachedEmpresametalurgicaSet1New = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSet1NewEmpresametalurgicaToAttach : empresametalurgicaSet1New) {
                empresametalurgicaSet1NewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSet1NewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSet1NewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet1New.add(empresametalurgicaSet1NewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSet1New = attachedEmpresametalurgicaSet1New;
            condicioniva.setEmpresametalurgicaSet1(empresametalurgicaSet1New);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            condicioniva.setClienteSet(clienteSetNew);
            Set<Cliente> attachedClienteSet1New = new HashSet<Cliente>();
            for (Cliente clienteSet1NewClienteToAttach : clienteSet1New) {
                clienteSet1NewClienteToAttach = em.getReference(clienteSet1NewClienteToAttach.getClass(), clienteSet1NewClienteToAttach.getIdcliente());
                attachedClienteSet1New.add(clienteSet1NewClienteToAttach);
            }
            clienteSet1New = attachedClienteSet1New;
            condicioniva.setClienteSet1(clienteSet1New);
            Set<Proveedor> attachedProveedorSetNew = new HashSet<Proveedor>();
            for (Proveedor proveedorSetNewProveedorToAttach : proveedorSetNew) {
                proveedorSetNewProveedorToAttach = em.getReference(proveedorSetNewProveedorToAttach.getClass(), proveedorSetNewProveedorToAttach.getIdproveedor());
                attachedProveedorSetNew.add(proveedorSetNewProveedorToAttach);
            }
            proveedorSetNew = attachedProveedorSetNew;
            condicioniva.setProveedorSet(proveedorSetNew);
            Set<Proveedor> attachedProveedorSet1New = new HashSet<Proveedor>();
            for (Proveedor proveedorSet1NewProveedorToAttach : proveedorSet1New) {
                proveedorSet1NewProveedorToAttach = em.getReference(proveedorSet1NewProveedorToAttach.getClass(), proveedorSet1NewProveedorToAttach.getIdproveedor());
                attachedProveedorSet1New.add(proveedorSet1NewProveedorToAttach);
            }
            proveedorSet1New = attachedProveedorSet1New;
            condicioniva.setProveedorSet1(proveedorSet1New);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSetNew = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSetNew) {
                proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSetNew.add(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSetNew = attachedProveedormantenimientomaquinaSetNew;
            condicioniva.setProveedormantenimientomaquinaSet(proveedormantenimientomaquinaSetNew);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet1New = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSet1New) {
                proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet1New.add(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSet1New = attachedProveedormantenimientomaquinaSet1New;
            condicioniva.setProveedormantenimientomaquinaSet1(proveedormantenimientomaquinaSet1New);
            condicioniva = em.merge(condicioniva);
            for (Empresametalurgica empresametalurgicaSetOldEmpresametalurgica : empresametalurgicaSetOld) {
                if (!empresametalurgicaSetNew.contains(empresametalurgicaSetOldEmpresametalurgica)) {
                    empresametalurgicaSetOldEmpresametalurgica.setCondicioniva(null);
                    empresametalurgicaSetOldEmpresametalurgica = em.merge(empresametalurgicaSetOldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgica : empresametalurgicaSetNew) {
                if (!empresametalurgicaSetOld.contains(empresametalurgicaSetNewEmpresametalurgica)) {
                    Condicioniva oldCondicionivaOfEmpresametalurgicaSetNewEmpresametalurgica = empresametalurgicaSetNewEmpresametalurgica.getCondicioniva();
                    empresametalurgicaSetNewEmpresametalurgica.setCondicioniva(condicioniva);
                    empresametalurgicaSetNewEmpresametalurgica = em.merge(empresametalurgicaSetNewEmpresametalurgica);
                    if (oldCondicionivaOfEmpresametalurgicaSetNewEmpresametalurgica != null && !oldCondicionivaOfEmpresametalurgicaSetNewEmpresametalurgica.equals(condicioniva)) {
                        oldCondicionivaOfEmpresametalurgicaSetNewEmpresametalurgica.getEmpresametalurgicaSet().remove(empresametalurgicaSetNewEmpresametalurgica);
                        oldCondicionivaOfEmpresametalurgicaSetNewEmpresametalurgica = em.merge(oldCondicionivaOfEmpresametalurgicaSetNewEmpresametalurgica);
                    }
                }
            }
            for (Empresametalurgica empresametalurgicaSet1OldEmpresametalurgica : empresametalurgicaSet1Old) {
                if (!empresametalurgicaSet1New.contains(empresametalurgicaSet1OldEmpresametalurgica)) {
                    empresametalurgicaSet1OldEmpresametalurgica.setCondicioniva1(null);
                    empresametalurgicaSet1OldEmpresametalurgica = em.merge(empresametalurgicaSet1OldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSet1NewEmpresametalurgica : empresametalurgicaSet1New) {
                if (!empresametalurgicaSet1Old.contains(empresametalurgicaSet1NewEmpresametalurgica)) {
                    Condicioniva oldCondicioniva1OfEmpresametalurgicaSet1NewEmpresametalurgica = empresametalurgicaSet1NewEmpresametalurgica.getCondicioniva1();
                    empresametalurgicaSet1NewEmpresametalurgica.setCondicioniva1(condicioniva);
                    empresametalurgicaSet1NewEmpresametalurgica = em.merge(empresametalurgicaSet1NewEmpresametalurgica);
                    if (oldCondicioniva1OfEmpresametalurgicaSet1NewEmpresametalurgica != null && !oldCondicioniva1OfEmpresametalurgicaSet1NewEmpresametalurgica.equals(condicioniva)) {
                        oldCondicioniva1OfEmpresametalurgicaSet1NewEmpresametalurgica.getEmpresametalurgicaSet1().remove(empresametalurgicaSet1NewEmpresametalurgica);
                        oldCondicioniva1OfEmpresametalurgicaSet1NewEmpresametalurgica = em.merge(oldCondicioniva1OfEmpresametalurgicaSet1NewEmpresametalurgica);
                    }
                }
            }
            for (Cliente clienteSetOldCliente : clienteSetOld) {
                if (!clienteSetNew.contains(clienteSetOldCliente)) {
                    clienteSetOldCliente.setCondicioniva(null);
                    clienteSetOldCliente = em.merge(clienteSetOldCliente);
                }
            }
            for (Cliente clienteSetNewCliente : clienteSetNew) {
                if (!clienteSetOld.contains(clienteSetNewCliente)) {
                    Condicioniva oldCondicionivaOfClienteSetNewCliente = clienteSetNewCliente.getCondicioniva();
                    clienteSetNewCliente.setCondicioniva(condicioniva);
                    clienteSetNewCliente = em.merge(clienteSetNewCliente);
                    if (oldCondicionivaOfClienteSetNewCliente != null && !oldCondicionivaOfClienteSetNewCliente.equals(condicioniva)) {
                        oldCondicionivaOfClienteSetNewCliente.getClienteSet().remove(clienteSetNewCliente);
                        oldCondicionivaOfClienteSetNewCliente = em.merge(oldCondicionivaOfClienteSetNewCliente);
                    }
                }
            }
            for (Cliente clienteSet1OldCliente : clienteSet1Old) {
                if (!clienteSet1New.contains(clienteSet1OldCliente)) {
                    clienteSet1OldCliente.setCondicioniva1(null);
                    clienteSet1OldCliente = em.merge(clienteSet1OldCliente);
                }
            }
            for (Cliente clienteSet1NewCliente : clienteSet1New) {
                if (!clienteSet1Old.contains(clienteSet1NewCliente)) {
                    Condicioniva oldCondicioniva1OfClienteSet1NewCliente = clienteSet1NewCliente.getCondicioniva1();
                    clienteSet1NewCliente.setCondicioniva1(condicioniva);
                    clienteSet1NewCliente = em.merge(clienteSet1NewCliente);
                    if (oldCondicioniva1OfClienteSet1NewCliente != null && !oldCondicioniva1OfClienteSet1NewCliente.equals(condicioniva)) {
                        oldCondicioniva1OfClienteSet1NewCliente.getClienteSet1().remove(clienteSet1NewCliente);
                        oldCondicioniva1OfClienteSet1NewCliente = em.merge(oldCondicioniva1OfClienteSet1NewCliente);
                    }
                }
            }
            for (Proveedor proveedorSetOldProveedor : proveedorSetOld) {
                if (!proveedorSetNew.contains(proveedorSetOldProveedor)) {
                    proveedorSetOldProveedor.setCondicion(null);
                    proveedorSetOldProveedor = em.merge(proveedorSetOldProveedor);
                }
            }
            for (Proveedor proveedorSetNewProveedor : proveedorSetNew) {
                if (!proveedorSetOld.contains(proveedorSetNewProveedor)) {
                    Condicioniva oldCondicionOfProveedorSetNewProveedor = proveedorSetNewProveedor.getCondicion();
                    proveedorSetNewProveedor.setCondicion(condicioniva);
                    proveedorSetNewProveedor = em.merge(proveedorSetNewProveedor);
                    if (oldCondicionOfProveedorSetNewProveedor != null && !oldCondicionOfProveedorSetNewProveedor.equals(condicioniva)) {
                        oldCondicionOfProveedorSetNewProveedor.getProveedorSet().remove(proveedorSetNewProveedor);
                        oldCondicionOfProveedorSetNewProveedor = em.merge(oldCondicionOfProveedorSetNewProveedor);
                    }
                }
            }
            for (Proveedor proveedorSet1OldProveedor : proveedorSet1Old) {
                if (!proveedorSet1New.contains(proveedorSet1OldProveedor)) {
                    proveedorSet1OldProveedor.setCondicion1(null);
                    proveedorSet1OldProveedor = em.merge(proveedorSet1OldProveedor);
                }
            }
            for (Proveedor proveedorSet1NewProveedor : proveedorSet1New) {
                if (!proveedorSet1Old.contains(proveedorSet1NewProveedor)) {
                    Condicioniva oldCondicion1OfProveedorSet1NewProveedor = proveedorSet1NewProveedor.getCondicion1();
                    proveedorSet1NewProveedor.setCondicion1(condicioniva);
                    proveedorSet1NewProveedor = em.merge(proveedorSet1NewProveedor);
                    if (oldCondicion1OfProveedorSet1NewProveedor != null && !oldCondicion1OfProveedorSet1NewProveedor.equals(condicioniva)) {
                        oldCondicion1OfProveedorSet1NewProveedor.getProveedorSet1().remove(proveedorSet1NewProveedor);
                        oldCondicion1OfProveedorSet1NewProveedor = em.merge(oldCondicion1OfProveedorSet1NewProveedor);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetOldProveedormantenimientomaquina : proveedormantenimientomaquinaSetOld) {
                if (!proveedormantenimientomaquinaSetNew.contains(proveedormantenimientomaquinaSetOldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaSetOldProveedormantenimientomaquina.setCondicioniva(null);
                    proveedormantenimientomaquinaSetOldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetOldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquina : proveedormantenimientomaquinaSetNew) {
                if (!proveedormantenimientomaquinaSetOld.contains(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina)) {
                    Condicioniva oldCondicionivaOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina = proveedormantenimientomaquinaSetNewProveedormantenimientomaquina.getCondicioniva();
                    proveedormantenimientomaquinaSetNewProveedormantenimientomaquina.setCondicioniva(condicioniva);
                    proveedormantenimientomaquinaSetNewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                    if (oldCondicionivaOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina != null && !oldCondicionivaOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina.equals(condicioniva)) {
                        oldCondicionivaOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                        oldCondicionivaOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina = em.merge(oldCondicionivaOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina : proveedormantenimientomaquinaSet1Old) {
                if (!proveedormantenimientomaquinaSet1New.contains(proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina.setCondicioniva1(null);
                    proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina : proveedormantenimientomaquinaSet1New) {
                if (!proveedormantenimientomaquinaSet1Old.contains(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina)) {
                    Condicioniva oldCondicioniva1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.getCondicioniva1();
                    proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.setCondicioniva1(condicioniva);
                    proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                    if (oldCondicioniva1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina != null && !oldCondicioniva1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.equals(condicioniva)) {
                        oldCondicioniva1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.getProveedormantenimientomaquinaSet1().remove(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                        oldCondicioniva1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = em.merge(oldCondicioniva1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = condicioniva.getIdcondicioniva();
                if (findCondicioniva(id) == null) {
                    throw new NonexistentEntityException("The condicioniva with id " + id + " no longer exists.");
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
            Condicioniva condicioniva;
            try {
                condicioniva = em.getReference(Condicioniva.class, id);
                condicioniva.getIdcondicioniva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The condicioniva with id " + id + " no longer exists.", enfe);
            }
            Set<Empresametalurgica> empresametalurgicaSet = condicioniva.getEmpresametalurgicaSet();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : empresametalurgicaSet) {
                empresametalurgicaSetEmpresametalurgica.setCondicioniva(null);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
            }
            Set<Empresametalurgica> empresametalurgicaSet1 = condicioniva.getEmpresametalurgicaSet1();
            for (Empresametalurgica empresametalurgicaSet1Empresametalurgica : empresametalurgicaSet1) {
                empresametalurgicaSet1Empresametalurgica.setCondicioniva1(null);
                empresametalurgicaSet1Empresametalurgica = em.merge(empresametalurgicaSet1Empresametalurgica);
            }
            Set<Cliente> clienteSet = condicioniva.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setCondicioniva(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Cliente> clienteSet1 = condicioniva.getClienteSet1();
            for (Cliente clienteSet1Cliente : clienteSet1) {
                clienteSet1Cliente.setCondicioniva1(null);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
            }
            Set<Proveedor> proveedorSet = condicioniva.getProveedorSet();
            for (Proveedor proveedorSetProveedor : proveedorSet) {
                proveedorSetProveedor.setCondicion(null);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
            }
            Set<Proveedor> proveedorSet1 = condicioniva.getProveedorSet1();
            for (Proveedor proveedorSet1Proveedor : proveedorSet1) {
                proveedorSet1Proveedor.setCondicion1(null);
                proveedorSet1Proveedor = em.merge(proveedorSet1Proveedor);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet = condicioniva.getProveedormantenimientomaquinaSet();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : proveedormantenimientomaquinaSet) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setCondicioniva(null);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1 = condicioniva.getProveedormantenimientomaquinaSet1();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1Proveedormantenimientomaquina : proveedormantenimientomaquinaSet1) {
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.setCondicioniva1(null);
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
            }
            em.remove(condicioniva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Condicioniva> findCondicionivaEntities() {
        return findCondicionivaEntities(true, -1, -1);
    }

    public List<Condicioniva> findCondicionivaEntities(int maxResults, int firstResult) {
        return findCondicionivaEntities(false, maxResults, firstResult);
    }

    private List<Condicioniva> findCondicionivaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Condicioniva.class));
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

    public Condicioniva findCondicioniva(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Condicioniva.class, id);
        } finally {
            em.close();
        }
    }

    public int getCondicionivaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Condicioniva> rt = cq.from(Condicioniva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
