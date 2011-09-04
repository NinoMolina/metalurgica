/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallerequerimientosmateriaprima")
@NamedQueries({
    @NamedQuery(name = "Detallerequerimientosmateriaprima.findAll", query = "SELECT d FROM Detallerequerimientosmateriaprima d"),
    @NamedQuery(name = "Detallerequerimientosmateriaprima.findByIddetalle", query = "SELECT d FROM Detallerequerimientosmateriaprima d WHERE d.detallerequerimientosmateriaprimaPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallerequerimientosmateriaprima.findByIdplanrequerimientosmateriaprima", query = "SELECT d FROM Detallerequerimientosmateriaprima d WHERE d.detallerequerimientosmateriaprimaPK.idplanrequerimientosmateriaprima = :idplanrequerimientosmateriaprima"),
    @NamedQuery(name = "Detallerequerimientosmateriaprima.findByCantidadmateriaprima", query = "SELECT d FROM Detallerequerimientosmateriaprima d WHERE d.cantidadmateriaprima = :cantidadmateriaprima"),
    @NamedQuery(name = "Detallerequerimientosmateriaprima.findByIdpieza", query = "SELECT d FROM Detallerequerimientosmateriaprima d WHERE d.idpieza = :idpieza")})
public class Detallerequerimientosmateriaprima implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallerequerimientosmateriaprimaPK detallerequerimientosmateriaprimaPK;
    @Column(name = "cantidadmateriaprima")
    private Integer cantidadmateriaprima;
    @Column(name = "idpieza")
    private BigInteger idpieza;
    @JoinColumn(name = "idplanrequerimientosmateriaprima", referencedColumnName = "idplanrequerimientosmateriaprima", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planrequerimientosmateriaprima planrequerimientosmateriaprima;
    @JoinColumn(name = "idmateriaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima idmateriaprima;

    public Detallerequerimientosmateriaprima() {
    }

    public Detallerequerimientosmateriaprima(DetallerequerimientosmateriaprimaPK detallerequerimientosmateriaprimaPK) {
        this.detallerequerimientosmateriaprimaPK = detallerequerimientosmateriaprimaPK;
    }

    public Detallerequerimientosmateriaprima(long iddetalle, long idplanrequerimientosmateriaprima) {
        this.detallerequerimientosmateriaprimaPK = new DetallerequerimientosmateriaprimaPK(iddetalle, idplanrequerimientosmateriaprima);
    }

    public DetallerequerimientosmateriaprimaPK getDetallerequerimientosmateriaprimaPK() {
        return detallerequerimientosmateriaprimaPK;
    }

    public void setDetallerequerimientosmateriaprimaPK(DetallerequerimientosmateriaprimaPK detallerequerimientosmateriaprimaPK) {
        this.detallerequerimientosmateriaprimaPK = detallerequerimientosmateriaprimaPK;
    }

    public Integer getCantidadmateriaprima() {
        return cantidadmateriaprima;
    }

    public void setCantidadmateriaprima(Integer cantidadmateriaprima) {
        this.cantidadmateriaprima = cantidadmateriaprima;
    }

    public BigInteger getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(BigInteger idpieza) {
        this.idpieza = idpieza;
    }

    public Planrequerimientosmateriaprima getPlanrequerimientosmateriaprima() {
        return planrequerimientosmateriaprima;
    }

    public void setPlanrequerimientosmateriaprima(Planrequerimientosmateriaprima planrequerimientosmateriaprima) {
        this.planrequerimientosmateriaprima = planrequerimientosmateriaprima;
    }

    public Materiaprima getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(Materiaprima idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallerequerimientosmateriaprimaPK != null ? detallerequerimientosmateriaprimaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallerequerimientosmateriaprima)) {
            return false;
        }
        Detallerequerimientosmateriaprima other = (Detallerequerimientosmateriaprima) object;
        if ((this.detallerequerimientosmateriaprimaPK == null && other.detallerequerimientosmateriaprimaPK != null) || (this.detallerequerimientosmateriaprimaPK != null && !this.detallerequerimientosmateriaprimaPK.equals(other.detallerequerimientosmateriaprimaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallerequerimientosmateriaprima[ detallerequerimientosmateriaprimaPK=" + detallerequerimientosmateriaprimaPK + " ]";
    }
    
}
