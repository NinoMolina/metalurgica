/*
 * PrincipalCUEliminarRepresentanteProveedor.java
 *
 * Created on 5 de junio de 2008, 19:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgEliminarRepresentanteProveedor;

import pkgCasosDeUso.pkgCompras.EliminarRepresentanteProveedor.PllaEliminarRepresentanteProveedor;
import pkgSoporte.Pantalla;

/**
 *
 * @author usuario
 */
public class PrincipalCUEliminarRepresentanteProveedor {
    
    /** Creates a new instance of PrincipalCUEliminarRepresentanteProveedor */
     public static void main(String[] args) {
        // TODO code application logic here
        PllaEliminarRepresentanteProveedor pa = new PllaEliminarRepresentanteProveedor();
        Pantalla.centrarEnPantallaJFrame(pa);
        pa.setVisible(true);
        
    }
    
}
