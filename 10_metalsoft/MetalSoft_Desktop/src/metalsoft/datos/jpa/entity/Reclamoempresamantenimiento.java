/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "reclamoempresamantenimiento")
@NamedQueries({
    @NamedQuery(name = "Reclamoempresamantenimiento.findAll", query = "SELECT r FROM Reclamoempresamantenimiento r"),
    @NamedQuery(name = "Reclamoempresamantenimiento.findByIdreclamo", query = "SELECT r FROM Reclamoempresamantenimiento r WHERE r.idreclamo = :idreclamo"),
    @NamedQuery(name = "Reclamoempresamantenimiento.findByNroreclamo", query = "SELECT r FROM Reclamoempresamantenimiento r WHERE r.nroreclamo = :nroreclamo"),
    @NamedQuery(name = "Reclamoempresamantenimiento.findByMotivo", query = "SELECT r FROM Reclamoempresamantenimiento r WHERE r.motivo = :motivo"),
    @NamedQuery(name = "Reclamoempresamantenimiento.findByFechareclamo", query = "SELECT r FROM Reclamoempresamantenimiento r WHERE r.fechareclamo = :fechareclamo")})
public class Reclamoempresamantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reclamoempresamantenimiento_seq")
    @SequenceGenerator(name = "reclamoempresamantenimiento_seq", sequenceName = "reclamoempresamantenimiento_idreclamo_seq", allocationSize = 1)
    @Column(name = "idreclamo")
    private Long idreclamo;
    @Column(name = "nroreclamo")
    private BigInteger nroreclamo;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "fechareclamo")
    @Temporal(TemporalType.DATE)
    private Date fechareclamo;
    @JoinColumn(name = "trabajotercerizado", referencedColumnName = "idtrabajo")
    @ManyToOne
    private Trabajotercerizado trabajotercerizado;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo;
    @JoinColumn(name = "idestadoreclamo", referencedColumnName = "idestadoreclamo")
    @ManyToOne
    private Estadoreclamo idestadoreclamo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idreclamo")
    private List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoList;

    public Reclamoempresamantenimiento() {
    }

    public Reclamoempresamantenimiento(Long idreclamo) {
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

    public Trabajotercerizado getTrabajotercerizado() {
        return trabajotercerizado;
    }

    public void setTrabajotercerizado(Trabajotercerizado trabajotercerizado) {
        this.trabajotercerizado = trabajotercerizado;
    }

    public Tiporeclamo getTiporeclamo() {
        return tiporeclamo;
    }

    public void setTiporeclamo(Tiporeclamo tiporeclamo) {
        this.tiporeclamo = tiporeclamo;
    }

    public Estadoreclamo getIdestadoreclamo() {
        return idestadoreclamo;
    }

    public void setIdestadoreclamo(Estadoreclamo idestadoreclamo) {
        this.idestadoreclamo = idestadoreclamo;
    }

    public List<Detallereclamoempresamantenimiento> getDetallereclamoempresamantenimientoList() {
        return detallereclamoempresamantenimientoList;
    }

    public void setDetallereclamoempresamantenimientoList(List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoList) {
        this.detallereclamoempresamantenimientoList = detallereclamoempresamantenimientoList;
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
        if (!(object instanceof Reclamoempresamantenimiento)) {
            return false;
        }
        Reclamoempresamantenimiento other = (Reclamoempresamantenimiento) object;
        if ((this.idreclamo == null && other.idreclamo != null) || (this.idreclamo != null && !this.idreclamo.equals(other.idreclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Reclamoempresamantenimiento[ idreclamo=" + idreclamo + " ]";
    }
    
}
