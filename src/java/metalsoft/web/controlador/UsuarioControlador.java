package metalsoft.web.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import metalsoft.web.vista.UsuarioVista;




@ManagedBean(name="usuarioControlador")
@RequestScoped
public class UsuarioControlador{
    
    @ManagedProperty(value="#{usuarioVista}")
    private UsuarioVista usuarioVista;

    public UsuarioControlador(){
        
    }
    
    public String hacerAlgo(){
       return "usuario"; 
    }

    public UsuarioVista getUsuarioVista() {
        return usuarioVista;
    }

    public void setUsuarioVista(UsuarioVista usuarioVista) {
        this.usuarioVista = usuarioVista;
    }
    
}