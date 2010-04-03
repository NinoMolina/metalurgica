/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

/**
 *
 * @author Armando
 */
public class ProductoDePlaya extends Producto{
    
    private double stockEnPlaya;
    
    public ProductoDePlaya() {}
    

    public ProductoDePlaya(String nombre, double precioCosto, double precioVenta, double stockMinimo, double stockActual, boolean autorizacionPedidoEnPlaya, Iva iva, double stockEnPlaya) {
        super(nombre, precioCosto, precioVenta, stockMinimo, stockActual, autorizacionPedidoEnPlaya, iva);
        this.stockEnPlaya = stockEnPlaya;
    }

    public double getStockEnPlaya() {
        return stockEnPlaya;
    }

    public void setStockEnPlaya(double stockEnPlaya) {
        this.stockEnPlaya = stockEnPlaya;
    }

}
