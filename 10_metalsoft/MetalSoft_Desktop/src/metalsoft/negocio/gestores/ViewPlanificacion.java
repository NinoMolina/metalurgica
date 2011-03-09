/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;
import java.util.Date;
/**
 *
 * @author Vicky
 */

public class ViewPlanificacion {

    public ViewPlanificacion() {
    }
    private long nropedido;
    private long nropedidocotizacioncliente;
    private String razonsocial;
    private String prioridad;
    private Date fechaentregaestipulada;
    
    private long idpedido;
    private long idpresupuesto;
    private long idcliente;
    private long idprioridad;
    private long idestado;

    public long getIdestado() {
        return idestado;
    }

    public void setIdestado(long idestado) {
        this.idestado = idestado;
    }

    public Date getFechaentregaestipulada() {
        return fechaentregaestipulada;
    }

    public void setFechaentregaestipulada(Date fechaentregaestipulada) {
        this.fechaentregaestipulada = fechaentregaestipulada;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
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

    public long getIdprioridad() {
        return idprioridad;
    }

    public void setIdprioridad(long idprioridad) {
        this.idprioridad = idprioridad;
    }

    public long getNropedido() {
        return nropedido;
    }

    public void setNropedido(long nropedido) {
        this.nropedido = nropedido;
    }

    public long getNropedidocotizacioncliente() {
        return nropedidocotizacioncliente;
    }

    public void setNropedidocotizacioncliente(long nropedidocotizacioncliente) {
        this.nropedidocotizacioncliente = nropedidocotizacioncliente;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

}
