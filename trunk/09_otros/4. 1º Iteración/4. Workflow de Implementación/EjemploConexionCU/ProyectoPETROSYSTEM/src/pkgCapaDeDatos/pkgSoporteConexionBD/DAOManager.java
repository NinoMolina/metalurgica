/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgSoporteConexionBD;

import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Armando
 * Esta clase brinda los servicios para trabajar sobre la Base de Datos. 
 * Utiliza a un objeto del tipo BDConecction para acceder a la Base de Datos.
 */
public class DAOManager {

    private BDConnection bd=new BDConnection();
    private ParametrosConexion parametros=null;
    
    private Hashtable <String,Object> conexiones=new Hashtable <String,Object>();
    public static final String Connection_Default="Connection_Default";
    
    
    public DAOManager(){
     this.addNuevaConexion(Connection_Default,null);
     this.seleccionarConexion(Connection_Default);
    
    }
    
    /**
     *Setea los parámetros de la conexión actual. Si no se ha agregado aún una nueva conexión,
     *el constructor de la clase ya agrega una conexión por defecto, bajo el nombre "connectionDefault", y
     *es sobre la misma que se seteará los parámetros especificados.  
     *driverName= nombre del driver
     *url= puente hasta la base de datos (Ej.-jdbc:odbc:bd)
     *usr=nombre de usuario. Si no se desea especificar al mismo, se pasa como valor null
     *pwd=password. Si no se desea especificar al mismo, se pasa como valor null 
     */
    
    public void configurarConexion(String driverName,String url,String usr,String pwd) throws Exception{
        
        if(parametros==null){
         parametros=new ParametrosConexion();
        }
        
        this.parametros.setDriverName(driverName);
        this.parametros.setUrl(url);
        this.parametros.setUsr(usr);
        this.parametros.setPwd(pwd);
        
        this.setParametros(parametros);
       
    }
    
    /**
     *Establce la conexión con la Base de Datos, con la conexión seleccionada por defecto, 
     *en base a los parámetros previamente seteados.
     *En caso de no haberse establecido los parámetros, se setan a los mismos con parámetros
     *por defecto.   
     */
    
    public void establecerConexion() throws Exception{
      
       if(parametros==null){   
        this.configurarConexionPorDefecto("com.mysql.jdbc.Driver","jdbc:mysql://Arma:3306/bdpetrosystem","root","root");
        bd.setParametros(parametros); 
       }
        
      
     
      bd.connect();
    }
    
    /**
     *Establece la conexion especificada por medio del parámetro nombreConexion.
     *Se debe tener en cuenta que al establecer la conexión, 
     *el método la coloca como predeterminada.
     */
     public void establecerConexion(String nombreConexion) throws Exception{
      
      BDConnection conexion=(BDConnection)this.getConexion(nombreConexion);
        
    
      conexion.connect();
      
      if(conexion!=null){
       this.bd=conexion;
      }
    }
    
    
    //Coloco aqui los parametros por defecto para la conexión con la BD
    private void configurarConexionPorDefecto(String nombreDriver,String url,String usuario,String password){
     parametros=new ParametrosConexion(nombreDriver,url,usuario,password);  
    }

    public void setParametrosPorDefecto(){
     if(parametros==null){
      parametros=new ParametrosConexion();
     }
     
     parametros.setDriverName("com.mysql.jdbc.Driver");
     parametros.setUrl("jdbc:mysql://Arma:3306/bdpetrosystem");
     parametros.setUsr("root");
     parametros.setPwd("root");
     
    }
    
    /**
     *Cierra la conexión actual establecida con la Base de Datos. 
     */
    
    public void cerrarConexion() throws Exception{
     bd.close();
    }
    /**
     *Cierra la conexión establecida con la Base de Datos, cuyo nombre es el
     *especificado en el parámetro nombreConexion. 
     */
    
