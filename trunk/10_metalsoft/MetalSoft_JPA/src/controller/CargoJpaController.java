/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Cargo;
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
public class CargoJpaController {

    public CargoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cargo cargo) throws PreexistingEntityException, Exception {
        if (cargo.getEmpleadoSet() == null) {
            cargo.setEmpleadoSet(new HashSet<Empleado>());
        }
        if (cargo.getEmpleadoSet1() == null) {
            cargo.setEmpleadoSet1(new HashSet<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Empleado> attachedEmpleadoSet = new HashSet<Empleado>();
            for (Empleado empleadoSetEmpleadoToAttach : cargo.getEmpleadoSet()) {
                empleadoSetEmpleadoToAttach = em.getReference(empleadoSetEmpleadoToAttach.getClass(), empleadoSetEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet.add(empleadoSetEmpleadoToAttach);
            }
            cargo.setEmpleadoSet(attachedEmpleadoSet);
            Set<Empleado> attachedEmpleadoSet1 = new HashSet<Empleado>();
            for (Empleado empleadoSet1EmpleadoToAttach : cargo.getEmpleadoSet1()) {
                empleadoSet1EmpleadoToAttach = em.getReference(empleadoSet1EmpleadoToAttach.getClass(), empleadoSet1EmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1.add(empleadoSet1EmpleadoToAttach);
            }
            cargo.setEmpleadoSet1(attachedEmpleadoSet1);
            em.persist(cargo);
            for (Empleado empleadoSetEmpleado : cargo.getEmpleadoSet()) {
                Cargo oldCargoOfEmpleadoSetEmpleado = empleadoSetEmpleado.getCargo();
                empleadoSetEmpleado.setCargo(cargo);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
                if (oldCargoOfEmpleadoSetEmpleado != null) {
                    oldCargoOfEmpleadoSetEmpleado.getEmpleadoSet().remove(empleadoSetEmpleado);
                    oldCargoOfEmpleadoSetEmpleado = em.merge(oldCargoOfEmpleadoSetEmpleado);
                }
            }
            for (Empleado empleadoSet1Empleado : cargo.getEmpleadoSet1()) {
                Cargo oldCargo1OfEmpleadoSet1Empleado = empleadoSet1Empleado.getCargo1();
                empleadoSet1Empleado.setCargo1(cargo);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
                if (oldCargo1OfEmpleadoSet1Empleado != null) {
                    oldCargo1OfEmpleadoSet1Empleado.getEmpleadoSet1().remove(empleadoSet1Empleado);
                    oldCargo1OfEmpleadoSet1Empleado = em.merge(oldCargo1OfEmpleadoSet1Empleado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCargo(cargo.getIdcargo()) != null) {
                throw new PreexistingEntityException("Cargo " + cargo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cargo cargo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo persistentCargo = em.find(Cargo.class, cargo.getIdcargo());
            Set<Empleado> empleadoSetOld = persistentCargo.getEmpleadoSet();
            Set<Empleado> empleadoSetNew = cargo.getEmpleadoSet();
            Set<Empleado> empleadoSet1Old = persistentCargo.getEmpleadoSet1();
            Set<Empleado> empleadoSet1New = cargo.getEmpleadoSet1();
            Set<Empleado> attachedEmpleadoSetNew = new HashSet<Empleado>();
            for (Empleado empleadoSetNewEmpleadoToAttach : empleadoSetNew) {
                empleadoSetNewEmpleadoToAttach = em.getReference(empleadoSetNewEmpleadoToAttach.getClass(), empleadoSetNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSetNew.add(empleadoSetNewEmpleadoToAttach);
            }
            empleadoSetNew = attachedEmpleadoSetNew;
            cargo.setEmpleadoSet(empleadoSetNew);
            Set<Empleado> attachedEmpleadoSet1New = new HashSet<Empleado>();
            for (Empleado empleadoSet1NewEmpleadoToAttach : empleadoSet1New) {
                empleadoSet1NewEmpleadoToAttach = em.getReference(empleadoSet1NewEmpleadoToAttach.getClass(), empleadoSet1NewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1New.add(empleadoSet1NewEmpleadoToAttach);
            }
            empleadoSet1New = attachedEmpleadoSet1New;
            cargo.setEmpleadoSet1(empleadoSet1New);
            cargo = em.merge(cargo);
            for (Empleado empleadoSetOldEmpleado : empleadoSetOld) {
                if (!empleadoSetNew.contains(empleadoSetOldEmpleado)) {
                    empleadoSetOldEmpleado.setCargo(null);
                    empleadoSetOldEmpleado = em.merge(empleadoSetOldEmpleado);
                }
            }
            for (Empleado empleadoSetNewEmpleado : empleadoSetNew) {
                if (!empleadoSetOld.contains(empleadoSetNewEmpleado)) {
                    Cargo oldCargoOfEmpleadoSetNewEmpleado = empleadoSetNewEmpleado.getCargo();
                    empleadoSetNewEmpleado.setCargo(cargo);
                    empleadoSetNewEmpleado = em.merge(empleadoSetNewEmpleado);
                    if (oldCargoOfEmpleadoSetNewEmpleado != null && !oldCargoOfEmpleadoSetNewEmpleado.equals(cargo)) {
                        oldCargoOfEmpleadoSetNewEmpleado.getEmpleadoSet().remove(empleadoSetNewEmpleado);
                        oldCargoOfEmpleadoSetNewEmpleado = em.merge(oldCargoOfEmpleadoSetNewEmpleado);
                    }
                }
            }
            for (Empleado empleadoSet1OldEmpleado : empleadoSet1Old) {
                if (!empleadoSet1New.contains(empleadoSet1OldEmpleado)) {
                    empleadoSet1OldEmpleado.setCargo1(null);
                    empleadoSet1OldEmpleado = em.merge(empleadoSet1OldEmpleado);
                }
            }
            for (Empleado empleadoSet1NewEmpleado : empleadoSet1New) {
                if (!empleadoSet1Old.contains(empleadoSet1NewEmpleado)) {
                    Cargo oldCargo1OfEmpleadoSet1NewEmpleado = empleadoSet1NewEmpleado.getCargo1();
                    empleadoSet1NewEmpleado.setCargo1(cargo);
                    empleadoSet1NewEmpleado = em.merge(empleadoSet1NewEmpleado);
                    if (oldCargo1OfEmpleadoSet1NewEmpleado != null && !oldCargo1OfEmpleadoSet1NewEmpleado.equals(cargo)) {
                        oldCargo1OfEmpleadoSet1NewEmpleado.getEmpleadoSet1().remove(empleadoSet1NewEmpleado);
                        oldCargo1OfEmpleadoSet1NewEmpleado = em.merge(oldCargo1OfEmpleadoSet1NewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cargo.getIdcargo();
                if (findCargo(id) == null) {
                    throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.");
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
            Cargo cargo;
            try {
                cargo = em.getReference(Cargo.class, id);
                cargo.getIdcargo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.", enfe);
            }
            Set<Empleado> empleadoSet = cargo.getEmpleadoSet();
            for (Empleado empleadoSetEmpleado : empleadoSet) {
                empleadoSetEmpleado.setCargo(null);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
            }
            Set<Empleado> empleadoSet1 = cargo.getEmpleadoSet1();
            for (Empleado empleadoSet1Empleado : empleadoSet1) {
                empleadoSet1Empleado.setCargo1(null);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
            }
            em.remove(cargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cargo> findCargoEntities() {
        return findCargoEntities(true, -1, -1);
    }

    public List<Cargo> findCargoEntities(int maxResults, int firstResult) {
        return findCargoEntities(false, maxResults, firstResult);
    }

    private List<Cargo> findCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cargo.class));
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

    public Cargo findCargo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cargo> rt = cq.from(Cargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
