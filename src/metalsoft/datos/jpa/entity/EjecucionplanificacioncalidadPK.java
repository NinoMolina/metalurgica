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
public class EjecucionplanificacioncalidadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idejecucion")
    private long idejecucion;
    @Basic(optional = false)
    @Column(name = "idplanificacioncalidad")
    private long idplanificacioncalidad;

    public EjecucionplanificacioncalidadPK() {
    }

    public EjecucionplanificacioncalidadPK(long idejecucion, long idplanificacioncalidad) {
        this.idejecucion = idejecucion;
        this.idplanificacioncalidad = idplanificacioncalidad;
    }

    public long getIdejecucion() {
        return idejecucion;
    }

    public void setIdejecucion(long idejecucion) {
        this.idejecucion = idejecucion;
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
        hash += (int) idejecucion;
        hash += (int) idplanificacioncalidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjecucionplanificacioncalidadPK)) {
            return false;
        }
        EjecucionplanificacioncalidadPK other = (EjecucionplanificacioncalidadPK) object;
        if (this.idejecucion != other.idejecucion) {
            return false;
        }
        if (this.idplanificacioncalidad != other.idplanificacioncalidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.EjecucionplanificacioncalidadPK[idejecucion=" + idejecucion + ", idplanificacioncalidad=" + idplanificacioncalidad + "]";
    }

}
