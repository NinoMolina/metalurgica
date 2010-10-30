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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @Column(name = "idtipoiva")
    private Long idtipoiva;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tipoiva")
    private Set<Factura> facturaSet;
    @OneToMany(mappedBy = "tipoiva1")
    private Set<Factura> facturaSet1;

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

    public Set<Factura> getFacturaSet() {
        return facturaSet;
    }

    public void setFacturaSet(Set<Factura> facturaSet) {
        this.facturaSet = facturaSet;
    }

    public Set<Factura> getFacturaSet1() {
        return facturaSet1;
    }

    public void setFacturaSet1(Set<Factura> facturaSet1) {
        this.facturaSet1 = facturaSet1;
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
        return "entity.Tipoiva[idtipoiva=" + idtipoiva + "]";
    }

}
