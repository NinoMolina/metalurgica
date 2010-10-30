/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Barrio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Localidad;
import entity.Domicilio;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class BarrioJpaController {

    public BarrioJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Barrio barrio) throws PreexistingEntityException, Exception {
        if (barrio.getDomicilioSet() == null) {
            barrio.setDomicilioSet(new HashSet<Domicilio>());
        }
        if (barrio.getDomicilioSet1() == null) {
            barrio.setDomicilioSet1(new HashSet<Domicilio>());
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
            Localidad localidad1 = barrio.getLocalidad1();
            if (localidad1 != null) {
                localidad1 = em.getReference(localidad1.getClass(), localidad1.getIdlocalidad());
                barrio.setLocalidad1(localidad1);
            }
            Set<Domicilio> attachedDomicilioSet = new HashSet<Domicilio>();
            for (Domicilio domicilioSetDomicilioToAttach : barrio.getDomicilioSet()) {
                domicilioSetDomicilioToAttach = em.getReference(domicilioSetDomicilioToAttach.getClass(), domicilioSetDomicilioToAttach.getIddomicilio());
                attachedDomicilioSet.add(domicilioSetDomicilioToAttach);
            }
            barrio.setDomicilioSet(attachedDomicilioSet);
            Set<Domicilio> attachedDomicilioSet1 = new HashSet<Domicilio>();
            for (Domicilio domicilioSet1DomicilioToAttach : barrio.getDomicilioSet1()) {
                domicilioSet1DomicilioToAttach = em.getReference(domicilioSet1DomicilioToAttach.getClass(), domicilioSet1DomicilioToAttach.getIddomicilio());
                attachedDomicilioSet1.add(domicilioSet1DomicilioToAttach);
            }
            barrio.setDomicilioSet1(attachedDomicilioSet1);
            em.persist(barrio);
            if (localidad != null) {
                localidad.getBarrioSet().add(barrio);
                localidad = em.merge(localidad);
            }
            if (localidad1 != null) {
                localidad1.getBarrioSet().add(barrio);
                localidad1 = em.merge(localidad1);
            }
            for (Domicilio domicilioSetDomicilio : barrio.getDomicilioSet()) {
                Barrio oldBarrioOfDomicilioSetDomicilio = domicilioSetDomicilio.getBarrio();
                domicilioSetDomicilio.setBarrio(barrio);
                domicilioSetDomicilio = em.merge(domicilioSetDomicilio);
                if (oldBarrioOfDomicilioSetDomicilio != null) {
                    oldBarrioOfDomicilioSetDomicilio.getDomicilioSet().remove(domicilioSetDomicilio);
                    oldBarrioOfDomicilioSetDomicilio = em.merge(oldBarrioOfDomicilioSetDomicilio);
                }
            }
            for (Domicilio domicilioSet1Domicilio : barrio.getDomicilioSet1()) {
                Barrio oldBarrio1OfDomicilioSet1Domicilio = domicilioSet1Domicilio.getBarrio1();
                domicilioSet1Domicilio.setBarrio1(barrio);
                domicilioSet1Domicilio = em.merge(domicilioSet1Domicilio);
                if (oldBarrio1OfDomicilioSet1Domicilio != null) {
                    oldBarrio1OfDomicilioSet1Domicilio.getDomicilioSet1().remove(domicilioSet1Domicilio);
                    oldBarrio1OfDomicilioSet1Domicilio = em.merge(oldBarrio1OfDomicilioSet1Domicilio);
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
            Localidad localidad1Old = persistentBarrio.getLocalidad1();
            Localidad localidad1New = barrio.getLocalidad1();
            Set<Domicilio> domicilioSetOld = persistentBarrio.getDomicilioSet();
            Set<Domicilio> domicilioSetNew = barrio.getDomicilioSet();
            Set<Domicilio> domicilioSet1Old = persistentBarrio.getDomicilioSet1();
            Set<Domicilio> domicilioSet1New = barrio.getDomicilioSet1();
            if (localidadNew != null) {
                localidadNew = em.getReference(localidadNew.getClass(), localidadNew.getIdlocalidad());
                barrio.setLocalidad(localidadNew);
            }
            if (localidad1New != null) {
                localidad1New = em.getReference(localidad1New.getClass(), localidad1New.getIdlocalidad());
                barrio.setLocalidad1(localidad1New);
            }
            Set<Domicilio> attachedDomicilioSetNew = new HashSet<Domicilio>();
            for (Domicilio domicilioSetNewDomicilioToAttach : domicilioSetNew) {
                domicilioSetNewDomicilioToAttach = em.getReference(domicilioSetNewDomicilioToAttach.getClass(), domicilioSetNewDomicilioToAttach.getIddomicilio());
                attachedDomicilioSetNew.add(domicilioSetNewDomicilioToAttach);
            }
            domicilioSetNew = attachedDomicilioSetNew;
            barrio.setDomicilioSet(domicilioSetNew);
            Set<Domicilio> attachedDomicilioSet1New = new HashSet<Domicilio>();
            for (Domicilio domicilioSet1NewDomicilioToAttach : domicilioSet1New) {
                domicilioSet1NewDomicilioToAttach = em.getReference(domicilioSet1NewDomicilioToAttach.getClass(), domicilioSet1NewDomicilioToAttach.getIddomicilio());
                attachedDomicilioSet1New.add(domicilioSet1NewDomicilioToAttach);
            }
            domicilioSet1New = attachedDomicilioSet1New;
            barrio.setDomicilioSet1(domicilioSet1New);
            barrio = em.merge(barrio);
            if (localidadOld != null && !localidadOld.equals(localidadNew)) {
                localidadOld.getBarrioSet().remove(barrio);
                localidadOld = em.merge(localidadOld);
            }
            if (localidadNew != null && !localidadNew.equals(localidadOld)) {
                localidadNew.getBarrioSet().add(barrio);
                localidadNew = em.merge(localidadNew);
            }
            if (localidad1Old != null && !localidad1Old.equals(localidad1New)) {
                localidad1Old.getBarrioSet().remove(barrio);
                localidad1Old = em.merge(localidad1Old);
            }
            if (localidad1New != null && !localidad1New.equals(localidad1Old)) {
                localidad1New.getBarrioSet().add(barrio);
                localidad1New = em.merge(localidad1New);
            }
            for (Domicilio domicilioSetOldDomicilio : domicilioSetOld) {
                if (!domicilioSetNew.contains(domicilioSetOldDomicilio)) {
                    domicilioSetOldDomicilio.setBarrio(null);
                    domicilioSetOldDomicilio = em.merge(domicilioSetOldDomicilio);
                }
            }
            for (Domicilio domicilioSetNewDomicilio : domicilioSetNew) {
                if (!domicilioSetOld.contains(domicilioSetNewDomicilio)) {
                    Barrio oldBarrioOfDomicilioSetNewDomicilio = domicilioSetNewDomicilio.getBarrio();
                    domicilioSetNewDomicilio.setBarrio(barrio);
                    domicilioSetNewDomicilio = em.merge(domicilioSetNewDomicilio);
                    if (oldBarrioOfDomicilioSetNewDomicilio != null && !oldBarrioOfDomicilioSetNewDomicilio.equals(barrio)) {
                        oldBarrioOfDomicilioSetNewDomicilio.getDomicilioSet().remove(domicilioSetNewDomicilio);
                        oldBarrioOfDomicilioSetNewDomicilio = em.merge(oldBarrioOfDomicilioSetNewDomicilio);
                    }
                }
            }
            for (Domicilio domicilioSet1OldDomicilio : domicilioSet1Old) {
                if (!domicilioSet1New.contains(domicilioSet1OldDomicilio)) {
                    domicilioSet1OldDomicilio.setBarrio1(null);
                    domicilioSet1OldDomicilio = em.merge(domicilioSet1OldDomicilio);
                }
            }
            for (Domicilio domicilioSet1NewDomicilio : domicilioSet1New) {
                if (!domicilioSet1Old.contains(domicilioSet1NewDomicilio)) {
                    Barrio oldBarrio1OfDomicilioSet1NewDomicilio = domicilioSet1NewDomicilio.getBarrio1();
                    domicilioSet1NewDomicilio.setBarrio1(barrio);
                    domicilioSet1NewDomicilio = em.merge(domicilioSet1NewDomicilio);
                    if (oldBarrio1OfDomicilioSet1NewDomicilio != null && !oldBarrio1OfDomicilioSet1NewDomicilio.equals(barrio)) {
                        oldBarrio1OfDomicilioSet1NewDomicilio.getDomicilioSet1().remove(domicilioSet1NewDomicilio);
                        oldBarrio1OfDomicilioSet1NewDomicilio = em.merge(oldBarrio1OfDomicilioSet1NewDomicilio);
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
                localidad.getBarrioSet().remove(barrio);
                localidad = em.merge(localidad);
            }
            Localidad localidad1 = barrio.getLocalidad1();
            if (localidad1 != null) {
                localidad1.getBarrioSet().remove(barrio);
                localidad1 = em.merge(localidad1);
            }
            Set<Domicilio> domicilioSet = barrio.getDomicilioSet();
            for (Domicilio domicilioSetDomicilio : domicilioSet) {
                domicilioSetDomicilio.setBarrio(null);
                domicilioSetDomicilio = em.merge(domicilioSetDomicilio);
            }
            Set<Domicilio> domicilioSet1 = barrio.getDomicilioSet1();
            for (Domicilio domicilioSet1Domicilio : domicilioSet1) {
                domicilioSet1Domicilio.setBarrio1(null);
                domicilioSet1Domicilio = em.merge(domicilioSet1Domicilio);
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
