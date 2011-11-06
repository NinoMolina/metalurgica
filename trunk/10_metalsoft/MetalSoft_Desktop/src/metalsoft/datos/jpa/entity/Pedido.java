/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.entity;

import java.io.Serializable;
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
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "espedidoweb")
    private Boolean espedidoweb;
    @Column(name = "nropedidocotizacioncliente")
    private Integer nropedidocotizacioncliente;
    @Column(name = "fecharegpedcotiz")
    @Temporal(TemporalType.DATE)
    private Date fecharegpedcotiz;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", sequenceName = "pedido_idpedido_seq", allocationSize = 1)
    @Column(name = "idpedido")
    private Long idpedido;
    @OneToMany(mappedBy = "pedido")
    private List<Planificacionproduccion> planificacionproduccionList;
    @JoinColumn(name = "prioridad", referencedColumnName = "idprioridad")
    @ManyToOne(optional = false)
    private Prioridad prioridad;
    @JoinColumn(name = "presupuesto", referencedColumnName = "idpresupuesto")
    @ManyToOne
    private Presupuesto presupuesto;
    @JoinColumn(name = "planrequerimientosmateriaprima", referencedColumnName = "idplanrequerimientosmateriaprima")
    @ManyToOne
    private Planrequerimientosmateriaprima planrequerimientosmateriaprima;
    @JoinColumn(name = "planprocesoscalidad", referencedColumnName = "idplanprocesoscalidad")
    @ManyToOne
    private Planprocesoscalidad planprocesoscalidad;
    @JoinColumn(name = "planprocedimientos", referencedColumnName = "idplanprocedimientos")
    @ManyToOne
    private Planprocedimientos planprocedimientos;
    @JoinColumn(name = "factura", referencedColumnName = "idfactura")
    @ManyToOne
    private Factura factura;
    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estadopedido estado;
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "idpedido")
    private List<Productoreal> productorealList;
    @OneToMany(mappedBy = "pedido")
    private List<Plano> planoList;
    @OneToMany(mappedBy = "pedido")
    private List<Trabajotercerizado> trabajotercerizadoList;
    @OneToMany(mappedBy = "pedido")
    private List<Planificacioncalidad> planificacioncalidadList;
    @OneToMany(mappedBy = "pedido")
    private List<Remito> remitoList;
    @OneToMany(mappedBy = "idpedido")
    private List<Detallefactura> detallefacturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpedido")
    private List<Detallepedido> detallepedidoList;

    public Pedido() {
    }

    public Pedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public Pedido(Long idpedido, long nropedido) {
        this.idpedido = idpedido;
        this.nropedido = nropedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public List<Planificacionproduccion> getPlanificacionproduccionList() {
        return planificacionproduccionList;
    }

    public void setPlanificacionproduccionList(List<Planificacionproduccion> planificacionproduccionList) {
        this.planificacionproduccionList = planificacionproduccionList;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Planrequerimientosmateriaprima getPlanrequerimientosmateriaprima() {
        return planrequerimientosmateriaprima;
    }

    public void setPlanrequerimientosmateriaprima(Planrequerimientosmateriaprima planrequerimientosmateriaprima) {
        this.planrequerimientosmateriaprima = planrequerimientosmateriaprima;
    }

    public Planprocesoscalidad getPlanprocesoscalidad() {
        return planprocesoscalidad;
    }

    public void setPlanprocesoscalidad(Planprocesoscalidad planprocesoscalidad) {
        this.planprocesoscalidad = planprocesoscalidad;
    }

    public Planprocedimientos getPlanprocedimientos() {
        return planprocedimientos;
    }

    public void setPlanprocedimientos(Planprocedimientos planprocedimientos) {
        this.planprocedimientos = planprocedimientos;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Estadopedido getEstado() {
        return estado;
    }

    public void setEstado(Estadopedido estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Productoreal> getProductorealList() {
        return productorealList;
    }

    public void setProductorealList(List<Productoreal> productorealList) {
        this.productorealList = productorealList;
    }

    public List<Plano> getPlanoList() {
        return planoList;
    }

    public void setPlanoList(List<Plano> planoList) {
        this.planoList = planoList;
    }

    public List<Trabajotercerizado> getTrabajotercerizadoList() {
        return trabajotercerizadoList;
    }

    public void setTrabajotercerizadoList(List<Trabajotercerizado> trabajotercerizadoList) {
        this.trabajotercerizadoList = trabajotercerizadoList;
    }

    public List<Planificacioncalidad> getPlanificacioncalidadList() {
        return planificacioncalidadList;
    }

    public void setPlanificacioncalidadList(List<Planificacioncalidad> planificacioncalidadList) {
        this.planificacioncalidadList = planificacioncalidadList;
    }

    public List<Remito> getRemitoList() {
        return remitoList;
    }

    public void setRemitoList(List<Remito> remitoList) {
        this.remitoList = remitoList;
    }

    public List<Detallefactura> getDetallefacturaList() {
        return detallefacturaList;
    }

    public void setDetallefacturaList(List<Detallefactura> detallefacturaList) {
        this.detallefacturaList = detallefacturaList;
    }

    public List<Detallepedido> getDetallepedidoList() {
        return detallepedidoList;
    }

    public void setDetallepedidoList(List<Detallepedido> detallepedidoList) {
        this.detallepedidoList = detallepedidoList;
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
        return "metalsoft.datos.jpa.entity.Pedido[ idpedido=" + idpedido + " ]";
    }
}
