/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.presentacion;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import metalsoft.presentacion.lookandfeel.LookAndFeelManager;
import metalsoft.presentacion.lookandfeel.Skins;

/**
 *
 * @author Vicky
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                LookAndFeelManager.setLookAndFeel(Skins.Moderate);
                AbrirSesion p = new AbrirSesion();
                p.setVisible(true);
                p.setLocationRelativeTo(null);
//        try {
//          UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
//        } catch (Exception e) {
//          System.out.println("Substance Graphite failed to initialize");
//        }
//        Walkthrough w = new Walkthrough();
//        w.setVisible(true);
            }
        });
    }
}
