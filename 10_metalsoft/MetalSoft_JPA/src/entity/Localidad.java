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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "localidad")
@NamedQueries({
    @NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l"),
    @NamedQuery(name = "Localidad.findByIdlocalidad", query = "SELECT l FROM Localidad l WHERE l.idlocalidad = :idlocalidad"),
    @NamedQuery(name = "Localidad.findByNombre", query = "SELECT l FROM Localidad l WHERE l.nombre = :nombre")})
public class Localidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idlocalidad")
    private Long idlocalidad;
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "provincia", referencedColumnName = "idprovincia")
    @ManyToOne
    private Provincia provincia;
    @JoinColumn(name = "provincia", referencedColumnName = "idprovincia")
    @ManyToOne
    private Provincia provincia1;
    @OneToMany(mappedBy = "localidad")
    private Set<Barrio> barrioSet;
    @OneToMany(mappedBy = "localidad1")
    private Set<Barrio> barrioSet1;

    public Localidad() {
    }

    public Localidad(Long idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public Long getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(Long idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Provincia getProvincia1() {
        return provincia1;
    }

    public void setProvincia1(Provincia provincia1) {
        this.provincia1 = provincia1;
    }

    public Set<Barrio> getBarrioSet() {
        return barrioSet;
    }

    public void setBarrioSet(Set<Barrio> barrioSet) {
        this.barrioSet = barrioSet;
    }

    public Set<Barrio> getBarrioSet1() {
        return barrioSet1;
    }

    public void setBarrioSet1(Set<Barrio> barrioSet1) {
        this.barrioSet1 = barrioSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocalidad != null ? idlocalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.idlocalidad == null && other.idlocalidad != null) || (this.idlocalidad != null && !this.idlocalidad.equals(other.idlocalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Localidad[idlocalidad=" + idlocalidad + "]";
    }

}