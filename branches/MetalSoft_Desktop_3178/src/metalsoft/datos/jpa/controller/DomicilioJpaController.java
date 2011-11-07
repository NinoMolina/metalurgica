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
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Barrio;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Responsable;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Cliente;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.datos.jpa.entity.Empleado;

/**
 *
 * @author Nino
 */
public class DomicilioJpaController implements Serializable {

    public DomicilioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Domicilio domicilio) throws PreexistingEntityException, Exception {
        if (domicilio.getResponsableList() == null) {
            domicilio.setResponsableList(new ArrayList<Responsable>());
        }
        if (domicilio.getEmpresametalurgicaList() == null) {
            domicilio.setEmpresametalurgicaList(new ArrayList<Empresametalurgica>());
        }
        if (domicilio.getClienteList() == null) {
            domicilio.setClienteList(new ArrayList<Cliente>());
        }
        if (domicilio.getProveedorList() == null) {
            domicilio.setProveedorList(new ArrayList<Proveedor>());
        }
        if (domicilio.getProveedormantenimientomaquinaList() == null) {
            domicilio.setProveedormantenimientomaquinaList(new ArrayList<Proveedormantenimientomaquina>());
        }
        if (domicilio.getEmpleadoList() == null) {
            domicilio.setEmpleadoList(new ArrayList<Empleado>());
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
            List<Responsable> attachedResponsableList = new ArrayList<Responsable>();
            for (Responsable responsableListResponsableToAttach : domicilio.getResponsableList()) {
                responsableListResponsableToAttach = em.getReference(responsableListResponsableToAttach.getClass(), responsableListResponsableToAttach.getIdresponsable());
                attachedResponsableList.add(responsableListResponsableToAttach);
            }
            domicilio.setResponsableList(attachedResponsableList);
            List<Empresametalurgica> attachedEmpresametalurgicaList = new ArrayList<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaListEmpresametalurgicaToAttach : domicilio.getEmpresametalurgicaList()) {
                empresametalurgicaListEmpresametalurgicaToAttach = em.getReference(empresametalurgicaListEmpresametalurgicaToAttach.getClass(), empresametalurgicaListEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaList.add(empresametalurgicaListEmpresametalurgicaToAttach);
            }
            domicilio.setEmpresametalurgicaList(attachedEmpresametalurgicaList);
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : domicilio.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            domicilio.setClienteList(attachedClienteList);
            List<Proveedor> attachedProveedorList = new ArrayList<Proveedor>();
            for (Proveedor proveedorListProveedorToAttach : domicilio.getProveedorList()) {
                proveedorListProveedorToAttach = em.getReference(proveedorListProveedorToAttach.getClass(), proveedorListProveedorToAttach.getIdproveedor());
                attachedProveedorList.add(proveedorListProveedorToAttach);
            }
            domicilio.setProveedorList(attachedProveedorList);
            List<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaList = new ArrayList<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach : domicilio.getProveedormantenimientomaquinaList()) {
                proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaList.add(proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach);
            }
            domicilio.setProveedormantenimientomaquinaList(attachedProveedormantenimientomaquinaList);
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : domicilio.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getIdempleado());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            domicilio.setEmpleadoList(attachedEmpleadoList);
            em.persist(domicilio);
            if (barrio != null) {
                barrio.getDomicilioList().add(domicilio);
                barrio = em.merge(barrio);
            }
            for (Responsable responsableListResponsable : domicilio.getResponsableList()) {
                Domicilio oldDomicilioOfResponsableListResponsable = responsableListResponsable.getDomicilio();
                responsableListResponsable.setDomicilio(domicilio);
                responsableListResponsable = em.merge(responsableListResponsable);
                if (oldDomicilioOfResponsableListResponsable != null) {
                    oldDomicilioOfResponsableListResponsable.getResponsableList().remove(responsableListResponsable);
                    oldDomicilioOfResponsableListResponsable = em.merge(oldDomicilioOfResponsableListResponsable);
                }
            }
            for (Empresametalurgica empresametalurgicaListEmpresametalurgica : domicilio.getEmpresametalurgicaList()) {
                Domicilio oldDomicilioOfEmpresametalurgicaListEmpresametalurgica = empresametalurgicaListEmpresametalurgica.getDomicilio();
                empresametalurgicaListEmpresametalurgica.setDomicilio(domicilio);
                empresametalurgicaListEmpresametalurgica = em.merge(empresametalurgicaListEmpresametalurgica);
                if (oldDomicilioOfEmpresametalurgicaListEmpresametalurgica != null) {
                    oldDomicilioOfEmpresametalurgicaListEmpresametalurgica.getEmpresametalurgicaList().remove(empresametalurgicaListEmpresametalurgica);
                    oldDomicilioOfEmpresametalurgicaListEmpresametalurgica = em.merge(oldDomicilioOfEmpresametalurgicaListEmpresametalurgica);
                }
            }
            for (Cliente clienteListCliente : domicilio.getClienteList()) {
                Domicilio oldDomicilioOfClienteListCliente = clienteListCliente.getDomicilio();
                clienteListCliente.setDomicilio(domicilio);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldDomicilioOfClienteListCliente != null) {
                    oldDomicilioOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldDomicilioOfClienteListCliente = em.merge(oldDomicilioOfClienteListCliente);
                }
            }
            for (Proveedor proveedorListProveedor : domicilio.getProveedorList()) {
                Domicilio oldDomicilioOfProveedorListProveedor = proveedorListProveedor.getDomicilio();
                proveedorListProveedor.setDomicilio(domicilio);
                proveedorListProveedor = em.merge(proveedorListProveedor);
                if (oldDomicilioOfProveedorListProveedor != null) {
                    oldDomicilioOfProveedorListProveedor.getProveedorList().remove(proveedorListProveedor);
                    oldDomicilioOfProveedorListProveedor = em.merge(oldDomicilioOfProveedorListProveedor);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquina : domicilio.getProveedormantenimientomaquinaList()) {
                Domicilio oldDomicilioOfProveedormantenimientomaquinaListProveedormantenimientomaquina = proveedormantenimientomaquinaListProveedormantenimientomaquina.getDomicilio();
                proveedormantenimientomaquinaListProveedormantenimientomaquina.setDomicilio(domicilio);
                proveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListProveedormantenimientomaquina);
                if (oldDomicilioOfProveedormantenimientomaquinaListProveedormantenimientomaquina != null) {
                    oldDomicilioOfProveedormantenimientomaquinaListProveedormantenimientomaquina.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquinaListProveedormantenimientomaquina);
                    oldDomicilioOfProveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(oldDomicilioOfProveedormantenimientomaquinaListProveedormantenimientomaquina);
                }
            }
            for (Empleado empleadoListEmpleado : domicilio.getEmpleadoList()) {
                Domicilio oldDomicilioOfEmpleadoListEmpleado = empleadoListEmpleado.getDomicilio();
                empleadoListEmpleado.setDomicilio(domicilio);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldDomicilioOfEmpleadoListEmpleado != null) {
                    oldDomicilioOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldDomicilioOfEmpleadoListEmpleado = em.merge(oldDomicilioOfEmpleadoListEmpleado);
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
            List<Responsable> responsableListOld = persistentDomicilio.getResponsableList();
            List<Responsable> responsableListNew = domicilio.getResponsableList();
            List<Empresametalurgica> empresametalurgicaListOld = persistentDomicilio.getEmpresametalurgicaList();
            List<Empresametalurgica> empresametalurgicaListNew = domicilio.getEmpresametalurgicaList();
            List<Cliente> clienteListOld = persistentDomicilio.getClienteList();
            List<Cliente> clienteListNew = domicilio.getClienteList();
            List<Proveedor> proveedorListOld = persistentDomicilio.getProveedorList();
            List<Proveedor> proveedorListNew = domicilio.getProveedorList();
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaListOld = persistentDomicilio.getProveedormantenimientomaquinaList();
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaListNew = domicilio.getProveedormantenimientomaquinaList();
            List<Empleado> empleadoListOld = persistentDomicilio.getEmpleadoList();
            List<Empleado> empleadoListNew = domicilio.getEmpleadoList();
            if (barrioNew != null) {
                barrioNew = em.getReference(barrioNew.getClass(), barrioNew.getIdbarrio());
                domicilio.setBarrio(barrioNew);
            }
            List<Responsable> attachedResponsableListNew = new ArrayList<Responsable>();
            for (Responsable responsableListNewResponsableToAttach : responsableListNew) {
                responsableListNewResponsableToAttach = em.getReference(responsableListNewResponsableToAttach.getClass(), responsableListNewResponsableToAttach.getIdresponsable());
                attachedResponsableListNew.add(responsableListNewResponsableToAttach);
            }
            responsableListNew = attachedResponsableListNew;
            domicilio.setResponsableList(responsableListNew);
            List<Empresametalurgica> attachedEmpresametalurgicaListNew = new ArrayList<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaListNewEmpresametalurgicaToAttach : empresametalurgicaListNew) {
                empresametalurgicaListNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaListNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaListNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaListNew.add(empresametalurgicaListNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaListNew = attachedEmpresametalurgicaListNew;
            domicilio.setEmpresametalurgicaList(empresametalurgicaListNew);
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            domicilio.setClienteList(clienteListNew);
            List<Proveedor> attachedProveedorListNew = new ArrayList<Proveedor>();
            for (Proveedor proveedorListNewProveedorToAttach : proveedorListNew) {
                proveedorListNewProveedorToAttach = em.getReference(proveedorListNewProveedorToAttach.getClass(), proveedorListNewProveedorToAttach.getIdproveedor());
                attachedProveedorListNew.add(proveedorListNewProveedorToAttach);
            }
            proveedorListNew = attachedProveedorListNew;
            domicilio.setProveedorList(proveedorListNew);
            List<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaListNew = new ArrayList<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaListNew) {
                proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaListNew.add(proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaListNew = attachedProveedormantenimientomaquinaListNew;
            domicilio.setProveedormantenimientomaquinaList(proveedormantenimientomaquinaListNew);
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            domicilio.setEmpleadoList(empleadoListNew);
            domicilio = em.merge(domicilio);
            if (barrioOld != null && !barrioOld.equals(barrioNew)) {
                barrioOld.getDomicilioList().remove(domicilio);
                barrioOld = em.merge(barrioOld);
            }
            if (barrioNew != null && !barrioNew.equals(barrioOld)) {
                barrioNew.getDomicilioList().add(domicilio);
                barrioNew = em.merge(barrioNew);
            }
            for (Responsable responsableListOldResponsable : responsableListOld) {
                if (!responsableListNew.contains(responsableListOldResponsable)) {
                    responsableListOldResponsable.setDomicilio(null);
                    responsableListOldResponsable = em.merge(responsableListOldResponsable);
                }
            }
            for (Responsable responsableListNewResponsable : responsableListNew) {
                if (!responsableListOld.contains(responsableListNewResponsable)) {
                    Domicilio oldDomicilioOfResponsableListNewResponsable = responsableListNewResponsable.getDomicilio();
                    responsableListNewResponsable.setDomicilio(domicilio);
                    responsableListNewResponsable = em.merge(responsableListNewResponsable);
                    if (oldDomicilioOfResponsableListNewResponsable != null && !oldDomicilioOfResponsableListNewResponsable.equals(domicilio)) {
                        oldDomicilioOfResponsableListNewResponsable.getResponsableList().remove(responsableListNewResponsable);
                        oldDomicilioOfResponsableListNewResponsable = em.merge(oldDomicilioOfResponsableListNewResponsable);
                    }
                }
            }
            for (Empresametalurgica empresametalurgicaListOldEmpresametalurgica : empresametalurgicaListOld) {
                if (!empresametalurgicaListNew.contains(empresametalurgicaListOldEmpresametalurgica)) {
                    empresametalurgicaListOldEmpresametalurgica.setDomicilio(null);
                    empresametalurgicaListOldEmpresametalurgica = em.merge(empresametalurgicaListOldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaListNewEmpresametalurgica : empresametalurgicaListNew) {
                if (!empresametalurgicaListOld.contains(empresametalurgicaListNewEmpresametalurgica)) {
                    Domicilio oldDomicilioOfEmpresametalurgicaListNewEmpresametalurgica = empresametalurgicaListNewEmpresametalurgica.getDomicilio();
                    empresametalurgicaListNewEmpresametalurgica.setDomicilio(domicilio);
                    empresametalurgicaListNewEmpresametalurgica = em.merge(empresametalurgicaListNewEmpresametalurgica);
                    if (oldDomicilioOfEmpresametalurgicaListNewEmpresametalurgica != null && !oldDomicilioOfEmpresametalurgicaListNewEmpresametalurgica.equals(domicilio)) {
                        oldDomicilioOfEmpresametalurgicaListNewEmpresametalurgica.getEmpresametalurgicaList().remove(empresametalurgicaListNewEmpresametalurgica);
                        oldDomicilioOfEmpresametalurgicaListNewEmpresametalurgica = em.merge(oldDomicilioOfEmpresametalurgicaListNewEmpresametalurgica);
                    }
                }
            }
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setDomicilio(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Domicilio oldDomicilioOfClienteListNewCliente = clienteListNewCliente.getDomicilio();
                    clienteListNewCliente.setDomicilio(domicilio);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldDomicilioOfClienteListNewCliente != null && !oldDomicilioOfClienteListNewCliente.equals(domicilio)) {
                        oldDomicilioOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldDomicilioOfClienteListNewCliente = em.merge(oldDomicilioOfClienteListNewCliente);
                    }
                }
            }
            for (Proveedor proveedorListOldProveedor : proveedorListOld) {
                if (!proveedorListNew.contains(proveedorListOldProveedor)) {
                    proveedorListOldProveedor.setDomicilio(null);
                    proveedorListOldProveedor = em.merge(proveedorListOldProveedor);
                }
            }
            for (Proveedor proveedorListNewProveedor : proveedorListNew) {
                if (!proveedorListOld.contains(proveedorListNewProveedor)) {
                    Domicilio oldDomicilioOfProveedorListNewProveedor = proveedorListNewProveedor.getDomicilio();
                    proveedorListNewProveedor.setDomicilio(domicilio);
                    proveedorListNewProveedor = em.merge(proveedorListNewProveedor);
                    if (oldDomicilioOfProveedorListNewProveedor != null && !oldDomicilioOfProveedorListNewProveedor.equals(domicilio)) {
                        oldDomicilioOfProveedorListNewProveedor.getProveedorList().remove(proveedorListNewProveedor);
                        oldDomicilioOfProveedorListNewProveedor = em.merge(oldDomicilioOfProveedorListNewProveedor);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListOldProveedormantenimientomaquina : proveedormantenimientomaquinaListOld) {
                if (!proveedormantenimientomaquinaListNew.contains(proveedormantenimientomaquinaListOldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaListOldProveedormantenimientomaquina.setDomicilio(null);
                    proveedormantenimientomaquinaListOldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListOldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListNewProveedormantenimientomaquina : proveedormantenimientomaquinaListNew) {
                if (!proveedormantenimientomaquinaListOld.contains(proveedormantenimientomaquinaListNewProveedormantenimientomaquina)) {
                    Domicilio oldDomicilioOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina = proveedormantenimientomaquinaListNewProveedormantenimientomaquina.getDomicilio();
                    proveedormantenimientomaquinaListNewProveedormantenimientomaquina.setDomicilio(domicilio);
                    proveedormantenimientomaquinaListNewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListNewProveedormantenimientomaquina);
                    if (oldDomicilioOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina != null && !oldDomicilioOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina.equals(domicilio)) {
                        oldDomicilioOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquinaListNewProveedormantenimientomaquina);
                        oldDomicilioOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina = em.merge(oldDomicilioOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina);
                    }
                }
            }
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    empleadoListOldEmpleado.setDomicilio(null);
                    empleadoListOldEmpleado = em.merge(empleadoListOldEmpleado);
                }
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Domicilio oldDomicilioOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getDomicilio();
                    empleadoListNewEmpleado.setDomicilio(domicilio);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldDomicilioOfEmpleadoListNewEmpleado != null && !oldDomicilioOfEmpleadoListNewEmpleado.equals(domicilio)) {
                        oldDomicilioOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldDomicilioOfEmpleadoListNewEmpleado = em.merge(oldDomicilioOfEmpleadoListNewEmpleado);
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
                barrio.getDomicilioList().remove(domicilio);
                barrio = em.merge(barrio);
            }
            List<Responsable> responsableList = domicilio.getResponsableList();
            for (Responsable responsableListResponsable : responsableList) {
                responsableListResponsable.setDomicilio(null);
                responsableListResponsable = em.merge(responsableListResponsable);
            }
            List<Empresametalurgica> empresametalurgicaList = domicilio.getEmpresametalurgicaList();
            for (Empresametalurgica empresametalurgicaListEmpresametalurgica : empresametalurgicaList) {
                empresametalurgicaListEmpresametalurgica.setDomicilio(null);
                empresametalurgicaListEmpresametalurgica = em.merge(empresametalurgicaListEmpresametalurgica);
            }
            List<Cliente> clienteList = domicilio.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setDomicilio(null);
                clienteListCliente = em.merge(clienteListCliente);
            }
            List<Proveedor> proveedorList = domicilio.getProveedorList();
            for (Proveedor proveedorListProveedor : proveedorList) {
                proveedorListProveedor.setDomicilio(null);
                proveedorListProveedor = em.merge(proveedorListProveedor);
            }
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaList = domicilio.getProveedormantenimientomaquinaList();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquina : proveedormantenimientomaquinaList) {
                proveedormantenimientomaquinaListProveedormantenimientomaquina.setDomicilio(null);
                proveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListProveedormantenimientomaquina);
            }
            List<Empleado> empleadoList = domicilio.getEmpleadoList();
            for (Empleado empleadoListEmpleado : empleadoList) {
                empleadoListEmpleado.setDomicilio(null);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
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
