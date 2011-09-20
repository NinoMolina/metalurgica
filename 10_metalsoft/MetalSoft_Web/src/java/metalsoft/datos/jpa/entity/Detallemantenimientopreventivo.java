/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallemantenimientopreventivo")
@NamedQueries({
    @NamedQuery(name = "Detallemantenimientopreventivo.findAll", query = "SELECT d FROM Detallemantenimientopreventivo d"),
    @NamedQuery(name = "Detallemantenimientopreventivo.findByIdmantenimientopreventivo", query = "SELECT d FROM Detallemantenimientopreventivo d WHERE d.detallemantenimientopreventivoPK.idmantenimientopreventivo = :idmantenimientopreventivo"),
    @NamedQuery(name = "Detallemantenimientopreventivo.findByIddetalle", query = "SELECT d FROM Detallemantenimientopreventivo d WHERE d.detallemantenimientopreventivoPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallemantenimientopreventivo.findByDuracion", query = "SELECT d FROM Detallemantenimientopreventivo d WHERE d.duracion = :duracion"),
    @NamedQuery(name = "Detallemantenimientopreventivo.findByObservaciones", query = "SELECT d FROM Detallemantenimientopreventivo d WHERE d.observaciones = :observaciones")})
public class Detallemantenimientopreventivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallemantenimientopreventivoPK detallemantenimientopreventivoPK;
    @Column(name = "duracion")
    @Temporal(TemporalType.TIME)
    private Date duracion;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "servicio", referencedColumnName = "idservicio")
    @ManyToOne
    private Servicio servicio;
    @JoinColumn(name = "idmantenimientopreventivo", referencedColumnName = "idmantenimientopreventivo", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Mantenimientopreventivo mantenimientopreventivo;

    public Detallemantenimientopreventivo() {
    }

    public Detallemantenimientopreventivo(DetallemantenimientopreventivoPK detallemantenimientopreventivoPK) {
        this.detallemantenimientopreventivoPK = detallemantenimientopreventivoPK;
    }

    public Detallemantenimientopreventivo(long idmantenimientopreventivo, long iddetalle) {
        this.detallemantenimientopreventivoPK = new DetallemantenimientopreventivoPK(idmantenimientopreventivo, iddetalle);
    }

    public DetallemantenimientopreventivoPK getDetallemantenimientopreventivoPK() {
        return detallemantenimientopreventivoPK;
    }

    public void setDetallemantenimientopreventivoPK(DetallemantenimientopreventivoPK detallemantenimientopreventivoPK) {
        this.detallemantenimientopreventivoPK = detallemantenimientopreventivoPK;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Mantenimientopreventivo getMantenimientopreventivo() {
        return mantenimientopreventivo;
    }

    public void setMantenimientopreventivo(Mantenimientopreventivo mantenimientopreventivo) {
        this.mantenimientopreventivo = mantenimientopreventivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallemantenimientopreventivoPK != null ? detallemantenimientopreventivoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallemantenimientopreventivo)) {
            return false;
        }
        Detallemantenimientopreventivo other = (Detallemantenimientopreventivo) object;
        if ((this.detallemantenimientopreventivoPK == null && other.detallemantenimientopreventivoPK != null) || (this.detallemantenimientopreventivoPK != null && !this.detallemantenimientopreventivoPK.equals(other.detallemantenimientopreventivoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallemantenimientopreventivo[ detallemantenimientopreventivoPK=" + detallemantenimientopreventivoPK + " ]";
    }
    
}
