/*
 * PrincipalCUModificarProducto.java
 *
 * Created on 9 de junio de 2008, 01:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgModificarProducto;

import pkgSoporte.Pantalla;

/**
 *
 * @author Diego
 */
public class PrincipalCUModificarProducto {
    
    /** Creates a new instance of PrincipalCUModificarProducto */
    public static void main(String[] args) {
        // TODO code application logic here
        PllaModificarProducto pa = new PllaModificarProducto();
        Pantalla.centrarEnPantallaJFrame(pa);
        pa.setVisible(true);
    }
    
}
