/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.datos.dbobject.TipomaterialPK;
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
public class TipomaterialDAOImplTest {

    public TipomaterialDAOImplTest() {
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
     * Test of delete method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        TipomaterialPK tipomaterialpk = null;
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        int expResult = 0;
        int result = instance.delete(tipomaterialpk, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        TipomaterialPK tipomaterialpk = null;
        Tipomaterial tipomaterial = null;
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        int expResult = 0;
        int result = instance.update(tipomaterialpk, tipomaterial, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Tipomaterial tipomaterial = new Tipomaterial();
        tipomaterial.setNombre("Plastico");
        tipomaterial.setDescripcion("Plastico duro");
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection con = pg.concectGetCn();
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        int expResult = 1;
        int result = instance.insert(tipomaterial, con);
        con.close();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findByPrimaryKey method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFindByPrimaryKey_int_Connection() throws Exception {
        System.out.println("findByPrimaryKey");
        int idtipomaterial = 0;
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial expResult = null;
        Tipomaterial result = instance.findByPrimaryKey(idtipomaterial, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPrimaryKey method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFindByPrimaryKey_TipomaterialPK_Connection() throws Exception {
        System.out.println("findByPrimaryKey");
        TipomaterialPK tipomaterialpk = null;
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial expResult = null;
        Tipomaterial result = instance.findByPrimaryKey(tipomaterialpk, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByIdtipomaterial method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFindByIdtipomaterial() throws Exception {
        System.out.println("findByIdtipomaterial");
        int idtipomaterial = 0;
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial[] expResult = null;
        Tipomaterial[] result = instance.findByIdtipomaterial(idtipomaterial, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByNombre method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFindByNombre() throws Exception {
        System.out.println("findByNombre");
        String nombre = "";
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial[] expResult = null;
        Tipomaterial[] result = instance.findByNombre(nombre, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDescripcion method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFindByDescripcion() throws Exception {
        System.out.println("findByDescripcion");
        String descripcion = "";
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial[] expResult = null;
        Tipomaterial[] result = instance.findByDescripcion(descripcion, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial[] expResult = null;
        Tipomaterial[] result = instance.findAll(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findExecutingUserSelect method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFindExecutingUserSelect() throws Exception {
        System.out.println("findExecutingUserSelect");
        String selectStatement = "";
        Object[] sqlParams = null;
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial[] expResult = null;
        Tipomaterial[] result = instance.findExecutingUserSelect(selectStatement, sqlParams, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findExecutingUserWhere method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFindExecutingUserWhere() throws Exception {
        System.out.println("findExecutingUserWhere");
        String whereClause = "";
        Object[] sqlParams = null;
        Connection con = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial[] expResult = null;
        Tipomaterial[] result = instance.findExecutingUserWhere(whereClause, sqlParams, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchSingleResult method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFetchSingleResult() throws Exception {
        System.out.println("fetchSingleResult");
        ResultSet rs = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial expResult = null;
        Tipomaterial result = instance.fetchSingleResult(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateVO method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testPopulateVO() throws Exception {
        System.out.println("populateVO");
        Tipomaterial dto = null;
        ResultSet rs = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        instance.populateVO(dto, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchMultiResults method, of class TipomaterialDAOImpl.
     */
    @Test
    public void testFetchMultiResults() throws Exception {
        System.out.println("fetchMultiResults");
        ResultSet rs = null;
        TipomaterialDAOImpl instance = new TipomaterialDAOImpl();
        Tipomaterial[] expResult = null;
        Tipomaterial[] result = instance.fetchMultiResults(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}