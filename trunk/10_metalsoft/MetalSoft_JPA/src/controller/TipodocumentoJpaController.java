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
        if (tipodocumento.getEmpleadoSet() == null) {
            tipodocumento.setEmpleadoSet(new HashSet<Empleado>());
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
            Set<Empleado> attachedEmpleadoSet = new HashSet<Empleado>();
            for (Empleado empleadoSetEmpleadoToAttach : tipodocumento.getEmpleadoSet()) {
                empleadoSetEmpleadoToAttach = em.getReference(empleadoSetEmpleadoToAttach.getClass(), empleadoSetEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet.add(empleadoSetEmpleadoToAttach);
            }
            tipodocumento.setEmpleadoSet(attachedEmpleadoSet);
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
            for (Empleado empleadoSetEmpleado : tipodocumento.getEmpleadoSet()) {
                Tipodocumento oldTipodocumentoOfEmpleadoSetEmpleado = empleadoSetEmpleado.getTipodocumento();
                empleadoSetEmpleado.setTipodocumento(tipodocumento);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
                if (oldTipodocumentoOfEmpleadoSetEmpleado != null) {
                    oldTipodocumentoOfEmpleadoSetEmpleado.getEmpleadoSet().remove(empleadoSetEmpleado);
                    oldTipodocumentoOfEmpleadoSetEmpleado = em.merge(oldTipodocumentoOfEmpleadoSetEmpleado);
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
            Set<Empleado> empleadoSetOld = persistentTipodocumento.getEmpleadoSet();
            Set<Empleado> empleadoSetNew = tipodocumento.getEmpleadoSet();
            Set<Responsable> attachedResponsableSetNew = new HashSet<Responsable>();
            for (Responsable responsableSetNewResponsableToAttach : responsableSetNew) {
                responsableSetNewResponsableToAttach = em.getReference(responsableSetNewResponsableToAttach.getClass(), responsableSetNewResponsableToAttach.getIdresponsable());
                attachedResponsableSetNew.add(responsableSetNewResponsableToAttach);
            }
            responsableSetNew = attachedResponsableSetNew;
            tipodocumento.setResponsableSet(responsableSetNew);
            Set<Empleado> attachedEmpleadoSetNew = new HashSet<Empleado>();
            for (Empleado empleadoSetNewEmpleadoToAttach : empleadoSetNew) {
                empleadoSetNewEmpleadoToAttach = em.getReference(empleadoSetNewEmpleadoToAttach.getClass(), empleadoSetNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSetNew.add(empleadoSetNewEmpleadoToAttach);
            }
            empleadoSetNew = attachedEmpleadoSetNew;
            tipodocumento.setEmpleadoSet(empleadoSetNew);
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
            Set<Empleado> empleadoSet = tipodocumento.getEmpleadoSet();
            for (Empleado empleadoSetEmpleado : empleadoSet) {
                empleadoSetEmpleado.setTipodocumento(null);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
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
