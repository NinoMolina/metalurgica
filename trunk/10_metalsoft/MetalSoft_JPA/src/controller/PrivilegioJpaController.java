/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

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
import entity.Rol;
import java.util.HashSet;
import java.util.Set;

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
        if (privilegio.getRolSet() == null) {
            privilegio.setRolSet(new HashSet<Rol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Rol> attachedRolSet = new HashSet<Rol>();
            for (Rol rolSetRolToAttach : privilegio.getRolSet()) {
                rolSetRolToAttach = em.getReference(rolSetRolToAttach.getClass(), rolSetRolToAttach.getIdrol());
                attachedRolSet.add(rolSetRolToAttach);
            }
            privilegio.setRolSet(attachedRolSet);
            em.persist(privilegio);
            for (Rol rolSetRol : privilegio.getRolSet()) {
                rolSetRol.getPrivilegioSet().add(privilegio);
                rolSetRol = em.merge(rolSetRol);
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

    public void edit(Privilegio privilegio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Privilegio persistentPrivilegio = em.find(Privilegio.class, privilegio.getIdprivilegio());
            Set<Rol> rolSetOld = persistentPrivilegio.getRolSet();
            Set<Rol> rolSetNew = privilegio.getRolSet();
            Set<Rol> attachedRolSetNew = new HashSet<Rol>();
            for (Rol rolSetNewRolToAttach : rolSetNew) {
                rolSetNewRolToAttach = em.getReference(rolSetNewRolToAttach.getClass(), rolSetNewRolToAttach.getIdrol());
                attachedRolSetNew.add(rolSetNewRolToAttach);
            }
            rolSetNew = attachedRolSetNew;
            privilegio.setRolSet(rolSetNew);
            privilegio = em.merge(privilegio);
            for (Rol rolSetOldRol : rolSetOld) {
                if (!rolSetNew.contains(rolSetOldRol)) {
                    rolSetOldRol.getPrivilegioSet().remove(privilegio);
                    rolSetOldRol = em.merge(rolSetOldRol);
                }
            }
            for (Rol rolSetNewRol : rolSetNew) {
                if (!rolSetOld.contains(rolSetNewRol)) {
                    rolSetNewRol.getPrivilegioSet().add(privilegio);
                    rolSetNewRol = em.merge(rolSetNewRol);
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            Set<Rol> rolSet = privilegio.getRolSet();
            for (Rol rolSetRol : rolSet) {
                rolSetRol.getPrivilegioSet().remove(privilegio);
                rolSetRol = em.merge(rolSetRol);
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
