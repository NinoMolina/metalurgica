/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Usuarioxrol;
import metalsoft.datos.jpa.entity.UsuarioxrolPK;

/**
 *
 * @author Nino
 */
public class UsuarioxrolJpaController {

    public UsuarioxrolJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarioxrol usuarioxrol) throws PreexistingEntityException, Exception {
        if (usuarioxrol.getUsuarioxrolPK() == null) {
            usuarioxrol.setUsuarioxrolPK(new UsuarioxrolPK());
        }
        usuarioxrol.getUsuarioxrolPK().setIdrol(usuarioxrol.getRol().getIdrol());
        usuarioxrol.getUsuarioxrolPK().setIdusuario(usuarioxrol.getUsuario().getIdusuario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rol = usuarioxrol.getRol();
            if (rol != null) {
                rol = em.getReference(rol.getClass(), rol.getIdrol());
                usuarioxrol.setRol(rol);
            }
            Usuario usuario = usuarioxrol.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                usuarioxrol.setUsuario(usuario);
            }
            em.persist(usuarioxrol);
            if (rol != null) {
                rol.getUsuarioxrolList().add(usuarioxrol);
                rol = em.merge(rol);
            }
            if (usuario != null) {
                usuario.getUsuarioxrolList().add(usuarioxrol);
                usuario = em.merge(usuario);
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
        usuarioxrol.getUsuarioxrolPK().setIdrol(usuarioxrol.getRol().getIdrol());
        usuarioxrol.getUsuarioxrolPK().setIdusuario(usuarioxrol.getUsuario().getIdusuario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarioxrol persistentUsuarioxrol = em.find(Usuarioxrol.class, usuarioxrol.getUsuarioxrolPK());
            Rol rolOld = persistentUsuarioxrol.getRol();
            Rol rolNew = usuarioxrol.getRol();
            Usuario usuarioOld = persistentUsuarioxrol.getUsuario();
            Usuario usuarioNew = usuarioxrol.getUsuario();
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getIdrol());
                usuarioxrol.setRol(rolNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                usuarioxrol.setUsuario(usuarioNew);
            }
            usuarioxrol = em.merge(usuarioxrol);
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getUsuarioxrolList().remove(usuarioxrol);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getUsuarioxrolList().add(usuarioxrol);
                rolNew = em.merge(rolNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getUsuarioxrolList().remove(usuarioxrol);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getUsuarioxrolList().add(usuarioxrol);
                usuarioNew = em.merge(usuarioNew);
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
                rol.getUsuarioxrolList().remove(usuarioxrol);
                rol = em.merge(rol);
            }
            Usuario usuario = usuarioxrol.getUsuario();
            if (usuario != null) {
                usuario.getUsuarioxrolList().remove(usuarioxrol);
                usuario = em.merge(usuario);
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
