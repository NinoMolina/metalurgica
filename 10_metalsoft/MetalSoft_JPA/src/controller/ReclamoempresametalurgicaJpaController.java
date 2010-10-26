/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Reclamoempresametalurgica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Tiporeclamo;
import entity.Trabajotercerizado;
import entity.Detallereclamoempresametalurgica;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ReclamoempresametalurgicaJpaController {

    public ReclamoempresametalurgicaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reclamoempresametalurgica reclamoempresametalurgica) throws PreexistingEntityException, Exception {
        if (reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet() == null) {
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet(new HashSet<Detallereclamoempresametalurgica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporeclamo tiporeclamo = reclamoempresametalurgica.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo = em.getReference(tiporeclamo.getClass(), tiporeclamo.getIdtiporeclamo());
                reclamoempresametalurgica.setTiporeclamo(tiporeclamo);
            }
            Trabajotercerizado trabajotercerizado = reclamoempresametalurgica.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado = em.getReference(trabajotercerizado.getClass(), trabajotercerizado.getIdtrabajo());
                reclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
            }
            Set<Detallereclamoempresametalurgica> attachedDetallereclamoempresametalurgicaSet = new HashSet<Detallereclamoempresametalurgica>();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach : reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet()) {
                detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach = em.getReference(detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach.getClass(), detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach.getDetallereclamoempresametalurgicaPK());
                attachedDetallereclamoempresametalurgicaSet.add(detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet(attachedDetallereclamoempresametalurgicaSet);
            em.persist(reclamoempresametalurgica);
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                tiporeclamo = em.merge(tiporeclamo);
            }
            if (trabajotercerizado != null) {
                trabajotercerizado.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica : reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet()) {
                Reclamoempresametalurgica oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica = detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica.getReclamoempresametalurgica();
                detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica.setReclamoempresametalurgica(reclamoempresametalurgica);
                detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica);
                if (oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica != null) {
                    oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica.getDetallereclamoempresametalurgicaSet().remove(detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica);
                    oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica = em.merge(oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReclamoempresametalurgica(reclamoempresametalurgica.getIdreclamo()) != null) {
                throw new PreexistingEntityException("Reclamoempresametalurgica " + reclamoempresametalurgica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reclamoempresametalurgica reclamoempresametalurgica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamoempresametalurgica persistentReclamoempresametalurgica = em.find(Reclamoempresametalurgica.class, reclamoempresametalurgica.getIdreclamo());
            Tiporeclamo tiporeclamoOld = persistentReclamoempresametalurgica.getTiporeclamo();
            Tiporeclamo tiporeclamoNew = reclamoempresametalurgica.getTiporeclamo();
            Trabajotercerizado trabajotercerizadoOld = persistentReclamoempresametalurgica.getTrabajotercerizado();
            Trabajotercerizado trabajotercerizadoNew = reclamoempresametalurgica.getTrabajotercerizado();
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSetOld = persistentReclamoempresametalurgica.getDetallereclamoempresametalurgicaSet();
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSetNew = reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetOldDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSetOld) {
                if (!detallereclamoempresametalurgicaSetNew.contains(detallereclamoempresametalurgicaSetOldDetallereclamoempresametalurgica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoempresametalurgica " + detallereclamoempresametalurgicaSetOldDetallereclamoempresametalurgica + " since its reclamoempresametalurgica field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tiporeclamoNew != null) {
                tiporeclamoNew = em.getReference(tiporeclamoNew.getClass(), tiporeclamoNew.getIdtiporeclamo());
                reclamoempresametalurgica.setTiporeclamo(tiporeclamoNew);
            }
            if (trabajotercerizadoNew != null) {
                trabajotercerizadoNew = em.getReference(trabajotercerizadoNew.getClass(), trabajotercerizadoNew.getIdtrabajo());
                reclamoempresametalurgica.setTrabajotercerizado(trabajotercerizadoNew);
            }
            Set<Detallereclamoempresametalurgica> attachedDetallereclamoempresametalurgicaSetNew = new HashSet<Detallereclamoempresametalurgica>();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach : detallereclamoempresametalurgicaSetNew) {
                detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach = em.getReference(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach.getClass(), detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach.getDetallereclamoempresametalurgicaPK());
                attachedDetallereclamoempresametalurgicaSetNew.add(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach);
            }
            detallereclamoempresametalurgicaSetNew = attachedDetallereclamoempresametalurgicaSetNew;
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet(detallereclamoempresametalurgicaSetNew);
            reclamoempresametalurgica = em.merge(reclamoempresametalurgica);
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            if (trabajotercerizadoOld != null && !trabajotercerizadoOld.equals(trabajotercerizadoNew)) {
                trabajotercerizadoOld.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                trabajotercerizadoOld = em.merge(trabajotercerizadoOld);
            }
            if (trabajotercerizadoNew != null && !trabajotercerizadoNew.equals(trabajotercerizadoOld)) {
                trabajotercerizadoNew.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                trabajotercerizadoNew = em.merge(trabajotercerizadoNew);
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSetNew) {
                if (!detallereclamoempresametalurgicaSetOld.contains(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica)) {
                    Reclamoempresametalurgica oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica = detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica.getReclamoempresametalurgica();
                    detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica.setReclamoempresametalurgica(reclamoempresametalurgica);
                    detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica);
                    if (oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica != null && !oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica.equals(reclamoempresametalurgica)) {
                        oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica.getDetallereclamoempresametalurgicaSet().remove(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica);
                        oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica = em.merge(oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reclamoempresametalurgica.getIdreclamo();
                if (findReclamoempresametalurgica(id) == null) {
                    throw new NonexistentEntityException("The reclamoempresametalurgica with id " + id + " no longer exists.");
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
            Reclamoempresametalurgica reclamoempresametalurgica;
            try {
                reclamoempresametalurgica = em.getReference(Reclamoempresametalurgica.class, id);
                reclamoempresametalurgica.getIdreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reclamoempresametalurgica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSetOrphanCheck = reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetOrphanCheckDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamoempresametalurgica (" + reclamoempresametalurgica + ") cannot be destroyed since the Detallereclamoempresametalurgica " + detallereclamoempresametalurgicaSetOrphanCheckDetallereclamoempresametalurgica + " in its detallereclamoempresametalurgicaSet field has a non-nullable reclamoempresametalurgica field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tiporeclamo tiporeclamo = reclamoempresametalurgica.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                tiporeclamo = em.merge(tiporeclamo);
            }
            Trabajotercerizado trabajotercerizado = reclamoempresametalurgica.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            em.remove(reclamoempresametalurgica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reclamoempresametalurgica> findReclamoempresametalurgicaEntities() {
        return findReclamoempresametalurgicaEntities(true, -1, -1);
    }

    public List<Reclamoempresametalurgica> findReclamoempresametalurgicaEntities(int maxResults, int firstResult) {
        return findReclamoempresametalurgicaEntities(false, maxResults, firstResult);
    }

    private List<Reclamoempresametalurgica> findReclamoempresametalurgicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reclamoempresametalurgica.class));
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

    public Reclamoempresametalurgica findReclamoempresametalurgica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reclamoempresametalurgica.class, id);
        } finally {
            em.close();
        }
    }

    public int getReclamoempresametalurgicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reclamoempresametalurgica> rt = cq.from(Reclamoempresametalurgica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
