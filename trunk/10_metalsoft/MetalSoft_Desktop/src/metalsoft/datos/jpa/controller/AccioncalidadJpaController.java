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
import metalsoft.datos.jpa.entity.Accioncalidad;
import metalsoft.datos.jpa.entity.Procesocalidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class AccioncalidadJpaController implements Serializable {

    public AccioncalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accioncalidad accioncalidad) throws PreexistingEntityException, Exception {
        if (accioncalidad.getProcesocalidadList() == null) {
            accioncalidad.setProcesocalidadList(new ArrayList<Procesocalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Procesocalidad> attachedProcesocalidadList = new ArrayList<Procesocalidad>();
            for (Procesocalidad procesocalidadListProcesocalidadToAttach : accioncalidad.getProcesocalidadList()) {
                procesocalidadListProcesocalidadToAttach = em.getReference(procesocalidadListProcesocalidadToAttach.getClass(), procesocalidadListProcesocalidadToAttach.getIdprocesocalidad());
                attachedProcesocalidadList.add(procesocalidadListProcesocalidadToAttach);
            }
            accioncalidad.setProcesocalidadList(attachedProcesocalidadList);
            em.persist(accioncalidad);
            for (Procesocalidad procesocalidadListProcesocalidad : accioncalidad.getProcesocalidadList()) {
                Accioncalidad oldAccioncalidadOfProcesocalidadListProcesocalidad = procesocalidadListProcesocalidad.getAccioncalidad();
                procesocalidadListProcesocalidad.setAccioncalidad(accioncalidad);
                procesocalidadListProcesocalidad = em.merge(procesocalidadListProcesocalidad);
                if (oldAccioncalidadOfProcesocalidadListProcesocalidad != null) {
                    oldAccioncalidadOfProcesocalidadListProcesocalidad.getProcesocalidadList().remove(procesocalidadListProcesocalidad);
                    oldAccioncalidadOfProcesocalidadListProcesocalidad = em.merge(oldAccioncalidadOfProcesocalidadListProcesocalidad);
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
            List<Procesocalidad> procesocalidadListOld = persistentAccioncalidad.getProcesocalidadList();
            List<Procesocalidad> procesocalidadListNew = accioncalidad.getProcesocalidadList();
            List<Procesocalidad> attachedProcesocalidadListNew = new ArrayList<Procesocalidad>();
            for (Procesocalidad procesocalidadListNewProcesocalidadToAttach : procesocalidadListNew) {
                procesocalidadListNewProcesocalidadToAttach = em.getReference(procesocalidadListNewProcesocalidadToAttach.getClass(), procesocalidadListNewProcesocalidadToAttach.getIdprocesocalidad());
                attachedProcesocalidadListNew.add(procesocalidadListNewProcesocalidadToAttach);
            }
            procesocalidadListNew = attachedProcesocalidadListNew;
            accioncalidad.setProcesocalidadList(procesocalidadListNew);
            accioncalidad = em.merge(accioncalidad);
            for (Procesocalidad procesocalidadListOldProcesocalidad : procesocalidadListOld) {
                if (!procesocalidadListNew.contains(procesocalidadListOldProcesocalidad)) {
                    procesocalidadListOldProcesocalidad.setAccioncalidad(null);
                    procesocalidadListOldProcesocalidad = em.merge(procesocalidadListOldProcesocalidad);
                }
            }
            for (Procesocalidad procesocalidadListNewProcesocalidad : procesocalidadListNew) {
                if (!procesocalidadListOld.contains(procesocalidadListNewProcesocalidad)) {
                    Accioncalidad oldAccioncalidadOfProcesocalidadListNewProcesocalidad = procesocalidadListNewProcesocalidad.getAccioncalidad();
                    procesocalidadListNewProcesocalidad.setAccioncalidad(accioncalidad);
                    procesocalidadListNewProcesocalidad = em.merge(procesocalidadListNewProcesocalidad);
                    if (oldAccioncalidadOfProcesocalidadListNewProcesocalidad != null && !oldAccioncalidadOfProcesocalidadListNewProcesocalidad.equals(accioncalidad)) {
                        oldAccioncalidadOfProcesocalidadListNewProcesocalidad.getProcesocalidadList().remove(procesocalidadListNewProcesocalidad);
                        oldAccioncalidadOfProcesocalidadListNewProcesocalidad = em.merge(oldAccioncalidadOfProcesocalidadListNewProcesocalidad);
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
            List<Procesocalidad> procesocalidadList = accioncalidad.getProcesocalidadList();
            for (Procesocalidad procesocalidadListProcesocalidad : procesocalidadList) {
                procesocalidadListProcesocalidad.setAccioncalidad(null);
                procesocalidadListProcesocalidad = em.merge(procesocalidadListProcesocalidad);
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
