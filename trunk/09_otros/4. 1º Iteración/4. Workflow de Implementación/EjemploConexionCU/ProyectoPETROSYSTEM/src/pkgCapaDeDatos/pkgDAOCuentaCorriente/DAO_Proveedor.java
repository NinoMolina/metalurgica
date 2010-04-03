/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOCuentaCorriente;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;
/**
 *
 * @author Armando
 */
public class DAO_Proveedor {
 
    DAOManager daoManager=new DAOManager();

    public DAO_Proveedor() {}
    
    public Proveedor materializar(String condicionBusqueda) throws Exception{
     Proveedor proveedor=null;  
   
     this.daoManager.establecerConexion();
        
        int id_Proveedor;
        String razonSocial=new String();
        int cuit;
        int fk_Domicilio;
        int nroIngresoBruto;
        
        Lista telefonos=new Lista();
        int fk_CondicionIva;
        Lista tipoProveedor=new Lista();
        int fk_CuentaCorrienteProveedor;
        Lista productos=new Lista();
      
       String consulta = "SELECT id_Proveedor, razonSocial, cuit, ingresoBruto, fk_Domicilio, fk_CondicionIva, fk_CuentaCorrienteProveedor  FROM bdpetrosystem.proveedor " +condicionBusqueda;
    
       ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
     while (res.next()){
      
        id_Proveedor=res.getInt(1);
        razonSocial=res.getString(2); 
        cuit=res.getInt(3);
        nroIngresoBruto=res.getInt(4);
        
        fk_Domicilio=res.getInt(5);
        fk_CondicionIva=res.getInt(6);
        fk_CuentaCorrienteProveedor=res.getInt(7);
        
              
       proveedor=new Proveedor();
       
       proveedor.addAttribute("id_Proveedor",new Integer(id_Proveedor));
       proveedor.setRazonSocial(razonSocial);
       proveedor.setCuit(cuit);
       proveedor.setNroIngresoBruto(nroIngresoBruto);
       
       proveedor.addAttribute("fk_Domicilio", new Integer(fk_Domicilio));
       proveedor.addAttribute("fk_CondicionIva", new Integer(fk_CondicionIva));
       proveedor.addAttribute("fk_CuentaCorrienteProveedor", new Integer(fk_CuentaCorrienteProveedor));
           
      }
   
      daoManager.cerrarConexion();

      return proveedor;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT id_Proveedor, razonSocial, cuit, ingresoBruto, fk_Domicilio, fk_CondicionIva, fk_CuentaCorrienteProveedor  FROM bdpetrosystem.proveedor " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Proveedor=res.getInt(1);
        String razonSocial=res.getString(2); 
        int cuit=res.getInt(3);
        int nroIngresoBruto=res.getInt(4);
        
        int fk_Domicilio=res.getInt(5);
        int fk_CondicionIva=res.getInt(6);
        int fk_CuentaCorrienteProveedor=res.getInt(7);
        
              
       Proveedor proveedor=new Proveedor();
       
       proveedor.addAttribute("id_Proveedor",new Integer(id_Proveedor));
       proveedor.setRazonSocial(razonSocial);
       proveedor.setCuit(cuit);
       proveedor.setNroIngresoBruto(nroIngresoBruto);
       
       proveedor.addAttribute("fk_Domicilio", new Integer(fk_Domicilio));
       proveedor.addAttribute("fk_CondicionIva", new Integer(fk_CondicionIva));
       proveedor.addAttribute("fk_CuentaCorrienteProveedor", new Integer(fk_CuentaCorrienteProveedor));
       
       lstResultado.insertarOrdenado(proveedor);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
   public boolean desmaterializar_CmdInsert(Proveedor proveedor) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      
      
      String consulta="INSERT INTO bdpetrosystem.proveedor (razonSocial, cuit, ingresoBruto, fk_Domicilio, fk_CondicionIva) VALUES ('"+proveedor.getRazonSocial()+"',"+
                             proveedor.getCuit()+","+proveedor.getNroIngresoBruto()+","+ValText.getInt_Integer(proveedor.getDomicilio().getAttribute("id_Domicilio"))+
                             ","+ValText.getInt_Integer(proveedor.getCondicionIva().getAttribute("id_CondicionIva"))+")";
    
    
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     daoManager.cerrarConexion(); 
      
     return res;
   }
   
