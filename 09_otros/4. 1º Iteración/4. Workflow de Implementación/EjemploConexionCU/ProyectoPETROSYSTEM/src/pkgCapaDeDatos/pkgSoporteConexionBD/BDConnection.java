/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgSoporteConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Armando
 * Esta clase representa la conexión con la BD. Es la que interactúa con la BD.
 * Utiliza un objeto ParametrosConexion, como recurso para poder acceder a la
 * Base de Datos, espeblecida en dicho objeto.
 */
public class BDConnection {

    private ParametrosConexion parametrosConexion=new ParametrosConexion();
    private String nombreConexion=null;
    
    private Connection cn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    /**
     * Creates a new instance of BDManager
     */
    public BDConnection() { 
        super();
    }
    
    
 
    public void connect() throws Exception {
        
        Class.forName(this.parametrosConexion.getDriverName());
        this.cn = DriverManager.getConnection(this.parametrosConexion.getUrl(),this.parametrosConexion.getUsr(), this.parametrosConexion.getPwd());
       
        this.stmt = this.cn.createStatement();
    }
    
    public Connection getConnection() throws Exception {
    	if (this.cn == null) this.connect();
    	return this.cn;
    }
    
    public ResultSet executeQuery(String query) throws Exception {
        rs=this.stmt.executeQuery(query);
        
        return this.rs;
    }
    
    public int executeUpdate(String statement) throws Exception {
        return this.stmt.executeUpdate(statement);
    }
    
    public void close() throws Exception {
        
        if (this.stmt != null) this.stmt.close();
        if (this.cn != null) this.cn.close();
        if(this.getRs()!= null)this.rs.close();
    }
    
    public boolean isConnect() throws SQLException{
       if(cn==null){return false;}
        
        
       return !(this.cn.isClosed());
    }

    public ResultSet getRs() {
        return rs;
    }

    public ParametrosConexion getParametros() {
        return parametrosConexion;
    }

    public void setParametros(ParametrosConexion parametros) {
        this.parametrosConexion = parametros;
    }

    public String getNombreConexion() {
        return nombreConexion;
    }

    public void setNombreConexion(String nombreConexion) {
        this.nombreConexion = nombreConexion;
    }
 
}
