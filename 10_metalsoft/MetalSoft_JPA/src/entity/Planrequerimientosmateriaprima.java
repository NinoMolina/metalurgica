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
    private Set<Pedido> pedidoSet;
    @OneToMany(mappedBy = "planrequerimientosmateriaprima1")
    private Set<Pedido> pedidoSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planrequerimientosmateriaprima")
    private Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planrequerimientosmateriaprima1")
    private Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet1;

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

    public Set<Detallerequerimientosmateriaprima> getDetallerequerimientosmateriaprimaSet() {
        return detallerequerimientosmateriaprimaSet;
    }

    public void setDetallerequerimientosmateriaprimaSet(Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet) {
        this.detallerequerimientosmateriaprimaSet = detallerequerimientosmateriaprimaSet;
    }

    public Set<Detallerequerimientosmateriaprima> getDetallerequerimientosmateriaprimaSet1() {
        return detallerequerimientosmateriaprimaSet1;
    }

    public void setDetallerequerimientosmateriaprimaSet1(Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet1) {
        this.detallerequerimientosmateriaprimaSet1 = detallerequerimientosmateriaprimaSet1;
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
        return "entity.Planrequerimientosmateriaprima[idplanrequerimientosmateriaprima=" + idplanrequerimientosmateriaprima + "]";
    }

}
