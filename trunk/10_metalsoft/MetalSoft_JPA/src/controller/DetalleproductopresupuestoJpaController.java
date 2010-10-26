/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleproductopresupuesto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detallepresupuesto;
import entity.Materiaprima;
import entity.Pieza;
import entity.Detallepiezacalidadpresupuesto;
import java.util.HashSet;
import java.util.Set;
import entity.Detallepiezapresupuesto;

/**
 *
 * @author Nino
 */
public class DetalleproductopresupuestoJpaController {

    public DetalleproductopresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleproductopresupuesto detalleproductopresupuesto) throws PreexistingEntityException, Exception {
        if (detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet() == null) {
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet(new HashSet<Detallepiezacalidadpresupuesto>());
        }
        if (detalleproductopresupuesto.getDetallepiezapresupuestoSet() == null) {
            detalleproductopresupuesto.setDetallepiezapresupuestoSet(new HashSet<Detallepiezapresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepresupuesto iddetallepresupuesto = detalleproductopresupuesto.getIddetallepresupuesto();
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto = em.getReference(iddetallepresupuesto.getClass(), iddetallepresupuesto.getIddetalle());
                detalleproductopresupuesto.setIddetallepresupuesto(iddetallepresupuesto);
            }
            Materiaprima idmateriaprima = detalleproductopresupuesto.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima = em.getReference(idmateriaprima.getClass(), idmateriaprima.getIdmateriaprima());
                detalleproductopresupuesto.setIdmateriaprima(idmateriaprima);
            }
            Pieza idpieza = detalleproductopresupuesto.getIdpieza();
            if (idpieza != null) {
                idpieza = em.getReference(idpieza.getClass(), idpieza.getIdpieza());
                detalleproductopresupuesto.setIdpieza(idpieza);
            }
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSet = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach : detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet()) {
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSet.add(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach);
            }
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet(attachedDetallepiezacalidadpresupuestoSet);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSet = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuestoToAttach : detalleproductopresupuesto.getDetallepiezapresupuestoSet()) {
                detallepiezapresupuestoSetDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSetDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSetDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSet.add(detallepiezapresupuestoSetDetallepiezapresupuestoToAttach);
            }
            detalleproductopresupuesto.setDetallepiezapresupuestoSet(attachedDetallepiezapresupuestoSet);
            em.persist(detalleproductopresupuesto);
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                iddetallepresupuesto = em.merge(iddetallepresupuesto);
            }
            if (idmateriaprima != null) {
                idmateriaprima.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idmateriaprima = em.merge(idmateriaprima);
            }
            if (idpieza != null) {
                idpieza.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idpieza = em.merge(idpieza);
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto : detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet()) {
                Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                if (oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto != null) {
                    oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                    oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuesto : detalleproductopresupuesto.getDetallepiezapresupuestoSet()) {
                Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto = detallepiezapresupuestoSetDetallepiezapresupuesto.getIddetalleproductopresupuesto();
                detallepiezapresupuestoSetDetallepiezapresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                detallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetDetallepiezapresupuesto);
                if (oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto != null) {
                    oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto.getDetallepiezapresupuestoSet().remove(detallepiezapresupuestoSetDetallepiezapresupuesto);
                    oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleproductopresupuesto(detalleproductopresupuesto.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detalleproductopresupuesto " + detalleproductopresupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleproductopresupuesto detalleproductopresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductopresupuesto persistentDetalleproductopresupuesto = em.find(Detalleproductopresupuesto.class, detalleproductopresupuesto.getIddetalle());
            Detallepresupuesto iddetallepresupuestoOld = persistentDetalleproductopresupuesto.getIddetallepresupuesto();
            Detallepresupuesto iddetallepresupuestoNew = detalleproductopresupuesto.getIddetallepresupuesto();
            Materiaprima idmateriaprimaOld = persistentDetalleproductopresupuesto.getIdmateriaprima();
            Materiaprima idmateriaprimaNew = detalleproductopresupuesto.getIdmateriaprima();
            Pieza idpiezaOld = persistentDetalleproductopresupuesto.getIdpieza();
            Pieza idpiezaNew = detalleproductopresupuesto.getIdpieza();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSetOld = persistentDetalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSetNew = detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSetOld = persistentDetalleproductopresupuesto.getDetallepiezapresupuestoSet();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSetNew = detalleproductopresupuesto.getDetallepiezapresupuestoSet();
            if (iddetallepresupuestoNew != null) {
                iddetallepresupuestoNew = em.getReference(iddetallepresupuestoNew.getClass(), iddetallepresupuestoNew.getIddetalle());
                detalleproductopresupuesto.setIddetallepresupuesto(iddetallepresupuestoNew);
            }
            if (idmateriaprimaNew != null) {
                idmateriaprimaNew = em.getReference(idmateriaprimaNew.getClass(), idmateriaprimaNew.getIdmateriaprima());
                detalleproductopresupuesto.setIdmateriaprima(idmateriaprimaNew);
            }
            if (idpiezaNew != null) {
                idpiezaNew = em.getReference(idpiezaNew.getClass(), idpiezaNew.getIdpieza());
                detalleproductopresupuesto.setIdpieza(idpiezaNew);
            }
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSetNew = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach : detallepiezacalidadpresupuestoSetNew) {
                detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSetNew.add(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach);
            }
            detallepiezacalidadpresupuestoSetNew = attachedDetallepiezacalidadpresupuestoSetNew;
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet(detallepiezacalidadpresupuestoSetNew);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSetNew = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach : detallepiezapresupuestoSetNew) {
                detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSetNew.add(detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach);
            }
            detallepiezapresupuestoSetNew = attachedDetallepiezapresupuestoSetNew;
            detalleproductopresupuesto.setDetallepiezapresupuestoSet(detallepiezapresupuestoSetNew);
            detalleproductopresupuesto = em.merge(detalleproductopresupuesto);
            if (iddetallepresupuestoOld != null && !iddetallepresupuestoOld.equals(iddetallepresupuestoNew)) {
                iddetallepresupuestoOld.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                iddetallepresupuestoOld = em.merge(iddetallepresupuestoOld);
            }
            if (iddetallepresupuestoNew != null && !iddetallepresupuestoNew.equals(iddetallepresupuestoOld)) {
                iddetallepresupuestoNew.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                iddetallepresupuestoNew = em.merge(iddetallepresupuestoNew);
            }
            if (idmateriaprimaOld != null && !idmateriaprimaOld.equals(idmateriaprimaNew)) {
                idmateriaprimaOld.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idmateriaprimaOld = em.merge(idmateriaprimaOld);
            }
            if (idmateriaprimaNew != null && !idmateriaprimaNew.equals(idmateriaprimaOld)) {
                idmateriaprimaNew.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idmateriaprimaNew = em.merge(idmateriaprimaNew);
            }
            if (idpiezaOld != null && !idpiezaOld.equals(idpiezaNew)) {
                idpiezaOld.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idpiezaOld = em.merge(idpiezaOld);
            }
            if (idpiezaNew != null && !idpiezaNew.equals(idpiezaOld)) {
                idpiezaNew.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idpiezaNew = em.merge(idpiezaNew);
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSetOld) {
                if (!detallepiezacalidadpresupuestoSetNew.contains(detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto)) {
                    detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(null);
                    detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSetNew) {
                if (!detallepiezacalidadpresupuestoSetOld.contains(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto)) {
                    Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
                    detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                    detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                    if (oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto != null && !oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.equals(detalleproductopresupuesto)) {
                        oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                        oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                    }
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetOldDetallepiezapresupuesto : detallepiezapresupuestoSetOld) {
                if (!detallepiezapresupuestoSetNew.contains(detallepiezapresupuestoSetOldDetallepiezapresupuesto)) {
                    detallepiezapresupuestoSetOldDetallepiezapresupuesto.setIddetalleproductopresupuesto(null);
                    detallepiezapresupuestoSetOldDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetOldDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetNewDetallepiezapresupuesto : detallepiezapresupuestoSetNew) {
                if (!detallepiezapresupuestoSetOld.contains(detallepiezapresupuestoSetNewDetallepiezapresupuesto)) {
                    Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto = detallepiezapresupuestoSetNewDetallepiezapresupuesto.getIddetalleproductopresupuesto();
                    detallepiezapresupuestoSetNewDetallepiezapresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                    detallepiezapresupuestoSetNewDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetNewDetallepiezapresupuesto);
                    if (oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto != null && !oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto.equals(detalleproductopresupuesto)) {
                        oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto.getDetallepiezapresupuestoSet().remove(detallepiezapresupuestoSetNewDetallepiezapresupuesto);
                        oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleproductopresupuesto.getIddetalle();
                if (findDetalleproductopresupuesto(id) == null) {
                    throw new NonexistentEntityException("The detalleproductopresupuesto with id " + id + " no longer exists.");
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
            Detalleproductopresupuesto detalleproductopresupuesto;
            try {
                detalleproductopresupuesto = em.getReference(Detalleproductopresupuesto.class, id);
                detalleproductopresupuesto.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleproductopresupuesto with id " + id + " no longer exists.", enfe);
            }
            Detallepresupuesto iddetallepresupuesto = detalleproductopresupuesto.getIddetallepresupuesto();
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                iddetallepresupuesto = em.merge(iddetallepresupuesto);
            }
            Materiaprima idmateriaprima = detalleproductopresupuesto.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idmateriaprima = em.merge(idmateriaprima);
            }
            Pieza idpieza = detalleproductopresupuesto.getIdpieza();
            if (idpieza != null) {
                idpieza.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idpieza = em.merge(idpieza);
            }
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet = detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet) {
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(null);
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
            }
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet = detalleproductopresupuesto.getDetallepiezapresupuestoSet();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuesto : detallepiezapresupuestoSet) {
                detallepiezapresupuestoSetDetallepiezapresupuesto.setIddetalleproductopresupuesto(null);
                detallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetDetallepiezapresupuesto);
            }
            em.remove(detalleproductopresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities() {
        return findDetalleproductopresupuestoEntities(true, -1, -1);
    }

    public List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities(int maxResults, int firstResult) {
        return findDetalleproductopresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleproductopresupuesto.class));
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

    public Detalleproductopresupuesto findDetalleproductopresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleproductopresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleproductopresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleproductopresupuesto> rt = cq.from(Detalleproductopresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
