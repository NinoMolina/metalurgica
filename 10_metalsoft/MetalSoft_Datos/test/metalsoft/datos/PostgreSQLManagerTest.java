/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos;

import metalsoft.datos.PostgreSQLManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nino
 */
public class PostgreSQLManagerTest {

    public PostgreSQLManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCn method, of class PostgreSQLManager.
     */
    @Test
    public void testGetCn() {
        System.out.println("getCn");
        PostgreSQLManager instance = new PostgreSQLManager();
        Connection expResult = null;
        Connection result = instance.getCn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCn method, of class PostgreSQLManager.
     */
    @Test
    public void testSetCn() {
        System.out.println("setCn");
        Connection cn = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setCn(cn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnectionMode method, of class PostgreSQLManager.
     */
    @Test
    public void testGetConnectionMode() {
        System.out.println("getConnectionMode");
        PostgreSQLManager instance = new PostgreSQLManager();
        int expResult = 0;
        int result = instance.getConnectionMode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConnectionMode method, of class PostgreSQLManager.
     */
    @Test
    public void testSetConnectionMode() {
        System.out.println("setConnectionMode");
        int connectionMode = 0;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setConnectionMode(connectionMode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCstmt method, of class PostgreSQLManager.
     */
    @Test
    public void testGetCstmt() throws Exception {
        System.out.println("getCstmt");
        String call = "";
        PostgreSQLManager instance = new PostgreSQLManager();
        CallableStatement expResult = null;
        CallableStatement result = instance.getCstmt(call);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCstmt method, of class PostgreSQLManager.
     */
    @Test
    public void testSetCstmt() {
        System.out.println("setCstmt");
        CallableStatement cstmt = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setCstmt(cstmt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCtx method, of class PostgreSQLManager.
     */
    @Test
    public void testGetCtx() {
        System.out.println("getCtx");
        PostgreSQLManager instance = new PostgreSQLManager();
        Context expResult = null;
        Context result = instance.getCtx();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCtx method, of class PostgreSQLManager.
     */
    @Test
    public void testSetCtx() {
        System.out.println("setCtx");
        Context ctx = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setCtx(ctx);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDriverName method, of class PostgreSQLManager.
     */
    @Test
    public void testGetDriverName() {
        System.out.println("getDriverName");
        PostgreSQLManager instance = new PostgreSQLManager();
        String expResult = "";
        String result = instance.getDriverName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDriverName method, of class PostgreSQLManager.
     */
    @Test
    public void testSetDriverName() {
        System.out.println("setDriverName");
        String driverName = "";
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setDriverName(driverName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDs method, of class PostgreSQLManager.
     */
    @Test
    public void testGetDs() {
        System.out.println("getDs");
        PostgreSQLManager instance = new PostgreSQLManager();
        DataSource expResult = null;
        DataSource result = instance.getDs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDs method, of class PostgreSQLManager.
     */
    @Test
    public void testSetDs() {
        System.out.println("setDs");
        DataSource ds = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setDs(ds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPstmt method, of class PostgreSQLManager.
     */
    @Test
    public void testGetPstmt() throws Exception {
        System.out.println("getPstmt");
        String consulta = "";
        PostgreSQLManager instance = new PostgreSQLManager();
        PreparedStatement expResult = null;
        PreparedStatement result = instance.getPstmt(consulta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPstmt method, of class PostgreSQLManager.
     */
    @Test
    public void testSetPstmt() {
        System.out.println("setPstmt");
        PreparedStatement pstmt = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setPstmt(pstmt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPwd method, of class PostgreSQLManager.
     */
    @Test
    public void testGetPwd() {
        System.out.println("getPwd");
        PostgreSQLManager instance = new PostgreSQLManager();
        String expResult = "";
        String result = instance.getPwd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPwd method, of class PostgreSQLManager.
     */
    @Test
    public void testSetPwd() {
        System.out.println("setPwd");
        String pwd = "";
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setPwd(pwd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResourceName method, of class PostgreSQLManager.
     */
    @Test
    public void testGetResourceName() {
        System.out.println("getResourceName");
        PostgreSQLManager instance = new PostgreSQLManager();
        String expResult = "";
        String result = instance.getResourceName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResourceName method, of class PostgreSQLManager.
     */
    @Test
    public void testSetResourceName() {
        System.out.println("setResourceName");
        String resourceName = "";
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setResourceName(resourceName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStmt method, of class PostgreSQLManager.
     */
    @Test
    public void testGetStmt() throws Exception {
        System.out.println("getStmt");
        PostgreSQLManager instance = new PostgreSQLManager();
        Statement expResult = null;
        Statement result = instance.getStmt();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStmt method, of class PostgreSQLManager.
     */
    @Test
    public void testSetStmt() {
        System.out.println("setStmt");
        Statement stmt = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setStmt(stmt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUrl method, of class PostgreSQLManager.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        PostgreSQLManager instance = new PostgreSQLManager();
        String expResult = "";
        String result = instance.getUrl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUrl method, of class PostgreSQLManager.
     */
    @Test
    public void testSetUrl() {
        System.out.println("setUrl");
        String url = "";
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setUrl(url);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsr method, of class PostgreSQLManager.
     */
    @Test
    public void testGetUsr() {
        System.out.println("getUsr");
        PostgreSQLManager instance = new PostgreSQLManager();
        String expResult = "";
        String result = instance.getUsr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsr method, of class PostgreSQLManager.
     */
    @Test
    public void testSetUsr() {
        System.out.println("setUsr");
        String usr = "";
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setUsr(usr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cerrarConexion method, of class PostgreSQLManager.
     */
    @Test
    public void testCerrarConexion_Connection() {
        System.out.println("cerrarConexion");
        Connection cn = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.cerrarConexion(cn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class PostgreSQLManager.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.connect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of concectGetCn method, of class PostgreSQLManager.
     */
    @Test
    public void testConcectGetCn() throws Exception {
        System.out.println("concectGetCn");
        PostgreSQLManager instance = new PostgreSQLManager();
        Connection expResult = null;
        Connection result = instance.concectGetCn();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setContext method, of class PostgreSQLManager.
     */
    @Test
    public void testSetContext() throws Exception {
        System.out.println("setContext");
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.setContext();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reconnect method, of class PostgreSQLManager.
     */
    @Test
    public void testReconnect() throws Exception {
        System.out.println("reconnect");
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.reconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disconnect method, of class PostgreSQLManager.
     */
    @Test
    public void testDisconnect() throws Exception {
        System.out.println("disconnect");
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.disconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cerrarConexion method, of class PostgreSQLManager.
     */
    @Test
    public void testCerrarConexion_3args_1() throws Exception {
        System.out.println("cerrarConexion");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection cn = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.cerrarConexion(rs, pstmt, cn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cerrarConexion method, of class PostgreSQLManager.
     */
    @Test
    public void testCerrarConexion_3args_2() throws Exception {
        System.out.println("cerrarConexion");
        ResultSet rs = null;
        Statement stmt = null;
        Connection cn = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.cerrarConexion(rs, stmt, cn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cerrarConexion method, of class PostgreSQLManager.
     */
    @Test
    public void testCerrarConexion_3args_3() throws Exception {
        System.out.println("cerrarConexion");
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection cn = null;
        PostgreSQLManager instance = new PostgreSQLManager();
        instance.cerrarConexion(rs, cstmt, cn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}