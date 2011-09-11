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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "encm")
    private Double encm;
    @Column(name = "enmm")
    private Double enmm;
    @OneToMany(mappedBy = "unidadmedida")
    private List<Materiaprima> materiaprimaList;
    @OneToMany(mappedBy = "unidadmedida")
    private List<Pieza> piezaList;
    @OneToMany(mappedBy = "idunidadmedida")
    private List<Maquina> maquinaList;
    @OneToMany(mappedBy = "unidaddemedida")
    private List<Etapadeproduccion> etapadeproduccionList;

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

    public List<Materiaprima> getMateriaprimaList() {
        return materiaprimaList;
    }

    public void setMateriaprimaList(List<Materiaprima> materiaprimaList) {
        this.materiaprimaList = materiaprimaList;
    }

    public List<Pieza> getPiezaList() {
        return piezaList;
    }

    public void setPiezaList(List<Pieza> piezaList) {
        this.piezaList = piezaList;
    }

    public List<Maquina> getMaquinaList() {
        return maquinaList;
    }

    public void setMaquinaList(List<Maquina> maquinaList) {
        this.maquinaList = maquinaList;
    }

    public List<Etapadeproduccion> getEtapadeproduccionList() {
        return etapadeproduccionList;
    }

    public void setEtapadeproduccionList(List<Etapadeproduccion> etapadeproduccionList) {
        this.etapadeproduccionList = etapadeproduccionList;
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
        return "metalsoft.datos.jpa.entity.Unidadmedida[ idunidadmedida=" + idunidadmedida + " ]";
    }
    
}
