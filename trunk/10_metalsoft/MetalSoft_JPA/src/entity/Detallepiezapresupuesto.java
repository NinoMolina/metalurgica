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
@Table(name = "detallepiezapresupuesto")
@NamedQueries({
    @NamedQuery(name = "Detallepiezapresupuesto.findAll", query = "SELECT d FROM Detallepiezapresupuesto d"),
    @NamedQuery(name = "Detallepiezapresupuesto.findByDuracionpiezaxetapa", query = "SELECT d FROM Detallepiezapresupuesto d WHERE d.duracionpiezaxetapa = :duracionpiezaxetapa"),
    @NamedQuery(name = "Detallepiezapresupuesto.findByIddetalle", query = "SELECT d FROM Detallepiezapresupuesto d WHERE d.iddetalle = :iddetalle")})
public class Detallepiezapresupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "duracionpiezaxetapa")
    @Temporal(TemporalType.TIME)
    private Date duracionpiezaxetapa;
    @Id
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @JoinColumn(name = "iddetalleproductopresupuesto", referencedColumnName = "iddetalle")
    @ManyToOne
    private Detalleproductopresupuesto iddetalleproductopresupuesto;
    @JoinColumn(name = "iddetalleproductopresupuesto", referencedColumnName = "iddetalle")
    @ManyToOne
    private Detalleproductopresupuesto iddetalleproductopresupuesto1;
    @JoinColumn(name = "idetapa", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion idetapa;
    @JoinColumn(name = "idetapa", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion idetapa1;

    public Detallepiezapresupuesto() {
    }

    public Detallepiezapresupuesto(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Date getDuracionpiezaxetapa() {
        return duracionpiezaxetapa;
    }

    public void setDuracionpiezaxetapa(Date duracionpiezaxetapa) {
        this.duracionpiezaxetapa = duracionpiezaxetapa;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Detalleproductopresupuesto getIddetalleproductopresupuesto() {
        return iddetalleproductopresupuesto;
    }

    public void setIddetalleproductopresupuesto(Detalleproductopresupuesto iddetalleproductopresupuesto) {
        this.iddetalleproductopresupuesto = iddetalleproductopresupuesto;
    }

    public Detalleproductopresupuesto getIddetalleproductopresupuesto1() {
        return iddetalleproductopresupuesto1;
    }

    public void setIddetalleproductopresupuesto1(Detalleproductopresupuesto iddetalleproductopresupuesto1) {
        this.iddetalleproductopresupuesto1 = iddetalleproductopresupuesto1;
    }

    public Etapadeproduccion getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(Etapadeproduccion idetapa) {
        this.idetapa = idetapa;
    }

    public Etapadeproduccion getIdetapa1() {
        return idetapa1;
    }

    public void setIdetapa1(Etapadeproduccion idetapa1) {
        this.idetapa1 = idetapa1;
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
        if (!(object instanceof Detallepiezapresupuesto)) {
            return false;
        }
        Detallepiezapresupuesto other = (Detallepiezapresupuesto) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Detallepiezapresupuesto[iddetalle=" + iddetalle + "]";
    }

}
