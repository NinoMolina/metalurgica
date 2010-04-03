/*
 * PrincipalCURegistrarTipoProveedor.java
 *
 * Created on 6 de junio de 2008, 13:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgRegistrarTipoProveedor;

import pkgSoporte.Pantalla;

/**
 *
 * @author usuario
 */
public class PrincipalCURegistrarTipoProveedor {
    
    /** Creates a new instance of PrincipalCURegistrarTipoProveedor */
     public static void main(String[] args) {
        // TODO code application logic here
        PllaRegistrarTipoProveedor pa = new PllaRegistrarTipoProveedor();
        Pantalla.centrarEnPantallaJFrame(pa,3,3);
        pa.setVisible(true);
        
    }
    
}
