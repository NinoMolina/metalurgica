/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.negocio.adminusuarios.Rol;

/**
 *
 * @author Nino
 */
public class GestorRol {

    public Rol[] obtenerRoles(long idUsuario)
    {
        GestorRolDB gdb=new GestorRolDB();
        return gdb.obtenerRoles(idUsuario);
    }
}
