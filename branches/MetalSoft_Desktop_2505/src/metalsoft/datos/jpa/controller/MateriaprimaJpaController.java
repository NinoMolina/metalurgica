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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Unidadmedida;
import metalsoft.datos.jpa.entity.Tipomaterial;
import metalsoft.datos.jpa.entity.Codigodebarra;
import metalsoft.datos.jpa.entity.Detallecompra;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Detallerequerimientosmateriaprima;
import metalsoft.datos.jpa.entity.Matriz;
import metalsoft.datos.jpa.entity.Detallempasignada;
import metalsoft.datos.jpa.entity.Proveedorxmateriaprima;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;

/**
 *
 * @author Nino
 */
public class MateriaprimaJpaController implements Serializable {

    public MateriaprimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materiaprima materiaprima) throws PreexistingEntityException, Exception {
        if (materiaprima.getDetallecompraList() == null) {
            materiaprima.setDetallecompraList(new ArrayList<Detallecompra>());
        }
        if (materiaprima.getPiezaList() == null) {
            materiaprima.setPiezaList(new ArrayList<Pieza>());
        }
        if (materiaprima.getDetallerequerimientosmateriaprimaList() == null) {
            materiaprima.setDetallerequerimientosmateriaprimaList(new ArrayList<Detallerequerimientosmateriaprima>());
        }
        if (materiaprima.getMatrizList() == null) {
            materiaprima.setMatrizList(new ArrayList<Matriz>());
        }
        if (materiaprima.getDetallempasignadaList() == null) {
            materiaprima.setDetallempasignadaList(new ArrayList<Detallempasignada>());
        }
        if (materiaprima.getProveedorxmateriaprimaList() == null) {
            materiaprima.setProveedorxmateriaprimaList(new ArrayList<Proveedorxmateriaprima>());
        }
        if (materiaprima.getDetalleproductopresupuestoList() == null) {
            materiaprima.setDetalleproductopresupuestoList(new ArrayList<Detalleproductopresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidadmedida unidadmedida = materiaprima.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida = em.getReference(unidadmedida.getClass(), unidadmedida.getIdunidadmedida());
                materiaprima.setUnidadmedida(unidadmedida);
            }
            Tipomaterial tipomaterial = materiaprima.getTipomaterial();
            if (tipomaterial != null) {
                tipomaterial = em.getReference(tipomaterial.getClass(), tipomaterial.getIdtipomaterial());
                materiaprima.setTipomaterial(tipomaterial);
            }
            Codigodebarra codbarra = materiaprima.getCodbarra();
            if (codbarra != null) {
                codbarra = em.getReference(codbarra.getClass(), codbarra.getIdcodigo());
                materiaprima.setCodbarra(codbarra);
            }
            List<Detallecompra> attachedDetallecompraList = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListDetallecompraToAttach : materiaprima.getDetallecompraList()) {
                detallecompraListDetallecompraToAttach = em.getReference(detallecompraListDetallecompraToAttach.getClass(), detallecompraListDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraList.add(detallecompraListDetallecompraToAttach);
            }
            materiaprima.setDetallecompraList(attachedDetallecompraList);
            List<Pieza> attachedPiezaList = new ArrayList<Pieza>();
            for (Pieza piezaListPiezaToAttach : materiaprima.getPiezaList()) {
                piezaListPiezaToAttach = em.getReference(piezaListPiezaToAttach.getClass(), piezaListPiezaToAttach.getIdpieza());
                attachedPiezaList.add(piezaListPiezaToAttach);
            }
            materiaprima.setPiezaList(attachedPiezaList);
            List<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaList = new ArrayList<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach : materiaprima.getDetallerequerimientosmateriaprimaList()) {
                detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaList.add(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach);
            }
            materiaprima.setDetallerequerimientosmateriaprimaList(attachedDetallerequerimientosmateriaprimaList);
            List<Matriz> attachedMatrizList = new ArrayList<Matriz>();
            for (Matriz matrizListMatrizToAttach : materiaprima.getMatrizList()) {
                matrizListMatrizToAttach = em.getReference(matrizListMatrizToAttach.getClass(), matrizListMatrizToAttach.getIdmatriz());
                attachedMatrizList.add(matrizListMatrizToAttach);
            }
            materiaprima.setMatrizList(attachedMatrizList);
            List<Detallempasignada> attachedDetallempasignadaList = new ArrayList<Detallempasignada>();
            for (Detallempasignada detallempasignadaListDetallempasignadaToAttach : materiaprima.getDetallempasignadaList()) {
                detallempasignadaListDetallempasignadaToAttach = em.getReference(detallempasignadaListDetallempasignadaToAttach.getClass(), detallempasignadaListDetallempasignadaToAttach.getId());
                attachedDetallempasignadaList.add(detallempasignadaListDetallempasignadaToAttach);
            }
            materiaprima.setDetallempasignadaList(attachedDetallempasignadaList);
            List<Proveedorxmateriaprima> attachedProveedorxmateriaprimaList = new ArrayList<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaListProveedorxmateriaprimaToAttach : materiaprima.getProveedorxmateriaprimaList()) {
                proveedorxmateriaprimaListProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaListProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaListProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaList.add(proveedorxmateriaprimaListProveedorxmateriaprimaToAttach);
            }
            materiaprima.setProveedorxmateriaprimaList(attachedProveedorxmateriaprimaList);
            List<Detalleproductopresupuesto> attachedDetalleproductopresupuestoList = new ArrayList<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuestoToAttach : materiaprima.getDetalleproductopresupuestoList()) {
                detalleproductopresupuestoListDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoListDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoListDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoList.add(detalleproductopresupuestoListDetalleproductopresupuestoToAttach);
            }
            materiaprima.setDetalleproductopresupuestoList(attachedDetalleproductopresupuestoList);
            em.persist(materiaprima);
            if (unidadmedida != null) {
                unidadmedida.getMateriaprimaList().add(materiaprima);
                unidadmedida = em.merge(unidadmedida);
            }
            if (tipomaterial != null) {
                tipomaterial.getMateriaprimaList().add(materiaprima);
                tipomaterial = em.merge(tipomaterial);
            }
            if (codbarra != null) {
                codbarra.getMateriaprimaList().add(materiaprima);
                codbarra = em.merge(codbarra);
            }
            for (Detallecompra detallecompraListDetallecompra : materiaprima.getDetallecompraList()) {
                Materiaprima oldMateriaprimaOfDetallecompraListDetallecompra = detallecompraListDetallecompra.getMateriaprima();
                detallecompraListDetallecompra.setMateriaprima(materiaprima);
                detallecompraListDetallecompra = em.merge(detallecompraListDetallecompra);
                if (oldMateriaprimaOfDetallecompraListDetallecompra != null) {
                    oldMateriaprimaOfDetallecompraListDetallecompra.getDetallecompraList().remove(detallecompraListDetallecompra);
                    oldMateriaprimaOfDetallecompraListDetallecompra = em.merge(oldMateriaprimaOfDetallecompraListDetallecompra);
                }
            }
            for (Pieza piezaListPieza : materiaprima.getPiezaList()) {
                Materiaprima oldMateriaprimaOfPiezaListPieza = piezaListPieza.getMateriaprima();
                piezaListPieza.setMateriaprima(materiaprima);
                piezaListPieza = em.merge(piezaListPieza);
                if (oldMateriaprimaOfPiezaListPieza != null) {
                    oldMateriaprimaOfPiezaListPieza.getPiezaList().remove(piezaListPieza);
                    oldMateriaprimaOfPiezaListPieza = em.merge(oldMateriaprimaOfPiezaListPieza);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima : materiaprima.getDetallerequerimientosmateriaprimaList()) {
                Materiaprima oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima.getIdmateriaprima();
                detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima.setIdmateriaprima(materiaprima);
                detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima);
                if (oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima != null) {
                    oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList().remove(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima);
                    oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima = em.merge(oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima);
                }
            }
            for (Matriz matrizListMatriz : materiaprima.getMatrizList()) {
                Materiaprima oldMateriaprimaOfMatrizListMatriz = matrizListMatriz.getMateriaprima();
                matrizListMatriz.setMateriaprima(materiaprima);
                matrizListMatriz = em.merge(matrizListMatriz);
                if (oldMateriaprimaOfMatrizListMatriz != null) {
                    oldMateriaprimaOfMatrizListMatriz.getMatrizList().remove(matrizListMatriz);
                    oldMateriaprimaOfMatrizListMatriz = em.merge(oldMateriaprimaOfMatrizListMatriz);
                }
            }
            for (Detallempasignada detallempasignadaListDetallempasignada : materiaprima.getDetallempasignadaList()) {
                Materiaprima oldIdmateriaprimaOfDetallempasignadaListDetallempasignada = detallempasignadaListDetallempasignada.getIdmateriaprima();
                detallempasignadaListDetallempasignada.setIdmateriaprima(materiaprima);
                detallempasignadaListDetallempasignada = em.merge(detallempasignadaListDetallempasignada);
                if (oldIdmateriaprimaOfDetallempasignadaListDetallempasignada != null) {
                    oldIdmateriaprimaOfDetallempasignadaListDetallempasignada.getDetallempasignadaList().remove(detallempasignadaListDetallempasignada);
                    oldIdmateriaprimaOfDetallempasignadaListDetallempasignada = em.merge(oldIdmateriaprimaOfDetallempasignadaListDetallempasignada);
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaListProveedorxmateriaprima : materiaprima.getProveedorxmateriaprimaList()) {
                Materiaprima oldMateriaprimaOfProveedorxmateriaprimaListProveedorxmateriaprima = proveedorxmateriaprimaListProveedorxmateriaprima.getMateriaprima();
                proveedorxmateriaprimaListProveedorxmateriaprima.setMateriaprima(materiaprima);
                proveedorxmateriaprimaListProveedorxmateriaprima = em.merge(proveedorxmateriaprimaListProveedorxmateriaprima);
                if (oldMateriaprimaOfProveedorxmateriaprimaListProveedorxmateriaprima != null) {
                    oldMateriaprimaOfProveedorxmateriaprimaListProveedorxmateriaprima.getProveedorxmateriaprimaList().remove(proveedorxmateriaprimaListProveedorxmateriaprima);
                    oldMateriaprimaOfProveedorxmateriaprimaListProveedorxmateriaprima = em.merge(oldMateriaprimaOfProveedorxmateriaprimaListProveedorxmateriaprima);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuesto : materiaprima.getDetalleproductopresupuestoList()) {
                Materiaprima oldIdmateriaprimaOfDetalleproductopresupuestoListDetalleproductopresupuesto = detalleproductopresupuestoListDetalleproductopresupuesto.getIdmateriaprima();
                detalleproductopresupuestoListDetalleproductopresupuesto.setIdmateriaprima(materiaprima);
                detalleproductopresupuestoListDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListDetalleproductopresupuesto);
                if (oldIdmateriaprimaOfDetalleproductopresupuestoListDetalleproductopresupuesto != null) {
                    oldIdmateriaprimaOfDetalleproductopresupuestoListDetalleproductopresupuesto.getDetalleproductopresupuestoList().remove(detalleproductopresupuestoListDetalleproductopresupuesto);
                    oldIdmateriaprimaOfDetalleproductopresupuestoListDetalleproductopresupuesto = em.merge(oldIdmateriaprimaOfDetalleproductopresupuestoListDetalleproductopresupuesto);
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
            Unidadmedida unidadmedidaOld = persistentMateriaprima.getUnidadmedida();
            Unidadmedida unidadmedidaNew = materiaprima.getUnidadmedida();
            Tipomaterial tipomaterialOld = persistentMateriaprima.getTipomaterial();
            Tipomaterial tipomaterialNew = materiaprima.getTipomaterial();
            Codigodebarra codbarraOld = persistentMateriaprima.getCodbarra();
            Codigodebarra codbarraNew = materiaprima.getCodbarra();
            List<Detallecompra> detallecompraListOld = persistentMateriaprima.getDetallecompraList();
            List<Detallecompra> detallecompraListNew = materiaprima.getDetallecompraList();
            List<Pieza> piezaListOld = persistentMateriaprima.getPiezaList();
            List<Pieza> piezaListNew = materiaprima.getPiezaList();
            List<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaListOld = persistentMateriaprima.getDetallerequerimientosmateriaprimaList();
            List<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaListNew = materiaprima.getDetallerequerimientosmateriaprimaList();
            List<Matriz> matrizListOld = persistentMateriaprima.getMatrizList();
            List<Matriz> matrizListNew = materiaprima.getMatrizList();
            List<Detallempasignada> detallempasignadaListOld = persistentMateriaprima.getDetallempasignadaList();
            List<Detallempasignada> detallempasignadaListNew = materiaprima.getDetallempasignadaList();
            List<Proveedorxmateriaprima> proveedorxmateriaprimaListOld = persistentMateriaprima.getProveedorxmateriaprimaList();
            List<Proveedorxmateriaprima> proveedorxmateriaprimaListNew = materiaprima.getProveedorxmateriaprimaList();
            List<Detalleproductopresupuesto> detalleproductopresupuestoListOld = persistentMateriaprima.getDetalleproductopresupuestoList();
            List<Detalleproductopresupuesto> detalleproductopresupuestoListNew = materiaprima.getDetalleproductopresupuestoList();
            List<String> illegalOrphanMessages = null;
            for (Proveedorxmateriaprima proveedorxmateriaprimaListOldProveedorxmateriaprima : proveedorxmateriaprimaListOld) {
                if (!proveedorxmateriaprimaListNew.contains(proveedorxmateriaprimaListOldProveedorxmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proveedorxmateriaprima " + proveedorxmateriaprimaListOldProveedorxmateriaprima + " since its materiaprima field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (unidadmedidaNew != null) {
                unidadmedidaNew = em.getReference(unidadmedidaNew.getClass(), unidadmedidaNew.getIdunidadmedida());
                materiaprima.setUnidadmedida(unidadmedidaNew);
            }
            if (tipomaterialNew != null) {
                tipomaterialNew = em.getReference(tipomaterialNew.getClass(), tipomaterialNew.getIdtipomaterial());
                materiaprima.setTipomaterial(tipomaterialNew);
            }
            if (codbarraNew != null) {
                codbarraNew = em.getReference(codbarraNew.getClass(), codbarraNew.getIdcodigo());
                materiaprima.setCodbarra(codbarraNew);
            }
            List<Detallecompra> attachedDetallecompraListNew = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListNewDetallecompraToAttach : detallecompraListNew) {
                detallecompraListNewDetallecompraToAttach = em.getReference(detallecompraListNewDetallecompraToAttach.getClass(), detallecompraListNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraListNew.add(detallecompraListNewDetallecompraToAttach);
            }
            detallecompraListNew = attachedDetallecompraListNew;
            materiaprima.setDetallecompraList(detallecompraListNew);
            List<Pieza> attachedPiezaListNew = new ArrayList<Pieza>();
            for (Pieza piezaListNewPiezaToAttach : piezaListNew) {
                piezaListNewPiezaToAttach = em.getReference(piezaListNewPiezaToAttach.getClass(), piezaListNewPiezaToAttach.getIdpieza());
                attachedPiezaListNew.add(piezaListNewPiezaToAttach);
            }
            piezaListNew = attachedPiezaListNew;
            materiaprima.setPiezaList(piezaListNew);
            List<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaListNew = new ArrayList<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach : detallerequerimientosmateriaprimaListNew) {
                detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaListNew.add(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach);
            }
            detallerequerimientosmateriaprimaListNew = attachedDetallerequerimientosmateriaprimaListNew;
            materiaprima.setDetallerequerimientosmateriaprimaList(detallerequerimientosmateriaprimaListNew);
            List<Matriz> attachedMatrizListNew = new ArrayList<Matriz>();
            for (Matriz matrizListNewMatrizToAttach : matrizListNew) {
                matrizListNewMatrizToAttach = em.getReference(matrizListNewMatrizToAttach.getClass(), matrizListNewMatrizToAttach.getIdmatriz());
                attachedMatrizListNew.add(matrizListNewMatrizToAttach);
            }
            matrizListNew = attachedMatrizListNew;
            materiaprima.setMatrizList(matrizListNew);
            List<Detallempasignada> attachedDetallempasignadaListNew = new ArrayList<Detallempasignada>();
            for (Detallempasignada detallempasignadaListNewDetallempasignadaToAttach : detallempasignadaListNew) {
                detallempasignadaListNewDetallempasignadaToAttach = em.getReference(detallempasignadaListNewDetallempasignadaToAttach.getClass(), detallempasignadaListNewDetallempasignadaToAttach.getId());
                attachedDetallempasignadaListNew.add(detallempasignadaListNewDetallempasignadaToAttach);
            }
            detallempasignadaListNew = attachedDetallempasignadaListNew;
            materiaprima.setDetallempasignadaList(detallempasignadaListNew);
            List<Proveedorxmateriaprima> attachedProveedorxmateriaprimaListNew = new ArrayList<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach : proveedorxmateriaprimaListNew) {
                proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaListNew.add(proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach);
            }
            proveedorxmateriaprimaListNew = attachedProveedorxmateriaprimaListNew;
            materiaprima.setProveedorxmateriaprimaList(proveedorxmateriaprimaListNew);
            List<Detalleproductopresupuesto> attachedDetalleproductopresupuestoListNew = new ArrayList<Detalleproductopresupuesto>();
            for (Detalleproductopresupuesto detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach : detalleproductopresupuestoListNew) {
                detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach = em.getReference(detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach.getClass(), detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach.getIddetalle());
                attachedDetalleproductopresupuestoListNew.add(detalleproductopresupuestoListNewDetalleproductopresupuestoToAttach);
            }
            detalleproductopresupuestoListNew = attachedDetalleproductopresupuestoListNew;
            materiaprima.setDetalleproductopresupuestoList(detalleproductopresupuestoListNew);
            materiaprima = em.merge(materiaprima);
            if (unidadmedidaOld != null && !unidadmedidaOld.equals(unidadmedidaNew)) {
                unidadmedidaOld.getMateriaprimaList().remove(materiaprima);
                unidadmedidaOld = em.merge(unidadmedidaOld);
            }
            if (unidadmedidaNew != null && !unidadmedidaNew.equals(unidadmedidaOld)) {
                unidadmedidaNew.getMateriaprimaList().add(materiaprima);
                unidadmedidaNew = em.merge(unidadmedidaNew);
            }
            if (tipomaterialOld != null && !tipomaterialOld.equals(tipomaterialNew)) {
                tipomaterialOld.getMateriaprimaList().remove(materiaprima);
                tipomaterialOld = em.merge(tipomaterialOld);
            }
            if (tipomaterialNew != null && !tipomaterialNew.equals(tipomaterialOld)) {
                tipomaterialNew.getMateriaprimaList().add(materiaprima);
                tipomaterialNew = em.merge(tipomaterialNew);
            }
            if (codbarraOld != null && !codbarraOld.equals(codbarraNew)) {
                codbarraOld.getMateriaprimaList().remove(materiaprima);
                codbarraOld = em.merge(codbarraOld);
            }
            if (codbarraNew != null && !codbarraNew.equals(codbarraOld)) {
                codbarraNew.getMateriaprimaList().add(materiaprima);
                codbarraNew = em.merge(codbarraNew);
            }
            for (Detallecompra detallecompraListOldDetallecompra : detallecompraListOld) {
                if (!detallecompraListNew.contains(detallecompraListOldDetallecompra)) {
                    detallecompraListOldDetallecompra.setMateriaprima(null);
                    detallecompraListOldDetallecompra = em.merge(detallecompraListOldDetallecompra);
                }
            }
            for (Detallecompra detallecompraListNewDetallecompra : detallecompraListNew) {
                if (!detallecompraListOld.contains(detallecompraListNewDetallecompra)) {
                    Materiaprima oldMateriaprimaOfDetallecompraListNewDetallecompra = detallecompraListNewDetallecompra.getMateriaprima();
                    detallecompraListNewDetallecompra.setMateriaprima(materiaprima);
                    detallecompraListNewDetallecompra = em.merge(detallecompraListNewDetallecompra);
                    if (oldMateriaprimaOfDetallecompraListNewDetallecompra != null && !oldMateriaprimaOfDetallecompraListNewDetallecompra.equals(materiaprima)) {
                        oldMateriaprimaOfDetallecompraListNewDetallecompra.getDetallecompraList().remove(detallecompraListNewDetallecompra);
                        oldMateriaprimaOfDetallecompraListNewDetallecompra = em.merge(oldMateriaprimaOfDetallecompraListNewDetallecompra);
                    }
                }
            }
            for (Pieza piezaListOldPieza : piezaListOld) {
                if (!piezaListNew.contains(piezaListOldPieza)) {
                    piezaListOldPieza.setMateriaprima(null);
                    piezaListOldPieza = em.merge(piezaListOldPieza);
                }
            }
            for (Pieza piezaListNewPieza : piezaListNew) {
                if (!piezaListOld.contains(piezaListNewPieza)) {
                    Materiaprima oldMateriaprimaOfPiezaListNewPieza = piezaListNewPieza.getMateriaprima();
                    piezaListNewPieza.setMateriaprima(materiaprima);
                    piezaListNewPieza = em.merge(piezaListNewPieza);
                    if (oldMateriaprimaOfPiezaListNewPieza != null && !oldMateriaprimaOfPiezaListNewPieza.equals(materiaprima)) {
                        oldMateriaprimaOfPiezaListNewPieza.getPiezaList().remove(piezaListNewPieza);
                        oldMateriaprimaOfPiezaListNewPieza = em.merge(oldMateriaprimaOfPiezaListNewPieza);
                    }
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListOldDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaListOld) {
                if (!detallerequerimientosmateriaprimaListNew.contains(detallerequerimientosmateriaprimaListOldDetallerequerimientosmateriaprima)) {
                    detallerequerimientosmateriaprimaListOldDetallerequerimientosmateriaprima.setIdmateriaprima(null);
                    detallerequerimientosmateriaprimaListOldDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaListOldDetallerequerimientosmateriaprima);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaListNew) {
                if (!detallerequerimientosmateriaprimaListOld.contains(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima)) {
                    Materiaprima oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima.getIdmateriaprima();
                    detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima.setIdmateriaprima(materiaprima);
                    detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima);
                    if (oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima != null && !oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima.equals(materiaprima)) {
                        oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList().remove(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima);
                        oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima = em.merge(oldIdmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima);
                    }
                }
            }
            for (Matriz matrizListOldMatriz : matrizListOld) {
                if (!matrizListNew.contains(matrizListOldMatriz)) {
                    matrizListOldMatriz.setMateriaprima(null);
                    matrizListOldMatriz = em.merge(matrizListOldMatriz);
                }
            }
            for (Matriz matrizListNewMatriz : matrizListNew) {
                if (!matrizListOld.contains(matrizListNewMatriz)) {
                    Materiaprima oldMateriaprimaOfMatrizListNewMatriz = matrizListNewMatriz.getMateriaprima();
                    matrizListNewMatriz.setMateriaprima(materiaprima);
                    matrizListNewMatriz = em.merge(matrizListNewMatriz);
                    if (oldMateriaprimaOfMatrizListNewMatriz != null && !oldMateriaprimaOfMatrizListNewMatriz.equals(materiaprima)) {
                        oldMateriaprimaOfMatrizListNewMatriz.getMatrizList().remove(matrizListNewMatriz);
                        oldMateriaprimaOfMatrizListNewMatriz = em.merge(oldMateriaprimaOfMatrizListNewMatriz);
                    }
                }
            }
            for (Detallempasignada detallempasignadaListOldDetallempasignada : detallempasignadaListOld) {
                if (!detallempasignadaListNew.contains(detallempasignadaListOldDetallempasignada)) {
                    detallempasignadaListOldDetallempasignada.setIdmateriaprima(null);
                    detallempasignadaListOldDetallempasignada = em.merge(detallempasignadaListOldDetallempasignada);
                }
            }
            for (Detallempasignada detallempasignadaListNewDetallempasignada : detallempasignadaListNew) {
                if (!detallempasignadaListOld.contains(detallempasignadaListNewDetallempasignada)) {
                    Materiaprima oldIdmateriaprimaOfDetallempasignadaListNewDetallempasignada = detallempasignadaListNewDetallempasignada.getIdmateriaprima();
                    detallempasignadaListNewDetallempasignada.setIdmateriaprima(materiaprima);
                    detallempasignadaListNewDetallempasignada = em.merge(detallempasignadaListNewDetallempasignada);
                    if (oldIdmateriaprimaOfDetallempasignadaListNewDetallempasignada != null && !oldIdmateriaprimaOfDetallempasignadaListNewDetallempasignada.equals(materiaprima)) {
                        oldIdmateriaprimaOfDetallempasignadaListNewDetallempasignada.getDetallempasignadaList().remove(detallempasignadaListNewDetallempasignada);
                        oldIdmateriaprimaOfDetallempasignadaListNewDetallempasignada = em.merge(oldIdmateriaprimaOfDetallempasignadaListNewDetallempasignada);
                    }
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaListNewProveedorxmateriaprima : proveedorxmateriaprimaListNew) {
                if (!proveedorxmateriaprimaListOld.contains(proveedorxmateriaprimaListNewProveedorxmateriaprima)) {
                    Materiaprima oldMateriaprimaOfProveedorxmateriaprimaListNewProveedorxmateriaprima = proveedorxmateriaprimaListNewProveedorxmateriaprima.getMateriaprima();
                    proveedorxmateriaprimaListNewProveedorxmateriaprima.setMateriaprima(materiaprima);
                    proveedorxmateriaprimaListNewProveedorxmateriaprima = em.merge(proveedorxmateriaprimaListNewProveedorxmateriaprima);
                    if (oldMateriaprimaOfProveedorxmateriaprimaListNewProveedorxmateriaprima != null && !oldMateriaprimaOfProveedorxmateriaprimaListNewProveedorxmateriaprima.equals(materiaprima)) {
                        oldMateriaprimaOfProveedorxmateriaprimaListNewProveedorxmateriaprima.getProveedorxmateriaprimaList().remove(proveedorxmateriaprimaListNewProveedorxmateriaprima);
                        oldMateriaprimaOfProveedorxmateriaprimaListNewProveedorxmateriaprima = em.merge(oldMateriaprimaOfProveedorxmateriaprimaListNewProveedorxmateriaprima);
                    }
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListOldDetalleproductopresupuesto : detalleproductopresupuestoListOld) {
                if (!detalleproductopresupuestoListNew.contains(detalleproductopresupuestoListOldDetalleproductopresupuesto)) {
                    detalleproductopresupuestoListOldDetalleproductopresupuesto.setIdmateriaprima(null);
                    detalleproductopresupuestoListOldDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListOldDetalleproductopresupuesto);
                }
            }
            for (Detalleproductopresupuesto detalleproductopresupuestoListNewDetalleproductopresupuesto : detalleproductopresupuestoListNew) {
                if (!detalleproductopresupuestoListOld.contains(detalleproductopresupuestoListNewDetalleproductopresupuesto)) {
                    Materiaprima oldIdmateriaprimaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto = detalleproductopresupuestoListNewDetalleproductopresupuesto.getIdmateriaprima();
                    detalleproductopresupuestoListNewDetalleproductopresupuesto.setIdmateriaprima(materiaprima);
                    detalleproductopresupuestoListNewDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListNewDetalleproductopresupuesto);
                    if (oldIdmateriaprimaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto != null && !oldIdmateriaprimaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto.equals(materiaprima)) {
                        oldIdmateriaprimaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto.getDetalleproductopresupuestoList().remove(detalleproductopresupuestoListNewDetalleproductopresupuesto);
                        oldIdmateriaprimaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto = em.merge(oldIdmateriaprimaOfDetalleproductopresupuestoListNewDetalleproductopresupuesto);
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
            List<Proveedorxmateriaprima> proveedorxmateriaprimaListOrphanCheck = materiaprima.getProveedorxmateriaprimaList();
            for (Proveedorxmateriaprima proveedorxmateriaprimaListOrphanCheckProveedorxmateriaprima : proveedorxmateriaprimaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Materiaprima (" + materiaprima + ") cannot be destroyed since the Proveedorxmateriaprima " + proveedorxmateriaprimaListOrphanCheckProveedorxmateriaprima + " in its proveedorxmateriaprimaList field has a non-nullable materiaprima field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Unidadmedida unidadmedida = materiaprima.getUnidadmedida();
            if (unidadmedida != null) {
                unidadmedida.getMateriaprimaList().remove(materiaprima);
                unidadmedida = em.merge(unidadmedida);
            }
            Tipomaterial tipomaterial = materiaprima.getTipomaterial();
            if (tipomaterial != null) {
                tipomaterial.getMateriaprimaList().remove(materiaprima);
                tipomaterial = em.merge(tipomaterial);
            }
            Codigodebarra codbarra = materiaprima.getCodbarra();
            if (codbarra != null) {
                codbarra.getMateriaprimaList().remove(materiaprima);
                codbarra = em.merge(codbarra);
            }
            List<Detallecompra> detallecompraList = materiaprima.getDetallecompraList();
            for (Detallecompra detallecompraListDetallecompra : detallecompraList) {
                detallecompraListDetallecompra.setMateriaprima(null);
                detallecompraListDetallecompra = em.merge(detallecompraListDetallecompra);
            }
            List<Pieza> piezaList = materiaprima.getPiezaList();
            for (Pieza piezaListPieza : piezaList) {
                piezaListPieza.setMateriaprima(null);
                piezaListPieza = em.merge(piezaListPieza);
            }
            List<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaList = materiaprima.getDetallerequerimientosmateriaprimaList();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaList) {
                detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima.setIdmateriaprima(null);
                detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima);
            }
            List<Matriz> matrizList = materiaprima.getMatrizList();
            for (Matriz matrizListMatriz : matrizList) {
                matrizListMatriz.setMateriaprima(null);
                matrizListMatriz = em.merge(matrizListMatriz);
            }
            List<Detallempasignada> detallempasignadaList = materiaprima.getDetallempasignadaList();
            for (Detallempasignada detallempasignadaListDetallempasignada : detallempasignadaList) {
                detallempasignadaListDetallempasignada.setIdmateriaprima(null);
                detallempasignadaListDetallempasignada = em.merge(detallempasignadaListDetallempasignada);
            }
            List<Detalleproductopresupuesto> detalleproductopresupuestoList = materiaprima.getDetalleproductopresupuestoList();
            for (Detalleproductopresupuesto detalleproductopresupuestoListDetalleproductopresupuesto : detalleproductopresupuestoList) {
                detalleproductopresupuestoListDetalleproductopresupuesto.setIdmateriaprima(null);
                detalleproductopresupuestoListDetalleproductopresupuesto = em.merge(detalleproductopresupuestoListDetalleproductopresupuesto);
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
