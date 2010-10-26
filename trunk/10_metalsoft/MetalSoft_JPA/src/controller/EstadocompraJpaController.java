/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadocompra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Compra;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadocompraJpaController {

    public EstadocompraJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadocompra estadocompra) throws PreexistingEntityException, Exception {
        if (estadocompra.getCompraSet() == null) {
            estadocompra.setCompraSet(new HashSet<Compra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Compra> attachedCompraSet = new HashSet<Compra>();
            for (Compra compraSetCompraToAttach : estadocompra.getCompraSet()) {
                compraSetCompraToAttach = em.getReference(compraSetCompraToAttach.getClass(), compraSetCompraToAttach.getIdcompra());
                attachedCompraSet.add(compraSetCompraToAttach);
            }
            estadocompra.setCompraSet(attachedCompraSet);
            em.persist(estadocompra);
            for (Compra compraSetCompra : estadocompra.getCompraSet()) {
                Estadocompra oldEstadoOfCompraSetCompra = compraSetCompra.getEstado();
                compraSetCompra.setEstado(estadocompra);
                compraSetCompra = em.merge(compraSetCompra);
                if (oldEstadoOfCompraSetCompra != null) {
                    oldEstadoOfCompraSetCompra.getCompraSet().remove(compraSetCompra);
                    oldEstadoOfCompraSetCompra = em.merge(oldEstadoOfCompraSetCompra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadocompra(estadocompra.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadocompra " + estadocompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadocompra estadocompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadocompra persistentEstadocompra = em.find(Estadocompra.class, estadocompra.getIdestado());
            Set<Compra> compraSetOld = persistentEstadocompra.getCompraSet();
            Set<Compra> compraSetNew = estadocompra.getCompraSet();
            Set<Compra> attachedCompraSetNew = new HashSet<Compra>();
            for (Compra compraSetNewCompraToAttach : compraSetNew) {
                compraSetNewCompraToAttach = em.getReference(compraSetNewCompraToAttach.getClass(), compraSetNewCompraToAttach.getIdcompra());
                attachedCompraSetNew.add(compraSetNewCompraToAttach);
            }
            compraSetNew = attachedCompraSetNew;
            estadocompra.setCompraSet(compraSetNew);
            estadocompra = em.merge(estadocompra);
            for (Compra compraSetOldCompra : compraSetOld) {
                if (!compraSetNew.contains(compraSetOldCompra)) {
                    compraSetOldCompra.setEstado(null);
                    compraSetOldCompra = em.merge(compraSetOldCompra);
                }
            }
            for (Compra compraSetNewCompra : compraSetNew) {
                if (!compraSetOld.contains(compraSetNewCompra)) {
                    Estadocompra oldEstadoOfCompraSetNewCompra = compraSetNewCompra.getEstado();
                    compraSetNewCompra.setEstado(estadocompra);
                    compraSetNewCompra = em.merge(compraSetNewCompra);
                    if (oldEstadoOfCompraSetNewCompra != null && !oldEstadoOfCompraSetNewCompra.equals(estadocompra)) {
                        oldEstadoOfCompraSetNewCompra.getCompraSet().remove(compraSetNewCompra);
                        oldEstadoOfCompraSetNewCompra = em.merge(oldEstadoOfCompraSetNewCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadocompra.getIdestado();
                if (findEstadocompra(id) == null) {
                    throw new NonexistentEntityException("The estadocompra with id " + id + " no longer exists.");
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
            Estadocompra estadocompra;
            try {
                estadocompra = em.getReference(Estadocompra.class, id);
                estadocompra.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadocompra with id " + id + " no longer exists.", enfe);
            }
            Set<Compra> compraSet = estadocompra.getCompraSet();
            for (Compra compraSetCompra : compraSet) {
                compraSetCompra.setEstado(null);
                compraSetCompra = em.merge(compraSetCompra);
            }
            em.remove(estadocompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadocompra> findEstadocompraEntities() {
        return findEstadocompraEntities(true, -1, -1);
    }

    public List<Estadocompra> findEstadocompraEntities(int maxResults, int firstResult) {
        return findEstadocompraEntities(false, maxResults, firstResult);
    }

    private List<Estadocompra> findEstadocompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadocompra.class));
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

    public Estadocompra findEstadocompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadocompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadocompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadocompra> rt = cq.from(Estadocompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
