/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empleado;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class CategoriaJpaController {

    public CategoriaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) throws PreexistingEntityException, Exception {
        if (categoria.getEmpleadoSet() == null) {
            categoria.setEmpleadoSet(new HashSet<Empleado>());
        }
        if (categoria.getEmpleadoSet1() == null) {
            categoria.setEmpleadoSet1(new HashSet<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Empleado> attachedEmpleadoSet = new HashSet<Empleado>();
            for (Empleado empleadoSetEmpleadoToAttach : categoria.getEmpleadoSet()) {
                empleadoSetEmpleadoToAttach = em.getReference(empleadoSetEmpleadoToAttach.getClass(), empleadoSetEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet.add(empleadoSetEmpleadoToAttach);
            }
            categoria.setEmpleadoSet(attachedEmpleadoSet);
            Set<Empleado> attachedEmpleadoSet1 = new HashSet<Empleado>();
            for (Empleado empleadoSet1EmpleadoToAttach : categoria.getEmpleadoSet1()) {
                empleadoSet1EmpleadoToAttach = em.getReference(empleadoSet1EmpleadoToAttach.getClass(), empleadoSet1EmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1.add(empleadoSet1EmpleadoToAttach);
            }
            categoria.setEmpleadoSet1(attachedEmpleadoSet1);
            em.persist(categoria);
            for (Empleado empleadoSetEmpleado : categoria.getEmpleadoSet()) {
                Categoria oldCategoriaOfEmpleadoSetEmpleado = empleadoSetEmpleado.getCategoria();
                empleadoSetEmpleado.setCategoria(categoria);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
                if (oldCategoriaOfEmpleadoSetEmpleado != null) {
                    oldCategoriaOfEmpleadoSetEmpleado.getEmpleadoSet().remove(empleadoSetEmpleado);
                    oldCategoriaOfEmpleadoSetEmpleado = em.merge(oldCategoriaOfEmpleadoSetEmpleado);
                }
            }
            for (Empleado empleadoSet1Empleado : categoria.getEmpleadoSet1()) {
                Categoria oldCategoria1OfEmpleadoSet1Empleado = empleadoSet1Empleado.getCategoria1();
                empleadoSet1Empleado.setCategoria1(categoria);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
                if (oldCategoria1OfEmpleadoSet1Empleado != null) {
                    oldCategoria1OfEmpleadoSet1Empleado.getEmpleadoSet1().remove(empleadoSet1Empleado);
                    oldCategoria1OfEmpleadoSet1Empleado = em.merge(oldCategoria1OfEmpleadoSet1Empleado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCategoria(categoria.getIdcategoria()) != null) {
                throw new PreexistingEntityException("Categoria " + categoria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getIdcategoria());
            Set<Empleado> empleadoSetOld = persistentCategoria.getEmpleadoSet();
            Set<Empleado> empleadoSetNew = categoria.getEmpleadoSet();
            Set<Empleado> empleadoSet1Old = persistentCategoria.getEmpleadoSet1();
            Set<Empleado> empleadoSet1New = categoria.getEmpleadoSet1();
            Set<Empleado> attachedEmpleadoSetNew = new HashSet<Empleado>();
            for (Empleado empleadoSetNewEmpleadoToAttach : empleadoSetNew) {
                empleadoSetNewEmpleadoToAttach = em.getReference(empleadoSetNewEmpleadoToAttach.getClass(), empleadoSetNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSetNew.add(empleadoSetNewEmpleadoToAttach);
            }
            empleadoSetNew = attachedEmpleadoSetNew;
            categoria.setEmpleadoSet(empleadoSetNew);
            Set<Empleado> attachedEmpleadoSet1New = new HashSet<Empleado>();
            for (Empleado empleadoSet1NewEmpleadoToAttach : empleadoSet1New) {
                empleadoSet1NewEmpleadoToAttach = em.getReference(empleadoSet1NewEmpleadoToAttach.getClass(), empleadoSet1NewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1New.add(empleadoSet1NewEmpleadoToAttach);
            }
            empleadoSet1New = attachedEmpleadoSet1New;
            categoria.setEmpleadoSet1(empleadoSet1New);
            categoria = em.merge(categoria);
            for (Empleado empleadoSetOldEmpleado : empleadoSetOld) {
                if (!empleadoSetNew.contains(empleadoSetOldEmpleado)) {
                    empleadoSetOldEmpleado.setCategoria(null);
                    empleadoSetOldEmpleado = em.merge(empleadoSetOldEmpleado);
                }
            }
            for (Empleado empleadoSetNewEmpleado : empleadoSetNew) {
                if (!empleadoSetOld.contains(empleadoSetNewEmpleado)) {
                    Categoria oldCategoriaOfEmpleadoSetNewEmpleado = empleadoSetNewEmpleado.getCategoria();
                    empleadoSetNewEmpleado.setCategoria(categoria);
                    empleadoSetNewEmpleado = em.merge(empleadoSetNewEmpleado);
                    if (oldCategoriaOfEmpleadoSetNewEmpleado != null && !oldCategoriaOfEmpleadoSetNewEmpleado.equals(categoria)) {
                        oldCategoriaOfEmpleadoSetNewEmpleado.getEmpleadoSet().remove(empleadoSetNewEmpleado);
                        oldCategoriaOfEmpleadoSetNewEmpleado = em.merge(oldCategoriaOfEmpleadoSetNewEmpleado);
                    }
                }
            }
            for (Empleado empleadoSet1OldEmpleado : empleadoSet1Old) {
                if (!empleadoSet1New.contains(empleadoSet1OldEmpleado)) {
                    empleadoSet1OldEmpleado.setCategoria1(null);
                    empleadoSet1OldEmpleado = em.merge(empleadoSet1OldEmpleado);
                }
            }
            for (Empleado empleadoSet1NewEmpleado : empleadoSet1New) {
                if (!empleadoSet1Old.contains(empleadoSet1NewEmpleado)) {
                    Categoria oldCategoria1OfEmpleadoSet1NewEmpleado = empleadoSet1NewEmpleado.getCategoria1();
                    empleadoSet1NewEmpleado.setCategoria1(categoria);
                    empleadoSet1NewEmpleado = em.merge(empleadoSet1NewEmpleado);
                    if (oldCategoria1OfEmpleadoSet1NewEmpleado != null && !oldCategoria1OfEmpleadoSet1NewEmpleado.equals(categoria)) {
                        oldCategoria1OfEmpleadoSet1NewEmpleado.getEmpleadoSet1().remove(empleadoSet1NewEmpleado);
                        oldCategoria1OfEmpleadoSet1NewEmpleado = em.merge(oldCategoria1OfEmpleadoSet1NewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = categoria.getIdcategoria();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getIdcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            Set<Empleado> empleadoSet = categoria.getEmpleadoSet();
            for (Empleado empleadoSetEmpleado : empleadoSet) {
                empleadoSetEmpleado.setCategoria(null);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
            }
            Set<Empleado> empleadoSet1 = categoria.getEmpleadoSet1();
            for (Empleado empleadoSet1Empleado : empleadoSet1) {
                empleadoSet1Empleado.setCategoria1(null);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
