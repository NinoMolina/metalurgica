/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.vista;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import metalsoft.datos.jpa.entity.Cliente;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Usuario;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "sesionVista")
@SessionScoped
public class SesionVista {

    private String estadoSesion = "Iniciar Sesion";
    private Usuario usuario;
    private List<Rol> rolUsuario;
    private String usuarioIngresado;
    private String passwordIngresada;
    private String resultadoSesion = "";
    private String mensajeDeBienvenida = "";
    private Cliente cliente;
    private boolean esAdministrador=false;
    private boolean esCliente=false;

    public SesionVista() {
        usuario = new Usuario();
        cliente=new Cliente();
    }

    public String getEstadoSesion() {
        return estadoSesion;
    }

    public void setEstadoSesion(String estadoSesion) {
        this.estadoSesion = estadoSesion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPasswordIngresada() {
        return passwordIngresada;
    }

    public void setPasswordIngresada(String passwordIngresada) {
        this.passwordIngresada = passwordIngresada;
    }

    public String getUsuarioIngresado() {
        return usuarioIngresado;
    }

    public void setUsuarioIngresado(String usuarioIngresado) {
        this.usuarioIngresado = usuarioIngresado;
    }

    public String getResultadoSesion() {
        return resultadoSesion;
    }

    public void setResultadoSesion(String resultadoSesion) {
        this.resultadoSesion = resultadoSesion;
    }

    public String getMensajeDeBienvenida() {
        return mensajeDeBienvenida;
    }

    public void setMensajeDeBienvenida(String mensajeDeBienvenida) {
        this.mensajeDeBienvenida = mensajeDeBienvenida;
    }

    public List<Rol> getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(List<Rol> rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public boolean isEsCliente() {
        return esCliente;
    }

    public void setEsCliente(boolean esCliente) {
        this.esCliente = esCliente;
    }

    public void cleanData() {
        estadoSesion = "Iniciar Sesion";
        usuario=new Usuario();
        usuarioIngresado="";
        passwordIngresada="";
        resultadoSesion = "";
        mensajeDeBienvenida = "";
        rolUsuario=new LinkedList<Rol> ();
        cliente=new Cliente();
        esAdministrador=false;
        esCliente=false;
    }
}
