/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Trabajotercerizado.findByMotivocancelacion", query = "SELECT t FROM Trabajotercerizado t WHERE t.motivocancelacion = :motivocancelacion"),
    @NamedQuery(name = "Trabajotercerizado.findByFechadelingresocotizacion", query = "SELECT t FROM Trabajotercerizado t WHERE t.fechadelingresocotizacion = :fechadelingresocotizacion"),
    @NamedQuery(name = "Trabajotercerizado.findByMontototal", query = "SELECT t FROM Trabajotercerizado t WHERE t.montototal = :montototal")})
public class Trabajotercerizado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trabajotercerizado_seq")
    @SequenceGenerator(name = "trabajotercerizado_seq", sequenceName = "trabajotercerizado_idtrabajo_seq", allocationSize = 1)
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
    @Column(name = "fechadelingresocotizacion")
    @Temporal(TemporalType.DATE)
    private Date fechadelingresocotizacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montototal")
    private Double montototal;
    @OneToMany(mappedBy = "trabajotercerizado")
    private List<Reclamoempresamantenimiento> reclamoempresamantenimientoList;
    @JoinColumn(name = "pedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estadotrabajotercerizado estado;
    @JoinColumn(name = "empresa", referencedColumnName = "idempresametalurgica")
    @ManyToOne
    private Empresametalurgica empresa;
    @OneToMany(mappedBy = "trabajotercerizado")
    private List<Reclamoempresametalurgica> reclamoempresametalurgicaList;
    @OneToMany(mappedBy = "idtrabajo")
    private List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtrabajotercerizado")
    private List<Detalletrabajotercerizado> detalletrabajotercerizadoList;

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

    public Date getFechadelingresocotizacion() {
        return fechadelingresocotizacion;
    }

    public void setFechadelingresocotizacion(Date fechadelingresocotizacion) {
        this.fechadelingresocotizacion = fechadelingresocotizacion;
    }

    public Double getMontototal() {
        return montototal;
    }

    public void setMontototal(Double montototal) {
        this.montototal = montototal;
    }

    public List<Reclamoempresamantenimiento> getReclamoempresamantenimientoList() {
        return reclamoempresamantenimientoList;
    }

    public void setReclamoempresamantenimientoList(List<Reclamoempresamantenimiento> reclamoempresamantenimientoList) {
        this.reclamoempresamantenimientoList = reclamoempresamantenimientoList;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Estadotrabajotercerizado getEstado() {
        return estado;
    }

    public void setEstado(Estadotrabajotercerizado estado) {
        this.estado = estado;
    }

    public Empresametalurgica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresametalurgica empresa) {
        this.empresa = empresa;
    }

    public List<Reclamoempresametalurgica> getReclamoempresametalurgicaList() {
        return reclamoempresametalurgicaList;
    }

    public void setReclamoempresametalurgicaList(List<Reclamoempresametalurgica> reclamoempresametalurgicaList) {
        this.reclamoempresametalurgicaList = reclamoempresametalurgicaList;
    }

    public List<Detallereclamoempresamantenimiento> getDetallereclamoempresamantenimientoList() {
        return detallereclamoempresamantenimientoList;
    }

    public void setDetallereclamoempresamantenimientoList(List<Detallereclamoempresamantenimiento> detallereclamoempresamantenimientoList) {
        this.detallereclamoempresamantenimientoList = detallereclamoempresamantenimientoList;
    }

    public List<Detalletrabajotercerizado> getDetalletrabajotercerizadoList() {
        return detalletrabajotercerizadoList;
    }

    public void setDetalletrabajotercerizadoList(List<Detalletrabajotercerizado> detalletrabajotercerizadoList) {
        this.detalletrabajotercerizadoList = detalletrabajotercerizadoList;
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
        return "metalsoft.datos.jpa.entity.Trabajotercerizado[ idtrabajo=" + idtrabajo + " ]";
    }
    
}
