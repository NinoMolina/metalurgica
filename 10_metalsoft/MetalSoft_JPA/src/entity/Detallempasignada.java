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
@Table(name = "detallempasignada")
@NamedQueries({
    @NamedQuery(name = "Detallempasignada.findAll", query = "SELECT d FROM Detallempasignada d"),
    @NamedQuery(name = "Detallempasignada.findById", query = "SELECT d FROM Detallempasignada d WHERE d.id = :id"),
    @NamedQuery(name = "Detallempasignada.findByCantidadmp", query = "SELECT d FROM Detallempasignada d WHERE d.cantidadmp = :cantidadmp")})
public class Detallempasignada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "cantidadmp")
    private Integer cantidadmp;
    @JoinColumn(name = "idmateriaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima idmateriaprima;
    @JoinColumn(name = "idmateriaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima idmateriaprima1;
    @JoinColumn(name = "idplanificacionproduccion", referencedColumnName = "idplanificacionproduccion")
    @ManyToOne
    private Planificacionproduccion idplanificacionproduccion;
    @JoinColumn(name = "idplanificacionproduccion", referencedColumnName = "idplanificacionproduccion")
    @ManyToOne
    private Planificacionproduccion idplanificacionproduccion1;
    @OneToMany(mappedBy = "iddetallempasignada")
    private Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet;
    @OneToMany(mappedBy = "iddetallempasignada1")
    private Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet1;

    public Detallempasignada() {
    }

    public Detallempasignada(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadmp() {
        return cantidadmp;
    }

    public void setCantidadmp(Integer cantidadmp) {
        this.cantidadmp = cantidadmp;
    }

    public Materiaprima getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(Materiaprima idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public Materiaprima getIdmateriaprima1() {
        return idmateriaprima1;
    }

    public void setIdmateriaprima1(Materiaprima idmateriaprima1) {
        this.idmateriaprima1 = idmateriaprima1;
    }

    public Planificacionproduccion getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(Planificacionproduccion idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    public Planificacionproduccion getIdplanificacionproduccion1() {
        return idplanificacionproduccion1;
    }

    public void setIdplanificacionproduccion1(Planificacionproduccion idplanificacionproduccion1) {
        this.idplanificacionproduccion1 = idplanificacionproduccion1;
    }

    public Set<Mpasignadaxpiezareal> getMpasignadaxpiezarealSet() {
        return mpasignadaxpiezarealSet;
    }

    public void setMpasignadaxpiezarealSet(Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet) {
        this.mpasignadaxpiezarealSet = mpasignadaxpiezarealSet;
    }

    public Set<Mpasignadaxpiezareal> getMpasignadaxpiezarealSet1() {
        return mpasignadaxpiezarealSet1;
    }

    public void setMpasignadaxpiezarealSet1(Set<Mpasignadaxpiezareal> mpasignadaxpiezarealSet1) {
        this.mpasignadaxpiezarealSet1 = mpasignadaxpiezarealSet1;
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
        if (!(object instanceof Detallempasignada)) {
            return false;
        }
        Detallempasignada other = (Detallempasignada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Detallempasignada[id=" + id + "]";
    }

}
