/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/**
 *
 * @author Nino
 */
public class PostgreSQLManager {
    public static int SINGLECONNECTIONMODE = 1;
    public static final int POOLSELECTIONMODE = 2;

    private int connectionMode=SINGLECONNECTIONMODE;
    private String driverName = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/metalsoft";
    private String usr = "admin";
    private String pwd = "admin";
    private String resourceName = "jdbc/_PostgreSQL";

    private Context ctx = null;
    private DataSource ds = null;
    private Connection cn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(int connectionMode) {
        this.connectionMode = connectionMode;
    }

    public CallableStatement getCstmt(String call) throws SQLException {
        if(cn!=null)return cn.prepareCall(call);
        return null;
    }

    public void setCstmt(CallableStatement cstmt) {
        this.cstmt = cstmt;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public PreparedStatement getPstmt(String consulta) throws SQLException {
        if(cn!=null)return cn.prepareStatement(consulta);
        return null;
    }

    public void setPstmt(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Statement getStmt() throws SQLException {
        return cn.createStatement();
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
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

    public void cerrarConexion(Connection cn)
    {
    }
    public void connect() throws Exception
    {
        if(this.getCn() == null)
        {
            if(this.getConnectionMode() == SINGLECONNECTIONMODE)
            {
               Class.forName(driverName).newInstance();
               this.cn = DriverManager.getConnection(this.url, this.usr, this.pwd);
            }
            else
            {
                this.setContext();
                this.setCn(this.getDs().getConnection());
            }
        }
    }
    public Connection concectGetCn() throws Exception
    {
        connect();
        return getCn();
    }
    public void setContext() throws Exception
    {
        if(this.ds == null)
        {
            if(this.resourceName == null)
            {
                throw new Exception("PostgreSQLManager Error: resourceName");
            }
            this.ctx = new InitialContext();
            this.ds = (DataSource) this.ctx.lookup(this.resourceName);
        }
    }
    public void reconnect() throws Exception
    {
        if(this.cn != null)
        {
            this.disconnect();
        }
        this.connect();
    }
    public void disconnect() throws SQLException
    {
        cn.close();
        cn=null;
    }

    public void cerrarConexion(ResultSet rs, PreparedStatement pstmt, Connection cn) throws SQLException
    {
        rs.close();
        rs=null;
        pstmt.close();
        pstmt=null;
        cn.close();
        cn=null;
    }

    public void cerrarConexion(ResultSet rs, Statement stmt, Connection cn) throws SQLException
    {
        rs.close();
        rs=null;
        stmt.close();
        stmt=null;
        cn.close();
        cn=null;
    }

    public void cerrarConexion(ResultSet rs, CallableStatement cstmt, Connection cn) throws SQLException
    {
        rs.close();
        rs=null;
        cstmt.close();
        cstmt=null;
        cn.close();
        cn=null;
    }
}
