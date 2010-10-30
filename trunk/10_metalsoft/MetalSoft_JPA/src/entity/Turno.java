/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "turno")
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findByIdturno", query = "SELECT t FROM Turno t WHERE t.idturno = :idturno"),
    @NamedQuery(name = "Turno.findByHorainicio", query = "SELECT t FROM Turno t WHERE t.horainicio = :horainicio"),
    @NamedQuery(name = "Turno.findByHorafin", query = "SELECT t FROM Turno t WHERE t.horafin = :horafin"),
    @NamedQuery(name = "Turno.findByNombre", query = "SELECT t FROM Turno t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Turno.findByDescripcion", query = "SELECT t FROM Turno t WHERE t.descripcion = :descripcion")})
public class Turno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idturno")
    private Long idturno;
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turno")
    private Set<Empleadoxturno> empleadoxturnoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turno1")
    private Set<Empleadoxturno> empleadoxturnoSet1;

    public Turno() {
    }

    public Turno(Long idturno) {
        this.idturno = idturno;
    }

    public Long getIdturno() {
        return idturno;
    }

    public void setIdturno(Long idturno) {
        this.idturno = idturno;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Empleadoxturno> getEmpleadoxturnoSet() {
        return empleadoxturnoSet;
    }

    public void setEmpleadoxturnoSet(Set<Empleadoxturno> empleadoxturnoSet) {
        this.empleadoxturnoSet = empleadoxturnoSet;
    }

    public Set<Empleadoxturno> getEmpleadoxturnoSet1() {
        return empleadoxturnoSet1;
    }

    public void setEmpleadoxturnoSet1(Set<Empleadoxturno> empleadoxturnoSet1) {
        this.empleadoxturnoSet1 = empleadoxturnoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idturno != null ? idturno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.idturno == null && other.idturno != null) || (this.idturno != null && !this.idturno.equals(other.idturno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Turno[idturno=" + idturno + "]";
    }

}
