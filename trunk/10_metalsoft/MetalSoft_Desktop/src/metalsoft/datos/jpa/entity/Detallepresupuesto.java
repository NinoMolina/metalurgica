/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
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

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallepresupuesto")
@NamedQueries({
    @NamedQuery(name = "Detallepresupuesto.findAll", query = "SELECT d FROM Detallepresupuesto d"),
    @NamedQuery(name = "Detallepresupuesto.findByIddetalle", query = "SELECT d FROM Detallepresupuesto d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallepresupuesto.findByIddetallepedido", query = "SELECT d FROM Detallepresupuesto d WHERE d.iddetallepedido = :iddetallepedido"),
    @NamedQuery(name = "Detallepresupuesto.findByCantidad", query = "SELECT d FROM Detallepresupuesto d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallepresupuesto.findByPrecio", query = "SELECT d FROM Detallepresupuesto d WHERE d.precio = :precio")})
public class Detallepresupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detallepresupuesto_seq")
    @SequenceGenerator(name = "detallepresupuesto_seq", sequenceName = "detallepresupuesto_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "iddetallepedido")
    private BigInteger iddetallepedido;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne
    private Producto idproducto;
    @JoinColumn(name = "idpresupuesto", referencedColumnName = "idpresupuesto")
    @ManyToOne(optional = false)
    private Presupuesto idpresupuesto;
    @OneToMany(mappedBy = "iddetallepresupuesto")
    private List<Detalleproductopresupuesto> detalleproductopresupuestoList;

    public Detallepresupuesto() {
    }

    public Detallepresupuesto(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public BigInteger getIddetallepedido() {
        return iddetallepedido;
    }

    public void setIddetallepedido(BigInteger iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Presupuesto getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Presupuesto idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public List<Detalleproductopresupuesto> getDetalleproductopresupuestoList() {
        return detalleproductopresupuestoList;
    }

    public void setDetalleproductopresupuestoList(List<Detalleproductopresupuesto> detalleproductopresupuestoList) {
        this.detalleproductopresupuestoList = detalleproductopresupuestoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepresupuesto)) {
            return false;
        }
        Detallepresupuesto other = (Detallepresupuesto) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallepresupuesto[ iddetalle=" + iddetalle + " ]";
    }
    
}
