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
        if (empresametalurgica.getTrabajotercerizadoSet1() == null) {
            empresametalurgica.setTrabajotercerizadoSet1(new HashSet<Trabajotercerizado>());
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
            Condicioniva condicioniva1 = empresametalurgica.getCondicioniva1();
            if (condicioniva1 != null) {
                condicioniva1 = em.getReference(condicioniva1.getClass(), condicioniva1.getIdcondicioniva());
                empresametalurgica.setCondicioniva1(condicioniva1);
            }
            Domicilio domicilio = empresametalurgica.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                empresametalurgica.setDomicilio(domicilio);
            }
            Domicilio domicilio1 = empresametalurgica.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1 = em.getReference(domicilio1.getClass(), domicilio1.getIddomicilio());
                empresametalurgica.setDomicilio1(domicilio1);
            }
            Responsable responsable = empresametalurgica.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                empresametalurgica.setResponsable(responsable);
            }
            Responsable responsable1 = empresametalurgica.getResponsable1();
            if (responsable1 != null) {
                responsable1 = em.getReference(responsable1.getClass(), responsable1.getIdresponsable());
                empresametalurgica.setResponsable1(responsable1);
            }
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizadoToAttach : empresametalurgica.getTrabajotercerizadoSet()) {
                trabajotercerizadoSetTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet.add(trabajotercerizadoSetTrabajotercerizadoToAttach);
            }
            empresametalurgica.setTrabajotercerizadoSet(attachedTrabajotercerizadoSet);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet1 = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSet1TrabajotercerizadoToAttach : empresametalurgica.getTrabajotercerizadoSet1()) {
                trabajotercerizadoSet1TrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSet1TrabajotercerizadoToAttach.getClass(), trabajotercerizadoSet1TrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet1.add(trabajotercerizadoSet1TrabajotercerizadoToAttach);
            }
            empresametalurgica.setTrabajotercerizadoSet1(attachedTrabajotercerizadoSet1);
            em.persist(empresametalurgica);
            if (condicioniva != null) {
                condicioniva.getEmpresametalurgicaSet().add(empresametalurgica);
                condicioniva = em.merge(condicioniva);
            }
            if (condicioniva1 != null) {
                condicioniva1.getEmpresametalurgicaSet().add(empresametalurgica);
                condicioniva1 = em.merge(condicioniva1);
            }
            if (domicilio != null) {
                domicilio.getEmpresametalurgicaSet().add(empresametalurgica);
                domicilio = em.merge(domicilio);
            }
            if (domicilio1 != null) {
                domicilio1.getEmpresametalurgicaSet().add(empresametalurgica);
                domicilio1 = em.merge(domicilio1);
            }
            if (responsable != null) {
                responsable.getEmpresametalurgicaSet().add(empresametalurgica);
                responsable = em.merge(responsable);
            }
            if (responsable1 != null) {
                responsable1.getEmpresametalurgicaSet().add(empresametalurgica);
                responsable1 = em.merge(responsable1);
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
            for (Trabajotercerizado trabajotercerizadoSet1Trabajotercerizado : empresametalurgica.getTrabajotercerizadoSet1()) {
                Empresametalurgica oldEmpresa1OfTrabajotercerizadoSet1Trabajotercerizado = trabajotercerizadoSet1Trabajotercerizado.getEmpresa1();
                trabajotercerizadoSet1Trabajotercerizado.setEmpresa1(empresametalurgica);
                trabajotercerizadoSet1Trabajotercerizado = em.merge(trabajotercerizadoSet1Trabajotercerizado);
                if (oldEmpresa1OfTrabajotercerizadoSet1Trabajotercerizado != null) {
                    oldEmpresa1OfTrabajotercerizadoSet1Trabajotercerizado.getTrabajotercerizadoSet1().remove(trabajotercerizadoSet1Trabajotercerizado);
                    oldEmpresa1OfTrabajotercerizadoSet1Trabajotercerizado = em.merge(oldEmpresa1OfTrabajotercerizadoSet1Trabajotercerizado);
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
            Condicioniva condicioniva1Old = persistentEmpresametalurgica.getCondicioniva1();
            Condicioniva condicioniva1New = empresametalurgica.getCondicioniva1();
            Domicilio domicilioOld = persistentEmpresametalurgica.getDomicilio();
            Domicilio domicilioNew = empresametalurgica.getDomicilio();
            Domicilio domicilio1Old = persistentEmpresametalurgica.getDomicilio1();
            Domicilio domicilio1New = empresametalurgica.getDomicilio1();
            Responsable responsableOld = persistentEmpresametalurgica.getResponsable();
            Responsable responsableNew = empresametalurgica.getResponsable();
            Responsable responsable1Old = persistentEmpresametalurgica.getResponsable1();
            Responsable responsable1New = empresametalurgica.getResponsable1();
            Set<Trabajotercerizado> trabajotercerizadoSetOld = persistentEmpresametalurgica.getTrabajotercerizadoSet();
            Set<Trabajotercerizado> trabajotercerizadoSetNew = empresametalurgica.getTrabajotercerizadoSet();
            Set<Trabajotercerizado> trabajotercerizadoSet1Old = persistentEmpresametalurgica.getTrabajotercerizadoSet1();
            Set<Trabajotercerizado> trabajotercerizadoSet1New = empresametalurgica.getTrabajotercerizadoSet1();
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                empresametalurgica.setCondicioniva(condicionivaNew);
            }
            if (condicioniva1New != null) {
                condicioniva1New = em.getReference(condicioniva1New.getClass(), condicioniva1New.getIdcondicioniva());
                empresametalurgica.setCondicioniva1(condicioniva1New);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                empresametalurgica.setDomicilio(domicilioNew);
            }
            if (domicilio1New != null) {
                domicilio1New = em.getReference(domicilio1New.getClass(), domicilio1New.getIddomicilio());
                empresametalurgica.setDomicilio1(domicilio1New);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                empresametalurgica.setResponsable(responsableNew);
            }
            if (responsable1New != null) {
                responsable1New = em.getReference(responsable1New.getClass(), responsable1New.getIdresponsable());
                empresametalurgica.setResponsable1(responsable1New);
            }
            Set<Trabajotercerizado> attachedTrabajotercerizadoSetNew = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetNewTrabajotercerizadoToAttach : trabajotercerizadoSetNew) {
                trabajotercerizadoSetNewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetNewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetNewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSetNew.add(trabajotercerizadoSetNewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoSetNew = attachedTrabajotercerizadoSetNew;
            empresametalurgica.setTrabajotercerizadoSet(trabajotercerizadoSetNew);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet1New = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSet1NewTrabajotercerizadoToAttach : trabajotercerizadoSet1New) {
                trabajotercerizadoSet1NewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSet1NewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSet1NewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet1New.add(trabajotercerizadoSet1NewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoSet1New = attachedTrabajotercerizadoSet1New;
            empresametalurgica.setTrabajotercerizadoSet1(trabajotercerizadoSet1New);
            empresametalurgica = em.merge(empresametalurgica);
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getEmpresametalurgicaSet().remove(empresametalurgica);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getEmpresametalurgicaSet().add(empresametalurgica);
                condicionivaNew = em.merge(condicionivaNew);
            }
            if (condicioniva1Old != null && !condicioniva1Old.equals(condicioniva1New)) {
                condicioniva1Old.getEmpresametalurgicaSet().remove(empresametalurgica);
                condicioniva1Old = em.merge(condicioniva1Old);
            }
            if (condicioniva1New != null && !condicioniva1New.equals(condicioniva1Old)) {
                condicioniva1New.getEmpresametalurgicaSet().add(empresametalurgica);
                condicioniva1New = em.merge(condicioniva1New);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getEmpresametalurgicaSet().remove(empresametalurgica);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getEmpresametalurgicaSet().add(empresametalurgica);
                domicilioNew = em.merge(domicilioNew);
            }
            if (domicilio1Old != null && !domicilio1Old.equals(domicilio1New)) {
                domicilio1Old.getEmpresametalurgicaSet().remove(empresametalurgica);
                domicilio1Old = em.merge(domicilio1Old);
            }
            if (domicilio1New != null && !domicilio1New.equals(domicilio1Old)) {
                domicilio1New.getEmpresametalurgicaSet().add(empresametalurgica);
                domicilio1New = em.merge(domicilio1New);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getEmpresametalurgicaSet().remove(empresametalurgica);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getEmpresametalurgicaSet().add(empresametalurgica);
                responsableNew = em.merge(responsableNew);
            }
            if (responsable1Old != null && !responsable1Old.equals(responsable1New)) {
                responsable1Old.getEmpresametalurgicaSet().remove(empresametalurgica);
                responsable1Old = em.merge(responsable1Old);
            }
            if (responsable1New != null && !responsable1New.equals(responsable1Old)) {
                responsable1New.getEmpresametalurgicaSet().add(empresametalurgica);
                responsable1New = em.merge(responsable1New);
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
            for (Trabajotercerizado trabajotercerizadoSet1OldTrabajotercerizado : trabajotercerizadoSet1Old) {
                if (!trabajotercerizadoSet1New.contains(trabajotercerizadoSet1OldTrabajotercerizado)) {
                    trabajotercerizadoSet1OldTrabajotercerizado.setEmpresa1(null);
                    trabajotercerizadoSet1OldTrabajotercerizado = em.merge(trabajotercerizadoSet1OldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSet1NewTrabajotercerizado : trabajotercerizadoSet1New) {
                if (!trabajotercerizadoSet1Old.contains(trabajotercerizadoSet1NewTrabajotercerizado)) {
                    Empresametalurgica oldEmpresa1OfTrabajotercerizadoSet1NewTrabajotercerizado = trabajotercerizadoSet1NewTrabajotercerizado.getEmpresa1();
                    trabajotercerizadoSet1NewTrabajotercerizado.setEmpresa1(empresametalurgica);
                    trabajotercerizadoSet1NewTrabajotercerizado = em.merge(trabajotercerizadoSet1NewTrabajotercerizado);
                    if (oldEmpresa1OfTrabajotercerizadoSet1NewTrabajotercerizado != null && !oldEmpresa1OfTrabajotercerizadoSet1NewTrabajotercerizado.equals(empresametalurgica)) {
                        oldEmpresa1OfTrabajotercerizadoSet1NewTrabajotercerizado.getTrabajotercerizadoSet1().remove(trabajotercerizadoSet1NewTrabajotercerizado);
                        oldEmpresa1OfTrabajotercerizadoSet1NewTrabajotercerizado = em.merge(oldEmpresa1OfTrabajotercerizadoSet1NewTrabajotercerizado);
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
            Condicioniva condicioniva1 = empresametalurgica.getCondicioniva1();
            if (condicioniva1 != null) {
                condicioniva1.getEmpresametalurgicaSet().remove(empresametalurgica);
                condicioniva1 = em.merge(condicioniva1);
            }
            Domicilio domicilio = empresametalurgica.getDomicilio();
            if (domicilio != null) {
                domicilio.getEmpresametalurgicaSet().remove(empresametalurgica);
                domicilio = em.merge(domicilio);
            }
            Domicilio domicilio1 = empresametalurgica.getDomicilio1();
            if (domicilio1 != null) {
                domicilio1.getEmpresametalurgicaSet().remove(empresametalurgica);
                domicilio1 = em.merge(domicilio1);
            }
            Responsable responsable = empresametalurgica.getResponsable();
            if (responsable != null) {
                responsable.getEmpresametalurgicaSet().remove(empresametalurgica);
                responsable = em.merge(responsable);
            }
            Responsable responsable1 = empresametalurgica.getResponsable1();
            if (responsable1 != null) {
                responsable1.getEmpresametalurgicaSet().remove(empresametalurgica);
                responsable1 = em.merge(responsable1);
            }
            Set<Trabajotercerizado> trabajotercerizadoSet = empresametalurgica.getTrabajotercerizadoSet();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : trabajotercerizadoSet) {
                trabajotercerizadoSetTrabajotercerizado.setEmpresa(null);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
            }
            Set<Trabajotercerizado> trabajotercerizadoSet1 = empresametalurgica.getTrabajotercerizadoSet1();
            for (Trabajotercerizado trabajotercerizadoSet1Trabajotercerizado : trabajotercerizadoSet1) {
                trabajotercerizadoSet1Trabajotercerizado.setEmpresa1(null);
                trabajotercerizadoSet1Trabajotercerizado = em.merge(trabajotercerizadoSet1Trabajotercerizado);
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
