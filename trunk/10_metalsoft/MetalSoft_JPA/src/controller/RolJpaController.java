/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Rol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Usuarioxrol;
import java.util.HashSet;
import java.util.Set;
import entity.Rolxprivilegio;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class RolJpaController {

    public RolJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) throws PreexistingEntityException, Exception {
        if (rol.getUsuarioxrolSet() == null) {
            rol.setUsuarioxrolSet(new HashSet<Usuarioxrol>());
        }
        if (rol.getUsuarioxrolSet1() == null) {
            rol.setUsuarioxrolSet1(new HashSet<Usuarioxrol>());
        }
        if (rol.getRolxprivilegioSet() == null) {
            rol.setRolxprivilegioSet(new HashSet<Rolxprivilegio>());
        }
        if (rol.getRolxprivilegioSet1() == null) {
            rol.setRolxprivilegioSet1(new HashSet<Rolxprivilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Usuarioxrol> attachedUsuarioxrolSet = new HashSet<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolSetUsuarioxrolToAttach : rol.getUsuarioxrolSet()) {
                usuarioxrolSetUsuarioxrolToAttach = em.getReference(usuarioxrolSetUsuarioxrolToAttach.getClass(), usuarioxrolSetUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolSet.add(usuarioxrolSetUsuarioxrolToAttach);
            }
            rol.setUsuarioxrolSet(attachedUsuarioxrolSet);
            Set<Usuarioxrol> attachedUsuarioxrolSet1 = new HashSet<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolSet1UsuarioxrolToAttach : rol.getUsuarioxrolSet1()) {
                usuarioxrolSet1UsuarioxrolToAttach = em.getReference(usuarioxrolSet1UsuarioxrolToAttach.getClass(), usuarioxrolSet1UsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolSet1.add(usuarioxrolSet1UsuarioxrolToAttach);
            }
            rol.setUsuarioxrolSet1(attachedUsuarioxrolSet1);
            Set<Rolxprivilegio> attachedRolxprivilegioSet = new HashSet<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioSetRolxprivilegioToAttach : rol.getRolxprivilegioSet()) {
                rolxprivilegioSetRolxprivilegioToAttach = em.getReference(rolxprivilegioSetRolxprivilegioToAttach.getClass(), rolxprivilegioSetRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioSet.add(rolxprivilegioSetRolxprivilegioToAttach);
            }
            rol.setRolxprivilegioSet(attachedRolxprivilegioSet);
            Set<Rolxprivilegio> attachedRolxprivilegioSet1 = new HashSet<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioSet1RolxprivilegioToAttach : rol.getRolxprivilegioSet1()) {
                rolxprivilegioSet1RolxprivilegioToAttach = em.getReference(rolxprivilegioSet1RolxprivilegioToAttach.getClass(), rolxprivilegioSet1RolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioSet1.add(rolxprivilegioSet1RolxprivilegioToAttach);
            }
            rol.setRolxprivilegioSet1(attachedRolxprivilegioSet1);
            em.persist(rol);
            for (Usuarioxrol usuarioxrolSetUsuarioxrol : rol.getUsuarioxrolSet()) {
                Rol oldRolOfUsuarioxrolSetUsuarioxrol = usuarioxrolSetUsuarioxrol.getRol();
                usuarioxrolSetUsuarioxrol.setRol(rol);
                usuarioxrolSetUsuarioxrol = em.merge(usuarioxrolSetUsuarioxrol);
                if (oldRolOfUsuarioxrolSetUsuarioxrol != null) {
                    oldRolOfUsuarioxrolSetUsuarioxrol.getUsuarioxrolSet().remove(usuarioxrolSetUsuarioxrol);
                    oldRolOfUsuarioxrolSetUsuarioxrol = em.merge(oldRolOfUsuarioxrolSetUsuarioxrol);
                }
            }
            for (Usuarioxrol usuarioxrolSet1Usuarioxrol : rol.getUsuarioxrolSet1()) {
                Rol oldRol1OfUsuarioxrolSet1Usuarioxrol = usuarioxrolSet1Usuarioxrol.getRol1();
                usuarioxrolSet1Usuarioxrol.setRol1(rol);
                usuarioxrolSet1Usuarioxrol = em.merge(usuarioxrolSet1Usuarioxrol);
                if (oldRol1OfUsuarioxrolSet1Usuarioxrol != null) {
                    oldRol1OfUsuarioxrolSet1Usuarioxrol.getUsuarioxrolSet1().remove(usuarioxrolSet1Usuarioxrol);
                    oldRol1OfUsuarioxrolSet1Usuarioxrol = em.merge(oldRol1OfUsuarioxrolSet1Usuarioxrol);
                }
            }
            for (Rolxprivilegio rolxprivilegioSetRolxprivilegio : rol.getRolxprivilegioSet()) {
                Rol oldRolOfRolxprivilegioSetRolxprivilegio = rolxprivilegioSetRolxprivilegio.getRol();
                rolxprivilegioSetRolxprivilegio.setRol(rol);
                rolxprivilegioSetRolxprivilegio = em.merge(rolxprivilegioSetRolxprivilegio);
                if (oldRolOfRolxprivilegioSetRolxprivilegio != null) {
                    oldRolOfRolxprivilegioSetRolxprivilegio.getRolxprivilegioSet().remove(rolxprivilegioSetRolxprivilegio);
                    oldRolOfRolxprivilegioSetRolxprivilegio = em.merge(oldRolOfRolxprivilegioSetRolxprivilegio);
                }
            }
            for (Rolxprivilegio rolxprivilegioSet1Rolxprivilegio : rol.getRolxprivilegioSet1()) {
                Rol oldRol1OfRolxprivilegioSet1Rolxprivilegio = rolxprivilegioSet1Rolxprivilegio.getRol1();
                rolxprivilegioSet1Rolxprivilegio.setRol1(rol);
                rolxprivilegioSet1Rolxprivilegio = em.merge(rolxprivilegioSet1Rolxprivilegio);
                if (oldRol1OfRolxprivilegioSet1Rolxprivilegio != null) {
                    oldRol1OfRolxprivilegioSet1Rolxprivilegio.getRolxprivilegioSet1().remove(rolxprivilegioSet1Rolxprivilegio);
                    oldRol1OfRolxprivilegioSet1Rolxprivilegio = em.merge(oldRol1OfRolxprivilegioSet1Rolxprivilegio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRol(rol.getIdrol()) != null) {
                throw new PreexistingEntityException("Rol " + rol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getIdrol());
            Set<Usuarioxrol> usuarioxrolSetOld = persistentRol.getUsuarioxrolSet();
            Set<Usuarioxrol> usuarioxrolSetNew = rol.getUsuarioxrolSet();
            Set<Usuarioxrol> usuarioxrolSet1Old = persistentRol.getUsuarioxrolSet1();
            Set<Usuarioxrol> usuarioxrolSet1New = rol.getUsuarioxrolSet1();
            Set<Rolxprivilegio> rolxprivilegioSetOld = persistentRol.getRolxprivilegioSet();
            Set<Rolxprivilegio> rolxprivilegioSetNew = rol.getRolxprivilegioSet();
            Set<Rolxprivilegio> rolxprivilegioSet1Old = persistentRol.getRolxprivilegioSet1();
            Set<Rolxprivilegio> rolxprivilegioSet1New = rol.getRolxprivilegioSet1();
            List<String> illegalOrphanMessages = null;
            for (Usuarioxrol usuarioxrolSetOldUsuarioxrol : usuarioxrolSetOld) {
                if (!usuarioxrolSetNew.contains(usuarioxrolSetOldUsuarioxrol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioxrol " + usuarioxrolSetOldUsuarioxrol + " since its rol field is not nullable.");
                }
            }
            for (Usuarioxrol usuarioxrolSet1OldUsuarioxrol : usuarioxrolSet1Old) {
                if (!usuarioxrolSet1New.contains(usuarioxrolSet1OldUsuarioxrol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioxrol " + usuarioxrolSet1OldUsuarioxrol + " since its rol1 field is not nullable.");
                }
            }
            for (Rolxprivilegio rolxprivilegioSetOldRolxprivilegio : rolxprivilegioSetOld) {
                if (!rolxprivilegioSetNew.contains(rolxprivilegioSetOldRolxprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rolxprivilegio " + rolxprivilegioSetOldRolxprivilegio + " since its rol field is not nullable.");
                }
            }
            for (Rolxprivilegio rolxprivilegioSet1OldRolxprivilegio : rolxprivilegioSet1Old) {
                if (!rolxprivilegioSet1New.contains(rolxprivilegioSet1OldRolxprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rolxprivilegio " + rolxprivilegioSet1OldRolxprivilegio + " since its rol1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Usuarioxrol> attachedUsuarioxrolSetNew = new HashSet<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolSetNewUsuarioxrolToAttach : usuarioxrolSetNew) {
                usuarioxrolSetNewUsuarioxrolToAttach = em.getReference(usuarioxrolSetNewUsuarioxrolToAttach.getClass(), usuarioxrolSetNewUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolSetNew.add(usuarioxrolSetNewUsuarioxrolToAttach);
            }
            usuarioxrolSetNew = attachedUsuarioxrolSetNew;
            rol.setUsuarioxrolSet(usuarioxrolSetNew);
            Set<Usuarioxrol> attachedUsuarioxrolSet1New = new HashSet<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolSet1NewUsuarioxrolToAttach : usuarioxrolSet1New) {
                usuarioxrolSet1NewUsuarioxrolToAttach = em.getReference(usuarioxrolSet1NewUsuarioxrolToAttach.getClass(), usuarioxrolSet1NewUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolSet1New.add(usuarioxrolSet1NewUsuarioxrolToAttach);
            }
            usuarioxrolSet1New = attachedUsuarioxrolSet1New;
            rol.setUsuarioxrolSet1(usuarioxrolSet1New);
            Set<Rolxprivilegio> attachedRolxprivilegioSetNew = new HashSet<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioSetNewRolxprivilegioToAttach : rolxprivilegioSetNew) {
                rolxprivilegioSetNewRolxprivilegioToAttach = em.getReference(rolxprivilegioSetNewRolxprivilegioToAttach.getClass(), rolxprivilegioSetNewRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioSetNew.add(rolxprivilegioSetNewRolxprivilegioToAttach);
            }
            rolxprivilegioSetNew = attachedRolxprivilegioSetNew;
            rol.setRolxprivilegioSet(rolxprivilegioSetNew);
            Set<Rolxprivilegio> attachedRolxprivilegioSet1New = new HashSet<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioSet1NewRolxprivilegioToAttach : rolxprivilegioSet1New) {
                rolxprivilegioSet1NewRolxprivilegioToAttach = em.getReference(rolxprivilegioSet1NewRolxprivilegioToAttach.getClass(), rolxprivilegioSet1NewRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioSet1New.add(rolxprivilegioSet1NewRolxprivilegioToAttach);
            }
            rolxprivilegioSet1New = attachedRolxprivilegioSet1New;
            rol.setRolxprivilegioSet1(rolxprivilegioSet1New);
            rol = em.merge(rol);
            for (Usuarioxrol usuarioxrolSetNewUsuarioxrol : usuarioxrolSetNew) {
                if (!usuarioxrolSetOld.contains(usuarioxrolSetNewUsuarioxrol)) {
                    Rol oldRolOfUsuarioxrolSetNewUsuarioxrol = usuarioxrolSetNewUsuarioxrol.getRol();
                    usuarioxrolSetNewUsuarioxrol.setRol(rol);
                    usuarioxrolSetNewUsuarioxrol = em.merge(usuarioxrolSetNewUsuarioxrol);
                    if (oldRolOfUsuarioxrolSetNewUsuarioxrol != null && !oldRolOfUsuarioxrolSetNewUsuarioxrol.equals(rol)) {
                        oldRolOfUsuarioxrolSetNewUsuarioxrol.getUsuarioxrolSet().remove(usuarioxrolSetNewUsuarioxrol);
                        oldRolOfUsuarioxrolSetNewUsuarioxrol = em.merge(oldRolOfUsuarioxrolSetNewUsuarioxrol);
                    }
                }
            }
            for (Usuarioxrol usuarioxrolSet1NewUsuarioxrol : usuarioxrolSet1New) {
                if (!usuarioxrolSet1Old.contains(usuarioxrolSet1NewUsuarioxrol)) {
                    Rol oldRol1OfUsuarioxrolSet1NewUsuarioxrol = usuarioxrolSet1NewUsuarioxrol.getRol1();
                    usuarioxrolSet1NewUsuarioxrol.setRol1(rol);
                    usuarioxrolSet1NewUsuarioxrol = em.merge(usuarioxrolSet1NewUsuarioxrol);
                    if (oldRol1OfUsuarioxrolSet1NewUsuarioxrol != null && !oldRol1OfUsuarioxrolSet1NewUsuarioxrol.equals(rol)) {
                        oldRol1OfUsuarioxrolSet1NewUsuarioxrol.getUsuarioxrolSet1().remove(usuarioxrolSet1NewUsuarioxrol);
                        oldRol1OfUsuarioxrolSet1NewUsuarioxrol = em.merge(oldRol1OfUsuarioxrolSet1NewUsuarioxrol);
                    }
                }
            }
            for (Rolxprivilegio rolxprivilegioSetNewRolxprivilegio : rolxprivilegioSetNew) {
                if (!rolxprivilegioSetOld.contains(rolxprivilegioSetNewRolxprivilegio)) {
                    Rol oldRolOfRolxprivilegioSetNewRolxprivilegio = rolxprivilegioSetNewRolxprivilegio.getRol();
                    rolxprivilegioSetNewRolxprivilegio.setRol(rol);
                    rolxprivilegioSetNewRolxprivilegio = em.merge(rolxprivilegioSetNewRolxprivilegio);
                    if (oldRolOfRolxprivilegioSetNewRolxprivilegio != null && !oldRolOfRolxprivilegioSetNewRolxprivilegio.equals(rol)) {
                        oldRolOfRolxprivilegioSetNewRolxprivilegio.getRolxprivilegioSet().remove(rolxprivilegioSetNewRolxprivilegio);
                        oldRolOfRolxprivilegioSetNewRolxprivilegio = em.merge(oldRolOfRolxprivilegioSetNewRolxprivilegio);
                    }
                }
            }
            for (Rolxprivilegio rolxprivilegioSet1NewRolxprivilegio : rolxprivilegioSet1New) {
                if (!rolxprivilegioSet1Old.contains(rolxprivilegioSet1NewRolxprivilegio)) {
                    Rol oldRol1OfRolxprivilegioSet1NewRolxprivilegio = rolxprivilegioSet1NewRolxprivilegio.getRol1();
                    rolxprivilegioSet1NewRolxprivilegio.setRol1(rol);
                    rolxprivilegioSet1NewRolxprivilegio = em.merge(rolxprivilegioSet1NewRolxprivilegio);
                    if (oldRol1OfRolxprivilegioSet1NewRolxprivilegio != null && !oldRol1OfRolxprivilegioSet1NewRolxprivilegio.equals(rol)) {
                        oldRol1OfRolxprivilegioSet1NewRolxprivilegio.getRolxprivilegioSet1().remove(rolxprivilegioSet1NewRolxprivilegio);
                        oldRol1OfRolxprivilegioSet1NewRolxprivilegio = em.merge(oldRol1OfRolxprivilegioSet1NewRolxprivilegio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rol.getIdrol();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getIdrol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Usuarioxrol> usuarioxrolSetOrphanCheck = rol.getUsuarioxrolSet();
            for (Usuarioxrol usuarioxrolSetOrphanCheckUsuarioxrol : usuarioxrolSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Usuarioxrol " + usuarioxrolSetOrphanCheckUsuarioxrol + " in its usuarioxrolSet field has a non-nullable rol field.");
            }
            Set<Usuarioxrol> usuarioxrolSet1OrphanCheck = rol.getUsuarioxrolSet1();
            for (Usuarioxrol usuarioxrolSet1OrphanCheckUsuarioxrol : usuarioxrolSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Usuarioxrol " + usuarioxrolSet1OrphanCheckUsuarioxrol + " in its usuarioxrolSet1 field has a non-nullable rol1 field.");
            }
            Set<Rolxprivilegio> rolxprivilegioSetOrphanCheck = rol.getRolxprivilegioSet();
            for (Rolxprivilegio rolxprivilegioSetOrphanCheckRolxprivilegio : rolxprivilegioSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Rolxprivilegio " + rolxprivilegioSetOrphanCheckRolxprivilegio + " in its rolxprivilegioSet field has a non-nullable rol field.");
            }
            Set<Rolxprivilegio> rolxprivilegioSet1OrphanCheck = rol.getRolxprivilegioSet1();
            for (Rolxprivilegio rolxprivilegioSet1OrphanCheckRolxprivilegio : rolxprivilegioSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Rolxprivilegio " + rolxprivilegioSet1OrphanCheckRolxprivilegio + " in its rolxprivilegioSet1 field has a non-nullable rol1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
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

    public Rol findRol(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
