/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Compra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadocompra;
import entity.Proveedor;
import entity.Detallecompra;
import java.util.HashSet;
import java.util.Set;
import entity.Reclamoproveedor;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class CompraJpaController {

    public CompraJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) throws PreexistingEntityException, Exception {
        if (compra.getDetallecompraSet() == null) {
            compra.setDetallecompraSet(new HashSet<Detallecompra>());
        }
        if (compra.getDetallecompraSet1() == null) {
            compra.setDetallecompraSet1(new HashSet<Detallecompra>());
        }
        if (compra.getReclamoproveedorSet() == null) {
            compra.setReclamoproveedorSet(new HashSet<Reclamoproveedor>());
        }
        if (compra.getReclamoproveedorSet1() == null) {
            compra.setReclamoproveedorSet1(new HashSet<Reclamoproveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadocompra estado = compra.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                compra.setEstado(estado);
            }
            Estadocompra estado1 = compra.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                compra.setEstado1(estado1);
            }
            Proveedor proveedor = compra.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdproveedor());
                compra.setProveedor(proveedor);
            }
            Proveedor proveedor1 = compra.getProveedor1();
            if (proveedor1 != null) {
                proveedor1 = em.getReference(proveedor1.getClass(), proveedor1.getIdproveedor());
                compra.setProveedor1(proveedor1);
            }
            Set<Detallecompra> attachedDetallecompraSet = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetDetallecompraToAttach : compra.getDetallecompraSet()) {
                detallecompraSetDetallecompraToAttach = em.getReference(detallecompraSetDetallecompraToAttach.getClass(), detallecompraSetDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet.add(detallecompraSetDetallecompraToAttach);
            }
            compra.setDetallecompraSet(attachedDetallecompraSet);
            Set<Detallecompra> attachedDetallecompraSet1 = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSet1DetallecompraToAttach : compra.getDetallecompraSet1()) {
                detallecompraSet1DetallecompraToAttach = em.getReference(detallecompraSet1DetallecompraToAttach.getClass(), detallecompraSet1DetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet1.add(detallecompraSet1DetallecompraToAttach);
            }
            compra.setDetallecompraSet1(attachedDetallecompraSet1);
            Set<Reclamoproveedor> attachedReclamoproveedorSet = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedorToAttach : compra.getReclamoproveedorSet()) {
                reclamoproveedorSetReclamoproveedorToAttach = em.getReference(reclamoproveedorSetReclamoproveedorToAttach.getClass(), reclamoproveedorSetReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSet.add(reclamoproveedorSetReclamoproveedorToAttach);
            }
            compra.setReclamoproveedorSet(attachedReclamoproveedorSet);
            Set<Reclamoproveedor> attachedReclamoproveedorSet1 = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSet1ReclamoproveedorToAttach : compra.getReclamoproveedorSet1()) {
                reclamoproveedorSet1ReclamoproveedorToAttach = em.getReference(reclamoproveedorSet1ReclamoproveedorToAttach.getClass(), reclamoproveedorSet1ReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSet1.add(reclamoproveedorSet1ReclamoproveedorToAttach);
            }
            compra.setReclamoproveedorSet1(attachedReclamoproveedorSet1);
            em.persist(compra);
            if (estado != null) {
                estado.getCompraSet().add(compra);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getCompraSet().add(compra);
                estado1 = em.merge(estado1);
            }
            if (proveedor != null) {
                proveedor.getCompraSet().add(compra);
                proveedor = em.merge(proveedor);
            }
            if (proveedor1 != null) {
                proveedor1.getCompraSet().add(compra);
                proveedor1 = em.merge(proveedor1);
            }
            for (Detallecompra detallecompraSetDetallecompra : compra.getDetallecompraSet()) {
                Compra oldCompraOfDetallecompraSetDetallecompra = detallecompraSetDetallecompra.getCompra();
                detallecompraSetDetallecompra.setCompra(compra);
                detallecompraSetDetallecompra = em.merge(detallecompraSetDetallecompra);
                if (oldCompraOfDetallecompraSetDetallecompra != null) {
                    oldCompraOfDetallecompraSetDetallecompra.getDetallecompraSet().remove(detallecompraSetDetallecompra);
                    oldCompraOfDetallecompraSetDetallecompra = em.merge(oldCompraOfDetallecompraSetDetallecompra);
                }
            }
            for (Detallecompra detallecompraSet1Detallecompra : compra.getDetallecompraSet1()) {
                Compra oldCompra1OfDetallecompraSet1Detallecompra = detallecompraSet1Detallecompra.getCompra1();
                detallecompraSet1Detallecompra.setCompra1(compra);
                detallecompraSet1Detallecompra = em.merge(detallecompraSet1Detallecompra);
                if (oldCompra1OfDetallecompraSet1Detallecompra != null) {
                    oldCompra1OfDetallecompraSet1Detallecompra.getDetallecompraSet1().remove(detallecompraSet1Detallecompra);
                    oldCompra1OfDetallecompraSet1Detallecompra = em.merge(oldCompra1OfDetallecompraSet1Detallecompra);
                }
            }
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedor : compra.getReclamoproveedorSet()) {
                Compra oldCompraOfReclamoproveedorSetReclamoproveedor = reclamoproveedorSetReclamoproveedor.getCompra();
                reclamoproveedorSetReclamoproveedor.setCompra(compra);
                reclamoproveedorSetReclamoproveedor = em.merge(reclamoproveedorSetReclamoproveedor);
                if (oldCompraOfReclamoproveedorSetReclamoproveedor != null) {
                    oldCompraOfReclamoproveedorSetReclamoproveedor.getReclamoproveedorSet().remove(reclamoproveedorSetReclamoproveedor);
                    oldCompraOfReclamoproveedorSetReclamoproveedor = em.merge(oldCompraOfReclamoproveedorSetReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorSet1Reclamoproveedor : compra.getReclamoproveedorSet1()) {
                Compra oldCompra1OfReclamoproveedorSet1Reclamoproveedor = reclamoproveedorSet1Reclamoproveedor.getCompra1();
                reclamoproveedorSet1Reclamoproveedor.setCompra1(compra);
                reclamoproveedorSet1Reclamoproveedor = em.merge(reclamoproveedorSet1Reclamoproveedor);
                if (oldCompra1OfReclamoproveedorSet1Reclamoproveedor != null) {
                    oldCompra1OfReclamoproveedorSet1Reclamoproveedor.getReclamoproveedorSet1().remove(reclamoproveedorSet1Reclamoproveedor);
                    oldCompra1OfReclamoproveedorSet1Reclamoproveedor = em.merge(oldCompra1OfReclamoproveedorSet1Reclamoproveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompra(compra.getIdcompra()) != null) {
                throw new PreexistingEntityException("Compra " + compra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getIdcompra());
            Estadocompra estadoOld = persistentCompra.getEstado();
            Estadocompra estadoNew = compra.getEstado();
            Estadocompra estado1Old = persistentCompra.getEstado1();
            Estadocompra estado1New = compra.getEstado1();
            Proveedor proveedorOld = persistentCompra.getProveedor();
            Proveedor proveedorNew = compra.getProveedor();
            Proveedor proveedor1Old = persistentCompra.getProveedor1();
            Proveedor proveedor1New = compra.getProveedor1();
            Set<Detallecompra> detallecompraSetOld = persistentCompra.getDetallecompraSet();
            Set<Detallecompra> detallecompraSetNew = compra.getDetallecompraSet();
            Set<Detallecompra> detallecompraSet1Old = persistentCompra.getDetallecompraSet1();
            Set<Detallecompra> detallecompraSet1New = compra.getDetallecompraSet1();
            Set<Reclamoproveedor> reclamoproveedorSetOld = persistentCompra.getReclamoproveedorSet();
            Set<Reclamoproveedor> reclamoproveedorSetNew = compra.getReclamoproveedorSet();
            Set<Reclamoproveedor> reclamoproveedorSet1Old = persistentCompra.getReclamoproveedorSet1();
            Set<Reclamoproveedor> reclamoproveedorSet1New = compra.getReclamoproveedorSet1();
            List<String> illegalOrphanMessages = null;
            for (Detallecompra detallecompraSetOldDetallecompra : detallecompraSetOld) {
                if (!detallecompraSetNew.contains(detallecompraSetOldDetallecompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompra " + detallecompraSetOldDetallecompra + " since its compra field is not nullable.");
                }
            }
            for (Detallecompra detallecompraSet1OldDetallecompra : detallecompraSet1Old) {
                if (!detallecompraSet1New.contains(detallecompraSet1OldDetallecompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompra " + detallecompraSet1OldDetallecompra + " since its compra1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                compra.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                compra.setEstado1(estado1New);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdproveedor());
                compra.setProveedor(proveedorNew);
            }
            if (proveedor1New != null) {
                proveedor1New = em.getReference(proveedor1New.getClass(), proveedor1New.getIdproveedor());
                compra.setProveedor1(proveedor1New);
            }
            Set<Detallecompra> attachedDetallecompraSetNew = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetNewDetallecompraToAttach : detallecompraSetNew) {
                detallecompraSetNewDetallecompraToAttach = em.getReference(detallecompraSetNewDetallecompraToAttach.getClass(), detallecompraSetNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSetNew.add(detallecompraSetNewDetallecompraToAttach);
            }
            detallecompraSetNew = attachedDetallecompraSetNew;
            compra.setDetallecompraSet(detallecompraSetNew);
            Set<Detallecompra> attachedDetallecompraSet1New = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSet1NewDetallecompraToAttach : detallecompraSet1New) {
                detallecompraSet1NewDetallecompraToAttach = em.getReference(detallecompraSet1NewDetallecompraToAttach.getClass(), detallecompraSet1NewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet1New.add(detallecompraSet1NewDetallecompraToAttach);
            }
            detallecompraSet1New = attachedDetallecompraSet1New;
            compra.setDetallecompraSet1(detallecompraSet1New);
            Set<Reclamoproveedor> attachedReclamoproveedorSetNew = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSetNewReclamoproveedorToAttach : reclamoproveedorSetNew) {
                reclamoproveedorSetNewReclamoproveedorToAttach = em.getReference(reclamoproveedorSetNewReclamoproveedorToAttach.getClass(), reclamoproveedorSetNewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSetNew.add(reclamoproveedorSetNewReclamoproveedorToAttach);
            }
            reclamoproveedorSetNew = attachedReclamoproveedorSetNew;
            compra.setReclamoproveedorSet(reclamoproveedorSetNew);
            Set<Reclamoproveedor> attachedReclamoproveedorSet1New = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSet1NewReclamoproveedorToAttach : reclamoproveedorSet1New) {
                reclamoproveedorSet1NewReclamoproveedorToAttach = em.getReference(reclamoproveedorSet1NewReclamoproveedorToAttach.getClass(), reclamoproveedorSet1NewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSet1New.add(reclamoproveedorSet1NewReclamoproveedorToAttach);
            }
            reclamoproveedorSet1New = attachedReclamoproveedorSet1New;
            compra.setReclamoproveedorSet1(reclamoproveedorSet1New);
            compra = em.merge(compra);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getCompraSet().remove(compra);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getCompraSet().add(compra);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getCompraSet().remove(compra);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getCompraSet().add(compra);
                estado1New = em.merge(estado1New);
            }
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getCompraSet().remove(compra);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getCompraSet().add(compra);
                proveedorNew = em.merge(proveedorNew);
            }
            if (proveedor1Old != null && !proveedor1Old.equals(proveedor1New)) {
                proveedor1Old.getCompraSet().remove(compra);
                proveedor1Old = em.merge(proveedor1Old);
            }
            if (proveedor1New != null && !proveedor1New.equals(proveedor1Old)) {
                proveedor1New.getCompraSet().add(compra);
                proveedor1New = em.merge(proveedor1New);
            }
            for (Detallecompra detallecompraSetNewDetallecompra : detallecompraSetNew) {
                if (!detallecompraSetOld.contains(detallecompraSetNewDetallecompra)) {
                    Compra oldCompraOfDetallecompraSetNewDetallecompra = detallecompraSetNewDetallecompra.getCompra();
                    detallecompraSetNewDetallecompra.setCompra(compra);
                    detallecompraSetNewDetallecompra = em.merge(detallecompraSetNewDetallecompra);
                    if (oldCompraOfDetallecompraSetNewDetallecompra != null && !oldCompraOfDetallecompraSetNewDetallecompra.equals(compra)) {
                        oldCompraOfDetallecompraSetNewDetallecompra.getDetallecompraSet().remove(detallecompraSetNewDetallecompra);
                        oldCompraOfDetallecompraSetNewDetallecompra = em.merge(oldCompraOfDetallecompraSetNewDetallecompra);
                    }
                }
            }
            for (Detallecompra detallecompraSet1NewDetallecompra : detallecompraSet1New) {
                if (!detallecompraSet1Old.contains(detallecompraSet1NewDetallecompra)) {
                    Compra oldCompra1OfDetallecompraSet1NewDetallecompra = detallecompraSet1NewDetallecompra.getCompra1();
                    detallecompraSet1NewDetallecompra.setCompra1(compra);
                    detallecompraSet1NewDetallecompra = em.merge(detallecompraSet1NewDetallecompra);
                    if (oldCompra1OfDetallecompraSet1NewDetallecompra != null && !oldCompra1OfDetallecompraSet1NewDetallecompra.equals(compra)) {
                        oldCompra1OfDetallecompraSet1NewDetallecompra.getDetallecompraSet1().remove(detallecompraSet1NewDetallecompra);
                        oldCompra1OfDetallecompraSet1NewDetallecompra = em.merge(oldCompra1OfDetallecompraSet1NewDetallecompra);
                    }
                }
            }
            for (Reclamoproveedor reclamoproveedorSetOldReclamoproveedor : reclamoproveedorSetOld) {
                if (!reclamoproveedorSetNew.contains(reclamoproveedorSetOldReclamoproveedor)) {
                    reclamoproveedorSetOldReclamoproveedor.setCompra(null);
                    reclamoproveedorSetOldReclamoproveedor = em.merge(reclamoproveedorSetOldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorSetNewReclamoproveedor : reclamoproveedorSetNew) {
                if (!reclamoproveedorSetOld.contains(reclamoproveedorSetNewReclamoproveedor)) {
                    Compra oldCompraOfReclamoproveedorSetNewReclamoproveedor = reclamoproveedorSetNewReclamoproveedor.getCompra();
                    reclamoproveedorSetNewReclamoproveedor.setCompra(compra);
                    reclamoproveedorSetNewReclamoproveedor = em.merge(reclamoproveedorSetNewReclamoproveedor);
                    if (oldCompraOfReclamoproveedorSetNewReclamoproveedor != null && !oldCompraOfReclamoproveedorSetNewReclamoproveedor.equals(compra)) {
                        oldCompraOfReclamoproveedorSetNewReclamoproveedor.getReclamoproveedorSet().remove(reclamoproveedorSetNewReclamoproveedor);
                        oldCompraOfReclamoproveedorSetNewReclamoproveedor = em.merge(oldCompraOfReclamoproveedorSetNewReclamoproveedor);
                    }
                }
            }
            for (Reclamoproveedor reclamoproveedorSet1OldReclamoproveedor : reclamoproveedorSet1Old) {
                if (!reclamoproveedorSet1New.contains(reclamoproveedorSet1OldReclamoproveedor)) {
                    reclamoproveedorSet1OldReclamoproveedor.setCompra1(null);
                    reclamoproveedorSet1OldReclamoproveedor = em.merge(reclamoproveedorSet1OldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorSet1NewReclamoproveedor : reclamoproveedorSet1New) {
                if (!reclamoproveedorSet1Old.contains(reclamoproveedorSet1NewReclamoproveedor)) {
                    Compra oldCompra1OfReclamoproveedorSet1NewReclamoproveedor = reclamoproveedorSet1NewReclamoproveedor.getCompra1();
                    reclamoproveedorSet1NewReclamoproveedor.setCompra1(compra);
                    reclamoproveedorSet1NewReclamoproveedor = em.merge(reclamoproveedorSet1NewReclamoproveedor);
                    if (oldCompra1OfReclamoproveedorSet1NewReclamoproveedor != null && !oldCompra1OfReclamoproveedorSet1NewReclamoproveedor.equals(compra)) {
                        oldCompra1OfReclamoproveedorSet1NewReclamoproveedor.getReclamoproveedorSet1().remove(reclamoproveedorSet1NewReclamoproveedor);
                        oldCompra1OfReclamoproveedorSet1NewReclamoproveedor = em.merge(oldCompra1OfReclamoproveedorSet1NewReclamoproveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = compra.getIdcompra();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getIdcompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallecompra> detallecompraSetOrphanCheck = compra.getDetallecompraSet();
            for (Detallecompra detallecompraSetOrphanCheckDetallecompra : detallecompraSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compra (" + compra + ") cannot be destroyed since the Detallecompra " + detallecompraSetOrphanCheckDetallecompra + " in its detallecompraSet field has a non-nullable compra field.");
            }
            Set<Detallecompra> detallecompraSet1OrphanCheck = compra.getDetallecompraSet1();
            for (Detallecompra detallecompraSet1OrphanCheckDetallecompra : detallecompraSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compra (" + compra + ") cannot be destroyed since the Detallecompra " + detallecompraSet1OrphanCheckDetallecompra + " in its detallecompraSet1 field has a non-nullable compra1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadocompra estado = compra.getEstado();
            if (estado != null) {
                estado.getCompraSet().remove(compra);
                estado = em.merge(estado);
            }
            Estadocompra estado1 = compra.getEstado1();
            if (estado1 != null) {
                estado1.getCompraSet().remove(compra);
                estado1 = em.merge(estado1);
            }
            Proveedor proveedor = compra.getProveedor();
            if (proveedor != null) {
                proveedor.getCompraSet().remove(compra);
                proveedor = em.merge(proveedor);
            }
            Proveedor proveedor1 = compra.getProveedor1();
            if (proveedor1 != null) {
                proveedor1.getCompraSet().remove(compra);
                proveedor1 = em.merge(proveedor1);
            }
            Set<Reclamoproveedor> reclamoproveedorSet = compra.getReclamoproveedorSet();
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedor : reclamoproveedorSet) {
                reclamoproveedorSetReclamoproveedor.setCompra(null);
                reclamoproveedorSetReclamoproveedor = em.merge(reclamoproveedorSetReclamoproveedor);
            }
            Set<Reclamoproveedor> reclamoproveedorSet1 = compra.getReclamoproveedorSet1();
            for (Reclamoproveedor reclamoproveedorSet1Reclamoproveedor : reclamoproveedorSet1) {
                reclamoproveedorSet1Reclamoproveedor.setCompra1(null);
                reclamoproveedorSet1Reclamoproveedor = em.merge(reclamoproveedorSet1Reclamoproveedor);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
