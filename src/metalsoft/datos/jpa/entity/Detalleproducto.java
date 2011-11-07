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
@Table(name = "detalleproducto")
@NamedQueries({
    @NamedQuery(name = "Detalleproducto.findAll", query = "SELECT d FROM Detalleproducto d"),
    @NamedQuery(name = "Detalleproducto.findByIddetalle", query = "SELECT d FROM Detalleproducto d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalleproducto.findByCantidadpiezas", query = "SELECT d FROM Detalleproducto d WHERE d.cantidadpiezas = :cantidadpiezas"),
    @NamedQuery(name = "Detalleproducto.findByDescripcion", query = "SELECT d FROM Detalleproducto d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detalleproducto.findByPieza", query = "SELECT d FROM Detalleproducto d WHERE d.pieza = :pieza")})
public class Detalleproducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalleproducto_seq")
    @SequenceGenerator(name = "detalleproducto_seq", sequenceName = "detalleproducto_iddetalle_seq", allocationSize = 1)
    @Column(name = "iddetalle")
    private Long iddetalle;
    @Column(name = "cantidadpiezas")
    private Integer cantidadpiezas;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "pieza")
    private long pieza;
    @OneToMany(mappedBy = "detalleproducto")
    private List<Detalleproductoreal> detalleproductorealList;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public Detalleproducto() {
    }

    public Detalleproducto(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Detalleproducto(Long iddetalle, long pieza) {
        this.iddetalle = iddetalle;
        this.pieza = pieza;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getCantidadpiezas() {
        return cantidadpiezas;
    }

    public void setCantidadpiezas(Integer cantidadpiezas) {
        this.cantidadpiezas = cantidadpiezas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPieza() {
        return pieza;
    }

    public void setPieza(long pieza) {
        this.pieza = pieza;
    }

    public List<Detalleproductoreal> getDetalleproductorealList() {
        return detalleproductorealList;
    }

    public void setDetalleproductorealList(List<Detalleproductoreal> detalleproductorealList) {
        this.detalleproductorealList = detalleproductorealList;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
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
        if (!(object instanceof Detalleproducto)) {
            return false;
        }
        Detalleproducto other = (Detalleproducto) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detalleproducto[ iddetalle=" + iddetalle + " ]";
    }
    
}
