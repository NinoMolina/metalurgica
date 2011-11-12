/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

/**
 *
 * @author Mariana
 */
public class ViewDetalleCompra {
    private int cantidad;
    private long idMateriaPrima;
    String nombreMateriaPrima;
    private long idDetalleCompra;
    private double precioProv;


    public ViewDetalleCompra() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(long idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public String getNombreMateriaPrima() {
        return nombreMateriaPrima;
    }

    public void setNombreMateriaPrima(String nombreMateriaPrima) {
        this.nombreMateriaPrima = nombreMateriaPrima;
    }

    public double getPrecioProv() {
        return precioProv;
    }

    public void setPrecioProv(double precioProv) {
        this.precioProv = precioProv;
    }

    public long getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(long idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    
}
