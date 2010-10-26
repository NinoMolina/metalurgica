/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Codigodebarra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Productoreal;
import java.util.HashSet;
import java.util.Set;
import entity.Materiaprima;
import entity.Piezareal;

/**
 *
 * @author Nino
 */
public class CodigodebarraJpaController {

    public CodigodebarraJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Codigodebarra codigodebarra) throws PreexistingEntityException, Exception {
        if (codigodebarra.getProductorealSet() == null) {
            codigodebarra.setProductorealSet(new HashSet<Productoreal>());
        }
        if (codigodebarra.getMateriaprimaSet() == null) {
            codigodebarra.setMateriaprimaSet(new HashSet<Materiaprima>());
        }
        if (codigodebarra.getPiezarealSet() == null) {
            codigodebarra.setPiezarealSet(new HashSet<Piezareal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Productoreal> attachedProductorealSet = new HashSet<Productoreal>();
            for (Productoreal productorealSetProductorealToAttach : codigodebarra.getProductorealSet()) {
                productorealSetProductorealToAttach = em.getReference(productorealSetProductorealToAttach.getClass(), productorealSetProductorealToAttach.getIdproductoreal());
                attachedProductorealSet.add(productorealSetProductorealToAttach);
            }
            codigodebarra.setProductorealSet(attachedProductorealSet);
            Set<Materiaprima> attachedMateriaprimaSet = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetMateriaprimaToAttach : codigodebarra.getMateriaprimaSet()) {
                materiaprimaSetMateriaprimaToAttach = em.getReference(materiaprimaSetMateriaprimaToAttach.getClass(), materiaprimaSetMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet.add(materiaprimaSetMateriaprimaToAttach);
            }
            codigodebarra.setMateriaprimaSet(attachedMateriaprimaSet);
            Set<Piezareal> attachedPiezarealSet = new HashSet<Piezareal>();
            for (Piezareal piezarealSetPiezarealToAttach : codigodebarra.getPiezarealSet()) {
                piezarealSetPiezarealToAttach = em.getReference(piezarealSetPiezarealToAttach.getClass(), piezarealSetPiezarealToAttach.getIdpiezareal());
                attachedPiezarealSet.add(piezarealSetPiezarealToAttach);
            }
            codigodebarra.setPiezarealSet(attachedPiezarealSet);
            em.persist(codigodebarra);
            for (Productoreal productorealSetProductoreal : codigodebarra.getProductorealSet()) {
                Codigodebarra oldCodigobarraOfProductorealSetProductoreal = productorealSetProductoreal.getCodigobarra();
                productorealSetProductoreal.setCodigobarra(codigodebarra);
                productorealSetProductoreal = em.merge(productorealSetProductoreal);
                if (oldCodigobarraOfProductorealSetProductoreal != null) {
                    oldCodigobarraOfProductorealSetProductoreal.getProductorealSet().remove(productorealSetProductoreal);
                    oldCodigobarraOfProductorealSetProductoreal = em.merge(oldCodigobarraOfProductorealSetProductoreal);
                }
            }
            for (Materiaprima materiaprimaSetMateriaprima : codigodebarra.getMateriaprimaSet()) {
                Codigodebarra oldCodbarraOfMateriaprimaSetMateriaprima = materiaprimaSetMateriaprima.getCodbarra();
                materiaprimaSetMateriaprima.setCodbarra(codigodebarra);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
                if (oldCodbarraOfMateriaprimaSetMateriaprima != null) {
                    oldCodbarraOfMateriaprimaSetMateriaprima.getMateriaprimaSet().remove(materiaprimaSetMateriaprima);
                    oldCodbarraOfMateriaprimaSetMateriaprima = em.merge(oldCodbarraOfMateriaprimaSetMateriaprima);
                }
            }
            for (Piezareal piezarealSetPiezareal : codigodebarra.getPiezarealSet()) {
                Codigodebarra oldIdcodigobarraOfPiezarealSetPiezareal = piezarealSetPiezareal.getIdcodigobarra();
                piezarealSetPiezareal.setIdcodigobarra(codigodebarra);
                piezarealSetPiezareal = em.merge(piezarealSetPiezareal);
                if (oldIdcodigobarraOfPiezarealSetPiezareal != null) {
                    oldIdcodigobarraOfPiezarealSetPiezareal.getPiezarealSet().remove(piezarealSetPiezareal);
                    oldIdcodigobarraOfPiezarealSetPiezareal = em.merge(oldIdcodigobarraOfPiezarealSetPiezareal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCodigodebarra(codigodebarra.getIdcodigo()) != null) {
                throw new PreexistingEntityException("Codigodebarra " + codigodebarra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Codigodebarra codigodebarra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codigodebarra persistentCodigodebarra = em.find(Codigodebarra.class, codigodebarra.getIdcodigo());
            Set<Productoreal> productorealSetOld = persistentCodigodebarra.getProductorealSet();
            Set<Productoreal> productorealSetNew = codigodebarra.getProductorealSet();
            Set<Materiaprima> materiaprimaSetOld = persistentCodigodebarra.getMateriaprimaSet();
            Set<Materiaprima> materiaprimaSetNew = codigodebarra.getMateriaprimaSet();
            Set<Piezareal> piezarealSetOld = persistentCodigodebarra.getPiezarealSet();
            Set<Piezareal> piezarealSetNew = codigodebarra.getPiezarealSet();
            Set<Productoreal> attachedProductorealSetNew = new HashSet<Productoreal>();
            for (Productoreal productorealSetNewProductorealToAttach : productorealSetNew) {
                productorealSetNewProductorealToAttach = em.getReference(productorealSetNewProductorealToAttach.getClass(), productorealSetNewProductorealToAttach.getIdproductoreal());
                attachedProductorealSetNew.add(productorealSetNewProductorealToAttach);
            }
            productorealSetNew = attachedProductorealSetNew;
            codigodebarra.setProductorealSet(productorealSetNew);
            Set<Materiaprima> attachedMateriaprimaSetNew = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetNewMateriaprimaToAttach : materiaprimaSetNew) {
                materiaprimaSetNewMateriaprimaToAttach = em.getReference(materiaprimaSetNewMateriaprimaToAttach.getClass(), materiaprimaSetNewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSetNew.add(materiaprimaSetNewMateriaprimaToAttach);
            }
            materiaprimaSetNew = attachedMateriaprimaSetNew;
            codigodebarra.setMateriaprimaSet(materiaprimaSetNew);
            Set<Piezareal> attachedPiezarealSetNew = new HashSet<Piezareal>();
            for (Piezareal piezarealSetNewPiezarealToAttach : piezarealSetNew) {
                piezarealSetNewPiezarealToAttach = em.getReference(piezarealSetNewPiezarealToAttach.getClass(), piezarealSetNewPiezarealToAttach.getIdpiezareal());
                attachedPiezarealSetNew.add(piezarealSetNewPiezarealToAttach);
            }
            piezarealSetNew = attachedPiezarealSetNew;
            codigodebarra.setPiezarealSet(piezarealSetNew);
            codigodebarra = em.merge(codigodebarra);
            for (Productoreal productorealSetOldProductoreal : productorealSetOld) {
                if (!productorealSetNew.contains(productorealSetOldProductoreal)) {
                    productorealSetOldProductoreal.setCodigobarra(null);
                    productorealSetOldProductoreal = em.merge(productorealSetOldProductoreal);
                }
            }
            for (Productoreal productorealSetNewProductoreal : productorealSetNew) {
                if (!productorealSetOld.contains(productorealSetNewProductoreal)) {
                    Codigodebarra oldCodigobarraOfProductorealSetNewProductoreal = productorealSetNewProductoreal.getCodigobarra();
                    productorealSetNewProductoreal.setCodigobarra(codigodebarra);
                    productorealSetNewProductoreal = em.merge(productorealSetNewProductoreal);
                    if (oldCodigobarraOfProductorealSetNewProductoreal != null && !oldCodigobarraOfProductorealSetNewProductoreal.equals(codigodebarra)) {
                        oldCodigobarraOfProductorealSetNewProductoreal.getProductorealSet().remove(productorealSetNewProductoreal);
                        oldCodigobarraOfProductorealSetNewProductoreal = em.merge(oldCodigobarraOfProductorealSetNewProductoreal);
                    }
                }
            }
            for (Materiaprima materiaprimaSetOldMateriaprima : materiaprimaSetOld) {
                if (!materiaprimaSetNew.contains(materiaprimaSetOldMateriaprima)) {
                    materiaprimaSetOldMateriaprima.setCodbarra(null);
                    materiaprimaSetOldMateriaprima = em.merge(materiaprimaSetOldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaSetNewMateriaprima : materiaprimaSetNew) {
                if (!materiaprimaSetOld.contains(materiaprimaSetNewMateriaprima)) {
                    Codigodebarra oldCodbarraOfMateriaprimaSetNewMateriaprima = materiaprimaSetNewMateriaprima.getCodbarra();
                    materiaprimaSetNewMateriaprima.setCodbarra(codigodebarra);
                    materiaprimaSetNewMateriaprima = em.merge(materiaprimaSetNewMateriaprima);
                    if (oldCodbarraOfMateriaprimaSetNewMateriaprima != null && !oldCodbarraOfMateriaprimaSetNewMateriaprima.equals(codigodebarra)) {
                        oldCodbarraOfMateriaprimaSetNewMateriaprima.getMateriaprimaSet().remove(materiaprimaSetNewMateriaprima);
                        oldCodbarraOfMateriaprimaSetNewMateriaprima = em.merge(oldCodbarraOfMateriaprimaSetNewMateriaprima);
                    }
                }
            }
            for (Piezareal piezarealSetOldPiezareal : piezarealSetOld) {
                if (!piezarealSetNew.contains(piezarealSetOldPiezareal)) {
                    piezarealSetOldPiezareal.setIdcodigobarra(null);
                    piezarealSetOldPiezareal = em.merge(piezarealSetOldPiezareal);
                }
            }
            for (Piezareal piezarealSetNewPiezareal : piezarealSetNew) {
                if (!piezarealSetOld.contains(piezarealSetNewPiezareal)) {
                    Codigodebarra oldIdcodigobarraOfPiezarealSetNewPiezareal = piezarealSetNewPiezareal.getIdcodigobarra();
                    piezarealSetNewPiezareal.setIdcodigobarra(codigodebarra);
                    piezarealSetNewPiezareal = em.merge(piezarealSetNewPiezareal);
                    if (oldIdcodigobarraOfPiezarealSetNewPiezareal != null && !oldIdcodigobarraOfPiezarealSetNewPiezareal.equals(codigodebarra)) {
                        oldIdcodigobarraOfPiezarealSetNewPiezareal.getPiezarealSet().remove(piezarealSetNewPiezareal);
                        oldIdcodigobarraOfPiezarealSetNewPiezareal = em.merge(oldIdcodigobarraOfPiezarealSetNewPiezareal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = codigodebarra.getIdcodigo();
                if (findCodigodebarra(id) == null) {
                    throw new NonexistentEntityException("The codigodebarra with id " + id + " no longer exists.");
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
            Codigodebarra codigodebarra;
            try {
                codigodebarra = em.getReference(Codigodebarra.class, id);
                codigodebarra.getIdcodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The codigodebarra with id " + id + " no longer exists.", enfe);
            }
            Set<Productoreal> productorealSet = codigodebarra.getProductorealSet();
            for (Productoreal productorealSetProductoreal : productorealSet) {
                productorealSetProductoreal.setCodigobarra(null);
                productorealSetProductoreal = em.merge(productorealSetProductoreal);
            }
            Set<Materiaprima> materiaprimaSet = codigodebarra.getMateriaprimaSet();
            for (Materiaprima materiaprimaSetMateriaprima : materiaprimaSet) {
                materiaprimaSetMateriaprima.setCodbarra(null);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
            }
            Set<Piezareal> piezarealSet = codigodebarra.getPiezarealSet();
            for (Piezareal piezarealSetPiezareal : piezarealSet) {
                piezarealSetPiezareal.setIdcodigobarra(null);
                piezarealSetPiezareal = em.merge(piezarealSetPiezareal);
            }
            em.remove(codigodebarra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Codigodebarra> findCodigodebarraEntities() {
        return findCodigodebarraEntities(true, -1, -1);
    }

    public List<Codigodebarra> findCodigodebarraEntities(int maxResults, int firstResult) {
        return findCodigodebarraEntities(false, maxResults, firstResult);
    }

    private List<Codigodebarra> findCodigodebarraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Codigodebarra.class));
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

    public Codigodebarra findCodigodebarra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Codigodebarra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCodigodebarraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Codigodebarra> rt = cq.from(Codigodebarra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
