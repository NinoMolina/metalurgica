/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "mpasignadaxpiezareal")
@NamedQueries({
    @NamedQuery(name = "Mpasignadaxpiezareal.findAll", query = "SELECT m FROM Mpasignadaxpiezareal m"),
    @NamedQuery(name = "Mpasignadaxpiezareal.findByIdpiezareal", query = "SELECT m FROM Mpasignadaxpiezareal m WHERE m.idpiezareal = :idpiezareal"),
    @NamedQuery(name = "Mpasignadaxpiezareal.findById", query = "SELECT m FROM Mpasignadaxpiezareal m WHERE m.id = :id")})
public class Mpasignadaxpiezareal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "idpiezareal")
    private BigInteger idpiezareal;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "iddetallempasignada", referencedColumnName = "id")
    @ManyToOne
    private Detallempasignada iddetallempasignada;
    @JoinColumn(name = "iddetallempasignada", referencedColumnName = "id")
    @ManyToOne
    private Detallempasignada iddetallempasignada1;

    public Mpasignadaxpiezareal() {
    }

    public Mpasignadaxpiezareal(Long id) {
        this.id = id;
    }

    public BigInteger getIdpiezareal() {
        return idpiezareal;
    }

    public void setIdpiezareal(BigInteger idpiezareal) {
        this.idpiezareal = idpiezareal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Detallempasignada getIddetallempasignada() {
        return iddetallempasignada;
    }

    public void setIddetallempasignada(Detallempasignada iddetallempasignada) {
        this.iddetallempasignada = iddetallempasignada;
    }

    public Detallempasignada getIddetallempasignada1() {
        return iddetallempasignada1;
    }

    public void setIddetallempasignada1(Detallempasignada iddetallempasignada1) {
        this.iddetallempasignada1 = iddetallempasignada1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mpasignadaxpiezareal)) {
            return false;
        }
        Mpasignadaxpiezareal other = (Mpasignadaxpiezareal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Mpasignadaxpiezareal[id=" + id + "]";
    }

}
