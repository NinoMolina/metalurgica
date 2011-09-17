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
@Table(name = "detallereclamoempresamantenimiento")
@NamedQueries({
    @NamedQuery(name = "Detallereclamoempresamantenimiento.findAll", query = "SELECT d FROM Detallereclamoempresamantenimiento d"),
    @NamedQuery(name = "Detallereclamoempresamantenimiento.findByIddetalle", query = "SELECT d FROM Detallereclamoempresamantenimiento d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallereclamoempresamantenimiento.findByCantidad", query = "SELECT d FROM Detallereclamoempresamantenimiento d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallereclamoempresamantenimiento.findByDescripcion", query = "SELECT d FROM Detallereclamoempresamantenimiento d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detallereclamoempresamantenimiento.findByMotivo", query = "SELECT d FROM Detallereclamoempresamantenimiento d WHERE d.motivo = :motivo"),
    @NamedQuery(name = "Detallereclamoempresamantenimiento.findByPieza", query = "SELECT d FROM Detallereclamoempresamantenimiento d WHERE d.pieza = :pieza"),
    @NamedQuery(name = "Detallereclamoempresamantenimiento.findByFechaegreso", query = "SELECT d FROM Detallereclamoempresamantenimiento d WHERE d.fechaegreso = :fechaegreso")})
public class Detallereclamoempresamantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "pieza")
    private BigInteger pieza;
    @Column(name = "fechaegreso")
    @Temporal(TemporalType.DATE)
    private Date fechaegreso;
    @JoinColumn(name = "idtrabajo", referencedColumnName = "idtrabajo")
    @ManyToOne
    private Trabajotercerizado idtrabajo;
    @JoinColumn(name = "idreclamo", referencedColumnName = "idreclamo")
    @ManyToOne(optional = false)
    private Reclamoempresamantenimiento idreclamo;
    @JoinColumn(name = "iddetalletrabajo", referencedColumnName = "iddetalle")
    @ManyToOne
    private Detalletrabajotercerizado iddetalletrabajo;

    public Detallereclamoempresamantenimiento() {
    }

    public Detallereclamoempresamantenimiento(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public BigInteger getPieza() {
        return pieza;
    }

    public void setPieza(BigInteger pieza) {
        this.pieza = pieza;
    }

    public Date getFechaegreso() {
        return fechaegreso;
    }

    public void setFechaegreso(Date fechaegreso) {
        this.fechaegreso = fechaegreso;
    }

    public Trabajotercerizado getIdtrabajo() {
        return idtrabajo;
    }

    public void setIdtrabajo(Trabajotercerizado idtrabajo) {
        this.idtrabajo = idtrabajo;
    }

    public Reclamoempresamantenimiento getIdreclamo() {
        return idreclamo;
    }

    public void setIdreclamo(Reclamoempresamantenimiento idreclamo) {
        this.idreclamo = idreclamo;
    }

    public Detalletrabajotercerizado getIddetalletrabajo() {
        return iddetalletrabajo;
    }

    public void setIddetalletrabajo(Detalletrabajotercerizado iddetalletrabajo) {
        this.iddetalletrabajo = iddetalletrabajo;
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
        if (!(object instanceof Detallereclamoempresamantenimiento)) {
            return false;
        }
        Detallereclamoempresamantenimiento other = (Detallereclamoempresamantenimiento) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallereclamoempresamantenimiento[ iddetalle=" + iddetalle + " ]";
    }
    
}
