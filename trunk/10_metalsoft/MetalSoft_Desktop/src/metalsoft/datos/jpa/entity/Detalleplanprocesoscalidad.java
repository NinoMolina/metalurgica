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
@Table(name = "detalleplanprocesoscalidad")
@NamedQueries({
    @NamedQuery(name = "Detalleplanprocesoscalidad.findAll", query = "SELECT d FROM Detalleplanprocesoscalidad d"),
    @NamedQuery(name = "Detalleplanprocesoscalidad.findByIddetalle", query = "SELECT d FROM Detalleplanprocesoscalidad d WHERE d.detalleplanprocesoscalidadPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleplanprocesoscalidad.findByIdplanprocesoscalidad", query = "SELECT d FROM Detalleplanprocesoscalidad d WHERE d.detalleplanprocesoscalidadPK.idplanprocesoscalidad = :idplanprocesoscalidad"),
    @NamedQuery(name = "Detalleplanprocesoscalidad.findByIdpieza", query = "SELECT d FROM Detalleplanprocesoscalidad d WHERE d.idpieza = :idpieza"),
    @NamedQuery(name = "Detalleplanprocesoscalidad.findByDuracionestimada", query = "SELECT d FROM Detalleplanprocesoscalidad d WHERE d.duracionestimada = :duracionestimada")})
public class Detalleplanprocesoscalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleplanprocesoscalidadPK detalleplanprocesoscalidadPK;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @Column(name = "duracionestimada")
    @Temporal(TemporalType.TIME)
    private Date duracionestimada;
    @JoinColumn(name = "idprocesocalidad", referencedColumnName = "idprocesocalidad")
    @ManyToOne
    private Procesocalidad idprocesocalidad;
    @JoinColumn(name = "idplanprocesoscalidad", referencedColumnName = "idplanprocesoscalidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planprocesoscalidad planprocesoscalidad;

    public Detalleplanprocesoscalidad() {
    }

    public Detalleplanprocesoscalidad(DetalleplanprocesoscalidadPK detalleplanprocesoscalidadPK) {
        this.detalleplanprocesoscalidadPK = detalleplanprocesoscalidadPK;
    }

    public Detalleplanprocesoscalidad(long iddetalle, long idplanprocesoscalidad) {
        this.detalleplanprocesoscalidadPK = new DetalleplanprocesoscalidadPK(iddetalle, idplanprocesoscalidad);
    }

    public DetalleplanprocesoscalidadPK getDetalleplanprocesoscalidadPK() {
        return detalleplanprocesoscalidadPK;
    }

    public void setDetalleplanprocesoscalidadPK(DetalleplanprocesoscalidadPK detalleplanprocesoscalidadPK) {
        this.detalleplanprocesoscalidadPK = detalleplanprocesoscalidadPK;
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

    public Procesocalidad getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(Procesocalidad idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    public Planprocesoscalidad getPlanprocesoscalidad() {
        return planprocesoscalidad;
    }

    public void setPlanprocesoscalidad(Planprocesoscalidad planprocesoscalidad) {
        this.planprocesoscalidad = planprocesoscalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleplanprocesoscalidadPK != null ? detalleplanprocesoscalidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleplanprocesoscalidad)) {
            return false;
        }
        Detalleplanprocesoscalidad other = (Detalleplanprocesoscalidad) object;
        if ((this.detalleplanprocesoscalidadPK == null && other.detalleplanprocesoscalidadPK != null) || (this.detalleplanprocesoscalidadPK != null && !this.detalleplanprocesoscalidadPK.equals(other.detalleplanprocesoscalidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalleplanprocesoscalidad[ detalleplanprocesoscalidadPK=" + detalleplanprocesoscalidadPK + " ]";
    }
    
}
