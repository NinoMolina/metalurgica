/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Nino
 */
@Embeddable
public class DetallerequerimientosmateriaprimaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idplanrequerimientosmateriaprima")
    private long idplanrequerimientosmateriaprima;

    public DetallerequerimientosmateriaprimaPK() {
    }

    public DetallerequerimientosmateriaprimaPK(long iddetalle, long idplanrequerimientosmateriaprima) {
        this.iddetalle = iddetalle;
        this.idplanrequerimientosmateriaprima = idplanrequerimientosmateriaprima;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdplanrequerimientosmateriaprima() {
        return idplanrequerimientosmateriaprima;
    }

    public void setIdplanrequerimientosmateriaprima(long idplanrequerimientosmateriaprima) {
        this.idplanrequerimientosmateriaprima = idplanrequerimientosmateriaprima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idplanrequerimientosmateriaprima;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallerequerimientosmateriaprimaPK)) {
            return false;
        }
        DetallerequerimientosmateriaprimaPK other = (DetallerequerimientosmateriaprimaPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idplanrequerimientosmateriaprima != other.idplanrequerimientosmateriaprima) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetallerequerimientosmateriaprimaPK[ iddetalle=" + iddetalle + ", idplanrequerimientosmateriaprima=" + idplanrequerimientosmateriaprima + " ]";
    }
    
}
