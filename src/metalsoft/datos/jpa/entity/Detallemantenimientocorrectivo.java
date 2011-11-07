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
@Table(name = "detallemantenimientocorrectivo")
@NamedQueries({
    @NamedQuery(name = "Detallemantenimientocorrectivo.findAll", query = "SELECT d FROM Detallemantenimientocorrectivo d"),
    @NamedQuery(name = "Detallemantenimientocorrectivo.findByIddetalle", query = "SELECT d FROM Detallemantenimientocorrectivo d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallemantenimientocorrectivo.findByDuracion", query = "SELECT d FROM Detallemantenimientocorrectivo d WHERE d.duracion = :duracion"),
    @NamedQuery(name = "Detallemantenimientocorrectivo.findByMotivorotura", query = "SELECT d FROM Detallemantenimientocorrectivo d WHERE d.motivorotura = :motivorotura")})
public class Detallemantenimientocorrectivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detallemantenimientocorrectivo_seq")
    @SequenceGenerator(name = "detallemantenimientocorrectivo_seq", sequenceName = "detallemantenimientocorrectivo_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "duracion")
    private Integer duracion;
    @Column(name = "motivorotura")
    private String motivorotura;
    @JoinColumn(name = "rotura", referencedColumnName = "idrotura")
    @ManyToOne
    private Rotura rotura;
    @JoinColumn(name = "idmantenimientocorrectivo", referencedColumnName = "idmantenimientocorrectivo")
    @ManyToOne(optional = false)
    private Mantenimientocorrectivo idmantenimientocorrectivo;

    public Detallemantenimientocorrectivo() {
    }

    public Detallemantenimientocorrectivo(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
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

    public Mantenimientocorrectivo getIdmantenimientocorrectivo() {
        return idmantenimientocorrectivo;
    }

    public void setIdmantenimientocorrectivo(Mantenimientocorrectivo idmantenimientocorrectivo) {
        this.idmantenimientocorrectivo = idmantenimientocorrectivo;
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
        if (!(object instanceof Detallemantenimientocorrectivo)) {
            return false;
        }
        Detallemantenimientocorrectivo other = (Detallemantenimientocorrectivo) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo[ iddetalle =" + iddetalle + " ]";
    }
}
