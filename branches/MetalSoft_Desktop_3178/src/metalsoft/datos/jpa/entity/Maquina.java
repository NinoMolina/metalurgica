/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "maquina")
@NamedQueries({
    @NamedQuery(name = "Maquina.findAll", query = "SELECT m FROM Maquina m"),
    @NamedQuery(name = "Maquina.findByIdmaquina", query = "SELECT m FROM Maquina m WHERE m.idmaquina = :idmaquina"),
    @NamedQuery(name = "Maquina.findByNombre", query = "SELECT m FROM Maquina m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Maquina.findByDescripcion", query = "SELECT m FROM Maquina m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Maquina.findByFechaalta", query = "SELECT m FROM Maquina m WHERE m.fechaalta = :fechaalta"),
    @NamedQuery(name = "Maquina.findByFechabaja", query = "SELECT m FROM Maquina m WHERE m.fechabaja = :fechabaja"),
    @NamedQuery(name = "Maquina.findByTiempocapacidadproduccion", query = "SELECT m FROM Maquina m WHERE m.tiempocapacidadproduccion = :tiempocapacidadproduccion")})
public class Maquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmaquina")
    private Long idmaquina;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechaalta")
    @Temporal(TemporalType.DATE)
    private Date fechaalta;
    @Column(name = "fechabaja")
    @Temporal(TemporalType.DATE)
    private Date fechabaja;
    @Column(name = "tiempocapacidadproduccion")
    @Temporal(TemporalType.TIME)
    private Date tiempocapacidadproduccion;
    @OneToMany(mappedBy = "idmaquina")
    private List<Detalleplanificacionproduccion> detalleplanificacionproduccionList;
    @JoinColumn(name = "idunidadmedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida idunidadmedida;
    @JoinColumn(name = "tipomaquina", referencedColumnName = "idtipomaquina")
    @ManyToOne
    private Tipomaquina tipomaquina;
    @JoinColumn(name = "marca", referencedColumnName = "idmarca")
    @ManyToOne
    private Marca marca;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadomaquina estado;
    @OneToMany(mappedBy = "maquina")
    private List<Detalleplanificacioncalidad> detalleplanificacioncalidadList;

    public Maquina() {
    }

    public Maquina(Long idmaquina) {
        this.idmaquina = idmaquina;
    }

    public Long getIdmaquina() {
        return idmaquina;
    }

    public void setIdmaquina(Long idmaquina) {
        this.idmaquina = idmaquina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getTiempocapacidadproduccion() {
        return tiempocapacidadproduccion;
    }

    public void setTiempocapacidadproduccion(Date tiempocapacidadproduccion) {
        this.tiempocapacidadproduccion = tiempocapacidadproduccion;
    }

    public List<Detalleplanificacionproduccion> getDetalleplanificacionproduccionList() {
        return detalleplanificacionproduccionList;
    }

    public void setDetalleplanificacionproduccionList(List<Detalleplanificacionproduccion> detalleplanificacionproduccionList) {
        this.detalleplanificacionproduccionList = detalleplanificacionproduccionList;
    }

    public Unidadmedida getIdunidadmedida() {
        return idunidadmedida;
    }

    public void setIdunidadmedida(Unidadmedida idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
    }

    public Tipomaquina getTipomaquina() {
        return tipomaquina;
    }

    public void setTipomaquina(Tipomaquina tipomaquina) {
        this.tipomaquina = tipomaquina;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Estadomaquina getEstado() {
        return estado;
    }

    public void setEstado(Estadomaquina estado) {
        this.estado = estado;
    }

    public List<Detalleplanificacioncalidad> getDetalleplanificacioncalidadList() {
        return detalleplanificacioncalidadList;
    }

    public void setDetalleplanificacioncalidadList(List<Detalleplanificacioncalidad> detalleplanificacioncalidadList) {
        this.detalleplanificacioncalidadList = detalleplanificacioncalidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmaquina != null ? idmaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquina)) {
            return false;
        }
        Maquina other = (Maquina) object;
        if ((this.idmaquina == null && other.idmaquina != null) || (this.idmaquina != null && !this.idmaquina.equals(other.idmaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Maquina[ idmaquina=" + idmaquina + " ]";
    }
    
}
