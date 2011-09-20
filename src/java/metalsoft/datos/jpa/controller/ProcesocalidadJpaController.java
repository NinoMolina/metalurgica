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
import metalsoft.datos.jpa.entity.Accioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanprocesoscalidad;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Maquinaxprocesocalidad;
import metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Procesocalidad;

/**
 *
 * @author Nino
 */
public class ProcesocalidadJpaController implements Serializable {

    public ProcesocalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesocalidad procesocalidad) {
        if (procesocalidad.getDetalleplanprocesoscalidadList() == null) {
            procesocalidad.setDetalleplanprocesoscalidadList(new ArrayList<Detalleplanprocesoscalidad>());
        }
        if (procesocalidad.getMaquinaxprocesocalidadList() == null) {
            procesocalidad.setMaquinaxprocesocalidadList(new ArrayList<Maquinaxprocesocalidad>());
        }
        if (procesocalidad.getDetallepiezacalidadpresupuestoList() == null) {
            procesocalidad.setDetallepiezacalidadpresupuestoList(new ArrayList<Detallepiezacalidadpresupuesto>());
        }
        if (procesocalidad.getDetalleplanificacioncalidadList() == null) {
            procesocalidad.setDetalleplanificacioncalidadList(new ArrayList<Detalleplanificacioncalidad>());
        }
        if (procesocalidad.getDetalleejecucionplanificacioncalidadList() == null) {
            procesocalidad.setDetalleejecucionplanificacioncalidadList(new ArrayList<Detalleejecucionplanificacioncalidad>());
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
            List<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadList = new ArrayList<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach : procesocalidad.getDetalleplanprocesoscalidadList()) {
                detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadList.add(detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach);
            }
            procesocalidad.setDetalleplanprocesoscalidadList(attachedDetalleplanprocesoscalidadList);
            List<Maquinaxprocesocalidad> attachedMaquinaxprocesocalidadList = new ArrayList<Maquinaxprocesocalidad>();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadListMaquinaxprocesocalidadToAttach : procesocalidad.getMaquinaxprocesocalidadList()) {
                maquinaxprocesocalidadListMaquinaxprocesocalidadToAttach = em.getReference(maquinaxprocesocalidadListMaquinaxprocesocalidadToAttach.getClass(), maquinaxprocesocalidadListMaquinaxprocesocalidadToAttach.getMaquinaxprocesocalidadPK());
                attachedMaquinaxprocesocalidadList.add(maquinaxprocesocalidadListMaquinaxprocesocalidadToAttach);
            }
            procesocalidad.setMaquinaxprocesocalidadList(attachedMaquinaxprocesocalidadList);
            List<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoList = new ArrayList<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach : procesocalidad.getDetallepiezacalidadpresupuestoList()) {
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoList.add(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuestoToAttach);
            }
            procesocalidad.setDetallepiezacalidadpresupuestoList(attachedDetallepiezacalidadpresupuestoList);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadList = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach : procesocalidad.getDetalleplanificacioncalidadList()) {
                detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadList.add(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach);
            }
            procesocalidad.setDetalleplanificacioncalidadList(attachedDetalleplanificacioncalidadList);
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadList = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach : procesocalidad.getDetalleejecucionplanificacioncalidadList()) {
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleejecucionplanificacioncalidadList.add(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach);
            }
            procesocalidad.setDetalleejecucionplanificacioncalidadList(attachedDetalleejecucionplanificacioncalidadList);
            em.persist(procesocalidad);
            if (accioncalidad != null) {
                accioncalidad.getProcesocalidadList().add(procesocalidad);
                accioncalidad = em.merge(accioncalidad);
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListDetalleplanprocesoscalidad : procesocalidad.getDetalleplanprocesoscalidadList()) {
                Procesocalidad oldIdprocesocalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad = detalleplanprocesoscalidadListDetalleplanprocesoscalidad.getIdprocesocalidad();
                detalleplanprocesoscalidadListDetalleplanprocesoscalidad.setIdprocesocalidad(procesocalidad);
                detalleplanprocesoscalidadListDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadListDetalleplanprocesoscalidad);
                if (oldIdprocesocalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad != null) {
                    oldIdprocesocalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadList().remove(detalleplanprocesoscalidadListDetalleplanprocesoscalidad);
                    oldIdprocesocalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad = em.merge(oldIdprocesocalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad);
                }
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadListMaquinaxprocesocalidad : procesocalidad.getMaquinaxprocesocalidadList()) {
                Procesocalidad oldProcesocalidadOfMaquinaxprocesocalidadListMaquinaxprocesocalidad = maquinaxprocesocalidadListMaquinaxprocesocalidad.getProcesocalidad();
                maquinaxprocesocalidadListMaquinaxprocesocalidad.setProcesocalidad(procesocalidad);
                maquinaxprocesocalidadListMaquinaxprocesocalidad = em.merge(maquinaxprocesocalidadListMaquinaxprocesocalidad);
                if (oldProcesocalidadOfMaquinaxprocesocalidadListMaquinaxprocesocalidad != null) {
                    oldProcesocalidadOfMaquinaxprocesocalidadListMaquinaxprocesocalidad.getMaquinaxprocesocalidadList().remove(maquinaxprocesocalidadListMaquinaxprocesocalidad);
                    oldProcesocalidadOfMaquinaxprocesocalidadListMaquinaxprocesocalidad = em.merge(oldProcesocalidadOfMaquinaxprocesocalidadListMaquinaxprocesocalidad);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto : procesocalidad.getDetallepiezacalidadpresupuestoList()) {
                Procesocalidad oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto.getIdprocesocalidad();
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto.setIdprocesocalidad(procesocalidad);
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto);
                if (oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto != null) {
                    oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoList().remove(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto);
                    oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto = em.merge(oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : procesocalidad.getDetalleplanificacioncalidadList()) {
                Procesocalidad oldProcesocalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = detalleplanificacioncalidadListDetalleplanificacioncalidad.getProcesocalidad();
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setProcesocalidad(procesocalidad);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                if (oldProcesocalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad != null) {
                    oldProcesocalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                    oldProcesocalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(oldProcesocalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad : procesocalidad.getDetalleejecucionplanificacioncalidadList()) {
                Procesocalidad oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getIdprocesocalidad();
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.setIdprocesocalidad(procesocalidad);
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                if (oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad != null) {
                    oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                    oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
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
            List<Detalleplanprocesoscalidad> detalleplanprocesoscalidadListOld = persistentProcesocalidad.getDetalleplanprocesoscalidadList();
            List<Detalleplanprocesoscalidad> detalleplanprocesoscalidadListNew = procesocalidad.getDetalleplanprocesoscalidadList();
            List<Maquinaxprocesocalidad> maquinaxprocesocalidadListOld = persistentProcesocalidad.getMaquinaxprocesocalidadList();
            List<Maquinaxprocesocalidad> maquinaxprocesocalidadListNew = procesocalidad.getMaquinaxprocesocalidadList();
            List<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoListOld = persistentProcesocalidad.getDetallepiezacalidadpresupuestoList();
            List<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoListNew = procesocalidad.getDetallepiezacalidadpresupuestoList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOld = persistentProcesocalidad.getDetalleplanificacioncalidadList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListNew = procesocalidad.getDetalleplanificacioncalidadList();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListOld = persistentProcesocalidad.getDetalleejecucionplanificacioncalidadList();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListNew = procesocalidad.getDetalleejecucionplanificacioncalidadList();
            List<String> illegalOrphanMessages = null;
            for (Maquinaxprocesocalidad maquinaxprocesocalidadListOldMaquinaxprocesocalidad : maquinaxprocesocalidadListOld) {
                if (!maquinaxprocesocalidadListNew.contains(maquinaxprocesocalidadListOldMaquinaxprocesocalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Maquinaxprocesocalidad " + maquinaxprocesocalidadListOldMaquinaxprocesocalidad + " since its procesocalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accioncalidadNew != null) {
                accioncalidadNew = em.getReference(accioncalidadNew.getClass(), accioncalidadNew.getIdaccioncalidad());
                procesocalidad.setAccioncalidad(accioncalidadNew);
            }
            List<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadListNew = new ArrayList<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach : detalleplanprocesoscalidadListNew) {
                detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadListNew.add(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach);
            }
            detalleplanprocesoscalidadListNew = attachedDetalleplanprocesoscalidadListNew;
            procesocalidad.setDetalleplanprocesoscalidadList(detalleplanprocesoscalidadListNew);
            List<Maquinaxprocesocalidad> attachedMaquinaxprocesocalidadListNew = new ArrayList<Maquinaxprocesocalidad>();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadListNewMaquinaxprocesocalidadToAttach : maquinaxprocesocalidadListNew) {
                maquinaxprocesocalidadListNewMaquinaxprocesocalidadToAttach = em.getReference(maquinaxprocesocalidadListNewMaquinaxprocesocalidadToAttach.getClass(), maquinaxprocesocalidadListNewMaquinaxprocesocalidadToAttach.getMaquinaxprocesocalidadPK());
                attachedMaquinaxprocesocalidadListNew.add(maquinaxprocesocalidadListNewMaquinaxprocesocalidadToAttach);
            }
            maquinaxprocesocalidadListNew = attachedMaquinaxprocesocalidadListNew;
            procesocalidad.setMaquinaxprocesocalidadList(maquinaxprocesocalidadListNew);
            List<Detallepiezacalidadpresupuesto> attachedDetallepiezacalidadpresupuestoListNew = new ArrayList<Detallepiezacalidadpresupuesto>();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach : detallepiezacalidadpresupuestoListNew) {
                detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach = em.getReference(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach.getClass(), detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach.getIddetalle());
                attachedDetallepiezacalidadpresupuestoListNew.add(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuestoToAttach);
            }
            detallepiezacalidadpresupuestoListNew = attachedDetallepiezacalidadpresupuestoListNew;
            procesocalidad.setDetallepiezacalidadpresupuestoList(detallepiezacalidadpresupuestoListNew);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadListNew = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadListNew) {
                detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadListNew.add(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadListNew = attachedDetalleplanificacioncalidadListNew;
            procesocalidad.setDetalleplanificacioncalidadList(detalleplanificacioncalidadListNew);
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadListNew = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadListNew) {
                detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleejecucionplanificacioncalidadListNew.add(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadListNew = attachedDetalleejecucionplanificacioncalidadListNew;
            procesocalidad.setDetalleejecucionplanificacioncalidadList(detalleejecucionplanificacioncalidadListNew);
            procesocalidad = em.merge(procesocalidad);
            if (accioncalidadOld != null && !accioncalidadOld.equals(accioncalidadNew)) {
                accioncalidadOld.getProcesocalidadList().remove(procesocalidad);
                accioncalidadOld = em.merge(accioncalidadOld);
            }
            if (accioncalidadNew != null && !accioncalidadNew.equals(accioncalidadOld)) {
                accioncalidadNew.getProcesocalidadList().add(procesocalidad);
                accioncalidadNew = em.merge(accioncalidadNew);
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListOldDetalleplanprocesoscalidad : detalleplanprocesoscalidadListOld) {
                if (!detalleplanprocesoscalidadListNew.contains(detalleplanprocesoscalidadListOldDetalleplanprocesoscalidad)) {
                    detalleplanprocesoscalidadListOldDetalleplanprocesoscalidad.setIdprocesocalidad(null);
                    detalleplanprocesoscalidadListOldDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadListOldDetalleplanprocesoscalidad);
                }
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad : detalleplanprocesoscalidadListNew) {
                if (!detalleplanprocesoscalidadListOld.contains(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad)) {
                    Procesocalidad oldIdprocesocalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad = detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad.getIdprocesocalidad();
                    detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad.setIdprocesocalidad(procesocalidad);
                    detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad);
                    if (oldIdprocesocalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad != null && !oldIdprocesocalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad.equals(procesocalidad)) {
                        oldIdprocesocalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadList().remove(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad);
                        oldIdprocesocalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad = em.merge(oldIdprocesocalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad);
                    }
                }
            }
            for (Maquinaxprocesocalidad maquinaxprocesocalidadListNewMaquinaxprocesocalidad : maquinaxprocesocalidadListNew) {
                if (!maquinaxprocesocalidadListOld.contains(maquinaxprocesocalidadListNewMaquinaxprocesocalidad)) {
                    Procesocalidad oldProcesocalidadOfMaquinaxprocesocalidadListNewMaquinaxprocesocalidad = maquinaxprocesocalidadListNewMaquinaxprocesocalidad.getProcesocalidad();
                    maquinaxprocesocalidadListNewMaquinaxprocesocalidad.setProcesocalidad(procesocalidad);
                    maquinaxprocesocalidadListNewMaquinaxprocesocalidad = em.merge(maquinaxprocesocalidadListNewMaquinaxprocesocalidad);
                    if (oldProcesocalidadOfMaquinaxprocesocalidadListNewMaquinaxprocesocalidad != null && !oldProcesocalidadOfMaquinaxprocesocalidadListNewMaquinaxprocesocalidad.equals(procesocalidad)) {
                        oldProcesocalidadOfMaquinaxprocesocalidadListNewMaquinaxprocesocalidad.getMaquinaxprocesocalidadList().remove(maquinaxprocesocalidadListNewMaquinaxprocesocalidad);
                        oldProcesocalidadOfMaquinaxprocesocalidadListNewMaquinaxprocesocalidad = em.merge(oldProcesocalidadOfMaquinaxprocesocalidadListNewMaquinaxprocesocalidad);
                    }
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoListOld) {
                if (!detallepiezacalidadpresupuestoListNew.contains(detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto)) {
                    detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto.setIdprocesocalidad(null);
                    detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoListOldDetallepiezacalidadpresupuesto);
                }
            }
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoListNew) {
                if (!detallepiezacalidadpresupuestoListOld.contains(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto)) {
                    Procesocalidad oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto = detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto.getIdprocesocalidad();
                    detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto.setIdprocesocalidad(procesocalidad);
                    detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto);
                    if (oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto != null && !oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto.equals(procesocalidad)) {
                        oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto.getDetallepiezacalidadpresupuestoList().remove(detallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto);
                        oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto = em.merge(oldIdprocesocalidadOfDetallepiezacalidadpresupuestoListNewDetallepiezacalidadpresupuesto);
                    }
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOldDetalleplanificacioncalidad : detalleplanificacioncalidadListOld) {
                if (!detalleplanificacioncalidadListNew.contains(detalleplanificacioncalidadListOldDetalleplanificacioncalidad)) {
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad.setProcesocalidad(null);
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListOldDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidad : detalleplanificacioncalidadListNew) {
                if (!detalleplanificacioncalidadListOld.contains(detalleplanificacioncalidadListNewDetalleplanificacioncalidad)) {
                    Procesocalidad oldProcesocalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = detalleplanificacioncalidadListNewDetalleplanificacioncalidad.getProcesocalidad();
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad.setProcesocalidad(procesocalidad);
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    if (oldProcesocalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad != null && !oldProcesocalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.equals(procesocalidad)) {
                        oldProcesocalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                        oldProcesocalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(oldProcesocalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    }
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListOld) {
                if (!detalleejecucionplanificacioncalidadListNew.contains(detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad)) {
                    detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad.setIdprocesocalidad(null);
                    detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad);
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListNew) {
                if (!detalleejecucionplanificacioncalidadListOld.contains(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad)) {
                    Procesocalidad oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getIdprocesocalidad();
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.setIdprocesocalidad(procesocalidad);
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                    if (oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad != null && !oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.equals(procesocalidad)) {
                        oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                        oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(oldIdprocesocalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
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
            List<Maquinaxprocesocalidad> maquinaxprocesocalidadListOrphanCheck = procesocalidad.getMaquinaxprocesocalidadList();
            for (Maquinaxprocesocalidad maquinaxprocesocalidadListOrphanCheckMaquinaxprocesocalidad : maquinaxprocesocalidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procesocalidad (" + procesocalidad + ") cannot be destroyed since the Maquinaxprocesocalidad " + maquinaxprocesocalidadListOrphanCheckMaquinaxprocesocalidad + " in its maquinaxprocesocalidadList field has a non-nullable procesocalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Accioncalidad accioncalidad = procesocalidad.getAccioncalidad();
            if (accioncalidad != null) {
                accioncalidad.getProcesocalidadList().remove(procesocalidad);
                accioncalidad = em.merge(accioncalidad);
            }
            List<Detalleplanprocesoscalidad> detalleplanprocesoscalidadList = procesocalidad.getDetalleplanprocesoscalidadList();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListDetalleplanprocesoscalidad : detalleplanprocesoscalidadList) {
                detalleplanprocesoscalidadListDetalleplanprocesoscalidad.setIdprocesocalidad(null);
                detalleplanprocesoscalidadListDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadListDetalleplanprocesoscalidad);
            }
            List<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoList = procesocalidad.getDetallepiezacalidadpresupuestoList();
            for (Detallepiezacalidadpresupuesto detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto : detallepiezacalidadpresupuestoList) {
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto.setIdprocesocalidad(null);
                detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto = em.merge(detallepiezacalidadpresupuestoListDetallepiezacalidadpresupuesto);
            }
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadList = procesocalidad.getDetalleplanificacioncalidadList();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : detalleplanificacioncalidadList) {
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setProcesocalidad(null);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
            }
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList = procesocalidad.getDetalleejecucionplanificacioncalidadList();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadList) {
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.setIdprocesocalidad(null);
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
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
