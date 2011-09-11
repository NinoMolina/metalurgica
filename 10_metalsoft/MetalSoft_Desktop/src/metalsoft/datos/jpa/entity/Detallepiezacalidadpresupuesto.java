/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallepiezacalidadpresupuesto")
@NamedQueries({
    @NamedQuery(name = "Detallepiezacalidadpresupuesto.findAll", query = "SELECT d FROM Detallepiezacalidadpresupuesto d"),
    @NamedQuery(name = "Detallepiezacalidadpresupuesto.findByIddetalle", query = "SELECT d FROM Detallepiezacalidadpresupuesto d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallepiezacalidadpresupuesto.findByCantprocesocalidad", query = "SELECT d FROM Detallepiezacalidadpresupuesto d WHERE d.cantprocesocalidad = :cantprocesocalidad"),
    @NamedQuery(name = "Detallepiezacalidadpresupuesto.findByDuracionxpieza", query = "SELECT d FROM Detallepiezacalidadpresupuesto d WHERE d.duracionxpieza = :duracionxpieza")})
public class Detallepiezacalidadpresupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detallepiezacalidadpresupuesto_seq")
    @SequenceGenerator(name = "detallepiezacalidadpresupuesto_seq", sequenceName = "detallepiezacalidadpresupuesto_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "cantprocesocalidad")
    private Integer cantprocesocalidad;
    @Column(name = "duracionxpieza")
    @Temporal(TemporalType.TIME)
    private Date duracionxpieza;
    @JoinColumn(name = "idprocesocalidad", referencedColumnName = "idprocesocalidad")
    @ManyToOne
    private Procesocalidad idprocesocalidad;
    @JoinColumn(name = "iddetalleproductopresupuesto", referencedColumnName = "iddetalle")
    @ManyToOne
    private Detalleproductopresupuesto iddetalleproductopresupuesto;

    public Detallepiezacalidadpresupuesto() {
    }

    public Detallepiezacalidadpresupuesto(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getCantprocesocalidad() {
        return cantprocesocalidad;
    }

    public void setCantprocesocalidad(Integer cantprocesocalidad) {
        this.cantprocesocalidad = cantprocesocalidad;
    }

    public Date getDuracionxpieza() {
        return duracionxpieza;
    }

    public void setDuracionxpieza(Date duracionxpieza) {
        this.duracionxpieza = duracionxpieza;
    }

    public Procesocalidad getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(Procesocalidad idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    public Detalleproductopresupuesto getIddetalleproductopresupuesto() {
        return iddetalleproductopresupuesto;
    }

    public void setIddetalleproductopresupuesto(Detalleproductopresupuesto iddetalleproductopresupuesto) {
        this.iddetalleproductopresupuesto = iddetalleproductopresupuesto;
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
        if (!(object instanceof Detallepiezacalidadpresupuesto)) {
            return false;
        }
        Detallepiezacalidadpresupuesto other = (Detallepiezacalidadpresupuesto) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto[ iddetalle=" + iddetalle + " ]";
    }
    
}
