/*
 * PrincipalCUConsultarMedicionTanque.java
 *
 * Created on 9 de junio de 2008, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarRecepcionPedidoProductosPlaya;

import pkgRecursosDeSoporte.Pantalla;



/**
 *
 * @author Fer
 */
public class PrincipalCURegistrarRecepcionPedidoProductosPlaya {
    
    /** Creates a new instance of PrincipalCUConsultarMedicionTanque */
    public PrincipalCURegistrarRecepcionPedidoProductosPlaya() {
    }
      public static void main(String[] args) {
 
       PllaRecepcionPedidoProductosPlaya plla=new PllaRecepcionPedidoProductosPlaya();
       Pantalla.centrarEnPantallaJFrame(plla,4,8);
       plla.setVisible(true);
      
      }
}
