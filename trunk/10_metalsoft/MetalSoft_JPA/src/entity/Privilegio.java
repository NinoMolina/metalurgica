/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "privilegio")
@NamedQueries({
    @NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p"),
    @NamedQuery(name = "Privilegio.findByIdprivilegio", query = "SELECT p FROM Privilegio p WHERE p.idprivilegio = :idprivilegio"),
    @NamedQuery(name = "Privilegio.findByPrivilegio", query = "SELECT p FROM Privilegio p WHERE p.privilegio = :privilegio"),
    @NamedQuery(name = "Privilegio.findByDescripcion", query = "SELECT p FROM Privilegio p WHERE p.descripcion = :descripcion")})
public class Privilegio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idprivilegio")
    private Long idprivilegio;
    @Column(name = "privilegio")
    private String privilegio;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinTable(name = "rolxprivilegio", joinColumns = {
        @JoinColumn(name = "idprivilegio", referencedColumnName = "idprivilegio")}, inverseJoinColumns = {
        @JoinColumn(name = "idrol", referencedColumnName = "idrol")})
    @ManyToMany
    private Set<Rol> rolSet;

    public Privilegio() {
    }

    public Privilegio(Long idprivilegio) {
        this.idprivilegio = idprivilegio;
    }

    public Long getIdprivilegio() {
        return idprivilegio;
    }

    public void setIdprivilegio(Long idprivilegio) {
        this.idprivilegio = idprivilegio;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Rol> getRolSet() {
        return rolSet;
    }

    public void setRolSet(Set<Rol> rolSet) {
        this.rolSet = rolSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprivilegio != null ? idprivilegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilegio)) {
            return false;
        }
        Privilegio other = (Privilegio) object;
        if ((this.idprivilegio == null && other.idprivilegio != null) || (this.idprivilegio != null && !this.idprivilegio.equals(other.idprivilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Privilegio[idprivilegio=" + idprivilegio + "]";
    }

}
