/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.CondicionIva;
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
public class GestorClienteTest {

    public GestorClienteTest() {
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
     * Test of razonSocial method, of class GestorCliente.
     */
    @Test
    public void testRazonSocial() {
        System.out.println("razonSocial");
        GestorCliente instance = new GestorCliente();
        instance.razonSocial();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numeroDeCuit method, of class GestorCliente.
     */
    @Test
    public void testNumeroDeCuit() {
        System.out.println("numeroDeCuit");
        GestorCliente instance = new GestorCliente();
        instance.numeroDeCuit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarCondicionIva method, of class GestorCliente.
     */
    @Test
    public void testBuscarCondicionIva_0args() {
        System.out.println("buscarCondicionIva");
        GestorCliente instance = new GestorCliente();
        CondicionIva[] expResult = null;
        CondicionIva[] result = instance.buscarCondicionIva();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarCondicionIva method, of class GestorCliente.
     */
    @Test
    public void testBuscarCondicionIva_JComboBox() {
        System.out.println("buscarCondicionIva");
        JComboBox combo = null;
        GestorCliente instance = new GestorCliente();
        instance.buscarCondicionIva(combo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPrioridades method, of class GestorCliente.
     */
    @Test
    public void testObtenerPrioridades() {
        System.out.println("obtenerPrioridades");
        JComboBox combo = null;
        GestorCliente instance = new GestorCliente();
        instance.obtenerPrioridades(combo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerEstados method, of class GestorCliente.
     */
    @Test
    public void testObtenerEstados() {
        System.out.println("obtenerEstados");
        JComboBox combo = null;
        GestorCliente instance = new GestorCliente();
        instance.obtenerEstados(combo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTipoDocumentos method, of class GestorCliente.
     */
    @Test
    public void testObtenerTipoDocumentos() {
        System.out.println("obtenerTipoDocumentos");
        JComboBox combo = null;
        GestorCliente instance = new GestorCliente();
        instance.obtenerTipoDocumentos(combo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerProvincias method, of class GestorCliente.
     */
    @Test
    public void testObtenerProvincias() {
        System.out.println("obtenerProvincias");
        JComboBox combo = null;
        GestorCliente instance = new GestorCliente();
        instance.obtenerProvincias(combo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarLocalidadesDeProvincia method, of class GestorCliente.
     */
    @Test
    public void testBuscarLocalidadesDeProvincia() {
        System.out.println("buscarLocalidadesDeProvincia");
        JComboBox combo = null;
        int index = 0;
        GestorCliente instance = new GestorCliente();
        instance.buscarLocalidadesDeProvincia(combo, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarBarriosDeLocalidad method, of class GestorCliente.
     */
    @Test
    public void testBuscarBarriosDeLocalidad() {
        System.out.println("buscarBarriosDeLocalidad");
        JComboBox combo = null;
        int index = 0;
        GestorCliente instance = new GestorCliente();
        instance.buscarBarriosDeLocalidad(combo, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarDomicilio method, of class GestorCliente.
     */
    @Test
    public void testRegistrarDomicilio() {
        System.out.println("registrarDomicilio");
        String calle = "";
        int numero = 0;
        int piso = 0;
        String depto = "";
        String torre = "";
        int indexBarrio = -1;
        GestorCliente instance = new GestorCliente();
        int expResult = 0;
        int result = instance.registrarDomicilio(calle, numero, piso, depto, torre, indexBarrio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarResponsable method, of class GestorCliente.
     */
    @Test
    public void testRegistrarResponsable() {
        System.out.println("registrarResponsable");
        Responsable responsable = null;
        int indexTipoDoc = 0;
        long idDomicilio = 0L;
        GestorCliente instance = new GestorCliente();
        long expResult = 0L;
        long result = instance.registrarResponsable(responsable, indexTipoDoc, idDomicilio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarCliente method, of class GestorCliente.
     */
    @Test
    public void testRegistrarCliente() {
        System.out.println("registrarCliente");
        Cliente cliente = new Cliente();

        long idResponsable = 1L;
        long idDomicilio = 1L;
        int indexEstado = 1;
        int indexCondIva = 1;
        int indexPrioridad = 1;

        cliente.setCUIT("cuit");
        cliente.setCelular("celular");
        Date fechaAlta=new Date();
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        String f=format.format(fechaAlta);
        try {
            fechaAlta = format.parse(f);
        } catch (ParseException ex) {
            Logger.getLogger(GestorClienteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente.setFechaAlta(fechaAlta);
        cliente.setFechaBaja(null);
        cliente.setMail("mail");
        cliente.setNroCliente(123);
        cliente.setRazonSocial("razon social");
        cliente.setTelefono("telefono");
        cliente.setUsuario(null);

        GestorCliente instance = new GestorCliente();
        instance.obtenerEstados(new JComboBox());
        instance.obtenerPrioridades(new JComboBox());
        instance.buscarCondicionIva(new JComboBox());
        long result = instance.registrarCliente(cliente, idResponsable, idDomicilio, indexEstado, indexCondIva, indexPrioridad);
        System.out.println(result);
        assertTrue(result>0);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of direccionCliente method, of class GestorCliente.
     */
    @Test
    public void testDireccionCliente() {
        System.out.println("direccionCliente");
        GestorCliente instance = new GestorCliente();
        instance.direccionCliente();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarBarriosExistentes method, of class GestorCliente.
     */
    @Test
    public void testBuscarBarriosExistentes() {
        System.out.println("buscarBarriosExistentes");
        GestorCliente instance = new GestorCliente();
        instance.buscarBarriosExistentes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of barrioSeleccionado method, of class GestorCliente.
     */
    @Test
    public void testBarrioSeleccionado() {
        System.out.println("barrioSeleccionado");
        GestorCliente instance = new GestorCliente();
        instance.barrioSeleccionado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarLocalidadesExistentes method, of class GestorCliente.
     */
    @Test
    public void testBuscarLocalidadesExistentes() {
        System.out.println("buscarLocalidadesExistentes");
        GestorCliente instance = new GestorCliente();
        instance.buscarLocalidadesExistentes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of localidadSeleccionada method, of class GestorCliente.
     */
    @Test
    public void testLocalidadSeleccionada() {
        System.out.println("localidadSeleccionada");
        GestorCliente instance = new GestorCliente();
        instance.localidadSeleccionada();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of datosResponsables method, of class GestorCliente.
     */
    @Test
    public void testDatosResponsables() {
        System.out.println("datosResponsables");
        GestorCliente instance = new GestorCliente();
        instance.datosResponsables();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of confirmacion method, of class GestorCliente.
     */
    @Test
    public void testConfirmacion() {
        System.out.println("confirmacion");
        GestorCliente instance = new GestorCliente();
        instance.confirmacion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarNuevoCliente method, of class GestorCliente.
     */
    @Test
    public void testRegistrarNuevoCliente() {
        System.out.println("registrarNuevoCliente");
        GestorCliente instance = new GestorCliente();
        instance.registrarNuevoCliente();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generarNumeroCliente method, of class GestorCliente.
     */
    @Test
    public void testGenerarNumeroCliente() {
        System.out.println("generarNumeroCliente");
        GestorCliente instance = new GestorCliente();
        instance.generarNumeroCliente();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarUltimoNumeroCliente method, of class GestorCliente.
     */
    @Test
    public void testBuscarUltimoNumeroCliente() {
        System.out.println("buscarUltimoNumeroCliente");
        GestorCliente instance = new GestorCliente();
        instance.buscarUltimoNumeroCliente();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerFechaActual method, of class GestorCliente.
     */
    @Test
    public void testObtenerFechaActual() {
        System.out.println("obtenerFechaActual");
        GestorCliente instance = new GestorCliente();
        instance.obtenerFechaActual();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finCasoDeUso method, of class GestorCliente.
     */
    @Test
    public void testFinCasoDeUso() {
        System.out.println("finCasoDeUso");
        GestorCliente instance = new GestorCliente();
        instance.finCasoDeUso();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancelacion method, of class GestorCliente.
     */
    @Test
    public void testCancelacion() {
        System.out.println("cancelacion");
        GestorCliente instance = new GestorCliente();
        instance.cancelacion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancelarCasoDeUso method, of class GestorCliente.
     */
    @Test
    public void testCancelarCasoDeUso() {
        System.out.println("cancelarCasoDeUso");
        GestorCliente instance = new GestorCliente();
        instance.cancelarCasoDeUso();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ingresoNuevoBarrio method, of class GestorCliente.
     */
    @Test
    public void testIngresoNuevoBarrio() {
        System.out.println("ingresoNuevoBarrio");
        GestorCliente instance = new GestorCliente();
        instance.ingresoNuevoBarrio();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of llamarCasoDeUsoRegistrarBarrio method, of class GestorCliente.
     */
    @Test
    public void testLlamarCasoDeUsoRegistrarBarrio() {
        System.out.println("llamarCasoDeUsoRegistrarBarrio");
        GestorCliente instance = new GestorCliente();
        instance.llamarCasoDeUsoRegistrarBarrio();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ingresoNuevaLocalidad method, of class GestorCliente.
     */
    @Test
    public void testIngresoNuevaLocalidad() {
        System.out.println("ingresoNuevaLocalidad");
        GestorCliente instance = new GestorCliente();
        instance.ingresoNuevaLocalidad();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of llamarCasoDeUsoRegistrarLocalidad method, of class GestorCliente.
     */
    @Test
    public void testLlamarCasoDeUsoRegistrarLocalidad() {
        System.out.println("llamarCasoDeUsoRegistrarLocalidad");
        GestorCliente instance = new GestorCliente();
        instance.llamarCasoDeUsoRegistrarLocalidad();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of noConfirmacion method, of class GestorCliente.
     */
    @Test
    public void testNoConfirmacion() {
        System.out.println("noConfirmacion");
        GestorCliente instance = new GestorCliente();
        instance.noConfirmacion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clienteSeleccionado method, of class GestorCliente.
     */
    @Test
    public void testClienteSeleccionado() {
        System.out.println("clienteSeleccionado");
        GestorCliente instance = new GestorCliente();
        instance.clienteSeleccionado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of consultaFinalizada method, of class GestorCliente.
     */
    @Test
    public void testConsultaFinalizada() {
        System.out.println("consultaFinalizada");
        GestorCliente instance = new GestorCliente();
        instance.consultaFinalizada();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of opcionImprimir method, of class GestorCliente.
     */
    @Test
    public void testOpcionImprimir() {
        System.out.println("opcionImprimir");
        GestorCliente instance = new GestorCliente();
        instance.opcionImprimir();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of datosAModificar method, of class GestorCliente.
     */
    @Test
    public void testDatosAModificar() {
        System.out.println("datosAModificar");
        GestorCliente instance = new GestorCliente();
        instance.datosAModificar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarCambios method, of class GestorCliente.
     */
    @Test
    public void testActualizarCambios() {
        System.out.println("actualizarCambios");
        GestorCliente instance = new GestorCliente();
        instance.actualizarCambios();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tomarDomicilioCliente method, of class GestorCliente.
     */
    @Test
    public void testTomarDomicilioCliente() {
        System.out.println("tomarDomicilioCliente");
        Domicilio dom = null;
        long id = 0L;
        GestorCliente instance = new GestorCliente();
        instance.tomarDomicilioCliente(dom, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tomarResponsableCliente method, of class GestorCliente.
     */
    @Test
    public void testTomarResponsableCliente() {
        System.out.println("tomarResponsableCliente");
        Responsable respNegocio = null;
        long idResponsable = 0L;
        GestorCliente instance = new GestorCliente();
        instance.tomarResponsableCliente(respNegocio, idResponsable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}