/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Tiporeclamo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Reclamoproveedor;
import java.util.HashSet;
import java.util.Set;
import entity.Reclamoempresametalurgica;
import entity.Reclamocliente;

/**
 *
 * @author Nino
 */
public class TiporeclamoJpaController {

    public TiporeclamoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiporeclamo tiporeclamo) throws PreexistingEntityException, Exception {
        if (tiporeclamo.getReclamoproveedorSet() == null) {
            tiporeclamo.setReclamoproveedorSet(new HashSet<Reclamoproveedor>());
        }
        if (tiporeclamo.getReclamoempresametalurgicaSet() == null) {
            tiporeclamo.setReclamoempresametalurgicaSet(new HashSet<Reclamoempresametalurgica>());
        }
        if (tiporeclamo.getReclamoclienteSet() == null) {
            tiporeclamo.setReclamoclienteSet(new HashSet<Reclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Reclamoproveedor> attachedReclamoproveedorSet = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedorToAttach : tiporeclamo.getReclamoproveedorSet()) {
                reclamoproveedorSetReclamoproveedorToAttach = em.getReference(reclamoproveedorSetReclamoproveedorToAttach.getClass(), reclamoproveedorSetReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSet.add(reclamoproveedorSetReclamoproveedorToAttach);
            }
            tiporeclamo.setReclamoproveedorSet(attachedReclamoproveedorSet);
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSet = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach : tiporeclamo.getReclamoempresametalurgicaSet()) {
                reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSet.add(reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach);
            }
            tiporeclamo.setReclamoempresametalurgicaSet(attachedReclamoempresametalurgicaSet);
            Set<Reclamocliente> attachedReclamoclienteSet = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSetReclamoclienteToAttach : tiporeclamo.getReclamoclienteSet()) {
                reclamoclienteSetReclamoclienteToAttach = em.getReference(reclamoclienteSetReclamoclienteToAttach.getClass(), reclamoclienteSetReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSet.add(reclamoclienteSetReclamoclienteToAttach);
            }
            tiporeclamo.setReclamoclienteSet(attachedReclamoclienteSet);
            em.persist(tiporeclamo);
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedor : tiporeclamo.getReclamoproveedorSet()) {
                Tiporeclamo oldTiporeclamoOfReclamoproveedorSetReclamoproveedor = reclamoproveedorSetReclamoproveedor.getTiporeclamo();
                reclamoproveedorSetReclamoproveedor.setTiporeclamo(tiporeclamo);
                reclamoproveedorSetReclamoproveedor = em.merge(reclamoproveedorSetReclamoproveedor);
                if (oldTiporeclamoOfReclamoproveedorSetReclamoproveedor != null) {
                    oldTiporeclamoOfReclamoproveedorSetReclamoproveedor.getReclamoproveedorSet().remove(reclamoproveedorSetReclamoproveedor);
                    oldTiporeclamoOfReclamoproveedorSetReclamoproveedor = em.merge(oldTiporeclamoOfReclamoproveedorSetReclamoproveedor);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgica : tiporeclamo.getReclamoempresametalurgicaSet()) {
                Tiporeclamo oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica = reclamoempresametalurgicaSetReclamoempresametalurgica.getTiporeclamo();
                reclamoempresametalurgicaSetReclamoempresametalurgica.setTiporeclamo(tiporeclamo);
                reclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetReclamoempresametalurgica);
                if (oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica != null) {
                    oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgicaSetReclamoempresametalurgica);
                    oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica);
                }
            }
            for (Reclamocliente reclamoclienteSetReclamocliente : tiporeclamo.getReclamoclienteSet()) {
                Tiporeclamo oldTiporeclamoOfReclamoclienteSetReclamocliente = reclamoclienteSetReclamocliente.getTiporeclamo();
                reclamoclienteSetReclamocliente.setTiporeclamo(tiporeclamo);
                reclamoclienteSetReclamocliente = em.merge(reclamoclienteSetReclamocliente);
                if (oldTiporeclamoOfReclamoclienteSetReclamocliente != null) {
                    oldTiporeclamoOfReclamoclienteSetReclamocliente.getReclamoclienteSet().remove(reclamoclienteSetReclamocliente);
                    oldTiporeclamoOfReclamoclienteSetReclamocliente = em.merge(oldTiporeclamoOfReclamoclienteSetReclamocliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiporeclamo(tiporeclamo.getIdtiporeclamo()) != null) {
                throw new PreexistingEntityException("Tiporeclamo " + tiporeclamo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiporeclamo tiporeclamo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporeclamo persistentTiporeclamo = em.find(Tiporeclamo.class, tiporeclamo.getIdtiporeclamo());
            Set<Reclamoproveedor> reclamoproveedorSetOld = persistentTiporeclamo.getReclamoproveedorSet();
            Set<Reclamoproveedor> reclamoproveedorSetNew = tiporeclamo.getReclamoproveedorSet();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSetOld = persistentTiporeclamo.getReclamoempresametalurgicaSet();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSetNew = tiporeclamo.getReclamoempresametalurgicaSet();
            Set<Reclamocliente> reclamoclienteSetOld = persistentTiporeclamo.getReclamoclienteSet();
            Set<Reclamocliente> reclamoclienteSetNew = tiporeclamo.getReclamoclienteSet();
            Set<Reclamoproveedor> attachedReclamoproveedorSetNew = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSetNewReclamoproveedorToAttach : reclamoproveedorSetNew) {
                reclamoproveedorSetNewReclamoproveedorToAttach = em.getReference(reclamoproveedorSetNewReclamoproveedorToAttach.getClass(), reclamoproveedorSetNewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSetNew.add(reclamoproveedorSetNewReclamoproveedorToAttach);
            }
            reclamoproveedorSetNew = attachedReclamoproveedorSetNew;
            tiporeclamo.setReclamoproveedorSet(reclamoproveedorSetNew);
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSetNew = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaSetNew) {
                reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSetNew.add(reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaSetNew = attachedReclamoempresametalurgicaSetNew;
            tiporeclamo.setReclamoempresametalurgicaSet(reclamoempresametalurgicaSetNew);
            Set<Reclamocliente> attachedReclamoclienteSetNew = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSetNewReclamoclienteToAttach : reclamoclienteSetNew) {
                reclamoclienteSetNewReclamoclienteToAttach = em.getReference(reclamoclienteSetNewReclamoclienteToAttach.getClass(), reclamoclienteSetNewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSetNew.add(reclamoclienteSetNewReclamoclienteToAttach);
            }
            reclamoclienteSetNew = attachedReclamoclienteSetNew;
            tiporeclamo.setReclamoclienteSet(reclamoclienteSetNew);
            tiporeclamo = em.merge(tiporeclamo);
            for (Reclamoproveedor reclamoproveedorSetOldReclamoproveedor : reclamoproveedorSetOld) {
                if (!reclamoproveedorSetNew.contains(reclamoproveedorSetOldReclamoproveedor)) {
                    reclamoproveedorSetOldReclamoproveedor.setTiporeclamo(null);
                    reclamoproveedorSetOldReclamoproveedor = em.merge(reclamoproveedorSetOldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorSetNewReclamoproveedor : reclamoproveedorSetNew) {
                if (!reclamoproveedorSetOld.contains(reclamoproveedorSetNewReclamoproveedor)) {
                    Tiporeclamo oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor = reclamoproveedorSetNewReclamoproveedor.getTiporeclamo();
                    reclamoproveedorSetNewReclamoproveedor.setTiporeclamo(tiporeclamo);
                    reclamoproveedorSetNewReclamoproveedor = em.merge(reclamoproveedorSetNewReclamoproveedor);
                    if (oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor != null && !oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor.getReclamoproveedorSet().remove(reclamoproveedorSetNewReclamoproveedor);
                        oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor = em.merge(oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor);
                    }
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetOldReclamoempresametalurgica : reclamoempresametalurgicaSetOld) {
                if (!reclamoempresametalurgicaSetNew.contains(reclamoempresametalurgicaSetOldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaSetOldReclamoempresametalurgica.setTiporeclamo(null);
                    reclamoempresametalurgicaSetOldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetOldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetNewReclamoempresametalurgica : reclamoempresametalurgicaSetNew) {
                if (!reclamoempresametalurgicaSetOld.contains(reclamoempresametalurgicaSetNewReclamoempresametalurgica)) {
                    Tiporeclamo oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica = reclamoempresametalurgicaSetNewReclamoempresametalurgica.getTiporeclamo();
                    reclamoempresametalurgicaSetNewReclamoempresametalurgica.setTiporeclamo(tiporeclamo);
                    reclamoempresametalurgicaSetNewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetNewReclamoempresametalurgica);
                    if (oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica != null && !oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgicaSetNewReclamoempresametalurgica);
                        oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica = em.merge(oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica);
                    }
                }
            }
            for (Reclamocliente reclamoclienteSetOldReclamocliente : reclamoclienteSetOld) {
                if (!reclamoclienteSetNew.contains(reclamoclienteSetOldReclamocliente)) {
                    reclamoclienteSetOldReclamocliente.setTiporeclamo(null);
                    reclamoclienteSetOldReclamocliente = em.merge(reclamoclienteSetOldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteSetNewReclamocliente : reclamoclienteSetNew) {
                if (!reclamoclienteSetOld.contains(reclamoclienteSetNewReclamocliente)) {
                    Tiporeclamo oldTiporeclamoOfReclamoclienteSetNewReclamocliente = reclamoclienteSetNewReclamocliente.getTiporeclamo();
                    reclamoclienteSetNewReclamocliente.setTiporeclamo(tiporeclamo);
                    reclamoclienteSetNewReclamocliente = em.merge(reclamoclienteSetNewReclamocliente);
                    if (oldTiporeclamoOfReclamoclienteSetNewReclamocliente != null && !oldTiporeclamoOfReclamoclienteSetNewReclamocliente.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoclienteSetNewReclamocliente.getReclamoclienteSet().remove(reclamoclienteSetNewReclamocliente);
                        oldTiporeclamoOfReclamoclienteSetNewReclamocliente = em.merge(oldTiporeclamoOfReclamoclienteSetNewReclamocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tiporeclamo.getIdtiporeclamo();
                if (findTiporeclamo(id) == null) {
                    throw new NonexistentEntityException("The tiporeclamo with id " + id + " no longer exists.");
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
            Tiporeclamo tiporeclamo;
            try {
                tiporeclamo = em.getReference(Tiporeclamo.class, id);
                tiporeclamo.getIdtiporeclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiporeclamo with id " + id + " no longer exists.", enfe);
            }
            Set<Reclamoproveedor> reclamoproveedorSet = tiporeclamo.getReclamoproveedorSet();
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedor : reclamoproveedorSet) {
                reclamoproveedorSetReclamoproveedor.setTiporeclamo(null);
                reclamoproveedorSetReclamoproveedor = em.merge(reclamoproveedorSetReclamoproveedor);
            }
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet = tiporeclamo.getReclamoempresametalurgicaSet();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgica : reclamoempresametalurgicaSet) {
                reclamoempresametalurgicaSetReclamoempresametalurgica.setTiporeclamo(null);
                reclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetReclamoempresametalurgica);
            }
            Set<Reclamocliente> reclamoclienteSet = tiporeclamo.getReclamoclienteSet();
            for (Reclamocliente reclamoclienteSetReclamocliente : reclamoclienteSet) {
                reclamoclienteSetReclamocliente.setTiporeclamo(null);
                reclamoclienteSetReclamocliente = em.merge(reclamoclienteSetReclamocliente);
            }
            em.remove(tiporeclamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiporeclamo> findTiporeclamoEntities() {
        return findTiporeclamoEntities(true, -1, -1);
    }

    public List<Tiporeclamo> findTiporeclamoEntities(int maxResults, int firstResult) {
        return findTiporeclamoEntities(false, maxResults, firstResult);
    }

    private List<Tiporeclamo> findTiporeclamoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiporeclamo.class));
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

    public Tiporeclamo findTiporeclamo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiporeclamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiporeclamoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiporeclamo> rt = cq.from(Tiporeclamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
