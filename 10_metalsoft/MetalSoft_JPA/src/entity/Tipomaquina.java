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
@Table(name = "tipomaquina")
@NamedQueries({
    @NamedQuery(name = "Tipomaquina.findAll", query = "SELECT t FROM Tipomaquina t"),
    @NamedQuery(name = "Tipomaquina.findByIdtipomaquina", query = "SELECT t FROM Tipomaquina t WHERE t.idtipomaquina = :idtipomaquina"),
    @NamedQuery(name = "Tipomaquina.findByNombre", query = "SELECT t FROM Tipomaquina t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipomaquina.findByDescripcion", query = "SELECT t FROM Tipomaquina t WHERE t.descripcion = :descripcion")})
public class Tipomaquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipomaquina")
    private Long idtipomaquina;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tipomaquina")
    private Set<Maquina> maquinaSet;
    @OneToMany(mappedBy = "tipomaquina1")
    private Set<Maquina> maquinaSet1;

    public Tipomaquina() {
    }

    public Tipomaquina(Long idtipomaquina) {
        this.idtipomaquina = idtipomaquina;
    }

    public Long getIdtipomaquina() {
        return idtipomaquina;
    }

    public void setIdtipomaquina(Long idtipomaquina) {
        this.idtipomaquina = idtipomaquina;
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

    public Set<Maquina> getMaquinaSet() {
        return maquinaSet;
    }

    public void setMaquinaSet(Set<Maquina> maquinaSet) {
        this.maquinaSet = maquinaSet;
    }

    public Set<Maquina> getMaquinaSet1() {
        return maquinaSet1;
    }

    public void setMaquinaSet1(Set<Maquina> maquinaSet1) {
        this.maquinaSet1 = maquinaSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipomaquina != null ? idtipomaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomaquina)) {
            return false;
        }
        Tipomaquina other = (Tipomaquina) object;
        if ((this.idtipomaquina == null && other.idtipomaquina != null) || (this.idtipomaquina != null && !this.idtipomaquina.equals(other.idtipomaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tipomaquina[idtipomaquina=" + idtipomaquina + "]";
    }

}
