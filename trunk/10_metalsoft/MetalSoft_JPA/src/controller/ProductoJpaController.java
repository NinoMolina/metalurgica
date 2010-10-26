/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detalleremito;
import java.util.HashSet;
import java.util.Set;
import entity.Detalleproducto;
import entity.Detallepedido;
import entity.Detallereclamocliente;
import entity.Detallepresupuesto;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ProductoJpaController {

    public ProductoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws PreexistingEntityException, Exception {
        if (producto.getDetalleremitoSet() == null) {
            producto.setDetalleremitoSet(new HashSet<Detalleremito>());
        }
        if (producto.getDetalleproductoSet() == null) {
            producto.setDetalleproductoSet(new HashSet<Detalleproducto>());
        }
        if (producto.getDetallepedidoSet() == null) {
            producto.setDetallepedidoSet(new HashSet<Detallepedido>());
        }
        if (producto.getDetallereclamoclienteSet() == null) {
            producto.setDetallereclamoclienteSet(new HashSet<Detallereclamocliente>());
        }
        if (producto.getDetallepresupuestoSet() == null) {
            producto.setDetallepresupuestoSet(new HashSet<Detallepresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Detalleremito> attachedDetalleremitoSet = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSetDetalleremitoToAttach : producto.getDetalleremitoSet()) {
                detalleremitoSetDetalleremitoToAttach = em.getReference(detalleremitoSetDetalleremitoToAttach.getClass(), detalleremitoSetDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSet.add(detalleremitoSetDetalleremitoToAttach);
            }
            producto.setDetalleremitoSet(attachedDetalleremitoSet);
            Set<Detalleproducto> attachedDetalleproductoSet = new HashSet<Detalleproducto>();
            for (Detalleproducto detalleproductoSetDetalleproductoToAttach : producto.getDetalleproductoSet()) {
                detalleproductoSetDetalleproductoToAttach = em.getReference(detalleproductoSetDetalleproductoToAttach.getClass(), detalleproductoSetDetalleproductoToAttach.getIddetalle());
                attachedDetalleproductoSet.add(detalleproductoSetDetalleproductoToAttach);
            }
            producto.setDetalleproductoSet(attachedDetalleproductoSet);
            Set<Detallepedido> attachedDetallepedidoSet = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSetDetallepedidoToAttach : producto.getDetallepedidoSet()) {
                detallepedidoSetDetallepedidoToAttach = em.getReference(detallepedidoSetDetallepedidoToAttach.getClass(), detallepedidoSetDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSet.add(detallepedidoSetDetallepedidoToAttach);
            }
            producto.setDetallepedidoSet(attachedDetallepedidoSet);
            Set<Detallereclamocliente> attachedDetallereclamoclienteSet = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSetDetallereclamoclienteToAttach : producto.getDetallereclamoclienteSet()) {
                detallereclamoclienteSetDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSetDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSetDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSet.add(detallereclamoclienteSetDetallereclamoclienteToAttach);
            }
            producto.setDetallereclamoclienteSet(attachedDetallereclamoclienteSet);
            Set<Detallepresupuesto> attachedDetallepresupuestoSet = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSetDetallepresupuestoToAttach : producto.getDetallepresupuestoSet()) {
                detallepresupuestoSetDetallepresupuestoToAttach = em.getReference(detallepresupuestoSetDetallepresupuestoToAttach.getClass(), detallepresupuestoSetDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSet.add(detallepresupuestoSetDetallepresupuestoToAttach);
            }
            producto.setDetallepresupuestoSet(attachedDetallepresupuestoSet);
            em.persist(producto);
            for (Detalleremito detalleremitoSetDetalleremito : producto.getDetalleremitoSet()) {
                Producto oldProductoOfDetalleremitoSetDetalleremito = detalleremitoSetDetalleremito.getProducto();
                detalleremitoSetDetalleremito.setProducto(producto);
                detalleremitoSetDetalleremito = em.merge(detalleremitoSetDetalleremito);
                if (oldProductoOfDetalleremitoSetDetalleremito != null) {
                    oldProductoOfDetalleremitoSetDetalleremito.getDetalleremitoSet().remove(detalleremitoSetDetalleremito);
                    oldProductoOfDetalleremitoSetDetalleremito = em.merge(oldProductoOfDetalleremitoSetDetalleremito);
                }
            }
            for (Detalleproducto detalleproductoSetDetalleproducto : producto.getDetalleproductoSet()) {
                Producto oldIdproductoOfDetalleproductoSetDetalleproducto = detalleproductoSetDetalleproducto.getIdproducto();
                detalleproductoSetDetalleproducto.setIdproducto(producto);
                detalleproductoSetDetalleproducto = em.merge(detalleproductoSetDetalleproducto);
                if (oldIdproductoOfDetalleproductoSetDetalleproducto != null) {
                    oldIdproductoOfDetalleproductoSetDetalleproducto.getDetalleproductoSet().remove(detalleproductoSetDetalleproducto);
                    oldIdproductoOfDetalleproductoSetDetalleproducto = em.merge(oldIdproductoOfDetalleproductoSetDetalleproducto);
                }
            }
            for (Detallepedido detallepedidoSetDetallepedido : producto.getDetallepedidoSet()) {
                Producto oldProductoOfDetallepedidoSetDetallepedido = detallepedidoSetDetallepedido.getProducto();
                detallepedidoSetDetallepedido.setProducto(producto);
                detallepedidoSetDetallepedido = em.merge(detallepedidoSetDetallepedido);
                if (oldProductoOfDetallepedidoSetDetallepedido != null) {
                    oldProductoOfDetallepedidoSetDetallepedido.getDetallepedidoSet().remove(detallepedidoSetDetallepedido);
                    oldProductoOfDetallepedidoSetDetallepedido = em.merge(oldProductoOfDetallepedidoSetDetallepedido);
                }
            }
            for (Detallereclamocliente detallereclamoclienteSetDetallereclamocliente : producto.getDetallereclamoclienteSet()) {
                Producto oldProductoOfDetallereclamoclienteSetDetallereclamocliente = detallereclamoclienteSetDetallereclamocliente.getProducto();
                detallereclamoclienteSetDetallereclamocliente.setProducto(producto);
                detallereclamoclienteSetDetallereclamocliente = em.merge(detallereclamoclienteSetDetallereclamocliente);
                if (oldProductoOfDetallereclamoclienteSetDetallereclamocliente != null) {
                    oldProductoOfDetallereclamoclienteSetDetallereclamocliente.getDetallereclamoclienteSet().remove(detallereclamoclienteSetDetallereclamocliente);
                    oldProductoOfDetallereclamoclienteSetDetallereclamocliente = em.merge(oldProductoOfDetallereclamoclienteSetDetallereclamocliente);
                }
            }
            for (Detallepresupuesto detallepresupuestoSetDetallepresupuesto : producto.getDetallepresupuestoSet()) {
                Producto oldIdproductoOfDetallepresupuestoSetDetallepresupuesto = detallepresupuestoSetDetallepresupuesto.getIdproducto();
                detallepresupuestoSetDetallepresupuesto.setIdproducto(producto);
                detallepresupuestoSetDetallepresupuesto = em.merge(detallepresupuestoSetDetallepresupuesto);
                if (oldIdproductoOfDetallepresupuestoSetDetallepresupuesto != null) {
                    oldIdproductoOfDetallepresupuestoSetDetallepresupuesto.getDetallepresupuestoSet().remove(detallepresupuestoSetDetallepresupuesto);
                    oldIdproductoOfDetallepresupuestoSetDetallepresupuesto = em.merge(oldIdproductoOfDetallepresupuestoSetDetallepresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProducto(producto.getIdproducto()) != null) {
                throw new PreexistingEntityException("Producto " + producto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdproducto());
            Set<Detalleremito> detalleremitoSetOld = persistentProducto.getDetalleremitoSet();
            Set<Detalleremito> detalleremitoSetNew = producto.getDetalleremitoSet();
            Set<Detalleproducto> detalleproductoSetOld = persistentProducto.getDetalleproductoSet();
            Set<Detalleproducto> detalleproductoSetNew = producto.getDetalleproductoSet();
            Set<Detallepedido> detallepedidoSetOld = persistentProducto.getDetallepedidoSet();
            Set<Detallepedido> detallepedidoSetNew = producto.getDetallepedidoSet();
            Set<Detallereclamocliente> detallereclamoclienteSetOld = persistentProducto.getDetallereclamoclienteSet();
            Set<Detallereclamocliente> detallereclamoclienteSetNew = producto.getDetallereclamoclienteSet();
            Set<Detallepresupuesto> detallepresupuestoSetOld = persistentProducto.getDetallepresupuestoSet();
            Set<Detallepresupuesto> detallepresupuestoSetNew = producto.getDetallepresupuestoSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleproducto detalleproductoSetOldDetalleproducto : detalleproductoSetOld) {
                if (!detalleproductoSetNew.contains(detalleproductoSetOldDetalleproducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleproducto " + detalleproductoSetOldDetalleproducto + " since its idproducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Detalleremito> attachedDetalleremitoSetNew = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSetNewDetalleremitoToAttach : detalleremitoSetNew) {
                detalleremitoSetNewDetalleremitoToAttach = em.getReference(detalleremitoSetNewDetalleremitoToAttach.getClass(), detalleremitoSetNewDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSetNew.add(detalleremitoSetNewDetalleremitoToAttach);
            }
            detalleremitoSetNew = attachedDetalleremitoSetNew;
            producto.setDetalleremitoSet(detalleremitoSetNew);
            Set<Detalleproducto> attachedDetalleproductoSetNew = new HashSet<Detalleproducto>();
            for (Detalleproducto detalleproductoSetNewDetalleproductoToAttach : detalleproductoSetNew) {
                detalleproductoSetNewDetalleproductoToAttach = em.getReference(detalleproductoSetNewDetalleproductoToAttach.getClass(), detalleproductoSetNewDetalleproductoToAttach.getIddetalle());
                attachedDetalleproductoSetNew.add(detalleproductoSetNewDetalleproductoToAttach);
            }
            detalleproductoSetNew = attachedDetalleproductoSetNew;
            producto.setDetalleproductoSet(detalleproductoSetNew);
            Set<Detallepedido> attachedDetallepedidoSetNew = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSetNewDetallepedidoToAttach : detallepedidoSetNew) {
                detallepedidoSetNewDetallepedidoToAttach = em.getReference(detallepedidoSetNewDetallepedidoToAttach.getClass(), detallepedidoSetNewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSetNew.add(detallepedidoSetNewDetallepedidoToAttach);
            }
            detallepedidoSetNew = attachedDetallepedidoSetNew;
            producto.setDetallepedidoSet(detallepedidoSetNew);
            Set<Detallereclamocliente> attachedDetallereclamoclienteSetNew = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSetNewDetallereclamoclienteToAttach : detallereclamoclienteSetNew) {
                detallereclamoclienteSetNewDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSetNewDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSetNewDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSetNew.add(detallereclamoclienteSetNewDetallereclamoclienteToAttach);
            }
            detallereclamoclienteSetNew = attachedDetallereclamoclienteSetNew;
            producto.setDetallereclamoclienteSet(detallereclamoclienteSetNew);
            Set<Detallepresupuesto> attachedDetallepresupuestoSetNew = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSetNewDetallepresupuestoToAttach : detallepresupuestoSetNew) {
                detallepresupuestoSetNewDetallepresupuestoToAttach = em.getReference(detallepresupuestoSetNewDetallepresupuestoToAttach.getClass(), detallepresupuestoSetNewDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSetNew.add(detallepresupuestoSetNewDetallepresupuestoToAttach);
            }
            detallepresupuestoSetNew = attachedDetallepresupuestoSetNew;
            producto.setDetallepresupuestoSet(detallepresupuestoSetNew);
            producto = em.merge(producto);
            for (Detalleremito detalleremitoSetOldDetalleremito : detalleremitoSetOld) {
                if (!detalleremitoSetNew.contains(detalleremitoSetOldDetalleremito)) {
                    detalleremitoSetOldDetalleremito.setProducto(null);
                    detalleremitoSetOldDetalleremito = em.merge(detalleremitoSetOldDetalleremito);
                }
            }
            for (Detalleremito detalleremitoSetNewDetalleremito : detalleremitoSetNew) {
                if (!detalleremitoSetOld.contains(detalleremitoSetNewDetalleremito)) {
                    Producto oldProductoOfDetalleremitoSetNewDetalleremito = detalleremitoSetNewDetalleremito.getProducto();
                    detalleremitoSetNewDetalleremito.setProducto(producto);
                    detalleremitoSetNewDetalleremito = em.merge(detalleremitoSetNewDetalleremito);
                    if (oldProductoOfDetalleremitoSetNewDetalleremito != null && !oldProductoOfDetalleremitoSetNewDetalleremito.equals(producto)) {
                        oldProductoOfDetalleremitoSetNewDetalleremito.getDetalleremitoSet().remove(detalleremitoSetNewDetalleremito);
                        oldProductoOfDetalleremitoSetNewDetalleremito = em.merge(oldProductoOfDetalleremitoSetNewDetalleremito);
                    }
                }
            }
            for (Detalleproducto detalleproductoSetNewDetalleproducto : detalleproductoSetNew) {
                if (!detalleproductoSetOld.contains(detalleproductoSetNewDetalleproducto)) {
                    Producto oldIdproductoOfDetalleproductoSetNewDetalleproducto = detalleproductoSetNewDetalleproducto.getIdproducto();
                    detalleproductoSetNewDetalleproducto.setIdproducto(producto);
                    detalleproductoSetNewDetalleproducto = em.merge(detalleproductoSetNewDetalleproducto);
                    if (oldIdproductoOfDetalleproductoSetNewDetalleproducto != null && !oldIdproductoOfDetalleproductoSetNewDetalleproducto.equals(producto)) {
                        oldIdproductoOfDetalleproductoSetNewDetalleproducto.getDetalleproductoSet().remove(detalleproductoSetNewDetalleproducto);
                        oldIdproductoOfDetalleproductoSetNewDetalleproducto = em.merge(oldIdproductoOfDetalleproductoSetNewDetalleproducto);
                    }
                }
            }
            for (Detallepedido detallepedidoSetOldDetallepedido : detallepedidoSetOld) {
                if (!detallepedidoSetNew.contains(detallepedidoSetOldDetallepedido)) {
                    detallepedidoSetOldDetallepedido.setProducto(null);
                    detallepedidoSetOldDetallepedido = em.merge(detallepedidoSetOldDetallepedido);
                }
            }
            for (Detallepedido detallepedidoSetNewDetallepedido : detallepedidoSetNew) {
                if (!detallepedidoSetOld.contains(detallepedidoSetNewDetallepedido)) {
                    Producto oldProductoOfDetallepedidoSetNewDetallepedido = detallepedidoSetNewDetallepedido.getProducto();
                    detallepedidoSetNewDetallepedido.setProducto(producto);
                    detallepedidoSetNewDetallepedido = em.merge(detallepedidoSetNewDetallepedido);
                    if (oldProductoOfDetallepedidoSetNewDetallepedido != null && !oldProductoOfDetallepedidoSetNewDetallepedido.equals(producto)) {
                        oldProductoOfDetallepedidoSetNewDetallepedido.getDetallepedidoSet().remove(detallepedidoSetNewDetallepedido);
                        oldProductoOfDetallepedidoSetNewDetallepedido = em.merge(oldProductoOfDetallepedidoSetNewDetallepedido);
                    }
                }
            }
            for (Detallereclamocliente detallereclamoclienteSetOldDetallereclamocliente : detallereclamoclienteSetOld) {
                if (!detallereclamoclienteSetNew.contains(detallereclamoclienteSetOldDetallereclamocliente)) {
                    detallereclamoclienteSetOldDetallereclamocliente.setProducto(null);
                    detallereclamoclienteSetOldDetallereclamocliente = em.merge(detallereclamoclienteSetOldDetallereclamocliente);
                }
            }
            for (Detallereclamocliente detallereclamoclienteSetNewDetallereclamocliente : detallereclamoclienteSetNew) {
                if (!detallereclamoclienteSetOld.contains(detallereclamoclienteSetNewDetallereclamocliente)) {
                    Producto oldProductoOfDetallereclamoclienteSetNewDetallereclamocliente = detallereclamoclienteSetNewDetallereclamocliente.getProducto();
                    detallereclamoclienteSetNewDetallereclamocliente.setProducto(producto);
                    detallereclamoclienteSetNewDetallereclamocliente = em.merge(detallereclamoclienteSetNewDetallereclamocliente);
                    if (oldProductoOfDetallereclamoclienteSetNewDetallereclamocliente != null && !oldProductoOfDetallereclamoclienteSetNewDetallereclamocliente.equals(producto)) {
                        oldProductoOfDetallereclamoclienteSetNewDetallereclamocliente.getDetallereclamoclienteSet().remove(detallereclamoclienteSetNewDetallereclamocliente);
                        oldProductoOfDetallereclamoclienteSetNewDetallereclamocliente = em.merge(oldProductoOfDetallereclamoclienteSetNewDetallereclamocliente);
                    }
                }
            }
            for (Detallepresupuesto detallepresupuestoSetOldDetallepresupuesto : detallepresupuestoSetOld) {
                if (!detallepresupuestoSetNew.contains(detallepresupuestoSetOldDetallepresupuesto)) {
                    detallepresupuestoSetOldDetallepresupuesto.setIdproducto(null);
                    detallepresupuestoSetOldDetallepresupuesto = em.merge(detallepresupuestoSetOldDetallepresupuesto);
                }
            }
            for (Detallepresupuesto detallepresupuestoSetNewDetallepresupuesto : detallepresupuestoSetNew) {
                if (!detallepresupuestoSetOld.contains(detallepresupuestoSetNewDetallepresupuesto)) {
                    Producto oldIdproductoOfDetallepresupuestoSetNewDetallepresupuesto = detallepresupuestoSetNewDetallepresupuesto.getIdproducto();
                    detallepresupuestoSetNewDetallepresupuesto.setIdproducto(producto);
                    detallepresupuestoSetNewDetallepresupuesto = em.merge(detallepresupuestoSetNewDetallepresupuesto);
                    if (oldIdproductoOfDetallepresupuestoSetNewDetallepresupuesto != null && !oldIdproductoOfDetallepresupuestoSetNewDetallepresupuesto.equals(producto)) {
                        oldIdproductoOfDetallepresupuestoSetNewDetallepresupuesto.getDetallepresupuestoSet().remove(detallepresupuestoSetNewDetallepresupuesto);
                        oldIdproductoOfDetallepresupuestoSetNewDetallepresupuesto = em.merge(oldIdproductoOfDetallepresupuestoSetNewDetallepresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = producto.getIdproducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdproducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleproducto> detalleproductoSetOrphanCheck = producto.getDetalleproductoSet();
            for (Detalleproducto detalleproductoSetOrphanCheckDetalleproducto : detalleproductoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Detalleproducto " + detalleproductoSetOrphanCheckDetalleproducto + " in its detalleproductoSet field has a non-nullable idproducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Detalleremito> detalleremitoSet = producto.getDetalleremitoSet();
            for (Detalleremito detalleremitoSetDetalleremito : detalleremitoSet) {
                detalleremitoSetDetalleremito.setProducto(null);
                detalleremitoSetDetalleremito = em.merge(detalleremitoSetDetalleremito);
            }
            Set<Detallepedido> detallepedidoSet = producto.getDetallepedidoSet();
            for (Detallepedido detallepedidoSetDetallepedido : detallepedidoSet) {
                detallepedidoSetDetallepedido.setProducto(null);
                detallepedidoSetDetallepedido = em.merge(detallepedidoSetDetallepedido);
            }
            Set<Detallereclamocliente> detallereclamoclienteSet = producto.getDetallereclamoclienteSet();
            for (Detallereclamocliente detallereclamoclienteSetDetallereclamocliente : detallereclamoclienteSet) {
                detallereclamoclienteSetDetallereclamocliente.setProducto(null);
                detallereclamoclienteSetDetallereclamocliente = em.merge(detallereclamoclienteSetDetallereclamocliente);
            }
            Set<Detallepresupuesto> detallepresupuestoSet = producto.getDetallepresupuestoSet();
            for (Detallepresupuesto detallepresupuestoSetDetallepresupuesto : detallepresupuestoSet) {
                detallepresupuestoSetDetallepresupuesto.setIdproducto(null);
                detallepresupuestoSetDetallepresupuesto = em.merge(detallepresupuestoSetDetallepresupuesto);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
