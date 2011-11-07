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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Estadoremito;
import metalsoft.datos.jpa.entity.Detalleremito;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Remito;

/**
 *
 * @author Nino
 */
public class RemitoJpaController implements Serializable {

    public RemitoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Remito remito) throws PreexistingEntityException, Exception {
        if (remito.getDetalleremitoList() == null) {
            remito.setDetalleremitoList(new ArrayList<Detalleremito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = remito.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                remito.setPedido(pedido);
            }
            Estadoremito estado = remito.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                remito.setEstado(estado);
            }
            List<Detalleremito> attachedDetalleremitoList = new ArrayList<Detalleremito>();
            for (Detalleremito detalleremitoListDetalleremitoToAttach : remito.getDetalleremitoList()) {
                detalleremitoListDetalleremitoToAttach = em.getReference(detalleremitoListDetalleremitoToAttach.getClass(), detalleremitoListDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoList.add(detalleremitoListDetalleremitoToAttach);
            }
            remito.setDetalleremitoList(attachedDetalleremitoList);
            em.persist(remito);
            if (pedido != null) {
                pedido.getRemitoList().add(remito);
                pedido = em.merge(pedido);
            }
            if (estado != null) {
                estado.getRemitoList().add(remito);
                estado = em.merge(estado);
            }
            for (Detalleremito detalleremitoListDetalleremito : remito.getDetalleremitoList()) {
                Remito oldRemitoOfDetalleremitoListDetalleremito = detalleremitoListDetalleremito.getRemito();
                detalleremitoListDetalleremito.setRemito(remito);
                detalleremitoListDetalleremito = em.merge(detalleremitoListDetalleremito);
                if (oldRemitoOfDetalleremitoListDetalleremito != null) {
                    oldRemitoOfDetalleremitoListDetalleremito.getDetalleremitoList().remove(detalleremitoListDetalleremito);
                    oldRemitoOfDetalleremitoListDetalleremito = em.merge(oldRemitoOfDetalleremitoListDetalleremito);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRemito(remito.getIdremito()) != null) {
                throw new PreexistingEntityException("Remito " + remito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Remito remito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remito persistentRemito = em.find(Remito.class, remito.getIdremito());
            Pedido pedidoOld = persistentRemito.getPedido();
            Pedido pedidoNew = remito.getPedido();
            Estadoremito estadoOld = persistentRemito.getEstado();
            Estadoremito estadoNew = remito.getEstado();
            List<Detalleremito> detalleremitoListOld = persistentRemito.getDetalleremitoList();
            List<Detalleremito> detalleremitoListNew = remito.getDetalleremitoList();
            List<String> illegalOrphanMessages = null;
            for (Detalleremito detalleremitoListOldDetalleremito : detalleremitoListOld) {
                if (!detalleremitoListNew.contains(detalleremitoListOldDetalleremito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleremito " + detalleremitoListOldDetalleremito + " since its remito field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                remito.setPedido(pedidoNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                remito.setEstado(estadoNew);
            }
            List<Detalleremito> attachedDetalleremitoListNew = new ArrayList<Detalleremito>();
            for (Detalleremito detalleremitoListNewDetalleremitoToAttach : detalleremitoListNew) {
                detalleremitoListNewDetalleremitoToAttach = em.getReference(detalleremitoListNewDetalleremitoToAttach.getClass(), detalleremitoListNewDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoListNew.add(detalleremitoListNewDetalleremitoToAttach);
            }
            detalleremitoListNew = attachedDetalleremitoListNew;
            remito.setDetalleremitoList(detalleremitoListNew);
            remito = em.merge(remito);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getRemitoList().remove(remito);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getRemitoList().add(remito);
                pedidoNew = em.merge(pedidoNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getRemitoList().remove(remito);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getRemitoList().add(remito);
                estadoNew = em.merge(estadoNew);
            }
            for (Detalleremito detalleremitoListNewDetalleremito : detalleremitoListNew) {
                if (!detalleremitoListOld.contains(detalleremitoListNewDetalleremito)) {
                    Remito oldRemitoOfDetalleremitoListNewDetalleremito = detalleremitoListNewDetalleremito.getRemito();
                    detalleremitoListNewDetalleremito.setRemito(remito);
                    detalleremitoListNewDetalleremito = em.merge(detalleremitoListNewDetalleremito);
                    if (oldRemitoOfDetalleremitoListNewDetalleremito != null && !oldRemitoOfDetalleremitoListNewDetalleremito.equals(remito)) {
                        oldRemitoOfDetalleremitoListNewDetalleremito.getDetalleremitoList().remove(detalleremitoListNewDetalleremito);
                        oldRemitoOfDetalleremitoListNewDetalleremito = em.merge(oldRemitoOfDetalleremitoListNewDetalleremito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = remito.getIdremito();
                if (findRemito(id) == null) {
                    throw new NonexistentEntityException("The remito with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remito remito;
            try {
                remito = em.getReference(Remito.class, id);
                remito.getIdremito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The remito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleremito> detalleremitoListOrphanCheck = remito.getDetalleremitoList();
            for (Detalleremito detalleremitoListOrphanCheckDetalleremito : detalleremitoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Remito (" + remito + ") cannot be destroyed since the Detalleremito " + detalleremitoListOrphanCheckDetalleremito + " in its detalleremitoList field has a non-nullable remito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedido = remito.getPedido();
            if (pedido != null) {
                pedido.getRemitoList().remove(remito);
                pedido = em.merge(pedido);
            }
            Estadoremito estado = remito.getEstado();
            if (estado != null) {
                estado.getRemitoList().remove(remito);
                estado = em.merge(estado);
            }
            em.remove(remito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Remito> findRemitoEntities() {
        return findRemitoEntities(true, -1, -1);
    }

    public List<Remito> findRemitoEntities(int maxResults, int firstResult) {
        return findRemitoEntities(false, maxResults, firstResult);
    }

    private List<Remito> findRemitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Remito.class));
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

    public Remito findRemito(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Remito.class, id);
        } finally {
            em.close();
        }
    }

    public int getRemitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Remito> rt = cq.from(Remito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
