/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.negocio.adminusuarios.Usuario;

/**
 *
 * @author Administrador
 */
public class GestorIniciarSesion {

    private String user;
    private String pass;

    public GestorIniciarSesion()
    {}

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public metalsoft.datos.dbobject.UsuarioDB buscarUsuario()
    {
        GestorIniciarSesionDB gdb=new GestorIniciarSesionDB();
        return gdb.esUsuario(user, pass);
    }
    
    public metalsoft.datos.dbobject.UsuarioDB buscarUsuario(String user, String pass)
    {
        GestorIniciarSesionDB gdb=new GestorIniciarSesionDB();
        return gdb.esUsuario(user, pass);
    }


}
