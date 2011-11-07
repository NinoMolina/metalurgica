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
public class ViewPedidosConProduccionFinalizada {

    private Long nropedido;
    private Long nroplanificacionproduccion;
    private Date fechacreacion;
    private Date fechainicioprevista;
    private Date fechafinprevista;
    private String observaciones;
    private Long idpedido;
    private Long idplanificacioncalidad;
    private Long idplanificacionproduccion;
    private Long idejecplanifproduccion;

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

    public Long getIdejecplanifproduccion() {
        return idejecplanifproduccion;
    }

    public void setIdejecplanifproduccion(Long idejecplanifproduccion) {
        this.idejecplanifproduccion = idejecplanifproduccion;
    }

    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public Long getIdplanificacioncalidad() {
        return idplanificacioncalidad;
    }

    public void setIdplanificacioncalidad(Long idplanificacioncalidad) {
        this.idplanificacioncalidad = idplanificacioncalidad;
    }

    public Long getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(Long idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    public Long getNropedido() {
        return nropedido;
    }

    public void setNropedido(Long nropedido) {
        this.nropedido = nropedido;
    }

    public Long getNroplanificacionproduccion() {
        return nroplanificacionproduccion;
    }

    public void setNroplanificacionproduccion(Long nroplanificacionproduccion) {
        this.nroplanificacionproduccion = nroplanificacionproduccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
