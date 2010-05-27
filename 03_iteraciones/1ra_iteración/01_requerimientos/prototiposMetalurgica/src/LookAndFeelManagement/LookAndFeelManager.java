/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LookAndFeelManagement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author Nino
 */
public class LookAndFeelManager {

    @SuppressWarnings("empty-statement")
    public static void setLookAndFeel(Temas t)
    {
        try
        {
            //seleccion del tema visual
            switch(t)
            {
                case EASYNTH: UIManager.setLookAndFeel(Temas.getEaSynth());
                        break;
                case IDW: UIManager.setLookAndFeel(Temas.getIdw());
                        break;
                case METAL: UIManager.setLookAndFeel(Temas.getMetal());
                        break;
                case MOTIF: UIManager.setLookAndFeel(Temas.getMotif());
                        break;
                case MULTI: UIManager.setLookAndFeel(Temas.getMulti());
                        break;
                case NIMROD: UIManager.setLookAndFeel(Temas.getNimROD());
                        break;
                case QUAQUA: UIManager.setLookAndFeel(Temas.getQuaqua());
                        break;
                case SYNTH: UIManager.setLookAndFeel(Temas.getSynth());
                        break;
                case SYNTHETICA: UIManager.setLookAndFeel(Temas.getSynthetica());
                        break;
                case WINDOWS: UIManager.setLookAndFeel(Temas.getWindows());
                        break;
                case WINDOWSCLASSIC: UIManager.setLookAndFeel(Temas.getWindowsClassic());
                        break;
                default: UIManager.setLookAndFeel(Temas.getMetal());
                        break;
            }
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "LookAndFeel", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void centrarVentana(JFrame v)
    {
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }
}
