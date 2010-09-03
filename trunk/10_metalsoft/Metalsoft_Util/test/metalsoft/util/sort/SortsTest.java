/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util.sort;

import java.util.ArrayList;
import java.util.Collection;
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
public class SortsTest {

    public SortsTest() {
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
     * Test of bubbleSort method, of class Sorts.
     */
    @Test
    public void testBubbleSort() {
        System.out.println("bubbleSort");
        int[] v = null;
        Sorts.bubbleSort(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of seleccion method, of class Sorts.
     */
    @Test
    public void testSeleccion_intArr() {
        System.out.println("seleccion");
        int[] v = null;
        Sorts.seleccion(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of seleccion method, of class Sorts.
     */
    @Test
    public void testSeleccion_Collection() {
        System.out.println("seleccion");
        Collection<Comparable> c = new ArrayList<Comparable>();
        c.add(new String("D"));
        c.add(new String("Z"));
        c.add(new String("B"));
        c.add(new String("T"));
        c.add(new String("A"));
        c.add(new String("J"));
        Object[] result = Sorts.seleccion(c);
        for(int i=0;i<result.length;i++)
        {
            System.out.println(result[i]);
        }
        assertNotNull(result);
    }

    /**
     * Test of insercion method, of class Sorts.
     */
    @Test
    public void testInsercion() {
        System.out.println("insercion");
        int[] v = null;
        Sorts.insercion(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quickSort method, of class Sorts.
     */
    @Test
    public void testQuickSort() {
        System.out.println("quickSort");
        int[] v = null;
        Sorts.quickSort(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of heapSort method, of class Sorts.
     */
    @Test
    public void testHeapSort() {
        System.out.println("heapSort");
        int[] v = null;
        Sorts.heapSort(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shellSort method, of class Sorts.
     */
    @Test
    public void testShellSort() {
        System.out.println("shellSort");
        int[] v = null;
        Sorts.shellSort(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}