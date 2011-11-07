/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.presentacion;

import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;

/**
 *
 * @author Nino
 */
public class JFrameManager {

    public static void centrarVentana(Dialog v) {
        v.setModal(true);
        v.setLocationRelativeTo(Principal.getVtnPrincipal());
    }

    public static Window crearVentana(String clase) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Dialog v = (Dialog) Class.forName(clase).newInstance();
        centrarVentana(v);
        mostrarVentana(v);
        return v;
    }

    public static Window centrarYMostrarVentana(JDialog v) {
        centrarVentana(v);
        mostrarVentana(v);
        return v;
    }

    public static void mostrarVentana(Window v) {
        v.setVisible(true);
    }
}
