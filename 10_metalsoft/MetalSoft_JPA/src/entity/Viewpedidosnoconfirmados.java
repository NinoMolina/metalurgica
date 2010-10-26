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
@Table(name = "viewpedidosnoconfirmados")
@NamedQueries({
    @NamedQuery(name = "Viewpedidosnoconfirmados.findAll", query = "SELECT v FROM Viewpedidosnoconfirmados v"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByNropedido", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.nropedido = :nropedido"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByNropedcotcli", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.nropedcotcli = :nropedcotcli"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByFechapedido", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.fechapedido = :fechapedido"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByEstado", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.estado = :estado"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByNropresupuesto", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.nropresupuesto = :nropresupuesto"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByMontototal", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.montototal = :montototal"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByVencimientopresupuesto", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.vencimientopresupuesto = :vencimientopresupuesto"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByRazonsocial", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.razonsocial = :razonsocial"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByIdpedido", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.idpedido = :idpedido"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByIdpresupuesto", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByIdestado", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.idestado = :idestado"),
    @NamedQuery(name = "Viewpedidosnoconfirmados.findByIdcliente", query = "SELECT v FROM Viewpedidosnoconfirmados v WHERE v.idcliente = :idcliente")})
public class Viewpedidosnoconfirmados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nropedido")
    private BigInteger nropedido;
    @Column(name = "nropedcotcli")
    private Integer nropedcotcli;
    @Column(name = "fechapedido")
    @Temporal(TemporalType.DATE)
    private Date fechapedido;
    @Column(name = "estado")
    private String estado;
    @Column(name = "nropresupuesto")
    private BigInteger nropresupuesto;
    @Column(name = "montototal")
    private Double montototal;
    @Column(name = "vencimientopresupuesto")
    @Temporal(TemporalType.DATE)
    private Date vencimientopresupuesto;
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

    public Viewpedidosnoconfirmados() {
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

    public Double getMontototal() {
        return montototal;
    }

    public void setMontototal(Double montototal) {
        this.montototal = montototal;
    }

    public Date getVencimientopresupuesto() {
        return vencimientopresupuesto;
    }

    public void setVencimientopresupuesto(Date vencimientopresupuesto) {
        this.vencimientopresupuesto = vencimientopresupuesto;
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
