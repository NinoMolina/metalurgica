/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Provincia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Localidad;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class ProvinciaJpaController {

    public ProvinciaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provincia provincia) throws PreexistingEntityException, Exception {
        if (provincia.getLocalidadSet() == null) {
            provincia.setLocalidadSet(new HashSet<Localidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Localidad> attachedLocalidadSet = new HashSet<Localidad>();
            for (Localidad localidadSetLocalidadToAttach : provincia.getLocalidadSet()) {
                localidadSetLocalidadToAttach = em.getReference(localidadSetLocalidadToAttach.getClass(), localidadSetLocalidadToAttach.getIdlocalidad());
                attachedLocalidadSet.add(localidadSetLocalidadToAttach);
            }
            provincia.setLocalidadSet(attachedLocalidadSet);
            em.persist(provincia);
            for (Localidad localidadSetLocalidad : provincia.getLocalidadSet()) {
                Provincia oldProvinciaOfLocalidadSetLocalidad = localidadSetLocalidad.getProvincia();
                localidadSetLocalidad.setProvincia(provincia);
                localidadSetLocalidad = em.merge(localidadSetLocalidad);
                if (oldProvinciaOfLocalidadSetLocalidad != null) {
                    oldProvinciaOfLocalidadSetLocalidad.getLocalidadSet().remove(localidadSetLocalidad);
                    oldProvinciaOfLocalidadSetLocalidad = em.merge(oldProvinciaOfLocalidadSetLocalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProvincia(provincia.getIdprovincia()) != null) {
                throw new PreexistingEntityException("Provincia " + provincia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provincia provincia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find(Provincia.class, provincia.getIdprovincia());
            Set<Localidad> localidadSetOld = persistentProvincia.getLocalidadSet();
            Set<Localidad> localidadSetNew = provincia.getLocalidadSet();
            Set<Localidad> attachedLocalidadSetNew = new HashSet<Localidad>();
            for (Localidad localidadSetNewLocalidadToAttach : localidadSetNew) {
                localidadSetNewLocalidadToAttach = em.getReference(localidadSetNewLocalidadToAttach.getClass(), localidadSetNewLocalidadToAttach.getIdlocalidad());
                attachedLocalidadSetNew.add(localidadSetNewLocalidadToAttach);
            }
            localidadSetNew = attachedLocalidadSetNew;
            provincia.setLocalidadSet(localidadSetNew);
            provincia = em.merge(provincia);
            for (Localidad localidadSetOldLocalidad : localidadSetOld) {
                if (!localidadSetNew.contains(localidadSetOldLocalidad)) {
                    localidadSetOldLocalidad.setProvincia(null);
                    localidadSetOldLocalidad = em.merge(localidadSetOldLocalidad);
                }
            }
            for (Localidad localidadSetNewLocalidad : localidadSetNew) {
                if (!localidadSetOld.contains(localidadSetNewLocalidad)) {
                    Provincia oldProvinciaOfLocalidadSetNewLocalidad = localidadSetNewLocalidad.getProvincia();
                    localidadSetNewLocalidad.setProvincia(provincia);
                    localidadSetNewLocalidad = em.merge(localidadSetNewLocalidad);
                    if (oldProvinciaOfLocalidadSetNewLocalidad != null && !oldProvinciaOfLocalidadSetNewLocalidad.equals(provincia)) {
                        oldProvinciaOfLocalidadSetNewLocalidad.getLocalidadSet().remove(localidadSetNewLocalidad);
                        oldProvinciaOfLocalidadSetNewLocalidad = em.merge(oldProvinciaOfLocalidadSetNewLocalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = provincia.getIdprovincia();
                if (findProvincia(id) == null) {
                    throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.");
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
            Provincia provincia;
            try {
                provincia = em.getReference(Provincia.class, id);
                provincia.getIdprovincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.", enfe);
            }
            Set<Localidad> localidadSet = provincia.getLocalidadSet();
            for (Localidad localidadSetLocalidad : localidadSet) {
                localidadSetLocalidad.setProvincia(null);
                localidadSetLocalidad = em.merge(localidadSetLocalidad);
            }
            em.remove(provincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provincia> findProvinciaEntities() {
        return findProvinciaEntities(true, -1, -1);
    }

    public List<Provincia> findProvinciaEntities(int maxResults, int firstResult) {
        return findProvinciaEntities(false, maxResults, firstResult);
    }

    private List<Provincia> findProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provincia.class));
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

    public Provincia findProvincia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from(Provincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
