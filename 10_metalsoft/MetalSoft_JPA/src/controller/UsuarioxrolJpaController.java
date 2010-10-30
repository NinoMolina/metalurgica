/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Usuarioxrol;
import entity.UsuarioxrolPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Rol;
import entity.Usuario;

/**
 *
 * @author Nino
 */
public class UsuarioxrolJpaController {

    public UsuarioxrolJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarioxrol usuarioxrol) throws PreexistingEntityException, Exception {
        if (usuarioxrol.getUsuarioxrolPK() == null) {
            usuarioxrol.setUsuarioxrolPK(new UsuarioxrolPK());
        }
        usuarioxrol.getUsuarioxrolPK().setIdrol(usuarioxrol.getRol1().getIdrol());
        usuarioxrol.getUsuarioxrolPK().setIdusuario(usuarioxrol.getUsuario1().getIdusuario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rol = usuarioxrol.getRol();
            if (rol != null) {
                rol = em.getReference(rol.getClass(), rol.getIdrol());
                usuarioxrol.setRol(rol);
            }
            Rol rol1 = usuarioxrol.getRol1();
            if (rol1 != null) {
                rol1 = em.getReference(rol1.getClass(), rol1.getIdrol());
                usuarioxrol.setRol1(rol1);
            }
            Usuario usuario = usuarioxrol.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                usuarioxrol.setUsuario(usuario);
            }
            Usuario usuario1 = usuarioxrol.getUsuario1();
            if (usuario1 != null) {
                usuario1 = em.getReference(usuario1.getClass(), usuario1.getIdusuario());
                usuarioxrol.setUsuario1(usuario1);
            }
            em.persist(usuarioxrol);
            if (rol != null) {
                rol.getUsuarioxrolSet().add(usuarioxrol);
                rol = em.merge(rol);
            }
            if (rol1 != null) {
                rol1.getUsuarioxrolSet().add(usuarioxrol);
                rol1 = em.merge(rol1);
            }
            if (usuario != null) {
                usuario.getUsuarioxrolSet().add(usuarioxrol);
                usuario = em.merge(usuario);
            }
            if (usuario1 != null) {
                usuario1.getUsuarioxrolSet().add(usuarioxrol);
                usuario1 = em.merge(usuario1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioxrol(usuarioxrol.getUsuarioxrolPK()) != null) {
                throw new PreexistingEntityException("Usuarioxrol " + usuarioxrol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarioxrol usuarioxrol) throws NonexistentEntityException, Exception {
        usuarioxrol.getUsuarioxrolPK().setIdrol(usuarioxrol.getRol1().getIdrol());
        usuarioxrol.getUsuarioxrolPK().setIdusuario(usuarioxrol.getUsuario1().getIdusuario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarioxrol persistentUsuarioxrol = em.find(Usuarioxrol.class, usuarioxrol.getUsuarioxrolPK());
            Rol rolOld = persistentUsuarioxrol.getRol();
            Rol rolNew = usuarioxrol.getRol();
            Rol rol1Old = persistentUsuarioxrol.getRol1();
            Rol rol1New = usuarioxrol.getRol1();
            Usuario usuarioOld = persistentUsuarioxrol.getUsuario();
            Usuario usuarioNew = usuarioxrol.getUsuario();
            Usuario usuario1Old = persistentUsuarioxrol.getUsuario1();
            Usuario usuario1New = usuarioxrol.getUsuario1();
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getIdrol());
                usuarioxrol.setRol(rolNew);
            }
            if (rol1New != null) {
                rol1New = em.getReference(rol1New.getClass(), rol1New.getIdrol());
                usuarioxrol.setRol1(rol1New);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                usuarioxrol.setUsuario(usuarioNew);
            }
            if (usuario1New != null) {
                usuario1New = em.getReference(usuario1New.getClass(), usuario1New.getIdusuario());
                usuarioxrol.setUsuario1(usuario1New);
            }
            usuarioxrol = em.merge(usuarioxrol);
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getUsuarioxrolSet().remove(usuarioxrol);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getUsuarioxrolSet().add(usuarioxrol);
                rolNew = em.merge(rolNew);
            }
            if (rol1Old != null && !rol1Old.equals(rol1New)) {
                rol1Old.getUsuarioxrolSet().remove(usuarioxrol);
                rol1Old = em.merge(rol1Old);
            }
            if (rol1New != null && !rol1New.equals(rol1Old)) {
                rol1New.getUsuarioxrolSet().add(usuarioxrol);
                rol1New = em.merge(rol1New);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getUsuarioxrolSet().remove(usuarioxrol);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getUsuarioxrolSet().add(usuarioxrol);
                usuarioNew = em.merge(usuarioNew);
            }
            if (usuario1Old != null && !usuario1Old.equals(usuario1New)) {
                usuario1Old.getUsuarioxrolSet().remove(usuarioxrol);
                usuario1Old = em.merge(usuario1Old);
            }
            if (usuario1New != null && !usuario1New.equals(usuario1Old)) {
                usuario1New.getUsuarioxrolSet().add(usuarioxrol);
                usuario1New = em.merge(usuario1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UsuarioxrolPK id = usuarioxrol.getUsuarioxrolPK();
                if (findUsuarioxrol(id) == null) {
                    throw new NonexistentEntityException("The usuarioxrol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UsuarioxrolPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarioxrol usuarioxrol;
            try {
                usuarioxrol = em.getReference(Usuarioxrol.class, id);
                usuarioxrol.getUsuarioxrolPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioxrol with id " + id + " no longer exists.", enfe);
            }
            Rol rol = usuarioxrol.getRol();
            if (rol != null) {
                rol.getUsuarioxrolSet().remove(usuarioxrol);
                rol = em.merge(rol);
            }
            Rol rol1 = usuarioxrol.getRol1();
            if (rol1 != null) {
                rol1.getUsuarioxrolSet().remove(usuarioxrol);
                rol1 = em.merge(rol1);
            }
            Usuario usuario = usuarioxrol.getUsuario();
            if (usuario != null) {
                usuario.getUsuarioxrolSet().remove(usuarioxrol);
                usuario = em.merge(usuario);
            }
            Usuario usuario1 = usuarioxrol.getUsuario1();
            if (usuario1 != null) {
                usuario1.getUsuarioxrolSet().remove(usuarioxrol);
                usuario1 = em.merge(usuario1);
            }
            em.remove(usuarioxrol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarioxrol> findUsuarioxrolEntities() {
        return findUsuarioxrolEntities(true, -1, -1);
    }

    public List<Usuarioxrol> findUsuarioxrolEntities(int maxResults, int firstResult) {
        return findUsuarioxrolEntities(false, maxResults, firstResult);
    }

    private List<Usuarioxrol> findUsuarioxrolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarioxrol.class));
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

    public Usuarioxrol findUsuarioxrol(UsuarioxrolPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarioxrol.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioxrolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarioxrol> rt = cq.from(Usuarioxrol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
