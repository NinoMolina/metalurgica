/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.negocio.produccion.TipoMaterial;

/**
 *
 * @author Nino
 */
public class GestorTipoMaterial {

    public GestorTipoMaterial()
    {}

    public int guardarTipoMaterial(String nombre, String descripcion)
    {
        GestorTipoMaterialDB gestor=new GestorTipoMaterialDB();
        return gestor.guardar(nombre, descripcion);
    }

    public TipoMaterial[] buscarTipoMaterial(String valor) {
        GestorTipoMaterialDB gestor=new GestorTipoMaterialDB();
        return gestor.buscarConLIKE(valor);
    }

    public boolean modificarTipoMaterial(TipoMaterial tipoMaterial, String nombre, String descripcion) {
        GestorTipoMaterialDB gestor=new GestorTipoMaterialDB();
        return gestor.modificarTipoMaterial(tipoMaterial,nombre,descripcion);
    }

    public boolean eliminarTipoMaterial(TipoMaterial tipoMaterial) {
        GestorTipoMaterialDB gestor=new GestorTipoMaterialDB();
        return gestor.eliminarTipoMaterial(tipoMaterial);
    }
}
