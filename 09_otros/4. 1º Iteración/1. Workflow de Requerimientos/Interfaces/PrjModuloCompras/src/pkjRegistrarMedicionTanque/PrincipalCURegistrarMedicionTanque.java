/*
 * PrincipalCUConsultarMedicionTanque.java
 *
 * Created on 9 de junio de 2008, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkjRegistrarMedicionTanque;

import pkgSoporte.Pantalla;

/**
 *
 * @author Fer
 */
public class PrincipalCURegistrarMedicionTanque {
    
    /** Creates a new instance of PrincipalCUConsultarMedicionTanque */
    public PrincipalCURegistrarMedicionTanque() {
    }
      public static void main(String[] args) {
   
          PllaRegistrarMedicionTanque plla=new PllaRegistrarMedicionTanque();
          Pantalla.centrarEnPantallaJFrame(plla,3,4);
   
      plla.setVisible(true);
      
      }
}
