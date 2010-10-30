/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Comprobantepago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Factura;
import entity.Formadepago;
import entity.Usuario;

/**
 *
 * @author Nino
 */
public class ComprobantepagoJpaController {

    public ComprobantepagoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
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
            Factura factura1 = comprobantepago.getFactura1();
            if (factura1 != null) {
                factura1 = em.getReference(factura1.getClass(), factura1.getIdfactura());
                comprobantepago.setFactura1(factura1);
            }
            Formadepago formadepago = comprobantepago.getFormadepago();
            if (formadepago != null) {
                formadepago = em.getReference(formadepago.getClass(), formadepago.getIdformapago());
                comprobantepago.setFormadepago(formadepago);
            }
            Formadepago formadepago1 = comprobantepago.getFormadepago1();
            if (formadepago1 != null) {
                formadepago1 = em.getReference(formadepago1.getClass(), formadepago1.getIdformapago());
                comprobantepago.setFormadepago1(formadepago1);
            }
            Usuario usuario = comprobantepago.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                comprobantepago.setUsuario(usuario);
            }
            Usuario usuario1 = comprobantepago.getUsuario1();
            if (usuario1 != null) {
                usuario1 = em.getReference(usuario1.getClass(), usuario1.getIdusuario());
                comprobantepago.setUsuario1(usuario1);
            }
            em.persist(comprobantepago);
            if (factura != null) {
                factura.getComprobantepagoSet().add(comprobantepago);
                factura = em.merge(factura);
            }
            if (factura1 != null) {
                factura1.getComprobantepagoSet().add(comprobantepago);
                factura1 = em.merge(factura1);
            }
            if (formadepago != null) {
                formadepago.getComprobantepagoSet().add(comprobantepago);
                formadepago = em.merge(formadepago);
            }
            if (formadepago1 != null) {
                formadepago1.getComprobantepagoSet().add(comprobantepago);
                formadepago1 = em.merge(formadepago1);
            }
            if (usuario != null) {
                usuario.getComprobantepagoSet().add(comprobantepago);
                usuario = em.merge(usuario);
            }
            if (usuario1 != null) {
                usuario1.getComprobantepagoSet().add(comprobantepago);
                usuario1 = em.merge(usuario1);
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
            Factura factura1Old = persistentComprobantepago.getFactura1();
            Factura factura1New = comprobantepago.getFactura1();
            Formadepago formadepagoOld = persistentComprobantepago.getFormadepago();
            Formadepago formadepagoNew = comprobantepago.getFormadepago();
            Formadepago formadepago1Old = persistentComprobantepago.getFormadepago1();
            Formadepago formadepago1New = comprobantepago.getFormadepago1();
            Usuario usuarioOld = persistentComprobantepago.getUsuario();
            Usuario usuarioNew = comprobantepago.getUsuario();
            Usuario usuario1Old = persistentComprobantepago.getUsuario1();
            Usuario usuario1New = comprobantepago.getUsuario1();
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                comprobantepago.setFactura(facturaNew);
            }
            if (factura1New != null) {
                factura1New = em.getReference(factura1New.getClass(), factura1New.getIdfactura());
                comprobantepago.setFactura1(factura1New);
            }
            if (formadepagoNew != null) {
                formadepagoNew = em.getReference(formadepagoNew.getClass(), formadepagoNew.getIdformapago());
                comprobantepago.setFormadepago(formadepagoNew);
            }
            if (formadepago1New != null) {
                formadepago1New = em.getReference(formadepago1New.getClass(), formadepago1New.getIdformapago());
                comprobantepago.setFormadepago1(formadepago1New);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                comprobantepago.setUsuario(usuarioNew);
            }
            if (usuario1New != null) {
                usuario1New = em.getReference(usuario1New.getClass(), usuario1New.getIdusuario());
                comprobantepago.setUsuario1(usuario1New);
            }
            comprobantepago = em.merge(comprobantepago);
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getComprobantepagoSet().remove(comprobantepago);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getComprobantepagoSet().add(comprobantepago);
                facturaNew = em.merge(facturaNew);
            }
            if (factura1Old != null && !factura1Old.equals(factura1New)) {
                factura1Old.getComprobantepagoSet().remove(comprobantepago);
                factura1Old = em.merge(factura1Old);
            }
            if (factura1New != null && !factura1New.equals(factura1Old)) {
                factura1New.getComprobantepagoSet().add(comprobantepago);
                factura1New = em.merge(factura1New);
            }
            if (formadepagoOld != null && !formadepagoOld.equals(formadepagoNew)) {
                formadepagoOld.getComprobantepagoSet().remove(comprobantepago);
                formadepagoOld = em.merge(formadepagoOld);
            }
            if (formadepagoNew != null && !formadepagoNew.equals(formadepagoOld)) {
                formadepagoNew.getComprobantepagoSet().add(comprobantepago);
                formadepagoNew = em.merge(formadepagoNew);
            }
            if (formadepago1Old != null && !formadepago1Old.equals(formadepago1New)) {
                formadepago1Old.getComprobantepagoSet().remove(comprobantepago);
                formadepago1Old = em.merge(formadepago1Old);
            }
            if (formadepago1New != null && !formadepago1New.equals(formadepago1Old)) {
                formadepago1New.getComprobantepagoSet().add(comprobantepago);
                formadepago1New = em.merge(formadepago1New);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getComprobantepagoSet().remove(comprobantepago);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getComprobantepagoSet().add(comprobantepago);
                usuarioNew = em.merge(usuarioNew);
            }
            if (usuario1Old != null && !usuario1Old.equals(usuario1New)) {
                usuario1Old.getComprobantepagoSet().remove(comprobantepago);
                usuario1Old = em.merge(usuario1Old);
            }
            if (usuario1New != null && !usuario1New.equals(usuario1Old)) {
                usuario1New.getComprobantepagoSet().add(comprobantepago);
                usuario1New = em.merge(usuario1New);
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
                factura.getComprobantepagoSet().remove(comprobantepago);
                factura = em.merge(factura);
            }
            Factura factura1 = comprobantepago.getFactura1();
            if (factura1 != null) {
                factura1.getComprobantepagoSet().remove(comprobantepago);
                factura1 = em.merge(factura1);
            }
            Formadepago formadepago = comprobantepago.getFormadepago();
            if (formadepago != null) {
                formadepago.getComprobantepagoSet().remove(comprobantepago);
                formadepago = em.merge(formadepago);
            }
            Formadepago formadepago1 = comprobantepago.getFormadepago1();
            if (formadepago1 != null) {
                formadepago1.getComprobantepagoSet().remove(comprobantepago);
                formadepago1 = em.merge(formadepago1);
            }
            Usuario usuario = comprobantepago.getUsuario();
            if (usuario != null) {
                usuario.getComprobantepagoSet().remove(comprobantepago);
                usuario = em.merge(usuario);
            }
            Usuario usuario1 = comprobantepago.getUsuario1();
            if (usuario1 != null) {
                usuario1.getComprobantepagoSet().remove(comprobantepago);
                usuario1 = em.merge(usuario1);
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
