/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "plano")
@NamedQueries({
    @NamedQuery(name = "Plano.findAll", query = "SELECT p FROM Plano p"),
    @NamedQuery(name = "Plano.findByIdplano", query = "SELECT p FROM Plano p WHERE p.idplano = :idplano"),
    @NamedQuery(name = "Plano.findByNroplano", query = "SELECT p FROM Plano p WHERE p.nroplano = :nroplano"),
    @NamedQuery(name = "Plano.findByEscala", query = "SELECT p FROM Plano p WHERE p.escala = :escala"),
    @NamedQuery(name = "Plano.findByImagen", query = "SELECT p FROM Plano p WHERE p.imagen = :imagen")})
public class Plano implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idplano")
    private Long idplano;
    @Column(name = "nroplano")
    private BigInteger nroplano;
    @Column(name = "escala")
    private Integer escala;
    @JoinColumn(name = "imagen")
    private Serializable imagen;
    @JoinColumn(name = "pedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido;

    public Plano() {
    }

    public Plano(Long idplano) {
        this.idplano = idplano;
    }

    public Long getIdplano() {
        return idplano;
    }

    public void setIdplano(Long idplano) {
        this.idplano = idplano;
    }

    public BigInteger getNroplano() {
        return nroplano;
    }

    public void setNroplano(BigInteger nroplano) {
        this.nroplano = nroplano;
    }

    public Integer getEscala() {
        return escala;
    }

    public void setEscala(Integer escala) {
        this.escala = escala;
    }

    public Serializable getImagen() {
        return imagen;
    }

    public void setImagen(Serializable imagen) {
        this.imagen = imagen;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplano != null ? idplano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plano)) {
            return false;
        }
        Plano other = (Plano) object;
        if ((this.idplano == null && other.idplano != null) || (this.idplano != null && !this.idplano.equals(other.idplano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Plano[ idplano=" + idplano + " ]";
    }
    
}
