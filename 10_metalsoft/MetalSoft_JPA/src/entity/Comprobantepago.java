/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @Column(name = "idcomprobantepago")
    private Long idcomprobantepago;
    @Column(name = "nrocomprobantepago")
    private BigInteger nrocomprobantepago;
    @Column(name = "fechaemision")
    @Temporal(TemporalType.DATE)
    private Date fechaemision;
    @Column(name = "monto")
    private Double monto;
    @JoinColumn(name = "factura", referencedColumnName = "idfactura")
    @ManyToOne
    private Factura factura;
    @JoinColumn(name = "factura", referencedColumnName = "idfactura")
    @ManyToOne
    private Factura factura1;
    @JoinColumn(name = "formadepago", referencedColumnName = "idformapago")
    @ManyToOne
    private Formadepago formadepago;
    @JoinColumn(name = "formadepago", referencedColumnName = "idformapago")
    @ManyToOne
    private Formadepago formadepago1;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario1;

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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Factura getFactura1() {
        return factura1;
    }

    public void setFactura1(Factura factura1) {
        this.factura1 = factura1;
    }

    public Formadepago getFormadepago() {
        return formadepago;
    }

    public void setFormadepago(Formadepago formadepago) {
        this.formadepago = formadepago;
    }

    public Formadepago getFormadepago1() {
        return formadepago1;
    }

    public void setFormadepago1(Formadepago formadepago1) {
        this.formadepago1 = formadepago1;
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
        return "entity.Comprobantepago[idcomprobantepago=" + idcomprobantepago + "]";
    }

}