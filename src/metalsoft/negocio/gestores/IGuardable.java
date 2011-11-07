/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

/**
 *
 * @author Nino
 */
public interface IGuardable {

    public int guardar(metalsoft.util.EnumOpcionesABM opcion);
    public int registrar();
    public int modificar();
}
