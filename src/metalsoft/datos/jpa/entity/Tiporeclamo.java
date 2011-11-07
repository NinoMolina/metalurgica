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
@Table(name = "tiporeclamo")
@NamedQueries({
    @NamedQuery(name = "Tiporeclamo.findAll", query = "SELECT t FROM Tiporeclamo t"),
    @NamedQuery(name = "Tiporeclamo.findByIdtiporeclamo", query = "SELECT t FROM Tiporeclamo t WHERE t.idtiporeclamo = :idtiporeclamo"),
    @NamedQuery(name = "Tiporeclamo.findByNombre", query = "SELECT t FROM Tiporeclamo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tiporeclamo.findByDescripcion", query = "SELECT t FROM Tiporeclamo t WHERE t.descripcion = :descripcion")})
public class Tiporeclamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtiporeclamo")
    private Long idtiporeclamo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tiporeclamo")
    private List<Reclamoproveedor> reclamoproveedorList;
    @OneToMany(mappedBy = "tiporeclamo")
    private List<Reclamoempresamantenimiento> reclamoempresamantenimientoList;
    @OneToMany(mappedBy = "tiporeclamo")
    private List<Reclamoempresametalurgica> reclamoempresametalurgicaList;
    @OneToMany(mappedBy = "tiporeclamo")
    private List<Reclamocliente> reclamoclienteList;

    public Tiporeclamo() {
    }

    public Tiporeclamo(Long idtiporeclamo) {
        this.idtiporeclamo = idtiporeclamo;
    }

    public Long getIdtiporeclamo() {
        return idtiporeclamo;
    }

    public void setIdtiporeclamo(Long idtiporeclamo) {
        this.idtiporeclamo = idtiporeclamo;
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
        hash += (idtiporeclamo != null ? idtiporeclamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiporeclamo)) {
            return false;
        }
        Tiporeclamo other = (Tiporeclamo) object;
        if ((this.idtiporeclamo == null && other.idtiporeclamo != null) || (this.idtiporeclamo != null && !this.idtiporeclamo.equals(other.idtiporeclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Tiporeclamo[ idtiporeclamo=" + idtiporeclamo + " ]";
    }
    
}
