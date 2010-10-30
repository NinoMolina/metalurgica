/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallecompra;
import entity.DetallecompraPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Compra;
import entity.Estadodetallecompra;
import entity.Materiaprima;
import entity.Detallereclamoproveedor;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class DetallecompraJpaController {

    public DetallecompraJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallecompra detallecompra) throws PreexistingEntityException, Exception {
        if (detallecompra.getDetallecompraPK() == null) {
            detallecompra.setDetallecompraPK(new DetallecompraPK());
        }
        if (detallecompra.getDetallereclamoproveedorSet() == null) {
            detallecompra.setDetallereclamoproveedorSet(new HashSet<Detallereclamoproveedor>());
        }
        if (detallecompra.getDetallereclamoproveedorSet1() == null) {
            detallecompra.setDetallereclamoproveedorSet1(new HashSet<Detallereclamoproveedor>());
        }
        detallecompra.getDetallecompraPK().setIdcompra(detallecompra.getCompra1().getIdcompra());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra compra = detallecompra.getCompra();
            if (compra != null) {
                compra = em.getReference(compra.getClass(), compra.getIdcompra());
                detallecompra.setCompra(compra);
            }
            Compra compra1 = detallecompra.getCompra1();
            if (compra1 != null) {
                compra1 = em.getReference(compra1.getClass(), compra1.getIdcompra());
                detallecompra.setCompra1(compra1);
            }
            Estadodetallecompra estado = detallecompra.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                detallecompra.setEstado(estado);
            }
            Estadodetallecompra estado1 = detallecompra.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                detallecompra.setEstado1(estado1);
            }
            Materiaprima materiaprima = detallecompra.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                detallecompra.setMateriaprima(materiaprima);
            }
            Materiaprima materiaprima1 = detallecompra.getMateriaprima1();
            if (materiaprima1 != null) {
                materiaprima1 = em.getReference(materiaprima1.getClass(), materiaprima1.getIdmateriaprima());
                detallecompra.setMateriaprima1(materiaprima1);
            }
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSet = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSetDetallereclamoproveedorToAttach : detallecompra.getDetallereclamoproveedorSet()) {
                detallereclamoproveedorSetDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSetDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSetDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSet.add(detallereclamoproveedorSetDetallereclamoproveedorToAttach);
            }
            detallecompra.setDetallereclamoproveedorSet(attachedDetallereclamoproveedorSet);
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSet1 = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSet1DetallereclamoproveedorToAttach : detallecompra.getDetallereclamoproveedorSet1()) {
                detallereclamoproveedorSet1DetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSet1DetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSet1DetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSet1.add(detallereclamoproveedorSet1DetallereclamoproveedorToAttach);
            }
            detallecompra.setDetallereclamoproveedorSet1(attachedDetallereclamoproveedorSet1);
            em.persist(detallecompra);
            if (compra != null) {
                compra.getDetallecompraSet().add(detallecompra);
                compra = em.merge(compra);
            }
            if (compra1 != null) {
                compra1.getDetallecompraSet().add(detallecompra);
                compra1 = em.merge(compra1);
            }
            if (estado != null) {
                estado.getDetallecompraSet().add(detallecompra);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getDetallecompraSet().add(detallecompra);
                estado1 = em.merge(estado1);
            }
            if (materiaprima != null) {
                materiaprima.getDetallecompraSet().add(detallecompra);
                materiaprima = em.merge(materiaprima);
            }
            if (materiaprima1 != null) {
                materiaprima1.getDetallecompraSet().add(detallecompra);
                materiaprima1 = em.merge(materiaprima1);
            }
            for (Detallereclamoproveedor detallereclamoproveedorSetDetallereclamoproveedor : detallecompra.getDetallereclamoproveedorSet()) {
                Detallecompra oldDetallecompraOfDetallereclamoproveedorSetDetallereclamoproveedor = detallereclamoproveedorSetDetallereclamoproveedor.getDetallecompra();
                detallereclamoproveedorSetDetallereclamoproveedor.setDetallecompra(detallecompra);
                detallereclamoproveedorSetDetallereclamoproveedor = em.merge(detallereclamoproveedorSetDetallereclamoproveedor);
                if (oldDetallecompraOfDetallereclamoproveedorSetDetallereclamoproveedor != null) {
                    oldDetallecompraOfDetallereclamoproveedorSetDetallereclamoproveedor.getDetallereclamoproveedorSet().remove(detallereclamoproveedorSetDetallereclamoproveedor);
                    oldDetallecompraOfDetallereclamoproveedorSetDetallereclamoproveedor = em.merge(oldDetallecompraOfDetallereclamoproveedorSetDetallereclamoproveedor);
                }
            }
            for (Detallereclamoproveedor detallereclamoproveedorSet1Detallereclamoproveedor : detallecompra.getDetallereclamoproveedorSet1()) {
                Detallecompra oldDetallecompra1OfDetallereclamoproveedorSet1Detallereclamoproveedor = detallereclamoproveedorSet1Detallereclamoproveedor.getDetallecompra1();
                detallereclamoproveedorSet1Detallereclamoproveedor.setDetallecompra1(detallecompra);
                detallereclamoproveedorSet1Detallereclamoproveedor = em.merge(detallereclamoproveedorSet1Detallereclamoproveedor);
                if (oldDetallecompra1OfDetallereclamoproveedorSet1Detallereclamoproveedor != null) {
                    oldDetallecompra1OfDetallereclamoproveedorSet1Detallereclamoproveedor.getDetallereclamoproveedorSet1().remove(detallereclamoproveedorSet1Detallereclamoproveedor);
                    oldDetallecompra1OfDetallereclamoproveedorSet1Detallereclamoproveedor = em.merge(oldDetallecompra1OfDetallereclamoproveedorSet1Detallereclamoproveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallecompra(detallecompra.getDetallecompraPK()) != null) {
                throw new PreexistingEntityException("Detallecompra " + detallecompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallecompra detallecompra) throws NonexistentEntityException, Exception {
        detallecompra.getDetallecompraPK().setIdcompra(detallecompra.getCompra1().getIdcompra());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallecompra persistentDetallecompra = em.find(Detallecompra.class, detallecompra.getDetallecompraPK());
            Compra compraOld = persistentDetallecompra.getCompra();
            Compra compraNew = detallecompra.getCompra();
            Compra compra1Old = persistentDetallecompra.getCompra1();
            Compra compra1New = detallecompra.getCompra1();
            Estadodetallecompra estadoOld = persistentDetallecompra.getEstado();
            Estadodetallecompra estadoNew = detallecompra.getEstado();
            Estadodetallecompra estado1Old = persistentDetallecompra.getEstado1();
            Estadodetallecompra estado1New = detallecompra.getEstado1();
            Materiaprima materiaprimaOld = persistentDetallecompra.getMateriaprima();
            Materiaprima materiaprimaNew = detallecompra.getMateriaprima();
            Materiaprima materiaprima1Old = persistentDetallecompra.getMateriaprima1();
            Materiaprima materiaprima1New = detallecompra.getMateriaprima1();
            Set<Detallereclamoproveedor> detallereclamoproveedorSetOld = persistentDetallecompra.getDetallereclamoproveedorSet();
            Set<Detallereclamoproveedor> detallereclamoproveedorSetNew = detallecompra.getDetallereclamoproveedorSet();
            Set<Detallereclamoproveedor> detallereclamoproveedorSet1Old = persistentDetallecompra.getDetallereclamoproveedorSet1();
            Set<Detallereclamoproveedor> detallereclamoproveedorSet1New = detallecompra.getDetallereclamoproveedorSet1();
            if (compraNew != null) {
                compraNew = em.getReference(compraNew.getClass(), compraNew.getIdcompra());
                detallecompra.setCompra(compraNew);
            }
            if (compra1New != null) {
                compra1New = em.getReference(compra1New.getClass(), compra1New.getIdcompra());
                detallecompra.setCompra1(compra1New);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                detallecompra.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                detallecompra.setEstado1(estado1New);
            }
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                detallecompra.setMateriaprima(materiaprimaNew);
            }
            if (materiaprima1New != null) {
                materiaprima1New = em.getReference(materiaprima1New.getClass(), materiaprima1New.getIdmateriaprima());
                detallecompra.setMateriaprima1(materiaprima1New);
            }
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSetNew = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSetNewDetallereclamoproveedorToAttach : detallereclamoproveedorSetNew) {
                detallereclamoproveedorSetNewDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSetNewDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSetNewDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSetNew.add(detallereclamoproveedorSetNewDetallereclamoproveedorToAttach);
            }
            detallereclamoproveedorSetNew = attachedDetallereclamoproveedorSetNew;
            detallecompra.setDetallereclamoproveedorSet(detallereclamoproveedorSetNew);
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSet1New = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach : detallereclamoproveedorSet1New) {
                detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSet1New.add(detallereclamoproveedorSet1NewDetallereclamoproveedorToAttach);
            }
            detallereclamoproveedorSet1New = attachedDetallereclamoproveedorSet1New;
            detallecompra.setDetallereclamoproveedorSet1(detallereclamoproveedorSet1New);
            detallecompra = em.merge(detallecompra);
            if (compraOld != null && !compraOld.equals(compraNew)) {
                compraOld.getDetallecompraSet().remove(detallecompra);
                compraOld = em.merge(compraOld);
            }
            if (compraNew != null && !compraNew.equals(compraOld)) {
                compraNew.getDetallecompraSet().add(detallecompra);
                compraNew = em.merge(compraNew);
            }
            if (compra1Old != null && !compra1Old.equals(compra1New)) {
                compra1Old.getDetallecompraSet().remove(detallecompra);
                compra1Old = em.merge(compra1Old);
            }
            if (compra1New != null && !compra1New.equals(compra1Old)) {
                compra1New.getDetallecompraSet().add(detallecompra);
                compra1New = em.merge(compra1New);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getDetallecompraSet().remove(detallecompra);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getDetallecompraSet().add(detallecompra);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getDetallecompraSet().remove(detallecompra);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getDetallecompraSet().add(detallecompra);
                estado1New = em.merge(estado1New);
            }
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getDetallecompraSet().remove(detallecompra);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getDetallecompraSet().add(detallecompra);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            if (materiaprima1Old != null && !materiaprima1Old.equals(materiaprima1New)) {
                materiaprima1Old.getDetallecompraSet().remove(detallecompra);
                materiaprima1Old = em.merge(materiaprima1Old);
            }
            if (materiaprima1New != null && !materiaprima1New.equals(materiaprima1Old)) {
                materiaprima1New.getDetallecompraSet().add(detallecompra);
                materiaprima1New = em.merge(materiaprima1New);
            }
            for (Detallereclamoproveedor detallereclamoproveedorSetOldDetallereclamoproveedor : detallereclamoproveedorSetOld) {
                if (!detallereclamoproveedorSetNew.contains(detallereclamoproveedorSetOldDetallereclamoproveedor)) {
                    detallereclamoproveedorSetOldDetallereclamoproveedor.setDetallecompra(null);
                    detallereclamoproveedorSetOldDetallereclamoproveedor = em.merge(detallereclamoproveedorSetOldDetallereclamoproveedor);
                }
            }
            for (Detallereclamoproveedor detallereclamoproveedorSetNewDetallereclamoproveedor : detallereclamoproveedorSetNew) {
                if (!detallereclamoproveedorSetOld.contains(detallereclamoproveedorSetNewDetallereclamoproveedor)) {
                    Detallecompra oldDetallecompraOfDetallereclamoproveedorSetNewDetallereclamoproveedor = detallereclamoproveedorSetNewDetallereclamoproveedor.getDetallecompra();
                    detallereclamoproveedorSetNewDetallereclamoproveedor.setDetallecompra(detallecompra);
                    detallereclamoproveedorSetNewDetallereclamoproveedor = em.merge(detallereclamoproveedorSetNewDetallereclamoproveedor);
                    if (oldDetallecompraOfDetallereclamoproveedorSetNewDetallereclamoproveedor != null && !oldDetallecompraOfDetallereclamoproveedorSetNewDetallereclamoproveedor.equals(detallecompra)) {
                        oldDetallecompraOfDetallereclamoproveedorSetNewDetallereclamoproveedor.getDetallereclamoproveedorSet().remove(detallereclamoproveedorSetNewDetallereclamoproveedor);
                        oldDetallecompraOfDetallereclamoproveedorSetNewDetallereclamoproveedor = em.merge(oldDetallecompraOfDetallereclamoproveedorSetNewDetallereclamoproveedor);
                    }
                }
            }
            for (Detallereclamoproveedor detallereclamoproveedorSet1OldDetallereclamoproveedor : detallereclamoproveedorSet1Old) {
                if (!detallereclamoproveedorSet1New.contains(detallereclamoproveedorSet1OldDetallereclamoproveedor)) {
                    detallereclamoproveedorSet1OldDetallereclamoproveedor.setDetallecompra1(null);
                    detallereclamoproveedorSet1OldDetallereclamoproveedor = em.merge(detallereclamoproveedorSet1OldDetallereclamoproveedor);
                }
            }
            for (Detallereclamoproveedor detallereclamoproveedorSet1NewDetallereclamoproveedor : detallereclamoproveedorSet1New) {
                if (!detallereclamoproveedorSet1Old.contains(detallereclamoproveedorSet1NewDetallereclamoproveedor)) {
                    Detallecompra oldDetallecompra1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor = detallereclamoproveedorSet1NewDetallereclamoproveedor.getDetallecompra1();
                    detallereclamoproveedorSet1NewDetallereclamoproveedor.setDetallecompra1(detallecompra);
                    detallereclamoproveedorSet1NewDetallereclamoproveedor = em.merge(detallereclamoproveedorSet1NewDetallereclamoproveedor);
                    if (oldDetallecompra1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor != null && !oldDetallecompra1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor.equals(detallecompra)) {
                        oldDetallecompra1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor.getDetallereclamoproveedorSet1().remove(detallereclamoproveedorSet1NewDetallereclamoproveedor);
                        oldDetallecompra1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor = em.merge(oldDetallecompra1OfDetallereclamoproveedorSet1NewDetallereclamoproveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallecompraPK id = detallecompra.getDetallecompraPK();
                if (findDetallecompra(id) == null) {
                    throw new NonexistentEntityException("The detallecompra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallecompraPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallecompra detallecompra;
            try {
                detallecompra = em.getReference(Detallecompra.class, id);
                detallecompra.getDetallecompraPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallecompra with id " + id + " no longer exists.", enfe);
            }
            Compra compra = detallecompra.getCompra();
            if (compra != null) {
                compra.getDetallecompraSet().remove(detallecompra);
                compra = em.merge(compra);
            }
            Compra compra1 = detallecompra.getCompra1();
            if (compra1 != null) {
                compra1.getDetallecompraSet().remove(detallecompra);
                compra1 = em.merge(compra1);
            }
            Estadodetallecompra estado = detallecompra.getEstado();
            if (estado != null) {
                estado.getDetallecompraSet().remove(detallecompra);
                estado = em.merge(estado);
            }
            Estadodetallecompra estado1 = detallecompra.getEstado1();
            if (estado1 != null) {
                estado1.getDetallecompraSet().remove(detallecompra);
                estado1 = em.merge(estado1);
            }
            Materiaprima materiaprima = detallecompra.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getDetallecompraSet().remove(detallecompra);
                materiaprima = em.merge(materiaprima);
            }
            Materiaprima materiaprima1 = detallecompra.getMateriaprima1();
            if (materiaprima1 != null) {
                materiaprima1.getDetallecompraSet().remove(detallecompra);
                materiaprima1 = em.merge(materiaprima1);
            }
            Set<Detallereclamoproveedor> detallereclamoproveedorSet = detallecompra.getDetallereclamoproveedorSet();
            for (Detallereclamoproveedor detallereclamoproveedorSetDetallereclamoproveedor : detallereclamoproveedorSet) {
                detallereclamoproveedorSetDetallereclamoproveedor.setDetallecompra(null);
                detallereclamoproveedorSetDetallereclamoproveedor = em.merge(detallereclamoproveedorSetDetallereclamoproveedor);
            }
            Set<Detallereclamoproveedor> detallereclamoproveedorSet1 = detallecompra.getDetallereclamoproveedorSet1();
            for (Detallereclamoproveedor detallereclamoproveedorSet1Detallereclamoproveedor : detallereclamoproveedorSet1) {
                detallereclamoproveedorSet1Detallereclamoproveedor.setDetallecompra1(null);
                detallereclamoproveedorSet1Detallereclamoproveedor = em.merge(detallereclamoproveedorSet1Detallereclamoproveedor);
            }
            em.remove(detallecompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallecompra> findDetallecompraEntities() {
        return findDetallecompraEntities(true, -1, -1);
    }

    public List<Detallecompra> findDetallecompraEntities(int maxResults, int firstResult) {
        return findDetallecompraEntities(false, maxResults, firstResult);
    }

    private List<Detallecompra> findDetallecompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallecompra.class));
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

    public Detallecompra findDetallecompra(DetallecompraPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallecompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallecompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallecompra> rt = cq.from(Detallecompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
