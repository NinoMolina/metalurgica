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
import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.datos.jpa.entity.Tipodocumento;
import metalsoft.datos.jpa.entity.Domicilio;
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
public class ResponsableJpaController implements Serializable {

    public ResponsableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Responsable responsable) throws PreexistingEntityException, Exception {
        if (responsable.getEmpresametalurgicaList() == null) {
            responsable.setEmpresametalurgicaList(new ArrayList<Empresametalurgica>());
        }
        if (responsable.getClienteList() == null) {
            responsable.setClienteList(new ArrayList<Cliente>());
        }
        if (responsable.getProveedorList() == null) {
            responsable.setProveedorList(new ArrayList<Proveedor>());
        }
        if (responsable.getProveedormantenimientomaquinaList() == null) {
            responsable.setProveedormantenimientomaquinaList(new ArrayList<Proveedormantenimientomaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipodocumento tipodocumento = responsable.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento = em.getReference(tipodocumento.getClass(), tipodocumento.getIdtipodocumento());
                responsable.setTipodocumento(tipodocumento);
            }
            Domicilio domicilio = responsable.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                responsable.setDomicilio(domicilio);
            }
            List<Empresametalurgica> attachedEmpresametalurgicaList = new ArrayList<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaListEmpresametalurgicaToAttach : responsable.getEmpresametalurgicaList()) {
                empresametalurgicaListEmpresametalurgicaToAttach = em.getReference(empresametalurgicaListEmpresametalurgicaToAttach.getClass(), empresametalurgicaListEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaList.add(empresametalurgicaListEmpresametalurgicaToAttach);
            }
            responsable.setEmpresametalurgicaList(attachedEmpresametalurgicaList);
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : responsable.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            responsable.setClienteList(attachedClienteList);
            List<Proveedor> attachedProveedorList = new ArrayList<Proveedor>();
            for (Proveedor proveedorListProveedorToAttach : responsable.getProveedorList()) {
                proveedorListProveedorToAttach = em.getReference(proveedorListProveedorToAttach.getClass(), proveedorListProveedorToAttach.getIdproveedor());
                attachedProveedorList.add(proveedorListProveedorToAttach);
            }
            responsable.setProveedorList(attachedProveedorList);
            List<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaList = new ArrayList<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach : responsable.getProveedormantenimientomaquinaList()) {
                proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaList.add(proveedormantenimientomaquinaListProveedormantenimientomaquinaToAttach);
            }
            responsable.setProveedormantenimientomaquinaList(attachedProveedormantenimientomaquinaList);
            em.persist(responsable);
            if (tipodocumento != null) {
                tipodocumento.getResponsableList().add(responsable);
                tipodocumento = em.merge(tipodocumento);
            }
            if (domicilio != null) {
                domicilio.getResponsableList().add(responsable);
                domicilio = em.merge(domicilio);
            }
            for (Empresametalurgica empresametalurgicaListEmpresametalurgica : responsable.getEmpresametalurgicaList()) {
                Responsable oldResponsableOfEmpresametalurgicaListEmpresametalurgica = empresametalurgicaListEmpresametalurgica.getResponsable();
                empresametalurgicaListEmpresametalurgica.setResponsable(responsable);
                empresametalurgicaListEmpresametalurgica = em.merge(empresametalurgicaListEmpresametalurgica);
                if (oldResponsableOfEmpresametalurgicaListEmpresametalurgica != null) {
                    oldResponsableOfEmpresametalurgicaListEmpresametalurgica.getEmpresametalurgicaList().remove(empresametalurgicaListEmpresametalurgica);
                    oldResponsableOfEmpresametalurgicaListEmpresametalurgica = em.merge(oldResponsableOfEmpresametalurgicaListEmpresametalurgica);
                }
            }
            for (Cliente clienteListCliente : responsable.getClienteList()) {
                Responsable oldResponsableOfClienteListCliente = clienteListCliente.getResponsable();
                clienteListCliente.setResponsable(responsable);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldResponsableOfClienteListCliente != null) {
                    oldResponsableOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldResponsableOfClienteListCliente = em.merge(oldResponsableOfClienteListCliente);
                }
            }
            for (Proveedor proveedorListProveedor : responsable.getProveedorList()) {
                Responsable oldResponsableOfProveedorListProveedor = proveedorListProveedor.getResponsable();
                proveedorListProveedor.setResponsable(responsable);
                proveedorListProveedor = em.merge(proveedorListProveedor);
                if (oldResponsableOfProveedorListProveedor != null) {
                    oldResponsableOfProveedorListProveedor.getProveedorList().remove(proveedorListProveedor);
                    oldResponsableOfProveedorListProveedor = em.merge(oldResponsableOfProveedorListProveedor);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquina : responsable.getProveedormantenimientomaquinaList()) {
                Responsable oldResponsableOfProveedormantenimientomaquinaListProveedormantenimientomaquina = proveedormantenimientomaquinaListProveedormantenimientomaquina.getResponsable();
                proveedormantenimientomaquinaListProveedormantenimientomaquina.setResponsable(responsable);
                proveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListProveedormantenimientomaquina);
                if (oldResponsableOfProveedormantenimientomaquinaListProveedormantenimientomaquina != null) {
                    oldResponsableOfProveedormantenimientomaquinaListProveedormantenimientomaquina.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquinaListProveedormantenimientomaquina);
                    oldResponsableOfProveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(oldResponsableOfProveedormantenimientomaquinaListProveedormantenimientomaquina);
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
            Tipodocumento tipodocumentoOld = persistentResponsable.getTipodocumento();
            Tipodocumento tipodocumentoNew = responsable.getTipodocumento();
            Domicilio domicilioOld = persistentResponsable.getDomicilio();
            Domicilio domicilioNew = responsable.getDomicilio();
            List<Empresametalurgica> empresametalurgicaListOld = persistentResponsable.getEmpresametalurgicaList();
            List<Empresametalurgica> empresametalurgicaListNew = responsable.getEmpresametalurgicaList();
            List<Cliente> clienteListOld = persistentResponsable.getClienteList();
            List<Cliente> clienteListNew = responsable.getClienteList();
            List<Proveedor> proveedorListOld = persistentResponsable.getProveedorList();
            List<Proveedor> proveedorListNew = responsable.getProveedorList();
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaListOld = persistentResponsable.getProveedormantenimientomaquinaList();
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaListNew = responsable.getProveedormantenimientomaquinaList();
            if (tipodocumentoNew != null) {
                tipodocumentoNew = em.getReference(tipodocumentoNew.getClass(), tipodocumentoNew.getIdtipodocumento());
                responsable.setTipodocumento(tipodocumentoNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                responsable.setDomicilio(domicilioNew);
            }
            List<Empresametalurgica> attachedEmpresametalurgicaListNew = new ArrayList<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaListNewEmpresametalurgicaToAttach : empresametalurgicaListNew) {
                empresametalurgicaListNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaListNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaListNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaListNew.add(empresametalurgicaListNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaListNew = attachedEmpresametalurgicaListNew;
            responsable.setEmpresametalurgicaList(empresametalurgicaListNew);
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            responsable.setClienteList(clienteListNew);
            List<Proveedor> attachedProveedorListNew = new ArrayList<Proveedor>();
            for (Proveedor proveedorListNewProveedorToAttach : proveedorListNew) {
                proveedorListNewProveedorToAttach = em.getReference(proveedorListNewProveedorToAttach.getClass(), proveedorListNewProveedorToAttach.getIdproveedor());
                attachedProveedorListNew.add(proveedorListNewProveedorToAttach);
            }
            proveedorListNew = attachedProveedorListNew;
            responsable.setProveedorList(proveedorListNew);
            List<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaListNew = new ArrayList<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaListNew) {
                proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaListNew.add(proveedormantenimientomaquinaListNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaListNew = attachedProveedormantenimientomaquinaListNew;
            responsable.setProveedormantenimientomaquinaList(proveedormantenimientomaquinaListNew);
            responsable = em.merge(responsable);
            if (tipodocumentoOld != null && !tipodocumentoOld.equals(tipodocumentoNew)) {
                tipodocumentoOld.getResponsableList().remove(responsable);
                tipodocumentoOld = em.merge(tipodocumentoOld);
            }
            if (tipodocumentoNew != null && !tipodocumentoNew.equals(tipodocumentoOld)) {
                tipodocumentoNew.getResponsableList().add(responsable);
                tipodocumentoNew = em.merge(tipodocumentoNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getResponsableList().remove(responsable);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getResponsableList().add(responsable);
                domicilioNew = em.merge(domicilioNew);
            }
            for (Empresametalurgica empresametalurgicaListOldEmpresametalurgica : empresametalurgicaListOld) {
                if (!empresametalurgicaListNew.contains(empresametalurgicaListOldEmpresametalurgica)) {
                    empresametalurgicaListOldEmpresametalurgica.setResponsable(null);
                    empresametalurgicaListOldEmpresametalurgica = em.merge(empresametalurgicaListOldEmpresametalurgica);
                }
            }
            for (Empresametalurgica empresametalurgicaListNewEmpresametalurgica : empresametalurgicaListNew) {
                if (!empresametalurgicaListOld.contains(empresametalurgicaListNewEmpresametalurgica)) {
                    Responsable oldResponsableOfEmpresametalurgicaListNewEmpresametalurgica = empresametalurgicaListNewEmpresametalurgica.getResponsable();
                    empresametalurgicaListNewEmpresametalurgica.setResponsable(responsable);
                    empresametalurgicaListNewEmpresametalurgica = em.merge(empresametalurgicaListNewEmpresametalurgica);
                    if (oldResponsableOfEmpresametalurgicaListNewEmpresametalurgica != null && !oldResponsableOfEmpresametalurgicaListNewEmpresametalurgica.equals(responsable)) {
                        oldResponsableOfEmpresametalurgicaListNewEmpresametalurgica.getEmpresametalurgicaList().remove(empresametalurgicaListNewEmpresametalurgica);
                        oldResponsableOfEmpresametalurgicaListNewEmpresametalurgica = em.merge(oldResponsableOfEmpresametalurgicaListNewEmpresametalurgica);
                    }
                }
            }
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setResponsable(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Responsable oldResponsableOfClienteListNewCliente = clienteListNewCliente.getResponsable();
                    clienteListNewCliente.setResponsable(responsable);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldResponsableOfClienteListNewCliente != null && !oldResponsableOfClienteListNewCliente.equals(responsable)) {
                        oldResponsableOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldResponsableOfClienteListNewCliente = em.merge(oldResponsableOfClienteListNewCliente);
                    }
                }
            }
            for (Proveedor proveedorListOldProveedor : proveedorListOld) {
                if (!proveedorListNew.contains(proveedorListOldProveedor)) {
                    proveedorListOldProveedor.setResponsable(null);
                    proveedorListOldProveedor = em.merge(proveedorListOldProveedor);
                }
            }
            for (Proveedor proveedorListNewProveedor : proveedorListNew) {
                if (!proveedorListOld.contains(proveedorListNewProveedor)) {
                    Responsable oldResponsableOfProveedorListNewProveedor = proveedorListNewProveedor.getResponsable();
                    proveedorListNewProveedor.setResponsable(responsable);
                    proveedorListNewProveedor = em.merge(proveedorListNewProveedor);
                    if (oldResponsableOfProveedorListNewProveedor != null && !oldResponsableOfProveedorListNewProveedor.equals(responsable)) {
                        oldResponsableOfProveedorListNewProveedor.getProveedorList().remove(proveedorListNewProveedor);
                        oldResponsableOfProveedorListNewProveedor = em.merge(oldResponsableOfProveedorListNewProveedor);
                    }
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListOldProveedormantenimientomaquina : proveedormantenimientomaquinaListOld) {
                if (!proveedormantenimientomaquinaListNew.contains(proveedormantenimientomaquinaListOldProveedormantenimientomaquina)) {
                    proveedormantenimientomaquinaListOldProveedormantenimientomaquina.setResponsable(null);
                    proveedormantenimientomaquinaListOldProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListOldProveedormantenimientomaquina);
                }
            }
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListNewProveedormantenimientomaquina : proveedormantenimientomaquinaListNew) {
                if (!proveedormantenimientomaquinaListOld.contains(proveedormantenimientomaquinaListNewProveedormantenimientomaquina)) {
                    Responsable oldResponsableOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina = proveedormantenimientomaquinaListNewProveedormantenimientomaquina.getResponsable();
                    proveedormantenimientomaquinaListNewProveedormantenimientomaquina.setResponsable(responsable);
                    proveedormantenimientomaquinaListNewProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListNewProveedormantenimientomaquina);
                    if (oldResponsableOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina != null && !oldResponsableOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina.equals(responsable)) {
                        oldResponsableOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina.getProveedormantenimientomaquinaList().remove(proveedormantenimientomaquinaListNewProveedormantenimientomaquina);
                        oldResponsableOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina = em.merge(oldResponsableOfProveedormantenimientomaquinaListNewProveedormantenimientomaquina);
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
            Tipodocumento tipodocumento = responsable.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento.getResponsableList().remove(responsable);
                tipodocumento = em.merge(tipodocumento);
            }
            Domicilio domicilio = responsable.getDomicilio();
            if (domicilio != null) {
                domicilio.getResponsableList().remove(responsable);
                domicilio = em.merge(domicilio);
            }
            List<Empresametalurgica> empresametalurgicaList = responsable.getEmpresametalurgicaList();
            for (Empresametalurgica empresametalurgicaListEmpresametalurgica : empresametalurgicaList) {
                empresametalurgicaListEmpresametalurgica.setResponsable(null);
                empresametalurgicaListEmpresametalurgica = em.merge(empresametalurgicaListEmpresametalurgica);
            }
            List<Cliente> clienteList = responsable.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setResponsable(null);
                clienteListCliente = em.merge(clienteListCliente);
            }
            List<Proveedor> proveedorList = responsable.getProveedorList();
            for (Proveedor proveedorListProveedor : proveedorList) {
                proveedorListProveedor.setResponsable(null);
                proveedorListProveedor = em.merge(proveedorListProveedor);
            }
            List<Proveedormantenimientomaquina> proveedormantenimientomaquinaList = responsable.getProveedormantenimientomaquinaList();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaListProveedormantenimientomaquina : proveedormantenimientomaquinaList) {
                proveedormantenimientomaquinaListProveedormantenimientomaquina.setResponsable(null);
                proveedormantenimientomaquinaListProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaListProveedormantenimientomaquina);
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
