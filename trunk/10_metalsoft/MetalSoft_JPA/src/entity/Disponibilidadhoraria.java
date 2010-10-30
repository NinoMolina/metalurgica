/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "disponibilidadhoraria")
@NamedQueries({
    @NamedQuery(name = "Disponibilidadhoraria.findAll", query = "SELECT d FROM Disponibilidadhoraria d"),
    @NamedQuery(name = "Disponibilidadhoraria.findById", query = "SELECT d FROM Disponibilidadhoraria d WHERE d.id = :id"),
    @NamedQuery(name = "Disponibilidadhoraria.findByFecha", query = "SELECT d FROM Disponibilidadhoraria d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "Disponibilidadhoraria.findByTiempodisponible", query = "SELECT d FROM Disponibilidadhoraria d WHERE d.tiempodisponible = :tiempodisponible")})
public class Disponibilidadhoraria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "tiempodisponible")
    @Temporal(TemporalType.TIME)
    private Date tiempodisponible;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado idempleado;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado idempleado1;

    public Disponibilidadhoraria() {
    }

    public Disponibilidadhoraria(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getTiempodisponible() {
        return tiempodisponible;
    }

    public void setTiempodisponible(Date tiempodisponible) {
        this.tiempodisponible = tiempodisponible;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    public Empleado getIdempleado1() {
        return idempleado1;
    }

    public void setIdempleado1(Empleado idempleado1) {
        this.idempleado1 = idempleado1;
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
        if (!(object instanceof Disponibilidadhoraria)) {
            return false;
        }
        Disponibilidadhoraria other = (Disponibilidadhoraria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Disponibilidadhoraria[id=" + id + "]";
    }

}
