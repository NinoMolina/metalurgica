/*
 * PrincipalCUCancelarPedidoProveedor.java
 *
 * Created on 8 de junio de 2008, 20:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgCancelarPedidoProveedor;

import pkgSoporte.Pantalla;
/**
 *
 * @author Armando
 */
public class PrincipalCUCancelarPedidoProveedor {
    
    public static void main(String[] args) {
        PllaCancelarPedidoProveedor plla=new PllaCancelarPedidoProveedor();
        Pantalla.centrarEnPantallaJFrame(plla);
        Pantalla.insertarIcono(plla,"D:/Facultad/5AñoK1/ProyectoFinal/Iteracion1/4. 1º Iteración/Interfaces/PrjModuloCompras/Imagenes/logoestacion.gif");
        plla.setVisible(true);
    }
    
}
