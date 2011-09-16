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
@Table(name = "tipomaterial")
@NamedQueries({
    @NamedQuery(name = "Tipomaterial.findAll", query = "SELECT t FROM Tipomaterial t"),
    @NamedQuery(name = "Tipomaterial.findByIdtipomaterial", query = "SELECT t FROM Tipomaterial t WHERE t.idtipomaterial = :idtipomaterial"),
    @NamedQuery(name = "Tipomaterial.findByNombre", query = "SELECT t FROM Tipomaterial t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipomaterial.findByDescripcion", query = "SELECT t FROM Tipomaterial t WHERE t.descripcion = :descripcion")})
public class Tipomaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipomaterial")
    private Long idtipomaterial;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tipomaterial")
    private List<Materiaprima> materiaprimaList;
    @OneToMany(mappedBy = "tipomaterial")
    private List<Matriz> matrizList;

    public Tipomaterial() {
    }

    public Tipomaterial(Long idtipomaterial) {
        this.idtipomaterial = idtipomaterial;
    }

    public Long getIdtipomaterial() {
        return idtipomaterial;
    }

    public void setIdtipomaterial(Long idtipomaterial) {
        this.idtipomaterial = idtipomaterial;
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

    public List<Materiaprima> getMateriaprimaList() {
        return materiaprimaList;
    }

    public void setMateriaprimaList(List<Materiaprima> materiaprimaList) {
        this.materiaprimaList = materiaprimaList;
    }

    public List<Matriz> getMatrizList() {
        return matrizList;
    }

    public void setMatrizList(List<Matriz> matrizList) {
        this.matrizList = matrizList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipomaterial != null ? idtipomaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomaterial)) {
            return false;
        }
        Tipomaterial other = (Tipomaterial) object;
        if ((this.idtipomaterial == null && other.idtipomaterial != null) || (this.idtipomaterial != null && !this.idtipomaterial.equals(other.idtipomaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Tipomaterial[ idtipomaterial=" + idtipomaterial + " ]";
    }
    
}