    public void cerrarConexion(String nombreConexion) throws Exception{
        BDConnection conexion=(BDConnection)this.getConexion(nombreConexion);
        
        conexion.close();
    }
    
    
    /**
     *Ejecuta una consulta, sobre la conexión actual seleccionda, retornando un objeto 
     *ResultSet. Este método debe ser utilizado para ejecutar consultas del tipo select.  
     */

    public ResultSet ejecutarConsulta(String consulta) throws Exception {
        
        if(consulta==null || consulta.length()==0){return null;}
        
        return this.bd.executeQuery(consulta);
    }
    
     /**
     *Ejecuta una consulta, sobre la conexión actual seleccionda, que produce 
     *alguna modificación en los registros de una tabla en particular de la BD. 
     *Retorna el número de filas afectadas.
     *Si el número devuelto es 0, es la consulta no afecto a ningun registro o bien
     *porque ha fallado la ejecución de la misma.
     *Este método se debería utilizar para ejecutar comandos del tipo update, delete, insert. 
     */
    
     public int ejecutarUpdate(String consultaUpdate) throws Exception {
        if(consultaUpdate==null || consultaUpdate.length()==0){return 0;}
        
        return this.bd.executeUpdate(consultaUpdate);
        
    }
    
    /**
     *Devuelve un objeto del tipo ParametrosConexion, que posee encapsulado
     *los parámetros utilizados para realizar la conexión con la Base de Datos.
     *El objeto ParametrosConexion devuelto,es el correspondiente a la conexión
     *actual seleccionada.    
     */
    public ParametrosConexion getParametros() {
        return parametros;
    }
    
     /**
     *Devuelve un objeto del tipo ParametrosConexion, que posee encapsulado
     *los parámetros utilizados para realizar la conexión con el nombre especificado
     *en el parámetro nombreConexion. 
     */
    public ParametrosConexion getParametros(String nombreConexion) {
        if(nombreConexion==null || nombreConexion.length()==0){return null;}
        
        BDConnection conexion=(BDConnection) this.getConexion(nombreConexion);
        
        if(conexion==null){
         return null;
        }
        
        return conexion.getParametros();
        
    }
    
    /**
     *Setea los parámetros de la conexión actual seleccionada.
     *El método no altera el estado de la conexión, es decir que si se encontraba abierta
     *la misma permanece abierta, pero con los nuevos parámetros. Lo mismo vale para 
     *cuando la conexión se encuentra cerrada.  
     */ 
    public void setParametros(ParametrosConexion pc) throws Exception {
        parametros=pc;
        
        int b=0;
        if(bd.isConnect()){
         b=1;
        }
        
        bd.close();
        bd.setParametros(parametros);
        
        if(b==1){
         bd.connect();
        }
    }
    
    /**
     *Setea los parámetros de la conexión especificada con el parámetro nombreConexion.
     *El método no altera el estado de la conexión, es decir que si se encontraba abierta
     *la misma permanece abierta, pero con los nuevos parámetros. Lo mismo vale para 
     *cuando la conexión se encuentra cerrada.  
     */ 
    public void setParametros(String nombreConexion,ParametrosConexion pc) throws Exception {
        
        BDConnection conexion=(BDConnection) this.getConexion(nombreConexion);
        
        int b=0;
        if(conexion.isConnect()){
         b=1;
        }
        
        conexion.close();
        
        conexion.setParametros(pc);
        
        if(b==1){
         conexion.connect();
        }
        
    }
    
     /**
     *Devuelve un objeto del tipo ParametrosConexion, que posee encapsulado
     *los parámetros utilizados para realizar la conexión con la Base de Datos.  
     */
    
   private Object getConexion(String nombreConexion) {
     if (conexiones==null || nombreConexion==null || nombreConexion.length()==0) return null;
     else return conexiones.get(nombreConexion);
    }
    /**Selecciona la conexion con la cual se desea trabajar.*/
    
    public void seleccionarConexion(String nombreConexion){
     if (conexiones==null || nombreConexion==null || nombreConexion.length()==0) return;   
        
      bd=(BDConnection) conexiones.get(nombreConexion);
    }
 
