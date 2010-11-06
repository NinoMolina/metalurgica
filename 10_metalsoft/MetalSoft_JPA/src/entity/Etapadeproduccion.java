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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "etapadeproduccion")
@NamedQueries({
    @NamedQuery(name = "Etapadeproduccion.findAll", query = "SELECT e FROM Etapadeproduccion e"),
    @NamedQuery(name = "Etapadeproduccion.findByIdetapaproduccion", query = "SELECT e FROM Etapadeproduccion e WHERE e.idetapaproduccion = :idetapaproduccion"),
    @NamedQuery(name = "Etapadeproduccion.findByNroetapaproduccion", query = "SELECT e FROM Etapadeproduccion e WHERE e.nroetapaproduccion = :nroetapaproduccion"),
    @NamedQuery(name = "Etapadeproduccion.findByNombre", query = "SELECT e FROM Etapadeproduccion e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Etapadeproduccion.findByHorasmaquina", query = "SELECT e FROM Etapadeproduccion e WHERE e.horasmaquina = :horasmaquina"),
    @NamedQuery(name = "Etapadeproduccion.findByHorashombre", query = "SELECT e FROM Etapadeproduccion e WHERE e.horashombre = :horashombre"),
    @NamedQuery(name = "Etapadeproduccion.findByDuracionestimada", query = "SELECT e FROM Etapadeproduccion e WHERE e.duracionestimada = :duracionestimada"),
    @NamedQuery(name = "Etapadeproduccion.findByFechacreacion", query = "SELECT e FROM Etapadeproduccion e WHERE e.fechacreacion = :fechacreacion")})
public class Etapadeproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idetapaproduccion")
    private Long idetapaproduccion;
    @Column(name = "nroetapaproduccion")
    private BigInteger nroetapaproduccion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "horasmaquina")
    @Temporal(TemporalType.TIME)
    private Date horasmaquina;
    @Column(name = "horashombre")
    @Temporal(TemporalType.TIME)
    private Date horashombre;
    @Column(name = "duracionestimada")
    @Temporal(TemporalType.TIME)
    private Date duracionestimada;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @OneToMany(mappedBy = "idetapaproduccion")
    private Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idetapaproduccion")
    private Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etapadeproduccion")
    private Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSet;
    @JoinColumn(name = "unidaddemedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida unidaddemedida;
    @JoinColumn(name = "maquina", referencedColumnName = "idmaquina")
    @ManyToOne
    private Maquina maquina;
    @OneToMany(mappedBy = "idetapa")
    private Set<Detallepiezapresupuesto> detallepiezapresupuestoSet;
    @OneToMany(mappedBy = "idetapaproduccion")
    private Set<Detalleplanprocedimientos> detalleplanprocedimientosSet;
    @OneToMany(mappedBy = "proceso")
    private Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet;

    public Etapadeproduccion() {
    }

    public Etapadeproduccion(Long idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    public Long getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(Long idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    public BigInteger getNroetapaproduccion() {
        return nroetapaproduccion;
    }

    public void setNroetapaproduccion(BigInteger nroetapaproduccion) {
        this.nroetapaproduccion = nroetapaproduccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getHorasmaquina() {
        return horasmaquina;
    }

    public void setHorasmaquina(Date horasmaquina) {
        this.horasmaquina = horasmaquina;
    }

    public Date getHorashombre() {
        return horashombre;
    }

    public void setHorashombre(Date horashombre) {
        this.horashombre = horashombre;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
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

    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccionSet() {
        return detalleplanificacionproduccionSet;
    }

    public void setDetalleplanificacionproduccionSet(Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet) {
        this.detalleplanificacionproduccionSet = detalleplanificacionproduccionSet;
    }

    

    public Set<Ejecucionetapaproduccion> getEjecucionetapaproduccionSet() {
        return ejecucionetapaproduccionSet;
    }

    public void setEjecucionetapaproduccionSet(Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet) {
        this.ejecucionetapaproduccionSet = ejecucionetapaproduccionSet;
    }

   

    public Set<Piezaxetapadeproduccion> getPiezaxetapadeproduccionSet() {
        return piezaxetapadeproduccionSet;
    }

    public void setPiezaxetapadeproduccionSet(Set<Piezaxetapadeproduccion> piezaxetapadeproduccionSet) {
        this.piezaxetapadeproduccionSet = piezaxetapadeproduccionSet;
    }

    

    public Unidadmedida getUnidaddemedida() {
        return unidaddemedida;
    }

    public void setUnidaddemedida(Unidadmedida unidaddemedida) {
        this.unidaddemedida = unidaddemedida;
    }

   

    public Set<Detallepiezapresupuesto> getDetallepiezapresupuestoSet() {
        return detallepiezapresupuestoSet;
    }

    public void setDetallepiezapresupuestoSet(Set<Detallepiezapresupuesto> detallepiezapresupuestoSet) {
        this.detallepiezapresupuestoSet = detallepiezapresupuestoSet;
    }

   
    public Set<Detalleplanprocedimientos> getDetalleplanprocedimientosSet() {
        return detalleplanprocedimientosSet;
    }

    public void setDetalleplanprocedimientosSet(Set<Detalleplanprocedimientos> detalleplanprocedimientosSet) {
        this.detalleplanprocedimientosSet = detalleplanprocedimientosSet;
    }

   
    public Set<Detalletrabajotercerizado> getDetalletrabajotercerizadoSet() {
        return detalletrabajotercerizadoSet;
    }

    public void setDetalletrabajotercerizadoSet(Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet) {
        this.detalletrabajotercerizadoSet = detalletrabajotercerizadoSet;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetapaproduccion != null ? idetapaproduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etapadeproduccion)) {
            return false;
        }
        Etapadeproduccion other = (Etapadeproduccion) object;
        if ((this.idetapaproduccion == null && other.idetapaproduccion != null) || (this.idetapaproduccion != null && !this.idetapaproduccion.equals(other.idetapaproduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Etapadeproduccion[idetapaproduccion=" + idetapaproduccion + "]";
    }

}
