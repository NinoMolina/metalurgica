/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

/**
 *
 * @author Mariana
 */
public class ViewDetalleTrabajoTercerizado {
    private int cantidad;
    private long idPieza;
    String nombrePieza;

    public ViewDetalleTrabajoTercerizado() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(long idPieza) {
        this.idPieza = idPieza;
    }

    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

}
