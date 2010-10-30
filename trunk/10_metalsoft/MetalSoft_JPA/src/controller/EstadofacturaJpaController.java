/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadofactura;
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
public class EstadofacturaJpaController {

    public EstadofacturaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadofactura estadofactura) throws PreexistingEntityException, Exception {
        if (estadofactura.getFacturaSet() == null) {
            estadofactura.setFacturaSet(new HashSet<Factura>());
        }
        if (estadofactura.getFacturaSet1() == null) {
            estadofactura.setFacturaSet1(new HashSet<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Factura> attachedFacturaSet = new HashSet<Factura>();
            for (Factura facturaSetFacturaToAttach : estadofactura.getFacturaSet()) {
                facturaSetFacturaToAttach = em.getReference(facturaSetFacturaToAttach.getClass(), facturaSetFacturaToAttach.getIdfactura());
                attachedFacturaSet.add(facturaSetFacturaToAttach);
            }
            estadofactura.setFacturaSet(attachedFacturaSet);
            Set<Factura> attachedFacturaSet1 = new HashSet<Factura>();
            for (Factura facturaSet1FacturaToAttach : estadofactura.getFacturaSet1()) {
                facturaSet1FacturaToAttach = em.getReference(facturaSet1FacturaToAttach.getClass(), facturaSet1FacturaToAttach.getIdfactura());
                attachedFacturaSet1.add(facturaSet1FacturaToAttach);
            }
            estadofactura.setFacturaSet1(attachedFacturaSet1);
            em.persist(estadofactura);
            for (Factura facturaSetFactura : estadofactura.getFacturaSet()) {
                Estadofactura oldEstadoOfFacturaSetFactura = facturaSetFactura.getEstado();
                facturaSetFactura.setEstado(estadofactura);
                facturaSetFactura = em.merge(facturaSetFactura);
                if (oldEstadoOfFacturaSetFactura != null) {
                    oldEstadoOfFacturaSetFactura.getFacturaSet().remove(facturaSetFactura);
                    oldEstadoOfFacturaSetFactura = em.merge(oldEstadoOfFacturaSetFactura);
                }
            }
            for (Factura facturaSet1Factura : estadofactura.getFacturaSet1()) {
                Estadofactura oldEstado1OfFacturaSet1Factura = facturaSet1Factura.getEstado1();
                facturaSet1Factura.setEstado1(estadofactura);
                facturaSet1Factura = em.merge(facturaSet1Factura);
                if (oldEstado1OfFacturaSet1Factura != null) {
                    oldEstado1OfFacturaSet1Factura.getFacturaSet1().remove(facturaSet1Factura);
                    oldEstado1OfFacturaSet1Factura = em.merge(oldEstado1OfFacturaSet1Factura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadofactura(estadofactura.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadofactura " + estadofactura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadofactura estadofactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadofactura persistentEstadofactura = em.find(Estadofactura.class, estadofactura.getIdestado());
            Set<Factura> facturaSetOld = persistentEstadofactura.getFacturaSet();
            Set<Factura> facturaSetNew = estadofactura.getFacturaSet();
            Set<Factura> facturaSet1Old = persistentEstadofactura.getFacturaSet1();
            Set<Factura> facturaSet1New = estadofactura.getFacturaSet1();
            Set<Factura> attachedFacturaSetNew = new HashSet<Factura>();
            for (Factura facturaSetNewFacturaToAttach : facturaSetNew) {
                facturaSetNewFacturaToAttach = em.getReference(facturaSetNewFacturaToAttach.getClass(), facturaSetNewFacturaToAttach.getIdfactura());
                attachedFacturaSetNew.add(facturaSetNewFacturaToAttach);
            }
            facturaSetNew = attachedFacturaSetNew;
            estadofactura.setFacturaSet(facturaSetNew);
            Set<Factura> attachedFacturaSet1New = new HashSet<Factura>();
            for (Factura facturaSet1NewFacturaToAttach : facturaSet1New) {
                facturaSet1NewFacturaToAttach = em.getReference(facturaSet1NewFacturaToAttach.getClass(), facturaSet1NewFacturaToAttach.getIdfactura());
                attachedFacturaSet1New.add(facturaSet1NewFacturaToAttach);
            }
            facturaSet1New = attachedFacturaSet1New;
            estadofactura.setFacturaSet1(facturaSet1New);
            estadofactura = em.merge(estadofactura);
            for (Factura facturaSetOldFactura : facturaSetOld) {
                if (!facturaSetNew.contains(facturaSetOldFactura)) {
                    facturaSetOldFactura.setEstado(null);
                    facturaSetOldFactura = em.merge(facturaSetOldFactura);
                }
            }
            for (Factura facturaSetNewFactura : facturaSetNew) {
                if (!facturaSetOld.contains(facturaSetNewFactura)) {
                    Estadofactura oldEstadoOfFacturaSetNewFactura = facturaSetNewFactura.getEstado();
                    facturaSetNewFactura.setEstado(estadofactura);
                    facturaSetNewFactura = em.merge(facturaSetNewFactura);
                    if (oldEstadoOfFacturaSetNewFactura != null && !oldEstadoOfFacturaSetNewFactura.equals(estadofactura)) {
                        oldEstadoOfFacturaSetNewFactura.getFacturaSet().remove(facturaSetNewFactura);
                        oldEstadoOfFacturaSetNewFactura = em.merge(oldEstadoOfFacturaSetNewFactura);
                    }
                }
            }
            for (Factura facturaSet1OldFactura : facturaSet1Old) {
                if (!facturaSet1New.contains(facturaSet1OldFactura)) {
                    facturaSet1OldFactura.setEstado1(null);
                    facturaSet1OldFactura = em.merge(facturaSet1OldFactura);
                }
            }
            for (Factura facturaSet1NewFactura : facturaSet1New) {
                if (!facturaSet1Old.contains(facturaSet1NewFactura)) {
                    Estadofactura oldEstado1OfFacturaSet1NewFactura = facturaSet1NewFactura.getEstado1();
                    facturaSet1NewFactura.setEstado1(estadofactura);
                    facturaSet1NewFactura = em.merge(facturaSet1NewFactura);
                    if (oldEstado1OfFacturaSet1NewFactura != null && !oldEstado1OfFacturaSet1NewFactura.equals(estadofactura)) {
                        oldEstado1OfFacturaSet1NewFactura.getFacturaSet1().remove(facturaSet1NewFactura);
                        oldEstado1OfFacturaSet1NewFactura = em.merge(oldEstado1OfFacturaSet1NewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadofactura.getIdestado();
                if (findEstadofactura(id) == null) {
                    throw new NonexistentEntityException("The estadofactura with id " + id + " no longer exists.");
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
            Estadofactura estadofactura;
            try {
                estadofactura = em.getReference(Estadofactura.class, id);
                estadofactura.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadofactura with id " + id + " no longer exists.", enfe);
            }
            Set<Factura> facturaSet = estadofactura.getFacturaSet();
            for (Factura facturaSetFactura : facturaSet) {
                facturaSetFactura.setEstado(null);
                facturaSetFactura = em.merge(facturaSetFactura);
            }
            Set<Factura> facturaSet1 = estadofactura.getFacturaSet1();
            for (Factura facturaSet1Factura : facturaSet1) {
                facturaSet1Factura.setEstado1(null);
                facturaSet1Factura = em.merge(facturaSet1Factura);
            }
            em.remove(estadofactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadofactura> findEstadofacturaEntities() {
        return findEstadofacturaEntities(true, -1, -1);
    }

    public List<Estadofactura> findEstadofacturaEntities(int maxResults, int firstResult) {
        return findEstadofacturaEntities(false, maxResults, firstResult);
    }

    private List<Estadofactura> findEstadofacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadofactura.class));
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

    public Estadofactura findEstadofactura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadofactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadofacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadofactura> rt = cq.from(Estadofactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
