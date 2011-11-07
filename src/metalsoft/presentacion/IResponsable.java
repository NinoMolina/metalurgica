/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import metalsoft.negocio.compras.Responsable;

/**
 *
 * @author Nino
 */
public interface IResponsable {

    public void setResponsable(Responsable respNegocio, long idResponsable);
    public void setResponsable(Responsable respNegocio, metalsoft.datos.dbobject.ResponsableDB respDB);
    public void mostrarDatosResponsable();
}
