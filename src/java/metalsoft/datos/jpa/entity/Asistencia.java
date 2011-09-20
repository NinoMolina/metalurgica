/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "asistencia")
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByEmpleado", query = "SELECT a FROM Asistencia a WHERE a.asistenciaPK.empleado = :empleado"),
    @NamedQuery(name = "Asistencia.findByFechaingreso", query = "SELECT a FROM Asistencia a WHERE a.asistenciaPK.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "Asistencia.findByHoraingreso", query = "SELECT a FROM Asistencia a WHERE a.asistenciaPK.horaingreso = :horaingreso"),
    @NamedQuery(name = "Asistencia.findByHoraegreso", query = "SELECT a FROM Asistencia a WHERE a.horaegreso = :horaegreso"),
    @NamedQuery(name = "Asistencia.findByObservaciones", query = "SELECT a FROM Asistencia a WHERE a.observaciones = :observaciones")})
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsistenciaPK asistenciaPK;
    @Column(name = "horaegreso")
    @Temporal(TemporalType.TIME)
    private Date horaegreso;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "empleado", referencedColumnName = "idempleado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado1;

    public Asistencia() {
    }

    public Asistencia(AsistenciaPK asistenciaPK) {
        this.asistenciaPK = asistenciaPK;
    }

    public Asistencia(long empleado, Date fechaingreso, Date horaingreso) {
        this.asistenciaPK = new AsistenciaPK(empleado, fechaingreso, horaingreso);
    }

    public AsistenciaPK getAsistenciaPK() {
        return asistenciaPK;
    }

    public void setAsistenciaPK(AsistenciaPK asistenciaPK) {
        this.asistenciaPK = asistenciaPK;
    }

    public Date getHoraegreso() {
        return horaegreso;
    }

    public void setHoraegreso(Date horaegreso) {
        this.horaegreso = horaegreso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Empleado getEmpleado1() {
        return empleado1;
    }

    public void setEmpleado1(Empleado empleado1) {
        this.empleado1 = empleado1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asistenciaPK != null ? asistenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.asistenciaPK == null && other.asistenciaPK != null) || (this.asistenciaPK != null && !this.asistenciaPK.equals(other.asistenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Asistencia[ asistenciaPK=" + asistenciaPK + " ]";
    }
    
}
