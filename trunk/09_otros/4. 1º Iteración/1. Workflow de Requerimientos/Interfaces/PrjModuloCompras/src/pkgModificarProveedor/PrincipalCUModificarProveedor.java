/*
 * PrincipalCUModificarProveedor.java
 *
 * Created on 9 de junio de 2008, 0:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgModificarProveedor;

import pkgSoporte.Pantalla;

/**
 *
 * @author Armando
 */
public class PrincipalCUModificarProveedor {
    
    public static void main(String[] args) {
        PllaModificarProveedor plla=new PllaModificarProveedor();
        Pantalla.centrarEnPantallaJFrame(plla);
        plla.setVisible(true);
    }
    
}
