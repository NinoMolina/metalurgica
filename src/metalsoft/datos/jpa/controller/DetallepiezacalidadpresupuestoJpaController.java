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
import metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Procesocalidad;

/**
 *
 * @author Nino
 */
public class DetallepiezacalidadpresupuestoJpaController {

    public DetallepiezacalidadpresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
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
            Procesocalidad idprocesocalidad = detallepiezacalidadpresupuesto.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad = em.getReference(idprocesocalidad.getClass(), idprocesocalidad.getIdprocesocalidad());
                detallepiezacalidadpresupuesto.setIdprocesocalidad(idprocesocalidad);
            }
            em.persist(detallepiezacalidadpresupuesto);
            if (iddetalleproductopresupuesto != null) {
                iddetalleproductopresupuesto.getDetallepiezacalidadpresupuestoList().add(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuesto = em.merge(iddetalleproductopresupuesto);
            }
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetallepiezacalidadpresupuestoList().add(detallepiezacalidadpresupuesto);
                idprocesocalidad = em.merge(idprocesocalidad);
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
            Procesocalidad idprocesocalidadOld = persistentDetallepiezacalidadpresupuesto.getIdprocesocalidad();
            Procesocalidad idprocesocalidadNew = detallepiezacalidadpresupuesto.getIdprocesocalidad();
            if (iddetalleproductopresupuestoNew != null) {
                iddetalleproductopresupuestoNew = em.getReference(iddetalleproductopresupuestoNew.getClass(), iddetalleproductopresupuestoNew.getIddetalle());
                detallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(iddetalleproductopresupuestoNew);
            }
            if (idprocesocalidadNew != null) {
                idprocesocalidadNew = em.getReference(idprocesocalidadNew.getClass(), idprocesocalidadNew.getIdprocesocalidad());
                detallepiezacalidadpresupuesto.setIdprocesocalidad(idprocesocalidadNew);
            }
            detallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuesto);
            if (iddetalleproductopresupuestoOld != null && !iddetalleproductopresupuestoOld.equals(iddetalleproductopresupuestoNew)) {
                iddetalleproductopresupuestoOld.getDetallepiezacalidadpresupuestoList().remove(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuestoOld = em.merge(iddetalleproductopresupuestoOld);
            }
            if (iddetalleproductopresupuestoNew != null && !iddetalleproductopresupuestoNew.equals(iddetalleproductopresupuestoOld)) {
                iddetalleproductopresupuestoNew.getDetallepiezacalidadpresupuestoList().add(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuestoNew = em.merge(iddetalleproductopresupuestoNew);
            }
            if (idprocesocalidadOld != null && !idprocesocalidadOld.equals(idprocesocalidadNew)) {
                idprocesocalidadOld.getDetallepiezacalidadpresupuestoList().remove(detallepiezacalidadpresupuesto);
                idprocesocalidadOld = em.merge(idprocesocalidadOld);
            }
            if (idprocesocalidadNew != null && !idprocesocalidadNew.equals(idprocesocalidadOld)) {
                idprocesocalidadNew.getDetallepiezacalidadpresupuestoList().add(detallepiezacalidadpresupuesto);
                idprocesocalidadNew = em.merge(idprocesocalidadNew);
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
                iddetalleproductopresupuesto.getDetallepiezacalidadpresupuestoList().remove(detallepiezacalidadpresupuesto);
                iddetalleproductopresupuesto = em.merge(iddetalleproductopresupuesto);
            }
            Procesocalidad idprocesocalidad = detallepiezacalidadpresupuesto.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetallepiezacalidadpresupuestoList().remove(detallepiezacalidadpresupuesto);
                idprocesocalidad = em.merge(idprocesocalidad);
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
