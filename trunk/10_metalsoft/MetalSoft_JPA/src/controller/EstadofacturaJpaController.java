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
            Set<Factura> attachedFacturaSetNew = new HashSet<Factura>();
            for (Factura facturaSetNewFacturaToAttach : facturaSetNew) {
                facturaSetNewFacturaToAttach = em.getReference(facturaSetNewFacturaToAttach.getClass(), facturaSetNewFacturaToAttach.getIdfactura());
                attachedFacturaSetNew.add(facturaSetNewFacturaToAttach);
            }
            facturaSetNew = attachedFacturaSetNew;
            estadofactura.setFacturaSet(facturaSetNew);
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
