/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.RolJpaController;
import metalsoft.datos.jpa.entity.Rol;

/**
 *
 * @author Vicky
 */
@ManagedBean(name="rolVista")
@SessionScoped
public class RolVista {

    private Rol rol;
    private String mensaje;
    
        /** Creates a new instance of NewJSFManagedBean */
    public RolVista() {
        rol=new Rol();
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    


    public void limpiarCampos() {
        
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    

    

}
