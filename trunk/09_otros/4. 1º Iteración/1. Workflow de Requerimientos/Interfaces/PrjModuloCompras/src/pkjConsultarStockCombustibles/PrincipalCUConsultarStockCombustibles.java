/*
 * PrincipalCUConsultarMedicionTanque.java
 *
 * Created on 9 de junio de 2008, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkjConsultarStockCombustibles;

import pkgSoporte.Pantalla;

/**
 *
 * @author Fer
 */
public class PrincipalCUConsultarStockCombustibles {
    
    
     public static void main(String[] args) {
      PllaConsultarStockCombustible plla=new PllaConsultarStockCombustible();
      Pantalla.centrarEnPantallaJFrame(plla);
 
      plla.setVisible(true);
      
     }
}
