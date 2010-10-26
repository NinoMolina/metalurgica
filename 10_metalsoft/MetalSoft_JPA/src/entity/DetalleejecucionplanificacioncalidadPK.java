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
public class DetalleejecucionplanificacioncalidadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idejecucionplanificacioncalidad")
    private long idejecucionplanificacioncalidad;
    @Basic(optional = false)
    @Column(name = "idplanificacioncalidad")
    private long idplanificacioncalidad;

    public DetalleejecucionplanificacioncalidadPK() {
    }

    public DetalleejecucionplanificacioncalidadPK(long iddetalle, long idejecucionplanificacioncalidad, long idplanificacioncalidad) {
        this.iddetalle = iddetalle;
        this.idejecucionplanificacioncalidad = idejecucionplanificacioncalidad;
        this.idplanificacioncalidad = idplanificacioncalidad;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdejecucionplanificacioncalidad() {
        return idejecucionplanificacioncalidad;
    }

    public void setIdejecucionplanificacioncalidad(long idejecucionplanificacioncalidad) {
        this.idejecucionplanificacioncalidad = idejecucionplanificacioncalidad;
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
        hash += (int) idejecucionplanificacioncalidad;
        hash += (int) idplanificacioncalidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleejecucionplanificacioncalidadPK)) {
            return false;
        }
        DetalleejecucionplanificacioncalidadPK other = (DetalleejecucionplanificacioncalidadPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idejecucionplanificacioncalidad != other.idejecucionplanificacioncalidad) {
            return false;
        }
        if (this.idplanificacioncalidad != other.idplanificacioncalidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DetalleejecucionplanificacioncalidadPK[iddetalle=" + iddetalle + ", idejecucionplanificacioncalidad=" + idejecucionplanificacioncalidad + ", idplanificacioncalidad=" + idplanificacioncalidad + "]";
    }

}
