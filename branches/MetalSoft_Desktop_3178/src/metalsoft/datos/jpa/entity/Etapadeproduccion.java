/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "etapadeproduccion_seq")
    @SequenceGenerator(name = "etapadeproduccion_seq", sequenceName = "etapadeproduccion_idetapaproduccion_seq", allocationSize = 1)
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
    private List<Detalleplanificacionproduccion> detalleplanificacionproduccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idetapaproduccion")
    private List<Ejecucionetapaproduccion> ejecucionetapaproduccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etapadeproduccion")
    private List<Piezaxetapadeproduccion> piezaxetapadeproduccionList;
    @OneToMany(mappedBy = "idetapaproduccion")
    private List<Detalleejecucionplanificacion> detalleejecucionplanificacionList;
    @JoinColumn(name = "unidaddemedida", referencedColumnName = "idunidadmedida")
    @ManyToOne
    private Unidadmedida unidaddemedida;
    @JoinColumn(name = "tipomaquina", referencedColumnName = "idtipomaquina")
    @ManyToOne
    private Tipomaquina tipomaquina;
    @OneToMany(mappedBy = "idetapa")
    private List<Detallepiezapresupuesto> detallepiezapresupuestoList;
    @OneToMany(mappedBy = "idetapaproduccion")
    private List<Detalleplanprocedimientos> detalleplanprocedimientosList;
    @OneToMany(mappedBy = "proceso")
    private List<Detalletrabajotercerizado> detalletrabajotercerizadoList;

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

    public List<Detalleplanificacionproduccion> getDetalleplanificacionproduccionList() {
        return detalleplanificacionproduccionList;
    }

    public void setDetalleplanificacionproduccionList(List<Detalleplanificacionproduccion> detalleplanificacionproduccionList) {
        this.detalleplanificacionproduccionList = detalleplanificacionproduccionList;
    }

    public List<Ejecucionetapaproduccion> getEjecucionetapaproduccionList() {
        return ejecucionetapaproduccionList;
    }

    public void setEjecucionetapaproduccionList(List<Ejecucionetapaproduccion> ejecucionetapaproduccionList) {
        this.ejecucionetapaproduccionList = ejecucionetapaproduccionList;
    }

    public List<Piezaxetapadeproduccion> getPiezaxetapadeproduccionList() {
        return piezaxetapadeproduccionList;
    }

    public void setPiezaxetapadeproduccionList(List<Piezaxetapadeproduccion> piezaxetapadeproduccionList) {
        this.piezaxetapadeproduccionList = piezaxetapadeproduccionList;
    }

    public List<Detalleejecucionplanificacion> getDetalleejecucionplanificacionList() {
        return detalleejecucionplanificacionList;
    }

    public void setDetalleejecucionplanificacionList(List<Detalleejecucionplanificacion> detalleejecucionplanificacionList) {
        this.detalleejecucionplanificacionList = detalleejecucionplanificacionList;
    }

    public Unidadmedida getUnidaddemedida() {
        return unidaddemedida;
    }

    public void setUnidaddemedida(Unidadmedida unidaddemedida) {
        this.unidaddemedida = unidaddemedida;
    }

    public Tipomaquina getTipomaquina() {
        return tipomaquina;
    }

    public void setTipomaquina(Tipomaquina tipomaquina) {
        this.tipomaquina = tipomaquina;
    }

    public List<Detallepiezapresupuesto> getDetallepiezapresupuestoList() {
        return detallepiezapresupuestoList;
    }

    public void setDetallepiezapresupuestoList(List<Detallepiezapresupuesto> detallepiezapresupuestoList) {
        this.detallepiezapresupuestoList = detallepiezapresupuestoList;
    }

    public List<Detalleplanprocedimientos> getDetalleplanprocedimientosList() {
        return detalleplanprocedimientosList;
    }

    public void setDetalleplanprocedimientosList(List<Detalleplanprocedimientos> detalleplanprocedimientosList) {
        this.detalleplanprocedimientosList = detalleplanprocedimientosList;
    }

    public List<Detalletrabajotercerizado> getDetalletrabajotercerizadoList() {
        return detalletrabajotercerizadoList;
    }

    public void setDetalletrabajotercerizadoList(List<Detalletrabajotercerizado> detalletrabajotercerizadoList) {
        this.detalletrabajotercerizadoList = detalletrabajotercerizadoList;
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
        return "metalsoft.datos.jpa.entity.Etapadeproduccion[ idetapaproduccion=" + idetapaproduccion + " ]";
    }
}
