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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presupuesto_seq")
    @SequenceGenerator(name = "presupuesto_seq", sequenceName = "presupuesto_idpresupuesto_seq", allocationSize = 1)
    @Column(name = "idpresupuesto")
    private Long idpresupuesto;
    @Column(name = "fechapresupuesto")
    @Temporal(TemporalType.DATE)
    private Date fechapresupuesto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montototal")
    private Double montototal;
    @Column(name = "gananciaadicional")
    private Double gananciaadicional;
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @Column(name = "nropresupuesto")
    private BigInteger nropresupuesto;
    @OneToMany(mappedBy = "presupuesto")
    private List<Pedido> pedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpresupuesto")
    private List<Detallepresupuesto> detallepresupuestoList;

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

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public List<Detallepresupuesto> getDetallepresupuestoList() {
        return detallepresupuestoList;
    }

    public void setDetallepresupuestoList(List<Detallepresupuesto> detallepresupuestoList) {
        this.detallepresupuestoList = detallepresupuestoList;
    }

    public Double getGananciaadicional() {
        return gananciaadicional;
    }

    public void setGananciaadicional(Double gananciaadicional) {
        this.gananciaadicional = gananciaadicional;
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
        return "metalsoft.datos.jpa.entity.Presupuesto[ idpresupuesto=" + idpresupuesto + " ]";
    }
}
