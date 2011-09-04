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
public class MaquinaxprocesocalidadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idprocesocalidad")
    private long idprocesocalidad;
    @Basic(optional = false)
    @Column(name = "idmaquina")
    private long idmaquina;

    public MaquinaxprocesocalidadPK() {
    }

    public MaquinaxprocesocalidadPK(long idprocesocalidad, long idmaquina) {
        this.idprocesocalidad = idprocesocalidad;
        this.idmaquina = idmaquina;
    }

    public long getIdprocesocalidad() {
        return idprocesocalidad;
    }

    public void setIdprocesocalidad(long idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }

    public long getIdmaquina() {
        return idmaquina;
    }

    public void setIdmaquina(long idmaquina) {
        this.idmaquina = idmaquina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idprocesocalidad;
        hash += (int) idmaquina;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaquinaxprocesocalidadPK)) {
            return false;
        }
        MaquinaxprocesocalidadPK other = (MaquinaxprocesocalidadPK) object;
        if (this.idprocesocalidad != other.idprocesocalidad) {
            return false;
        }
        if (this.idmaquina != other.idmaquina) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.MaquinaxprocesocalidadPK[ idprocesocalidad=" + idprocesocalidad + ", idmaquina=" + idmaquina + " ]";
    }
    
}
