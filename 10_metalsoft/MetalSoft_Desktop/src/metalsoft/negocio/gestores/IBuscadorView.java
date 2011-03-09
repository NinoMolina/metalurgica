/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.LinkedList;
import javax.swing.JTable;

/**
 *
 * @author Nino
 */
public interface IBuscadorView {

    public JTable getTable(String className);
    public LinkedList getFilas(String className);
}
