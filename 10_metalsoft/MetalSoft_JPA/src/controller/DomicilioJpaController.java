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
        if (domicilio.getEmpresametalurgicaSet() == null) {
            domicilio.setEmpresametalurgicaSet(new HashSet<Empresametalurgica>());
        }
        if (domicilio.getClienteSet() == null) {
            domicilio.setClienteSet(new HashSet<Cliente>());
        }
        if (domicilio.getProveedorSet() == null) {
            domicilio.setProveedorSet(new HashSet<Proveedor>());
        }
        if (domicilio.getProveedormantenimientomaquinaSet() == null) {
            domicilio.setProveedormantenimientomaquinaSet(new HashSet<Proveedormantenimientomaquina>());
        }
        if (domicilio.getEmpleadoSet() == null) {
            domicilio.setEmpleadoSet(new HashSet<Empleado>());
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
            Set<Responsable> attachedResponsableSet = new HashSet<Responsable>();
            for (Responsable responsableSetResponsableToAttach : domicilio.getResponsableSet()) {
                responsableSetResponsableToAttach = em.getReference(responsableSetResponsableToAttach.getClass(), responsableSetResponsableToAttach.getIdresponsable());
                attachedResponsableSet.add(responsableSetResponsableToAttach);
            }
            domicilio.setResponsableSet(attachedResponsableSet);
            Set<Empresametalurgica> attachedEmpresametalurgicaSet = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgicaToAttach : domicilio.getEmpresametalurgicaSet()) {
                empresametalurgicaSetEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet.add(empresametalurgicaSetEmpresametalurgicaToAttach);
            }
            domicilio.setEmpresametalurgicaSet(attachedEmpresametalurgicaSet);
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : domicilio.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            domicilio.setClienteSet(attachedClienteSet);
            Set<Proveedor> attachedProveedorSet = new HashSet<Proveedor>();
            for (Proveedor proveedorSetProveedorToAttach : domicilio.getProveedorSet()) {
                proveedorSetProveedorToAttach = em.getReference(proveedorSetProveedorToAttach.getClass(), proveedorSetProveedorToAttach.getIdproveedor());
                attachedProveedorSet.add(proveedorSetProveedorToAttach);
            }
            domicilio.setProveedorSet(attachedProveedorSet);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach : domicilio.getProveedormantenimientomaquinaSet()) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet.add(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach);
            }
            domicilio.setProveedormantenimientomaquinaSet(attachedProveedormantenimientomaquinaSet);
            Set<Empleado> attachedEmpleadoSet = new HashSet<Empleado>();
            for (Empleado empleadoSetEmpleadoToAttach : domicilio.getEmpleadoSet()) {
                empleadoSetEmpleadoToAttach = em.getReference(empleadoSetEmpleadoToAttach.getClass(), empleadoSetEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet.add(empleadoSetEmpleadoToAttach);
            }
            domicilio.setEmpleadoSet(attachedEmpleadoSet);
            em.persist(domicilio);
            if (barrio != null) {
                barrio.getDomicilioSet().add(domicilio);
                barrio = em.merge(barrio);
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
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : domicilio.getEmpresametalurgicaSet()) {
                Domicilio oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica = empresametalurgicaSetEmpresametalurgica.getDomicilio();
                empresametalurgicaSetEmpresametalurgica.setDomicilio(domicilio);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
                if (oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica != null) {
                    oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica.getEmpresametalurgicaSet().remove(empresametalurgicaSetEmpresametalurgica);
                    oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica = em.merge(oldDomicilioOfEmpresametalurgicaSetEmpresametalurgica);
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
            for (Proveedor proveedorSetProveedor : domicilio.getProveedorSet()) {
                Domicilio oldDomicilioOfProveedorSetProveedor = proveedorSetProveedor.getDomicilio();
                proveedorSetProveedor.setDomicilio(domicilio);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
                if (oldDomicilioOfProveedorSetProveedor != null) {
                    oldDomicilioOfProveedorSetProveedor.getProveedorSet().remove(proveedorSetProveedor);
                    oldDomicilioOfProveedorSetProveedor = em.merge(oldDomicilioOfProveedorSetProveedor);
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
            for (Empleado empleadoSetEmpleado : domicilio.getEmpleadoSet()) {
                Domicilio oldDomicilioOfEmpleadoSetEmpleado = empleadoSetEmpleado.getDomicilio();
                empleadoSetEmpleado.setDomicilio(domicilio);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
                if (oldDomicilioOfEmpleadoSetEmpleado != null) {
                    oldDomicilioOfEmpleadoSetEmpleado.getEmpleadoSet().remove(empleadoSetEmpleado);
                    oldDomicilioOfEmpleadoSetEmpleado = em.merge(oldDomicilioOfEmpleadoSetEmpleado);
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
            Set<Responsable> responsableSetOld = persistentDomicilio.getResponsableSet();
            Set<Responsable> responsableSetNew = domicilio.getResponsableSet();
            Set<Empresametalurgica> empresametalurgicaSetOld = persistentDomicilio.getEmpresametalurgicaSet();
            Set<Empresametalurgica> empresametalurgicaSetNew = domicilio.getEmpresametalurgicaSet();
            Set<Cliente> clienteSetOld = persistentDomicilio.getClienteSet();
            Set<Cliente> clienteSetNew = domicilio.getClienteSet();
            Set<Proveedor> proveedorSetOld = persistentDomicilio.getProveedorSet();
            Set<Proveedor> proveedorSetNew = domicilio.getProveedorSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetOld = persistentDomicilio.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetNew = domicilio.getProveedormantenimientomaquinaSet();
            Set<Empleado> empleadoSetOld = persistentDomicilio.getEmpleadoSet();
            Set<Empleado> empleadoSetNew = domicilio.getEmpleadoSet();
            if (barrioNew != null) {
                barrioNew = em.getReference(barrioNew.getClass(), barrioNew.getIdbarrio());
                domicilio.setBarrio(barrioNew);
            }
            Set<Responsable> attachedResponsableSetNew = new HashSet<Responsable>();
            for (Responsable responsableSetNewResponsableToAttach : responsableSetNew) {
                responsableSetNewResponsableToAttach = em.getReference(responsableSetNewResponsableToAttach.getClass(), responsableSetNewResponsableToAttach.getIdresponsable());
                attachedResponsableSetNew.add(responsableSetNewResponsableToAttach);
            }
            responsableSetNew = attachedResponsableSetNew;
            domicilio.setResponsableSet(responsableSetNew);
            Set<Empresametalurgica> attachedEmpresametalurgicaSetNew = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgicaToAttach : empresametalurgicaSetNew) {
                empresametalurgicaSetNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSetNew.add(empresametalurgicaSetNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSetNew = attachedEmpresametalurgicaSetNew;
            domicilio.setEmpresametalurgicaSet(empresametalurgicaSetNew);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            domicilio.setClienteSet(clienteSetNew);
            Set<Proveedor> attachedProveedorSetNew = new HashSet<Proveedor>();
            for (Proveedor proveedorSetNewProveedorToAttach : proveedorSetNew) {
                proveedorSetNewProveedorToAttach = em.getReference(proveedorSetNewProveedorToAttach.getClass(), proveedorSetNewProveedorToAttach.getIdproveedor());
                attachedProveedorSetNew.add(proveedorSetNewProveedorToAttach);
            }
            proveedorSetNew = attachedProveedorSetNew;
            domicilio.setProveedorSet(proveedorSetNew);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSetNew = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSetNew) {
                proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSetNew.add(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSetNew = attachedProveedormantenimientomaquinaSetNew;
            domicilio.setProveedormantenimientomaquinaSet(proveedormantenimientomaquinaSetNew);
            Set<Empleado> attachedEmpleadoSetNew = new HashSet<Empleado>();
            for (Empleado empleadoSetNewEmpleadoToAttach : empleadoSetNew) {
                empleadoSetNewEmpleadoToAttach = em.getReference(empleadoSetNewEmpleadoToAttach.getClass(), empleadoSetNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSetNew.add(empleadoSetNewEmpleadoToAttach);
            }
            empleadoSetNew = attachedEmpleadoSetNew;
            domicilio.setEmpleadoSet(empleadoSetNew);
            domicilio = em.merge(domicilio);
            if (barrioOld != null && !barrioOld.equals(barrioNew)) {
                barrioOld.getDomicilioSet().remove(domicilio);
                barrioOld = em.merge(barrioOld);
            }
            if (barrioNew != null && !barrioNew.equals(barrioOld)) {
                barrioNew.getDomicilioSet().add(domicilio);
                barrioNew = em.merge(barrioNew);
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
            Set<Responsable> responsableSet = domicilio.getResponsableSet();
            for (Responsable responsableSetResponsable : responsableSet) {
                responsableSetResponsable.setDomicilio(null);
                responsableSetResponsable = em.merge(responsableSetResponsable);
            }
            Set<Empresametalurgica> empresametalurgicaSet = domicilio.getEmpresametalurgicaSet();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : empresametalurgicaSet) {
                empresametalurgicaSetEmpresametalurgica.setDomicilio(null);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
            }
            Set<Cliente> clienteSet = domicilio.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setDomicilio(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Proveedor> proveedorSet = domicilio.getProveedorSet();
            for (Proveedor proveedorSetProveedor : proveedorSet) {
                proveedorSetProveedor.setDomicilio(null);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet = domicilio.getProveedormantenimientomaquinaSet();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : proveedormantenimientomaquinaSet) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setDomicilio(null);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
            }
            Set<Empleado> empleadoSet = domicilio.getEmpleadoSet();
            for (Empleado empleadoSetEmpleado : empleadoSet) {
                empleadoSetEmpleado.setDomicilio(null);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
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
