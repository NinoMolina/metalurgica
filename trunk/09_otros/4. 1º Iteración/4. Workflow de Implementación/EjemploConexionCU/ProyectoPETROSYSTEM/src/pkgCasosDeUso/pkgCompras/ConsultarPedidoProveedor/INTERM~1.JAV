/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.ConsultarPedidoProveedor;

import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_Estado;
import pkgCapaDeDatos.pkgDAOCompras.DAO_DetallePedidoAProveedor;
import pkgCapaDeDatos.pkgDAOCompras.DAO_DetalleRecepcion;
import pkgCapaDeDatos.pkgDAOCompras.DAO_FacturaProveedor;
import pkgCapaDeDatos.pkgDAOCompras.DAO_PedidoAProveedor;
import pkgCapaDeDatos.pkgDAOCompras.DAO_Producto;
import pkgCapaDeDatos.pkgDAOCompras.DAO_Recepcion;
import pkgCapaDeDatos.pkgDAOCompras.DAO_Remito;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_Proveedor;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_RepresentanteDeProveedor;
import pkgClasesDeNegocio.Compras.DetallePedidoAProveedor;
import pkgClasesDeNegocio.Compras.DetalleRecepcion;
import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.Compras.Producto;
import pkgClasesDeNegocio.Compras.Recepcion;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class IntermediarioBDConsultarPedidoProveedor {

    private DAO_Proveedor daoProveedor=new DAO_Proveedor();
    private DAO_PedidoAProveedor daoPedidoAProveedor= new DAO_PedidoAProveedor();
    private DAO_DetallePedidoAProveedor daoDetallePedidoAProveedor=new DAO_DetallePedidoAProveedor();
    private DAO_Estado daoEstado=new DAO_Estado();
    private DAO_RepresentanteDeProveedor daoRepresentante=new DAO_RepresentanteDeProveedor();
    private DAO_Producto daoProducto=new DAO_Producto();
    private DAO_Recepcion daoRecepcion=new DAO_Recepcion();
    private DAO_DetalleRecepcion daoDetalleRecepcion=new DAO_DetalleRecepcion();
    private DAO_FacturaProveedor daoFacturaProveedor=new DAO_FacturaProveedor();
    private DAO_Remito daoRemito=new DAO_Remito();
    
    public Lista getProveedores() throws Exception{
     
       return daoProveedor.materializarTodo(""); 
    
    }

    public Lista getPedidosDeProveedor(Proveedor proveedor) throws Exception {
       String condicion="where fk_Proveedor="+ValText.getInt_Integer(proveedor.getAttribute("id_Proveedor"));   
       
       Lista lstPedidos=daoPedidoAProveedor.materializarTodo(condicion);
       
       Iterador itPedidos=lstPedidos.crearIterador();
       String condicionEstado="where id_Estado=";
       String condicionRepresentante="where id_RepresentanteDeProveedor=";
       
       
       while(itPedidos.siguiente()){
       
           PedidoAProveedor p=(PedidoAProveedor)itPedidos.getElementoActual();
           
           
           
           //setEstado()
           p.setEstado(daoEstado.materializar(condicionEstado+ValText.getInt_Integer(p.getAttribute("fk_Estado"))));
           
           //setRepresentante()
           
           p.setRepresentanteProveedor(daoRepresentante.materializar(condicionRepresentante+ValText.getInt_Integer(p.getAttribute("fk_RepresentanteDeProveedor"))));
           
       }
       
       
       
       
       return lstPedidos;    
    }

    public Lista getDetallesDePedido(PedidoAProveedor p) throws Exception {
        
        String condicion="where fk_PedidoAProveedor="+ValText.getInt_Integer(p.getAttribute("id_PedidoAProveedor"));
        
        Lista lstDetalles=daoDetallePedidoAProveedor.materializarTodo(condicion);
        
        Iterador itDetalles=lstDetalles.crearIterador();
        String condicionProducto="where id_Producto=";
        String condicionEstado="where id_Estado=";
        
        while(itDetalles.siguiente()){
          DetallePedidoAProveedor dlle=(DetallePedidoAProveedor)itDetalles.getElementoActual();
            
          //setProducto()
          dlle.setProducto(daoProducto.materializarProducto(condicionProducto+ValText.getInt_Integer(dlle.getAttribute("fk_Producto"))));
            
          //setEstado() 
          dlle.setEstado(daoEstado.materializar(condicionEstado+ValText.getInt_Integer(dlle.getAttribute("fk_Estado"))));
            
        }
            
        return lstDetalles;
        
    }
    
    public Recepcion getRecepcionDePedido(PedidoAProveedor p) throws Exception{
    
        //Traer tambien los datos de la factura, y todos los detalles de la recepcion
        
        String condicionPedido="where fk_PedidoAProveedor="+p.getNumeroPedido();
        
        Recepcion recepcion=daoRecepcion.materializarRecepcion(condicionPedido);
        
        
        
        if(recepcion!=null){
          
           if(recepcion.getAttribute("fk_FacturaProveedor")!=null && ValText.getInt_Integer(recepcion.getAttribute("fk_FacturaProveedor"))>=0){
            //Busca la factura 
            String condicionFactura="where id_FacturaProveedor="+ValText.getInt_Integer(recepcion.getAttribute("fk_FacturaProveedor"));
            
            recepcion.setFactura(daoFacturaProveedor.materializar(condicionFactura));
       
           }
           
           //Busca los remitos 
           String condicionRemito="where fk_Recepcion="+ValText.getInt_Integer(recepcion.getAttribute("id_Recepcion"));
           recepcion.setRemito(daoRemito.materializarTodo(condicionRemito));
           
           //Busca los detalles, y a cada detalle buscar: Producto 
            
           String condicionDlleRecep="where fk_Recepcion="+ValText.getInt_Integer(recepcion.getAttribute("id_Recepcion"));
           recepcion.setDetallesRecepcion(daoDetalleRecepcion.materializarTodo(condicionDlleRecep)); 
           
           Iterador itDlleRecep=recepcion.getDetallesRecepcion().crearIterador();
           
           String condicionProductoDlle="where id_Producto=";
           
           while(itDlleRecep.siguiente()){
             
             DetalleRecepcion dlle=(DetalleRecepcion)itDlleRecep.getElementoActual();  
               
             Producto pro=daoProducto.materializarProducto(condicionProductoDlle+ValText.getInt_Integer(dlle.getAttribute("fk_Producto"))); 
               
             dlle.setProducto(pro);  
           }
    
        }
       
        return recepcion;
        
    }
    
    
    
}
