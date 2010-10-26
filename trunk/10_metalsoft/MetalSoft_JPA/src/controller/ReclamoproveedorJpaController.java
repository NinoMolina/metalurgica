/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Reclamoproveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Compra;
import entity.Tiporeclamo;
import entity.Detallereclamoproveedor;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ReclamoproveedorJpaController {

    public ReclamoproveedorJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reclamoproveedor reclamoproveedor) throws PreexistingEntityException, Exception {
        if (reclamoproveedor.getDetallereclamoproveedorSet() == null) {
            reclamoproveedor.setDetallereclamoproveedorSet(new HashSet<Detallereclamoproveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra compra = reclamoproveedor.getCompra();
            if (compra != null) {
                compra = em.getReference(compra.getClass(), compra.getIdcompra());
                reclamoproveedor.setCompra(compra);
            }
            Tiporeclamo tiporeclamo = reclamoproveedor.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo = em.getReference(tiporeclamo.getClass(), tiporeclamo.getIdtiporeclamo());
                reclamoproveedor.setTiporeclamo(tiporeclamo);
            }
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSet = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSetDetallereclamoproveedorToAttach : reclamoproveedor.getDetallereclamoproveedorSet()) {
                detallereclamoproveedorSetDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSetDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSetDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSet.add(detallereclamoproveedorSetDetallereclamoproveedorToAttach);
            }
            reclamoproveedor.setDetallereclamoproveedorSet(attachedDetallereclamoproveedorSet);
            em.persist(reclamoproveedor);
            if (compra != null) {
                compra.getReclamoproveedorSet().add(reclamoproveedor);
                compra = em.merge(compra);
            }
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoproveedorSet().add(reclamoproveedor);
                tiporeclamo = em.merge(tiporeclamo);
            }
            for (Detallereclamoproveedor detallereclamoproveedorSetDetallereclamoproveedor : reclamoproveedor.getDetallereclamoproveedorSet()) {
                Reclamoproveedor oldReclamoproveedorOfDetallereclamoproveedorSetDetallereclamoproveedor = detallereclamoproveedorSetDetallereclamoproveedor.getReclamoproveedor();
                detallereclamoproveedorSetDetallereclamoproveedor.setReclamoproveedor(reclamoproveedor);
                detallereclamoproveedorSetDetallereclamoproveedor = em.merge(detallereclamoproveedorSetDetallereclamoproveedor);
                if (oldReclamoproveedorOfDetallereclamoproveedorSetDetallereclamoproveedor != null) {
                    oldReclamoproveedorOfDetallereclamoproveedorSetDetallereclamoproveedor.getDetallereclamoproveedorSet().remove(detallereclamoproveedorSetDetallereclamoproveedor);
                    oldReclamoproveedorOfDetallereclamoproveedorSetDetallereclamoproveedor = em.merge(oldReclamoproveedorOfDetallereclamoproveedorSetDetallereclamoproveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReclamoproveedor(reclamoproveedor.getIdreclamo()) != null) {
                throw new PreexistingEntityException("Reclamoproveedor " + reclamoproveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reclamoproveedor reclamoproveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamoproveedor persistentReclamoproveedor = em.find(Reclamoproveedor.class, reclamoproveedor.getIdreclamo());
            Compra compraOld = persistentReclamoproveedor.getCompra();
            Compra compraNew = reclamoproveedor.getCompra();
            Tiporeclamo tiporeclamoOld = persistentReclamoproveedor.getTiporeclamo();
            Tiporeclamo tiporeclamoNew = reclamoproveedor.getTiporeclamo();
            Set<Detallereclamoproveedor> detallereclamoproveedorSetOld = persistentReclamoproveedor.getDetallereclamoproveedorSet();
            Set<Detallereclamoproveedor> detallereclamoproveedorSetNew = reclamoproveedor.getDetallereclamoproveedorSet();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamoproveedor detallereclamoproveedorSetOldDetallereclamoproveedor : detallereclamoproveedorSetOld) {
                if (!detallereclamoproveedorSetNew.contains(detallereclamoproveedorSetOldDetallereclamoproveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoproveedor " + detallereclamoproveedorSetOldDetallereclamoproveedor + " since its reclamoproveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (compraNew != null) {
                compraNew = em.getReference(compraNew.getClass(), compraNew.getIdcompra());
                reclamoproveedor.setCompra(compraNew);
            }
            if (tiporeclamoNew != null) {
                tiporeclamoNew = em.getReference(tiporeclamoNew.getClass(), tiporeclamoNew.getIdtiporeclamo());
                reclamoproveedor.setTiporeclamo(tiporeclamoNew);
            }
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSetNew = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSetNewDetallereclamoproveedorToAttach : detallereclamoproveedorSetNew) {
                detallereclamoproveedorSetNewDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSetNewDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSetNewDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSetNew.add(detallereclamoproveedorSetNewDetallereclamoproveedorToAttach);
            }
            detallereclamoproveedorSetNew = attachedDetallereclamoproveedorSetNew;
            reclamoproveedor.setDetallereclamoproveedorSet(detallereclamoproveedorSetNew);
            reclamoproveedor = em.merge(reclamoproveedor);
            if (compraOld != null && !compraOld.equals(compraNew)) {
                compraOld.getReclamoproveedorSet().remove(reclamoproveedor);
                compraOld = em.merge(compraOld);
            }
            if (compraNew != null && !compraNew.equals(compraOld)) {
                compraNew.getReclamoproveedorSet().add(reclamoproveedor);
                compraNew = em.merge(compraNew);
            }
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoproveedorSet().remove(reclamoproveedor);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoproveedorSet().add(reclamoproveedor);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            for (Detallereclamoproveedor detallereclamoproveedorSetNewDetallereclamoproveedor : detallereclamoproveedorSetNew) {
                if (!detallereclamoproveedorSetOld.contains(detallereclamoproveedorSetNewDetallereclamoproveedor)) {
                    Reclamoproveedor oldReclamoproveedorOfDetallereclamoproveedorSetNewDetallereclamoproveedor = detallereclamoproveedorSetNewDetallereclamoproveedor.getReclamoproveedor();
                    detallereclamoproveedorSetNewDetallereclamoproveedor.setReclamoproveedor(reclamoproveedor);
                    detallereclamoproveedorSetNewDetallereclamoproveedor = em.merge(detallereclamoproveedorSetNewDetallereclamoproveedor);
                    if (oldReclamoproveedorOfDetallereclamoproveedorSetNewDetallereclamoproveedor != null && !oldReclamoproveedorOfDetallereclamoproveedorSetNewDetallereclamoproveedor.equals(reclamoproveedor)) {
                        oldReclamoproveedorOfDetallereclamoproveedorSetNewDetallereclamoproveedor.getDetallereclamoproveedorSet().remove(detallereclamoproveedorSetNewDetallereclamoproveedor);
                        oldReclamoproveedorOfDetallereclamoproveedorSetNewDetallereclamoproveedor = em.merge(oldReclamoproveedorOfDetallereclamoproveedorSetNewDetallereclamoproveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reclamoproveedor.getIdreclamo();
                if (findReclamoproveedor(id) == null) {
                    throw new NonexistentEntityException("The reclamoproveedor with id " + id + " no longer exists.");
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
            Reclamoproveedor reclamoproveedor;
            try {
                reclamoproveedor = em.getReference(Reclamoproveedor.class, id);
                reclamoproveedor.getIdreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reclamoproveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallereclamoproveedor> detallereclamoproveedorSetOrphanCheck = reclamoproveedor.getDetallereclamoproveedorSet();
            for (Detallereclamoproveedor detallereclamoproveedorSetOrphanCheckDetallereclamoproveedor : detallereclamoproveedorSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamoproveedor (" + reclamoproveedor + ") cannot be destroyed since the Detallereclamoproveedor " + detallereclamoproveedorSetOrphanCheckDetallereclamoproveedor + " in its detallereclamoproveedorSet field has a non-nullable reclamoproveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Compra compra = reclamoproveedor.getCompra();
            if (compra != null) {
                compra.getReclamoproveedorSet().remove(reclamoproveedor);
                compra = em.merge(compra);
            }
            Tiporeclamo tiporeclamo = reclamoproveedor.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoproveedorSet().remove(reclamoproveedor);
                tiporeclamo = em.merge(tiporeclamo);
            }
            em.remove(reclamoproveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reclamoproveedor> findReclamoproveedorEntities() {
        return findReclamoproveedorEntities(true, -1, -1);
    }

    public List<Reclamoproveedor> findReclamoproveedorEntities(int maxResults, int firstResult) {
        return findReclamoproveedorEntities(false, maxResults, firstResult);
    }

    private List<Reclamoproveedor> findReclamoproveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reclamoproveedor.class));
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

    public Reclamoproveedor findReclamoproveedor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reclamoproveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getReclamoproveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reclamoproveedor> rt = cq.from(Reclamoproveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
