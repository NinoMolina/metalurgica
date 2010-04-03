/*
 * PrincipalCURegistrarProductoDePlaya.java
 *
 * Created on 6 de junio de 2008, 17:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgRegistrarProductoDePlaya;

import pkgSoporte.Pantalla;

/**
 *
 * @author usuario
 */
public class PrincipalCURegistrarProductoDePlaya {
    
    /** Creates a new instance of PrincipalCURegistrarProductoDePlaya */
    public static void main(String[] args) {
        // TODO code application logic here
        PllaRegistrarProductoDePlaya pa = new PllaRegistrarProductoDePlaya();
        Pantalla.centrarEnPantallaJFrame(pa,4,8);
        pa.setVisible(true);
        
    }
    
}
