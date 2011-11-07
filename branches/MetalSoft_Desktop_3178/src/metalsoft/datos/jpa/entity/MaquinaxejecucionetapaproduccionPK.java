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
public class MaquinaxejecucionetapaproduccionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idejecucionetapaproduccion")
    private long idejecucionetapaproduccion;
    @Basic(optional = false)
    @Column(name = "idetapaproduccion")
    private long idetapaproduccion;
    @Basic(optional = false)
    @Column(name = "idmaquina")
    private long idmaquina;

    public MaquinaxejecucionetapaproduccionPK() {
    }

    public MaquinaxejecucionetapaproduccionPK(long idejecucionetapaproduccion, long idetapaproduccion, long idmaquina) {
        this.idejecucionetapaproduccion = idejecucionetapaproduccion;
        this.idetapaproduccion = idetapaproduccion;
        this.idmaquina = idmaquina;
    }

    public long getIdejecucionetapaproduccion() {
        return idejecucionetapaproduccion;
    }

    public void setIdejecucionetapaproduccion(long idejecucionetapaproduccion) {
        this.idejecucionetapaproduccion = idejecucionetapaproduccion;
    }

    public long getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(long idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    public long getIdmaquina() {
        return idmaquina;
    }

    public void setIdmaquina(long idmaquina) {
        this.idmaquina = idmaquina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idejecucionetapaproduccion;
        hash += (int) idetapaproduccion;
        hash += (int) idmaquina;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaquinaxejecucionetapaproduccionPK)) {
            return false;
        }
        MaquinaxejecucionetapaproduccionPK other = (MaquinaxejecucionetapaproduccionPK) object;
        if (this.idejecucionetapaproduccion != other.idejecucionetapaproduccion) {
            return false;
        }
        if (this.idetapaproduccion != other.idetapaproduccion) {
            return false;
        }
        if (this.idmaquina != other.idmaquina) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.MaquinaxejecucionetapaproduccionPK[ idejecucionetapaproduccion=" + idejecucionetapaproduccion + ", idetapaproduccion=" + idetapaproduccion + ", idmaquina=" + idmaquina + " ]";
    }
    
}
