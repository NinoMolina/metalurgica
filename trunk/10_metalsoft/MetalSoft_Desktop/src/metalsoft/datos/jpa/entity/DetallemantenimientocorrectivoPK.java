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
public class DetallemantenimientocorrectivoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idmantenimientocorrectivo")
    private long idmantenimientocorrectivo;
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;

    public DetallemantenimientocorrectivoPK() {
    }

    public DetallemantenimientocorrectivoPK(long idmantenimientocorrectivo, long iddetalle) {
        this.idmantenimientocorrectivo = idmantenimientocorrectivo;
        this.iddetalle = iddetalle;
    }

    public long getIdmantenimientocorrectivo() {
        return idmantenimientocorrectivo;
    }

    public void setIdmantenimientocorrectivo(long idmantenimientocorrectivo) {
        this.idmantenimientocorrectivo = idmantenimientocorrectivo;
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
        hash += (int) idmantenimientocorrectivo;
        hash += (int) iddetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallemantenimientocorrectivoPK)) {
            return false;
        }
        DetallemantenimientocorrectivoPK other = (DetallemantenimientocorrectivoPK) object;
        if (this.idmantenimientocorrectivo != other.idmantenimientocorrectivo) {
            return false;
        }
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetallemantenimientocorrectivoPK[ idmantenimientocorrectivo=" + idmantenimientocorrectivo + ", iddetalle=" + iddetalle + " ]";
    }
    
}
