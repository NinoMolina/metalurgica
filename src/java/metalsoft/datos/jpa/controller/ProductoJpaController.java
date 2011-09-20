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
import metalsoft.datos.jpa.entity.Detalleremito;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.datos.jpa.entity.Productoreal;
import metalsoft.datos.jpa.entity.Detalleproducto;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Detallereclamocliente;
import metalsoft.datos.jpa.entity.Detallepresupuesto;

/**
 *
 * @author Nino
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws PreexistingEntityException, Exception {
        if (producto.getDetalleremitoList() == null) {
            producto.setDetalleremitoList(new ArrayList<Detalleremito>());
        }
        if (producto.getDetalleplanificacionproduccionList() == null) {
            producto.setDetalleplanificacionproduccionList(new ArrayList<Detalleplanificacionproduccion>());
        }
        if (producto.getProductorealList() == null) {
            producto.setProductorealList(new ArrayList<Productoreal>());
        }
        if (producto.getDetalleproductoList() == null) {
            producto.setDetalleproductoList(new ArrayList<Detalleproducto>());
        }
        if (producto.getDetalleplanificacioncalidadList() == null) {
            producto.setDetalleplanificacioncalidadList(new ArrayList<Detalleplanificacioncalidad>());
        }
        if (producto.getDetallepedidoList() == null) {
            producto.setDetallepedidoList(new ArrayList<Detallepedido>());
        }
        if (producto.getDetallereclamoclienteList() == null) {
            producto.setDetallereclamoclienteList(new ArrayList<Detallereclamocliente>());
        }
        if (producto.getDetallepresupuestoList() == null) {
            producto.setDetallepresupuestoList(new ArrayList<Detallepresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleremito> attachedDetalleremitoList = new ArrayList<Detalleremito>();
            for (Detalleremito detalleremitoListDetalleremitoToAttach : producto.getDetalleremitoList()) {
                detalleremitoListDetalleremitoToAttach = em.getReference(detalleremitoListDetalleremitoToAttach.getClass(), detalleremitoListDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoList.add(detalleremitoListDetalleremitoToAttach);
            }
            producto.setDetalleremitoList(attachedDetalleremitoList);
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionList = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach : producto.getDetalleplanificacionproduccionList()) {
                detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionList.add(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach);
            }
            producto.setDetalleplanificacionproduccionList(attachedDetalleplanificacionproduccionList);
            List<Productoreal> attachedProductorealList = new ArrayList<Productoreal>();
            for (Productoreal productorealListProductorealToAttach : producto.getProductorealList()) {
                productorealListProductorealToAttach = em.getReference(productorealListProductorealToAttach.getClass(), productorealListProductorealToAttach.getIdproductoreal());
                attachedProductorealList.add(productorealListProductorealToAttach);
            }
            producto.setProductorealList(attachedProductorealList);
            List<Detalleproducto> attachedDetalleproductoList = new ArrayList<Detalleproducto>();
            for (Detalleproducto detalleproductoListDetalleproductoToAttach : producto.getDetalleproductoList()) {
                detalleproductoListDetalleproductoToAttach = em.getReference(detalleproductoListDetalleproductoToAttach.getClass(), detalleproductoListDetalleproductoToAttach.getIddetalle());
                attachedDetalleproductoList.add(detalleproductoListDetalleproductoToAttach);
            }
            producto.setDetalleproductoList(attachedDetalleproductoList);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadList = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach : producto.getDetalleplanificacioncalidadList()) {
                detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadList.add(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach);
            }
            producto.setDetalleplanificacioncalidadList(attachedDetalleplanificacioncalidadList);
            List<Detallepedido> attachedDetallepedidoList = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoListDetallepedidoToAttach : producto.getDetallepedidoList()) {
                detallepedidoListDetallepedidoToAttach = em.getReference(detallepedidoListDetallepedidoToAttach.getClass(), detallepedidoListDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoList.add(detallepedidoListDetallepedidoToAttach);
            }
            producto.setDetallepedidoList(attachedDetallepedidoList);
            List<Detallereclamocliente> attachedDetallereclamoclienteList = new ArrayList<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteListDetallereclamoclienteToAttach : producto.getDetallereclamoclienteList()) {
                detallereclamoclienteListDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteListDetallereclamoclienteToAttach.getClass(), detallereclamoclienteListDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteList.add(detallereclamoclienteListDetallereclamoclienteToAttach);
            }
            producto.setDetallereclamoclienteList(attachedDetallereclamoclienteList);
            List<Detallepresupuesto> attachedDetallepresupuestoList = new ArrayList<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoListDetallepresupuestoToAttach : producto.getDetallepresupuestoList()) {
                detallepresupuestoListDetallepresupuestoToAttach = em.getReference(detallepresupuestoListDetallepresupuestoToAttach.getClass(), detallepresupuestoListDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoList.add(detallepresupuestoListDetallepresupuestoToAttach);
            }
            producto.setDetallepresupuestoList(attachedDetallepresupuestoList);
            em.persist(producto);
            for (Detalleremito detalleremitoListDetalleremito : producto.getDetalleremitoList()) {
                Producto oldProductoOfDetalleremitoListDetalleremito = detalleremitoListDetalleremito.getProducto();
                detalleremitoListDetalleremito.setProducto(producto);
                detalleremitoListDetalleremito = em.merge(detalleremitoListDetalleremito);
                if (oldProductoOfDetalleremitoListDetalleremito != null) {
                    oldProductoOfDetalleremitoListDetalleremito.getDetalleremitoList().remove(detalleremitoListDetalleremito);
                    oldProductoOfDetalleremitoListDetalleremito = em.merge(oldProductoOfDetalleremitoListDetalleremito);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : producto.getDetalleplanificacionproduccionList()) {
                Producto oldIdproductoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = detalleplanificacionproduccionListDetalleplanificacionproduccion.getIdproducto();
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdproducto(producto);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                if (oldIdproductoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion != null) {
                    oldIdproductoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                    oldIdproductoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(oldIdproductoOfDetalleplanificacionproduccionListDetalleplanificacionproduccion);
                }
            }
            for (Productoreal productorealListProductoreal : producto.getProductorealList()) {
                Producto oldProductoOfProductorealListProductoreal = productorealListProductoreal.getProducto();
                productorealListProductoreal.setProducto(producto);
                productorealListProductoreal = em.merge(productorealListProductoreal);
                if (oldProductoOfProductorealListProductoreal != null) {
                    oldProductoOfProductorealListProductoreal.getProductorealList().remove(productorealListProductoreal);
                    oldProductoOfProductorealListProductoreal = em.merge(oldProductoOfProductorealListProductoreal);
                }
            }
            for (Detalleproducto detalleproductoListDetalleproducto : producto.getDetalleproductoList()) {
                Producto oldIdproductoOfDetalleproductoListDetalleproducto = detalleproductoListDetalleproducto.getIdproducto();
                detalleproductoListDetalleproducto.setIdproducto(producto);
                detalleproductoListDetalleproducto = em.merge(detalleproductoListDetalleproducto);
                if (oldIdproductoOfDetalleproductoListDetalleproducto != null) {
                    oldIdproductoOfDetalleproductoListDetalleproducto.getDetalleproductoList().remove(detalleproductoListDetalleproducto);
                    oldIdproductoOfDetalleproductoListDetalleproducto = em.merge(oldIdproductoOfDetalleproductoListDetalleproducto);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : producto.getDetalleplanificacioncalidadList()) {
                Producto oldProductoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = detalleplanificacioncalidadListDetalleplanificacioncalidad.getProducto();
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setProducto(producto);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                if (oldProductoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad != null) {
                    oldProductoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                    oldProductoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(oldProductoOfDetalleplanificacioncalidadListDetalleplanificacioncalidad);
                }
            }
            for (Detallepedido detallepedidoListDetallepedido : producto.getDetallepedidoList()) {
                Producto oldProductoOfDetallepedidoListDetallepedido = detallepedidoListDetallepedido.getProducto();
                detallepedidoListDetallepedido.setProducto(producto);
                detallepedidoListDetallepedido = em.merge(detallepedidoListDetallepedido);
                if (oldProductoOfDetallepedidoListDetallepedido != null) {
                    oldProductoOfDetallepedidoListDetallepedido.getDetallepedidoList().remove(detallepedidoListDetallepedido);
                    oldProductoOfDetallepedidoListDetallepedido = em.merge(oldProductoOfDetallepedidoListDetallepedido);
                }
            }
            for (Detallereclamocliente detallereclamoclienteListDetallereclamocliente : producto.getDetallereclamoclienteList()) {
                Producto oldProductoOfDetallereclamoclienteListDetallereclamocliente = detallereclamoclienteListDetallereclamocliente.getProducto();
                detallereclamoclienteListDetallereclamocliente.setProducto(producto);
                detallereclamoclienteListDetallereclamocliente = em.merge(detallereclamoclienteListDetallereclamocliente);
                if (oldProductoOfDetallereclamoclienteListDetallereclamocliente != null) {
                    oldProductoOfDetallereclamoclienteListDetallereclamocliente.getDetallereclamoclienteList().remove(detallereclamoclienteListDetallereclamocliente);
                    oldProductoOfDetallereclamoclienteListDetallereclamocliente = em.merge(oldProductoOfDetallereclamoclienteListDetallereclamocliente);
                }
            }
            for (Detallepresupuesto detallepresupuestoListDetallepresupuesto : producto.getDetallepresupuestoList()) {
                Producto oldIdproductoOfDetallepresupuestoListDetallepresupuesto = detallepresupuestoListDetallepresupuesto.getIdproducto();
                detallepresupuestoListDetallepresupuesto.setIdproducto(producto);
                detallepresupuestoListDetallepresupuesto = em.merge(detallepresupuestoListDetallepresupuesto);
                if (oldIdproductoOfDetallepresupuestoListDetallepresupuesto != null) {
                    oldIdproductoOfDetallepresupuestoListDetallepresupuesto.getDetallepresupuestoList().remove(detallepresupuestoListDetallepresupuesto);
                    oldIdproductoOfDetallepresupuestoListDetallepresupuesto = em.merge(oldIdproductoOfDetallepresupuestoListDetallepresupuesto);
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
            List<Detalleremito> detalleremitoListOld = persistentProducto.getDetalleremitoList();
            List<Detalleremito> detalleremitoListNew = producto.getDetalleremitoList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOld = persistentProducto.getDetalleplanificacionproduccionList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListNew = producto.getDetalleplanificacionproduccionList();
            List<Productoreal> productorealListOld = persistentProducto.getProductorealList();
            List<Productoreal> productorealListNew = producto.getProductorealList();
            List<Detalleproducto> detalleproductoListOld = persistentProducto.getDetalleproductoList();
            List<Detalleproducto> detalleproductoListNew = producto.getDetalleproductoList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOld = persistentProducto.getDetalleplanificacioncalidadList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListNew = producto.getDetalleplanificacioncalidadList();
            List<Detallepedido> detallepedidoListOld = persistentProducto.getDetallepedidoList();
            List<Detallepedido> detallepedidoListNew = producto.getDetallepedidoList();
            List<Detallereclamocliente> detallereclamoclienteListOld = persistentProducto.getDetallereclamoclienteList();
            List<Detallereclamocliente> detallereclamoclienteListNew = producto.getDetallereclamoclienteList();
            List<Detallepresupuesto> detallepresupuestoListOld = persistentProducto.getDetallepresupuestoList();
            List<Detallepresupuesto> detallepresupuestoListNew = producto.getDetallepresupuestoList();
            List<String> illegalOrphanMessages = null;
            for (Detalleproducto detalleproductoListOldDetalleproducto : detalleproductoListOld) {
                if (!detalleproductoListNew.contains(detalleproductoListOldDetalleproducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleproducto " + detalleproductoListOldDetalleproducto + " since its idproducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleremito> attachedDetalleremitoListNew = new ArrayList<Detalleremito>();
            for (Detalleremito detalleremitoListNewDetalleremitoToAttach : detalleremitoListNew) {
                detalleremitoListNewDetalleremitoToAttach = em.getReference(detalleremitoListNewDetalleremitoToAttach.getClass(), detalleremitoListNewDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoListNew.add(detalleremitoListNewDetalleremitoToAttach);
            }
            detalleremitoListNew = attachedDetalleremitoListNew;
            producto.setDetalleremitoList(detalleremitoListNew);
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionListNew = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionListNew) {
                detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionListNew.add(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionListNew = attachedDetalleplanificacionproduccionListNew;
            producto.setDetalleplanificacionproduccionList(detalleplanificacionproduccionListNew);
            List<Productoreal> attachedProductorealListNew = new ArrayList<Productoreal>();
            for (Productoreal productorealListNewProductorealToAttach : productorealListNew) {
                productorealListNewProductorealToAttach = em.getReference(productorealListNewProductorealToAttach.getClass(), productorealListNewProductorealToAttach.getIdproductoreal());
                attachedProductorealListNew.add(productorealListNewProductorealToAttach);
            }
            productorealListNew = attachedProductorealListNew;
            producto.setProductorealList(productorealListNew);
            List<Detalleproducto> attachedDetalleproductoListNew = new ArrayList<Detalleproducto>();
            for (Detalleproducto detalleproductoListNewDetalleproductoToAttach : detalleproductoListNew) {
                detalleproductoListNewDetalleproductoToAttach = em.getReference(detalleproductoListNewDetalleproductoToAttach.getClass(), detalleproductoListNewDetalleproductoToAttach.getIddetalle());
                attachedDetalleproductoListNew.add(detalleproductoListNewDetalleproductoToAttach);
            }
            detalleproductoListNew = attachedDetalleproductoListNew;
            producto.setDetalleproductoList(detalleproductoListNew);
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadListNew = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadListNew) {
                detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getIddetalle());
                attachedDetalleplanificacioncalidadListNew.add(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadListNew = attachedDetalleplanificacioncalidadListNew;
            producto.setDetalleplanificacioncalidadList(detalleplanificacioncalidadListNew);
            List<Detallepedido> attachedDetallepedidoListNew = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoListNewDetallepedidoToAttach : detallepedidoListNew) {
                detallepedidoListNewDetallepedidoToAttach = em.getReference(detallepedidoListNewDetallepedidoToAttach.getClass(), detallepedidoListNewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoListNew.add(detallepedidoListNewDetallepedidoToAttach);
            }
            detallepedidoListNew = attachedDetallepedidoListNew;
            producto.setDetallepedidoList(detallepedidoListNew);
            List<Detallereclamocliente> attachedDetallereclamoclienteListNew = new ArrayList<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteListNewDetallereclamoclienteToAttach : detallereclamoclienteListNew) {
                detallereclamoclienteListNewDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteListNewDetallereclamoclienteToAttach.getClass(), detallereclamoclienteListNewDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteListNew.add(detallereclamoclienteListNewDetallereclamoclienteToAttach);
            }
            detallereclamoclienteListNew = attachedDetallereclamoclienteListNew;
            producto.setDetallereclamoclienteList(detallereclamoclienteListNew);
            List<Detallepresupuesto> attachedDetallepresupuestoListNew = new ArrayList<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoListNewDetallepresupuestoToAttach : detallepresupuestoListNew) {
                detallepresupuestoListNewDetallepresupuestoToAttach = em.getReference(detallepresupuestoListNewDetallepresupuestoToAttach.getClass(), detallepresupuestoListNewDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoListNew.add(detallepresupuestoListNewDetallepresupuestoToAttach);
            }
            detallepresupuestoListNew = attachedDetallepresupuestoListNew;
            producto.setDetallepresupuestoList(detallepresupuestoListNew);
            producto = em.merge(producto);
            for (Detalleremito detalleremitoListOldDetalleremito : detalleremitoListOld) {
                if (!detalleremitoListNew.contains(detalleremitoListOldDetalleremito)) {
                    detalleremitoListOldDetalleremito.setProducto(null);
                    detalleremitoListOldDetalleremito = em.merge(detalleremitoListOldDetalleremito);
                }
            }
            for (Detalleremito detalleremitoListNewDetalleremito : detalleremitoListNew) {
                if (!detalleremitoListOld.contains(detalleremitoListNewDetalleremito)) {
                    Producto oldProductoOfDetalleremitoListNewDetalleremito = detalleremitoListNewDetalleremito.getProducto();
                    detalleremitoListNewDetalleremito.setProducto(producto);
                    detalleremitoListNewDetalleremito = em.merge(detalleremitoListNewDetalleremito);
                    if (oldProductoOfDetalleremitoListNewDetalleremito != null && !oldProductoOfDetalleremitoListNewDetalleremito.equals(producto)) {
                        oldProductoOfDetalleremitoListNewDetalleremito.getDetalleremitoList().remove(detalleremitoListNewDetalleremito);
                        oldProductoOfDetalleremitoListNewDetalleremito = em.merge(oldProductoOfDetalleremitoListNewDetalleremito);
                    }
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListOldDetalleplanificacionproduccion : detalleplanificacionproduccionListOld) {
                if (!detalleplanificacionproduccionListNew.contains(detalleplanificacionproduccionListOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion.setIdproducto(null);
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccion : detalleplanificacionproduccionListNew) {
                if (!detalleplanificacionproduccionListOld.contains(detalleplanificacionproduccionListNewDetalleplanificacionproduccion)) {
                    Producto oldIdproductoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = detalleplanificacionproduccionListNewDetalleplanificacionproduccion.getIdproducto();
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion.setIdproducto(producto);
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    if (oldIdproductoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion != null && !oldIdproductoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.equals(producto)) {
                        oldIdproductoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                        oldIdproductoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(oldIdproductoOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Productoreal productorealListOldProductoreal : productorealListOld) {
                if (!productorealListNew.contains(productorealListOldProductoreal)) {
                    productorealListOldProductoreal.setProducto(null);
                    productorealListOldProductoreal = em.merge(productorealListOldProductoreal);
                }
            }
            for (Productoreal productorealListNewProductoreal : productorealListNew) {
                if (!productorealListOld.contains(productorealListNewProductoreal)) {
                    Producto oldProductoOfProductorealListNewProductoreal = productorealListNewProductoreal.getProducto();
                    productorealListNewProductoreal.setProducto(producto);
                    productorealListNewProductoreal = em.merge(productorealListNewProductoreal);
                    if (oldProductoOfProductorealListNewProductoreal != null && !oldProductoOfProductorealListNewProductoreal.equals(producto)) {
                        oldProductoOfProductorealListNewProductoreal.getProductorealList().remove(productorealListNewProductoreal);
                        oldProductoOfProductorealListNewProductoreal = em.merge(oldProductoOfProductorealListNewProductoreal);
                    }
                }
            }
            for (Detalleproducto detalleproductoListNewDetalleproducto : detalleproductoListNew) {
                if (!detalleproductoListOld.contains(detalleproductoListNewDetalleproducto)) {
                    Producto oldIdproductoOfDetalleproductoListNewDetalleproducto = detalleproductoListNewDetalleproducto.getIdproducto();
                    detalleproductoListNewDetalleproducto.setIdproducto(producto);
                    detalleproductoListNewDetalleproducto = em.merge(detalleproductoListNewDetalleproducto);
                    if (oldIdproductoOfDetalleproductoListNewDetalleproducto != null && !oldIdproductoOfDetalleproductoListNewDetalleproducto.equals(producto)) {
                        oldIdproductoOfDetalleproductoListNewDetalleproducto.getDetalleproductoList().remove(detalleproductoListNewDetalleproducto);
                        oldIdproductoOfDetalleproductoListNewDetalleproducto = em.merge(oldIdproductoOfDetalleproductoListNewDetalleproducto);
                    }
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOldDetalleplanificacioncalidad : detalleplanificacioncalidadListOld) {
                if (!detalleplanificacioncalidadListNew.contains(detalleplanificacioncalidadListOldDetalleplanificacioncalidad)) {
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad.setProducto(null);
                    detalleplanificacioncalidadListOldDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListOldDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidad : detalleplanificacioncalidadListNew) {
                if (!detalleplanificacioncalidadListOld.contains(detalleplanificacioncalidadListNewDetalleplanificacioncalidad)) {
                    Producto oldProductoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = detalleplanificacioncalidadListNewDetalleplanificacioncalidad.getProducto();
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad.setProducto(producto);
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    if (oldProductoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad != null && !oldProductoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.equals(producto)) {
                        oldProductoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                        oldProductoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(oldProductoOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    }
                }
            }
            for (Detallepedido detallepedidoListOldDetallepedido : detallepedidoListOld) {
                if (!detallepedidoListNew.contains(detallepedidoListOldDetallepedido)) {
                    detallepedidoListOldDetallepedido.setProducto(null);
                    detallepedidoListOldDetallepedido = em.merge(detallepedidoListOldDetallepedido);
                }
            }
            for (Detallepedido detallepedidoListNewDetallepedido : detallepedidoListNew) {
                if (!detallepedidoListOld.contains(detallepedidoListNewDetallepedido)) {
                    Producto oldProductoOfDetallepedidoListNewDetallepedido = detallepedidoListNewDetallepedido.getProducto();
                    detallepedidoListNewDetallepedido.setProducto(producto);
                    detallepedidoListNewDetallepedido = em.merge(detallepedidoListNewDetallepedido);
                    if (oldProductoOfDetallepedidoListNewDetallepedido != null && !oldProductoOfDetallepedidoListNewDetallepedido.equals(producto)) {
                        oldProductoOfDetallepedidoListNewDetallepedido.getDetallepedidoList().remove(detallepedidoListNewDetallepedido);
                        oldProductoOfDetallepedidoListNewDetallepedido = em.merge(oldProductoOfDetallepedidoListNewDetallepedido);
                    }
                }
            }
            for (Detallereclamocliente detallereclamoclienteListOldDetallereclamocliente : detallereclamoclienteListOld) {
                if (!detallereclamoclienteListNew.contains(detallereclamoclienteListOldDetallereclamocliente)) {
                    detallereclamoclienteListOldDetallereclamocliente.setProducto(null);
                    detallereclamoclienteListOldDetallereclamocliente = em.merge(detallereclamoclienteListOldDetallereclamocliente);
                }
            }
            for (Detallereclamocliente detallereclamoclienteListNewDetallereclamocliente : detallereclamoclienteListNew) {
                if (!detallereclamoclienteListOld.contains(detallereclamoclienteListNewDetallereclamocliente)) {
                    Producto oldProductoOfDetallereclamoclienteListNewDetallereclamocliente = detallereclamoclienteListNewDetallereclamocliente.getProducto();
                    detallereclamoclienteListNewDetallereclamocliente.setProducto(producto);
                    detallereclamoclienteListNewDetallereclamocliente = em.merge(detallereclamoclienteListNewDetallereclamocliente);
                    if (oldProductoOfDetallereclamoclienteListNewDetallereclamocliente != null && !oldProductoOfDetallereclamoclienteListNewDetallereclamocliente.equals(producto)) {
                        oldProductoOfDetallereclamoclienteListNewDetallereclamocliente.getDetallereclamoclienteList().remove(detallereclamoclienteListNewDetallereclamocliente);
                        oldProductoOfDetallereclamoclienteListNewDetallereclamocliente = em.merge(oldProductoOfDetallereclamoclienteListNewDetallereclamocliente);
                    }
                }
            }
            for (Detallepresupuesto detallepresupuestoListOldDetallepresupuesto : detallepresupuestoListOld) {
                if (!detallepresupuestoListNew.contains(detallepresupuestoListOldDetallepresupuesto)) {
                    detallepresupuestoListOldDetallepresupuesto.setIdproducto(null);
                    detallepresupuestoListOldDetallepresupuesto = em.merge(detallepresupuestoListOldDetallepresupuesto);
                }
            }
            for (Detallepresupuesto detallepresupuestoListNewDetallepresupuesto : detallepresupuestoListNew) {
                if (!detallepresupuestoListOld.contains(detallepresupuestoListNewDetallepresupuesto)) {
                    Producto oldIdproductoOfDetallepresupuestoListNewDetallepresupuesto = detallepresupuestoListNewDetallepresupuesto.getIdproducto();
                    detallepresupuestoListNewDetallepresupuesto.setIdproducto(producto);
                    detallepresupuestoListNewDetallepresupuesto = em.merge(detallepresupuestoListNewDetallepresupuesto);
                    if (oldIdproductoOfDetallepresupuestoListNewDetallepresupuesto != null && !oldIdproductoOfDetallepresupuestoListNewDetallepresupuesto.equals(producto)) {
                        oldIdproductoOfDetallepresupuestoListNewDetallepresupuesto.getDetallepresupuestoList().remove(detallepresupuestoListNewDetallepresupuesto);
                        oldIdproductoOfDetallepresupuestoListNewDetallepresupuesto = em.merge(oldIdproductoOfDetallepresupuestoListNewDetallepresupuesto);
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
            List<Detalleproducto> detalleproductoListOrphanCheck = producto.getDetalleproductoList();
            for (Detalleproducto detalleproductoListOrphanCheckDetalleproducto : detalleproductoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Detalleproducto " + detalleproductoListOrphanCheckDetalleproducto + " in its detalleproductoList field has a non-nullable idproducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleremito> detalleremitoList = producto.getDetalleremitoList();
            for (Detalleremito detalleremitoListDetalleremito : detalleremitoList) {
                detalleremitoListDetalleremito.setProducto(null);
                detalleremitoListDetalleremito = em.merge(detalleremitoListDetalleremito);
            }
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionList = producto.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : detalleplanificacionproduccionList) {
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdproducto(null);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
            }
            List<Productoreal> productorealList = producto.getProductorealList();
            for (Productoreal productorealListProductoreal : productorealList) {
                productorealListProductoreal.setProducto(null);
                productorealListProductoreal = em.merge(productorealListProductoreal);
            }
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadList = producto.getDetalleplanificacioncalidadList();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : detalleplanificacioncalidadList) {
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setProducto(null);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
            }
            List<Detallepedido> detallepedidoList = producto.getDetallepedidoList();
            for (Detallepedido detallepedidoListDetallepedido : detallepedidoList) {
                detallepedidoListDetallepedido.setProducto(null);
                detallepedidoListDetallepedido = em.merge(detallepedidoListDetallepedido);
            }
            List<Detallereclamocliente> detallereclamoclienteList = producto.getDetallereclamoclienteList();
            for (Detallereclamocliente detallereclamoclienteListDetallereclamocliente : detallereclamoclienteList) {
                detallereclamoclienteListDetallereclamocliente.setProducto(null);
                detallereclamoclienteListDetallereclamocliente = em.merge(detallereclamoclienteListDetallereclamocliente);
            }
            List<Detallepresupuesto> detallepresupuestoList = producto.getDetallepresupuestoList();
            for (Detallepresupuesto detallepresupuestoListDetallepresupuesto : detallepresupuestoList) {
                detallepresupuestoListDetallepresupuesto.setIdproducto(null);
                detallepresupuestoListDetallepresupuesto = em.merge(detallepresupuestoListDetallepresupuesto);
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
