/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallereclamoproveedor")
@NamedQueries({
    @NamedQuery(name = "Detallereclamoproveedor.findAll", query = "SELECT d FROM Detallereclamoproveedor d"),
    @NamedQuery(name = "Detallereclamoproveedor.findByIddetalle", query = "SELECT d FROM Detallereclamoproveedor d WHERE d.detallereclamoproveedorPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallereclamoproveedor.findByIdreclamo", query = "SELECT d FROM Detallereclamoproveedor d WHERE d.detallereclamoproveedorPK.idreclamo = :idreclamo"),
    @NamedQuery(name = "Detallereclamoproveedor.findByCantidad", query = "SELECT d FROM Detallereclamoproveedor d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallereclamoproveedor.findByDescripcion", query = "SELECT d FROM Detallereclamoproveedor d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Detallereclamoproveedor.findByMotivo", query = "SELECT d FROM Detallereclamoproveedor d WHERE d.motivo = :motivo"),
    @NamedQuery(name = "Detallereclamoproveedor.findByFechaegreso", query = "SELECT d FROM Detallereclamoproveedor d WHERE d.fechaegreso = :fechaegreso")})
public class Detallereclamoproveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallereclamoproveedorPK detallereclamoproveedorPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "fechaegreso")
    @Temporal(TemporalType.DATE)
    private Date fechaegreso;
    @JoinColumn(name = "idreclamo", referencedColumnName = "idreclamo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reclamoproveedor reclamoproveedor;
    @JoinColumns({
        @JoinColumn(name = "idcompra", referencedColumnName = "idcompra"),
        @JoinColumn(name = "iddetallecompra", referencedColumnName = "iddetalle")})
    @ManyToOne
    private Detallecompra detallecompra;

    public Detallereclamoproveedor() {
    }

    public Detallereclamoproveedor(DetallereclamoproveedorPK detallereclamoproveedorPK) {
        this.detallereclamoproveedorPK = detallereclamoproveedorPK;
    }

    public Detallereclamoproveedor(long iddetalle, long idreclamo) {
        this.detallereclamoproveedorPK = new DetallereclamoproveedorPK(iddetalle, idreclamo);
    }

    public DetallereclamoproveedorPK getDetallereclamoproveedorPK() {
        return detallereclamoproveedorPK;
    }

    public void setDetallereclamoproveedorPK(DetallereclamoproveedorPK detallereclamoproveedorPK) {
        this.detallereclamoproveedorPK = detallereclamoproveedorPK;
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

    public Date getFechaegreso() {
        return fechaegreso;
    }

    public void setFechaegreso(Date fechaegreso) {
        this.fechaegreso = fechaegreso;
    }

    public Reclamoproveedor getReclamoproveedor() {
        return reclamoproveedor;
    }

    public void setReclamoproveedor(Reclamoproveedor reclamoproveedor) {
        this.reclamoproveedor = reclamoproveedor;
    }

    public Detallecompra getDetallecompra() {
        return detallecompra;
    }

    public void setDetallecompra(Detallecompra detallecompra) {
        this.detallecompra = detallecompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallereclamoproveedorPK != null ? detallereclamoproveedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallereclamoproveedor)) {
            return false;
        }
        Detallereclamoproveedor other = (Detallereclamoproveedor) object;
        if ((this.detallereclamoproveedorPK == null && other.detallereclamoproveedorPK != null) || (this.detallereclamoproveedorPK != null && !this.detallereclamoproveedorPK.equals(other.detallereclamoproveedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallereclamoproveedor[ detallereclamoproveedorPK=" + detallereclamoproveedorPK + " ]";
    }
    
}
