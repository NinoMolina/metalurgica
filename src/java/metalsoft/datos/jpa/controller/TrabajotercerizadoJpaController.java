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
import metalsoft.datos.jpa.entity.Estadotrabajotercerizado;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Reclamoempresamantenimiento;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Reclamoempresametalurgica;
import metalsoft.datos.jpa.entity.Detallereclamoempresamantenimiento;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Trabajotercerizado;

/**
 *
 * @author Nino
 */
public class TrabajotercerizadoJpaController implements Serializable {

    public TrabajotercerizadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabajotercerizado trabajotercerizado) throws PreexistingEntityException, Exception {
        if (trabajotercerizado.getReclamoempresamantenimientoList() == null) {
            trabajotercerizado.setReclamoempresamantenimientoList(new ArrayList<Reclamoempresamantenimiento>());
        }
        if (trabajotercerizado.getReclamoempresametalurgicaList() == null) {
            trabajotercerizado.setReclamoempresametalurgicaList(new ArrayList<Reclamoempresametalurgica>());
        }
        if (trabajotercerizado.getDetallereclamoempresamantenimientoList() == null) {
            trabajotercerizado.setDetallereclamoempresamantenimientoList(new ArrayList<Detallereclamoempresamantenimiento>());
        }
        if (trabajotercerizado.getDetalletrabajotercerizadoList() == null) {
            trabajotercerizado.setDetalletrabajotercerizadoList(new ArrayList<Detalletrabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = trabajotercerizado.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                trabajotercerizado.setPedido(pedido);
            }
            Estadotrabajotercerizado estado = trabajotercerizado.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                trabajotercerizado.setEstado(estado);
            }
            Empresametalurgica empresa = trabajotercerizado.getEmpresa();
            if (empresa != null) {
                empresa = em.getReference(empresa.getClass(), empresa.getIdempresametalurgica());
                trabajotercerizado.setEmpresa(empresa);
            }
            List<Reclamoempresamantenimiento> attachedReclamoempresamantenimientoList = new ArrayList<Reclamoempresamantenimiento>();
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListReclamoempresamantenimientoToAttach : trabajotercerizado.getReclamoempresamantenimientoList()) {
                reclamoempresamantenimientoListReclamoempresamantenimientoToAttach = em.getReference(reclamoempresamantenimientoListReclamoempresamantenimientoToAttach.getClass(), reclamoempresamantenimientoListReclamoempresamantenimientoToAttach.getIdreclamo());
                attachedReclamoempresamantenimientoList.add(reclamoempresamantenimientoListReclamoempresamantenimientoToAttach);
            }
            trabajotercerizado.setReclamoempresamantenimientoList(attachedReclamoempresamantenimientoList);
            List<Reclamoempresametalurgica> attachedReclamoempresametalurgicaList = new ArrayList<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgicaToAttach : trabajotercerizado.getReclamoempresametalurgicaList()) {
                reclamoempresametalurgicaListReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaListReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaListReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaList.add(reclamoempresametalurgicaListReclamoempresametalurgicaToAttach);
            }
            trabajotercerizado.setReclamoempresametalurgicaList(attachedReclamoempresametalurgicaList);
            List<Detallereclamoempresamantenimiento> attachedDetallereclamoempresamantenimientoList = new ArrayList<Detallereclamoempresamantenimiento>();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach : trabajotercerizado.getDetallereclamoempresamantenimientoList()) {
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach = em.getReference(detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach.getClass(), detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach.getIddetalle());
                attachedDetallereclamoempresamantenimientoList.add(detallereclamoempresamantenimientoListDetallereclamoempresamantenimientoToAttach);
            }
            trabajotercerizado.setDetallereclamoempresamantenimientoList(attachedDetallereclamoempresamantenimientoList);
            List<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoList = new ArrayList<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach : trabajotercerizado.getDetalletrabajotercerizadoList()) {
                detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach.getIddetalle());
                attachedDetalletrabajotercerizadoList.add(detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach);
            }
            trabajotercerizado.setDetalletrabajotercerizadoList(attachedDetalletrabajotercerizadoList);
            em.persist(trabajotercerizado);
            if (pedido != null) {
                pedido.getTrabajotercerizadoList().add(trabajotercerizado);
                pedido = em.merge(pedido);
            }
            if (estado != null) {
                estado.getTrabajotercerizadoList().add(trabajotercerizado);
                estado = em.merge(estado);
            }
            if (empresa != null) {
                empresa.getTrabajotercerizadoList().add(trabajotercerizado);
                empresa = em.merge(empresa);
            }
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListReclamoempresamantenimiento : trabajotercerizado.getReclamoempresamantenimientoList()) {
                Trabajotercerizado oldTrabajotercerizadoOfReclamoempresamantenimientoListReclamoempresamantenimiento = reclamoempresamantenimientoListReclamoempresamantenimiento.getTrabajotercerizado();
                reclamoempresamantenimientoListReclamoempresamantenimiento.setTrabajotercerizado(trabajotercerizado);
                reclamoempresamantenimientoListReclamoempresamantenimiento = em.merge(reclamoempresamantenimientoListReclamoempresamantenimiento);
                if (oldTrabajotercerizadoOfReclamoempresamantenimientoListReclamoempresamantenimiento != null) {
                    oldTrabajotercerizadoOfReclamoempresamantenimientoListReclamoempresamantenimiento.getReclamoempresamantenimientoList().remove(reclamoempresamantenimientoListReclamoempresamantenimiento);
                    oldTrabajotercerizadoOfReclamoempresamantenimientoListReclamoempresamantenimiento = em.merge(oldTrabajotercerizadoOfReclamoempresamantenimientoListReclamoempresamantenimiento);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgica : trabajotercerizado.getReclamoempresametalurgicaList()) {
                Trabajotercerizado oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica = reclamoempresametalurgicaListReclamoempresametalurgica.getTrabajotercerizado();
                reclamoempresametalurgicaListReclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
                reclamoempresametalurgicaListReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListReclamoempresametalurgica);
                if (oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica != null) {
                    oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica.getReclamoempresametalurgicaList().remove(reclamoempresametalurgicaListReclamoempresametalurgica);
                    oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica = em.merge(oldTrabajotercerizadoOfReclamoempresametalurgicaListReclamoempresametalurgica);
                }
            }
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento : trabajotercerizado.getDetallereclamoempresamantenimientoList()) {
                Trabajotercerizado oldIdtrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.getIdtrabajo();
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.setIdtrabajo(trabajotercerizado);
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                if (oldIdtrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento != null) {
                    oldIdtrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                    oldIdtrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = em.merge(oldIdtrabajoOfDetallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizado : trabajotercerizado.getDetalletrabajotercerizadoList()) {
                Trabajotercerizado oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado = detalletrabajotercerizadoListDetalletrabajotercerizado.getIdtrabajotercerizado();
                detalletrabajotercerizadoListDetalletrabajotercerizado.setIdtrabajotercerizado(trabajotercerizado);
                detalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListDetalletrabajotercerizado);
                if (oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado != null) {
                    oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizadoListDetalletrabajotercerizado);
                    oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTrabajotercerizado(trabajotercerizado.getIdtrabajo()) != null) {
                throw new PreexistingEntityException("Trabajotercerizado " + trabajotercerizado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trabajotercerizado trabajotercerizado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajotercerizado persistentTrabajotercerizado = em.find(Trabajotercerizado.class, trabajotercerizado.getIdtrabajo());
            Pedido pedidoOld = persistentTrabajotercerizado.getPedido();
            Pedido pedidoNew = trabajotercerizado.getPedido();
            Estadotrabajotercerizado estadoOld = persistentTrabajotercerizado.getEstado();
            Estadotrabajotercerizado estadoNew = trabajotercerizado.getEstado();
            Empresametalurgica empresaOld = persistentTrabajotercerizado.getEmpresa();
            Empresametalurgica empresaNew = trabajotercerizado.getEmpresa();
            List<Reclamoempresamantenimiento> reclamoempresamantenimientoListOld = persistentTrabajotercerizado.getReclamoempresamantenimientoList();
            List<Reclamoempresamantenimiento> reclamoempresamantenimientoListNew = trabajotercerizado.getReclamoempresamantenimientoList();
            List<Reclamoempresametalurgica> reclamoempresametalurgicaListOld = persistentTrabajotercerizado.getReclamoempresametalurgicaList();
            List<Reclamoempresametalurgica> reclamoempresametalurgicaListNew = trabajotercerizado.getReclamoempresametalurgicaList();
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoListOld = persistentTrabajotercerizado.getDetallereclamoempresamantenimientoList();
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoListNew = trabajotercerizado.getDetallereclamoempresamantenimientoList();
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListOld = persistentTrabajotercerizado.getDetalletrabajotercerizadoList();
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListNew = trabajotercerizado.getDetalletrabajotercerizadoList();
            List<String> illegalOrphanMessages = null;
            for (Detalletrabajotercerizado detalletrabajotercerizadoListOldDetalletrabajotercerizado : detalletrabajotercerizadoListOld) {
                if (!detalletrabajotercerizadoListNew.contains(detalletrabajotercerizadoListOldDetalletrabajotercerizado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalletrabajotercerizado " + detalletrabajotercerizadoListOldDetalletrabajotercerizado + " since its idtrabajotercerizado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                trabajotercerizado.setPedido(pedidoNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                trabajotercerizado.setEstado(estadoNew);
            }
            if (empresaNew != null) {
                empresaNew = em.getReference(empresaNew.getClass(), empresaNew.getIdempresametalurgica());
                trabajotercerizado.setEmpresa(empresaNew);
            }
            List<Reclamoempresamantenimiento> attachedReclamoempresamantenimientoListNew = new ArrayList<Reclamoempresamantenimiento>();
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach : reclamoempresamantenimientoListNew) {
                reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach = em.getReference(reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach.getClass(), reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach.getIdreclamo());
                attachedReclamoempresamantenimientoListNew.add(reclamoempresamantenimientoListNewReclamoempresamantenimientoToAttach);
            }
            reclamoempresamantenimientoListNew = attachedReclamoempresamantenimientoListNew;
            trabajotercerizado.setReclamoempresamantenimientoList(reclamoempresamantenimientoListNew);
            List<Reclamoempresametalurgica> attachedReclamoempresametalurgicaListNew = new ArrayList<Reclamoempresametalurgica>();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach : reclamoempresametalurgicaListNew) {
                reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach = em.getReference(reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach.getClass(), reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach.getIdreclamo());
                attachedReclamoempresametalurgicaListNew.add(reclamoempresametalurgicaListNewReclamoempresametalurgicaToAttach);
            }
            reclamoempresametalurgicaListNew = attachedReclamoempresametalurgicaListNew;
            trabajotercerizado.setReclamoempresametalurgicaList(reclamoempresametalurgicaListNew);
            List<Detallereclamoempresamantenimiento> attachedDetallereclamoempresamantenimientoListNew = new ArrayList<Detallereclamoempresamantenimiento>();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach : detallereclamoempresamantenimientoListNew) {
                detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach = em.getReference(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach.getClass(), detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach.getIddetalle());
                attachedDetallereclamoempresamantenimientoListNew.add(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimientoToAttach);
            }
            detallereclamoempresamantenimientoListNew = attachedDetallereclamoempresamantenimientoListNew;
            trabajotercerizado.setDetallereclamoempresamantenimientoList(detallereclamoempresamantenimientoListNew);
            List<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoListNew = new ArrayList<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoListNew) {
                detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach.getIddetalle());
                attachedDetalletrabajotercerizadoListNew.add(detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoListNew = attachedDetalletrabajotercerizadoListNew;
            trabajotercerizado.setDetalletrabajotercerizadoList(detalletrabajotercerizadoListNew);
            trabajotercerizado = em.merge(trabajotercerizado);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getTrabajotercerizadoList().remove(trabajotercerizado);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getTrabajotercerizadoList().add(trabajotercerizado);
                pedidoNew = em.merge(pedidoNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getTrabajotercerizadoList().remove(trabajotercerizado);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getTrabajotercerizadoList().add(trabajotercerizado);
                estadoNew = em.merge(estadoNew);
            }
            if (empresaOld != null && !empresaOld.equals(empresaNew)) {
                empresaOld.getTrabajotercerizadoList().remove(trabajotercerizado);
                empresaOld = em.merge(empresaOld);
            }
            if (empresaNew != null && !empresaNew.equals(empresaOld)) {
                empresaNew.getTrabajotercerizadoList().add(trabajotercerizado);
                empresaNew = em.merge(empresaNew);
            }
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListOldReclamoempresamantenimiento : reclamoempresamantenimientoListOld) {
                if (!reclamoempresamantenimientoListNew.contains(reclamoempresamantenimientoListOldReclamoempresamantenimiento)) {
                    reclamoempresamantenimientoListOldReclamoempresamantenimiento.setTrabajotercerizado(null);
                    reclamoempresamantenimientoListOldReclamoempresamantenimiento = em.merge(reclamoempresamantenimientoListOldReclamoempresamantenimiento);
                }
            }
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListNewReclamoempresamantenimiento : reclamoempresamantenimientoListNew) {
                if (!reclamoempresamantenimientoListOld.contains(reclamoempresamantenimientoListNewReclamoempresamantenimiento)) {
                    Trabajotercerizado oldTrabajotercerizadoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento = reclamoempresamantenimientoListNewReclamoempresamantenimiento.getTrabajotercerizado();
                    reclamoempresamantenimientoListNewReclamoempresamantenimiento.setTrabajotercerizado(trabajotercerizado);
                    reclamoempresamantenimientoListNewReclamoempresamantenimiento = em.merge(reclamoempresamantenimientoListNewReclamoempresamantenimiento);
                    if (oldTrabajotercerizadoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento != null && !oldTrabajotercerizadoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento.equals(trabajotercerizado)) {
                        oldTrabajotercerizadoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento.getReclamoempresamantenimientoList().remove(reclamoempresamantenimientoListNewReclamoempresamantenimiento);
                        oldTrabajotercerizadoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento = em.merge(oldTrabajotercerizadoOfReclamoempresamantenimientoListNewReclamoempresamantenimiento);
                    }
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListOldReclamoempresametalurgica : reclamoempresametalurgicaListOld) {
                if (!reclamoempresametalurgicaListNew.contains(reclamoempresametalurgicaListOldReclamoempresametalurgica)) {
                    reclamoempresametalurgicaListOldReclamoempresametalurgica.setTrabajotercerizado(null);
                    reclamoempresametalurgicaListOldReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListOldReclamoempresametalurgica);
                }
            }
            for (Reclamoempresametalurgica reclamoempresametalurgicaListNewReclamoempresametalurgica : reclamoempresametalurgicaListNew) {
                if (!reclamoempresametalurgicaListOld.contains(reclamoempresametalurgicaListNewReclamoempresametalurgica)) {
                    Trabajotercerizado oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica = reclamoempresametalurgicaListNewReclamoempresametalurgica.getTrabajotercerizado();
                    reclamoempresametalurgicaListNewReclamoempresametalurgica.setTrabajotercerizado(trabajotercerizado);
                    reclamoempresametalurgicaListNewReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListNewReclamoempresametalurgica);
                    if (oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica != null && !oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica.equals(trabajotercerizado)) {
                        oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica.getReclamoempresametalurgicaList().remove(reclamoempresametalurgicaListNewReclamoempresametalurgica);
                        oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica = em.merge(oldTrabajotercerizadoOfReclamoempresametalurgicaListNewReclamoempresametalurgica);
                    }
                }
            }
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoListOld) {
                if (!detallereclamoempresamantenimientoListNew.contains(detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento)) {
                    detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento.setIdtrabajo(null);
                    detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListOldDetallereclamoempresamantenimiento);
                }
            }
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoListNew) {
                if (!detallereclamoempresamantenimientoListOld.contains(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento)) {
                    Trabajotercerizado oldIdtrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.getIdtrabajo();
                    detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.setIdtrabajo(trabajotercerizado);
                    detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                    if (oldIdtrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento != null && !oldIdtrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.equals(trabajotercerizado)) {
                        oldIdtrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                        oldIdtrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento = em.merge(oldIdtrabajoOfDetallereclamoempresamantenimientoListNewDetallereclamoempresamantenimiento);
                    }
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoListNewDetalletrabajotercerizado : detalletrabajotercerizadoListNew) {
                if (!detalletrabajotercerizadoListOld.contains(detalletrabajotercerizadoListNewDetalletrabajotercerizado)) {
                    Trabajotercerizado oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado = detalletrabajotercerizadoListNewDetalletrabajotercerizado.getIdtrabajotercerizado();
                    detalletrabajotercerizadoListNewDetalletrabajotercerizado.setIdtrabajotercerizado(trabajotercerizado);
                    detalletrabajotercerizadoListNewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListNewDetalletrabajotercerizado);
                    if (oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado != null && !oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado.equals(trabajotercerizado)) {
                        oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizadoListNewDetalletrabajotercerizado);
                        oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado = em.merge(oldIdtrabajotercerizadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = trabajotercerizado.getIdtrabajo();
                if (findTrabajotercerizado(id) == null) {
                    throw new NonexistentEntityException("The trabajotercerizado with id " + id + " no longer exists.");
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
            Trabajotercerizado trabajotercerizado;
            try {
                trabajotercerizado = em.getReference(Trabajotercerizado.class, id);
                trabajotercerizado.getIdtrabajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListOrphanCheck = trabajotercerizado.getDetalletrabajotercerizadoList();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListOrphanCheckDetalletrabajotercerizado : detalletrabajotercerizadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Trabajotercerizado (" + trabajotercerizado + ") cannot be destroyed since the Detalletrabajotercerizado " + detalletrabajotercerizadoListOrphanCheckDetalletrabajotercerizado + " in its detalletrabajotercerizadoList field has a non-nullable idtrabajotercerizado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedido = trabajotercerizado.getPedido();
            if (pedido != null) {
                pedido.getTrabajotercerizadoList().remove(trabajotercerizado);
                pedido = em.merge(pedido);
            }
            Estadotrabajotercerizado estado = trabajotercerizado.getEstado();
            if (estado != null) {
                estado.getTrabajotercerizadoList().remove(trabajotercerizado);
                estado = em.merge(estado);
            }
            Empresametalurgica empresa = trabajotercerizado.getEmpresa();
            if (empresa != null) {
                empresa.getTrabajotercerizadoList().remove(trabajotercerizado);
                empresa = em.merge(empresa);
            }
            List<Reclamoempresamantenimiento> reclamoempresamantenimientoList = trabajotercerizado.getReclamoempresamantenimientoList();
            for (Reclamoempresamantenimiento reclamoempresamantenimientoListReclamoempresamantenimiento : reclamoempresamantenimientoList) {
                reclamoempresamantenimientoListReclamoempresamantenimiento.setTrabajotercerizado(null);
                reclamoempresamantenimientoListReclamoempresamantenimiento = em.merge(reclamoempresamantenimientoListReclamoempresamantenimiento);
            }
            List<Reclamoempresametalurgica> reclamoempresametalurgicaList = trabajotercerizado.getReclamoempresametalurgicaList();
            for (Reclamoempresametalurgica reclamoempresametalurgicaListReclamoempresametalurgica : reclamoempresametalurgicaList) {
                reclamoempresametalurgicaListReclamoempresametalurgica.setTrabajotercerizado(null);
                reclamoempresametalurgicaListReclamoempresametalurgica = em.merge(reclamoempresametalurgicaListReclamoempresametalurgica);
            }
            List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoList = trabajotercerizado.getDetallereclamoempresamantenimientoList();
            for (Detallereclamoempresamantenimiento detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento : detallereclamoempresamantenimientoList) {
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento.setIdtrabajo(null);
                detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimientoListDetallereclamoempresamantenimiento);
            }
            em.remove(trabajotercerizado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trabajotercerizado> findTrabajotercerizadoEntities() {
        return findTrabajotercerizadoEntities(true, -1, -1);
    }

    public List<Trabajotercerizado> findTrabajotercerizadoEntities(int maxResults, int firstResult) {
        return findTrabajotercerizadoEntities(false, maxResults, firstResult);
    }

    private List<Trabajotercerizado> findTrabajotercerizadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabajotercerizado.class));
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

    public Trabajotercerizado findTrabajotercerizado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabajotercerizado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabajotercerizadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabajotercerizado> rt = cq.from(Trabajotercerizado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
