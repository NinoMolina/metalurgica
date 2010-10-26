/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "detalleproductoreal")
@NamedQueries({
    @NamedQuery(name = "Detalleproductoreal.findAll", query = "SELECT d FROM Detalleproductoreal d"),
    @NamedQuery(name = "Detalleproductoreal.findByIddetalle", query = "SELECT d FROM Detalleproductoreal d WHERE d.detalleproductorealPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleproductoreal.findByIdproductoreal", query = "SELECT d FROM Detalleproductoreal d WHERE d.detalleproductorealPK.idproductoreal = :idproductoreal"),
    @NamedQuery(name = "Detalleproductoreal.findByIdpieza", query = "SELECT d FROM Detalleproductoreal d WHERE d.idpieza = :idpieza"),
    @NamedQuery(name = "Detalleproductoreal.findByCantidadPiezas", query = "SELECT d FROM Detalleproductoreal d WHERE d.cantidadPiezas = :cantidadPiezas"),
    @NamedQuery(name = "Detalleproductoreal.findByDescripcion", query = "SELECT d FROM Detalleproductoreal d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detalleproductoreal.findByIdpiezareal", query = "SELECT d FROM Detalleproductoreal d WHERE d.idpiezareal = :idpiezareal")})
public class Detalleproductoreal implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleproductorealPK detalleproductorealPK;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @Column(name = "cantidadPiezas")
    private Integer cantidadPiezas;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "idpiezareal")
    private BigInteger idpiezareal;
    @JoinColumn(name = "idproductoreal", referencedColumnName = "idproductoreal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productoreal productoreal;

    public Detalleproductoreal() {
    }

    public Detalleproductoreal(DetalleproductorealPK detalleproductorealPK) {
        this.detalleproductorealPK = detalleproductorealPK;
    }

    public Detalleproductoreal(long iddetalle, long idproductoreal) {
        this.detalleproductorealPK = new DetalleproductorealPK(iddetalle, idproductoreal);
    }

    public DetalleproductorealPK getDetalleproductorealPK() {
        return detalleproductorealPK;
    }

    public void setDetalleproductorealPK(DetalleproductorealPK detalleproductorealPK) {
        this.detalleproductorealPK = detalleproductorealPK;
    }

    public BigInteger getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(BigInteger idpieza) {
        this.idpieza = idpieza;
    }

    public Integer getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(Integer cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getIdpiezareal() {
        return idpiezareal;
    }

    public void setIdpiezareal(BigInteger idpiezareal) {
        this.idpiezareal = idpiezareal;
    }

    public Productoreal getProductoreal() {
        return productoreal;
    }

    public void setProductoreal(Productoreal productoreal) {
        this.productoreal = productoreal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleproductorealPK != null ? detalleproductorealPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleproductoreal)) {
            return false;
        }
        Detalleproductoreal other = (Detalleproductoreal) object;
        if ((this.detalleproductorealPK == null && other.detalleproductorealPK != null) || (this.detalleproductorealPK != null && !this.detalleproductorealPK.equals(other.detalleproductorealPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Detalleproductoreal[detalleproductorealPK=" + detalleproductorealPK + "]";
    }

}
