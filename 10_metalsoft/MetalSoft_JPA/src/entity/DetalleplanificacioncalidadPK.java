/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Nino
 */
@Embeddable
public class DetalleplanificacioncalidadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idplanificacioncalidad")
    private long idplanificacioncalidad;

    public DetalleplanificacioncalidadPK() {
    }

    public DetalleplanificacioncalidadPK(long iddetalle, long idplanificacioncalidad) {
        this.iddetalle = iddetalle;
        this.idplanificacioncalidad = idplanificacioncalidad;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdplanificacioncalidad() {
        return idplanificacioncalidad;
    }

    public void setIdplanificacioncalidad(long idplanificacioncalidad) {
        this.idplanificacioncalidad = idplanificacioncalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idplanificacioncalidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleplanificacioncalidadPK)) {
            return false;
        }
        DetalleplanificacioncalidadPK other = (DetalleplanificacioncalidadPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idplanificacioncalidad != other.idplanificacioncalidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DetalleplanificacioncalidadPK[iddetalle=" + iddetalle + ", idplanificacioncalidad=" + idplanificacioncalidad + "]";
    }

}
