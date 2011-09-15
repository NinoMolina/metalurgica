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
import metalsoft.datos.jpa.entity.Detallepiezapresupuesto;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Etapadeproduccion;

/**
 *
 * @author Nino
 */
public class DetallepiezapresupuestoJpaController {

    public DetallepiezapresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepiezapresupuesto detallepiezapresupuesto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductopresupuesto iddetalleproductopresupuesto = detallepiezapresupuesto.getIddetalleproductopresupuesto();
            if (iddetalleproductopresupuesto != null) {
                iddetalleproductopresupuesto = em.getReference(iddetalleproductopresupuesto.getClass(), iddetalleproductopresupuesto.getIddetalle());
                detallepiezapresupuesto.setIddetalleproductopresupuesto(iddetalleproductopresupuesto);
            }
            Etapadeproduccion idetapa = detallepiezapresupuesto.getIdetapa();
            if (idetapa != null) {
                idetapa = em.getReference(idetapa.getClass(), idetapa.getIdetapaproduccion());
                detallepiezapresupuesto.setIdetapa(idetapa);
            }
            em.persist(detallepiezapresupuesto);
            if (iddetalleproductopresupuesto != null) {
                iddetalleproductopresupuesto.getDetallepiezapresupuestoList().add(detallepiezapresupuesto);
                iddetalleproductopresupuesto = em.merge(iddetalleproductopresupuesto);
            }
            if (idetapa != null) {
                idetapa.getDetallepiezapresupuestoList().add(detallepiezapresupuesto);
                idetapa = em.merge(idetapa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallepiezapresupuesto(detallepiezapresupuesto.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detallepiezapresupuesto " + detallepiezapresupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepiezapresupuesto detallepiezapresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepiezapresupuesto persistentDetallepiezapresupuesto = em.find(Detallepiezapresupuesto.class, detallepiezapresupuesto.getIddetalle());
            Detalleproductopresupuesto iddetalleproductopresupuestoOld = persistentDetallepiezapresupuesto.getIddetalleproductopresupuesto();
            Detalleproductopresupuesto iddetalleproductopresupuestoNew = detallepiezapresupuesto.getIddetalleproductopresupuesto();
            Etapadeproduccion idetapaOld = persistentDetallepiezapresupuesto.getIdetapa();
            Etapadeproduccion idetapaNew = detallepiezapresupuesto.getIdetapa();
            if (iddetalleproductopresupuestoNew != null) {
                iddetalleproductopresupuestoNew = em.getReference(iddetalleproductopresupuestoNew.getClass(), iddetalleproductopresupuestoNew.getIddetalle());
                detallepiezapresupuesto.setIddetalleproductopresupuesto(iddetalleproductopresupuestoNew);
            }
            if (idetapaNew != null) {
                idetapaNew = em.getReference(idetapaNew.getClass(), idetapaNew.getIdetapaproduccion());
                detallepiezapresupuesto.setIdetapa(idetapaNew);
            }
            detallepiezapresupuesto = em.merge(detallepiezapresupuesto);
            if (iddetalleproductopresupuestoOld != null && !iddetalleproductopresupuestoOld.equals(iddetalleproductopresupuestoNew)) {
                iddetalleproductopresupuestoOld.getDetallepiezapresupuestoList().remove(detallepiezapresupuesto);
                iddetalleproductopresupuestoOld = em.merge(iddetalleproductopresupuestoOld);
            }
            if (iddetalleproductopresupuestoNew != null && !iddetalleproductopresupuestoNew.equals(iddetalleproductopresupuestoOld)) {
                iddetalleproductopresupuestoNew.getDetallepiezapresupuestoList().add(detallepiezapresupuesto);
                iddetalleproductopresupuestoNew = em.merge(iddetalleproductopresupuestoNew);
            }
            if (idetapaOld != null && !idetapaOld.equals(idetapaNew)) {
                idetapaOld.getDetallepiezapresupuestoList().remove(detallepiezapresupuesto);
                idetapaOld = em.merge(idetapaOld);
            }
            if (idetapaNew != null && !idetapaNew.equals(idetapaOld)) {
                idetapaNew.getDetallepiezapresupuestoList().add(detallepiezapresupuesto);
                idetapaNew = em.merge(idetapaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallepiezapresupuesto.getIddetalle();
                if (findDetallepiezapresupuesto(id) == null) {
                    throw new NonexistentEntityException("The detallepiezapresupuesto with id " + id + " no longer exists.");
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
            Detallepiezapresupuesto detallepiezapresupuesto;
            try {
                detallepiezapresupuesto = em.getReference(Detallepiezapresupuesto.class, id);
                detallepiezapresupuesto.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepiezapresupuesto with id " + id + " no longer exists.", enfe);
            }
            Detalleproductopresupuesto iddetalleproductopresupuesto = detallepiezapresupuesto.getIddetalleproductopresupuesto();
            if (iddetalleproductopresupuesto != null) {
                iddetalleproductopresupuesto.getDetallepiezapresupuestoList().remove(detallepiezapresupuesto);
                iddetalleproductopresupuesto = em.merge(iddetalleproductopresupuesto);
            }
            Etapadeproduccion idetapa = detallepiezapresupuesto.getIdetapa();
            if (idetapa != null) {
                idetapa.getDetallepiezapresupuestoList().remove(detallepiezapresupuesto);
                idetapa = em.merge(idetapa);
            }
            em.remove(detallepiezapresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepiezapresupuesto> findDetallepiezapresupuestoEntities() {
        return findDetallepiezapresupuestoEntities(true, -1, -1);
    }

    public List<Detallepiezapresupuesto> findDetallepiezapresupuestoEntities(int maxResults, int firstResult) {
        return findDetallepiezapresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Detallepiezapresupuesto> findDetallepiezapresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepiezapresupuesto.class));
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

    public Detallepiezapresupuesto findDetallepiezapresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepiezapresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepiezapresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepiezapresupuesto> rt = cq.from(Detallepiezapresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
