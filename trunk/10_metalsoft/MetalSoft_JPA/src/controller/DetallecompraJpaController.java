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
        detallecompra.getDetallecompraPK().setIdcompra(detallecompra.getCompra().getIdcompra());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra compra = detallecompra.getCompra();
            if (compra != null) {
                compra = em.getReference(compra.getClass(), compra.getIdcompra());
                detallecompra.setCompra(compra);
            }
            Estadodetallecompra estado = detallecompra.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                detallecompra.setEstado(estado);
            }
            Materiaprima materiaprima = detallecompra.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                detallecompra.setMateriaprima(materiaprima);
            }
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSet = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSetDetallereclamoproveedorToAttach : detallecompra.getDetallereclamoproveedorSet()) {
                detallereclamoproveedorSetDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSetDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSetDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSet.add(detallereclamoproveedorSetDetallereclamoproveedorToAttach);
            }
            detallecompra.setDetallereclamoproveedorSet(attachedDetallereclamoproveedorSet);
            em.persist(detallecompra);
            if (compra != null) {
                compra.getDetallecompraSet().add(detallecompra);
                compra = em.merge(compra);
            }
            if (estado != null) {
                estado.getDetallecompraSet().add(detallecompra);
                estado = em.merge(estado);
            }
            if (materiaprima != null) {
                materiaprima.getDetallecompraSet().add(detallecompra);
                materiaprima = em.merge(materiaprima);
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
        detallecompra.getDetallecompraPK().setIdcompra(detallecompra.getCompra().getIdcompra());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallecompra persistentDetallecompra = em.find(Detallecompra.class, detallecompra.getDetallecompraPK());
            Compra compraOld = persistentDetallecompra.getCompra();
            Compra compraNew = detallecompra.getCompra();
            Estadodetallecompra estadoOld = persistentDetallecompra.getEstado();
            Estadodetallecompra estadoNew = detallecompra.getEstado();
            Materiaprima materiaprimaOld = persistentDetallecompra.getMateriaprima();
            Materiaprima materiaprimaNew = detallecompra.getMateriaprima();
            Set<Detallereclamoproveedor> detallereclamoproveedorSetOld = persistentDetallecompra.getDetallereclamoproveedorSet();
            Set<Detallereclamoproveedor> detallereclamoproveedorSetNew = detallecompra.getDetallereclamoproveedorSet();
            if (compraNew != null) {
                compraNew = em.getReference(compraNew.getClass(), compraNew.getIdcompra());
                detallecompra.setCompra(compraNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                detallecompra.setEstado(estadoNew);
            }
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                detallecompra.setMateriaprima(materiaprimaNew);
            }
            Set<Detallereclamoproveedor> attachedDetallereclamoproveedorSetNew = new HashSet<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorSetNewDetallereclamoproveedorToAttach : detallereclamoproveedorSetNew) {
                detallereclamoproveedorSetNewDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorSetNewDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorSetNewDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorSetNew.add(detallereclamoproveedorSetNewDetallereclamoproveedorToAttach);
            }
            detallereclamoproveedorSetNew = attachedDetallereclamoproveedorSetNew;
            detallecompra.setDetallereclamoproveedorSet(detallereclamoproveedorSetNew);
            detallecompra = em.merge(detallecompra);
            if (compraOld != null && !compraOld.equals(compraNew)) {
                compraOld.getDetallecompraSet().remove(detallecompra);
                compraOld = em.merge(compraOld);
            }
            if (compraNew != null && !compraNew.equals(compraOld)) {
                compraNew.getDetallecompraSet().add(detallecompra);
                compraNew = em.merge(compraNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getDetallecompraSet().remove(detallecompra);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getDetallecompraSet().add(detallecompra);
                estadoNew = em.merge(estadoNew);
            }
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getDetallecompraSet().remove(detallecompra);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getDetallecompraSet().add(detallecompra);
                materiaprimaNew = em.merge(materiaprimaNew);
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
            Estadodetallecompra estado = detallecompra.getEstado();
            if (estado != null) {
                estado.getDetallecompraSet().remove(detallecompra);
                estado = em.merge(estado);
            }
            Materiaprima materiaprima = detallecompra.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getDetallecompraSet().remove(detallecompra);
                materiaprima = em.merge(materiaprima);
            }
            Set<Detallereclamoproveedor> detallereclamoproveedorSet = detallecompra.getDetallereclamoproveedorSet();
            for (Detallereclamoproveedor detallereclamoproveedorSetDetallereclamoproveedor : detallereclamoproveedorSet) {
                detallereclamoproveedorSetDetallereclamoproveedor.setDetallecompra(null);
                detallereclamoproveedorSetDetallereclamoproveedor = em.merge(detallereclamoproveedorSetDetallereclamoproveedor);
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
