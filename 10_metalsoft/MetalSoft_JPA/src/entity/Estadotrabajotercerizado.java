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
@Table(name = "estadotrabajotercerizado")
@NamedQueries({
    @NamedQuery(name = "Estadotrabajotercerizado.findAll", query = "SELECT e FROM Estadotrabajotercerizado e"),
    @NamedQuery(name = "Estadotrabajotercerizado.findByIdestado", query = "SELECT e FROM Estadotrabajotercerizado e WHERE e.idestado = :idestado"),
    @NamedQuery(name = "Estadotrabajotercerizado.findByNombre", query = "SELECT e FROM Estadotrabajotercerizado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estadotrabajotercerizado.findByDescripcion", query = "SELECT e FROM Estadotrabajotercerizado e WHERE e.descripcion = :descripcion")})
public class Estadotrabajotercerizado implements Serializable {
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
    private Set<Trabajotercerizado> trabajotercerizadoSet;

    public Estadotrabajotercerizado() {
    }

    public Estadotrabajotercerizado(Long idestado) {
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

    public Set<Trabajotercerizado> getTrabajotercerizadoSet() {
        return trabajotercerizadoSet;
    }

    public void setTrabajotercerizadoSet(Set<Trabajotercerizado> trabajotercerizadoSet) {
        this.trabajotercerizadoSet = trabajotercerizadoSet;
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
        if (!(object instanceof Estadotrabajotercerizado)) {
            return false;
        }
        Estadotrabajotercerizado other = (Estadotrabajotercerizado) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Estadotrabajotercerizado[idestado=" + idestado + "]";
    }

}
