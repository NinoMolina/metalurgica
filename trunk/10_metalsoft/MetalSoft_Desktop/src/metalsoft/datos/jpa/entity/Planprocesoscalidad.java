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
    private List<Detalleplanprocesoscalidad> detalleplanprocesoscalidadList;
    @OneToMany(mappedBy = "planprocesoscalidad")
    private List<Pedido> pedidoList;

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

    public List<Detalleplanprocesoscalidad> getDetalleplanprocesoscalidadList() {
        return detalleplanprocesoscalidadList;
    }

    public void setDetalleplanprocesoscalidadList(List<Detalleplanprocesoscalidad> detalleplanprocesoscalidadList) {
        this.detalleplanprocesoscalidadList = detalleplanprocesoscalidadList;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
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
        return "metalsoft.datos.jpa.entity.Planprocesoscalidad[ idplanprocesoscalidad=" + idplanprocesoscalidad + " ]";
    }
    
}
