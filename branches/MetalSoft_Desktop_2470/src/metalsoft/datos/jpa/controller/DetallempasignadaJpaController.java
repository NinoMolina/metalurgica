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
import metalsoft.datos.jpa.entity.Detallempasignada;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Mpasignadaxpiezareal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class DetallempasignadaJpaController implements Serializable {

    public DetallempasignadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallempasignada detallempasignada) throws PreexistingEntityException, Exception {
        if (detallempasignada.getMpasignadaxpiezarealList() == null) {
            detallempasignada.setMpasignadaxpiezarealList(new ArrayList<Mpasignadaxpiezareal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacionproduccion idplanificacionproduccion = detallempasignada.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion = em.getReference(idplanificacionproduccion.getClass(), idplanificacionproduccion.getIdplanificacionproduccion());
                detallempasignada.setIdplanificacionproduccion(idplanificacionproduccion);
            }
            Materiaprima idmateriaprima = detallempasignada.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima = em.getReference(idmateriaprima.getClass(), idmateriaprima.getIdmateriaprima());
                detallempasignada.setIdmateriaprima(idmateriaprima);
            }
            List<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealList = new ArrayList<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezarealToAttach : detallempasignada.getMpasignadaxpiezarealList()) {
                mpasignadaxpiezarealListMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealListMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealListMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealList.add(mpasignadaxpiezarealListMpasignadaxpiezarealToAttach);
            }
            detallempasignada.setMpasignadaxpiezarealList(attachedMpasignadaxpiezarealList);
            em.persist(detallempasignada);
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetallempasignadaList().add(detallempasignada);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            if (idmateriaprima != null) {
                idmateriaprima.getDetallempasignadaList().add(detallempasignada);
                idmateriaprima = em.merge(idmateriaprima);
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezareal : detallempasignada.getMpasignadaxpiezarealList()) {
                Detallempasignada oldIddetallempasignadaOfMpasignadaxpiezarealListMpasignadaxpiezareal = mpasignadaxpiezarealListMpasignadaxpiezareal.getIddetallempasignada();
                mpasignadaxpiezarealListMpasignadaxpiezareal.setIddetallempasignada(detallempasignada);
                mpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListMpasignadaxpiezareal);
                if (oldIddetallempasignadaOfMpasignadaxpiezarealListMpasignadaxpiezareal != null) {
                    oldIddetallempasignadaOfMpasignadaxpiezarealListMpasignadaxpiezareal.getMpasignadaxpiezarealList().remove(mpasignadaxpiezarealListMpasignadaxpiezareal);
                    oldIddetallempasignadaOfMpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(oldIddetallempasignadaOfMpasignadaxpiezarealListMpasignadaxpiezareal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallempasignada(detallempasignada.getId()) != null) {
                throw new PreexistingEntityException("Detallempasignada " + detallempasignada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallempasignada detallempasignada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallempasignada persistentDetallempasignada = em.find(Detallempasignada.class, detallempasignada.getId());
            Planificacionproduccion idplanificacionproduccionOld = persistentDetallempasignada.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccionNew = detallempasignada.getIdplanificacionproduccion();
            Materiaprima idmateriaprimaOld = persistentDetallempasignada.getIdmateriaprima();
            Materiaprima idmateriaprimaNew = detallempasignada.getIdmateriaprima();
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealListOld = persistentDetallempasignada.getMpasignadaxpiezarealList();
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealListNew = detallempasignada.getMpasignadaxpiezarealList();
            if (idplanificacionproduccionNew != null) {
                idplanificacionproduccionNew = em.getReference(idplanificacionproduccionNew.getClass(), idplanificacionproduccionNew.getIdplanificacionproduccion());
                detallempasignada.setIdplanificacionproduccion(idplanificacionproduccionNew);
            }
            if (idmateriaprimaNew != null) {
                idmateriaprimaNew = em.getReference(idmateriaprimaNew.getClass(), idmateriaprimaNew.getIdmateriaprima());
                detallempasignada.setIdmateriaprima(idmateriaprimaNew);
            }
            List<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealListNew = new ArrayList<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach : mpasignadaxpiezarealListNew) {
                mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealListNew.add(mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach);
            }
            mpasignadaxpiezarealListNew = attachedMpasignadaxpiezarealListNew;
            detallempasignada.setMpasignadaxpiezarealList(mpasignadaxpiezarealListNew);
            detallempasignada = em.merge(detallempasignada);
            if (idplanificacionproduccionOld != null && !idplanificacionproduccionOld.equals(idplanificacionproduccionNew)) {
                idplanificacionproduccionOld.getDetallempasignadaList().remove(detallempasignada);
                idplanificacionproduccionOld = em.merge(idplanificacionproduccionOld);
            }
            if (idplanificacionproduccionNew != null && !idplanificacionproduccionNew.equals(idplanificacionproduccionOld)) {
                idplanificacionproduccionNew.getDetallempasignadaList().add(detallempasignada);
                idplanificacionproduccionNew = em.merge(idplanificacionproduccionNew);
            }
            if (idmateriaprimaOld != null && !idmateriaprimaOld.equals(idmateriaprimaNew)) {
                idmateriaprimaOld.getDetallempasignadaList().remove(detallempasignada);
                idmateriaprimaOld = em.merge(idmateriaprimaOld);
            }
            if (idmateriaprimaNew != null && !idmateriaprimaNew.equals(idmateriaprimaOld)) {
                idmateriaprimaNew.getDetallempasignadaList().add(detallempasignada);
                idmateriaprimaNew = em.merge(idmateriaprimaNew);
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListOldMpasignadaxpiezareal : mpasignadaxpiezarealListOld) {
                if (!mpasignadaxpiezarealListNew.contains(mpasignadaxpiezarealListOldMpasignadaxpiezareal)) {
                    mpasignadaxpiezarealListOldMpasignadaxpiezareal.setIddetallempasignada(null);
                    mpasignadaxpiezarealListOldMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListOldMpasignadaxpiezareal);
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListNewMpasignadaxpiezareal : mpasignadaxpiezarealListNew) {
                if (!mpasignadaxpiezarealListOld.contains(mpasignadaxpiezarealListNewMpasignadaxpiezareal)) {
                    Detallempasignada oldIddetallempasignadaOfMpasignadaxpiezarealListNewMpasignadaxpiezareal = mpasignadaxpiezarealListNewMpasignadaxpiezareal.getIddetallempasignada();
                    mpasignadaxpiezarealListNewMpasignadaxpiezareal.setIddetallempasignada(detallempasignada);
                    mpasignadaxpiezarealListNewMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListNewMpasignadaxpiezareal);
                    if (oldIddetallempasignadaOfMpasignadaxpiezarealListNewMpasignadaxpiezareal != null && !oldIddetallempasignadaOfMpasignadaxpiezarealListNewMpasignadaxpiezareal.equals(detallempasignada)) {
                        oldIddetallempasignadaOfMpasignadaxpiezarealListNewMpasignadaxpiezareal.getMpasignadaxpiezarealList().remove(mpasignadaxpiezarealListNewMpasignadaxpiezareal);
                        oldIddetallempasignadaOfMpasignadaxpiezarealListNewMpasignadaxpiezareal = em.merge(oldIddetallempasignadaOfMpasignadaxpiezarealListNewMpasignadaxpiezareal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallempasignada.getId();
                if (findDetallempasignada(id) == null) {
                    throw new NonexistentEntityException("The detallempasignada with id " + id + " no longer exists.");
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
            Detallempasignada detallempasignada;
            try {
                detallempasignada = em.getReference(Detallempasignada.class, id);
                detallempasignada.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallempasignada with id " + id + " no longer exists.", enfe);
            }
            Planificacionproduccion idplanificacionproduccion = detallempasignada.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getDetallempasignadaList().remove(detallempasignada);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            Materiaprima idmateriaprima = detallempasignada.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima.getDetallempasignadaList().remove(detallempasignada);
                idmateriaprima = em.merge(idmateriaprima);
            }
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealList = detallempasignada.getMpasignadaxpiezarealList();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezareal : mpasignadaxpiezarealList) {
                mpasignadaxpiezarealListMpasignadaxpiezareal.setIddetallempasignada(null);
                mpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListMpasignadaxpiezareal);
            }
            em.remove(detallempasignada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallempasignada> findDetallempasignadaEntities() {
        return findDetallempasignadaEntities(true, -1, -1);
    }

    public List<Detallempasignada> findDetallempasignadaEntities(int maxResults, int firstResult) {
        return findDetallempasignadaEntities(false, maxResults, firstResult);
    }

    private List<Detallempasignada> findDetallempasignadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallempasignada.class));
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

    public Detallempasignada findDetallempasignada(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallempasignada.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallempasignadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallempasignada> rt = cq.from(Detallempasignada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
