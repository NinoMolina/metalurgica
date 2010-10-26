/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Empresametalurgica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Condicioniva;
import entity.Domicilio;
import entity.Responsable;
import entity.Trabajotercerizado;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EmpresametalurgicaJpaController {

    public EmpresametalurgicaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresametalurgica empresametalurgica) throws PreexistingEntityException, Exception {
        if (empresametalurgica.getTrabajotercerizadoSet() == null) {
            empresametalurgica.setTrabajotercerizadoSet(new HashSet<Trabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condicioniva condicioniva = empresametalurgica.getCondicioniva();
            if (condicioniva != null) {
                condicioniva = em.getReference(condicioniva.getClass(), condicioniva.getIdcondicioniva());
                empresametalurgica.setCondicioniva(condicioniva);
            }
            Domicilio domicilio = empresametalurgica.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                empresametalurgica.setDomicilio(domicilio);
            }
            Responsable responsable = empresametalurgica.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                empresametalurgica.setResponsable(responsable);
            }
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizadoToAttach : empresametalurgica.getTrabajotercerizadoSet()) {
                trabajotercerizadoSetTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet.add(trabajotercerizadoSetTrabajotercerizadoToAttach);
            }
            empresametalurgica.setTrabajotercerizadoSet(attachedTrabajotercerizadoSet);
            em.persist(empresametalurgica);
            if (condicioniva != null) {
                condicioniva.getEmpresametalurgicaSet().add(empresametalurgica);
                condicioniva = em.merge(condicioniva);
            }
            if (domicilio != null) {
                domicilio.getEmpresametalurgicaSet().add(empresametalurgica);
                domicilio = em.merge(domicilio);
            }
            if (responsable != null) {
                responsable.getEmpresametalurgicaSet().add(empresametalurgica);
                responsable = em.merge(responsable);
            }
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : empresametalurgica.getTrabajotercerizadoSet()) {
                Empresametalurgica oldEmpresaOfTrabajotercerizadoSetTrabajotercerizado = trabajotercerizadoSetTrabajotercerizado.getEmpresa();
                trabajotercerizadoSetTrabajotercerizado.setEmpresa(empresametalurgica);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
                if (oldEmpresaOfTrabajotercerizadoSetTrabajotercerizado != null) {
                    oldEmpresaOfTrabajotercerizadoSetTrabajotercerizado.getTrabajotercerizadoSet().remove(trabajotercerizadoSetTrabajotercerizado);
                    oldEmpresaOfTrabajotercerizadoSetTrabajotercerizado = em.merge(oldEmpresaOfTrabajotercerizadoSetTrabajotercerizado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpresametalurgica(empresametalurgica.getIdempresametalurgica()) != null) {
                throw new PreexistingEntityException("Empresametalurgica " + empresametalurgica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresametalurgica empresametalurgica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresametalurgica persistentEmpresametalurgica = em.find(Empresametalurgica.class, empresametalurgica.getIdempresametalurgica());
            Condicioniva condicionivaOld = persistentEmpresametalurgica.getCondicioniva();
            Condicioniva condicionivaNew = empresametalurgica.getCondicioniva();
            Domicilio domicilioOld = persistentEmpresametalurgica.getDomicilio();
            Domicilio domicilioNew = empresametalurgica.getDomicilio();
            Responsable responsableOld = persistentEmpresametalurgica.getResponsable();
            Responsable responsableNew = empresametalurgica.getResponsable();
            Set<Trabajotercerizado> trabajotercerizadoSetOld = persistentEmpresametalurgica.getTrabajotercerizadoSet();
            Set<Trabajotercerizado> trabajotercerizadoSetNew = empresametalurgica.getTrabajotercerizadoSet();
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                empresametalurgica.setCondicioniva(condicionivaNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                empresametalurgica.setDomicilio(domicilioNew);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                empresametalurgica.setResponsable(responsableNew);
            }
            Set<Trabajotercerizado> attachedTrabajotercerizadoSetNew = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetNewTrabajotercerizadoToAttach : trabajotercerizadoSetNew) {
                trabajotercerizadoSetNewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetNewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetNewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSetNew.add(trabajotercerizadoSetNewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoSetNew = attachedTrabajotercerizadoSetNew;
            empresametalurgica.setTrabajotercerizadoSet(trabajotercerizadoSetNew);
            empresametalurgica = em.merge(empresametalurgica);
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getEmpresametalurgicaSet().remove(empresametalurgica);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getEmpresametalurgicaSet().add(empresametalurgica);
                condicionivaNew = em.merge(condicionivaNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getEmpresametalurgicaSet().remove(empresametalurgica);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getEmpresametalurgicaSet().add(empresametalurgica);
                domicilioNew = em.merge(domicilioNew);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getEmpresametalurgicaSet().remove(empresametalurgica);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getEmpresametalurgicaSet().add(empresametalurgica);
                responsableNew = em.merge(responsableNew);
            }
            for (Trabajotercerizado trabajotercerizadoSetOldTrabajotercerizado : trabajotercerizadoSetOld) {
                if (!trabajotercerizadoSetNew.contains(trabajotercerizadoSetOldTrabajotercerizado)) {
                    trabajotercerizadoSetOldTrabajotercerizado.setEmpresa(null);
                    trabajotercerizadoSetOldTrabajotercerizado = em.merge(trabajotercerizadoSetOldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSetNewTrabajotercerizado : trabajotercerizadoSetNew) {
                if (!trabajotercerizadoSetOld.contains(trabajotercerizadoSetNewTrabajotercerizado)) {
                    Empresametalurgica oldEmpresaOfTrabajotercerizadoSetNewTrabajotercerizado = trabajotercerizadoSetNewTrabajotercerizado.getEmpresa();
                    trabajotercerizadoSetNewTrabajotercerizado.setEmpresa(empresametalurgica);
                    trabajotercerizadoSetNewTrabajotercerizado = em.merge(trabajotercerizadoSetNewTrabajotercerizado);
                    if (oldEmpresaOfTrabajotercerizadoSetNewTrabajotercerizado != null && !oldEmpresaOfTrabajotercerizadoSetNewTrabajotercerizado.equals(empresametalurgica)) {
                        oldEmpresaOfTrabajotercerizadoSetNewTrabajotercerizado.getTrabajotercerizadoSet().remove(trabajotercerizadoSetNewTrabajotercerizado);
                        oldEmpresaOfTrabajotercerizadoSetNewTrabajotercerizado = em.merge(oldEmpresaOfTrabajotercerizadoSetNewTrabajotercerizado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = empresametalurgica.getIdempresametalurgica();
                if (findEmpresametalurgica(id) == null) {
                    throw new NonexistentEntityException("The empresametalurgica with id " + id + " no longer exists.");
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
            Empresametalurgica empresametalurgica;
            try {
                empresametalurgica = em.getReference(Empresametalurgica.class, id);
                empresametalurgica.getIdempresametalurgica();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresametalurgica with id " + id + " no longer exists.", enfe);
            }
            Condicioniva condicioniva = empresametalurgica.getCondicioniva();
            if (condicioniva != null) {
                condicioniva.getEmpresametalurgicaSet().remove(empresametalurgica);
                condicioniva = em.merge(condicioniva);
            }
            Domicilio domicilio = empresametalurgica.getDomicilio();
            if (domicilio != null) {
                domicilio.getEmpresametalurgicaSet().remove(empresametalurgica);
                domicilio = em.merge(domicilio);
            }
            Responsable responsable = empresametalurgica.getResponsable();
            if (responsable != null) {
                responsable.getEmpresametalurgicaSet().remove(empresametalurgica);
                responsable = em.merge(responsable);
            }
            Set<Trabajotercerizado> trabajotercerizadoSet = empresametalurgica.getTrabajotercerizadoSet();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : trabajotercerizadoSet) {
                trabajotercerizadoSetTrabajotercerizado.setEmpresa(null);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
            }
            em.remove(empresametalurgica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresametalurgica> findEmpresametalurgicaEntities() {
        return findEmpresametalurgicaEntities(true, -1, -1);
    }

    public List<Empresametalurgica> findEmpresametalurgicaEntities(int maxResults, int firstResult) {
        return findEmpresametalurgicaEntities(false, maxResults, firstResult);
    }

    private List<Empresametalurgica> findEmpresametalurgicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresametalurgica.class));
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

    public Empresametalurgica findEmpresametalurgica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresametalurgica.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresametalurgicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresametalurgica> rt = cq.from(Empresametalurgica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
