/*
 * PrincipalCUConsultarMedicionTanque.java
 *
 * Created on 9 de junio de 2008, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkjRegistrarRecepcionPedidoCombustibles;

import pkgSoporte.Pantalla;

/**
 *
 * @author Fer
 */
public class PrincipalCURegistrarRecepcionPedidoCombustible {
    
    /** Creates a new instance of PrincipalCUConsultarMedicionTanque */
    public PrincipalCURegistrarRecepcionPedidoCombustible() {
    }
      public static void main(String[] args) {
  
          PllaRecepcionPedidoCombustiblePrincipal plla=new PllaRecepcionPedidoCombustiblePrincipal();
          Pantalla.centrarEnPantallaJFrame(plla);
          plla.setVisible(true);
      
      }
}
