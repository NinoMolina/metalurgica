/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarRecepcionPedidoCombustible;

import java.util.Date;
//import pkgCasosDeUso.pkgCompras.RegistrarRecepcionPedidoCombustibles.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkgClasesDeNegocio.AdministracionDePersonal.Empleado;
import pkgClasesDeNegocio.AdministracionDePersonal.Estado;
import pkgClasesDeNegocio.Compras.DetallePedidoAProveedor;
import pkgClasesDeNegocio.Compras.DetalleRecepcion;
import pkgClasesDeNegocio.Compras.FacturaProveedor;
import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.Compras.Recepcion;
import pkgClasesDeNegocio.Compras.Remito;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.Ventas.TipoFactura;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;


/**
 *
 * @author Fer
 */
public class GestorRegistarRecepcionPedidoCombustible {
private PllaRecepcionPedidoCombustible pllaRecepcionPedidoCombustible ;
private PllaModificarCantidades pllaModificarCantidades;
private PllaModificarEstadosDetalle pllaModificarEstadosDetalle;
private IntermediariBDRegistrarRecepcionPedidoCombustible intermediariBDRegistrarRecepcionPedidoCombustible=new IntermediariBDRegistrarRecepcionPedidoCombustible();    
private Lista lstResponsables=new Lista();
private Lista lstProveedoresCombustible;
private Lista lstDetallePedidoAProveedor;
private Lista lstPedidosAProveedor;
private Lista lstDetalleRecepcion;
private Lista lstRemito =new Lista();
private Lista lstTipoFactura=new Lista();
private Date fechaRecepcion=new Date();
private Recepcion recepcion=new Recepcion();
private FacturaProveedor facturaProveedor=null;
private String formaPago=new String();
private String horaRecepcion=new String();
private Proveedor proveedorSeleccionado;
private Empleado responsableSeleccionado;
private PedidoAProveedor pedidoAProveedorSeleccionado;
private Lista lstEstadosPedidos;
    public GestorRegistarRecepcionPedidoCombustible() {
    }
    
     public   void nuevaRecepcionPedidoCombustible(PllaRecepcionPedidoCombustible plla) {
        try {
                this.pllaRecepcionPedidoCombustible=plla;
                this.buscarResponsables();
                pllaRecepcionPedidoCombustible.mostrarResponsables(lstResponsables);   
                this.buscarProveedorCombustible();   
                pllaRecepcionPedidoCombustible.mostrarProveedoresCombustible(lstProveedoresCombustible);
                this.buscarTipoFactura();
             }
          catch(Exception e)
    {
     pllaRecepcionPedidoCombustible.mostrarMensajePantalla("Error al cargar la pantalla", Mensaje.TIPO_ERROR);
    }
    }
    
    private void buscarTipoFactura() throws Exception
    {
    lstTipoFactura=intermediariBDRegistrarRecepcionPedidoCombustible.buscarTipoFactura();
    }

    public void tomarProveedor(String razonSocial) {
        proveedorSeleccionado=new Proveedor();
        proveedorSeleccionado.setRazonSocial(razonSocial);
        this.buscarPedidoCombustiblePendienteRecepcionOParcialmenteRecibidoProveedor(proveedorSeleccionado);
     }

    public void tomarPedido(int id_Pedido) {
        try
        {
             pedidoAProveedorSeleccionado=new PedidoAProveedor();
             pedidoAProveedorSeleccionado.setNumeroPedido(id_Pedido);
           Iterador itPedidosAProveedor= lstPedidosAProveedor.crearIterador();
           while(itPedidosAProveedor.siguiente())
           {
           PedidoAProveedor aux=(PedidoAProveedor) itPedidosAProveedor.getElementoActual();
           if(aux.compareTo(pedidoAProveedorSeleccionado)==0)
           {
           pedidoAProveedorSeleccionado=aux;
           pedidoAProveedorSeleccionado.addAttribute("Id_PedidoAProveedor", id_Pedido);
           }
           
           }
              
               this.buscarDetallePedidoSeleccionado(pedidoAProveedorSeleccionado);
        }
        catch(Exception e)
        {
        pllaRecepcionPedidoCombustible.mostrarMensajePantalla("Error al intentar mostrar el detalle del pedido seleccionado", Mensaje.TIPO_ERROR);
        
        }
    }

