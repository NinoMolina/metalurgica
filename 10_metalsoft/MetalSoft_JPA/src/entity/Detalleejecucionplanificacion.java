/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detalleejecucionplanificacion")
@NamedQueries({
    @NamedQuery(name = "Detalleejecucionplanificacion.findAll", query = "SELECT d FROM Detalleejecucionplanificacion d"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findById", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.id = :id"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByPieza", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.pieza = :pieza"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByFechainicio", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.fechainicio = :fechainicio"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByFechafin", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.fechafin = :fechafin"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByHorainicio", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.horainicio = :horainicio"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByHorafin", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.horafin = :horafin")})
public class Detalleejecucionplanificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "pieza")
    private BigInteger pieza;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @OneToMany(mappedBy = "iddetalleejecucionplanificacion")
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet;
    @JoinColumns({
        @JoinColumn(name = "ejecucionetapa", referencedColumnName = "idejecucion"),
        @JoinColumn(name = "idetapaproduccion", referencedColumnName = "idetapaproduccion")})
    @ManyToOne(optional = false)
    private Ejecucionetapaproduccion ejecucionetapaproduccion;
    @JoinColumn(name = "idejecucionplanificacionproduccion", referencedColumnName = "idejecucion")
    @ManyToOne(optional = false)
    private Ejecucionplanificacionproduccion idejecucionplanificacionproduccion;
    @JoinColumn(name = "piezareal", referencedColumnName = "idpiezareal")
    @ManyToOne
    private Piezareal piezareal;

    public Detalleejecucionplanificacion() {
    }

    public Detalleejecucionplanificacion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getPieza() {
        return pieza;
    }

    public void setPieza(BigInteger pieza) {
        this.pieza = pieza;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccionSet() {
        return detalleplanificacionproduccionSet;
    }

    public void setDetalleplanificacionproduccionSet(Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet) {
        this.detalleplanificacionproduccionSet = detalleplanificacionproduccionSet;
    }

    public Ejecucionetapaproduccion getEjecucionetapaproduccion() {
        return ejecucionetapaproduccion;
    }

    public void setEjecucionetapaproduccion(Ejecucionetapaproduccion ejecucionetapaproduccion) {
        this.ejecucionetapaproduccion = ejecucionetapaproduccion;
    }

    public Ejecucionplanificacionproduccion getIdejecucionplanificacionproduccion() {
        return idejecucionplanificacionproduccion;
    }

    public void setIdejecucionplanificacionproduccion(Ejecucionplanificacionproduccion idejecucionplanificacionproduccion) {
        this.idejecucionplanificacionproduccion = idejecucionplanificacionproduccion;
    }

    public Piezareal getPiezareal() {
        return piezareal;
    }

    public void setPiezareal(Piezareal piezareal) {
        this.piezareal = piezareal;
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
        if (!(object instanceof Detalleejecucionplanificacion)) {
            return false;
        }
        Detalleejecucionplanificacion other = (Detalleejecucionplanificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Detalleejecucionplanificacion[id=" + id + "]";
    }

}
