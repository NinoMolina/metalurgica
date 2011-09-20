/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "contactView")
@SessionScoped
public class ContactoView {

    private String nombreContacto = "";
    private String mail = "";
    private String telefono = "";
    private String descripcion = "";
    private String mensajeEnviado="";

    /** Creates a new instance of contactoView */
    public ContactoView() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensajeEnviado() {
        return mensajeEnviado;
    }

    public void setMensajeEnviado(String mensajeEnviado) {
        this.mensajeEnviado = mensajeEnviado;
    }

    public void limpiarCampos() {
        nombreContacto = "";
        mail = "";
        telefono = "";
        descripcion = "";
        mensajeEnviado="";
    }
}
