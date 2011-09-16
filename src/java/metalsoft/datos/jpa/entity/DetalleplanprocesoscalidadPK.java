/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Nino
 */
@Embeddable
public class DetalleplanprocesoscalidadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idplanprocesoscalidad")
    private long idplanprocesoscalidad;

    public DetalleplanprocesoscalidadPK() {
    }

    public DetalleplanprocesoscalidadPK(long iddetalle, long idplanprocesoscalidad) {
        this.iddetalle = iddetalle;
        this.idplanprocesoscalidad = idplanprocesoscalidad;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdplanprocesoscalidad() {
        return idplanprocesoscalidad;
    }

    public void setIdplanprocesoscalidad(long idplanprocesoscalidad) {
        this.idplanprocesoscalidad = idplanprocesoscalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idplanprocesoscalidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleplanprocesoscalidadPK)) {
            return false;
        }
        DetalleplanprocesoscalidadPK other = (DetalleplanprocesoscalidadPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idplanprocesoscalidad != other.idplanprocesoscalidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetalleplanprocesoscalidadPK[ iddetalle=" + iddetalle + ", idplanprocesoscalidad=" + idplanprocesoscalidad + " ]";
    }
    
}
