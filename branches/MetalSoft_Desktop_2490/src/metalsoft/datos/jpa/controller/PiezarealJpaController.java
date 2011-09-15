/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Codigodebarra;
import metalsoft.datos.jpa.entity.Estadopiezareal;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Mpasignadaxpiezareal;
import metalsoft.datos.jpa.entity.Piezareal;

/**
 *
 * @author Nino
 */
public class PiezarealJpaController {

    public PiezarealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Piezareal piezareal) throws PreexistingEntityException, Exception {
        if (piezareal.getDetalleejecucionplanificacionList() == null) {
            piezareal.setDetalleejecucionplanificacionList(new ArrayList<Detalleejecucionplanificacion>());
        }
        if (piezareal.getMpasignadaxpiezarealList() == null) {
            piezareal.setMpasignadaxpiezarealList(new ArrayList<Mpasignadaxpiezareal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codigodebarra idcodigobarra = piezareal.getIdcodigobarra();
            if (idcodigobarra != null) {
                idcodigobarra = em.getReference(idcodigobarra.getClass(), idcodigobarra.getIdcodigo());
                piezareal.setIdcodigobarra(idcodigobarra);
            }
            Estadopiezareal estado = piezareal.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                piezareal.setEstado(estado);
            }
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionList = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach : piezareal.getDetalleejecucionplanificacionList()) {
                detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionList.add(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach);
            }
            piezareal.setDetalleejecucionplanificacionList(attachedDetalleejecucionplanificacionList);
            List<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealList = new ArrayList<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezarealToAttach : piezareal.getMpasignadaxpiezarealList()) {
                mpasignadaxpiezarealListMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealListMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealListMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealList.add(mpasignadaxpiezarealListMpasignadaxpiezarealToAttach);
            }
            piezareal.setMpasignadaxpiezarealList(attachedMpasignadaxpiezarealList);
            em.persist(piezareal);
            if (idcodigobarra != null) {
                idcodigobarra.getPiezarealList().add(piezareal);
                idcodigobarra = em.merge(idcodigobarra);
            }
            if (estado != null) {
                estado.getPiezarealList().add(piezareal);
                estado = em.merge(estado);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : piezareal.getDetalleejecucionplanificacionList()) {
                Piezareal oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = detalleejecucionplanificacionListDetalleejecucionplanificacion.getPiezareal();
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setPiezareal(piezareal);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                if (oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion != null) {
                    oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                    oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(oldPiezarealOfDetalleejecucionplanificacionListDetalleejecucionplanificacion);
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezareal : piezareal.getMpasignadaxpiezarealList()) {
                Piezareal oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal = mpasignadaxpiezarealListMpasignadaxpiezareal.getIdpiezareal();
                mpasignadaxpiezarealListMpasignadaxpiezareal.setIdpiezareal(piezareal);
                mpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListMpasignadaxpiezareal);
                if (oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal != null) {
                    oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal.getMpasignadaxpiezarealList().remove(mpasignadaxpiezarealListMpasignadaxpiezareal);
                    oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(oldIdpiezarealOfMpasignadaxpiezarealListMpasignadaxpiezareal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPiezareal(piezareal.getIdpiezareal()) != null) {
                throw new PreexistingEntityException("Piezareal " + piezareal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Piezareal piezareal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Piezareal persistentPiezareal = em.find(Piezareal.class, piezareal.getIdpiezareal());
            Codigodebarra idcodigobarraOld = persistentPiezareal.getIdcodigobarra();
            Codigodebarra idcodigobarraNew = piezareal.getIdcodigobarra();
            Estadopiezareal estadoOld = persistentPiezareal.getEstado();
            Estadopiezareal estadoNew = piezareal.getEstado();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListOld = persistentPiezareal.getDetalleejecucionplanificacionList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListNew = piezareal.getDetalleejecucionplanificacionList();
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealListOld = persistentPiezareal.getMpasignadaxpiezarealList();
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealListNew = piezareal.getMpasignadaxpiezarealList();
            if (idcodigobarraNew != null) {
                idcodigobarraNew = em.getReference(idcodigobarraNew.getClass(), idcodigobarraNew.getIdcodigo());
                piezareal.setIdcodigobarra(idcodigobarraNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                piezareal.setEstado(estadoNew);
            }
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionListNew = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionListNew) {
                detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionListNew.add(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionListNew = attachedDetalleejecucionplanificacionListNew;
            piezareal.setDetalleejecucionplanificacionList(detalleejecucionplanificacionListNew);
            List<Mpasignadaxpiezareal> attachedMpasignadaxpiezarealListNew = new ArrayList<Mpasignadaxpiezareal>();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach : mpasignadaxpiezarealListNew) {
                mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach = em.getReference(mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach.getClass(), mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach.getId());
                attachedMpasignadaxpiezarealListNew.add(mpasignadaxpiezarealListNewMpasignadaxpiezarealToAttach);
            }
            mpasignadaxpiezarealListNew = attachedMpasignadaxpiezarealListNew;
            piezareal.setMpasignadaxpiezarealList(mpasignadaxpiezarealListNew);
            piezareal = em.merge(piezareal);
            if (idcodigobarraOld != null && !idcodigobarraOld.equals(idcodigobarraNew)) {
                idcodigobarraOld.getPiezarealList().remove(piezareal);
                idcodigobarraOld = em.merge(idcodigobarraOld);
            }
            if (idcodigobarraNew != null && !idcodigobarraNew.equals(idcodigobarraOld)) {
                idcodigobarraNew.getPiezarealList().add(piezareal);
                idcodigobarraNew = em.merge(idcodigobarraNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getPiezarealList().remove(piezareal);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getPiezarealList().add(piezareal);
                estadoNew = em.merge(estadoNew);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListOldDetalleejecucionplanificacion : detalleejecucionplanificacionListOld) {
                if (!detalleejecucionplanificacionListNew.contains(detalleejecucionplanificacionListOldDetalleejecucionplanificacion)) {
                    detalleejecucionplanificacionListOldDetalleejecucionplanificacion.setPiezareal(null);
                    detalleejecucionplanificacionListOldDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListOldDetalleejecucionplanificacion);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacion : detalleejecucionplanificacionListNew) {
                if (!detalleejecucionplanificacionListOld.contains(detalleejecucionplanificacionListNewDetalleejecucionplanificacion)) {
                    Piezareal oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = detalleejecucionplanificacionListNewDetalleejecucionplanificacion.getPiezareal();
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion.setPiezareal(piezareal);
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    if (oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion != null && !oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.equals(piezareal)) {
                        oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                        oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(oldPiezarealOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    }
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListOldMpasignadaxpiezareal : mpasignadaxpiezarealListOld) {
                if (!mpasignadaxpiezarealListNew.contains(mpasignadaxpiezarealListOldMpasignadaxpiezareal)) {
                    mpasignadaxpiezarealListOldMpasignadaxpiezareal.setIdpiezareal(null);
                    mpasignadaxpiezarealListOldMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListOldMpasignadaxpiezareal);
                }
            }
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListNewMpasignadaxpiezareal : mpasignadaxpiezarealListNew) {
                if (!mpasignadaxpiezarealListOld.contains(mpasignadaxpiezarealListNewMpasignadaxpiezareal)) {
                    Piezareal oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal = mpasignadaxpiezarealListNewMpasignadaxpiezareal.getIdpiezareal();
                    mpasignadaxpiezarealListNewMpasignadaxpiezareal.setIdpiezareal(piezareal);
                    mpasignadaxpiezarealListNewMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListNewMpasignadaxpiezareal);
                    if (oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal != null && !oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal.equals(piezareal)) {
                        oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal.getMpasignadaxpiezarealList().remove(mpasignadaxpiezarealListNewMpasignadaxpiezareal);
                        oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal = em.merge(oldIdpiezarealOfMpasignadaxpiezarealListNewMpasignadaxpiezareal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = piezareal.getIdpiezareal();
                if (findPiezareal(id) == null) {
                    throw new NonexistentEntityException("The piezareal with id " + id + " no longer exists.");
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
            Piezareal piezareal;
            try {
                piezareal = em.getReference(Piezareal.class, id);
                piezareal.getIdpiezareal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The piezareal with id " + id + " no longer exists.", enfe);
            }
            Codigodebarra idcodigobarra = piezareal.getIdcodigobarra();
            if (idcodigobarra != null) {
                idcodigobarra.getPiezarealList().remove(piezareal);
                idcodigobarra = em.merge(idcodigobarra);
            }
            Estadopiezareal estado = piezareal.getEstado();
            if (estado != null) {
                estado.getPiezarealList().remove(piezareal);
                estado = em.merge(estado);
            }
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionList = piezareal.getDetalleejecucionplanificacionList();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : detalleejecucionplanificacionList) {
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setPiezareal(null);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
            }
            List<Mpasignadaxpiezareal> mpasignadaxpiezarealList = piezareal.getMpasignadaxpiezarealList();
            for (Mpasignadaxpiezareal mpasignadaxpiezarealListMpasignadaxpiezareal : mpasignadaxpiezarealList) {
                mpasignadaxpiezarealListMpasignadaxpiezareal.setIdpiezareal(null);
                mpasignadaxpiezarealListMpasignadaxpiezareal = em.merge(mpasignadaxpiezarealListMpasignadaxpiezareal);
            }
            em.remove(piezareal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Piezareal> findPiezarealEntities() {
        return findPiezarealEntities(true, -1, -1);
    }

    public List<Piezareal> findPiezarealEntities(int maxResults, int firstResult) {
        return findPiezarealEntities(false, maxResults, firstResult);
    }

    private List<Piezareal> findPiezarealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Piezareal.class));
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

    public Piezareal findPiezareal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Piezareal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPiezarealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Piezareal> rt = cq.from(Piezareal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
