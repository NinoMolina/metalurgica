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
import metalsoft.datos.jpa.entity.Comprobantepago;
import metalsoft.datos.jpa.entity.Formadepago;

/**
 *
 * @author Nino
 */
public class FormadepagoJpaController implements Serializable {

    public FormadepagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formadepago formadepago) throws PreexistingEntityException, Exception {
        if (formadepago.getFacturaList() == null) {
            formadepago.setFacturaList(new ArrayList<Factura>());
        }
        if (formadepago.getComprobantepagoList() == null) {
            formadepago.setComprobantepagoList(new ArrayList<Comprobantepago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : formadepago.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdfactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            formadepago.setFacturaList(attachedFacturaList);
            List<Comprobantepago> attachedComprobantepagoList = new ArrayList<Comprobantepago>();
            for (Comprobantepago comprobantepagoListComprobantepagoToAttach : formadepago.getComprobantepagoList()) {
                comprobantepagoListComprobantepagoToAttach = em.getReference(comprobantepagoListComprobantepagoToAttach.getClass(), comprobantepagoListComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoList.add(comprobantepagoListComprobantepagoToAttach);
            }
            formadepago.setComprobantepagoList(attachedComprobantepagoList);
            em.persist(formadepago);
            for (Factura facturaListFactura : formadepago.getFacturaList()) {
                Formadepago oldFormapagoOfFacturaListFactura = facturaListFactura.getFormapago();
                facturaListFactura.setFormapago(formadepago);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldFormapagoOfFacturaListFactura != null) {
                    oldFormapagoOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldFormapagoOfFacturaListFactura = em.merge(oldFormapagoOfFacturaListFactura);
                }
            }
            for (Comprobantepago comprobantepagoListComprobantepago : formadepago.getComprobantepagoList()) {
                Formadepago oldFormadepagoOfComprobantepagoListComprobantepago = comprobantepagoListComprobantepago.getFormadepago();
                comprobantepagoListComprobantepago.setFormadepago(formadepago);
                comprobantepagoListComprobantepago = em.merge(comprobantepagoListComprobantepago);
                if (oldFormadepagoOfComprobantepagoListComprobantepago != null) {
                    oldFormadepagoOfComprobantepagoListComprobantepago.getComprobantepagoList().remove(comprobantepagoListComprobantepago);
                    oldFormadepagoOfComprobantepagoListComprobantepago = em.merge(oldFormadepagoOfComprobantepagoListComprobantepago);
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
            List<Factura> facturaListOld = persistentFormadepago.getFacturaList();
            List<Factura> facturaListNew = formadepago.getFacturaList();
            List<Comprobantepago> comprobantepagoListOld = persistentFormadepago.getComprobantepagoList();
            List<Comprobantepago> comprobantepagoListNew = formadepago.getComprobantepagoList();
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdfactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            formadepago.setFacturaList(facturaListNew);
            List<Comprobantepago> attachedComprobantepagoListNew = new ArrayList<Comprobantepago>();
            for (Comprobantepago comprobantepagoListNewComprobantepagoToAttach : comprobantepagoListNew) {
                comprobantepagoListNewComprobantepagoToAttach = em.getReference(comprobantepagoListNewComprobantepagoToAttach.getClass(), comprobantepagoListNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoListNew.add(comprobantepagoListNewComprobantepagoToAttach);
            }
            comprobantepagoListNew = attachedComprobantepagoListNew;
            formadepago.setComprobantepagoList(comprobantepagoListNew);
            formadepago = em.merge(formadepago);
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    facturaListOldFactura.setFormapago(null);
                    facturaListOldFactura = em.merge(facturaListOldFactura);
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Formadepago oldFormapagoOfFacturaListNewFactura = facturaListNewFactura.getFormapago();
                    facturaListNewFactura.setFormapago(formadepago);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldFormapagoOfFacturaListNewFactura != null && !oldFormapagoOfFacturaListNewFactura.equals(formadepago)) {
                        oldFormapagoOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldFormapagoOfFacturaListNewFactura = em.merge(oldFormapagoOfFacturaListNewFactura);
                    }
                }
            }
            for (Comprobantepago comprobantepagoListOldComprobantepago : comprobantepagoListOld) {
                if (!comprobantepagoListNew.contains(comprobantepagoListOldComprobantepago)) {
                    comprobantepagoListOldComprobantepago.setFormadepago(null);
                    comprobantepagoListOldComprobantepago = em.merge(comprobantepagoListOldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoListNewComprobantepago : comprobantepagoListNew) {
                if (!comprobantepagoListOld.contains(comprobantepagoListNewComprobantepago)) {
                    Formadepago oldFormadepagoOfComprobantepagoListNewComprobantepago = comprobantepagoListNewComprobantepago.getFormadepago();
                    comprobantepagoListNewComprobantepago.setFormadepago(formadepago);
                    comprobantepagoListNewComprobantepago = em.merge(comprobantepagoListNewComprobantepago);
                    if (oldFormadepagoOfComprobantepagoListNewComprobantepago != null && !oldFormadepagoOfComprobantepagoListNewComprobantepago.equals(formadepago)) {
                        oldFormadepagoOfComprobantepagoListNewComprobantepago.getComprobantepagoList().remove(comprobantepagoListNewComprobantepago);
                        oldFormadepagoOfComprobantepagoListNewComprobantepago = em.merge(oldFormadepagoOfComprobantepagoListNewComprobantepago);
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
            List<Factura> facturaList = formadepago.getFacturaList();
            for (Factura facturaListFactura : facturaList) {
                facturaListFactura.setFormapago(null);
                facturaListFactura = em.merge(facturaListFactura);
            }
            List<Comprobantepago> comprobantepagoList = formadepago.getComprobantepagoList();
            for (Comprobantepago comprobantepagoListComprobantepago : comprobantepagoList) {
                comprobantepagoListComprobantepago.setFormadepago(null);
                comprobantepagoListComprobantepago = em.merge(comprobantepagoListComprobantepago);
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
