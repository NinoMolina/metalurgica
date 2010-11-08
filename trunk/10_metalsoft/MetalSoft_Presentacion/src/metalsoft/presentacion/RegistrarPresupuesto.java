/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarCotización.java
 *
 * Created on May 11, 2010, 3:26:26 AM
 */
package metalsoft.presentacion;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.negocio.gestores.GestorPresupuesto;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewEtapasXPiezaPresupuesto;
import metalsoft.negocio.gestores.ViewMateriaPrimaXPiezaPresupuesto;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.negocio.gestores.ViewProcesoCalidadXPiezaPresupuesto;
import metalsoft.negocio.gestores.ViewProductoPresupuesto;
import metalsoft.negocio.gestores.ViewProveedorXMateriaPrima;
import metalsoft.util.Decimales;
import metalsoft.util.Fecha;
import metalsoft.util.Jornada;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky and Nino
 */
public class RegistrarPresupuesto extends javax.swing.JFrame {

    private LinkedList<ViewPedidoEnListadoProcedimientos> filasPedidos;
    private LinkedList<ViewEtapasXPiezaPresupuesto> filasEtapasXPiezaPresupuesto;
    private LinkedList<ViewMateriaPrimaXPiezaPresupuesto> filasMateriaPrimaXPiezaPresupuesto;
    private LinkedList<ViewProcesoCalidadXPiezaPresupuesto> filasProcesoCalidadXPiezaPresupuesto;
    private LinkedList<ViewProveedorXMateriaPrima> filasProveedorXMateriaPrima;
    private LinkedList<ViewProductoPresupuesto> filasProductoPresupuesto;
    private ViewProveedorXMateriaPrima currentViewProXMP;
    private GestorPresupuesto gestor;
    private int horas, minutos, segundos, dias;
    private double costoTotal;
    private double ganancia;
    private double netoTotalACobrar;
    private long idPedido;
    private double subTotal, iva;

    /** Creates new form RegistrarCotización */
    public RegistrarPresupuesto() {
        initComponents();
        addListeners();
        setearTablas();
        limpiarCampos();
        setEnabledComponents(false);
        filasPedidos = null;
        gestor = new GestorPresupuesto();
        buscarPedidosConDetalleProcesoCalidad();
    }

    private void limpiarCampos() {
        lblBrutoTotal.setText("...");
        lblCostoMateriaPrima.setText("...");
        lblCostoTotal.setText("0");
        lblDuracionProcesosCalidad.setText("...");
        lblDuracionProcesosProduccion.setText("...");
        lblGanancia.setText("...");
        lblHoraJornada.setText("...");
        lblIVA.setText("...");
        lblNroPresupuesto.setText("...");
        lblTotalACobrar.setText("...");
        if (filasEtapasXPiezaPresupuesto != null) {
            filasEtapasXPiezaPresupuesto.clear();
        }
        if (filasMateriaPrimaXPiezaPresupuesto != null) {
            filasMateriaPrimaXPiezaPresupuesto.clear();
        }
        if (filasProcesoCalidadXPiezaPresupuesto != null) {
            filasProcesoCalidadXPiezaPresupuesto.clear();
        }
        tblEtapasXPieza.updateUI();
        tblMatPrimaXPieza.updateUI();
        tblProCalidadXPieza.updateUI();
    }

    private void setEnabledComponents(boolean b) {
        beanBtnGuardar.getBtnGuardar().setEnabled(b);
        btnSeleccionarProveedor.setEnabled(b);
        btnVerDetalle.setEnabled(b);
        dccFechaEstimadaFinProduccion.setEnabled(b);
        dccFechaPresupuesto.setEnabled(b);
        dccFechaVencimiento.setEnabled(b);
    }

