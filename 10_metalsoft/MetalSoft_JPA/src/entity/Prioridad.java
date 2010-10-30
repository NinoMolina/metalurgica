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
@Table(name = "prioridad")
@NamedQueries({
    @NamedQuery(name = "Prioridad.findAll", query = "SELECT p FROM Prioridad p"),
    @NamedQuery(name = "Prioridad.findByIdprioridad", query = "SELECT p FROM Prioridad p WHERE p.idprioridad = :idprioridad"),
    @NamedQuery(name = "Prioridad.findByNombre", query = "SELECT p FROM Prioridad p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Prioridad.findByDescripcion", query = "SELECT p FROM Prioridad p WHERE p.descripcion = :descripcion")})
public class Prioridad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idprioridad")
    private Long idprioridad;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prioridad")
    private Set<Pedido> pedidoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prioridad1")
    private Set<Pedido> pedidoSet1;
    @OneToMany(mappedBy = "prioridad")
    private Set<Cliente> clienteSet;
    @OneToMany(mappedBy = "prioridad1")
    private Set<Cliente> clienteSet1;

    public Prioridad() {
    }

    public Prioridad(Long idprioridad) {
        this.idprioridad = idprioridad;
    }

    public Long getIdprioridad() {
        return idprioridad;
    }

    public void setIdprioridad(Long idprioridad) {
        this.idprioridad = idprioridad;
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

    public Set<Pedido> getPedidoSet() {
        return pedidoSet;
    }

    public void setPedidoSet(Set<Pedido> pedidoSet) {
        this.pedidoSet = pedidoSet;
    }

    public Set<Pedido> getPedidoSet1() {
        return pedidoSet1;
    }

    public void setPedidoSet1(Set<Pedido> pedidoSet1) {
        this.pedidoSet1 = pedidoSet1;
    }

    public Set<Cliente> getClienteSet() {
        return clienteSet;
    }

    public void setClienteSet(Set<Cliente> clienteSet) {
        this.clienteSet = clienteSet;
    }

    public Set<Cliente> getClienteSet1() {
        return clienteSet1;
    }

    public void setClienteSet1(Set<Cliente> clienteSet1) {
        this.clienteSet1 = clienteSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprioridad != null ? idprioridad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prioridad)) {
            return false;
        }
        Prioridad other = (Prioridad) object;
        if ((this.idprioridad == null && other.idprioridad != null) || (this.idprioridad != null && !this.idprioridad.equals(other.idprioridad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prioridad[idprioridad=" + idprioridad + "]";
    }

}
