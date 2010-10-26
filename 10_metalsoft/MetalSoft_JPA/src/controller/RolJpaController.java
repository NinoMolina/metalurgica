/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

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
import entity.Usuario;
import java.util.HashSet;
import java.util.Set;
import entity.Privilegio;

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
        if (rol.getUsuarioSet() == null) {
            rol.setUsuarioSet(new HashSet<Usuario>());
        }
        if (rol.getPrivilegioSet() == null) {
            rol.setPrivilegioSet(new HashSet<Privilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Usuario> attachedUsuarioSet = new HashSet<Usuario>();
            for (Usuario usuarioSetUsuarioToAttach : rol.getUsuarioSet()) {
                usuarioSetUsuarioToAttach = em.getReference(usuarioSetUsuarioToAttach.getClass(), usuarioSetUsuarioToAttach.getIdusuario());
                attachedUsuarioSet.add(usuarioSetUsuarioToAttach);
            }
            rol.setUsuarioSet(attachedUsuarioSet);
            Set<Privilegio> attachedPrivilegioSet = new HashSet<Privilegio>();
            for (Privilegio privilegioSetPrivilegioToAttach : rol.getPrivilegioSet()) {
                privilegioSetPrivilegioToAttach = em.getReference(privilegioSetPrivilegioToAttach.getClass(), privilegioSetPrivilegioToAttach.getIdprivilegio());
                attachedPrivilegioSet.add(privilegioSetPrivilegioToAttach);
            }
            rol.setPrivilegioSet(attachedPrivilegioSet);
            em.persist(rol);
            for (Usuario usuarioSetUsuario : rol.getUsuarioSet()) {
                usuarioSetUsuario.getRolSet().add(rol);
                usuarioSetUsuario = em.merge(usuarioSetUsuario);
            }
            for (Privilegio privilegioSetPrivilegio : rol.getPrivilegioSet()) {
                privilegioSetPrivilegio.getRolSet().add(rol);
                privilegioSetPrivilegio = em.merge(privilegioSetPrivilegio);
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

    public void edit(Rol rol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getIdrol());
            Set<Usuario> usuarioSetOld = persistentRol.getUsuarioSet();
            Set<Usuario> usuarioSetNew = rol.getUsuarioSet();
            Set<Privilegio> privilegioSetOld = persistentRol.getPrivilegioSet();
            Set<Privilegio> privilegioSetNew = rol.getPrivilegioSet();
            Set<Usuario> attachedUsuarioSetNew = new HashSet<Usuario>();
            for (Usuario usuarioSetNewUsuarioToAttach : usuarioSetNew) {
                usuarioSetNewUsuarioToAttach = em.getReference(usuarioSetNewUsuarioToAttach.getClass(), usuarioSetNewUsuarioToAttach.getIdusuario());
                attachedUsuarioSetNew.add(usuarioSetNewUsuarioToAttach);
            }
            usuarioSetNew = attachedUsuarioSetNew;
            rol.setUsuarioSet(usuarioSetNew);
            Set<Privilegio> attachedPrivilegioSetNew = new HashSet<Privilegio>();
            for (Privilegio privilegioSetNewPrivilegioToAttach : privilegioSetNew) {
                privilegioSetNewPrivilegioToAttach = em.getReference(privilegioSetNewPrivilegioToAttach.getClass(), privilegioSetNewPrivilegioToAttach.getIdprivilegio());
                attachedPrivilegioSetNew.add(privilegioSetNewPrivilegioToAttach);
            }
            privilegioSetNew = attachedPrivilegioSetNew;
            rol.setPrivilegioSet(privilegioSetNew);
            rol = em.merge(rol);
            for (Usuario usuarioSetOldUsuario : usuarioSetOld) {
                if (!usuarioSetNew.contains(usuarioSetOldUsuario)) {
                    usuarioSetOldUsuario.getRolSet().remove(rol);
                    usuarioSetOldUsuario = em.merge(usuarioSetOldUsuario);
                }
            }
            for (Usuario usuarioSetNewUsuario : usuarioSetNew) {
                if (!usuarioSetOld.contains(usuarioSetNewUsuario)) {
                    usuarioSetNewUsuario.getRolSet().add(rol);
                    usuarioSetNewUsuario = em.merge(usuarioSetNewUsuario);
                }
            }
            for (Privilegio privilegioSetOldPrivilegio : privilegioSetOld) {
                if (!privilegioSetNew.contains(privilegioSetOldPrivilegio)) {
                    privilegioSetOldPrivilegio.getRolSet().remove(rol);
                    privilegioSetOldPrivilegio = em.merge(privilegioSetOldPrivilegio);
                }
            }
            for (Privilegio privilegioSetNewPrivilegio : privilegioSetNew) {
                if (!privilegioSetOld.contains(privilegioSetNewPrivilegio)) {
                    privilegioSetNewPrivilegio.getRolSet().add(rol);
                    privilegioSetNewPrivilegio = em.merge(privilegioSetNewPrivilegio);
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            Set<Usuario> usuarioSet = rol.getUsuarioSet();
            for (Usuario usuarioSetUsuario : usuarioSet) {
                usuarioSetUsuario.getRolSet().remove(rol);
                usuarioSetUsuario = em.merge(usuarioSetUsuario);
            }
            Set<Privilegio> privilegioSet = rol.getPrivilegioSet();
            for (Privilegio privilegioSetPrivilegio : privilegioSet) {
                privilegioSetPrivilegio.getRolSet().remove(rol);
                privilegioSetPrivilegio = em.merge(privilegioSetPrivilegio);
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
