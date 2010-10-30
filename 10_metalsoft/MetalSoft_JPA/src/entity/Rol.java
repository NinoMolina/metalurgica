/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "rol")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByIdrol", query = "SELECT r FROM Rol r WHERE r.idrol = :idrol"),
    @NamedQuery(name = "Rol.findByRol", query = "SELECT r FROM Rol r WHERE r.rol = :rol"),
    @NamedQuery(name = "Rol.findByDescripcion", query = "SELECT r FROM Rol r WHERE r.descripcion = :descripcion")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idrol")
    private Long idrol;
    @Column(name = "rol")
    private String rol;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private Set<Usuarioxrol> usuarioxrolSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol1")
    private Set<Usuarioxrol> usuarioxrolSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private Set<Rolxprivilegio> rolxprivilegioSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol1")
    private Set<Rolxprivilegio> rolxprivilegioSet1;

    public Rol() {
    }

    public Rol(Long idrol) {
        this.idrol = idrol;
    }

    public Long getIdrol() {
        return idrol;
    }

    public void setIdrol(Long idrol) {
        this.idrol = idrol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Usuarioxrol> getUsuarioxrolSet() {
        return usuarioxrolSet;
    }

    public void setUsuarioxrolSet(Set<Usuarioxrol> usuarioxrolSet) {
        this.usuarioxrolSet = usuarioxrolSet;
    }

    public Set<Usuarioxrol> getUsuarioxrolSet1() {
        return usuarioxrolSet1;
    }

    public void setUsuarioxrolSet1(Set<Usuarioxrol> usuarioxrolSet1) {
        this.usuarioxrolSet1 = usuarioxrolSet1;
    }

    public Set<Rolxprivilegio> getRolxprivilegioSet() {
        return rolxprivilegioSet;
    }

    public void setRolxprivilegioSet(Set<Rolxprivilegio> rolxprivilegioSet) {
        this.rolxprivilegioSet = rolxprivilegioSet;
    }

    public Set<Rolxprivilegio> getRolxprivilegioSet1() {
        return rolxprivilegioSet1;
    }

    public void setRolxprivilegioSet1(Set<Rolxprivilegio> rolxprivilegioSet1) {
        this.rolxprivilegioSet1 = rolxprivilegioSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rol[idrol=" + idrol + "]";
    }

}
