/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleproductopresupuesto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detallepresupuesto;
import entity.Materiaprima;
import entity.Pieza;
import entity.Detallepiezacalidadpresupuesto;
import java.util.HashSet;
import java.util.Set;
import entity.Detallepiezapresupuesto;

/**
 *
 * @author Nino
 */
public class DetalleproductopresupuestoJpaController {

    public DetalleproductopresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleproductopresupuesto detalleproductopresupuesto) throws PreexistingEntityException, Exception {
        if (detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet() == null) {
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet(new HashSet<Detallepiezacalidadpresupuesto>());
        }
        if (detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet1() == null) {
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet1(new HashSet<Detallepiezacalidadpresupuesto>());
        }
        if (detalleproductopresupuesto.getDetallepiezapresupuestoSet() == null) {
            detalleproductopresupuesto.setDetallepiezapresupuestoSet(new HashSet<Detallepiezapresupuesto>());
        }
        if (detalleproductopresupuesto.getDetallepiezapresupuestoSet1() == null) {
            detalleproductopresupuesto.setDetallepiezapresupuestoSet1(new HashSet<Detallepiezapresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepresupuesto iddetallepresupuesto = detalleproductopresupuesto.getIddetallepresupuesto();
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto = em.getReference(iddetallepresupuesto.getClass(), iddetallepresupuesto.getIddetalle());
                detalleproductopresupuesto.setIddetallepresupuesto(iddetallepresupuesto);
            }
            Detallepresupuesto iddetallepresupuesto1 = detalleproductopresupuesto.getIddetallepresupuesto1();
            if (iddetallepresupuesto1 != null) {
                iddetallepresupuesto1 = em.getReference(iddetallepresupuesto1.getClass(), iddetallepresupuesto1.getIddetalle());
                detalleproductopresupuesto.setIddetallepresupuesto1(iddetallepresupuesto1);
            }
            Materiaprima idmateriaprima = detalleproductopresupuesto.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima = em.getReference(idmateriaprima.getClass(), idmateriaprima.getIdmateriaprima());
                detalleproductopresupuesto.setIdmateriaprima(idmateriaprima);
            }
            Materiaprima idmateriaprima1 = detalleproductopresupuesto.getIdmateriaprima1();
            if (idmateriaprima1 != null) {
                idmateriaprima1 = em.getReference(idmateriaprima1.getClass(), idmateriaprima1.getIdmateriaprima());
                detalleproductopresupuesto.setIdmateriaprima1(idmateriaprima1);
            }
            Pieza idpieza = detalleproductopresupuesto.getIdpieza();
            if (idpieza != null) {
                idpieza = em.getReference(idpieza.getClass(), idpieza.getIdpieza());
                detalleproductopresupuesto.setIdpieza(idpieza);
            }
            Pieza idpieza1 = detalleproductopresupuesto.getIdpieza1();
            if (idpieza1 != null) {
                idpieza1 = em.getReference(idpieza1.getClass(), idpieza1.getIdpieza());
                detalleproductopresupuesto.setIdpieza1(idpieza1);
            }
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSet = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach : detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet()) {
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSet.add(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach);
            }
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet(attachedDetallepiezacalidadpresupuestoSet);
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSet1 = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach : detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet1()) {
                detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSet1.add(detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach);
            }
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet1(attachedDetallepiezacalidadpresupuestoSet1);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSet = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuestoToAttach : detalleproductopresupuesto.getDetallepiezapresupuestoSet()) {
                detallepiezapresupuestoSetDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSetDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSetDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSet.add(detallepiezapresupuestoSetDetallepiezapresupuestoToAttach);
            }
            detalleproductopresupuesto.setDetallepiezapresupuestoSet(attachedDetallepiezapresupuestoSet);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSet1 = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach : detalleproductopresupuesto.getDetallepiezapresupuestoSet1()) {
                detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSet1.add(detallepiezapresupuestoSet1DetallepiezapresupuestoToAttach);
            }
            detalleproductopresupuesto.setDetallepiezapresupuestoSet1(attachedDetallepiezapresupuestoSet1);
            em.persist(detalleproductopresupuesto);
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                iddetallepresupuesto = em.merge(iddetallepresupuesto);
            }
            if (iddetallepresupuesto1 != null) {
                iddetallepresupuesto1.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                iddetallepresupuesto1 = em.merge(iddetallepresupuesto1);
            }
            if (idmateriaprima != null) {
                idmateriaprima.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idmateriaprima = em.merge(idmateriaprima);
            }
            if (idmateriaprima1 != null) {
                idmateriaprima1.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idmateriaprima1 = em.merge(idmateriaprima1);
            }
            if (idpieza != null) {
                idpieza.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idpieza = em.merge(idpieza);
            }
            if (idpieza1 != null) {
                idpieza1.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idpieza1 = em.merge(idpieza1);
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto : detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet()) {
                Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                if (oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto != null) {
                    oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                    oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto : detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet1()) {
                Detalleproductopresupuesto oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto.getIddetalleproductopresupuesto1();
                detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto.setIddetalleproductopresupuesto1(detalleproductopresupuesto);
                detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto);
                if (oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto != null) {
                    oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet1().remove(detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto);
                    oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto = em.merge(oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuesto : detalleproductopresupuesto.getDetallepiezapresupuestoSet()) {
                Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto = detallepiezapresupuestoSetDetallepiezapresupuesto.getIddetalleproductopresupuesto();
                detallepiezapresupuestoSetDetallepiezapresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                detallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetDetallepiezapresupuesto);
                if (oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto != null) {
                    oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto.getDetallepiezapresupuestoSet().remove(detallepiezapresupuestoSetDetallepiezapresupuesto);
                    oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1Detallepiezapresupuesto : detalleproductopresupuesto.getDetallepiezapresupuestoSet1()) {
                Detalleproductopresupuesto oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto = detallepiezapresupuestoSet1Detallepiezapresupuesto.getIddetalleproductopresupuesto1();
                detallepiezapresupuestoSet1Detallepiezapresupuesto.setIddetalleproductopresupuesto1(detalleproductopresupuesto);
                detallepiezapresupuestoSet1Detallepiezapresupuesto = em.merge(detallepiezapresupuestoSet1Detallepiezapresupuesto);
                if (oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto != null) {
                    oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto.getDetallepiezapresupuestoSet1().remove(detallepiezapresupuestoSet1Detallepiezapresupuesto);
                    oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto = em.merge(oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1Detallepiezapresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleproductopresupuesto(detalleproductopresupuesto.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detalleproductopresupuesto " + detalleproductopresupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleproductopresupuesto detalleproductopresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductopresupuesto persistentDetalleproductopresupuesto = em.find(Detalleproductopresupuesto.class, detalleproductopresupuesto.getIddetalle());
            Detallepresupuesto iddetallepresupuestoOld = persistentDetalleproductopresupuesto.getIddetallepresupuesto();
            Detallepresupuesto iddetallepresupuestoNew = detalleproductopresupuesto.getIddetallepresupuesto();
            Detallepresupuesto iddetallepresupuesto1Old = persistentDetalleproductopresupuesto.getIddetallepresupuesto1();
            Detallepresupuesto iddetallepresupuesto1New = detalleproductopresupuesto.getIddetallepresupuesto1();
            Materiaprima idmateriaprimaOld = persistentDetalleproductopresupuesto.getIdmateriaprima();
            Materiaprima idmateriaprimaNew = detalleproductopresupuesto.getIdmateriaprima();
            Materiaprima idmateriaprima1Old = persistentDetalleproductopresupuesto.getIdmateriaprima1();
            Materiaprima idmateriaprima1New = detalleproductopresupuesto.getIdmateriaprima1();
            Pieza idpiezaOld = persistentDetalleproductopresupuesto.getIdpieza();
            Pieza idpiezaNew = detalleproductopresupuesto.getIdpieza();
            Pieza idpieza1Old = persistentDetalleproductopresupuesto.getIdpieza1();
            Pieza idpieza1New = detalleproductopresupuesto.getIdpieza1();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSetOld = persistentDetalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSetNew = detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet1Old = persistentDetalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet1();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet1New = detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet1();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSetOld = persistentDetalleproductopresupuesto.getDetallepiezapresupuestoSet();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSetNew = detalleproductopresupuesto.getDetallepiezapresupuestoSet();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet1Old = persistentDetalleproductopresupuesto.getDetallepiezapresupuestoSet1();
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet1New = detalleproductopresupuesto.getDetallepiezapresupuestoSet1();
            if (iddetallepresupuestoNew != null) {
                iddetallepresupuestoNew = em.getReference(iddetallepresupuestoNew.getClass(), iddetallepresupuestoNew.getIddetalle());
                detalleproductopresupuesto.setIddetallepresupuesto(iddetallepresupuestoNew);
            }
            if (iddetallepresupuesto1New != null) {
                iddetallepresupuesto1New = em.getReference(iddetallepresupuesto1New.getClass(), iddetallepresupuesto1New.getIddetalle());
                detalleproductopresupuesto.setIddetallepresupuesto1(iddetallepresupuesto1New);
            }
            if (idmateriaprimaNew != null) {
                idmateriaprimaNew = em.getReference(idmateriaprimaNew.getClass(), idmateriaprimaNew.getIdmateriaprima());
                detalleproductopresupuesto.setIdmateriaprima(idmateriaprimaNew);
            }
            if (idmateriaprima1New != null) {
                idmateriaprima1New = em.getReference(idmateriaprima1New.getClass(), idmateriaprima1New.getIdmateriaprima());
                detalleproductopresupuesto.setIdmateriaprima1(idmateriaprima1New);
            }
            if (idpiezaNew != null) {
                idpiezaNew = em.getReference(idpiezaNew.getClass(), idpiezaNew.getIdpieza());
                detalleproductopresupuesto.setIdpieza(idpiezaNew);
            }
            if (idpieza1New != null) {
                idpieza1New = em.getReference(idpieza1New.getClass(), idpieza1New.getIdpieza());
                detalleproductopresupuesto.setIdpieza1(idpieza1New);
            }
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSetNew = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach : detallepiezacalidadpresupuestoSetNew) {
                detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSetNew.add(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach);
            }
            detallepiezacalidadpresupuestoSetNew = attachedDetallepiezacalidadpresupuestoSetNew;
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet(detallepiezacalidadpresupuestoSetNew);
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSet1New = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach : detallepiezacalidadpresupuestoSet1New) {
                detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSet1New.add(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach);
            }
            detallepiezacalidadpresupuestoSet1New = attachedDetallepiezacalidadpresupuestoSet1New;
            detalleproductopresupuesto.setDetallepiezacalidadpresupuestoSet1(detallepiezacalidadpresupuestoSet1New);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSetNew = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach : detallepiezapresupuestoSetNew) {
                detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSetNew.add(detallepiezapresupuestoSetNewDetallepiezapresupuestoToAttach);
            }
            detallepiezapresupuestoSetNew = attachedDetallepiezapresupuestoSetNew;
            detalleproductopresupuesto.setDetallepiezapresupuestoSet(detallepiezapresupuestoSetNew);
            Set<Detallepiezapresupuesto> attachedDetallepiezapresupuestoSet1New = new HashSet<Detallepiezapresupuesto>();
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach : detallepiezapresupuestoSet1New) {
                detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach = em.getReference(detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach.getClass(), detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach.getIddetalle());
                attachedDetallepiezapresupuestoSet1New.add(detallepiezapresupuestoSet1NewDetallepiezapresupuestoToAttach);
            }
            detallepiezapresupuestoSet1New = attachedDetallepiezapresupuestoSet1New;
            detalleproductopresupuesto.setDetallepiezapresupuestoSet1(detallepiezapresupuestoSet1New);
            detalleproductopresupuesto = em.merge(detalleproductopresupuesto);
            if (iddetallepresupuestoOld != null && !iddetallepresupuestoOld.equals(iddetallepresupuestoNew)) {
                iddetallepresupuestoOld.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                iddetallepresupuestoOld = em.merge(iddetallepresupuestoOld);
            }
            if (iddetallepresupuestoNew != null && !iddetallepresupuestoNew.equals(iddetallepresupuestoOld)) {
                iddetallepresupuestoNew.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                iddetallepresupuestoNew = em.merge(iddetallepresupuestoNew);
            }
            if (iddetallepresupuesto1Old != null && !iddetallepresupuesto1Old.equals(iddetallepresupuesto1New)) {
                iddetallepresupuesto1Old.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                iddetallepresupuesto1Old = em.merge(iddetallepresupuesto1Old);
            }
            if (iddetallepresupuesto1New != null && !iddetallepresupuesto1New.equals(iddetallepresupuesto1Old)) {
                iddetallepresupuesto1New.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                iddetallepresupuesto1New = em.merge(iddetallepresupuesto1New);
            }
            if (idmateriaprimaOld != null && !idmateriaprimaOld.equals(idmateriaprimaNew)) {
                idmateriaprimaOld.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idmateriaprimaOld = em.merge(idmateriaprimaOld);
            }
            if (idmateriaprimaNew != null && !idmateriaprimaNew.equals(idmateriaprimaOld)) {
                idmateriaprimaNew.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idmateriaprimaNew = em.merge(idmateriaprimaNew);
            }
            if (idmateriaprima1Old != null && !idmateriaprima1Old.equals(idmateriaprima1New)) {
                idmateriaprima1Old.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idmateriaprima1Old = em.merge(idmateriaprima1Old);
            }
            if (idmateriaprima1New != null && !idmateriaprima1New.equals(idmateriaprima1Old)) {
                idmateriaprima1New.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idmateriaprima1New = em.merge(idmateriaprima1New);
            }
            if (idpiezaOld != null && !idpiezaOld.equals(idpiezaNew)) {
                idpiezaOld.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idpiezaOld = em.merge(idpiezaOld);
            }
            if (idpiezaNew != null && !idpiezaNew.equals(idpiezaOld)) {
                idpiezaNew.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idpiezaNew = em.merge(idpiezaNew);
            }
            if (idpieza1Old != null && !idpieza1Old.equals(idpieza1New)) {
                idpieza1Old.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idpieza1Old = em.merge(idpieza1Old);
            }
            if (idpieza1New != null && !idpieza1New.equals(idpieza1Old)) {
                idpieza1New.getDetalleproductopresupuestoSet().add(detalleproductopresupuesto);
                idpieza1New = em.merge(idpieza1New);
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSetOld) {
                if (!detallepiezacalidadpresupuestoSetNew.contains(detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto)) {
                    detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(null);
                    detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSetNew) {
                if (!detallepiezacalidadpresupuestoSetOld.contains(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto)) {
                    Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto();
                    detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                    detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                    if (oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto != null && !oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.equals(detalleproductopresupuesto)) {
                        oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                        oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                    }
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet1Old) {
                if (!detallepiezacalidadpresupuestoSet1New.contains(detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto)) {
                    detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto1(null);
                    detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet1New) {
                if (!detallepiezacalidadpresupuestoSet1Old.contains(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto)) {
                    Detalleproductopresupuesto oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto.getIddetalleproductopresupuesto1();
                    detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto1(detalleproductopresupuesto);
                    detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto);
                    if (oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto != null && !oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto.equals(detalleproductopresupuesto)) {
                        oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet1().remove(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto);
                        oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto = em.merge(oldIddetalleproductopresupuesto1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto);
                    }
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetOldDetallepiezapresupuesto : detallepiezapresupuestoSetOld) {
                if (!detallepiezapresupuestoSetNew.contains(detallepiezapresupuestoSetOldDetallepiezapresupuesto)) {
                    detallepiezapresupuestoSetOldDetallepiezapresupuesto.setIddetalleproductopresupuesto(null);
                    detallepiezapresupuestoSetOldDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetOldDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSetNewDetallepiezapresupuesto : detallepiezapresupuestoSetNew) {
                if (!detallepiezapresupuestoSetOld.contains(detallepiezapresupuestoSetNewDetallepiezapresupuesto)) {
                    Detalleproductopresupuesto oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto = detallepiezapresupuestoSetNewDetallepiezapresupuesto.getIddetalleproductopresupuesto();
                    detallepiezapresupuestoSetNewDetallepiezapresupuesto.setIddetalleproductopresupuesto(detalleproductopresupuesto);
                    detallepiezapresupuestoSetNewDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetNewDetallepiezapresupuesto);
                    if (oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto != null && !oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto.equals(detalleproductopresupuesto)) {
                        oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto.getDetallepiezapresupuestoSet().remove(detallepiezapresupuestoSetNewDetallepiezapresupuesto);
                        oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto = em.merge(oldIddetalleproductopresupuestoOfDetallepiezapresupuestoSetNewDetallepiezapresupuesto);
                    }
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1OldDetallepiezapresupuesto : detallepiezapresupuestoSet1Old) {
                if (!detallepiezapresupuestoSet1New.contains(detallepiezapresupuestoSet1OldDetallepiezapresupuesto)) {
                    detallepiezapresupuestoSet1OldDetallepiezapresupuesto.setIddetalleproductopresupuesto1(null);
                    detallepiezapresupuestoSet1OldDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSet1OldDetallepiezapresupuesto);
                }
            }
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1NewDetallepiezapresupuesto : detallepiezapresupuestoSet1New) {
                if (!detallepiezapresupuestoSet1Old.contains(detallepiezapresupuestoSet1NewDetallepiezapresupuesto)) {
                    Detalleproductopresupuesto oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto = detallepiezapresupuestoSet1NewDetallepiezapresupuesto.getIddetalleproductopresupuesto1();
                    detallepiezapresupuestoSet1NewDetallepiezapresupuesto.setIddetalleproductopresupuesto1(detalleproductopresupuesto);
                    detallepiezapresupuestoSet1NewDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSet1NewDetallepiezapresupuesto);
                    if (oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto != null && !oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto.equals(detalleproductopresupuesto)) {
                        oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto.getDetallepiezapresupuestoSet1().remove(detallepiezapresupuestoSet1NewDetallepiezapresupuesto);
                        oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto = em.merge(oldIddetalleproductopresupuesto1OfDetallepiezapresupuestoSet1NewDetallepiezapresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleproductopresupuesto.getIddetalle();
                if (findDetalleproductopresupuesto(id) == null) {
                    throw new NonexistentEntityException("The detalleproductopresupuesto with id " + id + " no longer exists.");
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
            Detalleproductopresupuesto detalleproductopresupuesto;
            try {
                detalleproductopresupuesto = em.getReference(Detalleproductopresupuesto.class, id);
                detalleproductopresupuesto.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleproductopresupuesto with id " + id + " no longer exists.", enfe);
            }
            Detallepresupuesto iddetallepresupuesto = detalleproductopresupuesto.getIddetallepresupuesto();
            if (iddetallepresupuesto != null) {
                iddetallepresupuesto.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                iddetallepresupuesto = em.merge(iddetallepresupuesto);
            }
            Detallepresupuesto iddetallepresupuesto1 = detalleproductopresupuesto.getIddetallepresupuesto1();
            if (iddetallepresupuesto1 != null) {
                iddetallepresupuesto1.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                iddetallepresupuesto1 = em.merge(iddetallepresupuesto1);
            }
            Materiaprima idmateriaprima = detalleproductopresupuesto.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idmateriaprima = em.merge(idmateriaprima);
            }
            Materiaprima idmateriaprima1 = detalleproductopresupuesto.getIdmateriaprima1();
            if (idmateriaprima1 != null) {
                idmateriaprima1.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idmateriaprima1 = em.merge(idmateriaprima1);
            }
            Pieza idpieza = detalleproductopresupuesto.getIdpieza();
            if (idpieza != null) {
                idpieza.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idpieza = em.merge(idpieza);
            }
            Pieza idpieza1 = detalleproductopresupuesto.getIdpieza1();
            if (idpieza1 != null) {
                idpieza1.getDetalleproductopresupuestoSet().remove(detalleproductopresupuesto);
                idpieza1 = em.merge(idpieza1);
            }
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet = detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet) {
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.setIddetalleproductopresupuesto(null);
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
            }
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet1 = detalleproductopresupuesto.getDetallepiezacalidadpresupuestoSet1();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet1) {
                detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto.setIddetalleproductopresupuesto1(null);
                detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto);
            }
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet = detalleproductopresupuesto.getDetallepiezapresupuestoSet();
            for (Detallepiezapresupuesto detallepiezapresupuestoSetDetallepiezapresupuesto : detallepiezapresupuestoSet) {
                detallepiezapresupuestoSetDetallepiezapresupuesto.setIddetalleproductopresupuesto(null);
                detallepiezapresupuestoSetDetallepiezapresupuesto = em.merge(detallepiezapresupuestoSetDetallepiezapresupuesto);
            }
            Set<Detallepiezapresupuesto> detallepiezapresupuestoSet1 = detalleproductopresupuesto.getDetallepiezapresupuestoSet1();
            for (Detallepiezapresupuesto detallepiezapresupuestoSet1Detallepiezapresupuesto : detallepiezapresupuestoSet1) {
                detallepiezapresupuestoSet1Detallepiezapresupuesto.setIddetalleproductopresupuesto1(null);
                detallepiezapresupuestoSet1Detallepiezapresupuesto = em.merge(detallepiezapresupuestoSet1Detallepiezapresupuesto);
            }
            em.remove(detalleproductopresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities() {
        return findDetalleproductopresupuestoEntities(true, -1, -1);
    }

    public List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities(int maxResults, int firstResult) {
        return findDetalleproductopresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Detalleproductopresupuesto> findDetalleproductopresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleproductopresupuesto.class));
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

    public Detalleproductopresupuesto findDetalleproductopresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleproductopresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleproductopresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleproductopresupuesto> rt = cq.from(Detalleproductopresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
