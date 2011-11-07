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
@Table(name = "estadoreclamo")
@NamedQueries({
    @NamedQuery(name = "Estadoreclamo.findAll", query = "SELECT e FROM Estadoreclamo e"),
    @NamedQuery(name = "Estadoreclamo.findByIdestadoreclamo", query = "SELECT e FROM Estadoreclamo e WHERE e.idestadoreclamo = :idestadoreclamo"),
    @NamedQuery(name = "Estadoreclamo.findByNombre", query = "SELECT e FROM Estadoreclamo e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estadoreclamo.findByDescripcion", query = "SELECT e FROM Estadoreclamo e WHERE e.descripcion = :descripcion")})
public class Estadoreclamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idestadoreclamo")
    private Long idestadoreclamo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idestadoreclamo")
    private List<Reclamoproveedor> reclamoproveedorList;
    @OneToMany(mappedBy = "idestadoreclamo")
    private List<Reclamoempresamantenimiento> reclamoempresamantenimientoList;
    @OneToMany(mappedBy = "idestadoreclamo")
    private List<Reclamoempresametalurgica> reclamoempresametalurgicaList;
    @OneToMany(mappedBy = "idestadoreclamo")
    private List<Reclamocliente> reclamoclienteList;

    public Estadoreclamo() {
    }

    public Estadoreclamo(Long idestadoreclamo) {
        this.idestadoreclamo = idestadoreclamo;
    }

    public Long getIdestadoreclamo() {
        return idestadoreclamo;
    }

    public void setIdestadoreclamo(Long idestadoreclamo) {
        this.idestadoreclamo = idestadoreclamo;
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

    public List<Reclamoproveedor> getReclamoproveedorList() {
        return reclamoproveedorList;
    }

    public void setReclamoproveedorList(List<Reclamoproveedor> reclamoproveedorList) {
        this.reclamoproveedorList = reclamoproveedorList;
    }

    public List<Reclamoempresamantenimiento> getReclamoempresamantenimientoList() {
        return reclamoempresamantenimientoList;
    }

    public void setReclamoempresamantenimientoList(List<Reclamoempresamantenimiento> reclamoempresamantenimientoList) {
        this.reclamoempresamantenimientoList = reclamoempresamantenimientoList;
    }

    public List<Reclamoempresametalurgica> getReclamoempresametalurgicaList() {
        return reclamoempresametalurgicaList;
    }

    public void setReclamoempresametalurgicaList(List<Reclamoempresametalurgica> reclamoempresametalurgicaList) {
        this.reclamoempresametalurgicaList = reclamoempresametalurgicaList;
    }

    public List<Reclamocliente> getReclamoclienteList() {
        return reclamoclienteList;
    }

    public void setReclamoclienteList(List<Reclamocliente> reclamoclienteList) {
        this.reclamoclienteList = reclamoclienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoreclamo != null ? idestadoreclamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadoreclamo)) {
            return false;
        }
        Estadoreclamo other = (Estadoreclamo) object;
        if ((this.idestadoreclamo == null && other.idestadoreclamo != null) || (this.idestadoreclamo != null && !this.idestadoreclamo.equals(other.idestadoreclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Estadoreclamo[ idestadoreclamo=" + idestadoreclamo + " ]";
    }
    
}
