/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "factura_seq")
    @SequenceGenerator(name = "factura_seq", sequenceName = "factura_idfactura_seq", allocationSize = 1)
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
    private List<Pedido> pedidoList;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "tipoiva", referencedColumnName = "idtipoiva")
    @ManyToOne
    private Tipoiva tipoiva;
    @JoinColumn(name = "formapago", referencedColumnName = "idformapago")
    @ManyToOne
    private Formadepago formapago;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadofactura estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private List<Detallefactura> detallefacturaList;
    @OneToMany(mappedBy = "factura")
    private List<Comprobantepago> comprobantepagoList;

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

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tipoiva getTipoiva() {
        return tipoiva;
    }

    public void setTipoiva(Tipoiva tipoiva) {
        this.tipoiva = tipoiva;
    }

    public Formadepago getFormapago() {
        return formapago;
    }

    public void setFormapago(Formadepago formapago) {
        this.formapago = formapago;
    }

    public Estadofactura getEstado() {
        return estado;
    }

    public void setEstado(Estadofactura estado) {
        this.estado = estado;
    }

    public List<Detallefactura> getDetallefacturaList() {
        return detallefacturaList;
    }

    public void setDetallefacturaList(List<Detallefactura> detallefacturaList) {
        this.detallefacturaList = detallefacturaList;
    }

    public List<Comprobantepago> getComprobantepagoList() {
        return comprobantepagoList;
    }

    public void setComprobantepagoList(List<Comprobantepago> comprobantepagoList) {
        this.comprobantepagoList = comprobantepagoList;
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
        return "metalsoft.datos.jpa.entity.Factura[ idfactura=" + idfactura + " ]";
    }
    
}
