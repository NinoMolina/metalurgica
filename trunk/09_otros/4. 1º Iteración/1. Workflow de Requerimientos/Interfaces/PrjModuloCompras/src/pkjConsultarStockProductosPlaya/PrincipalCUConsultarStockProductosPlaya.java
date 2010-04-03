/*
 * PrincipalCUConsultarMedicionTanque.java
 *
 * Created on 9 de junio de 2008, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkjConsultarStockProductosPlaya;

import pkgSoporte.Pantalla;

/**
 *
 * @author Fer
 */
public class PrincipalCUConsultarStockProductosPlaya {
    
    /** Creates a new instance of PrincipalCUConsultarMedicionTanque */
    public PrincipalCUConsultarStockProductosPlaya() {
    }
      public static void main(String[] args) {
  
          
          PllaConsultarProductosPlaya plla=new PllaConsultarProductosPlaya();
          Pantalla.centrarEnPantallaJFrame(plla);
          plla.setVisible(true);
      
      }
}
