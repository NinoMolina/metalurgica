/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.util.EnumOpcionesABM;

/**
 *
 * @author Nino
 */
public abstract class AbsGuardable{

    public int guardar(EnumOpcionesABM opcion) {
        int result=-1;
        switch(opcion)
        {
            case GUARDAR:   result=registrar();
                            break;
            case MODIFICAR: result=modificar();
                            break;
            default: break;
        }
        return result;
    }

    public abstract int registrar();
    public abstract int modificar();

}
