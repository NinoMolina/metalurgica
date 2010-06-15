/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.negocio.adminusuarios.Rol;
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
public class GestorRolDBTest {

    public GestorRolDBTest() {
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
     * Test of obtenerRol method, of class GestorRolDB.
     */
    @Test
    public void testObtenerRol() {
        System.out.println("obtenerRol");
        long idrol = 0L;
        GestorRolDB instance = new GestorRolDB();
        Rol[] expResult = null;
        Rol[] result = instance.obtenerRol(idrol);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToRol method, of class GestorRolDB.
     */
    @Test
    public void testParseToRol_RolArr() {
        System.out.println("parseToRol");
        metalsoft.datos.dbobject.Rol[] roldb = null;
        Rol[] expResult = null;
        Rol[] result = GestorRolDB.parseToRol(roldb);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerRoles method, of class GestorRolDB.
     */
    @Test
    public void testObtenerRoles() {
        System.out.println("obtenerRoles");
        long idUsuario = 1L;
        GestorRolDB instance = new GestorRolDB();
        Rol[] expResult = null;
        Rol[] result = instance.obtenerRoles(idUsuario);
        assertNotNull(result);

    }

    /**
     * Test of parseToRol method, of class GestorRolDB.
     */
    @Test
    public void testParseToRol_Rol() {
        System.out.println("parseToRol");
        metalsoft.datos.dbobject.Rol rol = null;
        Rol expResult = null;
        Rol result = GestorRolDB.parseToRol(rol);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}