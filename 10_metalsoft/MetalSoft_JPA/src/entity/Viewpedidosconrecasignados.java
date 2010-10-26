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
@Table(name = "viewpedidosconrecasignados")
@NamedQueries({
    @NamedQuery(name = "Viewpedidosconrecasignados.findAll", query = "SELECT v FROM Viewpedidosconrecasignados v"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByNropedido", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.nropedido = :nropedido"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByNropedidocotizacioncliente", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.nropedidocotizacioncliente = :nropedidocotizacioncliente"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByRazonsocial", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.razonsocial = :razonsocial"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByPrioridad", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.prioridad = :prioridad"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByFechaentregaestipulada", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.fechaentregaestipulada = :fechaentregaestipulada"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByIdpedido", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.idpedido = :idpedido"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByIdcliente", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.idcliente = :idcliente"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByIdprioridad", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.idprioridad = :idprioridad"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByPresupuesto", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.presupuesto = :presupuesto"),
    @NamedQuery(name = "Viewpedidosconrecasignados.findByIdestado", query = "SELECT v FROM Viewpedidosconrecasignados v WHERE v.idestado = :idestado")})
public class Viewpedidosconrecasignados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nropedido")
    private BigInteger nropedido;
    @Column(name = "nropedidocotizacioncliente")
    private Integer nropedidocotizacioncliente;
    @Column(name = "razonsocial")
    private String razonsocial;
    @Column(name = "prioridad")
    private String prioridad;
    @Column(name = "fechaentregaestipulada")
    @Temporal(TemporalType.DATE)
    private Date fechaentregaestipulada;
    @Column(name = "idpedido")
    private BigInteger idpedido;
    @Column(name = "idcliente")
    private BigInteger idcliente;
    @Column(name = "idprioridad")
    private BigInteger idprioridad;
    @Column(name = "presupuesto")
    private BigInteger presupuesto;
    @Column(name = "idestado")
    private BigInteger idestado;

    public Viewpedidosconrecasignados() {
    }

    public BigInteger getNropedido() {
        return nropedido;
    }

    public void setNropedido(BigInteger nropedido) {
        this.nropedido = nropedido;
    }

    public Integer getNropedidocotizacioncliente() {
        return nropedidocotizacioncliente;
    }

    public void setNropedidocotizacioncliente(Integer nropedidocotizacioncliente) {
        this.nropedidocotizacioncliente = nropedidocotizacioncliente;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaentregaestipulada() {
        return fechaentregaestipulada;
    }

    public void setFechaentregaestipulada(Date fechaentregaestipulada) {
        this.fechaentregaestipulada = fechaentregaestipulada;
    }

    public BigInteger getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(BigInteger idpedido) {
        this.idpedido = idpedido;
    }

    public BigInteger getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(BigInteger idcliente) {
        this.idcliente = idcliente;
    }

    public BigInteger getIdprioridad() {
        return idprioridad;
    }

    public void setIdprioridad(BigInteger idprioridad) {
        this.idprioridad = idprioridad;
    }

    public BigInteger getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigInteger presupuesto) {
        this.presupuesto = presupuesto;
    }

    public BigInteger getIdestado() {
        return idestado;
    }

    public void setIdestado(BigInteger idestado) {
        this.idestado = idestado;
    }

}
