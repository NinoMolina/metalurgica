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
        if (condicioniva.getClienteSet() == null) {
            condicioniva.setClienteSet(new HashSet<Cliente>());
        }
        if (condicioniva.getProveedorSet() == null) {
            condicioniva.setProveedorSet(new HashSet<Proveedor>());
        }
        if (condicioniva.getProveedormantenimientomaquinaSet() == null) {
            condicioniva.setProveedormantenimientomaquinaSet(new HashSet<Proveedormantenimientomaquina>());
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
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : condicioniva.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            condicioniva.setClienteSet(attachedClienteSet);
            Set<Proveedor> attachedProveedorSet = new HashSet<Proveedor>();
            for (Proveedor proveedorSetProveedorToAttach : condicioniva.getProveedorSet()) {
                proveedorSetProveedorToAttach = em.getReference(proveedorSetProveedorToAttach.getClass(), proveedorSetProveedorToAttach.getIdproveedor());
                attachedProveedorSet.add(proveedorSetProveedorToAttach);
            }
            condicioniva.setProveedorSet(attachedProveedorSet);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSet = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach : condicioniva.getProveedormantenimientomaquinaSet()) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSet.add(proveedormantenimientomaquinaSetProveedormantenimientomaquinaToAttach);
            }
            condicioniva.setProveedormantenimientomaquinaSet(attachedProveedormantenimientomaquinaSet);
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
            for (Cliente clienteSetCliente : condicioniva.getClienteSet()) {
                Condicioniva oldCondicionivaOfClienteSetCliente = clienteSetCliente.getCondicioniva();
                clienteSetCliente.setCondicioniva(condicioniva);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldCondicionivaOfClienteSetCliente != null) {
                    oldCondicionivaOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldCondicionivaOfClienteSetCliente = em.merge(oldCondicionivaOfClienteSetCliente);
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
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : condicioniva.getProveedormantenimientomaquinaSet()) {
                Condicioniva oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = proveedormantenimientomaquinaSetProveedormantenimientomaquina.getCondicioniva();
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setCondicioniva(condicioniva);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                if (oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina != null) {
                    oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina.getProveedormantenimientomaquinaSet().remove(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
                    oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(oldCondicionivaOfProveedormantenimientomaquinaSetProveedormantenimientomaquina);
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
            Set<Cliente> clienteSetOld = persistentCondicioniva.getClienteSet();
            Set<Cliente> clienteSetNew = condicioniva.getClienteSet();
            Set<Proveedor> proveedorSetOld = persistentCondicioniva.getProveedorSet();
            Set<Proveedor> proveedorSetNew = condicioniva.getProveedorSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetOld = persistentCondicioniva.getProveedormantenimientomaquinaSet();
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSetNew = condicioniva.getProveedormantenimientomaquinaSet();
            Set<Empresametalurgica> attachedEmpresametalurgicaSetNew = new HashSet<Empresametalurgica>();
            for (Empresametalurgica empresametalurgicaSetNewEmpresametalurgicaToAttach : empresametalurgicaSetNew) {
                empresametalurgicaSetNewEmpresametalurgicaToAttach = em.getReference(empresametalurgicaSetNewEmpresametalurgicaToAttach.getClass(), empresametalurgicaSetNewEmpresametalurgicaToAttach.getIdempresametalurgica());
                attachedEmpresametalurgicaSetNew.add(empresametalurgicaSetNewEmpresametalurgicaToAttach);
            }
            empresametalurgicaSetNew = attachedEmpresametalurgicaSetNew;
            condicioniva.setEmpresametalurgicaSet(empresametalurgicaSetNew);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            condicioniva.setClienteSet(clienteSetNew);
            Set<Proveedor> attachedProveedorSetNew = new HashSet<Proveedor>();
            for (Proveedor proveedorSetNewProveedorToAttach : proveedorSetNew) {
                proveedorSetNewProveedorToAttach = em.getReference(proveedorSetNewProveedorToAttach.getClass(), proveedorSetNewProveedorToAttach.getIdproveedor());
                attachedProveedorSetNew.add(proveedorSetNewProveedorToAttach);
            }
            proveedorSetNew = attachedProveedorSetNew;
            condicioniva.setProveedorSet(proveedorSetNew);
            Set<Proveedormantenimientomaquina> attachedProveedormantenimientomaquinaSetNew = new HashSet<Proveedormantenimientomaquina>();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach : proveedormantenimientomaquinaSetNew) {
                proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach = em.getReference(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getClass(), proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach.getIdproveedormantenimiento());
                attachedProveedormantenimientomaquinaSetNew.add(proveedormantenimientomaquinaSetNewProveedormantenimientomaquinaToAttach);
            }
            proveedormantenimientomaquinaSetNew = attachedProveedormantenimientomaquinaSetNew;
            condicioniva.setProveedormantenimientomaquinaSet(proveedormantenimientomaquinaSetNew);
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
            Set<Cliente> clienteSet = condicioniva.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setCondicioniva(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Proveedor> proveedorSet = condicioniva.getProveedorSet();
            for (Proveedor proveedorSetProveedor : proveedorSet) {
                proveedorSetProveedor.setCondicion(null);
                proveedorSetProveedor = em.merge(proveedorSetProveedor);
            }
            Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet = condicioniva.getProveedormantenimientomaquinaSet();
            for (Proveedormantenimientomaquina proveedormantenimientomaquinaSetProveedormantenimientomaquina : proveedormantenimientomaquinaSet) {
                proveedormantenimientomaquinaSetProveedormantenimientomaquina.setCondicioniva(null);
                proveedormantenimientomaquinaSetProveedormantenimientomaquina = em.merge(proveedormantenimientomaquinaSetProveedormantenimientomaquina);
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
