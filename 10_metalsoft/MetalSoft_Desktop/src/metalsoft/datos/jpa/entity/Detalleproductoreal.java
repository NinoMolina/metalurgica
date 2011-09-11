/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detalleproductoreal")
@NamedQueries({
    @NamedQuery(name = "Detalleproductoreal.findAll", query = "SELECT d FROM Detalleproductoreal d"),
    @NamedQuery(name = "Detalleproductoreal.findByIddetalle", query = "SELECT d FROM Detalleproductoreal d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleproductoreal.findByCantidadPiezas", query = "SELECT d FROM Detalleproductoreal d WHERE d.cantidadPiezas = :cantidadPiezas"),
    @NamedQuery(name = "Detalleproductoreal.findByDescripcion", query = "SELECT d FROM Detalleproductoreal d WHERE d.descripcion = :descripcion")})
public class Detalleproductoreal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalleproductoreal_seq")
    @SequenceGenerator(name = "detalleproductoreal_seq", sequenceName = "detalleproductoreal_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "cantidadPiezas")
    private Integer cantidadPiezas;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "idproductoreal", referencedColumnName = "idproductoreal")
    @ManyToOne(optional = false)
    private Productoreal idproductoreal;
    @JoinColumn(name = "idpiezareal", referencedColumnName = "idpiezareal")
    @ManyToOne
    private Piezareal idpiezareal;
    @JoinColumn(name = "detalleproducto", referencedColumnName = "iddetalle")
    @ManyToOne
    private Detalleproducto detalleproducto;

    public Detalleproductoreal() {
    }

    public Detalleproductoreal(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(Integer cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Productoreal getIdproductoreal() {
        return idproductoreal;
    }

    public void setIdproductoreal(Productoreal idproductoreal) {
        this.idproductoreal = idproductoreal;
    }

    public Piezareal getIdpiezareal() {
        return idpiezareal;
    }

    public void setIdpiezareal(Piezareal idpiezareal) {
        this.idpiezareal = idpiezareal;
    }

    public Detalleproducto getDetalleproducto() {
        return detalleproducto;
    }

    public void setDetalleproducto(Detalleproducto detalleproducto) {
        this.detalleproducto = detalleproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleproductoreal)) {
            return false;
        }
        Detalleproductoreal other = (Detalleproductoreal) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalleproductoreal[ iddetalle=" + iddetalle + " ]";
    }
    
}
