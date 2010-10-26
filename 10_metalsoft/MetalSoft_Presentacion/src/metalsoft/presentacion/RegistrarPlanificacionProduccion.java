/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarPlanificacionProduccion.java
 *
 * Created on 10/10/2010, 16:22:57
 */
package metalsoft.presentacion;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;
import metalsoft.negocio.gestores.GestorRegistrarPlanificacionProduccion;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewPedidoNoPlanificado;
import metalsoft.util.Decimales;
import metalsoft.util.Fecha;
import metalsoft.util.Jornada;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimePeriodAnchor;
import pojos.Detallepiezapresupuesto;
import pojos.Detalleplanificacionproduccion;
import pojos.Detallepresupuesto;
import pojos.Detalleproductopresupuesto;
import pojos.Disponibilidadhoraria;
import pojos.Empleado;
import pojos.Etapadeproduccion;
import pojos.Maquina;
import pojos.Pedido;
import pojos.Pieza;
import pojos.Planificacioncalidad;
import pojos.Planificacionproduccion;
import pojos.Presupuesto;
import pojos.Producto;

/**
 *
 * @author Nino
 */
public class RegistrarPlanificacionProduccion extends javax.swing.JFrame {

    /** Creates new form RegistrarPlanificacionProduccion */
    private LinkedList<ViewPedidoNoPlanificado> filasPedidosNoPlanificados;
    private GestorRegistrarPlanificacionProduccion gestor;
    private Presupuesto presupuesto;
    private int columnCountTreeTable = 3;
    private ViewPedidoNoPlanificado viewPedidoSeleccionado;
    private ArrayList<String> listColumnNamesTreeTable;
    private HashMap<String, JPanel> hashPanels;
    private List<Empleado> lstEmpleados;
    private List<Maquina> lstMaquinas;
    private List<entity.Planificacionproduccion> lstPlanificacionProduccion;
    private Maquina maquinaSeleccionada;
    private Empleado empleadoSeleccionado;

    public RegistrarPlanificacionProduccion() {
        initComponents();
        iniciarPaneles();
        setEnableHyperLink(false);
        listColumnNamesTreeTable = new ArrayList<String>();
        listColumnNamesTreeTable.add("Detalle");
        listColumnNamesTreeTable.add("Empleado");
        listColumnNamesTreeTable.add("Máquinas");
        setearTablaPedidos();
        gestor = new GestorRegistrarPlanificacionProduccion();
        filasPedidosNoPlanificados = new LinkedList<ViewPedidoNoPlanificado>();
        buscarPedidosNoPlanificados();
        cargarGantt();
        iniciarTreeTable();
        tblPedidos.updateUI();
        tskPanel.setTitle("Asignaciones");
        tskPanel.setAnimated(true);
    }

    private void cargarGantt() {
    }

    private void setEnableHyperLink(boolean flag) {
        tskPanel.setEnabled(flag);
        hplAsignarEmpleado.setEnabled(flag);
        hplAsignarMaquinas.setEnabled(flag);
    }

    private void iniciarPaneles() {
        hashPanels = new HashMap<String, JPanel>();
        hashPanels.put(pnlTreeTable.getName(), pnlTreeTable);
        hashPanels.put(pnlEmpleado.getName(), pnlEmpleado);
        hashPanels.put(pnlMaquinas.getName(), pnlMaquinas);
        hashPanels.put(pnlDisponibilidad.getName(), pnlDisponibilidad);
        setVisiblePanel(pnlTreeTable.getName());
    }

    private void setVisiblePanel(String namePanel) {
        Collection<JPanel> collection = hashPanels.values();
        for (JPanel jPanel : collection) {
            jPanel.setVisible(false);
        }
        hashPanels.get(namePanel).setVisible(true);
    }

