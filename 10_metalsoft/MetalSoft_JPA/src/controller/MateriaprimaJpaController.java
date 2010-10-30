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
        if (materiaprima.getDetallecompraSet1() == null) {
            materiaprima.setDetallecompraSet1(new HashSet<Detallecompra>());
        }
        if (materiaprima.getPiezaSet() == null) {
            materiaprima.setPiezaSet(new HashSet<Pieza>());
        }
        if (materiaprima.getPiezaSet1() == null) {
            materiaprima.setPiezaSet1(new HashSet<Pieza>());
        }
        if (materiaprima.getDetallerequerimientosmateriaprimaSet() == null) {
            materiaprima.setDetallerequerimientosmateriaprimaSet(new HashSet<Detallerequerimientosmateriaprima>());
        }
        if (materiaprima.getDetallerequerimientosmateriaprimaSet1() == null) {
            materiaprima.setDetallerequerimientosmateriaprimaSet1(new HashSet<Detallerequerimientosmateriaprima>());
        }
        if (materiaprima.getMatrizSet() == null) {
            materiaprima.setMatrizSet(new HashSet<Matriz>());
        }
        if (materiaprima.getMatrizSet1() == null) {
            materiaprima.setMatrizSet1(new HashSet<Matriz>());
        }
        if (materiaprima.getDetallempasignadaSet() == null) {
            materiaprima.setDetallempasignadaSet(new HashSet<Detallempasignada>());
        }
        if (materiaprima.getDetallempasignadaSet1() == null) {
            materiaprima.setDetallempasignadaSet1(new HashSet<Detallempasignada>());
        }
        if (materiaprima.getProveedorxmateriaprimaSet() == null) {
            materiaprima.setProveedorxmateriaprimaSet(new HashSet<Proveedorxmateriaprima>());
        }
        if (materiaprima.getProveedorxmateriaprimaSet1() == null) {
            materiaprima.setProveedorxmateriaprimaSet1(new HashSet<Proveedorxmateriaprima>());
        }
        if (materiaprima.getDetalleproductopresupuestoSet() == null) {
            materiaprima.setDetalleproductopresupuestoSet(new HashSet<Detalleproductopresupuesto>());
        }
        if (materiaprima.getDetalleproductopresupuestoSet1() == null) {
            materiaprima.setDetalleproductopresupuestoSet1(new HashSet<Detalleproductopresupuesto>());
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
            Codigodebarra codbarra1 = materiaprima.getCodbarra1();
            if (codbarra1 != null) {
                codbarra1 = em.getReference(codbarra1.getClass(), codbarra1.getIdcodigo());
                materiaprima.setCodbarra1(codbarra1);
            }
            Tipomaterial tipomaterial = materiaprima.getTipomaterial();
            if (tipomaterial != null) {
                tipomaterial = em.getReference(tipomaterial.getClass(), tipomaterial.getIdtipomaterial());
                materiaprima.setTipomaterial(tipomaterial);
            }
            Tipomaterial tipomaterial1 = materiaprima.getTipomaterial1();
            if (tipomaterial1 != null) {
                tipomaterial1 = em.getReference(tipomaterial1.getClass(), tipomaterial1.getIdtipomaterial());
                materiaprima.setTipomaterial1(tipomaterial1);
            }
            Unidadmedida unidadmedida = materiaprima.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida = em.getReference(unidadmedida.getClass(), unidadmedida.getIdunidadmedida());
                materiaprima.setUnidadmedida(unidadmedida);
            }
            Unidadmedida unidadmedida1 = materiaprima.getUnidadmedida1();
            if (unidadmedida1 != null) {
                unidadmedida1 = em.getReference(unidadmedida1.getClass(), unidadmedida1.getIdunidadmedida());
                materiaprima.setUnidadmedida1(unidadmedida1);
            }
            Set<Detallecompra> attachedDetallecompraSet = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetDetallecompraToAttach : materiaprima.getDetallecompraSet()) {
                detallecompraSetDetallecompraToAttach = em.getReference(detallecompraSetDetallecompraToAttach.getClass(), detallecompraSetDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet.add(detallecompraSetDetallecompraToAttach);
            }
            materiaprima.setDetallecompraSet(attachedDetallecompraSet);
            Set<Detallecompra> attachedDetallecompraSet1 = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSet1DetallecompraToAttach : materiaprima.getDetallecompraSet1()) {
                detallecompraSet1DetallecompraToAttach = em.getReference(detallecompraSet1DetallecompraToAttach.getClass(), detallecompraSet1DetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet1.add(detallecompraSet1DetallecompraToAttach);
            }
            materiaprima.setDetallecompraSet1(attachedDetallecompraSet1);
            Set<Pieza> attachedPiezaSet = new HashSet<Pieza>();
            for (Pieza piezaSetPiezaToAttach : materiaprima.getPiezaSet()) {
                piezaSetPiezaToAttach = em.getReference(piezaSetPiezaToAttach.getClass(), piezaSetPiezaToAttach.getIdpieza());
                attachedPiezaSet.add(piezaSetPiezaToAttach);
            }
            materiaprima.setPiezaSet(attachedPiezaSet);
            Set<Pieza> attachedPiezaSet1 = new HashSet<Pieza>();
            for (Pieza piezaSet1PiezaToAttach : materiaprima.getPiezaSet1()) {
                piezaSet1PiezaToAttach = em.getReference(piezaSet1PiezaToAttach.getClass(), piezaSet1PiezaToAttach.getIdpieza());
                attachedPiezaSet1.add(piezaSet1PiezaToAttach);
            }
            materiaprima.setPiezaSet1(attachedPiezaSet1);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSet = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach : materiaprima.getDetallerequerimientosmateriaprimaSet()) {
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSet.add(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach);
            }
            materiaprima.setDetallerequerimientosmateriaprimaSet(attachedDetallerequerimientosmateriaprimaSet);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSet1 = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach : materiaprima.getDetallerequerimientosmateriaprimaSet1()) {
                detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSet1.add(detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach);
            }
            materiaprima.setDetallerequerimientosmateriaprimaSet1(attachedDetallerequerimientosmateriaprimaSet1);
            Set<Matriz> attachedMatrizSet = new HashSet<Matriz>();
            for (Matriz matrizSetMatrizToAttach : materiaprima.getMatrizSet()) {
                matrizSetMatrizToAttach = em.getReference(matrizSetMatrizToAttach.getClass(), matrizSetMatrizToAttach.getIdmatriz());
                attachedMatrizSet.add(matrizSetMatrizToAttach);
            }
            materiaprima.setMatrizSet(attachedMatrizSet);
            Set<Matriz> attachedMatrizSet1 = new HashSet<Matriz>();
            for (Matriz matrizSet1MatrizToAttach : materiaprima.getMatrizSet1()) {
                matrizSet1MatrizToAttach = em.getReference(matrizSet1MatrizToAttach.getClass(), matrizSet1MatrizToAttach.getIdmatriz());
                attachedMatrizSet1.add(matrizSet1MatrizToAttach);
            }
            materiaprima.setMatrizSet1(attachedMatrizSet1);
            Set<Detallempasignada> attachedDetallempasignadaSet = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSetDetallempasignadaToAttach : materiaprima.getDetallempasignadaSet()) {
                detallempasignadaSetDetallempasignadaToAttach = em.getReference(detallempasignadaSetDetallempasignadaToAttach.getClass(), detallempasignadaSetDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSet.add(detallempasignadaSetDetallempasignadaToAttach);
            }
            materiaprima.setDetallempasignadaSet(attachedDetallempasignadaSet);
            Set<Detallempasignada> attachedDetallempasignadaSet1 = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSet1DetallempasignadaToAttach : materiaprima.getDetallempasignadaSet1()) {
                detallempasignadaSet1DetallempasignadaToAttach = em.getReference(detallempasignadaSet1DetallempasignadaToAttach.getClass(), detallempasignadaSet1DetallempasignadaToAttach.getId());
                attachedDetallempasignadaSet1.add(detallempasignadaSet1DetallempasignadaToAttach);
            }
            materiaprima.setDetallempasignadaSet1(attachedDetallempasignadaSet1);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSet = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach : materiaprima.getProveedorxmateriaprimaSet()) {
                proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSet.add(proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach);
            }
            materiaprima.setProveedorxmateriaprimaSet(attachedProveedorxmateriaprimaSet);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSet1 = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach : materiaprima.getProveedorxmateriaprimaSet1()) {
                proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSet1.add(proveedorxmateriaprimaSet1ProveedorxmateriaprimaToAttach);
            }
            materiaprima.setProveedorxmateriaprimaSet1(attachedProveedorxmateriaprimaSet1);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuestoToAttach : materiaprima.getDetalleproductopresupuestoSet()) {
                detalleproductopresupuestoSetDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet.add(detalleproductopresupuestoSetDetalleproductopresupuestoToAttach);
            }
            materiaprima.setDetalleproductopresupuestoSet(attachedDetalleproductopresupuestoSet);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet1 = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach : materiaprima.getDetalleproductopresupuestoSet1()) {
                detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet1.add(detalleproductopresupuestoSet1DetalleproductopresupuestoToAttach);
            }
            materiaprima.setDetalleproductopresupuestoSet1(attachedDetalleproductopresupuestoSet1);
            em.persist(materiaprima);
            if (codbarra != null) {
                codbarra.getMateriaprimaSet().add(materiaprima);
                codbarra = em.merge(codbarra);
            }
            if (codbarra1 != null) {
                codbarra1.getMateriaprimaSet().add(materiaprima);
                codbarra1 = em.merge(codbarra1);
            }
            if (tipomaterial != null) {
                tipomaterial.getMateriaprimaSet().add(materiaprima);
                tipomaterial = em.merge(tipomaterial);
            }
            if (tipomaterial1 != null) {
                tipomaterial1.getMateriaprimaSet().add(materiaprima);
                tipomaterial1 = em.merge(tipomaterial1);
            }
            if (unidadmedida != null) {
                unidadmedida.getMateriaprimaSet().add(materiaprima);
                unidadmedida = em.merge(unidadmedida);
            }
            if (unidadmedida1 != null) {
                unidadmedida1.getMateriaprimaSet().add(materiaprima);
                unidadmedida1 = em.merge(unidadmedida1);
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
            for (Detallecompra detallecompraSet1Detallecompra : materiaprima.getDetallecompraSet1()) {
                Materiaprima oldMateriaprima1OfDetallecompraSet1Detallecompra = detallecompraSet1Detallecompra.getMateriaprima1();
                detallecompraSet1Detallecompra.setMateriaprima1(materiaprima);
                detallecompraSet1Detallecompra = em.merge(detallecompraSet1Detallecompra);
                if (oldMateriaprima1OfDetallecompraSet1Detallecompra != null) {
                    oldMateriaprima1OfDetallecompraSet1Detallecompra.getDetallecompraSet1().remove(detallecompraSet1Detallecompra);
                    oldMateriaprima1OfDetallecompraSet1Detallecompra = em.merge(oldMateriaprima1OfDetallecompraSet1Detallecompra);
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
            for (Pieza piezaSet1Pieza : materiaprima.getPiezaSet1()) {
                Materiaprima oldMateriaprima1OfPiezaSet1Pieza = piezaSet1Pieza.getMateriaprima1();
                piezaSet1Pieza.setMateriaprima1(materiaprima);
                piezaSet1Pieza = em.merge(piezaSet1Pieza);
                if (oldMateriaprima1OfPiezaSet1Pieza != null) {
                    oldMateriaprima1OfPiezaSet1Pieza.getPiezaSet1().remove(piezaSet1Pieza);
                    oldMateriaprima1OfPiezaSet1Pieza = em.merge(oldMateriaprima1OfPiezaSet1Pieza);
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
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima : materiaprima.getDetallerequerimientosmateriaprimaSet1()) {
                Materiaprima oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima.getIdmateriaprima1();
                detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima.setIdmateriaprima1(materiaprima);
                detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima);
                if (oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima != null) {
                    oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1().remove(detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima);
                    oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima = em.merge(oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima);
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
            for (Matriz matrizSet1Matriz : materiaprima.getMatrizSet1()) {
                Materiaprima oldMateriaprima1OfMatrizSet1Matriz = matrizSet1Matriz.getMateriaprima1();
                matrizSet1Matriz.setMateriaprima1(materiaprima);
                matrizSet1Matriz = em.merge(matrizSet1Matriz);
                if (oldMateriaprima1OfMatrizSet1Matriz != null) {
                    oldMateriaprima1OfMatrizSet1Matriz.getMatrizSet1().remove(matrizSet1Matriz);
                    oldMateriaprima1OfMatrizSet1Matriz = em.merge(oldMateriaprima1OfMatrizSet1Matriz);
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
            for (Detallempasignada detallempasignadaSet1Detallempasignada : materiaprima.getDetallempasignadaSet1()) {
                Materiaprima oldIdmateriaprima1OfDetallempasignadaSet1Detallempasignada = detallempasignadaSet1Detallempasignada.getIdmateriaprima1();
                detallempasignadaSet1Detallempasignada.setIdmateriaprima1(materiaprima);
                detallempasignadaSet1Detallempasignada = em.merge(detallempasignadaSet1Detallempasignada);
                if (oldIdmateriaprima1OfDetallempasignadaSet1Detallempasignada != null) {
                    oldIdmateriaprima1OfDetallempasignadaSet1Detallempasignada.getDetallempasignadaSet1().remove(detallempasignadaSet1Detallempasignada);
                    oldIdmateriaprima1OfDetallempasignadaSet1Detallempasignada = em.merge(oldIdmateriaprima1OfDetallempasignadaSet1Detallempasignada);
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
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1Proveedorxmateriaprima : materiaprima.getProveedorxmateriaprimaSet1()) {
                Materiaprima oldMateriaprima1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima = proveedorxmateriaprimaSet1Proveedorxmateriaprima.getMateriaprima1();
                proveedorxmateriaprimaSet1Proveedorxmateriaprima.setMateriaprima1(materiaprima);
                proveedorxmateriaprimaSet1Proveedorxmateriaprima = em.merge(proveedorxmateriaprimaSet1Proveedorxmateriaprima);
                if (oldMateriaprima1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima != null) {
                    oldMateriaprima1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima.getProveedorxmateriaprimaSet1().remove(proveedorxmateriaprimaSet1Proveedorxmateriaprima);
                    oldMateriaprima1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima = em.merge(oldMateriaprima1OfProveedorxmateriaprimaSet1Proveedorxmateriaprima);
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
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1Detalleproductopresupuesto : materiaprima.getDetalleproductopresupuestoSet1()) {
                Materiaprima oldIdmateriaprima1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto = detalleproductopresupuestoSet1Detalleproductopresupuesto.getIdmateriaprima1();
                detalleproductopresupuestoSet1Detalleproductopresupuesto.setIdmateriaprima1(materiaprima);
                detalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1Detalleproductopresupuesto);
                if (oldIdmateriaprima1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto != null) {
                    oldIdmateriaprima1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto.getDetalleproductopresupuestoSet1().remove(detalleproductopresupuestoSet1Detalleproductopresupuesto);
                    oldIdmateriaprima1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(oldIdmateriaprima1OfDetalleproductopresupuestoSet1Detalleproductopresupuesto);
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
            Codigodebarra codbarra1Old = persistentMateriaprima.getCodbarra1();
            Codigodebarra codbarra1New = materiaprima.getCodbarra1();
            Tipomaterial tipomaterialOld = persistentMateriaprima.getTipomaterial();
            Tipomaterial tipomaterialNew = materiaprima.getTipomaterial();
            Tipomaterial tipomaterial1Old = persistentMateriaprima.getTipomaterial1();
            Tipomaterial tipomaterial1New = materiaprima.getTipomaterial1();
            Unidadmedida unidadmedidaOld = persistentMateriaprima.getUnidadmedida();
            Unidadmedida unidadmedidaNew = materiaprima.getUnidadmedida();
            Unidadmedida unidadmedida1Old = persistentMateriaprima.getUnidadmedida1();
            Unidadmedida unidadmedida1New = materiaprima.getUnidadmedida1();
            Set<Detallecompra> detallecompraSetOld = persistentMateriaprima.getDetallecompraSet();
            Set<Detallecompra> detallecompraSetNew = materiaprima.getDetallecompraSet();
            Set<Detallecompra> detallecompraSet1Old = persistentMateriaprima.getDetallecompraSet1();
            Set<Detallecompra> detallecompraSet1New = materiaprima.getDetallecompraSet1();
            Set<Pieza> piezaSetOld = persistentMateriaprima.getPiezaSet();
            Set<Pieza> piezaSetNew = materiaprima.getPiezaSet();
            Set<Pieza> piezaSet1Old = persistentMateriaprima.getPiezaSet1();
            Set<Pieza> piezaSet1New = materiaprima.getPiezaSet1();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetOld = persistentMateriaprima.getDetallerequerimientosmateriaprimaSet();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetNew = materiaprima.getDetallerequerimientosmateriaprimaSet();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet1Old = persistentMateriaprima.getDetallerequerimientosmateriaprimaSet1();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet1New = materiaprima.getDetallerequerimientosmateriaprimaSet1();
            Set<Matriz> matrizSetOld = persistentMateriaprima.getMatrizSet();
            Set<Matriz> matrizSetNew = materiaprima.getMatrizSet();
            Set<Matriz> matrizSet1Old = persistentMateriaprima.getMatrizSet1();
            Set<Matriz> matrizSet1New = materiaprima.getMatrizSet1();
            Set<Detallempasignada> detallempasignadaSetOld = persistentMateriaprima.getDetallempasignadaSet();
            Set<Detallempasignada> detallempasignadaSetNew = materiaprima.getDetallempasignadaSet();
            Set<Detallempasignada> detallempasignadaSet1Old = persistentMateriaprima.getDetallempasignadaSet1();
            Set<Detallempasignada> detallempasignadaSet1New = materiaprima.getDetallempasignadaSet1();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetOld = persistentMateriaprima.getProveedorxmateriaprimaSet();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetNew = materiaprima.getProveedorxmateriaprimaSet();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet1Old = persistentMateriaprima.getProveedorxmateriaprimaSet1();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet1New = materiaprima.getProveedorxmateriaprimaSet1();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetOld = persistentMateriaprima.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSetNew = materiaprima.getDetalleproductopresupuestoSet();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1Old = persistentMateriaprima.getDetalleproductopresupuestoSet1();
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1New = materiaprima.getDetalleproductopresupuestoSet1();
            List<String> illegalOrphanMessages = null;
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetOldProveedorxmateriaprima : proveedorxmateriaprimaSetOld) {
                if (!proveedorxmateriaprimaSetNew.contains(proveedorxmateriaprimaSetOldProveedorxmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proveedorxmateriaprima " + proveedorxmateriaprimaSetOldProveedorxmateriaprima + " since its materiaprima field is not nullable.");
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1OldProveedorxmateriaprima : proveedorxmateriaprimaSet1Old) {
                if (!proveedorxmateriaprimaSet1New.contains(proveedorxmateriaprimaSet1OldProveedorxmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proveedorxmateriaprima " + proveedorxmateriaprimaSet1OldProveedorxmateriaprima + " since its materiaprima1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codbarraNew != null) {
                codbarraNew = em.getReference(codbarraNew.getClass(), codbarraNew.getIdcodigo());
                materiaprima.setCodbarra(codbarraNew);
            }
            if (codbarra1New != null) {
                codbarra1New = em.getReference(codbarra1New.getClass(), codbarra1New.getIdcodigo());
                materiaprima.setCodbarra1(codbarra1New);
            }
            if (tipomaterialNew != null) {
                tipomaterialNew = em.getReference(tipomaterialNew.getClass(), tipomaterialNew.getIdtipomaterial());
                materiaprima.setTipomaterial(tipomaterialNew);
            }
            if (tipomaterial1New != null) {
                tipomaterial1New = em.getReference(tipomaterial1New.getClass(), tipomaterial1New.getIdtipomaterial());
                materiaprima.setTipomaterial1(tipomaterial1New);
            }
            if (unidadmedidaNew != null) {
                unidadmedidaNew = em.getReference(unidadmedidaNew.getClass(), unidadmedidaNew.getIdunidadmedida());
                materiaprima.setUnidadmedida(unidadmedidaNew);
            }
            if (unidadmedida1New != null) {
                unidadmedida1New = em.getReference(unidadmedida1New.getClass(), unidadmedida1New.getIdunidadmedida());
                materiaprima.setUnidadmedida1(unidadmedida1New);
            }
            Set<Detallecompra> attachedDetallecompraSetNew = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetNewDetallecompraToAttach : detallecompraSetNew) {
                detallecompraSetNewDetallecompraToAttach = em.getReference(detallecompraSetNewDetallecompraToAttach.getClass(), detallecompraSetNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSetNew.add(detallecompraSetNewDetallecompraToAttach);
            }
            detallecompraSetNew = attachedDetallecompraSetNew;
            materiaprima.setDetallecompraSet(detallecompraSetNew);
            Set<Detallecompra> attachedDetallecompraSet1New = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSet1NewDetallecompraToAttach : detallecompraSet1New) {
                detallecompraSet1NewDetallecompraToAttach = em.getReference(detallecompraSet1NewDetallecompraToAttach.getClass(), detallecompraSet1NewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet1New.add(detallecompraSet1NewDetallecompraToAttach);
            }
            detallecompraSet1New = attachedDetallecompraSet1New;
            materiaprima.setDetallecompraSet1(detallecompraSet1New);
            Set<Pieza> attachedPiezaSetNew = new HashSet<Pieza>();
            for (Pieza piezaSetNewPiezaToAttach : piezaSetNew) {
                piezaSetNewPiezaToAttach = em.getReference(piezaSetNewPiezaToAttach.getClass(), piezaSetNewPiezaToAttach.getIdpieza());
                attachedPiezaSetNew.add(piezaSetNewPiezaToAttach);
            }
            piezaSetNew = attachedPiezaSetNew;
            materiaprima.setPiezaSet(piezaSetNew);
            Set<Pieza> attachedPiezaSet1New = new HashSet<Pieza>();
            for (Pieza piezaSet1NewPiezaToAttach : piezaSet1New) {
                piezaSet1NewPiezaToAttach = em.getReference(piezaSet1NewPiezaToAttach.getClass(), piezaSet1NewPiezaToAttach.getIdpieza());
                attachedPiezaSet1New.add(piezaSet1NewPiezaToAttach);
            }
            piezaSet1New = attachedPiezaSet1New;
            materiaprima.setPiezaSet1(piezaSet1New);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSetNew = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach : detallerequerimientosmateriaprimaSetNew) {
                detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSetNew.add(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach);
            }
            detallerequerimientosmateriaprimaSetNew = attachedDetallerequerimientosmateriaprimaSetNew;
            materiaprima.setDetallerequerimientosmateriaprimaSet(detallerequerimientosmateriaprimaSetNew);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSet1New = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach : detallerequerimientosmateriaprimaSet1New) {
                detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSet1New.add(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach);
            }
            detallerequerimientosmateriaprimaSet1New = attachedDetallerequerimientosmateriaprimaSet1New;
            materiaprima.setDetallerequerimientosmateriaprimaSet1(detallerequerimientosmateriaprimaSet1New);
            Set<Matriz> attachedMatrizSetNew = new HashSet<Matriz>();
            for (Matriz matrizSetNewMatrizToAttach : matrizSetNew) {
                matrizSetNewMatrizToAttach = em.getReference(matrizSetNewMatrizToAttach.getClass(), matrizSetNewMatrizToAttach.getIdmatriz());
                attachedMatrizSetNew.add(matrizSetNewMatrizToAttach);
            }
            matrizSetNew = attachedMatrizSetNew;
            materiaprima.setMatrizSet(matrizSetNew);
            Set<Matriz> attachedMatrizSet1New = new HashSet<Matriz>();
            for (Matriz matrizSet1NewMatrizToAttach : matrizSet1New) {
                matrizSet1NewMatrizToAttach = em.getReference(matrizSet1NewMatrizToAttach.getClass(), matrizSet1NewMatrizToAttach.getIdmatriz());
                attachedMatrizSet1New.add(matrizSet1NewMatrizToAttach);
            }
            matrizSet1New = attachedMatrizSet1New;
            materiaprima.setMatrizSet1(matrizSet1New);
            Set<Detallempasignada> attachedDetallempasignadaSetNew = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSetNewDetallempasignadaToAttach : detallempasignadaSetNew) {
                detallempasignadaSetNewDetallempasignadaToAttach = em.getReference(detallempasignadaSetNewDetallempasignadaToAttach.getClass(), detallempasignadaSetNewDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSetNew.add(detallempasignadaSetNewDetallempasignadaToAttach);
            }
            detallempasignadaSetNew = attachedDetallempasignadaSetNew;
            materiaprima.setDetallempasignadaSet(detallempasignadaSetNew);
            Set<Detallempasignada> attachedDetallempasignadaSet1New = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSet1NewDetallempasignadaToAttach : detallempasignadaSet1New) {
                detallempasignadaSet1NewDetallempasignadaToAttach = em.getReference(detallempasignadaSet1NewDetallempasignadaToAttach.getClass(), detallempasignadaSet1NewDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSet1New.add(detallempasignadaSet1NewDetallempasignadaToAttach);
            }
            detallempasignadaSet1New = attachedDetallempasignadaSet1New;
            materiaprima.setDetallempasignadaSet1(detallempasignadaSet1New);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSetNew = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach : proveedorxmateriaprimaSetNew) {
                proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSetNew.add(proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach);
            }
            proveedorxmateriaprimaSetNew = attachedProveedorxmateriaprimaSetNew;
            materiaprima.setProveedorxmateriaprimaSet(proveedorxmateriaprimaSetNew);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSet1New = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach : proveedorxmateriaprimaSet1New) {
                proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSet1New.add(proveedorxmateriaprimaSet1NewProveedorxmateriaprimaToAttach);
            }
            proveedorxmateriaprimaSet1New = attachedProveedorxmateriaprimaSet1New;
            materiaprima.setProveedorxmateriaprimaSet1(proveedorxmateriaprimaSet1New);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSetNew = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSetNew) {
                detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSetNew.add(detalleproductopresupuestoSetNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSetNew = attachedDetalleproductopresupuestoSetNew;
            materiaprima.setDetalleproductopresupuestoSet(detalleproductopresupuestoSetNew);
            Set<Detalleproductopresupuesto> attachedDetalleproductopresupuestoSet1New = new HashSet<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach : detalleproductopresupuestoSet1New) {
                detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoSet1New.add(detalleproductopresupuestoSet1NewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoSet1New = attachedDetalleproductopresupuestoSet1New;
            materiaprima.setDetalleproductopresupuestoSet1(detalleproductopresupuestoSet1New);
            materiaprima = em.merge(materiaprima);
            if (codbarraOld != null && !codbarraOld.equals(codbarraNew)) {
                codbarraOld.getMateriaprimaSet().remove(materiaprima);
                codbarraOld = em.merge(codbarraOld);
            }
            if (codbarraNew != null && !codbarraNew.equals(codbarraOld)) {
                codbarraNew.getMateriaprimaSet().add(materiaprima);
                codbarraNew = em.merge(codbarraNew);
            }
            if (codbarra1Old != null && !codbarra1Old.equals(codbarra1New)) {
                codbarra1Old.getMateriaprimaSet().remove(materiaprima);
                codbarra1Old = em.merge(codbarra1Old);
            }
            if (codbarra1New != null && !codbarra1New.equals(codbarra1Old)) {
                codbarra1New.getMateriaprimaSet().add(materiaprima);
                codbarra1New = em.merge(codbarra1New);
            }
            if (tipomaterialOld != null && !tipomaterialOld.equals(tipomaterialNew)) {
                tipomaterialOld.getMateriaprimaSet().remove(materiaprima);
                tipomaterialOld = em.merge(tipomaterialOld);
            }
            if (tipomaterialNew != null && !tipomaterialNew.equals(tipomaterialOld)) {
                tipomaterialNew.getMateriaprimaSet().add(materiaprima);
                tipomaterialNew = em.merge(tipomaterialNew);
            }
            if (tipomaterial1Old != null && !tipomaterial1Old.equals(tipomaterial1New)) {
                tipomaterial1Old.getMateriaprimaSet().remove(materiaprima);
                tipomaterial1Old = em.merge(tipomaterial1Old);
            }
            if (tipomaterial1New != null && !tipomaterial1New.equals(tipomaterial1Old)) {
                tipomaterial1New.getMateriaprimaSet().add(materiaprima);
                tipomaterial1New = em.merge(tipomaterial1New);
            }
            if (unidadmedidaOld != null && !unidadmedidaOld.equals(unidadmedidaNew)) {
                unidadmedidaOld.getMateriaprimaSet().remove(materiaprima);
                unidadmedidaOld = em.merge(unidadmedidaOld);
            }
            if (unidadmedidaNew != null && !unidadmedidaNew.equals(unidadmedidaOld)) {
                unidadmedidaNew.getMateriaprimaSet().add(materiaprima);
                unidadmedidaNew = em.merge(unidadmedidaNew);
            }
            if (unidadmedida1Old != null && !unidadmedida1Old.equals(unidadmedida1New)) {
                unidadmedida1Old.getMateriaprimaSet().remove(materiaprima);
                unidadmedida1Old = em.merge(unidadmedida1Old);
            }
            if (unidadmedida1New != null && !unidadmedida1New.equals(unidadmedida1Old)) {
                unidadmedida1New.getMateriaprimaSet().add(materiaprima);
                unidadmedida1New = em.merge(unidadmedida1New);
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
            for (Detallecompra detallecompraSet1OldDetallecompra : detallecompraSet1Old) {
                if (!detallecompraSet1New.contains(detallecompraSet1OldDetallecompra)) {
                    detallecompraSet1OldDetallecompra.setMateriaprima1(null);
                    detallecompraSet1OldDetallecompra = em.merge(detallecompraSet1OldDetallecompra);
                }
            }
            for (Detallecompra detallecompraSet1NewDetallecompra : detallecompraSet1New) {
                if (!detallecompraSet1Old.contains(detallecompraSet1NewDetallecompra)) {
                    Materiaprima oldMateriaprima1OfDetallecompraSet1NewDetallecompra = detallecompraSet1NewDetallecompra.getMateriaprima1();
                    detallecompraSet1NewDetallecompra.setMateriaprima1(materiaprima);
                    detallecompraSet1NewDetallecompra = em.merge(detallecompraSet1NewDetallecompra);
                    if (oldMateriaprima1OfDetallecompraSet1NewDetallecompra != null && !oldMateriaprima1OfDetallecompraSet1NewDetallecompra.equals(materiaprima)) {
                        oldMateriaprima1OfDetallecompraSet1NewDetallecompra.getDetallecompraSet1().remove(detallecompraSet1NewDetallecompra);
                        oldMateriaprima1OfDetallecompraSet1NewDetallecompra = em.merge(oldMateriaprima1OfDetallecompraSet1NewDetallecompra);
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
            for (Pieza piezaSet1OldPieza : piezaSet1Old) {
                if (!piezaSet1New.contains(piezaSet1OldPieza)) {
                    piezaSet1OldPieza.setMateriaprima1(null);
                    piezaSet1OldPieza = em.merge(piezaSet1OldPieza);
                }
            }
            for (Pieza piezaSet1NewPieza : piezaSet1New) {
                if (!piezaSet1Old.contains(piezaSet1NewPieza)) {
                    Materiaprima oldMateriaprima1OfPiezaSet1NewPieza = piezaSet1NewPieza.getMateriaprima1();
                    piezaSet1NewPieza.setMateriaprima1(materiaprima);
                    piezaSet1NewPieza = em.merge(piezaSet1NewPieza);
                    if (oldMateriaprima1OfPiezaSet1NewPieza != null && !oldMateriaprima1OfPiezaSet1NewPieza.equals(materiaprima)) {
                        oldMateriaprima1OfPiezaSet1NewPieza.getPiezaSet1().remove(piezaSet1NewPieza);
                        oldMateriaprima1OfPiezaSet1NewPieza = em.merge(oldMateriaprima1OfPiezaSet1NewPieza);
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
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1OldDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSet1Old) {
                if (!detallerequerimientosmateriaprimaSet1New.contains(detallerequerimientosmateriaprimaSet1OldDetallerequerimientosmateriaprima)) {
                    detallerequerimientosmateriaprimaSet1OldDetallerequerimientosmateriaprima.setIdmateriaprima1(null);
                    detallerequerimientosmateriaprimaSet1OldDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSet1OldDetallerequerimientosmateriaprima);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSet1New) {
                if (!detallerequerimientosmateriaprimaSet1Old.contains(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima)) {
                    Materiaprima oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima.getIdmateriaprima1();
                    detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima.setIdmateriaprima1(materiaprima);
                    detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima);
                    if (oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima != null && !oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima.equals(materiaprima)) {
                        oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1().remove(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima);
                        oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima = em.merge(oldIdmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima);
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
            for (Matriz matrizSet1OldMatriz : matrizSet1Old) {
                if (!matrizSet1New.contains(matrizSet1OldMatriz)) {
                    matrizSet1OldMatriz.setMateriaprima1(null);
                    matrizSet1OldMatriz = em.merge(matrizSet1OldMatriz);
                }
            }
            for (Matriz matrizSet1NewMatriz : matrizSet1New) {
                if (!matrizSet1Old.contains(matrizSet1NewMatriz)) {
                    Materiaprima oldMateriaprima1OfMatrizSet1NewMatriz = matrizSet1NewMatriz.getMateriaprima1();
                    matrizSet1NewMatriz.setMateriaprima1(materiaprima);
                    matrizSet1NewMatriz = em.merge(matrizSet1NewMatriz);
                    if (oldMateriaprima1OfMatrizSet1NewMatriz != null && !oldMateriaprima1OfMatrizSet1NewMatriz.equals(materiaprima)) {
                        oldMateriaprima1OfMatrizSet1NewMatriz.getMatrizSet1().remove(matrizSet1NewMatriz);
                        oldMateriaprima1OfMatrizSet1NewMatriz = em.merge(oldMateriaprima1OfMatrizSet1NewMatriz);
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
            for (Detallempasignada detallempasignadaSet1OldDetallempasignada : detallempasignadaSet1Old) {
                if (!detallempasignadaSet1New.contains(detallempasignadaSet1OldDetallempasignada)) {
                    detallempasignadaSet1OldDetallempasignada.setIdmateriaprima1(null);
                    detallempasignadaSet1OldDetallempasignada = em.merge(detallempasignadaSet1OldDetallempasignada);
                }
            }
            for (Detallempasignada detallempasignadaSet1NewDetallempasignada : detallempasignadaSet1New) {
                if (!detallempasignadaSet1Old.contains(detallempasignadaSet1NewDetallempasignada)) {
                    Materiaprima oldIdmateriaprima1OfDetallempasignadaSet1NewDetallempasignada = detallempasignadaSet1NewDetallempasignada.getIdmateriaprima1();
                    detallempasignadaSet1NewDetallempasignada.setIdmateriaprima1(materiaprima);
                    detallempasignadaSet1NewDetallempasignada = em.merge(detallempasignadaSet1NewDetallempasignada);
                    if (oldIdmateriaprima1OfDetallempasignadaSet1NewDetallempasignada != null && !oldIdmateriaprima1OfDetallempasignadaSet1NewDetallempasignada.equals(materiaprima)) {
                        oldIdmateriaprima1OfDetallempasignadaSet1NewDetallempasignada.getDetallempasignadaSet1().remove(detallempasignadaSet1NewDetallempasignada);
                        oldIdmateriaprima1OfDetallempasignadaSet1NewDetallempasignada = em.merge(oldIdmateriaprima1OfDetallempasignadaSet1NewDetallempasignada);
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
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1NewProveedorxmateriaprima : proveedorxmateriaprimaSet1New) {
                if (!proveedorxmateriaprimaSet1Old.contains(proveedorxmateriaprimaSet1NewProveedorxmateriaprima)) {
                    Materiaprima oldMateriaprima1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima = proveedorxmateriaprimaSet1NewProveedorxmateriaprima.getMateriaprima1();
                    proveedorxmateriaprimaSet1NewProveedorxmateriaprima.setMateriaprima1(materiaprima);
                    proveedorxmateriaprimaSet1NewProveedorxmateriaprima = em.merge(proveedorxmateriaprimaSet1NewProveedorxmateriaprima);
                    if (oldMateriaprima1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima != null && !oldMateriaprima1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima.equals(materiaprima)) {
                        oldMateriaprima1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima.getProveedorxmateriaprimaSet1().remove(proveedorxmateriaprimaSet1NewProveedorxmateriaprima);
                        oldMateriaprima1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima = em.merge(oldMateriaprima1OfProveedorxmateriaprimaSet1NewProveedorxmateriaprima);
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
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1OldDetalleproductopresupuesto : detalleproductopresupuestoSet1Old) {
                if (!detalleproductopresupuestoSet1New.contains(detalleproductopresupuestoSet1OldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoSet1OldDetalleproductopresupuesto.setIdmateriaprima1(null);
                    detalleproductopresupuestoSet1OldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1OldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1NewDetalleproductopresupuesto : detalleproductopresupuestoSet1New) {
                if (!detalleproductopresupuestoSet1Old.contains(detalleproductopresupuestoSet1NewDetalleproductopresupuesto)) {
                    Materiaprima oldIdmateriaprima1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto = detalleproductopresupuestoSet1NewDetalleproductopresupuesto.getIdmateriaprima1();
                    detalleproductopresupuestoSet1NewDetalleproductopresupuesto.setIdmateriaprima1(materiaprima);
                    detalleproductopresupuestoSet1NewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1NewDetalleproductopresupuesto);
                    if (oldIdmateriaprima1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto != null && !oldIdmateriaprima1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto.equals(materiaprima)) {
                        oldIdmateriaprima1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto.getDetalleproductopresupuestoSet1().remove(detalleproductopresupuestoSet1NewDetalleproductopresupuesto);
                        oldIdmateriaprima1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto = em.merge(oldIdmateriaprima1OfDetalleproductopresupuestoSet1NewDetalleproductopresupuesto);
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
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet1OrphanCheck = materiaprima.getProveedorxmateriaprimaSet1();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSet1OrphanCheckProveedorxmateriaprima : proveedorxmateriaprimaSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Materiaprima (" + materiaprima + ") cannot be destroyed since the Proveedorxmateriaprima " + proveedorxmateriaprimaSet1OrphanCheckProveedorxmateriaprima + " in its proveedorxmateriaprimaSet1 field has a non-nullable materiaprima1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Codigodebarra codbarra = materiaprima.getCodbarra();
            if (codbarra != null) {
                codbarra.getMateriaprimaSet().remove(materiaprima);
                codbarra = em.merge(codbarra);
            }
            Codigodebarra codbarra1 = materiaprima.getCodbarra1();
            if (codbarra1 != null) {
                codbarra1.getMateriaprimaSet().remove(materiaprima);
                codbarra1 = em.merge(codbarra1);
            }
            Tipomaterial tipomaterial = materiaprima.getTipomaterial();
            if (tipomaterial != null) {
                tipomaterial.getMateriaprimaSet().remove(materiaprima);
                tipomaterial = em.merge(tipomaterial);
            }
            Tipomaterial tipomaterial1 = materiaprima.getTipomaterial1();
            if (tipomaterial1 != null) {
                tipomaterial1.getMateriaprimaSet().remove(materiaprima);
                tipomaterial1 = em.merge(tipomaterial1);
            }
            Unidadmedida unidadmedida = materiaprima.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida.getMateriaprimaSet().remove(materiaprima);
                unidadmedida = em.merge(unidadmedida);
            }
            Unidadmedida unidadmedida1 = materiaprima.getUnidadmedida1();
            if (unidadmedida1 != null) {
                unidadmedida1.getMateriaprimaSet().remove(materiaprima);
                unidadmedida1 = em.merge(unidadmedida1);
            }
            Set<Detallecompra> detallecompraSet = materiaprima.getDetallecompraSet();
            for (Detallecompra detallecompraSetDetallecompra : detallecompraSet) {
                detallecompraSetDetallecompra.setMateriaprima(null);
                detallecompraSetDetallecompra = em.merge(detallecompraSetDetallecompra);
            }
            Set<Detallecompra> detallecompraSet1 = materiaprima.getDetallecompraSet1();
            for (Detallecompra detallecompraSet1Detallecompra : detallecompraSet1) {
                detallecompraSet1Detallecompra.setMateriaprima1(null);
                detallecompraSet1Detallecompra = em.merge(detallecompraSet1Detallecompra);
            }
            Set<Pieza> piezaSet = materiaprima.getPiezaSet();
            for (Pieza piezaSetPieza : piezaSet) {
                piezaSetPieza.setMateriaprima(null);
                piezaSetPieza = em.merge(piezaSetPieza);
            }
            Set<Pieza> piezaSet1 = materiaprima.getPiezaSet1();
            for (Pieza piezaSet1Pieza : piezaSet1) {
                piezaSet1Pieza.setMateriaprima1(null);
                piezaSet1Pieza = em.merge(piezaSet1Pieza);
            }
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet = materiaprima.getDetallerequerimientosmateriaprimaSet();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSet) {
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.setIdmateriaprima(null);
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
            }
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet1 = materiaprima.getDetallerequerimientosmateriaprimaSet1();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSet1) {
                detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima.setIdmateriaprima1(null);
                detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima);
            }
            Set<Matriz> matrizSet = materiaprima.getMatrizSet();
            for (Matriz matrizSetMatriz : matrizSet) {
                matrizSetMatriz.setMateriaprima(null);
                matrizSetMatriz = em.merge(matrizSetMatriz);
            }
            Set<Matriz> matrizSet1 = materiaprima.getMatrizSet1();
            for (Matriz matrizSet1Matriz : matrizSet1) {
                matrizSet1Matriz.setMateriaprima1(null);
                matrizSet1Matriz = em.merge(matrizSet1Matriz);
            }
            Set<Detallempasignada> detallempasignadaSet = materiaprima.getDetallempasignadaSet();
            for (Detallempasignada detallempasignadaSetDetallempasignada : detallempasignadaSet) {
                detallempasignadaSetDetallempasignada.setIdmateriaprima(null);
                detallempasignadaSetDetallempasignada = em.merge(detallempasignadaSetDetallempasignada);
            }
            Set<Detallempasignada> detallempasignadaSet1 = materiaprima.getDetallempasignadaSet1();
            for (Detallempasignada detallempasignadaSet1Detallempasignada : detallempasignadaSet1) {
                detallempasignadaSet1Detallempasignada.setIdmateriaprima1(null);
                detallempasignadaSet1Detallempasignada = em.merge(detallempasignadaSet1Detallempasignada);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet = materiaprima.getDetalleproductopresupuestoSet();
            for (Detalleproductopresupuesto detalleproductopresupuestoSetDetalleproductopresupuesto : detalleproductopresupuestoSet) {
                detalleproductopresupuestoSetDetalleproductopresupuesto.setIdmateriaprima(null);
                detalleproductopresupuestoSetDetalleproductopresupuesto = em.merge(detalleproductopresupuestoSetDetalleproductopresupuesto);
            }
            Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1 = materiaprima.getDetalleproductopresupuestoSet1();
            for (Detalleproductopresupuesto detalleproductopresupuestoSet1Detalleproductopresupuesto : detalleproductopresupuestoSet1) {
                detalleproductopresupuestoSet1Detalleproductopresupuesto.setIdmateriaprima1(null);
                detalleproductopresupuestoSet1Detalleproductopresupuesto = em.merge(detalleproductopresupuestoSet1Detalleproductopresupuesto);
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
