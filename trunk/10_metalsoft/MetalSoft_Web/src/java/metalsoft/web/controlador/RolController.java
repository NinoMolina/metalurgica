/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.RolJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.web.vista.RolVista;

/**
 *
 * @author Vicky
 */
@ManagedBean(name="rolController")
@RequestScoped
public class RolController {

    @ManagedProperty(value="#{rolVista}")
    private RolVista rolVista;

    public RolVista getRolVista() {
        return rolVista;
    }

    public void setRolVista(RolVista rol) {
        this.rolVista = rol;
    }
    /** Creates a new instance of RolController */
    public RolController() {
    }
    
    public String irAIndex(){
               
        return "principal";
    }
    
    public String guardar(){
//        try {
//            
//            RolJpaController con=new RolJpaController(JpaUtil.getEntityManagerFactory());
//            con.create(rolVista.getRol());
//            
//            rolVista.limpiarCampos();
//            
//            rolVista.setMensaje("Los Datos se guardaron correctamente.");
//            
//        } catch (Exception ex) {
//            rolVista.setMensaje("Los datos no se pudieron guardar");
//            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        rolVista.setMensaje("Los datos no se pudieron guardar");
        
        return null;
    }
}
