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
@Table(name = "maquinaxejecucionetapaproduccion")
@NamedQueries({
    @NamedQuery(name = "Maquinaxejecucionetapaproduccion.findAll", query = "SELECT m FROM Maquinaxejecucionetapaproduccion m"),
    @NamedQuery(name = "Maquinaxejecucionetapaproduccion.findByIdejecucionetapaproduccion", query = "SELECT m FROM Maquinaxejecucionetapaproduccion m WHERE m.maquinaxejecucionetapaproduccionPK.idejecucionetapaproduccion = :idejecucionetapaproduccion"),
    @NamedQuery(name = "Maquinaxejecucionetapaproduccion.findByIdetapaproduccion", query = "SELECT m FROM Maquinaxejecucionetapaproduccion m WHERE m.maquinaxejecucionetapaproduccionPK.idetapaproduccion = :idetapaproduccion"),
    @NamedQuery(name = "Maquinaxejecucionetapaproduccion.findByIdmaquina", query = "SELECT m FROM Maquinaxejecucionetapaproduccion m WHERE m.maquinaxejecucionetapaproduccionPK.idmaquina = :idmaquina"),
    @NamedQuery(name = "Maquinaxejecucionetapaproduccion.findByHorasmaquina", query = "SELECT m FROM Maquinaxejecucionetapaproduccion m WHERE m.horasmaquina = :horasmaquina"),
    @NamedQuery(name = "Maquinaxejecucionetapaproduccion.findByHorashombre", query = "SELECT m FROM Maquinaxejecucionetapaproduccion m WHERE m.horashombre = :horashombre")})
public class Maquinaxejecucionetapaproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaquinaxejecucionetapaproduccionPK maquinaxejecucionetapaproduccionPK;
    @Column(name = "horasmaquina")
    @Temporal(TemporalType.TIME)
    private Date horasmaquina;
    @Column(name = "horashombre")
    @Temporal(TemporalType.TIME)
    private Date horashombre;

    public Maquinaxejecucionetapaproduccion() {
    }

    public Maquinaxejecucionetapaproduccion(MaquinaxejecucionetapaproduccionPK maquinaxejecucionetapaproduccionPK) {
        this.maquinaxejecucionetapaproduccionPK = maquinaxejecucionetapaproduccionPK;
    }

    public Maquinaxejecucionetapaproduccion(long idejecucionetapaproduccion, long idetapaproduccion, long idmaquina) {
        this.maquinaxejecucionetapaproduccionPK = new MaquinaxejecucionetapaproduccionPK(idejecucionetapaproduccion, idetapaproduccion, idmaquina);
    }

    public MaquinaxejecucionetapaproduccionPK getMaquinaxejecucionetapaproduccionPK() {
        return maquinaxejecucionetapaproduccionPK;
    }

    public void setMaquinaxejecucionetapaproduccionPK(MaquinaxejecucionetapaproduccionPK maquinaxejecucionetapaproduccionPK) {
        this.maquinaxejecucionetapaproduccionPK = maquinaxejecucionetapaproduccionPK;
    }

    public Date getHorasmaquina() {
        return horasmaquina;
    }

    public void setHorasmaquina(Date horasmaquina) {
        this.horasmaquina = horasmaquina;
    }

    public Date getHorashombre() {
        return horashombre;
    }

    public void setHorashombre(Date horashombre) {
        this.horashombre = horashombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maquinaxejecucionetapaproduccionPK != null ? maquinaxejecucionetapaproduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquinaxejecucionetapaproduccion)) {
            return false;
        }
        Maquinaxejecucionetapaproduccion other = (Maquinaxejecucionetapaproduccion) object;
        if ((this.maquinaxejecucionetapaproduccionPK == null && other.maquinaxejecucionetapaproduccionPK != null) || (this.maquinaxejecucionetapaproduccionPK != null && !this.maquinaxejecucionetapaproduccionPK.equals(other.maquinaxejecucionetapaproduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Maquinaxejecucionetapaproduccion[ maquinaxejecucionetapaproduccionPK=" + maquinaxejecucionetapaproduccionPK + " ]";
    }
    
}
