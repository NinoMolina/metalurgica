/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

/**
 *
 * @author Nino
 */
public class ViewPresupuestoParaFactura {

    private Long nroproducto;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Double precio;
    private Long idproducto;
    private Long iddetalle;
    private Long idpedido;
    private Long idpresupuesto;
    private Long iddetallepresupuesto;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetallepresupuesto() {
        return iddetallepresupuesto;
    }

    public void setIddetallepresupuesto(Long iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public Long getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Long idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(Long nroproducto) {
        this.nroproducto = nroproducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