   public boolean cmdUpdate(Proveedor proveedor,String condicionUpdate) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      String consulta="UPDATE bdpetrosystem.proveedor set razonSocial='"+proveedor.getRazonSocial()+"',cuit="+proveedor.getCuit()+
                      ",ingresoBruto="+proveedor.getNroIngresoBruto()+",fk_Domicilio="+ValText.getInt_Integer(proveedor.getAttribute("fk_Domicilio"))+
                      ",fk_CondicionIva="+ValText.getInt_Integer(proveedor.getAttribute("fk_CondicionIva"))+",fk_CuentaCorrienteProveedor="+ValText.getInt_Integer(proveedor.getAttribute("fk_CuentaCorrienteProveedor"))+
                      " "+condicionUpdate;
 
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }  

  
   
   
   
   
   public Lista materializarTelefonos(Proveedor proveedor) throws Exception{
      Lista lstTelefonos=new Lista();   
         
      daoManager.addNuevaConexion("conProvXTel", null);
      daoManager.seleccionarConexion("conProvXTel");
      daoManager.establecerConexion();
      
      String consultaProvXTel= "SELECT max(fk_Telefono) idMax,min(fk_Telefono) idMin FROM bdpetrosystem.TelefonoXProveedor where fk_Proveedor= "+ValText.getInt_Integer(proveedor.getAttribute("id_Proveedor"));
      
      ResultSet res=daoManager.ejecutarConsulta(consultaProvXTel);
      
      int idMin=-1;
      int idMax=-1;
      
      while(res.next()){
       
          idMax=res.getInt("idMax");
          idMin=res.getInt("idMin"); 
      }
      
      if(idMin!=-1 && idMax!=-1){
       
          daoManager.seleccionarConexion(daoManager.Connection_Default);
          daoManager.establecerConexion();
          
          //numero,caracteristica
          String consultaTelefProv="SELECT * FROM bdpetrosystem.telefono where id_Telefono>="+idMin+" AND "+"id_Telefono<="+idMax;
          
          ResultSet resTelefonos=daoManager.ejecutarConsulta(consultaTelefProv);
          
          while(resTelefonos.next()){
           
             int id_Telefono=resTelefonos.getInt(1);
             int numero=resTelefonos.getInt(2);
             int caracteristica=resTelefonos.getInt(3);
             
             Telefono telefono=new Telefono();
             telefono.addAttribute("id_Telefono",new Integer(id_Telefono));
             telefono.setNumero(numero);
             telefono.setCaracteristica(caracteristica);
             
             lstTelefonos.insertarOrdenado(telefono);
          }
          
         
      
      }
    
      daoManager.cerrarTodasLasConexiones();
      
      return lstTelefonos;
   }
   
    public Lista materializarTiposProveedor(Proveedor proveedor) throws Exception{
      Lista lstTiposProveedor=new Lista();   
         
      daoManager.addNuevaConexion("conProvXTipoProv", null);
      daoManager.seleccionarConexion("conProvXTipoProv");
      daoManager.establecerConexion();
      
      String consultaProvXTipoProv= "SELECT max(id_TipoProveedor) idMax,min(id_TipoProveedor) idMin FROM bdpetrosystem.ProveedorXTipoProveedor where fk_Proveedor= "+ValText.getInt_Integer(proveedor.getAttribute("id_Proveedor"));
      
      ResultSet res=daoManager.ejecutarConsulta(consultaProvXTipoProv);
      
      int idMin=-1;
      int idMax=-1;
      
      while(res.next()){
       
          idMax=res.getInt("idMax");
          idMin=res.getInt("idMin"); 
      }
      
      if(idMin!=-1 && idMax!=-1){
       
          daoManager.seleccionarConexion(daoManager.Connection_Default);
          daoManager.establecerConexion();
          
         
          String consultaTelefProv="SELECT * FROM bdpetrosystem.tipoproveedor where id_TipoProveedor>="+idMin+" AND "+"id_TipoProveedor<="+idMax;
          
          ResultSet resTiposProveedor=daoManager.ejecutarConsulta(consultaTelefProv);
          
          while(resTiposProveedor.next()){
           
             int id_TipoProveedor=resTiposProveedor.getInt(1);
             String nombre=resTiposProveedor.getString(2);
             String descripcion=resTiposProveedor.getString(3);;
             
             TipoProveedor tipoProveedor=new TipoProveedor();
             tipoProveedor.addAttribute("id_TipoProveedor",new Integer(id_TipoProveedor));
             tipoProveedor.setNombre(nombre);
             tipoProveedor.setDescripcion(descripcion);
             
             lstTiposProveedor.insertarOrdenado(tipoProveedor);
          }
          
      
      
      }
    
      daoManager.cerrarTodasLasConexiones();
      
      return lstTiposProveedor;
   }
    
 public Lista materializarPorTipoProveedor(TipoProveedor tipoProveedor) throws Exception
 {
 Lista lstProveedor= new Lista();
 daoManager.addNuevaConexion("conProPorTipoProveedor", null);
 daoManager.seleccionarConexion("conProPorTipoProveedor");
 daoManager.establecerConexion();
 
 String consulta="SELECT p.id_Proveedor, p.razonSocial, p.cuit, p.ingresoBruto, p.fk_Domicilio, p.fk_CondicionIva, p.fk_CuentaCorrienteProveedor from bdpetrosystem.proveedor p, bdpetrosystem.proveedorxtipoproveedor ptp, bdpetrosystem.tipoproveedor tp where ptp.fk_proveedor=p.id_proveedor and ptp.fk_tipoproveedor=tp.id_tipoproveedor and tp.id_tipoproveedor="+ValText.getInt_Integer(tipoProveedor.getAttribute("id_TipoProveedor"));
   ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Proveedor=res.getInt(1);
        String razonSocial=res.getString(2); 
        int cuit=res.getInt(3);
        int nroIngresoBruto=res.getInt(4);
        
        int fk_Domicilio=res.getInt(5);
        int fk_CondicionIva=res.getInt(6);
        int fk_CuentaCorrienteProveedor=res.getInt(7);
        
              
       Proveedor proveedor=new Proveedor();
       
       proveedor.addAttribute("id_Proveedor",new Integer(id_Proveedor));
       proveedor.setRazonSocial(razonSocial);
       proveedor.setCuit(cuit);
       proveedor.setNroIngresoBruto(nroIngresoBruto);
       
       proveedor.addAttribute("fk_Domicilio", new Integer(fk_Domicilio));
       proveedor.addAttribute("fk_CondicionIva", new Integer(fk_CondicionIva));
       proveedor.addAttribute("fk_CuentaCorrienteProveedor", new Integer(fk_CuentaCorrienteProveedor));
       
       lstProveedor.insertarOrdenado(proveedor);
           
      }

      daoManager.cerrarConexion();
      
 
 return lstProveedor;
 }
 
     
 public void desmaterializarTelefonos(Proveedor proveedor) throws Exception{
   
        Lista telefonos=proveedor.getTelefonos();
        
        Iterador itTelefonos=telefonos.crearIterador();
        daoManager.establecerConexion();
        
        String consulta="insert into telefonoxproveedor (fk_Telefono,fk_Proveedor) values ";
        
        while(itTelefonos.siguiente()){
         Telefono tel=(Telefono) itTelefonos.getElementoActual();
         String datosFk="("+ValText.getInt_Integer(tel.getAttribute("id_Telefono"))+","+ValText.getInt_Integer(proveedor.getAttribute("id_Proveedor"))+")";
         
         String consultaEntera=consulta+datosFk;
         
         daoManager.ejecutarUpdate(consultaEntera);
  
        }
        
        daoManager.cerrarConexion();
        
        
        
    }


    
public void desmaterializarTiposProveedor(Proveedor proveedor) throws Exception{
   
        Lista tiposProveedor=proveedor.getTipoProveedor();
        
        Iterador itTipoProveedor=tiposProveedor.crearIterador();
        daoManager.establecerConexion();
        
        String consulta="insert into proveedorxtipoproveedor (fk_Proveedor,fk_TipoProveedor) values ";
        
        while(itTipoProveedor.siguiente()){
         TipoProveedor tipoProv=(TipoProveedor) itTipoProveedor.getElementoActual();
         String datosFk="("+ValText.getInt_Integer(tipoProv.getAttribute("id_TipoProveedor"))+","+ValText.getInt_Integer(proveedor.getAttribute("id_Proveedor"))+")";
         
         String consultaEntera=consulta+datosFk;
         
         daoManager.ejecutarUpdate(consultaEntera);
  
        }
        
        daoManager.cerrarConexion();
    }




public int getUltimoId() throws Exception{
      
        daoManager.establecerConexion();
        
        int valor=-1;
        String consulta="SELECT max(p.id_Proveedor) FROM proveedor p";
          
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
