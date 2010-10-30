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
@Table(name = "provincia")
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.findByIdprovincia", query = "SELECT p FROM Provincia p WHERE p.idprovincia = :idprovincia"),
    @NamedQuery(name = "Provincia.findByNombre", query = "SELECT p FROM Provincia p WHERE p.nombre = :nombre")})
public class Provincia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idprovincia")
    private Long idprovincia;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "provincia")
    private Set<Localidad> localidadSet;
    @OneToMany(mappedBy = "provincia1")
    private Set<Localidad> localidadSet1;

    public Provincia() {
    }

    public Provincia(Long idprovincia) {
        this.idprovincia = idprovincia;
    }

    public Long getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(Long idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Localidad> getLocalidadSet() {
        return localidadSet;
    }

    public void setLocalidadSet(Set<Localidad> localidadSet) {
        this.localidadSet = localidadSet;
    }

    public Set<Localidad> getLocalidadSet1() {
        return localidadSet1;
    }

    public void setLocalidadSet1(Set<Localidad> localidadSet1) {
        this.localidadSet1 = localidadSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprovincia != null ? idprovincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.idprovincia == null && other.idprovincia != null) || (this.idprovincia != null && !this.idprovincia.equals(other.idprovincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Provincia[idprovincia=" + idprovincia + "]";
    }

}
