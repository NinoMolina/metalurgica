/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

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
}
