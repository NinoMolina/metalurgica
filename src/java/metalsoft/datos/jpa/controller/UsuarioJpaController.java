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
import metalsoft.datos.jpa.entity.Cliente;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Factura;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Usuarioxrol;
import metalsoft.datos.jpa.entity.Sesion;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Comprobantepago;

/**
 *
 * @author Nino
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getClienteList() == null) {
            usuario.setClienteList(new ArrayList<Cliente>());
        }
        if (usuario.getFacturaList() == null) {
            usuario.setFacturaList(new ArrayList<Factura>());
        }
        if (usuario.getUsuarioxrolList() == null) {
            usuario.setUsuarioxrolList(new ArrayList<Usuarioxrol>());
        }
        if (usuario.getSesionList() == null) {
            usuario.setSesionList(new ArrayList<Sesion>());
        }
        if (usuario.getEmpleadoList() == null) {
            usuario.setEmpleadoList(new ArrayList<Empleado>());
        }
        if (usuario.getComprobantepagoList() == null) {
            usuario.setComprobantepagoList(new ArrayList<Comprobantepago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : usuario.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            usuario.setClienteList(attachedClienteList);
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : usuario.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdfactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            usuario.setFacturaList(attachedFacturaList);
            List<Usuarioxrol> attachedUsuarioxrolList = new ArrayList<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolListUsuarioxrolToAttach : usuario.getUsuarioxrolList()) {
                usuarioxrolListUsuarioxrolToAttach = em.getReference(usuarioxrolListUsuarioxrolToAttach.getClass(), usuarioxrolListUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolList.add(usuarioxrolListUsuarioxrolToAttach);
            }
            usuario.setUsuarioxrolList(attachedUsuarioxrolList);
            List<Sesion> attachedSesionList = new ArrayList<Sesion>();
            for (Sesion sesionListSesionToAttach : usuario.getSesionList()) {
                sesionListSesionToAttach = em.getReference(sesionListSesionToAttach.getClass(), sesionListSesionToAttach.getIdsesion());
                attachedSesionList.add(sesionListSesionToAttach);
            }
            usuario.setSesionList(attachedSesionList);
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : usuario.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getIdempleado());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            usuario.setEmpleadoList(attachedEmpleadoList);
            List<Comprobantepago> attachedComprobantepagoList = new ArrayList<Comprobantepago>();
            for (Comprobantepago comprobantepagoListComprobantepagoToAttach : usuario.getComprobantepagoList()) {
                comprobantepagoListComprobantepagoToAttach = em.getReference(comprobantepagoListComprobantepagoToAttach.getClass(), comprobantepagoListComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoList.add(comprobantepagoListComprobantepagoToAttach);
            }
            usuario.setComprobantepagoList(attachedComprobantepagoList);
            em.persist(usuario);
            for (Cliente clienteListCliente : usuario.getClienteList()) {
                Usuario oldUsuarioOfClienteListCliente = clienteListCliente.getUsuario();
                clienteListCliente.setUsuario(usuario);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldUsuarioOfClienteListCliente != null) {
                    oldUsuarioOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldUsuarioOfClienteListCliente = em.merge(oldUsuarioOfClienteListCliente);
                }
            }
            for (Factura facturaListFactura : usuario.getFacturaList()) {
                Usuario oldUsuarioOfFacturaListFactura = facturaListFactura.getUsuario();
                facturaListFactura.setUsuario(usuario);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldUsuarioOfFacturaListFactura != null) {
                    oldUsuarioOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldUsuarioOfFacturaListFactura = em.merge(oldUsuarioOfFacturaListFactura);
                }
            }
            for (Usuarioxrol usuarioxrolListUsuarioxrol : usuario.getUsuarioxrolList()) {
                Usuario oldUsuarioOfUsuarioxrolListUsuarioxrol = usuarioxrolListUsuarioxrol.getUsuario();
                usuarioxrolListUsuarioxrol.setUsuario(usuario);
                usuarioxrolListUsuarioxrol = em.merge(usuarioxrolListUsuarioxrol);
                if (oldUsuarioOfUsuarioxrolListUsuarioxrol != null) {
                    oldUsuarioOfUsuarioxrolListUsuarioxrol.getUsuarioxrolList().remove(usuarioxrolListUsuarioxrol);
                    oldUsuarioOfUsuarioxrolListUsuarioxrol = em.merge(oldUsuarioOfUsuarioxrolListUsuarioxrol);
                }
            }
            for (Sesion sesionListSesion : usuario.getSesionList()) {
                Usuario oldUsuarioOfSesionListSesion = sesionListSesion.getUsuario();
                sesionListSesion.setUsuario(usuario);
                sesionListSesion = em.merge(sesionListSesion);
                if (oldUsuarioOfSesionListSesion != null) {
                    oldUsuarioOfSesionListSesion.getSesionList().remove(sesionListSesion);
                    oldUsuarioOfSesionListSesion = em.merge(oldUsuarioOfSesionListSesion);
                }
            }
            for (Empleado empleadoListEmpleado : usuario.getEmpleadoList()) {
                Usuario oldUsuarioOfEmpleadoListEmpleado = empleadoListEmpleado.getUsuario();
                empleadoListEmpleado.setUsuario(usuario);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldUsuarioOfEmpleadoListEmpleado != null) {
                    oldUsuarioOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldUsuarioOfEmpleadoListEmpleado = em.merge(oldUsuarioOfEmpleadoListEmpleado);
                }
            }
            for (Comprobantepago comprobantepagoListComprobantepago : usuario.getComprobantepagoList()) {
                Usuario oldUsuarioOfComprobantepagoListComprobantepago = comprobantepagoListComprobantepago.getUsuario();
                comprobantepagoListComprobantepago.setUsuario(usuario);
                comprobantepagoListComprobantepago = em.merge(comprobantepagoListComprobantepago);
                if (oldUsuarioOfComprobantepagoListComprobantepago != null) {
                    oldUsuarioOfComprobantepagoListComprobantepago.getComprobantepagoList().remove(comprobantepagoListComprobantepago);
                    oldUsuarioOfComprobantepagoListComprobantepago = em.merge(oldUsuarioOfComprobantepagoListComprobantepago);
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

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdusuario());
            List<Cliente> clienteListOld = persistentUsuario.getClienteList();
            List<Cliente> clienteListNew = usuario.getClienteList();
            List<Factura> facturaListOld = persistentUsuario.getFacturaList();
            List<Factura> facturaListNew = usuario.getFacturaList();
            List<Usuarioxrol> usuarioxrolListOld = persistentUsuario.getUsuarioxrolList();
            List<Usuarioxrol> usuarioxrolListNew = usuario.getUsuarioxrolList();
            List<Sesion> sesionListOld = persistentUsuario.getSesionList();
            List<Sesion> sesionListNew = usuario.getSesionList();
            List<Empleado> empleadoListOld = persistentUsuario.getEmpleadoList();
            List<Empleado> empleadoListNew = usuario.getEmpleadoList();
            List<Comprobantepago> comprobantepagoListOld = persistentUsuario.getComprobantepagoList();
            List<Comprobantepago> comprobantepagoListNew = usuario.getComprobantepagoList();
            List<String> illegalOrphanMessages = null;
            for (Usuarioxrol usuarioxrolListOldUsuarioxrol : usuarioxrolListOld) {
                if (!usuarioxrolListNew.contains(usuarioxrolListOldUsuarioxrol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioxrol " + usuarioxrolListOldUsuarioxrol + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            usuario.setClienteList(clienteListNew);
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdfactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            usuario.setFacturaList(facturaListNew);
            List<Usuarioxrol> attachedUsuarioxrolListNew = new ArrayList<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolListNewUsuarioxrolToAttach : usuarioxrolListNew) {
                usuarioxrolListNewUsuarioxrolToAttach = em.getReference(usuarioxrolListNewUsuarioxrolToAttach.getClass(), usuarioxrolListNewUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolListNew.add(usuarioxrolListNewUsuarioxrolToAttach);
            }
            usuarioxrolListNew = attachedUsuarioxrolListNew;
            usuario.setUsuarioxrolList(usuarioxrolListNew);
            List<Sesion> attachedSesionListNew = new ArrayList<Sesion>();
            for (Sesion sesionListNewSesionToAttach : sesionListNew) {
                sesionListNewSesionToAttach = em.getReference(sesionListNewSesionToAttach.getClass(), sesionListNewSesionToAttach.getIdsesion());
                attachedSesionListNew.add(sesionListNewSesionToAttach);
            }
            sesionListNew = attachedSesionListNew;
            usuario.setSesionList(sesionListNew);
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            usuario.setEmpleadoList(empleadoListNew);
            List<Comprobantepago> attachedComprobantepagoListNew = new ArrayList<Comprobantepago>();
            for (Comprobantepago comprobantepagoListNewComprobantepagoToAttach : comprobantepagoListNew) {
                comprobantepagoListNewComprobantepagoToAttach = em.getReference(comprobantepagoListNewComprobantepagoToAttach.getClass(), comprobantepagoListNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoListNew.add(comprobantepagoListNewComprobantepagoToAttach);
            }
            comprobantepagoListNew = attachedComprobantepagoListNew;
            usuario.setComprobantepagoList(comprobantepagoListNew);
            usuario = em.merge(usuario);
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setUsuario(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Usuario oldUsuarioOfClienteListNewCliente = clienteListNewCliente.getUsuario();
                    clienteListNewCliente.setUsuario(usuario);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldUsuarioOfClienteListNewCliente != null && !oldUsuarioOfClienteListNewCliente.equals(usuario)) {
                        oldUsuarioOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldUsuarioOfClienteListNewCliente = em.merge(oldUsuarioOfClienteListNewCliente);
                    }
                }
            }
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    facturaListOldFactura.setUsuario(null);
                    facturaListOldFactura = em.merge(facturaListOldFactura);
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Usuario oldUsuarioOfFacturaListNewFactura = facturaListNewFactura.getUsuario();
                    facturaListNewFactura.setUsuario(usuario);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldUsuarioOfFacturaListNewFactura != null && !oldUsuarioOfFacturaListNewFactura.equals(usuario)) {
                        oldUsuarioOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldUsuarioOfFacturaListNewFactura = em.merge(oldUsuarioOfFacturaListNewFactura);
                    }
                }
            }
            for (Usuarioxrol usuarioxrolListNewUsuarioxrol : usuarioxrolListNew) {
                if (!usuarioxrolListOld.contains(usuarioxrolListNewUsuarioxrol)) {
                    Usuario oldUsuarioOfUsuarioxrolListNewUsuarioxrol = usuarioxrolListNewUsuarioxrol.getUsuario();
                    usuarioxrolListNewUsuarioxrol.setUsuario(usuario);
                    usuarioxrolListNewUsuarioxrol = em.merge(usuarioxrolListNewUsuarioxrol);
                    if (oldUsuarioOfUsuarioxrolListNewUsuarioxrol != null && !oldUsuarioOfUsuarioxrolListNewUsuarioxrol.equals(usuario)) {
                        oldUsuarioOfUsuarioxrolListNewUsuarioxrol.getUsuarioxrolList().remove(usuarioxrolListNewUsuarioxrol);
                        oldUsuarioOfUsuarioxrolListNewUsuarioxrol = em.merge(oldUsuarioOfUsuarioxrolListNewUsuarioxrol);
                    }
                }
            }
            for (Sesion sesionListOldSesion : sesionListOld) {
                if (!sesionListNew.contains(sesionListOldSesion)) {
                    sesionListOldSesion.setUsuario(null);
                    sesionListOldSesion = em.merge(sesionListOldSesion);
                }
            }
            for (Sesion sesionListNewSesion : sesionListNew) {
                if (!sesionListOld.contains(sesionListNewSesion)) {
                    Usuario oldUsuarioOfSesionListNewSesion = sesionListNewSesion.getUsuario();
                    sesionListNewSesion.setUsuario(usuario);
                    sesionListNewSesion = em.merge(sesionListNewSesion);
                    if (oldUsuarioOfSesionListNewSesion != null && !oldUsuarioOfSesionListNewSesion.equals(usuario)) {
                        oldUsuarioOfSesionListNewSesion.getSesionList().remove(sesionListNewSesion);
                        oldUsuarioOfSesionListNewSesion = em.merge(oldUsuarioOfSesionListNewSesion);
                    }
                }
            }
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    empleadoListOldEmpleado.setUsuario(null);
                    empleadoListOldEmpleado = em.merge(empleadoListOldEmpleado);
                }
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Usuario oldUsuarioOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getUsuario();
                    empleadoListNewEmpleado.setUsuario(usuario);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldUsuarioOfEmpleadoListNewEmpleado != null && !oldUsuarioOfEmpleadoListNewEmpleado.equals(usuario)) {
                        oldUsuarioOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldUsuarioOfEmpleadoListNewEmpleado = em.merge(oldUsuarioOfEmpleadoListNewEmpleado);
                    }
                }
            }
            for (Comprobantepago comprobantepagoListOldComprobantepago : comprobantepagoListOld) {
                if (!comprobantepagoListNew.contains(comprobantepagoListOldComprobantepago)) {
                    comprobantepagoListOldComprobantepago.setUsuario(null);
                    comprobantepagoListOldComprobantepago = em.merge(comprobantepagoListOldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoListNewComprobantepago : comprobantepagoListNew) {
                if (!comprobantepagoListOld.contains(comprobantepagoListNewComprobantepago)) {
                    Usuario oldUsuarioOfComprobantepagoListNewComprobantepago = comprobantepagoListNewComprobantepago.getUsuario();
                    comprobantepagoListNewComprobantepago.setUsuario(usuario);
                    comprobantepagoListNewComprobantepago = em.merge(comprobantepagoListNewComprobantepago);
                    if (oldUsuarioOfComprobantepagoListNewComprobantepago != null && !oldUsuarioOfComprobantepagoListNewComprobantepago.equals(usuario)) {
                        oldUsuarioOfComprobantepagoListNewComprobantepago.getComprobantepagoList().remove(comprobantepagoListNewComprobantepago);
                        oldUsuarioOfComprobantepagoListNewComprobantepago = em.merge(oldUsuarioOfComprobantepagoListNewComprobantepago);
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

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Usuarioxrol> usuarioxrolListOrphanCheck = usuario.getUsuarioxrolList();
            for (Usuarioxrol usuarioxrolListOrphanCheckUsuarioxrol : usuarioxrolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Usuarioxrol " + usuarioxrolListOrphanCheckUsuarioxrol + " in its usuarioxrolList field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cliente> clienteList = usuario.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setUsuario(null);
                clienteListCliente = em.merge(clienteListCliente);
            }
            List<Factura> facturaList = usuario.getFacturaList();
            for (Factura facturaListFactura : facturaList) {
                facturaListFactura.setUsuario(null);
                facturaListFactura = em.merge(facturaListFactura);
            }
            List<Sesion> sesionList = usuario.getSesionList();
            for (Sesion sesionListSesion : sesionList) {
                sesionListSesion.setUsuario(null);
                sesionListSesion = em.merge(sesionListSesion);
            }
            List<Empleado> empleadoList = usuario.getEmpleadoList();
            for (Empleado empleadoListEmpleado : empleadoList) {
                empleadoListEmpleado.setUsuario(null);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
            }
            List<Comprobantepago> comprobantepagoList = usuario.getComprobantepagoList();
            for (Comprobantepago comprobantepagoListComprobantepago : comprobantepagoList) {
                comprobantepagoListComprobantepago.setUsuario(null);
                comprobantepagoListComprobantepago = em.merge(comprobantepagoListComprobantepago);
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
