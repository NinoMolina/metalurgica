/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Disponibilidadhoraria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empleado;

/**
 *
 * @author Nino
 */
public class DisponibilidadhorariaJpaController {

    public DisponibilidadhorariaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Disponibilidadhoraria disponibilidadhoraria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado idempleado = disponibilidadhoraria.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                disponibilidadhoraria.setIdempleado(idempleado);
            }
            Empleado idempleado1 = disponibilidadhoraria.getIdempleado1();
            if (idempleado1 != null) {
                idempleado1 = em.getReference(idempleado1.getClass(), idempleado1.getIdempleado());
                disponibilidadhoraria.setIdempleado1(idempleado1);
            }
            em.persist(disponibilidadhoraria);
            if (idempleado != null) {
                idempleado.getDisponibilidadhorariaSet().add(disponibilidadhoraria);
                idempleado = em.merge(idempleado);
            }
            if (idempleado1 != null) {
                idempleado1.getDisponibilidadhorariaSet().add(disponibilidadhoraria);
                idempleado1 = em.merge(idempleado1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDisponibilidadhoraria(disponibilidadhoraria.getId()) != null) {
                throw new PreexistingEntityException("Disponibilidadhoraria " + disponibilidadhoraria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Disponibilidadhoraria disponibilidadhoraria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Disponibilidadhoraria persistentDisponibilidadhoraria = em.find(Disponibilidadhoraria.class, disponibilidadhoraria.getId());
            Empleado idempleadoOld = persistentDisponibilidadhoraria.getIdempleado();
            Empleado idempleadoNew = disponibilidadhoraria.getIdempleado();
            Empleado idempleado1Old = persistentDisponibilidadhoraria.getIdempleado1();
            Empleado idempleado1New = disponibilidadhoraria.getIdempleado1();
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                disponibilidadhoraria.setIdempleado(idempleadoNew);
            }
            if (idempleado1New != null) {
                idempleado1New = em.getReference(idempleado1New.getClass(), idempleado1New.getIdempleado());
                disponibilidadhoraria.setIdempleado1(idempleado1New);
            }
            disponibilidadhoraria = em.merge(disponibilidadhoraria);
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getDisponibilidadhorariaSet().remove(disponibilidadhoraria);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getDisponibilidadhorariaSet().add(disponibilidadhoraria);
                idempleadoNew = em.merge(idempleadoNew);
            }
            if (idempleado1Old != null && !idempleado1Old.equals(idempleado1New)) {
                idempleado1Old.getDisponibilidadhorariaSet().remove(disponibilidadhoraria);
                idempleado1Old = em.merge(idempleado1Old);
            }
            if (idempleado1New != null && !idempleado1New.equals(idempleado1Old)) {
                idempleado1New.getDisponibilidadhorariaSet().add(disponibilidadhoraria);
                idempleado1New = em.merge(idempleado1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = disponibilidadhoraria.getId();
                if (findDisponibilidadhoraria(id) == null) {
                    throw new NonexistentEntityException("The disponibilidadhoraria with id " + id + " no longer exists.");
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
            Disponibilidadhoraria disponibilidadhoraria;
            try {
                disponibilidadhoraria = em.getReference(Disponibilidadhoraria.class, id);
                disponibilidadhoraria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disponibilidadhoraria with id " + id + " no longer exists.", enfe);
            }
            Empleado idempleado = disponibilidadhoraria.getIdempleado();
            if (idempleado != null) {
                idempleado.getDisponibilidadhorariaSet().remove(disponibilidadhoraria);
                idempleado = em.merge(idempleado);
            }
            Empleado idempleado1 = disponibilidadhoraria.getIdempleado1();
            if (idempleado1 != null) {
                idempleado1.getDisponibilidadhorariaSet().remove(disponibilidadhoraria);
                idempleado1 = em.merge(idempleado1);
            }
            em.remove(disponibilidadhoraria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Disponibilidadhoraria> findDisponibilidadhorariaEntities() {
        return findDisponibilidadhorariaEntities(true, -1, -1);
    }

    public List<Disponibilidadhoraria> findDisponibilidadhorariaEntities(int maxResults, int firstResult) {
        return findDisponibilidadhorariaEntities(false, maxResults, firstResult);
    }

    private List<Disponibilidadhoraria> findDisponibilidadhorariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Disponibilidadhoraria.class));
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

    public Disponibilidadhoraria findDisponibilidadhoraria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Disponibilidadhoraria.class, id);
        } finally {
            em.close();
        }
    }

    public int getDisponibilidadhorariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Disponibilidadhoraria> rt = cq.from(Disponibilidadhoraria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
