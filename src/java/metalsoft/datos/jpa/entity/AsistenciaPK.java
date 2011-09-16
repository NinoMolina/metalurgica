/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Embeddable
public class AsistenciaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "empleado")
    private long empleado;
    @Basic(optional = false)
    @Column(name = "fechaingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @Basic(optional = false)
    @Column(name = "horaingreso")
    @Temporal(TemporalType.TIME)
    private Date horaingreso;

    public AsistenciaPK() {
    }

    public AsistenciaPK(long empleado, Date fechaingreso, Date horaingreso) {
        this.empleado = empleado;
        this.fechaingreso = fechaingreso;
        this.horaingreso = horaingreso;
    }

    public long getEmpleado() {
        return empleado;
    }

    public void setEmpleado(long empleado) {
        this.empleado = empleado;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public Date getHoraingreso() {
        return horaingreso;
    }

    public void setHoraingreso(Date horaingreso) {
        this.horaingreso = horaingreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empleado;
        hash += (fechaingreso != null ? fechaingreso.hashCode() : 0);
        hash += (horaingreso != null ? horaingreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaPK)) {
            return false;
        }
        AsistenciaPK other = (AsistenciaPK) object;
        if (this.empleado != other.empleado) {
            return false;
        }
        if ((this.fechaingreso == null && other.fechaingreso != null) || (this.fechaingreso != null && !this.fechaingreso.equals(other.fechaingreso))) {
            return false;
        }
        if ((this.horaingreso == null && other.horaingreso != null) || (this.horaingreso != null && !this.horaingreso.equals(other.horaingreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.AsistenciaPK[ empleado=" + empleado + ", fechaingreso=" + fechaingreso + ", horaingreso=" + horaingreso + " ]";
    }
    
}
