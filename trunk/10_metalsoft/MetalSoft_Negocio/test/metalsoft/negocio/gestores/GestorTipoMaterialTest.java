/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

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
public class GestorTipoMaterialTest {

    public GestorTipoMaterialTest() {
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
     * Test of guardarTipoMaterial method, of class GestorTipoMaterial.
     */
    @Test
    public void testGuardarTipoMaterial() {
        System.out.println("guardarTipoMaterial");
        String nombre = "Plástico";
        String descripcion = "Plastico XXXXX";
        GestorTipoMaterial instance = new GestorTipoMaterial();
        int expResult = 0;
        int result = instance.guardarTipoMaterial(nombre, descripcion);
        assertTrue(result>0);
        // TODO review the generated test code and remove the default call to fail.
    }

}