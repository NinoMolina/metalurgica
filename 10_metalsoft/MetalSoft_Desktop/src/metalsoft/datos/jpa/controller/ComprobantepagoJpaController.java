/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Comprobantepago;
import metalsoft.datos.jpa.entity.Factura;
import metalsoft.datos.jpa.entity.Formadepago;
import metalsoft.datos.jpa.entity.Usuario;

/**
 *
 * @author Nino
 */
public class ComprobantepagoJpaController {

    public ComprobantepagoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comprobantepago comprobantepago) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura = comprobantepago.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                comprobantepago.setFactura(factura);
            }
            Formadepago formadepago = comprobantepago.getFormadepago();
            if (formadepago != null) {
                formadepago = em.getReference(formadepago.getClass(), formadepago.getIdformapago());
                comprobantepago.setFormadepago(formadepago);
            }
            Usuario usuario = comprobantepago.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                comprobantepago.setUsuario(usuario);
            }
            em.persist(comprobantepago);
            if (factura != null) {
                factura.getComprobantepagoList().add(comprobantepago);
                factura = em.merge(factura);
            }
            if (formadepago != null) {
                formadepago.getComprobantepagoList().add(comprobantepago);
                formadepago = em.merge(formadepago);
            }
            if (usuario != null) {
                usuario.getComprobantepagoList().add(comprobantepago);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComprobantepago(comprobantepago.getIdcomprobantepago()) != null) {
                throw new PreexistingEntityException("Comprobantepago " + comprobantepago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comprobantepago comprobantepago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comprobantepago persistentComprobantepago = em.find(Comprobantepago.class, comprobantepago.getIdcomprobantepago());
            Factura facturaOld = persistentComprobantepago.getFactura();
            Factura facturaNew = comprobantepago.getFactura();
            Formadepago formadepagoOld = persistentComprobantepago.getFormadepago();
            Formadepago formadepagoNew = comprobantepago.getFormadepago();
            Usuario usuarioOld = persistentComprobantepago.getUsuario();
            Usuario usuarioNew = comprobantepago.getUsuario();
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                comprobantepago.setFactura(facturaNew);
            }
            if (formadepagoNew != null) {
                formadepagoNew = em.getReference(formadepagoNew.getClass(), formadepagoNew.getIdformapago());
                comprobantepago.setFormadepago(formadepagoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                comprobantepago.setUsuario(usuarioNew);
            }
            comprobantepago = em.merge(comprobantepago);
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getComprobantepagoList().remove(comprobantepago);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getComprobantepagoList().add(comprobantepago);
                facturaNew = em.merge(facturaNew);
            }
            if (formadepagoOld != null && !formadepagoOld.equals(formadepagoNew)) {
                formadepagoOld.getComprobantepagoList().remove(comprobantepago);
                formadepagoOld = em.merge(formadepagoOld);
            }
            if (formadepagoNew != null && !formadepagoNew.equals(formadepagoOld)) {
                formadepagoNew.getComprobantepagoList().add(comprobantepago);
                formadepagoNew = em.merge(formadepagoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getComprobantepagoList().remove(comprobantepago);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getComprobantepagoList().add(comprobantepago);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comprobantepago.getIdcomprobantepago();
                if (findComprobantepago(id) == null) {
                    throw new NonexistentEntityException("The comprobantepago with id " + id + " no longer exists.");
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
            Comprobantepago comprobantepago;
            try {
                comprobantepago = em.getReference(Comprobantepago.class, id);
                comprobantepago.getIdcomprobantepago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comprobantepago with id " + id + " no longer exists.", enfe);
            }
            Factura factura = comprobantepago.getFactura();
            if (factura != null) {
                factura.getComprobantepagoList().remove(comprobantepago);
                factura = em.merge(factura);
            }
            Formadepago formadepago = comprobantepago.getFormadepago();
            if (formadepago != null) {
                formadepago.getComprobantepagoList().remove(comprobantepago);
                formadepago = em.merge(formadepago);
            }
            Usuario usuario = comprobantepago.getUsuario();
            if (usuario != null) {
                usuario.getComprobantepagoList().remove(comprobantepago);
                usuario = em.merge(usuario);
            }
            em.remove(comprobantepago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comprobantepago> findComprobantepagoEntities() {
        return findComprobantepagoEntities(true, -1, -1);
    }

    public List<Comprobantepago> findComprobantepagoEntities(int maxResults, int firstResult) {
        return findComprobantepagoEntities(false, maxResults, firstResult);
    }

    private List<Comprobantepago> findComprobantepagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comprobantepago.class));
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

    public Comprobantepago findComprobantepago(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comprobantepago.class, id);
        } finally {
            em.close();
        }
    }

    public int getComprobantepagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comprobantepago> rt = cq.from(Comprobantepago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
