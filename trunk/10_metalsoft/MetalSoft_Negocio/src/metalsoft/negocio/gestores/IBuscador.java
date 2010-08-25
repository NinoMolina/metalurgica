/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import javax.swing.JComboBox;
import javax.swing.JList;

/**
 *
 * @author Nino
 */
public interface IBuscador {
    public JList getList(String className);
    public JComboBox getCombo(String className);
    public void setBusqueda(Object[] obj);
}
