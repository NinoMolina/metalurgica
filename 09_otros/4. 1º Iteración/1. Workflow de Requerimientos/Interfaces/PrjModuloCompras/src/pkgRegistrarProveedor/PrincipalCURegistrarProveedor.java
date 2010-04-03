/*
 * PrincipalCURegistrarProveedor.java
 *
 * Created on 8 de junio de 2008, 21:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgRegistrarProveedor;

import pkgSoporte.*;


/**
 *
 * @author Armando
 */
public class PrincipalCURegistrarProveedor {
    
    public static void main(String[] args) {
        PllaRegistrarProveedor plla=new PllaRegistrarProveedor();
        Pantalla.centrarEnPantallaJFrame(plla,4,8);
        plla.setVisible(true);
    }
    
}
