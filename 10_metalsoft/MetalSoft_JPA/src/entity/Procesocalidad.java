/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "procesocalidad")
@NamedQueries({
    @NamedQuery(name = "Procesocalidad.findAll", query = "SELECT p FROM Procesocalidad p"),
    @NamedQuery(name = "Procesocalidad.findByIdprocesocalidad", query = "SELECT p FROM Procesocalidad p WHERE p.idprocesocalidad = :idprocesocalidad"),
    @NamedQuery(name = "Procesocalidad.findByNombre", query = "SELECT p FROM Procesocalidad p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Procesocalidad.findByNroproceso", query = "SELECT p FROM Procesocalidad p WHERE p.nroproceso = :nroproceso"),
    @NamedQuery(name = "Procesocalidad.findByEspecificacion", query = "SELECT p FROM Procesocalidad p WHERE p.especificacion = :especificacion"),
    @NamedQuery(name = "Procesocalidad.findByTolerancia", query = "SELECT p FROM Procesocalidad p WHERE p.tolerancia = :tolerancia"),
    @NamedQuery(name = "Procesocalidad.findByDescripcion", query = "SELECT p FROM Procesocalidad p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Procesocalidad.findByDuracionestimada", query = "SELECT p FROM Procesocalidad p WHERE p.duracionestimada = :duracionestimada"),
    @NamedQuery(name = "Procesocalidad.findByFechacreacion", query = "SELECT p FROM Procesocalidad p WHERE p.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "Procesocalidad.findByHerramienta", query = "SELECT p FROM Procesocalidad p WHERE p.herramienta = :herramienta")})
public class Procesocalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idprocesocalidad")
    private Long idprocesocalidad;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nroproceso")
    private BigInteger nroproceso;
    @Column(name = "especificacion")
    private String especificacion;
    @Column(name = "tolerancia")
    private String tolerancia;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "duracionestimada")
    @Temporal(TemporalType.TIME)
    private Date duracionestimada;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @Column(name = "herramienta")
    private String herramienta;
    @JoinColumn(name = "accioncalidad", referencedColumnName = "idaccioncalidad")
    @ManyToOne
    private Accioncalidad accioncalidad;
    @OneToMany(mappedBy = "idprocesocalidad")
    private Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procesocalidad")
    private Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSet;
    @OneToMany(mappedBy = "idprocesocalidad")
    private Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "procesocalidad")
    private Ejecucionprocesocalidad ejecucionprocesocalidad;
    @OneToMany(mappedBy = "procesocalidad")
    private Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet;

    public Procesocalidad() {
    }

    public Procesocalidad(Long idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    public Long getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(Long idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getNroproceso() {
        return nroproceso;
    }

    public void setNroproceso(BigInteger nroproceso) {
        this.nroproceso = nroproceso;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public String getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(String tolerancia) {
        this.tolerancia = tolerancia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getDuracionestimada() {
        return duracionestimada;
    }

    public void setDuracionestimada(Date duracionestimada) {
        this.duracionestimada = duracionestimada;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public Accioncalidad getAccioncalidad() {
        return accioncalidad;
    }

    public void setAccioncalidad(Accioncalidad accioncalidad) {
        this.accioncalidad = accioncalidad;
    }

    public Set<Detalleplanprocesoscalidad> getDetalleplanprocesoscalidadSet() {
        return detalleplanprocesoscalidadSet;
    }

    public void setDetalleplanprocesoscalidadSet(Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet) {
        this.detalleplanprocesoscalidadSet = detalleplanprocesoscalidadSet;
    }

    public Set<Maquinaxprocesocalidad> getMaquinaxprocesocalidadSet() {
        return maquinaxprocesocalidadSet;
    }

    public void setMaquinaxprocesocalidadSet(Set<Maquinaxprocesocalidad> maquinaxprocesocalidadSet) {
        this.maquinaxprocesocalidadSet = maquinaxprocesocalidadSet;
    }

    public Set<Detallepiezacalidadpresupuesto> getDetallepiezacalidadpresupuestoSet() {
        return detallepiezacalidadpresupuestoSet;
    }

    public void setDetallepiezacalidadpresupuestoSet(Set<Detallepiezacalidadpresupuesto> detallepiezacalidadpresupuestoSet) {
        this.detallepiezacalidadpresupuestoSet = detallepiezacalidadpresupuestoSet;
    }

    public Ejecucionprocesocalidad getEjecucionprocesocalidad() {
        return ejecucionprocesocalidad;
    }

    public void setEjecucionprocesocalidad(Ejecucionprocesocalidad ejecucionprocesocalidad) {
        this.ejecucionprocesocalidad = ejecucionprocesocalidad;
    }

    public Set<Detalleplanificacioncalidad> getDetalleplanificacioncalidadSet() {
        return detalleplanificacioncalidadSet;
    }

    public void setDetalleplanificacioncalidadSet(Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet) {
        this.detalleplanificacioncalidadSet = detalleplanificacioncalidadSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocesocalidad != null ? idprocesocalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesocalidad)) {
            return false;
        }
        Procesocalidad other = (Procesocalidad) object;
        if ((this.idprocesocalidad == null && other.idprocesocalidad != null) || (this.idprocesocalidad != null && !this.idprocesocalidad.equals(other.idprocesocalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Procesocalidad[idprocesocalidad=" + idprocesocalidad + "]";
    }

}
