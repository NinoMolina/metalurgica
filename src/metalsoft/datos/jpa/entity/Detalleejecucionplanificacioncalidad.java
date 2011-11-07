/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "detalleejecucionplanificacioncalidad")
@NamedQueries({
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findAll", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByIddetalle", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByOrden", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.orden = :orden"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByFechainicio", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.fechainicio = :fechainicio"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByHorainicio", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.horainicio = :horainicio"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByFechafin", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.fechafin = :fechafin"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByHorafin", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.horafin = :horafin")})
public class Detalleejecucionplanificacioncalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalleejecucionplanificacioncalidad_seq")
    @SequenceGenerator(name = "detalleejecucionplanificacioncalidad_seq", sequenceName = "detalleejecucionplanificacioncalidad_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "orden")
    private Integer orden;
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
    @JoinColumn(name = "idprocesocalidad", referencedColumnName = "idprocesocalidad")
    @ManyToOne
    private Procesocalidad idprocesocalidad;
    @JoinColumn(name = "piezareal", referencedColumnName = "idpiezareal")
    @ManyToOne
    private Piezareal piezareal;
    @JoinColumn(name = "pieza", referencedColumnName = "idpieza")
    @ManyToOne
    private Pieza pieza;
    @JoinColumn(name = "ejecucionprocesocalidad", referencedColumnName = "idejecucion")
    @ManyToOne
    private Ejecucionprocesocalidad ejecucionprocesocalidad;
    @JoinColumn(name = "idejecucionplanificacioncalidad", referencedColumnName = "idejecucion")
    @ManyToOne(optional = false)
    private Ejecucionplanificacioncalidad idejecucionplanificacioncalidad;
    @OneToMany(mappedBy = "iddetalleejecucionplanificacioncalidad")
    private List<Detalleplanificacioncalidad> detalleplanificacioncalidadList;

    public Detalleejecucionplanificacioncalidad() {
    }

    public Detalleejecucionplanificacioncalidad(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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

    public Ejecucionprocesocalidad getEjecucionprocesocalidad() {
        return ejecucionprocesocalidad;
    }

    public void setEjecucionprocesocalidad(Ejecucionprocesocalidad ejecucionprocesocalidad) {
        this.ejecucionprocesocalidad = ejecucionprocesocalidad;
    }

    public Procesocalidad getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(Procesocalidad idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    public Piezareal getPiezareal() {
        return piezareal;
    }

    public void setPiezareal(Piezareal piezareal) {
        this.piezareal = piezareal;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public Ejecucionplanificacioncalidad getIdejecucionplanificacioncalidad() {
        return idejecucionplanificacioncalidad;
    }

    public void setIdejecucionplanificacioncalidad(Ejecucionplanificacioncalidad idejecucionplanificacioncalidad) {
        this.idejecucionplanificacioncalidad = idejecucionplanificacioncalidad;
    }

    public List<Detalleplanificacioncalidad> getDetalleplanificacioncalidadList() {
        return detalleplanificacioncalidadList;
    }

    public void setDetalleplanificacioncalidadList(List<Detalleplanificacioncalidad> detalleplanificacioncalidadList) {
        this.detalleplanificacioncalidadList = detalleplanificacioncalidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleejecucionplanificacioncalidad)) {
            return false;
        }
        Detalleejecucionplanificacioncalidad other = (Detalleejecucionplanificacioncalidad) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad[ iddetalle=" + iddetalle + " ]";
    }
}
