/*
 * PrincipalCUModificarRepresentanteProveedor.java
 *
 * Created on 5 de junio de 2008, 19:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgModificarRepresentanteProveedor;

import pkgCasosDeUso.pkgCompras.RegistrarRepresentanteProveedor.PllaModificarRepresentanteProveedor;
import pkgSoporte.Pantalla;

/**
 *
 * @author usuario
 */
public class PrincipalCUModificarRepresentanteProveedor {
    
    /** Creates a new instance of PrincipalCUModificarRepresentanteProveedor */
     public static void main(String[] args) {
        // TODO code application logic here
        PllaModificarRepresentanteProveedor pa = new PllaModificarRepresentanteProveedor();
        Pantalla.centrarEnPantallaJFrame(pa,4,8);
        pa.setVisible(true);
        
    }
    
}
