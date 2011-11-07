/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Estadomantpreventivo;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadomantpreventivoJpaController implements Serializable {

    public EstadomantpreventivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadomantpreventivo estadomantpreventivo) throws PreexistingEntityException, Exception {
        if (estadomantpreventivo.getMantenimientopreventivoList() == null) {
            estadomantpreventivo.setMantenimientopreventivoList(new ArrayList<Mantenimientopreventivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mantenimientopreventivo> attachedMantenimientopreventivoList = new ArrayList<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoListMantenimientopreventivoToAttach : estadomantpreventivo.getMantenimientopreventivoList()) {
                mantenimientopreventivoListMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoListMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoListMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoList.add(mantenimientopreventivoListMantenimientopreventivoToAttach);
            }
            estadomantpreventivo.setMantenimientopreventivoList(attachedMantenimientopreventivoList);
            em.persist(estadomantpreventivo);
            for (Mantenimientopreventivo mantenimientopreventivoListMantenimientopreventivo : estadomantpreventivo.getMantenimientopreventivoList()) {
                Estadomantpreventivo oldEstadoOfMantenimientopreventivoListMantenimientopreventivo = mantenimientopreventivoListMantenimientopreventivo.getEstado();
                mantenimientopreventivoListMantenimientopreventivo.setEstado(estadomantpreventivo);
                mantenimientopreventivoListMantenimientopreventivo = em.merge(mantenimientopreventivoListMantenimientopreventivo);
                if (oldEstadoOfMantenimientopreventivoListMantenimientopreventivo != null) {
                    oldEstadoOfMantenimientopreventivoListMantenimientopreventivo.getMantenimientopreventivoList().remove(mantenimientopreventivoListMantenimientopreventivo);
                    oldEstadoOfMantenimientopreventivoListMantenimientopreventivo = em.merge(oldEstadoOfMantenimientopreventivoListMantenimientopreventivo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadomantpreventivo(estadomantpreventivo.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadomantpreventivo " + estadomantpreventivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadomantpreventivo estadomantpreventivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadomantpreventivo persistentEstadomantpreventivo = em.find(Estadomantpreventivo.class, estadomantpreventivo.getIdestado());
            List<Mantenimientopreventivo> mantenimientopreventivoListOld = persistentEstadomantpreventivo.getMantenimientopreventivoList();
            List<Mantenimientopreventivo> mantenimientopreventivoListNew = estadomantpreventivo.getMantenimientopreventivoList();
            List<Mantenimientopreventivo> attachedMantenimientopreventivoListNew = new ArrayList<Mantenimientopreventivo>();
            for (Mantenimientopreventivo mantenimientopreventivoListNewMantenimientopreventivoToAttach : mantenimientopreventivoListNew) {
                mantenimientopreventivoListNewMantenimientopreventivoToAttach = em.getReference(mantenimientopreventivoListNewMantenimientopreventivoToAttach.getClass(), mantenimientopreventivoListNewMantenimientopreventivoToAttach.getIdmantenimientopreventivo());
                attachedMantenimientopreventivoListNew.add(mantenimientopreventivoListNewMantenimientopreventivoToAttach);
            }
            mantenimientopreventivoListNew = attachedMantenimientopreventivoListNew;
            estadomantpreventivo.setMantenimientopreventivoList(mantenimientopreventivoListNew);
            estadomantpreventivo = em.merge(estadomantpreventivo);
            for (Mantenimientopreventivo mantenimientopreventivoListOldMantenimientopreventivo : mantenimientopreventivoListOld) {
                if (!mantenimientopreventivoListNew.contains(mantenimientopreventivoListOldMantenimientopreventivo)) {
                    mantenimientopreventivoListOldMantenimientopreventivo.setEstado(null);
                    mantenimientopreventivoListOldMantenimientopreventivo = em.merge(mantenimientopreventivoListOldMantenimientopreventivo);
                }
            }
            for (Mantenimientopreventivo mantenimientopreventivoListNewMantenimientopreventivo : mantenimientopreventivoListNew) {
                if (!mantenimientopreventivoListOld.contains(mantenimientopreventivoListNewMantenimientopreventivo)) {
                    Estadomantpreventivo oldEstadoOfMantenimientopreventivoListNewMantenimientopreventivo = mantenimientopreventivoListNewMantenimientopreventivo.getEstado();
                    mantenimientopreventivoListNewMantenimientopreventivo.setEstado(estadomantpreventivo);
                    mantenimientopreventivoListNewMantenimientopreventivo = em.merge(mantenimientopreventivoListNewMantenimientopreventivo);
                    if (oldEstadoOfMantenimientopreventivoListNewMantenimientopreventivo != null && !oldEstadoOfMantenimientopreventivoListNewMantenimientopreventivo.equals(estadomantpreventivo)) {
                        oldEstadoOfMantenimientopreventivoListNewMantenimientopreventivo.getMantenimientopreventivoList().remove(mantenimientopreventivoListNewMantenimientopreventivo);
                        oldEstadoOfMantenimientopreventivoListNewMantenimientopreventivo = em.merge(oldEstadoOfMantenimientopreventivoListNewMantenimientopreventivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadomantpreventivo.getIdestado();
                if (findEstadomantpreventivo(id) == null) {
                    throw new NonexistentEntityException("The estadomantpreventivo with id " + id + " no longer exists.");
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
            Estadomantpreventivo estadomantpreventivo;
            try {
                estadomantpreventivo = em.getReference(Estadomantpreventivo.class, id);
                estadomantpreventivo.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadomantpreventivo with id " + id + " no longer exists.", enfe);
            }
            List<Mantenimientopreventivo> mantenimientopreventivoList = estadomantpreventivo.getMantenimientopreventivoList();
            for (Mantenimientopreventivo mantenimientopreventivoListMantenimientopreventivo : mantenimientopreventivoList) {
                mantenimientopreventivoListMantenimientopreventivo.setEstado(null);
                mantenimientopreventivoListMantenimientopreventivo = em.merge(mantenimientopreventivoListMantenimientopreventivo);
            }
            em.remove(estadomantpreventivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadomantpreventivo> findEstadomantpreventivoEntities() {
        return findEstadomantpreventivoEntities(true, -1, -1);
    }

    public List<Estadomantpreventivo> findEstadomantpreventivoEntities(int maxResults, int firstResult) {
        return findEstadomantpreventivoEntities(false, maxResults, firstResult);
    }

    private List<Estadomantpreventivo> findEstadomantpreventivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadomantpreventivo.class));
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

    public Estadomantpreventivo findEstadomantpreventivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadomantpreventivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadomantpreventivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadomantpreventivo> rt = cq.from(Estadomantpreventivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
