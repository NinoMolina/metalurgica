/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "viewpedidosconmpasignada")
@NamedQueries({
    @NamedQuery(name = "Viewpedidosconmpasignada.findAll", query = "SELECT v FROM Viewpedidosconmpasignada v"),
    @NamedQuery(name = "Viewpedidosconmpasignada.findByNropedido", query = "SELECT v FROM Viewpedidosconmpasignada v WHERE v.nropedido = :nropedido"),
    @NamedQuery(name = "Viewpedidosconmpasignada.findByNroplanificacionproduccion", query = "SELECT v FROM Viewpedidosconmpasignada v WHERE v.nroplanificacionproduccion = :nroplanificacionproduccion"),
    @NamedQuery(name = "Viewpedidosconmpasignada.findByFechacreacion", query = "SELECT v FROM Viewpedidosconmpasignada v WHERE v.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Viewpedidosconmpasignada.findByFechainicioprevista", query = "SELECT v FROM Viewpedidosconmpasignada v WHERE v.fechainicioprevista = :fechainicioprevista"),
    @NamedQuery(name = "Viewpedidosconmpasignada.findByFechafinprevista", query = "SELECT v FROM Viewpedidosconmpasignada v WHERE v.fechafinprevista = :fechafinprevista"),
    @NamedQuery(name = "Viewpedidosconmpasignada.findByObservaciones", query = "SELECT v FROM Viewpedidosconmpasignada v WHERE v.observaciones = :observaciones"),
    @NamedQuery(name = "Viewpedidosconmpasignada.findByIdpedido", query = "SELECT v FROM Viewpedidosconmpasignada v WHERE v.idpedido = :idpedido"),
    @NamedQuery(name = "Viewpedidosconmpasignada.findByIdplanificacionproduccion", query = "SELECT v FROM Viewpedidosconmpasignada v WHERE v.idplanificacionproduccion = :idplanificacionproduccion")})
public class Viewpedidosconmpasignada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nropedido")
    private BigInteger nropedido;
    @Column(name = "nroplanificacionproduccion")
    private BigInteger nroplanificacionproduccion;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @Column(name = "fechainicioprevista")
    @Temporal(TemporalType.DATE)
    private Date fechainicioprevista;
    @Column(name = "fechafinprevista")
    @Temporal(TemporalType.DATE)
    private Date fechafinprevista;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "idpedido")
    private BigInteger idpedido;
    @Column(name = "idplanificacionproduccion")
    private BigInteger idplanificacionproduccion;

    public Viewpedidosconmpasignada() {
    }

    public BigInteger getNropedido() {
        return nropedido;
    }

    public void setNropedido(BigInteger nropedido) {
        this.nropedido = nropedido;
    }

    public BigInteger getNroplanificacionproduccion() {
        return nroplanificacionproduccion;
    }

    public void setNroplanificacionproduccion(BigInteger nroplanificacionproduccion) {
        this.nroplanificacionproduccion = nroplanificacionproduccion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechainicioprevista() {
        return fechainicioprevista;
    }

    public void setFechainicioprevista(Date fechainicioprevista) {
        this.fechainicioprevista = fechainicioprevista;
    }

    public Date getFechafinprevista() {
        return fechafinprevista;
    }

    public void setFechafinprevista(Date fechafinprevista) {
        this.fechafinprevista = fechafinprevista;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigInteger getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(BigInteger idpedido) {
        this.idpedido = idpedido;
    }

    public BigInteger getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(BigInteger idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

}
