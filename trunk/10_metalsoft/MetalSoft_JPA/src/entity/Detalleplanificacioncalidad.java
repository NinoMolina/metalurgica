/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
    @NamedQuery(name = "Detalleplanificacioncalidad.findByIddetalle", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.detalleplanificacioncalidadPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByIdplanificacioncalidad", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.detalleplanificacioncalidadPK.idplanificacioncalidad = :idplanificacioncalidad"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByFechainicioplan", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.fechainicioplan = :fechainicioplan"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByFechafinplan", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.fechafinplan = :fechafinplan"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByHorainicioplan", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.horainicioplan = :horainicioplan"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByHorafinplan", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.horafinplan = :horafinplan"),
    @NamedQuery(name = "Detalleplanificacioncalidad.findByPieza", query = "SELECT d FROM Detalleplanificacioncalidad d WHERE d.pieza = :pieza")})
public class Detalleplanificacioncalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleplanificacioncalidadPK detalleplanificacioncalidadPK;
    @Column(name = "fechainicioplan")
    @Temporal(TemporalType.DATE)
    private Date fechainicioplan;
    @Column(name = "fechafinplan")
    @Temporal(TemporalType.DATE)
    private Date fechafinplan;
    @Column(name = "horainicioplan")
    @Temporal(TemporalType.TIME)
    private Date horainicioplan;
    @Column(name = "horafinplan")
    @Temporal(TemporalType.TIME)
    private Date horafinplan;
    @Column(name = "pieza")
    private BigInteger pieza;
    @JoinColumns({
        @JoinColumn(name = "iddetalleejecucionplanificacioncalidad", referencedColumnName = "idejecucionplanificacioncalidad"),
        @JoinColumn(name = "idejecucionplanificacioncalidad", referencedColumnName = "idplanificacioncalidad"),
        @JoinColumn(name = "idplanificacioncalidad", referencedColumnName = "iddetalle", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad;
    @JoinColumns({
        @JoinColumn(name = "idplanificacioncalidad", referencedColumnName = "iddetalle", insertable = false, updatable = false),
        @JoinColumn(name = "iddetalleejecucionplanificacioncalidad", referencedColumnName = "idejecucionplanificacioncalidad"),
        @JoinColumn(name = "idejecucionplanificacioncalidad", referencedColumnName = "idplanificacioncalidad")})
    @ManyToOne(optional = false)
    private Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad1;
    @JoinColumn(name = "idplanificacioncalidad", referencedColumnName = "idplanificacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planificacioncalidad planificacioncalidad;
    @JoinColumn(name = "idplanificacioncalidad", referencedColumnName = "idplanificacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planificacioncalidad planificacioncalidad1;
    @JoinColumn(name = "procesocalidad", referencedColumnName = "idprocesocalidad")
    @ManyToOne
    private Procesocalidad procesocalidad;
    @JoinColumn(name = "procesocalidad", referencedColumnName = "idprocesocalidad")
    @ManyToOne
    private Procesocalidad procesocalidad1;

    public Detalleplanificacioncalidad() {
    }

    public Detalleplanificacioncalidad(DetalleplanificacioncalidadPK detalleplanificacioncalidadPK) {
        this.detalleplanificacioncalidadPK = detalleplanificacioncalidadPK;
    }

    public Detalleplanificacioncalidad(long iddetalle, long idplanificacioncalidad) {
        this.detalleplanificacioncalidadPK = new DetalleplanificacioncalidadPK(iddetalle, idplanificacioncalidad);
    }

    public DetalleplanificacioncalidadPK getDetalleplanificacioncalidadPK() {
        return detalleplanificacioncalidadPK;
    }

    public void setDetalleplanificacioncalidadPK(DetalleplanificacioncalidadPK detalleplanificacioncalidadPK) {
        this.detalleplanificacioncalidadPK = detalleplanificacioncalidadPK;
    }

    public Date getFechainicioplan() {
        return fechainicioplan;
    }

    public void setFechainicioplan(Date fechainicioplan) {
        this.fechainicioplan = fechainicioplan;
    }

    public Date getFechafinplan() {
        return fechafinplan;
    }

    public void setFechafinplan(Date fechafinplan) {
        this.fechafinplan = fechafinplan;
    }

    public Date getHorainicioplan() {
        return horainicioplan;
    }

    public void setHorainicioplan(Date horainicioplan) {
        this.horainicioplan = horainicioplan;
    }

    public Date getHorafinplan() {
        return horafinplan;
    }

    public void setHorafinplan(Date horafinplan) {
        this.horafinplan = horafinplan;
    }

    public BigInteger getPieza() {
        return pieza;
    }

    public void setPieza(BigInteger pieza) {
        this.pieza = pieza;
    }

    public Detalleejecucionplanificacioncalidad getDetalleejecucionplanificacioncalidad() {
        return detalleejecucionplanificacioncalidad;
    }

    public void setDetalleejecucionplanificacioncalidad(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) {
        this.detalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad;
    }

    public Detalleejecucionplanificacioncalidad getDetalleejecucionplanificacioncalidad1() {
        return detalleejecucionplanificacioncalidad1;
    }

    public void setDetalleejecucionplanificacioncalidad1(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad1) {
        this.detalleejecucionplanificacioncalidad1 = detalleejecucionplanificacioncalidad1;
    }

    public Planificacioncalidad getPlanificacioncalidad() {
        return planificacioncalidad;
    }

    public void setPlanificacioncalidad(Planificacioncalidad planificacioncalidad) {
        this.planificacioncalidad = planificacioncalidad;
    }

    public Planificacioncalidad getPlanificacioncalidad1() {
        return planificacioncalidad1;
    }

    public void setPlanificacioncalidad1(Planificacioncalidad planificacioncalidad1) {
        this.planificacioncalidad1 = planificacioncalidad1;
    }

    public Procesocalidad getProcesocalidad() {
        return procesocalidad;
    }

    public void setProcesocalidad(Procesocalidad procesocalidad) {
        this.procesocalidad = procesocalidad;
    }

    public Procesocalidad getProcesocalidad1() {
        return procesocalidad1;
    }

    public void setProcesocalidad1(Procesocalidad procesocalidad1) {
        this.procesocalidad1 = procesocalidad1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleplanificacioncalidadPK != null ? detalleplanificacioncalidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleplanificacioncalidad)) {
            return false;
        }
        Detalleplanificacioncalidad other = (Detalleplanificacioncalidad) object;
        if ((this.detalleplanificacioncalidadPK == null && other.detalleplanificacioncalidadPK != null) || (this.detalleplanificacioncalidadPK != null && !this.detalleplanificacioncalidadPK.equals(other.detalleplanificacioncalidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Detalleplanificacioncalidad[detalleplanificacioncalidadPK=" + detalleplanificacioncalidadPK + "]";
    }

}
