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
import metalsoft.datos.jpa.entity.Compra;
import metalsoft.datos.jpa.entity.Detallecompra;
import metalsoft.datos.jpa.entity.DetallecompraPK;
import metalsoft.datos.jpa.entity.Estadodetallecompra;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Detallereclamoproveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class DetallecompraJpaController {

    public DetallecompraJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallecompra detallecompra) throws PreexistingEntityException, Exception {
        if (detallecompra.getDetallecompraPK() == null) {
            detallecompra.setDetallecompraPK(new DetallecompraPK());
        }
        if (detallecompra.getDetallereclamoproveedorList() == null) {
            detallecompra.setDetallereclamoproveedorList(new ArrayList<Detallereclamoproveedor>());
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
            List<Detallereclamoproveedor> attachedDetallereclamoproveedorList = new ArrayList<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorListDetallereclamoproveedorToAttach : detallecompra.getDetallereclamoproveedorList()) {
                detallereclamoproveedorListDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorListDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorListDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorList.add(detallereclamoproveedorListDetallereclamoproveedorToAttach);
            }
            detallecompra.setDetallereclamoproveedorList(attachedDetallereclamoproveedorList);
            em.persist(detallecompra);
            if (compra != null) {
                compra.getDetallecompraList().add(detallecompra);
                compra = em.merge(compra);
            }
            if (estado != null) {
                estado.getDetallecompraList().add(detallecompra);
                estado = em.merge(estado);
            }
            if (materiaprima != null) {
                materiaprima.getDetallecompraList().add(detallecompra);
                materiaprima = em.merge(materiaprima);
            }
            for (Detallereclamoproveedor detallereclamoproveedorListDetallereclamoproveedor : detallecompra.getDetallereclamoproveedorList()) {
                Detallecompra oldDetallecompraOfDetallereclamoproveedorListDetallereclamoproveedor = detallereclamoproveedorListDetallereclamoproveedor.getDetallecompra();
                detallereclamoproveedorListDetallereclamoproveedor.setDetallecompra(detallecompra);
                detallereclamoproveedorListDetallereclamoproveedor = em.merge(detallereclamoproveedorListDetallereclamoproveedor);
                if (oldDetallecompraOfDetallereclamoproveedorListDetallereclamoproveedor != null) {
                    oldDetallecompraOfDetallereclamoproveedorListDetallereclamoproveedor.getDetallereclamoproveedorList().remove(detallereclamoproveedorListDetallereclamoproveedor);
                    oldDetallecompraOfDetallereclamoproveedorListDetallereclamoproveedor = em.merge(oldDetallecompraOfDetallereclamoproveedorListDetallereclamoproveedor);
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
            List<Detallereclamoproveedor> detallereclamoproveedorListOld = persistentDetallecompra.getDetallereclamoproveedorList();
            List<Detallereclamoproveedor> detallereclamoproveedorListNew = detallecompra.getDetallereclamoproveedorList();
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
            List<Detallereclamoproveedor> attachedDetallereclamoproveedorListNew = new ArrayList<Detallereclamoproveedor>();
            for (Detallereclamoproveedor detallereclamoproveedorListNewDetallereclamoproveedorToAttach : detallereclamoproveedorListNew) {
                detallereclamoproveedorListNewDetallereclamoproveedorToAttach = em.getReference(detallereclamoproveedorListNewDetallereclamoproveedorToAttach.getClass(), detallereclamoproveedorListNewDetallereclamoproveedorToAttach.getDetallereclamoproveedorPK());
                attachedDetallereclamoproveedorListNew.add(detallereclamoproveedorListNewDetallereclamoproveedorToAttach);
            }
            detallereclamoproveedorListNew = attachedDetallereclamoproveedorListNew;
            detallecompra.setDetallereclamoproveedorList(detallereclamoproveedorListNew);
            detallecompra = em.merge(detallecompra);
            if (compraOld != null && !compraOld.equals(compraNew)) {
                compraOld.getDetallecompraList().remove(detallecompra);
                compraOld = em.merge(compraOld);
            }
            if (compraNew != null && !compraNew.equals(compraOld)) {
                compraNew.getDetallecompraList().add(detallecompra);
                compraNew = em.merge(compraNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getDetallecompraList().remove(detallecompra);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getDetallecompraList().add(detallecompra);
                estadoNew = em.merge(estadoNew);
            }
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getDetallecompraList().remove(detallecompra);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getDetallecompraList().add(detallecompra);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            for (Detallereclamoproveedor detallereclamoproveedorListOldDetallereclamoproveedor : detallereclamoproveedorListOld) {
                if (!detallereclamoproveedorListNew.contains(detallereclamoproveedorListOldDetallereclamoproveedor)) {
                    detallereclamoproveedorListOldDetallereclamoproveedor.setDetallecompra(null);
                    detallereclamoproveedorListOldDetallereclamoproveedor = em.merge(detallereclamoproveedorListOldDetallereclamoproveedor);
                }
            }
            for (Detallereclamoproveedor detallereclamoproveedorListNewDetallereclamoproveedor : detallereclamoproveedorListNew) {
                if (!detallereclamoproveedorListOld.contains(detallereclamoproveedorListNewDetallereclamoproveedor)) {
                    Detallecompra oldDetallecompraOfDetallereclamoproveedorListNewDetallereclamoproveedor = detallereclamoproveedorListNewDetallereclamoproveedor.getDetallecompra();
                    detallereclamoproveedorListNewDetallereclamoproveedor.setDetallecompra(detallecompra);
                    detallereclamoproveedorListNewDetallereclamoproveedor = em.merge(detallereclamoproveedorListNewDetallereclamoproveedor);
                    if (oldDetallecompraOfDetallereclamoproveedorListNewDetallereclamoproveedor != null && !oldDetallecompraOfDetallereclamoproveedorListNewDetallereclamoproveedor.equals(detallecompra)) {
                        oldDetallecompraOfDetallereclamoproveedorListNewDetallereclamoproveedor.getDetallereclamoproveedorList().remove(detallereclamoproveedorListNewDetallereclamoproveedor);
                        oldDetallecompraOfDetallereclamoproveedorListNewDetallereclamoproveedor = em.merge(oldDetallecompraOfDetallereclamoproveedorListNewDetallereclamoproveedor);
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
                compra.getDetallecompraList().remove(detallecompra);
                compra = em.merge(compra);
            }
            Estadodetallecompra estado = detallecompra.getEstado();
            if (estado != null) {
                estado.getDetallecompraList().remove(detallecompra);
                estado = em.merge(estado);
            }
            Materiaprima materiaprima = detallecompra.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getDetallecompraList().remove(detallecompra);
                materiaprima = em.merge(materiaprima);
            }
            List<Detallereclamoproveedor> detallereclamoproveedorList = detallecompra.getDetallereclamoproveedorList();
            for (Detallereclamoproveedor detallereclamoproveedorListDetallereclamoproveedor : detallereclamoproveedorList) {
                detallereclamoproveedorListDetallereclamoproveedor.setDetallecompra(null);
                detallereclamoproveedorListDetallereclamoproveedor = em.merge(detallereclamoproveedorListDetallereclamoproveedor);
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
