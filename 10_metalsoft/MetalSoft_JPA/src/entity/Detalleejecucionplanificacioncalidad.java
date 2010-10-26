/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detalleejecucionplanificacioncalidad")
@NamedQueries({
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findAll", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByIddetalle", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.detalleejecucionplanificacioncalidadPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByIdejecucionplanificacioncalidad", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.detalleejecucionplanificacioncalidadPK.idejecucionplanificacioncalidad = :idejecucionplanificacioncalidad"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByIdplanificacioncalidad", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.detalleejecucionplanificacioncalidadPK.idplanificacioncalidad = :idplanificacioncalidad"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByPieza", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.pieza = :pieza"),
    @NamedQuery(name = "Detalleejecucionplanificacioncalidad.findByPiezareal", query = "SELECT d FROM Detalleejecucionplanificacioncalidad d WHERE d.piezareal = :piezareal")})
public class Detalleejecucionplanificacioncalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadPK;
    @Column(name = "pieza")
    private BigInteger pieza;
    @Column(name = "piezareal")
    private BigInteger piezareal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleejecucionplanificacioncalidad")
    private Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet;
    @JoinColumns({
        @JoinColumn(name = "idejecucionplanificacioncalidad", referencedColumnName = "idejecucion", insertable = false, updatable = false),
        @JoinColumn(name = "idplanificacioncalidad", referencedColumnName = "idplanificacioncalidad", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Ejecucionplanificacioncalidad ejecucionplanificacioncalidad;
    @JoinColumns({
        @JoinColumn(name = "ejecucionprocesocalidad", referencedColumnName = "idejecucion"),
        @JoinColumn(name = "idprocesocalidad", referencedColumnName = "idprocesocalidad")})
    @ManyToOne
    private Ejecucionprocesocalidad ejecucionprocesocalidad;

    public Detalleejecucionplanificacioncalidad() {
    }

    public Detalleejecucionplanificacioncalidad(DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadPK) {
        this.detalleejecucionplanificacioncalidadPK = detalleejecucionplanificacioncalidadPK;
    }

    public Detalleejecucionplanificacioncalidad(long iddetalle, long idejecucionplanificacioncalidad, long idplanificacioncalidad) {
        this.detalleejecucionplanificacioncalidadPK = new DetalleejecucionplanificacioncalidadPK(iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad);
    }

    public DetalleejecucionplanificacioncalidadPK getDetalleejecucionplanificacioncalidadPK() {
        return detalleejecucionplanificacioncalidadPK;
    }

    public void setDetalleejecucionplanificacioncalidadPK(DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadPK) {
        this.detalleejecucionplanificacioncalidadPK = detalleejecucionplanificacioncalidadPK;
    }

    public BigInteger getPieza() {
        return pieza;
    }

    public void setPieza(BigInteger pieza) {
        this.pieza = pieza;
    }

    public BigInteger getPiezareal() {
        return piezareal;
    }

    public void setPiezareal(BigInteger piezareal) {
        this.piezareal = piezareal;
    }

    public Set<Detalleplanificacioncalidad> getDetalleplanificacioncalidadSet() {
        return detalleplanificacioncalidadSet;
    }

    public void setDetalleplanificacioncalidadSet(Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet) {
        this.detalleplanificacioncalidadSet = detalleplanificacioncalidadSet;
    }

    public Ejecucionplanificacioncalidad getEjecucionplanificacioncalidad() {
        return ejecucionplanificacioncalidad;
    }

    public void setEjecucionplanificacioncalidad(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) {
        this.ejecucionplanificacioncalidad = ejecucionplanificacioncalidad;
    }

    public Ejecucionprocesocalidad getEjecucionprocesocalidad() {
        return ejecucionprocesocalidad;
    }

    public void setEjecucionprocesocalidad(Ejecucionprocesocalidad ejecucionprocesocalidad) {
        this.ejecucionprocesocalidad = ejecucionprocesocalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleejecucionplanificacioncalidadPK != null ? detalleejecucionplanificacioncalidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleejecucionplanificacioncalidad)) {
            return false;
        }
        Detalleejecucionplanificacioncalidad other = (Detalleejecucionplanificacioncalidad) object;
        if ((this.detalleejecucionplanificacioncalidadPK == null && other.detalleejecucionplanificacioncalidadPK != null) || (this.detalleejecucionplanificacioncalidadPK != null && !this.detalleejecucionplanificacioncalidadPK.equals(other.detalleejecucionplanificacioncalidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Detalleejecucionplanificacioncalidad[detalleejecucionplanificacioncalidadPK=" + detalleejecucionplanificacioncalidadPK + "]";
    }

}
