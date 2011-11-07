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
import metalsoft.datos.jpa.entity.Responsable;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Tipodocumento;

/**
 *
 * @author Nino
 */
public class TipodocumentoJpaController implements Serializable {

    public TipodocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipodocumento tipodocumento) throws PreexistingEntityException, Exception {
        if (tipodocumento.getResponsableList() == null) {
            tipodocumento.setResponsableList(new ArrayList<Responsable>());
        }
        if (tipodocumento.getEmpleadoList() == null) {
            tipodocumento.setEmpleadoList(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Responsable> attachedResponsableList = new ArrayList<Responsable>();
            for (Responsable responsableListResponsableToAttach : tipodocumento.getResponsableList()) {
                responsableListResponsableToAttach = em.getReference(responsableListResponsableToAttach.getClass(), responsableListResponsableToAttach.getIdresponsable());
                attachedResponsableList.add(responsableListResponsableToAttach);
            }
            tipodocumento.setResponsableList(attachedResponsableList);
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : tipodocumento.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getIdempleado());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            tipodocumento.setEmpleadoList(attachedEmpleadoList);
            em.persist(tipodocumento);
            for (Responsable responsableListResponsable : tipodocumento.getResponsableList()) {
                Tipodocumento oldTipodocumentoOfResponsableListResponsable = responsableListResponsable.getTipodocumento();
                responsableListResponsable.setTipodocumento(tipodocumento);
                responsableListResponsable = em.merge(responsableListResponsable);
                if (oldTipodocumentoOfResponsableListResponsable != null) {
                    oldTipodocumentoOfResponsableListResponsable.getResponsableList().remove(responsableListResponsable);
                    oldTipodocumentoOfResponsableListResponsable = em.merge(oldTipodocumentoOfResponsableListResponsable);
                }
            }
            for (Empleado empleadoListEmpleado : tipodocumento.getEmpleadoList()) {
                Tipodocumento oldTipodocumentoOfEmpleadoListEmpleado = empleadoListEmpleado.getTipodocumento();
                empleadoListEmpleado.setTipodocumento(tipodocumento);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldTipodocumentoOfEmpleadoListEmpleado != null) {
                    oldTipodocumentoOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldTipodocumentoOfEmpleadoListEmpleado = em.merge(oldTipodocumentoOfEmpleadoListEmpleado);
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
            List<Responsable> responsableListOld = persistentTipodocumento.getResponsableList();
            List<Responsable> responsableListNew = tipodocumento.getResponsableList();
            List<Empleado> empleadoListOld = persistentTipodocumento.getEmpleadoList();
            List<Empleado> empleadoListNew = tipodocumento.getEmpleadoList();
            List<Responsable> attachedResponsableListNew = new ArrayList<Responsable>();
            for (Responsable responsableListNewResponsableToAttach : responsableListNew) {
                responsableListNewResponsableToAttach = em.getReference(responsableListNewResponsableToAttach.getClass(), responsableListNewResponsableToAttach.getIdresponsable());
                attachedResponsableListNew.add(responsableListNewResponsableToAttach);
            }
            responsableListNew = attachedResponsableListNew;
            tipodocumento.setResponsableList(responsableListNew);
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            tipodocumento.setEmpleadoList(empleadoListNew);
            tipodocumento = em.merge(tipodocumento);
            for (Responsable responsableListOldResponsable : responsableListOld) {
                if (!responsableListNew.contains(responsableListOldResponsable)) {
                    responsableListOldResponsable.setTipodocumento(null);
                    responsableListOldResponsable = em.merge(responsableListOldResponsable);
                }
            }
            for (Responsable responsableListNewResponsable : responsableListNew) {
                if (!responsableListOld.contains(responsableListNewResponsable)) {
                    Tipodocumento oldTipodocumentoOfResponsableListNewResponsable = responsableListNewResponsable.getTipodocumento();
                    responsableListNewResponsable.setTipodocumento(tipodocumento);
                    responsableListNewResponsable = em.merge(responsableListNewResponsable);
                    if (oldTipodocumentoOfResponsableListNewResponsable != null && !oldTipodocumentoOfResponsableListNewResponsable.equals(tipodocumento)) {
                        oldTipodocumentoOfResponsableListNewResponsable.getResponsableList().remove(responsableListNewResponsable);
                        oldTipodocumentoOfResponsableListNewResponsable = em.merge(oldTipodocumentoOfResponsableListNewResponsable);
                    }
                }
            }
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    empleadoListOldEmpleado.setTipodocumento(null);
                    empleadoListOldEmpleado = em.merge(empleadoListOldEmpleado);
                }
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Tipodocumento oldTipodocumentoOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getTipodocumento();
                    empleadoListNewEmpleado.setTipodocumento(tipodocumento);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldTipodocumentoOfEmpleadoListNewEmpleado != null && !oldTipodocumentoOfEmpleadoListNewEmpleado.equals(tipodocumento)) {
                        oldTipodocumentoOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldTipodocumentoOfEmpleadoListNewEmpleado = em.merge(oldTipodocumentoOfEmpleadoListNewEmpleado);
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
            List<Responsable> responsableList = tipodocumento.getResponsableList();
            for (Responsable responsableListResponsable : responsableList) {
                responsableListResponsable.setTipodocumento(null);
                responsableListResponsable = em.merge(responsableListResponsable);
            }
            List<Empleado> empleadoList = tipodocumento.getEmpleadoList();
            for (Empleado empleadoListEmpleado : empleadoList) {
                empleadoListEmpleado.setTipodocumento(null);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
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
