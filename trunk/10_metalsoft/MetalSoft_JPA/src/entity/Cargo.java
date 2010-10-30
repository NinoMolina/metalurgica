/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "cargo")
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByIdcargo", query = "SELECT c FROM Cargo c WHERE c.idcargo = :idcargo"),
    @NamedQuery(name = "Cargo.findByNombre", query = "SELECT c FROM Cargo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cargo.findByDescripcion", query = "SELECT c FROM Cargo c WHERE c.descripcion = :descripcion")})
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcargo")
    private Long idcargo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "cargo")
    private Set<Empleado> empleadoSet;
    @OneToMany(mappedBy = "cargo1")
    private Set<Empleado> empleadoSet1;

    public Cargo() {
    }

    public Cargo(Long idcargo) {
        this.idcargo = idcargo;
    }

    public Long getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Long idcargo) {
        this.idcargo = idcargo;
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

    public Set<Empleado> getEmpleadoSet() {
        return empleadoSet;
    }

    public void setEmpleadoSet(Set<Empleado> empleadoSet) {
        this.empleadoSet = empleadoSet;
    }

    public Set<Empleado> getEmpleadoSet1() {
        return empleadoSet1;
    }

    public void setEmpleadoSet1(Set<Empleado> empleadoSet1) {
        this.empleadoSet1 = empleadoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcargo != null ? idcargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.idcargo == null && other.idcargo != null) || (this.idcargo != null && !this.idcargo.equals(other.idcargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cargo[idcargo=" + idcargo + "]";
    }

}
