package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Pedido generated by hbm2java
 */
public class Pedido  implements java.io.Serializable {


     private long idpedido;
     private Planprocesoscalidad planprocesoscalidad;
     private Presupuesto presupuesto;
     private Estadopedido estadopedido;
     private Plano plano;
     private Planprocedimientos planprocedimientos;
     private Prioridad prioridad;
     private Factura factura;
     private Planrequerimientosmateriaprima planrequerimientosmateriaprima;
     private Cliente cliente;
     private long nropedido;
     private Date fechaconfirmacionpedido;
     private Date fechaentregaestipulada;
     private Date fechapedidocotizacion;
     private Date fechacancelacion;
     private Date fechaentregareal;
     private Date fecharequeridacotizacion;
     private String motivocancelacion;
     private Boolean espedidoweb;
     private Integer nropedidocotizacioncliente;
     private Date fecharegpedcotiz;
     private Set<Productoreal> productoreals = new HashSet<Productoreal>(0);
     private Set<Planificacionproduccion> planificacionproduccions = new HashSet<Planificacionproduccion>(0);
     private Set<Remito> remitos = new HashSet<Remito>(0);
     private Set<Detallefactura> detallefacturas = new HashSet<Detallefactura>(0);
     private Set<Plano> planos = new HashSet<Plano>(0);
     private Set<Trabajotercerizado> trabajotercerizados = new HashSet<Trabajotercerizado>(0);
     private Set<Detallepedido> detallepedidos = new HashSet<Detallepedido>(0);
     private Set<Planificacioncalidad> planificacioncalidads = new HashSet<Planificacioncalidad>(0);

    public Pedido() {
    }

	
    public Pedido(long idpedido, Estadopedido estadopedido, Prioridad prioridad, long nropedido) {
        this.idpedido = idpedido;
        this.estadopedido = estadopedido;
        this.prioridad = prioridad;
        this.nropedido = nropedido;
    }
    public Pedido(long idpedido, Planprocesoscalidad planprocesoscalidad, Presupuesto presupuesto, Estadopedido estadopedido, Plano plano, Planprocedimientos planprocedimientos, Prioridad prioridad, Factura factura, Planrequerimientosmateriaprima planrequerimientosmateriaprima, Cliente cliente, long nropedido, Date fechaconfirmacionpedido, Date fechaentregaestipulada, Date fechapedidocotizacion, Date fechacancelacion, Date fechaentregareal, Date fecharequeridacotizacion, String motivocancelacion, Boolean espedidoweb, Integer nropedidocotizacioncliente, Date fecharegpedcotiz, Set<Productoreal> productoreals, Set<Planificacionproduccion> planificacionproduccions, Set<Remito> remitos, Set<Detallefactura> detallefacturas, Set<Plano> planos, Set<Trabajotercerizado> trabajotercerizados, Set<Detallepedido> detallepedidos, Set<Planificacioncalidad> planificacioncalidads) {
       this.idpedido = idpedido;
       this.planprocesoscalidad = planprocesoscalidad;
       this.presupuesto = presupuesto;
       this.estadopedido = estadopedido;
       this.plano = plano;
       this.planprocedimientos = planprocedimientos;
       this.prioridad = prioridad;
       this.factura = factura;
       this.planrequerimientosmateriaprima = planrequerimientosmateriaprima;
       this.cliente = cliente;
       this.nropedido = nropedido;
       this.fechaconfirmacionpedido = fechaconfirmacionpedido;
       this.fechaentregaestipulada = fechaentregaestipulada;
       this.fechapedidocotizacion = fechapedidocotizacion;
       this.fechacancelacion = fechacancelacion;
       this.fechaentregareal = fechaentregareal;
       this.fecharequeridacotizacion = fecharequeridacotizacion;
       this.motivocancelacion = motivocancelacion;
       this.espedidoweb = espedidoweb;
       this.nropedidocotizacioncliente = nropedidocotizacioncliente;
       this.fecharegpedcotiz = fecharegpedcotiz;
       this.productoreals = productoreals;
       this.planificacionproduccions = planificacionproduccions;
       this.remitos = remitos;
       this.detallefacturas = detallefacturas;
       this.planos = planos;
       this.trabajotercerizados = trabajotercerizados;
       this.detallepedidos = detallepedidos;
       this.planificacioncalidads = planificacioncalidads;
    }
   
    public long getIdpedido() {
        return this.idpedido;
    }
    
    public void setIdpedido(long idpedido) {
        this.idpedido = idpedido;
    }
    public Planprocesoscalidad getPlanprocesoscalidad() {
        return this.planprocesoscalidad;
    }
    
    public void setPlanprocesoscalidad(Planprocesoscalidad planprocesoscalidad) {
        this.planprocesoscalidad = planprocesoscalidad;
    }
    public Presupuesto getPresupuesto() {
        return this.presupuesto;
    }
    
    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
    public Estadopedido getEstadopedido() {
        return this.estadopedido;
    }
    
    public void setEstadopedido(Estadopedido estadopedido) {
        this.estadopedido = estadopedido;
    }
    public Plano getPlano() {
        return this.plano;
    }
    
    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    public Planprocedimientos getPlanprocedimientos() {
        return this.planprocedimientos;
    }
    
