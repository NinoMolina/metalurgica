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
public class DetallereclamoempresametalurgicaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idreclamo")
    private long idreclamo;

    public DetallereclamoempresametalurgicaPK() {
    }

    public DetallereclamoempresametalurgicaPK(long iddetalle, long idreclamo) {
        this.iddetalle = iddetalle;
        this.idreclamo = idreclamo;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdreclamo() {
        return idreclamo;
    }

    public void setIdreclamo(long idreclamo) {
        this.idreclamo = idreclamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idreclamo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallereclamoempresametalurgicaPK)) {
            return false;
        }
        DetallereclamoempresametalurgicaPK other = (DetallereclamoempresametalurgicaPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idreclamo != other.idreclamo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetallereclamoempresametalurgicaPK[ iddetalle=" + iddetalle + ", idreclamo=" + idreclamo + " ]";
    }
    
}
