/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Tipoiva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Factura;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class TipoivaJpaController {

    public TipoivaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoiva tipoiva) throws PreexistingEntityException, Exception {
        if (tipoiva.getFacturaSet() == null) {
            tipoiva.setFacturaSet(new HashSet<Factura>());
        }
        if (tipoiva.getFacturaSet1() == null) {
            tipoiva.setFacturaSet1(new HashSet<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Factura> attachedFacturaSet = new HashSet<Factura>();
            for (Factura facturaSetFacturaToAttach : tipoiva.getFacturaSet()) {
                facturaSetFacturaToAttach = em.getReference(facturaSetFacturaToAttach.getClass(), facturaSetFacturaToAttach.getIdfactura());
                attachedFacturaSet.add(facturaSetFacturaToAttach);
            }
            tipoiva.setFacturaSet(attachedFacturaSet);
            Set<Factura> attachedFacturaSet1 = new HashSet<Factura>();
            for (Factura facturaSet1FacturaToAttach : tipoiva.getFacturaSet1()) {
                facturaSet1FacturaToAttach = em.getReference(facturaSet1FacturaToAttach.getClass(), facturaSet1FacturaToAttach.getIdfactura());
                attachedFacturaSet1.add(facturaSet1FacturaToAttach);
            }
            tipoiva.setFacturaSet1(attachedFacturaSet1);
            em.persist(tipoiva);
            for (Factura facturaSetFactura : tipoiva.getFacturaSet()) {
                Tipoiva oldTipoivaOfFacturaSetFactura = facturaSetFactura.getTipoiva();
                facturaSetFactura.setTipoiva(tipoiva);
                facturaSetFactura = em.merge(facturaSetFactura);
                if (oldTipoivaOfFacturaSetFactura != null) {
                    oldTipoivaOfFacturaSetFactura.getFacturaSet().remove(facturaSetFactura);
                    oldTipoivaOfFacturaSetFactura = em.merge(oldTipoivaOfFacturaSetFactura);
                }
            }
            for (Factura facturaSet1Factura : tipoiva.getFacturaSet1()) {
                Tipoiva oldTipoiva1OfFacturaSet1Factura = facturaSet1Factura.getTipoiva1();
                facturaSet1Factura.setTipoiva1(tipoiva);
                facturaSet1Factura = em.merge(facturaSet1Factura);
                if (oldTipoiva1OfFacturaSet1Factura != null) {
                    oldTipoiva1OfFacturaSet1Factura.getFacturaSet1().remove(facturaSet1Factura);
                    oldTipoiva1OfFacturaSet1Factura = em.merge(oldTipoiva1OfFacturaSet1Factura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoiva(tipoiva.getIdtipoiva()) != null) {
                throw new PreexistingEntityException("Tipoiva " + tipoiva + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoiva tipoiva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoiva persistentTipoiva = em.find(Tipoiva.class, tipoiva.getIdtipoiva());
            Set<Factura> facturaSetOld = persistentTipoiva.getFacturaSet();
            Set<Factura> facturaSetNew = tipoiva.getFacturaSet();
            Set<Factura> facturaSet1Old = persistentTipoiva.getFacturaSet1();
            Set<Factura> facturaSet1New = tipoiva.getFacturaSet1();
            Set<Factura> attachedFacturaSetNew = new HashSet<Factura>();
            for (Factura facturaSetNewFacturaToAttach : facturaSetNew) {
                facturaSetNewFacturaToAttach = em.getReference(facturaSetNewFacturaToAttach.getClass(), facturaSetNewFacturaToAttach.getIdfactura());
                attachedFacturaSetNew.add(facturaSetNewFacturaToAttach);
            }
            facturaSetNew = attachedFacturaSetNew;
            tipoiva.setFacturaSet(facturaSetNew);
            Set<Factura> attachedFacturaSet1New = new HashSet<Factura>();
            for (Factura facturaSet1NewFacturaToAttach : facturaSet1New) {
                facturaSet1NewFacturaToAttach = em.getReference(facturaSet1NewFacturaToAttach.getClass(), facturaSet1NewFacturaToAttach.getIdfactura());
                attachedFacturaSet1New.add(facturaSet1NewFacturaToAttach);
            }
            facturaSet1New = attachedFacturaSet1New;
            tipoiva.setFacturaSet1(facturaSet1New);
            tipoiva = em.merge(tipoiva);
            for (Factura facturaSetOldFactura : facturaSetOld) {
                if (!facturaSetNew.contains(facturaSetOldFactura)) {
                    facturaSetOldFactura.setTipoiva(null);
                    facturaSetOldFactura = em.merge(facturaSetOldFactura);
                }
            }
            for (Factura facturaSetNewFactura : facturaSetNew) {
                if (!facturaSetOld.contains(facturaSetNewFactura)) {
                    Tipoiva oldTipoivaOfFacturaSetNewFactura = facturaSetNewFactura.getTipoiva();
                    facturaSetNewFactura.setTipoiva(tipoiva);
                    facturaSetNewFactura = em.merge(facturaSetNewFactura);
                    if (oldTipoivaOfFacturaSetNewFactura != null && !oldTipoivaOfFacturaSetNewFactura.equals(tipoiva)) {
                        oldTipoivaOfFacturaSetNewFactura.getFacturaSet().remove(facturaSetNewFactura);
                        oldTipoivaOfFacturaSetNewFactura = em.merge(oldTipoivaOfFacturaSetNewFactura);
                    }
                }
            }
            for (Factura facturaSet1OldFactura : facturaSet1Old) {
                if (!facturaSet1New.contains(facturaSet1OldFactura)) {
                    facturaSet1OldFactura.setTipoiva1(null);
                    facturaSet1OldFactura = em.merge(facturaSet1OldFactura);
                }
            }
            for (Factura facturaSet1NewFactura : facturaSet1New) {
                if (!facturaSet1Old.contains(facturaSet1NewFactura)) {
                    Tipoiva oldTipoiva1OfFacturaSet1NewFactura = facturaSet1NewFactura.getTipoiva1();
                    facturaSet1NewFactura.setTipoiva1(tipoiva);
                    facturaSet1NewFactura = em.merge(facturaSet1NewFactura);
                    if (oldTipoiva1OfFacturaSet1NewFactura != null && !oldTipoiva1OfFacturaSet1NewFactura.equals(tipoiva)) {
                        oldTipoiva1OfFacturaSet1NewFactura.getFacturaSet1().remove(facturaSet1NewFactura);
                        oldTipoiva1OfFacturaSet1NewFactura = em.merge(oldTipoiva1OfFacturaSet1NewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoiva.getIdtipoiva();
                if (findTipoiva(id) == null) {
                    throw new NonexistentEntityException("The tipoiva with id " + id + " no longer exists.");
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
            Tipoiva tipoiva;
            try {
                tipoiva = em.getReference(Tipoiva.class, id);
                tipoiva.getIdtipoiva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoiva with id " + id + " no longer exists.", enfe);
            }
            Set<Factura> facturaSet = tipoiva.getFacturaSet();
            for (Factura facturaSetFactura : facturaSet) {
                facturaSetFactura.setTipoiva(null);
                facturaSetFactura = em.merge(facturaSetFactura);
            }
            Set<Factura> facturaSet1 = tipoiva.getFacturaSet1();
            for (Factura facturaSet1Factura : facturaSet1) {
                facturaSet1Factura.setTipoiva1(null);
                facturaSet1Factura = em.merge(facturaSet1Factura);
            }
            em.remove(tipoiva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoiva> findTipoivaEntities() {
        return findTipoivaEntities(true, -1, -1);
    }

    public List<Tipoiva> findTipoivaEntities(int maxResults, int firstResult) {
        return findTipoivaEntities(false, maxResults, firstResult);
    }

    private List<Tipoiva> findTipoivaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoiva.class));
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

    public Tipoiva findTipoiva(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoiva.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoivaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoiva> rt = cq.from(Tipoiva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
