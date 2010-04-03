/*
 * PrincipalCUConsultarMedicionTanque.java
 *
 * Created on 9 de junio de 2008, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarRecepcionPedidoMinishop;
import pkgRecursosDeSoporte.Pantalla;
/**
 *
 * @author Fer
 */
public class PrincipalCURegistrarRecepcionPedidoProductosMinishop {
    
    /** Creates a new instance of PrincipalCUConsultarMedicionTanque */
    public PrincipalCURegistrarRecepcionPedidoProductosMinishop() {
    }
      public static void main(String[] args) {
 
          PllaRecepcionPedidoProductosMinishop plla=new PllaRecepcionPedidoProductosMinishop();
          Pantalla.centrarEnPantallaJFrame(plla,4,8);
          plla.setVisible(true);
      
      }
}
