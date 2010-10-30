/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Procesocalidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Accioncalidad;
import entity.Ejecucionprocesocalidad;
import entity.Detalleplanprocesoscalidad;
import java.util.HashSet;
import java.util.Set;
import entity.Maquinaxprocesocalidad;
import entity.Detallepiezacalidadpresupuesto;
import entity.Detalleplanificacioncalidad;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ProcesocalidadJpaController {

    public ProcesocalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesocalidad procesocalidad) throws PreexistingEntityException, Exception {
        if (procesocalidad.getDetalleplanprocesoscalidadSet() == null) {
            procesocalidad.setDetalleplanprocesoscalidadSet(new HashSet<Detalleplanprocesoscalidad>());
        }
        if (procesocalidad.getDetalleplanprocesoscalidadSet1() == null) {
            procesocalidad.setDetalleplanprocesoscalidadSet1(new HashSet<Detalleplanprocesoscalidad>());
        }
        if (procesocalidad.getMaquinaxprocesocalidadSet() == null) {
            procesocalidad.setMaquinaxprocesocalidadSet(new HashSet<Maquinaxprocesocalidad>());
        }
        if (procesocalidad.getMaquinaxprocesocalidadSet1() == null) {
            procesocalidad.setMaquinaxprocesocalidadSet1(new HashSet<Maquinaxprocesocalidad>());
        }
        if (procesocalidad.getDetallepiezacalidadpresupuestoSet() == null) {
            procesocalidad.setDetallepiezacalidadpresupuestoSet(new HashSet<Detallepiezacalidadpresupuesto>());
        }
        if (procesocalidad.getDetallepiezacalidadpresupuestoSet1() == null) {
            procesocalidad.setDetallepiezacalidadpresupuestoSet1(new HashSet<Detallepiezacalidadpresupuesto>());
        }
        if (procesocalidad.getDetalleplanificacioncalidadSet() == null) {
            procesocalidad.setDetalleplanificacioncalidadSet(new HashSet<Detalleplanificacioncalidad>());
        }
        if (procesocalidad.getDetalleplanificacioncalidadSet1() == null) {
            procesocalidad.setDetalleplanificacioncalidadSet1(new HashSet<Detalleplanificacioncalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accioncalidad accioncalidad = procesocalidad.getAccioncalidad();
            if (accioncalidad != null) {
                accioncalidad = em.getReference(accioncalidad.getClass(), accioncalidad.getIdaccioncalidad());
                procesocalidad.setAccioncalidad(accioncalidad);
            }
            Accioncalidad accioncalidad1 = procesocalidad.getAccioncalidad1();
            if (accioncalidad1 != null) {
                accioncalidad1 = em.getReference(accioncalidad1.getClass(), accioncalidad1.getIdaccioncalidad());
                procesocalidad.setAccioncalidad1(accioncalidad1);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad = procesocalidad.getEjecucionprocesocalidad();
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad = em.getReference(ejecucionprocesocalidad.getClass(), ejecucionprocesocalidad.getEjecucionprocesocalidadPK());
                procesocalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad1 = procesocalidad.getEjecucionprocesocalidad1();
            if (ejecucionprocesocalidad1 != null) {
                ejecucionprocesocalidad1 = em.getReference(ejecucionprocesocalidad1.getClass(), ejecucionprocesocalidad1.getEjecucionprocesocalidadPK());
                procesocalidad.setEjecucionprocesocalidad1(ejecucionprocesocalidad1);
            }
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSet = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach : procesocalidad.getDetalleplanprocesoscalidadSet()) {
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSet.add(detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach);
            }
            procesocalidad.setDetalleplanprocesoscalidadSet(attachedDetalleplanprocesoscalidadSet);
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSet1 = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach : procesocalidad.getDetalleplanprocesoscalidadSet1()) {
                detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSet1.add(detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach);
            }
            procesocalidad.setDetalleplanprocesoscalidadSet1(attachedDetalleplanprocesoscalidadSet1);
            Set<Maquinaxprocesocalidad> attachedMaquinaxprocesocalidadSet = new HashSet<Maquinaxprocesocalidad>();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach : procesocalidad.getMaquinaxprocesocalidadSet()) {
                maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach = em.getReference(maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach.getClass(), maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach.getMaquinaxprocesocalidadPK());
                attachedMaquinaxprocesocalidadSet.add(maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach);
            }
            procesocalidad.setMaquinaxprocesocalidadSet(attachedMaquinaxprocesocalidadSet);
            Set<Maquinaxprocesocalidad> attachedMaquinaxprocesocalidadSet1 = new HashSet<Maquinaxprocesocalidad>();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSet1MaquinaxprocesocalidadToAttach : procesocalidad.getMaquinaxprocesocalidadSet1()) {
                maquinaxprocesocalidadSet1MaquinaxprocesocalidadToAttach = em.getReference(maquinaxprocesocalidadSet1MaquinaxprocesocalidadToAttach.getClass(), maquinaxprocesocalidadSet1MaquinaxprocesocalidadToAttach.getMaquinaxprocesocalidadPK());
                attachedMaquinaxprocesocalidadSet1.add(maquinaxprocesocalidadSet1MaquinaxprocesocalidadToAttach);
            }
            procesocalidad.setMaquinaxprocesocalidadSet1(attachedMaquinaxprocesocalidadSet1);
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSet = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach : procesocalidad.getDetallepiezacalidadpresupuestoSet()) {
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSet.add(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach);
            }
            procesocalidad.setDetallepiezacalidadpresupuestoSet(attachedDetallepiezacalidadpresupuestoSet);
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSet1 = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach : procesocalidad.getDetallepiezacalidadpresupuestoSet1()) {
                detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSet1.add(detallepiezacalidadpresupuestoSet1DetallepiezacalidadpresupuestoToAttach);
            }
            procesocalidad.setDetallepiezacalidadpresupuestoSet1(attachedDetallepiezacalidadpresupuestoSet1);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach : procesocalidad.getDetalleplanificacioncalidadSet()) {
                detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet.add(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach);
            }
            procesocalidad.setDetalleplanificacioncalidadSet(attachedDetalleplanificacioncalidadSet);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet1 = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach : procesocalidad.getDetalleplanificacioncalidadSet1()) {
                detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet1.add(detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach);
            }
            procesocalidad.setDetalleplanificacioncalidadSet1(attachedDetalleplanificacioncalidadSet1);
            em.persist(procesocalidad);
            if (accioncalidad != null) {
                accioncalidad.getProcesocalidadSet().add(procesocalidad);
                accioncalidad = em.merge(accioncalidad);
            }
            if (accioncalidad1 != null) {
                accioncalidad1.getProcesocalidadSet().add(procesocalidad);
                accioncalidad1 = em.merge(accioncalidad1);
            }
            if (ejecucionprocesocalidad != null) {
                Procesocalidad oldProcesocalidadOfEjecucionprocesocalidad = ejecucionprocesocalidad.getProcesocalidad();
                if (oldProcesocalidadOfEjecucionprocesocalidad != null) {
                    oldProcesocalidadOfEjecucionprocesocalidad.setEjecucionprocesocalidad(null);
                    oldProcesocalidadOfEjecucionprocesocalidad = em.merge(oldProcesocalidadOfEjecucionprocesocalidad);
                }
                ejecucionprocesocalidad.setProcesocalidad(procesocalidad);
                ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            }
            if (ejecucionprocesocalidad1 != null) {
                Procesocalidad oldProcesocalidad1OfEjecucionprocesocalidad1 = ejecucionprocesocalidad1.getProcesocalidad1();
                if (oldProcesocalidad1OfEjecucionprocesocalidad1 != null) {
                    oldProcesocalidad1OfEjecucionprocesocalidad1.setEjecucionprocesocalidad1(null);
                    oldProcesocalidad1OfEjecucionprocesocalidad1 = em.merge(oldProcesocalidad1OfEjecucionprocesocalidad1);
                }
                ejecucionprocesocalidad1.setProcesocalidad1(procesocalidad);
                ejecucionprocesocalidad1 = em.merge(ejecucionprocesocalidad1);
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidad : procesocalidad.getDetalleplanprocesoscalidadSet()) {
                Procesocalidad oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad = detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.getIdprocesocalidad();
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.setIdprocesocalidad(procesocalidad);
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                if (oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad != null) {
                    oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                    oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                }
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad : procesocalidad.getDetalleplanprocesoscalidadSet1()) {
                Procesocalidad oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad = detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad.getIdprocesocalidad1();
                detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad.setIdprocesocalidad1(procesocalidad);
                detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad);
                if (oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad != null) {
                    oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet1().remove(detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad);
                    oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad = em.merge(oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad);
                }
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetMaquinaxprocesocalidad : procesocalidad.getMaquinaxprocesocalidadSet()) {
                Procesocalidad oldProcesocalidadOfMaquinaxprocesocalidadSetMaquinaxprocesocalidad = maquinaxprocesocalidadSetMaquinaxprocesocalidad.getProcesocalidad();
                maquinaxprocesocalidadSetMaquinaxprocesocalidad.setProcesocalidad(procesocalidad);
                maquinaxprocesocalidadSetMaquinaxprocesocalidad = em.merge(maquinaxprocesocalidadSetMaquinaxprocesocalidad);
                if (oldProcesocalidadOfMaquinaxprocesocalidadSetMaquinaxprocesocalidad != null) {
                    oldProcesocalidadOfMaquinaxprocesocalidadSetMaquinaxprocesocalidad.getMaquinaxprocesocalidadSet().remove(maquinaxprocesocalidadSetMaquinaxprocesocalidad);
                    oldProcesocalidadOfMaquinaxprocesocalidadSetMaquinaxprocesocalidad = em.merge(oldProcesocalidadOfMaquinaxprocesocalidadSetMaquinaxprocesocalidad);
                }
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSet1Maquinaxprocesocalidad : procesocalidad.getMaquinaxprocesocalidadSet1()) {
                Procesocalidad oldProcesocalidad1OfMaquinaxprocesocalidadSet1Maquinaxprocesocalidad = maquinaxprocesocalidadSet1Maquinaxprocesocalidad.getProcesocalidad1();
                maquinaxprocesocalidadSet1Maquinaxprocesocalidad.setProcesocalidad1(procesocalidad);
                maquinaxprocesocalidadSet1Maquinaxprocesocalidad = em.merge(maquinaxprocesocalidadSet1Maquinaxprocesocalidad);
                if (oldProcesocalidad1OfMaquinaxprocesocalidadSet1Maquinaxprocesocalidad != null) {
                    oldProcesocalidad1OfMaquinaxprocesocalidadSet1Maquinaxprocesocalidad.getMaquinaxprocesocalidadSet1().remove(maquinaxprocesocalidadSet1Maquinaxprocesocalidad);
                    oldProcesocalidad1OfMaquinaxprocesocalidadSet1Maquinaxprocesocalidad = em.merge(oldProcesocalidad1OfMaquinaxprocesocalidadSet1Maquinaxprocesocalidad);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto : procesocalidad.getDetallepiezacalidadpresupuestoSet()) {
                Procesocalidad oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.getIdprocesocalidad();
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.setIdprocesocalidad(procesocalidad);
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                if (oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto != null) {
                    oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                    oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto : procesocalidad.getDetallepiezacalidadpresupuestoSet1()) {
                Procesocalidad oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto.getIdprocesocalidad1();
                detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto.setIdprocesocalidad1(procesocalidad);
                detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto);
                if (oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto != null) {
                    oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet1().remove(detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto);
                    oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto = em.merge(oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidad : procesocalidad.getDetalleplanificacioncalidadSet()) {
                Procesocalidad oldProcesocalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad = detalleplanificacioncalidadSetDetalleplanificacioncalidad.getProcesocalidad();
                detalleplanificacioncalidadSetDetalleplanificacioncalidad.setProcesocalidad(procesocalidad);
                detalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
                if (oldProcesocalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad != null) {
                    oldProcesocalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
                    oldProcesocalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(oldProcesocalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1Detalleplanificacioncalidad : procesocalidad.getDetalleplanificacioncalidadSet1()) {
                Procesocalidad oldProcesocalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad = detalleplanificacioncalidadSet1Detalleplanificacioncalidad.getProcesocalidad1();
                detalleplanificacioncalidadSet1Detalleplanificacioncalidad.setProcesocalidad1(procesocalidad);
                detalleplanificacioncalidadSet1Detalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSet1Detalleplanificacioncalidad);
                if (oldProcesocalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad != null) {
                    oldProcesocalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad.getDetalleplanificacioncalidadSet1().remove(detalleplanificacioncalidadSet1Detalleplanificacioncalidad);
                    oldProcesocalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad = em.merge(oldProcesocalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProcesocalidad(procesocalidad.getIdprocesocalidad()) != null) {
                throw new PreexistingEntityException("Procesocalidad " + procesocalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesocalidad procesocalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesocalidad persistentProcesocalidad = em.find(Procesocalidad.class, procesocalidad.getIdprocesocalidad());
            Accioncalidad accioncalidadOld = persistentProcesocalidad.getAccioncalidad();
            Accioncalidad accioncalidadNew = procesocalidad.getAccioncalidad();
            Accioncalidad accioncalidad1Old = persistentProcesocalidad.getAccioncalidad1();
            Accioncalidad accioncalidad1New = procesocalidad.getAccioncalidad1();
            Ejecucionprocesocalidad ejecucionprocesocalidadOld = persistentProcesocalidad.getEjecucionprocesocalidad();
            Ejecucionprocesocalidad ejecucionprocesocalidadNew = procesocalidad.getEjecucionprocesocalidad();
            Ejecucionprocesocalidad ejecucionprocesocalidad1Old = persistentProcesocalidad.getEjecucionprocesocalidad1();
            Ejecucionprocesocalidad ejecucionprocesocalidad1New = procesocalidad.getEjecucionprocesocalidad1();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetOld = persistentProcesocalidad.getDetalleplanprocesoscalidadSet();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetNew = procesocalidad.getDetalleplanprocesoscalidadSet();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet1Old = persistentProcesocalidad.getDetalleplanprocesoscalidadSet1();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet1New = procesocalidad.getDetalleplanprocesoscalidadSet1();
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSetOld = persistentProcesocalidad.getMaquinaxprocesocalidadSet();
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSetNew = procesocalidad.getMaquinaxprocesocalidadSet();
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSet1Old = persistentProcesocalidad.getMaquinaxprocesocalidadSet1();
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSet1New = procesocalidad.getMaquinaxprocesocalidadSet1();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSetOld = persistentProcesocalidad.getDetallepiezacalidadpresupuestoSet();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSetNew = procesocalidad.getDetallepiezacalidadpresupuestoSet();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet1Old = persistentProcesocalidad.getDetallepiezacalidadpresupuestoSet1();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet1New = procesocalidad.getDetallepiezacalidadpresupuestoSet1();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOld = persistentProcesocalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetNew = procesocalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1Old = persistentProcesocalidad.getDetalleplanificacioncalidadSet1();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1New = procesocalidad.getDetalleplanificacioncalidadSet1();
            List<String> illegalOrphanMessages = null;
            if (ejecucionprocesocalidadOld != null && !ejecucionprocesocalidadOld.equals(ejecucionprocesocalidadNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ejecucionprocesocalidad " + ejecucionprocesocalidadOld + " since its procesocalidad field is not nullable.");
            }
            if (ejecucionprocesocalidad1Old != null && !ejecucionprocesocalidad1Old.equals(ejecucionprocesocalidad1New)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ejecucionprocesocalidad " + ejecucionprocesocalidad1Old + " since its procesocalidad1 field is not nullable.");
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetOldMaquinaxprocesocalidad : maquinaxprocesocalidadSetOld) {
                if (!maquinaxprocesocalidadSetNew.contains(maquinaxprocesocalidadSetOldMaquinaxprocesocalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Maquinaxprocesocalidad " + maquinaxprocesocalidadSetOldMaquinaxprocesocalidad + " since its procesocalidad field is not nullable.");
                }
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSet1OldMaquinaxprocesocalidad : maquinaxprocesocalidadSet1Old) {
                if (!maquinaxprocesocalidadSet1New.contains(maquinaxprocesocalidadSet1OldMaquinaxprocesocalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Maquinaxprocesocalidad " + maquinaxprocesocalidadSet1OldMaquinaxprocesocalidad + " since its procesocalidad1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accioncalidadNew != null) {
                accioncalidadNew = em.getReference(accioncalidadNew.getClass(), accioncalidadNew.getIdaccioncalidad());
                procesocalidad.setAccioncalidad(accioncalidadNew);
            }
            if (accioncalidad1New != null) {
                accioncalidad1New = em.getReference(accioncalidad1New.getClass(), accioncalidad1New.getIdaccioncalidad());
                procesocalidad.setAccioncalidad1(accioncalidad1New);
            }
            if (ejecucionprocesocalidadNew != null) {
                ejecucionprocesocalidadNew = em.getReference(ejecucionprocesocalidadNew.getClass(), ejecucionprocesocalidadNew.getEjecucionprocesocalidadPK());
                procesocalidad.setEjecucionprocesocalidad(ejecucionprocesocalidadNew);
            }
            if (ejecucionprocesocalidad1New != null) {
                ejecucionprocesocalidad1New = em.getReference(ejecucionprocesocalidad1New.getClass(), ejecucionprocesocalidad1New.getEjecucionprocesocalidadPK());
                procesocalidad.setEjecucionprocesocalidad1(ejecucionprocesocalidad1New);
            }
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSetNew = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach : detalleplanprocesoscalidadSetNew) {
                detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSetNew.add(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach);
            }
            detalleplanprocesoscalidadSetNew = attachedDetalleplanprocesoscalidadSetNew;
            procesocalidad.setDetalleplanprocesoscalidadSet(detalleplanprocesoscalidadSetNew);
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSet1New = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach : detalleplanprocesoscalidadSet1New) {
                detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSet1New.add(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach);
            }
            detalleplanprocesoscalidadSet1New = attachedDetalleplanprocesoscalidadSet1New;
            procesocalidad.setDetalleplanprocesoscalidadSet1(detalleplanprocesoscalidadSet1New);
            Set<Maquinaxprocesocalidad> attachedMaquinaxprocesocalidadSetNew = new HashSet<Maquinaxprocesocalidad>();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach : maquinaxprocesocalidadSetNew) {
                maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach = em.getReference(maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach.getClass(), maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach.getMaquinaxprocesocalidadPK());
                attachedMaquinaxprocesocalidadSetNew.add(maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach);
            }
            maquinaxprocesocalidadSetNew = attachedMaquinaxprocesocalidadSetNew;
            procesocalidad.setMaquinaxprocesocalidadSet(maquinaxprocesocalidadSetNew);
            Set<Maquinaxprocesocalidad> attachedMaquinaxprocesocalidadSet1New = new HashSet<Maquinaxprocesocalidad>();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSet1NewMaquinaxprocesocalidadToAttach : maquinaxprocesocalidadSet1New) {
                maquinaxprocesocalidadSet1NewMaquinaxprocesocalidadToAttach = em.getReference(maquinaxprocesocalidadSet1NewMaquinaxprocesocalidadToAttach.getClass(), maquinaxprocesocalidadSet1NewMaquinaxprocesocalidadToAttach.getMaquinaxprocesocalidadPK());
                attachedMaquinaxprocesocalidadSet1New.add(maquinaxprocesocalidadSet1NewMaquinaxprocesocalidadToAttach);
            }
            maquinaxprocesocalidadSet1New = attachedMaquinaxprocesocalidadSet1New;
            procesocalidad.setMaquinaxprocesocalidadSet1(maquinaxprocesocalidadSet1New);
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSetNew = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach : detallepiezacalidadpresupuestoSetNew) {
                detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSetNew.add(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach);
            }
            detallepiezacalidadpresupuestoSetNew = attachedDetallepiezacalidadpresupuestoSetNew;
            procesocalidad.setDetallepiezacalidadpresupuestoSet(detallepiezacalidadpresupuestoSetNew);
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSet1New = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach : detallepiezacalidadpresupuestoSet1New) {
                detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSet1New.add(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuestoToAttach);
            }
            detallepiezacalidadpresupuestoSet1New = attachedDetallepiezacalidadpresupuestoSet1New;
            procesocalidad.setDetallepiezacalidadpresupuestoSet1(detallepiezacalidadpresupuestoSet1New);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSetNew = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSetNew) {
                detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSetNew.add(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSetNew = attachedDetalleplanificacioncalidadSetNew;
            procesocalidad.setDetalleplanificacioncalidadSet(detalleplanificacioncalidadSetNew);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet1New = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSet1New) {
                detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet1New.add(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSet1New = attachedDetalleplanificacioncalidadSet1New;
            procesocalidad.setDetalleplanificacioncalidadSet1(detalleplanificacioncalidadSet1New);
            procesocalidad = em.merge(procesocalidad);
            if (accioncalidadOld != null && !accioncalidadOld.equals(accioncalidadNew)) {
                accioncalidadOld.getProcesocalidadSet().remove(procesocalidad);
                accioncalidadOld = em.merge(accioncalidadOld);
            }
            if (accioncalidadNew != null && !accioncalidadNew.equals(accioncalidadOld)) {
                accioncalidadNew.getProcesocalidadSet().add(procesocalidad);
                accioncalidadNew = em.merge(accioncalidadNew);
            }
            if (accioncalidad1Old != null && !accioncalidad1Old.equals(accioncalidad1New)) {
                accioncalidad1Old.getProcesocalidadSet().remove(procesocalidad);
                accioncalidad1Old = em.merge(accioncalidad1Old);
            }
            if (accioncalidad1New != null && !accioncalidad1New.equals(accioncalidad1Old)) {
                accioncalidad1New.getProcesocalidadSet().add(procesocalidad);
                accioncalidad1New = em.merge(accioncalidad1New);
            }
            if (ejecucionprocesocalidadNew != null && !ejecucionprocesocalidadNew.equals(ejecucionprocesocalidadOld)) {
                Procesocalidad oldProcesocalidadOfEjecucionprocesocalidad = ejecucionprocesocalidadNew.getProcesocalidad();
                if (oldProcesocalidadOfEjecucionprocesocalidad != null) {
                    oldProcesocalidadOfEjecucionprocesocalidad.setEjecucionprocesocalidad(null);
                    oldProcesocalidadOfEjecucionprocesocalidad = em.merge(oldProcesocalidadOfEjecucionprocesocalidad);
                }
                ejecucionprocesocalidadNew.setProcesocalidad(procesocalidad);
                ejecucionprocesocalidadNew = em.merge(ejecucionprocesocalidadNew);
            }
            if (ejecucionprocesocalidad1New != null && !ejecucionprocesocalidad1New.equals(ejecucionprocesocalidad1Old)) {
                Procesocalidad oldProcesocalidad1OfEjecucionprocesocalidad1 = ejecucionprocesocalidad1New.getProcesocalidad1();
                if (oldProcesocalidad1OfEjecucionprocesocalidad1 != null) {
                    oldProcesocalidad1OfEjecucionprocesocalidad1.setEjecucionprocesocalidad1(null);
                    oldProcesocalidad1OfEjecucionprocesocalidad1 = em.merge(oldProcesocalidad1OfEjecucionprocesocalidad1);
                }
                ejecucionprocesocalidad1New.setProcesocalidad1(procesocalidad);
                ejecucionprocesocalidad1New = em.merge(ejecucionprocesocalidad1New);
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad : detalleplanprocesoscalidadSetOld) {
                if (!detalleplanprocesoscalidadSetNew.contains(detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad)) {
                    detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad.setIdprocesocalidad(null);
                    detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad);
                }
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad : detalleplanprocesoscalidadSetNew) {
                if (!detalleplanprocesoscalidadSetOld.contains(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad)) {
                    Procesocalidad oldIdprocesocalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.getIdprocesocalidad();
                    detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.setIdprocesocalidad(procesocalidad);
                    detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                    if (oldIdprocesocalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad != null && !oldIdprocesocalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.equals(procesocalidad)) {
                        oldIdprocesocalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                        oldIdprocesocalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = em.merge(oldIdprocesocalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                    }
                }
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1OldDetalleplanprocesoscalidad : detalleplanprocesoscalidadSet1Old) {
                if (!detalleplanprocesoscalidadSet1New.contains(detalleplanprocesoscalidadSet1OldDetalleplanprocesoscalidad)) {
                    detalleplanprocesoscalidadSet1OldDetalleplanprocesoscalidad.setIdprocesocalidad1(null);
                    detalleplanprocesoscalidadSet1OldDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSet1OldDetalleplanprocesoscalidad);
                }
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad : detalleplanprocesoscalidadSet1New) {
                if (!detalleplanprocesoscalidadSet1Old.contains(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad)) {
                    Procesocalidad oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad = detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad.getIdprocesocalidad1();
                    detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad.setIdprocesocalidad1(procesocalidad);
                    detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad);
                    if (oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad != null && !oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad.equals(procesocalidad)) {
                        oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet1().remove(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad);
                        oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad = em.merge(oldIdprocesocalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad);
                    }
                }
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetNewMaquinaxprocesocalidad : maquinaxprocesocalidadSetNew) {
                if (!maquinaxprocesocalidadSetOld.contains(maquinaxprocesocalidadSetNewMaquinaxprocesocalidad)) {
                    Procesocalidad oldProcesocalidadOfMaquinaxprocesocalidadSetNewMaquinaxprocesocalidad = maquinaxprocesocalidadSetNewMaquinaxprocesocalidad.getProcesocalidad();
                    maquinaxprocesocalidadSetNewMaquinaxprocesocalidad.setProcesocalidad(procesocalidad);
                    maquinaxprocesocalidadSetNewMaquinaxprocesocalidad = em.merge(maquinaxprocesocalidadSetNewMaquinaxprocesocalidad);
                    if (oldProcesocalidadOfMaquinaxprocesocalidadSetNewMaquinaxprocesocalidad != null && !oldProcesocalidadOfMaquinaxprocesocalidadSetNewMaquinaxprocesocalidad.equals(procesocalidad)) {
                        oldProcesocalidadOfMaquinaxprocesocalidadSetNewMaquinaxprocesocalidad.getMaquinaxprocesocalidadSet().remove(maquinaxprocesocalidadSetNewMaquinaxprocesocalidad);
                        oldProcesocalidadOfMaquinaxprocesocalidadSetNewMaquinaxprocesocalidad = em.merge(oldProcesocalidadOfMaquinaxprocesocalidadSetNewMaquinaxprocesocalidad);
                    }
                }
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSet1NewMaquinaxprocesocalidad : maquinaxprocesocalidadSet1New) {
                if (!maquinaxprocesocalidadSet1Old.contains(maquinaxprocesocalidadSet1NewMaquinaxprocesocalidad)) {
                    Procesocalidad oldProcesocalidad1OfMaquinaxprocesocalidadSet1NewMaquinaxprocesocalidad = maquinaxprocesocalidadSet1NewMaquinaxprocesocalidad.getProcesocalidad1();
                    maquinaxprocesocalidadSet1NewMaquinaxprocesocalidad.setProcesocalidad1(procesocalidad);
                    maquinaxprocesocalidadSet1NewMaquinaxprocesocalidad = em.merge(maquinaxprocesocalidadSet1NewMaquinaxprocesocalidad);
                    if (oldProcesocalidad1OfMaquinaxprocesocalidadSet1NewMaquinaxprocesocalidad != null && !oldProcesocalidad1OfMaquinaxprocesocalidadSet1NewMaquinaxprocesocalidad.equals(procesocalidad)) {
                        oldProcesocalidad1OfMaquinaxprocesocalidadSet1NewMaquinaxprocesocalidad.getMaquinaxprocesocalidadSet1().remove(maquinaxprocesocalidadSet1NewMaquinaxprocesocalidad);
                        oldProcesocalidad1OfMaquinaxprocesocalidadSet1NewMaquinaxprocesocalidad = em.merge(oldProcesocalidad1OfMaquinaxprocesocalidadSet1NewMaquinaxprocesocalidad);
                    }
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSetOld) {
                if (!detallepiezacalidadpresupuestoSetNew.contains(detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto)) {
                    detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto.setIdprocesocalidad(null);
                    detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetOldDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSetNew) {
                if (!detallepiezacalidadpresupuestoSetOld.contains(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto)) {
                    Procesocalidad oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.getIdprocesocalidad();
                    detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.setIdprocesocalidad(procesocalidad);
                    detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                    if (oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto != null && !oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.equals(procesocalidad)) {
                        oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                        oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto = em.merge(oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuesto);
                    }
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet1Old) {
                if (!detallepiezacalidadpresupuestoSet1New.contains(detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto)) {
                    detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto.setIdprocesocalidad1(null);
                    detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSet1OldDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet1New) {
                if (!detallepiezacalidadpresupuestoSet1Old.contains(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto)) {
                    Procesocalidad oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto.getIdprocesocalidad1();
                    detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto.setIdprocesocalidad1(procesocalidad);
                    detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto);
                    if (oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto != null && !oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto.equals(procesocalidad)) {
                        oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet1().remove(detallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto);
                        oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto = em.merge(oldIdprocesocalidad1OfDetallepiezacalidadpresupuestoSet1NewDetallepiezacalidadpresupuesto);
                    }
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetOldDetalleplanificacioncalidad : detalleplanificacioncalidadSetOld) {
                if (!detalleplanificacioncalidadSetNew.contains(detalleplanificacioncalidadSetOldDetalleplanificacioncalidad)) {
                    detalleplanificacioncalidadSetOldDetalleplanificacioncalidad.setProcesocalidad(null);
                    detalleplanificacioncalidadSetOldDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetOldDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidad : detalleplanificacioncalidadSetNew) {
                if (!detalleplanificacioncalidadSetOld.contains(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad)) {
                    Procesocalidad oldProcesocalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad = detalleplanificacioncalidadSetNewDetalleplanificacioncalidad.getProcesocalidad();
                    detalleplanificacioncalidadSetNewDetalleplanificacioncalidad.setProcesocalidad(procesocalidad);
                    detalleplanificacioncalidadSetNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                    if (oldProcesocalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad != null && !oldProcesocalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad.equals(procesocalidad)) {
                        oldProcesocalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                        oldProcesocalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad = em.merge(oldProcesocalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                    }
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad : detalleplanificacioncalidadSet1Old) {
                if (!detalleplanificacioncalidadSet1New.contains(detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad)) {
                    detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad.setProcesocalidad1(null);
                    detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad : detalleplanificacioncalidadSet1New) {
                if (!detalleplanificacioncalidadSet1Old.contains(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad)) {
                    Procesocalidad oldProcesocalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.getProcesocalidad1();
                    detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.setProcesocalidad1(procesocalidad);
                    detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
                    if (oldProcesocalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad != null && !oldProcesocalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.equals(procesocalidad)) {
                        oldProcesocalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet1().remove(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
                        oldProcesocalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = em.merge(oldProcesocalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = procesocalidad.getIdprocesocalidad();
                if (findProcesocalidad(id) == null) {
                    throw new NonexistentEntityException("The procesocalidad with id " + id + " no longer exists.");
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
            Procesocalidad procesocalidad;
            try {
                procesocalidad = em.getReference(Procesocalidad.class, id);
                procesocalidad.getIdprocesocalidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesocalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Ejecucionprocesocalidad ejecucionprocesocalidadOrphanCheck = procesocalidad.getEjecucionprocesocalidad();
            if (ejecucionprocesocalidadOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procesocalidad (" + procesocalidad + ") cannot be destroyed since the Ejecucionprocesocalidad " + ejecucionprocesocalidadOrphanCheck + " in its ejecucionprocesocalidad field has a non-nullable procesocalidad field.");
            }
            Ejecucionprocesocalidad ejecucionprocesocalidad1OrphanCheck = procesocalidad.getEjecucionprocesocalidad1();
            if (ejecucionprocesocalidad1OrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procesocalidad (" + procesocalidad + ") cannot be destroyed since the Ejecucionprocesocalidad " + ejecucionprocesocalidad1OrphanCheck + " in its ejecucionprocesocalidad1 field has a non-nullable procesocalidad1 field.");
            }
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSetOrphanCheck = procesocalidad.getMaquinaxprocesocalidadSet();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetOrphanCheckMaquinaxprocesocalidad : maquinaxprocesocalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procesocalidad (" + procesocalidad + ") cannot be destroyed since the Maquinaxprocesocalidad " + maquinaxprocesocalidadSetOrphanCheckMaquinaxprocesocalidad + " in its maquinaxprocesocalidadSet field has a non-nullable procesocalidad field.");
            }
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSet1OrphanCheck = procesocalidad.getMaquinaxprocesocalidadSet1();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSet1OrphanCheckMaquinaxprocesocalidad : maquinaxprocesocalidadSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procesocalidad (" + procesocalidad + ") cannot be destroyed since the Maquinaxprocesocalidad " + maquinaxprocesocalidadSet1OrphanCheckMaquinaxprocesocalidad + " in its maquinaxprocesocalidadSet1 field has a non-nullable procesocalidad1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Accioncalidad accioncalidad = procesocalidad.getAccioncalidad();
            if (accioncalidad != null) {
                accioncalidad.getProcesocalidadSet().remove(procesocalidad);
                accioncalidad = em.merge(accioncalidad);
            }
            Accioncalidad accioncalidad1 = procesocalidad.getAccioncalidad1();
            if (accioncalidad1 != null) {
                accioncalidad1.getProcesocalidadSet().remove(procesocalidad);
                accioncalidad1 = em.merge(accioncalidad1);
            }
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet = procesocalidad.getDetalleplanprocesoscalidadSet();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidad : detalleplanprocesoscalidadSet) {
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.setIdprocesocalidad(null);
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
            }
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet1 = procesocalidad.getDetalleplanprocesoscalidadSet1();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad : detalleplanprocesoscalidadSet1) {
                detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad.setIdprocesocalidad1(null);
                detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad);
            }
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet = procesocalidad.getDetallepiezacalidadpresupuestoSet();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet) {
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.setIdprocesocalidad(null);
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
            }
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet1 = procesocalidad.getDetallepiezacalidadpresupuestoSet1();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet1) {
                detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto.setIdprocesocalidad1(null);
                detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSet1Detallepiezacalidadpresupuesto);
            }
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet = procesocalidad.getDetalleplanificacioncalidadSet();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidad : detalleplanificacioncalidadSet) {
                detalleplanificacioncalidadSetDetalleplanificacioncalidad.setProcesocalidad(null);
                detalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
            }
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1 = procesocalidad.getDetalleplanificacioncalidadSet1();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1Detalleplanificacioncalidad : detalleplanificacioncalidadSet1) {
                detalleplanificacioncalidadSet1Detalleplanificacioncalidad.setProcesocalidad1(null);
                detalleplanificacioncalidadSet1Detalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSet1Detalleplanificacioncalidad);
            }
            em.remove(procesocalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procesocalidad> findProcesocalidadEntities() {
        return findProcesocalidadEntities(true, -1, -1);
    }

    public List<Procesocalidad> findProcesocalidadEntities(int maxResults, int firstResult) {
        return findProcesocalidadEntities(false, maxResults, firstResult);
    }

    private List<Procesocalidad> findProcesocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesocalidad.class));
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

    public Procesocalidad findProcesocalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesocalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesocalidad> rt = cq.from(Procesocalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
