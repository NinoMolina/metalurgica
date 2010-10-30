/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
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
@Table(name = "pieza")
@NamedQueries({
    @NamedQuery(name = "Pieza.findAll", query = "SELECT p FROM Pieza p"),
    @NamedQuery(name = "Pieza.findByIdpieza", query = "SELECT p FROM Pieza p WHERE p.idpieza = :idpieza"),
    @NamedQuery(name = "Pieza.findByNombre", query = "SELECT p FROM Pieza p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Pieza.findByTipomaterial", query = "SELECT p FROM Pieza p WHERE p.tipomaterial = :tipomaterial"),
    @NamedQuery(name = "Pieza.findByAlto", query = "SELECT p FROM Pieza p WHERE p.alto = :alto"),
    @NamedQuery(name = "Pieza.findByAncho", query = "SELECT p FROM Pieza p WHERE p.ancho = :ancho"),
    @NamedQuery(name = "Pieza.findByLargo", query = "SELECT p FROM Pieza p WHERE p.largo = :largo")})
public class Pieza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpieza")
    private Long idpieza;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipomaterial")
    private BigInteger tipomaterial;
    @Column(name = "alto")
    private BigDecimal alto;
    @Column(name = "ancho")
    private BigDecimal ancho;
    @Column(name = "largo")
    private BigDecimal largo;
    @OneToMany(mappedBy = "idpieza")
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet;
    @OneToMany(mappedBy = "idpieza1")
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1;
    @JoinColumn(name = "materiaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima materiaprima;
    @JoinColumn(name = "materiaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima materiaprima1;
    @JoinColumn(name = "matriz", referencedColumnName = "idmatriz")
    @ManyToOne
    private Matriz matriz;
    @JoinColumn(name = "matriz", referencedColumnName = "idmatriz")
    @ManyToOne
    private Matriz matriz1;
    @JoinColumn(name = "unidadmedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida unidadmedida;
    @JoinColumn(name = "unidadmedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida unidadmedida1;
    @OneToMany(mappedBy = "pieza")
    private Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSet;
    @OneToMany(mappedBy = "idpieza")
    private Set<Detalleproductopresupuesto> detalleproductopresupuestoSet;
    @OneToMany(mappedBy = "idpieza1")
    private Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1;

    public Pieza() {
    }

    public Pieza(Long idpieza) {
        this.idpieza = idpieza;
    }

    public Long getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(Long idpieza) {
        this.idpieza = idpieza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getTipomaterial() {
        return tipomaterial;
    }

    public void setTipomaterial(BigInteger tipomaterial) {
        this.tipomaterial = tipomaterial;
    }

    public BigDecimal getAlto() {
        return alto;
    }

    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccionSet() {
        return detalleplanificacionproduccionSet;
    }

    public void setDetalleplanificacionproduccionSet(Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet) {
        this.detalleplanificacionproduccionSet = detalleplanificacionproduccionSet;
    }

    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccionSet1() {
        return detalleplanificacionproduccionSet1;
    }

    public void setDetalleplanificacionproduccionSet1(Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1) {
        this.detalleplanificacionproduccionSet1 = detalleplanificacionproduccionSet1;
    }

    public Materiaprima getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }

    public Materiaprima getMateriaprima1() {
        return materiaprima1;
    }

    public void setMateriaprima1(Materiaprima materiaprima1) {
        this.materiaprima1 = materiaprima1;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public Matriz getMatriz1() {
        return matriz1;
    }

    public void setMatriz1(Matriz matriz1) {
        this.matriz1 = matriz1;
    }

    public Unidadmedida getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(Unidadmedida unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public Unidadmedida getUnidadmedida1() {
        return unidadmedida1;
    }

    public void setUnidadmedida1(Unidadmedida unidadmedida1) {
        this.unidadmedida1 = unidadmedida1;
    }

    public Set<Detalleejecucionplanificacion> getDetalleejecucionplanificacionSet() {
        return detalleejecucionplanificacionSet;
    }

    public void setDetalleejecucionplanificacionSet(Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSet) {
        this.detalleejecucionplanificacionSet = detalleejecucionplanificacionSet;
    }

    public Set<Detalleproductopresupuesto> getDetalleproductopresupuestoSet() {
        return detalleproductopresupuestoSet;
    }

    public void setDetalleproductopresupuestoSet(Set<Detalleproductopresupuesto> detalleproductopresupuestoSet) {
        this.detalleproductopresupuestoSet = detalleproductopresupuestoSet;
    }

    public Set<Detalleproductopresupuesto> getDetalleproductopresupuestoSet1() {
        return detalleproductopresupuestoSet1;
    }

    public void setDetalleproductopresupuestoSet1(Set<Detalleproductopresupuesto> detalleproductopresupuestoSet1) {
        this.detalleproductopresupuestoSet1 = detalleproductopresupuestoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpieza != null ? idpieza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pieza)) {
            return false;
        }
        Pieza other = (Pieza) object;
        if ((this.idpieza == null && other.idpieza != null) || (this.idpieza != null && !this.idpieza.equals(other.idpieza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pieza[idpieza=" + idpieza + "]";
    }

}
