/*
 * PrincipalCUConsultarProducto.java
 *
 * Created on 9 de junio de 2008, 00:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgConsultarProducto;

import pkgSoporte.Pantalla;

/**
 *
 * @author Diego
 */
public class PrincipalCUConsultarProducto {
    
    /** Creates a new instance of PrincipalCUConsultarProducto */
    public static void main(String[] args) {
        // TODO code application logic here
        PllaConsultarProducto pa = new PllaConsultarProducto();
        Pantalla.centrarEnPantallaJFrame(pa);
        pa.setVisible(true);
    }
    
}