    private void setearTablas() {
        //DETALLE PEDIDO
        tblPedidos.setModel(new PedidoTableModel());
        tblPedidos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedidos.setShowHorizontalLines(false);
        tblPedidos.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblPedidos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //DETALLE PRODUCTO
        tblEtapasXPieza.setModel(new EtapasXPiezaPresupuestoTableModel());
        tblEtapasXPieza.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblEtapasXPieza.setShowHorizontalLines(false);
        tblEtapasXPieza.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblEtapasXPieza.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        tblMatPrimaXPieza.setModel(new MateriaPrimaXPiezaPresupuestoTableModel());
        tblMatPrimaXPieza.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblMatPrimaXPieza.setShowHorizontalLines(false);
        tblMatPrimaXPieza.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblMatPrimaXPieza.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        tblProCalidadXPieza.setModel(new ProcesoCalidadXPiezaPresupuestoTableModel());
        tblProCalidadXPieza.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblProCalidadXPieza.setShowHorizontalLines(false);
        tblProCalidadXPieza.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblProCalidadXPieza.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListeners() {
        addListenerBtnGuardar();
        addListenerBtnSalir();
        addListenerBtnSeleccionarPedido();
    }

    private void addListenerBtnSeleccionarPedido() {
        beanBtnSeleccionarPedido.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPedidoActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarPedidoActionPerformed(java.awt.event.ActionEvent evt) {
        if(tblPedidos.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido!");
            return;
        }
        limpiarCampos();
        setEnabledComponents(true);
        beanBtnGuardar.getBtnGuardar().setEnabled(false);
        ViewPedidoEnListadoProcedimientos viewPedido = filasPedidos.get(tblPedidos.getSelectedRow());
        idPedido = viewPedido.getIdpedido();
        long nroPresupuesto = gestor.buscarNroPresupuesto(idPedido);
        lblNroPresupuesto.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRESUPUESTO, nroPresupuesto));
        lblHoraJornada.setText(Jornada.HORAS_JORNADA + " horas)");
        setFechas();
        cargarTablas();
        calcularTotales();
    }

