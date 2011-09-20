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
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Cliente;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;

/**
 *
 * @author Nino
 */
public class CondicionivaJpaController implements Serializable {

    public CondicionivaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Condicioniva condicioniva) throws PreexistingEntityException, Exception {
        if (condicioniva.getEmpresametalurgicaList() == null) {
            condicioniva.setEmpresametalurgicaList(new ArrayList<Empresametalurgica>());
        }
        if (condicioniva.getClienteList() == null) {
            condicioniva.setClienteList(new ArrayList<Cliente>());
        }
        if (condicioniva.getProveedorList() == null) {
            condicioniva.setProveedorList(new ArrayList<Proveedor>());
        }
        if (condicioniva.getProveedormantenimientomaquinaList() == null) {
            condicioniva.setProveedormantenimientomaquinaList(new ArrayList<Proveedormantenimientomaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empresametalurgica> attachedEmpresametalurgicaList = new ArrayList<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaListEmpresametalurgicaToAttach : condicioniva.getEmpresametalurgicaList()) {
                empresametalurgicaListEmpresametalurgicaToAttach = em.getReference(empresametalurgicaListEmpresametalurgicaToAttach.getClass(), empresametalurgicaListEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaList.add(empresametalurgicaListEmpresametalurgicaToAttach);
            }
            condicioniva.setEmpresametalurgicaList(attachedEmpresametalurgicaList);
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : condicioniva.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            condicioniva.setClienteList(attachedClienteList);
            List<Proveedor> attachedProveedorList = new ArrayList<Proveedor>();
            for (Proveedor proveedorListProveedorToAttach : condicioniva.getProveedorList()) {
                proveedorListProveedorToAttach = em.getReference(proveedorListProveedorToAttach.getClass(), proveedorListProveedorToAttach.getIdproveedor());
                attachedProveedorList.add(proveedorListProveedorToAttach);
            }
            condicioniva.setProveedorList(attachedProveedorList);
            List<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaList = new ArrayList<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach : condicioniva.getProveedormantenimientomaquinaList()) {
                proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaList.add(proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach);
            }
            condicioniva.setProveedormantenimientomaquinaList(attachedProveedormantenimientomaquinaList);
            em.persist(condicioniva);
            for (Empresametalurgica empresametalurgicaListEmpresametalurgica : condicioniva.getEmpresametalurgicaList()) {
                Condicioniva oldCondicionivaOfEmpresametalurgicaListEmpresametalurgica = empresametalurgicaListEmpresametalurgica.getCondicioniva();
                empresametalurgicaListEmpresametalurgica.setCondicioniva(condicioniva);
                empresametalurgicaListEmpresametalurgica = em.merge(empresametalurgicaListEmpresametalurgica);
                if (oldCondicionivaOfEmpresametalurgicaListEmpresametalurgica != null) {
                    oldCondicionivaOfEmpresametalurgicaListEmpresametalurgica.getEmpresametalurgicaList().remove(empresametalurgicaListEmpresametalurgica);
                    oldCondicionivaOfEmpresametalurgicaListEmpresametalurgica = em.merge(oldCondicionivaOfEmpresametalurgicaListEmpresametalurgica);
                }
            }
            for (Cliente clienteListCliente : condicioniva.getClienteList()) {
                Condicioniva oldCondicionivaOfClienteListCliente = clienteListCliente.getCondicioniva();
                clienteListCliente.setCondicioniva(condicioniva);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldCondicionivaOfClienteListCliente != null) {
                    oldCondicionivaOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldCondicionivaOfClienteListCliente = em.merge(oldCondicionivaOfClienteListCliente);
                }
            }
            for (Proveedor proveedorListProveedor : condicioniva.getProveedorList()) {
                Condicioniva oldCondicionOfProveedorListProveedor = proveedorListProveedor.getCondicion();
                proveedorListProveedor.setCondicion(condicioniva);
                proveedorListProveedor = em.merge(proveedorListProveedor);
                if (oldCondicionOfProveedorListProveedor != null) {
                    oldCondicionOfProveedorListProveedor.getProveedorList().remove(proveedorListProveedor);
                    oldCondicionOfProveedorListProveedor = em.merge(oldCondicionOfProveedorListProveedor);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquina : condicioniva.getProveedormantenimientomaquinaList()) {
                Condicioniva oldCondicionivaOfProveedormantenimientomaquinaListProveedormantenimientomaquina = proveedormantenimientomaquinaListProveedormantenimientomaquina.getCondicioniva();
                proveedormantenimientomaquinaListProveedormantenimientomaquina.setCondicioniva(condicioniva);
                proveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListProveedormantenimientomaquina);
                if (oldCondicionivaOfProveedormantenimientomaquinaListProveedormantenimientomaquina != null) {
                    oldCondicionivaOfProveedormantenimientomaquinaListProveedormantenimientomaquina.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquinaListProveedormantenimientomaquina);
                    oldCondicionivaOfProveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(oldCondicionivaOfProveedormantenimientomaquinaListProveedormantenimientomaquina);
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
            List<Empresametalurgica> empresametalurgicaListOld = persistentCondicioniva.getEmpresametalurgicaList();
            List<Empresametalurgica> empresametalurgicaListNew = condicioniva.getEmpresametalurgicaList();
            List<Cliente> clienteListOld = persistentCondicioniva.getClienteList();
            List<Cliente> clienteListNew = condicioniva.getClienteList();
            List<Proveedor> proveedorListOld = persistentCondicioniva.getProveedorList();
            List<Proveedor> proveedorListNew = condicioniva.getProveedorList();
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaListOld = persistentCondicioniva.getProveedormantenimientomaquinaList();
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaListNew = condicioniva.getProveedormantenimientomaquinaList();
            List<Empresametalurgica> attachedEmpresametalurgicaListNew = new ArrayList<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaListNewEmpresametalurgicaToAttach : empresametalurgicaListNew) {
                empresametalurgicaListNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaListNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaListNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaListNew.add(empresametalurgicaListNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaListNew = attachedEmpresametalurgicaListNew;
            condicioniva.setEmpresametalurgicaList(empresametalurgicaListNew);
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            condicioniva.setClienteList(clienteListNew);
            List<Proveedor> attachedProveedorListNew = new ArrayList<Proveedor>();
            for (Proveedor proveedorListNewProveedorToAttach : proveedorListNew) {
                proveedorListNewProveedorToAttach = em.getReference(proveedorListNewProveedorToAttach.getClass(), proveedorListNewProveedorToAttach.getIdproveedor());
                attachedProveedorListNew.add(proveedorListNewProveedorToAttach);
            }
            proveedorListNew = attachedProveedorListNew;
            condicioniva.setProveedorList(proveedorListNew);
            List<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaListNew = new ArrayList<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaListNew) {
                proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaListNew.add(proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaListNew = attachedProveedormantenimientomaquinaListNew;
            condicioniva.setProveedormantenimientomaquinaList(proveedormantenimientomaquinaListNew);
            condicioniva = em.merge(condicioniva);
            for (Empresametalurgica empresametalurgicaListOldEmpresametalurgica : empresametalurgicaListOld) {
                if (!empresametalurgicaListNew.contains(empresametalurgicaListOldEmpresametalurgica)) {
                    empresametalurgicaListOldEmpresametalurgica.setCondicioniva(null);
                    empresametalurgicaListOldEmpresametalurgica = em.merge(empresametalurgicaListOldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaListNewEmpresametalurgica : empresametalurgicaListNew) {
                if (!empresametalurgicaListOld.contains(empresametalurgicaListNewEmpresametalurgica)) {
                    Condicioniva oldCondicionivaOfEmpresametalurgicaListNewEmpresametalurgica = empresametalurgicaListNewEmpresametalurgica.getCondicioniva();
                    empresametalurgicaListNewEmpresametalurgica.setCondicioniva(condicioniva);
                    empresametalurgicaListNewEmpresametalurgica = em.merge(empresametalurgicaListNewEmpresametalurgica);
                    if (oldCondicionivaOfEmpresametalurgicaListNewEmpresametalurgica != null && !oldCondicionivaOfEmpresametalurgicaListNewEmpresametalurgica.equals(condicioniva)) {
                        oldCondicionivaOfEmpresametalurgicaListNewEmpresametalurgica.getEmpresametalurgicaList().remove(empresametalurgicaListNewEmpresametalurgica);
                        oldCondicionivaOfEmpresametalurgicaListNewEmpresametalurgica = em.merge(oldCondicionivaOfEmpresametalurgicaListNewEmpresametalurgica);
                    }
                }
            }
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setCondicioniva(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Condicioniva oldCondicionivaOfClienteListNewCliente = clienteListNewCliente.getCondicioniva();
                    clienteListNewCliente.setCondicioniva(condicioniva);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldCondicionivaOfClienteListNewCliente != null && !oldCondicionivaOfClienteListNewCliente.equals(condicioniva)) {
                        oldCondicionivaOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldCondicionivaOfClienteListNewCliente = em.merge(oldCondicionivaOfClienteListNewCliente);
                    }
                }
            }
            for (Proveedor proveedorListOldProveedor : proveedorListOld) {
                if (!proveedorListNew.contains(proveedorListOldProveedor)) {
                    proveedorListOldProveedor.setCondicion(null);
                    proveedorListOldProveedor = em.merge(proveedorListOldProveedor);
                }
            }
            for (Proveedor proveedorListNewProveedor : proveedorListNew) {
                if (!proveedorListOld.contains(proveedorListNewProveedor)) {
                    Condicioniva oldCondicionOfProveedorListNewProveedor = proveedorListNewProveedor.getCondicion();
                    proveedorListNewProveedor.setCondicion(condicioniva);
                    proveedorListNewProveedor = em.merge(proveedorListNewProveedor);
                    if (oldCondicionOfProveedorListNewProveedor != null && !oldCondicionOfProveedorListNewProveedor.equals(condicioniva)) {
                        oldCondicionOfProveedorListNewProveedor.getProveedorList().remove(proveedorListNewProveedor);
                        oldCondicionOfProveedorListNewProveedor = em.merge(oldCondicionOfProveedorListNewProveedor);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListOldProveedormantenimientomaquina : proveedormantenimientomaquinaListOld) {
                if (!proveedormantenimientomaquinaListNew.contains(proveedormantenimientomaquinaListOldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaListOldProveedormantenimientomaquina.setCondicioniva(null);
                    proveedormantenimientomaquinaListOldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListOldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListNewProveedormantenimientomaquina : proveedormantenimientomaquinaListNew) {
                if (!proveedormantenimientomaquinaListOld.contains(proveedormantenimientomaquinaListNewProveedormantenimientomaquina)) {
                    Condicioniva oldCondicionivaOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina = proveedormantenimientomaquinaListNewProveedormantenimientomaquina.getCondicioniva();
                    proveedormantenimientomaquinaListNewProveedormantenimientomaquina.setCondicioniva(condicioniva);
                    proveedormantenimientomaquinaListNewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListNewProveedormantenimientomaquina);
                    if (oldCondicionivaOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina != null && !oldCondicionivaOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina.equals(condicioniva)) {
                        oldCondicionivaOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquinaListNewProveedormantenimientomaquina);
                        oldCondicionivaOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina = em.merge(oldCondicionivaOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina);
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
            List<Empresametalurgica> empresametalurgicaList = condicioniva.getEmpresametalurgicaList();
            for (Empresametalurgica empresametalurgicaListEmpresametalurgica : empresametalurgicaList) {
                empresametalurgicaListEmpresametalurgica.setCondicioniva(null);
                empresametalurgicaListEmpresametalurgica = em.merge(empresametalurgicaListEmpresametalurgica);
            }
            List<Cliente> clienteList = condicioniva.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setCondicioniva(null);
                clienteListCliente = em.merge(clienteListCliente);
            }
            List<Proveedor> proveedorList = condicioniva.getProveedorList();
            for (Proveedor proveedorListProveedor : proveedorList) {
                proveedorListProveedor.setCondicion(null);
                proveedorListProveedor = em.merge(proveedorListProveedor);
            }
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaList = condicioniva.getProveedormantenimientomaquinaList();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquina : proveedormantenimientomaquinaList) {
                proveedormantenimientomaquinaListProveedormantenimientomaquina.setCondicioniva(null);
                proveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListProveedormantenimientomaquina);
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
