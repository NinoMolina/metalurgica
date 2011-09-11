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
import metalsoft.datos.jpa.entity.Barrio;
import metalsoft.datos.jpa.entity.Localidad;
import metalsoft.datos.jpa.entity.Domicilio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class BarrioJpaController implements Serializable {

    public BarrioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Barrio barrio) throws PreexistingEntityException, Exception {
        if (barrio.getDomicilioList() == null) {
            barrio.setDomicilioList(new ArrayList<Domicilio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidad localidad = barrio.getLocalidad();
            if (localidad != null) {
                localidad = em.getReference(localidad.getClass(), localidad.getIdlocalidad());
                barrio.setLocalidad(localidad);
            }
            List<Domicilio> attachedDomicilioList = new ArrayList<Domicilio>();
            for (Domicilio domicilioListDomicilioToAttach : barrio.getDomicilioList()) {
                domicilioListDomicilioToAttach = em.getReference(domicilioListDomicilioToAttach.getClass(), domicilioListDomicilioToAttach.getIddomicilio());
                attachedDomicilioList.add(domicilioListDomicilioToAttach);
            }
            barrio.setDomicilioList(attachedDomicilioList);
            em.persist(barrio);
            if (localidad != null) {
                localidad.getBarrioList().add(barrio);
                localidad = em.merge(localidad);
            }
            for (Domicilio domicilioListDomicilio : barrio.getDomicilioList()) {
                Barrio oldBarrioOfDomicilioListDomicilio = domicilioListDomicilio.getBarrio();
                domicilioListDomicilio.setBarrio(barrio);
                domicilioListDomicilio = em.merge(domicilioListDomicilio);
                if (oldBarrioOfDomicilioListDomicilio != null) {
                    oldBarrioOfDomicilioListDomicilio.getDomicilioList().remove(domicilioListDomicilio);
                    oldBarrioOfDomicilioListDomicilio = em.merge(oldBarrioOfDomicilioListDomicilio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBarrio(barrio.getIdbarrio()) != null) {
                throw new PreexistingEntityException("Barrio " + barrio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Barrio barrio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio persistentBarrio = em.find(Barrio.class, barrio.getIdbarrio());
            Localidad localidadOld = persistentBarrio.getLocalidad();
            Localidad localidadNew = barrio.getLocalidad();
            List<Domicilio> domicilioListOld = persistentBarrio.getDomicilioList();
            List<Domicilio> domicilioListNew = barrio.getDomicilioList();
            if (localidadNew != null) {
                localidadNew = em.getReference(localidadNew.getClass(), localidadNew.getIdlocalidad());
                barrio.setLocalidad(localidadNew);
            }
            List<Domicilio> attachedDomicilioListNew = new ArrayList<Domicilio>();
            for (Domicilio domicilioListNewDomicilioToAttach : domicilioListNew) {
                domicilioListNewDomicilioToAttach = em.getReference(domicilioListNewDomicilioToAttach.getClass(), domicilioListNewDomicilioToAttach.getIddomicilio());
                attachedDomicilioListNew.add(domicilioListNewDomicilioToAttach);
            }
            domicilioListNew = attachedDomicilioListNew;
            barrio.setDomicilioList(domicilioListNew);
            barrio = em.merge(barrio);
            if (localidadOld != null && !localidadOld.equals(localidadNew)) {
                localidadOld.getBarrioList().remove(barrio);
                localidadOld = em.merge(localidadOld);
            }
            if (localidadNew != null && !localidadNew.equals(localidadOld)) {
                localidadNew.getBarrioList().add(barrio);
                localidadNew = em.merge(localidadNew);
            }
            for (Domicilio domicilioListOldDomicilio : domicilioListOld) {
                if (!domicilioListNew.contains(domicilioListOldDomicilio)) {
                    domicilioListOldDomicilio.setBarrio(null);
                    domicilioListOldDomicilio = em.merge(domicilioListOldDomicilio);
                }
            }
            for (Domicilio domicilioListNewDomicilio : domicilioListNew) {
                if (!domicilioListOld.contains(domicilioListNewDomicilio)) {
                    Barrio oldBarrioOfDomicilioListNewDomicilio = domicilioListNewDomicilio.getBarrio();
                    domicilioListNewDomicilio.setBarrio(barrio);
                    domicilioListNewDomicilio = em.merge(domicilioListNewDomicilio);
                    if (oldBarrioOfDomicilioListNewDomicilio != null && !oldBarrioOfDomicilioListNewDomicilio.equals(barrio)) {
                        oldBarrioOfDomicilioListNewDomicilio.getDomicilioList().remove(domicilioListNewDomicilio);
                        oldBarrioOfDomicilioListNewDomicilio = em.merge(oldBarrioOfDomicilioListNewDomicilio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = barrio.getIdbarrio();
                if (findBarrio(id) == null) {
                    throw new NonexistentEntityException("The barrio with id " + id + " no longer exists.");
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
            Barrio barrio;
            try {
                barrio = em.getReference(Barrio.class, id);
                barrio.getIdbarrio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The barrio with id " + id + " no longer exists.", enfe);
            }
            Localidad localidad = barrio.getLocalidad();
            if (localidad != null) {
                localidad.getBarrioList().remove(barrio);
                localidad = em.merge(localidad);
            }
            List<Domicilio> domicilioList = barrio.getDomicilioList();
            for (Domicilio domicilioListDomicilio : domicilioList) {
                domicilioListDomicilio.setBarrio(null);
                domicilioListDomicilio = em.merge(domicilioListDomicilio);
            }
            em.remove(barrio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Barrio> findBarrioEntities() {
        return findBarrioEntities(true, -1, -1);
    }

    public List<Barrio> findBarrioEntities(int maxResults, int firstResult) {
        return findBarrioEntities(false, maxResults, firstResult);
    }

    private List<Barrio> findBarrioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Barrio.class));
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

    public Barrio findBarrio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Barrio.class, id);
        } finally {
            em.close();
        }
    }

    public int getBarrioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Barrio> rt = cq.from(Barrio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
