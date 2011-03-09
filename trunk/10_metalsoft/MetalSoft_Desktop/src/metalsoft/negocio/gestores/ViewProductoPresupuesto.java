/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

/**
 *
 * @author Nino
 */
public class ViewProductoPresupuesto {

    private int cantidadproducto;
    private String nombreproducto;
    private double preciounitario;
    private double importe;
    private long idpedido;
    private long idpresupuesto;
    private long iddetallepresupuesto;
    private long idproducto;

    public ViewProductoPresupuesto() {
    }

    public int getCantidadproducto() {
        return cantidadproducto;
    }

    public void setCantidadproducto(int cantidadproducto) {
        this.cantidadproducto = cantidadproducto;
    }

    public long getIddetallepresupuesto() {
        return iddetallepresupuesto;
    }

    public void setIddetallepresupuesto(long iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }

    public long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(long idpedido) {
        this.idpedido = idpedido;
    }

    public long getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(long idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(double preciounitario) {
        this.preciounitario = preciounitario;
    }

    
}
