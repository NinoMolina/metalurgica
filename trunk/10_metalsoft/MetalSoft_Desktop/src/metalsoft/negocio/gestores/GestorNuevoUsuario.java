/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.jpa.controller.RolJpaController;
import metalsoft.datos.jpa.controller.UsuarioJpaController;
import metalsoft.datos.jpa.controller.UsuarioxrolJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Usuarioxrol;
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

    public void cargarComboRoles(JComboBox combo) {
        roles = null;
        RolJpaController controller = new RolJpaController();
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

    public long guardarUsuario(Usuario user, List<Rol> filasRoles) {
        UsuarioJpaController controller = new UsuarioJpaController();
        UsuarioxrolJpaController controllerUxr = new UsuarioxrolJpaController();
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
    public Usuario buscarUsuario(long id)
    {
        UsuarioJpaController controller = new UsuarioJpaController();
        return controller.findUsuario(id);
    }
}
