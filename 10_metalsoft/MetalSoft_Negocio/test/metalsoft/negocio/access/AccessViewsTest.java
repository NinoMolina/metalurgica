/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.gestores.IdsEstadoPedido;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
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
public class AccessViewsTest {

    public AccessViewsTest() {
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
     * Test of detallePedidoCotizacion method, of class AccessViews.
     */
    @Test
    public void testDetallePedidoCotizacion() {
        System.out.println("detallePedidoCotizacion");
        long id = 0L;
        Connection cn = null;
        ViewDetallePedidoCotizacion expResult = null;
        ViewDetallePedidoCotizacion result = AccessViews.detallePedidoCotizacion(id, cn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pedidoEnListadoProcedimientos method, of class AccessViews.
     */
    @Test
    public void testPedidoEnListadoProcedimientos() {
        System.out.println("pedidoEnListadoProcedimientos");
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            LinkedList expResult = null;
            LinkedList result = AccessViews.pedidosSegunEstado(IdsEstadoPedido.GENERADO,cn);
            ViewPedidoEnListadoProcedimientos view=(ViewPedidoEnListadoProcedimientos)result.getFirst();
            System.out.println(view.getFechapedidocotizacion());
            assertNotNull(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessViewsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(AccessViewsTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}