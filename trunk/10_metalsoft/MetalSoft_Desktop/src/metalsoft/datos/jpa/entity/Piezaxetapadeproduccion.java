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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "piezaxetapadeproduccion")
@NamedQueries({
    @NamedQuery(name = "Piezaxetapadeproduccion.findAll", query = "SELECT p FROM Piezaxetapadeproduccion p"),
    @NamedQuery(name = "Piezaxetapadeproduccion.findByIdpieza", query = "SELECT p FROM Piezaxetapadeproduccion p WHERE p.piezaxetapadeproduccionPK.idpieza = :idpieza"),
    @NamedQuery(name = "Piezaxetapadeproduccion.findByIdetapaproduccion", query = "SELECT p FROM Piezaxetapadeproduccion p WHERE p.piezaxetapadeproduccionPK.idetapaproduccion = :idetapaproduccion"),
    @NamedQuery(name = "Piezaxetapadeproduccion.findByDuracion", query = "SELECT p FROM Piezaxetapadeproduccion p WHERE p.duracion = :duracion"),
    @NamedQuery(name = "Piezaxetapadeproduccion.findByDescripcion", query = "SELECT p FROM Piezaxetapadeproduccion p WHERE p.descripcion = :descripcion")})
public class Piezaxetapadeproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PiezaxetapadeproduccionPK piezaxetapadeproduccionPK;
    @Column(name = "duracion")
    @Temporal(TemporalType.TIME)
    private Date duracion;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "idetapaproduccion", referencedColumnName = "idetapaproduccion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Etapadeproduccion etapadeproduccion;

    public Piezaxetapadeproduccion() {
    }

    public Piezaxetapadeproduccion(PiezaxetapadeproduccionPK piezaxetapadeproduccionPK) {
        this.piezaxetapadeproduccionPK = piezaxetapadeproduccionPK;
    }

    public Piezaxetapadeproduccion(long idpieza, long idetapaproduccion) {
        this.piezaxetapadeproduccionPK = new PiezaxetapadeproduccionPK(idpieza, idetapaproduccion);
    }

    public PiezaxetapadeproduccionPK getPiezaxetapadeproduccionPK() {
        return piezaxetapadeproduccionPK;
    }

    public void setPiezaxetapadeproduccionPK(PiezaxetapadeproduccionPK piezaxetapadeproduccionPK) {
        this.piezaxetapadeproduccionPK = piezaxetapadeproduccionPK;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Etapadeproduccion getEtapadeproduccion() {
        return etapadeproduccion;
    }

    public void setEtapadeproduccion(Etapadeproduccion etapadeproduccion) {
        this.etapadeproduccion = etapadeproduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piezaxetapadeproduccionPK != null ? piezaxetapadeproduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piezaxetapadeproduccion)) {
            return false;
        }
        Piezaxetapadeproduccion other = (Piezaxetapadeproduccion) object;
        if ((this.piezaxetapadeproduccionPK == null && other.piezaxetapadeproduccionPK != null) || (this.piezaxetapadeproduccionPK != null && !this.piezaxetapadeproduccionPK.equals(other.piezaxetapadeproduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Piezaxetapadeproduccion[ piezaxetapadeproduccionPK=" + piezaxetapadeproduccionPK + " ]";
    }
    
}
