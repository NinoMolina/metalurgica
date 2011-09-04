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
@Table(name = "detalleejecucionplanificacion")
@NamedQueries({
    @NamedQuery(name = "Detalleejecucionplanificacion.findAll", query = "SELECT d FROM Detalleejecucionplanificacion d"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findById", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.id = :id"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByFechainicio", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.fechainicio = :fechainicio"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByFechafin", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.fechafin = :fechafin"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByHorainicio", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.horainicio = :horainicio"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByHorafin", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.horafin = :horafin"),
    @NamedQuery(name = "Detalleejecucionplanificacion.findByOrden", query = "SELECT d FROM Detalleejecucionplanificacion d WHERE d.orden = :orden")})
public class Detalleejecucionplanificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalleejecucionplanificacion_seq")
    @SequenceGenerator(name = "detalleejecucionplanificacion_seq", sequenceName = "detalleejecucionplanificacion_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
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
    @Column(name = "orden")
    private Integer orden;
    @OneToMany(mappedBy = "iddetalleejecucionplanificacion")
    private List<Detalleplanificacionproduccion> detalleplanificacionproduccionList;
    @JoinColumn(name = "piezareal", referencedColumnName = "idpiezareal")
    @ManyToOne
    private Piezareal piezareal;
    @JoinColumn(name = "pieza", referencedColumnName = "idpieza")
    @ManyToOne
    private Pieza pieza;
    @JoinColumn(name = "idetapaproduccion", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion idetapaproduccion;
    @JoinColumn(name = "idejecucionplanificacionproduccion", referencedColumnName = "idejecucion")
    @ManyToOne(optional = false)
    private Ejecucionplanificacionproduccion idejecucionplanificacionproduccion;
    @JoinColumn(name = "ejecucionetapa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ejecucionetapaproduccion ejecucionetapa;

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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public List<Detalleplanificacionproduccion> getDetalleplanificacionproduccionList() {
        return detalleplanificacionproduccionList;
    }

    public void setDetalleplanificacionproduccionList(List<Detalleplanificacionproduccion> detalleplanificacionproduccionList) {
        this.detalleplanificacionproduccionList = detalleplanificacionproduccionList;
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

    public Etapadeproduccion getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(Etapadeproduccion idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    public Ejecucionplanificacionproduccion getIdejecucionplanificacionproduccion() {
        return idejecucionplanificacionproduccion;
    }

    public void setIdejecucionplanificacionproduccion(Ejecucionplanificacionproduccion idejecucionplanificacionproduccion) {
        this.idejecucionplanificacionproduccion = idejecucionplanificacionproduccion;
    }

    public Ejecucionetapaproduccion getEjecucionetapa() {
        return ejecucionetapa;
    }

    public void setEjecucionetapa(Ejecucionetapaproduccion ejecucionetapa) {
        this.ejecucionetapa = ejecucionetapa;
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
        return "metalsoft.datos.jpa.entity.Detalleejecucionplanificacion[ id=" + id + " ]";
    }
    
}
