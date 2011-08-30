/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.List;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetallepedidoJpaController;
import metalsoft.datos.jpa.controller.FacturaJpaController;
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Factura;

/**
 *
 * @author Vicky
 */
public class GestorFactura {

    public GestorFactura(){

    }
    public Detallepedido buscarDetallePedido(long id){
        DetallepedidoJpaController con=new DetallepedidoJpaController();
        return con.findDetallepedido(id);
    }
    public List<Factura> buscarFacturasByNroLike(String param){
        List<Factura> list=JpaUtil.getFacturasByNroLIKE(param);
        return list;
    }
    public List<Factura> buscarFacturasByFechaEmision(String param){
        List<Factura> list=JpaUtil.getFacturasByFechaEmision(param);
        return list;
    }
    public List<Factura> buscarFacturasByFechaVto(String param){
        List<Factura> list=JpaUtil.getFacturasByFechaVto(param);
        return list;
    }
    public List<Detallefactura> buscarDetalleFacturaByFactura(long id){
        List<Detallefactura> list=JpaUtil.getDetalleFacturaByFactura(id);
        return list;
    }
    
}
