/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.List;
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
    private List<Pedido> pedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planprocedimientos")
    private List<Detalleplanprocedimientos> detalleplanprocedimientosList;

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

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public List<Detalleplanprocedimientos> getDetalleplanprocedimientosList() {
        return detalleplanprocedimientosList;
    }

    public void setDetalleplanprocedimientosList(List<Detalleplanprocedimientos> detalleplanprocedimientosList) {
        this.detalleplanprocedimientosList = detalleplanprocedimientosList;
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
        return "metalsoft.datos.jpa.entity.Planprocedimientos[ idplanprocedimientos=" + idplanprocedimientos + " ]";
    }
    
}
