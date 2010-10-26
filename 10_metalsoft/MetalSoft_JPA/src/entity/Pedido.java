/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
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
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByNropedido", query = "SELECT p FROM Pedido p WHERE p.nropedido = :nropedido"),
    @NamedQuery(name = "Pedido.findByFechaconfirmacionpedido", query = "SELECT p FROM Pedido p WHERE p.fechaconfirmacionpedido = :fechaconfirmacionpedido"),
    @NamedQuery(name = "Pedido.findByFechaentregaestipulada", query = "SELECT p FROM Pedido p WHERE p.fechaentregaestipulada = :fechaentregaestipulada"),
    @NamedQuery(name = "Pedido.findByFechapedidocotizacion", query = "SELECT p FROM Pedido p WHERE p.fechapedidocotizacion = :fechapedidocotizacion"),
    @NamedQuery(name = "Pedido.findByFechacancelacion", query = "SELECT p FROM Pedido p WHERE p.fechacancelacion = :fechacancelacion"),
    @NamedQuery(name = "Pedido.findByFechaentregareal", query = "SELECT p FROM Pedido p WHERE p.fechaentregareal = :fechaentregareal"),
    @NamedQuery(name = "Pedido.findByFecharequeridacotizacion", query = "SELECT p FROM Pedido p WHERE p.fecharequeridacotizacion = :fecharequeridacotizacion"),
    @NamedQuery(name = "Pedido.findByMotivocancelacion", query = "SELECT p FROM Pedido p WHERE p.motivocancelacion = :motivocancelacion"),
    @NamedQuery(name = "Pedido.findByEspedidoweb", query = "SELECT p FROM Pedido p WHERE p.espedidoweb = :espedidoweb"),
    @NamedQuery(name = "Pedido.findByNropedidocotizacioncliente", query = "SELECT p FROM Pedido p WHERE p.nropedidocotizacioncliente = :nropedidocotizacioncliente"),
    @NamedQuery(name = "Pedido.findByFecharegpedcotiz", query = "SELECT p FROM Pedido p WHERE p.fecharegpedcotiz = :fecharegpedcotiz"),
    @NamedQuery(name = "Pedido.findByIdpedido", query = "SELECT p FROM Pedido p WHERE p.idpedido = :idpedido")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nropedido")
    private long nropedido;
    @Column(name = "fechaconfirmacionpedido")
    @Temporal(TemporalType.DATE)
    private Date fechaconfirmacionpedido;
    @Column(name = "fechaentregaestipulada")
    @Temporal(TemporalType.DATE)
    private Date fechaentregaestipulada;
    @Column(name = "fechapedidocotizacion")
    @Temporal(TemporalType.DATE)
    private Date fechapedidocotizacion;
    @Column(name = "fechacancelacion")
    @Temporal(TemporalType.DATE)
    private Date fechacancelacion;
    @Column(name = "fechaentregareal")
    @Temporal(TemporalType.DATE)
    private Date fechaentregareal;
    @Column(name = "fecharequeridacotizacion")
    @Temporal(TemporalType.DATE)
    private Date fecharequeridacotizacion;
    @Column(name = "motivocancelacion")
    private String motivocancelacion;
    @Column(name = "espedidoweb")
    private Boolean espedidoweb;
    @Column(name = "nropedidocotizacioncliente")
    private Integer nropedidocotizacioncliente;
    @Column(name = "fecharegpedcotiz")
    @Temporal(TemporalType.DATE)
    private Date fecharegpedcotiz;
    @Id
    @Basic(optional = false)
    @Column(name = "idpedido")
    private Long idpedido;
    @OneToMany(mappedBy = "pedido")
    private Set<Planificacionproduccion> planificacionproduccionSet;
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estadopedido estado;
    @JoinColumn(name = "factura", referencedColumnName = "idfactura")
    @ManyToOne
    private Factura factura;
    @JoinColumn(name = "plano", referencedColumnName = "idplano")
    @ManyToOne
    private Plano plano;
    @JoinColumn(name = "planprocedimientos", referencedColumnName = "idplanprocedimientos")
    @ManyToOne
    private Planprocedimientos planprocedimientos;
    @JoinColumn(name = "planprocesoscalidad", referencedColumnName = "idplanprocesoscalidad")
    @ManyToOne
    private Planprocesoscalidad planprocesoscalidad;
    @JoinColumn(name = "planrequerimientosmateriaprima", referencedColumnName = "idplanrequerimientosmateriaprima")
    @ManyToOne
    private Planrequerimientosmateriaprima planrequerimientosmateriaprima;
    @JoinColumn(name = "presupuesto", referencedColumnName = "idpresupuesto")
    @ManyToOne
    private Presupuesto presupuesto;
    @JoinColumn(name = "prioridad", referencedColumnName = "idprioridad")
    @ManyToOne(optional = false)
    private Prioridad prioridad;
    @OneToMany(mappedBy = "idpedido")
    private Set<Productoreal> productorealSet;
    @OneToMany(mappedBy = "pedido")
    private Set<Plano> planoSet;
    @OneToMany(mappedBy = "pedido")
    private Set<Trabajotercerizado> trabajotercerizadoSet;
    @OneToMany(mappedBy = "pedido")
    private Set<Planificacioncalidad> planificacioncalidadSet;
    @OneToMany(mappedBy = "pedido")
    private Set<Remito> remitoSet;
    @OneToMany(mappedBy = "idpedido")
    private Set<Detallefactura> detallefacturaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpedido")
    private Set<Detallepedido> detallepedidoSet;

    public Pedido() {
    }

    public Pedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public Pedido(Long idpedido, long nropedido) {
        this.idpedido = idpedido;
        this.nropedido = nropedido;
    }

    public long getNropedido() {
        return nropedido;
    }

    public void setNropedido(long nropedido) {
        this.nropedido = nropedido;
    }

    public Date getFechaconfirmacionpedido() {
        return fechaconfirmacionpedido;
    }

    public void setFechaconfirmacionpedido(Date fechaconfirmacionpedido) {
        this.fechaconfirmacionpedido = fechaconfirmacionpedido;
    }

    public Date getFechaentregaestipulada() {
        return fechaentregaestipulada;
    }

    public void setFechaentregaestipulada(Date fechaentregaestipulada) {
        this.fechaentregaestipulada = fechaentregaestipulada;
    }

    public Date getFechapedidocotizacion() {
        return fechapedidocotizacion;
    }

    public void setFechapedidocotizacion(Date fechapedidocotizacion) {
        this.fechapedidocotizacion = fechapedidocotizacion;
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

    public Date getFecharequeridacotizacion() {
        return fecharequeridacotizacion;
    }

    public void setFecharequeridacotizacion(Date fecharequeridacotizacion) {
        this.fecharequeridacotizacion = fecharequeridacotizacion;
    }

    public String getMotivocancelacion() {
        return motivocancelacion;
    }

    public void setMotivocancelacion(String motivocancelacion) {
        this.motivocancelacion = motivocancelacion;
    }

    public Boolean getEspedidoweb() {
        return espedidoweb;
    }

    public void setEspedidoweb(Boolean espedidoweb) {
        this.espedidoweb = espedidoweb;
    }

    public Integer getNropedidocotizacioncliente() {
        return nropedidocotizacioncliente;
    }

    public void setNropedidocotizacioncliente(Integer nropedidocotizacioncliente) {
        this.nropedidocotizacioncliente = nropedidocotizacioncliente;
    }

    public Date getFecharegpedcotiz() {
        return fecharegpedcotiz;
    }

    public void setFecharegpedcotiz(Date fecharegpedcotiz) {
        this.fecharegpedcotiz = fecharegpedcotiz;
    }

    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public Set<Planificacionproduccion> getPlanificacionproduccionSet() {
        return planificacionproduccionSet;
    }

    public void setPlanificacionproduccionSet(Set<Planificacionproduccion> planificacionproduccionSet) {
        this.planificacionproduccionSet = planificacionproduccionSet;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estadopedido getEstado() {
        return estado;
    }

    public void setEstado(Estadopedido estado) {
        this.estado = estado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public Planprocedimientos getPlanprocedimientos() {
        return planprocedimientos;
    }

    public void setPlanprocedimientos(Planprocedimientos planprocedimientos) {
        this.planprocedimientos = planprocedimientos;
    }

    public Planprocesoscalidad getPlanprocesoscalidad() {
        return planprocesoscalidad;
    }

    public void setPlanprocesoscalidad(Planprocesoscalidad planprocesoscalidad) {
        this.planprocesoscalidad = planprocesoscalidad;
    }

    public Planrequerimientosmateriaprima getPlanrequerimientosmateriaprima() {
        return planrequerimientosmateriaprima;
    }

    public void setPlanrequerimientosmateriaprima(Planrequerimientosmateriaprima planrequerimientosmateriaprima) {
        this.planrequerimientosmateriaprima = planrequerimientosmateriaprima;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Set<Productoreal> getProductorealSet() {
        return productorealSet;
    }

    public void setProductorealSet(Set<Productoreal> productorealSet) {
        this.productorealSet = productorealSet;
    }

    public Set<Plano> getPlanoSet() {
        return planoSet;
    }

    public void setPlanoSet(Set<Plano> planoSet) {
        this.planoSet = planoSet;
    }

    public Set<Trabajotercerizado> getTrabajotercerizadoSet() {
        return trabajotercerizadoSet;
    }

    public void setTrabajotercerizadoSet(Set<Trabajotercerizado> trabajotercerizadoSet) {
        this.trabajotercerizadoSet = trabajotercerizadoSet;
    }

    public Set<Planificacioncalidad> getPlanificacioncalidadSet() {
        return planificacioncalidadSet;
    }

    public void setPlanificacioncalidadSet(Set<Planificacioncalidad> planificacioncalidadSet) {
        this.planificacioncalidadSet = planificacioncalidadSet;
    }

    public Set<Remito> getRemitoSet() {
        return remitoSet;
    }

    public void setRemitoSet(Set<Remito> remitoSet) {
        this.remitoSet = remitoSet;
    }

    public Set<Detallefactura> getDetallefacturaSet() {
        return detallefacturaSet;
    }

    public void setDetallefacturaSet(Set<Detallefactura> detallefacturaSet) {
        this.detallefacturaSet = detallefacturaSet;
    }

    public Set<Detallepedido> getDetallepedidoSet() {
        return detallepedidoSet;
    }

    public void setDetallepedidoSet(Set<Detallepedido> detallepedidoSet) {
        this.detallepedidoSet = detallepedidoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pedido[idpedido=" + idpedido + "]";
    }

}
