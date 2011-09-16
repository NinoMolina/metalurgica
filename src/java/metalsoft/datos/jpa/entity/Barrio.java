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
@Table(name = "barrio")
@NamedQueries({
    @NamedQuery(name = "Barrio.findAll", query = "SELECT b FROM Barrio b"),
    @NamedQuery(name = "Barrio.findByIdbarrio", query = "SELECT b FROM Barrio b WHERE b.idbarrio = :idbarrio"),
    @NamedQuery(name = "Barrio.findByNombre", query = "SELECT b FROM Barrio b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Barrio.findByCodpostal", query = "SELECT b FROM Barrio b WHERE b.codpostal = :codpostal")})
public class Barrio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "barrio_seq")
    @SequenceGenerator(name = "barrio_seq", sequenceName = "barrio_idbarrio_seq", allocationSize = 1)
    @Column(name = "idbarrio")
    private Long idbarrio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codpostal")
    private BigInteger codpostal;
    @JoinColumn(name = "localidad", referencedColumnName = "idlocalidad")
    @ManyToOne
    private Localidad localidad;
    @OneToMany(mappedBy = "barrio")
    private List<Domicilio> domicilioList;

    public Barrio() {
    }

    public Barrio(Long idbarrio) {
        this.idbarrio = idbarrio;
    }

    public Long getIdbarrio() {
        return idbarrio;
    }

    public void setIdbarrio(Long idbarrio) {
        this.idbarrio = idbarrio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(BigInteger codpostal) {
        this.codpostal = codpostal;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public List<Domicilio> getDomicilioList() {
        return domicilioList;
    }

    public void setDomicilioList(List<Domicilio> domicilioList) {
        this.domicilioList = domicilioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbarrio != null ? idbarrio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barrio)) {
            return false;
        }
        Barrio other = (Barrio) object;
        if ((this.idbarrio == null && other.idbarrio != null) || (this.idbarrio != null && !this.idbarrio.equals(other.idbarrio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Barrio[ idbarrio=" + idbarrio + " ]";
    }
    
}
