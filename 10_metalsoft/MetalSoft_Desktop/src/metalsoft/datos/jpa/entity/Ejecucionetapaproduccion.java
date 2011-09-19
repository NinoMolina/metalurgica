/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
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
@Table(name = "ejecucionetapaproduccion")
@NamedQueries({
    @NamedQuery(name = "Ejecucionetapaproduccion.findAll", query = "SELECT e FROM Ejecucionetapaproduccion e"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findById", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.id = :id"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByNombre", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByTotalhorasmaquina", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.totalhorasmaquina = :totalhorasmaquina"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByTotalhorashombre", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.totalhorashombre = :totalhorashombre"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByFechainicio", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.fechainicio = :fechainicio"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByHorainicio", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.horainicio = :horainicio"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByFechafin", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.fechafin = :fechafin"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByHorafin", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.horafin = :horafin"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByObservaciones", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.observaciones = :observaciones"),
    @NamedQuery(name = "Ejecucionetapaproduccion.findByNroejecucion", query = "SELECT e FROM Ejecucionetapaproduccion e WHERE e.nroejecucion = :nroejecucion")})
public class Ejecucionetapaproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ejecucionetapaproduccion_seq")
    @SequenceGenerator(name = "ejecucionetapaproduccion_seq", sequenceName = "ejecucionetapaproduccion_idejecucion_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "totalhorasmaquina")
    private String totalhorasmaquina;
    @Column(name = "totalhorashombre")
    private String totalhorashombre;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "nroejecucion")
    private long nroejecucion;
    @JoinColumn(name = "idetapaproduccion", referencedColumnName = "idetapaproduccion")
    @ManyToOne(optional = false)
    private Etapadeproduccion idetapaproduccion;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoejecetapaprod estado;
    @JoinColumn(name = "empleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado empleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejecucionetapa")
    private List<Detalleejecucionplanificacion> detalleejecucionplanificacionList;

    public Ejecucionetapaproduccion() {
    }

    public Ejecucionetapaproduccion(Long id) {
        this.id = id;
    }

    public Ejecucionetapaproduccion(Long id, long nroejecucion) {
        this.id = id;
        this.nroejecucion = nroejecucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTotalhorasmaquina() {
        return totalhorasmaquina;
    }

    public void setTotalhorasmaquina(String totalhorasmaquina) {
        this.totalhorasmaquina = totalhorasmaquina;
    }

    public String getTotalhorashombre() {
        return totalhorashombre;
    }

    public void setTotalhorashombre(String totalhorashombre) {
        this.totalhorashombre = totalhorashombre;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public long getNroejecucion() {
        return nroejecucion;
    }

    public void setNroejecucion(long nroejecucion) {
        this.nroejecucion = nroejecucion;
    }

    public Etapadeproduccion getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(Etapadeproduccion idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    public Estadoejecetapaprod getEstado() {
        return estado;
    }

    public void setEstado(Estadoejecetapaprod estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Detalleejecucionplanificacion> getDetalleejecucionplanificacionList() {
        return detalleejecucionplanificacionList;
    }

    public void setDetalleejecucionplanificacionList(List<Detalleejecucionplanificacion> detalleejecucionplanificacionList) {
        this.detalleejecucionplanificacionList = detalleejecucionplanificacionList;
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
        if (!(object instanceof Ejecucionetapaproduccion)) {
            return false;
        }
        Ejecucionetapaproduccion other = (Ejecucionetapaproduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Ejecucionetapaproduccion[ id=" + id + " ]";
    }
    
}
