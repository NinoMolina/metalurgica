/*
 * PrincipalCUConsultarMedicionTanque.java
 *
 * Created on 9 de junio de 2008, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkjConsultarMedicionTanque;

import pkgSoporte.Pantalla;

/**
 *
 * @author Fer
 */
public class PrincipalCUConsultarMedicionTanque {
    
    /** Creates a new instance of PrincipalCUConsultarMedicionTanque */
    public PrincipalCUConsultarMedicionTanque() {
    }
      public static void main(String[] args) {
    
          PllaConsultarMedicionTanque plla=new PllaConsultarMedicionTanque();
              Pantalla.centrarEnPantallaJFrame(plla,3,4);
 
      plla.setVisible(true);
      
      }
}
