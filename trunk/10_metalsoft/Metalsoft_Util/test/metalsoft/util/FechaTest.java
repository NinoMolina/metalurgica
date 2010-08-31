/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

import com.sun.org.apache.xerces.internal.impl.dv.xs.MonthDV;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class FechaTest {

    public FechaTest() {
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
     * Test of fechaActual method, of class Fecha.
     */
    @Test
    public void testFechaActual() {
        System.out.println("fechaActual");
        String expResult = "";
        String result = Fecha.fechaActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fechaActualCalendar method, of class Fecha.
     */
    @Test
    public void testFechaActualCalendar() {
        System.out.println("fechaActualCalendar");
        Calendar expResult = null;
        Calendar result = Fecha.fechaActualCalendar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fechaHoraMinutoSegundoActual method, of class Fecha.
     */
    @Test
    public void testFechaHoraMinutoSegundoActual() {
        System.out.println("fechaHoraMinutoSegundoActual");
        String expResult = "";
        String result = Fecha.fechaHoraMinutoSegundoActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToString method, of class Fecha.
     */
    @Test
    public void testParseToString_Date() {
        System.out.println("parseToString");
        Date fecha = null;
        String expResult = "";
        String result = Fecha.parseToString(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToCalendar method, of class Fecha.
     */
    @Test
    public void testParseToCalendar_Date() {
        System.out.println("parseToCalendar");
        Date fecha = null;
        Calendar expResult = null;
        Calendar result = Fecha.parseToCalendar(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToCalendar method, of class Fecha.
     */
    @Test
    public void testParseToCalendar_String() {
        System.out.println("parseToCalendar");
        String fecha = "";
        Calendar expResult = null;
        Calendar result = Fecha.parseToCalendar(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToString method, of class Fecha.
     */
    @Test
    public void testParseToString_Date_String() {
        System.out.println("parseToString");
        Date fecha = null;
        String formatoFecha = "";
        String expResult = "";
        String result = Fecha.parseToString(fecha, formatoFecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToString method, of class Fecha.
     */
    @Test
    public void testParseToString_Date_SimpleDateFormat() {
        System.out.println("parseToString");
        Date fecha = null;
        SimpleDateFormat formato = null;
        String expResult = "";
        String result = Fecha.parseToString(fecha, formato);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToDate method, of class Fecha.
     */
    @Test
    public void testParseToDate_String() {
        System.out.println("parseToDate");
        String fecha = "";
        Date expResult = null;
        Date result = Fecha.parseToDate(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToString method, of class Fecha.
     */
    @Test
    public void testParseToString_long() {
        System.out.println("parseToString");
        long fecha = 0L;
        String expResult = "";
        String result = Fecha.parseToString(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToDate method, of class Fecha.
     */
    @Test
    public void testParseToDate_long() {
        System.out.println("parseToDate");
        long fecha = 0L;
        Date expResult = null;
        Date result = Fecha.parseToDate(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToDate method, of class Fecha.
     */
    @Test
    public void testParseToDate_String_String() {
        System.out.println("parseToDate");
        String fecha = "";
        String formatoFecha = "";
        Date expResult = null;
        Date result = Fecha.parseToDate(fecha, formatoFecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToDate method, of class Fecha.
     */
    @Test
    public void testParseToDate_String_SimpleDateFormat() {
        System.out.println("parseToDate");
        String fecha = "";
        SimpleDateFormat formato = null;
        Date expResult = null;
        Date result = Fecha.parseToDate(fecha, formato);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToDateSQL method, of class Fecha.
     */
    @Test
    public void testParseToDateSQL() {
        System.out.println("parseToDateSQL");
        Date date = null;
        java.sql.Date expResult = null;
        java.sql.Date result = Fecha.parseToDateSQL(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToTimeSQL method, of class Fecha.
     */
    @Test
    public void testParseToTimeSQL() {
        System.out.println("parseToTimeSQL");
        Date date = null;
        Time expResult = null;
        Time result = Fecha.parseToTimeSQL(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToHourMinuteSecond method, of class Fecha.
     */
    @Test
    public void testParseToHourMinuteSecond_String() {
        System.out.println("parseToHourMinuteSecond");
        String fecha = "";
        Date expResult = null;
        Date result = Fecha.parseToHourMinuteSecond(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseToHourMinuteSecond method, of class Fecha.
     */
    @Test
    public void testParseToHourMinuteSecond_Date() {
        System.out.println("parseToHourMinuteSecond");
        Date fecha = null;
        String expResult = "";
        String result = Fecha.parseToHourMinuteSecond(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of diferenciaEnDias method, of class Fecha.
     */
    @Test
    public void testDiferenciaEnDias() {
        System.out.println("diferenciaEnDias");
        Date fechaInicial = new Date();
        Date fechaFinal = new Date();
        fechaInicial.setDate(1);
        fechaInicial.setMonth(Calendar.JULY);
        int expResult = 0;
        int result = Fecha.diferenciaEnDias(fechaInicial, fechaFinal);
        System.out.println(result);
        assertTrue(result>0);

    }

    /**
     * Test of diferenciaEnMinutos method, of class Fecha.
     */
//    @Test
//    public void testDiferenciaEnMinutos() {
//        System.out.println("diferenciaEnMinutos");
//        Date fechaInicial = new Date();
//        Date fechaFinal = new Date();
//        fechaInicial.setMinutes(1);
//        fechaInicial.setHours(23);
//        int expResult = 0;
//        int result = Fecha.diferenciaEnMinutos(fechaInicial, fechaFinal);
//        System.out.println(result);
//        assertTrue(result>0);
//    }
//
//    /**
//     * Test of diferenciaEnHoras method, of class Fecha.
//     */
//    @Test
//    public void testDiferenciaEnHoras() {
//        System.out.println("diferenciaEnHoras");
//        Date fechaInicial = new Date();
//        Date fechaFinal = new Date();
//        fechaInicial.setHours(23);
//        int expResult = 0;
//        int result = Fecha.diferenciaEnMinutos(fechaInicial, fechaFinal);
//        System.out.println(result);
//        assertTrue(result>0);
//    }
//
//    /**
//     * Test of diferenciaEnSegundos method, of class Fecha.
//     */
//    @Test
//    public void testDiferenciaEnSegundos() {
//        System.out.println("diferenciaEnSegundos");
//        Date fechaInicial = new Date();
//        Date fechaFinal = new Date();
//        fechaInicial.setMinutes(1);
//        fechaInicial.setSeconds(12);
//        int expResult = 0;
//        int result = Fecha.diferenciaEnMinutos(fechaInicial, fechaFinal);
//        System.out.println(result);
//        assertTrue(result>0);
//    }

    /**
     * Test of main method, of class Fecha.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Fecha.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}