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
@Table(name = "detalletrabajotercerizado")
@NamedQueries({
    @NamedQuery(name = "Detalletrabajotercerizado.findAll", query = "SELECT d FROM Detalletrabajotercerizado d"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByIddetalle", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByMontoparcial", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.montoparcial = :montoparcial"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByCantidad", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByDescripcion", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByFechaenvioreal", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.fechaenvioreal = :fechaenvioreal"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByFechaentregareal", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.fechaentregareal = :fechaentregareal"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByPieza", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.pieza = :pieza")})
public class Detalletrabajotercerizado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalletrabajotercerizado_seq")
    @SequenceGenerator(name = "detalletrabajotercerizado_seq", sequenceName = "detalletrabajotercerizado_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montoparcial")
    private Double montoparcial;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechaenvioreal")
    @Temporal(TemporalType.DATE)
    private Date fechaenvioreal;
    @Column(name = "fechaentregareal")
    @Temporal(TemporalType.DATE)
    private Date fechaentregareal;
    @Column(name = "pieza")
    private BigInteger pieza;
    @OneToMany(mappedBy = "iddetalletrabajo")
    private List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoList;
    @JoinColumn(name = "idtrabajotercerizado", referencedColumnName = "idtrabajo")
    @ManyToOne(optional = false)
    private Trabajotercerizado idtrabajotercerizado;
    @JoinColumn(name = "proceso", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion proceso;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadodetalletrabajotercerizado estado;

    public Detalletrabajotercerizado() {
    }

    public Detalletrabajotercerizado(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Double getMontoparcial() {
        return montoparcial;
    }

    public void setMontoparcial(Double montoparcial) {
        this.montoparcial = montoparcial;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaenvioreal() {
        return fechaenvioreal;
    }

    public void setFechaenvioreal(Date fechaenvioreal) {
        this.fechaenvioreal = fechaenvioreal;
    }

    public Date getFechaentregareal() {
        return fechaentregareal;
    }

    public void setFechaentregareal(Date fechaentregareal) {
        this.fechaentregareal = fechaentregareal;
    }

    public BigInteger getPieza() {
        return pieza;
    }

    public void setPieza(BigInteger pieza) {
        this.pieza = pieza;
    }

    public List<Detallereclamoempresamantenimiento> getDetallereclamoempresamantenimientoList() {
        return detallereclamoempresamantenimientoList;
    }

    public void setDetallereclamoempresamantenimientoList(List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoList) {
        this.detallereclamoempresamantenimientoList = detallereclamoempresamantenimientoList;
    }

    public Trabajotercerizado getIdtrabajotercerizado() {
        return idtrabajotercerizado;
    }

    public void setIdtrabajotercerizado(Trabajotercerizado idtrabajotercerizado) {
        this.idtrabajotercerizado = idtrabajotercerizado;
    }

    public Etapadeproduccion getProceso() {
        return proceso;
    }

    public void setProceso(Etapadeproduccion proceso) {
        this.proceso = proceso;
    }

    public Estadodetalletrabajotercerizado getEstado() {
        return estado;
    }

    public void setEstado(Estadodetalletrabajotercerizado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalletrabajotercerizado)) {
            return false;
        }
        Detalletrabajotercerizado other = (Detalletrabajotercerizado) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalletrabajotercerizado[ iddetalle=" + iddetalle + " ]";
    }
    
}
