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
        if (responsable.getClienteSet() == null) {
            responsable.setClienteSet(new HashSet<Cliente>());
        }
        if (responsable.getProveedorSet() == null) {
            responsable.setProveedorSet(new HashSet<Proveedor>());
        }
        if (responsable.getProveedormantenimientomaquinaSet() == null) {
            responsable.setProveedormantenimientomaquinaSet(new HashSet<Proveedormantenimientomaquina>());
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
            Tipodocumento tipodocumento = responsable.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento = em.getReference(tipodocumento.getClass(), tipodocumento.getIdtipodocumento());
                responsable.setTipodocumento(tipodocumento);
            }
            Set<Empresametalurgica> attachedEmpresametalurgicaSet = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgicaToAttach : responsable.getEmpresametalurgicaSet()) {
                empresametalurgicaSetEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSet.add(empresametalurgicaSetEmpresametalurgicaToAttach);
            }
            responsable.setEmpresametalurgicaSet(attachedEmpresametalurgicaSet);
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : responsable.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            responsable.setClienteSet(attachedClienteSet);
            Set<Proveedor> attachedProveedorSet = new HashSet<Proveedor>();
            for (Proveedor proveedorSetProveedorToAttach : responsable.getProveedorSet()) {
                proveedorSetProveedorToAttach = em.getReference(proveedorSetProveedorToAttach.getClass(), proveedorSetProveedorToAttach.getIdproveedor());
                attachedProveedorSet.add(proveedorSetProveedorToAttach);
            }
            responsable.setProveedorSet(attachedProveedorSet);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach : responsable.getProveedormantenimientomaquinaSet()) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet.add(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach);
            }
            responsable.setProveedormantenimientomaquinaSet(attachedProveedormantenimientomaquinaSet);
            em.persist(responsable);
            if (domicilio != null) {
                domicilio.getResponsableSet().add(responsable);
                domicilio = em.merge(domicilio);
            }
            if (tipodocumento != null) {
                tipodocumento.getResponsableSet().add(responsable);
                tipodocumento = em.merge(tipodocumento);
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
            for (Cliente clienteSetCliente : responsable.getClienteSet()) {
                Responsable oldResponsableOfClienteSetCliente = clienteSetCliente.getResponsable();
                clienteSetCliente.setResponsable(responsable);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldResponsableOfClienteSetCliente != null) {
                    oldResponsableOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldResponsableOfClienteSetCliente = em.merge(oldResponsableOfClienteSetCliente);
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
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : responsable.getProveedormantenimientomaquinaSet()) {
                Responsable oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = proveedormantenimientomaquinaSetProveedormantenimientomaquina.getResponsable();
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setResponsable(responsable);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                if (oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina != null) {
                    oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                    oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(oldResponsableOfProveedormantenimientomaquinaSetProveedormantenimientomaquina);
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
            Tipodocumento tipodocumentoOld = persistentResponsable.getTipodocumento();
            Tipodocumento tipodocumentoNew = responsable.getTipodocumento();
            Set<Empresametalurgica> empresametalurgicaSetOld = persistentResponsable.getEmpresametalurgicaSet();
            Set<Empresametalurgica> empresametalurgicaSetNew = responsable.getEmpresametalurgicaSet();
            Set<Cliente> clienteSetOld = persistentResponsable.getClienteSet();
            Set<Cliente> clienteSetNew = responsable.getClienteSet();
            Set<Proveedor> proveedorSetOld = persistentResponsable.getProveedorSet();
            Set<Proveedor> proveedorSetNew = responsable.getProveedorSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetOld = persistentResponsable.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetNew = responsable.getProveedormantenimientomaquinaSet();
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                responsable.setDomicilio(domicilioNew);
            }
            if (tipodocumentoNew != null) {
                tipodocumentoNew = em.getReference(tipodocumentoNew.getClass(), tipodocumentoNew.getIdtipodocumento());
                responsable.setTipodocumento(tipodocumentoNew);
            }
            Set<Empresametalurgica> attachedEmpresametalurgicaSetNew = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgicaToAttach : empresametalurgicaSetNew) {
                empresametalurgicaSetNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSetNew.add(empresametalurgicaSetNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSetNew = attachedEmpresametalurgicaSetNew;
            responsable.setEmpresametalurgicaSet(empresametalurgicaSetNew);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            responsable.setClienteSet(clienteSetNew);
            Set<Proveedor> attachedProveedorSetNew = new HashSet<Proveedor>();
            for (Proveedor proveedorSetNewProveedorToAttach : proveedorSetNew) {
                proveedorSetNewProveedorToAttach = em.getReference(proveedorSetNewProveedorToAttach.getClass(), proveedorSetNewProveedorToAttach.getIdproveedor());
                attachedProveedorSetNew.add(proveedorSetNewProveedorToAttach);
            }
            proveedorSetNew = attachedProveedorSetNew;
            responsable.setProveedorSet(proveedorSetNew);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSetNew = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSetNew) {
                proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSetNew.add(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSetNew = attachedProveedormantenimientomaquinaSetNew;
            responsable.setProveedormantenimientomaquinaSet(proveedormantenimientomaquinaSetNew);
            responsable = em.merge(responsable);
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getResponsableSet().remove(responsable);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getResponsableSet().add(responsable);
                domicilioNew = em.merge(domicilioNew);
            }
            if (tipodocumentoOld != null && !tipodocumentoOld.equals(tipodocumentoNew)) {
                tipodocumentoOld.getResponsableSet().remove(responsable);
                tipodocumentoOld = em.merge(tipodocumentoOld);
            }
            if (tipodocumentoNew != null && !tipodocumentoNew.equals(tipodocumentoOld)) {
                tipodocumentoNew.getResponsableSet().add(responsable);
                tipodocumentoNew = em.merge(tipodocumentoNew);
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
            Tipodocumento tipodocumento = responsable.getTipodocumento();
            if (tipodocumento != null) {
                tipodocumento.getResponsableSet().remove(responsable);
                tipodocumento = em.merge(tipodocumento);
            }
            Set<Empresametalurgica> empresametalurgicaSet = responsable.getEmpresametalurgicaSet();
            for (Empresametalurgica empresametalurgicaSetEmpresametalurgica : empresametalurgicaSet) {
                empresametalurgicaSetEmpresametalurgica.setResponsable(null);
                empresametalurgicaSetEmpresametalurgica = em.merge(empresametalurgicaSetEmpresametalurgica);
            }
            Set<Cliente> clienteSet = responsable.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setResponsable(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Proveedor> proveedorSet = responsable.getProveedorSet();
            for (Proveedor proveedorSetProveedor : proveedorSet) {
                proveedorSetProveedor.setResponsable(null);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet = responsable.getProveedormantenimientomaquinaSet();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : proveedormantenimientomaquinaSet) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setResponsable(null);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
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
