/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallepiezapresupuesto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detalleproductopresupuesto;
import entity.Etapadeproduccion;

/**
 *
 * @author Nino
 */
public class DetallepiezapresupuestoJpaController {

    public DetallepiezapresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
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
            Detalleproductopresupuesto iddetalleproductopresupuesto1 = detallepiezapresupuesto.getIddetalleproductopresupuesto1();
            if (iddetalleproductopresupuesto1 != null) {
                iddetalleproductopresupuesto1 = em.getReference(iddetalleproductopresupuesto1.getClass(), iddetalleproductopresupuesto1.getIddetalle());
                detallepiezapresupuesto.setIddetalleproductopresupuesto1(iddetalleproductopresupuesto1);
            }
            Etapadeproduccion idetapa = detallepiezapresupuesto.getIdetapa();
            if (idetapa != null) {
                idetapa = em.getReference(idetapa.getClass(), idetapa.getIdetapaproduccion());
                detallepiezapresupuesto.setIdetapa(idetapa);
            }
            Etapadeproduccion idetapa1 = detallepiezapresupuesto.getIdetapa1();
            if (idetapa1 != null) {
                idetapa1 = em.getReference(idetapa1.getClass(), idetapa1.getIdetapaproduccion());
                detallepiezapresupuesto.setIdetapa1(idetapa1);
            }
            em.persist(detallepiezapresupuesto);
            if (iddetalleproductopresupuesto != null) {
                iddetalleproductopresupuesto.getDetallepiezapresupuestoSet().add(detallepiezapresupuesto);
                iddetalleproductopresupuesto = em.merge(iddetalleproductopresupuesto);
            }
            if (iddetalleproductopresupuesto1 != null) {
                iddetalleproductopresupuesto1.getDetallepiezapresupuestoSet().add(detallepiezapresupuesto);
                iddetalleproductopresupuesto1 = em.merge(iddetalleproductopresupuesto1);
            }
            if (idetapa != null) {
                idetapa.getDetallepiezapresupuestoSet().add(detallepiezapresupuesto);
                idetapa = em.merge(idetapa);
            }
            if (idetapa1 != null) {
                idetapa1.getDetallepiezapresupuestoSet().add(detallepiezapresupuesto);
                idetapa1 = em.merge(idetapa1);
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
            Detalleproductopresupuesto iddetalleproductopresupuesto1Old = persistentDetallepiezapresupuesto.getIddetalleproductopresupuesto1();
            Detalleproductopresupuesto iddetalleproductopresupuesto1New = detallepiezapresupuesto.getIddetalleproductopresupuesto1();
            Etapadeproduccion idetapaOld = persistentDetallepiezapresupuesto.getIdetapa();
            Etapadeproduccion idetapaNew = detallepiezapresupuesto.getIdetapa();
            Etapadeproduccion idetapa1Old = persistentDetallepiezapresupuesto.getIdetapa1();
            Etapadeproduccion idetapa1New = detallepiezapresupuesto.getIdetapa1();
            if (iddetalleproductopresupuestoNew != null) {
                iddetalleproductopresupuestoNew = em.getReference(iddetalleproductopresupuestoNew.getClass(), iddetalleproductopresupuestoNew.getIddetalle());
                detallepiezapresupuesto.setIddetalleproductopresupuesto(iddetalleproductopresupuestoNew);
            }
            if (iddetalleproductopresupuesto1New != null) {
                iddetalleproductopresupuesto1New = em.getReference(iddetalleproductopresupuesto1New.getClass(), iddetalleproductopresupuesto1New.getIddetalle());
                detallepiezapresupuesto.setIddetalleproductopresupuesto1(iddetalleproductopresupuesto1New);
            }
            if (idetapaNew != null) {
                idetapaNew = em.getReference(idetapaNew.getClass(), idetapaNew.getIdetapaproduccion());
                detallepiezapresupuesto.setIdetapa(idetapaNew);
            }
            if (idetapa1New != null) {
                idetapa1New = em.getReference(idetapa1New.getClass(), idetapa1New.getIdetapaproduccion());
                detallepiezapresupuesto.setIdetapa1(idetapa1New);
            }
            detallepiezapresupuesto = em.merge(detallepiezapresupuesto);
            if (iddetalleproductopresupuestoOld != null && !iddetalleproductopresupuestoOld.equals(iddetalleproductopresupuestoNew)) {
                iddetalleproductopresupuestoOld.getDetallepiezapresupuestoSet().remove(detallepiezapresupuesto);
                iddetalleproductopresupuestoOld = em.merge(iddetalleproductopresupuestoOld);
            }
            if (iddetalleproductopresupuestoNew != null && !iddetalleproductopresupuestoNew.equals(iddetalleproductopresupuestoOld)) {
                iddetalleproductopresupuestoNew.getDetallepiezapresupuestoSet().add(detallepiezapresupuesto);
                iddetalleproductopresupuestoNew = em.merge(iddetalleproductopresupuestoNew);
            }
            if (iddetalleproductopresupuesto1Old != null && !iddetalleproductopresupuesto1Old.equals(iddetalleproductopresupuesto1New)) {
                iddetalleproductopresupuesto1Old.getDetallepiezapresupuestoSet().remove(detallepiezapresupuesto);
                iddetalleproductopresupuesto1Old = em.merge(iddetalleproductopresupuesto1Old);
            }
            if (iddetalleproductopresupuesto1New != null && !iddetalleproductopresupuesto1New.equals(iddetalleproductopresupuesto1Old)) {
                iddetalleproductopresupuesto1New.getDetallepiezapresupuestoSet().add(detallepiezapresupuesto);
                iddetalleproductopresupuesto1New = em.merge(iddetalleproductopresupuesto1New);
            }
            if (idetapaOld != null && !idetapaOld.equals(idetapaNew)) {
                idetapaOld.getDetallepiezapresupuestoSet().remove(detallepiezapresupuesto);
                idetapaOld = em.merge(idetapaOld);
            }
            if (idetapaNew != null && !idetapaNew.equals(idetapaOld)) {
                idetapaNew.getDetallepiezapresupuestoSet().add(detallepiezapresupuesto);
                idetapaNew = em.merge(idetapaNew);
            }
            if (idetapa1Old != null && !idetapa1Old.equals(idetapa1New)) {
                idetapa1Old.getDetallepiezapresupuestoSet().remove(detallepiezapresupuesto);
                idetapa1Old = em.merge(idetapa1Old);
            }
            if (idetapa1New != null && !idetapa1New.equals(idetapa1Old)) {
                idetapa1New.getDetallepiezapresupuestoSet().add(detallepiezapresupuesto);
                idetapa1New = em.merge(idetapa1New);
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
                iddetalleproductopresupuesto.getDetallepiezapresupuestoSet().remove(detallepiezapresupuesto);
                iddetalleproductopresupuesto = em.merge(iddetalleproductopresupuesto);
            }
            Detalleproductopresupuesto iddetalleproductopresupuesto1 = detallepiezapresupuesto.getIddetalleproductopresupuesto1();
            if (iddetalleproductopresupuesto1 != null) {
                iddetalleproductopresupuesto1.getDetallepiezapresupuestoSet().remove(detallepiezapresupuesto);
                iddetalleproductopresupuesto1 = em.merge(iddetalleproductopresupuesto1);
            }
            Etapadeproduccion idetapa = detallepiezapresupuesto.getIdetapa();
            if (idetapa != null) {
                idetapa.getDetallepiezapresupuestoSet().remove(detallepiezapresupuesto);
                idetapa = em.merge(idetapa);
            }
            Etapadeproduccion idetapa1 = detallepiezapresupuesto.getIdetapa1();
            if (idetapa1 != null) {
                idetapa1.getDetallepiezapresupuestoSet().remove(detallepiezapresupuesto);
                idetapa1 = em.merge(idetapa1);
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
