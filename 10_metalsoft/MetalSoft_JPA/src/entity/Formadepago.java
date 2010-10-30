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
@Table(name = "formadepago")
@NamedQueries({
    @NamedQuery(name = "Formadepago.findAll", query = "SELECT f FROM Formadepago f"),
    @NamedQuery(name = "Formadepago.findByIdformapago", query = "SELECT f FROM Formadepago f WHERE f.idformapago = :idformapago"),
    @NamedQuery(name = "Formadepago.findByNombre", query = "SELECT f FROM Formadepago f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Formadepago.findByDescripcion", query = "SELECT f FROM Formadepago f WHERE f.descripcion = :descripcion")})
public class Formadepago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idformapago")
    private Long idformapago;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "formapago")
    private Set<Factura> facturaSet;
    @OneToMany(mappedBy = "formapago1")
    private Set<Factura> facturaSet1;
    @OneToMany(mappedBy = "formadepago")
    private Set<Comprobantepago> comprobantepagoSet;
    @OneToMany(mappedBy = "formadepago1")
    private Set<Comprobantepago> comprobantepagoSet1;

    public Formadepago() {
    }

    public Formadepago(Long idformapago) {
        this.idformapago = idformapago;
    }

    public Long getIdformapago() {
        return idformapago;
    }

    public void setIdformapago(Long idformapago) {
        this.idformapago = idformapago;
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

    public Set<Comprobantepago> getComprobantepagoSet() {
        return comprobantepagoSet;
    }

    public void setComprobantepagoSet(Set<Comprobantepago> comprobantepagoSet) {
        this.comprobantepagoSet = comprobantepagoSet;
    }

    public Set<Comprobantepago> getComprobantepagoSet1() {
        return comprobantepagoSet1;
    }

    public void setComprobantepagoSet1(Set<Comprobantepago> comprobantepagoSet1) {
        this.comprobantepagoSet1 = comprobantepagoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformapago != null ? idformapago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formadepago)) {
            return false;
        }
        Formadepago other = (Formadepago) object;
        if ((this.idformapago == null && other.idformapago != null) || (this.idformapago != null && !this.idformapago.equals(other.idformapago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Formadepago[idformapago=" + idformapago + "]";
    }

}
