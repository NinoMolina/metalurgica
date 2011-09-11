/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "rolxprivilegio")
@NamedQueries({
    @NamedQuery(name = "Rolxprivilegio.findAll", query = "SELECT r FROM Rolxprivilegio r"),
    @NamedQuery(name = "Rolxprivilegio.findByIdrol", query = "SELECT r FROM Rolxprivilegio r WHERE r.rolxprivilegioPK.idrol = :idrol"),
    @NamedQuery(name = "Rolxprivilegio.findByIdprivilegio", query = "SELECT r FROM Rolxprivilegio r WHERE r.rolxprivilegioPK.idprivilegio = :idprivilegio")})
public class Rolxprivilegio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolxprivilegioPK rolxprivilegioPK;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "idprivilegio", referencedColumnName = "idprivilegio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Privilegio privilegio;

    public Rolxprivilegio() {
    }

    public Rolxprivilegio(RolxprivilegioPK rolxprivilegioPK) {
        this.rolxprivilegioPK = rolxprivilegioPK;
    }

    public Rolxprivilegio(long idrol, long idprivilegio) {
        this.rolxprivilegioPK = new RolxprivilegioPK(idrol, idprivilegio);
    }

    public RolxprivilegioPK getRolxprivilegioPK() {
        return rolxprivilegioPK;
    }

    public void setRolxprivilegioPK(RolxprivilegioPK rolxprivilegioPK) {
        this.rolxprivilegioPK = rolxprivilegioPK;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Privilegio getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(Privilegio privilegio) {
        this.privilegio = privilegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolxprivilegioPK != null ? rolxprivilegioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolxprivilegio)) {
            return false;
        }
        Rolxprivilegio other = (Rolxprivilegio) object;
        if ((this.rolxprivilegioPK == null && other.rolxprivilegioPK != null) || (this.rolxprivilegioPK != null && !this.rolxprivilegioPK.equals(other.rolxprivilegioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Rolxprivilegio[ rolxprivilegioPK=" + rolxprivilegioPK + " ]";
    }
    
}
