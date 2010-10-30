/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "trabajotercerizado")
@NamedQueries({
    @NamedQuery(name = "Trabajotercerizado.findAll", query = "SELECT t FROM Trabajotercerizado t"),
    @NamedQuery(name = "Trabajotercerizado.findByIdtrabajo", query = "SELECT t FROM Trabajotercerizado t WHERE t.idtrabajo = :idtrabajo"),
    @NamedQuery(name = "Trabajotercerizado.findByNrotrabajotercerizado", query = "SELECT t FROM Trabajotercerizado t WHERE t.nrotrabajotercerizado = :nrotrabajotercerizado"),
    @NamedQuery(name = "Trabajotercerizado.findByFechapedidocotizacion", query = "SELECT t FROM Trabajotercerizado t WHERE t.fechapedidocotizacion = :fechapedidocotizacion"),
    @NamedQuery(name = "Trabajotercerizado.findByFechaentregaestipulada", query = "SELECT t FROM Trabajotercerizado t WHERE t.fechaentregaestipulada = :fechaentregaestipulada"),
    @NamedQuery(name = "Trabajotercerizado.findByFechaconfirmaciontrabajo", query = "SELECT t FROM Trabajotercerizado t WHERE t.fechaconfirmaciontrabajo = :fechaconfirmaciontrabajo"),
    @NamedQuery(name = "Trabajotercerizado.findByFechacancelacion", query = "SELECT t FROM Trabajotercerizado t WHERE t.fechacancelacion = :fechacancelacion"),
    @NamedQuery(name = "Trabajotercerizado.findByFechaentregareal", query = "SELECT t FROM Trabajotercerizado t WHERE t.fechaentregareal = :fechaentregareal"),
    @NamedQuery(name = "Trabajotercerizado.findByFechaenvioaempresa", query = "SELECT t FROM Trabajotercerizado t WHERE t.fechaenvioaempresa = :fechaenvioaempresa"),
    @NamedQuery(name = "Trabajotercerizado.findByMotivocancelacion", query = "SELECT t FROM Trabajotercerizado t WHERE t.motivocancelacion = :motivocancelacion")})
