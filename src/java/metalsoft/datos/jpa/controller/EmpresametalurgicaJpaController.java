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
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EmpresametalurgicaJpaController implements Serializable {

    public EmpresametalurgicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresametalurgica empresametalurgica) throws PreexistingEntityException, Exception {
        if (empresametalurgica.getTrabajotercerizadoList() == null) {
            empresametalurgica.setTrabajotercerizadoList(new ArrayList<Trabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsable responsable = empresametalurgica.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                empresametalurgica.setResponsable(responsable);
            }
            Domicilio domicilio = empresametalurgica.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                empresametalurgica.setDomicilio(domicilio);
            }
            Condicioniva condicioniva = empresametalurgica.getCondicioniva();
            if (condicioniva != null) {
                condicioniva = em.getReference(condicioniva.getClass(), condicioniva.getIdcondicioniva());
                empresametalurgica.setCondicioniva(condicioniva);
            }
            List<Trabajotercerizado> attachedTrabajotercerizadoList = new ArrayList<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizadoToAttach : empresametalurgica.getTrabajotercerizadoList()) {
                trabajotercerizadoListTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoListTrabajotercerizadoToAttach.getClass(), trabajotercerizadoListTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoList.add(trabajotercerizadoListTrabajotercerizadoToAttach);
            }
            empresametalurgica.setTrabajotercerizadoList(attachedTrabajotercerizadoList);
            em.persist(empresametalurgica);
            if (responsable != null) {
                responsable.getEmpresametalurgicaList().add(empresametalurgica);
                responsable = em.merge(responsable);
            }
            if (domicilio != null) {
                domicilio.getEmpresametalurgicaList().add(empresametalurgica);
                domicilio = em.merge(domicilio);
            }
            if (condicioniva != null) {
                condicioniva.getEmpresametalurgicaList().add(empresametalurgica);
                condicioniva = em.merge(condicioniva);
            }
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizado : empresametalurgica.getTrabajotercerizadoList()) {
                Empresametalurgica oldEmpresaOfTrabajotercerizadoListTrabajotercerizado = trabajotercerizadoListTrabajotercerizado.getEmpresa();
                trabajotercerizadoListTrabajotercerizado.setEmpresa(empresametalurgica);
                trabajotercerizadoListTrabajotercerizado = em.merge(trabajotercerizadoListTrabajotercerizado);
                if (oldEmpresaOfTrabajotercerizadoListTrabajotercerizado != null) {
                    oldEmpresaOfTrabajotercerizadoListTrabajotercerizado.getTrabajotercerizadoList().remove(trabajotercerizadoListTrabajotercerizado);
                    oldEmpresaOfTrabajotercerizadoListTrabajotercerizado = em.merge(oldEmpresaOfTrabajotercerizadoListTrabajotercerizado);
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
            Responsable responsableOld = persistentEmpresametalurgica.getResponsable();
            Responsable responsableNew = empresametalurgica.getResponsable();
            Domicilio domicilioOld = persistentEmpresametalurgica.getDomicilio();
            Domicilio domicilioNew = empresametalurgica.getDomicilio();
            Condicioniva condicionivaOld = persistentEmpresametalurgica.getCondicioniva();
            Condicioniva condicionivaNew = empresametalurgica.getCondicioniva();
            List<Trabajotercerizado> trabajotercerizadoListOld = persistentEmpresametalurgica.getTrabajotercerizadoList();
            List<Trabajotercerizado> trabajotercerizadoListNew = empresametalurgica.getTrabajotercerizadoList();
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                empresametalurgica.setResponsable(responsableNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                empresametalurgica.setDomicilio(domicilioNew);
            }
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                empresametalurgica.setCondicioniva(condicionivaNew);
            }
            List<Trabajotercerizado> attachedTrabajotercerizadoListNew = new ArrayList<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoListNewTrabajotercerizadoToAttach : trabajotercerizadoListNew) {
                trabajotercerizadoListNewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoListNewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoListNewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoListNew.add(trabajotercerizadoListNewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoListNew = attachedTrabajotercerizadoListNew;
            empresametalurgica.setTrabajotercerizadoList(trabajotercerizadoListNew);
            empresametalurgica = em.merge(empresametalurgica);
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getEmpresametalurgicaList().remove(empresametalurgica);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getEmpresametalurgicaList().add(empresametalurgica);
                responsableNew = em.merge(responsableNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getEmpresametalurgicaList().remove(empresametalurgica);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getEmpresametalurgicaList().add(empresametalurgica);
                domicilioNew = em.merge(domicilioNew);
            }
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getEmpresametalurgicaList().remove(empresametalurgica);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getEmpresametalurgicaList().add(empresametalurgica);
                condicionivaNew = em.merge(condicionivaNew);
            }
            for (Trabajotercerizado trabajotercerizadoListOldTrabajotercerizado : trabajotercerizadoListOld) {
                if (!trabajotercerizadoListNew.contains(trabajotercerizadoListOldTrabajotercerizado)) {
                    trabajotercerizadoListOldTrabajotercerizado.setEmpresa(null);
                    trabajotercerizadoListOldTrabajotercerizado = em.merge(trabajotercerizadoListOldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoListNewTrabajotercerizado : trabajotercerizadoListNew) {
                if (!trabajotercerizadoListOld.contains(trabajotercerizadoListNewTrabajotercerizado)) {
                    Empresametalurgica oldEmpresaOfTrabajotercerizadoListNewTrabajotercerizado = trabajotercerizadoListNewTrabajotercerizado.getEmpresa();
                    trabajotercerizadoListNewTrabajotercerizado.setEmpresa(empresametalurgica);
                    trabajotercerizadoListNewTrabajotercerizado = em.merge(trabajotercerizadoListNewTrabajotercerizado);
                    if (oldEmpresaOfTrabajotercerizadoListNewTrabajotercerizado != null && !oldEmpresaOfTrabajotercerizadoListNewTrabajotercerizado.equals(empresametalurgica)) {
                        oldEmpresaOfTrabajotercerizadoListNewTrabajotercerizado.getTrabajotercerizadoList().remove(trabajotercerizadoListNewTrabajotercerizado);
                        oldEmpresaOfTrabajotercerizadoListNewTrabajotercerizado = em.merge(oldEmpresaOfTrabajotercerizadoListNewTrabajotercerizado);
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
            Responsable responsable = empresametalurgica.getResponsable();
            if (responsable != null) {
                responsable.getEmpresametalurgicaList().remove(empresametalurgica);
                responsable = em.merge(responsable);
            }
            Domicilio domicilio = empresametalurgica.getDomicilio();
            if (domicilio != null) {
                domicilio.getEmpresametalurgicaList().remove(empresametalurgica);
                domicilio = em.merge(domicilio);
            }
            Condicioniva condicioniva = empresametalurgica.getCondicioniva();
            if (condicioniva != null) {
                condicioniva.getEmpresametalurgicaList().remove(empresametalurgica);
                condicioniva = em.merge(condicioniva);
            }
            List<Trabajotercerizado> trabajotercerizadoList = empresametalurgica.getTrabajotercerizadoList();
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizado : trabajotercerizadoList) {
                trabajotercerizadoListTrabajotercerizado.setEmpresa(null);
                trabajotercerizadoListTrabajotercerizado = em.merge(trabajotercerizadoListTrabajotercerizado);
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
