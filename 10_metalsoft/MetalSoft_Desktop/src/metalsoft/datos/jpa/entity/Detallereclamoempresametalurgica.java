/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "detallereclamoempresametalurgica")
@NamedQueries({
    @NamedQuery(name = "Detallereclamoempresametalurgica.findAll", query = "SELECT d FROM Detallereclamoempresametalurgica d"),
    @NamedQuery(name = "Detallereclamoempresametalurgica.findByIddetalle", query = "SELECT d FROM Detallereclamoempresametalurgica d WHERE d.detallereclamoempresametalurgicaPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallereclamoempresametalurgica.findByIdreclamo", query = "SELECT d FROM Detallereclamoempresametalurgica d WHERE d.detallereclamoempresametalurgicaPK.idreclamo = :idreclamo"),
    @NamedQuery(name = "Detallereclamoempresametalurgica.findByCantidad", query = "SELECT d FROM Detallereclamoempresametalurgica d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallereclamoempresametalurgica.findByDescripcion", query = "SELECT d FROM Detallereclamoempresametalurgica d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detallereclamoempresametalurgica.findByMotivo", query = "SELECT d FROM Detallereclamoempresametalurgica d WHERE d.motivo = :motivo"),
    @NamedQuery(name = "Detallereclamoempresametalurgica.findByPieza", query = "SELECT d FROM Detallereclamoempresametalurgica d WHERE d.pieza = :pieza"),
    @NamedQuery(name = "Detallereclamoempresametalurgica.findByFechaegreso", query = "SELECT d FROM Detallereclamoempresametalurgica d WHERE d.fechaegreso = :fechaegreso")})
public class Detallereclamoempresametalurgica implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallereclamoempresametalurgicaPK detallereclamoempresametalurgicaPK;
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
    @JoinColumn(name = "idreclamo", referencedColumnName = "idreclamo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reclamoempresametalurgica reclamoempresametalurgica;

    public Detallereclamoempresametalurgica() {
    }

    public Detallereclamoempresametalurgica(DetallereclamoempresametalurgicaPK detallereclamoempresametalurgicaPK) {
        this.detallereclamoempresametalurgicaPK = detallereclamoempresametalurgicaPK;
    }

    public Detallereclamoempresametalurgica(long iddetalle, long idreclamo) {
        this.detallereclamoempresametalurgicaPK = new DetallereclamoempresametalurgicaPK(iddetalle, idreclamo);
    }

    public DetallereclamoempresametalurgicaPK getDetallereclamoempresametalurgicaPK() {
        return detallereclamoempresametalurgicaPK;
    }

    public void setDetallereclamoempresametalurgicaPK(DetallereclamoempresametalurgicaPK detallereclamoempresametalurgicaPK) {
        this.detallereclamoempresametalurgicaPK = detallereclamoempresametalurgicaPK;
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

    public Reclamoempresametalurgica getReclamoempresametalurgica() {
        return reclamoempresametalurgica;
    }

    public void setReclamoempresametalurgica(Reclamoempresametalurgica reclamoempresametalurgica) {
        this.reclamoempresametalurgica = reclamoempresametalurgica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallereclamoempresametalurgicaPK != null ? detallereclamoempresametalurgicaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallereclamoempresametalurgica)) {
            return false;
        }
        Detallereclamoempresametalurgica other = (Detallereclamoempresametalurgica) object;
        if ((this.detallereclamoempresametalurgicaPK == null && other.detallereclamoempresametalurgicaPK != null) || (this.detallereclamoempresametalurgicaPK != null && !this.detallereclamoempresametalurgicaPK.equals(other.detallereclamoempresametalurgicaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallereclamoempresametalurgica[ detallereclamoempresametalurgicaPK=" + detallereclamoempresametalurgicaPK + " ]";
    }
    
}
