/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Prueba;
import metalsoft.datos.dbobject.PruebaPK;
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
public class PruebaDAOImplTest {

    public PruebaDAOImplTest() {
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
     * Test of insert method, of class PruebaDAOImpl.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Prueba prueba = new Prueba();
        Connection con = new PostgreSQLManager().concectGetCn();
        prueba.setValor("prueba");
        PruebaDAOImpl instance = new PruebaDAOImpl();
        int expResult = 1;
        int result = instance.insert(prueba, con);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    /**
     * Test of delete method, of class PruebaDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        PruebaPK pruebapk = new PruebaPK();
        pruebapk.setId(1);
        Connection con = new PostgreSQLManager().concectGetCn();
        PruebaDAOImpl instance = new PruebaDAOImpl();
        int expResult = 1;
        int result = instance.delete(pruebapk, con);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of update method, of class PruebaDAOImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        PruebaPK pruebapk = null;
        Prueba prueba = null;
        Connection con = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        int expResult = 0;
        int result = instance.update(pruebapk, prueba, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    

    /**
     * Test of findByPrimaryKey method, of class PruebaDAOImpl.
     */
    @Test
    public void testFindByPrimaryKey_int_Connection() throws Exception {
        System.out.println("findByPrimaryKey");
        int id = 0;
        Connection con = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba expResult = null;
        Prueba result = instance.findByPrimaryKey(id, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPrimaryKey method, of class PruebaDAOImpl.
     */
    @Test
    public void testFindByPrimaryKey_PruebaPK_Connection() throws Exception {
        System.out.println("findByPrimaryKey");
        PruebaPK pruebapk = null;
        Connection con = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba expResult = null;
        Prueba result = instance.findByPrimaryKey(pruebapk, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class PruebaDAOImpl.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        int id = 0;
        Connection con = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba[] expResult = null;
        Prueba[] result = instance.findById(id, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByValor method, of class PruebaDAOImpl.
     */
    @Test
    public void testFindByValor() throws Exception {
        System.out.println("findByValor");
        String valor = "";
        Connection con = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba[] expResult = null;
        Prueba[] result = instance.findByValor(valor, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class PruebaDAOImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        Connection con = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba[] expResult = null;
        Prueba[] result = instance.findAll(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findExecutingUserSelect method, of class PruebaDAOImpl.
     */
    @Test
    public void testFindExecutingUserSelect() throws Exception {
        System.out.println("findExecutingUserSelect");
        String selectStatement = "";
        Object[] sqlParams = null;
        Connection con = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba[] expResult = null;
        Prueba[] result = instance.findExecutingUserSelect(selectStatement, sqlParams, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findExecutingUserWhere method, of class PruebaDAOImpl.
     */
    @Test
    public void testFindExecutingUserWhere() throws Exception {
        System.out.println("findExecutingUserWhere");
        String whereClause = "";
        Object[] sqlParams = null;
        Connection con = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba[] expResult = null;
        Prueba[] result = instance.findExecutingUserWhere(whereClause, sqlParams, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchSingleResult method, of class PruebaDAOImpl.
     */
    @Test
    public void testFetchSingleResult() throws Exception {
        System.out.println("fetchSingleResult");
        ResultSet rs = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba expResult = null;
        Prueba result = instance.fetchSingleResult(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateVO method, of class PruebaDAOImpl.
     */
    @Test
    public void testPopulateVO() throws Exception {
        System.out.println("populateVO");
        Prueba dto = null;
        ResultSet rs = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        instance.populateVO(dto, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchMultiResults method, of class PruebaDAOImpl.
     */
    @Test
    public void testFetchMultiResults() throws Exception {
        System.out.println("fetchMultiResults");
        ResultSet rs = null;
        PruebaDAOImpl instance = new PruebaDAOImpl();
        Prueba[] expResult = null;
        Prueba[] result = instance.fetchMultiResults(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}