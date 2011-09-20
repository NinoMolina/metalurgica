/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
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
@Table(name = "maquinaxprocesocalidad")
@NamedQueries({
    @NamedQuery(name = "Maquinaxprocesocalidad.findAll", query = "SELECT m FROM Maquinaxprocesocalidad m"),
    @NamedQuery(name = "Maquinaxprocesocalidad.findByIdprocesocalidad", query = "SELECT m FROM Maquinaxprocesocalidad m WHERE m.maquinaxprocesocalidadPK.idprocesocalidad = :idprocesocalidad"),
    @NamedQuery(name = "Maquinaxprocesocalidad.findByIdmaquina", query = "SELECT m FROM Maquinaxprocesocalidad m WHERE m.maquinaxprocesocalidadPK.idmaquina = :idmaquina"),
    @NamedQuery(name = "Maquinaxprocesocalidad.findByDuracion", query = "SELECT m FROM Maquinaxprocesocalidad m WHERE m.duracion = :duracion"),
    @NamedQuery(name = "Maquinaxprocesocalidad.findByDescripcion", query = "SELECT m FROM Maquinaxprocesocalidad m WHERE m.descripcion = :descripcion")})
public class Maquinaxprocesocalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaquinaxprocesocalidadPK maquinaxprocesocalidadPK;
    @Column(name = "duracion")
    @Temporal(TemporalType.TIME)
    private Date duracion;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "idprocesocalidad", referencedColumnName = "idprocesocalidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Procesocalidad procesocalidad;

    public Maquinaxprocesocalidad() {
    }

    public Maquinaxprocesocalidad(MaquinaxprocesocalidadPK maquinaxprocesocalidadPK) {
        this.maquinaxprocesocalidadPK = maquinaxprocesocalidadPK;
    }

    public Maquinaxprocesocalidad(long idprocesocalidad, long idmaquina) {
        this.maquinaxprocesocalidadPK = new MaquinaxprocesocalidadPK(idprocesocalidad, idmaquina);
    }

    public MaquinaxprocesocalidadPK getMaquinaxprocesocalidadPK() {
        return maquinaxprocesocalidadPK;
    }

    public void setMaquinaxprocesocalidadPK(MaquinaxprocesocalidadPK maquinaxprocesocalidadPK) {
        this.maquinaxprocesocalidadPK = maquinaxprocesocalidadPK;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Procesocalidad getProcesocalidad() {
        return procesocalidad;
    }

    public void setProcesocalidad(Procesocalidad procesocalidad) {
        this.procesocalidad = procesocalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maquinaxprocesocalidadPK != null ? maquinaxprocesocalidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquinaxprocesocalidad)) {
            return false;
        }
        Maquinaxprocesocalidad other = (Maquinaxprocesocalidad) object;
        if ((this.maquinaxprocesocalidadPK == null && other.maquinaxprocesocalidadPK != null) || (this.maquinaxprocesocalidadPK != null && !this.maquinaxprocesocalidadPK.equals(other.maquinaxprocesocalidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Maquinaxprocesocalidad[ maquinaxprocesocalidadPK=" + maquinaxprocesocalidadPK + " ]";
    }
    
}
