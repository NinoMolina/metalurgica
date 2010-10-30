/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
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
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet;
    @OneToMany(mappedBy = "idmaquina1")
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadomaquina estado;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadomaquina estado1;
    @JoinColumn(name = "marca", referencedColumnName = "idmarca")
    @ManyToOne
    private Marca marca;
    @JoinColumn(name = "marca", referencedColumnName = "idmarca")
    @ManyToOne
    private Marca marca1;
    @JoinColumn(name = "tipomaquina", referencedColumnName = "idtipomaquina")
    @ManyToOne
    private Tipomaquina tipomaquina;
    @JoinColumn(name = "tipomaquina", referencedColumnName = "idtipomaquina")
    @ManyToOne
    private Tipomaquina tipomaquina1;
    @JoinColumn(name = "idunidadmedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida idunidadmedida;
    @JoinColumn(name = "idunidadmedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida idunidadmedida1;

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

    public Estadomaquina getEstado() {
        return estado;
    }

    public void setEstado(Estadomaquina estado) {
        this.estado = estado;
    }

    public Estadomaquina getEstado1() {
        return estado1;
    }

    public void setEstado1(Estadomaquina estado1) {
        this.estado1 = estado1;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Marca getMarca1() {
        return marca1;
    }

    public void setMarca1(Marca marca1) {
        this.marca1 = marca1;
    }

    public Tipomaquina getTipomaquina() {
        return tipomaquina;
    }

    public void setTipomaquina(Tipomaquina tipomaquina) {
        this.tipomaquina = tipomaquina;
    }

    public Tipomaquina getTipomaquina1() {
        return tipomaquina1;
    }

    public void setTipomaquina1(Tipomaquina tipomaquina1) {
        this.tipomaquina1 = tipomaquina1;
    }

    public Unidadmedida getIdunidadmedida() {
        return idunidadmedida;
    }

    public void setIdunidadmedida(Unidadmedida idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
    }

    public Unidadmedida getIdunidadmedida1() {
        return idunidadmedida1;
    }

    public void setIdunidadmedida1(Unidadmedida idunidadmedida1) {
        this.idunidadmedida1 = idunidadmedida1;
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
        return "entity.Maquina[idmaquina=" + idmaquina + "]";
    }

}
