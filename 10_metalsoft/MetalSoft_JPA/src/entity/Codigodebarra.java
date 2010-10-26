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
@Table(name = "codigodebarra")
@NamedQueries({
    @NamedQuery(name = "Codigodebarra.findAll", query = "SELECT c FROM Codigodebarra c"),
    @NamedQuery(name = "Codigodebarra.findByIdcodigo", query = "SELECT c FROM Codigodebarra c WHERE c.idcodigo = :idcodigo"),
    @NamedQuery(name = "Codigodebarra.findByDescripcion", query = "SELECT c FROM Codigodebarra c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Codigodebarra.findByCodigo", query = "SELECT c FROM Codigodebarra c WHERE c.codigo = :codigo")})
public class Codigodebarra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcodigo")
    private Long idcodigo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "codigobarra")
    private Set<Productoreal> productorealSet;
    @OneToMany(mappedBy = "codbarra")
    private Set<Materiaprima> materiaprimaSet;
    @OneToMany(mappedBy = "idcodigobarra")
    private Set<Piezareal> piezarealSet;

    public Codigodebarra() {
    }

    public Codigodebarra(Long idcodigo) {
        this.idcodigo = idcodigo;
    }

    public Long getIdcodigo() {
        return idcodigo;
    }

    public void setIdcodigo(Long idcodigo) {
        this.idcodigo = idcodigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Productoreal> getProductorealSet() {
        return productorealSet;
    }

    public void setProductorealSet(Set<Productoreal> productorealSet) {
        this.productorealSet = productorealSet;
    }

    public Set<Materiaprima> getMateriaprimaSet() {
        return materiaprimaSet;
    }

    public void setMateriaprimaSet(Set<Materiaprima> materiaprimaSet) {
        this.materiaprimaSet = materiaprimaSet;
    }

    public Set<Piezareal> getPiezarealSet() {
        return piezarealSet;
    }

    public void setPiezarealSet(Set<Piezareal> piezarealSet) {
        this.piezarealSet = piezarealSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcodigo != null ? idcodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codigodebarra)) {
            return false;
        }
        Codigodebarra other = (Codigodebarra) object;
        if ((this.idcodigo == null && other.idcodigo != null) || (this.idcodigo != null && !this.idcodigo.equals(other.idcodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Codigodebarra[idcodigo=" + idcodigo + "]";
    }

}
