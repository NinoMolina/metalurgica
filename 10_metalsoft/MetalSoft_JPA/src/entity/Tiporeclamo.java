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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "tiporeclamo")
@NamedQueries({
    @NamedQuery(name = "Tiporeclamo.findAll", query = "SELECT t FROM Tiporeclamo t"),
    @NamedQuery(name = "Tiporeclamo.findByIdtiporeclamo", query = "SELECT t FROM Tiporeclamo t WHERE t.idtiporeclamo = :idtiporeclamo"),
    @NamedQuery(name = "Tiporeclamo.findByNombre", query = "SELECT t FROM Tiporeclamo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tiporeclamo.findByDescripcion", query = "SELECT t FROM Tiporeclamo t WHERE t.descripcion = :descripcion")})
public class Tiporeclamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtiporeclamo")
    private Long idtiporeclamo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tiporeclamo")
    private Set<Reclamoproveedor> reclamoproveedorSet;
    @OneToMany(mappedBy = "tiporeclamo1")
    private Set<Reclamoproveedor> reclamoproveedorSet1;
    @OneToMany(mappedBy = "tiporeclamo")
    private Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet;
    @OneToMany(mappedBy = "tiporeclamo1")
    private Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1;
    @OneToMany(mappedBy = "tiporeclamo")
    private Set<Reclamocliente> reclamoclienteSet;
    @OneToMany(mappedBy = "tiporeclamo1")
    private Set<Reclamocliente> reclamoclienteSet1;

    public Tiporeclamo() {
    }

    public Tiporeclamo(Long idtiporeclamo) {
        this.idtiporeclamo = idtiporeclamo;
    }

    public Long getIdtiporeclamo() {
        return idtiporeclamo;
    }

    public void setIdtiporeclamo(Long idtiporeclamo) {
        this.idtiporeclamo = idtiporeclamo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Reclamoproveedor> getReclamoproveedorSet() {
        return reclamoproveedorSet;
    }

    public void setReclamoproveedorSet(Set<Reclamoproveedor> reclamoproveedorSet) {
        this.reclamoproveedorSet = reclamoproveedorSet;
    }

    public Set<Reclamoproveedor> getReclamoproveedorSet1() {
        return reclamoproveedorSet1;
    }

    public void setReclamoproveedorSet1(Set<Reclamoproveedor> reclamoproveedorSet1) {
        this.reclamoproveedorSet1 = reclamoproveedorSet1;
    }

    public Set<Reclamoempresametalurgica> getReclamoempresametalurgicaSet() {
        return reclamoempresametalurgicaSet;
    }

    public void setReclamoempresametalurgicaSet(Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet) {
        this.reclamoempresametalurgicaSet = reclamoempresametalurgicaSet;
    }

    public Set<Reclamoempresametalurgica> getReclamoempresametalurgicaSet1() {
        return reclamoempresametalurgicaSet1;
    }

    public void setReclamoempresametalurgicaSet1(Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1) {
        this.reclamoempresametalurgicaSet1 = reclamoempresametalurgicaSet1;
    }

    public Set<Reclamocliente> getReclamoclienteSet() {
        return reclamoclienteSet;
    }

    public void setReclamoclienteSet(Set<Reclamocliente> reclamoclienteSet) {
        this.reclamoclienteSet = reclamoclienteSet;
    }

    public Set<Reclamocliente> getReclamoclienteSet1() {
        return reclamoclienteSet1;
    }

    public void setReclamoclienteSet1(Set<Reclamocliente> reclamoclienteSet1) {
        this.reclamoclienteSet1 = reclamoclienteSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiporeclamo != null ? idtiporeclamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiporeclamo)) {
            return false;
        }
        Tiporeclamo other = (Tiporeclamo) object;
        if ((this.idtiporeclamo == null && other.idtiporeclamo != null) || (this.idtiporeclamo != null && !this.idtiporeclamo.equals(other.idtiporeclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tiporeclamo[idtiporeclamo=" + idtiporeclamo + "]";
    }

}
