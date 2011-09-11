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
@Table(name = "compra")
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdcompra", query = "SELECT c FROM Compra c WHERE c.idcompra = :idcompra"),
    @NamedQuery(name = "Compra.findByNrocompra", query = "SELECT c FROM Compra c WHERE c.nrocompra = :nrocompra"),
    @NamedQuery(name = "Compra.findByFechacompra", query = "SELECT c FROM Compra c WHERE c.fechacompra = :fechacompra"),
    @NamedQuery(name = "Compra.findByVigencia", query = "SELECT c FROM Compra c WHERE c.vigencia = :vigencia"),
    @NamedQuery(name = "Compra.findByDocumentoremito", query = "SELECT c FROM Compra c WHERE c.documentoremito = :documentoremito"),
    @NamedQuery(name = "Compra.findByPreciototal", query = "SELECT c FROM Compra c WHERE c.preciototal = :preciototal"),
    @NamedQuery(name = "Compra.findByMotivo", query = "SELECT c FROM Compra c WHERE c.motivo = :motivo")})
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_seq")
    @SequenceGenerator(name = "compra_seq", sequenceName = "compra_idcompra_seq", allocationSize = 1)
    @Column(name = "idcompra")
    private Long idcompra;
    @Column(name = "nrocompra")
    private BigInteger nrocompra;
    @Column(name = "fechacompra")
    @Temporal(TemporalType.DATE)
    private Date fechacompra;
    @Column(name = "vigencia")
    @Temporal(TemporalType.DATE)
    private Date vigencia;
    @Column(name = "documentoremito")
    private BigInteger documentoremito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preciototal")
    private Double preciototal;
    @Column(name = "motivo")
    private String motivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
    private List<Detallecompra> detallecompraList;
    @OneToMany(mappedBy = "compra")
    private List<Reclamoproveedor> reclamoproveedorList;
    @JoinColumn(name = "proveedor", referencedColumnName = "idproveedor")
    @ManyToOne
    private Proveedor proveedor;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadocompra estado;

    public Compra() {
    }

    public Compra(Long idcompra) {
        this.idcompra = idcompra;
    }

    public Long getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Long idcompra) {
        this.idcompra = idcompra;
    }

    public BigInteger getNrocompra() {
        return nrocompra;
    }

    public void setNrocompra(BigInteger nrocompra) {
        this.nrocompra = nrocompra;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public BigInteger getDocumentoremito() {
        return documentoremito;
    }

    public void setDocumentoremito(BigInteger documentoremito) {
        this.documentoremito = documentoremito;
    }

    public Double getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(Double preciototal) {
        this.preciototal = preciototal;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<Detallecompra> getDetallecompraList() {
        return detallecompraList;
    }

    public void setDetallecompraList(List<Detallecompra> detallecompraList) {
        this.detallecompraList = detallecompraList;
    }

    public List<Reclamoproveedor> getReclamoproveedorList() {
        return reclamoproveedorList;
    }

    public void setReclamoproveedorList(List<Reclamoproveedor> reclamoproveedorList) {
        this.reclamoproveedorList = reclamoproveedorList;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Estadocompra getEstado() {
        return estado;
    }

    public void setEstado(Estadocompra estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompra != null ? idcompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idcompra == null && other.idcompra != null) || (this.idcompra != null && !this.idcompra.equals(other.idcompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Compra[ idcompra=" + idcompra + " ]";
    }
    
}
