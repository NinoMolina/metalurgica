/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Accioncalidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Procesocalidad;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class AccioncalidadJpaController {

    public AccioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accioncalidad accioncalidad) throws PreexistingEntityException, Exception {
        if (accioncalidad.getProcesocalidadSet() == null) {
            accioncalidad.setProcesocalidadSet(new HashSet<Procesocalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Procesocalidad> attachedProcesocalidadSet = new HashSet<Procesocalidad>();
            for (Procesocalidad procesocalidadSetProcesocalidadToAttach : accioncalidad.getProcesocalidadSet()) {
                procesocalidadSetProcesocalidadToAttach = em.getReference(procesocalidadSetProcesocalidadToAttach.getClass(), procesocalidadSetProcesocalidadToAttach.getIdprocesocalidad());
                attachedProcesocalidadSet.add(procesocalidadSetProcesocalidadToAttach);
            }
            accioncalidad.setProcesocalidadSet(attachedProcesocalidadSet);
            em.persist(accioncalidad);
            for (Procesocalidad procesocalidadSetProcesocalidad : accioncalidad.getProcesocalidadSet()) {
                Accioncalidad oldAccioncalidadOfProcesocalidadSetProcesocalidad = procesocalidadSetProcesocalidad.getAccioncalidad();
                procesocalidadSetProcesocalidad.setAccioncalidad(accioncalidad);
                procesocalidadSetProcesocalidad = em.merge(procesocalidadSetProcesocalidad);
                if (oldAccioncalidadOfProcesocalidadSetProcesocalidad != null) {
                    oldAccioncalidadOfProcesocalidadSetProcesocalidad.getProcesocalidadSet().remove(procesocalidadSetProcesocalidad);
                    oldAccioncalidadOfProcesocalidadSetProcesocalidad = em.merge(oldAccioncalidadOfProcesocalidadSetProcesocalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccioncalidad(accioncalidad.getIdaccioncalidad()) != null) {
                throw new PreexistingEntityException("Accioncalidad " + accioncalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accioncalidad accioncalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accioncalidad persistentAccioncalidad = em.find(Accioncalidad.class, accioncalidad.getIdaccioncalidad());
            Set<Procesocalidad> procesocalidadSetOld = persistentAccioncalidad.getProcesocalidadSet();
            Set<Procesocalidad> procesocalidadSetNew = accioncalidad.getProcesocalidadSet();
            Set<Procesocalidad> attachedProcesocalidadSetNew = new HashSet<Procesocalidad>();
            for (Procesocalidad procesocalidadSetNewProcesocalidadToAttach : procesocalidadSetNew) {
                procesocalidadSetNewProcesocalidadToAttach = em.getReference(procesocalidadSetNewProcesocalidadToAttach.getClass(), procesocalidadSetNewProcesocalidadToAttach.getIdprocesocalidad());
                attachedProcesocalidadSetNew.add(procesocalidadSetNewProcesocalidadToAttach);
            }
            procesocalidadSetNew = attachedProcesocalidadSetNew;
            accioncalidad.setProcesocalidadSet(procesocalidadSetNew);
            accioncalidad = em.merge(accioncalidad);
            for (Procesocalidad procesocalidadSetOldProcesocalidad : procesocalidadSetOld) {
                if (!procesocalidadSetNew.contains(procesocalidadSetOldProcesocalidad)) {
                    procesocalidadSetOldProcesocalidad.setAccioncalidad(null);
                    procesocalidadSetOldProcesocalidad = em.merge(procesocalidadSetOldProcesocalidad);
                }
            }
            for (Procesocalidad procesocalidadSetNewProcesocalidad : procesocalidadSetNew) {
                if (!procesocalidadSetOld.contains(procesocalidadSetNewProcesocalidad)) {
                    Accioncalidad oldAccioncalidadOfProcesocalidadSetNewProcesocalidad = procesocalidadSetNewProcesocalidad.getAccioncalidad();
                    procesocalidadSetNewProcesocalidad.setAccioncalidad(accioncalidad);
                    procesocalidadSetNewProcesocalidad = em.merge(procesocalidadSetNewProcesocalidad);
                    if (oldAccioncalidadOfProcesocalidadSetNewProcesocalidad != null && !oldAccioncalidadOfProcesocalidadSetNewProcesocalidad.equals(accioncalidad)) {
                        oldAccioncalidadOfProcesocalidadSetNewProcesocalidad.getProcesocalidadSet().remove(procesocalidadSetNewProcesocalidad);
                        oldAccioncalidadOfProcesocalidadSetNewProcesocalidad = em.merge(oldAccioncalidadOfProcesocalidadSetNewProcesocalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = accioncalidad.getIdaccioncalidad();
                if (findAccioncalidad(id) == null) {
                    throw new NonexistentEntityException("The accioncalidad with id " + id + " no longer exists.");
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
            Accioncalidad accioncalidad;
            try {
                accioncalidad = em.getReference(Accioncalidad.class, id);
                accioncalidad.getIdaccioncalidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accioncalidad with id " + id + " no longer exists.", enfe);
            }
            Set<Procesocalidad> procesocalidadSet = accioncalidad.getProcesocalidadSet();
            for (Procesocalidad procesocalidadSetProcesocalidad : procesocalidadSet) {
                procesocalidadSetProcesocalidad.setAccioncalidad(null);
                procesocalidadSetProcesocalidad = em.merge(procesocalidadSetProcesocalidad);
            }
            em.remove(accioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accioncalidad> findAccioncalidadEntities() {
        return findAccioncalidadEntities(true, -1, -1);
    }

    public List<Accioncalidad> findAccioncalidadEntities(int maxResults, int firstResult) {
        return findAccioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Accioncalidad> findAccioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accioncalidad.class));
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

    public Accioncalidad findAccioncalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accioncalidad> rt = cq.from(Accioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
