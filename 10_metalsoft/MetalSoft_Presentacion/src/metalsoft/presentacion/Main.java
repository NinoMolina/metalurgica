/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import metalsoft.presentacion.lookandfeel.LookAndFeelManager;
import metalsoft.presentacion.lookandfeel.Temas;


/**
 *
 * @author Vicky
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LookAndFeelManager.setLookAndFeel(Temas.WINDOWS);
        AbrirSesion p=new AbrirSesion();
        p.setVisible(true);
        p.setLocationRelativeTo(null);
        
    }

}
