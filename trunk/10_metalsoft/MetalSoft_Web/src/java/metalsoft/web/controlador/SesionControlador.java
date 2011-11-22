/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.UsuarioJpaController;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.web.vista.SesionVista;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "sesionControlador")
@RequestScoped
public class SesionControlador {

    @ManagedProperty(value = "#{sesionVista}")
    private SesionVista sesionVista;

    /** Creates a new instance of SesionControlador */
    public SesionControlador() {
    }

    public String irAIniciarSesion() {
        if (sesionVista.getEstadoSesion().equals("Cerrar Sesión")) {
            sesionVista.cleanData();
            return "principal";
        } else if (sesionVista.getEstadoSesion().equals("Iniciar Sesión")) {
            return "iniciarSesion";
        }else{
            return null;
        }
    }

    public SesionVista getSesionVista() {
        return sesionVista;
    }

    public void setSesionVista(SesionVista sesionVista) {
        this.sesionVista = sesionVista;
    }

    public String iniciarSesion() {
        UsuarioJpaController controller = new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        List<Usuario> list = controller.findUsuarioEntities();
        if (sesionVista.getEstadoSesion().equals("Iniciar Sesión")) {
            for (Usuario user : list) {
                if (user.getUsuario().equals(sesionVista.getUsuarioIngresado()) && user.getClave().equals(sesionVista.getPasswordIngresada())) {
                    sesionVista.setUsuario(user);
                    
                    List<Rol> lstRoles = JpaUtil.getRolByUsuario(user.getIdusuario());
                    sesionVista.setRolUsuario(lstRoles);
                    
                    String usuario = sesionVista.getUsuarioIngresado();
                    sesionVista.setEstadoSesion("Cerrar Sesión");
                    sesionVista.setUsuarioIngresado("");
                    sesionVista.setPasswordIngresada("");
                    for(Rol rol : sesionVista.getRolUsuario()) {
                        if(rol.getIdrol()==8) {
                            sesionVista.setEsCliente(true);
                            sesionVista.setCliente(JpaUtil.getClienteByUsuario(user.getIdusuario()));
                            sesionVista.setMensajeDeBienvenida("Bienvenido " + sesionVista.getCliente().getRazonsocial() + " !!");
                        }
                        if(rol.getIdrol()==1) {
                            sesionVista.setEsAdministrador(true);
                            sesionVista.setMensajeDeBienvenida("Bienvenido " + usuario);
                        }
                    }
                    return "principal";
                }
            }
            sesionVista.setResultadoSesion("Ud ha ingresado un usuario o contraseña inválidas");
            return null;
        }
        return null;
    }
}
