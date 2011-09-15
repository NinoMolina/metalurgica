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
public class DetalleproductorealPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idproductoreal")
    private long idproductoreal;

    public DetalleproductorealPK() {
    }

    public DetalleproductorealPK(long iddetalle, long idproductoreal) {
        this.iddetalle = iddetalle;
        this.idproductoreal = idproductoreal;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdproductoreal() {
        return idproductoreal;
    }

    public void setIdproductoreal(long idproductoreal) {
        this.idproductoreal = idproductoreal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idproductoreal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleproductorealPK)) {
            return false;
        }
        DetalleproductorealPK other = (DetalleproductorealPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idproductoreal != other.idproductoreal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetalleproductorealPK[iddetalle=" + iddetalle + ", idproductoreal=" + idproductoreal + "]";
    }

}
