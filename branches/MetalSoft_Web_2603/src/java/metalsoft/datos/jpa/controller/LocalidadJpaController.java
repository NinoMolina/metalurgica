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
import metalsoft.datos.jpa.entity.Localidad;
import metalsoft.datos.jpa.entity.Provincia;
import metalsoft.datos.jpa.entity.Barrio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class LocalidadJpaController implements Serializable {

    public LocalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Localidad localidad) throws PreexistingEntityException, Exception {
        if (localidad.getBarrioList() == null) {
            localidad.setBarrioList(new ArrayList<Barrio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia provincia = localidad.getProvincia();
            if (provincia != null) {
                provincia = em.getReference(provincia.getClass(), provincia.getIdprovincia());
                localidad.setProvincia(provincia);
            }
            List<Barrio> attachedBarrioList = new ArrayList<Barrio>();
            for (Barrio barrioListBarrioToAttach : localidad.getBarrioList()) {
                barrioListBarrioToAttach = em.getReference(barrioListBarrioToAttach.getClass(), barrioListBarrioToAttach.getIdbarrio());
                attachedBarrioList.add(barrioListBarrioToAttach);
            }
            localidad.setBarrioList(attachedBarrioList);
            em.persist(localidad);
            if (provincia != null) {
                provincia.getLocalidadList().add(localidad);
                provincia = em.merge(provincia);
            }
            for (Barrio barrioListBarrio : localidad.getBarrioList()) {
                Localidad oldLocalidadOfBarrioListBarrio = barrioListBarrio.getLocalidad();
                barrioListBarrio.setLocalidad(localidad);
                barrioListBarrio = em.merge(barrioListBarrio);
                if (oldLocalidadOfBarrioListBarrio != null) {
                    oldLocalidadOfBarrioListBarrio.getBarrioList().remove(barrioListBarrio);
                    oldLocalidadOfBarrioListBarrio = em.merge(oldLocalidadOfBarrioListBarrio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLocalidad(localidad.getIdlocalidad()) != null) {
                throw new PreexistingEntityException("Localidad " + localidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localidad localidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidad persistentLocalidad = em.find(Localidad.class, localidad.getIdlocalidad());
            Provincia provinciaOld = persistentLocalidad.getProvincia();
            Provincia provinciaNew = localidad.getProvincia();
            List<Barrio> barrioListOld = persistentLocalidad.getBarrioList();
            List<Barrio> barrioListNew = localidad.getBarrioList();
            if (provinciaNew != null) {
                provinciaNew = em.getReference(provinciaNew.getClass(), provinciaNew.getIdprovincia());
                localidad.setProvincia(provinciaNew);
            }
            List<Barrio> attachedBarrioListNew = new ArrayList<Barrio>();
            for (Barrio barrioListNewBarrioToAttach : barrioListNew) {
                barrioListNewBarrioToAttach = em.getReference(barrioListNewBarrioToAttach.getClass(), barrioListNewBarrioToAttach.getIdbarrio());
                attachedBarrioListNew.add(barrioListNewBarrioToAttach);
            }
            barrioListNew = attachedBarrioListNew;
            localidad.setBarrioList(barrioListNew);
            localidad = em.merge(localidad);
            if (provinciaOld != null && !provinciaOld.equals(provinciaNew)) {
                provinciaOld.getLocalidadList().remove(localidad);
                provinciaOld = em.merge(provinciaOld);
            }
            if (provinciaNew != null && !provinciaNew.equals(provinciaOld)) {
                provinciaNew.getLocalidadList().add(localidad);
                provinciaNew = em.merge(provinciaNew);
            }
            for (Barrio barrioListOldBarrio : barrioListOld) {
                if (!barrioListNew.contains(barrioListOldBarrio)) {
                    barrioListOldBarrio.setLocalidad(null);
                    barrioListOldBarrio = em.merge(barrioListOldBarrio);
                }
            }
            for (Barrio barrioListNewBarrio : barrioListNew) {
                if (!barrioListOld.contains(barrioListNewBarrio)) {
                    Localidad oldLocalidadOfBarrioListNewBarrio = barrioListNewBarrio.getLocalidad();
                    barrioListNewBarrio.setLocalidad(localidad);
                    barrioListNewBarrio = em.merge(barrioListNewBarrio);
                    if (oldLocalidadOfBarrioListNewBarrio != null && !oldLocalidadOfBarrioListNewBarrio.equals(localidad)) {
                        oldLocalidadOfBarrioListNewBarrio.getBarrioList().remove(barrioListNewBarrio);
                        oldLocalidadOfBarrioListNewBarrio = em.merge(oldLocalidadOfBarrioListNewBarrio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = localidad.getIdlocalidad();
                if (findLocalidad(id) == null) {
                    throw new NonexistentEntityException("The localidad with id " + id + " no longer exists.");
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
            Localidad localidad;
            try {
                localidad = em.getReference(Localidad.class, id);
                localidad.getIdlocalidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localidad with id " + id + " no longer exists.", enfe);
            }
            Provincia provincia = localidad.getProvincia();
            if (provincia != null) {
                provincia.getLocalidadList().remove(localidad);
                provincia = em.merge(provincia);
            }
            List<Barrio> barrioList = localidad.getBarrioList();
            for (Barrio barrioListBarrio : barrioList) {
                barrioListBarrio.setLocalidad(null);
                barrioListBarrio = em.merge(barrioListBarrio);
            }
            em.remove(localidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localidad> findLocalidadEntities() {
        return findLocalidadEntities(true, -1, -1);
    }

    public List<Localidad> findLocalidadEntities(int maxResults, int firstResult) {
        return findLocalidadEntities(false, maxResults, firstResult);
    }

    private List<Localidad> findLocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Localidad.class));
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

    public Localidad findLocalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Localidad> rt = cq.from(Localidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
