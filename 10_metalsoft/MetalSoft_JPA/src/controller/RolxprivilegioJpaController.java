/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Rolxprivilegio;
import entity.RolxprivilegioPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Privilegio;
import entity.Rol;

/**
 *
 * @author Nino
 */
public class RolxprivilegioJpaController {

    public RolxprivilegioJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolxprivilegio rolxprivilegio) throws PreexistingEntityException, Exception {
        if (rolxprivilegio.getRolxprivilegioPK() == null) {
            rolxprivilegio.setRolxprivilegioPK(new RolxprivilegioPK());
        }
        rolxprivilegio.getRolxprivilegioPK().setIdprivilegio(rolxprivilegio.getPrivilegio1().getIdprivilegio());
        rolxprivilegio.getRolxprivilegioPK().setIdrol(rolxprivilegio.getRol1().getIdrol());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Privilegio privilegio = rolxprivilegio.getPrivilegio();
            if (privilegio != null) {
                privilegio = em.getReference(privilegio.getClass(), privilegio.getIdprivilegio());
                rolxprivilegio.setPrivilegio(privilegio);
            }
            Privilegio privilegio1 = rolxprivilegio.getPrivilegio1();
            if (privilegio1 != null) {
                privilegio1 = em.getReference(privilegio1.getClass(), privilegio1.getIdprivilegio());
                rolxprivilegio.setPrivilegio1(privilegio1);
            }
            Rol rol = rolxprivilegio.getRol();
            if (rol != null) {
                rol = em.getReference(rol.getClass(), rol.getIdrol());
                rolxprivilegio.setRol(rol);
            }
            Rol rol1 = rolxprivilegio.getRol1();
            if (rol1 != null) {
                rol1 = em.getReference(rol1.getClass(), rol1.getIdrol());
                rolxprivilegio.setRol1(rol1);
            }
            em.persist(rolxprivilegio);
            if (privilegio != null) {
                privilegio.getRolxprivilegioSet().add(rolxprivilegio);
                privilegio = em.merge(privilegio);
            }
            if (privilegio1 != null) {
                privilegio1.getRolxprivilegioSet().add(rolxprivilegio);
                privilegio1 = em.merge(privilegio1);
            }
            if (rol != null) {
                rol.getRolxprivilegioSet().add(rolxprivilegio);
                rol = em.merge(rol);
            }
            if (rol1 != null) {
                rol1.getRolxprivilegioSet().add(rolxprivilegio);
                rol1 = em.merge(rol1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRolxprivilegio(rolxprivilegio.getRolxprivilegioPK()) != null) {
                throw new PreexistingEntityException("Rolxprivilegio " + rolxprivilegio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rolxprivilegio rolxprivilegio) throws NonexistentEntityException, Exception {
        rolxprivilegio.getRolxprivilegioPK().setIdprivilegio(rolxprivilegio.getPrivilegio1().getIdprivilegio());
        rolxprivilegio.getRolxprivilegioPK().setIdrol(rolxprivilegio.getRol1().getIdrol());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolxprivilegio persistentRolxprivilegio = em.find(Rolxprivilegio.class, rolxprivilegio.getRolxprivilegioPK());
            Privilegio privilegioOld = persistentRolxprivilegio.getPrivilegio();
            Privilegio privilegioNew = rolxprivilegio.getPrivilegio();
            Privilegio privilegio1Old = persistentRolxprivilegio.getPrivilegio1();
            Privilegio privilegio1New = rolxprivilegio.getPrivilegio1();
            Rol rolOld = persistentRolxprivilegio.getRol();
            Rol rolNew = rolxprivilegio.getRol();
            Rol rol1Old = persistentRolxprivilegio.getRol1();
            Rol rol1New = rolxprivilegio.getRol1();
            if (privilegioNew != null) {
                privilegioNew = em.getReference(privilegioNew.getClass(), privilegioNew.getIdprivilegio());
                rolxprivilegio.setPrivilegio(privilegioNew);
            }
            if (privilegio1New != null) {
                privilegio1New = em.getReference(privilegio1New.getClass(), privilegio1New.getIdprivilegio());
                rolxprivilegio.setPrivilegio1(privilegio1New);
            }
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getIdrol());
                rolxprivilegio.setRol(rolNew);
            }
            if (rol1New != null) {
                rol1New = em.getReference(rol1New.getClass(), rol1New.getIdrol());
                rolxprivilegio.setRol1(rol1New);
            }
            rolxprivilegio = em.merge(rolxprivilegio);
            if (privilegioOld != null && !privilegioOld.equals(privilegioNew)) {
                privilegioOld.getRolxprivilegioSet().remove(rolxprivilegio);
                privilegioOld = em.merge(privilegioOld);
            }
            if (privilegioNew != null && !privilegioNew.equals(privilegioOld)) {
                privilegioNew.getRolxprivilegioSet().add(rolxprivilegio);
                privilegioNew = em.merge(privilegioNew);
            }
            if (privilegio1Old != null && !privilegio1Old.equals(privilegio1New)) {
                privilegio1Old.getRolxprivilegioSet().remove(rolxprivilegio);
                privilegio1Old = em.merge(privilegio1Old);
            }
            if (privilegio1New != null && !privilegio1New.equals(privilegio1Old)) {
                privilegio1New.getRolxprivilegioSet().add(rolxprivilegio);
                privilegio1New = em.merge(privilegio1New);
            }
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getRolxprivilegioSet().remove(rolxprivilegio);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getRolxprivilegioSet().add(rolxprivilegio);
                rolNew = em.merge(rolNew);
            }
            if (rol1Old != null && !rol1Old.equals(rol1New)) {
                rol1Old.getRolxprivilegioSet().remove(rolxprivilegio);
                rol1Old = em.merge(rol1Old);
            }
            if (rol1New != null && !rol1New.equals(rol1Old)) {
                rol1New.getRolxprivilegioSet().add(rolxprivilegio);
                rol1New = em.merge(rol1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RolxprivilegioPK id = rolxprivilegio.getRolxprivilegioPK();
                if (findRolxprivilegio(id) == null) {
                    throw new NonexistentEntityException("The rolxprivilegio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RolxprivilegioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolxprivilegio rolxprivilegio;
            try {
                rolxprivilegio = em.getReference(Rolxprivilegio.class, id);
                rolxprivilegio.getRolxprivilegioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolxprivilegio with id " + id + " no longer exists.", enfe);
            }
            Privilegio privilegio = rolxprivilegio.getPrivilegio();
            if (privilegio != null) {
                privilegio.getRolxprivilegioSet().remove(rolxprivilegio);
                privilegio = em.merge(privilegio);
            }
            Privilegio privilegio1 = rolxprivilegio.getPrivilegio1();
            if (privilegio1 != null) {
                privilegio1.getRolxprivilegioSet().remove(rolxprivilegio);
                privilegio1 = em.merge(privilegio1);
            }
            Rol rol = rolxprivilegio.getRol();
            if (rol != null) {
                rol.getRolxprivilegioSet().remove(rolxprivilegio);
                rol = em.merge(rol);
            }
            Rol rol1 = rolxprivilegio.getRol1();
            if (rol1 != null) {
                rol1.getRolxprivilegioSet().remove(rolxprivilegio);
                rol1 = em.merge(rol1);
            }
            em.remove(rolxprivilegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rolxprivilegio> findRolxprivilegioEntities() {
        return findRolxprivilegioEntities(true, -1, -1);
    }

    public List<Rolxprivilegio> findRolxprivilegioEntities(int maxResults, int firstResult) {
        return findRolxprivilegioEntities(false, maxResults, firstResult);
    }

    private List<Rolxprivilegio> findRolxprivilegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rolxprivilegio.class));
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

    public Rolxprivilegio findRolxprivilegio(RolxprivilegioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rolxprivilegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolxprivilegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rolxprivilegio> rt = cq.from(Rolxprivilegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
