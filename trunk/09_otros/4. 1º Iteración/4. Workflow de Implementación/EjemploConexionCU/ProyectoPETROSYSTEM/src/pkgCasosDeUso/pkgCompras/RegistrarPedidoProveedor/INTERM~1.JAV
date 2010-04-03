/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarPedidoProveedor;

import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_AmbitoEstado;
import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_Ciudad;
import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_DatosEmpresa;
import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_Domicilio;
import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_Estado;
import pkgCapaDeDatos.pkgDAOCompras.DAO_DetallePedidoAProveedor;
import pkgCapaDeDatos.pkgDAOCompras.DAO_PedidoAProveedor;
import pkgCapaDeDatos.pkgDAOCompras.DAO_Producto;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_Proveedor;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_RepresentanteDeProveedor;
import pkgClasesDeNegocio.AdministracionDePersonal.DatosEmpresa;
import pkgClasesDeNegocio.AdministracionDePersonal.Domicilio;
import pkgClasesDeNegocio.AdministracionDePersonal.Empleado;
import pkgClasesDeNegocio.AdministracionDePersonal.Estado;
import pkgClasesDeNegocio.Compras.AmbitoEstado;
import pkgClasesDeNegocio.Compras.DetallePedidoAProveedor;
import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class IntermediarioBDRegistrarPedidoProveedor {

    
    private DAO_Proveedor daoProveedor=new DAO_Proveedor(); 
    private DAO_RepresentanteDeProveedor daoRepresentante=new DAO_RepresentanteDeProveedor();
    private DAO_Producto daoProducto=new DAO_Producto();
    private DAO_AmbitoEstado daoAmbitoEstado=new DAO_AmbitoEstado();
    private DAO_Estado daoEstado=new DAO_Estado();
    private DAO_PedidoAProveedor daoPedidoAProveedor=new DAO_PedidoAProveedor();
    private DAO_DetallePedidoAProveedor daoDetallePedido=new DAO_DetallePedidoAProveedor();
    private DAO_DatosEmpresa daoDatosEmpresa=new DAO_DatosEmpresa();
    private DAO_Domicilio daoDomicilio=new DAO_Domicilio();
    private DAO_Ciudad daoCiudad=new DAO_Ciudad();
    
    public IntermediarioBDRegistrarPedidoProveedor() {
    }

    public Lista getProveedores() throws Exception{
     
       return daoProveedor.materializarTodo(""); 
    
    }
    
    public Proveedor getProveedor(Proveedor proveedor) throws Exception{
    
        String condicion="where razonSocial='"+proveedor.getRazonSocial()+"'";
        
        return daoProveedor.materializar(condicion);
         
    }
    
    public Lista getRepresentantesDeProveedor(Proveedor proveedor) throws Exception{
     
     String condicion="where fk_Proveedor="+ValText.getInt_Integer(proveedor.getAttribute("id_Proveedor"));   
        
     return daoRepresentante.materializarTodo(condicion);   
    }
    
    public Lista getProductosDeProveedor(Proveedor proveedor) throws Exception{
    
        return daoProducto.materializarProductosProveedor(proveedor);
    
    }
    
    public DatosEmpresa getDatosEmpresa() throws Exception{    
      return daoDatosEmpresa.materializar("");
    }
    
    public Domicilio getDomicilioEmpresa(DatosEmpresa empresa) throws Exception{
         Domicilio domicilio=null;
        
        
         String condicionDomicilio="where id_Domicilio="+ValText.getInt_Integer(empresa.getAttribute("fk_Domicilio"));   
    
         domicilio=daoDomicilio.materializar(condicionDomicilio);
         
         
         String condicionCiudad="where id_Ciudad="+ValText.getInt_Integer(domicilio.getAttribute("fk_Ciudad"));
         domicilio.setCiudad(daoCiudad.materializar(condicionCiudad));   
            
         return domicilio;
        
    }
    
    
    
    public void setEstadoPedido(PedidoAProveedor pedido) throws Exception{
    
       Lista lstAmbitoEstado=new Lista();
       lstAmbitoEstado=daoAmbitoEstado.materializarTodo("where nombre='Pedido a proveedor' or nombre='Detalle pedido proveedor'");
       Iterador itAmbitoEstado=lstAmbitoEstado.crearIterador();
       
       AmbitoEstado ambitoEstadoDetalle=new AmbitoEstado();
       ambitoEstadoDetalle.setNombre("Detalle pedido proveedor");
       ambitoEstadoDetalle=(AmbitoEstado)itAmbitoEstado.buscarElemento(ambitoEstadoDetalle); 
      
       this.setEstadosDetallesPedido(pedido.getDetallesPedidoAProveedor(),ambitoEstadoDetalle);
       
       AmbitoEstado ambitoEstadoPedido=new AmbitoEstado();
       ambitoEstadoPedido.setNombre("Pedido a proveedor");
       ambitoEstadoPedido=(AmbitoEstado)itAmbitoEstado.buscarElemento(ambitoEstadoPedido);
       
       this.setEstadoPedidoProveedor(pedido,ambitoEstadoPedido);
      
    }

    public void crearPedidoProvedor(PedidoAProveedor pedidoAProveedor) throws Exception {
        
        this.daoPedidoAProveedor.desmaterializar_CmdInsert(pedidoAProveedor);
        
        int id_PedidoAProveedor=this.daoPedidoAProveedor.getUltimoId();
        pedidoAProveedor.addAttribute("id_PedidoAProveedor", new Integer(id_PedidoAProveedor));
        
        this.crearDetallesPedido(id_PedidoAProveedor,pedidoAProveedor.getDetallesPedidoAProveedor());
        
 
    }

    private void crearDetallesPedido(int id_Pedido,Lista detalles) throws Exception {
       
        Iterador itDetalles=detalles.crearIterador();
        
        while(itDetalles.siguiente()){
          DetallePedidoAProveedor dlle=(DetallePedidoAProveedor) itDetalles.getElementoActual();
          dlle.addAttribute("fk_PedidoAProveedor",new Integer(id_Pedido));
          
          this.daoDetallePedido.desmaterializar_CmdInsert(dlle);
             
        }
        
        
    }
    
    private void setEstadoPedidoProveedor(PedidoAProveedor pedido,AmbitoEstado ambitoEstado) throws Exception{
    
         Lista lstEstados=new Lista();
         lstEstados=daoEstado.materializarTodo("where fk_AmbitoEstado="+ValText.getInt_Integer(ambitoEstado.getAttribute("id_AmbitoEstado")));
        
         Estado estadoPedido=(Estado)lstEstados.crearIterador().buscarElemento(pedido.getEstado()); 
         
         pedido.setEstado(estadoPedido);
         
    
    }
    
    private void setEstadosDetallesPedido(Lista detalles,AmbitoEstado ambitoEstado) throws Exception{
    
        Lista lstEstados=new Lista();
        lstEstados=daoEstado.materializarTodo("where fk_AmbitoEstado="+ValText.getInt_Integer(ambitoEstado.getAttribute("id_AmbitoEstado")));
        
        
        
        Iterador itDetalles=detalles.crearIterador();
        
        while(itDetalles.siguiente()){
            
            DetallePedidoAProveedor detallePedido=(DetallePedidoAProveedor) itDetalles.getElementoActual();
        
            Estado estadoDetalle=(Estado)lstEstados.crearIterador().buscarElemento(detallePedido.getEstado()); 
            
            detallePedido.setEstado(estadoDetalle);
        
        }
        
    
        
        
    }
    
    
}
