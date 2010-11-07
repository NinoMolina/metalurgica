/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "pedidomatriz")
@NamedQueries({
    @NamedQuery(name = "Pedidomatriz.findAll", query = "SELECT p FROM Pedidomatriz p"),
    @NamedQuery(name = "Pedidomatriz.findByIdpedidomatriz", query = "SELECT p FROM Pedidomatriz p WHERE p.idpedidomatriz = :idpedidomatriz"),
    @NamedQuery(name = "Pedidomatriz.findByNropedidomatriz", query = "SELECT p FROM Pedidomatriz p WHERE p.nropedidomatriz = :nropedidomatriz"),
    @NamedQuery(name = "Pedidomatriz.findByFechapedidomatriz", query = "SELECT p FROM Pedidomatriz p WHERE p.fechapedidomatriz = :fechapedidomatriz"),
    @NamedQuery(name = "Pedidomatriz.findByObservaciones", query = "SELECT p FROM Pedidomatriz p WHERE p.observaciones = :observaciones")})
public class Pedidomatriz implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpedidomatriz")
    private Long idpedidomatriz;
    @Column(name = "nropedidomatriz")
    private BigInteger nropedidomatriz;
    @Column(name = "fechapedidomatriz")
    @Temporal(TemporalType.DATE)
    private Date fechapedidomatriz;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "idmatriz", referencedColumnName = "idmatriz")
    @ManyToOne
    private Matriz idmatriz;
    @JoinColumn(name = "idmatriz", referencedColumnName = "idmatriz")
    @ManyToOne
    private Matriz idmatriz1;

    public Pedidomatriz() {
    }

    public Pedidomatriz(Long idpedidomatriz) {
        this.idpedidomatriz = idpedidomatriz;
    }

    public Long getIdpedidomatriz() {
        return idpedidomatriz;
    }

    public void setIdpedidomatriz(Long idpedidomatriz) {
        this.idpedidomatriz = idpedidomatriz;
    }

    public BigInteger getNropedidomatriz() {
        return nropedidomatriz;
    }

    public void setNropedidomatriz(BigInteger nropedidomatriz) {
        this.nropedidomatriz = nropedidomatriz;
    }

    public Date getFechapedidomatriz() {
        return fechapedidomatriz;
    }

    public void setFechapedidomatriz(Date fechapedidomatriz) {
        this.fechapedidomatriz = fechapedidomatriz;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Matriz getIdmatriz() {
        return idmatriz;
    }

    public void setIdmatriz(Matriz idmatriz) {
        this.idmatriz = idmatriz;
    }

    public Matriz getIdmatriz1() {
        return idmatriz1;
    }

    public void setIdmatriz1(Matriz idmatriz1) {
        this.idmatriz1 = idmatriz1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedidomatriz != null ? idpedidomatriz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidomatriz)) {
            return false;
        }
        Pedidomatriz other = (Pedidomatriz) object;
        if ((this.idpedidomatriz == null && other.idpedidomatriz != null) || (this.idpedidomatriz != null && !this.idpedidomatriz.equals(other.idpedidomatriz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pedidomatriz[idpedidomatriz=" + idpedidomatriz + "]";
    }

}