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
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Estadopiezareal;
import metalsoft.datos.jpa.entity.Codigodebarra;
import metalsoft.datos.jpa.entity.Detalleproductoreal;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Mpasignadaxpiezareal;
import metalsoft.datos.jpa.entity.Piezareal;

/**
 *
 * @author Nino
 */
public class PiezarealJpaController implements Serializable {

    public PiezarealJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Piezareal piezareal) throws PreexistingEntityException, Exception {
        if (piezareal.getDetalleproductorealList() == null) {
            piezareal.setDetalleproductorealList(new ArrayList<Detalleproductoreal>());
        }
        if (piezareal.getDetalleejecucionplanificacionList() == null) {
            piezareal.setDetalleejecucionplanificacionList(new ArrayList<Detalleejecucionplanificacion>());
        }
        if (piezareal.getDetalleejecucionplanificacioncalidadList() == null) {
            piezareal.setDetalleejecucionplanificacioncalidadList(new ArrayList<Detalleejecucionplanificacioncalidad>());
        }
        if (piezareal.getMpasignadaxpiezarealList() == null) {
            piezareal.setMpasignadaxpiezarealList(new ArrayList<Mpasignadaxpiezareal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pieza idpieza = piezareal.getIdpieza();
            if (idpieza != null) {
                idpieza = em.getReference(idpieza.getClass(), idpieza.getIdpieza());
                piezareal.setIdpieza(idpieza);
            }
            Estadopiezareal estado = piezareal.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                piezareal.setEstado(estado);
            }
            Codigodebarra idcodigobarra = piezareal.getIdcodigobarra();
            if (idcodigobarra != null) {
                idcodigobarra = em.getReference(idcodigobarra.getClass(), idcodigobarra.getIdcodigo());
                piezareal.setIdcodigobarra(idcodigobarra);
            }
            List<Detalleproductoreal> attachedDetalleproductorealList = new ArrayList<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealListDetalleproductorealToAttach : piezareal.getDetalleproductorealList()) {
                detalleproductorealListDetalleproductorealToAttach = em.getReference(detalleproductorealListDetalleproductorealToAttach.getClass(), detalleproductorealListDetalleproductorealToAttach.getIddetalle());
                attachedDetalleproductorealList.add(detalleproductorealListDetalleproductorealToAttach);
            }
            piezareal.setDetalleproductorealList(attachedDetalleproductorealList);
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionList = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach : piezareal.getDetalleejecucionplanificacionList()) {
                detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionList.add(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach);
            }
            piezareal.setDetalleejecucionplanificacionList(attachedDetalleejecucionplanificacionList);
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadList = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach : piezareal.getDetalleejecucionplanificacioncalidadList()) {
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleejecucionplanificacioncalidadList.add(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach);
            }
            piezareal.setDetalleejecucionplanificacioncalidadList(attachedDetalleejecucionplanificacioncalidadList);
            List<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealList = new ArrayList<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezarealToAttach : piezareal.getMpasignadaxpiezarealList()) {
                mpasignadaxpiezarealListMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealListMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealListMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealList.add(mpasignadaxpiezarealListMpasignadaxpiezarealToAttach);
            }
            piezareal.setMpasignadaxpiezarealList(attachedMpasignadaxpiezarealList);
            em.persist(piezareal);
            if (idpieza != null) {
                idpieza.getPiezarealList().add(piezareal);
                idpieza = em.merge(idpieza);
            }
            if (estado != null) {
                estado.getPiezarealList().add(piezareal);
                estado = em.merge(estado);
            }
            if (idcodigobarra != null) {
                idcodigobarra.getPiezarealList().add(piezareal);
                idcodigobarra = em.merge(idcodigobarra);
            }
            for (Detalleproductoreal detalleproductorealListDetalleproductoreal : piezareal.getDetalleproductorealList()) {
                Piezareal oldIdpiezarealOfDetalleproductorealListDetalleproductoreal = detalleproductorealListDetalleproductoreal.getIdpiezareal();
                detalleproductorealListDetalleproductoreal.setIdpiezareal(piezareal);
                detalleproductorealListDetalleproductoreal = em.merge(detalleproductorealListDetalleproductoreal);
                if (oldIdpiezarealOfDetalleproductorealListDetalleproductoreal != null) {
                    oldIdpiezarealOfDetalleproductorealListDetalleproductoreal.getDetalleproductorealList().remove(detalleproductorealListDetalleproductoreal);
                    oldIdpiezarealOfDetalleproductorealListDetalleproductoreal = em.merge(oldIdpiezarealOfDetalleproductorealListDetalleproductoreal);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : piezareal.getDetalleejecucionplanificacionList()) {
                Piezareal oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = detalleejecucionplanificacionListDetalleejecucionplanificacion.getPiezareal();
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setPiezareal(piezareal);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                if (oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion != null) {
                    oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                    oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad : piezareal.getDetalleejecucionplanificacioncalidadList()) {
                Piezareal oldPiezarealOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getPiezareal();
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.setPiezareal(piezareal);
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                if (oldPiezarealOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad != null) {
                    oldPiezarealOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                    oldPiezarealOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(oldPiezarealOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezareal : piezareal.getMpasignadaxpiezarealList()) {
                Piezareal oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal = mpasignadaxpiezarealListMpasignadaxpiezareal.getIdpiezareal();
                mpasignadaxpiezarealListMpasignadaxpiezareal.setIdpiezareal(piezareal);
                mpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListMpasignadaxpiezareal);
                if (oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal != null) {
                    oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal.getMpasignadaxpiezarealList().remove(mpasignadaxpiezarealListMpasignadaxpiezareal);
                    oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPiezareal(piezareal.getIdpiezareal()) != null) {
                throw new PreexistingEntityException("Piezareal " + piezareal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Piezareal piezareal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Piezareal persistentPiezareal = em.find(Piezareal.class, piezareal.getIdpiezareal());
            Pieza idpiezaOld = persistentPiezareal.getIdpieza();
            Pieza idpiezaNew = piezareal.getIdpieza();
            Estadopiezareal estadoOld = persistentPiezareal.getEstado();
            Estadopiezareal estadoNew = piezareal.getEstado();
            Codigodebarra idcodigobarraOld = persistentPiezareal.getIdcodigobarra();
            Codigodebarra idcodigobarraNew = piezareal.getIdcodigobarra();
            List<Detalleproductoreal> detalleproductorealListOld = persistentPiezareal.getDetalleproductorealList();
            List<Detalleproductoreal> detalleproductorealListNew = piezareal.getDetalleproductorealList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListOld = persistentPiezareal.getDetalleejecucionplanificacionList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListNew = piezareal.getDetalleejecucionplanificacionList();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListOld = persistentPiezareal.getDetalleejecucionplanificacioncalidadList();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListNew = piezareal.getDetalleejecucionplanificacioncalidadList();
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealListOld = persistentPiezareal.getMpasignadaxpiezarealList();
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealListNew = piezareal.getMpasignadaxpiezarealList();
            if (idpiezaNew != null) {
                idpiezaNew = em.getReference(idpiezaNew.getClass(), idpiezaNew.getIdpieza());
                piezareal.setIdpieza(idpiezaNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                piezareal.setEstado(estadoNew);
            }
            if (idcodigobarraNew != null) {
                idcodigobarraNew = em.getReference(idcodigobarraNew.getClass(), idcodigobarraNew.getIdcodigo());
                piezareal.setIdcodigobarra(idcodigobarraNew);
            }
            List<Detalleproductoreal> attachedDetalleproductorealListNew = new ArrayList<Detalleproductoreal>();
            for (Detalleproductoreal detalleproductorealListNewDetalleproductorealToAttach : detalleproductorealListNew) {
                detalleproductorealListNewDetalleproductorealToAttach = em.getReference(detalleproductorealListNewDetalleproductorealToAttach.getClass(), detalleproductorealListNewDetalleproductorealToAttach.getIddetalle());
                attachedDetalleproductorealListNew.add(detalleproductorealListNewDetalleproductorealToAttach);
            }
            detalleproductorealListNew = attachedDetalleproductorealListNew;
            piezareal.setDetalleproductorealList(detalleproductorealListNew);
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionListNew = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionListNew) {
                detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionListNew.add(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionListNew = attachedDetalleejecucionplanificacionListNew;
            piezareal.setDetalleejecucionplanificacionList(detalleejecucionplanificacionListNew);
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadListNew = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadListNew) {
                detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleejecucionplanificacioncalidadListNew.add(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadListNew = attachedDetalleejecucionplanificacioncalidadListNew;
            piezareal.setDetalleejecucionplanificacioncalidadList(detalleejecucionplanificacioncalidadListNew);
            List<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealListNew = new ArrayList<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach : mpasignadaxpiezarealListNew) {
                mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealListNew.add(mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach);
            }
            mpasignadaxpiezarealListNew = attachedMpasignadaxpiezarealListNew;
            piezareal.setMpasignadaxpiezarealList(mpasignadaxpiezarealListNew);
            piezareal = em.merge(piezareal);
            if (idpiezaOld != null && !idpiezaOld.equals(idpiezaNew)) {
                idpiezaOld.getPiezarealList().remove(piezareal);
                idpiezaOld = em.merge(idpiezaOld);
            }
            if (idpiezaNew != null && !idpiezaNew.equals(idpiezaOld)) {
                idpiezaNew.getPiezarealList().add(piezareal);
                idpiezaNew = em.merge(idpiezaNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getPiezarealList().remove(piezareal);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getPiezarealList().add(piezareal);
                estadoNew = em.merge(estadoNew);
            }
            if (idcodigobarraOld != null && !idcodigobarraOld.equals(idcodigobarraNew)) {
                idcodigobarraOld.getPiezarealList().remove(piezareal);
                idcodigobarraOld = em.merge(idcodigobarraOld);
            }
            if (idcodigobarraNew != null && !idcodigobarraNew.equals(idcodigobarraOld)) {
                idcodigobarraNew.getPiezarealList().add(piezareal);
                idcodigobarraNew = em.merge(idcodigobarraNew);
            }
            for (Detalleproductoreal detalleproductorealListOldDetalleproductoreal : detalleproductorealListOld) {
                if (!detalleproductorealListNew.contains(detalleproductorealListOldDetalleproductoreal)) {
                    detalleproductorealListOldDetalleproductoreal.setIdpiezareal(null);
                    detalleproductorealListOldDetalleproductoreal = em.merge(detalleproductorealListOldDetalleproductoreal);
                }
            }
            for (Detalleproductoreal detalleproductorealListNewDetalleproductoreal : detalleproductorealListNew) {
                if (!detalleproductorealListOld.contains(detalleproductorealListNewDetalleproductoreal)) {
                    Piezareal oldIdpiezarealOfDetalleproductorealListNewDetalleproductoreal = detalleproductorealListNewDetalleproductoreal.getIdpiezareal();
                    detalleproductorealListNewDetalleproductoreal.setIdpiezareal(piezareal);
                    detalleproductorealListNewDetalleproductoreal = em.merge(detalleproductorealListNewDetalleproductoreal);
                    if (oldIdpiezarealOfDetalleproductorealListNewDetalleproductoreal != null && !oldIdpiezarealOfDetalleproductorealListNewDetalleproductoreal.equals(piezareal)) {
                        oldIdpiezarealOfDetalleproductorealListNewDetalleproductoreal.getDetalleproductorealList().remove(detalleproductorealListNewDetalleproductoreal);
                        oldIdpiezarealOfDetalleproductorealListNewDetalleproductoreal = em.merge(oldIdpiezarealOfDetalleproductorealListNewDetalleproductoreal);
                    }
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListOldDetalleejecucionplanificacion : detalleejecucionplanificacionListOld) {
                if (!detalleejecucionplanificacionListNew.contains(detalleejecucionplanificacionListOldDetalleejecucionplanificacion)) {
                    detalleejecucionplanificacionListOldDetalleejecucionplanificacion.setPiezareal(null);
                    detalleejecucionplanificacionListOldDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListOldDetalleejecucionplanificacion);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacion : detalleejecucionplanificacionListNew) {
                if (!detalleejecucionplanificacionListOld.contains(detalleejecucionplanificacionListNewDetalleejecucionplanificacion)) {
                    Piezareal oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = detalleejecucionplanificacionListNewDetalleejecucionplanificacion.getPiezareal();
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion.setPiezareal(piezareal);
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    if (oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion != null && !oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.equals(piezareal)) {
                        oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                        oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    }
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListOld) {
                if (!detalleejecucionplanificacioncalidadListNew.contains(detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad)) {
                    detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad.setPiezareal(null);
                    detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListNew) {
                if (!detalleejecucionplanificacioncalidadListOld.contains(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad)) {
                    Piezareal oldPiezarealOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getPiezareal();
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.setPiezareal(piezareal);
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                    if (oldPiezarealOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad != null && !oldPiezarealOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.equals(piezareal)) {
                        oldPiezarealOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                        oldPiezarealOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(oldPiezarealOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                    }
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListOldMpasignadaxpiezareal : mpasignadaxpiezarealListOld) {
                if (!mpasignadaxpiezarealListNew.contains(mpasignadaxpiezarealListOldMpasignadaxpiezareal)) {
                    mpasignadaxpiezarealListOldMpasignadaxpiezareal.setIdpiezareal(null);
                    mpasignadaxpiezarealListOldMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListOldMpasignadaxpiezareal);
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListNewMpasignadaxpiezareal : mpasignadaxpiezarealListNew) {
                if (!mpasignadaxpiezarealListOld.contains(mpasignadaxpiezarealListNewMpasignadaxpiezareal)) {
                    Piezareal oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal = mpasignadaxpiezarealListNewMpasignadaxpiezareal.getIdpiezareal();
                    mpasignadaxpiezarealListNewMpasignadaxpiezareal.setIdpiezareal(piezareal);
                    mpasignadaxpiezarealListNewMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListNewMpasignadaxpiezareal);
                    if (oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal != null && !oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal.equals(piezareal)) {
                        oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal.getMpasignadaxpiezarealList().remove(mpasignadaxpiezarealListNewMpasignadaxpiezareal);
                        oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal = em.merge(oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = piezareal.getIdpiezareal();
                if (findPiezareal(id) == null) {
                    throw new NonexistentEntityException("The piezareal with id " + id + " no longer exists.");
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
            Piezareal piezareal;
            try {
                piezareal = em.getReference(Piezareal.class, id);
                piezareal.getIdpiezareal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The piezareal with id " + id + " no longer exists.", enfe);
            }
            Pieza idpieza = piezareal.getIdpieza();
            if (idpieza != null) {
                idpieza.getPiezarealList().remove(piezareal);
                idpieza = em.merge(idpieza);
            }
            Estadopiezareal estado = piezareal.getEstado();
            if (estado != null) {
                estado.getPiezarealList().remove(piezareal);
                estado = em.merge(estado);
            }
            Codigodebarra idcodigobarra = piezareal.getIdcodigobarra();
            if (idcodigobarra != null) {
                idcodigobarra.getPiezarealList().remove(piezareal);
                idcodigobarra = em.merge(idcodigobarra);
            }
            List<Detalleproductoreal> detalleproductorealList = piezareal.getDetalleproductorealList();
            for (Detalleproductoreal detalleproductorealListDetalleproductoreal : detalleproductorealList) {
                detalleproductorealListDetalleproductoreal.setIdpiezareal(null);
                detalleproductorealListDetalleproductoreal = em.merge(detalleproductorealListDetalleproductoreal);
            }
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionList = piezareal.getDetalleejecucionplanificacionList();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : detalleejecucionplanificacionList) {
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setPiezareal(null);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
            }
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList = piezareal.getDetalleejecucionplanificacioncalidadList();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadList) {
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.setPiezareal(null);
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
            }
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealList = piezareal.getMpasignadaxpiezarealList();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezareal : mpasignadaxpiezarealList) {
                mpasignadaxpiezarealListMpasignadaxpiezareal.setIdpiezareal(null);
                mpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListMpasignadaxpiezareal);
            }
            em.remove(piezareal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Piezareal> findPiezarealEntities() {
        return findPiezarealEntities(true, -1, -1);
    }

    public List<Piezareal> findPiezarealEntities(int maxResults, int firstResult) {
        return findPiezarealEntities(false, maxResults, firstResult);
    }

    private List<Piezareal> findPiezarealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Piezareal.class));
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

    public Piezareal findPiezareal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Piezareal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPiezarealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Piezareal> rt = cq.from(Piezareal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
