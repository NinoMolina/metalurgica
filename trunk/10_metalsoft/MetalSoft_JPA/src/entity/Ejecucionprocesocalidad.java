/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "ejecucionprocesocalidad")
@NamedQueries({
    @NamedQuery(name = "Ejecucionprocesocalidad.findAll", query = "SELECT e FROM Ejecucionprocesocalidad e"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByIdejecucion", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.ejecucionprocesocalidadPK.idejecucion = :idejecucion"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByIdprocesocalidad", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.ejecucionprocesocalidadPK.idprocesocalidad = :idprocesocalidad"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByDuracionreal", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.duracionreal = :duracionreal"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByResultado", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.resultado = :resultado"),
    @NamedQuery(name = "Ejecucionprocesocalidad.findByNroejecucion", query = "SELECT e FROM Ejecucionprocesocalidad e WHERE e.nroejecucion = :nroejecucion")})
public class Ejecucionprocesocalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EjecucionprocesocalidadPK ejecucionprocesocalidadPK;
    @Column(name = "duracionreal")
    @Temporal(TemporalType.TIME)
    private Date duracionreal;
    @Column(name = "resultado")
    private String resultado;
    @Column(name = "nroejecucion")
    private BigInteger nroejecucion;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoejecucionprocesocalidad estado;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadoejecucionprocesocalidad estado1;
    @JoinColumn(name = "idprocesocalidad", referencedColumnName = "idprocesocalidad", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Procesocalidad procesocalidad;
    @JoinColumn(name = "idprocesocalidad", referencedColumnName = "idprocesocalidad", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Procesocalidad procesocalidad1;
    @OneToMany(mappedBy = "ejecucionprocesocalidad")
    private Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet;
    @OneToMany(mappedBy = "ejecucionprocesocalidad1")
    private Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet1;

    public Ejecucionprocesocalidad() {
    }

    public Ejecucionprocesocalidad(EjecucionprocesocalidadPK ejecucionprocesocalidadPK) {
        this.ejecucionprocesocalidadPK = ejecucionprocesocalidadPK;
    }

    public Ejecucionprocesocalidad(long idejecucion, long idprocesocalidad) {
        this.ejecucionprocesocalidadPK = new EjecucionprocesocalidadPK(idejecucion, idprocesocalidad);
    }

    public EjecucionprocesocalidadPK getEjecucionprocesocalidadPK() {
        return ejecucionprocesocalidadPK;
    }

    public void setEjecucionprocesocalidadPK(EjecucionprocesocalidadPK ejecucionprocesocalidadPK) {
        this.ejecucionprocesocalidadPK = ejecucionprocesocalidadPK;
    }

    public Date getDuracionreal() {
        return duracionreal;
    }

    public void setDuracionreal(Date duracionreal) {
        this.duracionreal = duracionreal;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public BigInteger getNroejecucion() {
        return nroejecucion;
    }

    public void setNroejecucion(BigInteger nroejecucion) {
        this.nroejecucion = nroejecucion;
    }

    public Estadoejecucionprocesocalidad getEstado() {
        return estado;
    }

    public void setEstado(Estadoejecucionprocesocalidad estado) {
        this.estado = estado;
    }

    public Estadoejecucionprocesocalidad getEstado1() {
        return estado1;
    }

    public void setEstado1(Estadoejecucionprocesocalidad estado1) {
        this.estado1 = estado1;
    }

    public Procesocalidad getProcesocalidad() {
        return procesocalidad;
    }

    public void setProcesocalidad(Procesocalidad procesocalidad) {
        this.procesocalidad = procesocalidad;
    }

    public Procesocalidad getProcesocalidad1() {
        return procesocalidad1;
    }

    public void setProcesocalidad1(Procesocalidad procesocalidad1) {
        this.procesocalidad1 = procesocalidad1;
    }

    public Set<Detalleejecucionplanificacioncalidad> getDetalleejecucionplanificacioncalidadSet() {
        return detalleejecucionplanificacioncalidadSet;
    }

    public void setDetalleejecucionplanificacioncalidadSet(Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet) {
        this.detalleejecucionplanificacioncalidadSet = detalleejecucionplanificacioncalidadSet;
    }

    public Set<Detalleejecucionplanificacioncalidad> getDetalleejecucionplanificacioncalidadSet1() {
        return detalleejecucionplanificacioncalidadSet1;
    }

    public void setDetalleejecucionplanificacioncalidadSet1(Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSet1) {
        this.detalleejecucionplanificacioncalidadSet1 = detalleejecucionplanificacioncalidadSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejecucionprocesocalidadPK != null ? ejecucionprocesocalidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejecucionprocesocalidad)) {
            return false;
        }
        Ejecucionprocesocalidad other = (Ejecucionprocesocalidad) object;
        if ((this.ejecucionprocesocalidadPK == null && other.ejecucionprocesocalidadPK != null) || (this.ejecucionprocesocalidadPK != null && !this.ejecucionprocesocalidadPK.equals(other.ejecucionprocesocalidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ejecucionprocesocalidad[ejecucionprocesocalidadPK=" + ejecucionprocesocalidadPK + "]";
    }

}
