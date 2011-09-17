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
import metalsoft.datos.jpa.entity.Factura;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Tipoiva;

/**
 *
 * @author Nino
 */
public class TipoivaJpaController implements Serializable {

    public TipoivaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoiva tipoiva) throws PreexistingEntityException, Exception {
        if (tipoiva.getFacturaList() == null) {
            tipoiva.setFacturaList(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : tipoiva.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdfactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            tipoiva.setFacturaList(attachedFacturaList);
            em.persist(tipoiva);
            for (Factura facturaListFactura : tipoiva.getFacturaList()) {
                Tipoiva oldTipoivaOfFacturaListFactura = facturaListFactura.getTipoiva();
                facturaListFactura.setTipoiva(tipoiva);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldTipoivaOfFacturaListFactura != null) {
                    oldTipoivaOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldTipoivaOfFacturaListFactura = em.merge(oldTipoivaOfFacturaListFactura);
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
            List<Factura> facturaListOld = persistentTipoiva.getFacturaList();
            List<Factura> facturaListNew = tipoiva.getFacturaList();
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdfactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            tipoiva.setFacturaList(facturaListNew);
            tipoiva = em.merge(tipoiva);
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    facturaListOldFactura.setTipoiva(null);
                    facturaListOldFactura = em.merge(facturaListOldFactura);
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Tipoiva oldTipoivaOfFacturaListNewFactura = facturaListNewFactura.getTipoiva();
                    facturaListNewFactura.setTipoiva(tipoiva);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldTipoivaOfFacturaListNewFactura != null && !oldTipoivaOfFacturaListNewFactura.equals(tipoiva)) {
                        oldTipoivaOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldTipoivaOfFacturaListNewFactura = em.merge(oldTipoivaOfFacturaListNewFactura);
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
            List<Factura> facturaList = tipoiva.getFacturaList();
            for (Factura facturaListFactura : facturaList) {
                facturaListFactura.setTipoiva(null);
                facturaListFactura = em.merge(facturaListFactura);
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
