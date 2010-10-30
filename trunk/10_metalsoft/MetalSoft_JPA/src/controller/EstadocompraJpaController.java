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
        if (estadocompra.getCompraSet1() == null) {
            estadocompra.setCompraSet1(new HashSet<Compra>());
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
            Set<Compra> attachedCompraSet1 = new HashSet<Compra>();
            for (Compra compraSet1CompraToAttach : estadocompra.getCompraSet1()) {
                compraSet1CompraToAttach = em.getReference(compraSet1CompraToAttach.getClass(), compraSet1CompraToAttach.getIdcompra());
                attachedCompraSet1.add(compraSet1CompraToAttach);
            }
            estadocompra.setCompraSet1(attachedCompraSet1);
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
            for (Compra compraSet1Compra : estadocompra.getCompraSet1()) {
                Estadocompra oldEstado1OfCompraSet1Compra = compraSet1Compra.getEstado1();
                compraSet1Compra.setEstado1(estadocompra);
                compraSet1Compra = em.merge(compraSet1Compra);
                if (oldEstado1OfCompraSet1Compra != null) {
                    oldEstado1OfCompraSet1Compra.getCompraSet1().remove(compraSet1Compra);
                    oldEstado1OfCompraSet1Compra = em.merge(oldEstado1OfCompraSet1Compra);
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
            Set<Compra> compraSet1Old = persistentEstadocompra.getCompraSet1();
            Set<Compra> compraSet1New = estadocompra.getCompraSet1();
            Set<Compra> attachedCompraSetNew = new HashSet<Compra>();
            for (Compra compraSetNewCompraToAttach : compraSetNew) {
                compraSetNewCompraToAttach = em.getReference(compraSetNewCompraToAttach.getClass(), compraSetNewCompraToAttach.getIdcompra());
                attachedCompraSetNew.add(compraSetNewCompraToAttach);
            }
            compraSetNew = attachedCompraSetNew;
            estadocompra.setCompraSet(compraSetNew);
            Set<Compra> attachedCompraSet1New = new HashSet<Compra>();
            for (Compra compraSet1NewCompraToAttach : compraSet1New) {
                compraSet1NewCompraToAttach = em.getReference(compraSet1NewCompraToAttach.getClass(), compraSet1NewCompraToAttach.getIdcompra());
                attachedCompraSet1New.add(compraSet1NewCompraToAttach);
            }
            compraSet1New = attachedCompraSet1New;
            estadocompra.setCompraSet1(compraSet1New);
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
            for (Compra compraSet1OldCompra : compraSet1Old) {
                if (!compraSet1New.contains(compraSet1OldCompra)) {
                    compraSet1OldCompra.setEstado1(null);
                    compraSet1OldCompra = em.merge(compraSet1OldCompra);
                }
            }
            for (Compra compraSet1NewCompra : compraSet1New) {
                if (!compraSet1Old.contains(compraSet1NewCompra)) {
                    Estadocompra oldEstado1OfCompraSet1NewCompra = compraSet1NewCompra.getEstado1();
                    compraSet1NewCompra.setEstado1(estadocompra);
                    compraSet1NewCompra = em.merge(compraSet1NewCompra);
                    if (oldEstado1OfCompraSet1NewCompra != null && !oldEstado1OfCompraSet1NewCompra.equals(estadocompra)) {
                        oldEstado1OfCompraSet1NewCompra.getCompraSet1().remove(compraSet1NewCompra);
                        oldEstado1OfCompraSet1NewCompra = em.merge(oldEstado1OfCompraSet1NewCompra);
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
            Set<Compra> compraSet1 = estadocompra.getCompraSet1();
            for (Compra compraSet1Compra : compraSet1) {
                compraSet1Compra.setEstado1(null);
                compraSet1Compra = em.merge(compraSet1Compra);
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
