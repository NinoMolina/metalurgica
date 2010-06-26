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
public class GestorRolTest {

    public GestorRolTest() {
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
     * Test of obtenerRoles method, of class GestorRol.
     */
    @Test
    public void testObtenerRoles() {
        System.out.println("obtenerRoles");
        long idUsuario = 1L;
        GestorRol instance = new GestorRol();
        Rol[] expResult = null;
        Rol[] result = instance.obtenerRoles(idUsuario);

    }

}