/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "estadoplanificacionproduccion")
@NamedQueries({
    @NamedQuery(name = "Estadoplanificacionproduccion.findAll", query = "SELECT e FROM Estadoplanificacionproduccion e"),
    @NamedQuery(name = "Estadoplanificacionproduccion.findById", query = "SELECT e FROM Estadoplanificacionproduccion e WHERE e.id = :id"),
    @NamedQuery(name = "Estadoplanificacionproduccion.findByNombre", query = "SELECT e FROM Estadoplanificacionproduccion e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estadoplanificacionproduccion.findByDescripcion", query = "SELECT e FROM Estadoplanificacionproduccion e WHERE e.descripcion = :descripcion")})
public class Estadoplanificacionproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idestado")
    private List<Planificacionproduccion> planificacionproduccionList;

    public Estadoplanificacionproduccion() {
    }

    public Estadoplanificacionproduccion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Planificacionproduccion> getPlanificacionproduccionList() {
        return planificacionproduccionList;
    }

    public void setPlanificacionproduccionList(List<Planificacionproduccion> planificacionproduccionList) {
        this.planificacionproduccionList = planificacionproduccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadoplanificacionproduccion)) {
            return false;
        }
        Estadoplanificacionproduccion other = (Estadoplanificacionproduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Estadoplanificacionproduccion[ id=" + id + " ]";
    }
    
}