    private void addListenerBtnGuardar() {
        beanBtnGuardar.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        gestor.setFechaPresupuesto(dccFechaPresupuesto.getDate());
        gestor.setFechaVencimientoPresupuesto(dccFechaVencimiento.getDate());
        gestor.setFechaEstimadaFinProduccion(dccFechaEstimadaFinProduccion.getDate());
        gestor.setMontoTotal(lblTotalACobrar.getText());
        gestor.setProveedoresXMateriaPrima(filasMateriaPrimaXPiezaPresupuesto);
        int result = -1;
        result = gestor.guardarPresupuesto();
        int ok = -1;
        if (result > 0) {
            ok = JOptionPane.showConfirmDialog(this, "Los datos se guardaron correctamente..!\n Desea imprimir el Presupuesto?");
            limpiarCampos();
            setEnabledComponents(false);
            if (ok == JOptionPane.OK_OPTION) {
                imprimirPresupuesto();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el Presupuesto!");
        }
    }

    private void addListenerBtnSalir() {
        beanBtnSalir.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public void addProveedorXMateriaPrima(ViewProveedorXMateriaPrima view) {
        if (filasProveedorXMateriaPrima == null) {
            filasProveedorXMateriaPrima = new LinkedList<ViewProveedorXMateriaPrima>();
        }
        int contiene = ViewProveedorXMateriaPrima.contain(view, filasProveedorXMateriaPrima);
        int opcion = -1;
        currentViewProXMP = view;
        if (contiene > 0) {
            filasProveedorXMateriaPrima.set(contiene, view);
            opcion = 1;
        } else {
            boolean r = filasProveedorXMateriaPrima.add(view);
            if (r) {
                opcion = 0;
            } else {
                opcion = -1;
            }
        }
        if (opcion == 0) {
            JOptionPane.showMessageDialog(this, "Se seleccionó un proveedor para la materia prima" + filasMateriaPrimaXPiezaPresupuesto.get(tblMatPrimaXPieza.getSelectedRow()).getNombremateriaprima());
            calcularCostoTotal();
        }
        if (opcion == 1) {
            JOptionPane.showMessageDialog(this, "Se MODIFICÓ la seleccion de proveedor para la materia prima" + filasMateriaPrimaXPiezaPresupuesto.get(tblMatPrimaXPieza.getSelectedRow()).getNombremateriaprima());
            calcularCostoTotal();
        }
        if (opcion == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo seleccionar proveedor para la materia prima" + filasMateriaPrimaXPiezaPresupuesto.get(tblMatPrimaXPieza.getSelectedRow()).getNombremateriaprima());
        }
    }

    private void buscarPedidosConDetalleProcesoCalidad() {
        filasPedidos = gestor.buscarPedidosConDetalleProcesoCalidad();
        tblPedidos.updateUI();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPedidos = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionarPedido = new metalsoft.beans.BtnSeleccionar();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblCostoMateriaPrima = new javax.swing.JLabel();
        btnSeleccionarProveedor = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMatPrimaXPieza = new org.jdesktop.swingx.JXTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblDuracionProcesosCalidad = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblProCalidadXPieza = new org.jdesktop.swingx.JXTable();
        lblDuracionTotal2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblDuracionProcesosProduccion = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblEtapasXPieza = new org.jdesktop.swingx.JXTable();
        lblDuracionTotal1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblNroPresupuesto = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblDuracionTotal = new javax.swing.JLabel();
        lblCostoTotal = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        lblGanancia = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblTotalACobrar = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblBrutoTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblHoraJornada = new javax.swing.JLabel();
        dccFechaPresupuesto = new com.toedter.calendar.JDateChooser();
        dccFechaVencimiento = new com.toedter.calendar.JDateChooser();
        dccFechaEstimadaFinProduccion = new com.toedter.calendar.JDateChooser();
        btnVerDetalle = new javax.swing.JButton();
        beanBtnGuardar = new metalsoft.beans.BtnGuardar();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Presupuesto");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Presupuestar"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos A Presupuestar"));

        jScrollPane3.setViewportView(tblPedidos);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, beanBtnSeleccionarPedido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(beanBtnSeleccionarPedido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles Pedido"));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Materia Prima"));

        jLabel14.setText("Costo Por Materias Primas: $");

        lblCostoMateriaPrima.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblCostoMateriaPrima.setText("0");

        btnSeleccionarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/seleccionar.png"))); // NOI18N
        btnSeleccionarProveedor.setText("Seleccionar Proveedor");
        btnSeleccionarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProveedorActionPerformed(evt);
            }
        });

        jScrollPane6.setViewportView(tblMatPrimaXPieza);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(btnSeleccionarProveedor)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 92, Short.MAX_VALUE)
                        .add(jLabel14)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblCostoMateriaPrima, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnSeleccionarProveedor)
                    .add(lblCostoMateriaPrima)
                    .add(jLabel14)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Procesos Calidad"));

        jLabel15.setText("Duración de Procesos de Calidad:");

        lblDuracionProcesosCalidad.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblDuracionProcesosCalidad.setText("...");

        jScrollPane8.setViewportView(tblProCalidadXPieza);

        lblDuracionTotal2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDuracionTotal2.setText("(hh:mm:ss)");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel15)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblDuracionProcesosCalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblDuracionTotal2)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .add(jScrollPane8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel15)
                    .add(lblDuracionProcesosCalidad)
                    .add(lblDuracionTotal2))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedimientos Producción"));

        jLabel17.setText("Duración de Procesos de Producción:");

        lblDuracionProcesosProduccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDuracionProcesosProduccion.setText("...");

        jScrollPane7.setViewportView(tblEtapasXPieza);

        lblDuracionTotal1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDuracionTotal1.setText("(hh:mm:ss)");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel17)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblDuracionProcesosProduccion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblDuracionTotal1)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 7, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(lblDuracionProcesosProduccion)
                    .add(lblDuracionTotal1))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5)
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Presupuesto"));

        jLabel16.setText("COSTO TOTAL:    $");

        jLabel19.setText("Fecha vencimiento presupuesto:");

        jLabel18.setText("Fecha presupuesto:");

        lblNroPresupuesto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNroPresupuesto.setText("...");

        jLabel11.setText("Nro. Presupuesto:");

        jLabel20.setText("DURACIÓN TOTAL:");

        jLabel1.setText("Fecha Estimada Fin Producción:");

        lblDuracionTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDuracionTotal.setText("...");

        lblCostoTotal.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblCostoTotal.setText("0");

        jLabel21.setText("GANANCIA:         $");

        lblGanancia.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblGanancia.setText("...");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel22.setText("NETO TOTAL A COBRAR: $");

        lblTotalACobrar.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTotalACobrar.setText("...");

        jLabel4.setText("IVA (21%):          $");

        lblIVA.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblIVA.setText("...");

        jLabel23.setText("SUBTOTAL A COBRAR: $");

        lblBrutoTotal.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblBrutoTotal.setText("...");

        jLabel5.setText("(Joranda laboral de");

        lblHoraJornada.setText("...");

        btnVerDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detalle.gif"))); // NOI18N
        btnVerDetalle.setText("Ver Detalle Presupuesto");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel11)
                            .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel7Layout.createSequentialGroup()
                                    .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel19)
                                        .add(jLabel18))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, dccFechaPresupuesto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, lblNroPresupuesto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, dccFechaVencimiento, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel7Layout.createSequentialGroup()
                                    .add(jLabel1)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                    .add(dccFechaEstimadaFinProduccion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))))
                        .add(jPanel7Layout.createSequentialGroup()
                            .add(jLabel22)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(lblTotalACobrar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 172, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(280, Short.MAX_VALUE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
                            .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                                .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE))
                            .addContainerGap())
                        .add(jPanel7Layout.createSequentialGroup()
                            .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel7Layout.createSequentialGroup()
                                    .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel20)
                                        .add(jLabel16))
                                    .add(18, 18, 18)
                                    .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel7Layout.createSequentialGroup()
                                            .add(lblCostoTotal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                            .add(101, 101, 101))
                                        .add(jPanel7Layout.createSequentialGroup()
                                            .add(lblDuracionTotal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))))
                                .add(jPanel7Layout.createSequentialGroup()
                                    .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel7Layout.createSequentialGroup()
                                            .add(jLabel23)
                                            .add(18, 18, 18)
                                            .add(lblBrutoTotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
                                            .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel7Layout.createSequentialGroup()
                                                    .add(jLabel4)
                                                    .add(18, 18, 18)
                                                    .add(lblIVA, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel7Layout.createSequentialGroup()
                                                    .add(111, 111, 111)
                                                    .add(lblGanancia, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel7Layout.createSequentialGroup()
                                                    .add(jLabel21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(188, 188, 188)))
                                            .add(25, 25, 25)))
                                    .add(86, 86, 86)))
                            .add(jLabel5)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(lblHoraJornada, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
                        .add(btnVerDetalle)
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel11)
                    .add(lblNroPresupuesto))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel18)
                    .add(dccFechaPresupuesto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel19)
                    .add(dccFechaVencimiento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(dccFechaEstimadaFinProduccion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel20)
                    .add(lblDuracionTotal)
                    .add(lblHoraJornada)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel16)
                    .add(lblCostoTotal))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel21)
                    .add(lblGanancia))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel23)
                    .add(lblBrutoTotal))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(lblIVA))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                .add(btnVerDetalle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(lblTotalACobrar))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(12, 12, 12))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(beanBtnGuardar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 1110, Short.MAX_VALUE)
                        .add(beanBtnSalir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 587, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(beanBtnGuardar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(beanBtnSalir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProveedorActionPerformed
        ViewMateriaPrimaXPiezaPresupuesto view = filasMateriaPrimaXPiezaPresupuesto.get(tblMatPrimaXPieza.getSelectedRow());
        long idMatPrima = view.getIdmateriaprima();
        SeleccionarProveedor p = new SeleccionarProveedor(idMatPrima, this);
        p.setVisible(true);
        p.setLocationRelativeTo(null);
        beanBtnGuardar.getBtnGuardar().setEnabled(true);
    }//GEN-LAST:event_btnSeleccionarProveedorActionPerformed

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        RegistrarPresupuest_VerDetalle detalle = new RegistrarPresupuest_VerDetalle(filasProductoPresupuesto);
        detalle.setLocationRelativeTo(null);
        detalle.setVisible(true);
    }//GEN-LAST:event_btnVerDetalleActionPerformed

    private void imprimirPresupuesto() {
        gestor.imprimirPresupuesto();
    }

    private void cargarTablas() {
        filasEtapasXPiezaPresupuesto = gestor.buscarEtapasXPiezaPresupuesto();
        //System.out.println(filasEtapasXPiezaPresupuesto.get(0).getDuraciontotal());
        tblEtapasXPieza.updateUI();
        filasMateriaPrimaXPiezaPresupuesto = gestor.buscarMatPrimaXPiezaPresupuesto();
        tblMatPrimaXPieza.updateUI();
        filasProcesoCalidadXPiezaPresupuesto = gestor.buscarProCalidadXPiezaPresupuesto();
        tblProCalidadXPieza.updateUI();
        filasProductoPresupuesto = gestor.buscarProductosPresupuesto(idPedido);

    }

    private void setFechas() {
//        dccFechaPresupuesto.setFormat(DateFormat.MEDIUM);
        dccFechaPresupuesto.setDate(Fecha.fechaActualDate());
        Calendar c = (GregorianCalendar) Fecha.fechaActualCalendar();
        c = Fecha.addDias(c, 7);
//        dccFechaVencimiento.setFormat(DateFormat.MEDIUM);
        dccFechaVencimiento.setDate(c.getTime());
    }

    private void calcularTotales() {
        calcularDuracionEtapaXPiezaTotal();
        calcularDuracionProCalidadXPiezaTotal();
        //calcularPrecioMateriaPrimaXPiezaTotal();
        calcularDuracionTotal();
        //calcularCostoTotal();
        calcularSubTotal();
        //calcularGanancia();
        calcularIVA();
        calcularNetoTotalACobrar();
        calcularFechaFinProduccion();
    }

    private void calcularIVA() {
        iva = subTotal * 0.21;
        lblIVA.setText(Decimales.con2Decimales(iva));
    }

    private void calcularSubTotal() {
        subTotal = gestor.calcularIngresoTotal();
        lblBrutoTotal.setText(Decimales.con2Decimales(subTotal));
    }

    private void calcularNetoTotalACobrar() {
        netoTotalACobrar = subTotal + iva;
        lblTotalACobrar.setText(Decimales.con2Decimales(netoTotalACobrar));
    }

    private void calcularGanancia() {
        ganancia = subTotal - costoTotal;
        lblGanancia.setText(Decimales.con2Decimales(ganancia));
    }

    private void calcularFechaFinProduccion() {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dccFechaPresupuesto.getDate());
        c.add(Calendar.SECOND, segundos);
        c.add(Calendar.MINUTE, minutos);
        c.add(Calendar.HOUR_OF_DAY, horas);
        c.add(Calendar.DAY_OF_YEAR, dias);
        dccFechaEstimadaFinProduccion.setDate(c.getTime());
    }

    private void calcularCostoTotal() {
        ViewMateriaPrimaXPiezaPresupuesto viewMP = filasMateriaPrimaXPiezaPresupuesto.get(tblMatPrimaXPieza.getSelectedRow());
        double precio = currentViewProXMP.getPrecio();
        viewMP.setPreciomateriaprima(precio);
        viewMP.setPreciototal(precio * viewMP.getCanttotal());
        viewMP.setIdproveedor(currentViewProXMP.getIdproveedor());
        calcularPrecioMateriaPrimaXPiezaTotal();

        double costoMP = Double.parseDouble(lblCostoMateriaPrima.getText());
        costoTotal = costoMP;
        lblCostoTotal.setText(Decimales.con2Decimales(costoTotal));
        calcularGanancia();
    }

    private void calcularDuracionTotal() {
        horas = 0;
        minutos = 0;
        segundos = 0;
        dias = 0;
        String duracionEtapas = lblDuracionProcesosProduccion.getText();
        String[] hmsEtapas = duracionEtapas.split(":");
        int hE = Integer.parseInt(hmsEtapas[0]);
        int mE = Integer.parseInt(hmsEtapas[1]);
        int sE = Integer.parseInt(hmsEtapas[2]);
        String duracionProCalidad = lblDuracionProcesosCalidad.getText();
        String[] hmsProCalidad = duracionProCalidad.split(":");
        int hC = Integer.parseInt(hmsProCalidad[0]);
        int mC = Integer.parseInt(hmsProCalidad[1]);
        int sC = Integer.parseInt(hmsProCalidad[2]);

        segundos = sC + sE;
        minutos = mC + mE;
        horas = hC + hE;

        int sumarMinutos = segundos / 60;
        segundos = segundos - (60 * sumarMinutos);
        minutos += sumarMinutos;
        int sumarHoras = minutos / 60;
        minutos = minutos - (60 * sumarHoras);
        horas += sumarHoras;
        int sumarDias = horas / 24;
        horas = horas - (24 * sumarDias);
        dias = sumarDias;
        dias = (dias * 24) / Jornada.HORAS_JORNADA;
        String durTotal = dias + " dias, " + horas + " horas, " + minutos + " minutos, " + segundos + " segundos";
        lblDuracionTotal.setText(durTotal);
    }

    private void calcularDuracionEtapaXPiezaTotal() {
        Iterator<ViewEtapasXPiezaPresupuesto> iter = filasEtapasXPiezaPresupuesto.iterator();
        ViewEtapasXPiezaPresupuesto view = null;
        String durTotal = "";
        String durParcial = "";
        String[] hms = null;
        int hora = 0, minuto = 0, segundo = 0;
        while (iter.hasNext()) {
            view = iter.next();
            durParcial = view.getDuraciontotal();
            hms = durParcial.split(":");
            hora += Integer.parseInt(hms[0]);
            minuto += Integer.parseInt(hms[1]);
            segundo += Integer.parseInt(hms[2]);
        }
        int sumarMinutos = segundo / 60;
        segundo = segundo - (60 * sumarMinutos);
        minuto += sumarMinutos;
        int sumarHoras = minuto / 60;
        minuto = minuto - (60 * sumarHoras);
        hora += sumarHoras;
        durTotal = hora + ":" + minuto + ":" + segundo;
        lblDuracionProcesosProduccion.setText(durTotal);
    }

    private void calcularDuracionProCalidadXPiezaTotal() {
        Iterator<ViewProcesoCalidadXPiezaPresupuesto> iter = filasProcesoCalidadXPiezaPresupuesto.iterator();
        ViewProcesoCalidadXPiezaPresupuesto view = null;
        String durTotal = "";
        String durParcial = "";
        String[] hms = null;
        int hora = 0, minuto = 0, segundo = 0;
        while (iter.hasNext()) {
            view = iter.next();
            durParcial = view.getDuraciontotal();
            hms = durParcial.split(":");
            hora += Integer.parseInt(hms[0]);
            minuto += Integer.parseInt(hms[1]);
            segundo += Integer.parseInt(hms[2]);
        }
        int sumarMinutos = segundo / 60;
        segundo = segundo - (60 * sumarMinutos);
        minuto += sumarMinutos;
        int sumarHoras = minuto / 60;
        minuto = minuto - (60 * sumarHoras);
        hora += sumarHoras;
        durTotal = hora + ":" + minuto + ":" + segundo;
        lblDuracionProcesosCalidad.setText(durTotal);
    }

    private void calcularPrecioMateriaPrimaXPiezaTotal() {
        Iterator<ViewMateriaPrimaXPiezaPresupuesto> iter = filasMateriaPrimaXPiezaPresupuesto.iterator();
        ViewMateriaPrimaXPiezaPresupuesto view = null;
        double precioTotal = 0;
        double precioParcial = 0;

        while (iter.hasNext()) {
            view = iter.next();
            try {
                precioParcial = view.getPreciototal();
                precioTotal += precioParcial;
            } catch (Exception ex) {
            }

        }
        lblCostoMateriaPrima.setText(String.valueOf(precioTotal));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarPresupuesto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnGuardar beanBtnGuardar;
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarPedido;
    private javax.swing.JButton btnSeleccionarProveedor;
    private javax.swing.JButton btnVerDetalle;
    private com.toedter.calendar.JDateChooser dccFechaEstimadaFinProduccion;
    private com.toedter.calendar.JDateChooser dccFechaPresupuesto;
    private com.toedter.calendar.JDateChooser dccFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblBrutoTotal;
    private javax.swing.JLabel lblCostoMateriaPrima;
    private javax.swing.JLabel lblCostoTotal;
    private javax.swing.JLabel lblDuracionProcesosCalidad;
    private javax.swing.JLabel lblDuracionProcesosProduccion;
    private javax.swing.JLabel lblDuracionTotal;
    private javax.swing.JLabel lblDuracionTotal1;
    private javax.swing.JLabel lblDuracionTotal2;
    private javax.swing.JLabel lblGanancia;
    private javax.swing.JLabel lblHoraJornada;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblNroPresupuesto;
    private javax.swing.JLabel lblTotalACobrar;
    private org.jdesktop.swingx.JXTable tblEtapasXPieza;
    private org.jdesktop.swingx.JXTable tblMatPrimaXPieza;
    private org.jdesktop.swingx.JXTable tblPedidos;
    private org.jdesktop.swingx.JXTable tblProCalidadXPieza;
    // End of variables declaration//GEN-END:variables

    class PedidoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nro",
            "Nro Ped Cliente",
            "Prioridad",
            "Cliente",
            "Ped Cotizacion",
            "Cot Req Para",
            "Entrega Estipulada",
            "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewPedidoEnListadoProcedimientos view = filasPedidos.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, view.getNropedido());
                case 1:
                    return view.getNropedidocotizacioncliente();
                case 2:
                    return view.getPrioridad();
                case 3:
                    return view.getCliente();
                case 4:
                    return Fecha.parseToString(view.getFechapedidocotizacion().getTime());
                case 5:
                    return Fecha.parseToString(view.getFecharequeridacotizacion().getTime());
                case 6:
                    if (view.getFechaentregaestipulada() == null) {
                        return "";
                    } else {
                        return Fecha.parseToString(view.getFechaentregaestipulada().getTime());
                    }
                case 7:
                    return view.getEstado();
                default:
                    return null;
            }

        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasPedidos != null) {
                return filasPedidos.size();
            }
            return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];

        }
    }

    class EtapasXPiezaPresupuestoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nro Prod",
            "Nombre",
            "Cant Prod",
            "Nombre Pieza",
            "Cant Pieza",
            "Nro Et Prod",
            "Etapa",
            "Duración",
            "Dur Total",};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewEtapasXPiezaPresupuesto view = null;
            view = filasEtapasXPiezaPresupuesto.get(rowIndex);