    private void iniciarTreeTable() {
        DefaultMutableTreeTableNode raiz = new DefaultMutableTreeTableNode("");
//        DefaultMutableTreeTableNode n1 = new DefaultMutableTreeTableNode("AAAAAA");
//        raiz.add(n1);
//        n1.add(new DefaultMutableTreeTableNode("algo"));

        TreeTableModel treeTableModel = new DefaultTreeTableModel(raiz, listColumnNamesTreeTable);
//        treeTableModel.setValueAt("changos", raiz, 2);
//        ProductoNode n1 = new ProductoNode(new Producto(WIDTH, Long.MIN_VALUE, "Producto1", Double.NaN, null, null, null, null, null, null));
//        raiz.add(n1);
        trtDetalleProcProd.setSelectionMode(SelectionMode.SINGLE_SELECTION.ordinal());
        trtDetalleProcProd.setTreeTableModel(treeTableModel);
        trtDetalleProcProd.setRootVisible(true);
    }

    private void setearTablaPedidos() {
        tblPedidos.setModel(new PedidoNoPlanificadoTableModel());
        tblPedidos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedidos.setShowHorizontalLines(false);
        tblPedidos.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblPedidos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void buscarPedidosNoPlanificados() {
        filasPedidosNoPlanificados = gestor.buscarPedidosNoPlanificados();
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
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtValorBusqueda = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new org.jdesktop.swingx.JXTable();
        jPanel3 = new javax.swing.JPanel();
        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        tskPanel = new org.jdesktop.swingx.JXTaskPane();
        hplAsignarEmpleado = new org.jdesktop.swingx.JXHyperlink();
        hplAsignarMaquinas = new org.jdesktop.swingx.JXHyperlink();
        hplVerDisponibilidad = new org.jdesktop.swingx.JXHyperlink();
        hplVerPlanificacion = new org.jdesktop.swingx.JXHyperlink();
        pnl = new javax.swing.JPanel();
        pnlTreeTable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        trtDetalleProcProd = new org.jdesktop.swingx.JXTreeTable();
        pnlEmpleado = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlstEmpleados = new org.jdesktop.swingx.JXList();
        btnSeleccionarEmpleado = new javax.swing.JButton();
        pnlDispHoraria = new javax.swing.JPanel();
        btnAsignarEmpleado = new javax.swing.JButton();
        pnlMaquinas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jlstMaquinas = new org.jdesktop.swingx.JXList();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAsignarMaquina = new javax.swing.JButton();
        pnlDisponibilidad = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Planificación");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Confirmados"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar Datos"));

        jRadioButton3.setText("Nro Pedido");

        jLabel1.setText("Valor de Búsqueda:");

        jRadioButton2.setText("Fecha Pedido");

        jRadioButton1.setText("Cliente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton3)
                .addComponent(jRadioButton2)
                .addComponent(jRadioButton1)
                .addComponent(jLabel1)
                .addComponent(txtValorBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tblPedidos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Planificación"));

        hplAsignarEmpleado.setText("Asignar Empleado");
        hplAsignarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hplAsignarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hplAsignarEmpleadoActionPerformed(evt);
            }
        });
        tskPanel.getContentPane().add(hplAsignarEmpleado);

        hplAsignarMaquinas.setText("Asignar Máquinas");
        hplAsignarMaquinas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hplAsignarMaquinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hplAsignarMaquinasActionPerformed(evt);
            }
        });
        tskPanel.getContentPane().add(hplAsignarMaquinas);

        hplVerDisponibilidad.setText("Ver Disponibilidad");
        hplVerDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hplVerDisponibilidadActionPerformed(evt);
            }
        });
        tskPanel.getContentPane().add(hplVerDisponibilidad);

        hplVerPlanificacion.setText("Ver Planificación");
        hplVerPlanificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hplVerPlanificacionActionPerformed(evt);
            }
        });
        tskPanel.getContentPane().add(hplVerPlanificacion);

        jXTaskPaneContainer1.add(tskPanel);

        pnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTreeTable.setName("pnlTreeTable"); // NOI18N

        jScrollPane2.setViewportView(trtDetalleProcProd);

        javax.swing.GroupLayout pnlTreeTableLayout = new javax.swing.GroupLayout(pnlTreeTable);
        pnlTreeTable.setLayout(pnlTreeTableLayout);
        pnlTreeTableLayout.setHorizontalGroup(
            pnlTreeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );
        pnlTreeTableLayout.setVerticalGroup(
            pnlTreeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreeTableLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl.add(pnlTreeTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 340));

        pnlEmpleado.setName("pnlEmpleado"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));

        jScrollPane3.setViewportView(jlstEmpleados);

        btnSeleccionarEmpleado.setText("Ver Disponibilidad");
        btnSeleccionarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionarEmpleado)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSeleccionarEmpleado)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDispHoraria.setBorder(javax.swing.BorderFactory.createTitledBorder("Disponibilidad Horaria"));

        javax.swing.GroupLayout pnlDispHorariaLayout = new javax.swing.GroupLayout(pnlDispHoraria);
        pnlDispHoraria.setLayout(pnlDispHorariaLayout);
        pnlDispHorariaLayout.setHorizontalGroup(
            pnlDispHorariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
        );
        pnlDispHorariaLayout.setVerticalGroup(
            pnlDispHorariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );

        btnAsignarEmpleado.setText("Asignar");
        btnAsignarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEmpleadoLayout = new javax.swing.GroupLayout(pnlEmpleado);
        pnlEmpleado.setLayout(pnlEmpleadoLayout);
        pnlEmpleadoLayout.setHorizontalGroup(
            pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEmpleadoLayout.createSequentialGroup()
                .addGroup(pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDispHoraria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEmpleadoLayout.createSequentialGroup()
                        .addContainerGap(601, Short.MAX_VALUE)
                        .addComponent(btnAsignarEmpleado)))
                .addContainerGap())
        );
        pnlEmpleadoLayout.setVerticalGroup(
            pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpleadoLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDispHoraria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAsignarEmpleado)
                .addContainerGap())
        );

        pnl.add(pnlEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 340));

        pnlMaquinas.setName("pnlMaquinas"); // NOI18N

        jlstMaquinas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jlstMaquinas);

        jLabel2.setText("Filtro:");

        btnAsignarMaquina.setText("Asignar");
        btnAsignarMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarMaquinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMaquinasLayout = new javax.swing.GroupLayout(pnlMaquinas);
        pnlMaquinas.setLayout(pnlMaquinasLayout);
        pnlMaquinasLayout.setHorizontalGroup(
            pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaquinasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addGroup(pnlMaquinasLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAsignarMaquina, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pnlMaquinasLayout.setVerticalGroup(
            pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaquinasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAsignarMaquina)
                .addContainerGap())
        );

        pnl.add(pnlMaquinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 340));

        pnlDisponibilidad.setName("pnlDisponibilidad"); // NOI18N

        javax.swing.GroupLayout pnlDisponibilidadLayout = new javax.swing.GroupLayout(pnlDisponibilidad);
        pnlDisponibilidad.setLayout(pnlDisponibilidadLayout);
        pnlDisponibilidadLayout.setHorizontalGroup(
            pnlDisponibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        pnlDisponibilidadLayout.setVerticalGroup(
            pnlDisponibilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        pnl.add(pnlDisponibilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 340));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        viewPedidoSeleccionado = filasPedidosNoPlanificados.get(tblPedidos.getSelectedRow());
        presupuesto = gestor.buscarPresupuesto(viewPedidoSeleccionado.getIdpresupuesto());
        setVisiblePanel(pnlTreeTable.getName());
        setEnableHyperLink(true);
        cargarDatosTreeTable(presupuesto.getDetallepresupuestos());
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void hplAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarEmpleadoActionPerformed
        setVisiblePanel(pnlEmpleado.getName());
        lstEmpleados = gestor.obtenerEmpleados();
        cargarLista(jlstEmpleados, lstEmpleados.toArray());
    }//GEN-LAST:event_hplAsignarEmpleadoActionPerformed

    private void btnAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarEmpleadoActionPerformed
        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();
        if (obj instanceof EtapaProduccionNode) {
            EtapaProduccionNode node = (EtapaProduccionNode) obj;
            node.setEmpleado(empleadoSeleccionado);
        }
        setVisiblePanel(pnlTreeTable.getName());
    }//GEN-LAST:event_btnAsignarEmpleadoActionPerformed

    private void btnSeleccionarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarEmpleadoActionPerformed
        empleadoSeleccionado = (Empleado) jlstEmpleados.getSelectedValue();
        //validar que disp horaria no sea null
        try {
//            lstDisponibilidad.setListData(empleadoSeleccionado.getDisponibilidadhorarias().toArray());
            Set<Disponibilidadhoraria> disp = empleadoSeleccionado.getDisponibilidadhorarias();
            TaskSeriesCollection dataset = new TaskSeriesCollection();
            TaskSeries unavailable = new TaskSeries("Disponibilidad");

            for (Disponibilidadhoraria dispHoraria : disp) {
                //creo una nueva tarea
                Date fecha = dispHoraria.getFecha();
                Task task = new Task(Fecha.parseToString(fecha),
                        new GregorianCalendar(2010, 11, 1, 0, 0, 0).getTime(),
                        new GregorianCalendar(2010, 11, 1, Jornada.HORAS_JORNADA, 0, 0).getTime());
                //agrego la tarea la serie de taras
                unavailable.add(task);

                task.addSubtask(new Task("",
                        new GregorianCalendar(2010, 11, 1, 0, 0, 0).getTime(),
                        new GregorianCalendar(2010, 11, 1, Jornada.HORAS_JORNADA-dispHoraria.getTiempodisponible().getHours(), 60-dispHoraria.getTiempodisponible().getMinutes(), 0).getTime()));
            }

            dataset.add(unavailable);

// title, domain axis, range axis, dataset, legend, tooltip, urls
            JFreeChart chart = ChartFactory.createGanttChart("", "Días", "Horas", dataset, true, true, false);
            ChartPanel chartPanel = new ChartPanel(chart);
            chart.setBorderVisible(true);
            chartPanel.setBounds(10, 15, pnlDispHoraria.getWidth()-20, pnlDispHoraria.getHeight()-30);
            pnlDispHoraria.removeAll();
            pnlDispHoraria.add(chartPanel);
            pnlDispHoraria.updateUI();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("entro en el catch de lstDisponibilidad");
        }
    }//GEN-LAST:event_btnSeleccionarEmpleadoActionPerformed

    private void hplAsignarMaquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarMaquinasActionPerformed
        setVisiblePanel(pnlMaquinas.getName());
        lstMaquinas = gestor.obtenerMaquinas();
        cargarLista(jlstMaquinas, lstMaquinas.toArray());
    }//GEN-LAST:event_hplAsignarMaquinasActionPerformed

    private void btnAsignarMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarMaquinaActionPerformed
        maquinaSeleccionada = (Maquina) jlstMaquinas.getSelectedValue();
        //obtengo el nodo seleccionado
        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();
        //el nodo seleccionado tiene que ser uno de etapaproduccion
        if (obj instanceof EtapaProduccionNode) {
            EtapaProduccionNode node = (EtapaProduccionNode) obj;
            node.setMaquina(maquinaSeleccionada);
        }
        setVisiblePanel(pnlTreeTable.getName());
    }//GEN-LAST:event_btnAsignarMaquinaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        Planificacionproduccion planificacionproduccion = new Planificacionproduccion();
        Set<Detalleplanificacionproduccion> setDetalle = new HashSet<Detalleplanificacionproduccion>();
        Detalleplanificacionproduccion detalleplanificacionproduccion = null;
        Pedido pedido = gestor.buscarPedido(viewPedidoSeleccionado.getIdpedido());
        int filas = trtDetalleProcProd.getRowCount();
        TreePath tp = null;
        for (int i = 0; i < filas; i++) {
            tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();
            if (obj instanceof EtapaProduccionNode) {
                EtapaProduccionNode etapaProduccionNode = (EtapaProduccionNode) obj;
                PiezaNode piezaNode = (PiezaNode) etapaProduccionNode.getParent();
                detalleplanificacionproduccion = new Detalleplanificacionproduccion();
                detalleplanificacionproduccion.setEmpleado(etapaProduccionNode.getEmpleado());
                detalleplanificacionproduccion.setMaquina(etapaProduccionNode.getMaquina());
                detalleplanificacionproduccion.setEtapadeproduccion(etapaProduccionNode.getEtapa());
                detalleplanificacionproduccion.setPieza(piezaNode.getPieza());
                setDetalle.add(detalleplanificacionproduccion);
            }
        }
        planificacionproduccion.setPedido(pedido);
        planificacionproduccion.setDetalleplanificacionproduccions(setDetalle);
        planificacionproduccion.setFechainicioprevista(viewPedidoSeleccionado.getFechapresupuesto());
        planificacionproduccion.setFechafinprevista(viewPedidoSeleccionado.getFechaentregaestipulada());
        planificacionproduccion.setFechacreacion(Fecha.fechaActualDate());
        try {
            gestor.guardarPlanificacionProduccion(planificacionproduccion);
            gestor.actualizarEstadoPedido(pedido);
            JOptionPane.showMessageDialog(this, "Los datos se guardaron CORRECTAMENTE!");
            filasPedidosNoPlanificados.remove(viewPedidoSeleccionado);
            tblPedidos.updateUI();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos\nNo se pudo guardar!!");

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void limpiarCampos() {
        trtDetalleProcProd.removeAll();
        iniciarTreeTable();
        jlstEmpleados.removeAll();
        jlstMaquinas.removeAll();
        txtValorBusqueda.setText("");
        setEnableHyperLink(false);
        setVisiblePanel(pnlTreeTable.getName());
    }
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
//        gestor.limpiarSessionHibernate();
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        gestor.limpiarSessionHibernate();
    }//GEN-LAST:event_formWindowClosing

    private void hplVerDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplVerDisponibilidadActionPerformed
        pnlDisponibilidad.removeAll();
        lstPlanificacionProduccion = gestor.buscarPlanificacionesProduccion();
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries unavailable = new TaskSeries("Etapas Producción");

        for (entity.Planificacionproduccion planificacion : lstPlanificacionProduccion) {
            //creo una nueva tarea a agregar, las tareas son las planificaciones
            Task task = new Task(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PLANIF_PRODUCCION, planificacion.getNroplanificacion()),
                    planificacion.getFechainicioprevista(),
                    planificacion.getFechafinprevista());
            //agrego la tarea la serie de taras
            unavailable.add(task);
            //busco los detalles, donde saco la etapa a ejecutar.
            Set<entity.Detalleplanificacionproduccion> setDetalle = planificacion.getDetalleplanificacionproduccionSet();
            for (entity.Detalleplanificacionproduccion detalle : setDetalle) {
                GregorianCalendar inicio = new GregorianCalendar();
                inicio.setTime(detalle.getFechainicio());
                inicio.set(Calendar.HOUR, detalle.getHorainicio().getHours());
                inicio.set(Calendar.MINUTE, detalle.getHorainicio().getMinutes());
                inicio.set(Calendar.SECOND, detalle.getHorainicio().getSeconds());
                System.out.println("inicio:" + Fecha.parseToString(inicio.getTime(), "dd/MM/yyyy hh:mm:ss"));
                GregorianCalendar fin = new GregorianCalendar();
                fin.setTime(detalle.getFechafin());
                fin.set(Calendar.HOUR, detalle.getHorafin().getHours());
                fin.set(Calendar.MINUTE, detalle.getHorafin().getMinutes());
                fin.set(Calendar.SECOND, detalle.getHorafin().getSeconds());
                System.out.println("fin:" + Fecha.parseToString(fin.getTime(), "dd/MM/yyyy hh:mm:ss"));
                task.addSubtask(new Task(detalle.getIdetapaproduccion().getNombre(),
                        inicio.getTime(),
                        fin.getTime()));
            }
        }

        dataset.add(unavailable);

