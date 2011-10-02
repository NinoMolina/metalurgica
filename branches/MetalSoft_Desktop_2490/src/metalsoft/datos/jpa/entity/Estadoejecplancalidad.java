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
@Table(name = "estadoejecplancalidad")
@NamedQueries({
    @NamedQuery(name = "Estadoejecplancalidad.findAll", query = "SELECT e FROM Estadoejecplancalidad e"),
    @NamedQuery(name = "Estadoejecplancalidad.findByIdestado", query = "SELECT e FROM Estadoejecplancalidad e WHERE e.idestado = :idestado"),
    @NamedQuery(name = "Estadoejecplancalidad.findByNombre", query = "SELECT e FROM Estadoejecplancalidad e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estadoejecplancalidad.findByDescripcion", query = "SELECT e FROM Estadoejecplancalidad e WHERE e.descripcion = :descripcion")})
public class Estadoejecplancalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idestado")
    private Long idestado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "estado")
    private List<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadList;


    public Estadoejecplancalidad() {
    }

    public Estadoejecplancalidad(Long idestado) {
        this.idestado = idestado;
    }

    public Long getIdestado() {
        return idestado;
    }

    public void setIdestado(Long idestado) {
        this.idestado = idestado;
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

    public List<Ejecucionplanificacioncalidad> getEjecucionplanificacioncalidadList() {
        return ejecucionplanificacioncalidadList;
    }

    public void setEjecucionplanificacioncalidadList(List<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadList) {
        this.ejecucionplanificacioncalidadList = ejecucionplanificacioncalidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestado != null ? idestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadoejecplancalidad)) {
            return false;
        }
        Estadoejecplancalidad other = (Estadoejecplancalidad) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Estadoejecplancalidad[idestado=" + idestado + "]";
    }

}