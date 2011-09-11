package metalsoft.web.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.UsuarioJpaController;
import metalsoft.datos.jpa.entity.Usuario;


@ManagedBean(name = "usuarioVista")
@SessionScoped
public class UsuarioVista{
    
    private Usuario usuario;
    private String algo;
    
    public UsuarioVista(){

    }

    public Usuario getUsuario() {
        UsuarioJpaController controller = new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        usuario = controller.findUsuario(1L);
        System.out.println(usuario.getUsuario());
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAlgo() {
        return "algo";
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }
    
    
}
