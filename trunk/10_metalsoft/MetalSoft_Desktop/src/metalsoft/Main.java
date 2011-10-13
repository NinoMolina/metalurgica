/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import metalsoft.presentacion.AbrirSesion;
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
//                JDialog.setDefaultLookAndFeelDecorated(true);
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
    
    public static void ejecutarAyuda(String ruta){
        Runtime run = Runtime.getRuntime();
        Process pro = null;
        try {
            pro = run.exec("hh.exe " + ruta);
            }
        catch (Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }

        }
}
