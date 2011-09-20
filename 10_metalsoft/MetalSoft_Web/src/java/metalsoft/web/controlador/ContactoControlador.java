/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import metalsoft.web.vista.ContactoView;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "contactoControlador")
@RequestScoped
public class ContactoControlador {

    @ManagedProperty(value = "#{contactView}")
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

    public String irAContacto() {
        return "contacto";
    }

    public String enviarConsulta() {
        if (validarEnvio()) {
            try {
                Properties props = new Properties();

                // Nombre del host de correo, es smtp.gmail.com
                props.setProperty("mail.smtp.host", "smtp.gmail.com");

                // TLS si está disponible
                props.setProperty("mail.smtp.starttls.enable", "true");

                // Puerto de gmail para envio de correos
                props.setProperty("mail.smtp.port", "587");

                // Nombre del usuario
                props.setProperty("mail.smtp.user", contactoView.getMail());

                // Si requiere o no usuario y password para conectarse.
                props.setProperty("mail.smtp.auth", "true");

                Session session = Session.getDefaultInstance(props);
                session.setDebug(true);

                MimeMessage message = new MimeMessage(session);

                // Quien envia el correo
                message.setFrom(new InternetAddress(contactoView.getMail()));

                // A quien va dirigido
                message.addRecipient(Message.RecipientType.TO, new InternetAddress("canovasybarale@gmail.com"));

                message.setSubject("Consulta WEB");
                message.setText("Teléfono del contacto: " + contactoView.getTelefono() + "\n\n"
                        + "Descripción de la consulta: \n"
                        + contactoView.getDescripcion()+
                        "\n\n Email del usuario:\n"
                        +contactoView.getMail());
                Transport t = session.getTransport("smtp");
                t.connect("canovasybarale@gmail.com","canovasybarale2011");
                t.sendMessage(message,message.getAllRecipients());
                
                t.close();
                
                contactoView.setMensajeEnviado("Su consulta ha sido enviada");
                contactoView.setMostrarMensaje(true);
                
            } catch (MessagingException ex) {
                contactoView.setMensajeEnviado("Su consulta NO ha podido ser enviada");
                contactoView.setMostrarMensaje(true);
                Logger.getLogger(ContactoControlador.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            contactoView.limpiarCampos();
            return null;
        } else {
            contactoView.setMostrarMensaje(true);
            return null;
        }
    }

    public boolean validarEnvio() {
        if (contactoView.getNombreContacto().equals("")) {
            contactoView.setMensajeEnviado("Debe ingresar su nombre");
            contactoView.setMostrarMensaje(true);
            return false;
        }
        if (contactoView.getMail().equals("")) {
            contactoView.setMensajeEnviado("Debe ingresar su mail");
            contactoView.setMostrarMensaje(true);
            return false;
        }
        if (contactoView.getTelefono().equals("")) {
            contactoView.setMensajeEnviado("Debe ingresar su telefono");
            contactoView.setMostrarMensaje(true);
            return false;
        }
        if (contactoView.getDescripcion().equals("")) {
            contactoView.setMensajeEnviado("Debe ingresar su Consulta");
            contactoView.setMostrarMensaje(true);
            return false;
        }
        return true;
    }
    
    public String cerrarMensaje(){
        contactoView.setMostrarMensaje(false);
        return null;
    }
}
