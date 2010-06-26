/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.negocio.adminusuarios.Usuario;
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
public class GestorIniciarSesionDBTest {

    public GestorIniciarSesionDBTest() {
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
     * Test of esUsuario method, of class GestorIniciarSesionDB.
     */
    @Test
    public void testEsUsuario() {
        System.out.println("esUsuario");
        String user = "admin";
        String pass = "admin";
        GestorIniciarSesionDB instance = new GestorIniciarSesionDB();
        Usuario expResult = null;
        long id = instance.esUsuario(user, pass);
        assertTrue(id>0);
        // TODO review the generated test code and remove the default call to fail.
    }

}