/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarRecepcionPedidoCombustible;
import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.*;
import pkgCapaDeDatos.pkgDAOCompras.DAO_Combustible;
import pkgCapaDeDatos.pkgDAOCompras.DAO_DetallePedidoAProveedor;
import pkgCapaDeDatos.pkgDAOCompras.DAO_DetalleRecepcion;
import pkgCapaDeDatos.pkgDAOCompras.DAO_FacturaProveedor;
import pkgCapaDeDatos.pkgDAOCompras.DAO_PedidoAProveedor;
import pkgCapaDeDatos.pkgDAOCompras.DAO_Producto;
import pkgCapaDeDatos.pkgDAOCompras.DAO_ProductoDePlaya;
import pkgCapaDeDatos.pkgDAOCompras.DAO_Recepcion;
import pkgCapaDeDatos.pkgDAOCompras.DAO_Remito;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_Proveedor;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_TipoProveedor;
import pkgCapaDeDatos.pkgDAOVentas.DAO_TipoFactura;
import pkgClasesDeNegocio.AdministracionDePersonal.Estado;

import pkgClasesDeNegocio.Compras.AmbitoEstado;
import pkgClasesDeNegocio.Compras.DetallePedidoAProveedor;
import pkgClasesDeNegocio.Compras.DetalleRecepcion;
import pkgClasesDeNegocio.Compras.FacturaProveedor;
import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.Compras.Recepcion;
import pkgClasesDeNegocio.Compras.Remito;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;
/**
 *
 * @author Fer
 */
class IntermediariBDRegistrarRecepcionPedidoCombustible {
private DAO_Empleado daoEmpleado=new DAO_Empleado();
private DAO_Proveedor daoProveedor=new DAO_Proveedor();  
private DAO_TipoProveedor daoTipoProveedor=new DAO_TipoProveedor();
private DAO_Estado daoEstado=new DAO_Estado();
private DAO_AmbitoEstado daoAmbitoEstado=new DAO_AmbitoEstado();
private DAO_PedidoAProveedor daoPedidoAProveedor=new DAO_PedidoAProveedor();
private DAO_DetallePedidoAProveedor daoDetallePedidoAProveedor=new DAO_DetallePedidoAProveedor();
private DAO_Producto  daoProducto=new DAO_Producto();
private DAO_DetalleRecepcion daoDetalleRecepcion=new DAO_DetalleRecepcion();
private DAO_Recepcion daoRecepcion=new DAO_Recepcion();
private DAO_FacturaProveedor daoFacturaProveedor=new DAO_FacturaProveedor();
private DAO_TipoFactura daoTipoFactura=new DAO_TipoFactura();
private DAO_Remito daoRemito=new DAO_Remito();
public Lista buscarResponsables() throws Exception {
     
        return daoEmpleado.materializarTodo(""); 
   
      
    }

    public Lista buscarProveedoresCombustible() throws Exception {
      TipoProveedor tipoProveedor=new TipoProveedor();
        Lista lstTipoProveedor;
        lstTipoProveedor= daoTipoProveedor.materializarTodo("where nombre='Proveedor de combustible'");
        Iterador itTipoProveedor=lstTipoProveedor.crearIterador();
        while(itTipoProveedor.siguiente())
        {
           tipoProveedor=(TipoProveedor)itTipoProveedor.getElementoActual();
        }
        return daoProveedor.materializarPorTipoProveedor(tipoProveedor);
    }

    public Lista buscarPedidoCombustiblePendiente(Proveedor proveedor) throws Exception {
       Lista lstAmbitoEstado=new Lista();
       lstAmbitoEstado=daoAmbitoEstado.materializarTodo("where nombre='Pedido a proveedor'");
       Iterador itAmbitoEstado=lstAmbitoEstado.crearIterador();
      AmbitoEstado ambitoEstado=new AmbitoEstado();
       while(itAmbitoEstado.siguiente())
       {
     ambitoEstado=(AmbitoEstado)itAmbitoEstado.getElementoActual();
       }
       
       
       Lista lstEstados=new Lista();
       lstEstados=daoEstado.materializarTodo("where (nombre='Recibido parcial' or nombre='Pendiente de Recepcion') and fk_ambitoestado="+ValText.getInt_Integer(ambitoEstado.getAttribute("id_AmbitoEstado")));
      Iterador itEstado=lstEstados.crearIterador();
       Estado estado=new Estado(); 
       
       Lista lstPedidos=new Lista();
         int aux1;
         int aux2;
       
         itEstado.siguiente();
         estado=(Estado)itEstado.getElementoActual();
         aux1=ValText.getInt_Integer(estado.getAttribute("id_Estado"));
         
         itEstado.siguiente();
         estado=(Estado)itEstado.getElementoActual();
         aux2=ValText.getInt_Integer(estado.getAttribute("id_Estado"));
       
       
            Lista lstProveedor=daoProveedor.materializarTodo(" where razonsocial='"+proveedor.getRazonSocial().toString()+"'" );
            Iterador itProveedor=lstProveedor.crearIterador();
    
         while(itProveedor.siguiente())
                    {
                     Proveedor p =(Proveedor)itProveedor.getElementoActual();
                     proveedor.addAttribute("id_Proveedor", p.getAttribute("id_Proveedor"));
                     }
       lstPedidos=daoPedidoAProveedor.materializarTodo(" where (fk_estado="+aux1+" or fk_estado="+aux2+") and fk_proveedor="+ValText.getInt_Integer(proveedor.getAttribute("id_Proveedor")));
       
       
       
     
       
       //ver como hacerlo mejor!!!!!!!!!!!!!!!!!!!!
        
        return lstPedidos;
    }

