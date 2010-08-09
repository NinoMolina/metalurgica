/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

/**
 *
 * @author Nino
 */
public class ViewDetalleProducto {

    private String nombrePieza;
    private String descripcion;
    private int cantidad;
    private String dimensiones;
    private String nombreTipoMaterial;
    private long idProducto,idPieza,idDetalle;

    public ViewDetalleProducto() {
    }

    public long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(long idPieza) {
        this.idPieza = idPieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public String getNombreTipoMaterial() {
        return nombreTipoMaterial;
    }

    public void setNombreTipoMaterial(String nombreTipoMaterial) {
        this.nombreTipoMaterial = nombreTipoMaterial;
    }

    
}
