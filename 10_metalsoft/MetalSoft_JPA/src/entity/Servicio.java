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
@Table(name = "servicio")
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
    @NamedQuery(name = "Servicio.findByIdservicio", query = "SELECT s FROM Servicio s WHERE s.idservicio = :idservicio"),
    @NamedQuery(name = "Servicio.findByNombre", query = "SELECT s FROM Servicio s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Servicio.findByDescripcion", query = "SELECT s FROM Servicio s WHERE s.descripcion = :descripcion")})
public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idservicio")
    private Long idservicio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "servicio")
    private Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSet;
    @OneToMany(mappedBy = "servicio1")
    private Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSet1;

    public Servicio() {
    }

    public Servicio(Long idservicio) {
        this.idservicio = idservicio;
    }

    public Long getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Long idservicio) {
        this.idservicio = idservicio;
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

    public Set<Detallemantenimientopreventivo> getDetallemantenimientopreventivoSet() {
        return detallemantenimientopreventivoSet;
    }

    public void setDetallemantenimientopreventivoSet(Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSet) {
        this.detallemantenimientopreventivoSet = detallemantenimientopreventivoSet;
    }

    public Set<Detallemantenimientopreventivo> getDetallemantenimientopreventivoSet1() {
        return detallemantenimientopreventivoSet1;
    }

    public void setDetallemantenimientopreventivoSet1(Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSet1) {
        this.detallemantenimientopreventivoSet1 = detallemantenimientopreventivoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservicio != null ? idservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.idservicio == null && other.idservicio != null) || (this.idservicio != null && !this.idservicio.equals(other.idservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Servicio[idservicio=" + idservicio + "]";
    }

}
