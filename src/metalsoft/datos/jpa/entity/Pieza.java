/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pieza_seq")
    @SequenceGenerator(name = "pieza_seq", sequenceName = "pieza_idpieza_seq", allocationSize = 1)
    @Column(name = "idpieza")
    private Long idpieza;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipomaterial")
    private BigInteger tipomaterial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "alto")
    private BigDecimal alto;
    @Column(name = "ancho")
    private BigDecimal ancho;
    @Column(name = "largo")
    private BigDecimal largo;
    @OneToMany(mappedBy = "idpieza")
    private List<Detalleplanificacionproduccion> detalleplanificacionproduccionList;
    @JoinColumn(name = "unidadmedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida unidadmedida;
    @JoinColumn(name = "matriz", referencedColumnName = "idmatriz")
    @ManyToOne
    private Matriz matriz;
    @JoinColumn(name = "materiaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima materiaprima;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpieza")
    private List<Piezareal> piezarealList;
    @OneToMany(mappedBy = "pieza")
    private List<Detalleejecucionplanificacion> detalleejecucionplanificacionList;
    @OneToMany(mappedBy = "pieza")
    private List<Detalleplanificacioncalidad> detalleplanificacioncalidadList;
    @OneToMany(mappedBy = "pieza")
    private List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList;
    @OneToMany(mappedBy = "idpieza")
    private List<Detalleproductopresupuesto> detalleproductopresupuestoList;

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

    public List<Detalleplanificacionproduccion> getDetalleplanificacionproduccionList() {
        return detalleplanificacionproduccionList;
    }

    public void setDetalleplanificacionproduccionList(List<Detalleplanificacionproduccion> detalleplanificacionproduccionList) {
        this.detalleplanificacionproduccionList = detalleplanificacionproduccionList;
    }

    public Unidadmedida getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(Unidadmedida unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public Materiaprima getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }

    public List<Piezareal> getPiezarealList() {
        return piezarealList;
    }

    public void setPiezarealList(List<Piezareal> piezarealList) {
        this.piezarealList = piezarealList;
    }

    public List<Detalleejecucionplanificacion> getDetalleejecucionplanificacionList() {
        return detalleejecucionplanificacionList;
    }

    public void setDetalleejecucionplanificacionList(List<Detalleejecucionplanificacion> detalleejecucionplanificacionList) {
        this.detalleejecucionplanificacionList = detalleejecucionplanificacionList;
    }

    public List<Detalleplanificacioncalidad> getDetalleplanificacioncalidadList() {
        return detalleplanificacioncalidadList;
    }

    public void setDetalleplanificacioncalidadList(List<Detalleplanificacioncalidad> detalleplanificacioncalidadList) {
        this.detalleplanificacioncalidadList = detalleplanificacioncalidadList;
    }

    public List<Detalleejecucionplanificacioncalidad> getDetalleejecucionplanificacioncalidadList() {
        return detalleejecucionplanificacioncalidadList;
    }

    public void setDetalleejecucionplanificacioncalidadList(List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList) {
        this.detalleejecucionplanificacioncalidadList = detalleejecucionplanificacioncalidadList;
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
        return "metalsoft.datos.jpa.entity.Pieza[ idpieza=" + idpieza + " ]";
    }
    
}
