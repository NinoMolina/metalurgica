/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Reclamoempresametalurgica;
import metalsoft.datos.jpa.entity.Tiporeclamo;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.datos.jpa.entity.Detallereclamoempresametalurgica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class ReclamoempresametalurgicaJpaController {

    public ReclamoempresametalurgicaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reclamoempresametalurgica reclamoempresametalurgica) throws PreexistingEntityException, Exception {
        if (reclamoempresametalurgica.getDetallereclamoempresametalurgicaList() == null) {
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaList(new ArrayList<Detallereclamoempresametalurgica>());
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
            List<Detallereclamoempresametalurgica> attachedDetallereclamoempresametalurgicaList = new ArrayList<Detallereclamoempresametalurgica>();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaListDetallereclamoempresametalurgicaToAttach : reclamoempresametalurgica.getDetallereclamoempresametalurgicaList()) {
                detallereclamoempresametalurgicaListDetallereclamoempresametalurgicaToAttach = em.getReference(detallereclamoempresametalurgicaListDetallereclamoempresametalurgicaToAttach.getClass(), detallereclamoempresametalurgicaListDetallereclamoempresametalurgicaToAttach.getDetallereclamoempresametalurgicaPK());
                attachedDetallereclamoempresametalurgicaList.add(detallereclamoempresametalurgicaListDetallereclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaList(attachedDetallereclamoempresametalurgicaList);
            em.persist(reclamoempresametalurgica);
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoempresametalurgicaList().add(reclamoempresametalurgica);
                tiporeclamo = em.merge(tiporeclamo);
            }
            if (trabajotercerizado != null) {
                trabajotercerizado.getReclamoempresametalurgicaList().add(reclamoempresametalurgica);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaListDetallereclamoempresametalurgica : reclamoempresametalurgica.getDetallereclamoempresametalurgicaList()) {
                Reclamoempresametalurgica oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListDetallereclamoempresametalurgica = detallereclamoempresametalurgicaListDetallereclamoempresametalurgica.getReclamoempresametalurgica();
                detallereclamoempresametalurgicaListDetallereclamoempresametalurgica.setReclamoempresametalurgica(reclamoempresametalurgica);
                detallereclamoempresametalurgicaListDetallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgicaListDetallereclamoempresametalurgica);
                if (oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListDetallereclamoempresametalurgica != null) {
                    oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListDetallereclamoempresametalurgica.getDetallereclamoempresametalurgicaList().remove(detallereclamoempresametalurgicaListDetallereclamoempresametalurgica);
                    oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListDetallereclamoempresametalurgica = em.merge(oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListDetallereclamoempresametalurgica);
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
            List<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaListOld = persistentReclamoempresametalurgica.getDetallereclamoempresametalurgicaList();
            List<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaListNew = reclamoempresametalurgica.getDetallereclamoempresametalurgicaList();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaListOldDetallereclamoempresametalurgica : detallereclamoempresametalurgicaListOld) {
                if (!detallereclamoempresametalurgicaListNew.contains(detallereclamoempresametalurgicaListOldDetallereclamoempresametalurgica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoempresametalurgica " + detallereclamoempresametalurgicaListOldDetallereclamoempresametalurgica + " since its reclamoempresametalurgica field is not nullable.");
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
            List<Detallereclamoempresametalurgica> attachedDetallereclamoempresametalurgicaListNew = new ArrayList<Detallereclamoempresametalurgica>();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgicaToAttach : detallereclamoempresametalurgicaListNew) {
                detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgicaToAttach = em.getReference(detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgicaToAttach.getClass(), detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgicaToAttach.getDetallereclamoempresametalurgicaPK());
                attachedDetallereclamoempresametalurgicaListNew.add(detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgicaToAttach);
            }
            detallereclamoempresametalurgicaListNew = attachedDetallereclamoempresametalurgicaListNew;
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaList(detallereclamoempresametalurgicaListNew);
            reclamoempresametalurgica = em.merge(reclamoempresametalurgica);
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoempresametalurgicaList().remove(reclamoempresametalurgica);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoempresametalurgicaList().add(reclamoempresametalurgica);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            if (trabajotercerizadoOld != null && !trabajotercerizadoOld.equals(trabajotercerizadoNew)) {
                trabajotercerizadoOld.getReclamoempresametalurgicaList().remove(reclamoempresametalurgica);
                trabajotercerizadoOld = em.merge(trabajotercerizadoOld);
            }
            if (trabajotercerizadoNew != null && !trabajotercerizadoNew.equals(trabajotercerizadoOld)) {
                trabajotercerizadoNew.getReclamoempresametalurgicaList().add(reclamoempresametalurgica);
                trabajotercerizadoNew = em.merge(trabajotercerizadoNew);
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica : detallereclamoempresametalurgicaListNew) {
                if (!detallereclamoempresametalurgicaListOld.contains(detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica)) {
                    Reclamoempresametalurgica oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica = detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica.getReclamoempresametalurgica();
                    detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica.setReclamoempresametalurgica(reclamoempresametalurgica);
                    detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica);
                    if (oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica != null && !oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica.equals(reclamoempresametalurgica)) {
                        oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica.getDetallereclamoempresametalurgicaList().remove(detallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica);
                        oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica = em.merge(oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaListNewDetallereclamoempresametalurgica);
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
            List<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaListOrphanCheck = reclamoempresametalurgica.getDetallereclamoempresametalurgicaList();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaListOrphanCheckDetallereclamoempresametalurgica : detallereclamoempresametalurgicaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamoempresametalurgica (" + reclamoempresametalurgica + ") cannot be destroyed since the Detallereclamoempresametalurgica " + detallereclamoempresametalurgicaListOrphanCheckDetallereclamoempresametalurgica + " in its detallereclamoempresametalurgicaList field has a non-nullable reclamoempresametalurgica field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tiporeclamo tiporeclamo = reclamoempresametalurgica.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoempresametalurgicaList().remove(reclamoempresametalurgica);
                tiporeclamo = em.merge(tiporeclamo);
            }
            Trabajotercerizado trabajotercerizado = reclamoempresametalurgica.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado.getReclamoempresametalurgicaList().remove(reclamoempresametalurgica);
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
