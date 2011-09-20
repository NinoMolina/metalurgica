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
import metalsoft.datos.jpa.entity.Estadopiezareal;
import metalsoft.datos.jpa.entity.Piezareal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadopiezarealJpaController implements Serializable {

    public EstadopiezarealJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadopiezareal estadopiezareal) throws PreexistingEntityException, Exception {
        if (estadopiezareal.getPiezarealList() == null) {
            estadopiezareal.setPiezarealList(new ArrayList<Piezareal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Piezareal> attachedPiezarealList = new ArrayList<Piezareal>();
            for (Piezareal piezarealListPiezarealToAttach : estadopiezareal.getPiezarealList()) {
                piezarealListPiezarealToAttach = em.getReference(piezarealListPiezarealToAttach.getClass(), piezarealListPiezarealToAttach.getIdpiezareal());
                attachedPiezarealList.add(piezarealListPiezarealToAttach);
            }
            estadopiezareal.setPiezarealList(attachedPiezarealList);
            em.persist(estadopiezareal);
            for (Piezareal piezarealListPiezareal : estadopiezareal.getPiezarealList()) {
                Estadopiezareal oldEstadoOfPiezarealListPiezareal = piezarealListPiezareal.getEstado();
                piezarealListPiezareal.setEstado(estadopiezareal);
                piezarealListPiezareal = em.merge(piezarealListPiezareal);
                if (oldEstadoOfPiezarealListPiezareal != null) {
                    oldEstadoOfPiezarealListPiezareal.getPiezarealList().remove(piezarealListPiezareal);
                    oldEstadoOfPiezarealListPiezareal = em.merge(oldEstadoOfPiezarealListPiezareal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadopiezareal(estadopiezareal.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadopiezareal " + estadopiezareal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadopiezareal estadopiezareal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopiezareal persistentEstadopiezareal = em.find(Estadopiezareal.class, estadopiezareal.getIdestado());
            List<Piezareal> piezarealListOld = persistentEstadopiezareal.getPiezarealList();
            List<Piezareal> piezarealListNew = estadopiezareal.getPiezarealList();
            List<Piezareal> attachedPiezarealListNew = new ArrayList<Piezareal>();
            for (Piezareal piezarealListNewPiezarealToAttach : piezarealListNew) {
                piezarealListNewPiezarealToAttach = em.getReference(piezarealListNewPiezarealToAttach.getClass(), piezarealListNewPiezarealToAttach.getIdpiezareal());
                attachedPiezarealListNew.add(piezarealListNewPiezarealToAttach);
            }
            piezarealListNew = attachedPiezarealListNew;
            estadopiezareal.setPiezarealList(piezarealListNew);
            estadopiezareal = em.merge(estadopiezareal);
            for (Piezareal piezarealListOldPiezareal : piezarealListOld) {
                if (!piezarealListNew.contains(piezarealListOldPiezareal)) {
                    piezarealListOldPiezareal.setEstado(null);
                    piezarealListOldPiezareal = em.merge(piezarealListOldPiezareal);
                }
            }
            for (Piezareal piezarealListNewPiezareal : piezarealListNew) {
                if (!piezarealListOld.contains(piezarealListNewPiezareal)) {
                    Estadopiezareal oldEstadoOfPiezarealListNewPiezareal = piezarealListNewPiezareal.getEstado();
                    piezarealListNewPiezareal.setEstado(estadopiezareal);
                    piezarealListNewPiezareal = em.merge(piezarealListNewPiezareal);
                    if (oldEstadoOfPiezarealListNewPiezareal != null && !oldEstadoOfPiezarealListNewPiezareal.equals(estadopiezareal)) {
                        oldEstadoOfPiezarealListNewPiezareal.getPiezarealList().remove(piezarealListNewPiezareal);
                        oldEstadoOfPiezarealListNewPiezareal = em.merge(oldEstadoOfPiezarealListNewPiezareal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadopiezareal.getIdestado();
                if (findEstadopiezareal(id) == null) {
                    throw new NonexistentEntityException("The estadopiezareal with id " + id + " no longer exists.");
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
            Estadopiezareal estadopiezareal;
            try {
                estadopiezareal = em.getReference(Estadopiezareal.class, id);
                estadopiezareal.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadopiezareal with id " + id + " no longer exists.", enfe);
            }
            List<Piezareal> piezarealList = estadopiezareal.getPiezarealList();
            for (Piezareal piezarealListPiezareal : piezarealList) {
                piezarealListPiezareal.setEstado(null);
                piezarealListPiezareal = em.merge(piezarealListPiezareal);
            }
            em.remove(estadopiezareal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadopiezareal> findEstadopiezarealEntities() {
        return findEstadopiezarealEntities(true, -1, -1);
    }

    public List<Estadopiezareal> findEstadopiezarealEntities(int maxResults, int firstResult) {
        return findEstadopiezarealEntities(false, maxResults, firstResult);
    }

    private List<Estadopiezareal> findEstadopiezarealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadopiezareal.class));
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

    public Estadopiezareal findEstadopiezareal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadopiezareal.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadopiezarealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadopiezareal> rt = cq.from(Estadopiezareal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
