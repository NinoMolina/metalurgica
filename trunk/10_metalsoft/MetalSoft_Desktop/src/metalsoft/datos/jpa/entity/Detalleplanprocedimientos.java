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
@Table(name = "detalleplanprocedimientos")
@NamedQueries({
    @NamedQuery(name = "Detalleplanprocedimientos.findAll", query = "SELECT d FROM Detalleplanprocedimientos d"),
    @NamedQuery(name = "Detalleplanprocedimientos.findByIddetalle", query = "SELECT d FROM Detalleplanprocedimientos d WHERE d.detalleplanprocedimientosPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleplanprocedimientos.findByIdplanpprocedimientos", query = "SELECT d FROM Detalleplanprocedimientos d WHERE d.detalleplanprocedimientosPK.idplanpprocedimientos = :idplanpprocedimientos"),
    @NamedQuery(name = "Detalleplanprocedimientos.findByIdpieza", query = "SELECT d FROM Detalleplanprocedimientos d WHERE d.idpieza = :idpieza"),
    @NamedQuery(name = "Detalleplanprocedimientos.findByDuracionestimada", query = "SELECT d FROM Detalleplanprocedimientos d WHERE d.duracionestimada = :duracionestimada")})
public class Detalleplanprocedimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleplanprocedimientosPK detalleplanprocedimientosPK;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @Column(name = "duracionestimada")
    @Temporal(TemporalType.TIME)
    private Date duracionestimada;
    @JoinColumn(name = "idplanpprocedimientos", referencedColumnName = "idplanprocedimientos", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planprocedimientos planprocedimientos;
    @JoinColumn(name = "idetapaproduccion", referencedColumnName = "idetapaproduccion")
    @ManyToOne
    private Etapadeproduccion idetapaproduccion;

    public Detalleplanprocedimientos() {
    }

    public Detalleplanprocedimientos(DetalleplanprocedimientosPK detalleplanprocedimientosPK) {
        this.detalleplanprocedimientosPK = detalleplanprocedimientosPK;
    }

    public Detalleplanprocedimientos(long iddetalle, long idplanpprocedimientos) {
        this.detalleplanprocedimientosPK = new DetalleplanprocedimientosPK(iddetalle, idplanpprocedimientos);
    }

    public DetalleplanprocedimientosPK getDetalleplanprocedimientosPK() {
        return detalleplanprocedimientosPK;
    }

    public void setDetalleplanprocedimientosPK(DetalleplanprocedimientosPK detalleplanprocedimientosPK) {
        this.detalleplanprocedimientosPK = detalleplanprocedimientosPK;
    }

    public BigInteger getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(BigInteger idpieza) {
        this.idpieza = idpieza;
    }

    public Date getDuracionestimada() {
        return duracionestimada;
    }

    public void setDuracionestimada(Date duracionestimada) {
        this.duracionestimada = duracionestimada;
    }

    public Planprocedimientos getPlanprocedimientos() {
        return planprocedimientos;
    }

    public void setPlanprocedimientos(Planprocedimientos planprocedimientos) {
        this.planprocedimientos = planprocedimientos;
    }

    public Etapadeproduccion getIdetapaproduccion() {
        return idetapaproduccion;
    }

    public void setIdetapaproduccion(Etapadeproduccion idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleplanprocedimientosPK != null ? detalleplanprocedimientosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleplanprocedimientos)) {
            return false;
        }
        Detalleplanprocedimientos other = (Detalleplanprocedimientos) object;
        if ((this.detalleplanprocedimientosPK == null && other.detalleplanprocedimientosPK != null) || (this.detalleplanprocedimientosPK != null && !this.detalleplanprocedimientosPK.equals(other.detalleplanprocedimientosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalleplanprocedimientos[ detalleplanprocedimientosPK=" + detalleplanprocedimientosPK + " ]";
    }
    
}
