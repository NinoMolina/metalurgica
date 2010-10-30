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
@Table(name = "planprocesoscalidad")
@NamedQueries({
    @NamedQuery(name = "Planprocesoscalidad.findAll", query = "SELECT p FROM Planprocesoscalidad p"),
    @NamedQuery(name = "Planprocesoscalidad.findByIdplanprocesoscalidad", query = "SELECT p FROM Planprocesoscalidad p WHERE p.idplanprocesoscalidad = :idplanprocesoscalidad")})
public class Planprocesoscalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idplanprocesoscalidad")
    private Long idplanprocesoscalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planprocesoscalidad")
    private Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planprocesoscalidad1")
    private Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet1;
    @OneToMany(mappedBy = "planprocesoscalidad")
    private Set<Pedido> pedidoSet;
    @OneToMany(mappedBy = "planprocesoscalidad1")
    private Set<Pedido> pedidoSet1;

    public Planprocesoscalidad() {
    }

    public Planprocesoscalidad(Long idplanprocesoscalidad) {
        this.idplanprocesoscalidad = idplanprocesoscalidad;
    }

    public Long getIdplanprocesoscalidad() {
        return idplanprocesoscalidad;
    }

    public void setIdplanprocesoscalidad(Long idplanprocesoscalidad) {
        this.idplanprocesoscalidad = idplanprocesoscalidad;
    }

    public Set<Detalleplanprocesoscalidad> getDetalleplanprocesoscalidadSet() {
        return detalleplanprocesoscalidadSet;
    }

    public void setDetalleplanprocesoscalidadSet(Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet) {
        this.detalleplanprocesoscalidadSet = detalleplanprocesoscalidadSet;
    }

    public Set<Detalleplanprocesoscalidad> getDetalleplanprocesoscalidadSet1() {
        return detalleplanprocesoscalidadSet1;
    }

    public void setDetalleplanprocesoscalidadSet1(Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet1) {
        this.detalleplanprocesoscalidadSet1 = detalleplanprocesoscalidadSet1;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanprocesoscalidad != null ? idplanprocesoscalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planprocesoscalidad)) {
            return false;
        }
        Planprocesoscalidad other = (Planprocesoscalidad) object;
        if ((this.idplanprocesoscalidad == null && other.idplanprocesoscalidad != null) || (this.idplanprocesoscalidad != null && !this.idplanprocesoscalidad.equals(other.idplanprocesoscalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Planprocesoscalidad[idplanprocesoscalidad=" + idplanprocesoscalidad + "]";
    }

}
