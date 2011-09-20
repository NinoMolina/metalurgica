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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigodebarra_seq")
    @SequenceGenerator(name = "codigodebarra_seq", sequenceName = "codigodebarra_idcodigo_seq", allocationSize = 1)
    @Column(name = "idcodigo")
    private Long idcodigo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(mappedBy = "codigobarra")
    private List<Productoreal> productorealList;
    @OneToMany(mappedBy = "codbarra")
    private List<Materiaprima> materiaprimaList;
    @OneToMany(mappedBy = "idcodigobarra")
    private List<Piezareal> piezarealList;

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

    public List<Productoreal> getProductorealList() {
        return productorealList;
    }

    public void setProductorealList(List<Productoreal> productorealList) {
        this.productorealList = productorealList;
    }

    public List<Materiaprima> getMateriaprimaList() {
        return materiaprimaList;
    }

    public void setMateriaprimaList(List<Materiaprima> materiaprimaList) {
        this.materiaprimaList = materiaprimaList;
    }

    public List<Piezareal> getPiezarealList() {
        return piezarealList;
    }

    public void setPiezarealList(List<Piezareal> piezarealList) {
        this.piezarealList = piezarealList;
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
        return "metalsoft.datos.jpa.entity.Codigodebarra[ idcodigo=" + idcodigo + " ]";
    }
    
}
