/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.Date;

/**
 *
 * @author Nino
 */
public class ViewPedidosConMPAsignada {

    private long nropedido;
    private long nroplanificacionproduccion;
    private Date fechacreacion;
    private Date fechainicioprevista;
    private Date fechafinprevista;
    private String observaciones;
    private long idpedido;
    private long idplanificacionproduccion;

    public ViewPedidosConMPAsignada() {
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechafinprevista() {
        return fechafinprevista;
    }

    public void setFechafinprevista(Date fechafinprevista) {
        this.fechafinprevista = fechafinprevista;
    }

    public Date getFechainicioprevista() {
        return fechainicioprevista;
    }

    public void setFechainicioprevista(Date fechainicioprevista) {
        this.fechainicioprevista = fechainicioprevista;
    }

    public long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(long idpedido) {
        this.idpedido = idpedido;
    }

    public long getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(long idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    public long getNropedido() {
        return nropedido;
    }

    public void setNropedido(long nropedido) {
        this.nropedido = nropedido;
    }

    public long getNroplanificacionproduccion() {
        return nroplanificacionproduccion;
    }

    public void setNroplanificacionproduccion(long nroplanificacionproduccion) {
        this.nroplanificacionproduccion = nroplanificacionproduccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
}
