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
@Table(name = "planificacionproduccion")
@NamedQueries({
    @NamedQuery(name = "Planificacionproduccion.findAll", query = "SELECT p FROM Planificacionproduccion p"),
    @NamedQuery(name = "Planificacionproduccion.findByIdplanificacionproduccion", query = "SELECT p FROM Planificacionproduccion p WHERE p.idplanificacionproduccion = :idplanificacionproduccion"),
    @NamedQuery(name = "Planificacionproduccion.findByNroplanificacion", query = "SELECT p FROM Planificacionproduccion p WHERE p.nroplanificacion = :nroplanificacion"),
    @NamedQuery(name = "Planificacionproduccion.findByFechacreacion", query = "SELECT p FROM Planificacionproduccion p WHERE p.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Planificacionproduccion.findByObservaciones", query = "SELECT p FROM Planificacionproduccion p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "Planificacionproduccion.findByFechainicioprevista", query = "SELECT p FROM Planificacionproduccion p WHERE p.fechainicioprevista = :fechainicioprevista"),
    @NamedQuery(name = "Planificacionproduccion.findByFechafinprevista", query = "SELECT p FROM Planificacionproduccion p WHERE p.fechafinprevista = :fechafinprevista")})
public class Planificacionproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planificacionproduccion_seq")
    @SequenceGenerator(name = "planificacionproduccion_seq", sequenceName = "planificacionproduccion_idplanificacionproduccion_seq", allocationSize = 1)
    @Column(name = "idplanificacionproduccion")
    private Long idplanificacionproduccion;
    @Column(name = "nroplanificacion")
    private BigInteger nroplanificacion;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fechainicioprevista")
    @Temporal(TemporalType.DATE)
    private Date fechainicioprevista;
    @Column(name = "fechafinprevista")
    @Temporal(TemporalType.DATE)
    private Date fechafinprevista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idplanificacionproduccion")
    private List<Detalleplanificacionproduccion> detalleplanificacionproduccionList;
    @JoinColumn(name = "pedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido;
    @JoinColumn(name = "idestado", referencedColumnName = "id")
    @ManyToOne
    private Estadoplanificacionproduccion idestado;
    @OneToMany(mappedBy = "idplanificacionproduccion")
    private List<Detallempasignada> detallempasignadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idplanificacionproduccion")
    private List<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionList;

    public Planificacionproduccion() {
    }

    public Planificacionproduccion(Long idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    public Long getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(Long idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    public BigInteger getNroplanificacion() {
        return nroplanificacion;
    }

    public void setNroplanificacion(BigInteger nroplanificacion) {
        this.nroplanificacion = nroplanificacion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechainicioprevista() {
        return fechainicioprevista;
    }

    public void setFechainicioprevista(Date fechainicioprevista) {
        this.fechainicioprevista = fechainicioprevista;
    }

    public Date getFechafinprevista() {
        return fechafinprevista;
    }

    public void setFechafinprevista(Date fechafinprevista) {
        this.fechafinprevista = fechafinprevista;
    }
    
    public List<Detalleplanificacionproduccion> getDetalleplanificacionproduccionList() {
        return detalleplanificacionproduccionList;
    }

    public void setDetalleplanificacionproduccionList(List<Detalleplanificacionproduccion> detalleplanificacionproduccionList) {
        this.detalleplanificacionproduccionList = detalleplanificacionproduccionList;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Estadoplanificacionproduccion getIdestado() {
        return idestado;
    }

    public void setIdestado(Estadoplanificacionproduccion idestado) {
        this.idestado = idestado;
    }

    public List<Detallempasignada> getDetallempasignadaList() {
        return detallempasignadaList;
    }

    public void setDetallempasignadaList(List<Detallempasignada> detallempasignadaList) {
        this.detallempasignadaList = detallempasignadaList;
    }

    public List<Ejecucionplanificacionproduccion> getEjecucionplanificacionproduccionList() {
        return ejecucionplanificacionproduccionList;
    }

    public void setEjecucionplanificacionproduccionList(List<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionList) {
        this.ejecucionplanificacionproduccionList = ejecucionplanificacionproduccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanificacionproduccion != null ? idplanificacionproduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planificacionproduccion)) {
            return false;
        }
        Planificacionproduccion other = (Planificacionproduccion) object;
        if ((this.idplanificacionproduccion == null && other.idplanificacionproduccion != null) || (this.idplanificacionproduccion != null && !this.idplanificacionproduccion.equals(other.idplanificacionproduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Planificacionproduccion[ idplanificacionproduccion=" + idplanificacionproduccion + " ]";
    }
    
}
