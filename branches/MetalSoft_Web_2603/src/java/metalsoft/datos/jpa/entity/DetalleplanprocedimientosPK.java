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
public class DetalleplanprocedimientosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idplanpprocedimientos")
    private long idplanpprocedimientos;

    public DetalleplanprocedimientosPK() {
    }

    public DetalleplanprocedimientosPK(long iddetalle, long idplanpprocedimientos) {
        this.iddetalle = iddetalle;
        this.idplanpprocedimientos = idplanpprocedimientos;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdplanpprocedimientos() {
        return idplanpprocedimientos;
    }

    public void setIdplanpprocedimientos(long idplanpprocedimientos) {
        this.idplanpprocedimientos = idplanpprocedimientos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idplanpprocedimientos;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleplanprocedimientosPK)) {
            return false;
        }
        DetalleplanprocedimientosPK other = (DetalleplanprocedimientosPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idplanpprocedimientos != other.idplanpprocedimientos) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetalleplanprocedimientosPK[ iddetalle=" + iddetalle + ", idplanpprocedimientos=" + idplanpprocedimientos + " ]";
    }
    
}
