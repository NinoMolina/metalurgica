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
@Table(name = "tipodocumento")
@NamedQueries({
    @NamedQuery(name = "Tipodocumento.findAll", query = "SELECT t FROM Tipodocumento t"),
    @NamedQuery(name = "Tipodocumento.findByIdtipodocumento", query = "SELECT t FROM Tipodocumento t WHERE t.idtipodocumento = :idtipodocumento"),
    @NamedQuery(name = "Tipodocumento.findByTipo", query = "SELECT t FROM Tipodocumento t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Tipodocumento.findByNombre", query = "SELECT t FROM Tipodocumento t WHERE t.nombre = :nombre")})
public class Tipodocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipodocumento")
    private Long idtipodocumento;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "tipodocumento")
    private List<Responsable> responsableList;
    @OneToMany(mappedBy = "tipodocumento")
    private List<Empleado> empleadoList;

    public Tipodocumento() {
    }

    public Tipodocumento(Long idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public Long getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(Long idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Responsable> getResponsableList() {
        return responsableList;
    }

    public void setResponsableList(List<Responsable> responsableList) {
        this.responsableList = responsableList;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipodocumento != null ? idtipodocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodocumento)) {
            return false;
        }
        Tipodocumento other = (Tipodocumento) object;
        if ((this.idtipodocumento == null && other.idtipodocumento != null) || (this.idtipodocumento != null && !this.idtipodocumento.equals(other.idtipodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Tipodocumento[ idtipodocumento=" + idtipodocumento + " ]";
    }
    
}