//            try
//            {
//                view=filasEtapasXPiezaPresupuesto.get(rowIndex);
//            }
//            catch(Exception ex)
//            {
//                return "";
//            }
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRODUCTO, view.getNroproducto());
                case 1:
                    return view.getNombreproducto();
                case 2:
                    return view.getCantproducto();
                case 3:
                    return view.getNombrepieza();
                case 4:
                    return view.getCantpieza();
                case 5:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_ETAPA_PRODUCCION, view.getNroetapaproduccion());
                case 6:
                    return view.getNombreetapaproduccion();
                case 7:
                    return Fecha.parseToHourMinuteSecond(view.getDuracionetapaxpieza())+" (hh:mm:ss)";
                case 8:
                    return view.getDuraciontotal()+" (hh:mm:ss)";
                default:
                    return null;
            }

        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasEtapasXPiezaPresupuesto != null) {
                return filasEtapasXPiezaPresupuesto.size();
            }
            return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];

        }
    }

    class MateriaPrimaXPiezaPresupuestoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nro Prod",
            "Nombre",
            "Cant Prod",
            "Nombre Pieza",
            "Cant Pieza",
            "Mat Prima",
            "Precio",
            "Cant MP",
            "Cant Total",
            "Precio Total"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewMateriaPrimaXPiezaPresupuesto view = null;
            view = filasMateriaPrimaXPiezaPresupuesto.get(rowIndex);
