/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

import java.util.Date;
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
public class CalculosTest {

    public CalculosTest() {
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
     * Test of mayor method, of class Calculos.
     */
    @Test
    public void testMayor() {
        System.out.println("mayor");
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double expResult = 0.0;
        double result = Calculos.mayor(a, b, c);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of menor method, of class Calculos.
     */
    @Test
    public void testMenor() {
        System.out.println("menor");
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double expResult = 0.0;
        double result = Calculos.menor(a, b, c);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of medio method, of class Calculos.
     */
    @Test
    public void testMedio() {
        System.out.println("medio");
        double a = 0.0;
        double b = 0.0;
        double c = 0.0;
        double expResult = 0.0;
        double result = Calculos.medio(a, b, c);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularCantidadMateriaPrima method, of class Calculos.
     */
    @Test
    public void testCalcularCantidadMateriaPrima() {
        System.out.println("calcularCantidadMateriaPrima");
        int capacidadMatPrima = 2;
        int cantPiezas = 2;
        
        int result = Calculos.calcularCantidadMateriaPrima(capacidadMatPrima, cantPiezas);
        System.out.println(result);
        assertTrue(result>0);
    }

    @Test
    public void testCalcularDuracionPiezaXEtapa() {
        System.out.println("calcularDuracionPiezaXEtapa");
        int capacidadMatPrima = 2;
        int cantPiezas = 2;
        Date duracionEstimada=new Date();
        duracionEstimada.setHours(00);
        duracionEstimada.setMinutes(0);
        duracionEstimada.setSeconds(30);
        Date result = Calculos.calcularDuracionPiezaXEtapa(duracionEstimada, 10, 5, 5);
        System.out.println(Fecha.parseToHourMinuteSecond(result));
        assertTrue(result!=null);
    }

}