/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "sesion")
@NamedQueries({
    @NamedQuery(name = "Sesion.findAll", query = "SELECT s FROM Sesion s"),
    @NamedQuery(name = "Sesion.findByIdsesion", query = "SELECT s FROM Sesion s WHERE s.idsesion = :idsesion"),
    @NamedQuery(name = "Sesion.findByFechainicio", query = "SELECT s FROM Sesion s WHERE s.fechainicio = :fechainicio"),
    @NamedQuery(name = "Sesion.findByFechafin", query = "SELECT s FROM Sesion s WHERE s.fechafin = :fechafin"),
    @NamedQuery(name = "Sesion.findByHorainicio", query = "SELECT s FROM Sesion s WHERE s.horainicio = :horainicio"),
    @NamedQuery(name = "Sesion.findByHorafin", query = "SELECT s FROM Sesion s WHERE s.horafin = :horafin")})
public class Sesion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idsesion")
    private Long idsesion;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;

    public Sesion() {
    }

    public Sesion(Long idsesion) {
        this.idsesion = idsesion;
    }

    public Long getIdsesion() {
        return idsesion;
    }

    public void setIdsesion(Long idsesion) {
        this.idsesion = idsesion;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsesion != null ? idsesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sesion)) {
            return false;
        }
        Sesion other = (Sesion) object;
        if ((this.idsesion == null && other.idsesion != null) || (this.idsesion != null && !this.idsesion.equals(other.idsesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Sesion[ idsesion=" + idsesion + " ]";
    }
    
}
