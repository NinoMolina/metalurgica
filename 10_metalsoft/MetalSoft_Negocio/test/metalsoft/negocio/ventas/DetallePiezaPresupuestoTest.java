/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.ventas;

import java.util.Date;
import metalsoft.util.Fecha;
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
public class DetallePiezaPresupuestoTest {

    public DetallePiezaPresupuestoTest() {
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
     * Test of getDuracionEtapaXPieza method, of class DetallePiezaPresupuesto.
     */
    @Test
    public void testGetDuracionEtapaXPieza() {
        System.out.println("getDuracionEtapaXPieza");
        DetallePiezaPresupuesto instance = new DetallePiezaPresupuesto();
        Date expResult = null;
        Date result = instance.getDuracionEtapaXPieza();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDuracionEtapaXPieza method, of class DetallePiezaPresupuesto.
     */
    @Test
    public void testSetDuracionEtapaXPieza() {
        System.out.println("setDuracionEtapaXPieza");
        Date duracionEtapaXPieza = null;
        DetallePiezaPresupuesto instance = new DetallePiezaPresupuesto();
        instance.setDuracionEtapaXPieza(duracionEtapaXPieza);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEtapa method, of class DetallePiezaPresupuesto.
     */
    @Test
    public void testGetEtapa() {
        System.out.println("getEtapa");
        DetallePiezaPresupuesto instance = new DetallePiezaPresupuesto();
        EtapaDeProduccion expResult = null;
        EtapaDeProduccion result = instance.getEtapa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEtapa method, of class DetallePiezaPresupuesto.
     */
    @Test
    public void testSetEtapa() {
        System.out.println("setEtapa");
        EtapaDeProduccion etapa = null;
        DetallePiezaPresupuesto instance = new DetallePiezaPresupuesto();
        instance.setEtapa(etapa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularDuracion method, of class DetallePiezaPresupuesto.
     */
    @Test
    public void testCalcularDuracion() {
        System.out.println("calcularDuracion");
        Date duracionEstimada = new Date();
        duracionEstimada.setHours(00);
        duracionEstimada.setMinutes(04);
        duracionEstimada.setSeconds(30);
        System.out.println(duracionEstimada);

        double alto = 1.0;
        double ancho = 1.0;
        double largo = 2.0;

        DetallePiezaPresupuesto instance = new DetallePiezaPresupuesto();
        Date result = instance.calcularDuracion(duracionEstimada, alto, ancho, largo);
        System.out.println(result);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

}