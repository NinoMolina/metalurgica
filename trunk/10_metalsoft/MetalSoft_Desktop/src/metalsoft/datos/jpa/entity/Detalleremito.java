/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detalleremito")
@NamedQueries({
    @NamedQuery(name = "Detalleremito.findAll", query = "SELECT d FROM Detalleremito d"),
    @NamedQuery(name = "Detalleremito.findByIddetalle", query = "SELECT d FROM Detalleremito d WHERE d.detalleremitoPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleremito.findByIdremito", query = "SELECT d FROM Detalleremito d WHERE d.detalleremitoPK.idremito = :idremito"),
    @NamedQuery(name = "Detalleremito.findByCantidad", query = "SELECT d FROM Detalleremito d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleremito.findByDescripcion", query = "SELECT d FROM Detalleremito d WHERE d.descripcion = :descripcion")})
public class Detalleremito implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleremitoPK detalleremitoPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "idremito", referencedColumnName = "idremito", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Remito remito;
    @JoinColumn(name = "producto", referencedColumnName = "idproducto")
    @ManyToOne
    private Producto producto;

    public Detalleremito() {
    }

    public Detalleremito(DetalleremitoPK detalleremitoPK) {
        this.detalleremitoPK = detalleremitoPK;
    }

    public Detalleremito(long iddetalle, long idremito) {
        this.detalleremitoPK = new DetalleremitoPK(iddetalle, idremito);
    }

    public DetalleremitoPK getDetalleremitoPK() {
        return detalleremitoPK;
    }

    public void setDetalleremitoPK(DetalleremitoPK detalleremitoPK) {
        this.detalleremitoPK = detalleremitoPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Remito getRemito() {
        return remito;
    }

    public void setRemito(Remito remito) {
        this.remito = remito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleremitoPK != null ? detalleremitoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleremito)) {
            return false;
        }
        Detalleremito other = (Detalleremito) object;
        if ((this.detalleremitoPK == null && other.detalleremitoPK != null) || (this.detalleremitoPK != null && !this.detalleremitoPK.equals(other.detalleremitoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalleremito[ detalleremitoPK=" + detalleremitoPK + " ]";
    }
    
}
