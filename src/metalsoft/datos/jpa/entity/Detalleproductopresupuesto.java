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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detalleproductopresupuesto")
@NamedQueries({
    @NamedQuery(name = "Detalleproductopresupuesto.findAll", query = "SELECT d FROM Detalleproductopresupuesto d"),
    @NamedQuery(name = "Detalleproductopresupuesto.findByIddetalle", query = "SELECT d FROM Detalleproductopresupuesto d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleproductopresupuesto.findByCantmateriaprima", query = "SELECT d FROM Detalleproductopresupuesto d WHERE d.cantmateriaprima = :cantmateriaprima"),
    @NamedQuery(name = "Detalleproductopresupuesto.findByCantpiezas", query = "SELECT d FROM Detalleproductopresupuesto d WHERE d.cantpiezas = :cantpiezas"),
    @NamedQuery(name = "Detalleproductopresupuesto.findByPreciomateriaprima", query = "SELECT d FROM Detalleproductopresupuesto d WHERE d.preciomateriaprima = :preciomateriaprima"),
    @NamedQuery(name = "Detalleproductopresupuesto.findByIdproveedor", query = "SELECT d FROM Detalleproductopresupuesto d WHERE d.idproveedor = :idproveedor")})
public class Detalleproductopresupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "cantmateriaprima")
    private Integer cantmateriaprima;
    @Column(name = "cantpiezas")
    private Integer cantpiezas;
    @Column(name = "preciomateriaprima")
    private Double preciomateriaprima;
    @Column(name = "idproveedor")
    private BigInteger idproveedor;
    @OneToMany(mappedBy = "iddetalleproductopresupuesto")
    private List<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoList;

    @OneToMany(mappedBy = "iddetalleproductopresupuesto")
    private List<Detallepiezapresupuesto> detallepiezapresupuestoList;

    @JoinColumn(name = "iddetallepresupuesto", referencedColumnName = "iddetalle")
    @ManyToOne
    private Detallepresupuesto iddetallepresupuesto;

    @JoinColumn(name = "idmateriaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima idmateriaprima;

    @JoinColumn(name = "idpieza", referencedColumnName = "idpieza")
    @ManyToOne
    private Pieza idpieza;


    public Detalleproductopresupuesto() {
    }

    public Detalleproductopresupuesto(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getCantmateriaprima() {
        return cantmateriaprima;
    }

    public void setCantmateriaprima(Integer cantmateriaprima) {
        this.cantmateriaprima = cantmateriaprima;
    }

    public Integer getCantpiezas() {
        return cantpiezas;
    }

    public void setCantpiezas(Integer cantpiezas) {
        this.cantpiezas = cantpiezas;
    }

    public Double getPreciomateriaprima() {
        return preciomateriaprima;
    }

    public void setPreciomateriaprima(Double preciomateriaprima) {
        this.preciomateriaprima = preciomateriaprima;
    }

    public BigInteger getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(BigInteger idproveedor) {
        this.idproveedor = idproveedor;
    }

    public List<Detallepiezacalidadpresupuesto> getDetallepiezacalidadpresupuestoList() {
        return detallepiezacalidadpresupuestoList;
    }

    public void setDetallepiezacalidadpresupuestoList(List<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoList) {
        this.detallepiezacalidadpresupuestoList = detallepiezacalidadpresupuestoList;
    }


    public List<Detallepiezapresupuesto> getDetallepiezapresupuestoList() {
        return detallepiezapresupuestoList;
    }

    public void setDetallepiezapresupuestoList(List<Detallepiezapresupuesto> detallepiezapresupuestoList) {
        this.detallepiezapresupuestoList = detallepiezapresupuestoList;
    }


    public Detallepresupuesto getIddetallepresupuesto() {
        return iddetallepresupuesto;
    }

    public void setIddetallepresupuesto(Detallepresupuesto iddetallepresupuesto) {
        this.iddetallepresupuesto = iddetallepresupuesto;
    }


    public Materiaprima getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(Materiaprima idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }



    public Pieza getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(Pieza idpieza) {
        this.idpieza = idpieza;
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
        if (!(object instanceof Detalleproductopresupuesto)) {
            return false;
        }
        Detalleproductopresupuesto other = (Detalleproductopresupuesto) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalleproductopresupuesto[iddetalle=" + iddetalle + "]";
    }

}
