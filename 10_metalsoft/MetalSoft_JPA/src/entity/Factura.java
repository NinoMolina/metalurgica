/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "factura")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdfactura", query = "SELECT f FROM Factura f WHERE f.idfactura = :idfactura"),
    @NamedQuery(name = "Factura.findByNrofactura", query = "SELECT f FROM Factura f WHERE f.nrofactura = :nrofactura"),
    @NamedQuery(name = "Factura.findByFechaemision", query = "SELECT f FROM Factura f WHERE f.fechaemision = :fechaemision"),
    @NamedQuery(name = "Factura.findByFecharealcobro", query = "SELECT f FROM Factura f WHERE f.fecharealcobro = :fecharealcobro"),
    @NamedQuery(name = "Factura.findByFechavencimiento", query = "SELECT f FROM Factura f WHERE f.fechavencimiento = :fechavencimiento"),
    @NamedQuery(name = "Factura.findByTipofactura", query = "SELECT f FROM Factura f WHERE f.tipofactura = :tipofactura")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idfactura")
    private Long idfactura;
    @Column(name = "nrofactura")
    private BigInteger nrofactura;
    @Column(name = "fechaemision")
    @Temporal(TemporalType.DATE)
    private Date fechaemision;
    @Column(name = "fecharealcobro")
    @Temporal(TemporalType.DATE)
    private Date fecharealcobro;
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @Column(name = "tipofactura")
    private String tipofactura;
    @OneToMany(mappedBy = "factura")
    private Set<Pedido> pedidoSet;
    @OneToMany(mappedBy = "factura1")
    private Set<Pedido> pedidoSet1;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadofactura estado;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadofactura estado1;
    @JoinColumn(name = "formapago", referencedColumnName = "idformapago")
    @ManyToOne
    private Formadepago formapago;
    @JoinColumn(name = "formapago", referencedColumnName = "idformapago")
    @ManyToOne
    private Formadepago formapago1;
    @JoinColumn(name = "tipoiva", referencedColumnName = "idtipoiva")
    @ManyToOne
    private Tipoiva tipoiva;
    @JoinColumn(name = "tipoiva", referencedColumnName = "idtipoiva")
    @ManyToOne
    private Tipoiva tipoiva1;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private Set<Detallefactura> detallefacturaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura1")
    private Set<Detallefactura> detallefacturaSet1;
    @OneToMany(mappedBy = "factura")
    private Set<Comprobantepago> comprobantepagoSet;
    @OneToMany(mappedBy = "factura1")
    private Set<Comprobantepago> comprobantepagoSet1;

    public Factura() {
    }

    public Factura(Long idfactura) {
        this.idfactura = idfactura;
    }

    public Long getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Long idfactura) {
        this.idfactura = idfactura;
    }

    public BigInteger getNrofactura() {
        return nrofactura;
    }

    public void setNrofactura(BigInteger nrofactura) {
        this.nrofactura = nrofactura;
    }

    public Date getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }

    public Date getFecharealcobro() {
        return fecharealcobro;
    }

    public void setFecharealcobro(Date fecharealcobro) {
        this.fecharealcobro = fecharealcobro;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public String getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(String tipofactura) {
        this.tipofactura = tipofactura;
    }

    public Set<Pedido> getPedidoSet() {
        return pedidoSet;
    }

    public void setPedidoSet(Set<Pedido> pedidoSet) {
        this.pedidoSet = pedidoSet;
    }

    public Set<Pedido> getPedidoSet1() {
        return pedidoSet1;
    }

    public void setPedidoSet1(Set<Pedido> pedidoSet1) {
        this.pedidoSet1 = pedidoSet1;
    }

    public Estadofactura getEstado() {
        return estado;
    }

    public void setEstado(Estadofactura estado) {
        this.estado = estado;
    }

    public Estadofactura getEstado1() {
        return estado1;
    }

    public void setEstado1(Estadofactura estado1) {
        this.estado1 = estado1;
    }

    public Formadepago getFormapago() {
        return formapago;
    }

    public void setFormapago(Formadepago formapago) {
        this.formapago = formapago;
    }

    public Formadepago getFormapago1() {
        return formapago1;
    }

    public void setFormapago1(Formadepago formapago1) {
        this.formapago1 = formapago1;
    }

    public Tipoiva getTipoiva() {
        return tipoiva;
    }

    public void setTipoiva(Tipoiva tipoiva) {
        this.tipoiva = tipoiva;
    }

    public Tipoiva getTipoiva1() {
        return tipoiva1;
    }

    public void setTipoiva1(Tipoiva tipoiva1) {
        this.tipoiva1 = tipoiva1;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Set<Detallefactura> getDetallefacturaSet() {
        return detallefacturaSet;
    }

    public void setDetallefacturaSet(Set<Detallefactura> detallefacturaSet) {
        this.detallefacturaSet = detallefacturaSet;
    }

    public Set<Detallefactura> getDetallefacturaSet1() {
        return detallefacturaSet1;
    }

    public void setDetallefacturaSet1(Set<Detallefactura> detallefacturaSet1) {
        this.detallefacturaSet1 = detallefacturaSet1;
    }

    public Set<Comprobantepago> getComprobantepagoSet() {
        return comprobantepagoSet;
    }

    public void setComprobantepagoSet(Set<Comprobantepago> comprobantepagoSet) {
        this.comprobantepagoSet = comprobantepagoSet;
    }

    public Set<Comprobantepago> getComprobantepagoSet1() {
        return comprobantepagoSet1;
    }

    public void setComprobantepagoSet1(Set<Comprobantepago> comprobantepagoSet1) {
        this.comprobantepagoSet1 = comprobantepagoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfactura != null ? idfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idfactura == null && other.idfactura != null) || (this.idfactura != null && !this.idfactura.equals(other.idfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Factura[idfactura=" + idfactura + "]";
    }

}
