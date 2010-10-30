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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "reclamoproveedor")
@NamedQueries({
    @NamedQuery(name = "Reclamoproveedor.findAll", query = "SELECT r FROM Reclamoproveedor r"),
    @NamedQuery(name = "Reclamoproveedor.findByIdreclamo", query = "SELECT r FROM Reclamoproveedor r WHERE r.idreclamo = :idreclamo"),
    @NamedQuery(name = "Reclamoproveedor.findByNroreclamo", query = "SELECT r FROM Reclamoproveedor r WHERE r.nroreclamo = :nroreclamo"),
    @NamedQuery(name = "Reclamoproveedor.findByMotivo", query = "SELECT r FROM Reclamoproveedor r WHERE r.motivo = :motivo"),
    @NamedQuery(name = "Reclamoproveedor.findByFechareclamo", query = "SELECT r FROM Reclamoproveedor r WHERE r.fechareclamo = :fechareclamo")})
public class Reclamoproveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idreclamo")
    private Long idreclamo;
    @Column(name = "nroreclamo")
    private BigInteger nroreclamo;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "fechareclamo")
    @Temporal(TemporalType.DATE)
    private Date fechareclamo;
    @JoinColumn(name = "compra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra compra;
    @JoinColumn(name = "compra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra compra1;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamoproveedor")
    private Set<Detallereclamoproveedor> detallereclamoproveedorSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamoproveedor1")
    private Set<Detallereclamoproveedor> detallereclamoproveedorSet1;

    public Reclamoproveedor() {
    }

    public Reclamoproveedor(Long idreclamo) {
        this.idreclamo = idreclamo;
    }

    public Long getIdreclamo() {
        return idreclamo;
    }

    public void setIdreclamo(Long idreclamo) {
        this.idreclamo = idreclamo;
    }

    public BigInteger getNroreclamo() {
        return nroreclamo;
    }

    public void setNroreclamo(BigInteger nroreclamo) {
        this.nroreclamo = nroreclamo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechareclamo() {
        return fechareclamo;
    }

    public void setFechareclamo(Date fechareclamo) {
        this.fechareclamo = fechareclamo;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Compra getCompra1() {
        return compra1;
    }

    public void setCompra1(Compra compra1) {
        this.compra1 = compra1;
    }

    public Tiporeclamo getTiporeclamo() {
        return tiporeclamo;
    }

    public void setTiporeclamo(Tiporeclamo tiporeclamo) {
        this.tiporeclamo = tiporeclamo;
    }

    public Tiporeclamo getTiporeclamo1() {
        return tiporeclamo1;
    }

    public void setTiporeclamo1(Tiporeclamo tiporeclamo1) {
        this.tiporeclamo1 = tiporeclamo1;
    }

    public Set<Detallereclamoproveedor> getDetallereclamoproveedorSet() {
        return detallereclamoproveedorSet;
    }

    public void setDetallereclamoproveedorSet(Set<Detallereclamoproveedor> detallereclamoproveedorSet) {
        this.detallereclamoproveedorSet = detallereclamoproveedorSet;
    }

    public Set<Detallereclamoproveedor> getDetallereclamoproveedorSet1() {
        return detallereclamoproveedorSet1;
    }

    public void setDetallereclamoproveedorSet1(Set<Detallereclamoproveedor> detallereclamoproveedorSet1) {
        this.detallereclamoproveedorSet1 = detallereclamoproveedorSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreclamo != null ? idreclamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reclamoproveedor)) {
            return false;
        }
        Reclamoproveedor other = (Reclamoproveedor) object;
        if ((this.idreclamo == null && other.idreclamo != null) || (this.idreclamo != null && !this.idreclamo.equals(other.idreclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reclamoproveedor[idreclamo=" + idreclamo + "]";
    }

}
