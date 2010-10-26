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
        if (procesocalidad.getMaquinaxprocesocalidadSet() == null) {
            procesocalidad.setMaquinaxprocesocalidadSet(new HashSet<Maquinaxprocesocalidad>());
        }
        if (procesocalidad.getDetallepiezacalidadpresupuestoSet() == null) {
            procesocalidad.setDetallepiezacalidadpresupuestoSet(new HashSet<Detallepiezacalidadpresupuesto>());
        }
        if (procesocalidad.getDetalleplanificacioncalidadSet() == null) {
            procesocalidad.setDetalleplanificacioncalidadSet(new HashSet<Detalleplanificacioncalidad>());
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
            Ejecucionprocesocalidad ejecucionprocesocalidad = procesocalidad.getEjecucionprocesocalidad();
            if (ejecucionprocesocalidad != null) {
                ejecucionprocesocalidad = em.getReference(ejecucionprocesocalidad.getClass(), ejecucionprocesocalidad.getEjecucionprocesocalidadPK());
                procesocalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);
            }
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSet = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach : procesocalidad.getDetalleplanprocesoscalidadSet()) {
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSet.add(detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach);
            }
            procesocalidad.setDetalleplanprocesoscalidadSet(attachedDetalleplanprocesoscalidadSet);
            Set<Maquinaxprocesocalidad> attachedMaquinaxprocesocalidadSet = new HashSet<Maquinaxprocesocalidad>();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach : procesocalidad.getMaquinaxprocesocalidadSet()) {
                maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach = em.getReference(maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach.getClass(), maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach.getMaquinaxprocesocalidadPK());
                attachedMaquinaxprocesocalidadSet.add(maquinaxprocesocalidadSetMaquinaxprocesocalidadToAttach);
            }
            procesocalidad.setMaquinaxprocesocalidadSet(attachedMaquinaxprocesocalidadSet);
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSet = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach : procesocalidad.getDetallepiezacalidadpresupuestoSet()) {
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSet.add(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuestoToAttach);
            }
            procesocalidad.setDetallepiezacalidadpresupuestoSet(attachedDetallepiezacalidadpresupuestoSet);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach : procesocalidad.getDetalleplanificacioncalidadSet()) {
                detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet.add(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach);
            }
            procesocalidad.setDetalleplanificacioncalidadSet(attachedDetalleplanificacioncalidadSet);
            em.persist(procesocalidad);
            if (accioncalidad != null) {
                accioncalidad.getProcesocalidadSet().add(procesocalidad);
                accioncalidad = em.merge(accioncalidad);
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
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidad : procesocalidad.getDetalleplanprocesoscalidadSet()) {
                Procesocalidad oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad = detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.getIdprocesocalidad();
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.setIdprocesocalidad(procesocalidad);
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                if (oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad != null) {
                    oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                    oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(oldIdprocesocalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
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
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto : procesocalidad.getDetallepiezacalidadpresupuestoSet()) {
                Procesocalidad oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.getIdprocesocalidad();
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.setIdprocesocalidad(procesocalidad);
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                if (oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto != null) {
                    oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoSet().remove(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
                    oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(oldIdprocesocalidadOfDetallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
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
            Ejecucionprocesocalidad ejecucionprocesocalidadOld = persistentProcesocalidad.getEjecucionprocesocalidad();
            Ejecucionprocesocalidad ejecucionprocesocalidadNew = procesocalidad.getEjecucionprocesocalidad();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetOld = persistentProcesocalidad.getDetalleplanprocesoscalidadSet();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetNew = procesocalidad.getDetalleplanprocesoscalidadSet();
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSetOld = persistentProcesocalidad.getMaquinaxprocesocalidadSet();
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSetNew = procesocalidad.getMaquinaxprocesocalidadSet();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSetOld = persistentProcesocalidad.getDetallepiezacalidadpresupuestoSet();
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSetNew = procesocalidad.getDetallepiezacalidadpresupuestoSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOld = persistentProcesocalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetNew = procesocalidad.getDetalleplanificacioncalidadSet();
            List<String> illegalOrphanMessages = null;
            if (ejecucionprocesocalidadOld != null && !ejecucionprocesocalidadOld.equals(ejecucionprocesocalidadNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ejecucionprocesocalidad " + ejecucionprocesocalidadOld + " since its procesocalidad field is not nullable.");
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetOldMaquinaxprocesocalidad : maquinaxprocesocalidadSetOld) {
                if (!maquinaxprocesocalidadSetNew.contains(maquinaxprocesocalidadSetOldMaquinaxprocesocalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Maquinaxprocesocalidad " + maquinaxprocesocalidadSetOldMaquinaxprocesocalidad + " since its procesocalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accioncalidadNew != null) {
                accioncalidadNew = em.getReference(accioncalidadNew.getClass(), accioncalidadNew.getIdaccioncalidad());
                procesocalidad.setAccioncalidad(accioncalidadNew);
            }
            if (ejecucionprocesocalidadNew != null) {
                ejecucionprocesocalidadNew = em.getReference(ejecucionprocesocalidadNew.getClass(), ejecucionprocesocalidadNew.getEjecucionprocesocalidadPK());
                procesocalidad.setEjecucionprocesocalidad(ejecucionprocesocalidadNew);
            }
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSetNew = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach : detalleplanprocesoscalidadSetNew) {
                detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSetNew.add(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach);
            }
            detalleplanprocesoscalidadSetNew = attachedDetalleplanprocesoscalidadSetNew;
            procesocalidad.setDetalleplanprocesoscalidadSet(detalleplanprocesoscalidadSetNew);
            Set<Maquinaxprocesocalidad> attachedMaquinaxprocesocalidadSetNew = new HashSet<Maquinaxprocesocalidad>();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach : maquinaxprocesocalidadSetNew) {
                maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach = em.getReference(maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach.getClass(), maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach.getMaquinaxprocesocalidadPK());
                attachedMaquinaxprocesocalidadSetNew.add(maquinaxprocesocalidadSetNewMaquinaxprocesocalidadToAttach);
            }
            maquinaxprocesocalidadSetNew = attachedMaquinaxprocesocalidadSetNew;
            procesocalidad.setMaquinaxprocesocalidadSet(maquinaxprocesocalidadSetNew);
            Set<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoSetNew = new HashSet<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach : detallepiezacalidadpresupuestoSetNew) {
                detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoSetNew.add(detallepiezacalidadpresupuestoSetNewDetallepiezacalidadpresupuestoToAttach);
            }
            detallepiezacalidadpresupuestoSetNew = attachedDetallepiezacalidadpresupuestoSetNew;
            procesocalidad.setDetallepiezacalidadpresupuestoSet(detallepiezacalidadpresupuestoSetNew);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSetNew = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSetNew) {
                detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSetNew.add(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSetNew = attachedDetalleplanificacioncalidadSetNew;
            procesocalidad.setDetalleplanificacioncalidadSet(detalleplanificacioncalidadSetNew);
            procesocalidad = em.merge(procesocalidad);
            if (accioncalidadOld != null && !accioncalidadOld.equals(accioncalidadNew)) {
                accioncalidadOld.getProcesocalidadSet().remove(procesocalidad);
                accioncalidadOld = em.merge(accioncalidadOld);
            }
            if (accioncalidadNew != null && !accioncalidadNew.equals(accioncalidadOld)) {
                accioncalidadNew.getProcesocalidadSet().add(procesocalidad);
                accioncalidadNew = em.merge(accioncalidadNew);
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
            Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSetOrphanCheck = procesocalidad.getMaquinaxprocesocalidadSet();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadSetOrphanCheckMaquinaxprocesocalidad : maquinaxprocesocalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procesocalidad (" + procesocalidad + ") cannot be destroyed since the Maquinaxprocesocalidad " + maquinaxprocesocalidadSetOrphanCheckMaquinaxprocesocalidad + " in its maquinaxprocesocalidadSet field has a non-nullable procesocalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Accioncalidad accioncalidad = procesocalidad.getAccioncalidad();
            if (accioncalidad != null) {
                accioncalidad.getProcesocalidadSet().remove(procesocalidad);
                accioncalidad = em.merge(accioncalidad);
            }
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet = procesocalidad.getDetalleplanprocesoscalidadSet();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidad : detalleplanprocesoscalidadSet) {
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.setIdprocesocalidad(null);
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
            }
            Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet = procesocalidad.getDetallepiezacalidadpresupuestoSet();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoSet) {
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto.setIdprocesocalidad(null);
                detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoSetDetallepiezacalidadpresupuesto);
            }
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet = procesocalidad.getDetalleplanificacioncalidadSet();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidad : detalleplanificacioncalidadSet) {
                detalleplanificacioncalidadSetDetalleplanificacioncalidad.setProcesocalidad(null);
                detalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
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
