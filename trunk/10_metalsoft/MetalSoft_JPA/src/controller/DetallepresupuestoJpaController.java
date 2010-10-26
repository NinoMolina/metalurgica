/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallepresupuesto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Presupuesto;
import entity.Producto;
import entity.Detalleproductopresupuesto;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class DetallepresupuestoJpaController {

    public DetallepresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepresupuesto detallepresupuesto) throws PreexistingEntityException, Exception {
        if (detallepresupuesto.getDetalleproductopresupuestoSet() == null) {
            detallepresupuesto.setDetalleproductopresupuestoSet(new HashSet<Detalleproductopresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Presupuesto idpresupuesto = detallepresupuesto.getIdpresupuesto();
            if (idpresupuesto != null) {
                idpresupuesto = em.getReference(idpresupuesto.getClass(), idpresupuesto.getIdpresupuesto());
                detallepresupuesto.setIdpresupuesto(idpresupuesto);
            }
            Producto idproducto = detallepresupuesto.getIdproducto();
            if (idproducto != null) {
                idproducto = em.getReference(idproducto.getClass(), idproducto.getIdproducto());
                detallepresupuesto.setIdproducto(idproducto);
            }
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuestoToAttach : detallepresupuesto.getDetalleproductopresupuestoSet()) {
                detalleproductopresupuestoSetDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet.add(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach);
            }
            detallepresupuesto.setDetalleproductopresupuestoSet(attachedDetalleproductopresupuestoSet);
            em.persist(detallepresupuesto);
            if (idpresupuesto != null) {
                idpresupuesto.getDetallepresupuestoSet().add(detallepresupuesto);
                idpresupuesto = em.merge(idpresupuesto);
            }
            if (idproducto != null) {
                idproducto.getDetallepresupuestoSet().add(detallepresupuesto);
                idproducto = em.merge(idproducto);
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : detallepresupuesto.getDetalleproductopresupuestoSet()) {
                Detallepresupuesto oldIddetallepresupuestoOfDetalleproductopresupuestoSetDetalleproductopresupuesto = detalleproductopresupuestoSetDetalleproductopresupuesto.getIddetallepresupuesto();
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIddetallepresupuesto(detallepresupuesto);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
                if (oldIddetallepresupuestoOfDetalleproductopresupuestoSetDetalleproductopresupuesto != null) {
                    oldIddetallepresupuestoOfDetalleproductopresupuestoSetDetalleproductopresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuestoSetDetalleproductopresupuesto);
                    oldIddetallepresupuestoOfDetalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(oldIddetallepresupuestoOfDetalleproductopresupuestoSetDetalleproductopresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallepresupuesto(detallepresupuesto.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detallepresupuesto " + detallepresupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepresupuesto detallepresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepresupuesto persistentDetallepresupuesto = em.find(Detallepresupuesto.class, detallepresupuesto.getIddetalle());
            Presupuesto idpresupuestoOld = persistentDetallepresupuesto.getIdpresupuesto();
            Presupuesto idpresupuestoNew = detallepresupuesto.getIdpresupuesto();
            Producto idproductoOld = persistentDetallepresupuesto.getIdproducto();
            Producto idproductoNew = detallepresupuesto.getIdproducto();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetOld = persistentDetallepresupuesto.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetNew = detallepresupuesto.getDetalleproductopresupuestoSet();
            if (idpresupuestoNew != null) {
                idpresupuestoNew = em.getReference(idpresupuestoNew.getClass(), idpresupuestoNew.getIdpresupuesto());
                detallepresupuesto.setIdpresupuesto(idpresupuestoNew);
            }
            if (idproductoNew != null) {
                idproductoNew = em.getReference(idproductoNew.getClass(), idproductoNew.getIdproducto());
                detallepresupuesto.setIdproducto(idproductoNew);
            }
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSetNew = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSetNew) {
                detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSetNew.add(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSetNew = attachedDetalleproductopresupuestoSetNew;
            detallepresupuesto.setDetalleproductopresupuestoSet(detalleproductopresupuestoSetNew);
            detallepresupuesto = em.merge(detallepresupuesto);
            if (idpresupuestoOld != null && !idpresupuestoOld.equals(idpresupuestoNew)) {
                idpresupuestoOld.getDetallepresupuestoSet().remove(detallepresupuesto);
                idpresupuestoOld = em.merge(idpresupuestoOld);
            }
            if (idpresupuestoNew != null && !idpresupuestoNew.equals(idpresupuestoOld)) {
                idpresupuestoNew.getDetallepresupuestoSet().add(detallepresupuesto);
                idpresupuestoNew = em.merge(idpresupuestoNew);
            }
            if (idproductoOld != null && !idproductoOld.equals(idproductoNew)) {
                idproductoOld.getDetallepresupuestoSet().remove(detallepresupuesto);
                idproductoOld = em.merge(idproductoOld);
            }
            if (idproductoNew != null && !idproductoNew.equals(idproductoOld)) {
                idproductoNew.getDetallepresupuestoSet().add(detallepresupuesto);
                idproductoNew = em.merge(idproductoNew);
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetOldDetalleproductopresupuesto : detalleproductopresupuestoSetOld) {
                if (!detalleproductopresupuestoSetNew.contains(detalleproductopresupuestoSetOldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoSetOldDetalleproductopresupuesto.setIddetallepresupuesto(null);
                    detalleproductopresupuestoSetOldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetOldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuesto : detalleproductopresupuestoSetNew) {
                if (!detalleproductopresupuestoSetOld.contains(detalleproductopresupuestoSetNewDetalleproductopresupuesto)) {
                    Detallepresupuesto oldIddetallepresupuestoOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto = detalleproductopresupuestoSetNewDetalleproductopresupuesto.getIddetallepresupuesto();
                    detalleproductopresupuestoSetNewDetalleproductopresupuesto.setIddetallepresupuesto(detallepresupuesto);
                    detalleproductopresupuestoSetNewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetNewDetalleproductopresupuesto);
                    if (oldIddetallepresupuestoOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto != null && !oldIddetallepresupuestoOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto.equals(detallepresupuesto)) {
                        oldIddetallepresupuestoOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuestoSetNewDetalleproductopresupuesto);
                        oldIddetallepresupuestoOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto = em.merge(oldIddetallepresupuestoOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallepresupuesto.getIddetalle();
                if (findDetallepresupuesto(id) == null) {
                    throw new NonexistentEntityException("The detallepresupuesto with id " + id + " no longer exists.");
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
            Detallepresupuesto detallepresupuesto;
            try {
                detallepresupuesto = em.getReference(Detallepresupuesto.class, id);
                detallepresupuesto.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepresupuesto with id " + id + " no longer exists.", enfe);
            }
            Presupuesto idpresupuesto = detallepresupuesto.getIdpresupuesto();
            if (idpresupuesto != null) {
                idpresupuesto.getDetallepresupuestoSet().remove(detallepresupuesto);
                idpresupuesto = em.merge(idpresupuesto);
            }
            Producto idproducto = detallepresupuesto.getIdproducto();
            if (idproducto != null) {
                idproducto.getDetallepresupuestoSet().remove(detallepresupuesto);
                idproducto = em.merge(idproducto);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet = detallepresupuesto.getDetalleproductopresupuestoSet();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : detalleproductopresupuestoSet) {
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIddetallepresupuesto(null);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
            }
            em.remove(detallepresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepresupuesto> findDetallepresupuestoEntities() {
        return findDetallepresupuestoEntities(true, -1, -1);
    }

    public List<Detallepresupuesto> findDetallepresupuestoEntities(int maxResults, int firstResult) {
        return findDetallepresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Detallepresupuesto> findDetallepresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepresupuesto.class));
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

    public Detallepresupuesto findDetallepresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepresupuesto> rt = cq.from(Detallepresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
