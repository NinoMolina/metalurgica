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
import metalsoft.datos.jpa.entity.Compra;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Estadocompra;

/**
 *
 * @author Nino
 */
public class EstadocompraJpaController implements Serializable {

    public EstadocompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadocompra estadocompra) throws PreexistingEntityException, Exception {
        if (estadocompra.getCompraList() == null) {
            estadocompra.setCompraList(new ArrayList<Compra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Compra> attachedCompraList = new ArrayList<Compra>();
            for (Compra compraListCompraToAttach : estadocompra.getCompraList()) {
                compraListCompraToAttach = em.getReference(compraListCompraToAttach.getClass(), compraListCompraToAttach.getIdcompra());
                attachedCompraList.add(compraListCompraToAttach);
            }
            estadocompra.setCompraList(attachedCompraList);
            em.persist(estadocompra);
            for (Compra compraListCompra : estadocompra.getCompraList()) {
                Estadocompra oldEstadoOfCompraListCompra = compraListCompra.getEstado();
                compraListCompra.setEstado(estadocompra);
                compraListCompra = em.merge(compraListCompra);
                if (oldEstadoOfCompraListCompra != null) {
                    oldEstadoOfCompraListCompra.getCompraList().remove(compraListCompra);
                    oldEstadoOfCompraListCompra = em.merge(oldEstadoOfCompraListCompra);
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
            List<Compra> compraListOld = persistentEstadocompra.getCompraList();
            List<Compra> compraListNew = estadocompra.getCompraList();
            List<Compra> attachedCompraListNew = new ArrayList<Compra>();
            for (Compra compraListNewCompraToAttach : compraListNew) {
                compraListNewCompraToAttach = em.getReference(compraListNewCompraToAttach.getClass(), compraListNewCompraToAttach.getIdcompra());
                attachedCompraListNew.add(compraListNewCompraToAttach);
            }
            compraListNew = attachedCompraListNew;
            estadocompra.setCompraList(compraListNew);
            estadocompra = em.merge(estadocompra);
            for (Compra compraListOldCompra : compraListOld) {
                if (!compraListNew.contains(compraListOldCompra)) {
                    compraListOldCompra.setEstado(null);
                    compraListOldCompra = em.merge(compraListOldCompra);
                }
            }
            for (Compra compraListNewCompra : compraListNew) {
                if (!compraListOld.contains(compraListNewCompra)) {
                    Estadocompra oldEstadoOfCompraListNewCompra = compraListNewCompra.getEstado();
                    compraListNewCompra.setEstado(estadocompra);
                    compraListNewCompra = em.merge(compraListNewCompra);
                    if (oldEstadoOfCompraListNewCompra != null && !oldEstadoOfCompraListNewCompra.equals(estadocompra)) {
                        oldEstadoOfCompraListNewCompra.getCompraList().remove(compraListNewCompra);
                        oldEstadoOfCompraListNewCompra = em.merge(oldEstadoOfCompraListNewCompra);
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
            List<Compra> compraList = estadocompra.getCompraList();
            for (Compra compraListCompra : compraList) {
                compraListCompra.setEstado(null);
                compraListCompra = em.merge(compraListCompra);
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
