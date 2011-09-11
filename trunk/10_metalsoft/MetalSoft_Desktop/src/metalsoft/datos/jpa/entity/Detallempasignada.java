/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallempasignada")
@NamedQueries({
    @NamedQuery(name = "Detallempasignada.findAll", query = "SELECT d FROM Detallempasignada d"),
    @NamedQuery(name = "Detallempasignada.findById", query = "SELECT d FROM Detallempasignada d WHERE d.id = :id"),
    @NamedQuery(name = "Detallempasignada.findByCantidadmp", query = "SELECT d FROM Detallempasignada d WHERE d.cantidadmp = :cantidadmp")})
public class Detallempasignada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detallempasignada_seq")
    @SequenceGenerator(name = "detallempasignada_seq", sequenceName = "detallempasignada_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "cantidadmp")
    private Integer cantidadmp;
    @JoinColumn(name = "idplanificacionproduccion", referencedColumnName = "idplanificacionproduccion")
    @ManyToOne
    private Planificacionproduccion idplanificacionproduccion;
    @JoinColumn(name = "idmateriaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima idmateriaprima;
    @OneToMany(mappedBy = "iddetallempasignada")
    private List<Mpasignadaxpiezareal> mpasignadaxpiezarealList;

    public Detallempasignada() {
    }

    public Detallempasignada(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadmp() {
        return cantidadmp;
    }

    public void setCantidadmp(Integer cantidadmp) {
        this.cantidadmp = cantidadmp;
    }

    public Planificacionproduccion getIdplanificacionproduccion() {
        return idplanificacionproduccion;
    }

    public void setIdplanificacionproduccion(Planificacionproduccion idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }

    public Materiaprima getIdmateriaprima() {
        return idmateriaprima;
    }

    public void setIdmateriaprima(Materiaprima idmateriaprima) {
        this.idmateriaprima = idmateriaprima;
    }

    public List<Mpasignadaxpiezareal> getMpasignadaxpiezarealList() {
        return mpasignadaxpiezarealList;
    }

    public void setMpasignadaxpiezarealList(List<Mpasignadaxpiezareal> mpasignadaxpiezarealList) {
        this.mpasignadaxpiezarealList = mpasignadaxpiezarealList;
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
        if (!(object instanceof Detallempasignada)) {
            return false;
        }
        Detallempasignada other = (Detallempasignada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallempasignada[ id=" + id + " ]";
    }
    
}
