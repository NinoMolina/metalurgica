/*
 * PrincipalCURegistrarPedidoProveedor.java
 *
 * Created on 7 de junio de 2008, 19:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgRegistrarPedidoProveedor;

import pkgSoporte.Pantalla;

/**
 *
 * @author Armando
 */
public class PrincipalCURegistrarPedidoProveedor {
    
     public static void main(String[] args) {
        PllaPrincipalRegistrarPedido plla=new PllaPrincipalRegistrarPedido();
        Pantalla.centrarEnPantallaJFrame(plla,4,8);
        plla.setVisible(true);
    }
}
