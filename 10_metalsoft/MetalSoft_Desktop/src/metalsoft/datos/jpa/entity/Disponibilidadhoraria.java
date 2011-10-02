/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disponibilidadhoraria_seq")
    @SequenceGenerator(name = "disponibilidadhoraria_seq", sequenceName = "disponibilidadhoraria_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @OrderBy
    private Date fecha;
    @Column(name = "tiempodisponible")
    @Temporal(TemporalType.TIME)
    private Date tiempodisponible;
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    @OrderBy
    private Date horainicio;
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado idempleado;

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

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
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
        return "metalsoft.datos.jpa.entity.Disponibilidadhoraria[ id=" + id + " ]";
    }
}
