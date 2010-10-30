/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Localidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Provincia;
import entity.Barrio;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class LocalidadJpaController {

    public LocalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Localidad localidad) throws PreexistingEntityException, Exception {
        if (localidad.getBarrioSet() == null) {
            localidad.setBarrioSet(new HashSet<Barrio>());
        }
        if (localidad.getBarrioSet1() == null) {
            localidad.setBarrioSet1(new HashSet<Barrio>());
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
            Provincia provincia1 = localidad.getProvincia1();
            if (provincia1 != null) {
                provincia1 = em.getReference(provincia1.getClass(), provincia1.getIdprovincia());
                localidad.setProvincia1(provincia1);
            }
            Set<Barrio> attachedBarrioSet = new HashSet<Barrio>();
            for (Barrio barrioSetBarrioToAttach : localidad.getBarrioSet()) {
                barrioSetBarrioToAttach = em.getReference(barrioSetBarrioToAttach.getClass(), barrioSetBarrioToAttach.getIdbarrio());
                attachedBarrioSet.add(barrioSetBarrioToAttach);
            }
            localidad.setBarrioSet(attachedBarrioSet);
            Set<Barrio> attachedBarrioSet1 = new HashSet<Barrio>();
            for (Barrio barrioSet1BarrioToAttach : localidad.getBarrioSet1()) {
                barrioSet1BarrioToAttach = em.getReference(barrioSet1BarrioToAttach.getClass(), barrioSet1BarrioToAttach.getIdbarrio());
                attachedBarrioSet1.add(barrioSet1BarrioToAttach);
            }
            localidad.setBarrioSet1(attachedBarrioSet1);
            em.persist(localidad);
            if (provincia != null) {
                provincia.getLocalidadSet().add(localidad);
                provincia = em.merge(provincia);
            }
            if (provincia1 != null) {
                provincia1.getLocalidadSet().add(localidad);
                provincia1 = em.merge(provincia1);
            }
            for (Barrio barrioSetBarrio : localidad.getBarrioSet()) {
                Localidad oldLocalidadOfBarrioSetBarrio = barrioSetBarrio.getLocalidad();
                barrioSetBarrio.setLocalidad(localidad);
                barrioSetBarrio = em.merge(barrioSetBarrio);
                if (oldLocalidadOfBarrioSetBarrio != null) {
                    oldLocalidadOfBarrioSetBarrio.getBarrioSet().remove(barrioSetBarrio);
                    oldLocalidadOfBarrioSetBarrio = em.merge(oldLocalidadOfBarrioSetBarrio);
                }
            }
            for (Barrio barrioSet1Barrio : localidad.getBarrioSet1()) {
                Localidad oldLocalidad1OfBarrioSet1Barrio = barrioSet1Barrio.getLocalidad1();
                barrioSet1Barrio.setLocalidad1(localidad);
                barrioSet1Barrio = em.merge(barrioSet1Barrio);
                if (oldLocalidad1OfBarrioSet1Barrio != null) {
                    oldLocalidad1OfBarrioSet1Barrio.getBarrioSet1().remove(barrioSet1Barrio);
                    oldLocalidad1OfBarrioSet1Barrio = em.merge(oldLocalidad1OfBarrioSet1Barrio);
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
            Provincia provincia1Old = persistentLocalidad.getProvincia1();
            Provincia provincia1New = localidad.getProvincia1();
            Set<Barrio> barrioSetOld = persistentLocalidad.getBarrioSet();
            Set<Barrio> barrioSetNew = localidad.getBarrioSet();
            Set<Barrio> barrioSet1Old = persistentLocalidad.getBarrioSet1();
            Set<Barrio> barrioSet1New = localidad.getBarrioSet1();
            if (provinciaNew != null) {
                provinciaNew = em.getReference(provinciaNew.getClass(), provinciaNew.getIdprovincia());
                localidad.setProvincia(provinciaNew);
            }
            if (provincia1New != null) {
                provincia1New = em.getReference(provincia1New.getClass(), provincia1New.getIdprovincia());
                localidad.setProvincia1(provincia1New);
            }
            Set<Barrio> attachedBarrioSetNew = new HashSet<Barrio>();
            for (Barrio barrioSetNewBarrioToAttach : barrioSetNew) {
                barrioSetNewBarrioToAttach = em.getReference(barrioSetNewBarrioToAttach.getClass(), barrioSetNewBarrioToAttach.getIdbarrio());
                attachedBarrioSetNew.add(barrioSetNewBarrioToAttach);
            }
            barrioSetNew = attachedBarrioSetNew;
            localidad.setBarrioSet(barrioSetNew);
            Set<Barrio> attachedBarrioSet1New = new HashSet<Barrio>();
            for (Barrio barrioSet1NewBarrioToAttach : barrioSet1New) {
                barrioSet1NewBarrioToAttach = em.getReference(barrioSet1NewBarrioToAttach.getClass(), barrioSet1NewBarrioToAttach.getIdbarrio());
                attachedBarrioSet1New.add(barrioSet1NewBarrioToAttach);
            }
            barrioSet1New = attachedBarrioSet1New;
            localidad.setBarrioSet1(barrioSet1New);
            localidad = em.merge(localidad);
            if (provinciaOld != null && !provinciaOld.equals(provinciaNew)) {
                provinciaOld.getLocalidadSet().remove(localidad);
                provinciaOld = em.merge(provinciaOld);
            }
            if (provinciaNew != null && !provinciaNew.equals(provinciaOld)) {
                provinciaNew.getLocalidadSet().add(localidad);
                provinciaNew = em.merge(provinciaNew);
            }
            if (provincia1Old != null && !provincia1Old.equals(provincia1New)) {
                provincia1Old.getLocalidadSet().remove(localidad);
                provincia1Old = em.merge(provincia1Old);
            }
            if (provincia1New != null && !provincia1New.equals(provincia1Old)) {
                provincia1New.getLocalidadSet().add(localidad);
                provincia1New = em.merge(provincia1New);
            }
            for (Barrio barrioSetOldBarrio : barrioSetOld) {
                if (!barrioSetNew.contains(barrioSetOldBarrio)) {
                    barrioSetOldBarrio.setLocalidad(null);
                    barrioSetOldBarrio = em.merge(barrioSetOldBarrio);
                }
            }
            for (Barrio barrioSetNewBarrio : barrioSetNew) {
                if (!barrioSetOld.contains(barrioSetNewBarrio)) {
                    Localidad oldLocalidadOfBarrioSetNewBarrio = barrioSetNewBarrio.getLocalidad();
                    barrioSetNewBarrio.setLocalidad(localidad);
                    barrioSetNewBarrio = em.merge(barrioSetNewBarrio);
                    if (oldLocalidadOfBarrioSetNewBarrio != null && !oldLocalidadOfBarrioSetNewBarrio.equals(localidad)) {
                        oldLocalidadOfBarrioSetNewBarrio.getBarrioSet().remove(barrioSetNewBarrio);
                        oldLocalidadOfBarrioSetNewBarrio = em.merge(oldLocalidadOfBarrioSetNewBarrio);
                    }
                }
            }
            for (Barrio barrioSet1OldBarrio : barrioSet1Old) {
                if (!barrioSet1New.contains(barrioSet1OldBarrio)) {
                    barrioSet1OldBarrio.setLocalidad1(null);
                    barrioSet1OldBarrio = em.merge(barrioSet1OldBarrio);
                }
            }
            for (Barrio barrioSet1NewBarrio : barrioSet1New) {
                if (!barrioSet1Old.contains(barrioSet1NewBarrio)) {
                    Localidad oldLocalidad1OfBarrioSet1NewBarrio = barrioSet1NewBarrio.getLocalidad1();
                    barrioSet1NewBarrio.setLocalidad1(localidad);
                    barrioSet1NewBarrio = em.merge(barrioSet1NewBarrio);
                    if (oldLocalidad1OfBarrioSet1NewBarrio != null && !oldLocalidad1OfBarrioSet1NewBarrio.equals(localidad)) {
                        oldLocalidad1OfBarrioSet1NewBarrio.getBarrioSet1().remove(barrioSet1NewBarrio);
                        oldLocalidad1OfBarrioSet1NewBarrio = em.merge(oldLocalidad1OfBarrioSet1NewBarrio);
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
                provincia.getLocalidadSet().remove(localidad);
                provincia = em.merge(provincia);
            }
            Provincia provincia1 = localidad.getProvincia1();
            if (provincia1 != null) {
                provincia1.getLocalidadSet().remove(localidad);
                provincia1 = em.merge(provincia1);
            }
            Set<Barrio> barrioSet = localidad.getBarrioSet();
            for (Barrio barrioSetBarrio : barrioSet) {
                barrioSetBarrio.setLocalidad(null);
                barrioSetBarrio = em.merge(barrioSetBarrio);
            }
            Set<Barrio> barrioSet1 = localidad.getBarrioSet1();
            for (Barrio barrioSet1Barrio : barrioSet1) {
                barrioSet1Barrio.setLocalidad1(null);
                barrioSet1Barrio = em.merge(barrioSet1Barrio);
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
