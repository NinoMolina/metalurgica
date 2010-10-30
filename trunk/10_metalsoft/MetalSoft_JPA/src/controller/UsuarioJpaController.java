/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
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
import entity.Cliente;
import java.util.HashSet;
import java.util.Set;
import entity.Factura;
import entity.Usuarioxrol;
import entity.Sesion;
import entity.Empleado;
import entity.Comprobantepago;
import java.util.ArrayList;

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
        if (usuario.getClienteSet() == null) {
            usuario.setClienteSet(new HashSet<Cliente>());
        }
        if (usuario.getClienteSet1() == null) {
            usuario.setClienteSet1(new HashSet<Cliente>());
        }
        if (usuario.getFacturaSet() == null) {
            usuario.setFacturaSet(new HashSet<Factura>());
        }
        if (usuario.getFacturaSet1() == null) {
            usuario.setFacturaSet1(new HashSet<Factura>());
        }
        if (usuario.getUsuarioxrolSet() == null) {
            usuario.setUsuarioxrolSet(new HashSet<Usuarioxrol>());
        }
        if (usuario.getUsuarioxrolSet1() == null) {
            usuario.setUsuarioxrolSet1(new HashSet<Usuarioxrol>());
        }
        if (usuario.getSesionSet() == null) {
            usuario.setSesionSet(new HashSet<Sesion>());
        }
        if (usuario.getSesionSet1() == null) {
            usuario.setSesionSet1(new HashSet<Sesion>());
        }
        if (usuario.getEmpleadoSet() == null) {
            usuario.setEmpleadoSet(new HashSet<Empleado>());
        }
        if (usuario.getEmpleadoSet1() == null) {
            usuario.setEmpleadoSet1(new HashSet<Empleado>());
        }
        if (usuario.getComprobantepagoSet() == null) {
            usuario.setComprobantepagoSet(new HashSet<Comprobantepago>());
        }
        if (usuario.getComprobantepagoSet1() == null) {
            usuario.setComprobantepagoSet1(new HashSet<Comprobantepago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : usuario.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            usuario.setClienteSet(attachedClienteSet);
            Set<Cliente> attachedClienteSet1 = new HashSet<Cliente>();
            for (Cliente clienteSet1ClienteToAttach : usuario.getClienteSet1()) {
                clienteSet1ClienteToAttach = em.getReference(clienteSet1ClienteToAttach.getClass(), clienteSet1ClienteToAttach.getIdcliente());
                attachedClienteSet1.add(clienteSet1ClienteToAttach);
            }
            usuario.setClienteSet1(attachedClienteSet1);
            Set<Factura> attachedFacturaSet = new HashSet<Factura>();
            for (Factura facturaSetFacturaToAttach : usuario.getFacturaSet()) {
                facturaSetFacturaToAttach = em.getReference(facturaSetFacturaToAttach.getClass(), facturaSetFacturaToAttach.getIdfactura());
                attachedFacturaSet.add(facturaSetFacturaToAttach);
            }
            usuario.setFacturaSet(attachedFacturaSet);
            Set<Factura> attachedFacturaSet1 = new HashSet<Factura>();
            for (Factura facturaSet1FacturaToAttach : usuario.getFacturaSet1()) {
                facturaSet1FacturaToAttach = em.getReference(facturaSet1FacturaToAttach.getClass(), facturaSet1FacturaToAttach.getIdfactura());
                attachedFacturaSet1.add(facturaSet1FacturaToAttach);
            }
            usuario.setFacturaSet1(attachedFacturaSet1);
            Set<Usuarioxrol> attachedUsuarioxrolSet = new HashSet<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolSetUsuarioxrolToAttach : usuario.getUsuarioxrolSet()) {
                usuarioxrolSetUsuarioxrolToAttach = em.getReference(usuarioxrolSetUsuarioxrolToAttach.getClass(), usuarioxrolSetUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolSet.add(usuarioxrolSetUsuarioxrolToAttach);
            }
            usuario.setUsuarioxrolSet(attachedUsuarioxrolSet);
            Set<Usuarioxrol> attachedUsuarioxrolSet1 = new HashSet<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolSet1UsuarioxrolToAttach : usuario.getUsuarioxrolSet1()) {
                usuarioxrolSet1UsuarioxrolToAttach = em.getReference(usuarioxrolSet1UsuarioxrolToAttach.getClass(), usuarioxrolSet1UsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolSet1.add(usuarioxrolSet1UsuarioxrolToAttach);
            }
            usuario.setUsuarioxrolSet1(attachedUsuarioxrolSet1);
            Set<Sesion> attachedSesionSet = new HashSet<Sesion>();
            for (Sesion sesionSetSesionToAttach : usuario.getSesionSet()) {
                sesionSetSesionToAttach = em.getReference(sesionSetSesionToAttach.getClass(), sesionSetSesionToAttach.getIdsesion());
                attachedSesionSet.add(sesionSetSesionToAttach);
            }
            usuario.setSesionSet(attachedSesionSet);
            Set<Sesion> attachedSesionSet1 = new HashSet<Sesion>();
            for (Sesion sesionSet1SesionToAttach : usuario.getSesionSet1()) {
                sesionSet1SesionToAttach = em.getReference(sesionSet1SesionToAttach.getClass(), sesionSet1SesionToAttach.getIdsesion());
                attachedSesionSet1.add(sesionSet1SesionToAttach);
            }
            usuario.setSesionSet1(attachedSesionSet1);
            Set<Empleado> attachedEmpleadoSet = new HashSet<Empleado>();
            for (Empleado empleadoSetEmpleadoToAttach : usuario.getEmpleadoSet()) {
                empleadoSetEmpleadoToAttach = em.getReference(empleadoSetEmpleadoToAttach.getClass(), empleadoSetEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet.add(empleadoSetEmpleadoToAttach);
            }
            usuario.setEmpleadoSet(attachedEmpleadoSet);
            Set<Empleado> attachedEmpleadoSet1 = new HashSet<Empleado>();
            for (Empleado empleadoSet1EmpleadoToAttach : usuario.getEmpleadoSet1()) {
                empleadoSet1EmpleadoToAttach = em.getReference(empleadoSet1EmpleadoToAttach.getClass(), empleadoSet1EmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1.add(empleadoSet1EmpleadoToAttach);
            }
            usuario.setEmpleadoSet1(attachedEmpleadoSet1);
            Set<Comprobantepago> attachedComprobantepagoSet = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetComprobantepagoToAttach : usuario.getComprobantepagoSet()) {
                comprobantepagoSetComprobantepagoToAttach = em.getReference(comprobantepagoSetComprobantepagoToAttach.getClass(), comprobantepagoSetComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet.add(comprobantepagoSetComprobantepagoToAttach);
            }
            usuario.setComprobantepagoSet(attachedComprobantepagoSet);
            Set<Comprobantepago> attachedComprobantepagoSet1 = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSet1ComprobantepagoToAttach : usuario.getComprobantepagoSet1()) {
                comprobantepagoSet1ComprobantepagoToAttach = em.getReference(comprobantepagoSet1ComprobantepagoToAttach.getClass(), comprobantepagoSet1ComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet1.add(comprobantepagoSet1ComprobantepagoToAttach);
            }
            usuario.setComprobantepagoSet1(attachedComprobantepagoSet1);
            em.persist(usuario);
            for (Cliente clienteSetCliente : usuario.getClienteSet()) {
                Usuario oldUsuarioOfClienteSetCliente = clienteSetCliente.getUsuario();
                clienteSetCliente.setUsuario(usuario);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldUsuarioOfClienteSetCliente != null) {
                    oldUsuarioOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldUsuarioOfClienteSetCliente = em.merge(oldUsuarioOfClienteSetCliente);
                }
            }
            for (Cliente clienteSet1Cliente : usuario.getClienteSet1()) {
                Usuario oldUsuario1OfClienteSet1Cliente = clienteSet1Cliente.getUsuario1();
                clienteSet1Cliente.setUsuario1(usuario);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
                if (oldUsuario1OfClienteSet1Cliente != null) {
                    oldUsuario1OfClienteSet1Cliente.getClienteSet1().remove(clienteSet1Cliente);
                    oldUsuario1OfClienteSet1Cliente = em.merge(oldUsuario1OfClienteSet1Cliente);
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
            for (Factura facturaSet1Factura : usuario.getFacturaSet1()) {
                Usuario oldUsuario1OfFacturaSet1Factura = facturaSet1Factura.getUsuario1();
                facturaSet1Factura.setUsuario1(usuario);
                facturaSet1Factura = em.merge(facturaSet1Factura);
                if (oldUsuario1OfFacturaSet1Factura != null) {
                    oldUsuario1OfFacturaSet1Factura.getFacturaSet1().remove(facturaSet1Factura);
                    oldUsuario1OfFacturaSet1Factura = em.merge(oldUsuario1OfFacturaSet1Factura);
                }
            }
            for (Usuarioxrol usuarioxrolSetUsuarioxrol : usuario.getUsuarioxrolSet()) {
                Usuario oldUsuarioOfUsuarioxrolSetUsuarioxrol = usuarioxrolSetUsuarioxrol.getUsuario();
                usuarioxrolSetUsuarioxrol.setUsuario(usuario);
                usuarioxrolSetUsuarioxrol = em.merge(usuarioxrolSetUsuarioxrol);
                if (oldUsuarioOfUsuarioxrolSetUsuarioxrol != null) {
                    oldUsuarioOfUsuarioxrolSetUsuarioxrol.getUsuarioxrolSet().remove(usuarioxrolSetUsuarioxrol);
                    oldUsuarioOfUsuarioxrolSetUsuarioxrol = em.merge(oldUsuarioOfUsuarioxrolSetUsuarioxrol);
                }
            }
            for (Usuarioxrol usuarioxrolSet1Usuarioxrol : usuario.getUsuarioxrolSet1()) {
                Usuario oldUsuario1OfUsuarioxrolSet1Usuarioxrol = usuarioxrolSet1Usuarioxrol.getUsuario1();
                usuarioxrolSet1Usuarioxrol.setUsuario1(usuario);
                usuarioxrolSet1Usuarioxrol = em.merge(usuarioxrolSet1Usuarioxrol);
                if (oldUsuario1OfUsuarioxrolSet1Usuarioxrol != null) {
                    oldUsuario1OfUsuarioxrolSet1Usuarioxrol.getUsuarioxrolSet1().remove(usuarioxrolSet1Usuarioxrol);
                    oldUsuario1OfUsuarioxrolSet1Usuarioxrol = em.merge(oldUsuario1OfUsuarioxrolSet1Usuarioxrol);
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
            for (Sesion sesionSet1Sesion : usuario.getSesionSet1()) {
                Usuario oldUsuario1OfSesionSet1Sesion = sesionSet1Sesion.getUsuario1();
                sesionSet1Sesion.setUsuario1(usuario);
                sesionSet1Sesion = em.merge(sesionSet1Sesion);
                if (oldUsuario1OfSesionSet1Sesion != null) {
                    oldUsuario1OfSesionSet1Sesion.getSesionSet1().remove(sesionSet1Sesion);
                    oldUsuario1OfSesionSet1Sesion = em.merge(oldUsuario1OfSesionSet1Sesion);
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
            for (Empleado empleadoSet1Empleado : usuario.getEmpleadoSet1()) {
                Usuario oldUsuario1OfEmpleadoSet1Empleado = empleadoSet1Empleado.getUsuario1();
                empleadoSet1Empleado.setUsuario1(usuario);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
                if (oldUsuario1OfEmpleadoSet1Empleado != null) {
                    oldUsuario1OfEmpleadoSet1Empleado.getEmpleadoSet1().remove(empleadoSet1Empleado);
                    oldUsuario1OfEmpleadoSet1Empleado = em.merge(oldUsuario1OfEmpleadoSet1Empleado);
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
            for (Comprobantepago comprobantepagoSet1Comprobantepago : usuario.getComprobantepagoSet1()) {
                Usuario oldUsuario1OfComprobantepagoSet1Comprobantepago = comprobantepagoSet1Comprobantepago.getUsuario1();
                comprobantepagoSet1Comprobantepago.setUsuario1(usuario);
                comprobantepagoSet1Comprobantepago = em.merge(comprobantepagoSet1Comprobantepago);
                if (oldUsuario1OfComprobantepagoSet1Comprobantepago != null) {
                    oldUsuario1OfComprobantepagoSet1Comprobantepago.getComprobantepagoSet1().remove(comprobantepagoSet1Comprobantepago);
                    oldUsuario1OfComprobantepagoSet1Comprobantepago = em.merge(oldUsuario1OfComprobantepagoSet1Comprobantepago);
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
            Set<Cliente> clienteSetOld = persistentUsuario.getClienteSet();
            Set<Cliente> clienteSetNew = usuario.getClienteSet();
            Set<Cliente> clienteSet1Old = persistentUsuario.getClienteSet1();
            Set<Cliente> clienteSet1New = usuario.getClienteSet1();
            Set<Factura> facturaSetOld = persistentUsuario.getFacturaSet();
            Set<Factura> facturaSetNew = usuario.getFacturaSet();
            Set<Factura> facturaSet1Old = persistentUsuario.getFacturaSet1();
            Set<Factura> facturaSet1New = usuario.getFacturaSet1();
            Set<Usuarioxrol> usuarioxrolSetOld = persistentUsuario.getUsuarioxrolSet();
            Set<Usuarioxrol> usuarioxrolSetNew = usuario.getUsuarioxrolSet();
            Set<Usuarioxrol> usuarioxrolSet1Old = persistentUsuario.getUsuarioxrolSet1();
            Set<Usuarioxrol> usuarioxrolSet1New = usuario.getUsuarioxrolSet1();
            Set<Sesion> sesionSetOld = persistentUsuario.getSesionSet();
            Set<Sesion> sesionSetNew = usuario.getSesionSet();
            Set<Sesion> sesionSet1Old = persistentUsuario.getSesionSet1();
            Set<Sesion> sesionSet1New = usuario.getSesionSet1();
            Set<Empleado> empleadoSetOld = persistentUsuario.getEmpleadoSet();
            Set<Empleado> empleadoSetNew = usuario.getEmpleadoSet();
            Set<Empleado> empleadoSet1Old = persistentUsuario.getEmpleadoSet1();
            Set<Empleado> empleadoSet1New = usuario.getEmpleadoSet1();
            Set<Comprobantepago> comprobantepagoSetOld = persistentUsuario.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSetNew = usuario.getComprobantepagoSet();
            Set<Comprobantepago> comprobantepagoSet1Old = persistentUsuario.getComprobantepagoSet1();
            Set<Comprobantepago> comprobantepagoSet1New = usuario.getComprobantepagoSet1();
            List<String> illegalOrphanMessages = null;
            for (Usuarioxrol usuarioxrolSetOldUsuarioxrol : usuarioxrolSetOld) {
                if (!usuarioxrolSetNew.contains(usuarioxrolSetOldUsuarioxrol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioxrol " + usuarioxrolSetOldUsuarioxrol + " since its usuario field is not nullable.");
                }
            }
            for (Usuarioxrol usuarioxrolSet1OldUsuarioxrol : usuarioxrolSet1Old) {
                if (!usuarioxrolSet1New.contains(usuarioxrolSet1OldUsuarioxrol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioxrol " + usuarioxrolSet1OldUsuarioxrol + " since its usuario1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            usuario.setClienteSet(clienteSetNew);
            Set<Cliente> attachedClienteSet1New = new HashSet<Cliente>();
            for (Cliente clienteSet1NewClienteToAttach : clienteSet1New) {
                clienteSet1NewClienteToAttach = em.getReference(clienteSet1NewClienteToAttach.getClass(), clienteSet1NewClienteToAttach.getIdcliente());
                attachedClienteSet1New.add(clienteSet1NewClienteToAttach);
            }
            clienteSet1New = attachedClienteSet1New;
            usuario.setClienteSet1(clienteSet1New);
            Set<Factura> attachedFacturaSetNew = new HashSet<Factura>();
            for (Factura facturaSetNewFacturaToAttach : facturaSetNew) {
                facturaSetNewFacturaToAttach = em.getReference(facturaSetNewFacturaToAttach.getClass(), facturaSetNewFacturaToAttach.getIdfactura());
                attachedFacturaSetNew.add(facturaSetNewFacturaToAttach);
            }
            facturaSetNew = attachedFacturaSetNew;
            usuario.setFacturaSet(facturaSetNew);
            Set<Factura> attachedFacturaSet1New = new HashSet<Factura>();
            for (Factura facturaSet1NewFacturaToAttach : facturaSet1New) {
                facturaSet1NewFacturaToAttach = em.getReference(facturaSet1NewFacturaToAttach.getClass(), facturaSet1NewFacturaToAttach.getIdfactura());
                attachedFacturaSet1New.add(facturaSet1NewFacturaToAttach);
            }
            facturaSet1New = attachedFacturaSet1New;
            usuario.setFacturaSet1(facturaSet1New);
            Set<Usuarioxrol> attachedUsuarioxrolSetNew = new HashSet<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolSetNewUsuarioxrolToAttach : usuarioxrolSetNew) {
                usuarioxrolSetNewUsuarioxrolToAttach = em.getReference(usuarioxrolSetNewUsuarioxrolToAttach.getClass(), usuarioxrolSetNewUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolSetNew.add(usuarioxrolSetNewUsuarioxrolToAttach);
            }
            usuarioxrolSetNew = attachedUsuarioxrolSetNew;
            usuario.setUsuarioxrolSet(usuarioxrolSetNew);
            Set<Usuarioxrol> attachedUsuarioxrolSet1New = new HashSet<Usuarioxrol>();
            for (Usuarioxrol usuarioxrolSet1NewUsuarioxrolToAttach : usuarioxrolSet1New) {
                usuarioxrolSet1NewUsuarioxrolToAttach = em.getReference(usuarioxrolSet1NewUsuarioxrolToAttach.getClass(), usuarioxrolSet1NewUsuarioxrolToAttach.getUsuarioxrolPK());
                attachedUsuarioxrolSet1New.add(usuarioxrolSet1NewUsuarioxrolToAttach);
            }
            usuarioxrolSet1New = attachedUsuarioxrolSet1New;
            usuario.setUsuarioxrolSet1(usuarioxrolSet1New);
            Set<Sesion> attachedSesionSetNew = new HashSet<Sesion>();
            for (Sesion sesionSetNewSesionToAttach : sesionSetNew) {
                sesionSetNewSesionToAttach = em.getReference(sesionSetNewSesionToAttach.getClass(), sesionSetNewSesionToAttach.getIdsesion());
                attachedSesionSetNew.add(sesionSetNewSesionToAttach);
            }
            sesionSetNew = attachedSesionSetNew;
            usuario.setSesionSet(sesionSetNew);
            Set<Sesion> attachedSesionSet1New = new HashSet<Sesion>();
            for (Sesion sesionSet1NewSesionToAttach : sesionSet1New) {
                sesionSet1NewSesionToAttach = em.getReference(sesionSet1NewSesionToAttach.getClass(), sesionSet1NewSesionToAttach.getIdsesion());
                attachedSesionSet1New.add(sesionSet1NewSesionToAttach);
            }
            sesionSet1New = attachedSesionSet1New;
            usuario.setSesionSet1(sesionSet1New);
            Set<Empleado> attachedEmpleadoSetNew = new HashSet<Empleado>();
            for (Empleado empleadoSetNewEmpleadoToAttach : empleadoSetNew) {
                empleadoSetNewEmpleadoToAttach = em.getReference(empleadoSetNewEmpleadoToAttach.getClass(), empleadoSetNewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSetNew.add(empleadoSetNewEmpleadoToAttach);
            }
            empleadoSetNew = attachedEmpleadoSetNew;
            usuario.setEmpleadoSet(empleadoSetNew);
            Set<Empleado> attachedEmpleadoSet1New = new HashSet<Empleado>();
            for (Empleado empleadoSet1NewEmpleadoToAttach : empleadoSet1New) {
                empleadoSet1NewEmpleadoToAttach = em.getReference(empleadoSet1NewEmpleadoToAttach.getClass(), empleadoSet1NewEmpleadoToAttach.getIdempleado());
                attachedEmpleadoSet1New.add(empleadoSet1NewEmpleadoToAttach);
            }
            empleadoSet1New = attachedEmpleadoSet1New;
            usuario.setEmpleadoSet1(empleadoSet1New);
            Set<Comprobantepago> attachedComprobantepagoSetNew = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSetNewComprobantepagoToAttach : comprobantepagoSetNew) {
                comprobantepagoSetNewComprobantepagoToAttach = em.getReference(comprobantepagoSetNewComprobantepagoToAttach.getClass(), comprobantepagoSetNewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSetNew.add(comprobantepagoSetNewComprobantepagoToAttach);
            }
            comprobantepagoSetNew = attachedComprobantepagoSetNew;
            usuario.setComprobantepagoSet(comprobantepagoSetNew);
            Set<Comprobantepago> attachedComprobantepagoSet1New = new HashSet<Comprobantepago>();
            for (Comprobantepago comprobantepagoSet1NewComprobantepagoToAttach : comprobantepagoSet1New) {
                comprobantepagoSet1NewComprobantepagoToAttach = em.getReference(comprobantepagoSet1NewComprobantepagoToAttach.getClass(), comprobantepagoSet1NewComprobantepagoToAttach.getIdcomprobantepago());
                attachedComprobantepagoSet1New.add(comprobantepagoSet1NewComprobantepagoToAttach);
            }
            comprobantepagoSet1New = attachedComprobantepagoSet1New;
            usuario.setComprobantepagoSet1(comprobantepagoSet1New);
            usuario = em.merge(usuario);
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
            for (Cliente clienteSet1OldCliente : clienteSet1Old) {
                if (!clienteSet1New.contains(clienteSet1OldCliente)) {
                    clienteSet1OldCliente.setUsuario1(null);
                    clienteSet1OldCliente = em.merge(clienteSet1OldCliente);
                }
            }
            for (Cliente clienteSet1NewCliente : clienteSet1New) {
                if (!clienteSet1Old.contains(clienteSet1NewCliente)) {
                    Usuario oldUsuario1OfClienteSet1NewCliente = clienteSet1NewCliente.getUsuario1();
                    clienteSet1NewCliente.setUsuario1(usuario);
                    clienteSet1NewCliente = em.merge(clienteSet1NewCliente);
                    if (oldUsuario1OfClienteSet1NewCliente != null && !oldUsuario1OfClienteSet1NewCliente.equals(usuario)) {
                        oldUsuario1OfClienteSet1NewCliente.getClienteSet1().remove(clienteSet1NewCliente);
                        oldUsuario1OfClienteSet1NewCliente = em.merge(oldUsuario1OfClienteSet1NewCliente);
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
            for (Factura facturaSet1OldFactura : facturaSet1Old) {
                if (!facturaSet1New.contains(facturaSet1OldFactura)) {
                    facturaSet1OldFactura.setUsuario1(null);
                    facturaSet1OldFactura = em.merge(facturaSet1OldFactura);
                }
            }
            for (Factura facturaSet1NewFactura : facturaSet1New) {
                if (!facturaSet1Old.contains(facturaSet1NewFactura)) {
                    Usuario oldUsuario1OfFacturaSet1NewFactura = facturaSet1NewFactura.getUsuario1();
                    facturaSet1NewFactura.setUsuario1(usuario);
                    facturaSet1NewFactura = em.merge(facturaSet1NewFactura);
                    if (oldUsuario1OfFacturaSet1NewFactura != null && !oldUsuario1OfFacturaSet1NewFactura.equals(usuario)) {
                        oldUsuario1OfFacturaSet1NewFactura.getFacturaSet1().remove(facturaSet1NewFactura);
                        oldUsuario1OfFacturaSet1NewFactura = em.merge(oldUsuario1OfFacturaSet1NewFactura);
                    }
                }
            }
            for (Usuarioxrol usuarioxrolSetNewUsuarioxrol : usuarioxrolSetNew) {
                if (!usuarioxrolSetOld.contains(usuarioxrolSetNewUsuarioxrol)) {
                    Usuario oldUsuarioOfUsuarioxrolSetNewUsuarioxrol = usuarioxrolSetNewUsuarioxrol.getUsuario();
                    usuarioxrolSetNewUsuarioxrol.setUsuario(usuario);
                    usuarioxrolSetNewUsuarioxrol = em.merge(usuarioxrolSetNewUsuarioxrol);
                    if (oldUsuarioOfUsuarioxrolSetNewUsuarioxrol != null && !oldUsuarioOfUsuarioxrolSetNewUsuarioxrol.equals(usuario)) {
                        oldUsuarioOfUsuarioxrolSetNewUsuarioxrol.getUsuarioxrolSet().remove(usuarioxrolSetNewUsuarioxrol);
                        oldUsuarioOfUsuarioxrolSetNewUsuarioxrol = em.merge(oldUsuarioOfUsuarioxrolSetNewUsuarioxrol);
                    }
                }
            }
            for (Usuarioxrol usuarioxrolSet1NewUsuarioxrol : usuarioxrolSet1New) {
                if (!usuarioxrolSet1Old.contains(usuarioxrolSet1NewUsuarioxrol)) {
                    Usuario oldUsuario1OfUsuarioxrolSet1NewUsuarioxrol = usuarioxrolSet1NewUsuarioxrol.getUsuario1();
                    usuarioxrolSet1NewUsuarioxrol.setUsuario1(usuario);
                    usuarioxrolSet1NewUsuarioxrol = em.merge(usuarioxrolSet1NewUsuarioxrol);
                    if (oldUsuario1OfUsuarioxrolSet1NewUsuarioxrol != null && !oldUsuario1OfUsuarioxrolSet1NewUsuarioxrol.equals(usuario)) {
                        oldUsuario1OfUsuarioxrolSet1NewUsuarioxrol.getUsuarioxrolSet1().remove(usuarioxrolSet1NewUsuarioxrol);
                        oldUsuario1OfUsuarioxrolSet1NewUsuarioxrol = em.merge(oldUsuario1OfUsuarioxrolSet1NewUsuarioxrol);
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
            for (Sesion sesionSet1OldSesion : sesionSet1Old) {
                if (!sesionSet1New.contains(sesionSet1OldSesion)) {
                    sesionSet1OldSesion.setUsuario1(null);
                    sesionSet1OldSesion = em.merge(sesionSet1OldSesion);
                }
            }
            for (Sesion sesionSet1NewSesion : sesionSet1New) {
                if (!sesionSet1Old.contains(sesionSet1NewSesion)) {
                    Usuario oldUsuario1OfSesionSet1NewSesion = sesionSet1NewSesion.getUsuario1();
                    sesionSet1NewSesion.setUsuario1(usuario);
                    sesionSet1NewSesion = em.merge(sesionSet1NewSesion);
                    if (oldUsuario1OfSesionSet1NewSesion != null && !oldUsuario1OfSesionSet1NewSesion.equals(usuario)) {
                        oldUsuario1OfSesionSet1NewSesion.getSesionSet1().remove(sesionSet1NewSesion);
                        oldUsuario1OfSesionSet1NewSesion = em.merge(oldUsuario1OfSesionSet1NewSesion);
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
            for (Empleado empleadoSet1OldEmpleado : empleadoSet1Old) {
                if (!empleadoSet1New.contains(empleadoSet1OldEmpleado)) {
                    empleadoSet1OldEmpleado.setUsuario1(null);
                    empleadoSet1OldEmpleado = em.merge(empleadoSet1OldEmpleado);
                }
            }
            for (Empleado empleadoSet1NewEmpleado : empleadoSet1New) {
                if (!empleadoSet1Old.contains(empleadoSet1NewEmpleado)) {
                    Usuario oldUsuario1OfEmpleadoSet1NewEmpleado = empleadoSet1NewEmpleado.getUsuario1();
                    empleadoSet1NewEmpleado.setUsuario1(usuario);
                    empleadoSet1NewEmpleado = em.merge(empleadoSet1NewEmpleado);
                    if (oldUsuario1OfEmpleadoSet1NewEmpleado != null && !oldUsuario1OfEmpleadoSet1NewEmpleado.equals(usuario)) {
                        oldUsuario1OfEmpleadoSet1NewEmpleado.getEmpleadoSet1().remove(empleadoSet1NewEmpleado);
                        oldUsuario1OfEmpleadoSet1NewEmpleado = em.merge(oldUsuario1OfEmpleadoSet1NewEmpleado);
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
            for (Comprobantepago comprobantepagoSet1OldComprobantepago : comprobantepagoSet1Old) {
                if (!comprobantepagoSet1New.contains(comprobantepagoSet1OldComprobantepago)) {
                    comprobantepagoSet1OldComprobantepago.setUsuario1(null);
                    comprobantepagoSet1OldComprobantepago = em.merge(comprobantepagoSet1OldComprobantepago);
                }
            }
            for (Comprobantepago comprobantepagoSet1NewComprobantepago : comprobantepagoSet1New) {
                if (!comprobantepagoSet1Old.contains(comprobantepagoSet1NewComprobantepago)) {
                    Usuario oldUsuario1OfComprobantepagoSet1NewComprobantepago = comprobantepagoSet1NewComprobantepago.getUsuario1();
                    comprobantepagoSet1NewComprobantepago.setUsuario1(usuario);
                    comprobantepagoSet1NewComprobantepago = em.merge(comprobantepagoSet1NewComprobantepago);
                    if (oldUsuario1OfComprobantepagoSet1NewComprobantepago != null && !oldUsuario1OfComprobantepagoSet1NewComprobantepago.equals(usuario)) {
                        oldUsuario1OfComprobantepagoSet1NewComprobantepago.getComprobantepagoSet1().remove(comprobantepagoSet1NewComprobantepago);
                        oldUsuario1OfComprobantepagoSet1NewComprobantepago = em.merge(oldUsuario1OfComprobantepagoSet1NewComprobantepago);
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
            Set<Usuarioxrol> usuarioxrolSetOrphanCheck = usuario.getUsuarioxrolSet();
            for (Usuarioxrol usuarioxrolSetOrphanCheckUsuarioxrol : usuarioxrolSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Usuarioxrol " + usuarioxrolSetOrphanCheckUsuarioxrol + " in its usuarioxrolSet field has a non-nullable usuario field.");
            }
            Set<Usuarioxrol> usuarioxrolSet1OrphanCheck = usuario.getUsuarioxrolSet1();
            for (Usuarioxrol usuarioxrolSet1OrphanCheckUsuarioxrol : usuarioxrolSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Usuarioxrol " + usuarioxrolSet1OrphanCheckUsuarioxrol + " in its usuarioxrolSet1 field has a non-nullable usuario1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Cliente> clienteSet = usuario.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setUsuario(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Cliente> clienteSet1 = usuario.getClienteSet1();
            for (Cliente clienteSet1Cliente : clienteSet1) {
                clienteSet1Cliente.setUsuario1(null);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
            }
            Set<Factura> facturaSet = usuario.getFacturaSet();
            for (Factura facturaSetFactura : facturaSet) {
                facturaSetFactura.setUsuario(null);
                facturaSetFactura = em.merge(facturaSetFactura);
            }
            Set<Factura> facturaSet1 = usuario.getFacturaSet1();
            for (Factura facturaSet1Factura : facturaSet1) {
                facturaSet1Factura.setUsuario1(null);
                facturaSet1Factura = em.merge(facturaSet1Factura);
            }
            Set<Sesion> sesionSet = usuario.getSesionSet();
            for (Sesion sesionSetSesion : sesionSet) {
                sesionSetSesion.setUsuario(null);
                sesionSetSesion = em.merge(sesionSetSesion);
            }
            Set<Sesion> sesionSet1 = usuario.getSesionSet1();
            for (Sesion sesionSet1Sesion : sesionSet1) {
                sesionSet1Sesion.setUsuario1(null);
                sesionSet1Sesion = em.merge(sesionSet1Sesion);
            }
            Set<Empleado> empleadoSet = usuario.getEmpleadoSet();
            for (Empleado empleadoSetEmpleado : empleadoSet) {
                empleadoSetEmpleado.setUsuario(null);
                empleadoSetEmpleado = em.merge(empleadoSetEmpleado);
            }
            Set<Empleado> empleadoSet1 = usuario.getEmpleadoSet1();
            for (Empleado empleadoSet1Empleado : empleadoSet1) {
                empleadoSet1Empleado.setUsuario1(null);
                empleadoSet1Empleado = em.merge(empleadoSet1Empleado);
            }
            Set<Comprobantepago> comprobantepagoSet = usuario.getComprobantepagoSet();
            for (Comprobantepago comprobantepagoSetComprobantepago : comprobantepagoSet) {
                comprobantepagoSetComprobantepago.setUsuario(null);
                comprobantepagoSetComprobantepago = em.merge(comprobantepagoSetComprobantepago);
            }
            Set<Comprobantepago> comprobantepagoSet1 = usuario.getComprobantepagoSet1();
            for (Comprobantepago comprobantepagoSet1Comprobantepago : comprobantepagoSet1) {
                comprobantepagoSet1Comprobantepago.setUsuario1(null);
                comprobantepagoSet1Comprobantepago = em.merge(comprobantepagoSet1Comprobantepago);
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
