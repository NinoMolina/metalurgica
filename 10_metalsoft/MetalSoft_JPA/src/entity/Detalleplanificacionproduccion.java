/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
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
@Table(name = "detalleplanificacionproduccion")
@NamedQueries({
    @NamedQuery(name = "Detalleplanificacionproduccion.findAll", query = "SELECT d FROM Detalleplanificacionproduccion d"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findById", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.id = :id"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByFechainicio", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.fechainicio = :fechainicio"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByHorainicio", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.horainicio = :horainicio"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByFechafin", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.fechafin = :fechafin"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByHorafin", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.horafin = :horafin"),
    @NamedQuery(name = "Detalleplanificacionproduccion.findByOrden", query = "SELECT d FROM Detalleplanificacionproduccion d WHERE d.orden = :orden")})
public class Detalleplanificacionproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
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
    @Column(name = "orden")
    private Integer orden;
    @JoinColumn(name = "iddetalleejecucionplanificacion", referencedColumnName = "id")
    @ManyToOne
    private Detalleejecucionplanificacion iddetalleejecucionplanificacion;
    @JoinColumn(name = "iddetalleejecucionplanificacion", referencedColumnName = "id")
    @ManyToOne
    private Detalleejecucionplanificacion iddetalleejecucionplanificacion1;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado idempleado;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleado idempleado1;
    @JoinColumn(name = "idetapaproduccion", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion idetapaproduccion;
    @JoinColumn(name = "idetapaproduccion", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion idetapaproduccion1;
    @JoinColumn(name = "idmaquina", referencedColumnName = "idmaquina")
    @ManyToOne
    private Maquina idmaquina;
    @JoinColumn(name = "idmaquina", referencedColumnName = "idmaquina")
    @ManyToOne
    private Maquina idmaquina1;
    @JoinColumn(name = "idpieza", referencedColumnName = "idpieza")
    @ManyToOne
    private Pieza idpieza;
    @JoinColumn(name = "idpieza", referencedColumnName = "idpieza")
    @ManyToOne
    private Pieza idpieza1;
    @JoinColumn(name = "idplanificacionproduccion", referencedColumnName = "idplanificacionproduccion")
    @ManyToOne(optional = false)
    private Planificacionproduccion idplanificacionproduccion;
    @JoinColumn(name = "idplanificacionproduccion", referencedColumnName = "idplanificacionproduccion")
    @ManyToOne(optional = false)
    private Planificacionproduccion idplanificacionproduccion1;

    public Detalleplanificacionproduccion() {
    }

    public Detalleplanificacionproduccion(Long id) {
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Detalleejecucionplanificacion getIddetalleejecucionplanificacion() {
        return iddetalleejecucionplanificacion;
    }

    public void setIddetalleejecucionplanificacion(Detalleejecucionplanificacion iddetalleejecucionplanificacion) {
        this.iddetalleejecucionplanificacion = iddetalleejecucionplanificacion;
    }

    public Detalleejecucionplanificacion getIddetalleejecucionplanificacion1() {
        return iddetalleejecucionplanificacion1;
    }

    public void setIddetalleejecucionplanificacion1(Detalleejecucionplanificacion iddetalleejecucionplanificacion1) {
        this.iddetalleejecucionplanificacion1 = iddetalleejecucionplanificacion1;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    public Empleado getIdempleado1() {
        return idempleado1;
    }

    public void setIdempleado1(Empleado idempleado1) {
        this.idempleado1 = idempleado1;
    }

    public Etapadeproduccion getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(Etapadeproduccion idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    public Etapadeproduccion getIdetapaproduccion1() {
        return idetapaproduccion1;
    }

    public void setIdetapaproduccion1(Etapadeproduccion idetapaproduccion1) {
        this.idetapaproduccion1 = idetapaproduccion1;
    }

    public Maquina getIdmaquina() {
        return idmaquina;
    }

    public void setIdmaquina(Maquina idmaquina) {
        this.idmaquina = idmaquina;
    }

    public Maquina getIdmaquina1() {
        return idmaquina1;
    }

    public void setIdmaquina1(Maquina idmaquina1) {
        this.idmaquina1 = idmaquina1;
    }

    public Pieza getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(Pieza idpieza) {
        this.idpieza = idpieza;
    }

    public Pieza getIdpieza1() {
        return idpieza1;
    }

    public void setIdpieza1(Pieza idpieza1) {
        this.idpieza1 = idpieza1;
    }

    public Planificacionproduccion getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(Planificacionproduccion idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    public Planificacionproduccion getIdplanificacionproduccion1() {
        return idplanificacionproduccion1;
    }

    public void setIdplanificacionproduccion1(Planificacionproduccion idplanificacionproduccion1) {
        this.idplanificacionproduccion1 = idplanificacionproduccion1;
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
        if (!(object instanceof Detalleplanificacionproduccion)) {
            return false;
        }
        Detalleplanificacionproduccion other = (Detalleplanificacionproduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Detalleplanificacionproduccion[id=" + id + "]";
    }

}
