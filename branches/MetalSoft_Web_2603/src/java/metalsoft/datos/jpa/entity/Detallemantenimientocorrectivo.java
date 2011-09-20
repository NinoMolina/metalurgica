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
@Table(name = "detallemantenimientocorrectivo")
@NamedQueries({
    @NamedQuery(name = "Detallemantenimientocorrectivo.findAll", query = "SELECT d FROM Detallemantenimientocorrectivo d"),
    @NamedQuery(name = "Detallemantenimientocorrectivo.findByIdmantenimientocorrectivo", query = "SELECT d FROM Detallemantenimientocorrectivo d WHERE d.detallemantenimientocorrectivoPK.idmantenimientocorrectivo = :idmantenimientocorrectivo"),
    @NamedQuery(name = "Detallemantenimientocorrectivo.findByIddetalle", query = "SELECT d FROM Detallemantenimientocorrectivo d WHERE d.detallemantenimientocorrectivoPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallemantenimientocorrectivo.findByDuracion", query = "SELECT d FROM Detallemantenimientocorrectivo d WHERE d.duracion = :duracion"),
    @NamedQuery(name = "Detallemantenimientocorrectivo.findByMotivorotura", query = "SELECT d FROM Detallemantenimientocorrectivo d WHERE d.motivorotura = :motivorotura")})
public class Detallemantenimientocorrectivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallemantenimientocorrectivoPK detallemantenimientocorrectivoPK;
    @Column(name = "duracion")
    @Temporal(TemporalType.TIME)
    private Date duracion;
    @Column(name = "motivorotura")
    private String motivorotura;
    @JoinColumn(name = "rotura", referencedColumnName = "idrotura")
    @ManyToOne
    private Rotura rotura;
    @JoinColumn(name = "idmantenimientocorrectivo", referencedColumnName = "idmantenimientocorrectivo", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Mantenimientocorrectivo mantenimientocorrectivo;

    public Detallemantenimientocorrectivo() {
    }

    public Detallemantenimientocorrectivo(DetallemantenimientocorrectivoPK detallemantenimientocorrectivoPK) {
        this.detallemantenimientocorrectivoPK = detallemantenimientocorrectivoPK;
    }

    public Detallemantenimientocorrectivo(long idmantenimientocorrectivo, long iddetalle) {
        this.detallemantenimientocorrectivoPK = new DetallemantenimientocorrectivoPK(idmantenimientocorrectivo, iddetalle);
    }

    public DetallemantenimientocorrectivoPK getDetallemantenimientocorrectivoPK() {
        return detallemantenimientocorrectivoPK;
    }

    public void setDetallemantenimientocorrectivoPK(DetallemantenimientocorrectivoPK detallemantenimientocorrectivoPK) {
        this.detallemantenimientocorrectivoPK = detallemantenimientocorrectivoPK;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getMotivorotura() {
        return motivorotura;
    }

    public void setMotivorotura(String motivorotura) {
        this.motivorotura = motivorotura;
    }

    public Rotura getRotura() {
        return rotura;
    }

    public void setRotura(Rotura rotura) {
        this.rotura = rotura;
    }

    public Mantenimientocorrectivo getMantenimientocorrectivo() {
        return mantenimientocorrectivo;
    }

    public void setMantenimientocorrectivo(Mantenimientocorrectivo mantenimientocorrectivo) {
        this.mantenimientocorrectivo = mantenimientocorrectivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallemantenimientocorrectivoPK != null ? detallemantenimientocorrectivoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallemantenimientocorrectivo)) {
            return false;
        }
        Detallemantenimientocorrectivo other = (Detallemantenimientocorrectivo) object;
        if ((this.detallemantenimientocorrectivoPK == null && other.detallemantenimientocorrectivoPK != null) || (this.detallemantenimientocorrectivoPK != null && !this.detallemantenimientocorrectivoPK.equals(other.detallemantenimientocorrectivoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo[ detallemantenimientocorrectivoPK=" + detallemantenimientocorrectivoPK + " ]";
    }
    
}
