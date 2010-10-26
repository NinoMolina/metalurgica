/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "ejecucionplanificacioncalidad")
@NamedQueries({
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findAll", query = "SELECT e FROM Ejecucionplanificacioncalidad e"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByIdejecucion", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.ejecucionplanificacioncalidadPK.idejecucion = :idejecucion"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByIdplanificacioncalidad", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.ejecucionplanificacioncalidadPK.idplanificacioncalidad = :idplanificacioncalidad"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByFechainicio", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.fechainicio = :fechainicio"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByFechafin", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.fechafin = :fechafin"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByHorainicio", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.horainicio = :horainicio"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByHorafin", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.horafin = :horafin")})
public class Ejecucionplanificacioncalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EjecucionplanificacioncalidadPK ejecucionplanificacioncalidadPK;
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
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoejecplancalidad estado;
    @JoinColumn(name = "idplanificacioncalidad", referencedColumnName = "idplanificacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Planificacioncalidad planificacioncalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejecucionplanificacioncalidad")
    private Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet;

    public Ejecucionplanificacioncalidad() {
    }

    public Ejecucionplanificacioncalidad(EjecucionplanificacioncalidadPK ejecucionplanificacioncalidadPK) {
        this.ejecucionplanificacioncalidadPK = ejecucionplanificacioncalidadPK;
    }

    public Ejecucionplanificacioncalidad(long idejecucion, long idplanificacioncalidad) {
        this.ejecucionplanificacioncalidadPK = new EjecucionplanificacioncalidadPK(idejecucion, idplanificacioncalidad);
    }

    public EjecucionplanificacioncalidadPK getEjecucionplanificacioncalidadPK() {
        return ejecucionplanificacioncalidadPK;
    }

    public void setEjecucionplanificacioncalidadPK(EjecucionplanificacioncalidadPK ejecucionplanificacioncalidadPK) {
        this.ejecucionplanificacioncalidadPK = ejecucionplanificacioncalidadPK;
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

    public Estadoejecplancalidad getEstado() {
        return estado;
    }

    public void setEstado(Estadoejecplancalidad estado) {
        this.estado = estado;
    }

    public Planificacioncalidad getPlanificacioncalidad() {
        return planificacioncalidad;
    }

    public void setPlanificacioncalidad(Planificacioncalidad planificacioncalidad) {
        this.planificacioncalidad = planificacioncalidad;
    }

    public Set<Detalleejecucionplanificacioncalidad> getDetalleejecucionplanificacioncalidadSet() {
        return detalleejecucionplanificacioncalidadSet;
    }

    public void setDetalleejecucionplanificacioncalidadSet(Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet) {
        this.detalleejecucionplanificacioncalidadSet = detalleejecucionplanificacioncalidadSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejecucionplanificacioncalidadPK != null ? ejecucionplanificacioncalidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejecucionplanificacioncalidad)) {
            return false;
        }
        Ejecucionplanificacioncalidad other = (Ejecucionplanificacioncalidad) object;
        if ((this.ejecucionplanificacioncalidadPK == null && other.ejecucionplanificacioncalidadPK != null) || (this.ejecucionplanificacioncalidadPK != null && !this.ejecucionplanificacioncalidadPK.equals(other.ejecucionplanificacioncalidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ejecucionplanificacioncalidad[ejecucionplanificacioncalidadPK=" + ejecucionplanificacioncalidadPK + "]";
    }

}
