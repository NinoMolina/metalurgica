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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "piezareal")
@NamedQueries({
    @NamedQuery(name = "Piezareal.findAll", query = "SELECT p FROM Piezareal p"),
    @NamedQuery(name = "Piezareal.findByIdpiezareal", query = "SELECT p FROM Piezareal p WHERE p.idpiezareal = :idpiezareal"),
    @NamedQuery(name = "Piezareal.findByNropieza", query = "SELECT p FROM Piezareal p WHERE p.nropieza = :nropieza")})
public class Piezareal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "piezareal_seq")
    @SequenceGenerator(name = "piezareal_seq", sequenceName = "piezareal_idpiezareal_seq", allocationSize = 1)
    @Column(name = "idpiezareal")
    private Long idpiezareal;
    @Column(name = "nropieza")
    private Integer nropieza;
    @OneToMany(mappedBy = "idpiezareal")
    private List<Detalleproductoreal> detalleproductorealList;
    @JoinColumn(name = "idpieza", referencedColumnName = "idpieza")
    @ManyToOne(optional = false)
    private Pieza idpieza;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadopiezareal estado;
    @JoinColumn(name = "idcodigobarra", referencedColumnName = "idcodigo")
    @ManyToOne
    private Codigodebarra idcodigobarra;
    @OneToMany(mappedBy = "piezareal")
    private List<Detalleejecucionplanificacion> detalleejecucionplanificacionList;
    @OneToMany(mappedBy = "piezareal")
    private List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList;
    @OneToMany(mappedBy = "idpiezareal")
    private List<Mpasignadaxpiezareal> mpasignadaxpiezarealList;

    public Piezareal() {
    }

    public Piezareal(Long idpiezareal) {
        this.idpiezareal = idpiezareal;
    }

    public Long getIdpiezareal() {
        return idpiezareal;
    }

    public void setIdpiezareal(Long idpiezareal) {
        this.idpiezareal = idpiezareal;
    }

    public Integer getNropieza() {
        return nropieza;
    }

    public void setNropieza(Integer nropieza) {
        this.nropieza = nropieza;
    }

    public List<Detalleproductoreal> getDetalleproductorealList() {
        return detalleproductorealList;
    }

    public void setDetalleproductorealList(List<Detalleproductoreal> detalleproductorealList) {
        this.detalleproductorealList = detalleproductorealList;
    }

    public Pieza getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(Pieza idpieza) {
        this.idpieza = idpieza;
    }

    public Estadopiezareal getEstado() {
        return estado;
    }

    public void setEstado(Estadopiezareal estado) {
        this.estado = estado;
    }

    public Codigodebarra getIdcodigobarra() {
        return idcodigobarra;
    }

    public void setIdcodigobarra(Codigodebarra idcodigobarra) {
        this.idcodigobarra = idcodigobarra;
    }

    public List<Detalleejecucionplanificacion> getDetalleejecucionplanificacionList() {
        return detalleejecucionplanificacionList;
    }

    public void setDetalleejecucionplanificacionList(List<Detalleejecucionplanificacion> detalleejecucionplanificacionList) {
        this.detalleejecucionplanificacionList = detalleejecucionplanificacionList;
    }

    public List<Detalleejecucionplanificacioncalidad> getDetalleejecucionplanificacioncalidadList() {
        return detalleejecucionplanificacioncalidadList;
    }

    public void setDetalleejecucionplanificacioncalidadList(List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadList) {
        this.detalleejecucionplanificacioncalidadList = detalleejecucionplanificacioncalidadList;
    }

    public List<Mpasignadaxpiezareal> getMpasignadaxpiezarealList() {
        return mpasignadaxpiezarealList;
    }

    public void setMpasignadaxpiezarealList(List<Mpasignadaxpiezareal> mpasignadaxpiezarealList) {
        this.mpasignadaxpiezarealList = mpasignadaxpiezarealList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpiezareal != null ? idpiezareal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piezareal)) {
            return false;
        }
        Piezareal other = (Piezareal) object;
        if ((this.idpiezareal == null && other.idpiezareal != null) || (this.idpiezareal != null && !this.idpiezareal.equals(other.idpiezareal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Piezareal[ idpiezareal=" + idpiezareal + " ]";
    }
    
}
