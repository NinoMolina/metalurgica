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
        if (detallepresupuesto.getDetalleproductopresupuestoSet1() == null) {
            detallepresupuesto.setDetalleproductopresupuestoSet1(new HashSet<Detalleproductopresupuesto>());
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
            Presupuesto idpresupuesto1 = detallepresupuesto.getIdpresupuesto1();
            if (idpresupuesto1 != null) {
                idpresupuesto1 = em.getReference(idpresupuesto1.getClass(), idpresupuesto1.getIdpresupuesto());
                detallepresupuesto.setIdpresupuesto1(idpresupuesto1);
            }
            Producto idproducto = detallepresupuesto.getIdproducto();
            if (idproducto != null) {
                idproducto = em.getReference(idproducto.getClass(), idproducto.getIdproducto());
                detallepresupuesto.setIdproducto(idproducto);
            }
            Producto idproducto1 = detallepresupuesto.getIdproducto1();
            if (idproducto1 != null) {
                idproducto1 = em.getReference(idproducto1.getClass(), idproducto1.getIdproducto());
                detallepresupuesto.setIdproducto1(idproducto1);
            }
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuestoToAttach : detallepresupuesto.getDetalleproductopresupuestoSet()) {
                detalleproductopresupuestoSetDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet.add(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach);
            }
            detallepresupuesto.setDetalleproductopresupuestoSet(attachedDetalleproductopresupuestoSet);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet1 = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach : detallepresupuesto.getDetalleproductopresupuestoSet1()) {
                detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet1.add(detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach);
            }
            detallepresupuesto.setDetalleproductopresupuestoSet1(attachedDetalleproductopresupuestoSet1);
            em.persist(detallepresupuesto);
            if (idpresupuesto != null) {
                idpresupuesto.getDetallepresupuestoSet().add(detallepresupuesto);
                idpresupuesto = em.merge(idpresupuesto);
            }
            if (idpresupuesto1 != null) {
                idpresupuesto1.getDetallepresupuestoSet().add(detallepresupuesto);
                idpresupuesto1 = em.merge(idpresupuesto1);
            }
            if (idproducto != null) {
                idproducto.getDetallepresupuestoSet().add(detallepresupuesto);
                idproducto = em.merge(idproducto);
            }
            if (idproducto1 != null) {
                idproducto1.getDetallepresupuestoSet().add(detallepresupuesto);
                idproducto1 = em.merge(idproducto1);
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
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1Detalleproductopresupuesto : detallepresupuesto.getDetalleproductopresupuestoSet1()) {
                Detallepresupuesto oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto = detalleproductopresupuestoSet1Detalleproductopresupuesto.getIddetallepresupuesto1();
                detalleproductopresupuestoSet1Detalleproductopresupuesto.setIddetallepresupuesto1(detallepresupuesto);
                detalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1Detalleproductopresupuesto);
                if (oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto != null) {
                    oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto.getDetalleproductopresupuestoSet1().remove(detalleproductopresupuestoSet1Detalleproductopresupuesto);
                    oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto);
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
            Presupuesto idpresupuesto1Old = persistentDetallepresupuesto.getIdpresupuesto1();
            Presupuesto idpresupuesto1New = detallepresupuesto.getIdpresupuesto1();
            Producto idproductoOld = persistentDetallepresupuesto.getIdproducto();
            Producto idproductoNew = detallepresupuesto.getIdproducto();
            Producto idproducto1Old = persistentDetallepresupuesto.getIdproducto1();
            Producto idproducto1New = detallepresupuesto.getIdproducto1();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetOld = persistentDetallepresupuesto.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetNew = detallepresupuesto.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1Old = persistentDetallepresupuesto.getDetalleproductopresupuestoSet1();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1New = detallepresupuesto.getDetalleproductopresupuestoSet1();
            if (idpresupuestoNew != null) {
                idpresupuestoNew = em.getReference(idpresupuestoNew.getClass(), idpresupuestoNew.getIdpresupuesto());
                detallepresupuesto.setIdpresupuesto(idpresupuestoNew);
            }
            if (idpresupuesto1New != null) {
                idpresupuesto1New = em.getReference(idpresupuesto1New.getClass(), idpresupuesto1New.getIdpresupuesto());
                detallepresupuesto.setIdpresupuesto1(idpresupuesto1New);
            }
            if (idproductoNew != null) {
                idproductoNew = em.getReference(idproductoNew.getClass(), idproductoNew.getIdproducto());
                detallepresupuesto.setIdproducto(idproductoNew);
            }
            if (idproducto1New != null) {
                idproducto1New = em.getReference(idproducto1New.getClass(), idproducto1New.getIdproducto());
                detallepresupuesto.setIdproducto1(idproducto1New);
            }
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSetNew = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSetNew) {
                detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSetNew.add(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSetNew = attachedDetalleproductopresupuestoSetNew;
            detallepresupuesto.setDetalleproductopresupuestoSet(detalleproductopresupuestoSetNew);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet1New = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSet1New) {
                detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet1New.add(detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSet1New = attachedDetalleproductopresupuestoSet1New;
            detallepresupuesto.setDetalleproductopresupuestoSet1(detalleproductopresupuestoSet1New);
            detallepresupuesto = em.merge(detallepresupuesto);
            if (idpresupuestoOld != null && !idpresupuestoOld.equals(idpresupuestoNew)) {
                idpresupuestoOld.getDetallepresupuestoSet().remove(detallepresupuesto);
                idpresupuestoOld = em.merge(idpresupuestoOld);
            }
            if (idpresupuestoNew != null && !idpresupuestoNew.equals(idpresupuestoOld)) {
                idpresupuestoNew.getDetallepresupuestoSet().add(detallepresupuesto);
                idpresupuestoNew = em.merge(idpresupuestoNew);
            }
            if (idpresupuesto1Old != null && !idpresupuesto1Old.equals(idpresupuesto1New)) {
                idpresupuesto1Old.getDetallepresupuestoSet().remove(detallepresupuesto);
                idpresupuesto1Old = em.merge(idpresupuesto1Old);
            }
            if (idpresupuesto1New != null && !idpresupuesto1New.equals(idpresupuesto1Old)) {
                idpresupuesto1New.getDetallepresupuestoSet().add(detallepresupuesto);
                idpresupuesto1New = em.merge(idpresupuesto1New);
            }
            if (idproductoOld != null && !idproductoOld.equals(idproductoNew)) {
                idproductoOld.getDetallepresupuestoSet().remove(detallepresupuesto);
                idproductoOld = em.merge(idproductoOld);
            }
            if (idproductoNew != null && !idproductoNew.equals(idproductoOld)) {
                idproductoNew.getDetallepresupuestoSet().add(detallepresupuesto);
                idproductoNew = em.merge(idproductoNew);
            }
            if (idproducto1Old != null && !idproducto1Old.equals(idproducto1New)) {
                idproducto1Old.getDetallepresupuestoSet().remove(detallepresupuesto);
                idproducto1Old = em.merge(idproducto1Old);
            }
            if (idproducto1New != null && !idproducto1New.equals(idproducto1Old)) {
                idproducto1New.getDetallepresupuestoSet().add(detallepresupuesto);
                idproducto1New = em.merge(idproducto1New);
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
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1OldDetalleproductopresupuesto : detalleproductopresupuestoSet1Old) {
                if (!detalleproductopresupuestoSet1New.contains(detalleproductopresupuestoSet1OldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoSet1OldDetalleproductopresupuesto.setIddetallepresupuesto1(null);
                    detalleproductopresupuestoSet1OldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1OldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1NewDetalleproductopresupuesto : detalleproductopresupuestoSet1New) {
                if (!detalleproductopresupuestoSet1Old.contains(detalleproductopresupuestoSet1NewDetalleproductopresupuesto)) {
                    Detallepresupuesto oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto = detalleproductopresupuestoSet1NewDetalleproductopresupuesto.getIddetallepresupuesto1();
                    detalleproductopresupuestoSet1NewDetalleproductopresupuesto.setIddetallepresupuesto1(detallepresupuesto);
                    detalleproductopresupuestoSet1NewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1NewDetalleproductopresupuesto);
                    if (oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto != null && !oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto.equals(detallepresupuesto)) {
                        oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto.getDetalleproductopresupuestoSet1().remove(detalleproductopresupuestoSet1NewDetalleproductopresupuesto);
                        oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto = em.merge(oldIddetallepresupuesto1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto);
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
            Presupuesto idpresupuesto1 = detallepresupuesto.getIdpresupuesto1();
            if (idpresupuesto1 != null) {
                idpresupuesto1.getDetallepresupuestoSet().remove(detallepresupuesto);
                idpresupuesto1 = em.merge(idpresupuesto1);
            }
            Producto idproducto = detallepresupuesto.getIdproducto();
            if (idproducto != null) {
                idproducto.getDetallepresupuestoSet().remove(detallepresupuesto);
                idproducto = em.merge(idproducto);
            }
            Producto idproducto1 = detallepresupuesto.getIdproducto1();
            if (idproducto1 != null) {
                idproducto1.getDetallepresupuestoSet().remove(detallepresupuesto);
                idproducto1 = em.merge(idproducto1);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet = detallepresupuesto.getDetalleproductopresupuestoSet();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : detalleproductopresupuestoSet) {
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIddetallepresupuesto(null);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1 = detallepresupuesto.getDetalleproductopresupuestoSet1();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1Detalleproductopresupuesto : detalleproductopresupuestoSet1) {
                detalleproductopresupuestoSet1Detalleproductopresupuesto.setIddetallepresupuesto1(null);
                detalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1Detalleproductopresupuesto);
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
