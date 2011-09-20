/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallereclamocliente")
@NamedQueries({
    @NamedQuery(name = "Detallereclamocliente.findAll", query = "SELECT d FROM Detallereclamocliente d"),
    @NamedQuery(name = "Detallereclamocliente.findByIddetalle", query = "SELECT d FROM Detallereclamocliente d WHERE d.detallereclamoclientePK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallereclamocliente.findByIdreclamo", query = "SELECT d FROM Detallereclamocliente d WHERE d.detallereclamoclientePK.idreclamo = :idreclamo"),
    @NamedQuery(name = "Detallereclamocliente.findByCantidad", query = "SELECT d FROM Detallereclamocliente d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallereclamocliente.findByDescripcion", query = "SELECT d FROM Detallereclamocliente d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detallereclamocliente.findByMotivo", query = "SELECT d FROM Detallereclamocliente d WHERE d.motivo = :motivo")})
public class Detallereclamocliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallereclamoclientePK detallereclamoclientePK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "motivo")
    private String motivo;
    @JoinColumn(name = "idreclamo", referencedColumnName = "idreclamo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reclamocliente reclamocliente;
    @JoinColumn(name = "producto", referencedColumnName = "idproducto")
    @ManyToOne
    private Producto producto;

    public Detallereclamocliente() {
    }

    public Detallereclamocliente(DetallereclamoclientePK detallereclamoclientePK) {
        this.detallereclamoclientePK = detallereclamoclientePK;
    }

    public Detallereclamocliente(long iddetalle, long idreclamo) {
        this.detallereclamoclientePK = new DetallereclamoclientePK(iddetalle, idreclamo);
    }

    public DetallereclamoclientePK getDetallereclamoclientePK() {
        return detallereclamoclientePK;
    }

    public void setDetallereclamoclientePK(DetallereclamoclientePK detallereclamoclientePK) {
        this.detallereclamoclientePK = detallereclamoclientePK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Reclamocliente getReclamocliente() {
        return reclamocliente;
    }

    public void setReclamocliente(Reclamocliente reclamocliente) {
        this.reclamocliente = reclamocliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallereclamoclientePK != null ? detallereclamoclientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallereclamocliente)) {
            return false;
        }
        Detallereclamocliente other = (Detallereclamocliente) object;
        if ((this.detallereclamoclientePK == null && other.detallereclamoclientePK != null) || (this.detallereclamoclientePK != null && !this.detallereclamoclientePK.equals(other.detallereclamoclientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallereclamocliente[ detallereclamoclientePK=" + detallereclamoclientePK + " ]";
    }
    
}
