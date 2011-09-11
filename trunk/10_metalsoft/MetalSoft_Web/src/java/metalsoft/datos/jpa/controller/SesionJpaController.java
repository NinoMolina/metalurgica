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
import metalsoft.datos.jpa.entity.Sesion;
import metalsoft.datos.jpa.entity.Usuario;

/**
 *
 * @author Nino
 */
public class SesionJpaController implements Serializable {

    public SesionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sesion sesion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = sesion.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                sesion.setUsuario(usuario);
            }
            em.persist(sesion);
            if (usuario != null) {
                usuario.getSesionList().add(sesion);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSesion(sesion.getIdsesion()) != null) {
                throw new PreexistingEntityException("Sesion " + sesion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sesion sesion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sesion persistentSesion = em.find(Sesion.class, sesion.getIdsesion());
            Usuario usuarioOld = persistentSesion.getUsuario();
            Usuario usuarioNew = sesion.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                sesion.setUsuario(usuarioNew);
            }
            sesion = em.merge(sesion);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getSesionList().remove(sesion);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getSesionList().add(sesion);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sesion.getIdsesion();
                if (findSesion(id) == null) {
                    throw new NonexistentEntityException("The sesion with id " + id + " no longer exists.");
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
            Sesion sesion;
            try {
                sesion = em.getReference(Sesion.class, id);
                sesion.getIdsesion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sesion with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = sesion.getUsuario();
            if (usuario != null) {
                usuario.getSesionList().remove(sesion);
                usuario = em.merge(usuario);
            }
            em.remove(sesion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sesion> findSesionEntities() {
        return findSesionEntities(true, -1, -1);
    }

    public List<Sesion> findSesionEntities(int maxResults, int firstResult) {
        return findSesionEntities(false, maxResults, firstResult);
    }

    private List<Sesion> findSesionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sesion.class));
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

    public Sesion findSesion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sesion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSesionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sesion> rt = cq.from(Sesion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
