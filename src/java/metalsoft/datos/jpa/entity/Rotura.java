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
@Table(name = "rotura")
@NamedQueries({
    @NamedQuery(name = "Rotura.findAll", query = "SELECT r FROM Rotura r"),
    @NamedQuery(name = "Rotura.findByIdrotura", query = "SELECT r FROM Rotura r WHERE r.idrotura = :idrotura"),
    @NamedQuery(name = "Rotura.findByNombre", query = "SELECT r FROM Rotura r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Rotura.findByDescripcion", query = "SELECT r FROM Rotura r WHERE r.descripcion = :descripcion")})
public class Rotura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idrotura")
    private Long idrotura;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "rotura")
    private List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoList;

    public Rotura() {
    }

    public Rotura(Long idrotura) {
        this.idrotura = idrotura;
    }

    public Long getIdrotura() {
        return idrotura;
    }

    public void setIdrotura(Long idrotura) {
        this.idrotura = idrotura;
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

    public List<Detallemantenimientocorrectivo> getDetallemantenimientocorrectivoList() {
        return detallemantenimientocorrectivoList;
    }

    public void setDetallemantenimientocorrectivoList(List<Detallemantenimientocorrectivo> detallemantenimientocorrectivoList) {
        this.detallemantenimientocorrectivoList = detallemantenimientocorrectivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrotura != null ? idrotura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotura)) {
            return false;
        }
        Rotura other = (Rotura) object;
        if ((this.idrotura == null && other.idrotura != null) || (this.idrotura != null && !this.idrotura.equals(other.idrotura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Rotura[ idrotura=" + idrotura + " ]";
    }
    
}
