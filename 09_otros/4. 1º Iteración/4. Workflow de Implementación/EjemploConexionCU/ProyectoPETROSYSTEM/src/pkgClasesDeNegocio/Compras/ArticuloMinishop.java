/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class ArticuloMinishop extends Producto{

    private double numeroCodigoBarra;
    private TipoProducto tipoProducto=new TipoProducto();
    
  

    public ArticuloMinishop() {}

    
    public ArticuloMinishop(String nombre, double precioCosto, double precioVenta, double stockMinimo, double stockActual, boolean autorizacionPedidoEnPlaya, Iva iva, long numeroCodigoBarra) {
        super(nombre, precioCosto, precioVenta, stockMinimo, stockActual, autorizacionPedidoEnPlaya, iva);
        this.numeroCodigoBarra = numeroCodigoBarra;
    }

    public double getNumeroCodigoBarra() {
        return numeroCodigoBarra;
    }

    public void setNumeroCodigoBarra(double numeroCodigoBarra) {
        this.numeroCodigoBarra = numeroCodigoBarra;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    
    
    
}
