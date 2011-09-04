/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
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

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallepedido")
@NamedQueries({
    @NamedQuery(name = "Detallepedido.findAll", query = "SELECT d FROM Detallepedido d"),
    @NamedQuery(name = "Detallepedido.findByIddetalle", query = "SELECT d FROM Detallepedido d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallepedido.findByPrecio", query = "SELECT d FROM Detallepedido d WHERE d.precio = :precio"),
    @NamedQuery(name = "Detallepedido.findByCantidad", query = "SELECT d FROM Detallepedido d WHERE d.cantidad = :cantidad")})
public class Detallepedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detallepedido_seq")
    @SequenceGenerator(name = "detallepedido_seq", sequenceName = "detallepedido_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "producto", referencedColumnName = "idproducto")
    @ManyToOne
    private Producto producto;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedido idpedido;

    public Detallepedido() {
    }

    public Detallepedido(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Pedido idpedido) {
        this.idpedido = idpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepedido)) {
            return false;
        }
        Detallepedido other = (Detallepedido) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallepedido[ iddetalle=" + iddetalle + " ]";
    }
    
}
