/*
 * PrincipalCUEliminarProveedor.java
 *
 * Created on 9 de junio de 2008, 0:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgEliminarProveedor;

import pkgSoporte.Pantalla;

/**
 *
 * @author Armando
 */
public class PrincipalCUEliminarProveedor {
    
     public static void main(String[] args) {
       PllaEliminarProveedor plla=new PllaEliminarProveedor();
       Pantalla.centrarEnPantallaJFrame(plla);
       plla.setVisible(true);
    }
    
}
