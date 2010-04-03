/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgSoporteConexionBD;

/**
 *
 * @author Armando
 * Esta clase encapsula los parámetros para la conexión a la Base de Datos.
 */
public class ParametrosConexion {
    
    private String driverName = null;               // "sun.jdbc.odbc.JdbcOdbcDriver";
    private String url = null;                      // "jdbc:odbc:Alias";

    private String usr = null;
    private String pwd = null;

    
    
    public ParametrosConexion(){}
    
    public ParametrosConexion(String driver,String url,String usuario,String password){
     this.driverName=driver;
     this.url=url;
     this.usr=usuario;
     this.pwd=password;
    }
    
    
    
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
