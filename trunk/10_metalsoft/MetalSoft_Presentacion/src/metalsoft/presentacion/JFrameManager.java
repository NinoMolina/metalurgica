/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JFrame;

/**
 *
 * @author Nino
 */
public class JFrameManager {

    public static void centrarVentana(JFrame v)
    {
        v.setLocationRelativeTo(null);
    }
    public static void crearVentana(String clase) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        //JOptionPane.showMessageDialog(null, Principal.class.getName());
        JFrame v=(JFrame) Class.forName(clase).newInstance();
        mostrarVentana(v);
        centrarVentana(v);
    }
    public static void mostrarVentana(JFrame v)
    {
        v.setVisible(true);
    }
}
