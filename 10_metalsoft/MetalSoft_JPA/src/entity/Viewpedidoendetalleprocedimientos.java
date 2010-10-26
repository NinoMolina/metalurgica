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
@Table(name = "viewpedidoendetalleprocedimientos")
@NamedQueries({
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findAll", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByNropedido", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.nropedido = :nropedido"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByNropedidocotizacioncliente", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.nropedidocotizacioncliente = :nropedidocotizacioncliente"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByFechapedidocotizacion", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.fechapedidocotizacion = :fechapedidocotizacion"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByFecharequeridacotizacion", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.fecharequeridacotizacion = :fecharequeridacotizacion"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByFechaentregaestipulada", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.fechaentregaestipulada = :fechaentregaestipulada"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByEspedidoweb", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.espedidoweb = :espedidoweb"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByEstado", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.estado = :estado"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByPrioridad", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.prioridad = :prioridad"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByCliente", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.cliente = :cliente"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByIdpedido", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.idpedido = :idpedido"),
    @NamedQuery(name = "Viewpedidoendetalleprocedimientos.findByIdestado", query = "SELECT v FROM Viewpedidoendetalleprocedimientos v WHERE v.idestado = :idestado")})
public class Viewpedidoendetalleprocedimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nropedido")
    private BigInteger nropedido;
    @Column(name = "nropedidocotizacioncliente")
    private Integer nropedidocotizacioncliente;
    @Column(name = "fechapedidocotizacion")
    @Temporal(TemporalType.DATE)
    private Date fechapedidocotizacion;
    @Column(name = "fecharequeridacotizacion")
    @Temporal(TemporalType.DATE)
    private Date fecharequeridacotizacion;
    @Column(name = "fechaentregaestipulada")
    @Temporal(TemporalType.DATE)
    private Date fechaentregaestipulada;
    @Column(name = "espedidoweb")
    private Boolean espedidoweb;
    @Column(name = "estado")
    private String estado;
    @Column(name = "prioridad")
    private String prioridad;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "idpedido")
    private BigInteger idpedido;
    @Column(name = "idestado")
    private BigInteger idestado;

    public Viewpedidoendetalleprocedimientos() {
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

    public Date getFechapedidocotizacion() {
        return fechapedidocotizacion;
    }

    public void setFechapedidocotizacion(Date fechapedidocotizacion) {
        this.fechapedidocotizacion = fechapedidocotizacion;
    }

    public Date getFecharequeridacotizacion() {
        return fecharequeridacotizacion;
    }

    public void setFecharequeridacotizacion(Date fecharequeridacotizacion) {
        this.fecharequeridacotizacion = fecharequeridacotizacion;
    }

    public Date getFechaentregaestipulada() {
        return fechaentregaestipulada;
    }

    public void setFechaentregaestipulada(Date fechaentregaestipulada) {
        this.fechaentregaestipulada = fechaentregaestipulada;
    }

    public Boolean getEspedidoweb() {
        return espedidoweb;
    }

    public void setEspedidoweb(Boolean espedidoweb) {
        this.espedidoweb = espedidoweb;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigInteger getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(BigInteger idpedido) {
        this.idpedido = idpedido;
    }

    public BigInteger getIdestado() {
        return idestado;
    }

    public void setIdestado(BigInteger idestado) {
        this.idestado = idestado;
    }

}
