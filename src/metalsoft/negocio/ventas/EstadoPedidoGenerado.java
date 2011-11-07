/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.ventas;

/**
 *
 * @author Nino
 */
public class EstadoPedidoGenerado extends EstadoPedido{

    @Override
    public EstadoPedido goNext() {
        return new EstadoPedidoCDetProcedimientos();
    }

}