//            try
//            {
//                view=filasMateriaPrimaXPiezaPresupuesto.get(rowIndex);
//            }
//            catch(Exception ex)
//            {
//                return "";
//            }
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRODUCTO, view.getNroproducto());
                case 1:
                    return view.getNombreproducto();
                case 2:
                    return view.getCantproducto();
                case 3:
                    return view.getNombrepieza();
                case 4:
                    return view.getCantpieza();
                case 5:
                    return view.getNombremateriaprima();
                case 6:
                    return "$ "+view.getPreciomateriaprima();
                case 7:
                    return view.getCantmateriaprima();
                case 8:
                    return view.getCanttotal();
                case 9:
                    return "$ "+view.getPreciototal();
                default:
                    return null;
            }

        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasMateriaPrimaXPiezaPresupuesto != null) {
                return filasMateriaPrimaXPiezaPresupuesto.size();
            }
            return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];

        }
    }

    class ProcesoCalidadXPiezaPresupuestoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nro Prod",
            "Nombre",
            "Cant Prod",
            "Nombre Pieza",
            "Cant Pieza",
            "Nro Pro Cal",
            "Pro Calidad",
            "Cant PC",
            "Duración",
            "Dur Total",};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewProcesoCalidadXPiezaPresupuesto view = null;
            view = filasProcesoCalidadXPiezaPresupuesto.get(rowIndex);
//            try
//            {
//                view=filasProcesoCalidadXPiezaPresupuesto.get(rowIndex);
//            }
//            catch(Exception ex)
//            {
//                return "";
//            }
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRODUCTO, view.getNroproducto());
                case 1:
                    return view.getNombreproducto();
                case 2:
                    return view.getCantproducto();
                case 3:
                    return view.getNombrepieza();
                case 4:
                    return view.getCantpieza();
                case 5:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PROCESO_CALIDAD, view.getNroprocesocalidad());
                case 6:
                    return view.getNombreprocesocalidad();
                case 7:
                    return view.getCantprocesocalidad();
                case 8:
                    return Fecha.parseToHourMinuteSecond(view.getDuracionprocalidadxpieza())+" (hh:mm:ss)";
                case 9:
                    return view.getDuraciontotal()+" (hh:mm:ss)";
                default:
                    return null;
            }

        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasProcesoCalidadXPiezaPresupuesto != null) {
                return filasProcesoCalidadXPiezaPresupuesto.size();
            }
            return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];

        }
    }
}
