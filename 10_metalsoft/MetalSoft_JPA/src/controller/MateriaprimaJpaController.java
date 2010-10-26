/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Materiaprima;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Codigodebarra;
import entity.Tipomaterial;
import entity.Unidadmedida;
import entity.Detallecompra;
import java.util.HashSet;
import java.util.Set;
import entity.Pieza;
import entity.Detallerequerimientosmateriaprima;
import entity.Matriz;
import entity.Detallempasignada;
import entity.Proveedorxmateriaprima;
import entity.Detalleproductopresupuesto;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class MateriaprimaJpaController {

    public MateriaprimaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materiaprima materiaprima) throws PreexistingEntityException, Exception {
        if (materiaprima.getDetallecompraSet() == null) {
            materiaprima.setDetallecompraSet(new HashSet<Detallecompra>());
        }
        if (materiaprima.getPiezaSet() == null) {
            materiaprima.setPiezaSet(new HashSet<Pieza>());
        }
        if (materiaprima.getDetallerequerimientosmateriaprimaSet() == null) {
            materiaprima.setDetallerequerimientosmateriaprimaSet(new HashSet<Detallerequerimientosmateriaprima>());
        }
        if (materiaprima.getMatrizSet() == null) {
            materiaprima.setMatrizSet(new HashSet<Matriz>());
        }
        if (materiaprima.getDetallempasignadaSet() == null) {
            materiaprima.setDetallempasignadaSet(new HashSet<Detallempasignada>());
        }
        if (materiaprima.getProveedorxmateriaprimaSet() == null) {
            materiaprima.setProveedorxmateriaprimaSet(new HashSet<Proveedorxmateriaprima>());
        }
        if (materiaprima.getDetalleproductopresupuestoSet() == null) {
            materiaprima.setDetalleproductopresupuestoSet(new HashSet<Detalleproductopresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codigodebarra codbarra = materiaprima.getCodbarra();
            if (codbarra != null) {
                codbarra = em.getReference(codbarra.getClass(), codbarra.getIdcodigo());
                materiaprima.setCodbarra(codbarra);
            }
            Tipomaterial tipomaterial = materiaprima.getTipomaterial();
            if (tipomaterial != null) {
                tipomaterial = em.getReference(tipomaterial.getClass(), tipomaterial.getIdtipomaterial());
                materiaprima.setTipomaterial(tipomaterial);
            }
            Unidadmedida unidadmedida = materiaprima.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida = em.getReference(unidadmedida.getClass(), unidadmedida.getIdunidadmedida());
                materiaprima.setUnidadmedida(unidadmedida);
            }
            Set<Detallecompra> attachedDetallecompraSet = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetDetallecompraToAttach : materiaprima.getDetallecompraSet()) {
                detallecompraSetDetallecompraToAttach = em.getReference(detallecompraSetDetallecompraToAttach.getClass(), detallecompraSetDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet.add(detallecompraSetDetallecompraToAttach);
            }
            materiaprima.setDetallecompraSet(attachedDetallecompraSet);
            Set<Pieza> attachedPiezaSet = new HashSet<Pieza>();
            for (Pieza piezaSetPiezaToAttach : materiaprima.getPiezaSet()) {
                piezaSetPiezaToAttach = em.getReference(piezaSetPiezaToAttach.getClass(), piezaSetPiezaToAttach.getIdpieza());
                attachedPiezaSet.add(piezaSetPiezaToAttach);
            }
            materiaprima.setPiezaSet(attachedPiezaSet);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSet = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach : materiaprima.getDetallerequerimientosmateriaprimaSet()) {
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSet.add(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach);
            }
            materiaprima.setDetallerequerimientosmateriaprimaSet(attachedDetallerequerimientosmateriaprimaSet);
            Set<Matriz> attachedMatrizSet = new HashSet<Matriz>();
            for (Matriz matrizSetMatrizToAttach : materiaprima.getMatrizSet()) {
                matrizSetMatrizToAttach = em.getReference(matrizSetMatrizToAttach.getClass(), matrizSetMatrizToAttach.getIdmatriz());
                attachedMatrizSet.add(matrizSetMatrizToAttach);
            }
            materiaprima.setMatrizSet(attachedMatrizSet);
            Set<Detallempasignada> attachedDetallempasignadaSet = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSetDetallempasignadaToAttach : materiaprima.getDetallempasignadaSet()) {
                detallempasignadaSetDetallempasignadaToAttach = em.getReference(detallempasignadaSetDetallempasignadaToAttach.getClass(), detallempasignadaSetDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSet.add(detallempasignadaSetDetallempasignadaToAttach);
            }
            materiaprima.setDetallempasignadaSet(attachedDetallempasignadaSet);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSet = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach : materiaprima.getProveedorxmateriaprimaSet()) {
                proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSet.add(proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach);
            }
            materiaprima.setProveedorxmateriaprimaSet(attachedProveedorxmateriaprimaSet);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuestoToAttach : materiaprima.getDetalleproductopresupuestoSet()) {
                detalleproductopresupuestoSetDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet.add(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach);
            }
            materiaprima.setDetalleproductopresupuestoSet(attachedDetalleproductopresupuestoSet);
            em.persist(materiaprima);
            if (codbarra != null) {
                codbarra.getMateriaprimaSet().add(materiaprima);
                codbarra = em.merge(codbarra);
            }
            if (tipomaterial != null) {
                tipomaterial.getMateriaprimaSet().add(materiaprima);
                tipomaterial = em.merge(tipomaterial);
            }
            if (unidadmedida != null) {
                unidadmedida.getMateriaprimaSet().add(materiaprima);
                unidadmedida = em.merge(unidadmedida);
            }
            for (Detallecompra detallecompraSetDetallecompra : materiaprima.getDetallecompraSet()) {
                Materiaprima oldMateriaprimaOfDetallecompraSetDetallecompra = detallecompraSetDetallecompra.getMateriaprima();
                detallecompraSetDetallecompra.setMateriaprima(materiaprima);
                detallecompraSetDetallecompra = em.merge(detallecompraSetDetallecompra);
                if (oldMateriaprimaOfDetallecompraSetDetallecompra != null) {
                    oldMateriaprimaOfDetallecompraSetDetallecompra.getDetallecompraSet().remove(detallecompraSetDetallecompra);
                    oldMateriaprimaOfDetallecompraSetDetallecompra = em.merge(oldMateriaprimaOfDetallecompraSetDetallecompra);
                }
            }
            for (Pieza piezaSetPieza : materiaprima.getPiezaSet()) {
                Materiaprima oldMateriaprimaOfPiezaSetPieza = piezaSetPieza.getMateriaprima();
                piezaSetPieza.setMateriaprima(materiaprima);
                piezaSetPieza = em.merge(piezaSetPieza);
                if (oldMateriaprimaOfPiezaSetPieza != null) {
                    oldMateriaprimaOfPiezaSetPieza.getPiezaSet().remove(piezaSetPieza);
                    oldMateriaprimaOfPiezaSetPieza = em.merge(oldMateriaprimaOfPiezaSetPieza);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima : materiaprima.getDetallerequerimientosmateriaprimaSet()) {
                Materiaprima oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.getIdmateriaprima();
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.setIdmateriaprima(materiaprima);
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                if (oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima != null) {
                    oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                    oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = em.merge(oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                }
            }
            for (Matriz matrizSetMatriz : materiaprima.getMatrizSet()) {
                Materiaprima oldMateriaprimaOfMatrizSetMatriz = matrizSetMatriz.getMateriaprima();
                matrizSetMatriz.setMateriaprima(materiaprima);
                matrizSetMatriz = em.merge(matrizSetMatriz);
                if (oldMateriaprimaOfMatrizSetMatriz != null) {
                    oldMateriaprimaOfMatrizSetMatriz.getMatrizSet().remove(matrizSetMatriz);
                    oldMateriaprimaOfMatrizSetMatriz = em.merge(oldMateriaprimaOfMatrizSetMatriz);
                }
            }
            for (Detallempasignada detallempasignadaSetDetallempasignada : materiaprima.getDetallempasignadaSet()) {
                Materiaprima oldIdmateriaprimaOfDetallempasignadaSetDetallempasignada = detallempasignadaSetDetallempasignada.getIdmateriaprima();
                detallempasignadaSetDetallempasignada.setIdmateriaprima(materiaprima);
                detallempasignadaSetDetallempasignada = em.merge(detallempasignadaSetDetallempasignada);
                if (oldIdmateriaprimaOfDetallempasignadaSetDetallempasignada != null) {
                    oldIdmateriaprimaOfDetallempasignadaSetDetallempasignada.getDetallempasignadaSet().remove(detallempasignadaSetDetallempasignada);
                    oldIdmateriaprimaOfDetallempasignadaSetDetallempasignada = em.merge(oldIdmateriaprimaOfDetallempasignadaSetDetallempasignada);
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetProveedorxmateriaprima : materiaprima.getProveedorxmateriaprimaSet()) {
                Materiaprima oldMateriaprimaOfProveedorxmateriaprimaSetProveedorxmateriaprima = proveedorxmateriaprimaSetProveedorxmateriaprima.getMateriaprima();
                proveedorxmateriaprimaSetProveedorxmateriaprima.setMateriaprima(materiaprima);
                proveedorxmateriaprimaSetProveedorxmateriaprima = em.merge(proveedorxmateriaprimaSetProveedorxmateriaprima);
                if (oldMateriaprimaOfProveedorxmateriaprimaSetProveedorxmateriaprima != null) {
                    oldMateriaprimaOfProveedorxmateriaprimaSetProveedorxmateriaprima.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprimaSetProveedorxmateriaprima);
                    oldMateriaprimaOfProveedorxmateriaprimaSetProveedorxmateriaprima = em.merge(oldMateriaprimaOfProveedorxmateriaprimaSetProveedorxmateriaprima);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : materiaprima.getDetalleproductopresupuestoSet()) {
                Materiaprima oldIdmateriaprimaOfDetalleproductopresupuestoSetDetalleproductopresupuesto = detalleproductopresupuestoSetDetalleproductopresupuesto.getIdmateriaprima();
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIdmateriaprima(materiaprima);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
                if (oldIdmateriaprimaOfDetalleproductopresupuestoSetDetalleproductopresupuesto != null) {
                    oldIdmateriaprimaOfDetalleproductopresupuestoSetDetalleproductopresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuestoSetDetalleproductopresupuesto);
                    oldIdmateriaprimaOfDetalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(oldIdmateriaprimaOfDetalleproductopresupuestoSetDetalleproductopresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateriaprima(materiaprima.getIdmateriaprima()) != null) {
                throw new PreexistingEntityException("Materiaprima " + materiaprima + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materiaprima materiaprima) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima persistentMateriaprima = em.find(Materiaprima.class, materiaprima.getIdmateriaprima());
            Codigodebarra codbarraOld = persistentMateriaprima.getCodbarra();
            Codigodebarra codbarraNew = materiaprima.getCodbarra();
            Tipomaterial tipomaterialOld = persistentMateriaprima.getTipomaterial();
            Tipomaterial tipomaterialNew = materiaprima.getTipomaterial();
            Unidadmedida unidadmedidaOld = persistentMateriaprima.getUnidadmedida();
            Unidadmedida unidadmedidaNew = materiaprima.getUnidadmedida();
            Set<Detallecompra> detallecompraSetOld = persistentMateriaprima.getDetallecompraSet();
            Set<Detallecompra> detallecompraSetNew = materiaprima.getDetallecompraSet();
            Set<Pieza> piezaSetOld = persistentMateriaprima.getPiezaSet();
            Set<Pieza> piezaSetNew = materiaprima.getPiezaSet();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetOld = persistentMateriaprima.getDetallerequerimientosmateriaprimaSet();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetNew = materiaprima.getDetallerequerimientosmateriaprimaSet();
            Set<Matriz> matrizSetOld = persistentMateriaprima.getMatrizSet();
            Set<Matriz> matrizSetNew = materiaprima.getMatrizSet();
            Set<Detallempasignada> detallempasignadaSetOld = persistentMateriaprima.getDetallempasignadaSet();
            Set<Detallempasignada> detallempasignadaSetNew = materiaprima.getDetallempasignadaSet();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetOld = persistentMateriaprima.getProveedorxmateriaprimaSet();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetNew = materiaprima.getProveedorxmateriaprimaSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetOld = persistentMateriaprima.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetNew = materiaprima.getDetalleproductopresupuestoSet();
            List<String> illegalOrphanMessages = null;
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetOldProveedorxmateriaprima : proveedorxmateriaprimaSetOld) {
                if (!proveedorxmateriaprimaSetNew.contains(proveedorxmateriaprimaSetOldProveedorxmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proveedorxmateriaprima " + proveedorxmateriaprimaSetOldProveedorxmateriaprima + " since its materiaprima field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codbarraNew != null) {
                codbarraNew = em.getReference(codbarraNew.getClass(), codbarraNew.getIdcodigo());
                materiaprima.setCodbarra(codbarraNew);
            }
            if (tipomaterialNew != null) {
                tipomaterialNew = em.getReference(tipomaterialNew.getClass(), tipomaterialNew.getIdtipomaterial());
                materiaprima.setTipomaterial(tipomaterialNew);
            }
            if (unidadmedidaNew != null) {
                unidadmedidaNew = em.getReference(unidadmedidaNew.getClass(), unidadmedidaNew.getIdunidadmedida());
                materiaprima.setUnidadmedida(unidadmedidaNew);
            }
            Set<Detallecompra> attachedDetallecompraSetNew = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetNewDetallecompraToAttach : detallecompraSetNew) {
                detallecompraSetNewDetallecompraToAttach = em.getReference(detallecompraSetNewDetallecompraToAttach.getClass(), detallecompraSetNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSetNew.add(detallecompraSetNewDetallecompraToAttach);
            }
            detallecompraSetNew = attachedDetallecompraSetNew;
            materiaprima.setDetallecompraSet(detallecompraSetNew);
            Set<Pieza> attachedPiezaSetNew = new HashSet<Pieza>();
            for (Pieza piezaSetNewPiezaToAttach : piezaSetNew) {
                piezaSetNewPiezaToAttach = em.getReference(piezaSetNewPiezaToAttach.getClass(), piezaSetNewPiezaToAttach.getIdpieza());
                attachedPiezaSetNew.add(piezaSetNewPiezaToAttach);
            }
            piezaSetNew = attachedPiezaSetNew;
            materiaprima.setPiezaSet(piezaSetNew);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSetNew = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach : detallerequerimientosmateriaprimaSetNew) {
                detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSetNew.add(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach);
            }
            detallerequerimientosmateriaprimaSetNew = attachedDetallerequerimientosmateriaprimaSetNew;
            materiaprima.setDetallerequerimientosmateriaprimaSet(detallerequerimientosmateriaprimaSetNew);
            Set<Matriz> attachedMatrizSetNew = new HashSet<Matriz>();
            for (Matriz matrizSetNewMatrizToAttach : matrizSetNew) {
                matrizSetNewMatrizToAttach = em.getReference(matrizSetNewMatrizToAttach.getClass(), matrizSetNewMatrizToAttach.getIdmatriz());
                attachedMatrizSetNew.add(matrizSetNewMatrizToAttach);
            }
            matrizSetNew = attachedMatrizSetNew;
            materiaprima.setMatrizSet(matrizSetNew);
            Set<Detallempasignada> attachedDetallempasignadaSetNew = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSetNewDetallempasignadaToAttach : detallempasignadaSetNew) {
                detallempasignadaSetNewDetallempasignadaToAttach = em.getReference(detallempasignadaSetNewDetallempasignadaToAttach.getClass(), detallempasignadaSetNewDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSetNew.add(detallempasignadaSetNewDetallempasignadaToAttach);
            }
            detallempasignadaSetNew = attachedDetallempasignadaSetNew;
            materiaprima.setDetallempasignadaSet(detallempasignadaSetNew);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSetNew = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach : proveedorxmateriaprimaSetNew) {
                proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSetNew.add(proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach);
            }
            proveedorxmateriaprimaSetNew = attachedProveedorxmateriaprimaSetNew;
            materiaprima.setProveedorxmateriaprimaSet(proveedorxmateriaprimaSetNew);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSetNew = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSetNew) {
                detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSetNew.add(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSetNew = attachedDetalleproductopresupuestoSetNew;
            materiaprima.setDetalleproductopresupuestoSet(detalleproductopresupuestoSetNew);
            materiaprima = em.merge(materiaprima);
            if (codbarraOld != null && !codbarraOld.equals(codbarraNew)) {
                codbarraOld.getMateriaprimaSet().remove(materiaprima);
                codbarraOld = em.merge(codbarraOld);
            }
            if (codbarraNew != null && !codbarraNew.equals(codbarraOld)) {
                codbarraNew.getMateriaprimaSet().add(materiaprima);
                codbarraNew = em.merge(codbarraNew);
            }
            if (tipomaterialOld != null && !tipomaterialOld.equals(tipomaterialNew)) {
                tipomaterialOld.getMateriaprimaSet().remove(materiaprima);
                tipomaterialOld = em.merge(tipomaterialOld);
            }
            if (tipomaterialNew != null && !tipomaterialNew.equals(tipomaterialOld)) {
                tipomaterialNew.getMateriaprimaSet().add(materiaprima);
                tipomaterialNew = em.merge(tipomaterialNew);
            }
            if (unidadmedidaOld != null && !unidadmedidaOld.equals(unidadmedidaNew)) {
                unidadmedidaOld.getMateriaprimaSet().remove(materiaprima);
                unidadmedidaOld = em.merge(unidadmedidaOld);
            }
            if (unidadmedidaNew != null && !unidadmedidaNew.equals(unidadmedidaOld)) {
                unidadmedidaNew.getMateriaprimaSet().add(materiaprima);
                unidadmedidaNew = em.merge(unidadmedidaNew);
            }
            for (Detallecompra detallecompraSetOldDetallecompra : detallecompraSetOld) {
                if (!detallecompraSetNew.contains(detallecompraSetOldDetallecompra)) {
                    detallecompraSetOldDetallecompra.setMateriaprima(null);
                    detallecompraSetOldDetallecompra = em.merge(detallecompraSetOldDetallecompra);
                }
            }
            for (Detallecompra detallecompraSetNewDetallecompra : detallecompraSetNew) {
                if (!detallecompraSetOld.contains(detallecompraSetNewDetallecompra)) {
                    Materiaprima oldMateriaprimaOfDetallecompraSetNewDetallecompra = detallecompraSetNewDetallecompra.getMateriaprima();
                    detallecompraSetNewDetallecompra.setMateriaprima(materiaprima);
                    detallecompraSetNewDetallecompra = em.merge(detallecompraSetNewDetallecompra);
                    if (oldMateriaprimaOfDetallecompraSetNewDetallecompra != null && !oldMateriaprimaOfDetallecompraSetNewDetallecompra.equals(materiaprima)) {
                        oldMateriaprimaOfDetallecompraSetNewDetallecompra.getDetallecompraSet().remove(detallecompraSetNewDetallecompra);
                        oldMateriaprimaOfDetallecompraSetNewDetallecompra = em.merge(oldMateriaprimaOfDetallecompraSetNewDetallecompra);
                    }
                }
            }
            for (Pieza piezaSetOldPieza : piezaSetOld) {
                if (!piezaSetNew.contains(piezaSetOldPieza)) {
                    piezaSetOldPieza.setMateriaprima(null);
                    piezaSetOldPieza = em.merge(piezaSetOldPieza);
                }
            }
            for (Pieza piezaSetNewPieza : piezaSetNew) {
                if (!piezaSetOld.contains(piezaSetNewPieza)) {
                    Materiaprima oldMateriaprimaOfPiezaSetNewPieza = piezaSetNewPieza.getMateriaprima();
                    piezaSetNewPieza.setMateriaprima(materiaprima);
                    piezaSetNewPieza = em.merge(piezaSetNewPieza);
                    if (oldMateriaprimaOfPiezaSetNewPieza != null && !oldMateriaprimaOfPiezaSetNewPieza.equals(materiaprima)) {
                        oldMateriaprimaOfPiezaSetNewPieza.getPiezaSet().remove(piezaSetNewPieza);
                        oldMateriaprimaOfPiezaSetNewPieza = em.merge(oldMateriaprimaOfPiezaSetNewPieza);
                    }
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSetOld) {
                if (!detallerequerimientosmateriaprimaSetNew.contains(detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima)) {
                    detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima.setIdmateriaprima(null);
                    detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSetNew) {
                if (!detallerequerimientosmateriaprimaSetOld.contains(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima)) {
                    Materiaprima oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.getIdmateriaprima();
                    detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.setIdmateriaprima(materiaprima);
                    detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                    if (oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima != null && !oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.equals(materiaprima)) {
                        oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                        oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = em.merge(oldIdmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                    }
                }
            }
            for (Matriz matrizSetOldMatriz : matrizSetOld) {
                if (!matrizSetNew.contains(matrizSetOldMatriz)) {
                    matrizSetOldMatriz.setMateriaprima(null);
                    matrizSetOldMatriz = em.merge(matrizSetOldMatriz);
                }
            }
            for (Matriz matrizSetNewMatriz : matrizSetNew) {
                if (!matrizSetOld.contains(matrizSetNewMatriz)) {
                    Materiaprima oldMateriaprimaOfMatrizSetNewMatriz = matrizSetNewMatriz.getMateriaprima();
                    matrizSetNewMatriz.setMateriaprima(materiaprima);
                    matrizSetNewMatriz = em.merge(matrizSetNewMatriz);
                    if (oldMateriaprimaOfMatrizSetNewMatriz != null && !oldMateriaprimaOfMatrizSetNewMatriz.equals(materiaprima)) {
                        oldMateriaprimaOfMatrizSetNewMatriz.getMatrizSet().remove(matrizSetNewMatriz);
                        oldMateriaprimaOfMatrizSetNewMatriz = em.merge(oldMateriaprimaOfMatrizSetNewMatriz);
                    }
                }
            }
            for (Detallempasignada detallempasignadaSetOldDetallempasignada : detallempasignadaSetOld) {
                if (!detallempasignadaSetNew.contains(detallempasignadaSetOldDetallempasignada)) {
                    detallempasignadaSetOldDetallempasignada.setIdmateriaprima(null);
                    detallempasignadaSetOldDetallempasignada = em.merge(detallempasignadaSetOldDetallempasignada);
                }
            }
            for (Detallempasignada detallempasignadaSetNewDetallempasignada : detallempasignadaSetNew) {
                if (!detallempasignadaSetOld.contains(detallempasignadaSetNewDetallempasignada)) {
                    Materiaprima oldIdmateriaprimaOfDetallempasignadaSetNewDetallempasignada = detallempasignadaSetNewDetallempasignada.getIdmateriaprima();
                    detallempasignadaSetNewDetallempasignada.setIdmateriaprima(materiaprima);
                    detallempasignadaSetNewDetallempasignada = em.merge(detallempasignadaSetNewDetallempasignada);
                    if (oldIdmateriaprimaOfDetallempasignadaSetNewDetallempasignada != null && !oldIdmateriaprimaOfDetallempasignadaSetNewDetallempasignada.equals(materiaprima)) {
                        oldIdmateriaprimaOfDetallempasignadaSetNewDetallempasignada.getDetallempasignadaSet().remove(detallempasignadaSetNewDetallempasignada);
                        oldIdmateriaprimaOfDetallempasignadaSetNewDetallempasignada = em.merge(oldIdmateriaprimaOfDetallempasignadaSetNewDetallempasignada);
                    }
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetNewProveedorxmateriaprima : proveedorxmateriaprimaSetNew) {
                if (!proveedorxmateriaprimaSetOld.contains(proveedorxmateriaprimaSetNewProveedorxmateriaprima)) {
                    Materiaprima oldMateriaprimaOfProveedorxmateriaprimaSetNewProveedorxmateriaprima = proveedorxmateriaprimaSetNewProveedorxmateriaprima.getMateriaprima();
                    proveedorxmateriaprimaSetNewProveedorxmateriaprima.setMateriaprima(materiaprima);
                    proveedorxmateriaprimaSetNewProveedorxmateriaprima = em.merge(proveedorxmateriaprimaSetNewProveedorxmateriaprima);
                    if (oldMateriaprimaOfProveedorxmateriaprimaSetNewProveedorxmateriaprima != null && !oldMateriaprimaOfProveedorxmateriaprimaSetNewProveedorxmateriaprima.equals(materiaprima)) {
                        oldMateriaprimaOfProveedorxmateriaprimaSetNewProveedorxmateriaprima.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprimaSetNewProveedorxmateriaprima);
                        oldMateriaprimaOfProveedorxmateriaprimaSetNewProveedorxmateriaprima = em.merge(oldMateriaprimaOfProveedorxmateriaprimaSetNewProveedorxmateriaprima);
                    }
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetOldDetalleproductopresupuesto : detalleproductopresupuestoSetOld) {
                if (!detalleproductopresupuestoSetNew.contains(detalleproductopresupuestoSetOldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoSetOldDetalleproductopresupuesto.setIdmateriaprima(null);
                    detalleproductopresupuestoSetOldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetOldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuesto : detalleproductopresupuestoSetNew) {
                if (!detalleproductopresupuestoSetOld.contains(detalleproductopresupuestoSetNewDetalleproductopresupuesto)) {
                    Materiaprima oldIdmateriaprimaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto = detalleproductopresupuestoSetNewDetalleproductopresupuesto.getIdmateriaprima();
                    detalleproductopresupuestoSetNewDetalleproductopresupuesto.setIdmateriaprima(materiaprima);
                    detalleproductopresupuestoSetNewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetNewDetalleproductopresupuesto);
                    if (oldIdmateriaprimaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto != null && !oldIdmateriaprimaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto.equals(materiaprima)) {
                        oldIdmateriaprimaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuestoSetNewDetalleproductopresupuesto);
                        oldIdmateriaprimaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto = em.merge(oldIdmateriaprimaOfDetalleproductopresupuestoSetNewDetalleproductopresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = materiaprima.getIdmateriaprima();
                if (findMateriaprima(id) == null) {
                    throw new NonexistentEntityException("The materiaprima with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima materiaprima;
            try {
                materiaprima = em.getReference(Materiaprima.class, id);
                materiaprima.getIdmateriaprima();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaprima with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetOrphanCheck = materiaprima.getProveedorxmateriaprimaSet();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetOrphanCheckProveedorxmateriaprima : proveedorxmateriaprimaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Materiaprima (" + materiaprima + ") cannot be destroyed since the Proveedorxmateriaprima " + proveedorxmateriaprimaSetOrphanCheckProveedorxmateriaprima + " in its proveedorxmateriaprimaSet field has a non-nullable materiaprima field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codigodebarra codbarra = materiaprima.getCodbarra();
            if (codbarra != null) {
                codbarra.getMateriaprimaSet().remove(materiaprima);
                codbarra = em.merge(codbarra);
            }
            Tipomaterial tipomaterial = materiaprima.getTipomaterial();
            if (tipomaterial != null) {
                tipomaterial.getMateriaprimaSet().remove(materiaprima);
                tipomaterial = em.merge(tipomaterial);
            }
            Unidadmedida unidadmedida = materiaprima.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida.getMateriaprimaSet().remove(materiaprima);
                unidadmedida = em.merge(unidadmedida);
            }
            Set<Detallecompra> detallecompraSet = materiaprima.getDetallecompraSet();
            for (Detallecompra detallecompraSetDetallecompra : detallecompraSet) {
                detallecompraSetDetallecompra.setMateriaprima(null);
                detallecompraSetDetallecompra = em.merge(detallecompraSetDetallecompra);
            }
            Set<Pieza> piezaSet = materiaprima.getPiezaSet();
            for (Pieza piezaSetPieza : piezaSet) {
                piezaSetPieza.setMateriaprima(null);
                piezaSetPieza = em.merge(piezaSetPieza);
            }
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet = materiaprima.getDetallerequerimientosmateriaprimaSet();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSet) {
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.setIdmateriaprima(null);
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
            }
            Set<Matriz> matrizSet = materiaprima.getMatrizSet();
            for (Matriz matrizSetMatriz : matrizSet) {
                matrizSetMatriz.setMateriaprima(null);
                matrizSetMatriz = em.merge(matrizSetMatriz);
            }
            Set<Detallempasignada> detallempasignadaSet = materiaprima.getDetallempasignadaSet();
            for (Detallempasignada detallempasignadaSetDetallempasignada : detallempasignadaSet) {
                detallempasignadaSetDetallempasignada.setIdmateriaprima(null);
                detallempasignadaSetDetallempasignada = em.merge(detallempasignadaSetDetallempasignada);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet = materiaprima.getDetalleproductopresupuestoSet();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : detalleproductopresupuestoSet) {
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIdmateriaprima(null);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
            }
            em.remove(materiaprima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materiaprima> findMateriaprimaEntities() {
        return findMateriaprimaEntities(true, -1, -1);
    }

    public List<Materiaprima> findMateriaprimaEntities(int maxResults, int firstResult) {
        return findMateriaprimaEntities(false, maxResults, firstResult);
    }

    private List<Materiaprima> findMateriaprimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materiaprima.class));
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

    public Materiaprima findMateriaprima(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materiaprima.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaprimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materiaprima> rt = cq.from(Materiaprima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
