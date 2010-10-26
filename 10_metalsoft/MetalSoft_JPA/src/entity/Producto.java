/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdproducto", query = "SELECT p FROM Producto p WHERE p.idproducto = :idproducto"),
    @NamedQuery(name = "Producto.findByNroproducto", query = "SELECT p FROM Producto p WHERE p.nroproducto = :nroproducto"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByPreciounitario", query = "SELECT p FROM Producto p WHERE p.preciounitario = :preciounitario"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idproducto")
    private Long idproducto;
    @Column(name = "nroproducto")
    private BigInteger nroproducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "preciounitario")
    private Double preciounitario;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "producto")
    private Set<Detalleremito> detalleremitoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private Set<Detalleproducto> detalleproductoSet;
    @OneToMany(mappedBy = "producto")
    private Set<Detallepedido> detallepedidoSet;
    @OneToMany(mappedBy = "producto")
    private Set<Detallereclamocliente> detallereclamoclienteSet;
    @OneToMany(mappedBy = "idproducto")
    private Set<Detallepresupuesto> detallepresupuestoSet;

    public Producto() {
    }

    public Producto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public BigInteger getNroproducto() {
        return nroproducto;
    }

    public void setNroproducto(BigInteger nroproducto) {
        this.nroproducto = nroproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(Double preciounitario) {
        this.preciounitario = preciounitario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Detalleremito> getDetalleremitoSet() {
        return detalleremitoSet;
    }

    public void setDetalleremitoSet(Set<Detalleremito> detalleremitoSet) {
        this.detalleremitoSet = detalleremitoSet;
    }

    public Set<Detalleproducto> getDetalleproductoSet() {
        return detalleproductoSet;
    }

    public void setDetalleproductoSet(Set<Detalleproducto> detalleproductoSet) {
        this.detalleproductoSet = detalleproductoSet;
    }

    public Set<Detallepedido> getDetallepedidoSet() {
        return detallepedidoSet;
    }

    public void setDetallepedidoSet(Set<Detallepedido> detallepedidoSet) {
        this.detallepedidoSet = detallepedidoSet;
    }

    public Set<Detallereclamocliente> getDetallereclamoclienteSet() {
        return detallereclamoclienteSet;
    }

    public void setDetallereclamoclienteSet(Set<Detallereclamocliente> detallereclamoclienteSet) {
        this.detallereclamoclienteSet = detallereclamoclienteSet;
    }

    public Set<Detallepresupuesto> getDetallepresupuestoSet() {
        return detallepresupuestoSet;
    }

    public void setDetallepresupuestoSet(Set<Detallepresupuesto> detallepresupuestoSet) {
        this.detallepresupuestoSet = detallepresupuestoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Producto[idproducto=" + idproducto + "]";
    }

}
