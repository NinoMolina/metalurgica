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
@Table(name = "marca")
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findByIdmarca", query = "SELECT m FROM Marca m WHERE m.idmarca = :idmarca"),
    @NamedQuery(name = "Marca.findByNombre", query = "SELECT m FROM Marca m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Marca.findByDescripcion", query = "SELECT m FROM Marca m WHERE m.descripcion = :descripcion")})
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmarca")
    private Long idmarca;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "marca")
    private List<Maquina> maquinaList;

    public Marca() {
    }

    public Marca(Long idmarca) {
        this.idmarca = idmarca;
    }

    public Long getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Long idmarca) {
        this.idmarca = idmarca;
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

    public List<Maquina> getMaquinaList() {
        return maquinaList;
    }

    public void setMaquinaList(List<Maquina> maquinaList) {
        this.maquinaList = maquinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmarca != null ? idmarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.idmarca == null && other.idmarca != null) || (this.idmarca != null && !this.idmarca.equals(other.idmarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Marca[ idmarca=" + idmarca + " ]";
    }
    
}
