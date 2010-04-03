/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOCompras;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import java.util.Date;
import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;
/**
 *
 * @author Armando
 */
public class DAO_PedidoAProveedor {
  DAOManager daoManager=new DAOManager();

    public DAO_PedidoAProveedor() {}
    
    public PedidoAProveedor materializar(String condicionBusqueda) throws Exception{
      return null;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.pedidoaproveedor " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen pedidos de proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Pedido=res.getInt(1);
        Date fechaRealizacion=res.getDate(2);
        String horaRealizacion=res.getString(3);
        Date fechaEstimadaEntrega=res.getDate(4);
        String horaEstimadaEntrega=res.getString(5);
        double montoTotal=res.getDouble(6);
        int fk_Estado=res.getInt(7);
        int fk_RepresentanteDeProveedor=res.getInt(8);
        int fk_Proveedor=res.getInt(9);
        int fk_Empleado=res.getInt(10);
        
              
       PedidoAProveedor pedido=new PedidoAProveedor();
       
       pedido.addAttribute("id_PedidoAProveedor", new Integer(id_Pedido));
       pedido.setNumeroPedido(id_Pedido);
       pedido.setFechaRealizacion(fechaRealizacion);
       pedido.setHoraRealizacion(horaRealizacion);
       pedido.setFechaEstimadaEntrega(fechaEstimadaEntrega);
       pedido.setHoraEstimadaEntrega(horaEstimadaEntrega);
       pedido.setMontoTotal(montoTotal);
       pedido.addAttribute("fk_Estado",new Integer(fk_Estado));
       pedido.addAttribute("fk_RepresentanteDeProveedor",new Integer(fk_RepresentanteDeProveedor));
       pedido.addAttribute("fk_Proveedor",new Integer(fk_Proveedor));
       pedido.addAttribute("fk_Empleado",new Integer(fk_Empleado));
       
       lstResultado.insertarOrdenado(pedido);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
   public boolean desmaterializar_CmdInsert(PedidoAProveedor pedido) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
       
       

       
      String consulta="INSERT INTO bdpetrosystem.pedidoaproveedor (fechaRealizacion,horaRealizacion,fechaEstimadaEntrega,horaEstimadaEntrega,montoTotal,fk_Estado,fk_RepresentanteDeProveedor,fk_Proveedor,fk_Empleado) VALUES ('"+
                             ValText.getFormatStringDate(pedido.getFechaRealizacion())+"','"+pedido.getHoraRealizacion()+"','"+ValText.getFormatStringDate(pedido.getFechaEstimadaEntrega())+
                             "','"+pedido.getHoraEstimadaEntrega()+"',"+pedido.getMontoTotal()+","+ValText.getInt_Integer(pedido.getEstado().getAttribute("id_Estado"))+","+ValText.getString_Object(pedido.getRepresentanteProveedor().getAttribute("id_RepresentanteDeProveedor"))+","+ValText.getInt_Integer(pedido.getProveedor().getAttribute("id_Proveedor"))+","+ValText.getInt_Integer(pedido.getEmpleado().getAttribute("id_Empleado"))+")";
      
    
      
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     daoManager.cerrarConexion(); 
      
     return res;
   }
   
   public boolean cmdUpdate(PedidoAProveedor pedido,String condicionUpdate) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      String consulta="UPDATE bdpetrosystem.pedidoaproveedor set fechaRealizacion='"+ValText.getFormatStringDate(pedido.getFechaRealizacion())+"',horaRealizacion='"+pedido.getHoraRealizacion()+
                      "',fechaEstimadaEntrega='"+ValText.getFormatStringDate(pedido.getFechaEstimadaEntrega())+"',horaEstimadaEntrega='"+pedido.getHoraEstimadaEntrega()+
                      "',montoTotal="+pedido.getMontoTotal()+",fk_Estado="+ValText.getInt_Integer(pedido.getAttribute("fk_Estado"))+
                      ",fk_RepresentanteDeProveedor="+ValText.getInt_Integer(pedido.getAttribute("fk_RepresentanteDeProveedor"))+",fk_Proveedor="+ValText.getInt_Integer(pedido.getAttribute("fk_Proveedor"))+",fk_Empleado="+ValText.getInt_Integer(pedido.getAttribute("fk_Empleado"))+" "+condicionUpdate;
 
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }  

  
   public int getUltimoId() throws Exception{
      
        daoManager.establecerConexion();
        
        int valor=-1;
        String consulta="SELECT max(p.id_PedidoAProveedor) FROM bdpetrosystem.pedidoaproveedor p";
          
        ResultSet res=daoManager.ejecutarConsulta(consulta);
        
        if(res==null){
         return -1;
        }
        
        while(res.next()){
        
         valor=res.getInt(1);
        }
        
       daoManager.cerrarConexion(); 
        
       return valor;
    
    }
   
   
   
   
   
   /*public boolean cmdDelete(Proveedor proveedor){
     boolean res=false;    
     return res;
   }*/ 
    
    
    
    
}
