/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Domicilio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Barrio;
import entity.Responsable;
import java.util.HashSet;
import java.util.Set;
import entity.Empresametalurgica;
import entity.Cliente;
import entity.Proveedor;
import entity.Proveedormantenimientomaquina;
import entity.Empleado;

/**
 *
 * @author Nino
 */
public class DomicilioJpaController {

    public DomicilioJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Domicilio domicilio) throws PreexistingEntityException, Exception {
        if (domicilio.getResponsableSet() == null) {
            domicilio.setResponsableSet(new HashSet<Responsable>());
        }
        if (domicilio.getResponsableSet1() == null) {
            domicilio.setResponsableSet1(new HashSet<Responsable>());
        }
        if (domicilio.getEmpresametalurgicaSet() == null) {
            domicilio.setEmpresametalurgicaSet(new HashSet<Empresametalurgica>());
        }
        if (domicilio.getEmpresametalurgicaSet1() == null) {
            domicilio.setEmpresametalurgicaSet1(new HashSet<Empresametalurgica>());
        }
        if (domicilio.getClienteSet() == null) {
            domicilio.setClienteSet(new HashSet<Cliente>());
        }
        if (domicilio.getClienteSet1() == null) {
            domicilio.setClienteSet1(new HashSet<Cliente>());
        }
        if (domicilio.getProveedorSet() == null) {
            domicilio.setProveedorSet(new HashSet<Proveedor>());
        }
        if (domicilio.getProveedorSet1() == null) {
            domicilio.setProveedorSet1(new HashSet<Proveedor>());
        }
        if (domicilio.getProveedormantenimientomaquinaSet() == null) {
            domicilio.setProveedormantenimientomaquinaSet(new HashSet<Proveedormantenimientomaquina>());
        }
        if (domicilio.getProveedormantenimientomaquinaSet1() == null) {
            domicilio.setProveedormantenimientomaquinaSet1(new HashSet<Proveedormantenimientomaquina>());
        }
        if (domicilio.getEmpleadoSet() == null) {
            domicilio.setEmpleadoSet(new HashSet<Empleado>());
        }
        if (domicilio.getEmpleadoSet1() == null) {
            domicilio.setEmpleadoSet1(new HashSet<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio barrio = domicilio.getBarrio();
            if (barrio != null) {
                barrio = em.getReference(barrio.getClass(), barrio.getIdbarrio());
                domicilio.setBarrio(barrio);
            }
            Barrio barrio1 = domicilio.getBarrio1();
            if (barrio1 != null) {
                barrio1 = em.getReference(barrio1.getClass(), barrio1.getIdbarrio());
                domicilio.setBarrio1(barrio1);
            }
            Set<Responsable> attachedResponsableSet = new HashSet<Responsable>();
            for (Responsable responsableSetResponsableToAttach : domicilio.getResponsableSet()) {
                responsableSetResponsableToAttach = em.getReference(responsableSetResponsableToAttach.getClass(), responsableSetResponsableToAttach.getIdresponsable());
                attachedResponsableSet.add(responsableSetResponsableToAttach);
            }
            domicilio.setResponsableSet(attachedResponsableSet);
            Set<Responsable> attachedResponsableSet1 = new HashSet<Responsable>();
            for (Responsable responsableSet1ResponsableToAttach : domicilio.getResponsableSet1()) {
                responsableSet1ResponsableToAttach = em.getReference(responsableSet1ResponsableToAttach.getClass(), responsableSet1ResponsableToAttach.getIdresponsable());
                attachedResponsableSet1.add(responsableSet1ResponsableToAttach);
            }
            domicilio.setResponsableSet1(attachedResponsableSet1);
            Set<Empresametalurgica> attachedEmpresametalurgicaSet = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgicaToAttach : domicilio.getEmpresametalurgicaSet()) {
                empresametalurgicaSetEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet.add(empresametalurgicaSetEmpresametalurgicaToAttach);
            }
            domicilio.setEmpresametalurgicaSet(attachedEmpresametalurgicaSet);
            Set<Empresametalurgica> attachedEmpresametalurgicaSet1 = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSet1EmpresametalurgicaToAttach : domicilio.getEmpresametalurgicaSet1()) {
                empresametalurgicaSet1EmpresametalurgicaToAttach = em.getReference(empresametalurgicaSet1EmpresametalurgicaToAttach.getClass(), empresametalurgicaSet1EmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet1.add(empresametalurgicaSet1EmpresametalurgicaToAttach);
            }
            domicilio.setEmpresametalurgicaSet1(attachedEmpresametalurgicaSet1);
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : domicilio.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            domicilio.setClienteSet(attachedClienteSet);
            Set<Cliente> attachedClienteSet1 = new HashSet<Cliente>();
            for (Cliente clienteSet1ClienteToAttach : domicilio.getClienteSet1()) {
                clienteSet1ClienteToAttach = em.getReference(clienteSet1ClienteToAttach.getClass(), clienteSet1ClienteToAttach.getIdcliente());
                attachedClienteSet1.add(clienteSet1ClienteToAttach);
            }
            domicilio.setClienteSet1(attachedClienteSet1);
            Set<Proveedor> attachedProveedorSet = new HashSet<Proveedor>();
            for (Proveedor proveedorSetProveedorToAttach : domicilio.getProveedorSet()) {
                proveedorSetProveedorToAttach = em.getReference(proveedorSetProveedorToAttach.getClass(), proveedorSetProveedorToAttach.getIdproveedor());
                attachedProveedorSet.add(proveedorSetProveedorToAttach);
            }
            domicilio.setProveedorSet(attachedProveedorSet);
            Set<Proveedor> attachedProveedorSet1 = new HashSet<Proveedor>();
            for (Proveedor proveedorSet1ProveedorToAttach : domicilio.getProveedorSet1()) {
                proveedorSet1ProveedorToAttach = em.getReference(proveedorSet1ProveedorToAttach.getClass(), proveedorSet1ProveedorToAttach.getIdproveedor());
                attachedProveedorSet1.add(proveedorSet1ProveedorToAttach);
            }
            domicilio.setProveedorSet1(attachedProveedorSet1);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach : domicilio.getProveedormantenimientomaquinaSet()) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet.add(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach);
            }
            domicilio.setProveedormantenimientomaquinaSet(attachedProveedormantenimientomaquinaSet);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet1 = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach : domicilio.getProveedormantenimientomaquinaSet1()) {
                proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet1.add(proveedormantenimientomaquinaSet1ProveedormantenimientomaquinaToAttach);
            }
            domicilio.setProveedormantenimientomaquinaSet1(attachedProveedormantenimientomaquinaSet1);
            Set<Empleado> attachedEmpleadoSet = new HashSet<Empleado>();
            for (Empleado empleadoSetEmpleadoToAttach : domicilio.getEmpleadoSet()) {
                empleadoSetEmpleadoToAttach = em.getReference(empleadoSetEmpleadoToAttach.getClass(), empleadoSetEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet.add(empleadoSetEmpleadoToAttach);
            }
            domicilio.setEmpleadoSet(attachedEmpleadoSet);
            Set<Empleado> attachedEmpleadoSet1 = new HashSet<Empleado>();
            for (Empleado empleadoSet1EmpleadoToAttach : domicilio.getEmpleadoSet1()) {
                empleadoSet1EmpleadoToAttach = em.getReference(empleadoSet1EmpleadoToAttach.getClass(), empleadoSet1EmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1.add(empleadoSet1EmpleadoToAttach);
            }
            domicilio.setEmpleadoSet1(attachedEmpleadoSet1);
            em.persist(domicilio);
            if (barrio != null) {
                barrio.getDomicilioSet().add(domicilio);
                barrio = em.merge(barrio);
            }
            if (barrio1 != null) {
                barrio1.getDomicilioSet().add(domicilio);
                barrio1 = em.merge(barrio1);
            }
            for (Responsable responsableSetResponsable : domicilio.getResponsableSet()) {
                Domicilio oldDomicilioOfResponsableSetResponsable = responsableSetResponsable.getDomicilio();
                responsableSetResponsable.setDomicilio(domicilio);
                responsableSetResponsable = em.merge(responsableSetResponsable);
                if (oldDomicilioOfResponsableSetResponsable != null) {
                    oldDomicilioOfResponsableSetResponsable.getResponsableSet().remove(responsableSetResponsable);
                    oldDomicilioOfResponsableSetResponsable = em.merge(oldDomicilioOfResponsableSetResponsable);
                }
            }
            for (Responsable responsableSet1Responsable : domicilio.getResponsableSet1()) {
                Domicilio oldDomicilio1OfResponsableSet1Responsable = responsableSet1Responsable.getDomicilio1();
                responsableSet1Responsable.setDomicilio1(domicilio);
                responsableSet1Responsable = em.merge(responsableSet1Responsable);
                if (oldDomicilio1OfResponsableSet1Responsable != null) {
                    oldDomicilio1OfResponsableSet1Responsable.getResponsableSet1().remove(responsableSet1Responsable);
                    oldDomicilio1OfResponsableSet1Responsable = em.merge(oldDomicilio1OfResponsableSet1Responsable);
                }
            }
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : domicilio.getEmpresametalurgicaSet()) {
                Domicilio oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica = empresametalurgicaSetEmpresametalurgica.getDomicilio();
                empresametalurgicaSetEmpresametalurgica.setDomicilio(domicilio);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
                if (oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica != null) {
                    oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica.getEmpresametalurgicaSet().remove(empresametalurgicaSetEmpresametalurgica);
                    oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica = em.merge(oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSet1Empresametalurgica : domicilio.getEmpresametalurgicaSet1()) {
                Domicilio oldDomicilio1OfEmpresametalurgicaSet1Empresametalurgica = empresametalurgicaSet1Empresametalurgica.getDomicilio1();
                empresametalurgicaSet1Empresametalurgica.setDomicilio1(domicilio);
                empresametalurgicaSet1Empresametalurgica = em.merge(empresametalurgicaSet1Empresametalurgica);
                if (oldDomicilio1OfEmpresametalurgicaSet1Empresametalurgica != null) {
                    oldDomicilio1OfEmpresametalurgicaSet1Empresametalurgica.getEmpresametalurgicaSet1().remove(empresametalurgicaSet1Empresametalurgica);
                    oldDomicilio1OfEmpresametalurgicaSet1Empresametalurgica = em.merge(oldDomicilio1OfEmpresametalurgicaSet1Empresametalurgica);
                }
            }
            for (Cliente clienteSetCliente : domicilio.getClienteSet()) {
                Domicilio oldDomicilioOfClienteSetCliente = clienteSetCliente.getDomicilio();
                clienteSetCliente.setDomicilio(domicilio);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldDomicilioOfClienteSetCliente != null) {
                    oldDomicilioOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldDomicilioOfClienteSetCliente = em.merge(oldDomicilioOfClienteSetCliente);
                }
            }
            for (Cliente clienteSet1Cliente : domicilio.getClienteSet1()) {
                Domicilio oldDomicilio1OfClienteSet1Cliente = clienteSet1Cliente.getDomicilio1();
                clienteSet1Cliente.setDomicilio1(domicilio);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
                if (oldDomicilio1OfClienteSet1Cliente != null) {
                    oldDomicilio1OfClienteSet1Cliente.getClienteSet1().remove(clienteSet1Cliente);
                    oldDomicilio1OfClienteSet1Cliente = em.merge(oldDomicilio1OfClienteSet1Cliente);
                }
            }
            for (Proveedor proveedorSetProveedor : domicilio.getProveedorSet()) {
                Domicilio oldDomicilioOfProveedorSetProveedor = proveedorSetProveedor.getDomicilio();
                proveedorSetProveedor.setDomicilio(domicilio);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
                if (oldDomicilioOfProveedorSetProveedor != null) {
                    oldDomicilioOfProveedorSetProveedor.getProveedorSet().remove(proveedorSetProveedor);
                    oldDomicilioOfProveedorSetProveedor = em.merge(oldDomicilioOfProveedorSetProveedor);
                }
            }
            for (Proveedor proveedorSet1Proveedor : domicilio.getProveedorSet1()) {
                Domicilio oldDomicilio1OfProveedorSet1Proveedor = proveedorSet1Proveedor.getDomicilio1();
                proveedorSet1Proveedor.setDomicilio1(domicilio);
                proveedorSet1Proveedor = em.merge(proveedorSet1Proveedor);
                if (oldDomicilio1OfProveedorSet1Proveedor != null) {
                    oldDomicilio1OfProveedorSet1Proveedor.getProveedorSet1().remove(proveedorSet1Proveedor);
                    oldDomicilio1OfProveedorSet1Proveedor = em.merge(oldDomicilio1OfProveedorSet1Proveedor);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : domicilio.getProveedormantenimientomaquinaSet()) {
                Domicilio oldDomicilioOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = proveedormantenimientomaquinaSetProveedormantenimientomaquina.getDomicilio();
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setDomicilio(domicilio);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                if (oldDomicilioOfProveedormantenimientomaquinaSetProveedormantenimientomaquina != null) {
                    oldDomicilioOfProveedormantenimientomaquinaSetProveedormantenimientomaquina.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                    oldDomicilioOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(oldDomicilioOfProveedormantenimientomaquinaSetProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1Proveedormantenimientomaquina : domicilio.getProveedormantenimientomaquinaSet1()) {
                Domicilio oldDomicilio1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina = proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.getDomicilio1();
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.setDomicilio1(domicilio);
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                if (oldDomicilio1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina != null) {
                    oldDomicilio1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina.getProveedormantenimientomaquinaSet1().remove(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                    oldDomicilio1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(oldDomicilio1OfProveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
                }
            }
            for (Empleado empleadoSetEmpleado : domicilio.getEmpleadoSet()) {
                Domicilio oldDomicilioOfEmpleadoSetEmpleado = empleadoSetEmpleado.getDomicilio();
                empleadoSetEmpleado.setDomicilio(domicilio);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
                if (oldDomicilioOfEmpleadoSetEmpleado != null) {
                    oldDomicilioOfEmpleadoSetEmpleado.getEmpleadoSet().remove(empleadoSetEmpleado);
                    oldDomicilioOfEmpleadoSetEmpleado = em.merge(oldDomicilioOfEmpleadoSetEmpleado);
                }
            }
            for (Empleado empleadoSet1Empleado : domicilio.getEmpleadoSet1()) {
                Domicilio oldDomicilio1OfEmpleadoSet1Empleado = empleadoSet1Empleado.getDomicilio1();
                empleadoSet1Empleado.setDomicilio1(domicilio);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
                if (oldDomicilio1OfEmpleadoSet1Empleado != null) {
                    oldDomicilio1OfEmpleadoSet1Empleado.getEmpleadoSet1().remove(empleadoSet1Empleado);
                    oldDomicilio1OfEmpleadoSet1Empleado = em.merge(oldDomicilio1OfEmpleadoSet1Empleado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDomicilio(domicilio.getIddomicilio()) != null) {
                throw new PreexistingEntityException("Domicilio " + domicilio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Domicilio domicilio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domicilio persistentDomicilio = em.find(Domicilio.class, domicilio.getIddomicilio());
            Barrio barrioOld = persistentDomicilio.getBarrio();
            Barrio barrioNew = domicilio.getBarrio();
            Barrio barrio1Old = persistentDomicilio.getBarrio1();
            Barrio barrio1New = domicilio.getBarrio1();
            Set<Responsable> responsableSetOld = persistentDomicilio.getResponsableSet();
            Set<Responsable> responsableSetNew = domicilio.getResponsableSet();
            Set<Responsable> responsableSet1Old = persistentDomicilio.getResponsableSet1();
            Set<Responsable> responsableSet1New = domicilio.getResponsableSet1();
            Set<Empresametalurgica> empresametalurgicaSetOld = persistentDomicilio.getEmpresametalurgicaSet();
            Set<Empresametalurgica> empresametalurgicaSetNew = domicilio.getEmpresametalurgicaSet();
            Set<Empresametalurgica> empresametalurgicaSet1Old = persistentDomicilio.getEmpresametalurgicaSet1();
            Set<Empresametalurgica> empresametalurgicaSet1New = domicilio.getEmpresametalurgicaSet1();
            Set<Cliente> clienteSetOld = persistentDomicilio.getClienteSet();
            Set<Cliente> clienteSetNew = domicilio.getClienteSet();
            Set<Cliente> clienteSet1Old = persistentDomicilio.getClienteSet1();
            Set<Cliente> clienteSet1New = domicilio.getClienteSet1();
            Set<Proveedor> proveedorSetOld = persistentDomicilio.getProveedorSet();
            Set<Proveedor> proveedorSetNew = domicilio.getProveedorSet();
            Set<Proveedor> proveedorSet1Old = persistentDomicilio.getProveedorSet1();
            Set<Proveedor> proveedorSet1New = domicilio.getProveedorSet1();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetOld = persistentDomicilio.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetNew = domicilio.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1Old = persistentDomicilio.getProveedormantenimientomaquinaSet1();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1New = domicilio.getProveedormantenimientomaquinaSet1();
            Set<Empleado> empleadoSetOld = persistentDomicilio.getEmpleadoSet();
            Set<Empleado> empleadoSetNew = domicilio.getEmpleadoSet();
            Set<Empleado> empleadoSet1Old = persistentDomicilio.getEmpleadoSet1();
            Set<Empleado> empleadoSet1New = domicilio.getEmpleadoSet1();
            if (barrioNew != null) {
                barrioNew = em.getReference(barrioNew.getClass(), barrioNew.getIdbarrio());
                domicilio.setBarrio(barrioNew);
            }
            if (barrio1New != null) {
                barrio1New = em.getReference(barrio1New.getClass(), barrio1New.getIdbarrio());
                domicilio.setBarrio1(barrio1New);
            }
            Set<Responsable> attachedResponsableSetNew = new HashSet<Responsable>();
            for (Responsable responsableSetNewResponsableToAttach : responsableSetNew) {
                responsableSetNewResponsableToAttach = em.getReference(responsableSetNewResponsableToAttach.getClass(), responsableSetNewResponsableToAttach.getIdresponsable());
                attachedResponsableSetNew.add(responsableSetNewResponsableToAttach);
            }
            responsableSetNew = attachedResponsableSetNew;
            domicilio.setResponsableSet(responsableSetNew);
            Set<Responsable> attachedResponsableSet1New = new HashSet<Responsable>();
            for (Responsable responsableSet1NewResponsableToAttach : responsableSet1New) {
                responsableSet1NewResponsableToAttach = em.getReference(responsableSet1NewResponsableToAttach.getClass(), responsableSet1NewResponsableToAttach.getIdresponsable());
                attachedResponsableSet1New.add(responsableSet1NewResponsableToAttach);
            }
            responsableSet1New = attachedResponsableSet1New;
            domicilio.setResponsableSet1(responsableSet1New);
            Set<Empresametalurgica> attachedEmpresametalurgicaSetNew = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgicaToAttach : empresametalurgicaSetNew) {
                empresametalurgicaSetNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSetNew.add(empresametalurgicaSetNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSetNew = attachedEmpresametalurgicaSetNew;
            domicilio.setEmpresametalurgicaSet(empresametalurgicaSetNew);
            Set<Empresametalurgica> attachedEmpresametalurgicaSet1New = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSet1NewEmpresametalurgicaToAttach : empresametalurgicaSet1New) {
                empresametalurgicaSet1NewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSet1NewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSet1NewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet1New.add(empresametalurgicaSet1NewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSet1New = attachedEmpresametalurgicaSet1New;
            domicilio.setEmpresametalurgicaSet1(empresametalurgicaSet1New);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            domicilio.setClienteSet(clienteSetNew);
            Set<Cliente> attachedClienteSet1New = new HashSet<Cliente>();
            for (Cliente clienteSet1NewClienteToAttach : clienteSet1New) {
                clienteSet1NewClienteToAttach = em.getReference(clienteSet1NewClienteToAttach.getClass(), clienteSet1NewClienteToAttach.getIdcliente());
                attachedClienteSet1New.add(clienteSet1NewClienteToAttach);
            }
            clienteSet1New = attachedClienteSet1New;
            domicilio.setClienteSet1(clienteSet1New);
            Set<Proveedor> attachedProveedorSetNew = new HashSet<Proveedor>();
            for (Proveedor proveedorSetNewProveedorToAttach : proveedorSetNew) {
                proveedorSetNewProveedorToAttach = em.getReference(proveedorSetNewProveedorToAttach.getClass(), proveedorSetNewProveedorToAttach.getIdproveedor());
                attachedProveedorSetNew.add(proveedorSetNewProveedorToAttach);
            }
            proveedorSetNew = attachedProveedorSetNew;
            domicilio.setProveedorSet(proveedorSetNew);
            Set<Proveedor> attachedProveedorSet1New = new HashSet<Proveedor>();
            for (Proveedor proveedorSet1NewProveedorToAttach : proveedorSet1New) {
                proveedorSet1NewProveedorToAttach = em.getReference(proveedorSet1NewProveedorToAttach.getClass(), proveedorSet1NewProveedorToAttach.getIdproveedor());
                attachedProveedorSet1New.add(proveedorSet1NewProveedorToAttach);
            }
            proveedorSet1New = attachedProveedorSet1New;
            domicilio.setProveedorSet1(proveedorSet1New);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSetNew = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSetNew) {
                proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSetNew.add(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSetNew = attachedProveedormantenimientomaquinaSetNew;
            domicilio.setProveedormantenimientomaquinaSet(proveedormantenimientomaquinaSetNew);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet1New = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSet1New) {
                proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet1New.add(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSet1New = attachedProveedormantenimientomaquinaSet1New;
            domicilio.setProveedormantenimientomaquinaSet1(proveedormantenimientomaquinaSet1New);
            Set<Empleado> attachedEmpleadoSetNew = new HashSet<Empleado>();
            for (Empleado empleadoSetNewEmpleadoToAttach : empleadoSetNew) {
                empleadoSetNewEmpleadoToAttach = em.getReference(empleadoSetNewEmpleadoToAttach.getClass(), empleadoSetNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSetNew.add(empleadoSetNewEmpleadoToAttach);
            }
            empleadoSetNew = attachedEmpleadoSetNew;
            domicilio.setEmpleadoSet(empleadoSetNew);
            Set<Empleado> attachedEmpleadoSet1New = new HashSet<Empleado>();
            for (Empleado empleadoSet1NewEmpleadoToAttach : empleadoSet1New) {
                empleadoSet1NewEmpleadoToAttach = em.getReference(empleadoSet1NewEmpleadoToAttach.getClass(), empleadoSet1NewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1New.add(empleadoSet1NewEmpleadoToAttach);
            }
            empleadoSet1New = attachedEmpleadoSet1New;
            domicilio.setEmpleadoSet1(empleadoSet1New);
            domicilio = em.merge(domicilio);
            if (barrioOld != null && !barrioOld.equals(barrioNew)) {
                barrioOld.getDomicilioSet().remove(domicilio);
                barrioOld = em.merge(barrioOld);
            }
            if (barrioNew != null && !barrioNew.equals(barrioOld)) {
                barrioNew.getDomicilioSet().add(domicilio);
                barrioNew = em.merge(barrioNew);
            }
            if (barrio1Old != null && !barrio1Old.equals(barrio1New)) {
                barrio1Old.getDomicilioSet().remove(domicilio);
                barrio1Old = em.merge(barrio1Old);
            }
            if (barrio1New != null && !barrio1New.equals(barrio1Old)) {
                barrio1New.getDomicilioSet().add(domicilio);
                barrio1New = em.merge(barrio1New);
            }
            for (Responsable responsableSetOldResponsable : responsableSetOld) {
                if (!responsableSetNew.contains(responsableSetOldResponsable)) {
                    responsableSetOldResponsable.setDomicilio(null);
                    responsableSetOldResponsable = em.merge(responsableSetOldResponsable);
                }
            }
            for (Responsable responsableSetNewResponsable : responsableSetNew) {
                if (!responsableSetOld.contains(responsableSetNewResponsable)) {
                    Domicilio oldDomicilioOfResponsableSetNewResponsable = responsableSetNewResponsable.getDomicilio();
                    responsableSetNewResponsable.setDomicilio(domicilio);
                    responsableSetNewResponsable = em.merge(responsableSetNewResponsable);
                    if (oldDomicilioOfResponsableSetNewResponsable != null && !oldDomicilioOfResponsableSetNewResponsable.equals(domicilio)) {
                        oldDomicilioOfResponsableSetNewResponsable.getResponsableSet().remove(responsableSetNewResponsable);
                        oldDomicilioOfResponsableSetNewResponsable = em.merge(oldDomicilioOfResponsableSetNewResponsable);
                    }
                }
            }
            for (Responsable responsableSet1OldResponsable : responsableSet1Old) {
                if (!responsableSet1New.contains(responsableSet1OldResponsable)) {
                    responsableSet1OldResponsable.setDomicilio1(null);
                    responsableSet1OldResponsable = em.merge(responsableSet1OldResponsable);
                }
            }
            for (Responsable responsableSet1NewResponsable : responsableSet1New) {
                if (!responsableSet1Old.contains(responsableSet1NewResponsable)) {
                    Domicilio oldDomicilio1OfResponsableSet1NewResponsable = responsableSet1NewResponsable.getDomicilio1();
                    responsableSet1NewResponsable.setDomicilio1(domicilio);
                    responsableSet1NewResponsable = em.merge(responsableSet1NewResponsable);
                    if (oldDomicilio1OfResponsableSet1NewResponsable != null && !oldDomicilio1OfResponsableSet1NewResponsable.equals(domicilio)) {
                        oldDomicilio1OfResponsableSet1NewResponsable.getResponsableSet1().remove(responsableSet1NewResponsable);
                        oldDomicilio1OfResponsableSet1NewResponsable = em.merge(oldDomicilio1OfResponsableSet1NewResponsable);
                    }
                }
            }
            for (Empresametalurgica empresametalurgicaSetOldEmpresametalurgica : empresametalurgicaSetOld) {
                if (!empresametalurgicaSetNew.contains(empresametalurgicaSetOldEmpresametalurgica)) {
                    empresametalurgicaSetOldEmpresametalurgica.setDomicilio(null);
                    empresametalurgicaSetOldEmpresametalurgica = em.merge(empresametalurgicaSetOldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgica : empresametalurgicaSetNew) {
                if (!empresametalurgicaSetOld.contains(empresametalurgicaSetNewEmpresametalurgica)) {
                    Domicilio oldDomicilioOfEmpresametalurgicaSetNewEmpresametalurgica = empresametalurgicaSetNewEmpresametalurgica.getDomicilio();
                    empresametalurgicaSetNewEmpresametalurgica.setDomicilio(domicilio);
                    empresametalurgicaSetNewEmpresametalurgica = em.merge(empresametalurgicaSetNewEmpresametalurgica);
                    if (oldDomicilioOfEmpresametalurgicaSetNewEmpresametalurgica != null && !oldDomicilioOfEmpresametalurgicaSetNewEmpresametalurgica.equals(domicilio)) {
                        oldDomicilioOfEmpresametalurgicaSetNewEmpresametalurgica.getEmpresametalurgicaSet().remove(empresametalurgicaSetNewEmpresametalurgica);
                        oldDomicilioOfEmpresametalurgicaSetNewEmpresametalurgica = em.merge(oldDomicilioOfEmpresametalurgicaSetNewEmpresametalurgica);
                    }
                }
            }
            for (Empresametalurgica empresametalurgicaSet1OldEmpresametalurgica : empresametalurgicaSet1Old) {
                if (!empresametalurgicaSet1New.contains(empresametalurgicaSet1OldEmpresametalurgica)) {
                    empresametalurgicaSet1OldEmpresametalurgica.setDomicilio1(null);
                    empresametalurgicaSet1OldEmpresametalurgica = em.merge(empresametalurgicaSet1OldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaSet1NewEmpresametalurgica : empresametalurgicaSet1New) {
                if (!empresametalurgicaSet1Old.contains(empresametalurgicaSet1NewEmpresametalurgica)) {
                    Domicilio oldDomicilio1OfEmpresametalurgicaSet1NewEmpresametalurgica = empresametalurgicaSet1NewEmpresametalurgica.getDomicilio1();
                    empresametalurgicaSet1NewEmpresametalurgica.setDomicilio1(domicilio);
                    empresametalurgicaSet1NewEmpresametalurgica = em.merge(empresametalurgicaSet1NewEmpresametalurgica);
                    if (oldDomicilio1OfEmpresametalurgicaSet1NewEmpresametalurgica != null && !oldDomicilio1OfEmpresametalurgicaSet1NewEmpresametalurgica.equals(domicilio)) {
                        oldDomicilio1OfEmpresametalurgicaSet1NewEmpresametalurgica.getEmpresametalurgicaSet1().remove(empresametalurgicaSet1NewEmpresametalurgica);
                        oldDomicilio1OfEmpresametalurgicaSet1NewEmpresametalurgica = em.merge(oldDomicilio1OfEmpresametalurgicaSet1NewEmpresametalurgica);
                    }
                }
            }
            for (Cliente clienteSetOldCliente : clienteSetOld) {
                if (!clienteSetNew.contains(clienteSetOldCliente)) {
                    clienteSetOldCliente.setDomicilio(null);
                    clienteSetOldCliente = em.merge(clienteSetOldCliente);
                }
            }
            for (Cliente clienteSetNewCliente : clienteSetNew) {
                if (!clienteSetOld.contains(clienteSetNewCliente)) {
                    Domicilio oldDomicilioOfClienteSetNewCliente = clienteSetNewCliente.getDomicilio();
                    clienteSetNewCliente.setDomicilio(domicilio);
                    clienteSetNewCliente = em.merge(clienteSetNewCliente);
                    if (oldDomicilioOfClienteSetNewCliente != null && !oldDomicilioOfClienteSetNewCliente.equals(domicilio)) {
                        oldDomicilioOfClienteSetNewCliente.getClienteSet().remove(clienteSetNewCliente);
                        oldDomicilioOfClienteSetNewCliente = em.merge(oldDomicilioOfClienteSetNewCliente);
                    }
                }
            }
            for (Cliente clienteSet1OldCliente : clienteSet1Old) {
                if (!clienteSet1New.contains(clienteSet1OldCliente)) {
                    clienteSet1OldCliente.setDomicilio1(null);
                    clienteSet1OldCliente = em.merge(clienteSet1OldCliente);
                }
            }
            for (Cliente clienteSet1NewCliente : clienteSet1New) {
                if (!clienteSet1Old.contains(clienteSet1NewCliente)) {
                    Domicilio oldDomicilio1OfClienteSet1NewCliente = clienteSet1NewCliente.getDomicilio1();
                    clienteSet1NewCliente.setDomicilio1(domicilio);
                    clienteSet1NewCliente = em.merge(clienteSet1NewCliente);
                    if (oldDomicilio1OfClienteSet1NewCliente != null && !oldDomicilio1OfClienteSet1NewCliente.equals(domicilio)) {
                        oldDomicilio1OfClienteSet1NewCliente.getClienteSet1().remove(clienteSet1NewCliente);
                        oldDomicilio1OfClienteSet1NewCliente = em.merge(oldDomicilio1OfClienteSet1NewCliente);
                    }
                }
            }
            for (Proveedor proveedorSetOldProveedor : proveedorSetOld) {
                if (!proveedorSetNew.contains(proveedorSetOldProveedor)) {
                    proveedorSetOldProveedor.setDomicilio(null);
                    proveedorSetOldProveedor = em.merge(proveedorSetOldProveedor);
                }
            }
            for (Proveedor proveedorSetNewProveedor : proveedorSetNew) {
                if (!proveedorSetOld.contains(proveedorSetNewProveedor)) {
                    Domicilio oldDomicilioOfProveedorSetNewProveedor = proveedorSetNewProveedor.getDomicilio();
                    proveedorSetNewProveedor.setDomicilio(domicilio);
                    proveedorSetNewProveedor = em.merge(proveedorSetNewProveedor);
                    if (oldDomicilioOfProveedorSetNewProveedor != null && !oldDomicilioOfProveedorSetNewProveedor.equals(domicilio)) {
                        oldDomicilioOfProveedorSetNewProveedor.getProveedorSet().remove(proveedorSetNewProveedor);
                        oldDomicilioOfProveedorSetNewProveedor = em.merge(oldDomicilioOfProveedorSetNewProveedor);
                    }
                }
            }
            for (Proveedor proveedorSet1OldProveedor : proveedorSet1Old) {
                if (!proveedorSet1New.contains(proveedorSet1OldProveedor)) {
                    proveedorSet1OldProveedor.setDomicilio1(null);
                    proveedorSet1OldProveedor = em.merge(proveedorSet1OldProveedor);
                }
            }
            for (Proveedor proveedorSet1NewProveedor : proveedorSet1New) {
                if (!proveedorSet1Old.contains(proveedorSet1NewProveedor)) {
                    Domicilio oldDomicilio1OfProveedorSet1NewProveedor = proveedorSet1NewProveedor.getDomicilio1();
                    proveedorSet1NewProveedor.setDomicilio1(domicilio);
                    proveedorSet1NewProveedor = em.merge(proveedorSet1NewProveedor);
                    if (oldDomicilio1OfProveedorSet1NewProveedor != null && !oldDomicilio1OfProveedorSet1NewProveedor.equals(domicilio)) {
                        oldDomicilio1OfProveedorSet1NewProveedor.getProveedorSet1().remove(proveedorSet1NewProveedor);
                        oldDomicilio1OfProveedorSet1NewProveedor = em.merge(oldDomicilio1OfProveedorSet1NewProveedor);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetOldProveedormantenimientomaquina : proveedormantenimientomaquinaSetOld) {
                if (!proveedormantenimientomaquinaSetNew.contains(proveedormantenimientomaquinaSetOldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaSetOldProveedormantenimientomaquina.setDomicilio(null);
                    proveedormantenimientomaquinaSetOldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetOldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquina : proveedormantenimientomaquinaSetNew) {
                if (!proveedormantenimientomaquinaSetOld.contains(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina)) {
                    Domicilio oldDomicilioOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina = proveedormantenimientomaquinaSetNewProveedormantenimientomaquina.getDomicilio();
                    proveedormantenimientomaquinaSetNewProveedormantenimientomaquina.setDomicilio(domicilio);
                    proveedormantenimientomaquinaSetNewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                    if (oldDomicilioOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina != null && !oldDomicilioOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina.equals(domicilio)) {
                        oldDomicilioOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                        oldDomicilioOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina = em.merge(oldDomicilioOfProveedormantenimientomaquinaSetNewProveedormantenimientomaquina);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina : proveedormantenimientomaquinaSet1Old) {
                if (!proveedormantenimientomaquinaSet1New.contains(proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina.setDomicilio1(null);
                    proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1OldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina : proveedormantenimientomaquinaSet1New) {
                if (!proveedormantenimientomaquinaSet1Old.contains(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina)) {
                    Domicilio oldDomicilio1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.getDomicilio1();
                    proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.setDomicilio1(domicilio);
                    proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                    if (oldDomicilio1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina != null && !oldDomicilio1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.equals(domicilio)) {
                        oldDomicilio1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina.getProveedormantenimientomaquinaSet1().remove(proveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                        oldDomicilio1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina = em.merge(oldDomicilio1OfProveedormantenimientomaquinaSet1NewProveedormantenimientomaquina);
                    }
                }
            }
            for (Empleado empleadoSetOldEmpleado : empleadoSetOld) {
                if (!empleadoSetNew.contains(empleadoSetOldEmpleado)) {
                    empleadoSetOldEmpleado.setDomicilio(null);
                    empleadoSetOldEmpleado = em.merge(empleadoSetOldEmpleado);
                }
            }
            for (Empleado empleadoSetNewEmpleado : empleadoSetNew) {
                if (!empleadoSetOld.contains(empleadoSetNewEmpleado)) {
                    Domicilio oldDomicilioOfEmpleadoSetNewEmpleado = empleadoSetNewEmpleado.getDomicilio();
                    empleadoSetNewEmpleado.setDomicilio(domicilio);
                    empleadoSetNewEmpleado = em.merge(empleadoSetNewEmpleado);
                    if (oldDomicilioOfEmpleadoSetNewEmpleado != null && !oldDomicilioOfEmpleadoSetNewEmpleado.equals(domicilio)) {
                        oldDomicilioOfEmpleadoSetNewEmpleado.getEmpleadoSet().remove(empleadoSetNewEmpleado);
                        oldDomicilioOfEmpleadoSetNewEmpleado = em.merge(oldDomicilioOfEmpleadoSetNewEmpleado);
                    }
                }
            }
            for (Empleado empleadoSet1OldEmpleado : empleadoSet1Old) {
                if (!empleadoSet1New.contains(empleadoSet1OldEmpleado)) {
                    empleadoSet1OldEmpleado.setDomicilio1(null);
                    empleadoSet1OldEmpleado = em.merge(empleadoSet1OldEmpleado);
                }
            }
            for (Empleado empleadoSet1NewEmpleado : empleadoSet1New) {
                if (!empleadoSet1Old.contains(empleadoSet1NewEmpleado)) {
                    Domicilio oldDomicilio1OfEmpleadoSet1NewEmpleado = empleadoSet1NewEmpleado.getDomicilio1();
                    empleadoSet1NewEmpleado.setDomicilio1(domicilio);
                    empleadoSet1NewEmpleado = em.merge(empleadoSet1NewEmpleado);
                    if (oldDomicilio1OfEmpleadoSet1NewEmpleado != null && !oldDomicilio1OfEmpleadoSet1NewEmpleado.equals(domicilio)) {
                        oldDomicilio1OfEmpleadoSet1NewEmpleado.getEmpleadoSet1().remove(empleadoSet1NewEmpleado);
                        oldDomicilio1OfEmpleadoSet1NewEmpleado = em.merge(oldDomicilio1OfEmpleadoSet1NewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = domicilio.getIddomicilio();
                if (findDomicilio(id) == null) {
                    throw new NonexistentEntityException("The domicilio with id " + id + " no longer exists.");
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
            Domicilio domicilio;
            try {
                domicilio = em.getReference(Domicilio.class, id);
                domicilio.getIddomicilio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The domicilio with id " + id + " no longer exists.", enfe);
            }
            Barrio barrio = domicilio.getBarrio();
            if (barrio != null) {
                barrio.getDomicilioSet().remove(domicilio);
                barrio = em.merge(barrio);
            }
            Barrio barrio1 = domicilio.getBarrio1();
            if (barrio1 != null) {
                barrio1.getDomicilioSet().remove(domicilio);
                barrio1 = em.merge(barrio1);
            }
            Set<Responsable> responsableSet = domicilio.getResponsableSet();
            for (Responsable responsableSetResponsable : responsableSet) {
                responsableSetResponsable.setDomicilio(null);
                responsableSetResponsable = em.merge(responsableSetResponsable);
            }
            Set<Responsable> responsableSet1 = domicilio.getResponsableSet1();
            for (Responsable responsableSet1Responsable : responsableSet1) {
                responsableSet1Responsable.setDomicilio1(null);
                responsableSet1Responsable = em.merge(responsableSet1Responsable);
            }
            Set<Empresametalurgica> empresametalurgicaSet = domicilio.getEmpresametalurgicaSet();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : empresametalurgicaSet) {
                empresametalurgicaSetEmpresametalurgica.setDomicilio(null);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
            }
            Set<Empresametalurgica> empresametalurgicaSet1 = domicilio.getEmpresametalurgicaSet1();
            for (Empresametalurgica empresametalurgicaSet1Empresametalurgica : empresametalurgicaSet1) {
                empresametalurgicaSet1Empresametalurgica.setDomicilio1(null);
                empresametalurgicaSet1Empresametalurgica = em.merge(empresametalurgicaSet1Empresametalurgica);
            }
            Set<Cliente> clienteSet = domicilio.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setDomicilio(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Cliente> clienteSet1 = domicilio.getClienteSet1();
            for (Cliente clienteSet1Cliente : clienteSet1) {
                clienteSet1Cliente.setDomicilio1(null);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
            }
            Set<Proveedor> proveedorSet = domicilio.getProveedorSet();
            for (Proveedor proveedorSetProveedor : proveedorSet) {
                proveedorSetProveedor.setDomicilio(null);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
            }
            Set<Proveedor> proveedorSet1 = domicilio.getProveedorSet1();
            for (Proveedor proveedorSet1Proveedor : proveedorSet1) {
                proveedorSet1Proveedor.setDomicilio1(null);
                proveedorSet1Proveedor = em.merge(proveedorSet1Proveedor);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet = domicilio.getProveedormantenimientomaquinaSet();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : proveedormantenimientomaquinaSet) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setDomicilio(null);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet1 = domicilio.getProveedormantenimientomaquinaSet1();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSet1Proveedormantenimientomaquina : proveedormantenimientomaquinaSet1) {
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina.setDomicilio1(null);
                proveedormantenimientomaquinaSet1Proveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSet1Proveedormantenimientomaquina);
            }
            Set<Empleado> empleadoSet = domicilio.getEmpleadoSet();
            for (Empleado empleadoSetEmpleado : empleadoSet) {
                empleadoSetEmpleado.setDomicilio(null);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
            }
            Set<Empleado> empleadoSet1 = domicilio.getEmpleadoSet1();
            for (Empleado empleadoSet1Empleado : empleadoSet1) {
                empleadoSet1Empleado.setDomicilio1(null);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
            }
            em.remove(domicilio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Domicilio> findDomicilioEntities() {
        return findDomicilioEntities(true, -1, -1);
    }

    public List<Domicilio> findDomicilioEntities(int maxResults, int firstResult) {
        return findDomicilioEntities(false, maxResults, firstResult);
    }

    private List<Domicilio> findDomicilioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Domicilio.class));
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

    public Domicilio findDomicilio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Domicilio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDomicilioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Domicilio> rt = cq.from(Domicilio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
