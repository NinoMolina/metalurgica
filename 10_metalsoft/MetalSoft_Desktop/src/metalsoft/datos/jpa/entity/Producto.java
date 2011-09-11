/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @SequenceGenerator(name = "producto_seq", sequenceName = "producto_idproducto_seq", allocationSize = 1)
    @Column(name = "idproducto")
    private Long idproducto;
    @Column(name = "nroproducto")
    private BigInteger nroproducto;
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preciounitario")
    private Double preciounitario;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "producto")
    private List<Detalleremito> detalleremitoList;
    @OneToMany(mappedBy = "idproducto")
    private List<Detalleplanificacionproduccion> detalleplanificacionproduccionList;
    @OneToMany(mappedBy = "producto")
    private List<Productoreal> productorealList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Detalleproducto> detalleproductoList;
    @OneToMany(mappedBy = "producto")
    private List<Detalleplanificacioncalidad> detalleplanificacioncalidadList;
    @OneToMany(mappedBy = "producto")
    private List<Detallepedido> detallepedidoList;
    @OneToMany(mappedBy = "producto")
    private List<Detallereclamocliente> detallereclamoclienteList;
    @OneToMany(mappedBy = "idproducto")
    private List<Detallepresupuesto> detallepresupuestoList;

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

    public List<Detalleremito> getDetalleremitoList() {
        return detalleremitoList;
    }

    public void setDetalleremitoList(List<Detalleremito> detalleremitoList) {
        this.detalleremitoList = detalleremitoList;
    }

    public List<Detalleplanificacionproduccion> getDetalleplanificacionproduccionList() {
        return detalleplanificacionproduccionList;
    }

    public void setDetalleplanificacionproduccionList(List<Detalleplanificacionproduccion> detalleplanificacionproduccionList) {
        this.detalleplanificacionproduccionList = detalleplanificacionproduccionList;
    }

    public List<Productoreal> getProductorealList() {
        return productorealList;
    }

    public void setProductorealList(List<Productoreal> productorealList) {
        this.productorealList = productorealList;
    }

    public List<Detalleproducto> getDetalleproductoList() {
        return detalleproductoList;
    }

    public void setDetalleproductoList(List<Detalleproducto> detalleproductoList) {
        this.detalleproductoList = detalleproductoList;
    }

    public List<Detalleplanificacioncalidad> getDetalleplanificacioncalidadList() {
        return detalleplanificacioncalidadList;
    }

    public void setDetalleplanificacioncalidadList(List<Detalleplanificacioncalidad> detalleplanificacioncalidadList) {
        this.detalleplanificacioncalidadList = detalleplanificacioncalidadList;
    }

    public List<Detallepedido> getDetallepedidoList() {
        return detallepedidoList;
    }

    public void setDetallepedidoList(List<Detallepedido> detallepedidoList) {
        this.detallepedidoList = detallepedidoList;
    }

    public List<Detallereclamocliente> getDetallereclamoclienteList() {
        return detallereclamoclienteList;
    }

    public void setDetallereclamoclienteList(List<Detallereclamocliente> detallereclamoclienteList) {
        this.detallereclamoclienteList = detallereclamoclienteList;
    }

    public List<Detallepresupuesto> getDetallepresupuestoList() {
        return detallepresupuestoList;
    }

    public void setDetallepresupuestoList(List<Detallepresupuesto> detallepresupuestoList) {
        this.detallepresupuestoList = detallepresupuestoList;
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
        return "metalsoft.datos.jpa.entity.Producto[ idproducto=" + idproducto + " ]";
    }
    
}
