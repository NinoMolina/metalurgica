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
        if (codigodebarra.getProductorealSet1() == null) {
            codigodebarra.setProductorealSet1(new HashSet<Productoreal>());
        }
        if (codigodebarra.getMateriaprimaSet() == null) {
            codigodebarra.setMateriaprimaSet(new HashSet<Materiaprima>());
        }
        if (codigodebarra.getMateriaprimaSet1() == null) {
            codigodebarra.setMateriaprimaSet1(new HashSet<Materiaprima>());
        }
        if (codigodebarra.getPiezarealSet() == null) {
            codigodebarra.setPiezarealSet(new HashSet<Piezareal>());
        }
        if (codigodebarra.getPiezarealSet1() == null) {
            codigodebarra.setPiezarealSet1(new HashSet<Piezareal>());
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
            Set<Productoreal> attachedProductorealSet1 = new HashSet<Productoreal>();
            for (Productoreal productorealSet1ProductorealToAttach : codigodebarra.getProductorealSet1()) {
                productorealSet1ProductorealToAttach = em.getReference(productorealSet1ProductorealToAttach.getClass(), productorealSet1ProductorealToAttach.getIdproductoreal());
                attachedProductorealSet1.add(productorealSet1ProductorealToAttach);
            }
            codigodebarra.setProductorealSet1(attachedProductorealSet1);
            Set<Materiaprima> attachedMateriaprimaSet = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetMateriaprimaToAttach : codigodebarra.getMateriaprimaSet()) {
                materiaprimaSetMateriaprimaToAttach = em.getReference(materiaprimaSetMateriaprimaToAttach.getClass(), materiaprimaSetMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet.add(materiaprimaSetMateriaprimaToAttach);
            }
            codigodebarra.setMateriaprimaSet(attachedMateriaprimaSet);
            Set<Materiaprima> attachedMateriaprimaSet1 = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSet1MateriaprimaToAttach : codigodebarra.getMateriaprimaSet1()) {
                materiaprimaSet1MateriaprimaToAttach = em.getReference(materiaprimaSet1MateriaprimaToAttach.getClass(), materiaprimaSet1MateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet1.add(materiaprimaSet1MateriaprimaToAttach);
            }
            codigodebarra.setMateriaprimaSet1(attachedMateriaprimaSet1);
            Set<Piezareal> attachedPiezarealSet = new HashSet<Piezareal>();
            for (Piezareal piezarealSetPiezarealToAttach : codigodebarra.getPiezarealSet()) {
                piezarealSetPiezarealToAttach = em.getReference(piezarealSetPiezarealToAttach.getClass(), piezarealSetPiezarealToAttach.getIdpiezareal());
                attachedPiezarealSet.add(piezarealSetPiezarealToAttach);
            }
            codigodebarra.setPiezarealSet(attachedPiezarealSet);
            Set<Piezareal> attachedPiezarealSet1 = new HashSet<Piezareal>();
            for (Piezareal piezarealSet1PiezarealToAttach : codigodebarra.getPiezarealSet1()) {
                piezarealSet1PiezarealToAttach = em.getReference(piezarealSet1PiezarealToAttach.getClass(), piezarealSet1PiezarealToAttach.getIdpiezareal());
                attachedPiezarealSet1.add(piezarealSet1PiezarealToAttach);
            }
            codigodebarra.setPiezarealSet1(attachedPiezarealSet1);
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
            for (Productoreal productorealSet1Productoreal : codigodebarra.getProductorealSet1()) {
                Codigodebarra oldCodigobarra1OfProductorealSet1Productoreal = productorealSet1Productoreal.getCodigobarra1();
                productorealSet1Productoreal.setCodigobarra1(codigodebarra);
                productorealSet1Productoreal = em.merge(productorealSet1Productoreal);
                if (oldCodigobarra1OfProductorealSet1Productoreal != null) {
                    oldCodigobarra1OfProductorealSet1Productoreal.getProductorealSet1().remove(productorealSet1Productoreal);
                    oldCodigobarra1OfProductorealSet1Productoreal = em.merge(oldCodigobarra1OfProductorealSet1Productoreal);
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
            for (Materiaprima materiaprimaSet1Materiaprima : codigodebarra.getMateriaprimaSet1()) {
                Codigodebarra oldCodbarra1OfMateriaprimaSet1Materiaprima = materiaprimaSet1Materiaprima.getCodbarra1();
                materiaprimaSet1Materiaprima.setCodbarra1(codigodebarra);
                materiaprimaSet1Materiaprima = em.merge(materiaprimaSet1Materiaprima);
                if (oldCodbarra1OfMateriaprimaSet1Materiaprima != null) {
                    oldCodbarra1OfMateriaprimaSet1Materiaprima.getMateriaprimaSet1().remove(materiaprimaSet1Materiaprima);
                    oldCodbarra1OfMateriaprimaSet1Materiaprima = em.merge(oldCodbarra1OfMateriaprimaSet1Materiaprima);
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
            for (Piezareal piezarealSet1Piezareal : codigodebarra.getPiezarealSet1()) {
                Codigodebarra oldIdcodigobarra1OfPiezarealSet1Piezareal = piezarealSet1Piezareal.getIdcodigobarra1();
                piezarealSet1Piezareal.setIdcodigobarra1(codigodebarra);
                piezarealSet1Piezareal = em.merge(piezarealSet1Piezareal);
                if (oldIdcodigobarra1OfPiezarealSet1Piezareal != null) {
                    oldIdcodigobarra1OfPiezarealSet1Piezareal.getPiezarealSet1().remove(piezarealSet1Piezareal);
                    oldIdcodigobarra1OfPiezarealSet1Piezareal = em.merge(oldIdcodigobarra1OfPiezarealSet1Piezareal);
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
            Set<Productoreal> productorealSet1Old = persistentCodigodebarra.getProductorealSet1();
            Set<Productoreal> productorealSet1New = codigodebarra.getProductorealSet1();
            Set<Materiaprima> materiaprimaSetOld = persistentCodigodebarra.getMateriaprimaSet();
            Set<Materiaprima> materiaprimaSetNew = codigodebarra.getMateriaprimaSet();
            Set<Materiaprima> materiaprimaSet1Old = persistentCodigodebarra.getMateriaprimaSet1();
            Set<Materiaprima> materiaprimaSet1New = codigodebarra.getMateriaprimaSet1();
            Set<Piezareal> piezarealSetOld = persistentCodigodebarra.getPiezarealSet();
            Set<Piezareal> piezarealSetNew = codigodebarra.getPiezarealSet();
            Set<Piezareal> piezarealSet1Old = persistentCodigodebarra.getPiezarealSet1();
            Set<Piezareal> piezarealSet1New = codigodebarra.getPiezarealSet1();
            Set<Productoreal> attachedProductorealSetNew = new HashSet<Productoreal>();
            for (Productoreal productorealSetNewProductorealToAttach : productorealSetNew) {
                productorealSetNewProductorealToAttach = em.getReference(productorealSetNewProductorealToAttach.getClass(), productorealSetNewProductorealToAttach.getIdproductoreal());
                attachedProductorealSetNew.add(productorealSetNewProductorealToAttach);
            }
            productorealSetNew = attachedProductorealSetNew;
            codigodebarra.setProductorealSet(productorealSetNew);
            Set<Productoreal> attachedProductorealSet1New = new HashSet<Productoreal>();
            for (Productoreal productorealSet1NewProductorealToAttach : productorealSet1New) {
                productorealSet1NewProductorealToAttach = em.getReference(productorealSet1NewProductorealToAttach.getClass(), productorealSet1NewProductorealToAttach.getIdproductoreal());
                attachedProductorealSet1New.add(productorealSet1NewProductorealToAttach);
            }
            productorealSet1New = attachedProductorealSet1New;
            codigodebarra.setProductorealSet1(productorealSet1New);
            Set<Materiaprima> attachedMateriaprimaSetNew = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetNewMateriaprimaToAttach : materiaprimaSetNew) {
                materiaprimaSetNewMateriaprimaToAttach = em.getReference(materiaprimaSetNewMateriaprimaToAttach.getClass(), materiaprimaSetNewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSetNew.add(materiaprimaSetNewMateriaprimaToAttach);
            }
            materiaprimaSetNew = attachedMateriaprimaSetNew;
            codigodebarra.setMateriaprimaSet(materiaprimaSetNew);
            Set<Materiaprima> attachedMateriaprimaSet1New = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSet1NewMateriaprimaToAttach : materiaprimaSet1New) {
                materiaprimaSet1NewMateriaprimaToAttach = em.getReference(materiaprimaSet1NewMateriaprimaToAttach.getClass(), materiaprimaSet1NewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet1New.add(materiaprimaSet1NewMateriaprimaToAttach);
            }
            materiaprimaSet1New = attachedMateriaprimaSet1New;
            codigodebarra.setMateriaprimaSet1(materiaprimaSet1New);
            Set<Piezareal> attachedPiezarealSetNew = new HashSet<Piezareal>();
            for (Piezareal piezarealSetNewPiezarealToAttach : piezarealSetNew) {
                piezarealSetNewPiezarealToAttach = em.getReference(piezarealSetNewPiezarealToAttach.getClass(), piezarealSetNewPiezarealToAttach.getIdpiezareal());
                attachedPiezarealSetNew.add(piezarealSetNewPiezarealToAttach);
            }
            piezarealSetNew = attachedPiezarealSetNew;
            codigodebarra.setPiezarealSet(piezarealSetNew);
            Set<Piezareal> attachedPiezarealSet1New = new HashSet<Piezareal>();
            for (Piezareal piezarealSet1NewPiezarealToAttach : piezarealSet1New) {
                piezarealSet1NewPiezarealToAttach = em.getReference(piezarealSet1NewPiezarealToAttach.getClass(), piezarealSet1NewPiezarealToAttach.getIdpiezareal());
                attachedPiezarealSet1New.add(piezarealSet1NewPiezarealToAttach);
            }
            piezarealSet1New = attachedPiezarealSet1New;
            codigodebarra.setPiezarealSet1(piezarealSet1New);
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
            for (Productoreal productorealSet1OldProductoreal : productorealSet1Old) {
                if (!productorealSet1New.contains(productorealSet1OldProductoreal)) {
                    productorealSet1OldProductoreal.setCodigobarra1(null);
                    productorealSet1OldProductoreal = em.merge(productorealSet1OldProductoreal);
                }
            }
            for (Productoreal productorealSet1NewProductoreal : productorealSet1New) {
                if (!productorealSet1Old.contains(productorealSet1NewProductoreal)) {
                    Codigodebarra oldCodigobarra1OfProductorealSet1NewProductoreal = productorealSet1NewProductoreal.getCodigobarra1();
                    productorealSet1NewProductoreal.setCodigobarra1(codigodebarra);
                    productorealSet1NewProductoreal = em.merge(productorealSet1NewProductoreal);
                    if (oldCodigobarra1OfProductorealSet1NewProductoreal != null && !oldCodigobarra1OfProductorealSet1NewProductoreal.equals(codigodebarra)) {
                        oldCodigobarra1OfProductorealSet1NewProductoreal.getProductorealSet1().remove(productorealSet1NewProductoreal);
                        oldCodigobarra1OfProductorealSet1NewProductoreal = em.merge(oldCodigobarra1OfProductorealSet1NewProductoreal);
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
            for (Materiaprima materiaprimaSet1OldMateriaprima : materiaprimaSet1Old) {
                if (!materiaprimaSet1New.contains(materiaprimaSet1OldMateriaprima)) {
                    materiaprimaSet1OldMateriaprima.setCodbarra1(null);
                    materiaprimaSet1OldMateriaprima = em.merge(materiaprimaSet1OldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaSet1NewMateriaprima : materiaprimaSet1New) {
                if (!materiaprimaSet1Old.contains(materiaprimaSet1NewMateriaprima)) {
                    Codigodebarra oldCodbarra1OfMateriaprimaSet1NewMateriaprima = materiaprimaSet1NewMateriaprima.getCodbarra1();
                    materiaprimaSet1NewMateriaprima.setCodbarra1(codigodebarra);
                    materiaprimaSet1NewMateriaprima = em.merge(materiaprimaSet1NewMateriaprima);
                    if (oldCodbarra1OfMateriaprimaSet1NewMateriaprima != null && !oldCodbarra1OfMateriaprimaSet1NewMateriaprima.equals(codigodebarra)) {
                        oldCodbarra1OfMateriaprimaSet1NewMateriaprima.getMateriaprimaSet1().remove(materiaprimaSet1NewMateriaprima);
                        oldCodbarra1OfMateriaprimaSet1NewMateriaprima = em.merge(oldCodbarra1OfMateriaprimaSet1NewMateriaprima);
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
            for (Piezareal piezarealSet1OldPiezareal : piezarealSet1Old) {
                if (!piezarealSet1New.contains(piezarealSet1OldPiezareal)) {
                    piezarealSet1OldPiezareal.setIdcodigobarra1(null);
                    piezarealSet1OldPiezareal = em.merge(piezarealSet1OldPiezareal);
                }
            }
            for (Piezareal piezarealSet1NewPiezareal : piezarealSet1New) {
                if (!piezarealSet1Old.contains(piezarealSet1NewPiezareal)) {
                    Codigodebarra oldIdcodigobarra1OfPiezarealSet1NewPiezareal = piezarealSet1NewPiezareal.getIdcodigobarra1();
                    piezarealSet1NewPiezareal.setIdcodigobarra1(codigodebarra);
                    piezarealSet1NewPiezareal = em.merge(piezarealSet1NewPiezareal);
                    if (oldIdcodigobarra1OfPiezarealSet1NewPiezareal != null && !oldIdcodigobarra1OfPiezarealSet1NewPiezareal.equals(codigodebarra)) {
                        oldIdcodigobarra1OfPiezarealSet1NewPiezareal.getPiezarealSet1().remove(piezarealSet1NewPiezareal);
                        oldIdcodigobarra1OfPiezarealSet1NewPiezareal = em.merge(oldIdcodigobarra1OfPiezarealSet1NewPiezareal);
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
            Set<Productoreal> productorealSet1 = codigodebarra.getProductorealSet1();
            for (Productoreal productorealSet1Productoreal : productorealSet1) {
                productorealSet1Productoreal.setCodigobarra1(null);
                productorealSet1Productoreal = em.merge(productorealSet1Productoreal);
            }
            Set<Materiaprima> materiaprimaSet = codigodebarra.getMateriaprimaSet();
            for (Materiaprima materiaprimaSetMateriaprima : materiaprimaSet) {
                materiaprimaSetMateriaprima.setCodbarra(null);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
            }
            Set<Materiaprima> materiaprimaSet1 = codigodebarra.getMateriaprimaSet1();
            for (Materiaprima materiaprimaSet1Materiaprima : materiaprimaSet1) {
                materiaprimaSet1Materiaprima.setCodbarra1(null);
                materiaprimaSet1Materiaprima = em.merge(materiaprimaSet1Materiaprima);
            }
            Set<Piezareal> piezarealSet = codigodebarra.getPiezarealSet();
            for (Piezareal piezarealSetPiezareal : piezarealSet) {
                piezarealSetPiezareal.setIdcodigobarra(null);
                piezarealSetPiezareal = em.merge(piezarealSetPiezareal);
            }
            Set<Piezareal> piezarealSet1 = codigodebarra.getPiezarealSet1();
            for (Piezareal piezarealSet1Piezareal : piezarealSet1) {
                piezarealSet1Piezareal.setIdcodigobarra1(null);
                piezarealSet1Piezareal = em.merge(piezarealSet1Piezareal);
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
