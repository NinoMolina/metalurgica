/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Codigodebarra;
import metalsoft.datos.jpa.entity.Productoreal;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Piezareal;

/**
 *
 * @author Nino
 */
public class CodigodebarraJpaController implements Serializable {

    public CodigodebarraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Codigodebarra codigodebarra) throws PreexistingEntityException, Exception {
        if (codigodebarra.getProductorealList() == null) {
            codigodebarra.setProductorealList(new ArrayList<Productoreal>());
        }
        if (codigodebarra.getMateriaprimaList() == null) {
            codigodebarra.setMateriaprimaList(new ArrayList<Materiaprima>());
        }
        if (codigodebarra.getPiezarealList() == null) {
            codigodebarra.setPiezarealList(new ArrayList<Piezareal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Productoreal> attachedProductorealList = new ArrayList<Productoreal>();
            for (Productoreal productorealListProductorealToAttach : codigodebarra.getProductorealList()) {
                productorealListProductorealToAttach = em.getReference(productorealListProductorealToAttach.getClass(), productorealListProductorealToAttach.getIdproductoreal());
                attachedProductorealList.add(productorealListProductorealToAttach);
            }
            codigodebarra.setProductorealList(attachedProductorealList);
            List<Materiaprima> attachedMateriaprimaList = new ArrayList<Materiaprima>();
            for (Materiaprima materiaprimaListMateriaprimaToAttach : codigodebarra.getMateriaprimaList()) {
                materiaprimaListMateriaprimaToAttach = em.getReference(materiaprimaListMateriaprimaToAttach.getClass(), materiaprimaListMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaList.add(materiaprimaListMateriaprimaToAttach);
            }
            codigodebarra.setMateriaprimaList(attachedMateriaprimaList);
            List<Piezareal> attachedPiezarealList = new ArrayList<Piezareal>();
            for (Piezareal piezarealListPiezarealToAttach : codigodebarra.getPiezarealList()) {
                piezarealListPiezarealToAttach = em.getReference(piezarealListPiezarealToAttach.getClass(), piezarealListPiezarealToAttach.getIdpiezareal());
                attachedPiezarealList.add(piezarealListPiezarealToAttach);
            }
            codigodebarra.setPiezarealList(attachedPiezarealList);
            em.persist(codigodebarra);
            for (Productoreal productorealListProductoreal : codigodebarra.getProductorealList()) {
                Codigodebarra oldCodigobarraOfProductorealListProductoreal = productorealListProductoreal.getCodigobarra();
                productorealListProductoreal.setCodigobarra(codigodebarra);
                productorealListProductoreal = em.merge(productorealListProductoreal);
                if (oldCodigobarraOfProductorealListProductoreal != null) {
                    oldCodigobarraOfProductorealListProductoreal.getProductorealList().remove(productorealListProductoreal);
                    oldCodigobarraOfProductorealListProductoreal = em.merge(oldCodigobarraOfProductorealListProductoreal);
                }
            }
            for (Materiaprima materiaprimaListMateriaprima : codigodebarra.getMateriaprimaList()) {
                Codigodebarra oldCodbarraOfMateriaprimaListMateriaprima = materiaprimaListMateriaprima.getCodbarra();
                materiaprimaListMateriaprima.setCodbarra(codigodebarra);
                materiaprimaListMateriaprima = em.merge(materiaprimaListMateriaprima);
                if (oldCodbarraOfMateriaprimaListMateriaprima != null) {
                    oldCodbarraOfMateriaprimaListMateriaprima.getMateriaprimaList().remove(materiaprimaListMateriaprima);
                    oldCodbarraOfMateriaprimaListMateriaprima = em.merge(oldCodbarraOfMateriaprimaListMateriaprima);
                }
            }
            for (Piezareal piezarealListPiezareal : codigodebarra.getPiezarealList()) {
                Codigodebarra oldIdcodigobarraOfPiezarealListPiezareal = piezarealListPiezareal.getIdcodigobarra();
                piezarealListPiezareal.setIdcodigobarra(codigodebarra);
                piezarealListPiezareal = em.merge(piezarealListPiezareal);
                if (oldIdcodigobarraOfPiezarealListPiezareal != null) {
                    oldIdcodigobarraOfPiezarealListPiezareal.getPiezarealList().remove(piezarealListPiezareal);
                    oldIdcodigobarraOfPiezarealListPiezareal = em.merge(oldIdcodigobarraOfPiezarealListPiezareal);
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
            List<Productoreal> productorealListOld = persistentCodigodebarra.getProductorealList();
            List<Productoreal> productorealListNew = codigodebarra.getProductorealList();
            List<Materiaprima> materiaprimaListOld = persistentCodigodebarra.getMateriaprimaList();
            List<Materiaprima> materiaprimaListNew = codigodebarra.getMateriaprimaList();
            List<Piezareal> piezarealListOld = persistentCodigodebarra.getPiezarealList();
            List<Piezareal> piezarealListNew = codigodebarra.getPiezarealList();
            List<Productoreal> attachedProductorealListNew = new ArrayList<Productoreal>();
            for (Productoreal productorealListNewProductorealToAttach : productorealListNew) {
                productorealListNewProductorealToAttach = em.getReference(productorealListNewProductorealToAttach.getClass(), productorealListNewProductorealToAttach.getIdproductoreal());
                attachedProductorealListNew.add(productorealListNewProductorealToAttach);
            }
            productorealListNew = attachedProductorealListNew;
            codigodebarra.setProductorealList(productorealListNew);
            List<Materiaprima> attachedMateriaprimaListNew = new ArrayList<Materiaprima>();
            for (Materiaprima materiaprimaListNewMateriaprimaToAttach : materiaprimaListNew) {
                materiaprimaListNewMateriaprimaToAttach = em.getReference(materiaprimaListNewMateriaprimaToAttach.getClass(), materiaprimaListNewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaListNew.add(materiaprimaListNewMateriaprimaToAttach);
            }
            materiaprimaListNew = attachedMateriaprimaListNew;
            codigodebarra.setMateriaprimaList(materiaprimaListNew);
            List<Piezareal> attachedPiezarealListNew = new ArrayList<Piezareal>();
            for (Piezareal piezarealListNewPiezarealToAttach : piezarealListNew) {
                piezarealListNewPiezarealToAttach = em.getReference(piezarealListNewPiezarealToAttach.getClass(), piezarealListNewPiezarealToAttach.getIdpiezareal());
                attachedPiezarealListNew.add(piezarealListNewPiezarealToAttach);
            }
            piezarealListNew = attachedPiezarealListNew;
            codigodebarra.setPiezarealList(piezarealListNew);
            codigodebarra = em.merge(codigodebarra);
            for (Productoreal productorealListOldProductoreal : productorealListOld) {
                if (!productorealListNew.contains(productorealListOldProductoreal)) {
                    productorealListOldProductoreal.setCodigobarra(null);
                    productorealListOldProductoreal = em.merge(productorealListOldProductoreal);
                }
            }
            for (Productoreal productorealListNewProductoreal : productorealListNew) {
                if (!productorealListOld.contains(productorealListNewProductoreal)) {
                    Codigodebarra oldCodigobarraOfProductorealListNewProductoreal = productorealListNewProductoreal.getCodigobarra();
                    productorealListNewProductoreal.setCodigobarra(codigodebarra);
                    productorealListNewProductoreal = em.merge(productorealListNewProductoreal);
                    if (oldCodigobarraOfProductorealListNewProductoreal != null && !oldCodigobarraOfProductorealListNewProductoreal.equals(codigodebarra)) {
                        oldCodigobarraOfProductorealListNewProductoreal.getProductorealList().remove(productorealListNewProductoreal);
                        oldCodigobarraOfProductorealListNewProductoreal = em.merge(oldCodigobarraOfProductorealListNewProductoreal);
                    }
                }
            }
            for (Materiaprima materiaprimaListOldMateriaprima : materiaprimaListOld) {
                if (!materiaprimaListNew.contains(materiaprimaListOldMateriaprima)) {
                    materiaprimaListOldMateriaprima.setCodbarra(null);
                    materiaprimaListOldMateriaprima = em.merge(materiaprimaListOldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaListNewMateriaprima : materiaprimaListNew) {
                if (!materiaprimaListOld.contains(materiaprimaListNewMateriaprima)) {
                    Codigodebarra oldCodbarraOfMateriaprimaListNewMateriaprima = materiaprimaListNewMateriaprima.getCodbarra();
                    materiaprimaListNewMateriaprima.setCodbarra(codigodebarra);
                    materiaprimaListNewMateriaprima = em.merge(materiaprimaListNewMateriaprima);
                    if (oldCodbarraOfMateriaprimaListNewMateriaprima != null && !oldCodbarraOfMateriaprimaListNewMateriaprima.equals(codigodebarra)) {
                        oldCodbarraOfMateriaprimaListNewMateriaprima.getMateriaprimaList().remove(materiaprimaListNewMateriaprima);
                        oldCodbarraOfMateriaprimaListNewMateriaprima = em.merge(oldCodbarraOfMateriaprimaListNewMateriaprima);
                    }
                }
            }
            for (Piezareal piezarealListOldPiezareal : piezarealListOld) {
                if (!piezarealListNew.contains(piezarealListOldPiezareal)) {
                    piezarealListOldPiezareal.setIdcodigobarra(null);
                    piezarealListOldPiezareal = em.merge(piezarealListOldPiezareal);
                }
            }
            for (Piezareal piezarealListNewPiezareal : piezarealListNew) {
                if (!piezarealListOld.contains(piezarealListNewPiezareal)) {
                    Codigodebarra oldIdcodigobarraOfPiezarealListNewPiezareal = piezarealListNewPiezareal.getIdcodigobarra();
                    piezarealListNewPiezareal.setIdcodigobarra(codigodebarra);
                    piezarealListNewPiezareal = em.merge(piezarealListNewPiezareal);
                    if (oldIdcodigobarraOfPiezarealListNewPiezareal != null && !oldIdcodigobarraOfPiezarealListNewPiezareal.equals(codigodebarra)) {
                        oldIdcodigobarraOfPiezarealListNewPiezareal.getPiezarealList().remove(piezarealListNewPiezareal);
                        oldIdcodigobarraOfPiezarealListNewPiezareal = em.merge(oldIdcodigobarraOfPiezarealListNewPiezareal);
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
            List<Productoreal> productorealList = codigodebarra.getProductorealList();
            for (Productoreal productorealListProductoreal : productorealList) {
                productorealListProductoreal.setCodigobarra(null);
                productorealListProductoreal = em.merge(productorealListProductoreal);
            }
            List<Materiaprima> materiaprimaList = codigodebarra.getMateriaprimaList();
            for (Materiaprima materiaprimaListMateriaprima : materiaprimaList) {
                materiaprimaListMateriaprima.setCodbarra(null);
                materiaprimaListMateriaprima = em.merge(materiaprimaListMateriaprima);
            }
            List<Piezareal> piezarealList = codigodebarra.getPiezarealList();
            for (Piezareal piezarealListPiezareal : piezarealList) {
                piezarealListPiezareal.setIdcodigobarra(null);
                piezarealListPiezareal = em.merge(piezarealListPiezareal);
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
