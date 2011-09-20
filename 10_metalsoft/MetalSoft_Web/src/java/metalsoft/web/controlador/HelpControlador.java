/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import metalsoft.web.vista.HelpVista;

/**
 *
 * @author Vicky
 */
@ManagedBean(name="helpControlador")
@RequestScoped
public class HelpControlador {
    
    @ManagedProperty(value="#{helpVista}")
    private HelpVista helpVista;

    /** Creates a new instance of HelpControlador */
    public HelpControlador() {
    }

    public HelpVista getHelpVista() {
        return helpVista;
    }

    public void setHelpVista(HelpVista helpVista) {
        this.helpVista = helpVista;
    }
    
    public String irAHelp(){
        return "help";
    }
}
