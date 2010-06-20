/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.dao;

import metalsoft.datos.PostgreSQLManager;
import java.sql.Connection;
import java.sql.ResultSet;
import metalsoft.datos.dbobject.Usuario;
import metalsoft.datos.dbobject.UsuarioPK;
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
public class UsuarioDAOImplTest {

    public UsuarioDAOImplTest() {
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
     * Test of delete method, of class UsuarioDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        UsuarioPK usuariopk = null;
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        int expResult = 0;
        int result = instance.delete(usuariopk, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class UsuarioDAOImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        UsuarioPK usuariopk = null;
        Usuario usuario = null;
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        int expResult = 0;
        int result = instance.update(usuariopk, usuario, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class UsuarioDAOImpl.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Usuario usuario = null;
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        int expResult = 0;
        int result = instance.insert(usuario, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPrimaryKey method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindByPrimaryKey_long_Connection() throws Exception {
        System.out.println("findByPrimaryKey");
        long idusuario = 0L;
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario expResult = null;
        Usuario result = instance.findByPrimaryKey(idusuario, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPrimaryKey method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindByPrimaryKey_UsuarioPK_Connection() throws Exception {
        System.out.println("findByPrimaryKey");
        UsuarioPK usuariopk = null;
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario expResult = null;
        Usuario result = instance.findByPrimaryKey(usuariopk, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByIdusuario method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindByIdusuario() throws Exception {
        System.out.println("findByIdusuario");
        long idusuario = 0L;
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario[] expResult = null;
        Usuario[] result = instance.findByIdusuario(idusuario, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsuario method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindByUsuario() throws Exception {
        System.out.println("findByUsuario");
        String usuario = "";
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario[] expResult = null;
        Usuario[] result = instance.findByUsuario(usuario, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByClave method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindByClave() throws Exception {
        System.out.println("findByClave");
        String clave = "";
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario[] expResult = null;
        Usuario[] result = instance.findByClave(clave, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario[] expResult = null;
        Usuario[] result = instance.findAll(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findExecutingUserSelect method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindExecutingUserSelect() throws Exception {
        System.out.println("findExecutingUserSelect");
        String selectStatement = "";
        Object[] sqlParams = null;
        Connection con = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario[] expResult = null;
        Usuario[] result = instance.findExecutingUserSelect(selectStatement, sqlParams, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findExecutingUserWhere method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFindExecutingUserWhere() throws Exception {
        System.out.println("findExecutingUserWhere");
        String user="admin";
        String pass="admin";
        String whereClause = "usuario=? AND clave=?";
        Object[] sqlParams = new Object[2];
        sqlParams[0]="admin";
        sqlParams[1]="admin";
        Connection con = new PostgreSQLManager().concectGetCn();
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario[] expResult = null;
        Usuario[] result = instance.findExecutingUserWhere(whereClause, sqlParams, con);
        assertNotNull(result);

        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of fetchSingleResult method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFetchSingleResult() throws Exception {
        System.out.println("fetchSingleResult");
        ResultSet rs = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario expResult = null;
        Usuario result = instance.fetchSingleResult(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateVO method, of class UsuarioDAOImpl.
     */
    @Test
    public void testPopulateVO() throws Exception {
        System.out.println("populateVO");
        Usuario dto = null;
        ResultSet rs = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        instance.populateVO(dto, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchMultiResults method, of class UsuarioDAOImpl.
     */
    @Test
    public void testFetchMultiResults() throws Exception {
        System.out.println("fetchMultiResults");
        ResultSet rs = null;
        UsuarioDAOImpl instance = new UsuarioDAOImpl();
        Usuario[] expResult = null;
        Usuario[] result = instance.fetchMultiResults(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}