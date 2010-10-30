/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Tiporeclamo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Reclamoproveedor;
import java.util.HashSet;
import java.util.Set;
import entity.Reclamoempresametalurgica;
import entity.Reclamocliente;

/**
 *
 * @author Nino
 */
public class TiporeclamoJpaController {

    public TiporeclamoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiporeclamo tiporeclamo) throws PreexistingEntityException, Exception {
        if (tiporeclamo.getReclamoproveedorSet() == null) {
            tiporeclamo.setReclamoproveedorSet(new HashSet<Reclamoproveedor>());
        }
        if (tiporeclamo.getReclamoproveedorSet1() == null) {
            tiporeclamo.setReclamoproveedorSet1(new HashSet<Reclamoproveedor>());
        }
        if (tiporeclamo.getReclamoempresametalurgicaSet() == null) {
            tiporeclamo.setReclamoempresametalurgicaSet(new HashSet<Reclamoempresametalurgica>());
        }
        if (tiporeclamo.getReclamoempresametalurgicaSet1() == null) {
            tiporeclamo.setReclamoempresametalurgicaSet1(new HashSet<Reclamoempresametalurgica>());
        }
        if (tiporeclamo.getReclamoclienteSet() == null) {
            tiporeclamo.setReclamoclienteSet(new HashSet<Reclamocliente>());
        }
        if (tiporeclamo.getReclamoclienteSet1() == null) {
            tiporeclamo.setReclamoclienteSet1(new HashSet<Reclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Reclamoproveedor> attachedReclamoproveedorSet = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedorToAttach : tiporeclamo.getReclamoproveedorSet()) {
                reclamoproveedorSetReclamoproveedorToAttach = em.getReference(reclamoproveedorSetReclamoproveedorToAttach.getClass(), reclamoproveedorSetReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSet.add(reclamoproveedorSetReclamoproveedorToAttach);
            }
            tiporeclamo.setReclamoproveedorSet(attachedReclamoproveedorSet);
            Set<Reclamoproveedor> attachedReclamoproveedorSet1 = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSet1ReclamoproveedorToAttach : tiporeclamo.getReclamoproveedorSet1()) {
                reclamoproveedorSet1ReclamoproveedorToAttach = em.getReference(reclamoproveedorSet1ReclamoproveedorToAttach.getClass(), reclamoproveedorSet1ReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSet1.add(reclamoproveedorSet1ReclamoproveedorToAttach);
            }
            tiporeclamo.setReclamoproveedorSet1(attachedReclamoproveedorSet1);
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSet = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach : tiporeclamo.getReclamoempresametalurgicaSet()) {
                reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSet.add(reclamoempresametalurgicaSetReclamoempresametalurgicaToAttach);
            }
            tiporeclamo.setReclamoempresametalurgicaSet(attachedReclamoempresametalurgicaSet);
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSet1 = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach : tiporeclamo.getReclamoempresametalurgicaSet1()) {
                reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSet1.add(reclamoempresametalurgicaSet1ReclamoempresametalurgicaToAttach);
            }
            tiporeclamo.setReclamoempresametalurgicaSet1(attachedReclamoempresametalurgicaSet1);
            Set<Reclamocliente> attachedReclamoclienteSet = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSetReclamoclienteToAttach : tiporeclamo.getReclamoclienteSet()) {
                reclamoclienteSetReclamoclienteToAttach = em.getReference(reclamoclienteSetReclamoclienteToAttach.getClass(), reclamoclienteSetReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSet.add(reclamoclienteSetReclamoclienteToAttach);
            }
            tiporeclamo.setReclamoclienteSet(attachedReclamoclienteSet);
            Set<Reclamocliente> attachedReclamoclienteSet1 = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSet1ReclamoclienteToAttach : tiporeclamo.getReclamoclienteSet1()) {
                reclamoclienteSet1ReclamoclienteToAttach = em.getReference(reclamoclienteSet1ReclamoclienteToAttach.getClass(), reclamoclienteSet1ReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSet1.add(reclamoclienteSet1ReclamoclienteToAttach);
            }
            tiporeclamo.setReclamoclienteSet1(attachedReclamoclienteSet1);
            em.persist(tiporeclamo);
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedor : tiporeclamo.getReclamoproveedorSet()) {
                Tiporeclamo oldTiporeclamoOfReclamoproveedorSetReclamoproveedor = reclamoproveedorSetReclamoproveedor.getTiporeclamo();
                reclamoproveedorSetReclamoproveedor.setTiporeclamo(tiporeclamo);
                reclamoproveedorSetReclamoproveedor = em.merge(reclamoproveedorSetReclamoproveedor);
                if (oldTiporeclamoOfReclamoproveedorSetReclamoproveedor != null) {
                    oldTiporeclamoOfReclamoproveedorSetReclamoproveedor.getReclamoproveedorSet().remove(reclamoproveedorSetReclamoproveedor);
                    oldTiporeclamoOfReclamoproveedorSetReclamoproveedor = em.merge(oldTiporeclamoOfReclamoproveedorSetReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorSet1Reclamoproveedor : tiporeclamo.getReclamoproveedorSet1()) {
                Tiporeclamo oldTiporeclamo1OfReclamoproveedorSet1Reclamoproveedor = reclamoproveedorSet1Reclamoproveedor.getTiporeclamo1();
                reclamoproveedorSet1Reclamoproveedor.setTiporeclamo1(tiporeclamo);
                reclamoproveedorSet1Reclamoproveedor = em.merge(reclamoproveedorSet1Reclamoproveedor);
                if (oldTiporeclamo1OfReclamoproveedorSet1Reclamoproveedor != null) {
                    oldTiporeclamo1OfReclamoproveedorSet1Reclamoproveedor.getReclamoproveedorSet1().remove(reclamoproveedorSet1Reclamoproveedor);
                    oldTiporeclamo1OfReclamoproveedorSet1Reclamoproveedor = em.merge(oldTiporeclamo1OfReclamoproveedorSet1Reclamoproveedor);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgica : tiporeclamo.getReclamoempresametalurgicaSet()) {
                Tiporeclamo oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica = reclamoempresametalurgicaSetReclamoempresametalurgica.getTiporeclamo();
                reclamoempresametalurgicaSetReclamoempresametalurgica.setTiporeclamo(tiporeclamo);
                reclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetReclamoempresametalurgica);
                if (oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica != null) {
                    oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgicaSetReclamoempresametalurgica);
                    oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(oldTiporeclamoOfReclamoempresametalurgicaSetReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1Reclamoempresametalurgica : tiporeclamo.getReclamoempresametalurgicaSet1()) {
                Tiporeclamo oldTiporeclamo1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica = reclamoempresametalurgicaSet1Reclamoempresametalurgica.getTiporeclamo1();
                reclamoempresametalurgicaSet1Reclamoempresametalurgica.setTiporeclamo1(tiporeclamo);
                reclamoempresametalurgicaSet1Reclamoempresametalurgica = em.merge(reclamoempresametalurgicaSet1Reclamoempresametalurgica);
                if (oldTiporeclamo1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica != null) {
                    oldTiporeclamo1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica.getReclamoempresametalurgicaSet1().remove(reclamoempresametalurgicaSet1Reclamoempresametalurgica);
                    oldTiporeclamo1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica = em.merge(oldTiporeclamo1OfReclamoempresametalurgicaSet1Reclamoempresametalurgica);
                }
            }
            for (Reclamocliente reclamoclienteSetReclamocliente : tiporeclamo.getReclamoclienteSet()) {
                Tiporeclamo oldTiporeclamoOfReclamoclienteSetReclamocliente = reclamoclienteSetReclamocliente.getTiporeclamo();
                reclamoclienteSetReclamocliente.setTiporeclamo(tiporeclamo);
                reclamoclienteSetReclamocliente = em.merge(reclamoclienteSetReclamocliente);
                if (oldTiporeclamoOfReclamoclienteSetReclamocliente != null) {
                    oldTiporeclamoOfReclamoclienteSetReclamocliente.getReclamoclienteSet().remove(reclamoclienteSetReclamocliente);
                    oldTiporeclamoOfReclamoclienteSetReclamocliente = em.merge(oldTiporeclamoOfReclamoclienteSetReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteSet1Reclamocliente : tiporeclamo.getReclamoclienteSet1()) {
                Tiporeclamo oldTiporeclamo1OfReclamoclienteSet1Reclamocliente = reclamoclienteSet1Reclamocliente.getTiporeclamo1();
                reclamoclienteSet1Reclamocliente.setTiporeclamo1(tiporeclamo);
                reclamoclienteSet1Reclamocliente = em.merge(reclamoclienteSet1Reclamocliente);
                if (oldTiporeclamo1OfReclamoclienteSet1Reclamocliente != null) {
                    oldTiporeclamo1OfReclamoclienteSet1Reclamocliente.getReclamoclienteSet1().remove(reclamoclienteSet1Reclamocliente);
                    oldTiporeclamo1OfReclamoclienteSet1Reclamocliente = em.merge(oldTiporeclamo1OfReclamoclienteSet1Reclamocliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiporeclamo(tiporeclamo.getIdtiporeclamo()) != null) {
                throw new PreexistingEntityException("Tiporeclamo " + tiporeclamo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiporeclamo tiporeclamo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporeclamo persistentTiporeclamo = em.find(Tiporeclamo.class, tiporeclamo.getIdtiporeclamo());
            Set<Reclamoproveedor> reclamoproveedorSetOld = persistentTiporeclamo.getReclamoproveedorSet();
            Set<Reclamoproveedor> reclamoproveedorSetNew = tiporeclamo.getReclamoproveedorSet();
            Set<Reclamoproveedor> reclamoproveedorSet1Old = persistentTiporeclamo.getReclamoproveedorSet1();
            Set<Reclamoproveedor> reclamoproveedorSet1New = tiporeclamo.getReclamoproveedorSet1();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSetOld = persistentTiporeclamo.getReclamoempresametalurgicaSet();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSetNew = tiporeclamo.getReclamoempresametalurgicaSet();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1Old = persistentTiporeclamo.getReclamoempresametalurgicaSet1();
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1New = tiporeclamo.getReclamoempresametalurgicaSet1();
            Set<Reclamocliente> reclamoclienteSetOld = persistentTiporeclamo.getReclamoclienteSet();
            Set<Reclamocliente> reclamoclienteSetNew = tiporeclamo.getReclamoclienteSet();
            Set<Reclamocliente> reclamoclienteSet1Old = persistentTiporeclamo.getReclamoclienteSet1();
            Set<Reclamocliente> reclamoclienteSet1New = tiporeclamo.getReclamoclienteSet1();
            Set<Reclamoproveedor> attachedReclamoproveedorSetNew = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSetNewReclamoproveedorToAttach : reclamoproveedorSetNew) {
                reclamoproveedorSetNewReclamoproveedorToAttach = em.getReference(reclamoproveedorSetNewReclamoproveedorToAttach.getClass(), reclamoproveedorSetNewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSetNew.add(reclamoproveedorSetNewReclamoproveedorToAttach);
            }
            reclamoproveedorSetNew = attachedReclamoproveedorSetNew;
            tiporeclamo.setReclamoproveedorSet(reclamoproveedorSetNew);
            Set<Reclamoproveedor> attachedReclamoproveedorSet1New = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSet1NewReclamoproveedorToAttach : reclamoproveedorSet1New) {
                reclamoproveedorSet1NewReclamoproveedorToAttach = em.getReference(reclamoproveedorSet1NewReclamoproveedorToAttach.getClass(), reclamoproveedorSet1NewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSet1New.add(reclamoproveedorSet1NewReclamoproveedorToAttach);
            }
            reclamoproveedorSet1New = attachedReclamoproveedorSet1New;
            tiporeclamo.setReclamoproveedorSet1(reclamoproveedorSet1New);
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSetNew = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaSetNew) {
                reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSetNew.add(reclamoempresametalurgicaSetNewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaSetNew = attachedReclamoempresametalurgicaSetNew;
            tiporeclamo.setReclamoempresametalurgicaSet(reclamoempresametalurgicaSetNew);
            Set<Reclamoempresametalurgica> attachedReclamoempresametalurgicaSet1New = new HashSet<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaSet1New) {
                reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaSet1New.add(reclamoempresametalurgicaSet1NewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaSet1New = attachedReclamoempresametalurgicaSet1New;
            tiporeclamo.setReclamoempresametalurgicaSet1(reclamoempresametalurgicaSet1New);
            Set<Reclamocliente> attachedReclamoclienteSetNew = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSetNewReclamoclienteToAttach : reclamoclienteSetNew) {
                reclamoclienteSetNewReclamoclienteToAttach = em.getReference(reclamoclienteSetNewReclamoclienteToAttach.getClass(), reclamoclienteSetNewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSetNew.add(reclamoclienteSetNewReclamoclienteToAttach);
            }
            reclamoclienteSetNew = attachedReclamoclienteSetNew;
            tiporeclamo.setReclamoclienteSet(reclamoclienteSetNew);
            Set<Reclamocliente> attachedReclamoclienteSet1New = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSet1NewReclamoclienteToAttach : reclamoclienteSet1New) {
                reclamoclienteSet1NewReclamoclienteToAttach = em.getReference(reclamoclienteSet1NewReclamoclienteToAttach.getClass(), reclamoclienteSet1NewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSet1New.add(reclamoclienteSet1NewReclamoclienteToAttach);
            }
            reclamoclienteSet1New = attachedReclamoclienteSet1New;
            tiporeclamo.setReclamoclienteSet1(reclamoclienteSet1New);
            tiporeclamo = em.merge(tiporeclamo);
            for (Reclamoproveedor reclamoproveedorSetOldReclamoproveedor : reclamoproveedorSetOld) {
                if (!reclamoproveedorSetNew.contains(reclamoproveedorSetOldReclamoproveedor)) {
                    reclamoproveedorSetOldReclamoproveedor.setTiporeclamo(null);
                    reclamoproveedorSetOldReclamoproveedor = em.merge(reclamoproveedorSetOldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorSetNewReclamoproveedor : reclamoproveedorSetNew) {
                if (!reclamoproveedorSetOld.contains(reclamoproveedorSetNewReclamoproveedor)) {
                    Tiporeclamo oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor = reclamoproveedorSetNewReclamoproveedor.getTiporeclamo();
                    reclamoproveedorSetNewReclamoproveedor.setTiporeclamo(tiporeclamo);
                    reclamoproveedorSetNewReclamoproveedor = em.merge(reclamoproveedorSetNewReclamoproveedor);
                    if (oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor != null && !oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor.getReclamoproveedorSet().remove(reclamoproveedorSetNewReclamoproveedor);
                        oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor = em.merge(oldTiporeclamoOfReclamoproveedorSetNewReclamoproveedor);
                    }
                }
            }
            for (Reclamoproveedor reclamoproveedorSet1OldReclamoproveedor : reclamoproveedorSet1Old) {
                if (!reclamoproveedorSet1New.contains(reclamoproveedorSet1OldReclamoproveedor)) {
                    reclamoproveedorSet1OldReclamoproveedor.setTiporeclamo1(null);
                    reclamoproveedorSet1OldReclamoproveedor = em.merge(reclamoproveedorSet1OldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorSet1NewReclamoproveedor : reclamoproveedorSet1New) {
                if (!reclamoproveedorSet1Old.contains(reclamoproveedorSet1NewReclamoproveedor)) {
                    Tiporeclamo oldTiporeclamo1OfReclamoproveedorSet1NewReclamoproveedor = reclamoproveedorSet1NewReclamoproveedor.getTiporeclamo1();
                    reclamoproveedorSet1NewReclamoproveedor.setTiporeclamo1(tiporeclamo);
                    reclamoproveedorSet1NewReclamoproveedor = em.merge(reclamoproveedorSet1NewReclamoproveedor);
                    if (oldTiporeclamo1OfReclamoproveedorSet1NewReclamoproveedor != null && !oldTiporeclamo1OfReclamoproveedorSet1NewReclamoproveedor.equals(tiporeclamo)) {
                        oldTiporeclamo1OfReclamoproveedorSet1NewReclamoproveedor.getReclamoproveedorSet1().remove(reclamoproveedorSet1NewReclamoproveedor);
                        oldTiporeclamo1OfReclamoproveedorSet1NewReclamoproveedor = em.merge(oldTiporeclamo1OfReclamoproveedorSet1NewReclamoproveedor);
                    }
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetOldReclamoempresametalurgica : reclamoempresametalurgicaSetOld) {
                if (!reclamoempresametalurgicaSetNew.contains(reclamoempresametalurgicaSetOldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaSetOldReclamoempresametalurgica.setTiporeclamo(null);
                    reclamoempresametalurgicaSetOldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetOldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetNewReclamoempresametalurgica : reclamoempresametalurgicaSetNew) {
                if (!reclamoempresametalurgicaSetOld.contains(reclamoempresametalurgicaSetNewReclamoempresametalurgica)) {
                    Tiporeclamo oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica = reclamoempresametalurgicaSetNewReclamoempresametalurgica.getTiporeclamo();
                    reclamoempresametalurgicaSetNewReclamoempresametalurgica.setTiporeclamo(tiporeclamo);
                    reclamoempresametalurgicaSetNewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetNewReclamoempresametalurgica);
                    if (oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica != null && !oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica.getReclamoempresametalurgicaSet().remove(reclamoempresametalurgicaSetNewReclamoempresametalurgica);
                        oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica = em.merge(oldTiporeclamoOfReclamoempresametalurgicaSetNewReclamoempresametalurgica);
                    }
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1OldReclamoempresametalurgica : reclamoempresametalurgicaSet1Old) {
                if (!reclamoempresametalurgicaSet1New.contains(reclamoempresametalurgicaSet1OldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaSet1OldReclamoempresametalurgica.setTiporeclamo1(null);
                    reclamoempresametalurgicaSet1OldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSet1OldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1NewReclamoempresametalurgica : reclamoempresametalurgicaSet1New) {
                if (!reclamoempresametalurgicaSet1Old.contains(reclamoempresametalurgicaSet1NewReclamoempresametalurgica)) {
                    Tiporeclamo oldTiporeclamo1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica = reclamoempresametalurgicaSet1NewReclamoempresametalurgica.getTiporeclamo1();
                    reclamoempresametalurgicaSet1NewReclamoempresametalurgica.setTiporeclamo1(tiporeclamo);
                    reclamoempresametalurgicaSet1NewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSet1NewReclamoempresametalurgica);
                    if (oldTiporeclamo1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica != null && !oldTiporeclamo1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica.equals(tiporeclamo)) {
                        oldTiporeclamo1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica.getReclamoempresametalurgicaSet1().remove(reclamoempresametalurgicaSet1NewReclamoempresametalurgica);
                        oldTiporeclamo1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica = em.merge(oldTiporeclamo1OfReclamoempresametalurgicaSet1NewReclamoempresametalurgica);
                    }
                }
            }
            for (Reclamocliente reclamoclienteSetOldReclamocliente : reclamoclienteSetOld) {
                if (!reclamoclienteSetNew.contains(reclamoclienteSetOldReclamocliente)) {
                    reclamoclienteSetOldReclamocliente.setTiporeclamo(null);
                    reclamoclienteSetOldReclamocliente = em.merge(reclamoclienteSetOldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteSetNewReclamocliente : reclamoclienteSetNew) {
                if (!reclamoclienteSetOld.contains(reclamoclienteSetNewReclamocliente)) {
                    Tiporeclamo oldTiporeclamoOfReclamoclienteSetNewReclamocliente = reclamoclienteSetNewReclamocliente.getTiporeclamo();
                    reclamoclienteSetNewReclamocliente.setTiporeclamo(tiporeclamo);
                    reclamoclienteSetNewReclamocliente = em.merge(reclamoclienteSetNewReclamocliente);
                    if (oldTiporeclamoOfReclamoclienteSetNewReclamocliente != null && !oldTiporeclamoOfReclamoclienteSetNewReclamocliente.equals(tiporeclamo)) {
                        oldTiporeclamoOfReclamoclienteSetNewReclamocliente.getReclamoclienteSet().remove(reclamoclienteSetNewReclamocliente);
                        oldTiporeclamoOfReclamoclienteSetNewReclamocliente = em.merge(oldTiporeclamoOfReclamoclienteSetNewReclamocliente);
                    }
                }
            }
            for (Reclamocliente reclamoclienteSet1OldReclamocliente : reclamoclienteSet1Old) {
                if (!reclamoclienteSet1New.contains(reclamoclienteSet1OldReclamocliente)) {
                    reclamoclienteSet1OldReclamocliente.setTiporeclamo1(null);
                    reclamoclienteSet1OldReclamocliente = em.merge(reclamoclienteSet1OldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteSet1NewReclamocliente : reclamoclienteSet1New) {
                if (!reclamoclienteSet1Old.contains(reclamoclienteSet1NewReclamocliente)) {
                    Tiporeclamo oldTiporeclamo1OfReclamoclienteSet1NewReclamocliente = reclamoclienteSet1NewReclamocliente.getTiporeclamo1();
                    reclamoclienteSet1NewReclamocliente.setTiporeclamo1(tiporeclamo);
                    reclamoclienteSet1NewReclamocliente = em.merge(reclamoclienteSet1NewReclamocliente);
                    if (oldTiporeclamo1OfReclamoclienteSet1NewReclamocliente != null && !oldTiporeclamo1OfReclamoclienteSet1NewReclamocliente.equals(tiporeclamo)) {
                        oldTiporeclamo1OfReclamoclienteSet1NewReclamocliente.getReclamoclienteSet1().remove(reclamoclienteSet1NewReclamocliente);
                        oldTiporeclamo1OfReclamoclienteSet1NewReclamocliente = em.merge(oldTiporeclamo1OfReclamoclienteSet1NewReclamocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tiporeclamo.getIdtiporeclamo();
                if (findTiporeclamo(id) == null) {
                    throw new NonexistentEntityException("The tiporeclamo with id " + id + " no longer exists.");
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
            Tiporeclamo tiporeclamo;
            try {
                tiporeclamo = em.getReference(Tiporeclamo.class, id);
                tiporeclamo.getIdtiporeclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiporeclamo with id " + id + " no longer exists.", enfe);
            }
            Set<Reclamoproveedor> reclamoproveedorSet = tiporeclamo.getReclamoproveedorSet();
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedor : reclamoproveedorSet) {
                reclamoproveedorSetReclamoproveedor.setTiporeclamo(null);
                reclamoproveedorSetReclamoproveedor = em.merge(reclamoproveedorSetReclamoproveedor);
            }
            Set<Reclamoproveedor> reclamoproveedorSet1 = tiporeclamo.getReclamoproveedorSet1();
            for (Reclamoproveedor reclamoproveedorSet1Reclamoproveedor : reclamoproveedorSet1) {
                reclamoproveedorSet1Reclamoproveedor.setTiporeclamo1(null);
                reclamoproveedorSet1Reclamoproveedor = em.merge(reclamoproveedorSet1Reclamoproveedor);
            }
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet = tiporeclamo.getReclamoempresametalurgicaSet();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSetReclamoempresametalurgica : reclamoempresametalurgicaSet) {
                reclamoempresametalurgicaSetReclamoempresametalurgica.setTiporeclamo(null);
                reclamoempresametalurgicaSetReclamoempresametalurgica = em.merge(reclamoempresametalurgicaSetReclamoempresametalurgica);
            }
            Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1 = tiporeclamo.getReclamoempresametalurgicaSet1();
            for (Reclamoempresametalurgica reclamoempresametalurgicaSet1Reclamoempresametalurgica : reclamoempresametalurgicaSet1) {
                reclamoempresametalurgicaSet1Reclamoempresametalurgica.setTiporeclamo1(null);
                reclamoempresametalurgicaSet1Reclamoempresametalurgica = em.merge(reclamoempresametalurgicaSet1Reclamoempresametalurgica);
            }
            Set<Reclamocliente> reclamoclienteSet = tiporeclamo.getReclamoclienteSet();
            for (Reclamocliente reclamoclienteSetReclamocliente : reclamoclienteSet) {
                reclamoclienteSetReclamocliente.setTiporeclamo(null);
                reclamoclienteSetReclamocliente = em.merge(reclamoclienteSetReclamocliente);
            }
            Set<Reclamocliente> reclamoclienteSet1 = tiporeclamo.getReclamoclienteSet1();
            for (Reclamocliente reclamoclienteSet1Reclamocliente : reclamoclienteSet1) {
                reclamoclienteSet1Reclamocliente.setTiporeclamo1(null);
                reclamoclienteSet1Reclamocliente = em.merge(reclamoclienteSet1Reclamocliente);
            }
            em.remove(tiporeclamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiporeclamo> findTiporeclamoEntities() {
        return findTiporeclamoEntities(true, -1, -1);
    }

    public List<Tiporeclamo> findTiporeclamoEntities(int maxResults, int firstResult) {
        return findTiporeclamoEntities(false, maxResults, firstResult);
    }

    private List<Tiporeclamo> findTiporeclamoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiporeclamo.class));
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

    public Tiporeclamo findTiporeclamo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiporeclamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiporeclamoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiporeclamo> rt = cq.from(Tiporeclamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
