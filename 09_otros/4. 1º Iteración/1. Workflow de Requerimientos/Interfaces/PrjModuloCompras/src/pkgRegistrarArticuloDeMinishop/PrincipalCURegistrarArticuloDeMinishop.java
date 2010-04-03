/*
 * PrincipalCURegistrarArticuloDeMinishop.java
 *
 * Created on 6 de junio de 2008, 17:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgRegistrarArticuloDeMinishop;

import pkgSoporte.Pantalla;

/**
 *
 * @author usuario
 */
public class PrincipalCURegistrarArticuloDeMinishop {
    
    /** Creates a new instance of PrincipalCURegistrarArticuloDeMinishop */
    public static void main(String[] args) {
        // TODO code application logic here
        PllaRegistrarArticuloDeMinishop pa = new PllaRegistrarArticuloDeMinishop();
        Pantalla.centrarEnPantallaJFrame(pa,4,8);
        pa.setVisible(true);
        
    }
    
}
