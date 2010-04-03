/*
 * PrincipalCUEliminarProducto.java
 *
 * Created on 9 de junio de 2008, 01:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgEliminarProducto;
import pkgSoporte.Pantalla;
/**
 *
 * @author Diego
 */
public class PrincipalCUEliminarProducto {
    
    /** Creates a new instance of PrincipalCUEliminarProducto */
    public static void main(String[] args) {
        // TODO code application logic here
        PllaEliminarProducto pa = new PllaEliminarProducto();
        Pantalla.centrarEnPantallaJFrame(pa);
        pa.setVisible(true);
    }
    
}
