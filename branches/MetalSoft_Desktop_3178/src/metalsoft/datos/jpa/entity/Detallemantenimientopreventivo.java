/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallemantenimientopreventivo")
@NamedQueries({
    @NamedQuery(name = "Detallemantenimientopreventivo.findAll", query = "SELECT d FROM Detallemantenimientopreventivo d"),
    @NamedQuery(name = "Detallemantenimientopreventivo.findByIddetalle", query = "SELECT d FROM Detallemantenimientopreventivo d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallemantenimientopreventivo.findByObservaciones", query = "SELECT d FROM Detallemantenimientopreventivo d WHERE d.observaciones = :observaciones"),
    @NamedQuery(name = "Detallemantenimientopreventivo.findByDuracion", query = "SELECT d FROM Detallemantenimientopreventivo d WHERE d.duracion = :duracion")})
public class Detallemantenimientopreventivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detallemantenimientopreventivo_seq")
    @SequenceGenerator(name = "detallemantenimientopreventivo_seq", sequenceName = "detallemantenimientopreventivo_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "duracion")
    private Integer duracion;
    @JoinColumn(name = "servicio", referencedColumnName = "idservicio")
    @ManyToOne
    private Servicio servicio;
    @JoinColumn(name = "idmantenimientopreventivo", referencedColumnName = "idmantenimientopreventivo")
    @ManyToOne(optional = false)
    private Mantenimientopreventivo idmantenimientopreventivo;

    public Detallemantenimientopreventivo() {
    }

    public Detallemantenimientopreventivo(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Mantenimientopreventivo getIdmantenimientopreventivo() {
        return idmantenimientopreventivo;
    }

    public void setIdmantenimientopreventivo(Mantenimientopreventivo idmantenimientopreventivo) {
        this.idmantenimientopreventivo = idmantenimientopreventivo;
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
        if (!(object instanceof Detallemantenimientopreventivo)) {
            return false;
        }
        Detallemantenimientopreventivo other = (Detallemantenimientopreventivo) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication2.Detallemantenimientopreventivo[ iddetalle=" + iddetalle + " ]";
    }
    
}
