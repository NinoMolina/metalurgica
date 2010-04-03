/*
 * PrincipalCUConsultarProveedor.java
 *
 * Created on 9 de junio de 2008, 0:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgConsultarProveedor;

import pkgSoporte.Pantalla;

/**
 *
 * @author Armando
 */
public class PrincipalCUConsultarProveedor {
    
   public static void main(String[] args) {
       PllaConsultarProveedor plla=new PllaConsultarProveedor();
       Pantalla.centrarEnPantallaJFrame(plla);
       plla.setVisible(true);
    }
    
}
