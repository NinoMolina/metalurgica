/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Maquina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadomaquina;
import entity.Marca;
import entity.Tipomaquina;
import entity.Unidadmedida;
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class MaquinaJpaController {

    public MaquinaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquina maquina) throws PreexistingEntityException, Exception {
        if (maquina.getDetalleplanificacionproduccionSet() == null) {
            maquina.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        if (maquina.getDetalleplanificacionproduccionSet1() == null) {
            maquina.setDetalleplanificacionproduccionSet1(new HashSet<Detalleplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadomaquina estado = maquina.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                maquina.setEstado(estado);
            }
            Estadomaquina estado1 = maquina.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                maquina.setEstado1(estado1);
            }
            Marca marca = maquina.getMarca();
            if (marca != null) {
                marca = em.getReference(marca.getClass(), marca.getIdmarca());
                maquina.setMarca(marca);
            }
            Marca marca1 = maquina.getMarca1();
            if (marca1 != null) {
                marca1 = em.getReference(marca1.getClass(), marca1.getIdmarca());
                maquina.setMarca1(marca1);
            }
            Tipomaquina tipomaquina = maquina.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina = em.getReference(tipomaquina.getClass(), tipomaquina.getIdtipomaquina());
                maquina.setTipomaquina(tipomaquina);
            }
            Tipomaquina tipomaquina1 = maquina.getTipomaquina1();
            if (tipomaquina1 != null) {
                tipomaquina1 = em.getReference(tipomaquina1.getClass(), tipomaquina1.getIdtipomaquina());
                maquina.setTipomaquina1(tipomaquina1);
            }
            Unidadmedida idunidadmedida = maquina.getIdunidadmedida();
            if (idunidadmedida != null) {
                idunidadmedida = em.getReference(idunidadmedida.getClass(), idunidadmedida.getIdunidadmedida());
                maquina.setIdunidadmedida(idunidadmedida);
            }
            Unidadmedida idunidadmedida1 = maquina.getIdunidadmedida1();
            if (idunidadmedida1 != null) {
                idunidadmedida1 = em.getReference(idunidadmedida1.getClass(), idunidadmedida1.getIdunidadmedida());
                maquina.setIdunidadmedida1(idunidadmedida1);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : maquina.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            maquina.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1 = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach : maquina.getDetalleplanificacionproduccionSet1()) {
                detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1.add(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach);
            }
            maquina.setDetalleplanificacionproduccionSet1(attachedDetalleplanificacionproduccionSet1);
            em.persist(maquina);
            if (estado != null) {
                estado.getMaquinaSet().add(maquina);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getMaquinaSet().add(maquina);
                estado1 = em.merge(estado1);
            }
            if (marca != null) {
                marca.getMaquinaSet().add(maquina);
                marca = em.merge(marca);
            }
            if (marca1 != null) {
                marca1.getMaquinaSet().add(maquina);
                marca1 = em.merge(marca1);
            }
            if (tipomaquina != null) {
                tipomaquina.getMaquinaSet().add(maquina);
                tipomaquina = em.merge(tipomaquina);
            }
            if (tipomaquina1 != null) {
                tipomaquina1.getMaquinaSet().add(maquina);
                tipomaquina1 = em.merge(tipomaquina1);
            }
            if (idunidadmedida != null) {
                idunidadmedida.getMaquinaSet().add(maquina);
                idunidadmedida = em.merge(idunidadmedida);
            }
            if (idunidadmedida1 != null) {
                idunidadmedida1.getMaquinaSet().add(maquina);
                idunidadmedida1 = em.merge(idunidadmedida1);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : maquina.getDetalleplanificacionproduccionSet()) {
                Maquina oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = detalleplanificacionproduccionSetDetalleplanificacionproduccion.getIdmaquina();
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdmaquina(maquina);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                if (oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion != null) {
                    oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                    oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(oldIdmaquinaOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : maquina.getDetalleplanificacionproduccionSet1()) {
                Maquina oldIdmaquina1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = detalleplanificacionproduccionSet1Detalleplanificacionproduccion.getIdmaquina1();
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdmaquina1(maquina);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                if (oldIdmaquina1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion != null) {
                    oldIdmaquina1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                    oldIdmaquina1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(oldIdmaquina1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaquina(maquina.getIdmaquina()) != null) {
                throw new PreexistingEntityException("Maquina " + maquina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maquina maquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquina persistentMaquina = em.find(Maquina.class, maquina.getIdmaquina());
            Estadomaquina estadoOld = persistentMaquina.getEstado();
            Estadomaquina estadoNew = maquina.getEstado();
            Estadomaquina estado1Old = persistentMaquina.getEstado1();
            Estadomaquina estado1New = maquina.getEstado1();
            Marca marcaOld = persistentMaquina.getMarca();
            Marca marcaNew = maquina.getMarca();
            Marca marca1Old = persistentMaquina.getMarca1();
            Marca marca1New = maquina.getMarca1();
            Tipomaquina tipomaquinaOld = persistentMaquina.getTipomaquina();
            Tipomaquina tipomaquinaNew = maquina.getTipomaquina();
            Tipomaquina tipomaquina1Old = persistentMaquina.getTipomaquina1();
            Tipomaquina tipomaquina1New = maquina.getTipomaquina1();
            Unidadmedida idunidadmedidaOld = persistentMaquina.getIdunidadmedida();
            Unidadmedida idunidadmedidaNew = maquina.getIdunidadmedida();
            Unidadmedida idunidadmedida1Old = persistentMaquina.getIdunidadmedida1();
            Unidadmedida idunidadmedida1New = maquina.getIdunidadmedida1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentMaquina.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = maquina.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1Old = persistentMaquina.getDetalleplanificacionproduccionSet1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1New = maquina.getDetalleplanificacionproduccionSet1();
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                maquina.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                maquina.setEstado1(estado1New);
            }
            if (marcaNew != null) {
                marcaNew = em.getReference(marcaNew.getClass(), marcaNew.getIdmarca());
                maquina.setMarca(marcaNew);
            }
            if (marca1New != null) {
                marca1New = em.getReference(marca1New.getClass(), marca1New.getIdmarca());
                maquina.setMarca1(marca1New);
            }
            if (tipomaquinaNew != null) {
                tipomaquinaNew = em.getReference(tipomaquinaNew.getClass(), tipomaquinaNew.getIdtipomaquina());
                maquina.setTipomaquina(tipomaquinaNew);
            }
            if (tipomaquina1New != null) {
                tipomaquina1New = em.getReference(tipomaquina1New.getClass(), tipomaquina1New.getIdtipomaquina());
                maquina.setTipomaquina1(tipomaquina1New);
            }
            if (idunidadmedidaNew != null) {
                idunidadmedidaNew = em.getReference(idunidadmedidaNew.getClass(), idunidadmedidaNew.getIdunidadmedida());
                maquina.setIdunidadmedida(idunidadmedidaNew);
            }
            if (idunidadmedida1New != null) {
                idunidadmedida1New = em.getReference(idunidadmedida1New.getClass(), idunidadmedida1New.getIdunidadmedida());
                maquina.setIdunidadmedida1(idunidadmedida1New);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            maquina.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1New = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSet1New) {
                detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1New.add(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSet1New = attachedDetalleplanificacionproduccionSet1New;
            maquina.setDetalleplanificacionproduccionSet1(detalleplanificacionproduccionSet1New);
            maquina = em.merge(maquina);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getMaquinaSet().remove(maquina);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getMaquinaSet().add(maquina);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getMaquinaSet().remove(maquina);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getMaquinaSet().add(maquina);
                estado1New = em.merge(estado1New);
            }
            if (marcaOld != null && !marcaOld.equals(marcaNew)) {
                marcaOld.getMaquinaSet().remove(maquina);
                marcaOld = em.merge(marcaOld);
            }
            if (marcaNew != null && !marcaNew.equals(marcaOld)) {
                marcaNew.getMaquinaSet().add(maquina);
                marcaNew = em.merge(marcaNew);
            }
            if (marca1Old != null && !marca1Old.equals(marca1New)) {
                marca1Old.getMaquinaSet().remove(maquina);
                marca1Old = em.merge(marca1Old);
            }
            if (marca1New != null && !marca1New.equals(marca1Old)) {
                marca1New.getMaquinaSet().add(maquina);
                marca1New = em.merge(marca1New);
            }
            if (tipomaquinaOld != null && !tipomaquinaOld.equals(tipomaquinaNew)) {
                tipomaquinaOld.getMaquinaSet().remove(maquina);
                tipomaquinaOld = em.merge(tipomaquinaOld);
            }
            if (tipomaquinaNew != null && !tipomaquinaNew.equals(tipomaquinaOld)) {
                tipomaquinaNew.getMaquinaSet().add(maquina);
                tipomaquinaNew = em.merge(tipomaquinaNew);
            }
            if (tipomaquina1Old != null && !tipomaquina1Old.equals(tipomaquina1New)) {
                tipomaquina1Old.getMaquinaSet().remove(maquina);
                tipomaquina1Old = em.merge(tipomaquina1Old);
            }
            if (tipomaquina1New != null && !tipomaquina1New.equals(tipomaquina1Old)) {
                tipomaquina1New.getMaquinaSet().add(maquina);
                tipomaquina1New = em.merge(tipomaquina1New);
            }
            if (idunidadmedidaOld != null && !idunidadmedidaOld.equals(idunidadmedidaNew)) {
                idunidadmedidaOld.getMaquinaSet().remove(maquina);
                idunidadmedidaOld = em.merge(idunidadmedidaOld);
            }
            if (idunidadmedidaNew != null && !idunidadmedidaNew.equals(idunidadmedidaOld)) {
                idunidadmedidaNew.getMaquinaSet().add(maquina);
                idunidadmedidaNew = em.merge(idunidadmedidaNew);
            }
            if (idunidadmedida1Old != null && !idunidadmedida1Old.equals(idunidadmedida1New)) {
                idunidadmedida1Old.getMaquinaSet().remove(maquina);
                idunidadmedida1Old = em.merge(idunidadmedida1Old);
            }
            if (idunidadmedida1New != null && !idunidadmedida1New.equals(idunidadmedida1Old)) {
                idunidadmedida1New.getMaquinaSet().add(maquina);
                idunidadmedida1New = em.merge(idunidadmedida1New);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion.setIdmaquina(null);
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccion : detalleplanificacionproduccionSetNew) {
                if (!detalleplanificacionproduccionSetOld.contains(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion)) {
                    Maquina oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getIdmaquina();
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.setIdmaquina(maquina);
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    if (oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion != null && !oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.equals(maquina)) {
                        oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                        oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(oldIdmaquinaOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion : detalleplanificacionproduccionSet1Old) {
                if (!detalleplanificacionproduccionSet1New.contains(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion.setIdmaquina1(null);
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion : detalleplanificacionproduccionSet1New) {
                if (!detalleplanificacionproduccionSet1Old.contains(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion)) {
                    Maquina oldIdmaquina1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getIdmaquina1();
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.setIdmaquina1(maquina);
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    if (oldIdmaquina1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion != null && !oldIdmaquina1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.equals(maquina)) {
                        oldIdmaquina1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                        oldIdmaquina1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(oldIdmaquina1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = maquina.getIdmaquina();
                if (findMaquina(id) == null) {
                    throw new NonexistentEntityException("The maquina with id " + id + " no longer exists.");
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
            Maquina maquina;
            try {
                maquina = em.getReference(Maquina.class, id);
                maquina.getIdmaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maquina with id " + id + " no longer exists.", enfe);
            }
            Estadomaquina estado = maquina.getEstado();
            if (estado != null) {
                estado.getMaquinaSet().remove(maquina);
                estado = em.merge(estado);
            }
            Estadomaquina estado1 = maquina.getEstado1();
            if (estado1 != null) {
                estado1.getMaquinaSet().remove(maquina);
                estado1 = em.merge(estado1);
            }
            Marca marca = maquina.getMarca();
            if (marca != null) {
                marca.getMaquinaSet().remove(maquina);
                marca = em.merge(marca);
            }
            Marca marca1 = maquina.getMarca1();
            if (marca1 != null) {
                marca1.getMaquinaSet().remove(maquina);
                marca1 = em.merge(marca1);
            }
            Tipomaquina tipomaquina = maquina.getTipomaquina();
            if (tipomaquina != null) {
                tipomaquina.getMaquinaSet().remove(maquina);
                tipomaquina = em.merge(tipomaquina);
            }
            Tipomaquina tipomaquina1 = maquina.getTipomaquina1();
            if (tipomaquina1 != null) {
                tipomaquina1.getMaquinaSet().remove(maquina);
                tipomaquina1 = em.merge(tipomaquina1);
            }
            Unidadmedida idunidadmedida = maquina.getIdunidadmedida();
            if (idunidadmedida != null) {
                idunidadmedida.getMaquinaSet().remove(maquina);
                idunidadmedida = em.merge(idunidadmedida);
            }
            Unidadmedida idunidadmedida1 = maquina.getIdunidadmedida1();
            if (idunidadmedida1 != null) {
                idunidadmedida1.getMaquinaSet().remove(maquina);
                idunidadmedida1 = em.merge(idunidadmedida1);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = maquina.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdmaquina(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1 = maquina.getDetalleplanificacionproduccionSet1();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : detalleplanificacionproduccionSet1) {
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdmaquina1(null);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
            }
            em.remove(maquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maquina> findMaquinaEntities() {
        return findMaquinaEntities(true, -1, -1);
    }

    public List<Maquina> findMaquinaEntities(int maxResults, int firstResult) {
        return findMaquinaEntities(false, maxResults, firstResult);
    }

    private List<Maquina> findMaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maquina.class));
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

    public Maquina findMaquina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maquina> rt = cq.from(Maquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
