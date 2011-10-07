/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.RolJpaController;
import metalsoft.datos.jpa.controller.UsuarioJpaController;
import metalsoft.datos.jpa.controller.UsuarioxrolJpaController;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Usuarioxrol;
import metalsoft.datos.jpa.entity.UsuarioxrolPK;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Nino
 */
public class GestorNuevoUsuario {

    private List<Rol> roles;

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Rol getRolSeleccionado(String id) {
        long idLong = Long.parseLong(id);
        return searchRolById(idLong);
    }

    private Rol searchRolById(long id) {
        for (Rol rol : roles) {
            if (rol.getIdrol() == id) {
                return rol;
            }
        }
        return null;
    }

    public void cargarComboRoles(JComboBox combo, long idUsuario) {
        roles = null;
        roles = JpaUtil.getRolesNoTieneUsuario(idUsuario);
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Rol rol : roles) {
            item = new ItemCombo();
            item.setId(String.valueOf(rol.getIdrol()));
            item.setMostrar(rol.getRol());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }
    public void cargarComboRoles(JComboBox combo) {
        roles = null;
        RolJpaController controller = new RolJpaController(JpaUtil.getEntityManagerFactory());
        roles = controller.findRolEntities();
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Rol rol : roles) {
            item = new ItemCombo();
            item.setId(String.valueOf(rol.getIdrol()));
            item.setMostrar(rol.getRol());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }

    public void cargarComboUsuarios(JComboBox combo) {
        List<Usuario> usuarios = null;
        UsuarioJpaController controller = new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        usuarios = controller.findUsuarioEntities();
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Usuario user : usuarios) {
            item = new ItemCombo();
            item.setId(String.valueOf(user.getIdusuario()));
            item.setMostrar(user.getUsuario());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }

    public long guardarUsuario(Usuario user, List<Rol> filasRoles) {
        UsuarioJpaController controller = new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        UsuarioxrolJpaController controllerUxr = new UsuarioxrolJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controller.create(user);
            List<Usuarioxrol> uxrList = new ArrayList<Usuarioxrol>();
            for (Rol rol : filasRoles) {
                Usuarioxrol uxr = new Usuarioxrol();
                uxr.setRol(rol);
                uxr.setUsuario(user);
                controllerUxr.create(uxr);
                uxrList.add(uxr);
            }
            user.setUsuarioxrolList(uxrList);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user.getIdusuario();
    }

    public long modificarUsuarioXRol(Usuario user, List<Rol> filasRoles) {
        UsuarioJpaController controller = new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        UsuarioxrolJpaController controllerUxr = new UsuarioxrolJpaController(JpaUtil.getEntityManagerFactory());
        try {
            List<Usuarioxrol> uxrList = new ArrayList<Usuarioxrol>();
            for (Rol rol : filasRoles) {
                Usuarioxrol uxr = new Usuarioxrol();
                uxr.setRol(rol);
                uxr.setUsuario(user);
                if (!existeUsuarioXRol(uxr)) {
                    controllerUxr.create(uxr);
                    uxrList.add(uxr);
                }
            }
            user.setUsuarioxrolList(uxrList);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user.getIdusuario();
    }
    public long eliminarUsuarioXRol(Usuario user, List<Rol> filasRoles) {
        Boolean ban=false;
        UsuarioxrolJpaController controllerUxr = new UsuarioxrolJpaController(JpaUtil.getEntityManagerFactory());
        try {
            List<Usuarioxrol> uxrList = new ArrayList<Usuarioxrol>();
            for (Rol rol : filasRoles) {
                Usuarioxrol uxr = new Usuarioxrol();
                uxr.setRol(rol);
                uxr.setUsuario(user);
                if (existeUsuarioXRol(uxr)) {
                    UsuarioxrolPK pk = new UsuarioxrolPK(uxr.getRol().getIdrol(), uxr.getUsuario().getIdusuario());
                    controllerUxr.destroy(pk);
                    ban=uxrList.remove(uxr);
                }
            }
            user.setUsuarioxrolList(uxrList);
        } catch (Exception ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user.getIdusuario();
    }
    public boolean existeUsuarioXRol(Usuarioxrol uxr) {
        UsuarioxrolJpaController controllerUxr = new UsuarioxrolJpaController(JpaUtil.getEntityManagerFactory());
        UsuarioxrolPK pk = new UsuarioxrolPK(uxr.getRol().getIdrol(), uxr.getUsuario().getIdusuario());
        Usuarioxrol uxrol = controllerUxr.findUsuarioxrol(pk);
        if (uxrol != null) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario buscarUsuario(long id) {
        UsuarioJpaController controller = new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findUsuario(id);
    }

    public long modificarUsuario(Usuario user) {
        UsuarioJpaController controller = new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controller.edit(user);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user.getIdusuario();
    }

    public List<Rol> buscarRolesUsuario(Usuario user) {
        List<Usuarioxrol> list = JpaUtil.getUsuarioXRolByUsuario(user.getIdusuario());
        RolJpaController con = new RolJpaController(JpaUtil.getEntityManagerFactory());
        List<Rol> rolList = new LinkedList<Rol>();
        for (Usuarioxrol ur : list) {
            rolList.add(con.findRol(ur.getRol().getIdrol()));
        }
        return rolList;
    }

    public Rol buscarRol(long id) {
        RolJpaController con = new RolJpaController(JpaUtil.getEntityManagerFactory());
        return con.findRol(id);
    }

    public Usuario getusuarioByDatos(String nombre, String apellido, String tipoDocumento, String documento) {
//         return JpaUtil.getUsuarioByDatos(nombre, apellido, tipoDocumento, documento);
        return null;
    }
}
