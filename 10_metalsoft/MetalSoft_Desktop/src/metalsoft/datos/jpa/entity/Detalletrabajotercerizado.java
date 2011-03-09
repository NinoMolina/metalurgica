/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Detalletrabajotercerizado.findByIddetalle", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.detalletrabajotercerizadoPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByIdtrabajotercerizado", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.detalletrabajotercerizadoPK.idtrabajotercerizado = :idtrabajotercerizado"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByMontoparcial", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.montoparcial = :montoparcial"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByCantidad", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByDescripcion", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByFechaenvioreal", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.fechaenvioreal = :fechaenvioreal"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByFechaentregareal", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.fechaentregareal = :fechaentregareal"),
    @NamedQuery(name = "Detalletrabajotercerizado.findByPieza", query = "SELECT d FROM Detalletrabajotercerizado d WHERE d.pieza = :pieza")})
public class Detalletrabajotercerizado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalletrabajotercerizadoPK detalletrabajotercerizadoPK;
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
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadodetalletrabajotercerizado estado;

    @JoinColumn(name = "proceso", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion proceso;

    @JoinColumn(name = "idtrabajotercerizado", referencedColumnName = "idtrabajo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Trabajotercerizado trabajotercerizado;


    public Detalletrabajotercerizado() {
    }

    public Detalletrabajotercerizado(DetalletrabajotercerizadoPK detalletrabajotercerizadoPK) {
        this.detalletrabajotercerizadoPK = detalletrabajotercerizadoPK;
    }

    public Detalletrabajotercerizado(long iddetalle, long idtrabajotercerizado) {
        this.detalletrabajotercerizadoPK = new DetalletrabajotercerizadoPK(iddetalle, idtrabajotercerizado);
    }

    public DetalletrabajotercerizadoPK getDetalletrabajotercerizadoPK() {
        return detalletrabajotercerizadoPK;
    }

    public void setDetalletrabajotercerizadoPK(DetalletrabajotercerizadoPK detalletrabajotercerizadoPK) {
        this.detalletrabajotercerizadoPK = detalletrabajotercerizadoPK;
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

    public Estadodetalletrabajotercerizado getEstado() {
        return estado;
    }

    public void setEstado(Estadodetalletrabajotercerizado estado) {
        this.estado = estado;
    }



    public Etapadeproduccion getProceso() {
        return proceso;
    }

    public void setProceso(Etapadeproduccion proceso) {
        this.proceso = proceso;
    }



    public Trabajotercerizado getTrabajotercerizado() {
        return trabajotercerizado;
    }

    public void setTrabajotercerizado(Trabajotercerizado trabajotercerizado) {
        this.trabajotercerizado = trabajotercerizado;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalletrabajotercerizadoPK != null ? detalletrabajotercerizadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalletrabajotercerizado)) {
            return false;
        }
        Detalletrabajotercerizado other = (Detalletrabajotercerizado) object;
        if ((this.detalletrabajotercerizadoPK == null && other.detalletrabajotercerizadoPK != null) || (this.detalletrabajotercerizadoPK != null && !this.detalletrabajotercerizadoPK.equals(other.detalletrabajotercerizadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalletrabajotercerizado[detalletrabajotercerizadoPK=" + detalletrabajotercerizadoPK + "]";
    }

}
