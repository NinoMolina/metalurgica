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
        if (producto.getDetalleremitoSet1() == null) {
            producto.setDetalleremitoSet1(new HashSet<Detalleremito>());
        }
        if (producto.getDetalleproductoSet() == null) {
            producto.setDetalleproductoSet(new HashSet<Detalleproducto>());
        }
        if (producto.getDetalleproductoSet1() == null) {
            producto.setDetalleproductoSet1(new HashSet<Detalleproducto>());
        }
        if (producto.getDetallepedidoSet() == null) {
            producto.setDetallepedidoSet(new HashSet<Detallepedido>());
        }
        if (producto.getDetallepedidoSet1() == null) {
            producto.setDetallepedidoSet1(new HashSet<Detallepedido>());
        }
        if (producto.getDetallereclamoclienteSet() == null) {
            producto.setDetallereclamoclienteSet(new HashSet<Detallereclamocliente>());
        }
        if (producto.getDetallereclamoclienteSet1() == null) {
            producto.setDetallereclamoclienteSet1(new HashSet<Detallereclamocliente>());
        }
        if (producto.getDetallepresupuestoSet() == null) {
            producto.setDetallepresupuestoSet(new HashSet<Detallepresupuesto>());
        }
        if (producto.getDetallepresupuestoSet1() == null) {
            producto.setDetallepresupuestoSet1(new HashSet<Detallepresupuesto>());
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
            Set<Detalleremito> attachedDetalleremitoSet1 = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSet1DetalleremitoToAttach : producto.getDetalleremitoSet1()) {
                detalleremitoSet1DetalleremitoToAttach = em.getReference(detalleremitoSet1DetalleremitoToAttach.getClass(), detalleremitoSet1DetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSet1.add(detalleremitoSet1DetalleremitoToAttach);
            }
            producto.setDetalleremitoSet1(attachedDetalleremitoSet1);
            Set<Detalleproducto> attachedDetalleproductoSet = new HashSet<Detalleproducto>();
            for (Detalleproducto detalleproductoSetDetalleproductoToAttach : producto.getDetalleproductoSet()) {
                detalleproductoSetDetalleproductoToAttach = em.getReference(detalleproductoSetDetalleproductoToAttach.getClass(), detalleproductoSetDetalleproductoToAttach.getIddetalle());
                attachedDetalleproductoSet.add(detalleproductoSetDetalleproductoToAttach);
            }
            producto.setDetalleproductoSet(attachedDetalleproductoSet);
            Set<Detalleproducto> attachedDetalleproductoSet1 = new HashSet<Detalleproducto>();
            for (Detalleproducto detalleproductoSet1DetalleproductoToAttach : producto.getDetalleproductoSet1()) {
                detalleproductoSet1DetalleproductoToAttach = em.getReference(detalleproductoSet1DetalleproductoToAttach.getClass(), detalleproductoSet1DetalleproductoToAttach.getIddetalle());
                attachedDetalleproductoSet1.add(detalleproductoSet1DetalleproductoToAttach);
            }
            producto.setDetalleproductoSet1(attachedDetalleproductoSet1);
            Set<Detallepedido> attachedDetallepedidoSet = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSetDetallepedidoToAttach : producto.getDetallepedidoSet()) {
                detallepedidoSetDetallepedidoToAttach = em.getReference(detallepedidoSetDetallepedidoToAttach.getClass(), detallepedidoSetDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSet.add(detallepedidoSetDetallepedidoToAttach);
            }
            producto.setDetallepedidoSet(attachedDetallepedidoSet);
            Set<Detallepedido> attachedDetallepedidoSet1 = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSet1DetallepedidoToAttach : producto.getDetallepedidoSet1()) {
                detallepedidoSet1DetallepedidoToAttach = em.getReference(detallepedidoSet1DetallepedidoToAttach.getClass(), detallepedidoSet1DetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSet1.add(detallepedidoSet1DetallepedidoToAttach);
            }
            producto.setDetallepedidoSet1(attachedDetallepedidoSet1);
            Set<Detallereclamocliente> attachedDetallereclamoclienteSet = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSetDetallereclamoclienteToAttach : producto.getDetallereclamoclienteSet()) {
                detallereclamoclienteSetDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSetDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSetDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSet.add(detallereclamoclienteSetDetallereclamoclienteToAttach);
            }
            producto.setDetallereclamoclienteSet(attachedDetallereclamoclienteSet);
            Set<Detallereclamocliente> attachedDetallereclamoclienteSet1 = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSet1DetallereclamoclienteToAttach : producto.getDetallereclamoclienteSet1()) {
                detallereclamoclienteSet1DetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSet1DetallereclamoclienteToAttach.getClass(), detallereclamoclienteSet1DetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSet1.add(detallereclamoclienteSet1DetallereclamoclienteToAttach);
            }
            producto.setDetallereclamoclienteSet1(attachedDetallereclamoclienteSet1);
            Set<Detallepresupuesto> attachedDetallepresupuestoSet = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSetDetallepresupuestoToAttach : producto.getDetallepresupuestoSet()) {
                detallepresupuestoSetDetallepresupuestoToAttach = em.getReference(detallepresupuestoSetDetallepresupuestoToAttach.getClass(), detallepresupuestoSetDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSet.add(detallepresupuestoSetDetallepresupuestoToAttach);
            }
            producto.setDetallepresupuestoSet(attachedDetallepresupuestoSet);
            Set<Detallepresupuesto> attachedDetallepresupuestoSet1 = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSet1DetallepresupuestoToAttach : producto.getDetallepresupuestoSet1()) {
                detallepresupuestoSet1DetallepresupuestoToAttach = em.getReference(detallepresupuestoSet1DetallepresupuestoToAttach.getClass(), detallepresupuestoSet1DetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSet1.add(detallepresupuestoSet1DetallepresupuestoToAttach);
            }
            producto.setDetallepresupuestoSet1(attachedDetallepresupuestoSet1);
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
            for (Detalleremito detalleremitoSet1Detalleremito : producto.getDetalleremitoSet1()) {
                Producto oldProducto1OfDetalleremitoSet1Detalleremito = detalleremitoSet1Detalleremito.getProducto1();
                detalleremitoSet1Detalleremito.setProducto1(producto);
                detalleremitoSet1Detalleremito = em.merge(detalleremitoSet1Detalleremito);
                if (oldProducto1OfDetalleremitoSet1Detalleremito != null) {
                    oldProducto1OfDetalleremitoSet1Detalleremito.getDetalleremitoSet1().remove(detalleremitoSet1Detalleremito);
                    oldProducto1OfDetalleremitoSet1Detalleremito = em.merge(oldProducto1OfDetalleremitoSet1Detalleremito);
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
            for (Detalleproducto detalleproductoSet1Detalleproducto : producto.getDetalleproductoSet1()) {
                Producto oldIdproducto1OfDetalleproductoSet1Detalleproducto = detalleproductoSet1Detalleproducto.getIdproducto1();
                detalleproductoSet1Detalleproducto.setIdproducto1(producto);
                detalleproductoSet1Detalleproducto = em.merge(detalleproductoSet1Detalleproducto);
                if (oldIdproducto1OfDetalleproductoSet1Detalleproducto != null) {
                    oldIdproducto1OfDetalleproductoSet1Detalleproducto.getDetalleproductoSet1().remove(detalleproductoSet1Detalleproducto);
                    oldIdproducto1OfDetalleproductoSet1Detalleproducto = em.merge(oldIdproducto1OfDetalleproductoSet1Detalleproducto);
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
            for (Detallepedido detallepedidoSet1Detallepedido : producto.getDetallepedidoSet1()) {
                Producto oldProducto1OfDetallepedidoSet1Detallepedido = detallepedidoSet1Detallepedido.getProducto1();
                detallepedidoSet1Detallepedido.setProducto1(producto);
                detallepedidoSet1Detallepedido = em.merge(detallepedidoSet1Detallepedido);
                if (oldProducto1OfDetallepedidoSet1Detallepedido != null) {
                    oldProducto1OfDetallepedidoSet1Detallepedido.getDetallepedidoSet1().remove(detallepedidoSet1Detallepedido);
                    oldProducto1OfDetallepedidoSet1Detallepedido = em.merge(oldProducto1OfDetallepedidoSet1Detallepedido);
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
            for (Detallereclamocliente detallereclamoclienteSet1Detallereclamocliente : producto.getDetallereclamoclienteSet1()) {
                Producto oldProducto1OfDetallereclamoclienteSet1Detallereclamocliente = detallereclamoclienteSet1Detallereclamocliente.getProducto1();
                detallereclamoclienteSet1Detallereclamocliente.setProducto1(producto);
                detallereclamoclienteSet1Detallereclamocliente = em.merge(detallereclamoclienteSet1Detallereclamocliente);
                if (oldProducto1OfDetallereclamoclienteSet1Detallereclamocliente != null) {
                    oldProducto1OfDetallereclamoclienteSet1Detallereclamocliente.getDetallereclamoclienteSet1().remove(detallereclamoclienteSet1Detallereclamocliente);
                    oldProducto1OfDetallereclamoclienteSet1Detallereclamocliente = em.merge(oldProducto1OfDetallereclamoclienteSet1Detallereclamocliente);
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
            for (Detallepresupuesto detallepresupuestoSet1Detallepresupuesto : producto.getDetallepresupuestoSet1()) {
                Producto oldIdproducto1OfDetallepresupuestoSet1Detallepresupuesto = detallepresupuestoSet1Detallepresupuesto.getIdproducto1();
                detallepresupuestoSet1Detallepresupuesto.setIdproducto1(producto);
                detallepresupuestoSet1Detallepresupuesto = em.merge(detallepresupuestoSet1Detallepresupuesto);
                if (oldIdproducto1OfDetallepresupuestoSet1Detallepresupuesto != null) {
                    oldIdproducto1OfDetallepresupuestoSet1Detallepresupuesto.getDetallepresupuestoSet1().remove(detallepresupuestoSet1Detallepresupuesto);
                    oldIdproducto1OfDetallepresupuestoSet1Detallepresupuesto = em.merge(oldIdproducto1OfDetallepresupuestoSet1Detallepresupuesto);
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
            Set<Detalleremito> detalleremitoSet1Old = persistentProducto.getDetalleremitoSet1();
            Set<Detalleremito> detalleremitoSet1New = producto.getDetalleremitoSet1();
            Set<Detalleproducto> detalleproductoSetOld = persistentProducto.getDetalleproductoSet();
            Set<Detalleproducto> detalleproductoSetNew = producto.getDetalleproductoSet();
            Set<Detalleproducto> detalleproductoSet1Old = persistentProducto.getDetalleproductoSet1();
            Set<Detalleproducto> detalleproductoSet1New = producto.getDetalleproductoSet1();
            Set<Detallepedido> detallepedidoSetOld = persistentProducto.getDetallepedidoSet();
            Set<Detallepedido> detallepedidoSetNew = producto.getDetallepedidoSet();
            Set<Detallepedido> detallepedidoSet1Old = persistentProducto.getDetallepedidoSet1();
            Set<Detallepedido> detallepedidoSet1New = producto.getDetallepedidoSet1();
            Set<Detallereclamocliente> detallereclamoclienteSetOld = persistentProducto.getDetallereclamoclienteSet();
            Set<Detallereclamocliente> detallereclamoclienteSetNew = producto.getDetallereclamoclienteSet();
            Set<Detallereclamocliente> detallereclamoclienteSet1Old = persistentProducto.getDetallereclamoclienteSet1();
            Set<Detallereclamocliente> detallereclamoclienteSet1New = producto.getDetallereclamoclienteSet1();
            Set<Detallepresupuesto> detallepresupuestoSetOld = persistentProducto.getDetallepresupuestoSet();
            Set<Detallepresupuesto> detallepresupuestoSetNew = producto.getDetallepresupuestoSet();
            Set<Detallepresupuesto> detallepresupuestoSet1Old = persistentProducto.getDetallepresupuestoSet1();
            Set<Detallepresupuesto> detallepresupuestoSet1New = producto.getDetallepresupuestoSet1();
            List<String> illegalOrphanMessages = null;
            for (Detalleproducto detalleproductoSetOldDetalleproducto : detalleproductoSetOld) {
                if (!detalleproductoSetNew.contains(detalleproductoSetOldDetalleproducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleproducto " + detalleproductoSetOldDetalleproducto + " since its idproducto field is not nullable.");
                }
            }
            for (Detalleproducto detalleproductoSet1OldDetalleproducto : detalleproductoSet1Old) {
                if (!detalleproductoSet1New.contains(detalleproductoSet1OldDetalleproducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleproducto " + detalleproductoSet1OldDetalleproducto + " since its idproducto1 field is not nullable.");
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
            Set<Detalleremito> attachedDetalleremitoSet1New = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSet1NewDetalleremitoToAttach : detalleremitoSet1New) {
                detalleremitoSet1NewDetalleremitoToAttach = em.getReference(detalleremitoSet1NewDetalleremitoToAttach.getClass(), detalleremitoSet1NewDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSet1New.add(detalleremitoSet1NewDetalleremitoToAttach);
            }
            detalleremitoSet1New = attachedDetalleremitoSet1New;
            producto.setDetalleremitoSet1(detalleremitoSet1New);
            Set<Detalleproducto> attachedDetalleproductoSetNew = new HashSet<Detalleproducto>();
            for (Detalleproducto detalleproductoSetNewDetalleproductoToAttach : detalleproductoSetNew) {
                detalleproductoSetNewDetalleproductoToAttach = em.getReference(detalleproductoSetNewDetalleproductoToAttach.getClass(), detalleproductoSetNewDetalleproductoToAttach.getIddetalle());
                attachedDetalleproductoSetNew.add(detalleproductoSetNewDetalleproductoToAttach);
            }
            detalleproductoSetNew = attachedDetalleproductoSetNew;
            producto.setDetalleproductoSet(detalleproductoSetNew);
            Set<Detalleproducto> attachedDetalleproductoSet1New = new HashSet<Detalleproducto>();
            for (Detalleproducto detalleproductoSet1NewDetalleproductoToAttach : detalleproductoSet1New) {
                detalleproductoSet1NewDetalleproductoToAttach = em.getReference(detalleproductoSet1NewDetalleproductoToAttach.getClass(), detalleproductoSet1NewDetalleproductoToAttach.getIddetalle());
                attachedDetalleproductoSet1New.add(detalleproductoSet1NewDetalleproductoToAttach);
            }
            detalleproductoSet1New = attachedDetalleproductoSet1New;
            producto.setDetalleproductoSet1(detalleproductoSet1New);
            Set<Detallepedido> attachedDetallepedidoSetNew = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSetNewDetallepedidoToAttach : detallepedidoSetNew) {
                detallepedidoSetNewDetallepedidoToAttach = em.getReference(detallepedidoSetNewDetallepedidoToAttach.getClass(), detallepedidoSetNewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSetNew.add(detallepedidoSetNewDetallepedidoToAttach);
            }
            detallepedidoSetNew = attachedDetallepedidoSetNew;
            producto.setDetallepedidoSet(detallepedidoSetNew);
            Set<Detallepedido> attachedDetallepedidoSet1New = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSet1NewDetallepedidoToAttach : detallepedidoSet1New) {
                detallepedidoSet1NewDetallepedidoToAttach = em.getReference(detallepedidoSet1NewDetallepedidoToAttach.getClass(), detallepedidoSet1NewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSet1New.add(detallepedidoSet1NewDetallepedidoToAttach);
            }
            detallepedidoSet1New = attachedDetallepedidoSet1New;
            producto.setDetallepedidoSet1(detallepedidoSet1New);
            Set<Detallereclamocliente> attachedDetallereclamoclienteSetNew = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSetNewDetallereclamoclienteToAttach : detallereclamoclienteSetNew) {
                detallereclamoclienteSetNewDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSetNewDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSetNewDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSetNew.add(detallereclamoclienteSetNewDetallereclamoclienteToAttach);
            }
            detallereclamoclienteSetNew = attachedDetallereclamoclienteSetNew;
            producto.setDetallereclamoclienteSet(detallereclamoclienteSetNew);
            Set<Detallereclamocliente> attachedDetallereclamoclienteSet1New = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSet1NewDetallereclamoclienteToAttach : detallereclamoclienteSet1New) {
                detallereclamoclienteSet1NewDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSet1NewDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSet1NewDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSet1New.add(detallereclamoclienteSet1NewDetallereclamoclienteToAttach);
            }
            detallereclamoclienteSet1New = attachedDetallereclamoclienteSet1New;
            producto.setDetallereclamoclienteSet1(detallereclamoclienteSet1New);
            Set<Detallepresupuesto> attachedDetallepresupuestoSetNew = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSetNewDetallepresupuestoToAttach : detallepresupuestoSetNew) {
                detallepresupuestoSetNewDetallepresupuestoToAttach = em.getReference(detallepresupuestoSetNewDetallepresupuestoToAttach.getClass(), detallepresupuestoSetNewDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSetNew.add(detallepresupuestoSetNewDetallepresupuestoToAttach);
            }
            detallepresupuestoSetNew = attachedDetallepresupuestoSetNew;
            producto.setDetallepresupuestoSet(detallepresupuestoSetNew);
            Set<Detallepresupuesto> attachedDetallepresupuestoSet1New = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSet1NewDetallepresupuestoToAttach : detallepresupuestoSet1New) {
                detallepresupuestoSet1NewDetallepresupuestoToAttach = em.getReference(detallepresupuestoSet1NewDetallepresupuestoToAttach.getClass(), detallepresupuestoSet1NewDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSet1New.add(detallepresupuestoSet1NewDetallepresupuestoToAttach);
            }
            detallepresupuestoSet1New = attachedDetallepresupuestoSet1New;
            producto.setDetallepresupuestoSet1(detallepresupuestoSet1New);
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
            for (Detalleremito detalleremitoSet1OldDetalleremito : detalleremitoSet1Old) {
                if (!detalleremitoSet1New.contains(detalleremitoSet1OldDetalleremito)) {
                    detalleremitoSet1OldDetalleremito.setProducto1(null);
                    detalleremitoSet1OldDetalleremito = em.merge(detalleremitoSet1OldDetalleremito);
                }
            }
            for (Detalleremito detalleremitoSet1NewDetalleremito : detalleremitoSet1New) {
                if (!detalleremitoSet1Old.contains(detalleremitoSet1NewDetalleremito)) {
                    Producto oldProducto1OfDetalleremitoSet1NewDetalleremito = detalleremitoSet1NewDetalleremito.getProducto1();
                    detalleremitoSet1NewDetalleremito.setProducto1(producto);
                    detalleremitoSet1NewDetalleremito = em.merge(detalleremitoSet1NewDetalleremito);
                    if (oldProducto1OfDetalleremitoSet1NewDetalleremito != null && !oldProducto1OfDetalleremitoSet1NewDetalleremito.equals(producto)) {
                        oldProducto1OfDetalleremitoSet1NewDetalleremito.getDetalleremitoSet1().remove(detalleremitoSet1NewDetalleremito);
                        oldProducto1OfDetalleremitoSet1NewDetalleremito = em.merge(oldProducto1OfDetalleremitoSet1NewDetalleremito);
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
            for (Detalleproducto detalleproductoSet1NewDetalleproducto : detalleproductoSet1New) {
                if (!detalleproductoSet1Old.contains(detalleproductoSet1NewDetalleproducto)) {
                    Producto oldIdproducto1OfDetalleproductoSet1NewDetalleproducto = detalleproductoSet1NewDetalleproducto.getIdproducto1();
                    detalleproductoSet1NewDetalleproducto.setIdproducto1(producto);
                    detalleproductoSet1NewDetalleproducto = em.merge(detalleproductoSet1NewDetalleproducto);
                    if (oldIdproducto1OfDetalleproductoSet1NewDetalleproducto != null && !oldIdproducto1OfDetalleproductoSet1NewDetalleproducto.equals(producto)) {
                        oldIdproducto1OfDetalleproductoSet1NewDetalleproducto.getDetalleproductoSet1().remove(detalleproductoSet1NewDetalleproducto);
                        oldIdproducto1OfDetalleproductoSet1NewDetalleproducto = em.merge(oldIdproducto1OfDetalleproductoSet1NewDetalleproducto);
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
            for (Detallepedido detallepedidoSet1OldDetallepedido : detallepedidoSet1Old) {
                if (!detallepedidoSet1New.contains(detallepedidoSet1OldDetallepedido)) {
                    detallepedidoSet1OldDetallepedido.setProducto1(null);
                    detallepedidoSet1OldDetallepedido = em.merge(detallepedidoSet1OldDetallepedido);
                }
            }
            for (Detallepedido detallepedidoSet1NewDetallepedido : detallepedidoSet1New) {
                if (!detallepedidoSet1Old.contains(detallepedidoSet1NewDetallepedido)) {
                    Producto oldProducto1OfDetallepedidoSet1NewDetallepedido = detallepedidoSet1NewDetallepedido.getProducto1();
                    detallepedidoSet1NewDetallepedido.setProducto1(producto);
                    detallepedidoSet1NewDetallepedido = em.merge(detallepedidoSet1NewDetallepedido);
                    if (oldProducto1OfDetallepedidoSet1NewDetallepedido != null && !oldProducto1OfDetallepedidoSet1NewDetallepedido.equals(producto)) {
                        oldProducto1OfDetallepedidoSet1NewDetallepedido.getDetallepedidoSet1().remove(detallepedidoSet1NewDetallepedido);
                        oldProducto1OfDetallepedidoSet1NewDetallepedido = em.merge(oldProducto1OfDetallepedidoSet1NewDetallepedido);
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
            for (Detallereclamocliente detallereclamoclienteSet1OldDetallereclamocliente : detallereclamoclienteSet1Old) {
                if (!detallereclamoclienteSet1New.contains(detallereclamoclienteSet1OldDetallereclamocliente)) {
                    detallereclamoclienteSet1OldDetallereclamocliente.setProducto1(null);
                    detallereclamoclienteSet1OldDetallereclamocliente = em.merge(detallereclamoclienteSet1OldDetallereclamocliente);
                }
            }
            for (Detallereclamocliente detallereclamoclienteSet1NewDetallereclamocliente : detallereclamoclienteSet1New) {
                if (!detallereclamoclienteSet1Old.contains(detallereclamoclienteSet1NewDetallereclamocliente)) {
                    Producto oldProducto1OfDetallereclamoclienteSet1NewDetallereclamocliente = detallereclamoclienteSet1NewDetallereclamocliente.getProducto1();
                    detallereclamoclienteSet1NewDetallereclamocliente.setProducto1(producto);
                    detallereclamoclienteSet1NewDetallereclamocliente = em.merge(detallereclamoclienteSet1NewDetallereclamocliente);
                    if (oldProducto1OfDetallereclamoclienteSet1NewDetallereclamocliente != null && !oldProducto1OfDetallereclamoclienteSet1NewDetallereclamocliente.equals(producto)) {
                        oldProducto1OfDetallereclamoclienteSet1NewDetallereclamocliente.getDetallereclamoclienteSet1().remove(detallereclamoclienteSet1NewDetallereclamocliente);
                        oldProducto1OfDetallereclamoclienteSet1NewDetallereclamocliente = em.merge(oldProducto1OfDetallereclamoclienteSet1NewDetallereclamocliente);
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
            for (Detallepresupuesto detallepresupuestoSet1OldDetallepresupuesto : detallepresupuestoSet1Old) {
                if (!detallepresupuestoSet1New.contains(detallepresupuestoSet1OldDetallepresupuesto)) {
                    detallepresupuestoSet1OldDetallepresupuesto.setIdproducto1(null);
                    detallepresupuestoSet1OldDetallepresupuesto = em.merge(detallepresupuestoSet1OldDetallepresupuesto);
                }
            }
            for (Detallepresupuesto detallepresupuestoSet1NewDetallepresupuesto : detallepresupuestoSet1New) {
                if (!detallepresupuestoSet1Old.contains(detallepresupuestoSet1NewDetallepresupuesto)) {
                    Producto oldIdproducto1OfDetallepresupuestoSet1NewDetallepresupuesto = detallepresupuestoSet1NewDetallepresupuesto.getIdproducto1();
                    detallepresupuestoSet1NewDetallepresupuesto.setIdproducto1(producto);
                    detallepresupuestoSet1NewDetallepresupuesto = em.merge(detallepresupuestoSet1NewDetallepresupuesto);
                    if (oldIdproducto1OfDetallepresupuestoSet1NewDetallepresupuesto != null && !oldIdproducto1OfDetallepresupuestoSet1NewDetallepresupuesto.equals(producto)) {
                        oldIdproducto1OfDetallepresupuestoSet1NewDetallepresupuesto.getDetallepresupuestoSet1().remove(detallepresupuestoSet1NewDetallepresupuesto);
                        oldIdproducto1OfDetallepresupuestoSet1NewDetallepresupuesto = em.merge(oldIdproducto1OfDetallepresupuestoSet1NewDetallepresupuesto);
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
            Set<Detalleproducto> detalleproductoSet1OrphanCheck = producto.getDetalleproductoSet1();
            for (Detalleproducto detalleproductoSet1OrphanCheckDetalleproducto : detalleproductoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Detalleproducto " + detalleproductoSet1OrphanCheckDetalleproducto + " in its detalleproductoSet1 field has a non-nullable idproducto1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Detalleremito> detalleremitoSet = producto.getDetalleremitoSet();
            for (Detalleremito detalleremitoSetDetalleremito : detalleremitoSet) {
                detalleremitoSetDetalleremito.setProducto(null);
                detalleremitoSetDetalleremito = em.merge(detalleremitoSetDetalleremito);
            }
            Set<Detalleremito> detalleremitoSet1 = producto.getDetalleremitoSet1();
            for (Detalleremito detalleremitoSet1Detalleremito : detalleremitoSet1) {
                detalleremitoSet1Detalleremito.setProducto1(null);
                detalleremitoSet1Detalleremito = em.merge(detalleremitoSet1Detalleremito);
            }
            Set<Detallepedido> detallepedidoSet = producto.getDetallepedidoSet();
            for (Detallepedido detallepedidoSetDetallepedido : detallepedidoSet) {
                detallepedidoSetDetallepedido.setProducto(null);
                detallepedidoSetDetallepedido = em.merge(detallepedidoSetDetallepedido);
            }
            Set<Detallepedido> detallepedidoSet1 = producto.getDetallepedidoSet1();
            for (Detallepedido detallepedidoSet1Detallepedido : detallepedidoSet1) {
                detallepedidoSet1Detallepedido.setProducto1(null);
                detallepedidoSet1Detallepedido = em.merge(detallepedidoSet1Detallepedido);
            }
            Set<Detallereclamocliente> detallereclamoclienteSet = producto.getDetallereclamoclienteSet();
            for (Detallereclamocliente detallereclamoclienteSetDetallereclamocliente : detallereclamoclienteSet) {
                detallereclamoclienteSetDetallereclamocliente.setProducto(null);
                detallereclamoclienteSetDetallereclamocliente = em.merge(detallereclamoclienteSetDetallereclamocliente);
            }
            Set<Detallereclamocliente> detallereclamoclienteSet1 = producto.getDetallereclamoclienteSet1();
            for (Detallereclamocliente detallereclamoclienteSet1Detallereclamocliente : detallereclamoclienteSet1) {
                detallereclamoclienteSet1Detallereclamocliente.setProducto1(null);
                detallereclamoclienteSet1Detallereclamocliente = em.merge(detallereclamoclienteSet1Detallereclamocliente);
            }
            Set<Detallepresupuesto> detallepresupuestoSet = producto.getDetallepresupuestoSet();
            for (Detallepresupuesto detallepresupuestoSetDetallepresupuesto : detallepresupuestoSet) {
                detallepresupuestoSetDetallepresupuesto.setIdproducto(null);
                detallepresupuestoSetDetallepresupuesto = em.merge(detallepresupuestoSetDetallepresupuesto);
            }
            Set<Detallepresupuesto> detallepresupuestoSet1 = producto.getDetallepresupuestoSet1();
            for (Detallepresupuesto detallepresupuestoSet1Detallepresupuesto : detallepresupuestoSet1) {
                detallepresupuestoSet1Detallepresupuesto.setIdproducto1(null);
                detallepresupuestoSet1Detallepresupuesto = em.merge(detallepresupuestoSet1Detallepresupuesto);
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
