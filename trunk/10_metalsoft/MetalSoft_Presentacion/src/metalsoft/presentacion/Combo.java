/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import javax.swing.JComboBox;
import metalsoft.negocio.ItemCombo;

/**
 *
 * @author Nino
 */
public class Combo {

    public static void cargarCombo(JComboBox combo, ItemCombo[] items)
    {
        for(int i=0;i<items.length;i++)
            combo.addItem(items[i]);
    }
}
