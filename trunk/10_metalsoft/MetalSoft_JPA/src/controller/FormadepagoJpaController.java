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
        if (formadepago.getFacturaSet1() == null) {
            formadepago.setFacturaSet1(new HashSet<Factura>());
        }
        if (formadepago.getComprobantepagoSet() == null) {
            formadepago.setComprobantepagoSet(new HashSet<Comprobantepago>());
        }
        if (formadepago.getComprobantepagoSet1() == null) {
            formadepago.setComprobantepagoSet1(new HashSet<Comprobantepago>());
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
            Set<Factura> attachedFacturaSet1 = new HashSet<Factura>();
            for (Factura facturaSet1FacturaToAttach : formadepago.getFacturaSet1()) {
                facturaSet1FacturaToAttach = em.getReference(facturaSet1FacturaToAttach.getClass(), facturaSet1FacturaToAttach.getIdfactura());
                attachedFacturaSet1.add(facturaSet1FacturaToAttach);
            }
            formadepago.setFacturaSet1(attachedFacturaSet1);
            Set<Comprobantepago> attachedComprobantepagoSet = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetComprobantepagoToAttach : formadepago.getComprobantepagoSet()) {
                comprobantepagoSetComprobantepagoToAttach = em.getReference(comprobantepagoSetComprobantepagoToAttach.getClass(), comprobantepagoSetComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet.add(comprobantepagoSetComprobantepagoToAttach);
            }
            formadepago.setComprobantepagoSet(attachedComprobantepagoSet);
            Set<Comprobantepago> attachedComprobantepagoSet1 = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSet1ComprobantepagoToAttach : formadepago.getComprobantepagoSet1()) {
                comprobantepagoSet1ComprobantepagoToAttach = em.getReference(comprobantepagoSet1ComprobantepagoToAttach.getClass(), comprobantepagoSet1ComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet1.add(comprobantepagoSet1ComprobantepagoToAttach);
            }
            formadepago.setComprobantepagoSet1(attachedComprobantepagoSet1);
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
            for (Factura facturaSet1Factura : formadepago.getFacturaSet1()) {
                Formadepago oldFormapago1OfFacturaSet1Factura = facturaSet1Factura.getFormapago1();
                facturaSet1Factura.setFormapago1(formadepago);
                facturaSet1Factura = em.merge(facturaSet1Factura);
                if (oldFormapago1OfFacturaSet1Factura != null) {
                    oldFormapago1OfFacturaSet1Factura.getFacturaSet1().remove(facturaSet1Factura);
                    oldFormapago1OfFacturaSet1Factura = em.merge(oldFormapago1OfFacturaSet1Factura);
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
            for (Comprobantepago comprobantepagoSet1Comprobantepago : formadepago.getComprobantepagoSet1()) {
                Formadepago oldFormadepago1OfComprobantepagoSet1Comprobantepago = comprobantepagoSet1Comprobantepago.getFormadepago1();
                comprobantepagoSet1Comprobantepago.setFormadepago1(formadepago);
                comprobantepagoSet1Comprobantepago = em.merge(comprobantepagoSet1Comprobantepago);
                if (oldFormadepago1OfComprobantepagoSet1Comprobantepago != null) {
                    oldFormadepago1OfComprobantepagoSet1Comprobantepago.getComprobantepagoSet1().remove(comprobantepagoSet1Comprobantepago);
                    oldFormadepago1OfComprobantepagoSet1Comprobantepago = em.merge(oldFormadepago1OfComprobantepagoSet1Comprobantepago);
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
            Set<Factura> facturaSet1Old = persistentFormadepago.getFacturaSet1();
            Set<Factura> facturaSet1New = formadepago.getFacturaSet1();
            Set<Comprobantepago> comprobantepagoSetOld = persistentFormadepago.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSetNew = formadepago.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSet1Old = persistentFormadepago.getComprobantepagoSet1();
            Set<Comprobantepago> comprobantepagoSet1New = formadepago.getComprobantepagoSet1();
            Set<Factura> attachedFacturaSetNew = new HashSet<Factura>();
            for (Factura facturaSetNewFacturaToAttach : facturaSetNew) {
                facturaSetNewFacturaToAttach = em.getReference(facturaSetNewFacturaToAttach.getClass(), facturaSetNewFacturaToAttach.getIdfactura());
                attachedFacturaSetNew.add(facturaSetNewFacturaToAttach);
            }
            facturaSetNew = attachedFacturaSetNew;
            formadepago.setFacturaSet(facturaSetNew);
            Set<Factura> attachedFacturaSet1New = new HashSet<Factura>();
            for (Factura facturaSet1NewFacturaToAttach : facturaSet1New) {
                facturaSet1NewFacturaToAttach = em.getReference(facturaSet1NewFacturaToAttach.getClass(), facturaSet1NewFacturaToAttach.getIdfactura());
                attachedFacturaSet1New.add(facturaSet1NewFacturaToAttach);
            }
            facturaSet1New = attachedFacturaSet1New;
            formadepago.setFacturaSet1(facturaSet1New);
            Set<Comprobantepago> attachedComprobantepagoSetNew = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetNewComprobantepagoToAttach : comprobantepagoSetNew) {
                comprobantepagoSetNewComprobantepagoToAttach = em.getReference(comprobantepagoSetNewComprobantepagoToAttach.getClass(), comprobantepagoSetNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSetNew.add(comprobantepagoSetNewComprobantepagoToAttach);
            }
            comprobantepagoSetNew = attachedComprobantepagoSetNew;
            formadepago.setComprobantepagoSet(comprobantepagoSetNew);
            Set<Comprobantepago> attachedComprobantepagoSet1New = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSet1NewComprobantepagoToAttach : comprobantepagoSet1New) {
                comprobantepagoSet1NewComprobantepagoToAttach = em.getReference(comprobantepagoSet1NewComprobantepagoToAttach.getClass(), comprobantepagoSet1NewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet1New.add(comprobantepagoSet1NewComprobantepagoToAttach);
            }
            comprobantepagoSet1New = attachedComprobantepagoSet1New;
            formadepago.setComprobantepagoSet1(comprobantepagoSet1New);
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
            for (Factura facturaSet1OldFactura : facturaSet1Old) {
                if (!facturaSet1New.contains(facturaSet1OldFactura)) {
                    facturaSet1OldFactura.setFormapago1(null);
                    facturaSet1OldFactura = em.merge(facturaSet1OldFactura);
                }
            }
            for (Factura facturaSet1NewFactura : facturaSet1New) {
                if (!facturaSet1Old.contains(facturaSet1NewFactura)) {
                    Formadepago oldFormapago1OfFacturaSet1NewFactura = facturaSet1NewFactura.getFormapago1();
                    facturaSet1NewFactura.setFormapago1(formadepago);
                    facturaSet1NewFactura = em.merge(facturaSet1NewFactura);
                    if (oldFormapago1OfFacturaSet1NewFactura != null && !oldFormapago1OfFacturaSet1NewFactura.equals(formadepago)) {
                        oldFormapago1OfFacturaSet1NewFactura.getFacturaSet1().remove(facturaSet1NewFactura);
                        oldFormapago1OfFacturaSet1NewFactura = em.merge(oldFormapago1OfFacturaSet1NewFactura);
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
            for (Comprobantepago comprobantepagoSet1OldComprobantepago : comprobantepagoSet1Old) {
                if (!comprobantepagoSet1New.contains(comprobantepagoSet1OldComprobantepago)) {
                    comprobantepagoSet1OldComprobantepago.setFormadepago1(null);
                    comprobantepagoSet1OldComprobantepago = em.merge(comprobantepagoSet1OldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoSet1NewComprobantepago : comprobantepagoSet1New) {
                if (!comprobantepagoSet1Old.contains(comprobantepagoSet1NewComprobantepago)) {
                    Formadepago oldFormadepago1OfComprobantepagoSet1NewComprobantepago = comprobantepagoSet1NewComprobantepago.getFormadepago1();
                    comprobantepagoSet1NewComprobantepago.setFormadepago1(formadepago);
                    comprobantepagoSet1NewComprobantepago = em.merge(comprobantepagoSet1NewComprobantepago);
                    if (oldFormadepago1OfComprobantepagoSet1NewComprobantepago != null && !oldFormadepago1OfComprobantepagoSet1NewComprobantepago.equals(formadepago)) {
                        oldFormadepago1OfComprobantepagoSet1NewComprobantepago.getComprobantepagoSet1().remove(comprobantepagoSet1NewComprobantepago);
                        oldFormadepago1OfComprobantepagoSet1NewComprobantepago = em.merge(oldFormadepago1OfComprobantepagoSet1NewComprobantepago);
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
            Set<Factura> facturaSet1 = formadepago.getFacturaSet1();
            for (Factura facturaSet1Factura : facturaSet1) {
                facturaSet1Factura.setFormapago1(null);
                facturaSet1Factura = em.merge(facturaSet1Factura);
            }
            Set<Comprobantepago> comprobantepagoSet = formadepago.getComprobantepagoSet();
            for (Comprobantepago comprobantepagoSetComprobantepago : comprobantepagoSet) {
                comprobantepagoSetComprobantepago.setFormadepago(null);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
            }
            Set<Comprobantepago> comprobantepagoSet1 = formadepago.getComprobantepagoSet1();
            for (Comprobantepago comprobantepagoSet1Comprobantepago : comprobantepagoSet1) {
                comprobantepagoSet1Comprobantepago.setFormadepago1(null);
                comprobantepagoSet1Comprobantepago = em.merge(comprobantepagoSet1Comprobantepago);
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
