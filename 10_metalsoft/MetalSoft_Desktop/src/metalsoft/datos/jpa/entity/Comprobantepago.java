/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "comprobantepago")
@NamedQueries({
    @NamedQuery(name = "Comprobantepago.findAll", query = "SELECT c FROM Comprobantepago c"),
    @NamedQuery(name = "Comprobantepago.findByIdcomprobantepago", query = "SELECT c FROM Comprobantepago c WHERE c.idcomprobantepago = :idcomprobantepago"),
    @NamedQuery(name = "Comprobantepago.findByNrocomprobantepago", query = "SELECT c FROM Comprobantepago c WHERE c.nrocomprobantepago = :nrocomprobantepago"),
    @NamedQuery(name = "Comprobantepago.findByFechaemision", query = "SELECT c FROM Comprobantepago c WHERE c.fechaemision = :fechaemision"),
    @NamedQuery(name = "Comprobantepago.findByMonto", query = "SELECT c FROM Comprobantepago c WHERE c.monto = :monto")})
public class Comprobantepago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comprobantepago_seq")
    @SequenceGenerator(name = "comprobantepago_seq", sequenceName = "comprobantepago_idcomprobantepago_seq", allocationSize = 1)
    @Column(name = "idcomprobantepago")
    private Long idcomprobantepago;
    @Column(name = "nrocomprobantepago")
    private BigInteger nrocomprobantepago;
    @Column(name = "fechaemision")
    @Temporal(TemporalType.DATE)
    private Date fechaemision;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "formadepago", referencedColumnName = "idformapago")
    @ManyToOne
    private Formadepago formadepago;
    @JoinColumn(name = "factura", referencedColumnName = "idfactura")
    @ManyToOne
    private Factura factura;

    public Comprobantepago() {
    }

    public Comprobantepago(Long idcomprobantepago) {
        this.idcomprobantepago = idcomprobantepago;
    }

    public Long getIdcomprobantepago() {
        return idcomprobantepago;
    }

    public void setIdcomprobantepago(Long idcomprobantepago) {
        this.idcomprobantepago = idcomprobantepago;
    }

    public BigInteger getNrocomprobantepago() {
        return nrocomprobantepago;
    }

    public void setNrocomprobantepago(BigInteger nrocomprobantepago) {
        this.nrocomprobantepago = nrocomprobantepago;
    }

    public Date getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Formadepago getFormadepago() {
        return formadepago;
    }

    public void setFormadepago(Formadepago formadepago) {
        this.formadepago = formadepago;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomprobantepago != null ? idcomprobantepago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprobantepago)) {
            return false;
        }
        Comprobantepago other = (Comprobantepago) object;
        if ((this.idcomprobantepago == null && other.idcomprobantepago != null) || (this.idcomprobantepago != null && !this.idcomprobantepago.equals(other.idcomprobantepago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Comprobantepago[ idcomprobantepago=" + idcomprobantepago + " ]";
    }
    
}