// title, domain axis, range axis, dataset, legend, tooltip, urls
        JFreeChart chart = ChartFactory.createGanttChart("Producción", "Producciones", "Time", dataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chart.setBorderVisible(true);
        chartPanel.setBounds(0, 0, pnlDisponibilidad.getWidth(), pnlDisponibilidad.getHeight());
        pnlDisponibilidad.add(chartPanel);
//        this.getContentPane().add(chartPanel);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setBounds(50, 50, 800, 200);

//        JFrame frame = new JFrame("MeetNow!");
//        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setBounds(50, 50, 800, 800);
//        frame.setVisible(true);
        setVisiblePanel(pnlDisponibilidad.getName());
    }//GEN-LAST:event_hplVerDisponibilidadActionPerformed

    private void hplVerPlanificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplVerPlanificacionActionPerformed
        setVisiblePanel(pnlTreeTable.getName());
    }//GEN-LAST:event_hplVerPlanificacionActionPerformed

    private void cargarLista(JXList lista, Object[] values) {
        lista.setListData(values);
    }

    private void cargarDatosTreeTable(Set<Detallepresupuesto> detallepresupuestos) {
        DefaultMutableTreeTableNode raiz = new DefaultMutableTreeTableNode(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido()));
        trtDetalleProcProd.removeAll();
        trtDetalleProcProd.setTreeTableModel(new DefaultTreeTableModel(raiz, listColumnNamesTreeTable));
        Iterator<Detallepresupuesto> it = detallepresupuestos.iterator();
        Detallepresupuesto dp = null;
        ProductoNode prod = null;
        while (it.hasNext()) {
            dp = it.next();
            prod = new ProductoNode(dp.getProducto());
            raiz.add(prod);
            Set<Detalleproductopresupuesto> setDetProPre = dp.getDetalleproductopresupuestos();
            Iterator<Detalleproductopresupuesto> itDetProPre = setDetProPre.iterator();
            PiezaNode pieza = null;
            Detalleproductopresupuesto detProPre = null;
            while (itDetProPre.hasNext()) {
                detProPre = itDetProPre.next();
                pieza = new PiezaNode(detProPre.getPieza());
                prod.add(pieza);
                Set<Detallepiezapresupuesto> setDetPiPre = detProPre.getDetallepiezapresupuestos();
                Detallepiezapresupuesto detPiPre = null;
                Iterator<Detallepiezapresupuesto> itDetPiPre = setDetPiPre.iterator();
                EtapaProduccionNode etapaProd = null;
                while (itDetPiPre.hasNext()) {
                    detPiPre = itDetPiPre.next();
                    etapaProd = new EtapaProduccionNode(detPiPre.getEtapadeproduccion());
                    pieza.add(etapaProd);
                }
            }
        }
        trtDetalleProcProd.expandAll();
        trtDetalleProcProd.setEditable(false);