    public  void tomarFechaHoraRecepcion(Date Fecha,String horaRecepcion) {
        fechaRecepcion=Fecha;
        
        recepcion.setFechaRealRecepcion(fechaRecepcion);
        
        this.horaRecepcion=horaRecepcion;
        
        recepcion.setHoraRealRecepcion(horaRecepcion);
    }

    void tomarConfirmacion(String respuesta) {
      if(respuesta.compareTo("Si")==0)
      {
      this.crearRecepcion();
      }
      else
      {
      
      
      }
    }

    void tomarFormaPago(String formaPago) {
       this.formaPago=formaPago;
    }

    void tomarNumeroFacturaRecibido(int numero,Date fecha,Double montoTotal,int fk_TipoFactura) {
       facturaProveedor=new FacturaProveedor();
       facturaProveedor.setNumero(numero);
       facturaProveedor.setFecha(fecha);
       facturaProveedor.setMontoTotal(montoTotal);
       facturaProveedor.addAttribute("fk_TipoFactura", fk_TipoFactura);
      facturaProveedor.addAttribute("fk_PedidoAProveedor", pedidoAProveedorSeleccionado.getAttribute("id_PedidoAProveedor"));
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    void tomarNumeroRemitoRecibido(String producto, int numeroRemito) {
         Iterador itDetalleModificado=lstDetalleRecepcion.crearIterador();
  
    while(itDetalleModificado.siguiente())
    {
        
        DetalleRecepcion detalleRecepcion=(DetalleRecepcion) itDetalleModificado.getElementoActual();
        if(detalleRecepcion.getProducto().getNombre().compareTo(producto)==0)
        {
            Remito remito=new Remito(numeroRemito,detalleRecepcion);
        lstRemito.insertarOrdenado(remito);
        }
        
    }
  recepcion.setRemito(lstRemito);

    }

    private void calcularMontoTotalPedido() {
       Iterador aux=lstDetalleRecepcion.crearIterador();
       double acumulador=0;
       while(aux.siguiente())
       {
       DetalleRecepcion detalleRecepcion=(DetalleRecepcion) aux.getElementoActual();
       acumulador+=detalleRecepcion.getPrecioUnitario()*detalleRecepcion.getCantidadRecibida();
             
       }
       recepcion.setMontoTotal(acumulador);
       
        
    }

    private void crearRecepcion() {
       try
       {
          this.calcularMontoTotalPedido();
         intermediariBDRegistrarRecepcionPedidoCombustible.crearRecepcion(recepcion,facturaProveedor,pedidoAProveedorSeleccionado);
       
       
       }
       catch(Exception e)
       {
       }
    }
private  void preCrearRecepcion()
{
recepcion.addAttribute("fk_PedidoAProveedor", pedidoAProveedorSeleccionado.getAttribute("id_PedidoAProveedor"));
lstDetalleRecepcion=new Lista();
Iterador itAuxDetallePedidoAProveedor=lstDetallePedidoAProveedor.crearIterador();

while(itAuxDetallePedidoAProveedor.siguiente())
{
    DetallePedidoAProveedor detallePedidoAProveedor=(DetallePedidoAProveedor) itAuxDetallePedidoAProveedor.getElementoActual();
    DetalleRecepcion detalleRecepcion=new DetalleRecepcion();

    detalleRecepcion.setEmpleado(responsableSeleccionado);
    detalleRecepcion.addAttribute("fk_Empleado", responsableSeleccionado.getAttribute("id_Empleado"));
    detalleRecepcion.setFechaRecepcion(fechaRecepcion);
    detalleRecepcion.setHoraRealRecepcion(horaRecepcion);
    detalleRecepcion.setDetallePedido(detallePedidoAProveedor);
    detalleRecepcion.addAttribute("fk_DetallePedidoAProveedor", detallePedidoAProveedor.getAttribute("id_DetallePedidoAProveedor"));
    detalleRecepcion.setPrecioUnitario(detallePedidoAProveedor.getProducto().getPrecioCosto());
    detalleRecepcion.setProducto(detallePedidoAProveedor.getProducto());
    detalleRecepcion.addAttribute("fk_Producto",detallePedidoAProveedor.getAttribute("fk_Producto") );
    
lstDetalleRecepcion.insertarOrdenado(detalleRecepcion);
}
recepcion.setDetallesRecepcion(lstDetalleRecepcion);

}
    public void tomarCantidadProductoRecibido(String producto, Double cantidad) {
    Iterador itDetalleModificado=lstDetalleRecepcion.crearIterador();
   Lista auxiliar=new Lista();
    while(itDetalleModificado.siguiente())
    {
        
        DetalleRecepcion detalleRecepcion=(DetalleRecepcion) itDetalleModificado.getElementoActual();
        if(detalleRecepcion.getProducto().getNombre().compareTo(producto)==0)
        {
            detalleRecepcion.setCantidadRecibida(cantidad);
        }
        auxiliar.insertarOrdenado(detalleRecepcion);
    }
  
  lstDetalleRecepcion=auxiliar;
    recepcion.setDetallesRecepcion(lstDetalleRecepcion);
    
    }
    
    
    


public    void tomarResponsableRecibirPedido(String responsable) {
        responsableSeleccionado=new Empleado();
        Iterador aux=lstResponsables.crearIterador();
        while(aux.siguiente())
        {
        Empleado auxEmpleado=(Empleado) aux.getElementoActual();
        if(auxEmpleado.getNombre().compareTo(responsable)==0)
        {
         responsableSeleccionado=auxEmpleado;
         return;
        }
        }
       
}

    void tomarEstadoRecepcionPedidoActual(String estadoRecepcionPedidoActual) {
       
                   try
                       { 
                             lstEstadosPedidos = intermediariBDRegistrarRecepcionPedidoCombustible.buscarEstadosPorAmbito("Pedido a proveedor");
                       } 
                   catch (Exception ex) 
                        {
                            Mensaje.mensaje_Estandar("Error en la carga de estados");
                        }   

        if (estadoRecepcionPedidoActual.compareTo("Recibido total")==0)
        {
             Estado estado = new Estado(estadoRecepcionPedidoActual, "");
                Iterador itEstadosPedidos=lstEstadosPedidos.crearIterador();
                 while(itEstadosPedidos.siguiente())
                 {
                     Estado aux=(Estado) itEstadosPedidos.getElementoActual();
                      if(aux.compareTo(estado)==0)
                         {
                             estado=aux;
                         }
                }
                  
                pedidoAProveedorSeleccionado.setEstado(estado);
                pedidoAProveedorSeleccionado.addAttribute("fk_Estado", estado.getAttribute("id_Estado"));
                pllaModificarCantidades = new PllaModificarCantidades(this);

                pllaModificarCantidades.abrirVentana(lstDetallePedidoAProveedor, proveedorSeleccionado, lstTipoFactura);
                this.preCrearRecepcion();
           
        }
        else 
        {
            if(estadoRecepcionPedidoActual.compareTo("Recibido Parcial")==0)
            {
                 Estado estado=new Estado(estadoRecepcionPedidoActual,"");
                 pedidoAProveedorSeleccionado.setEstado(estado);
                pllaModificarEstadosDetalle=new PllaModificarEstadosDetalle(this);
                pllaModificarEstadosDetalle.setVisible(true);
            }
        }
    }

    private void buscarDetallePedidoSeleccionado(PedidoAProveedor pedidoAProveedor) throws Exception {
   
            lstDetallePedidoAProveedor= intermediariBDRegistrarRecepcionPedidoCombustible.buscarDetallePedidoSeleccionado(pedidoAProveedor);
             pllaRecepcionPedidoCombustible.mostrarDetallePedidoSeleccionado(lstDetallePedidoAProveedor);
         
       
    
     
    }

    private void buscarPedidoCombustiblePendienteRecepcionOParcialmenteRecibidoProveedor(Proveedor proveedor) {
       
       try
       {        lstPedidosAProveedor=intermediariBDRegistrarRecepcionPedidoCombustible.buscarPedidoCombustiblePendiente(proveedor);
                pllaRecepcionPedidoCombustible.mostrarDatosPedido(lstPedidosAProveedor);
       }
       catch(Exception e)
       {}

    }

    private void buscarProveedorCombustible() throws Exception {
       
              lstProveedoresCombustible=intermediariBDRegistrarRecepcionPedidoCombustible.buscarProveedoresCombustible();
        
        
    }

    private void buscarResponsables() throws Exception {
   
        lstResponsables=intermediariBDRegistrarRecepcionPedidoCombustible.buscarResponsables();
        
   
  
     
    
    }
    



}
