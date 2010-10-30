/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Unidadmedida;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import java.util.HashSet;
import java.util.Set;
import entity.Pieza;
import entity.Maquina;
import entity.Etapadeproduccion;

/**
 *
 * @author Nino
 */
public class UnidadmedidaJpaController {

    public UnidadmedidaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Unidadmedida unidadmedida) throws PreexistingEntityException, Exception {
        if (unidadmedida.getMateriaprimaSet() == null) {
            unidadmedida.setMateriaprimaSet(new HashSet<Materiaprima>());
        }
        if (unidadmedida.getMateriaprimaSet1() == null) {
            unidadmedida.setMateriaprimaSet1(new HashSet<Materiaprima>());
        }
        if (unidadmedida.getPiezaSet() == null) {
            unidadmedida.setPiezaSet(new HashSet<Pieza>());
        }
        if (unidadmedida.getPiezaSet1() == null) {
            unidadmedida.setPiezaSet1(new HashSet<Pieza>());
        }
        if (unidadmedida.getMaquinaSet() == null) {
            unidadmedida.setMaquinaSet(new HashSet<Maquina>());
        }
        if (unidadmedida.getMaquinaSet1() == null) {
            unidadmedida.setMaquinaSet1(new HashSet<Maquina>());
        }
        if (unidadmedida.getEtapadeproduccionSet() == null) {
            unidadmedida.setEtapadeproduccionSet(new HashSet<Etapadeproduccion>());
        }
        if (unidadmedida.getEtapadeproduccionSet1() == null) {
            unidadmedida.setEtapadeproduccionSet1(new HashSet<Etapadeproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Materiaprima> attachedMateriaprimaSet = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetMateriaprimaToAttach : unidadmedida.getMateriaprimaSet()) {
                materiaprimaSetMateriaprimaToAttach = em.getReference(materiaprimaSetMateriaprimaToAttach.getClass(), materiaprimaSetMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet.add(materiaprimaSetMateriaprimaToAttach);
            }
            unidadmedida.setMateriaprimaSet(attachedMateriaprimaSet);
            Set<Materiaprima> attachedMateriaprimaSet1 = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSet1MateriaprimaToAttach : unidadmedida.getMateriaprimaSet1()) {
                materiaprimaSet1MateriaprimaToAttach = em.getReference(materiaprimaSet1MateriaprimaToAttach.getClass(), materiaprimaSet1MateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet1.add(materiaprimaSet1MateriaprimaToAttach);
            }
            unidadmedida.setMateriaprimaSet1(attachedMateriaprimaSet1);
            Set<Pieza> attachedPiezaSet = new HashSet<Pieza>();
            for (Pieza piezaSetPiezaToAttach : unidadmedida.getPiezaSet()) {
                piezaSetPiezaToAttach = em.getReference(piezaSetPiezaToAttach.getClass(), piezaSetPiezaToAttach.getIdpieza());
                attachedPiezaSet.add(piezaSetPiezaToAttach);
            }
            unidadmedida.setPiezaSet(attachedPiezaSet);
            Set<Pieza> attachedPiezaSet1 = new HashSet<Pieza>();
            for (Pieza piezaSet1PiezaToAttach : unidadmedida.getPiezaSet1()) {
                piezaSet1PiezaToAttach = em.getReference(piezaSet1PiezaToAttach.getClass(), piezaSet1PiezaToAttach.getIdpieza());
                attachedPiezaSet1.add(piezaSet1PiezaToAttach);
            }
            unidadmedida.setPiezaSet1(attachedPiezaSet1);
            Set<Maquina> attachedMaquinaSet = new HashSet<Maquina>();
            for (Maquina maquinaSetMaquinaToAttach : unidadmedida.getMaquinaSet()) {
                maquinaSetMaquinaToAttach = em.getReference(maquinaSetMaquinaToAttach.getClass(), maquinaSetMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet.add(maquinaSetMaquinaToAttach);
            }
            unidadmedida.setMaquinaSet(attachedMaquinaSet);
            Set<Maquina> attachedMaquinaSet1 = new HashSet<Maquina>();
            for (Maquina maquinaSet1MaquinaToAttach : unidadmedida.getMaquinaSet1()) {
                maquinaSet1MaquinaToAttach = em.getReference(maquinaSet1MaquinaToAttach.getClass(), maquinaSet1MaquinaToAttach.getIdmaquina());
                attachedMaquinaSet1.add(maquinaSet1MaquinaToAttach);
            }
            unidadmedida.setMaquinaSet1(attachedMaquinaSet1);
            Set<Etapadeproduccion> attachedEtapadeproduccionSet = new HashSet<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionSetEtapadeproduccionToAttach : unidadmedida.getEtapadeproduccionSet()) {
                etapadeproduccionSetEtapadeproduccionToAttach = em.getReference(etapadeproduccionSetEtapadeproduccionToAttach.getClass(), etapadeproduccionSetEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionSet.add(etapadeproduccionSetEtapadeproduccionToAttach);
            }
            unidadmedida.setEtapadeproduccionSet(attachedEtapadeproduccionSet);
            Set<Etapadeproduccion> attachedEtapadeproduccionSet1 = new HashSet<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionSet1EtapadeproduccionToAttach : unidadmedida.getEtapadeproduccionSet1()) {
                etapadeproduccionSet1EtapadeproduccionToAttach = em.getReference(etapadeproduccionSet1EtapadeproduccionToAttach.getClass(), etapadeproduccionSet1EtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionSet1.add(etapadeproduccionSet1EtapadeproduccionToAttach);
            }
            unidadmedida.setEtapadeproduccionSet1(attachedEtapadeproduccionSet1);
            em.persist(unidadmedida);
            for (Materiaprima materiaprimaSetMateriaprima : unidadmedida.getMateriaprimaSet()) {
                Unidadmedida oldUnidadmedidaOfMateriaprimaSetMateriaprima = materiaprimaSetMateriaprima.getUnidadmedida();
                materiaprimaSetMateriaprima.setUnidadmedida(unidadmedida);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
                if (oldUnidadmedidaOfMateriaprimaSetMateriaprima != null) {
                    oldUnidadmedidaOfMateriaprimaSetMateriaprima.getMateriaprimaSet().remove(materiaprimaSetMateriaprima);
                    oldUnidadmedidaOfMateriaprimaSetMateriaprima = em.merge(oldUnidadmedidaOfMateriaprimaSetMateriaprima);
                }
            }
            for (Materiaprima materiaprimaSet1Materiaprima : unidadmedida.getMateriaprimaSet1()) {
                Unidadmedida oldUnidadmedida1OfMateriaprimaSet1Materiaprima = materiaprimaSet1Materiaprima.getUnidadmedida1();
                materiaprimaSet1Materiaprima.setUnidadmedida1(unidadmedida);
                materiaprimaSet1Materiaprima = em.merge(materiaprimaSet1Materiaprima);
                if (oldUnidadmedida1OfMateriaprimaSet1Materiaprima != null) {
                    oldUnidadmedida1OfMateriaprimaSet1Materiaprima.getMateriaprimaSet1().remove(materiaprimaSet1Materiaprima);
                    oldUnidadmedida1OfMateriaprimaSet1Materiaprima = em.merge(oldUnidadmedida1OfMateriaprimaSet1Materiaprima);
                }
            }
            for (Pieza piezaSetPieza : unidadmedida.getPiezaSet()) {
                Unidadmedida oldUnidadmedidaOfPiezaSetPieza = piezaSetPieza.getUnidadmedida();
                piezaSetPieza.setUnidadmedida(unidadmedida);
                piezaSetPieza = em.merge(piezaSetPieza);
                if (oldUnidadmedidaOfPiezaSetPieza != null) {
                    oldUnidadmedidaOfPiezaSetPieza.getPiezaSet().remove(piezaSetPieza);
                    oldUnidadmedidaOfPiezaSetPieza = em.merge(oldUnidadmedidaOfPiezaSetPieza);
                }
            }
            for (Pieza piezaSet1Pieza : unidadmedida.getPiezaSet1()) {
                Unidadmedida oldUnidadmedida1OfPiezaSet1Pieza = piezaSet1Pieza.getUnidadmedida1();
                piezaSet1Pieza.setUnidadmedida1(unidadmedida);
                piezaSet1Pieza = em.merge(piezaSet1Pieza);
                if (oldUnidadmedida1OfPiezaSet1Pieza != null) {
                    oldUnidadmedida1OfPiezaSet1Pieza.getPiezaSet1().remove(piezaSet1Pieza);
                    oldUnidadmedida1OfPiezaSet1Pieza = em.merge(oldUnidadmedida1OfPiezaSet1Pieza);
                }
            }
            for (Maquina maquinaSetMaquina : unidadmedida.getMaquinaSet()) {
                Unidadmedida oldIdunidadmedidaOfMaquinaSetMaquina = maquinaSetMaquina.getIdunidadmedida();
                maquinaSetMaquina.setIdunidadmedida(unidadmedida);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
                if (oldIdunidadmedidaOfMaquinaSetMaquina != null) {
                    oldIdunidadmedidaOfMaquinaSetMaquina.getMaquinaSet().remove(maquinaSetMaquina);
                    oldIdunidadmedidaOfMaquinaSetMaquina = em.merge(oldIdunidadmedidaOfMaquinaSetMaquina);
                }
            }
            for (Maquina maquinaSet1Maquina : unidadmedida.getMaquinaSet1()) {
                Unidadmedida oldIdunidadmedida1OfMaquinaSet1Maquina = maquinaSet1Maquina.getIdunidadmedida1();
                maquinaSet1Maquina.setIdunidadmedida1(unidadmedida);
                maquinaSet1Maquina = em.merge(maquinaSet1Maquina);
                if (oldIdunidadmedida1OfMaquinaSet1Maquina != null) {
                    oldIdunidadmedida1OfMaquinaSet1Maquina.getMaquinaSet1().remove(maquinaSet1Maquina);
                    oldIdunidadmedida1OfMaquinaSet1Maquina = em.merge(oldIdunidadmedida1OfMaquinaSet1Maquina);
                }
            }
            for (Etapadeproduccion etapadeproduccionSetEtapadeproduccion : unidadmedida.getEtapadeproduccionSet()) {
                Unidadmedida oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion = etapadeproduccionSetEtapadeproduccion.getUnidaddemedida();
                etapadeproduccionSetEtapadeproduccion.setUnidaddemedida(unidadmedida);
                etapadeproduccionSetEtapadeproduccion = em.merge(etapadeproduccionSetEtapadeproduccion);
                if (oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion != null) {
                    oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion.getEtapadeproduccionSet().remove(etapadeproduccionSetEtapadeproduccion);
                    oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion = em.merge(oldUnidaddemedidaOfEtapadeproduccionSetEtapadeproduccion);
                }
            }
            for (Etapadeproduccion etapadeproduccionSet1Etapadeproduccion : unidadmedida.getEtapadeproduccionSet1()) {
                Unidadmedida oldUnidaddemedida1OfEtapadeproduccionSet1Etapadeproduccion = etapadeproduccionSet1Etapadeproduccion.getUnidaddemedida1();
                etapadeproduccionSet1Etapadeproduccion.setUnidaddemedida1(unidadmedida);
                etapadeproduccionSet1Etapadeproduccion = em.merge(etapadeproduccionSet1Etapadeproduccion);
                if (oldUnidaddemedida1OfEtapadeproduccionSet1Etapadeproduccion != null) {
                    oldUnidaddemedida1OfEtapadeproduccionSet1Etapadeproduccion.getEtapadeproduccionSet1().remove(etapadeproduccionSet1Etapadeproduccion);
                    oldUnidaddemedida1OfEtapadeproduccionSet1Etapadeproduccion = em.merge(oldUnidaddemedida1OfEtapadeproduccionSet1Etapadeproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUnidadmedida(unidadmedida.getIdunidadmedida()) != null) {
                throw new PreexistingEntityException("Unidadmedida " + unidadmedida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Unidadmedida unidadmedida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidadmedida persistentUnidadmedida = em.find(Unidadmedida.class, unidadmedida.getIdunidadmedida());
            Set<Materiaprima> materiaprimaSetOld = persistentUnidadmedida.getMateriaprimaSet();
            Set<Materiaprima> materiaprimaSetNew = unidadmedida.getMateriaprimaSet();
            Set<Materiaprima> materiaprimaSet1Old = persistentUnidadmedida.getMateriaprimaSet1();
            Set<Materiaprima> materiaprimaSet1New = unidadmedida.getMateriaprimaSet1();
            Set<Pieza> piezaSetOld = persistentUnidadmedida.getPiezaSet();
            Set<Pieza> piezaSetNew = unidadmedida.getPiezaSet();
            Set<Pieza> piezaSet1Old = persistentUnidadmedida.getPiezaSet1();
            Set<Pieza> piezaSet1New = unidadmedida.getPiezaSet1();
            Set<Maquina> maquinaSetOld = persistentUnidadmedida.getMaquinaSet();
            Set<Maquina> maquinaSetNew = unidadmedida.getMaquinaSet();
            Set<Maquina> maquinaSet1Old = persistentUnidadmedida.getMaquinaSet1();
            Set<Maquina> maquinaSet1New = unidadmedida.getMaquinaSet1();
            Set<Etapadeproduccion> etapadeproduccionSetOld = persistentUnidadmedida.getEtapadeproduccionSet();
            Set<Etapadeproduccion> etapadeproduccionSetNew = unidadmedida.getEtapadeproduccionSet();
            Set<Etapadeproduccion> etapadeproduccionSet1Old = persistentUnidadmedida.getEtapadeproduccionSet1();
            Set<Etapadeproduccion> etapadeproduccionSet1New = unidadmedida.getEtapadeproduccionSet1();
            Set<Materiaprima> attachedMateriaprimaSetNew = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSetNewMateriaprimaToAttach : materiaprimaSetNew) {
                materiaprimaSetNewMateriaprimaToAttach = em.getReference(materiaprimaSetNewMateriaprimaToAttach.getClass(), materiaprimaSetNewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSetNew.add(materiaprimaSetNewMateriaprimaToAttach);
            }
            materiaprimaSetNew = attachedMateriaprimaSetNew;
            unidadmedida.setMateriaprimaSet(materiaprimaSetNew);
            Set<Materiaprima> attachedMateriaprimaSet1New = new HashSet<Materiaprima>();
            for (Materiaprima materiaprimaSet1NewMateriaprimaToAttach : materiaprimaSet1New) {
                materiaprimaSet1NewMateriaprimaToAttach = em.getReference(materiaprimaSet1NewMateriaprimaToAttach.getClass(), materiaprimaSet1NewMateriaprimaToAttach.getIdmateriaprima());
                attachedMateriaprimaSet1New.add(materiaprimaSet1NewMateriaprimaToAttach);
            }
            materiaprimaSet1New = attachedMateriaprimaSet1New;
            unidadmedida.setMateriaprimaSet1(materiaprimaSet1New);
            Set<Pieza> attachedPiezaSetNew = new HashSet<Pieza>();
            for (Pieza piezaSetNewPiezaToAttach : piezaSetNew) {
                piezaSetNewPiezaToAttach = em.getReference(piezaSetNewPiezaToAttach.getClass(), piezaSetNewPiezaToAttach.getIdpieza());
                attachedPiezaSetNew.add(piezaSetNewPiezaToAttach);
            }
            piezaSetNew = attachedPiezaSetNew;
            unidadmedida.setPiezaSet(piezaSetNew);
            Set<Pieza> attachedPiezaSet1New = new HashSet<Pieza>();
            for (Pieza piezaSet1NewPiezaToAttach : piezaSet1New) {
                piezaSet1NewPiezaToAttach = em.getReference(piezaSet1NewPiezaToAttach.getClass(), piezaSet1NewPiezaToAttach.getIdpieza());
                attachedPiezaSet1New.add(piezaSet1NewPiezaToAttach);
            }
            piezaSet1New = attachedPiezaSet1New;
            unidadmedida.setPiezaSet1(piezaSet1New);
            Set<Maquina> attachedMaquinaSetNew = new HashSet<Maquina>();
            for (Maquina maquinaSetNewMaquinaToAttach : maquinaSetNew) {
                maquinaSetNewMaquinaToAttach = em.getReference(maquinaSetNewMaquinaToAttach.getClass(), maquinaSetNewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSetNew.add(maquinaSetNewMaquinaToAttach);
            }
            maquinaSetNew = attachedMaquinaSetNew;
            unidadmedida.setMaquinaSet(maquinaSetNew);
            Set<Maquina> attachedMaquinaSet1New = new HashSet<Maquina>();
            for (Maquina maquinaSet1NewMaquinaToAttach : maquinaSet1New) {
                maquinaSet1NewMaquinaToAttach = em.getReference(maquinaSet1NewMaquinaToAttach.getClass(), maquinaSet1NewMaquinaToAttach.getIdmaquina());
                attachedMaquinaSet1New.add(maquinaSet1NewMaquinaToAttach);
            }
            maquinaSet1New = attachedMaquinaSet1New;
            unidadmedida.setMaquinaSet1(maquinaSet1New);
            Set<Etapadeproduccion> attachedEtapadeproduccionSetNew = new HashSet<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionSetNewEtapadeproduccionToAttach : etapadeproduccionSetNew) {
                etapadeproduccionSetNewEtapadeproduccionToAttach = em.getReference(etapadeproduccionSetNewEtapadeproduccionToAttach.getClass(), etapadeproduccionSetNewEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionSetNew.add(etapadeproduccionSetNewEtapadeproduccionToAttach);
            }
            etapadeproduccionSetNew = attachedEtapadeproduccionSetNew;
            unidadmedida.setEtapadeproduccionSet(etapadeproduccionSetNew);
            Set<Etapadeproduccion> attachedEtapadeproduccionSet1New = new HashSet<Etapadeproduccion>();
            for (Etapadeproduccion etapadeproduccionSet1NewEtapadeproduccionToAttach : etapadeproduccionSet1New) {
                etapadeproduccionSet1NewEtapadeproduccionToAttach = em.getReference(etapadeproduccionSet1NewEtapadeproduccionToAttach.getClass(), etapadeproduccionSet1NewEtapadeproduccionToAttach.getIdetapaproduccion());
                attachedEtapadeproduccionSet1New.add(etapadeproduccionSet1NewEtapadeproduccionToAttach);
            }
            etapadeproduccionSet1New = attachedEtapadeproduccionSet1New;
            unidadmedida.setEtapadeproduccionSet1(etapadeproduccionSet1New);
            unidadmedida = em.merge(unidadmedida);
            for (Materiaprima materiaprimaSetOldMateriaprima : materiaprimaSetOld) {
                if (!materiaprimaSetNew.contains(materiaprimaSetOldMateriaprima)) {
                    materiaprimaSetOldMateriaprima.setUnidadmedida(null);
                    materiaprimaSetOldMateriaprima = em.merge(materiaprimaSetOldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaSetNewMateriaprima : materiaprimaSetNew) {
                if (!materiaprimaSetOld.contains(materiaprimaSetNewMateriaprima)) {
                    Unidadmedida oldUnidadmedidaOfMateriaprimaSetNewMateriaprima = materiaprimaSetNewMateriaprima.getUnidadmedida();
                    materiaprimaSetNewMateriaprima.setUnidadmedida(unidadmedida);
                    materiaprimaSetNewMateriaprima = em.merge(materiaprimaSetNewMateriaprima);
                    if (oldUnidadmedidaOfMateriaprimaSetNewMateriaprima != null && !oldUnidadmedidaOfMateriaprimaSetNewMateriaprima.equals(unidadmedida)) {
                        oldUnidadmedidaOfMateriaprimaSetNewMateriaprima.getMateriaprimaSet().remove(materiaprimaSetNewMateriaprima);
                        oldUnidadmedidaOfMateriaprimaSetNewMateriaprima = em.merge(oldUnidadmedidaOfMateriaprimaSetNewMateriaprima);
                    }
                }
            }
            for (Materiaprima materiaprimaSet1OldMateriaprima : materiaprimaSet1Old) {
                if (!materiaprimaSet1New.contains(materiaprimaSet1OldMateriaprima)) {
                    materiaprimaSet1OldMateriaprima.setUnidadmedida1(null);
                    materiaprimaSet1OldMateriaprima = em.merge(materiaprimaSet1OldMateriaprima);
                }
            }
            for (Materiaprima materiaprimaSet1NewMateriaprima : materiaprimaSet1New) {
                if (!materiaprimaSet1Old.contains(materiaprimaSet1NewMateriaprima)) {
                    Unidadmedida oldUnidadmedida1OfMateriaprimaSet1NewMateriaprima = materiaprimaSet1NewMateriaprima.getUnidadmedida1();
                    materiaprimaSet1NewMateriaprima.setUnidadmedida1(unidadmedida);
                    materiaprimaSet1NewMateriaprima = em.merge(materiaprimaSet1NewMateriaprima);
                    if (oldUnidadmedida1OfMateriaprimaSet1NewMateriaprima != null && !oldUnidadmedida1OfMateriaprimaSet1NewMateriaprima.equals(unidadmedida)) {
                        oldUnidadmedida1OfMateriaprimaSet1NewMateriaprima.getMateriaprimaSet1().remove(materiaprimaSet1NewMateriaprima);
                        oldUnidadmedida1OfMateriaprimaSet1NewMateriaprima = em.merge(oldUnidadmedida1OfMateriaprimaSet1NewMateriaprima);
                    }
                }
            }
            for (Pieza piezaSetOldPieza : piezaSetOld) {
                if (!piezaSetNew.contains(piezaSetOldPieza)) {
                    piezaSetOldPieza.setUnidadmedida(null);
                    piezaSetOldPieza = em.merge(piezaSetOldPieza);
                }
            }
            for (Pieza piezaSetNewPieza : piezaSetNew) {
                if (!piezaSetOld.contains(piezaSetNewPieza)) {
                    Unidadmedida oldUnidadmedidaOfPiezaSetNewPieza = piezaSetNewPieza.getUnidadmedida();
                    piezaSetNewPieza.setUnidadmedida(unidadmedida);
                    piezaSetNewPieza = em.merge(piezaSetNewPieza);
                    if (oldUnidadmedidaOfPiezaSetNewPieza != null && !oldUnidadmedidaOfPiezaSetNewPieza.equals(unidadmedida)) {
                        oldUnidadmedidaOfPiezaSetNewPieza.getPiezaSet().remove(piezaSetNewPieza);
                        oldUnidadmedidaOfPiezaSetNewPieza = em.merge(oldUnidadmedidaOfPiezaSetNewPieza);
                    }
                }
            }
            for (Pieza piezaSet1OldPieza : piezaSet1Old) {
                if (!piezaSet1New.contains(piezaSet1OldPieza)) {
                    piezaSet1OldPieza.setUnidadmedida1(null);
                    piezaSet1OldPieza = em.merge(piezaSet1OldPieza);
                }
            }
            for (Pieza piezaSet1NewPieza : piezaSet1New) {
                if (!piezaSet1Old.contains(piezaSet1NewPieza)) {
                    Unidadmedida oldUnidadmedida1OfPiezaSet1NewPieza = piezaSet1NewPieza.getUnidadmedida1();
                    piezaSet1NewPieza.setUnidadmedida1(unidadmedida);
                    piezaSet1NewPieza = em.merge(piezaSet1NewPieza);
                    if (oldUnidadmedida1OfPiezaSet1NewPieza != null && !oldUnidadmedida1OfPiezaSet1NewPieza.equals(unidadmedida)) {
                        oldUnidadmedida1OfPiezaSet1NewPieza.getPiezaSet1().remove(piezaSet1NewPieza);
                        oldUnidadmedida1OfPiezaSet1NewPieza = em.merge(oldUnidadmedida1OfPiezaSet1NewPieza);
                    }
                }
            }
            for (Maquina maquinaSetOldMaquina : maquinaSetOld) {
                if (!maquinaSetNew.contains(maquinaSetOldMaquina)) {
                    maquinaSetOldMaquina.setIdunidadmedida(null);
                    maquinaSetOldMaquina = em.merge(maquinaSetOldMaquina);
                }
            }
            for (Maquina maquinaSetNewMaquina : maquinaSetNew) {
                if (!maquinaSetOld.contains(maquinaSetNewMaquina)) {
                    Unidadmedida oldIdunidadmedidaOfMaquinaSetNewMaquina = maquinaSetNewMaquina.getIdunidadmedida();
                    maquinaSetNewMaquina.setIdunidadmedida(unidadmedida);
                    maquinaSetNewMaquina = em.merge(maquinaSetNewMaquina);
                    if (oldIdunidadmedidaOfMaquinaSetNewMaquina != null && !oldIdunidadmedidaOfMaquinaSetNewMaquina.equals(unidadmedida)) {
                        oldIdunidadmedidaOfMaquinaSetNewMaquina.getMaquinaSet().remove(maquinaSetNewMaquina);
                        oldIdunidadmedidaOfMaquinaSetNewMaquina = em.merge(oldIdunidadmedidaOfMaquinaSetNewMaquina);
                    }
                }
            }
            for (Maquina maquinaSet1OldMaquina : maquinaSet1Old) {
                if (!maquinaSet1New.contains(maquinaSet1OldMaquina)) {
                    maquinaSet1OldMaquina.setIdunidadmedida1(null);
                    maquinaSet1OldMaquina = em.merge(maquinaSet1OldMaquina);
                }
            }
            for (Maquina maquinaSet1NewMaquina : maquinaSet1New) {
                if (!maquinaSet1Old.contains(maquinaSet1NewMaquina)) {
                    Unidadmedida oldIdunidadmedida1OfMaquinaSet1NewMaquina = maquinaSet1NewMaquina.getIdunidadmedida1();
                    maquinaSet1NewMaquina.setIdunidadmedida1(unidadmedida);
                    maquinaSet1NewMaquina = em.merge(maquinaSet1NewMaquina);
                    if (oldIdunidadmedida1OfMaquinaSet1NewMaquina != null && !oldIdunidadmedida1OfMaquinaSet1NewMaquina.equals(unidadmedida)) {
                        oldIdunidadmedida1OfMaquinaSet1NewMaquina.getMaquinaSet1().remove(maquinaSet1NewMaquina);
                        oldIdunidadmedida1OfMaquinaSet1NewMaquina = em.merge(oldIdunidadmedida1OfMaquinaSet1NewMaquina);
                    }
                }
            }
            for (Etapadeproduccion etapadeproduccionSetOldEtapadeproduccion : etapadeproduccionSetOld) {
                if (!etapadeproduccionSetNew.contains(etapadeproduccionSetOldEtapadeproduccion)) {
                    etapadeproduccionSetOldEtapadeproduccion.setUnidaddemedida(null);
                    etapadeproduccionSetOldEtapadeproduccion = em.merge(etapadeproduccionSetOldEtapadeproduccion);
                }
            }
            for (Etapadeproduccion etapadeproduccionSetNewEtapadeproduccion : etapadeproduccionSetNew) {
                if (!etapadeproduccionSetOld.contains(etapadeproduccionSetNewEtapadeproduccion)) {
                    Unidadmedida oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion = etapadeproduccionSetNewEtapadeproduccion.getUnidaddemedida();
                    etapadeproduccionSetNewEtapadeproduccion.setUnidaddemedida(unidadmedida);
                    etapadeproduccionSetNewEtapadeproduccion = em.merge(etapadeproduccionSetNewEtapadeproduccion);
                    if (oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion != null && !oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion.equals(unidadmedida)) {
                        oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion.getEtapadeproduccionSet().remove(etapadeproduccionSetNewEtapadeproduccion);
                        oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion = em.merge(oldUnidaddemedidaOfEtapadeproduccionSetNewEtapadeproduccion);
                    }
                }
            }
            for (Etapadeproduccion etapadeproduccionSet1OldEtapadeproduccion : etapadeproduccionSet1Old) {
                if (!etapadeproduccionSet1New.contains(etapadeproduccionSet1OldEtapadeproduccion)) {
                    etapadeproduccionSet1OldEtapadeproduccion.setUnidaddemedida1(null);
                    etapadeproduccionSet1OldEtapadeproduccion = em.merge(etapadeproduccionSet1OldEtapadeproduccion);
                }
            }
            for (Etapadeproduccion etapadeproduccionSet1NewEtapadeproduccion : etapadeproduccionSet1New) {
                if (!etapadeproduccionSet1Old.contains(etapadeproduccionSet1NewEtapadeproduccion)) {
                    Unidadmedida oldUnidaddemedida1OfEtapadeproduccionSet1NewEtapadeproduccion = etapadeproduccionSet1NewEtapadeproduccion.getUnidaddemedida1();
                    etapadeproduccionSet1NewEtapadeproduccion.setUnidaddemedida1(unidadmedida);
                    etapadeproduccionSet1NewEtapadeproduccion = em.merge(etapadeproduccionSet1NewEtapadeproduccion);
                    if (oldUnidaddemedida1OfEtapadeproduccionSet1NewEtapadeproduccion != null && !oldUnidaddemedida1OfEtapadeproduccionSet1NewEtapadeproduccion.equals(unidadmedida)) {
                        oldUnidaddemedida1OfEtapadeproduccionSet1NewEtapadeproduccion.getEtapadeproduccionSet1().remove(etapadeproduccionSet1NewEtapadeproduccion);
                        oldUnidaddemedida1OfEtapadeproduccionSet1NewEtapadeproduccion = em.merge(oldUnidaddemedida1OfEtapadeproduccionSet1NewEtapadeproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = unidadmedida.getIdunidadmedida();
                if (findUnidadmedida(id) == null) {
                    throw new NonexistentEntityException("The unidadmedida with id " + id + " no longer exists.");
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
            Unidadmedida unidadmedida;
            try {
                unidadmedida = em.getReference(Unidadmedida.class, id);
                unidadmedida.getIdunidadmedida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadmedida with id " + id + " no longer exists.", enfe);
            }
            Set<Materiaprima> materiaprimaSet = unidadmedida.getMateriaprimaSet();
            for (Materiaprima materiaprimaSetMateriaprima : materiaprimaSet) {
                materiaprimaSetMateriaprima.setUnidadmedida(null);
                materiaprimaSetMateriaprima = em.merge(materiaprimaSetMateriaprima);
            }
            Set<Materiaprima> materiaprimaSet1 = unidadmedida.getMateriaprimaSet1();
            for (Materiaprima materiaprimaSet1Materiaprima : materiaprimaSet1) {
                materiaprimaSet1Materiaprima.setUnidadmedida1(null);
                materiaprimaSet1Materiaprima = em.merge(materiaprimaSet1Materiaprima);
            }
            Set<Pieza> piezaSet = unidadmedida.getPiezaSet();
            for (Pieza piezaSetPieza : piezaSet) {
                piezaSetPieza.setUnidadmedida(null);
                piezaSetPieza = em.merge(piezaSetPieza);
            }
            Set<Pieza> piezaSet1 = unidadmedida.getPiezaSet1();
            for (Pieza piezaSet1Pieza : piezaSet1) {
                piezaSet1Pieza.setUnidadmedida1(null);
                piezaSet1Pieza = em.merge(piezaSet1Pieza);
            }
            Set<Maquina> maquinaSet = unidadmedida.getMaquinaSet();
            for (Maquina maquinaSetMaquina : maquinaSet) {
                maquinaSetMaquina.setIdunidadmedida(null);
                maquinaSetMaquina = em.merge(maquinaSetMaquina);
            }
            Set<Maquina> maquinaSet1 = unidadmedida.getMaquinaSet1();
            for (Maquina maquinaSet1Maquina : maquinaSet1) {
                maquinaSet1Maquina.setIdunidadmedida1(null);
                maquinaSet1Maquina = em.merge(maquinaSet1Maquina);
            }
            Set<Etapadeproduccion> etapadeproduccionSet = unidadmedida.getEtapadeproduccionSet();
            for (Etapadeproduccion etapadeproduccionSetEtapadeproduccion : etapadeproduccionSet) {
                etapadeproduccionSetEtapadeproduccion.setUnidaddemedida(null);
                etapadeproduccionSetEtapadeproduccion = em.merge(etapadeproduccionSetEtapadeproduccion);
            }
            Set<Etapadeproduccion> etapadeproduccionSet1 = unidadmedida.getEtapadeproduccionSet1();
            for (Etapadeproduccion etapadeproduccionSet1Etapadeproduccion : etapadeproduccionSet1) {
                etapadeproduccionSet1Etapadeproduccion.setUnidaddemedida1(null);
                etapadeproduccionSet1Etapadeproduccion = em.merge(etapadeproduccionSet1Etapadeproduccion);
            }
            em.remove(unidadmedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Unidadmedida> findUnidadmedidaEntities() {
        return findUnidadmedidaEntities(true, -1, -1);
    }

    public List<Unidadmedida> findUnidadmedidaEntities(int maxResults, int firstResult) {
        return findUnidadmedidaEntities(false, maxResults, firstResult);
    }

    private List<Unidadmedida> findUnidadmedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Unidadmedida.class));
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

    public Unidadmedida findUnidadmedida(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Unidadmedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadmedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Unidadmedida> rt = cq.from(Unidadmedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