     /**
      *Crea una nueva conexión.
      *En caso de que se reciba null en el parámetro parametrosConexion, la
      *nueva conexión toma como parámetros los de la conexión predeterminada. 
      *En caso de no existir una conexión, la conexión se crea pero sin los 
      *parámetros, que luegos podrán ser seteados con el método setParametros().  
      */
    
   public void addNuevaConexion(String nombreConexion, ParametrosConexion parametrosConexion) {
     //Si parametrosConexion es null, establecer los parametros de la primera conexion
       if (conexiones==null) conexiones = new Hashtable<String, Object>();
       if(nombreConexion==null||nombreConexion.length()==0){ return;}
       
       BDConnection nuevaConexion=new BDConnection();
       
       if(parametrosConexion==null){
           
        if(parametros==null){this.setParametrosPorDefecto();}   
           
        nuevaConexion.setParametros(parametros);
       }
       else{
           nuevaConexion.setParametros(parametrosConexion);
       }
       
       nuevaConexion.setNombreConexion(nombreConexion);
       conexiones.put(nombreConexion,nuevaConexion);  
       
   }
   
   /**
    *Retorna un objeto del tipo BDConnection que representa a la conexión actual.
    */
   public BDConnection getBDConexioActual(){
     
    return bd; 
    
   }
   
   /**
    *Devuelve un objeto BDManager, que representa a la conexión con
    *el nombre especificado en el parámetro nombreConexion. 
    *En caso de no existir la conexión con el nombre especificado, el valor
    *de retorno será null.  
    */
   
   public BDConnection getBDConexion(String nombreConexion){
        BDConnection conexion=null;
       
        if (conexiones==null || nombreConexion==null || nombreConexion.length()==0) return null;
        else {
        conexion=(BDConnection)this.getConexion(nombreConexion);
        
        }
        
        return conexion;
       
   }
	
   
     /**
    *Retorna un String con el nombre que representa a la conexión actual.
    */
   public String getNombreConexioActual(){
    
       
    if(bd.getParametros()==null){return null;}
     
    return bd.getNombreConexion(); 
    
   }
   
   /**
    *Elimina la conexión con el nombre especificado.
    *En caso de eliminarse la conexión seleccionada como predeterminda, se adopta
    *como nueva conexión la creada por defecto.  
    *Retorna false, en caso de que no exista una conexión con el nombre especificado.
    *Retorna true, si se pudo llevar a cabo la eliminación. 
    */
   
   public boolean eliminarConexion(String nombreConexion) throws Exception{
   
     if (conexiones==null || nombreConexion==null || nombreConexion.length()==0 || nombreConexion.compareTo(Connection_Default)==0) return false;
     
     BDConnection conexion=(BDConnection)this.getConexion(nombreConexion);
     
     if(conexion!=null && conexion.isConnect()){
       conexion.close();
     }
     
     if(bd.equals(conexion)){
       bd=(BDConnection)this.getConexion(Connection_Default);
     }
     
     
     this.conexiones.remove(nombreConexion);
     
     return true;
   
   }
   
   public void cerrarTodasLasConexiones() throws Exception{
    Enumeration valores=this.conexiones.elements();
    while(valores.hasMoreElements()){
     BDConnection conexion=(BDConnection)valores.nextElement();
     conexion.close();
    }
    
   }
   
    /**
    *Devuelve el estado de la conexión con el nombre especificado en el
    *parámetro nombreConexion.
    *Valores de Retorno:
    *- Conexión Abierta=1
    *- Conexión Cerrada=0;
    *- Si no existe la conexión, o si ocurre un error en la consulta se retorna 
    *  el valor -1.     
    */
   
   public int estadoConexion(String nombreConexion){
     
    try{   
     BDConnection conexion=(BDConnection) this.getConexion(nombreConexion);
     if(conexion!=null){
      if(conexion.isConnect()){return 1;}
      else{return 0;}
     }
     else{
      return -1;
     }
    
     
    }
    catch(Exception ex){
     return -1;
    }
     
   }
   
    
    
    
}
