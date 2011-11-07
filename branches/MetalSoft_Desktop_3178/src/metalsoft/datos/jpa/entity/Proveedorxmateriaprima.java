/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
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
@Table(name = "proveedorxmateriaprima")
@NamedQueries({
    @NamedQuery(name = "Proveedorxmateriaprima.findAll", query = "SELECT p FROM Proveedorxmateriaprima p"),
    @NamedQuery(name = "Proveedorxmateriaprima.findByIdproveedor", query = "SELECT p FROM Proveedorxmateriaprima p WHERE p.proveedorxmateriaprimaPK.idproveedor = :idproveedor"),
    @NamedQuery(name = "Proveedorxmateriaprima.findByIdmateriaprima", query = "SELECT p FROM Proveedorxmateriaprima p WHERE p.proveedorxmateriaprimaPK.idmateriaprima = :idmateriaprima"),
    @NamedQuery(name = "Proveedorxmateriaprima.findByPrecio", query = "SELECT p FROM Proveedorxmateriaprima p WHERE p.precio = :precio")})
public class Proveedorxmateriaprima implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProveedorxmateriaprimaPK proveedorxmateriaprimaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @JoinColumn(name = "idproveedor", referencedColumnName = "idproveedor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;
    @JoinColumn(name = "idmateriaprima", referencedColumnName = "idmateriaprima", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Materiaprima materiaprima;

    public Proveedorxmateriaprima() {
    }

    public Proveedorxmateriaprima(ProveedorxmateriaprimaPK proveedorxmateriaprimaPK) {
        this.proveedorxmateriaprimaPK = proveedorxmateriaprimaPK;
    }

    public Proveedorxmateriaprima(long idproveedor, long idmateriaprima) {
        this.proveedorxmateriaprimaPK = new ProveedorxmateriaprimaPK(idproveedor, idmateriaprima);
    }

    public ProveedorxmateriaprimaPK getProveedorxmateriaprimaPK() {
        return proveedorxmateriaprimaPK;
    }

    public void setProveedorxmateriaprimaPK(ProveedorxmateriaprimaPK proveedorxmateriaprimaPK) {
        this.proveedorxmateriaprimaPK = proveedorxmateriaprimaPK;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Materiaprima getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorxmateriaprimaPK != null ? proveedorxmateriaprimaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedorxmateriaprima)) {
            return false;
        }
        Proveedorxmateriaprima other = (Proveedorxmateriaprima) object;
        if ((this.proveedorxmateriaprimaPK == null && other.proveedorxmateriaprimaPK != null) || (this.proveedorxmateriaprimaPK != null && !this.proveedorxmateriaprimaPK.equals(other.proveedorxmateriaprimaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Proveedorxmateriaprima[ proveedorxmateriaprimaPK=" + proveedorxmateriaprimaPK + " ]";
    }
    
}
