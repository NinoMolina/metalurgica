/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detallereclamoempresamantenimiento;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Reclamoempresamantenimiento;

/**
 *
 * @author Nino
 */
public class ReclamoempresamantenimientoJpaController {

    public ReclamoempresamantenimientoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reclamoempresamantenimiento reclamoempresamantenimiento) throws PreexistingEntityException, Exception {
        if (reclamoempresamantenimiento.getDetallereclamoempresamantenimientoList() == null) {
            reclamoempresamantenimiento.setDetallereclamoempresamantenimientoList(new ArrayList<Detallereclamoempresamantenimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallereclamoempresamantenimiento> attachedDetallereclamoempresamantenimientoList = new ArrayList<Detallereclamoempresamantenimiento>();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach : reclamoempresamantenimiento.getDetallereclamoempresamantenimientoList()) {
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach = em.getReference(detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach.getClass(), detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach.getIddetalle());
                attachedDetallereclamoempresamantenimientoList.add(detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach);
            }
            reclamoempresamantenimiento.setDetallereclamoempresamantenimientoList(attachedDetallereclamoempresamantenimientoList);
            em.persist(reclamoempresamantenimiento);
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento : reclamoempresamantenimiento.getDetallereclamoempresamantenimientoList()) {
                Reclamoempresamantenimiento oldIdreclamoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.getIdreclamo();
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.setIdreclamo(reclamoempresamantenimiento);
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                if (oldIdreclamoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento != null) {
                    oldIdreclamoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                    oldIdreclamoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = em.merge(oldIdreclamoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReclamoempresamantenimiento(reclamoempresamantenimiento.getIdreclamo()) != null) {
                throw new PreexistingEntityException("Reclamoempresamantenimiento " + reclamoempresamantenimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reclamoempresamantenimiento reclamoempresamantenimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamoempresamantenimiento persistentReclamoempresamantenimiento = em.find(Reclamoempresamantenimiento.class, reclamoempresamantenimiento.getIdreclamo());
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoListOld = persistentReclamoempresamantenimiento.getDetallereclamoempresamantenimientoList();
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoListNew = reclamoempresamantenimiento.getDetallereclamoempresamantenimientoList();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoListOld) {
                if (!detallereclamoempresamantenimientoListNew.contains(detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoempresamantenimiento " + detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento + " since its idreclamo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detallereclamoempresamantenimiento> attachedDetallereclamoempresamantenimientoListNew = new ArrayList<Detallereclamoempresamantenimiento>();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach : detallereclamoempresamantenimientoListNew) {
                detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach = em.getReference(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach.getClass(), detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach.getIddetalle());
                attachedDetallereclamoempresamantenimientoListNew.add(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach);
            }
            detallereclamoempresamantenimientoListNew = attachedDetallereclamoempresamantenimientoListNew;
            reclamoempresamantenimiento.setDetallereclamoempresamantenimientoList(detallereclamoempresamantenimientoListNew);
            reclamoempresamantenimiento = em.merge(reclamoempresamantenimiento);
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoListNew) {
                if (!detallereclamoempresamantenimientoListOld.contains(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento)) {
                    Reclamoempresamantenimiento oldIdreclamoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.getIdreclamo();
                    detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.setIdreclamo(reclamoempresamantenimiento);
                    detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                    if (oldIdreclamoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento != null && !oldIdreclamoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.equals(reclamoempresamantenimiento)) {
                        oldIdreclamoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                        oldIdreclamoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = em.merge(oldIdreclamoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reclamoempresamantenimiento.getIdreclamo();
                if (findReclamoempresamantenimiento(id) == null) {
                    throw new NonexistentEntityException("The reclamoempresamantenimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamoempresamantenimiento reclamoempresamantenimiento;
            try {
                reclamoempresamantenimiento = em.getReference(Reclamoempresamantenimiento.class, id);
                reclamoempresamantenimiento.getIdreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reclamoempresamantenimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoListOrphanCheck = reclamoempresamantenimiento.getDetallereclamoempresamantenimientoList();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListOrphanCheckDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamoempresamantenimiento (" + reclamoempresamantenimiento + ") cannot be destroyed since the Detallereclamoempresamantenimiento " + detallereclamoempresamantenimientoListOrphanCheckDetallereclamoempresamantenimiento + " in its detallereclamoempresamantenimientoList field has a non-nullable idreclamo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(reclamoempresamantenimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reclamoempresamantenimiento> findReclamoempresamantenimientoEntities() {
        return findReclamoempresamantenimientoEntities(true, -1, -1);
    }

    public List<Reclamoempresamantenimiento> findReclamoempresamantenimientoEntities(int maxResults, int firstResult) {
        return findReclamoempresamantenimientoEntities(false, maxResults, firstResult);
    }

    private List<Reclamoempresamantenimiento> findReclamoempresamantenimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reclamoempresamantenimiento.class));
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

    public Reclamoempresamantenimiento findReclamoempresamantenimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reclamoempresamantenimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getReclamoempresamantenimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reclamoempresamantenimiento> rt = cq.from(Reclamoempresamantenimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
