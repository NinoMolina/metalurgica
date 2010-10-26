/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Nino
 */
@Embeddable
public class EjecucionetapaproduccionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idejecucion")
    private long idejecucion;
    @Basic(optional = false)
    @Column(name = "idetapaproduccion")
    private long idetapaproduccion;

    public EjecucionetapaproduccionPK() {
    }

    public EjecucionetapaproduccionPK(long idejecucion, long idetapaproduccion) {
        this.idejecucion = idejecucion;
        this.idetapaproduccion = idetapaproduccion;
    }

    public long getIdejecucion() {
        return idejecucion;
    }

    public void setIdejecucion(long idejecucion) {
        this.idejecucion = idejecucion;
    }

    public long getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(long idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idejecucion;
        hash += (int) idetapaproduccion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjecucionetapaproduccionPK)) {
            return false;
        }
        EjecucionetapaproduccionPK other = (EjecucionetapaproduccionPK) object;
        if (this.idejecucion != other.idejecucion) {
            return false;
        }
        if (this.idetapaproduccion != other.idetapaproduccion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EjecucionetapaproduccionPK[idejecucion=" + idejecucion + ", idetapaproduccion=" + idetapaproduccion + "]";
    }

}
