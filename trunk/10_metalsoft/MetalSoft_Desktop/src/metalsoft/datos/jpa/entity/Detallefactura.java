/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallefactura")
@NamedQueries({
    @NamedQuery(name = "Detallefactura.findAll", query = "SELECT d FROM Detallefactura d"),
    @NamedQuery(name = "Detallefactura.findByIddetalle", query = "SELECT d FROM Detallefactura d WHERE d.detallefacturaPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallefactura.findByIdfactura", query = "SELECT d FROM Detallefactura d WHERE d.detallefacturaPK.idfactura = :idfactura"),
    @NamedQuery(name = "Detallefactura.findByMontoparcial", query = "SELECT d FROM Detallefactura d WHERE d.montoparcial = :montoparcial"),
    @NamedQuery(name = "Detallefactura.findByCantidad", query = "SELECT d FROM Detallefactura d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallefactura.findByIddetallepedido", query = "SELECT d FROM Detallefactura d WHERE d.iddetallepedido = :iddetallepedido")})
public class Detallefactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallefacturaPK detallefacturaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montoparcial")
    private Double montoparcial;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "iddetallepedido")
    private BigInteger iddetallepedido;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido idpedido;
    @JoinColumn(name = "idfactura", referencedColumnName = "idfactura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;

    public Detallefactura() {
    }

    public Detallefactura(DetallefacturaPK detallefacturaPK) {
        this.detallefacturaPK = detallefacturaPK;
    }

    public Detallefactura(long iddetalle, long idfactura) {
        this.detallefacturaPK = new DetallefacturaPK(iddetalle, idfactura);
    }

    public DetallefacturaPK getDetallefacturaPK() {
        return detallefacturaPK;
    }

    public void setDetallefacturaPK(DetallefacturaPK detallefacturaPK) {
        this.detallefacturaPK = detallefacturaPK;
    }

    public Double getMontoparcial() {
        return montoparcial;
    }

    public void setMontoparcial(Double montoparcial) {
        this.montoparcial = montoparcial;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigInteger getIddetallepedido() {
        return iddetallepedido;
    }

    public void setIddetallepedido(BigInteger iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Pedido getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Pedido idpedido) {
        this.idpedido = idpedido;
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
        hash += (detallefacturaPK != null ? detallefacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactura)) {
            return false;
        }
        Detallefactura other = (Detallefactura) object;
        if ((this.detallefacturaPK == null && other.detallefacturaPK != null) || (this.detallefacturaPK != null && !this.detallefacturaPK.equals(other.detallefacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallefactura[ detallefacturaPK=" + detallefacturaPK + " ]";
    }
    
}
