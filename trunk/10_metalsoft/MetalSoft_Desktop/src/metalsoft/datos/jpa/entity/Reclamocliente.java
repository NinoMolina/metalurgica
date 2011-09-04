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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reclamocliente_seq")
    @SequenceGenerator(name = "reclamocliente_seq", sequenceName = "reclamocliente_idreclamo_seq", allocationSize = 1)
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
    private List<Detallereclamocliente> detallereclamoclienteList;
    @JoinColumn(name = "tiporeclamo", referencedColumnName = "idtiporeclamo")
    @ManyToOne
    private Tiporeclamo tiporeclamo;
    @JoinColumn(name = "idestadoreclamo", referencedColumnName = "idestadoreclamo")
    @ManyToOne
    private Estadoreclamo idestadoreclamo;
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente cliente;

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

    public List<Detallereclamocliente> getDetallereclamoclienteList() {
        return detallereclamoclienteList;
    }

    public void setDetallereclamoclienteList(List<Detallereclamocliente> detallereclamoclienteList) {
        this.detallereclamoclienteList = detallereclamoclienteList;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        return "metalsoft.datos.jpa.entity.Reclamocliente[ idreclamo=" + idreclamo + " ]";
    }
    
}
