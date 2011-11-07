//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\metalsoft\\negocio\\ventas\\Pedido.java

package metalsoft.negocio.ventas;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import metalsoft.datos.dbobject.DetallepedidoDB;
import metalsoft.negocio.access.AccessDetallePedido;
import metalsoft.negocio.access.AccessDetalleProducto;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.trabajostercerizados.TrabajoTercerizado;


public class Pedido 
{
   private int nroPedido;
   private Date fechaConfirmacionPedido;
   private Date fechaEntregaEstipulada;
   private Date fechaPedidoCotizacion;
   private Date fechaCancelacion;
   private TrabajoTercerizado trabajoTercerizado;
   private Remito remito;
   private Date fechaEntregaReal;
   private Prioridad prioridad;
   private EstadoPedido estado;
   private Factura factura;
   private ArrayList<DetallePedido> detalle;
   private Presupuesto presupuesto;
   private Date fechaRequeridaCotizacion;
   //private Plano plano;
   private String motivoCancelacion;
   private Boolean esPedidoWeb;
   private int nroPedCotizCliente;
   private Date fechaRegistroPedidoCotizacion;

   
   /**
    * @roseuid 4C20555303BD
    */
   public Pedido() 
   {
    
   }

    public ArrayList<DetallePedido> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<DetallePedido> detalle) {
        this.detalle = detalle;
    }

    public Boolean getEsPedidoWeb() {
        return esPedidoWeb;
    }

    public void setEsPedidoWeb(Boolean esPedidoWeb) {
        this.esPedidoWeb = esPedidoWeb;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Date getFechaConfirmacionPedido() {
        return fechaConfirmacionPedido;
    }

    public void setFechaConfirmacionPedido(Date fechaConfirmacionPedido) {
        this.fechaConfirmacionPedido = fechaConfirmacionPedido;
    }

    public Date getFechaEntregaEstipulada() {
        return fechaEntregaEstipulada;
    }

    public void setFechaEntregaEstipulada(Date fechaEntregaEstipulada) {
        this.fechaEntregaEstipulada = fechaEntregaEstipulada;
    }

    public Date getFechaEntregaReal() {
        return fechaEntregaReal;
    }

    public void setFechaEntregaReal(Date fechaEntregaReal) {
        this.fechaEntregaReal = fechaEntregaReal;
    }

    public Date getFechaPedidoCotizacion() {
        return fechaPedidoCotizacion;
    }

    public void setFechaPedidoCotizacion(Date fechaPedidoCotizacion) {
        this.fechaPedidoCotizacion = fechaPedidoCotizacion;
    }

    public Date getFechaRegistroPedidoCotizacion() {
        return fechaRegistroPedidoCotizacion;
    }

    public void setFechaRegistroPedidoCotizacion(Date fechaRegistroPedidoCotizacion) {
        this.fechaRegistroPedidoCotizacion = fechaRegistroPedidoCotizacion;
    }

    public Date getFechaRequeridaCotizacion() {
        return fechaRequeridaCotizacion;
    }

    public void setFechaRequeridaCotizacion(Date fechaRequeridaCotizacion) {
        this.fechaRequeridaCotizacion = fechaRequeridaCotizacion;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public int getNroPedCotizCliente() {
        return nroPedCotizCliente;
    }

    public void setNroPedCotizCliente(int nroPedCotizCliente) {
        this.nroPedCotizCliente = nroPedCotizCliente;
    }

    public int getNroPedido() {
        return nroPedido;
    }

    public void setNroPedido(int nroPedido) {
        this.nroPedido = nroPedido;
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

    public Remito getRemito() {
        return remito;
    }

    public void setRemito(Remito remito) {
        this.remito = remito;
    }

    public TrabajoTercerizado getTrabajoTercerizado() {
        return trabajoTercerizado;
    }

    public void setTrabajoTercerizado(TrabajoTercerizado trabajoTercerizado) {
        this.trabajoTercerizado = trabajoTercerizado;
    }
   
   /**
    * @roseuid 4BC2618C0292
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC2618F025E
    */
   public void conocerFactura() 
   {
    
   }
   
   /**
    * @roseuid 4BC2619402A4
    */
   public void conocerDetallePedido() 
   {
    
   }
   
   /**
    * @roseuid 4BC2619A0337
    */
   public void conocerCliente() 
   {
    
   }
   
   /**
    * @roseuid 4BC2619F016A
    */
   public void conocerPresupuesto() 
   {
    
   }
   
   /**
    * @roseuid 4BE5D05801E8
    */
   public void calcularMontoTotal() 
   {
    
   }
   
   /**
    * @roseuid 4C1806F10046
    */
   public void conocerTrabajoTercerizado() 
   {
    
   }
   
   /**
    * @roseuid 4C1806F90034
    */
   public void conocerRemito() 
   {
    
   }
   
   /**
    * @roseuid 4C1807070279
    */
   public void conocerEstadoPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C18070F020C
    */
   public void conocerPlano() 
   {
    
   }
   
   /**
    * @roseuid 4C1FDA64018C
    */
   public void mostrarNumeroPlano() 
   {
    
   }
   
   /**
    * @roseuid 4C1808F203AB
    */
   public void conocerPlanificacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1F961D036E
    */
   public void esPedidoGeneradoYNoPresupuestado() 
   {
    
   }
   
   /**
    * @roseuid 4C1F965902C0
    */
   public void mostrarPlanRequerimiento() 
   {
    
   }
   
   /**
    * @roseuid 4C1FA32E02ED
    */
   public void esPedidoPresupuestado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FA4EC03CC
    */
   public void esPedidoConfirmado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FA64A01BC
    */
   public void esPedidoConfirmadoYNoCancelado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FA66003DA
    */
   public void mostrarDatosPresupuesto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FB8A50393
    */
   public void mostrarProductos() 
   {
    
   }
   
   /**
    * @roseuid 4C1FBED30232
    */
   public DetallePedido crearDetallePedido(int cantidad,float precio)
   {
        DetallePedido dp=new DetallePedido();
        dp.setCantidad(cantidad);
        dp.setPrecio(precio);
        return dp;
   }
   
   /**
    * @roseuid 4C1FC0320126
    */
   public static Presupuesto crearPresupuesto()
   {
        return new Presupuesto();
   }

    public long guardar(Pedido p,long idCliente, long idEstado, long idPrioridad, LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido, Connection cn) {
        //coleccion de detalles del producto
        //ArrayList arlDetalle=p.getDetalle();
        //iterador que recorre la coleccion de detalle
        Iterator<ViewDetallePedidoCotizacion> iter=filasDetallePedido.iterator();
        ViewDetallePedidoCotizacion view=null;
        //iterador que recorre los ids de las piezas que forman parte del detalle
        //Iterator iterIds=arlIdsPiezasDetalleProducto.iterator();
        long idPed=-1;
        //inserto el producto
        idPed=AccessPedido.insert(p,idCliente,idEstado,idPrioridad,cn);
        DetallePedido x=null;
        //recorro los detalles del producto y los guardo
        while(iter.hasNext())
        {
            view=iter.next();

            x = new DetallePedido();
            x.setCantidad(view.getCantidad());
            x.setPrecio(view.getPrecio());
            long idProd=Long.parseLong(String.valueOf(view.getIdProducto()));
            AccessDetallePedido.insert(x,idPed,idProd,cn);
        }
        return idPed;
    }
}