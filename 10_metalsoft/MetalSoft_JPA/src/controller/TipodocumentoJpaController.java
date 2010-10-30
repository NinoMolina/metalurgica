/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Tipodocumento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Responsable;
import java.util.HashSet;
import java.util.Set;
import entity.Empleado;

/**
 *
 * @author Nino
 */
public class TipodocumentoJpaController {

    public TipodocumentoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipodocumento tipodocumento) throws PreexistingEntityException, Exception {
        if (tipodocumento.getResponsableSet() == null) {
            tipodocumento.setResponsableSet(new HashSet<Responsable>());
        }
        if (tipodocumento.getResponsableSet1() == null) {
            tipodocumento.setResponsableSet1(new HashSet<Responsable>());
        }
        if (tipodocumento.getEmpleadoSet() == null) {
            tipodocumento.setEmpleadoSet(new HashSet<Empleado>());
        }
        if (tipodocumento.getEmpleadoSet1() == null) {
            tipodocumento.setEmpleadoSet1(new HashSet<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Responsable> attachedResponsableSet = new HashSet<Responsable>();
            for (Responsable responsableSetResponsableToAttach : tipodocumento.getResponsableSet()) {
                responsableSetResponsableToAttach = em.getReference(responsableSetResponsableToAttach.getClass(), responsableSetResponsableToAttach.getIdresponsable());
                attachedResponsableSet.add(responsableSetResponsableToAttach);
            }
            tipodocumento.setResponsableSet(attachedResponsableSet);
            Set<Responsable> attachedResponsableSet1 = new HashSet<Responsable>();
            for (Responsable responsableSet1ResponsableToAttach : tipodocumento.getResponsableSet1()) {
                responsableSet1ResponsableToAttach = em.getReference(responsableSet1ResponsableToAttach.getClass(), responsableSet1ResponsableToAttach.getIdresponsable());
                attachedResponsableSet1.add(responsableSet1ResponsableToAttach);
            }
            tipodocumento.setResponsableSet1(attachedResponsableSet1);
            Set<Empleado> attachedEmpleadoSet = new HashSet<Empleado>();
            for (Empleado empleadoSetEmpleadoToAttach : tipodocumento.getEmpleadoSet()) {
                empleadoSetEmpleadoToAttach = em.getReference(empleadoSetEmpleadoToAttach.getClass(), empleadoSetEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet.add(empleadoSetEmpleadoToAttach);
            }
            tipodocumento.setEmpleadoSet(attachedEmpleadoSet);
            Set<Empleado> attachedEmpleadoSet1 = new HashSet<Empleado>();
            for (Empleado empleadoSet1EmpleadoToAttach : tipodocumento.getEmpleadoSet1()) {
                empleadoSet1EmpleadoToAttach = em.getReference(empleadoSet1EmpleadoToAttach.getClass(), empleadoSet1EmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1.add(empleadoSet1EmpleadoToAttach);
            }
            tipodocumento.setEmpleadoSet1(attachedEmpleadoSet1);
            em.persist(tipodocumento);
            for (Responsable responsableSetResponsable : tipodocumento.getResponsableSet()) {
                Tipodocumento oldTipodocumentoOfResponsableSetResponsable = responsableSetResponsable.getTipodocumento();
                responsableSetResponsable.setTipodocumento(tipodocumento);
                responsableSetResponsable = em.merge(responsableSetResponsable);
                if (oldTipodocumentoOfResponsableSetResponsable != null) {
                    oldTipodocumentoOfResponsableSetResponsable.getResponsableSet().remove(responsableSetResponsable);
                    oldTipodocumentoOfResponsableSetResponsable = em.merge(oldTipodocumentoOfResponsableSetResponsable);
                }
            }
            for (Responsable responsableSet1Responsable : tipodocumento.getResponsableSet1()) {
                Tipodocumento oldTipodocumento1OfResponsableSet1Responsable = responsableSet1Responsable.getTipodocumento1();
                responsableSet1Responsable.setTipodocumento1(tipodocumento);
                responsableSet1Responsable = em.merge(responsableSet1Responsable);
                if (oldTipodocumento1OfResponsableSet1Responsable != null) {
                    oldTipodocumento1OfResponsableSet1Responsable.getResponsableSet1().remove(responsableSet1Responsable);
                    oldTipodocumento1OfResponsableSet1Responsable = em.merge(oldTipodocumento1OfResponsableSet1Responsable);
                }
            }
            for (Empleado empleadoSetEmpleado : tipodocumento.getEmpleadoSet()) {
                Tipodocumento oldTipodocumentoOfEmpleadoSetEmpleado = empleadoSetEmpleado.getTipodocumento();
                empleadoSetEmpleado.setTipodocumento(tipodocumento);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
                if (oldTipodocumentoOfEmpleadoSetEmpleado != null) {
                    oldTipodocumentoOfEmpleadoSetEmpleado.getEmpleadoSet().remove(empleadoSetEmpleado);
                    oldTipodocumentoOfEmpleadoSetEmpleado = em.merge(oldTipodocumentoOfEmpleadoSetEmpleado);
                }
            }
            for (Empleado empleadoSet1Empleado : tipodocumento.getEmpleadoSet1()) {
                Tipodocumento oldTipodocumento1OfEmpleadoSet1Empleado = empleadoSet1Empleado.getTipodocumento1();
                empleadoSet1Empleado.setTipodocumento1(tipodocumento);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
                if (oldTipodocumento1OfEmpleadoSet1Empleado != null) {
                    oldTipodocumento1OfEmpleadoSet1Empleado.getEmpleadoSet1().remove(empleadoSet1Empleado);
                    oldTipodocumento1OfEmpleadoSet1Empleado = em.merge(oldTipodocumento1OfEmpleadoSet1Empleado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipodocumento(tipodocumento.getIdtipodocumento()) != null) {
                throw new PreexistingEntityException("Tipodocumento " + tipodocumento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipodocumento tipodocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipodocumento persistentTipodocumento = em.find(Tipodocumento.class, tipodocumento.getIdtipodocumento());
            Set<Responsable> responsableSetOld = persistentTipodocumento.getResponsableSet();
            Set<Responsable> responsableSetNew = tipodocumento.getResponsableSet();
            Set<Responsable> responsableSet1Old = persistentTipodocumento.getResponsableSet1();
            Set<Responsable> responsableSet1New = tipodocumento.getResponsableSet1();
            Set<Empleado> empleadoSetOld = persistentTipodocumento.getEmpleadoSet();
            Set<Empleado> empleadoSetNew = tipodocumento.getEmpleadoSet();
            Set<Empleado> empleadoSet1Old = persistentTipodocumento.getEmpleadoSet1();
            Set<Empleado> empleadoSet1New = tipodocumento.getEmpleadoSet1();
            Set<Responsable> attachedResponsableSetNew = new HashSet<Responsable>();
            for (Responsable responsableSetNewResponsableToAttach : responsableSetNew) {
                responsableSetNewResponsableToAttach = em.getReference(responsableSetNewResponsableToAttach.getClass(), responsableSetNewResponsableToAttach.getIdresponsable());
                attachedResponsableSetNew.add(responsableSetNewResponsableToAttach);
            }
            responsableSetNew = attachedResponsableSetNew;
            tipodocumento.setResponsableSet(responsableSetNew);
            Set<Responsable> attachedResponsableSet1New = new HashSet<Responsable>();
            for (Responsable responsableSet1NewResponsableToAttach : responsableSet1New) {
                responsableSet1NewResponsableToAttach = em.getReference(responsableSet1NewResponsableToAttach.getClass(), responsableSet1NewResponsableToAttach.getIdresponsable());
                attachedResponsableSet1New.add(responsableSet1NewResponsableToAttach);
            }
            responsableSet1New = attachedResponsableSet1New;
            tipodocumento.setResponsableSet1(responsableSet1New);
            Set<Empleado> attachedEmpleadoSetNew = new HashSet<Empleado>();
            for (Empleado empleadoSetNewEmpleadoToAttach : empleadoSetNew) {
                empleadoSetNewEmpleadoToAttach = em.getReference(empleadoSetNewEmpleadoToAttach.getClass(), empleadoSetNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSetNew.add(empleadoSetNewEmpleadoToAttach);
            }
            empleadoSetNew = attachedEmpleadoSetNew;
            tipodocumento.setEmpleadoSet(empleadoSetNew);
            Set<Empleado> attachedEmpleadoSet1New = new HashSet<Empleado>();
            for (Empleado empleadoSet1NewEmpleadoToAttach : empleadoSet1New) {
                empleadoSet1NewEmpleadoToAttach = em.getReference(empleadoSet1NewEmpleadoToAttach.getClass(), empleadoSet1NewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1New.add(empleadoSet1NewEmpleadoToAttach);
            }
            empleadoSet1New = attachedEmpleadoSet1New;
            tipodocumento.setEmpleadoSet1(empleadoSet1New);
            tipodocumento = em.merge(tipodocumento);
            for (Responsable responsableSetOldResponsable : responsableSetOld) {
                if (!responsableSetNew.contains(responsableSetOldResponsable)) {
                    responsableSetOldResponsable.setTipodocumento(null);
                    responsableSetOldResponsable = em.merge(responsableSetOldResponsable);
                }
            }
            for (Responsable responsableSetNewResponsable : responsableSetNew) {
                if (!responsableSetOld.contains(responsableSetNewResponsable)) {
                    Tipodocumento oldTipodocumentoOfResponsableSetNewResponsable = responsableSetNewResponsable.getTipodocumento();
                    responsableSetNewResponsable.setTipodocumento(tipodocumento);
                    responsableSetNewResponsable = em.merge(responsableSetNewResponsable);
                    if (oldTipodocumentoOfResponsableSetNewResponsable != null && !oldTipodocumentoOfResponsableSetNewResponsable.equals(tipodocumento)) {
                        oldTipodocumentoOfResponsableSetNewResponsable.getResponsableSet().remove(responsableSetNewResponsable);
                        oldTipodocumentoOfResponsableSetNewResponsable = em.merge(oldTipodocumentoOfResponsableSetNewResponsable);
                    }
                }
            }
            for (Responsable responsableSet1OldResponsable : responsableSet1Old) {
                if (!responsableSet1New.contains(responsableSet1OldResponsable)) {
                    responsableSet1OldResponsable.setTipodocumento1(null);
                    responsableSet1OldResponsable = em.merge(responsableSet1OldResponsable);
                }
            }
            for (Responsable responsableSet1NewResponsable : responsableSet1New) {
                if (!responsableSet1Old.contains(responsableSet1NewResponsable)) {
                    Tipodocumento oldTipodocumento1OfResponsableSet1NewResponsable = responsableSet1NewResponsable.getTipodocumento1();
                    responsableSet1NewResponsable.setTipodocumento1(tipodocumento);
                    responsableSet1NewResponsable = em.merge(responsableSet1NewResponsable);
                    if (oldTipodocumento1OfResponsableSet1NewResponsable != null && !oldTipodocumento1OfResponsableSet1NewResponsable.equals(tipodocumento)) {
                        oldTipodocumento1OfResponsableSet1NewResponsable.getResponsableSet1().remove(responsableSet1NewResponsable);
                        oldTipodocumento1OfResponsableSet1NewResponsable = em.merge(oldTipodocumento1OfResponsableSet1NewResponsable);
                    }
                }
            }
            for (Empleado empleadoSetOldEmpleado : empleadoSetOld) {
                if (!empleadoSetNew.contains(empleadoSetOldEmpleado)) {
                    empleadoSetOldEmpleado.setTipodocumento(null);
                    empleadoSetOldEmpleado = em.merge(empleadoSetOldEmpleado);
                }
            }
            for (Empleado empleadoSetNewEmpleado : empleadoSetNew) {
                if (!empleadoSetOld.contains(empleadoSetNewEmpleado)) {
                    Tipodocumento oldTipodocumentoOfEmpleadoSetNewEmpleado = empleadoSetNewEmpleado.getTipodocumento();
                    empleadoSetNewEmpleado.setTipodocumento(tipodocumento);
                    empleadoSetNewEmpleado = em.merge(empleadoSetNewEmpleado);
                    if (oldTipodocumentoOfEmpleadoSetNewEmpleado != null && !oldTipodocumentoOfEmpleadoSetNewEmpleado.equals(tipodocumento)) {
                        oldTipodocumentoOfEmpleadoSetNewEmpleado.getEmpleadoSet().remove(empleadoSetNewEmpleado);
                        oldTipodocumentoOfEmpleadoSetNewEmpleado = em.merge(oldTipodocumentoOfEmpleadoSetNewEmpleado);
                    }
                }
            }
            for (Empleado empleadoSet1OldEmpleado : empleadoSet1Old) {
                if (!empleadoSet1New.contains(empleadoSet1OldEmpleado)) {
                    empleadoSet1OldEmpleado.setTipodocumento1(null);
                    empleadoSet1OldEmpleado = em.merge(empleadoSet1OldEmpleado);
                }
            }
            for (Empleado empleadoSet1NewEmpleado : empleadoSet1New) {
                if (!empleadoSet1Old.contains(empleadoSet1NewEmpleado)) {
                    Tipodocumento oldTipodocumento1OfEmpleadoSet1NewEmpleado = empleadoSet1NewEmpleado.getTipodocumento1();
                    empleadoSet1NewEmpleado.setTipodocumento1(tipodocumento);
                    empleadoSet1NewEmpleado = em.merge(empleadoSet1NewEmpleado);
                    if (oldTipodocumento1OfEmpleadoSet1NewEmpleado != null && !oldTipodocumento1OfEmpleadoSet1NewEmpleado.equals(tipodocumento)) {
                        oldTipodocumento1OfEmpleadoSet1NewEmpleado.getEmpleadoSet1().remove(empleadoSet1NewEmpleado);
                        oldTipodocumento1OfEmpleadoSet1NewEmpleado = em.merge(oldTipodocumento1OfEmpleadoSet1NewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipodocumento.getIdtipodocumento();
                if (findTipodocumento(id) == null) {
                    throw new NonexistentEntityException("The tipodocumento with id " + id + " no longer exists.");
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
            Tipodocumento tipodocumento;
            try {
                tipodocumento = em.getReference(Tipodocumento.class, id);
                tipodocumento.getIdtipodocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipodocumento with id " + id + " no longer exists.", enfe);
            }
            Set<Responsable> responsableSet = tipodocumento.getResponsableSet();
            for (Responsable responsableSetResponsable : responsableSet) {
                responsableSetResponsable.setTipodocumento(null);
                responsableSetResponsable = em.merge(responsableSetResponsable);
            }
            Set<Responsable> responsableSet1 = tipodocumento.getResponsableSet1();
            for (Responsable responsableSet1Responsable : responsableSet1) {
                responsableSet1Responsable.setTipodocumento1(null);
                responsableSet1Responsable = em.merge(responsableSet1Responsable);
            }
            Set<Empleado> empleadoSet = tipodocumento.getEmpleadoSet();
            for (Empleado empleadoSetEmpleado : empleadoSet) {
                empleadoSetEmpleado.setTipodocumento(null);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
            }
            Set<Empleado> empleadoSet1 = tipodocumento.getEmpleadoSet1();
            for (Empleado empleadoSet1Empleado : empleadoSet1) {
                empleadoSet1Empleado.setTipodocumento1(null);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
            }
            em.remove(tipodocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipodocumento> findTipodocumentoEntities() {
        return findTipodocumentoEntities(true, -1, -1);
    }

    public List<Tipodocumento> findTipodocumentoEntities(int maxResults, int firstResult) {
        return findTipodocumentoEntities(false, maxResults, firstResult);
    }

    private List<Tipodocumento> findTipodocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipodocumento.class));
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

    public Tipodocumento findTipodocumento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipodocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipodocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipodocumento> rt = cq.from(Tipodocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
