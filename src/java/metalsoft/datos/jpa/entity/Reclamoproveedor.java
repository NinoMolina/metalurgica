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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reclamoproveedor_seq")
    @SequenceGenerator(name = "reclamoproveedor_seq", sequenceName = "reclamoproveedor_idreclamo_seq", allocationSize = 1)
    @Column(name = "idreclamo")
    private Long idreclamo;
    @Column(name = "nroreclamo")
    private BigInteger nroreclamo;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "fechareclamo")
    @Temporal(TemporalType.DATE)
    private Date fechareclamo;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo;
    @JoinColumn(name = "idestadoreclamo", referencedColumnName = "idestadoreclamo")
    @ManyToOne
    private Estadoreclamo idestadoreclamo;
    @JoinColumn(name = "compra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra compra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamoproveedor")
    private List<Detallereclamoproveedor> detallereclamoproveedorList;

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

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public List<Detallereclamoproveedor> getDetallereclamoproveedorList() {
        return detallereclamoproveedorList;
    }

    public void setDetallereclamoproveedorList(List<Detallereclamoproveedor> detallereclamoproveedorList) {
        this.detallereclamoproveedorList = detallereclamoproveedorList;
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
        return "metalsoft.datos.jpa.entity.Reclamoproveedor[ idreclamo=" + idreclamo + " ]";
    }
    
}
