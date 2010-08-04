/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import javax.swing.JList;

/**
 *
 * @author Nino
 */
public interface IBuscador {
    public JList getList();
    public void setBusqueda(Object[] obj);
}
