/*
 * PrincipalCUModificarPedidoProveedor.java
 *
 * Created on 8 de junio de 2008, 23:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgModificarPedidoProveedor;

import pkgSoporte.Pantalla;

/**
 *
 * @author Armando
 */
public class PrincipalCUModificarPedidoProveedor {
    
     public static void main(String[] args) {
        PllaModificarPedidoProveedor plla=new PllaModificarPedidoProveedor();
        Pantalla.centrarEnPantallaJFrame(plla);
        plla.setVisible(true);
    }
    
}
