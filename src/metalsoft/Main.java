/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import java.awt.Color;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import metalsoft.presentacion.AbrirSesion;
import metalsoft.presentacion.lookandfeel.LookAndFeelManager;
import metalsoft.presentacion.lookandfeel.Skins;
import metalsoft.util.MetalsoftProperties;

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
                UIManager.put("Button.background", Color.getHSBColor(120, 140, 115));
                UIManager.put("JXTable.background", Color.getHSBColor(120, 140, 115));
//                UIManager.put("Button.foreground", Color.white);
//                Font f = new Font("Serif", Font.ITALIC, 24);
//                UIManager.put("Button.font", f);
                JFrame.setDefaultLookAndFeelDecorated(true);
                LookAndFeelManager.setLookAndFeel(Skins.Moderate);

                TimeZone timeZone = TimeZone.getTimeZone(MetalsoftProperties.getProperty(MetalsoftProperties.TIMEZONE));
                TimeZone.setDefault(timeZone);

                AbrirSesion p = new AbrirSesion();
                p.setVisible(true);
                p.setLocationRelativeTo(null);
            }
        });
    }

    public static void ejecutarAyuda(String ruta) {
        Runtime run = Runtime.getRuntime();
        Process pro = null;
        try {
            pro = run.exec("hh.exe " + ruta);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
