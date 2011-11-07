/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

import javax.swing.JComboBox;
import metalsoft.util.ItemCombo;

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
    public static void cargarCombo(JComboBox combo, String id, String mostrar)
    {
        ItemCombo item=new ItemCombo(id,mostrar);
        combo.addItem(item);
    }
    public static void setItemComboSeleccionado(JComboBox cmb, long id) {
        int length=cmb.getItemCount();
        ItemCombo item=null;
        for(int i=0;i<length;i++)
        {
            item=(ItemCombo)cmb.getItemAt(i);
            long x=Long.parseLong(item.getId());
            if(x==id)
            {
                cmb.setSelectedIndex(i);
                break;
            }
        }
    }
}
