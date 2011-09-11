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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "ejecucionplanificacionproduccion")
@NamedQueries({
    @NamedQuery(name = "Ejecucionplanificacionproduccion.findAll", query = "SELECT e FROM Ejecucionplanificacionproduccion e"),
    @NamedQuery(name = "Ejecucionplanificacionproduccion.findByIdejecucion", query = "SELECT e FROM Ejecucionplanificacionproduccion e WHERE e.idejecucion = :idejecucion"),
    @NamedQuery(name = "Ejecucionplanificacionproduccion.findByFechainicio", query = "SELECT e FROM Ejecucionplanificacionproduccion e WHERE e.fechainicio = :fechainicio"),
    @NamedQuery(name = "Ejecucionplanificacionproduccion.findByFechafin", query = "SELECT e FROM Ejecucionplanificacionproduccion e WHERE e.fechafin = :fechafin"),
    @NamedQuery(name = "Ejecucionplanificacionproduccion.findByHorainicio", query = "SELECT e FROM Ejecucionplanificacionproduccion e WHERE e.horainicio = :horainicio"),
    @NamedQuery(name = "Ejecucionplanificacionproduccion.findByHorafin", query = "SELECT e FROM Ejecucionplanificacionproduccion e WHERE e.horafin = :horafin"),
    @NamedQuery(name = "Ejecucionplanificacionproduccion.findByNroejecucionplanificacion", query = "SELECT e FROM Ejecucionplanificacionproduccion e WHERE e.nroejecucionplanificacion = :nroejecucionplanificacion")})
public class Ejecucionplanificacionproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ejecucionplanificacionproduccion_seq")
    @SequenceGenerator(name = "ejecucionplanificacionproduccion_seq", sequenceName = "cliente_idejecucion_seq", allocationSize = 1)
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
    @Column(name = "nroejecucionplanificacion")
    private BigInteger nroejecucionplanificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idejecucionplanificacionproduccion")
    private List<Detalleejecucionplanificacion> detalleejecucionplanificacionList;
    @JoinColumn(name = "idplanificacionproduccion", referencedColumnName = "idplanificacionproduccion")
    @ManyToOne(optional = false)
    private Planificacionproduccion idplanificacionproduccion;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoejecplanifpedido estado;

    public Ejecucionplanificacionproduccion() {
    }

    public Ejecucionplanificacionproduccion(Long idejecucion) {
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

    public BigInteger getNroejecucionplanificacion() {
        return nroejecucionplanificacion;
    }

    public void setNroejecucionplanificacion(BigInteger nroejecucionplanificacion) {
        this.nroejecucionplanificacion = nroejecucionplanificacion;
    }

    public List<Detalleejecucionplanificacion> getDetalleejecucionplanificacionList() {
        return detalleejecucionplanificacionList;
    }

    public void setDetalleejecucionplanificacionList(List<Detalleejecucionplanificacion> detalleejecucionplanificacionList) {
        this.detalleejecucionplanificacionList = detalleejecucionplanificacionList;
    }

    public Planificacionproduccion getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(Planificacionproduccion idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    public Estadoejecplanifpedido getEstado() {
        return estado;
    }

    public void setEstado(Estadoejecplanifpedido estado) {
        this.estado = estado;
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
        if (!(object instanceof Ejecucionplanificacionproduccion)) {
            return false;
        }
        Ejecucionplanificacionproduccion other = (Ejecucionplanificacionproduccion) object;
        if ((this.idejecucion == null && other.idejecucion != null) || (this.idejecucion != null && !this.idejecucion.equals(other.idejecucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion[ idejecucion=" + idejecucion + " ]";
    }
    
}
