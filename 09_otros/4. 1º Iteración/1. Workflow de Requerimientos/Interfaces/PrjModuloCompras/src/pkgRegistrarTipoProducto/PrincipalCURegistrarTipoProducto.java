/*
 * principalCURegistrarTipoProducto.java
 *
 * Created on 8 de junio de 2008, 16:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgRegistrarTipoProducto;

import pkgSoporte.Pantalla;

/**
 *
 * @author Diego
 */
public class PrincipalCURegistrarTipoProducto {
    
    /** Creates a new instance of principalCURegistrarTipoProducto */
   public static void main(String[] args) {
        // TODO code application logic here
        PllaRegistrarTipoProducto pa = new PllaRegistrarTipoProducto();
        Pantalla.centrarEnPantallaJFrame(pa,3,4);
        pa.setVisible(true);
    }
    
}