public class Trabajotercerizado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtrabajo")
    private Long idtrabajo;
    @Column(name = "nrotrabajotercerizado")
    private BigInteger nrotrabajotercerizado;
    @Column(name = "fechapedidocotizacion")
    @Temporal(TemporalType.DATE)
    private Date fechapedidocotizacion;
    @Column(name = "fechaentregaestipulada")
    @Temporal(TemporalType.DATE)
    private Date fechaentregaestipulada;
    @Column(name = "fechaconfirmaciontrabajo")
    @Temporal(TemporalType.DATE)
    private Date fechaconfirmaciontrabajo;
    @Column(name = "fechacancelacion")
    @Temporal(TemporalType.DATE)
    private Date fechacancelacion;
    @Column(name = "fechaentregareal")
    @Temporal(TemporalType.DATE)
    private Date fechaentregareal;
    @Column(name = "fechaenvioaempresa")
    @Temporal(TemporalType.DATE)
    private Date fechaenvioaempresa;
    @Column(name = "motivocancelacion")
    private String motivocancelacion;
    @JoinColumn(name = "empresa", referencedColumnName = "idempresametalurgica")
    @ManyToOne
    private Empresametalurgica empresa;
    @JoinColumn(name = "empresa", referencedColumnName = "idempresametalurgica")
    @ManyToOne
    private Empresametalurgica empresa1;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadotrabajotercerizado estado;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadotrabajotercerizado estado1;
    @JoinColumn(name = "pedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido;
    @JoinColumn(name = "pedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido1;
    @OneToMany(mappedBy = "trabajotercerizado")
    private Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet;
    @OneToMany(mappedBy = "trabajotercerizado1")
    private Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajotercerizado")
    private Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajotercerizado1")
    private Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet1;

    public Trabajotercerizado() {
    }

    public Trabajotercerizado(Long idtrabajo) {
        this.idtrabajo = idtrabajo;
    }

    public Long getIdtrabajo() {
        return idtrabajo;
    }

    public void setIdtrabajo(Long idtrabajo) {
        this.idtrabajo = idtrabajo;
    }

    public BigInteger getNrotrabajotercerizado() {
        return nrotrabajotercerizado;
    }

    public void setNrotrabajotercerizado(BigInteger nrotrabajotercerizado) {
        this.nrotrabajotercerizado = nrotrabajotercerizado;
    }

    public Date getFechapedidocotizacion() {
        return fechapedidocotizacion;
    }

    public void setFechapedidocotizacion(Date fechapedidocotizacion) {
        this.fechapedidocotizacion = fechapedidocotizacion;
    }

    public Date getFechaentregaestipulada() {
        return fechaentregaestipulada;
    }

    public void setFechaentregaestipulada(Date fechaentregaestipulada) {
        this.fechaentregaestipulada = fechaentregaestipulada;
    }

    public Date getFechaconfirmaciontrabajo() {
        return fechaconfirmaciontrabajo;
    }

    public void setFechaconfirmaciontrabajo(Date fechaconfirmaciontrabajo) {
        this.fechaconfirmaciontrabajo = fechaconfirmaciontrabajo;
    }

    public Date getFechacancelacion() {
        return fechacancelacion;
    }

    public void setFechacancelacion(Date fechacancelacion) {
        this.fechacancelacion = fechacancelacion;
    }

    public Date getFechaentregareal() {
        return fechaentregareal;
    }

    public void setFechaentregareal(Date fechaentregareal) {
        this.fechaentregareal = fechaentregareal;
    }

    public Date getFechaenvioaempresa() {
        return fechaenvioaempresa;
    }

    public void setFechaenvioaempresa(Date fechaenvioaempresa) {
        this.fechaenvioaempresa = fechaenvioaempresa;
    }

    public String getMotivocancelacion() {
        return motivocancelacion;
    }

    public void setMotivocancelacion(String motivocancelacion) {
        this.motivocancelacion = motivocancelacion;
    }

    public Empresametalurgica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresametalurgica empresa) {
        this.empresa = empresa;
    }

    public Empresametalurgica getEmpresa1() {
        return empresa1;
    }

    public void setEmpresa1(Empresametalurgica empresa1) {
        this.empresa1 = empresa1;
    }

    public Estadotrabajotercerizado getEstado() {
        return estado;
    }

    public void setEstado(Estadotrabajotercerizado estado) {
        this.estado = estado;
    }

    public Estadotrabajotercerizado getEstado1() {
        return estado1;
    }

    public void setEstado1(Estadotrabajotercerizado estado1) {
        this.estado1 = estado1;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido1() {
        return pedido1;
    }

    public void setPedido1(Pedido pedido1) {
        this.pedido1 = pedido1;
    }

    public Set<Reclamoempresametalurgica> getReclamoempresametalurgicaSet() {
        return reclamoempresametalurgicaSet;
    }

    public void setReclamoempresametalurgicaSet(Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet) {
        this.reclamoempresametalurgicaSet = reclamoempresametalurgicaSet;
    }

    public Set<Reclamoempresametalurgica> getReclamoempresametalurgicaSet1() {
        return reclamoempresametalurgicaSet1;
    }

    public void setReclamoempresametalurgicaSet1(Set<Reclamoempresametalurgica> reclamoempresametalurgicaSet1) {
        this.reclamoempresametalurgicaSet1 = reclamoempresametalurgicaSet1;
    }

    public Set<Detalletrabajotercerizado> getDetalletrabajotercerizadoSet() {
        return detalletrabajotercerizadoSet;
    }

    public void setDetalletrabajotercerizadoSet(Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet) {
        this.detalletrabajotercerizadoSet = detalletrabajotercerizadoSet;
    }

    public Set<Detalletrabajotercerizado> getDetalletrabajotercerizadoSet1() {
        return detalletrabajotercerizadoSet1;
    }

    public void setDetalletrabajotercerizadoSet1(Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet1) {
        this.detalletrabajotercerizadoSet1 = detalletrabajotercerizadoSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtrabajo != null ? idtrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajotercerizado)) {
            return false;
        }
        Trabajotercerizado other = (Trabajotercerizado) object;
        if ((this.idtrabajo == null && other.idtrabajo != null) || (this.idtrabajo != null && !this.idtrabajo.equals(other.idtrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Trabajotercerizado[idtrabajo=" + idtrabajo + "]";
    }

}
