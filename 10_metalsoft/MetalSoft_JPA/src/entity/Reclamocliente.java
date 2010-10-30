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
@Table(name = "reclamocliente")
@NamedQueries({
    @NamedQuery(name = "Reclamocliente.findAll", query = "SELECT r FROM Reclamocliente r"),
    @NamedQuery(name = "Reclamocliente.findByIdreclamo", query = "SELECT r FROM Reclamocliente r WHERE r.idreclamo = :idreclamo"),
    @NamedQuery(name = "Reclamocliente.findByNroreclamo", query = "SELECT r FROM Reclamocliente r WHERE r.nroreclamo = :nroreclamo"),
    @NamedQuery(name = "Reclamocliente.findByMotivo", query = "SELECT r FROM Reclamocliente r WHERE r.motivo = :motivo"),
    @NamedQuery(name = "Reclamocliente.findByFechareclamo", query = "SELECT r FROM Reclamocliente r WHERE r.fechareclamo = :fechareclamo")})
public class Reclamocliente implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamocliente")
    private Set<Detallereclamocliente> detallereclamoclienteSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamocliente1")
    private Set<Detallereclamocliente> detallereclamoclienteSet1;
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente cliente1;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo1;

    public Reclamocliente() {
    }

    public Reclamocliente(Long idreclamo) {
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

    public Set<Detallereclamocliente> getDetallereclamoclienteSet() {
        return detallereclamoclienteSet;
    }

    public void setDetallereclamoclienteSet(Set<Detallereclamocliente> detallereclamoclienteSet) {
        this.detallereclamoclienteSet = detallereclamoclienteSet;
    }

    public Set<Detallereclamocliente> getDetallereclamoclienteSet1() {
        return detallereclamoclienteSet1;
    }

    public void setDetallereclamoclienteSet1(Set<Detallereclamocliente> detallereclamoclienteSet1) {
        this.detallereclamoclienteSet1 = detallereclamoclienteSet1;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreclamo != null ? idreclamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reclamocliente)) {
            return false;
        }
        Reclamocliente other = (Reclamocliente) object;
        if ((this.idreclamo == null && other.idreclamo != null) || (this.idreclamo != null && !this.idreclamo.equals(other.idreclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reclamocliente[idreclamo=" + idreclamo + "]";
    }

}
