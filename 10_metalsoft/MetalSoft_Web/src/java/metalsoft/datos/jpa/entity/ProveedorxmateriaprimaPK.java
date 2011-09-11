/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Nino
 */
@Embeddable
public class ProveedorxmateriaprimaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idproveedor")
    private long idproveedor;
    @Basic(optional = false)
    @Column(name = "idmateriaprima")
    private long idmateriaprima;

    public ProveedorxmateriaprimaPK() {
    }

    public ProveedorxmateriaprimaPK(long idproveedor, long idmateriaprima) {
        this.idproveedor = idproveedor;
        this.idmateriaprima = idmateriaprima;
    }

    public long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(long idproveedor) {
        this.idproveedor = idproveedor;
    }

    public long getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idproveedor;
        hash += (int) idmateriaprima;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorxmateriaprimaPK)) {
            return false;
        }
        ProveedorxmateriaprimaPK other = (ProveedorxmateriaprimaPK) object;
        if (this.idproveedor != other.idproveedor) {
            return false;
        }
        if (this.idmateriaprima != other.idmateriaprima) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.ProveedorxmateriaprimaPK[ idproveedor=" + idproveedor + ", idmateriaprima=" + idmateriaprima + " ]";
    }
    
}