    public void setPlanprocedimientos(Planprocedimientos planprocedimientos) {
        this.planprocedimientos = planprocedimientos;
    }
    public Prioridad getPrioridad() {
        return this.prioridad;
    }
    
    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
    public Factura getFactura() {
        return this.factura;
    }
    
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public Planrequerimientosmateriaprima getPlanrequerimientosmateriaprima() {
        return this.planrequerimientosmateriaprima;
    }
    
    public void setPlanrequerimientosmateriaprima(Planrequerimientosmateriaprima planrequerimientosmateriaprima) {
        this.planrequerimientosmateriaprima = planrequerimientosmateriaprima;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public long getNropedido() {
        return this.nropedido;
    }
    
    public void setNropedido(long nropedido) {
        this.nropedido = nropedido;
    }
    public Date getFechaconfirmacionpedido() {
        return this.fechaconfirmacionpedido;
    }
    
    public void setFechaconfirmacionpedido(Date fechaconfirmacionpedido) {
        this.fechaconfirmacionpedido = fechaconfirmacionpedido;
    }
    public Date getFechaentregaestipulada() {
        return this.fechaentregaestipulada;
    }
    
    public void setFechaentregaestipulada(Date fechaentregaestipulada) {
        this.fechaentregaestipulada = fechaentregaestipulada;
    }
    public Date getFechapedidocotizacion() {
        return this.fechapedidocotizacion;
    }
    
    public void setFechapedidocotizacion(Date fechapedidocotizacion) {
        this.fechapedidocotizacion = fechapedidocotizacion;
    }
    public Date getFechacancelacion() {
        return this.fechacancelacion;
    }
    
    public void setFechacancelacion(Date fechacancelacion) {
        this.fechacancelacion = fechacancelacion;
    }
    public Date getFechaentregareal() {
        return this.fechaentregareal;
    }
    
    public void setFechaentregareal(Date fechaentregareal) {
        this.fechaentregareal = fechaentregareal;
    }
    public Date getFecharequeridacotizacion() {
        return this.fecharequeridacotizacion;
    }
    
    public void setFecharequeridacotizacion(Date fecharequeridacotizacion) {
        this.fecharequeridacotizacion = fecharequeridacotizacion;
    }
    public String getMotivocancelacion() {
        return this.motivocancelacion;
    }
    
    public void setMotivocancelacion(String motivocancelacion) {
        this.motivocancelacion = motivocancelacion;
    }
    public Boolean getEspedidoweb() {
        return this.espedidoweb;
    }
    
    public void setEspedidoweb(Boolean espedidoweb) {
        this.espedidoweb = espedidoweb;
    }
    public Integer getNropedidocotizacioncliente() {
        return this.nropedidocotizacioncliente;
    }
    
    public void setNropedidocotizacioncliente(Integer nropedidocotizacioncliente) {
        this.nropedidocotizacioncliente = nropedidocotizacioncliente;
    }
    public Date getFecharegpedcotiz() {
        return this.fecharegpedcotiz;
    }
    
    public void setFecharegpedcotiz(Date fecharegpedcotiz) {
        this.fecharegpedcotiz = fecharegpedcotiz;
    }
    public Set<Productoreal> getProductoreals() {
        return this.productoreals;
    }
    
    public void setProductoreals(Set<Productoreal> productoreals) {
        this.productoreals = productoreals;
    }
    public Set<Planificacionproduccion> getPlanificacionproduccions() {
        return this.planificacionproduccions;
    }
    
    public void setPlanificacionproduccions(Set<Planificacionproduccion> planificacionproduccions) {
        this.planificacionproduccions = planificacionproduccions;
    }
    public Set<Remito> getRemitos() {
        return this.remitos;
    }
    
    public void setRemitos(Set<Remito> remitos) {
        this.remitos = remitos;
    }
    public Set<Detallefactura> getDetallefacturas() {
        return this.detallefacturas;
    }
    
    public void setDetallefacturas(Set<Detallefactura> detallefacturas) {
        this.detallefacturas = detallefacturas;
    }
    public Set<Plano> getPlanos() {
        return this.planos;
    }
    
    public void setPlanos(Set<Plano> planos) {
        this.planos = planos;
    }
    public Set<Trabajotercerizado> getTrabajotercerizados() {
        return this.trabajotercerizados;
    }
    
    public void setTrabajotercerizados(Set<Trabajotercerizado> trabajotercerizados) {
        this.trabajotercerizados = trabajotercerizados;
    }
    public Set<Detallepedido> getDetallepedidos() {
        return this.detallepedidos;
    }
    
    public void setDetallepedidos(Set<Detallepedido> detallepedidos) {
        this.detallepedidos = detallepedidos;
    }
    public Set<Planificacioncalidad> getPlanificacioncalidads() {
        return this.planificacioncalidads;
    }
    
    public void setPlanificacioncalidads(Set<Planificacioncalidad> planificacioncalidads) {
        this.planificacioncalidads = planificacioncalidads;
    }




}


