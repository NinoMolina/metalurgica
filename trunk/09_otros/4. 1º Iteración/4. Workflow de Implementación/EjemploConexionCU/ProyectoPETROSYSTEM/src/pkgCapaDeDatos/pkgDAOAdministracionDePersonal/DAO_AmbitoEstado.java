/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOAdministracionDePersonal;

import java.sql.ResultSet;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import pkgClasesDeNegocio.Compras.AmbitoEstado;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Fer
 */
public class DAO_AmbitoEstado {
DAOManager daoManager=new DAOManager();

    public Lista materializarTodo(String condicion) throws Exception {
        Lista lstAmbitoEstado=new Lista();
          this.daoManager.establecerConexion();
          
          String consulta= new String();
          consulta="Select id_ambitoestado, nombre from ambitoestado "+condicion;
          
            ResultSet res=daoManager.ejecutarConsulta(consulta);
          
            while(res.next())
            {
            int id_AmbitoEstado=res.getInt(1);
            String nombre=new String();
            nombre=res.getString(2);
            
            AmbitoEstado ambitoEstado=new AmbitoEstado();
            ambitoEstado.setNombre(nombre);
            ambitoEstado.addAttribute("id_AmbitoEstado", id_AmbitoEstado);
            
            lstAmbitoEstado.insertarOrdenado(ambitoEstado);
            
            }
                             
               
        
        daoManager.cerrarConexion();
        return lstAmbitoEstado;
        
    }

}