    public Lista buscarDetallePedidoSeleccionado(PedidoAProveedor pedidoAProveedor) throws Exception {
       Lista lstDetallePedido=new Lista();
       Lista nuevaLstDetallePedido=new Lista();
       lstDetallePedido=daoDetallePedidoAProveedor.materializarTodo(" where fk_pedidoaproveedor="+ValText.getInt_Integer(pedidoAProveedor.getAttribute("id_PedidoAProveedor")));
       
       Iterador itDetallePedidoAProveedor=lstDetallePedido.crearIterador();
       while(itDetallePedidoAProveedor.siguiente())
       {
               DetallePedidoAProveedor detallePedidoAProveedor=(DetallePedidoAProveedor) itDetallePedidoAProveedor.getElementoActual();
               detallePedidoAProveedor.setProducto(daoProducto.materializarProducto(" where id_Producto="+ValText.getInt_Integer(detallePedidoAProveedor.getAttribute("fk_Producto"))));
               detallePedidoAProveedor.setEstado(daoEstado.materializar(" where id_estado="+ValText.getInt_Integer(detallePedidoAProveedor.getAttribute("fk_Estado"))));     
               
               Lista existenRecepcionesAnteriores=daoDetalleRecepcion.materializarTodo(" where fk_detallepedidoaproveedor="+ValText.getInt_Integer(detallePedidoAProveedor.getAttribute("id_DetallePedidoAProveedor")));
               Iterador itExistenRecepcionesAnteriores=existenRecepcionesAnteriores.crearIterador();
               double acumuladorCantidad=0;
               while(itExistenRecepcionesAnteriores.siguiente())
               {
               DetalleRecepcion detalleRecepcion=(DetalleRecepcion) itExistenRecepcionesAnteriores.getElementoActual();
               acumuladorCantidad+=detalleRecepcion.getCantidadRecibida();
               }
               double cantidadPorRecibir=detallePedidoAProveedor.getCantidad()-acumuladorCantidad;
               detallePedidoAProveedor.addAttribute("cantidadPorRecibir",cantidadPorRecibir );
               nuevaLstDetallePedido.insertarOrdenado(detallePedidoAProveedor);
       }
        
       
       
       
        return nuevaLstDetallePedido;
    }

    public  void crearRecepcion(Recepcion recepcion,FacturaProveedor facturaProveedor,PedidoAProveedor pedidoAProveedorSeleccionado) throws Exception {
      
        if(facturaProveedor==null)
            {
           
                    recepcion.addAttribute("fk_FacturaProveedor", null);
            }   
        else
            {
                    int id_Factura=this.crearFacturaProveedor(facturaProveedor);
                    recepcion.addAttribute("fk_FacturaProveedor", id_Factura);
            }
                
        daoRecepcion.desmaterializar_CmdInsert(recepcion);
        int id_Recepcion=daoRecepcion.getUltimoId();
       ///////////// la lista de remitos la sacaria de la recepcion
        Iterador itDetalleRecepcion=recepcion.getDetallesRecepcion().crearIterador();
        Iterador itRemito=recepcion.getRemito().crearIterador();
        while(itDetalleRecepcion.siguiente())
        {
           
                    
            DetalleRecepcion detalleRecepcion=(DetalleRecepcion) itDetalleRecepcion.getElementoActual();
            detalleRecepcion.addAttribute("fk_Recepcion", id_Recepcion);
            this.crearDetalleRecepcion(detalleRecepcion);
            int id_DetalleRecepcion=daoDetalleRecepcion.getUltimoId();
            itRemito.siguiente();
            Remito remito=(Remito)itRemito.getElementoActual();
            remito.addAttribute("fk_Recepcion", id_Recepcion);
            remito.addAttribute("fk_DetalleRecepcion", id_DetalleRecepcion);
            this.crearRemito(remito);
        }
    this.modificarEstadoPedido(pedidoAProveedorSeleccionado);
        
        
    }

    public Lista buscarTipoFactura() throws Exception {
        
     return daoTipoFactura.materializarTodo("");
             
    }

    public Lista buscarEstadosPorAmbito(String nombre) throws Exception {
      Lista lstAmbitoEstado= daoAmbitoEstado.materializarTodo(" where nombre='"+nombre+"'");
      Iterador itAmbitoEstado=lstAmbitoEstado.crearIterador();  
      AmbitoEstado aux= (AmbitoEstado) itAmbitoEstado.getPrimerElemento();
      return daoEstado.materializarTodo(" where fk_ambitoestado="+aux.getAttribute("id_AmbitoEstado").toString());
    }
    private int crearFacturaProveedor(FacturaProveedor facturaProveedor) throws Exception
    {
        daoFacturaProveedor.desmaterializar_CmdInsert(facturaProveedor);
        return daoFacturaProveedor.getUltimoId();
        
    }

    private void crearDetalleRecepcion(DetalleRecepcion detalleRecepcion) throws Exception {
        daoDetalleRecepcion.desmaterializar_CmdInsert(detalleRecepcion);
    }

    private void crearRemito(Remito remito) throws Exception {
       daoRemito.desmaterializar_CmdInsert(remito);
    }

    private void modificarEstadoPedido(PedidoAProveedor pedidoAProveedorSeleccionado) throws Exception {
       
        daoPedidoAProveedor.cmdUpdate(pedidoAProveedorSeleccionado, " where id_pedidoaproveedor="+pedidoAProveedorSeleccionado.getAttribute("id_PedidoAProveedor").toString());
    }
    }
