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
        if (reclamoproveedor.getDetallereclamoproveedorSet1() == null) {
            reclamoproveedor.setDetallereclamoproveedorSet1(new HashSet<Detallereclamoproveedor>());
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
            Compra compra1 = reclamoproveedor.getCompra1();
            if (compra1 != null) {
                compra1 = em.getReference(compra1.getClass(), compra1.getIdcompra());
                reclamoproveedor.setCompra1(compra1);
            }
            Tiporeclamo tiporeclamo = reclamoproveedor.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo = em.getReference(tiporeclamo.getClass(), tiporeclamo.getIdtiporeclamo());
                reclamoproveedor.setTiporeclamo(tiporeclamo);
            }
            Tiporeclamo tiporeclamo1 = reclamoproveedor.getTiporeclamo1();
            if (tiporeclamo1 != null) {
                tiporeclamo1 = em.getReference(tiporeclamo1.getClass(), tiporeclamo1.getIdtiporeclamo());
                reclamoproveedor.setTiporeclamo1(tiporeclamo1);
            }
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSet = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSetDetallereclamoproveedorToAttach : reclamoproveedor.getDetallereclamoproveedorSet()) {
                detallereclamoproveedorSetDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSetDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSetDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSet.add(detallereclamoproveedorSetDetallereclamoproveedorToAttach);
            }
            reclamoproveedor.setDetallereclamoproveedorSet(attachedDetallereclamoproveedorSet);
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSet1 = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSet1DetallereclamoproveedorToAttach : reclamoproveedor.getDetallereclamoproveedorSet1()) {
                detallereclamoproveedorSet1DetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSet1DetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSet1DetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSet1.add(detallereclamoproveedorSet1DetallereclamoproveedorToAttach);
            }
            reclamoproveedor.setDetallereclamoproveedorSet1(attachedDetallereclamoproveedorSet1);
            em.persist(reclamoproveedor);
            if (compra != null) {
                compra.getReclamoproveedorSet().add(reclamoproveedor);
                compra = em.merge(compra);
            }
            if (compra1 != null) {
                compra1.getReclamoproveedorSet().add(reclamoproveedor);
                compra1 = em.merge(compra1);
            }
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoproveedorSet().add(reclamoproveedor);
                tiporeclamo = em.merge(tiporeclamo);
            }
            if (tiporeclamo1 != null) {
                tiporeclamo1.getReclamoproveedorSet().add(reclamoproveedor);
                tiporeclamo1 = em.merge(tiporeclamo1);
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
            for (Detallereclamoproveedor detallereclamoproveedorSet1Detallereclamoproveedor : reclamoproveedor.getDetallereclamoproveedorSet1()) {
                Reclamoproveedor oldReclamoproveedor1OfDetallereclamoproveedorSet1Detallereclamoproveedor = detallereclamoproveedorSet1Detallereclamoproveedor.getReclamoproveedor1();
                detallereclamoproveedorSet1Detallereclamoproveedor.setReclamoproveedor1(reclamoproveedor);
                detallereclamoproveedorSet1Detallereclamoproveedor = em.merge(detallereclamoproveedorSet1Detallereclamoproveedor);
                if (oldReclamoproveedor1OfDetallereclamoproveedorSet1Detallereclamoproveedor != null) {
                    oldReclamoproveedor1OfDetallereclamoproveedorSet1Detallereclamoproveedor.getDetallereclamoproveedorSet1().remove(detallereclamoproveedorSet1Detallereclamoproveedor);
                    oldReclamoproveedor1OfDetallereclamoproveedorSet1Detallereclamoproveedor = em.merge(oldReclamoproveedor1OfDetallereclamoproveedorSet1Detallereclamoproveedor);
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
            Compra compra1Old = persistentReclamoproveedor.getCompra1();
            Compra compra1New = reclamoproveedor.getCompra1();
            Tiporeclamo tiporeclamoOld = persistentReclamoproveedor.getTiporeclamo();
            Tiporeclamo tiporeclamoNew = reclamoproveedor.getTiporeclamo();
            Tiporeclamo tiporeclamo1Old = persistentReclamoproveedor.getTiporeclamo1();
            Tiporeclamo tiporeclamo1New = reclamoproveedor.getTiporeclamo1();
            Set<Detallereclamoproveedor> detallereclamoproveedorSetOld = persistentReclamoproveedor.getDetallereclamoproveedorSet();
            Set<Detallereclamoproveedor> detallereclamoproveedorSetNew = reclamoproveedor.getDetallereclamoproveedorSet();
            Set<Detallereclamoproveedor> detallereclamoproveedorSet1Old = persistentReclamoproveedor.getDetallereclamoproveedorSet1();
            Set<Detallereclamoproveedor> detallereclamoproveedorSet1New = reclamoproveedor.getDetallereclamoproveedorSet1();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamoproveedor detallereclamoproveedorSetOldDetallereclamoproveedor : detallereclamoproveedorSetOld) {
                if (!detallereclamoproveedorSetNew.contains(detallereclamoproveedorSetOldDetallereclamoproveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoproveedor " + detallereclamoproveedorSetOldDetallereclamoproveedor + " since its reclamoproveedor field is not nullable.");
                }
            }
            for (Detallereclamoproveedor detallereclamoproveedorSet1OldDetallereclamoproveedor : detallereclamoproveedorSet1Old) {
                if (!detallereclamoproveedorSet1New.contains(detallereclamoproveedorSet1OldDetallereclamoproveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoproveedor " + detallereclamoproveedorSet1OldDetallereclamoproveedor + " since its reclamoproveedor1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (compraNew != null) {
                compraNew = em.getReference(compraNew.getClass(), compraNew.getIdcompra());
                reclamoproveedor.setCompra(compraNew);
            }
            if (compra1New != null) {
                compra1New = em.getReference(compra1New.getClass(), compra1New.getIdcompra());
                reclamoproveedor.setCompra1(compra1New);
            }
            if (tiporeclamoNew != null) {
                tiporeclamoNew = em.getReference(tiporeclamoNew.getClass(), tiporeclamoNew.getIdtiporeclamo());
                reclamoproveedor.setTiporeclamo(tiporeclamoNew);
            }
            if (tiporeclamo1New != null) {
                tiporeclamo1New = em.getReference(tiporeclamo1New.getClass(), tiporeclamo1New.getIdtiporeclamo());
                reclamoproveedor.setTiporeclamo1(tiporeclamo1New);
            }
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSetNew = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSetNewDetallereclamoproveedorToAttach : detallereclamoproveedorSetNew) {
                detallereclamoproveedorSetNewDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSetNewDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSetNewDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSetNew.add(detallereclamoproveedorSetNewDetallereclamoproveedorToAttach);
            }
            detallereclamoproveedorSetNew = attachedDetallereclamoproveedorSetNew;
            reclamoproveedor.setDetallereclamoproveedorSet(detallereclamoproveedorSetNew);
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSet1New = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach : detallereclamoproveedorSet1New) {
                detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSet1New.add(detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach);
            }
            detallereclamoproveedorSet1New = attachedDetallereclamoproveedorSet1New;
            reclamoproveedor.setDetallereclamoproveedorSet1(detallereclamoproveedorSet1New);
            reclamoproveedor = em.merge(reclamoproveedor);
            if (compraOld != null && !compraOld.equals(compraNew)) {
                compraOld.getReclamoproveedorSet().remove(reclamoproveedor);
                compraOld = em.merge(compraOld);
            }
            if (compraNew != null && !compraNew.equals(compraOld)) {
                compraNew.getReclamoproveedorSet().add(reclamoproveedor);
                compraNew = em.merge(compraNew);
            }
            if (compra1Old != null && !compra1Old.equals(compra1New)) {
                compra1Old.getReclamoproveedorSet().remove(reclamoproveedor);
                compra1Old = em.merge(compra1Old);
            }
            if (compra1New != null && !compra1New.equals(compra1Old)) {
                compra1New.getReclamoproveedorSet().add(reclamoproveedor);
                compra1New = em.merge(compra1New);
            }
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoproveedorSet().remove(reclamoproveedor);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoproveedorSet().add(reclamoproveedor);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            if (tiporeclamo1Old != null && !tiporeclamo1Old.equals(tiporeclamo1New)) {
                tiporeclamo1Old.getReclamoproveedorSet().remove(reclamoproveedor);
                tiporeclamo1Old = em.merge(tiporeclamo1Old);
            }
            if (tiporeclamo1New != null && !tiporeclamo1New.equals(tiporeclamo1Old)) {
                tiporeclamo1New.getReclamoproveedorSet().add(reclamoproveedor);
                tiporeclamo1New = em.merge(tiporeclamo1New);
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
            for (Detallereclamoproveedor detallereclamoproveedorSet1NewDetallereclamoproveedor : detallereclamoproveedorSet1New) {
                if (!detallereclamoproveedorSet1Old.contains(detallereclamoproveedorSet1NewDetallereclamoproveedor)) {
                    Reclamoproveedor oldReclamoproveedor1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor = detallereclamoproveedorSet1NewDetallereclamoproveedor.getReclamoproveedor1();
                    detallereclamoproveedorSet1NewDetallereclamoproveedor.setReclamoproveedor1(reclamoproveedor);
                    detallereclamoproveedorSet1NewDetallereclamoproveedor = em.merge(detallereclamoproveedorSet1NewDetallereclamoproveedor);
                    if (oldReclamoproveedor1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor != null && !oldReclamoproveedor1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor.equals(reclamoproveedor)) {
                        oldReclamoproveedor1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor.getDetallereclamoproveedorSet1().remove(detallereclamoproveedorSet1NewDetallereclamoproveedor);
                        oldReclamoproveedor1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor = em.merge(oldReclamoproveedor1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor);
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
            Set<Detallereclamoproveedor> detallereclamoproveedorSet1OrphanCheck = reclamoproveedor.getDetallereclamoproveedorSet1();
            for (Detallereclamoproveedor detallereclamoproveedorSet1OrphanCheckDetallereclamoproveedor : detallereclamoproveedorSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamoproveedor (" + reclamoproveedor + ") cannot be destroyed since the Detallereclamoproveedor " + detallereclamoproveedorSet1OrphanCheckDetallereclamoproveedor + " in its detallereclamoproveedorSet1 field has a non-nullable reclamoproveedor1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Compra compra = reclamoproveedor.getCompra();
            if (compra != null) {
                compra.getReclamoproveedorSet().remove(reclamoproveedor);
                compra = em.merge(compra);
            }
            Compra compra1 = reclamoproveedor.getCompra1();
            if (compra1 != null) {
                compra1.getReclamoproveedorSet().remove(reclamoproveedor);
                compra1 = em.merge(compra1);
            }
            Tiporeclamo tiporeclamo = reclamoproveedor.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoproveedorSet().remove(reclamoproveedor);
                tiporeclamo = em.merge(tiporeclamo);
            }
            Tiporeclamo tiporeclamo1 = reclamoproveedor.getTiporeclamo1();
            if (tiporeclamo1 != null) {
                tiporeclamo1.getReclamoproveedorSet().remove(reclamoproveedor);
                tiporeclamo1 = em.merge(tiporeclamo1);
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
