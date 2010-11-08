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
@Table(name = "estadoproductoreal")
@NamedQueries({
    @NamedQuery(name = "Estadoproductoreal.findAll", query = "SELECT e FROM Estadoproductoreal e"),
    @NamedQuery(name = "Estadoproductoreal.findByIdestado", query = "SELECT e FROM Estadoproductoreal e WHERE e.idestado = :idestado"),
    @NamedQuery(name = "Estadoproductoreal.findByNombre", query = "SELECT e FROM Estadoproductoreal e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estadoproductoreal.findByDescripcion", query = "SELECT e FROM Estadoproductoreal e WHERE e.descripcion = :descripcion")})
public class Estadoproductoreal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idestado")
    private Long idestado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "estado")
    private Set<Productoreal> productorealSet;
    @OneToMany(mappedBy = "estado1")
    private Set<Productoreal> productorealSet1;

    public Estadoproductoreal() {
    }

    public Estadoproductoreal(Long idestado) {
        this.idestado = idestado;
    }

    public Long getIdestado() {
        return idestado;
    }

    public void setIdestado(Long idestado) {
        this.idestado = idestado;
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

    public Set<Productoreal> getProductorealSet() {
        return productorealSet;
    }

    public void setProductorealSet(Set<Productoreal> productorealSet) {
        this.productorealSet = productorealSet;
    }

    public Set<Productoreal> getProductorealSet1() {
        return productorealSet1;
    }

    public void setProductorealSet1(Set<Productoreal> productorealSet1) {
        this.productorealSet1 = productorealSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestado != null ? idestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadoproductoreal)) {
            return false;
        }
        Estadoproductoreal other = (Estadoproductoreal) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Estadoproductoreal[idestado=" + idestado + "]";
    }

}