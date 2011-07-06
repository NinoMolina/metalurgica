//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\compras\\Compra.java

package metalsoft.negocio.compras;

import java.util.Date;
import metalsoft.datos.dbobject.EstadocompraDB;


public class Compra 
{
   private int nroCompra;
   private int idCompra;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
   private Date fechaCompra;
   private Date fechaRecepcion;
   private Date vigencia;
   private int documentoRemito;
   private int precioTotal;
   private EstadocompraDB estado;
   private int motivoCancelacion;
   private DetalleCompra detalle;
   private Proveedor proveedor;
   private Reclamo reclamo;
   private int cancelacion;
   public Proveedor theProveedor;
   public EstadoCompra theEstadoCompra;
   public Reclamo theReclamo[];
   public DetalleCompra theDetalleCompra[];

    public int getCancelacion() {
        return cancelacion;
    }

    public void setCancelacion(int cancelacion) {
        this.cancelacion = cancelacion;
    }

    public DetalleCompra getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleCompra detalle) {
        this.detalle = detalle;
    }

    public int getDocumentoRemito() {
        return documentoRemito;
    }

    public void setDocumentoRemito(int documentoRemito) {
        this.documentoRemito = documentoRemito;
    }

    public EstadocompraDB getEstado() {
        return estado;
    }

    public void setEstado(EstadocompraDB estado) {
        this.estado = estado;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public int getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(int motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public int getNroCompra() {
        return nroCompra;
    }

    public void setNroCompra(int nroCompra) {
        this.nroCompra = nroCompra;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }
   
   /**
    * @roseuid 4C27ED1701A0
    */
   public Compra() 
   {
    
   }
   
   /**
    * @roseuid 4BC24E8900BC
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC24E8C0079
    */
   public void conocerEstadoCompra() 
   {
    
   }
   
   /**
    * @roseuid 4BE5F56B019E
    */
   public void calcularMontoTotal() 
   {
    
   }
   
   /**
    * @roseuid 4C17F113039A
    */
   public void conocerProveedor() 
   {
    
   }
   
   /**
    * @roseuid 4C17F1EC0053
    */
   public void conocerReclamo() 
   {
    
   }
   
   /**
    * @roseuid 4C17FD900379
    */
   public void conocerDetalleCompra() 
   {
    
   }
}
