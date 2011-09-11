/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Disponibilidadhoraria;
import metalsoft.datos.jpa.entity.Empleado;

/**
 *
 * @author Nino
 */
public class DisponibilidadhorariaJpaController implements Serializable {

    public DisponibilidadhorariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
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
            em.persist(disponibilidadhoraria);
            if (idempleado != null) {
                idempleado.getDisponibilidadhorariaList().add(disponibilidadhoraria);
                idempleado = em.merge(idempleado);
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
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                disponibilidadhoraria.setIdempleado(idempleadoNew);
            }
            disponibilidadhoraria = em.merge(disponibilidadhoraria);
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getDisponibilidadhorariaList().remove(disponibilidadhoraria);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getDisponibilidadhorariaList().add(disponibilidadhoraria);
                idempleadoNew = em.merge(idempleadoNew);
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
                idempleado.getDisponibilidadhorariaList().remove(disponibilidadhoraria);
                idempleado = em.merge(idempleado);
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
