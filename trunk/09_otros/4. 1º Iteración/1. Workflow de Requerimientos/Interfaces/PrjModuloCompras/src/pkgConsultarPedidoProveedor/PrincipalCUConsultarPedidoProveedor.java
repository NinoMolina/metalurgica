/*
 * PrincipalCUConsultarPedidoProveedor.java
 *
 * Created on 8 de junio de 2008, 21:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgConsultarPedidoProveedor;

import pkgSoporte.Pantalla;

/**
 *
 * @author Armando
 */
public class PrincipalCUConsultarPedidoProveedor {
    
   public static void main(String[] args) {
        PllaConsultarPedidoProveedor plla=new PllaConsultarPedidoProveedor();
        Pantalla.centrarEnPantallaJFrame(plla);
       
        plla.setVisible(true);
    }
    
}
