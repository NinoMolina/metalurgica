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
public class UsuarioxrolPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idrol")
    private long idrol;
    @Basic(optional = false)
    @Column(name = "idusuario")
    private long idusuario;

    public UsuarioxrolPK() {
    }

    public UsuarioxrolPK(long idrol, long idusuario) {
        this.idrol = idrol;
        this.idusuario = idusuario;
    }

    public long getIdrol() {
        return idrol;
    }

    public void setIdrol(long idrol) {
        this.idrol = idrol;
    }

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idrol;
        hash += (int) idusuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioxrolPK)) {
            return false;
        }
        UsuarioxrolPK other = (UsuarioxrolPK) object;
        if (this.idrol != other.idrol) {
            return false;
        }
        if (this.idusuario != other.idusuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.UsuarioxrolPK[ idrol=" + idrol + ", idusuario=" + idusuario + " ]";
    }
    
}
