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
public class DetalleremitoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private long iddetalle;
    @Basic(optional = false)
    @Column(name = "idremito")
    private long idremito;

    public DetalleremitoPK() {
    }

    public DetalleremitoPK(long iddetalle, long idremito) {
        this.iddetalle = iddetalle;
        this.idremito = idremito;
    }

    public long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public long getIdremito() {
        return idremito;
    }

    public void setIdremito(long idremito) {
        this.idremito = idremito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetalle;
        hash += (int) idremito;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleremitoPK)) {
            return false;
        }
        DetalleremitoPK other = (DetalleremitoPK) object;
        if (this.iddetalle != other.iddetalle) {
            return false;
        }
        if (this.idremito != other.idremito) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.DetalleremitoPK[ iddetalle=" + iddetalle + ", idremito=" + idremito + " ]";
    }
    
}
