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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Reclamocliente;
import metalsoft.datos.jpa.entity.Tiporeclamo;
import metalsoft.datos.jpa.entity.Estadoreclamo;
import metalsoft.datos.jpa.entity.Cliente;
import metalsoft.datos.jpa.entity.Detallereclamocliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class ReclamoclienteJpaController implements Serializable {

    public ReclamoclienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reclamocliente reclamocliente) throws PreexistingEntityException, Exception {
        if (reclamocliente.getDetallereclamoclienteList() == null) {
            reclamocliente.setDetallereclamoclienteList(new ArrayList<Detallereclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporeclamo tiporeclamo = reclamocliente.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo = em.getReference(tiporeclamo.getClass(), tiporeclamo.getIdtiporeclamo());
                reclamocliente.setTiporeclamo(tiporeclamo);
            }
            Estadoreclamo idestadoreclamo = reclamocliente.getIdestadoreclamo();
            if (idestadoreclamo != null) {
                idestadoreclamo = em.getReference(idestadoreclamo.getClass(), idestadoreclamo.getIdestadoreclamo());
                reclamocliente.setIdestadoreclamo(idestadoreclamo);
            }
            Cliente cliente = reclamocliente.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdcliente());
                reclamocliente.setCliente(cliente);
            }
            List<Detallereclamocliente> attachedDetallereclamoclienteList = new ArrayList<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteListDetallereclamoclienteToAttach : reclamocliente.getDetallereclamoclienteList()) {
                detallereclamoclienteListDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteListDetallereclamoclienteToAttach.getClass(), detallereclamoclienteListDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteList.add(detallereclamoclienteListDetallereclamoclienteToAttach);
            }
            reclamocliente.setDetallereclamoclienteList(attachedDetallereclamoclienteList);
            em.persist(reclamocliente);
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoclienteList().add(reclamocliente);
                tiporeclamo = em.merge(tiporeclamo);
            }
            if (idestadoreclamo != null) {
                idestadoreclamo.getReclamoclienteList().add(reclamocliente);
                idestadoreclamo = em.merge(idestadoreclamo);
            }
            if (cliente != null) {
                cliente.getReclamoclienteList().add(reclamocliente);
                cliente = em.merge(cliente);
            }
            for (Detallereclamocliente detallereclamoclienteListDetallereclamocliente : reclamocliente.getDetallereclamoclienteList()) {
                Reclamocliente oldReclamoclienteOfDetallereclamoclienteListDetallereclamocliente = detallereclamoclienteListDetallereclamocliente.getReclamocliente();
                detallereclamoclienteListDetallereclamocliente.setReclamocliente(reclamocliente);
                detallereclamoclienteListDetallereclamocliente = em.merge(detallereclamoclienteListDetallereclamocliente);
                if (oldReclamoclienteOfDetallereclamoclienteListDetallereclamocliente != null) {
                    oldReclamoclienteOfDetallereclamoclienteListDetallereclamocliente.getDetallereclamoclienteList().remove(detallereclamoclienteListDetallereclamocliente);
                    oldReclamoclienteOfDetallereclamoclienteListDetallereclamocliente = em.merge(oldReclamoclienteOfDetallereclamoclienteListDetallereclamocliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReclamocliente(reclamocliente.getIdreclamo()) != null) {
                throw new PreexistingEntityException("Reclamocliente " + reclamocliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reclamocliente reclamocliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamocliente persistentReclamocliente = em.find(Reclamocliente.class, reclamocliente.getIdreclamo());
            Tiporeclamo tiporeclamoOld = persistentReclamocliente.getTiporeclamo();
            Tiporeclamo tiporeclamoNew = reclamocliente.getTiporeclamo();
            Estadoreclamo idestadoreclamoOld = persistentReclamocliente.getIdestadoreclamo();
            Estadoreclamo idestadoreclamoNew = reclamocliente.getIdestadoreclamo();
            Cliente clienteOld = persistentReclamocliente.getCliente();
            Cliente clienteNew = reclamocliente.getCliente();
            List<Detallereclamocliente> detallereclamoclienteListOld = persistentReclamocliente.getDetallereclamoclienteList();
            List<Detallereclamocliente> detallereclamoclienteListNew = reclamocliente.getDetallereclamoclienteList();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamocliente detallereclamoclienteListOldDetallereclamocliente : detallereclamoclienteListOld) {
                if (!detallereclamoclienteListNew.contains(detallereclamoclienteListOldDetallereclamocliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamocliente " + detallereclamoclienteListOldDetallereclamocliente + " since its reclamocliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tiporeclamoNew != null) {
                tiporeclamoNew = em.getReference(tiporeclamoNew.getClass(), tiporeclamoNew.getIdtiporeclamo());
                reclamocliente.setTiporeclamo(tiporeclamoNew);
            }
            if (idestadoreclamoNew != null) {
                idestadoreclamoNew = em.getReference(idestadoreclamoNew.getClass(), idestadoreclamoNew.getIdestadoreclamo());
                reclamocliente.setIdestadoreclamo(idestadoreclamoNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdcliente());
                reclamocliente.setCliente(clienteNew);
            }
            List<Detallereclamocliente> attachedDetallereclamoclienteListNew = new ArrayList<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteListNewDetallereclamoclienteToAttach : detallereclamoclienteListNew) {
                detallereclamoclienteListNewDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteListNewDetallereclamoclienteToAttach.getClass(), detallereclamoclienteListNewDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteListNew.add(detallereclamoclienteListNewDetallereclamoclienteToAttach);
            }
            detallereclamoclienteListNew = attachedDetallereclamoclienteListNew;
            reclamocliente.setDetallereclamoclienteList(detallereclamoclienteListNew);
            reclamocliente = em.merge(reclamocliente);
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoclienteList().remove(reclamocliente);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoclienteList().add(reclamocliente);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            if (idestadoreclamoOld != null && !idestadoreclamoOld.equals(idestadoreclamoNew)) {
                idestadoreclamoOld.getReclamoclienteList().remove(reclamocliente);
                idestadoreclamoOld = em.merge(idestadoreclamoOld);
            }
            if (idestadoreclamoNew != null && !idestadoreclamoNew.equals(idestadoreclamoOld)) {
                idestadoreclamoNew.getReclamoclienteList().add(reclamocliente);
                idestadoreclamoNew = em.merge(idestadoreclamoNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getReclamoclienteList().remove(reclamocliente);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getReclamoclienteList().add(reclamocliente);
                clienteNew = em.merge(clienteNew);
            }
            for (Detallereclamocliente detallereclamoclienteListNewDetallereclamocliente : detallereclamoclienteListNew) {
                if (!detallereclamoclienteListOld.contains(detallereclamoclienteListNewDetallereclamocliente)) {
                    Reclamocliente oldReclamoclienteOfDetallereclamoclienteListNewDetallereclamocliente = detallereclamoclienteListNewDetallereclamocliente.getReclamocliente();
                    detallereclamoclienteListNewDetallereclamocliente.setReclamocliente(reclamocliente);
                    detallereclamoclienteListNewDetallereclamocliente = em.merge(detallereclamoclienteListNewDetallereclamocliente);
                    if (oldReclamoclienteOfDetallereclamoclienteListNewDetallereclamocliente != null && !oldReclamoclienteOfDetallereclamoclienteListNewDetallereclamocliente.equals(reclamocliente)) {
                        oldReclamoclienteOfDetallereclamoclienteListNewDetallereclamocliente.getDetallereclamoclienteList().remove(detallereclamoclienteListNewDetallereclamocliente);
                        oldReclamoclienteOfDetallereclamoclienteListNewDetallereclamocliente = em.merge(oldReclamoclienteOfDetallereclamoclienteListNewDetallereclamocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reclamocliente.getIdreclamo();
                if (findReclamocliente(id) == null) {
                    throw new NonexistentEntityException("The reclamocliente with id " + id + " no longer exists.");
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
            Reclamocliente reclamocliente;
            try {
                reclamocliente = em.getReference(Reclamocliente.class, id);
                reclamocliente.getIdreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reclamocliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallereclamocliente> detallereclamoclienteListOrphanCheck = reclamocliente.getDetallereclamoclienteList();
            for (Detallereclamocliente detallereclamoclienteListOrphanCheckDetallereclamocliente : detallereclamoclienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamocliente (" + reclamocliente + ") cannot be destroyed since the Detallereclamocliente " + detallereclamoclienteListOrphanCheckDetallereclamocliente + " in its detallereclamoclienteList field has a non-nullable reclamocliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tiporeclamo tiporeclamo = reclamocliente.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoclienteList().remove(reclamocliente);
                tiporeclamo = em.merge(tiporeclamo);
            }
            Estadoreclamo idestadoreclamo = reclamocliente.getIdestadoreclamo();
            if (idestadoreclamo != null) {
                idestadoreclamo.getReclamoclienteList().remove(reclamocliente);
                idestadoreclamo = em.merge(idestadoreclamo);
            }
            Cliente cliente = reclamocliente.getCliente();
            if (cliente != null) {
                cliente.getReclamoclienteList().remove(reclamocliente);
                cliente = em.merge(cliente);
            }
            em.remove(reclamocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reclamocliente> findReclamoclienteEntities() {
        return findReclamoclienteEntities(true, -1, -1);
    }

    public List<Reclamocliente> findReclamoclienteEntities(int maxResults, int firstResult) {
        return findReclamoclienteEntities(false, maxResults, firstResult);
    }

    private List<Reclamocliente> findReclamoclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reclamocliente.class));
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

    public Reclamocliente findReclamocliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reclamocliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getReclamoclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reclamocliente> rt = cq.from(Reclamocliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
