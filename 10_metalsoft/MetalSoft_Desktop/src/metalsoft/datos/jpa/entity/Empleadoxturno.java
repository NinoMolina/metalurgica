/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "empleadoxturno")
@NamedQueries({
    @NamedQuery(name = "Empleadoxturno.findAll", query = "SELECT e FROM Empleadoxturno e"),
    @NamedQuery(name = "Empleadoxturno.findByIdempleado", query = "SELECT e FROM Empleadoxturno e WHERE e.empleadoxturnoPK.idempleado = :idempleado"),
    @NamedQuery(name = "Empleadoxturno.findByIdturno", query = "SELECT e FROM Empleadoxturno e WHERE e.empleadoxturnoPK.idturno = :idturno")})
public class Empleadoxturno implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmpleadoxturnoPK empleadoxturnoPK;
    @JoinColumn(name = "idturno", referencedColumnName = "idturno", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Turno turno;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Empleadoxturno() {
    }

    public Empleadoxturno(EmpleadoxturnoPK empleadoxturnoPK) {
        this.empleadoxturnoPK = empleadoxturnoPK;
    }

    public Empleadoxturno(long idempleado, long idturno) {
        this.empleadoxturnoPK = new EmpleadoxturnoPK(idempleado, idturno);
    }

    public EmpleadoxturnoPK getEmpleadoxturnoPK() {
        return empleadoxturnoPK;
    }

    public void setEmpleadoxturnoPK(EmpleadoxturnoPK empleadoxturnoPK) {
        this.empleadoxturnoPK = empleadoxturnoPK;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleadoxturnoPK != null ? empleadoxturnoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleadoxturno)) {
            return false;
        }
        Empleadoxturno other = (Empleadoxturno) object;
        if ((this.empleadoxturnoPK == null && other.empleadoxturnoPK != null) || (this.empleadoxturnoPK != null && !this.empleadoxturnoPK.equals(other.empleadoxturnoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Empleadoxturno[ empleadoxturnoPK=" + empleadoxturnoPK + " ]";
    }
    
}
