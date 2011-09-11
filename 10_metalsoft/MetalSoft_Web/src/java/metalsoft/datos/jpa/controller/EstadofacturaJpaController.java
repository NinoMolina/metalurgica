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
import metalsoft.datos.jpa.entity.Estadofactura;
import metalsoft.datos.jpa.entity.Factura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadofacturaJpaController implements Serializable {

    public EstadofacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadofactura estadofactura) throws PreexistingEntityException, Exception {
        if (estadofactura.getFacturaList() == null) {
            estadofactura.setFacturaList(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : estadofactura.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdfactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            estadofactura.setFacturaList(attachedFacturaList);
            em.persist(estadofactura);
            for (Factura facturaListFactura : estadofactura.getFacturaList()) {
                Estadofactura oldEstadoOfFacturaListFactura = facturaListFactura.getEstado();
                facturaListFactura.setEstado(estadofactura);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldEstadoOfFacturaListFactura != null) {
                    oldEstadoOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldEstadoOfFacturaListFactura = em.merge(oldEstadoOfFacturaListFactura);
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
            List<Factura> facturaListOld = persistentEstadofactura.getFacturaList();
            List<Factura> facturaListNew = estadofactura.getFacturaList();
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdfactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            estadofactura.setFacturaList(facturaListNew);
            estadofactura = em.merge(estadofactura);
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    facturaListOldFactura.setEstado(null);
                    facturaListOldFactura = em.merge(facturaListOldFactura);
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Estadofactura oldEstadoOfFacturaListNewFactura = facturaListNewFactura.getEstado();
                    facturaListNewFactura.setEstado(estadofactura);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldEstadoOfFacturaListNewFactura != null && !oldEstadoOfFacturaListNewFactura.equals(estadofactura)) {
                        oldEstadoOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldEstadoOfFacturaListNewFactura = em.merge(oldEstadoOfFacturaListNewFactura);
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
            List<Factura> facturaList = estadofactura.getFacturaList();
            for (Factura facturaListFactura : facturaList) {
                facturaListFactura.setEstado(null);
                facturaListFactura = em.merge(facturaListFactura);
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
