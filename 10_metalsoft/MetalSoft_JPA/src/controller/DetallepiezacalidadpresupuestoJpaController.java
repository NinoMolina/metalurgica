/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallepiezacalidadpresupuesto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detalleproductopresupuesto;
import entity.Procesocalidad;

/**
 *
 * @author Nino
 */
public class DetallepiezacalidadpresupuestoJpaController {

    public DetallepiezacalidadpresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepiezacalidadpresupuesto detallepiezacalidadpresupuesto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductopresupuesto iddetalleproductopresupuesto = detallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
            if (iddetalleproductopresupuesto != null) {
                iddetalleproductopresupuesto = em.getReference(iddetalleproductopresupuesto.getClass(), iddetalleproductopresupuesto.getIddetalle());
                detallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(iddetalleproductopresupuesto);
            }
            Detalleproductopresupuesto iddetalleproductopresupuesto1 = detallepiezacalidadpresupuesto.getIddetalleproductopresupuesto1();
            if (iddetalleproductopresupuesto1 != null) {
                iddetalleproductopresupuesto1 = em.getReference(iddetalleproductopresupuesto1.getClass(), iddetalleproductopresupuesto1.getIddetalle());
                detallepiezacalidadpresupuesto.setIddetalleproductopresupuesto1(iddetalleproductopresupuesto1);
            }
            Procesocalidad idprocesocalidad = detallepiezacalidadpresupuesto.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad = em.getReference(idprocesocalidad.getClass(), idprocesocalidad.getIdprocesocalidad());
                detallepiezacalidadpresupuesto.setIdprocesocalidad(idprocesocalidad);
            }
            Procesocalidad idprocesocalidad1 = detallepiezacalidadpresupuesto.getIdprocesocalidad1();
            if (idprocesocalidad1 != null) {
                idprocesocalidad1 = em.getReference(idprocesocalidad1.getClass(), idprocesocalidad1.getIdprocesocalidad());
                detallepiezacalidadpresupuesto.setIdprocesocalidad1(idprocesocalidad1);
            }
            em.persist(detallepiezacalidadpresupuesto);
            if (iddetalleproductopresupuesto != null) {
                iddetalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet().add(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuesto = em.merge(iddetalleproductopresupuesto);
            }
            if (iddetalleproductopresupuesto1 != null) {
                iddetalleproductopresupuesto1.getDetallepiezacalidadpresupuestoSet().add(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuesto1 = em.merge(iddetalleproductopresupuesto1);
            }
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetallepiezacalidadpresupuestoSet().add(detallepiezacalidadpresupuesto);
                idprocesocalidad = em.merge(idprocesocalidad);
            }
            if (idprocesocalidad1 != null) {
                idprocesocalidad1.getDetallepiezacalidadpresupuestoSet().add(detallepiezacalidadpresupuesto);
                idprocesocalidad1 = em.merge(idprocesocalidad1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallepiezacalidadpresupuesto(detallepiezacalidadpresupuesto.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detallepiezacalidadpresupuesto " + detallepiezacalidadpresupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepiezacalidadpresupuesto detallepiezacalidadpresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepiezacalidadpresupuesto persistentDetallepiezacalidadpresupuesto = em.find(Detallepiezacalidadpresupuesto.class, detallepiezacalidadpresupuesto.getIddetalle());
            Detalleproductopresupuesto iddetalleproductopresupuestoOld = persistentDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
            Detalleproductopresupuesto iddetalleproductopresupuestoNew = detallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
            Detalleproductopresupuesto iddetalleproductopresupuesto1Old = persistentDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto1();
            Detalleproductopresupuesto iddetalleproductopresupuesto1New = detallepiezacalidadpresupuesto.getIddetalleproductopresupuesto1();
            Procesocalidad idprocesocalidadOld = persistentDetallepiezacalidadpresupuesto.getIdprocesocalidad();
            Procesocalidad idprocesocalidadNew = detallepiezacalidadpresupuesto.getIdprocesocalidad();
            Procesocalidad idprocesocalidad1Old = persistentDetallepiezacalidadpresupuesto.getIdprocesocalidad1();
            Procesocalidad idprocesocalidad1New = detallepiezacalidadpresupuesto.getIdprocesocalidad1();
            if (iddetalleproductopresupuestoNew != null) {
                iddetalleproductopresupuestoNew = em.getReference(iddetalleproductopresupuestoNew.getClass(), iddetalleproductopresupuestoNew.getIddetalle());
                detallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(iddetalleproductopresupuestoNew);
            }
            if (iddetalleproductopresupuesto1New != null) {
                iddetalleproductopresupuesto1New = em.getReference(iddetalleproductopresupuesto1New.getClass(), iddetalleproductopresupuesto1New.getIddetalle());
                detallepiezacalidadpresupuesto.setIddetalleproductopresupuesto1(iddetalleproductopresupuesto1New);
            }
            if (idprocesocalidadNew != null) {
                idprocesocalidadNew = em.getReference(idprocesocalidadNew.getClass(), idprocesocalidadNew.getIdprocesocalidad());
                detallepiezacalidadpresupuesto.setIdprocesocalidad(idprocesocalidadNew);
            }
            if (idprocesocalidad1New != null) {
                idprocesocalidad1New = em.getReference(idprocesocalidad1New.getClass(), idprocesocalidad1New.getIdprocesocalidad());
                detallepiezacalidadpresupuesto.setIdprocesocalidad1(idprocesocalidad1New);
            }
            detallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuesto);
            if (iddetalleproductopresupuestoOld != null && !iddetalleproductopresupuestoOld.equals(iddetalleproductopresupuestoNew)) {
                iddetalleproductopresupuestoOld.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuestoOld = em.merge(iddetalleproductopresupuestoOld);
            }
            if (iddetalleproductopresupuestoNew != null && !iddetalleproductopresupuestoNew.equals(iddetalleproductopresupuestoOld)) {
                iddetalleproductopresupuestoNew.getDetallepiezacalidadpresupuestoSet().add(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuestoNew = em.merge(iddetalleproductopresupuestoNew);
            }
            if (iddetalleproductopresupuesto1Old != null && !iddetalleproductopresupuesto1Old.equals(iddetalleproductopresupuesto1New)) {
                iddetalleproductopresupuesto1Old.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuesto1Old = em.merge(iddetalleproductopresupuesto1Old);
            }
            if (iddetalleproductopresupuesto1New != null && !iddetalleproductopresupuesto1New.equals(iddetalleproductopresupuesto1Old)) {
                iddetalleproductopresupuesto1New.getDetallepiezacalidadpresupuestoSet().add(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuesto1New = em.merge(iddetalleproductopresupuesto1New);
            }
            if (idprocesocalidadOld != null && !idprocesocalidadOld.equals(idprocesocalidadNew)) {
                idprocesocalidadOld.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuesto);
                idprocesocalidadOld = em.merge(idprocesocalidadOld);
            }
            if (idprocesocalidadNew != null && !idprocesocalidadNew.equals(idprocesocalidadOld)) {
                idprocesocalidadNew.getDetallepiezacalidadpresupuestoSet().add(detallepiezacalidadpresupuesto);
                idprocesocalidadNew = em.merge(idprocesocalidadNew);
            }
            if (idprocesocalidad1Old != null && !idprocesocalidad1Old.equals(idprocesocalidad1New)) {
                idprocesocalidad1Old.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuesto);
                idprocesocalidad1Old = em.merge(idprocesocalidad1Old);
            }
            if (idprocesocalidad1New != null && !idprocesocalidad1New.equals(idprocesocalidad1Old)) {
                idprocesocalidad1New.getDetallepiezacalidadpresupuestoSet().add(detallepiezacalidadpresupuesto);
                idprocesocalidad1New = em.merge(idprocesocalidad1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallepiezacalidadpresupuesto.getIddetalle();
                if (findDetallepiezacalidadpresupuesto(id) == null) {
                    throw new NonexistentEntityException("The detallepiezacalidadpresupuesto with id " + id + " no longer exists.");
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
            Detallepiezacalidadpresupuesto detallepiezacalidadpresupuesto;
            try {
                detallepiezacalidadpresupuesto = em.getReference(Detallepiezacalidadpresupuesto.class, id);
                detallepiezacalidadpresupuesto.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepiezacalidadpresupuesto with id " + id + " no longer exists.", enfe);
            }
            Detalleproductopresupuesto iddetalleproductopresupuesto = detallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
            if (iddetalleproductopresupuesto != null) {
                iddetalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuesto = em.merge(iddetalleproductopresupuesto);
            }
            Detalleproductopresupuesto iddetalleproductopresupuesto1 = detallepiezacalidadpresupuesto.getIddetalleproductopresupuesto1();
            if (iddetalleproductopresupuesto1 != null) {
                iddetalleproductopresupuesto1.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuesto1 = em.merge(iddetalleproductopresupuesto1);
            }
            Procesocalidad idprocesocalidad = detallepiezacalidadpresupuesto.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuesto);
                idprocesocalidad = em.merge(idprocesocalidad);
            }
            Procesocalidad idprocesocalidad1 = detallepiezacalidadpresupuesto.getIdprocesocalidad1();
            if (idprocesocalidad1 != null) {
                idprocesocalidad1.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuesto);
                idprocesocalidad1 = em.merge(idprocesocalidad1);
            }
            em.remove(detallepiezacalidadpresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepiezacalidadpresupuesto> findDetallepiezacalidadpresupuestoEntities() {
        return findDetallepiezacalidadpresupuestoEntities(true, -1, -1);
    }

    public List<Detallepiezacalidadpresupuesto> findDetallepiezacalidadpresupuestoEntities(int maxResults, int firstResult) {
        return findDetallepiezacalidadpresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Detallepiezacalidadpresupuesto> findDetallepiezacalidadpresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepiezacalidadpresupuesto.class));
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

    public Detallepiezacalidadpresupuesto findDetallepiezacalidadpresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepiezacalidadpresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepiezacalidadpresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepiezacalidadpresupuesto> rt = cq.from(Detallepiezacalidadpresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
