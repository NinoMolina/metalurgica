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
public class EjecucionprocesocalidadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idejecucion")
    private long idejecucion;
    @Basic(optional = false)
    @Column(name = "idprocesocalidad")
    private long idprocesocalidad;

    public EjecucionprocesocalidadPK() {
    }

    public EjecucionprocesocalidadPK(long idejecucion, long idprocesocalidad) {
        this.idejecucion = idejecucion;
        this.idprocesocalidad = idprocesocalidad;
    }

    public long getIdejecucion() {
        return idejecucion;
    }

    public void setIdejecucion(long idejecucion) {
        this.idejecucion = idejecucion;
    }

    public long getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(long idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idejecucion;
        hash += (int) idprocesocalidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjecucionprocesocalidadPK)) {
            return false;
        }
        EjecucionprocesocalidadPK other = (EjecucionprocesocalidadPK) object;
        if (this.idejecucion != other.idejecucion) {
            return false;
        }
        if (this.idprocesocalidad != other.idprocesocalidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.EjecucionprocesocalidadPK[idejecucion=" + idejecucion + ", idprocesocalidad=" + idprocesocalidad + "]";
    }

}
