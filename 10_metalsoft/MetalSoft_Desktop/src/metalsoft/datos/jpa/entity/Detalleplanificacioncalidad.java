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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detalleplanificacioncalidad")
@NamedQueries({
    @NamedQuery(name = "Detalleplanificacioncalidad.findAll", query = "SELECT d FROM Detalleplanificacioncalidad d"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByIddetalle", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByIddetalleejecucionplanificacioncalidad", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.iddetalleejecucionplanificacioncalidad = :iddetalleejecucionplanificacioncalidad"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByFechainicio", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.fechainicio = :fechainicio"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByFechafin", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.fechafin = :fechafin"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByHorainicio", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.horainicio = :horainicio"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByHorafin", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.horafin = :horafin"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByOrden", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.orden = :orden")})
public class Detalleplanificacioncalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "iddetalleejecucionplanificacioncalidad")
    private BigInteger iddetalleejecucionplanificacioncalidad;
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
    @JoinColumn(name = "producto", referencedColumnName = "idproducto")
    @ManyToOne
    private Producto producto;
    @JoinColumn(name = "procesocalidad", referencedColumnName = "idprocesocalidad")
    @ManyToOne
    private Procesocalidad procesocalidad;
    @JoinColumn(name = "idplanificacioncalidad", referencedColumnName = "idplanificacion")
    @ManyToOne(optional = false)
    private Planificacioncalidad idplanificacioncalidad;
    @JoinColumn(name = "pieza", referencedColumnName = "idpieza")
    @ManyToOne
    private Pieza pieza;
    @JoinColumn(name = "maquina", referencedColumnName = "idmaquina")
    @ManyToOne
    private Maquina maquina;
    @JoinColumn(name = "empleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado empleado;

    public Detalleplanificacioncalidad() {
    }

    public Detalleplanificacioncalidad(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public BigInteger getIddetalleejecucionplanificacioncalidad() {
        return iddetalleejecucionplanificacioncalidad;
    }

    public void setIddetalleejecucionplanificacioncalidad(BigInteger iddetalleejecucionplanificacioncalidad) {
        this.iddetalleejecucionplanificacioncalidad = iddetalleejecucionplanificacioncalidad;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Procesocalidad getProcesocalidad() {
        return procesocalidad;
    }

    public void setProcesocalidad(Procesocalidad procesocalidad) {
        this.procesocalidad = procesocalidad;
    }

    public Planificacioncalidad getIdplanificacioncalidad() {
        return idplanificacioncalidad;
    }

    public void setIdplanificacioncalidad(Planificacioncalidad idplanificacioncalidad) {
        this.idplanificacioncalidad = idplanificacioncalidad;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
        if (!(object instanceof Detalleplanificacioncalidad)) {
            return false;
        }
        Detalleplanificacioncalidad other = (Detalleplanificacioncalidad) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalleplanificacioncalidad[ iddetalle=" + iddetalle + " ]";
    }
    
}
