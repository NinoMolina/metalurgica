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
public class DetallemantenimientopreventivoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idmantenimientopreventivo")
    private long idmantenimientopreventivo;
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;

    public DetallemantenimientopreventivoPK() {
    }

    public DetallemantenimientopreventivoPK(long idmantenimientopreventivo, long iddetalle) {
        this.idmantenimientopreventivo = idmantenimientopreventivo;
        this.iddetalle = iddetalle;
    }

    public long getIdmantenimientopreventivo() {
        return idmantenimientopreventivo;
    }

    public void setIdmantenimientopreventivo(long idmantenimientopreventivo) {
        this.idmantenimientopreventivo = idmantenimientopreventivo;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idmantenimientopreventivo;
        hash += (int) iddetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallemantenimientopreventivoPK)) {
            return false;
        }
        DetallemantenimientopreventivoPK other = (DetallemantenimientopreventivoPK) object;
        if (this.idmantenimientopreventivo != other.idmantenimientopreventivo) {
            return false;
        }
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetallemantenimientopreventivoPK[ idmantenimientopreventivo=" + idmantenimientopreventivo + ", iddetalle=" + iddetalle + " ]";
    }
    
}
