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
@Table(name = "remito")
@NamedQueries({
    @NamedQuery(name = "Remito.findAll", query = "SELECT r FROM Remito r"),
    @NamedQuery(name = "Remito.findByIdremito", query = "SELECT r FROM Remito r WHERE r.idremito = :idremito"),
    @NamedQuery(name = "Remito.findByNroremito", query = "SELECT r FROM Remito r WHERE r.nroremito = :nroremito"),
    @NamedQuery(name = "Remito.findByFechaemision", query = "SELECT r FROM Remito r WHERE r.fechaemision = :fechaemision")})
public class Remito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "remito_seq")
    @SequenceGenerator(name = "remito_seq", sequenceName = "remito_idremito_seq", allocationSize = 1)
    @Column(name = "idremito")
    private Long idremito;
    @Column(name = "nroremito")
    private BigInteger nroremito;
    @Column(name = "fechaemision")
    @Temporal(TemporalType.DATE)
    private Date fechaemision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remito")
    private List<Detalleremito> detalleremitoList;
    @JoinColumn(name = "pedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoremito estado;

    public Remito() {
    }

    public Remito(Long idremito) {
        this.idremito = idremito;
    }

    public Long getIdremito() {
        return idremito;
    }

    public void setIdremito(Long idremito) {
        this.idremito = idremito;
    }

    public BigInteger getNroremito() {
        return nroremito;
    }

    public void setNroremito(BigInteger nroremito) {
        this.nroremito = nroremito;
    }

    public Date getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }

    public List<Detalleremito> getDetalleremitoList() {
        return detalleremitoList;
    }

    public void setDetalleremitoList(List<Detalleremito> detalleremitoList) {
        this.detalleremitoList = detalleremitoList;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Estadoremito getEstado() {
        return estado;
    }

    public void setEstado(Estadoremito estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idremito != null ? idremito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remito)) {
            return false;
        }
        Remito other = (Remito) object;
        if ((this.idremito == null && other.idremito != null) || (this.idremito != null && !this.idremito.equals(other.idremito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Remito[ idremito=" + idremito + " ]";
    }
    
}
