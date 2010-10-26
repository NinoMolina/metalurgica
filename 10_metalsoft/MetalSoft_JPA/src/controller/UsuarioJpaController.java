/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Rol;
import java.util.HashSet;
import java.util.Set;
import entity.Cliente;
import entity.Factura;
import entity.Sesion;
import entity.Empleado;
import entity.Comprobantepago;

/**
 *
 * @author Nino
 */
public class UsuarioJpaController {

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getRolSet() == null) {
            usuario.setRolSet(new HashSet<Rol>());
        }
        if (usuario.getClienteSet() == null) {
            usuario.setClienteSet(new HashSet<Cliente>());
        }
        if (usuario.getFacturaSet() == null) {
            usuario.setFacturaSet(new HashSet<Factura>());
        }
        if (usuario.getSesionSet() == null) {
            usuario.setSesionSet(new HashSet<Sesion>());
        }
        if (usuario.getEmpleadoSet() == null) {
            usuario.setEmpleadoSet(new HashSet<Empleado>());
        }
        if (usuario.getComprobantepagoSet() == null) {
            usuario.setComprobantepagoSet(new HashSet<Comprobantepago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Rol> attachedRolSet = new HashSet<Rol>();
            for (Rol rolSetRolToAttach : usuario.getRolSet()) {
                rolSetRolToAttach = em.getReference(rolSetRolToAttach.getClass(), rolSetRolToAttach.getIdrol());
                attachedRolSet.add(rolSetRolToAttach);
            }
            usuario.setRolSet(attachedRolSet);
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : usuario.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            usuario.setClienteSet(attachedClienteSet);
            Set<Factura> attachedFacturaSet = new HashSet<Factura>();
            for (Factura facturaSetFacturaToAttach : usuario.getFacturaSet()) {
                facturaSetFacturaToAttach = em.getReference(facturaSetFacturaToAttach.getClass(), facturaSetFacturaToAttach.getIdfactura());
                attachedFacturaSet.add(facturaSetFacturaToAttach);
            }
            usuario.setFacturaSet(attachedFacturaSet);
            Set<Sesion> attachedSesionSet = new HashSet<Sesion>();
            for (Sesion sesionSetSesionToAttach : usuario.getSesionSet()) {
                sesionSetSesionToAttach = em.getReference(sesionSetSesionToAttach.getClass(), sesionSetSesionToAttach.getIdsesion());
                attachedSesionSet.add(sesionSetSesionToAttach);
            }
            usuario.setSesionSet(attachedSesionSet);
            Set<Empleado> attachedEmpleadoSet = new HashSet<Empleado>();
            for (Empleado empleadoSetEmpleadoToAttach : usuario.getEmpleadoSet()) {
                empleadoSetEmpleadoToAttach = em.getReference(empleadoSetEmpleadoToAttach.getClass(), empleadoSetEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet.add(empleadoSetEmpleadoToAttach);
            }
            usuario.setEmpleadoSet(attachedEmpleadoSet);
            Set<Comprobantepago> attachedComprobantepagoSet = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetComprobantepagoToAttach : usuario.getComprobantepagoSet()) {
                comprobantepagoSetComprobantepagoToAttach = em.getReference(comprobantepagoSetComprobantepagoToAttach.getClass(), comprobantepagoSetComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet.add(comprobantepagoSetComprobantepagoToAttach);
            }
            usuario.setComprobantepagoSet(attachedComprobantepagoSet);
            em.persist(usuario);
            for (Rol rolSetRol : usuario.getRolSet()) {
                rolSetRol.getUsuarioSet().add(usuario);
                rolSetRol = em.merge(rolSetRol);
            }
            for (Cliente clienteSetCliente : usuario.getClienteSet()) {
                Usuario oldUsuarioOfClienteSetCliente = clienteSetCliente.getUsuario();
                clienteSetCliente.setUsuario(usuario);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldUsuarioOfClienteSetCliente != null) {
                    oldUsuarioOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldUsuarioOfClienteSetCliente = em.merge(oldUsuarioOfClienteSetCliente);
                }
            }
            for (Factura facturaSetFactura : usuario.getFacturaSet()) {
                Usuario oldUsuarioOfFacturaSetFactura = facturaSetFactura.getUsuario();
                facturaSetFactura.setUsuario(usuario);
                facturaSetFactura = em.merge(facturaSetFactura);
                if (oldUsuarioOfFacturaSetFactura != null) {
                    oldUsuarioOfFacturaSetFactura.getFacturaSet().remove(facturaSetFactura);
                    oldUsuarioOfFacturaSetFactura = em.merge(oldUsuarioOfFacturaSetFactura);
                }
            }
            for (Sesion sesionSetSesion : usuario.getSesionSet()) {
                Usuario oldUsuarioOfSesionSetSesion = sesionSetSesion.getUsuario();
                sesionSetSesion.setUsuario(usuario);
                sesionSetSesion = em.merge(sesionSetSesion);
                if (oldUsuarioOfSesionSetSesion != null) {
                    oldUsuarioOfSesionSetSesion.getSesionSet().remove(sesionSetSesion);
                    oldUsuarioOfSesionSetSesion = em.merge(oldUsuarioOfSesionSetSesion);
                }
            }
            for (Empleado empleadoSetEmpleado : usuario.getEmpleadoSet()) {
                Usuario oldUsuarioOfEmpleadoSetEmpleado = empleadoSetEmpleado.getUsuario();
                empleadoSetEmpleado.setUsuario(usuario);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
                if (oldUsuarioOfEmpleadoSetEmpleado != null) {
                    oldUsuarioOfEmpleadoSetEmpleado.getEmpleadoSet().remove(empleadoSetEmpleado);
                    oldUsuarioOfEmpleadoSetEmpleado = em.merge(oldUsuarioOfEmpleadoSetEmpleado);
                }
            }
            for (Comprobantepago comprobantepagoSetComprobantepago : usuario.getComprobantepagoSet()) {
                Usuario oldUsuarioOfComprobantepagoSetComprobantepago = comprobantepagoSetComprobantepago.getUsuario();
                comprobantepagoSetComprobantepago.setUsuario(usuario);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
                if (oldUsuarioOfComprobantepagoSetComprobantepago != null) {
                    oldUsuarioOfComprobantepagoSetComprobantepago.getComprobantepagoSet().remove(comprobantepagoSetComprobantepago);
                    oldUsuarioOfComprobantepagoSetComprobantepago = em.merge(oldUsuarioOfComprobantepagoSetComprobantepago);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getIdusuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdusuario());
            Set<Rol> rolSetOld = persistentUsuario.getRolSet();
            Set<Rol> rolSetNew = usuario.getRolSet();
            Set<Cliente> clienteSetOld = persistentUsuario.getClienteSet();
            Set<Cliente> clienteSetNew = usuario.getClienteSet();
            Set<Factura> facturaSetOld = persistentUsuario.getFacturaSet();
            Set<Factura> facturaSetNew = usuario.getFacturaSet();
            Set<Sesion> sesionSetOld = persistentUsuario.getSesionSet();
            Set<Sesion> sesionSetNew = usuario.getSesionSet();
            Set<Empleado> empleadoSetOld = persistentUsuario.getEmpleadoSet();
            Set<Empleado> empleadoSetNew = usuario.getEmpleadoSet();
            Set<Comprobantepago> comprobantepagoSetOld = persistentUsuario.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSetNew = usuario.getComprobantepagoSet();
            Set<Rol> attachedRolSetNew = new HashSet<Rol>();
            for (Rol rolSetNewRolToAttach : rolSetNew) {
                rolSetNewRolToAttach = em.getReference(rolSetNewRolToAttach.getClass(), rolSetNewRolToAttach.getIdrol());
                attachedRolSetNew.add(rolSetNewRolToAttach);
            }
            rolSetNew = attachedRolSetNew;
            usuario.setRolSet(rolSetNew);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            usuario.setClienteSet(clienteSetNew);
            Set<Factura> attachedFacturaSetNew = new HashSet<Factura>();
            for (Factura facturaSetNewFacturaToAttach : facturaSetNew) {
                facturaSetNewFacturaToAttach = em.getReference(facturaSetNewFacturaToAttach.getClass(), facturaSetNewFacturaToAttach.getIdfactura());
                attachedFacturaSetNew.add(facturaSetNewFacturaToAttach);
            }
            facturaSetNew = attachedFacturaSetNew;
            usuario.setFacturaSet(facturaSetNew);
            Set<Sesion> attachedSesionSetNew = new HashSet<Sesion>();
            for (Sesion sesionSetNewSesionToAttach : sesionSetNew) {
                sesionSetNewSesionToAttach = em.getReference(sesionSetNewSesionToAttach.getClass(), sesionSetNewSesionToAttach.getIdsesion());
                attachedSesionSetNew.add(sesionSetNewSesionToAttach);
            }
            sesionSetNew = attachedSesionSetNew;
            usuario.setSesionSet(sesionSetNew);
            Set<Empleado> attachedEmpleadoSetNew = new HashSet<Empleado>();
            for (Empleado empleadoSetNewEmpleadoToAttach : empleadoSetNew) {
                empleadoSetNewEmpleadoToAttach = em.getReference(empleadoSetNewEmpleadoToAttach.getClass(), empleadoSetNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSetNew.add(empleadoSetNewEmpleadoToAttach);
            }
            empleadoSetNew = attachedEmpleadoSetNew;
            usuario.setEmpleadoSet(empleadoSetNew);
            Set<Comprobantepago> attachedComprobantepagoSetNew = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetNewComprobantepagoToAttach : comprobantepagoSetNew) {
                comprobantepagoSetNewComprobantepagoToAttach = em.getReference(comprobantepagoSetNewComprobantepagoToAttach.getClass(), comprobantepagoSetNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSetNew.add(comprobantepagoSetNewComprobantepagoToAttach);
            }
            comprobantepagoSetNew = attachedComprobantepagoSetNew;
            usuario.setComprobantepagoSet(comprobantepagoSetNew);
            usuario = em.merge(usuario);
            for (Rol rolSetOldRol : rolSetOld) {
                if (!rolSetNew.contains(rolSetOldRol)) {
                    rolSetOldRol.getUsuarioSet().remove(usuario);
                    rolSetOldRol = em.merge(rolSetOldRol);
                }
            }
            for (Rol rolSetNewRol : rolSetNew) {
                if (!rolSetOld.contains(rolSetNewRol)) {
                    rolSetNewRol.getUsuarioSet().add(usuario);
                    rolSetNewRol = em.merge(rolSetNewRol);
                }
            }
            for (Cliente clienteSetOldCliente : clienteSetOld) {
                if (!clienteSetNew.contains(clienteSetOldCliente)) {
                    clienteSetOldCliente.setUsuario(null);
                    clienteSetOldCliente = em.merge(clienteSetOldCliente);
                }
            }
            for (Cliente clienteSetNewCliente : clienteSetNew) {
                if (!clienteSetOld.contains(clienteSetNewCliente)) {
                    Usuario oldUsuarioOfClienteSetNewCliente = clienteSetNewCliente.getUsuario();
                    clienteSetNewCliente.setUsuario(usuario);
                    clienteSetNewCliente = em.merge(clienteSetNewCliente);
                    if (oldUsuarioOfClienteSetNewCliente != null && !oldUsuarioOfClienteSetNewCliente.equals(usuario)) {
                        oldUsuarioOfClienteSetNewCliente.getClienteSet().remove(clienteSetNewCliente);
                        oldUsuarioOfClienteSetNewCliente = em.merge(oldUsuarioOfClienteSetNewCliente);
                    }
                }
            }
            for (Factura facturaSetOldFactura : facturaSetOld) {
                if (!facturaSetNew.contains(facturaSetOldFactura)) {
                    facturaSetOldFactura.setUsuario(null);
                    facturaSetOldFactura = em.merge(facturaSetOldFactura);
                }
            }
            for (Factura facturaSetNewFactura : facturaSetNew) {
                if (!facturaSetOld.contains(facturaSetNewFactura)) {
                    Usuario oldUsuarioOfFacturaSetNewFactura = facturaSetNewFactura.getUsuario();
                    facturaSetNewFactura.setUsuario(usuario);
                    facturaSetNewFactura = em.merge(facturaSetNewFactura);
                    if (oldUsuarioOfFacturaSetNewFactura != null && !oldUsuarioOfFacturaSetNewFactura.equals(usuario)) {
                        oldUsuarioOfFacturaSetNewFactura.getFacturaSet().remove(facturaSetNewFactura);
                        oldUsuarioOfFacturaSetNewFactura = em.merge(oldUsuarioOfFacturaSetNewFactura);
                    }
                }
            }
            for (Sesion sesionSetOldSesion : sesionSetOld) {
                if (!sesionSetNew.contains(sesionSetOldSesion)) {
                    sesionSetOldSesion.setUsuario(null);
                    sesionSetOldSesion = em.merge(sesionSetOldSesion);
                }
            }
            for (Sesion sesionSetNewSesion : sesionSetNew) {
                if (!sesionSetOld.contains(sesionSetNewSesion)) {
                    Usuario oldUsuarioOfSesionSetNewSesion = sesionSetNewSesion.getUsuario();
                    sesionSetNewSesion.setUsuario(usuario);
                    sesionSetNewSesion = em.merge(sesionSetNewSesion);
                    if (oldUsuarioOfSesionSetNewSesion != null && !oldUsuarioOfSesionSetNewSesion.equals(usuario)) {
                        oldUsuarioOfSesionSetNewSesion.getSesionSet().remove(sesionSetNewSesion);
                        oldUsuarioOfSesionSetNewSesion = em.merge(oldUsuarioOfSesionSetNewSesion);
                    }
                }
            }
            for (Empleado empleadoSetOldEmpleado : empleadoSetOld) {
                if (!empleadoSetNew.contains(empleadoSetOldEmpleado)) {
                    empleadoSetOldEmpleado.setUsuario(null);
                    empleadoSetOldEmpleado = em.merge(empleadoSetOldEmpleado);
                }
            }
            for (Empleado empleadoSetNewEmpleado : empleadoSetNew) {
                if (!empleadoSetOld.contains(empleadoSetNewEmpleado)) {
                    Usuario oldUsuarioOfEmpleadoSetNewEmpleado = empleadoSetNewEmpleado.getUsuario();
                    empleadoSetNewEmpleado.setUsuario(usuario);
                    empleadoSetNewEmpleado = em.merge(empleadoSetNewEmpleado);
                    if (oldUsuarioOfEmpleadoSetNewEmpleado != null && !oldUsuarioOfEmpleadoSetNewEmpleado.equals(usuario)) {
                        oldUsuarioOfEmpleadoSetNewEmpleado.getEmpleadoSet().remove(empleadoSetNewEmpleado);
                        oldUsuarioOfEmpleadoSetNewEmpleado = em.merge(oldUsuarioOfEmpleadoSetNewEmpleado);
                    }
                }
            }
            for (Comprobantepago comprobantepagoSetOldComprobantepago : comprobantepagoSetOld) {
                if (!comprobantepagoSetNew.contains(comprobantepagoSetOldComprobantepago)) {
                    comprobantepagoSetOldComprobantepago.setUsuario(null);
                    comprobantepagoSetOldComprobantepago = em.merge(comprobantepagoSetOldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoSetNewComprobantepago : comprobantepagoSetNew) {
                if (!comprobantepagoSetOld.contains(comprobantepagoSetNewComprobantepago)) {
                    Usuario oldUsuarioOfComprobantepagoSetNewComprobantepago = comprobantepagoSetNewComprobantepago.getUsuario();
                    comprobantepagoSetNewComprobantepago.setUsuario(usuario);
                    comprobantepagoSetNewComprobantepago = em.merge(comprobantepagoSetNewComprobantepago);
                    if (oldUsuarioOfComprobantepagoSetNewComprobantepago != null && !oldUsuarioOfComprobantepagoSetNewComprobantepago.equals(usuario)) {
                        oldUsuarioOfComprobantepagoSetNewComprobantepago.getComprobantepagoSet().remove(comprobantepagoSetNewComprobantepago);
                        oldUsuarioOfComprobantepagoSetNewComprobantepago = em.merge(oldUsuarioOfComprobantepagoSetNewComprobantepago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getIdusuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Set<Rol> rolSet = usuario.getRolSet();
            for (Rol rolSetRol : rolSet) {
                rolSetRol.getUsuarioSet().remove(usuario);
                rolSetRol = em.merge(rolSetRol);
            }
            Set<Cliente> clienteSet = usuario.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setUsuario(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Factura> facturaSet = usuario.getFacturaSet();
            for (Factura facturaSetFactura : facturaSet) {
                facturaSetFactura.setUsuario(null);
                facturaSetFactura = em.merge(facturaSetFactura);
            }
            Set<Sesion> sesionSet = usuario.getSesionSet();
            for (Sesion sesionSetSesion : sesionSet) {
                sesionSetSesion.setUsuario(null);
                sesionSetSesion = em.merge(sesionSetSesion);
            }
            Set<Empleado> empleadoSet = usuario.getEmpleadoSet();
            for (Empleado empleadoSetEmpleado : empleadoSet) {
                empleadoSetEmpleado.setUsuario(null);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
            }
            Set<Comprobantepago> comprobantepagoSet = usuario.getComprobantepagoSet();
            for (Comprobantepago comprobantepagoSetComprobantepago : comprobantepagoSet) {
                comprobantepagoSetComprobantepago.setUsuario(null);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
