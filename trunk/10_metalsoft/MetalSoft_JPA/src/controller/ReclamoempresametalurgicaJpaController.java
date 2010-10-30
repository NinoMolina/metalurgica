/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Reclamoempresametalurgica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Tiporeclamo;
import entity.Trabajotercerizado;
import entity.Detallereclamoempresametalurgica;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ReclamoempresametalurgicaJpaController {

    public ReclamoempresametalurgicaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reclamoempresametalurgica reclamoempresametalurgica) throws PreexistingEntityException, Exception {
        if (reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet() == null) {
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet(new HashSet<Detallereclamoempresametalurgica>());
        }
        if (reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet1() == null) {
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet1(new HashSet<Detallereclamoempresametalurgica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporeclamo tiporeclamo = reclamoempresametalurgica.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo = em.getReference(tiporeclamo.getClass(), tiporeclamo.getIdtiporeclamo());
                reclamoempresametalurgica.setTiporeclamo(tiporeclamo);
            }
            Tiporeclamo tiporeclamo1 = reclamoempresametalurgica.getTiporeclamo1();
            if (tiporeclamo1 != null) {
                tiporeclamo1 = em.getReference(tiporeclamo1.getClass(), tiporeclamo1.getIdtiporeclamo());
                reclamoempresametalurgica.setTiporeclamo1(tiporeclamo1);
            }
            Trabajotercerizado trabajotercerizado = reclamoempresametalurgica.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado = em.getReference(trabajotercerizado.getClass(), trabajotercerizado.getIdtrabajo());
                reclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
            }
            Trabajotercerizado trabajotercerizado1 = reclamoempresametalurgica.getTrabajotercerizado1();
            if (trabajotercerizado1 != null) {
                trabajotercerizado1 = em.getReference(trabajotercerizado1.getClass(), trabajotercerizado1.getIdtrabajo());
                reclamoempresametalurgica.setTrabajotercerizado1(trabajotercerizado1);
            }
            Set<Detallereclamoempresametalurgica> attachedDetallereclamoempresametalurgicaSet = new HashSet<Detallereclamoempresametalurgica>();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach : reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet()) {
                detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach = em.getReference(detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach.getClass(), detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach.getDetallereclamoempresametalurgicaPK());
                attachedDetallereclamoempresametalurgicaSet.add(detallereclamoempresametalurgicaSetDetallereclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet(attachedDetallereclamoempresametalurgicaSet);
            Set<Detallereclamoempresametalurgica> attachedDetallereclamoempresametalurgicaSet1 = new HashSet<Detallereclamoempresametalurgica>();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSet1DetallereclamoempresametalurgicaToAttach : reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet1()) {
                detallereclamoempresametalurgicaSet1DetallereclamoempresametalurgicaToAttach = em.getReference(detallereclamoempresametalurgicaSet1DetallereclamoempresametalurgicaToAttach.getClass(), detallereclamoempresametalurgicaSet1DetallereclamoempresametalurgicaToAttach.getDetallereclamoempresametalurgicaPK());
                attachedDetallereclamoempresametalurgicaSet1.add(detallereclamoempresametalurgicaSet1DetallereclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet1(attachedDetallereclamoempresametalurgicaSet1);
            em.persist(reclamoempresametalurgica);
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                tiporeclamo = em.merge(tiporeclamo);
            }
            if (tiporeclamo1 != null) {
                tiporeclamo1.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                tiporeclamo1 = em.merge(tiporeclamo1);
            }
            if (trabajotercerizado != null) {
                trabajotercerizado.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            if (trabajotercerizado1 != null) {
                trabajotercerizado1.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                trabajotercerizado1 = em.merge(trabajotercerizado1);
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica : reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet()) {
                Reclamoempresametalurgica oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica = detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica.getReclamoempresametalurgica();
                detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica.setReclamoempresametalurgica(reclamoempresametalurgica);
                detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica);
                if (oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica != null) {
                    oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica.getDetallereclamoempresametalurgicaSet().remove(detallereclamoempresametalurgicaSetDetallereclamoempresametalurgica);
                    oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica = em.merge(oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetDetallereclamoempresametalurgica);
                }
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica : reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet1()) {
                Reclamoempresametalurgica oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica = detallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica.getReclamoempresametalurgica1();
                detallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica.setReclamoempresametalurgica1(reclamoempresametalurgica);
                detallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica);
                if (oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica != null) {
                    oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica.getDetallereclamoempresametalurgicaSet1().remove(detallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica);
                    oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica = em.merge(oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1Detallereclamoempresametalurgica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReclamoempresametalurgica(reclamoempresametalurgica.getIdreclamo()) != null) {
                throw new PreexistingEntityException("Reclamoempresametalurgica " + reclamoempresametalurgica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reclamoempresametalurgica reclamoempresametalurgica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamoempresametalurgica persistentReclamoempresametalurgica = em.find(Reclamoempresametalurgica.class, reclamoempresametalurgica.getIdreclamo());
            Tiporeclamo tiporeclamoOld = persistentReclamoempresametalurgica.getTiporeclamo();
            Tiporeclamo tiporeclamoNew = reclamoempresametalurgica.getTiporeclamo();
            Tiporeclamo tiporeclamo1Old = persistentReclamoempresametalurgica.getTiporeclamo1();
            Tiporeclamo tiporeclamo1New = reclamoempresametalurgica.getTiporeclamo1();
            Trabajotercerizado trabajotercerizadoOld = persistentReclamoempresametalurgica.getTrabajotercerizado();
            Trabajotercerizado trabajotercerizadoNew = reclamoempresametalurgica.getTrabajotercerizado();
            Trabajotercerizado trabajotercerizado1Old = persistentReclamoempresametalurgica.getTrabajotercerizado1();
            Trabajotercerizado trabajotercerizado1New = reclamoempresametalurgica.getTrabajotercerizado1();
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSetOld = persistentReclamoempresametalurgica.getDetallereclamoempresametalurgicaSet();
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSetNew = reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet();
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSet1Old = persistentReclamoempresametalurgica.getDetallereclamoempresametalurgicaSet1();
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSet1New = reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet1();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetOldDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSetOld) {
                if (!detallereclamoempresametalurgicaSetNew.contains(detallereclamoempresametalurgicaSetOldDetallereclamoempresametalurgica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoempresametalurgica " + detallereclamoempresametalurgicaSetOldDetallereclamoempresametalurgica + " since its reclamoempresametalurgica field is not nullable.");
                }
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSet1OldDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSet1Old) {
                if (!detallereclamoempresametalurgicaSet1New.contains(detallereclamoempresametalurgicaSet1OldDetallereclamoempresametalurgica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamoempresametalurgica " + detallereclamoempresametalurgicaSet1OldDetallereclamoempresametalurgica + " since its reclamoempresametalurgica1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tiporeclamoNew != null) {
                tiporeclamoNew = em.getReference(tiporeclamoNew.getClass(), tiporeclamoNew.getIdtiporeclamo());
                reclamoempresametalurgica.setTiporeclamo(tiporeclamoNew);
            }
            if (tiporeclamo1New != null) {
                tiporeclamo1New = em.getReference(tiporeclamo1New.getClass(), tiporeclamo1New.getIdtiporeclamo());
                reclamoempresametalurgica.setTiporeclamo1(tiporeclamo1New);
            }
            if (trabajotercerizadoNew != null) {
                trabajotercerizadoNew = em.getReference(trabajotercerizadoNew.getClass(), trabajotercerizadoNew.getIdtrabajo());
                reclamoempresametalurgica.setTrabajotercerizado(trabajotercerizadoNew);
            }
            if (trabajotercerizado1New != null) {
                trabajotercerizado1New = em.getReference(trabajotercerizado1New.getClass(), trabajotercerizado1New.getIdtrabajo());
                reclamoempresametalurgica.setTrabajotercerizado1(trabajotercerizado1New);
            }
            Set<Detallereclamoempresametalurgica> attachedDetallereclamoempresametalurgicaSetNew = new HashSet<Detallereclamoempresametalurgica>();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach : detallereclamoempresametalurgicaSetNew) {
                detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach = em.getReference(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach.getClass(), detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach.getDetallereclamoempresametalurgicaPK());
                attachedDetallereclamoempresametalurgicaSetNew.add(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgicaToAttach);
            }
            detallereclamoempresametalurgicaSetNew = attachedDetallereclamoempresametalurgicaSetNew;
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet(detallereclamoempresametalurgicaSetNew);
            Set<Detallereclamoempresametalurgica> attachedDetallereclamoempresametalurgicaSet1New = new HashSet<Detallereclamoempresametalurgica>();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgicaToAttach : detallereclamoempresametalurgicaSet1New) {
                detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgicaToAttach = em.getReference(detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgicaToAttach.getClass(), detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgicaToAttach.getDetallereclamoempresametalurgicaPK());
                attachedDetallereclamoempresametalurgicaSet1New.add(detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgicaToAttach);
            }
            detallereclamoempresametalurgicaSet1New = attachedDetallereclamoempresametalurgicaSet1New;
            reclamoempresametalurgica.setDetallereclamoempresametalurgicaSet1(detallereclamoempresametalurgicaSet1New);
            reclamoempresametalurgica = em.merge(reclamoempresametalurgica);
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            if (tiporeclamo1Old != null && !tiporeclamo1Old.equals(tiporeclamo1New)) {
                tiporeclamo1Old.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                tiporeclamo1Old = em.merge(tiporeclamo1Old);
            }
            if (tiporeclamo1New != null && !tiporeclamo1New.equals(tiporeclamo1Old)) {
                tiporeclamo1New.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                tiporeclamo1New = em.merge(tiporeclamo1New);
            }
            if (trabajotercerizadoOld != null && !trabajotercerizadoOld.equals(trabajotercerizadoNew)) {
                trabajotercerizadoOld.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                trabajotercerizadoOld = em.merge(trabajotercerizadoOld);
            }
            if (trabajotercerizadoNew != null && !trabajotercerizadoNew.equals(trabajotercerizadoOld)) {
                trabajotercerizadoNew.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                trabajotercerizadoNew = em.merge(trabajotercerizadoNew);
            }
            if (trabajotercerizado1Old != null && !trabajotercerizado1Old.equals(trabajotercerizado1New)) {
                trabajotercerizado1Old.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                trabajotercerizado1Old = em.merge(trabajotercerizado1Old);
            }
            if (trabajotercerizado1New != null && !trabajotercerizado1New.equals(trabajotercerizado1Old)) {
                trabajotercerizado1New.getReclamoempresametalurgicaSet().add(reclamoempresametalurgica);
                trabajotercerizado1New = em.merge(trabajotercerizado1New);
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSetNew) {
                if (!detallereclamoempresametalurgicaSetOld.contains(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica)) {
                    Reclamoempresametalurgica oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica = detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica.getReclamoempresametalurgica();
                    detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica.setReclamoempresametalurgica(reclamoempresametalurgica);
                    detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica);
                    if (oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica != null && !oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica.equals(reclamoempresametalurgica)) {
                        oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica.getDetallereclamoempresametalurgicaSet().remove(detallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica);
                        oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica = em.merge(oldReclamoempresametalurgicaOfDetallereclamoempresametalurgicaSetNewDetallereclamoempresametalurgica);
                    }
                }
            }
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSet1New) {
                if (!detallereclamoempresametalurgicaSet1Old.contains(detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica)) {
                    Reclamoempresametalurgica oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica = detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica.getReclamoempresametalurgica1();
                    detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica.setReclamoempresametalurgica1(reclamoempresametalurgica);
                    detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica);
                    if (oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica != null && !oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica.equals(reclamoempresametalurgica)) {
                        oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica.getDetallereclamoempresametalurgicaSet1().remove(detallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica);
                        oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica = em.merge(oldReclamoempresametalurgica1OfDetallereclamoempresametalurgicaSet1NewDetallereclamoempresametalurgica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reclamoempresametalurgica.getIdreclamo();
                if (findReclamoempresametalurgica(id) == null) {
                    throw new NonexistentEntityException("The reclamoempresametalurgica with id " + id + " no longer exists.");
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
            Reclamoempresametalurgica reclamoempresametalurgica;
            try {
                reclamoempresametalurgica = em.getReference(Reclamoempresametalurgica.class, id);
                reclamoempresametalurgica.getIdreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reclamoempresametalurgica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSetOrphanCheck = reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSetOrphanCheckDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamoempresametalurgica (" + reclamoempresametalurgica + ") cannot be destroyed since the Detallereclamoempresametalurgica " + detallereclamoempresametalurgicaSetOrphanCheckDetallereclamoempresametalurgica + " in its detallereclamoempresametalurgicaSet field has a non-nullable reclamoempresametalurgica field.");
            }
            Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSet1OrphanCheck = reclamoempresametalurgica.getDetallereclamoempresametalurgicaSet1();
            for (Detallereclamoempresametalurgica detallereclamoempresametalurgicaSet1OrphanCheckDetallereclamoempresametalurgica : detallereclamoempresametalurgicaSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamoempresametalurgica (" + reclamoempresametalurgica + ") cannot be destroyed since the Detallereclamoempresametalurgica " + detallereclamoempresametalurgicaSet1OrphanCheckDetallereclamoempresametalurgica + " in its detallereclamoempresametalurgicaSet1 field has a non-nullable reclamoempresametalurgica1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tiporeclamo tiporeclamo = reclamoempresametalurgica.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                tiporeclamo = em.merge(tiporeclamo);
            }
            Tiporeclamo tiporeclamo1 = reclamoempresametalurgica.getTiporeclamo1();
            if (tiporeclamo1 != null) {
                tiporeclamo1.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                tiporeclamo1 = em.merge(tiporeclamo1);
            }
            Trabajotercerizado trabajotercerizado = reclamoempresametalurgica.getTrabajotercerizado();
            if (trabajotercerizado != null) {
                trabajotercerizado.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                trabajotercerizado = em.merge(trabajotercerizado);
            }
            Trabajotercerizado trabajotercerizado1 = reclamoempresametalurgica.getTrabajotercerizado1();
            if (trabajotercerizado1 != null) {
                trabajotercerizado1.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgica);
                trabajotercerizado1 = em.merge(trabajotercerizado1);
            }
            em.remove(reclamoempresametalurgica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reclamoempresametalurgica> findReclamoempresametalurgicaEntities() {
        return findReclamoempresametalurgicaEntities(true, -1, -1);
    }

    public List<Reclamoempresametalurgica> findReclamoempresametalurgicaEntities(int maxResults, int firstResult) {
        return findReclamoempresametalurgicaEntities(false, maxResults, firstResult);
    }

    private List<Reclamoempresametalurgica> findReclamoempresametalurgicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reclamoempresametalurgica.class));
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

    public Reclamoempresametalurgica findReclamoempresametalurgica(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reclamoempresametalurgica.class, id);
        } finally {
            em.close();
        }
    }

    public int getReclamoempresametalurgicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reclamoempresametalurgica> rt = cq.from(Reclamoempresametalurgica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
