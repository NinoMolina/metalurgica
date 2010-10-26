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
@Table(name = "condicioniva")
@NamedQueries({
    @NamedQuery(name = "Condicioniva.findAll", query = "SELECT c FROM Condicioniva c"),
    @NamedQuery(name = "Condicioniva.findByIdcondicioniva", query = "SELECT c FROM Condicioniva c WHERE c.idcondicioniva = :idcondicioniva"),
    @NamedQuery(name = "Condicioniva.findByNombre", query = "SELECT c FROM Condicioniva c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Condicioniva.findByDescripcion", query = "SELECT c FROM Condicioniva c WHERE c.descripcion = :descripcion")})
public class Condicioniva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcondicioniva")
    private Long idcondicioniva;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "condicioniva")
    private Set<Empresametalurgica> empresametalurgicaSet;
    @OneToMany(mappedBy = "condicioniva")
    private Set<Cliente> clienteSet;
    @OneToMany(mappedBy = "condicion")
    private Set<Proveedor> proveedorSet;
    @OneToMany(mappedBy = "condicioniva")
    private Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet;

    public Condicioniva() {
    }

    public Condicioniva(Long idcondicioniva) {
        this.idcondicioniva = idcondicioniva;
    }

    public Long getIdcondicioniva() {
        return idcondicioniva;
    }

    public void setIdcondicioniva(Long idcondicioniva) {
        this.idcondicioniva = idcondicioniva;
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

    public Set<Empresametalurgica> getEmpresametalurgicaSet() {
        return empresametalurgicaSet;
    }

    public void setEmpresametalurgicaSet(Set<Empresametalurgica> empresametalurgicaSet) {
        this.empresametalurgicaSet = empresametalurgicaSet;
    }

    public Set<Cliente> getClienteSet() {
        return clienteSet;
    }

    public void setClienteSet(Set<Cliente> clienteSet) {
        this.clienteSet = clienteSet;
    }

    public Set<Proveedor> getProveedorSet() {
        return proveedorSet;
    }

    public void setProveedorSet(Set<Proveedor> proveedorSet) {
        this.proveedorSet = proveedorSet;
    }

    public Set<Proveedormantenimientomaquina> getProveedormantenimientomaquinaSet() {
        return proveedormantenimientomaquinaSet;
    }

    public void setProveedormantenimientomaquinaSet(Set<Proveedormantenimientomaquina> proveedormantenimientomaquinaSet) {
        this.proveedormantenimientomaquinaSet = proveedormantenimientomaquinaSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcondicioniva != null ? idcondicioniva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condicioniva)) {
            return false;
        }
        Condicioniva other = (Condicioniva) object;
        if ((this.idcondicioniva == null && other.idcondicioniva != null) || (this.idcondicioniva != null && !this.idcondicioniva.equals(other.idcondicioniva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Condicioniva[idcondicioniva=" + idcondicioniva + "]";
    }

}
