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
public class EmpleadoxturnoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idempleado")
    private long idempleado;
    @Basic(optional = false)
    @Column(name = "idturno")
    private long idturno;

    public EmpleadoxturnoPK() {
    }

    public EmpleadoxturnoPK(long idempleado, long idturno) {
        this.idempleado = idempleado;
        this.idturno = idturno;
    }

    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
    }

    public long getIdturno() {
        return idturno;
    }

    public void setIdturno(long idturno) {
        this.idturno = idturno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idempleado;
        hash += (int) idturno;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadoxturnoPK)) {
            return false;
        }
        EmpleadoxturnoPK other = (EmpleadoxturnoPK) object;
        if (this.idempleado != other.idempleado) {
            return false;
        }
        if (this.idturno != other.idturno) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.EmpleadoxturnoPK[ idempleado=" + idempleado + ", idturno=" + idturno + " ]";
    }
    
}
