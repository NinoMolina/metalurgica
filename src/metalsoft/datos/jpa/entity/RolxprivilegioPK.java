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
public class RolxprivilegioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idrol")
    private long idrol;
    @Basic(optional = false)
    @Column(name = "idprivilegio")
    private long idprivilegio;

    public RolxprivilegioPK() {
    }

    public RolxprivilegioPK(long idrol, long idprivilegio) {
        this.idrol = idrol;
        this.idprivilegio = idprivilegio;
    }

    public long getIdrol() {
        return idrol;
    }

    public void setIdrol(long idrol) {
        this.idrol = idrol;
    }

    public long getIdprivilegio() {
        return idprivilegio;
    }

    public void setIdprivilegio(long idprivilegio) {
        this.idprivilegio = idprivilegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idrol;
        hash += (int) idprivilegio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolxprivilegioPK)) {
            return false;
        }
        RolxprivilegioPK other = (RolxprivilegioPK) object;
        if (this.idrol != other.idrol) {
            return false;
        }
        if (this.idprivilegio != other.idprivilegio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.RolxprivilegioPK[ idrol=" + idrol + ", idprivilegio=" + idprivilegio + " ]";
    }
    
}
