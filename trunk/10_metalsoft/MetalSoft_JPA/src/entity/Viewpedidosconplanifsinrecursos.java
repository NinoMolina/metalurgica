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
@Table(name = "viewpedidosconplanifsinrecursos")
@NamedQueries({
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findAll", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByNropedido", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.nropedido = :nropedido"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByNropedcotcli", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.nropedcotcli = :nropedcotcli"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByFechapedido", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.fechapedido = :fechapedido"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByFechaentregaestipulada", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.fechaentregaestipulada = :fechaentregaestipulada"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByEstado", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.estado = :estado"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByNropresupuesto", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.nropresupuesto = :nropresupuesto"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByFechapresupuesto", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.fechapresupuesto = :fechapresupuesto"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByMontototal", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.montototal = :montototal"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByNrocliente", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.nrocliente = :nrocliente"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByRazonsocial", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.razonsocial = :razonsocial"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByIdpedido", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.idpedido = :idpedido"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByIdpresupuesto", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByIdestado", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.idestado = :idestado"),
    @NamedQuery(name = "Viewpedidosconplanifsinrecursos.findByIdcliente", query = "SELECT v FROM Viewpedidosconplanifsinrecursos v WHERE v.idcliente = :idcliente")})
public class Viewpedidosconplanifsinrecursos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nropedido")
    private BigInteger nropedido;
    @Column(name = "nropedcotcli")
    private Integer nropedcotcli;
    @Column(name = "fechapedido")
    @Temporal(TemporalType.DATE)
    private Date fechapedido;
    @Column(name = "fechaentregaestipulada")
    @Temporal(TemporalType.DATE)
    private Date fechaentregaestipulada;
    @Column(name = "estado")
    private String estado;
    @Column(name = "nropresupuesto")
    private BigInteger nropresupuesto;
    @Column(name = "fechapresupuesto")
    @Temporal(TemporalType.DATE)
    private Date fechapresupuesto;
    @Column(name = "montototal")
    private Double montototal;
    @Column(name = "nrocliente")
    private BigInteger nrocliente;
    @Column(name = "razonsocial")
    private String razonsocial;
    @Column(name = "idpedido")
    private BigInteger idpedido;
    @Column(name = "idpresupuesto")
    private BigInteger idpresupuesto;
    @Column(name = "idestado")
    private BigInteger idestado;
    @Column(name = "idcliente")
    private BigInteger idcliente;

    public Viewpedidosconplanifsinrecursos() {
    }

    public BigInteger getNropedido() {
        return nropedido;
    }

    public void setNropedido(BigInteger nropedido) {
        this.nropedido = nropedido;
    }

    public Integer getNropedcotcli() {
        return nropedcotcli;
    }

    public void setNropedcotcli(Integer nropedcotcli) {
        this.nropedcotcli = nropedcotcli;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public Date getFechaentregaestipulada() {
        return fechaentregaestipulada;
    }

    public void setFechaentregaestipulada(Date fechaentregaestipulada) {
        this.fechaentregaestipulada = fechaentregaestipulada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getNropresupuesto() {
        return nropresupuesto;
    }

    public void setNropresupuesto(BigInteger nropresupuesto) {
        this.nropresupuesto = nropresupuesto;
    }

    public Date getFechapresupuesto() {
        return fechapresupuesto;
    }

    public void setFechapresupuesto(Date fechapresupuesto) {
        this.fechapresupuesto = fechapresupuesto;
    }

    public Double getMontototal() {
        return montototal;
    }

    public void setMontototal(Double montototal) {
        this.montototal = montototal;
    }

    public BigInteger getNrocliente() {
        return nrocliente;
    }

    public void setNrocliente(BigInteger nrocliente) {
        this.nrocliente = nrocliente;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public BigInteger getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(BigInteger idpedido) {
        this.idpedido = idpedido;
    }

    public BigInteger getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(BigInteger idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public BigInteger getIdestado() {
        return idestado;
    }

    public void setIdestado(BigInteger idestado) {
        this.idestado = idestado;
    }

    public BigInteger getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(BigInteger idcliente) {
        this.idcliente = idcliente;
    }

}
