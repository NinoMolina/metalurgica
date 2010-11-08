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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "planificacioncalidad")
@NamedQueries({
    @NamedQuery(name = "Planificacioncalidad.findAll", query = "SELECT p FROM Planificacioncalidad p"),
    @NamedQuery(name = "Planificacioncalidad.findByIdplanificacion", query = "SELECT p FROM Planificacioncalidad p WHERE p.idplanificacion = :idplanificacion"),
    @NamedQuery(name = "Planificacioncalidad.findByNroplanificacion", query = "SELECT p FROM Planificacioncalidad p WHERE p.nroplanificacion = :nroplanificacion"),
    @NamedQuery(name = "Planificacioncalidad.findByFechacreacion", query = "SELECT p FROM Planificacioncalidad p WHERE p.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Planificacioncalidad.findByObservaciones", query = "SELECT p FROM Planificacioncalidad p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "Planificacioncalidad.findByFechainicioprevista", query = "SELECT p FROM Planificacioncalidad p WHERE p.fechainicioprevista = :fechainicioprevista"),
    @NamedQuery(name = "Planificacioncalidad.findByFechafinprevista", query = "SELECT p FROM Planificacioncalidad p WHERE p.fechafinprevista = :fechafinprevista")})
public class Planificacioncalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idplanificacion")
    private Long idplanificacion;
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "planificacioncalidad")
    private Ejecucionplanificacioncalidad ejecucionplanificacioncalidad;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "planificacioncalidad1")
    private Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1;
    @JoinColumn(name = "pedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido;
    @JoinColumn(name = "pedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planificacioncalidad")
    private Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planificacioncalidad1")
    private Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1;

    public Planificacioncalidad() {
    }

    public Planificacioncalidad(Long idplanificacion) {
        this.idplanificacion = idplanificacion;
    }

    public Long getIdplanificacion() {
        return idplanificacion;
    }

    public void setIdplanificacion(Long idplanificacion) {
        this.idplanificacion = idplanificacion;
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

    public Ejecucionplanificacioncalidad getEjecucionplanificacioncalidad() {
        return ejecucionplanificacioncalidad;
    }

    public void setEjecucionplanificacioncalidad(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) {
        this.ejecucionplanificacioncalidad = ejecucionplanificacioncalidad;
    }

    public Ejecucionplanificacioncalidad getEjecucionplanificacioncalidad1() {
        return ejecucionplanificacioncalidad1;
    }

    public void setEjecucionplanificacioncalidad1(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1) {
        this.ejecucionplanificacioncalidad1 = ejecucionplanificacioncalidad1;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido1() {
        return pedido1;
    }

    public void setPedido1(Pedido pedido1) {
        this.pedido1 = pedido1;
    }

    public Set<Detalleplanificacioncalidad> getDetalleplanificacioncalidadSet() {
        return detalleplanificacioncalidadSet;
    }

    public void setDetalleplanificacioncalidadSet(Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet) {
        this.detalleplanificacioncalidadSet = detalleplanificacioncalidadSet;
    }

    public Set<Detalleplanificacioncalidad> getDetalleplanificacioncalidadSet1() {
        return detalleplanificacioncalidadSet1;
    }

    public void setDetalleplanificacioncalidadSet1(Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1) {
        this.detalleplanificacioncalidadSet1 = detalleplanificacioncalidadSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanificacion != null ? idplanificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planificacioncalidad)) {
            return false;
        }
        Planificacioncalidad other = (Planificacioncalidad) object;
        if ((this.idplanificacion == null && other.idplanificacion != null) || (this.idplanificacion != null && !this.idplanificacion.equals(other.idplanificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Planificacioncalidad[idplanificacion=" + idplanificacion + "]";
    }

}