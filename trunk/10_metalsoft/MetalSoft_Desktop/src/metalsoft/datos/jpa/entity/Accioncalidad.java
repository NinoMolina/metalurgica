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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "accioncalidad")
@NamedQueries({
    @NamedQuery(name = "Accioncalidad.findAll", query = "SELECT a FROM Accioncalidad a"),
    @NamedQuery(name = "Accioncalidad.findByIdaccioncalidad", query = "SELECT a FROM Accioncalidad a WHERE a.idaccioncalidad = :idaccioncalidad"),
    @NamedQuery(name = "Accioncalidad.findByNombre", query = "SELECT a FROM Accioncalidad a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Accioncalidad.findByDescripcion", query = "SELECT a FROM Accioncalidad a WHERE a.descripcion = :descripcion")})
public class Accioncalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accioncalidad_seq")
    @SequenceGenerator(name = "accioncalidad_seq", sequenceName = "accioncalidad_idaccioncalidad_seq", allocationSize = 1)
    @Column(name = "idaccioncalidad")
    private Long idaccioncalidad;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "accioncalidad")
    private List<Procesocalidad> procesocalidadList;

    public Accioncalidad() {
    }

    public Accioncalidad(Long idaccioncalidad) {
        this.idaccioncalidad = idaccioncalidad;
    }

    public Long getIdaccioncalidad() {
        return idaccioncalidad;
    }

    public void setIdaccioncalidad(Long idaccioncalidad) {
        this.idaccioncalidad = idaccioncalidad;
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

    public List<Procesocalidad> getProcesocalidadList() {
        return procesocalidadList;
    }

    public void setProcesocalidadList(List<Procesocalidad> procesocalidadList) {
        this.procesocalidadList = procesocalidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaccioncalidad != null ? idaccioncalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accioncalidad)) {
            return false;
        }
        Accioncalidad other = (Accioncalidad) object;
        if ((this.idaccioncalidad == null && other.idaccioncalidad != null) || (this.idaccioncalidad != null && !this.idaccioncalidad.equals(other.idaccioncalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Accioncalidad[ idaccioncalidad=" + idaccioncalidad + " ]";
    }
    
}