//        trtDetalleProcProd.updateUI();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarPlanificacionProduccion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarEmpleado;
    private javax.swing.JButton btnAsignarMaquina;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton btnSeleccionarEmpleado;
    private org.jdesktop.swingx.JXHyperlink hplAsignarEmpleado;
    private org.jdesktop.swingx.JXHyperlink hplAsignarMaquinas;
    private org.jdesktop.swingx.JXHyperlink hplVerDisponibilidad;
    private org.jdesktop.swingx.JXHyperlink hplVerPlanificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    private org.jdesktop.swingx.JXList jlstEmpleados;
    private org.jdesktop.swingx.JXList jlstMaquinas;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnlDispHoraria;
    private javax.swing.JPanel pnlDisponibilidad;
    private javax.swing.JPanel pnlEmpleado;
    private javax.swing.JPanel pnlMaquinas;
    private javax.swing.JPanel pnlTreeTable;
    private org.jdesktop.swingx.JXTable tblPedidos;
    private org.jdesktop.swingx.JXTreeTable trtDetalleProcProd;
    private org.jdesktop.swingx.JXTaskPane tskPanel;
    private javax.swing.JTextField txtValorBusqueda;
    // End of variables declaration//GEN-END:variables

    // End of variables declaration
    class PedidoNoPlanificadoTableModel extends AbstractTableModel {

        private String[] columnNames = {
            "Nro.Ped",
            "Nro.Ped.Cli",
            "Fecha Pedido",
            "Estado",
            "Nro.Pres",
            "Monto",
            "Nro. Cli",
            "Cliente"
        };

        public int getRowCount() {
            if (filasPedidosNoPlanificados != null) {
                return filasPedidosNoPlanificados.size();
            } else {
                return 0;
            }
        }

        public String[] getColumnNames() {
            return columnNames;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            ViewPedidoNoPlanificado view = filasPedidosNoPlanificados.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, view.getNropedido());
                case 1:
                    return view.getNropedcotcli();
                case 2:
                    return Fecha.parseToString(view.getFechapedido());
                case 3:
                    return view.getEstado();
                case 4:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRESUPUESTO, view.getNropresupuesto());
                case 5:
                    return Decimales.con2Decimales(view.getMontototal());
                case 6:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_CLIENTE, view.getNrocliente());
                case 7:
                    return view.getRazonsocial();
                default:
                    return null;
            }
        }
    }

    class ProductoNode extends AbstractMutableTreeTableNode {

        private Producto producto;

        public ProductoNode(Producto producto) {
            this.producto = producto;
        }

        public Object getValueAt(int i) {
            switch (i) {
                case 0:
                    return producto.getNombre();
            }
            return "";
        }

        public int getColumnCount() {
            return columnCountTreeTable;
        }
    }

    class PiezaNode extends AbstractMutableTreeTableNode {

        private Pieza pieza;

        public PiezaNode(Pieza pieza) {
            this.pieza = pieza;
        }

        public Object getValueAt(int i) {
            switch (i) {
                case 0:
                    return pieza.getNombre();
            }
            return "";
        }

        public int getColumnCount() {
            return columnCountTreeTable;
        }

        public Pieza getPieza() {
            return pieza;
        }
    }

    class EtapaProduccionNode extends AbstractMutableTreeTableNode {

        private Etapadeproduccion etapa;
        private Maquina maquina;
        private Empleado empleado;

        public EtapaProduccionNode(Etapadeproduccion etapa) {
            this.etapa = etapa;
        }

        public Empleado getEmpleado() {
            return empleado;
        }

        public void setEmpleado(Empleado empleado) {
            this.empleado = empleado;
        }

        public Etapadeproduccion getEtapa() {
            return etapa;
        }

        public void setEtapa(Etapadeproduccion etapa) {
            this.etapa = etapa;
        }

        public Maquina getMaquina() {
            return maquina;
        }

        public void setMaquina(Maquina maquina) {
            this.maquina = maquina;
        }

        public Object getValueAt(int i) {
            try {
                switch (i) {
                    case 0:
                        return etapa.getNombre();
                    case 1:
                        return empleado.getNombre() + " " + empleado.getApellido();
                    case 2:
                        return maquina.getNombre();
                }
            } catch (NullPointerException ex) {
                return "----";
            }
            return "";
        }

        public int getColumnCount() {
            return columnCountTreeTable;
        }
    }
}
