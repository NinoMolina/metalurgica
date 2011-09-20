/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "ejecucionprocesocalidad")
@NamedQueries({
    @NamedQuery(name = "Ejecucionprocesocalidad.findAll", query = "SELECT e FROM Ejecucionprocesocalidad e"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByIdejecucion", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.idejecucion = :idejecucion"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByResultado", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.resultado = :resultado"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByNroejecucion", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.nroejecucion = :nroejecucion"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByFechafin", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.fechafin = :fechafin"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByFechainicio", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.fechainicio = :fechainicio"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByHorainicio", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.horainicio = :horainicio"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByHorafin", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.horafin = :horafin"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByNombre", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByObservacion", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.observacion = :observacion")})
public class Ejecucionprocesocalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ejecucionprocesocalidad_seq")
    @SequenceGenerator(name = "ejecucionprocesocalidad_seq", sequenceName = "ejecucionprocesocalidad_idejecucion_seq", allocationSize = 1)
    @Column(name = "idejecucion")
    private Long idejecucion;
    @Column(name = "resultado")
    private String resultado;
    @Column(name = "nroejecucion")
    private BigInteger nroejecucion;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoejecucionprocesocalidad estado;

    public Ejecucionprocesocalidad() {
    }

    public Ejecucionprocesocalidad(Long idejecucion) {
        this.idejecucion = idejecucion;
    }

    public Long getIdejecucion() {
        return idejecucion;
    }

    public void setIdejecucion(Long idejecucion) {
        this.idejecucion = idejecucion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public BigInteger getNroejecucion() {
        return nroejecucion;
    }

    public void setNroejecucion(BigInteger nroejecucion) {
        this.nroejecucion = nroejecucion;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
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

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Estadoejecucionprocesocalidad getEstado() {
        return estado;
    }

    public void setEstado(Estadoejecucionprocesocalidad estado) {
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
        if (!(object instanceof Ejecucionprocesocalidad)) {
            return false;
        }
        Ejecucionprocesocalidad other = (Ejecucionprocesocalidad) object;
        if ((this.idejecucion == null && other.idejecucion != null) || (this.idejecucion != null && !this.idejecucion.equals(other.idejecucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.tmp.Ejecucionprocesocalidad[ idejecucion=" + idejecucion + " ]";
    }
    
}
