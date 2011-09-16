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
@Table(name = "planrequerimientosmateriaprima")
@NamedQueries({
    @NamedQuery(name = "Planrequerimientosmateriaprima.findAll", query = "SELECT p FROM Planrequerimientosmateriaprima p"),
    @NamedQuery(name = "Planrequerimientosmateriaprima.findByIdplanrequerimientosmateriaprima", query = "SELECT p FROM Planrequerimientosmateriaprima p WHERE p.idplanrequerimientosmateriaprima = :idplanrequerimientosmateriaprima")})
public class Planrequerimientosmateriaprima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idplanrequerimientosmateriaprima")
    private Long idplanrequerimientosmateriaprima;
    @OneToMany(mappedBy = "planrequerimientosmateriaprima")
    private List<Pedido> pedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planrequerimientosmateriaprima")
    private List<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaList;

    public Planrequerimientosmateriaprima() {
    }

    public Planrequerimientosmateriaprima(Long idplanrequerimientosmateriaprima) {
        this.idplanrequerimientosmateriaprima = idplanrequerimientosmateriaprima;
    }

    public Long getIdplanrequerimientosmateriaprima() {
        return idplanrequerimientosmateriaprima;
    }

    public void setIdplanrequerimientosmateriaprima(Long idplanrequerimientosmateriaprima) {
        this.idplanrequerimientosmateriaprima = idplanrequerimientosmateriaprima;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public List<Detallerequerimientosmateriaprima> getDetallerequerimientosmateriaprimaList() {
        return detallerequerimientosmateriaprimaList;
    }

    public void setDetallerequerimientosmateriaprimaList(List<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaList) {
        this.detallerequerimientosmateriaprimaList = detallerequerimientosmateriaprimaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanrequerimientosmateriaprima != null ? idplanrequerimientosmateriaprima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planrequerimientosmateriaprima)) {
            return false;
        }
        Planrequerimientosmateriaprima other = (Planrequerimientosmateriaprima) object;
        if ((this.idplanrequerimientosmateriaprima == null && other.idplanrequerimientosmateriaprima != null) || (this.idplanrequerimientosmateriaprima != null && !this.idplanrequerimientosmateriaprima.equals(other.idplanrequerimientosmateriaprima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Planrequerimientosmateriaprima[ idplanrequerimientosmateriaprima=" + idplanrequerimientosmateriaprima + " ]";
    }
    
}
