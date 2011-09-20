/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleproductoreal;
import metalsoft.datos.jpa.entity.Productoreal;
import metalsoft.datos.jpa.entity.Piezareal;
import metalsoft.datos.jpa.entity.Detalleproducto;

/**
 *
 * @author Nino
 */
public class DetalleproductorealJpaController implements Serializable {

    public DetalleproductorealJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleproductoreal detalleproductoreal) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productoreal idproductoreal = detalleproductoreal.getIdproductoreal();
            if (idproductoreal != null) {
                idproductoreal = em.getReference(idproductoreal.getClass(), idproductoreal.getIdproductoreal());
                detalleproductoreal.setIdproductoreal(idproductoreal);
            }
            Piezareal idpiezareal = detalleproductoreal.getIdpiezareal();
            if (idpiezareal != null) {
                idpiezareal = em.getReference(idpiezareal.getClass(), idpiezareal.getIdpiezareal());
                detalleproductoreal.setIdpiezareal(idpiezareal);
            }
            Detalleproducto detalleproducto = detalleproductoreal.getDetalleproducto();
            if (detalleproducto != null) {
                detalleproducto = em.getReference(detalleproducto.getClass(), detalleproducto.getIddetalle());
                detalleproductoreal.setDetalleproducto(detalleproducto);
            }
            em.persist(detalleproductoreal);
            if (idproductoreal != null) {
                idproductoreal.getDetalleproductorealList().add(detalleproductoreal);
                idproductoreal = em.merge(idproductoreal);
            }
            if (idpiezareal != null) {
                idpiezareal.getDetalleproductorealList().add(detalleproductoreal);
                idpiezareal = em.merge(idpiezareal);
            }
            if (detalleproducto != null) {
                detalleproducto.getDetalleproductorealList().add(detalleproductoreal);
                detalleproducto = em.merge(detalleproducto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleproductoreal(detalleproductoreal.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detalleproductoreal " + detalleproductoreal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleproductoreal detalleproductoreal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductoreal persistentDetalleproductoreal = em.find(Detalleproductoreal.class, detalleproductoreal.getIddetalle());
            Productoreal idproductorealOld = persistentDetalleproductoreal.getIdproductoreal();
            Productoreal idproductorealNew = detalleproductoreal.getIdproductoreal();
            Piezareal idpiezarealOld = persistentDetalleproductoreal.getIdpiezareal();
            Piezareal idpiezarealNew = detalleproductoreal.getIdpiezareal();
            Detalleproducto detalleproductoOld = persistentDetalleproductoreal.getDetalleproducto();
            Detalleproducto detalleproductoNew = detalleproductoreal.getDetalleproducto();
            if (idproductorealNew != null) {
                idproductorealNew = em.getReference(idproductorealNew.getClass(), idproductorealNew.getIdproductoreal());
                detalleproductoreal.setIdproductoreal(idproductorealNew);
            }
            if (idpiezarealNew != null) {
                idpiezarealNew = em.getReference(idpiezarealNew.getClass(), idpiezarealNew.getIdpiezareal());
                detalleproductoreal.setIdpiezareal(idpiezarealNew);
            }
            if (detalleproductoNew != null) {
                detalleproductoNew = em.getReference(detalleproductoNew.getClass(), detalleproductoNew.getIddetalle());
                detalleproductoreal.setDetalleproducto(detalleproductoNew);
            }
            detalleproductoreal = em.merge(detalleproductoreal);
            if (idproductorealOld != null && !idproductorealOld.equals(idproductorealNew)) {
                idproductorealOld.getDetalleproductorealList().remove(detalleproductoreal);
                idproductorealOld = em.merge(idproductorealOld);
            }
            if (idproductorealNew != null && !idproductorealNew.equals(idproductorealOld)) {
                idproductorealNew.getDetalleproductorealList().add(detalleproductoreal);
                idproductorealNew = em.merge(idproductorealNew);
            }
            if (idpiezarealOld != null && !idpiezarealOld.equals(idpiezarealNew)) {
                idpiezarealOld.getDetalleproductorealList().remove(detalleproductoreal);
                idpiezarealOld = em.merge(idpiezarealOld);
            }
            if (idpiezarealNew != null && !idpiezarealNew.equals(idpiezarealOld)) {
                idpiezarealNew.getDetalleproductorealList().add(detalleproductoreal);
                idpiezarealNew = em.merge(idpiezarealNew);
            }
            if (detalleproductoOld != null && !detalleproductoOld.equals(detalleproductoNew)) {
                detalleproductoOld.getDetalleproductorealList().remove(detalleproductoreal);
                detalleproductoOld = em.merge(detalleproductoOld);
            }
            if (detalleproductoNew != null && !detalleproductoNew.equals(detalleproductoOld)) {
                detalleproductoNew.getDetalleproductorealList().add(detalleproductoreal);
                detalleproductoNew = em.merge(detalleproductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleproductoreal.getIddetalle();
                if (findDetalleproductoreal(id) == null) {
                    throw new NonexistentEntityException("The detalleproductoreal with id " + id + " no longer exists.");
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
            Detalleproductoreal detalleproductoreal;
            try {
                detalleproductoreal = em.getReference(Detalleproductoreal.class, id);
                detalleproductoreal.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleproductoreal with id " + id + " no longer exists.", enfe);
            }
            Productoreal idproductoreal = detalleproductoreal.getIdproductoreal();
            if (idproductoreal != null) {
                idproductoreal.getDetalleproductorealList().remove(detalleproductoreal);
                idproductoreal = em.merge(idproductoreal);
            }
            Piezareal idpiezareal = detalleproductoreal.getIdpiezareal();
            if (idpiezareal != null) {
                idpiezareal.getDetalleproductorealList().remove(detalleproductoreal);
                idpiezareal = em.merge(idpiezareal);
            }
            Detalleproducto detalleproducto = detalleproductoreal.getDetalleproducto();
            if (detalleproducto != null) {
                detalleproducto.getDetalleproductorealList().remove(detalleproductoreal);
                detalleproducto = em.merge(detalleproducto);
            }
            em.remove(detalleproductoreal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleproductoreal> findDetalleproductorealEntities() {
        return findDetalleproductorealEntities(true, -1, -1);
    }

    public List<Detalleproductoreal> findDetalleproductorealEntities(int maxResults, int firstResult) {
        return findDetalleproductorealEntities(false, maxResults, firstResult);
    }

    private List<Detalleproductoreal> findDetalleproductorealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleproductoreal.class));
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

    public Detalleproductoreal findDetalleproductoreal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleproductoreal.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleproductorealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleproductoreal> rt = cq.from(Detalleproductoreal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
