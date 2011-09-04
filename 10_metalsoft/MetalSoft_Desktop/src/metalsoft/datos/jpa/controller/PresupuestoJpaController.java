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
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detallepresupuesto;
import metalsoft.datos.jpa.entity.Presupuesto;

/**
 *
 * @author Nino
 */
public class PresupuestoJpaController implements Serializable {

    public PresupuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Presupuesto presupuesto) throws PreexistingEntityException, Exception {
        if (presupuesto.getPedidoList() == null) {
            presupuesto.setPedidoList(new ArrayList<Pedido>());
        }
        if (presupuesto.getDetallepresupuestoList() == null) {
            presupuesto.setDetallepresupuestoList(new ArrayList<Detallepresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : presupuesto.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            presupuesto.setPedidoList(attachedPedidoList);
            List<Detallepresupuesto> attachedDetallepresupuestoList = new ArrayList<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoListDetallepresupuestoToAttach : presupuesto.getDetallepresupuestoList()) {
                detallepresupuestoListDetallepresupuestoToAttach = em.getReference(detallepresupuestoListDetallepresupuestoToAttach.getClass(), detallepresupuestoListDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoList.add(detallepresupuestoListDetallepresupuestoToAttach);
            }
            presupuesto.setDetallepresupuestoList(attachedDetallepresupuestoList);
            em.persist(presupuesto);
            for (Pedido pedidoListPedido : presupuesto.getPedidoList()) {
                Presupuesto oldPresupuestoOfPedidoListPedido = pedidoListPedido.getPresupuesto();
                pedidoListPedido.setPresupuesto(presupuesto);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldPresupuestoOfPedidoListPedido != null) {
                    oldPresupuestoOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldPresupuestoOfPedidoListPedido = em.merge(oldPresupuestoOfPedidoListPedido);
                }
            }
            for (Detallepresupuesto detallepresupuestoListDetallepresupuesto : presupuesto.getDetallepresupuestoList()) {
                Presupuesto oldIdpresupuestoOfDetallepresupuestoListDetallepresupuesto = detallepresupuestoListDetallepresupuesto.getIdpresupuesto();
                detallepresupuestoListDetallepresupuesto.setIdpresupuesto(presupuesto);
                detallepresupuestoListDetallepresupuesto = em.merge(detallepresupuestoListDetallepresupuesto);
                if (oldIdpresupuestoOfDetallepresupuestoListDetallepresupuesto != null) {
                    oldIdpresupuestoOfDetallepresupuestoListDetallepresupuesto.getDetallepresupuestoList().remove(detallepresupuestoListDetallepresupuesto);
                    oldIdpresupuestoOfDetallepresupuestoListDetallepresupuesto = em.merge(oldIdpresupuestoOfDetallepresupuestoListDetallepresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPresupuesto(presupuesto.getIdpresupuesto()) != null) {
                throw new PreexistingEntityException("Presupuesto " + presupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Presupuesto presupuesto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Presupuesto persistentPresupuesto = em.find(Presupuesto.class, presupuesto.getIdpresupuesto());
            List<Pedido> pedidoListOld = persistentPresupuesto.getPedidoList();
            List<Pedido> pedidoListNew = presupuesto.getPedidoList();
            List<Detallepresupuesto> detallepresupuestoListOld = persistentPresupuesto.getDetallepresupuestoList();
            List<Detallepresupuesto> detallepresupuestoListNew = presupuesto.getDetallepresupuestoList();
            List<String> illegalOrphanMessages = null;
            for (Detallepresupuesto detallepresupuestoListOldDetallepresupuesto : detallepresupuestoListOld) {
                if (!detallepresupuestoListNew.contains(detallepresupuestoListOldDetallepresupuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepresupuesto " + detallepresupuestoListOldDetallepresupuesto + " since its idpresupuesto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdpedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            presupuesto.setPedidoList(pedidoListNew);
            List<Detallepresupuesto> attachedDetallepresupuestoListNew = new ArrayList<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoListNewDetallepresupuestoToAttach : detallepresupuestoListNew) {
                detallepresupuestoListNewDetallepresupuestoToAttach = em.getReference(detallepresupuestoListNewDetallepresupuestoToAttach.getClass(), detallepresupuestoListNewDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoListNew.add(detallepresupuestoListNewDetallepresupuestoToAttach);
            }
            detallepresupuestoListNew = attachedDetallepresupuestoListNew;
            presupuesto.setDetallepresupuestoList(detallepresupuestoListNew);
            presupuesto = em.merge(presupuesto);
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setPresupuesto(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Presupuesto oldPresupuestoOfPedidoListNewPedido = pedidoListNewPedido.getPresupuesto();
                    pedidoListNewPedido.setPresupuesto(presupuesto);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldPresupuestoOfPedidoListNewPedido != null && !oldPresupuestoOfPedidoListNewPedido.equals(presupuesto)) {
                        oldPresupuestoOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldPresupuestoOfPedidoListNewPedido = em.merge(oldPresupuestoOfPedidoListNewPedido);
                    }
                }
            }
            for (Detallepresupuesto detallepresupuestoListNewDetallepresupuesto : detallepresupuestoListNew) {
                if (!detallepresupuestoListOld.contains(detallepresupuestoListNewDetallepresupuesto)) {
                    Presupuesto oldIdpresupuestoOfDetallepresupuestoListNewDetallepresupuesto = detallepresupuestoListNewDetallepresupuesto.getIdpresupuesto();
                    detallepresupuestoListNewDetallepresupuesto.setIdpresupuesto(presupuesto);
                    detallepresupuestoListNewDetallepresupuesto = em.merge(detallepresupuestoListNewDetallepresupuesto);
                    if (oldIdpresupuestoOfDetallepresupuestoListNewDetallepresupuesto != null && !oldIdpresupuestoOfDetallepresupuestoListNewDetallepresupuesto.equals(presupuesto)) {
                        oldIdpresupuestoOfDetallepresupuestoListNewDetallepresupuesto.getDetallepresupuestoList().remove(detallepresupuestoListNewDetallepresupuesto);
                        oldIdpresupuestoOfDetallepresupuestoListNewDetallepresupuesto = em.merge(oldIdpresupuestoOfDetallepresupuestoListNewDetallepresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = presupuesto.getIdpresupuesto();
                if (findPresupuesto(id) == null) {
                    throw new NonexistentEntityException("The presupuesto with id " + id + " no longer exists.");
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
            Presupuesto presupuesto;
            try {
                presupuesto = em.getReference(Presupuesto.class, id);
                presupuesto.getIdpresupuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuesto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallepresupuesto> detallepresupuestoListOrphanCheck = presupuesto.getDetallepresupuestoList();
            for (Detallepresupuesto detallepresupuestoListOrphanCheckDetallepresupuesto : detallepresupuestoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Presupuesto (" + presupuesto + ") cannot be destroyed since the Detallepresupuesto " + detallepresupuestoListOrphanCheckDetallepresupuesto + " in its detallepresupuestoList field has a non-nullable idpresupuesto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> pedidoList = presupuesto.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setPresupuesto(null);
                pedidoListPedido = em.merge(pedidoListPedido);
            }
            em.remove(presupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Presupuesto> findPresupuestoEntities() {
        return findPresupuestoEntities(true, -1, -1);
    }

    public List<Presupuesto> findPresupuestoEntities(int maxResults, int firstResult) {
        return findPresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Presupuesto> findPresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Presupuesto.class));
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

    public Presupuesto findPresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Presupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Presupuesto> rt = cq.from(Presupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
