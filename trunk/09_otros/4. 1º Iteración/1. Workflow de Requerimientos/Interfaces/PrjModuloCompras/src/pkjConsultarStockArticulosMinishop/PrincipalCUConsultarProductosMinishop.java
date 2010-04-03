/*
 * PrincipalCUConsultarMedicionTanque.java
 *
 * Created on 9 de junio de 2008, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkjConsultarStockArticulosMinishop;

import pkgSoporte.Pantalla;

/**
 *
 * @author Fer
 */
public class PrincipalCUConsultarProductosMinishop {
    
    /** Creates a new instance of PrincipalCUConsultarMedicionTanque */
    public PrincipalCUConsultarProductosMinishop() {
    }
      public static void main(String[] args) {
    
          
      PllaConsultarProductosMinishop plla=new PllaConsultarProductosMinishop();
      Pantalla.centrarEnPantallaJFrame(plla);
      plla.setVisible(true);
      
      }
}
