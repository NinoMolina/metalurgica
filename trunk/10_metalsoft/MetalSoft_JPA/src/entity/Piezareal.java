/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Set;
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
@Table(name = "piezareal")
@NamedQueries({
    @NamedQuery(name = "Piezareal.findAll", query = "SELECT p FROM Piezareal p"),
    @NamedQuery(name = "Piezareal.findByIdpiezareal", query = "SELECT p FROM Piezareal p WHERE p.idpiezareal = :idpiezareal"),
    @NamedQuery(name = "Piezareal.findByIdpieza", query = "SELECT p FROM Piezareal p WHERE p.idpieza = :idpieza"),
    @NamedQuery(name = "Piezareal.findByNropieza", query = "SELECT p FROM Piezareal p WHERE p.nropieza = :nropieza")})
public class Piezareal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpiezareal")
    private Long idpiezareal;
    @Basic(optional = false)
    @Column(name = "idpieza")
    private long idpieza;
    @Column(name = "nropieza")
    private Integer nropieza;
    @JoinColumn(name = "idcodigobarra", referencedColumnName = "idcodigo")
    @ManyToOne
    private Codigodebarra idcodigobarra;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadopiezareal estado;
    @OneToMany(mappedBy = "piezareal")
    private Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSet;

    public Piezareal() {
    }

    public Piezareal(Long idpiezareal) {
        this.idpiezareal = idpiezareal;
    }

    public Piezareal(Long idpiezareal, long idpieza) {
        this.idpiezareal = idpiezareal;
        this.idpieza = idpieza;
    }

    public Long getIdpiezareal() {
        return idpiezareal;
    }

    public void setIdpiezareal(Long idpiezareal) {
        this.idpiezareal = idpiezareal;
    }

    public long getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(long idpieza) {
        this.idpieza = idpieza;
    }

    public Integer getNropieza() {
        return nropieza;
    }

    public void setNropieza(Integer nropieza) {
        this.nropieza = nropieza;
    }

    public Codigodebarra getIdcodigobarra() {
        return idcodigobarra;
    }

    public void setIdcodigobarra(Codigodebarra idcodigobarra) {
        this.idcodigobarra = idcodigobarra;
    }

    public Estadopiezareal getEstado() {
        return estado;
    }

    public void setEstado(Estadopiezareal estado) {
        this.estado = estado;
    }

    public Set<Detalleejecucionplanificacion> getDetalleejecucionplanificacionSet() {
        return detalleejecucionplanificacionSet;
    }

    public void setDetalleejecucionplanificacionSet(Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSet) {
        this.detalleejecucionplanificacionSet = detalleejecucionplanificacionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpiezareal != null ? idpiezareal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piezareal)) {
            return false;
        }
        Piezareal other = (Piezareal) object;
        if ((this.idpiezareal == null && other.idpiezareal != null) || (this.idpiezareal != null && !this.idpiezareal.equals(other.idpiezareal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Piezareal[idpiezareal=" + idpiezareal + "]";
    }

}
