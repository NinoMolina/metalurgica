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
@Table(name = "tipoiva")
@NamedQueries({
    @NamedQuery(name = "Tipoiva.findAll", query = "SELECT t FROM Tipoiva t"),
    @NamedQuery(name = "Tipoiva.findByIdtipoiva", query = "SELECT t FROM Tipoiva t WHERE t.idtipoiva = :idtipoiva"),
    @NamedQuery(name = "Tipoiva.findByNombre", query = "SELECT t FROM Tipoiva t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipoiva.findByDescripcion", query = "SELECT t FROM Tipoiva t WHERE t.descripcion = :descripcion")})
public class Tipoiva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoiva_seq")
    @SequenceGenerator(name = "tipoiva_seq", sequenceName = "tipoiva_idtipoiva_seq", allocationSize = 1)
    @Column(name = "idtipoiva")
    private Long idtipoiva;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tipoiva")
    private List<Factura> facturaList;

    public Tipoiva() {
    }

    public Tipoiva(Long idtipoiva) {
        this.idtipoiva = idtipoiva;
    }

    public Long getIdtipoiva() {
        return idtipoiva;
    }

    public void setIdtipoiva(Long idtipoiva) {
        this.idtipoiva = idtipoiva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoiva != null ? idtipoiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoiva)) {
            return false;
        }
        Tipoiva other = (Tipoiva) object;
        if ((this.idtipoiva == null && other.idtipoiva != null) || (this.idtipoiva != null && !this.idtipoiva.equals(other.idtipoiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Tipoiva[ idtipoiva=" + idtipoiva + " ]";
    }
    
}
