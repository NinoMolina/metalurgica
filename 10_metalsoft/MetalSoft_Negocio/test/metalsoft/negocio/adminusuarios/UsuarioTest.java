/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.adminusuarios;

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
public class UsuarioTest {

    public UsuarioTest() {
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
     * Test of getPass method, of class Usuario.
     */
    @Test
    public void testGetPass() {
        System.out.println("getPass");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getPass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPass method, of class Usuario.
     */
    @Test
    public void testSetPass() {
        System.out.println("setPass");
        String pass = "";
        Usuario instance = new Usuario();
        instance.setPass(pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRol method, of class Usuario.
     */
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        Usuario instance = new Usuario();
        Rol[] expResult = null;
        Rol[] result = instance.getRol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRol method, of class Usuario.
     */
    @Test
    public void testSetRol() {
        System.out.println("setRol");
        Rol[] rol = null;
        Usuario instance = new Usuario();
        instance.setRol(rol);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class Usuario.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUser method, of class Usuario.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String user = "";
        Usuario instance = new Usuario();
        instance.setUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarUsuario method, of class Usuario.
     */
    @Test
    public void testValidarUsuario() {
        System.out.println("validarUsuario");
        Usuario instance = new Usuario();
        instance.validarUsuario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conocerRol method, of class Usuario.
     */
    @Test
    public void testConocerRol() {
        System.out.println("conocerRol");
        Usuario instance = new Usuario();
        instance.conocerRol();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarApellido method, of class Usuario.
     */
    @Test
    public void testMostrarApellido() {
        System.out.println("mostrarApellido");
        Usuario instance = new Usuario();
        instance.mostrarApellido();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarTipoDocumento method, of class Usuario.
     */
    @Test
    public void testMostrarTipoDocumento() {
        System.out.println("mostrarTipoDocumento");
        Usuario instance = new Usuario();
        instance.mostrarTipoDocumento();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarDocumento method, of class Usuario.
     */
    @Test
    public void testMostrarDocumento() {
        System.out.println("mostrarDocumento");
        Usuario instance = new Usuario();
        instance.mostrarDocumento();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarNumeroDocumento method, of class Usuario.
     */
    @Test
    public void testMostrarNumeroDocumento() {
        System.out.println("mostrarNumeroDocumento");
        Usuario instance = new Usuario();
        instance.mostrarNumeroDocumento();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarCargo method, of class Usuario.
     */
    @Test
    public void testMostrarCargo() {
        System.out.println("mostrarCargo");
        Usuario instance = new Usuario();
        instance.mostrarCargo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tomarContrasenia method, of class Usuario.
     */
    @Test
    public void testTomarContrasenia() {
        System.out.println("tomarContrasenia");
        Usuario instance = new Usuario();
        instance.tomarContrasenia();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarPrivilegio method, of class Usuario.
     */
    @Test
    public void testMostrarPrivilegio() {
        System.out.println("mostrarPrivilegio");
        Usuario instance = new Usuario();
        instance.mostrarPrivilegio();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarRol method, of class Usuario.
     */
    @Test
    public void testMostrarRol() {
        System.out.println("mostrarRol");
        Usuario instance = new Usuario();
        instance.mostrarRol();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarPerfiles method, of class Usuario.
     */
    @Test
    public void testMostrarPerfiles() {
        System.out.println("mostrarPerfiles");
        Usuario instance = new Usuario();
        instance.mostrarPerfiles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of esUsuario method, of class Usuario.
     */
    @Test
    public void testEsUsuario() {
        System.out.println("esUsuario");
        String user = "admin";
        String pass = "admin";
        Usuario expResult = null;
        long id = Usuario.esUsuario(user, pass);
        System.out.println(id);
        assertTrue(id>0);
        // TODO review the generated test code and remove the default call to fail.

    }

}