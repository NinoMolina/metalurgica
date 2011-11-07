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
import metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo;
import metalsoft.datos.jpa.entity.Rotura;
import metalsoft.datos.jpa.entity.Mantenimientocorrectivo;

/**
 *
 * @author Nino
 */
public class DetallemantenimientocorrectivoJpaController implements Serializable {

    public DetallemantenimientocorrectivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallemantenimientocorrectivo detallemantenimientocorrectivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rotura rotura = detallemantenimientocorrectivo.getRotura();
            if (rotura != null) {
                rotura = em.getReference(rotura.getClass(), rotura.getIdrotura());
                detallemantenimientocorrectivo.setRotura(rotura);
            }
            Mantenimientocorrectivo idmantenimientocorrectivo = detallemantenimientocorrectivo.getIdmantenimientocorrectivo();
            if (idmantenimientocorrectivo != null) {
                idmantenimientocorrectivo = em.getReference(idmantenimientocorrectivo.getClass(), idmantenimientocorrectivo.getIdmantenimientocorrectivo());
                detallemantenimientocorrectivo.setIdmantenimientocorrectivo(idmantenimientocorrectivo);
            }
            em.persist(detallemantenimientocorrectivo);
            if (rotura != null) {
                rotura.getDetallemantenimientocorrectivoList().add(detallemantenimientocorrectivo);
                rotura = em.merge(rotura);
            }
            if (idmantenimientocorrectivo != null) {
                idmantenimientocorrectivo.getDetallemantenimientocorrectivoList().add(detallemantenimientocorrectivo);
                idmantenimientocorrectivo = em.merge(idmantenimientocorrectivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallemantenimientocorrectivo detallemantenimientocorrectivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientocorrectivo persistentDetallemantenimientocorrectivo = em.find(Detallemantenimientocorrectivo.class, detallemantenimientocorrectivo.getIddetalle());
            Rotura roturaOld = persistentDetallemantenimientocorrectivo.getRotura();
            Rotura roturaNew = detallemantenimientocorrectivo.getRotura();
            Mantenimientocorrectivo idmantenimientocorrectivoOld = persistentDetallemantenimientocorrectivo.getIdmantenimientocorrectivo();
            Mantenimientocorrectivo idmantenimientocorrectivoNew = detallemantenimientocorrectivo.getIdmantenimientocorrectivo();
            if (roturaNew != null) {
                roturaNew = em.getReference(roturaNew.getClass(), roturaNew.getIdrotura());
                detallemantenimientocorrectivo.setRotura(roturaNew);
            }
            if (idmantenimientocorrectivoNew != null) {
                idmantenimientocorrectivoNew = em.getReference(idmantenimientocorrectivoNew.getClass(), idmantenimientocorrectivoNew.getIdmantenimientocorrectivo());
                detallemantenimientocorrectivo.setIdmantenimientocorrectivo(idmantenimientocorrectivoNew);
            }
            detallemantenimientocorrectivo = em.merge(detallemantenimientocorrectivo);
            if (roturaOld != null && !roturaOld.equals(roturaNew)) {
                roturaOld.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivo);
                roturaOld = em.merge(roturaOld);
            }
            if (roturaNew != null && !roturaNew.equals(roturaOld)) {
                roturaNew.getDetallemantenimientocorrectivoList().add(detallemantenimientocorrectivo);
                roturaNew = em.merge(roturaNew);
            }
            if (idmantenimientocorrectivoOld != null && !idmantenimientocorrectivoOld.equals(idmantenimientocorrectivoNew)) {
                idmantenimientocorrectivoOld.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivo);
                idmantenimientocorrectivoOld = em.merge(idmantenimientocorrectivoOld);
            }
            if (idmantenimientocorrectivoNew != null && !idmantenimientocorrectivoNew.equals(idmantenimientocorrectivoOld)) {
                idmantenimientocorrectivoNew.getDetallemantenimientocorrectivoList().add(detallemantenimientocorrectivo);
                idmantenimientocorrectivoNew = em.merge(idmantenimientocorrectivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallemantenimientocorrectivo.getIddetalle();
                if (findDetallemantenimientocorrectivo(id) == null) {
                    throw new NonexistentEntityException("The detallemantenimientocorrectivo with id " + id + " no longer exists.");
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
            Detallemantenimientocorrectivo detallemantenimientocorrectivo;
            try {
                detallemantenimientocorrectivo = em.getReference(Detallemantenimientocorrectivo.class, id);
                detallemantenimientocorrectivo.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallemantenimientocorrectivo with id " + id + " no longer exists.", enfe);
            }
            Rotura rotura = detallemantenimientocorrectivo.getRotura();
            if (rotura != null) {
                rotura.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivo);
                rotura = em.merge(rotura);
            }
            Mantenimientocorrectivo idmantenimientocorrectivo = detallemantenimientocorrectivo.getIdmantenimientocorrectivo();
            if (idmantenimientocorrectivo != null) {
                idmantenimientocorrectivo.getDetallemantenimientocorrectivoList().remove(detallemantenimientocorrectivo);
                idmantenimientocorrectivo = em.merge(idmantenimientocorrectivo);
            }
            em.remove(detallemantenimientocorrectivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallemantenimientocorrectivo> findDetallemantenimientocorrectivoEntities() {
        return findDetallemantenimientocorrectivoEntities(true, -1, -1);
    }

    public List<Detallemantenimientocorrectivo> findDetallemantenimientocorrectivoEntities(int maxResults, int firstResult) {
        return findDetallemantenimientocorrectivoEntities(false, maxResults, firstResult);
    }

    private List<Detallemantenimientocorrectivo> findDetallemantenimientocorrectivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallemantenimientocorrectivo.class));
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

    public Detallemantenimientocorrectivo findDetallemantenimientocorrectivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallemantenimientocorrectivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallemantenimientocorrectivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallemantenimientocorrectivo> rt = cq.from(Detallemantenimientocorrectivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
