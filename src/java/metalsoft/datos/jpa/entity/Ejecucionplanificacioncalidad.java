/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByIdejecucion", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.idejecucion = :idejecucion"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByFechainicio", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.fechainicio = :fechainicio"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByFechafin", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.fechafin = :fechafin"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByHorainicio", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.horainicio = :horainicio"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByHorafin", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.horafin = :horafin"),
    @NamedQuery(name = "Ejecucionplanificacioncalidad.findByNroejecucionplanificacioncalidad", query = "SELECT e FROM Ejecucionplanificacioncalidad e WHERE e.nroejecucionplanificacioncalidad = :nroejecucionplanificacioncalidad")})
public class Ejecucionplanificacioncalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ejecucionplanificacioncalidad_seq")
    @SequenceGenerator(name = "ejecucionplanificacioncalidad_seq", sequenceName = "ejecucionplanificacioncalidad_idejecucion_seq", allocationSize = 1)
    @Column(name = "idejecucion")
    private Long idejecucion;
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
    @Column(name = "nroejecucionplanificacioncalidad")
    private BigInteger nroejecucionplanificacioncalidad;
    @JoinColumn(name = "idplanificacioncalidad", referencedColumnName = "idplanificacion")
    @OneToOne(optional = false)
    private Planificacioncalidad idplanificacioncalidad;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoejecplancalidad estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idejecucionplanificacioncalidad")
    private List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList;

    public Ejecucionplanificacioncalidad() {
    }

    public Ejecucionplanificacioncalidad(Long idejecucion) {
        this.idejecucion = idejecucion;
    }

    public Long getIdejecucion() {
        return idejecucion;
    }

    public void setIdejecucion(Long idejecucion) {
        this.idejecucion = idejecucion;
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

    public BigInteger getNroejecucionplanificacioncalidad() {
        return nroejecucionplanificacioncalidad;
    }

    public void setNroejecucionplanificacioncalidad(BigInteger nroejecucionplanificacioncalidad) {
        this.nroejecucionplanificacioncalidad = nroejecucionplanificacioncalidad;
    }

    public Planificacioncalidad getIdplanificacioncalidad() {
        return idplanificacioncalidad;
    }

    public void setIdplanificacioncalidad(Planificacioncalidad idplanificacioncalidad) {
        this.idplanificacioncalidad = idplanificacioncalidad;
    }

    public Estadoejecplancalidad getEstado() {
        return estado;
    }

    public void setEstado(Estadoejecplancalidad estado) {
        this.estado = estado;
    }

    public List<Detalleejecucionplanificacioncalidad> getDetalleejecucionplanificacioncalidadList() {
        return detalleejecucionplanificacioncalidadList;
    }

    public void setDetalleejecucionplanificacioncalidadList(List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList) {
        this.detalleejecucionplanificacioncalidadList = detalleejecucionplanificacioncalidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idejecucion != null ? idejecucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejecucionplanificacioncalidad)) {
            return false;
        }
        Ejecucionplanificacioncalidad other = (Ejecucionplanificacioncalidad) object;
        if ((this.idejecucion == null && other.idejecucion != null) || (this.idejecucion != null && !this.idejecucion.equals(other.idejecucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad[ idejecucion=" + idejecucion + " ]";
    }
    
}
