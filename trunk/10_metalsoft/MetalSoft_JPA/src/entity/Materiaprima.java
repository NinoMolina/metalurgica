/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "materiaprima")
@NamedQueries({
    @NamedQuery(name = "Materiaprima.findAll", query = "SELECT m FROM Materiaprima m"),
    @NamedQuery(name = "Materiaprima.findByIdmateriaprima", query = "SELECT m FROM Materiaprima m WHERE m.idmateriaprima = :idmateriaprima"),
    @NamedQuery(name = "Materiaprima.findByNombre", query = "SELECT m FROM Materiaprima m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Materiaprima.findByFechaalta", query = "SELECT m FROM Materiaprima m WHERE m.fechaalta = :fechaalta"),
    @NamedQuery(name = "Materiaprima.findByFechabaja", query = "SELECT m FROM Materiaprima m WHERE m.fechabaja = :fechabaja"),
    @NamedQuery(name = "Materiaprima.findByStock", query = "SELECT m FROM Materiaprima m WHERE m.stock = :stock"),
    @NamedQuery(name = "Materiaprima.findByDescripcion", query = "SELECT m FROM Materiaprima m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Materiaprima.findByAlto", query = "SELECT m FROM Materiaprima m WHERE m.alto = :alto"),
    @NamedQuery(name = "Materiaprima.findByLargo", query = "SELECT m FROM Materiaprima m WHERE m.largo = :largo"),
    @NamedQuery(name = "Materiaprima.findByAncho", query = "SELECT m FROM Materiaprima m WHERE m.ancho = :ancho"),
    @NamedQuery(name = "Materiaprima.findByCodproducto", query = "SELECT m FROM Materiaprima m WHERE m.codproducto = :codproducto"),
    @NamedQuery(name = "Materiaprima.findByPrecio", query = "SELECT m FROM Materiaprima m WHERE m.precio = :precio"),
    @NamedQuery(name = "Materiaprima.findByNromateriaprima", query = "SELECT m FROM Materiaprima m WHERE m.nromateriaprima = :nromateriaprima")})
public class Materiaprima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmateriaprima")
    private Long idmateriaprima;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechaalta")
    @Temporal(TemporalType.DATE)
    private Date fechaalta;
    @Column(name = "fechabaja")
    @Temporal(TemporalType.DATE)
    private Date fechabaja;
    @Column(name = "stock")
    private BigInteger stock;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "alto")
    private BigDecimal alto;
    @Column(name = "largo")
    private BigDecimal largo;
    @Column(name = "ancho")
    private BigDecimal ancho;
    @Column(name = "codproducto")
    private String codproducto;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "nromateriaprima")
    private BigInteger nromateriaprima;
    @OneToMany(mappedBy = "materiaprima")
    private Set<Detallecompra> detallecompraSet;
    @JoinColumn(name = "codbarra", referencedColumnName = "idcodigo")
    @ManyToOne
    private Codigodebarra codbarra;
    @JoinColumn(name = "tipomaterial", referencedColumnName = "idtipomaterial")
    @ManyToOne
    private Tipomaterial tipomaterial;
    @JoinColumn(name = "unidadmedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida unidadmedida;
    @OneToMany(mappedBy = "materiaprima")
    private Set<Pieza> piezaSet;
    @OneToMany(mappedBy = "idmateriaprima")
    private Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet;
    @OneToMany(mappedBy = "materiaprima")
    private Set<Matriz> matrizSet;
    @OneToMany(mappedBy = "idmateriaprima")
    private Set<Detallempasignada> detallempasignadaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiaprima")
    private Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet;
    @OneToMany(mappedBy = "idmateriaprima")
    private Set<Detalleproductopresupuesto> detalleproductopresupuestoSet;

    public Materiaprima() {
    }

    public Materiaprima(Long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public Long getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(Long idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }

    public BigInteger getStock() {
        return stock;
    }

    public void setStock(BigInteger stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getAlto() {
        return alto;
    }

    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public String getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(String codproducto) {
        this.codproducto = codproducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public BigInteger getNromateriaprima() {
        return nromateriaprima;
    }

    public void setNromateriaprima(BigInteger nromateriaprima) {
        this.nromateriaprima = nromateriaprima;
    }

    public Set<Detallecompra> getDetallecompraSet() {
        return detallecompraSet;
    }

    public void setDetallecompraSet(Set<Detallecompra> detallecompraSet) {
        this.detallecompraSet = detallecompraSet;
    }

    public Codigodebarra getCodbarra() {
        return codbarra;
    }

    public void setCodbarra(Codigodebarra codbarra) {
        this.codbarra = codbarra;
    }

    public Tipomaterial getTipomaterial() {
        return tipomaterial;
    }

    public void setTipomaterial(Tipomaterial tipomaterial) {
        this.tipomaterial = tipomaterial;
    }

    public Unidadmedida getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(Unidadmedida unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public Set<Pieza> getPiezaSet() {
        return piezaSet;
    }

    public void setPiezaSet(Set<Pieza> piezaSet) {
        this.piezaSet = piezaSet;
    }

    public Set<Detallerequerimientosmateriaprima> getDetallerequerimientosmateriaprimaSet() {
        return detallerequerimientosmateriaprimaSet;
    }

    public void setDetallerequerimientosmateriaprimaSet(Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet) {
        this.detallerequerimientosmateriaprimaSet = detallerequerimientosmateriaprimaSet;
    }

    public Set<Matriz> getMatrizSet() {
        return matrizSet;
    }

    public void setMatrizSet(Set<Matriz> matrizSet) {
        this.matrizSet = matrizSet;
    }

    public Set<Detallempasignada> getDetallempasignadaSet() {
        return detallempasignadaSet;
    }

    public void setDetallempasignadaSet(Set<Detallempasignada> detallempasignadaSet) {
        this.detallempasignadaSet = detallempasignadaSet;
    }

    public Set<Proveedorxmateriaprima> getProveedorxmateriaprimaSet() {
        return proveedorxmateriaprimaSet;
    }

    public void setProveedorxmateriaprimaSet(Set<Proveedorxmateriaprima> proveedorxmateriaprimaSet) {
        this.proveedorxmateriaprimaSet = proveedorxmateriaprimaSet;
    }

    public Set<Detalleproductopresupuesto> getDetalleproductopresupuestoSet() {
        return detalleproductopresupuestoSet;
    }

    public void setDetalleproductopresupuestoSet(Set<Detalleproductopresupuesto> detalleproductopresupuestoSet) {
        this.detalleproductopresupuestoSet = detalleproductopresupuestoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmateriaprima != null ? idmateriaprima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiaprima)) {
            return false;
        }
        Materiaprima other = (Materiaprima) object;
        if ((this.idmateriaprima == null && other.idmateriaprima != null) || (this.idmateriaprima != null && !this.idmateriaprima.equals(other.idmateriaprima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Materiaprima[idmateriaprima=" + idmateriaprima + "]";
    }

}
