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
public class DetallecompraPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idcompra")
    private long idcompra;

    public DetallecompraPK() {
    }

    public DetallecompraPK(long iddetalle, long idcompra) {
        this.iddetalle = iddetalle;
        this.idcompra = idcompra;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(long idcompra) {
        this.idcompra = idcompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idcompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallecompraPK)) {
            return false;
        }
        DetallecompraPK other = (DetallecompraPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idcompra != other.idcompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetallecompraPK[ iddetalle=" + iddetalle + ", idcompra=" + idcompra + " ]";
    }
    
}
