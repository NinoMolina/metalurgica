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
@Table(name = "reclamoempresametalurgica")
@NamedQueries({
    @NamedQuery(name = "Reclamoempresametalurgica.findAll", query = "SELECT r FROM Reclamoempresametalurgica r"),
    @NamedQuery(name = "Reclamoempresametalurgica.findByIdreclamo", query = "SELECT r FROM Reclamoempresametalurgica r WHERE r.idreclamo = :idreclamo"),
    @NamedQuery(name = "Reclamoempresametalurgica.findByNroreclamo", query = "SELECT r FROM Reclamoempresametalurgica r WHERE r.nroreclamo = :nroreclamo"),
    @NamedQuery(name = "Reclamoempresametalurgica.findByMotivo", query = "SELECT r FROM Reclamoempresametalurgica r WHERE r.motivo = :motivo"),
    @NamedQuery(name = "Reclamoempresametalurgica.findByFechareclamo", query = "SELECT r FROM Reclamoempresametalurgica r WHERE r.fechareclamo = :fechareclamo")})
public class Reclamoempresametalurgica implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamoempresametalurgica")
    private Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamoempresametalurgica1")
    private Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSet1;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo1;
    @JoinColumn(name = "trabajotercerizado", referencedColumnName = "idtrabajo")
    @ManyToOne
    private Trabajotercerizado trabajotercerizado;
    @JoinColumn(name = "trabajotercerizado", referencedColumnName = "idtrabajo")
    @ManyToOne
    private Trabajotercerizado trabajotercerizado1;

    public Reclamoempresametalurgica() {
    }

    public Reclamoempresametalurgica(Long idreclamo) {
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

    public Set<Detallereclamoempresametalurgica> getDetallereclamoempresametalurgicaSet() {
        return detallereclamoempresametalurgicaSet;
    }

    public void setDetallereclamoempresametalurgicaSet(Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSet) {
        this.detallereclamoempresametalurgicaSet = detallereclamoempresametalurgicaSet;
    }

    public Set<Detallereclamoempresametalurgica> getDetallereclamoempresametalurgicaSet1() {
        return detallereclamoempresametalurgicaSet1;
    }

    public void setDetallereclamoempresametalurgicaSet1(Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicaSet1) {
        this.detallereclamoempresametalurgicaSet1 = detallereclamoempresametalurgicaSet1;
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

    public Trabajotercerizado getTrabajotercerizado() {
        return trabajotercerizado;
    }

    public void setTrabajotercerizado(Trabajotercerizado trabajotercerizado) {
        this.trabajotercerizado = trabajotercerizado;
    }

    public Trabajotercerizado getTrabajotercerizado1() {
        return trabajotercerizado1;
    }

    public void setTrabajotercerizado1(Trabajotercerizado trabajotercerizado1) {
        this.trabajotercerizado1 = trabajotercerizado1;
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
        if (!(object instanceof Reclamoempresametalurgica)) {
            return false;
        }
        Reclamoempresametalurgica other = (Reclamoempresametalurgica) object;
        if ((this.idreclamo == null && other.idreclamo != null) || (this.idreclamo != null && !this.idreclamo.equals(other.idreclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reclamoempresametalurgica[idreclamo=" + idreclamo + "]";
    }

}
