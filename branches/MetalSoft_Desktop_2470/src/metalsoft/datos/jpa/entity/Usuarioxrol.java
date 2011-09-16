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
@Table(name = "usuarioxrol")
@NamedQueries({
    @NamedQuery(name = "Usuarioxrol.findAll", query = "SELECT u FROM Usuarioxrol u"),
    @NamedQuery(name = "Usuarioxrol.findByIdrol", query = "SELECT u FROM Usuarioxrol u WHERE u.usuarioxrolPK.idrol = :idrol"),
    @NamedQuery(name = "Usuarioxrol.findByIdusuario", query = "SELECT u FROM Usuarioxrol u WHERE u.usuarioxrolPK.idusuario = :idusuario")})
public class Usuarioxrol implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioxrolPK usuarioxrolPK;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    public Usuarioxrol() {
    }

    public Usuarioxrol(UsuarioxrolPK usuarioxrolPK) {
        this.usuarioxrolPK = usuarioxrolPK;
    }

    public Usuarioxrol(long idrol, long idusuario) {
        this.usuarioxrolPK = new UsuarioxrolPK(idrol, idusuario);
    }

    public UsuarioxrolPK getUsuarioxrolPK() {
        return usuarioxrolPK;
    }

    public void setUsuarioxrolPK(UsuarioxrolPK usuarioxrolPK) {
        this.usuarioxrolPK = usuarioxrolPK;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioxrolPK != null ? usuarioxrolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarioxrol)) {
            return false;
        }
        Usuarioxrol other = (Usuarioxrol) object;
        if ((this.usuarioxrolPK == null && other.usuarioxrolPK != null) || (this.usuarioxrolPK != null && !this.usuarioxrolPK.equals(other.usuarioxrolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Usuarioxrol[ usuarioxrolPK=" + usuarioxrolPK + " ]";
    }
    
}
