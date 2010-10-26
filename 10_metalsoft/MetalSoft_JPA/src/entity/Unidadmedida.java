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
@Table(name = "unidadmedida")
@NamedQueries({
    @NamedQuery(name = "Unidadmedida.findAll", query = "SELECT u FROM Unidadmedida u"),
    @NamedQuery(name = "Unidadmedida.findByIdunidadmedida", query = "SELECT u FROM Unidadmedida u WHERE u.idunidadmedida = :idunidadmedida"),
    @NamedQuery(name = "Unidadmedida.findByNombre", query = "SELECT u FROM Unidadmedida u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Unidadmedida.findByDescripcion", query = "SELECT u FROM Unidadmedida u WHERE u.descripcion = :descripcion"),
    @NamedQuery(name = "Unidadmedida.findByEncm", query = "SELECT u FROM Unidadmedida u WHERE u.encm = :encm"),
    @NamedQuery(name = "Unidadmedida.findByEnmm", query = "SELECT u FROM Unidadmedida u WHERE u.enmm = :enmm")})
public class Unidadmedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idunidadmedida")
    private Long idunidadmedida;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "encm")
    private Double encm;
    @Column(name = "enmm")
    private Double enmm;
    @OneToMany(mappedBy = "unidadmedida")
    private Set<Materiaprima> materiaprimaSet;
    @OneToMany(mappedBy = "unidadmedida")
    private Set<Pieza> piezaSet;
    @OneToMany(mappedBy = "idunidadmedida")
    private Set<Maquina> maquinaSet;
    @OneToMany(mappedBy = "unidaddemedida")
    private Set<Etapadeproduccion> etapadeproduccionSet;

    public Unidadmedida() {
    }

    public Unidadmedida(Long idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
    }

    public Long getIdunidadmedida() {
        return idunidadmedida;
    }

    public void setIdunidadmedida(Long idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
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

    public Double getEncm() {
        return encm;
    }

    public void setEncm(Double encm) {
        this.encm = encm;
    }

    public Double getEnmm() {
        return enmm;
    }

    public void setEnmm(Double enmm) {
        this.enmm = enmm;
    }

    public Set<Materiaprima> getMateriaprimaSet() {
        return materiaprimaSet;
    }

    public void setMateriaprimaSet(Set<Materiaprima> materiaprimaSet) {
        this.materiaprimaSet = materiaprimaSet;
    }

    public Set<Pieza> getPiezaSet() {
        return piezaSet;
    }

    public void setPiezaSet(Set<Pieza> piezaSet) {
        this.piezaSet = piezaSet;
    }

    public Set<Maquina> getMaquinaSet() {
        return maquinaSet;
    }

    public void setMaquinaSet(Set<Maquina> maquinaSet) {
        this.maquinaSet = maquinaSet;
    }

    public Set<Etapadeproduccion> getEtapadeproduccionSet() {
        return etapadeproduccionSet;
    }

    public void setEtapadeproduccionSet(Set<Etapadeproduccion> etapadeproduccionSet) {
        this.etapadeproduccionSet = etapadeproduccionSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idunidadmedida != null ? idunidadmedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadmedida)) {
            return false;
        }
        Unidadmedida other = (Unidadmedida) object;
        if ((this.idunidadmedida == null && other.idunidadmedida != null) || (this.idunidadmedida != null && !this.idunidadmedida.equals(other.idunidadmedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Unidadmedida[idunidadmedida=" + idunidadmedida + "]";
    }

}
