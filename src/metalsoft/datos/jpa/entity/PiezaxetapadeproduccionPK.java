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
public class PiezaxetapadeproduccionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idpieza")
    private long idpieza;
    @Basic(optional = false)
    @Column(name = "idetapaproduccion")
    private long idetapaproduccion;

    public PiezaxetapadeproduccionPK() {
    }

    public PiezaxetapadeproduccionPK(long idpieza, long idetapaproduccion) {
        this.idpieza = idpieza;
        this.idetapaproduccion = idetapaproduccion;
    }

    public long getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(long idpieza) {
        this.idpieza = idpieza;
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
        hash += (int) idpieza;
        hash += (int) idetapaproduccion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PiezaxetapadeproduccionPK)) {
            return false;
        }
        PiezaxetapadeproduccionPK other = (PiezaxetapadeproduccionPK) object;
        if (this.idpieza != other.idpieza) {
            return false;
        }
        if (this.idetapaproduccion != other.idetapaproduccion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.PiezaxetapadeproduccionPK[ idpieza=" + idpieza + ", idetapaproduccion=" + idetapaproduccion + " ]";
    }
    
}
