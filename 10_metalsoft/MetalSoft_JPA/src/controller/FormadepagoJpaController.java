/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Formadepago;
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
import entity.Comprobantepago;

/**
 *
 * @author Nino
 */
public class FormadepagoJpaController {

    public FormadepagoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formadepago formadepago) throws PreexistingEntityException, Exception {
        if (formadepago.getFacturaSet() == null) {
            formadepago.setFacturaSet(new HashSet<Factura>());
        }
        if (formadepago.getComprobantepagoSet() == null) {
            formadepago.setComprobantepagoSet(new HashSet<Comprobantepago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Factura> attachedFacturaSet = new HashSet<Factura>();
            for (Factura facturaSetFacturaToAttach : formadepago.getFacturaSet()) {
                facturaSetFacturaToAttach = em.getReference(facturaSetFacturaToAttach.getClass(), facturaSetFacturaToAttach.getIdfactura());
                attachedFacturaSet.add(facturaSetFacturaToAttach);
            }
            formadepago.setFacturaSet(attachedFacturaSet);
            Set<Comprobantepago> attachedComprobantepagoSet = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetComprobantepagoToAttach : formadepago.getComprobantepagoSet()) {
                comprobantepagoSetComprobantepagoToAttach = em.getReference(comprobantepagoSetComprobantepagoToAttach.getClass(), comprobantepagoSetComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet.add(comprobantepagoSetComprobantepagoToAttach);
            }
            formadepago.setComprobantepagoSet(attachedComprobantepagoSet);
            em.persist(formadepago);
            for (Factura facturaSetFactura : formadepago.getFacturaSet()) {
                Formadepago oldFormapagoOfFacturaSetFactura = facturaSetFactura.getFormapago();
                facturaSetFactura.setFormapago(formadepago);
                facturaSetFactura = em.merge(facturaSetFactura);
                if (oldFormapagoOfFacturaSetFactura != null) {
                    oldFormapagoOfFacturaSetFactura.getFacturaSet().remove(facturaSetFactura);
                    oldFormapagoOfFacturaSetFactura = em.merge(oldFormapagoOfFacturaSetFactura);
                }
            }
            for (Comprobantepago comprobantepagoSetComprobantepago : formadepago.getComprobantepagoSet()) {
                Formadepago oldFormadepagoOfComprobantepagoSetComprobantepago = comprobantepagoSetComprobantepago.getFormadepago();
                comprobantepagoSetComprobantepago.setFormadepago(formadepago);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
                if (oldFormadepagoOfComprobantepagoSetComprobantepago != null) {
                    oldFormadepagoOfComprobantepagoSetComprobantepago.getComprobantepagoSet().remove(comprobantepagoSetComprobantepago);
                    oldFormadepagoOfComprobantepagoSetComprobantepago = em.merge(oldFormadepagoOfComprobantepagoSetComprobantepago);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormadepago(formadepago.getIdformapago()) != null) {
                throw new PreexistingEntityException("Formadepago " + formadepago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formadepago formadepago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formadepago persistentFormadepago = em.find(Formadepago.class, formadepago.getIdformapago());
            Set<Factura> facturaSetOld = persistentFormadepago.getFacturaSet();
            Set<Factura> facturaSetNew = formadepago.getFacturaSet();
            Set<Comprobantepago> comprobantepagoSetOld = persistentFormadepago.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSetNew = formadepago.getComprobantepagoSet();
            Set<Factura> attachedFacturaSetNew = new HashSet<Factura>();
            for (Factura facturaSetNewFacturaToAttach : facturaSetNew) {
                facturaSetNewFacturaToAttach = em.getReference(facturaSetNewFacturaToAttach.getClass(), facturaSetNewFacturaToAttach.getIdfactura());
                attachedFacturaSetNew.add(facturaSetNewFacturaToAttach);
            }
            facturaSetNew = attachedFacturaSetNew;
            formadepago.setFacturaSet(facturaSetNew);
            Set<Comprobantepago> attachedComprobantepagoSetNew = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetNewComprobantepagoToAttach : comprobantepagoSetNew) {
                comprobantepagoSetNewComprobantepagoToAttach = em.getReference(comprobantepagoSetNewComprobantepagoToAttach.getClass(), comprobantepagoSetNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSetNew.add(comprobantepagoSetNewComprobantepagoToAttach);
            }
            comprobantepagoSetNew = attachedComprobantepagoSetNew;
            formadepago.setComprobantepagoSet(comprobantepagoSetNew);
            formadepago = em.merge(formadepago);
            for (Factura facturaSetOldFactura : facturaSetOld) {
                if (!facturaSetNew.contains(facturaSetOldFactura)) {
                    facturaSetOldFactura.setFormapago(null);
                    facturaSetOldFactura = em.merge(facturaSetOldFactura);
                }
            }
            for (Factura facturaSetNewFactura : facturaSetNew) {
                if (!facturaSetOld.contains(facturaSetNewFactura)) {
                    Formadepago oldFormapagoOfFacturaSetNewFactura = facturaSetNewFactura.getFormapago();
                    facturaSetNewFactura.setFormapago(formadepago);
                    facturaSetNewFactura = em.merge(facturaSetNewFactura);
                    if (oldFormapagoOfFacturaSetNewFactura != null && !oldFormapagoOfFacturaSetNewFactura.equals(formadepago)) {
                        oldFormapagoOfFacturaSetNewFactura.getFacturaSet().remove(facturaSetNewFactura);
                        oldFormapagoOfFacturaSetNewFactura = em.merge(oldFormapagoOfFacturaSetNewFactura);
                    }
                }
            }
            for (Comprobantepago comprobantepagoSetOldComprobantepago : comprobantepagoSetOld) {
                if (!comprobantepagoSetNew.contains(comprobantepagoSetOldComprobantepago)) {
                    comprobantepagoSetOldComprobantepago.setFormadepago(null);
                    comprobantepagoSetOldComprobantepago = em.merge(comprobantepagoSetOldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoSetNewComprobantepago : comprobantepagoSetNew) {
                if (!comprobantepagoSetOld.contains(comprobantepagoSetNewComprobantepago)) {
                    Formadepago oldFormadepagoOfComprobantepagoSetNewComprobantepago = comprobantepagoSetNewComprobantepago.getFormadepago();
                    comprobantepagoSetNewComprobantepago.setFormadepago(formadepago);
                    comprobantepagoSetNewComprobantepago = em.merge(comprobantepagoSetNewComprobantepago);
                    if (oldFormadepagoOfComprobantepagoSetNewComprobantepago != null && !oldFormadepagoOfComprobantepagoSetNewComprobantepago.equals(formadepago)) {
                        oldFormadepagoOfComprobantepagoSetNewComprobantepago.getComprobantepagoSet().remove(comprobantepagoSetNewComprobantepago);
                        oldFormadepagoOfComprobantepagoSetNewComprobantepago = em.merge(oldFormadepagoOfComprobantepagoSetNewComprobantepago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formadepago.getIdformapago();
                if (findFormadepago(id) == null) {
                    throw new NonexistentEntityException("The formadepago with id " + id + " no longer exists.");
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
            Formadepago formadepago;
            try {
                formadepago = em.getReference(Formadepago.class, id);
                formadepago.getIdformapago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formadepago with id " + id + " no longer exists.", enfe);
            }
            Set<Factura> facturaSet = formadepago.getFacturaSet();
            for (Factura facturaSetFactura : facturaSet) {
                facturaSetFactura.setFormapago(null);
                facturaSetFactura = em.merge(facturaSetFactura);
            }
            Set<Comprobantepago> comprobantepagoSet = formadepago.getComprobantepagoSet();
            for (Comprobantepago comprobantepagoSetComprobantepago : comprobantepagoSet) {
                comprobantepagoSetComprobantepago.setFormadepago(null);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
            }
            em.remove(formadepago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formadepago> findFormadepagoEntities() {
        return findFormadepagoEntities(true, -1, -1);
    }

    public List<Formadepago> findFormadepagoEntities(int maxResults, int firstResult) {
        return findFormadepagoEntities(false, maxResults, firstResult);
    }

    private List<Formadepago> findFormadepagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formadepago.class));
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

    public Formadepago findFormadepago(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formadepago.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormadepagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formadepago> rt = cq.from(Formadepago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
