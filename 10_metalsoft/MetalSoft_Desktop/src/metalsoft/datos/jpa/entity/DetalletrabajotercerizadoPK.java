/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Nino
 */
@Embeddable
public class DetalletrabajotercerizadoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idtrabajotercerizado")
    private long idtrabajotercerizado;

    public DetalletrabajotercerizadoPK() {
    }

    public DetalletrabajotercerizadoPK(long iddetalle, long idtrabajotercerizado) {
        this.iddetalle = iddetalle;
        this.idtrabajotercerizado = idtrabajotercerizado;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdtrabajotercerizado() {
        return idtrabajotercerizado;
    }

    public void setIdtrabajotercerizado(long idtrabajotercerizado) {
        this.idtrabajotercerizado = idtrabajotercerizado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idtrabajotercerizado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalletrabajotercerizadoPK)) {
            return false;
        }
        DetalletrabajotercerizadoPK other = (DetalletrabajotercerizadoPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idtrabajotercerizado != other.idtrabajotercerizado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetalletrabajotercerizadoPK[iddetalle=" + iddetalle + ", idtrabajotercerizado=" + idtrabajotercerizado + "]";
    }

}
