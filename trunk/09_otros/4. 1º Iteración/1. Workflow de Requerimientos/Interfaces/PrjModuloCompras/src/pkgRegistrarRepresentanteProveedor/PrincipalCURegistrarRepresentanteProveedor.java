/*
 * PrincipalCURegistrarRepresentanteProveedor.java
 *
 * Created on 5 de junio de 2008, 18:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgRegistrarRepresentanteProveedor;

import pkgCasosDeUso.pkgCompras.RegistrarRepresentanteProveedor.PllaRegistrarRepresentanteProveedor;
import pkgSoporte.Pantalla;

/**
 *
 * @author usuario
 */
public class PrincipalCURegistrarRepresentanteProveedor {
    
    /** Creates a new instance of PrincipalCURegistrarRepresentanteProveedor */
    public static void main(String[] args) {
        // TODO code application logic here
        PllaRegistrarRepresentanteProveedor pa = new PllaRegistrarRepresentanteProveedor();
        Pantalla.centrarEnPantallaJFrame(pa,3,4);
        pa.setVisible(true);
        
    }
    
}
