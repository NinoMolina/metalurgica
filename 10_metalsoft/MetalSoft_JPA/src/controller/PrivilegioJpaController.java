/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Privilegio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Rolxprivilegio;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PrivilegioJpaController {

    public PrivilegioJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Privilegio privilegio) throws PreexistingEntityException, Exception {
        if (privilegio.getRolxprivilegioSet() == null) {
            privilegio.setRolxprivilegioSet(new HashSet<Rolxprivilegio>());
        }
        if (privilegio.getRolxprivilegioSet1() == null) {
            privilegio.setRolxprivilegioSet1(new HashSet<Rolxprivilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Rolxprivilegio> attachedRolxprivilegioSet = new HashSet<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioSetRolxprivilegioToAttach : privilegio.getRolxprivilegioSet()) {
                rolxprivilegioSetRolxprivilegioToAttach = em.getReference(rolxprivilegioSetRolxprivilegioToAttach.getClass(), rolxprivilegioSetRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioSet.add(rolxprivilegioSetRolxprivilegioToAttach);
            }
            privilegio.setRolxprivilegioSet(attachedRolxprivilegioSet);
            Set<Rolxprivilegio> attachedRolxprivilegioSet1 = new HashSet<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioSet1RolxprivilegioToAttach : privilegio.getRolxprivilegioSet1()) {
                rolxprivilegioSet1RolxprivilegioToAttach = em.getReference(rolxprivilegioSet1RolxprivilegioToAttach.getClass(), rolxprivilegioSet1RolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioSet1.add(rolxprivilegioSet1RolxprivilegioToAttach);
            }
            privilegio.setRolxprivilegioSet1(attachedRolxprivilegioSet1);
            em.persist(privilegio);
            for (Rolxprivilegio rolxprivilegioSetRolxprivilegio : privilegio.getRolxprivilegioSet()) {
                Privilegio oldPrivilegioOfRolxprivilegioSetRolxprivilegio = rolxprivilegioSetRolxprivilegio.getPrivilegio();
                rolxprivilegioSetRolxprivilegio.setPrivilegio(privilegio);
                rolxprivilegioSetRolxprivilegio = em.merge(rolxprivilegioSetRolxprivilegio);
                if (oldPrivilegioOfRolxprivilegioSetRolxprivilegio != null) {
                    oldPrivilegioOfRolxprivilegioSetRolxprivilegio.getRolxprivilegioSet().remove(rolxprivilegioSetRolxprivilegio);
                    oldPrivilegioOfRolxprivilegioSetRolxprivilegio = em.merge(oldPrivilegioOfRolxprivilegioSetRolxprivilegio);
                }
            }
            for (Rolxprivilegio rolxprivilegioSet1Rolxprivilegio : privilegio.getRolxprivilegioSet1()) {
                Privilegio oldPrivilegio1OfRolxprivilegioSet1Rolxprivilegio = rolxprivilegioSet1Rolxprivilegio.getPrivilegio1();
                rolxprivilegioSet1Rolxprivilegio.setPrivilegio1(privilegio);
                rolxprivilegioSet1Rolxprivilegio = em.merge(rolxprivilegioSet1Rolxprivilegio);
                if (oldPrivilegio1OfRolxprivilegioSet1Rolxprivilegio != null) {
                    oldPrivilegio1OfRolxprivilegioSet1Rolxprivilegio.getRolxprivilegioSet1().remove(rolxprivilegioSet1Rolxprivilegio);
                    oldPrivilegio1OfRolxprivilegioSet1Rolxprivilegio = em.merge(oldPrivilegio1OfRolxprivilegioSet1Rolxprivilegio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrivilegio(privilegio.getIdprivilegio()) != null) {
                throw new PreexistingEntityException("Privilegio " + privilegio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Privilegio privilegio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Privilegio persistentPrivilegio = em.find(Privilegio.class, privilegio.getIdprivilegio());
            Set<Rolxprivilegio> rolxprivilegioSetOld = persistentPrivilegio.getRolxprivilegioSet();
            Set<Rolxprivilegio> rolxprivilegioSetNew = privilegio.getRolxprivilegioSet();
            Set<Rolxprivilegio> rolxprivilegioSet1Old = persistentPrivilegio.getRolxprivilegioSet1();
            Set<Rolxprivilegio> rolxprivilegioSet1New = privilegio.getRolxprivilegioSet1();
            List<String> illegalOrphanMessages = null;
            for (Rolxprivilegio rolxprivilegioSetOldRolxprivilegio : rolxprivilegioSetOld) {
                if (!rolxprivilegioSetNew.contains(rolxprivilegioSetOldRolxprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rolxprivilegio " + rolxprivilegioSetOldRolxprivilegio + " since its privilegio field is not nullable.");
                }
            }
            for (Rolxprivilegio rolxprivilegioSet1OldRolxprivilegio : rolxprivilegioSet1Old) {
                if (!rolxprivilegioSet1New.contains(rolxprivilegioSet1OldRolxprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rolxprivilegio " + rolxprivilegioSet1OldRolxprivilegio + " since its privilegio1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Rolxprivilegio> attachedRolxprivilegioSetNew = new HashSet<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioSetNewRolxprivilegioToAttach : rolxprivilegioSetNew) {
                rolxprivilegioSetNewRolxprivilegioToAttach = em.getReference(rolxprivilegioSetNewRolxprivilegioToAttach.getClass(), rolxprivilegioSetNewRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioSetNew.add(rolxprivilegioSetNewRolxprivilegioToAttach);
            }
            rolxprivilegioSetNew = attachedRolxprivilegioSetNew;
            privilegio.setRolxprivilegioSet(rolxprivilegioSetNew);
            Set<Rolxprivilegio> attachedRolxprivilegioSet1New = new HashSet<Rolxprivilegio>();
            for (Rolxprivilegio rolxprivilegioSet1NewRolxprivilegioToAttach : rolxprivilegioSet1New) {
                rolxprivilegioSet1NewRolxprivilegioToAttach = em.getReference(rolxprivilegioSet1NewRolxprivilegioToAttach.getClass(), rolxprivilegioSet1NewRolxprivilegioToAttach.getRolxprivilegioPK());
                attachedRolxprivilegioSet1New.add(rolxprivilegioSet1NewRolxprivilegioToAttach);
            }
            rolxprivilegioSet1New = attachedRolxprivilegioSet1New;
            privilegio.setRolxprivilegioSet1(rolxprivilegioSet1New);
            privilegio = em.merge(privilegio);
            for (Rolxprivilegio rolxprivilegioSetNewRolxprivilegio : rolxprivilegioSetNew) {
                if (!rolxprivilegioSetOld.contains(rolxprivilegioSetNewRolxprivilegio)) {
                    Privilegio oldPrivilegioOfRolxprivilegioSetNewRolxprivilegio = rolxprivilegioSetNewRolxprivilegio.getPrivilegio();
                    rolxprivilegioSetNewRolxprivilegio.setPrivilegio(privilegio);
                    rolxprivilegioSetNewRolxprivilegio = em.merge(rolxprivilegioSetNewRolxprivilegio);
                    if (oldPrivilegioOfRolxprivilegioSetNewRolxprivilegio != null && !oldPrivilegioOfRolxprivilegioSetNewRolxprivilegio.equals(privilegio)) {
                        oldPrivilegioOfRolxprivilegioSetNewRolxprivilegio.getRolxprivilegioSet().remove(rolxprivilegioSetNewRolxprivilegio);
                        oldPrivilegioOfRolxprivilegioSetNewRolxprivilegio = em.merge(oldPrivilegioOfRolxprivilegioSetNewRolxprivilegio);
                    }
                }
            }
            for (Rolxprivilegio rolxprivilegioSet1NewRolxprivilegio : rolxprivilegioSet1New) {
                if (!rolxprivilegioSet1Old.contains(rolxprivilegioSet1NewRolxprivilegio)) {
                    Privilegio oldPrivilegio1OfRolxprivilegioSet1NewRolxprivilegio = rolxprivilegioSet1NewRolxprivilegio.getPrivilegio1();
                    rolxprivilegioSet1NewRolxprivilegio.setPrivilegio1(privilegio);
                    rolxprivilegioSet1NewRolxprivilegio = em.merge(rolxprivilegioSet1NewRolxprivilegio);
                    if (oldPrivilegio1OfRolxprivilegioSet1NewRolxprivilegio != null && !oldPrivilegio1OfRolxprivilegioSet1NewRolxprivilegio.equals(privilegio)) {
                        oldPrivilegio1OfRolxprivilegioSet1NewRolxprivilegio.getRolxprivilegioSet1().remove(rolxprivilegioSet1NewRolxprivilegio);
                        oldPrivilegio1OfRolxprivilegioSet1NewRolxprivilegio = em.merge(oldPrivilegio1OfRolxprivilegioSet1NewRolxprivilegio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = privilegio.getIdprivilegio();
                if (findPrivilegio(id) == null) {
                    throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.");
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
            Privilegio privilegio;
            try {
                privilegio = em.getReference(Privilegio.class, id);
                privilegio.getIdprivilegio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Rolxprivilegio> rolxprivilegioSetOrphanCheck = privilegio.getRolxprivilegioSet();
            for (Rolxprivilegio rolxprivilegioSetOrphanCheckRolxprivilegio : rolxprivilegioSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Privilegio (" + privilegio + ") cannot be destroyed since the Rolxprivilegio " + rolxprivilegioSetOrphanCheckRolxprivilegio + " in its rolxprivilegioSet field has a non-nullable privilegio field.");
            }
            Set<Rolxprivilegio> rolxprivilegioSet1OrphanCheck = privilegio.getRolxprivilegioSet1();
            for (Rolxprivilegio rolxprivilegioSet1OrphanCheckRolxprivilegio : rolxprivilegioSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Privilegio (" + privilegio + ") cannot be destroyed since the Rolxprivilegio " + rolxprivilegioSet1OrphanCheckRolxprivilegio + " in its rolxprivilegioSet1 field has a non-nullable privilegio1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(privilegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Privilegio> findPrivilegioEntities() {
        return findPrivilegioEntities(true, -1, -1);
    }

    public List<Privilegio> findPrivilegioEntities(int maxResults, int firstResult) {
        return findPrivilegioEntities(false, maxResults, firstResult);
    }

    private List<Privilegio> findPrivilegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Privilegio.class));
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

    public Privilegio findPrivilegio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Privilegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrivilegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Privilegio> rt = cq.from(Privilegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
