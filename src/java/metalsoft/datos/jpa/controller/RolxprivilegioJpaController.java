/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Privilegio;
import metalsoft.datos.jpa.entity.Rolxprivilegio;
import metalsoft.datos.jpa.entity.RolxprivilegioPK;

/**
 *
 * @author Nino
 */
public class RolxprivilegioJpaController implements Serializable {

    public RolxprivilegioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolxprivilegio rolxprivilegio) throws PreexistingEntityException, Exception {
        if (rolxprivilegio.getRolxprivilegioPK() == null) {
            rolxprivilegio.setRolxprivilegioPK(new RolxprivilegioPK());
        }
        rolxprivilegio.getRolxprivilegioPK().setIdrol(rolxprivilegio.getRol().getIdrol());
        rolxprivilegio.getRolxprivilegioPK().setIdprivilegio(rolxprivilegio.getPrivilegio().getIdprivilegio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rol = rolxprivilegio.getRol();
            if (rol != null) {
                rol = em.getReference(rol.getClass(), rol.getIdrol());
                rolxprivilegio.setRol(rol);
            }
            Privilegio privilegio = rolxprivilegio.getPrivilegio();
            if (privilegio != null) {
                privilegio = em.getReference(privilegio.getClass(), privilegio.getIdprivilegio());
                rolxprivilegio.setPrivilegio(privilegio);
            }
            em.persist(rolxprivilegio);
            if (rol != null) {
                rol.getRolxprivilegioList().add(rolxprivilegio);
                rol = em.merge(rol);
            }
            if (privilegio != null) {
                privilegio.getRolxprivilegioList().add(rolxprivilegio);
                privilegio = em.merge(privilegio);
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
        rolxprivilegio.getRolxprivilegioPK().setIdrol(rolxprivilegio.getRol().getIdrol());
        rolxprivilegio.getRolxprivilegioPK().setIdprivilegio(rolxprivilegio.getPrivilegio().getIdprivilegio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolxprivilegio persistentRolxprivilegio = em.find(Rolxprivilegio.class, rolxprivilegio.getRolxprivilegioPK());
            Rol rolOld = persistentRolxprivilegio.getRol();
            Rol rolNew = rolxprivilegio.getRol();
            Privilegio privilegioOld = persistentRolxprivilegio.getPrivilegio();
            Privilegio privilegioNew = rolxprivilegio.getPrivilegio();
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getIdrol());
                rolxprivilegio.setRol(rolNew);
            }
            if (privilegioNew != null) {
                privilegioNew = em.getReference(privilegioNew.getClass(), privilegioNew.getIdprivilegio());
                rolxprivilegio.setPrivilegio(privilegioNew);
            }
            rolxprivilegio = em.merge(rolxprivilegio);
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getRolxprivilegioList().remove(rolxprivilegio);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getRolxprivilegioList().add(rolxprivilegio);
                rolNew = em.merge(rolNew);
            }
            if (privilegioOld != null && !privilegioOld.equals(privilegioNew)) {
                privilegioOld.getRolxprivilegioList().remove(rolxprivilegio);
                privilegioOld = em.merge(privilegioOld);
            }
            if (privilegioNew != null && !privilegioNew.equals(privilegioOld)) {
                privilegioNew.getRolxprivilegioList().add(rolxprivilegio);
                privilegioNew = em.merge(privilegioNew);
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
            Rol rol = rolxprivilegio.getRol();
            if (rol != null) {
                rol.getRolxprivilegioList().remove(rolxprivilegio);
                rol = em.merge(rol);
            }
            Privilegio privilegio = rolxprivilegio.getPrivilegio();
            if (privilegio != null) {
                privilegio.getRolxprivilegioList().remove(rolxprivilegio);
                privilegio = em.merge(privilegio);
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
