/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import metalsoft.web.vista.ContactoView;

/**
 *
 * @author Vicky
 */
@ManagedBean(name="contactoControlador")
@RequestScoped
public class ContactoControlador {

    @ManagedProperty(value="#{contactView}")
    private ContactoView contactoView;
    /** Creates a new instance of contactoControlador */
    public ContactoControlador() {
    }

    public ContactoView getContactoView() {
        return contactoView;
    }

    public void setContactoView(ContactoView contactoView) {
        this.contactoView = contactoView;
    }
    
    public String irAContacto(){
        return "contacto";
    }
    
    public String enviarConsulta(){
        if(validarEnvio()){
            
            contactoView.setMensajeEnviado("Su consulta ha sido enviada");
            return null;
        }else{
            return null;
        }
    }
    
    public boolean validarEnvio(){
        if(contactoView.getNombreContacto().equals("")){
            contactoView.setMensajeEnviado("Debe ingresar su nombre");
            return false;
        }
        if(contactoView.getMail().equals("")){
            contactoView.setMensajeEnviado("Debe ingresar su mail");
            return false;
        }
        if(contactoView.getTelefono().equals("")){
            contactoView.setMensajeEnviado("Debe ingresar su telefono");
            return false;
        }
        if(contactoView.getDescripcion().equals("")){
            contactoView.setMensajeEnviado("Debe ingresar su Consulta");
            return false;
        }
        return true;
    }
    
}
