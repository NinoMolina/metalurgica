/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "presupuesto")
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p"),
    @NamedQuery(name = "Presupuesto.findByIdpresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Presupuesto.findByFechapresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.fechapresupuesto = :fechapresupuesto"),
    @NamedQuery(name = "Presupuesto.findByMontototal", query = "SELECT p FROM Presupuesto p WHERE p.montototal = :montototal"),
    @NamedQuery(name = "Presupuesto.findByFechavencimiento", query = "SELECT p FROM Presupuesto p WHERE p.fechavencimiento = :fechavencimiento"),
    @NamedQuery(name = "Presupuesto.findByNropresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.nropresupuesto = :nropresupuesto")})
public class Presupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpresupuesto")
    private Long idpresupuesto;
    @Column(name = "fechapresupuesto")
    @Temporal(TemporalType.DATE)
    private Date fechapresupuesto;
    @Column(name = "montototal")
    private Double montototal;
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @Column(name = "nropresupuesto")
    private BigInteger nropresupuesto;
    @OneToMany(mappedBy = "presupuesto")
    private Set<Pedido> pedidoSet;
    @OneToMany(mappedBy = "presupuesto1")
    private Set<Pedido> pedidoSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpresupuesto")
    private Set<Detallepresupuesto> detallepresupuestoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpresupuesto1")
    private Set<Detallepresupuesto> detallepresupuestoSet1;

    public Presupuesto() {
    }

    public Presupuesto(Long idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public Long getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Long idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public Date getFechapresupuesto() {
        return fechapresupuesto;
    }

    public void setFechapresupuesto(Date fechapresupuesto) {
        this.fechapresupuesto = fechapresupuesto;
    }

    public Double getMontototal() {
        return montototal;
    }

    public void setMontototal(Double montototal) {
        this.montototal = montototal;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public BigInteger getNropresupuesto() {
        return nropresupuesto;
    }

    public void setNropresupuesto(BigInteger nropresupuesto) {
        this.nropresupuesto = nropresupuesto;
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

    public Set<Detallepresupuesto> getDetallepresupuestoSet() {
        return detallepresupuestoSet;
    }

    public void setDetallepresupuestoSet(Set<Detallepresupuesto> detallepresupuestoSet) {
        this.detallepresupuestoSet = detallepresupuestoSet;
    }

    public Set<Detallepresupuesto> getDetallepresupuestoSet1() {
        return detallepresupuestoSet1;
    }

    public void setDetallepresupuestoSet1(Set<Detallepresupuesto> detallepresupuestoSet1) {
        this.detallepresupuestoSet1 = detallepresupuestoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpresupuesto != null ? idpresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.idpresupuesto == null && other.idpresupuesto != null) || (this.idpresupuesto != null && !this.idpresupuesto.equals(other.idpresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Presupuesto[idpresupuesto=" + idpresupuesto + "]";
    }

}
