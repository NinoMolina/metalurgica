/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import metalsoft.negocio.rrhh.Domicilio;

/**
 *
 * @author Nino
 */
public interface IDomiciliable {

    public void setDomicilio(Domicilio dom,long id);
    public void setDomicilio(Domicilio dom,metalsoft.datos.dbobject.DomicilioDB domDB);
    public void mostrarDatosDomicilio();
}
