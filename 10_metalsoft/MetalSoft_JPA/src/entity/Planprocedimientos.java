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
@Table(name = "planprocedimientos")
@NamedQueries({
    @NamedQuery(name = "Planprocedimientos.findAll", query = "SELECT p FROM Planprocedimientos p"),
    @NamedQuery(name = "Planprocedimientos.findByIdplanprocedimientos", query = "SELECT p FROM Planprocedimientos p WHERE p.idplanprocedimientos = :idplanprocedimientos")})
public class Planprocedimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idplanprocedimientos")
    private Long idplanprocedimientos;
    @OneToMany(mappedBy = "planprocedimientos")
    private Set<Pedido> pedidoSet;
    @OneToMany(mappedBy = "planprocedimientos1")
    private Set<Pedido> pedidoSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planprocedimientos")
    private Set<Detalleplanprocedimientos> detalleplanprocedimientosSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planprocedimientos1")
    private Set<Detalleplanprocedimientos> detalleplanprocedimientosSet1;

    public Planprocedimientos() {
    }

    public Planprocedimientos(Long idplanprocedimientos) {
        this.idplanprocedimientos = idplanprocedimientos;
    }

    public Long getIdplanprocedimientos() {
        return idplanprocedimientos;
    }

    public void setIdplanprocedimientos(Long idplanprocedimientos) {
        this.idplanprocedimientos = idplanprocedimientos;
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

    public Set<Detalleplanprocedimientos> getDetalleplanprocedimientosSet() {
        return detalleplanprocedimientosSet;
    }

    public void setDetalleplanprocedimientosSet(Set<Detalleplanprocedimientos> detalleplanprocedimientosSet) {
        this.detalleplanprocedimientosSet = detalleplanprocedimientosSet;
    }

    public Set<Detalleplanprocedimientos> getDetalleplanprocedimientosSet1() {
        return detalleplanprocedimientosSet1;
    }

    public void setDetalleplanprocedimientosSet1(Set<Detalleplanprocedimientos> detalleplanprocedimientosSet1) {
        this.detalleplanprocedimientosSet1 = detalleplanprocedimientosSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanprocedimientos != null ? idplanprocedimientos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planprocedimientos)) {
            return false;
        }
        Planprocedimientos other = (Planprocedimientos) object;
        if ((this.idplanprocedimientos == null && other.idplanprocedimientos != null) || (this.idplanprocedimientos != null && !this.idplanprocedimientos.equals(other.idplanprocedimientos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Planprocedimientos[idplanprocedimientos=" + idplanprocedimientos + "]";
    }

}
