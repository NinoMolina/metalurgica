/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

/**
 *
 * @author Vicky
 */
public class viewAsignarMateriaPrima {

    public viewAsignarMateriaPrima() {
    }
    private long pedido;
    private long idmateriaprima;
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public long getPedido() {
        return pedido;
    }

    public void setPedido(long pedido) {
        this.pedido = pedido;
    }

}
