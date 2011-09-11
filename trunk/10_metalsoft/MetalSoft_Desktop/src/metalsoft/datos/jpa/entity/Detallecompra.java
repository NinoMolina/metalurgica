/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Nino
 */
@Entity
@Table(name = "detallecompra")
@NamedQueries({
    @NamedQuery(name = "Detallecompra.findAll", query = "SELECT d FROM Detallecompra d"),
    @NamedQuery(name = "Detallecompra.findByIddetalle", query = "SELECT d FROM Detallecompra d WHERE d.detallecompraPK.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detallecompra.findByIdcompra", query = "SELECT d FROM Detallecompra d WHERE d.detallecompraPK.idcompra = :idcompra"),
    @NamedQuery(name = "Detallecompra.findByCantidad", query = "SELECT d FROM Detallecompra d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallecompra.findByPreciohistorico", query = "SELECT d FROM Detallecompra d WHERE d.preciohistorico = :preciohistorico"),
    @NamedQuery(name = "Detallecompra.findByFecharecepcionparcial", query = "SELECT d FROM Detallecompra d WHERE d.fecharecepcionparcial = :fecharecepcionparcial")})
public class Detallecompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallecompraPK detallecompraPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preciohistorico")
    private Double preciohistorico;
    @Column(name = "fecharecepcionparcial")
    @Temporal(TemporalType.DATE)
    private Date fecharecepcionparcial;
    @JoinColumn(name = "materiaprima", referencedColumnName = "idmateriaprima")
    @ManyToOne
    private Materiaprima materiaprima;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadodetallecompra estado;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra compra;
    @OneToMany(mappedBy = "detallecompra")
    private List<Detallereclamoproveedor> detallereclamoproveedorList;

    public Detallecompra() {
    }

    public Detallecompra(DetallecompraPK detallecompraPK) {
        this.detallecompraPK = detallecompraPK;
    }

    public Detallecompra(long iddetalle, long idcompra) {
        this.detallecompraPK = new DetallecompraPK(iddetalle, idcompra);
    }

    public DetallecompraPK getDetallecompraPK() {
        return detallecompraPK;
    }

    public void setDetallecompraPK(DetallecompraPK detallecompraPK) {
        this.detallecompraPK = detallecompraPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPreciohistorico() {
        return preciohistorico;
    }

    public void setPreciohistorico(Double preciohistorico) {
        this.preciohistorico = preciohistorico;
    }

    public Date getFecharecepcionparcial() {
        return fecharecepcionparcial;
    }

    public void setFecharecepcionparcial(Date fecharecepcionparcial) {
        this.fecharecepcionparcial = fecharecepcionparcial;
    }

    public Materiaprima getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }

    public Estadodetallecompra getEstado() {
        return estado;
    }

    public void setEstado(Estadodetallecompra estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public List<Detallereclamoproveedor> getDetallereclamoproveedorList() {
        return detallereclamoproveedorList;
    }

    public void setDetallereclamoproveedorList(List<Detallereclamoproveedor> detallereclamoproveedorList) {
        this.detallereclamoproveedorList = detallereclamoproveedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallecompraPK != null ? detallecompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallecompra)) {
            return false;
        }
        Detallecompra other = (Detallecompra) object;
        if ((this.detallecompraPK == null && other.detallecompraPK != null) || (this.detallecompraPK != null && !this.detallecompraPK.equals(other.detallecompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metalsoft.datos.jpa.entity.Detallecompra[ detallecompraPK=" + detallecompraPK + " ]";
    }
    
}
