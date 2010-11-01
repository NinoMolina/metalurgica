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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

/**
 *
 * @author Nino
 */
public class RegistrarPlanificacionProduccion extends javax.swing.JFrame {

    /** Creates new form RegistrarPlanificacionProduccion */
    private LinkedList<ViewPedidoNoPlanificado> filasPedidosNoPlanificados;
    private GestorRegistrarPlanificacionProduccion gestor;
    private entity.Presupuesto presupuesto;
    private int columnCountTreeTable = 3;
    private ViewPedidoNoPlanificado viewPedidoSeleccionado;
    private ArrayList<String> listColumnNamesTreeTable;
    private HashMap<String, JPanel> hashPanels;
    private List<entity.Empleado> lstEmpleados;
    private List<entity.Maquina> lstMaquinas;
    private List<entity.Planificacionproduccion> lstPlanificacionProduccion;
    private entity.Maquina maquinaSeleccionada;
    private entity.Empleado empleadoSeleccionado;

    public RegistrarPlanificacionProduccion() {
        initComponents();
        addListeners();
        iniciarPaneles();
        setEnableHyperLink(false);
        listColumnNamesTreeTable = new ArrayList<String>();
        listColumnNamesTreeTable.add("Detalle");
        listColumnNamesTreeTable.add("Empleado");
        listColumnNamesTreeTable.add("Máquinas");
        setearTablas();
        gestor = new GestorRegistrarPlanificacionProduccion();
        filasPedidosNoPlanificados = new LinkedList<ViewPedidoNoPlanificado>();
        buscarPedidosNoPlanificados();
        iniciarTreeTable();
        tblPedidos.updateUI();
        tskPanel.setTitle("Asignaciones");
        tskPanel.setAnimated(true);
    }

    private void addListeners() {
        addListenerBtnSubir();
        addListenerBtnBajar();
        addListenerBtnGuardar();
        addListenerBtnSalir();
        addListenerBtnSeleccionar();
    }

    private void addListenerBtnSubir() {
        subirBajar.getBtnSubir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });
    }

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {
        //obtengo el nodo seleccionado
        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();
        //obtengo el nodo anterior (que es por el cual deberia intercambiar)
        TreePath tpAnterior = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow() - 1);
        Object objAnterior = tpAnterior.getLastPathComponent();
        //el nodo seleccionado tiene que ser uno de etapaproduccion
        if (obj instanceof EtapaProduccionNode && objAnterior instanceof EtapaProduccionNode) {
            EtapaProduccionNode node = (EtapaProduccionNode) obj;
            EtapaProduccionNode nodeAnterior = (EtapaProduccionNode) objAnterior;
            int indexNode = node.getParent().getIndex(node);
            int indexNodeAnterior = nodeAnterior.getParent().getIndex(nodeAnterior);
            PiezaNode parent = (PiezaNode) nodeAnterior.getParent();
            parent.getChildren().set(indexNode, nodeAnterior);
            parent.getChildren().set(indexNodeAnterior, node);
        }
        trtDetalleProcProd.updateUI();
    }

    private void addListenerBtnBajar() {
        subirBajar.getBtnBajar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajarActionPerformed(evt);
            }
        });
    }

    private void btnBajarActionPerformed(java.awt.event.ActionEvent evt) {
        //obtengo el nodo seleccionado
        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();
        //obtengo el nodo anterior (que es por el cual deberia intercambiar)
        TreePath tpSiguiente = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow() + 1);
        try {
            Object objSiguiente = tpSiguiente.getLastPathComponent();
            //el nodo seleccionado tiene que ser uno de etapaproduccion
            if (obj instanceof EtapaProduccionNode && objSiguiente instanceof EtapaProduccionNode) {
                EtapaProduccionNode node = (EtapaProduccionNode) obj;
                EtapaProduccionNode nodeSiguiente = (EtapaProduccionNode) objSiguiente;
                int indexNode = node.getParent().getIndex(node);
                int indexNodeSiguiente = nodeSiguiente.getParent().getIndex(nodeSiguiente);
                PiezaNode parent = (PiezaNode) nodeSiguiente.getParent();
                parent.getChildren().set(indexNode, nodeSiguiente);
                parent.getChildren().set(indexNodeSiguiente, node);
            }
            trtDetalleProcProd.updateUI();
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }

    private void addListenerBtnGuardar() {
        beanBtnGuardar.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void addListenerBtnSalir() {
        beanBtnSalir.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void addListenerBtnSeleccionar() {
        beanBtnSeleccionar.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {
        viewPedidoSeleccionado = filasPedidosNoPlanificados.get(tblPedidos.getSelectedRow());
        presupuesto = gestor.buscarPresupuesto(viewPedidoSeleccionado.getIdpresupuesto());
        setVisiblePanel(pnlTreeTable.getName());
        setEnableHyperLink(true);
        cargarDatosTreeTable(presupuesto.getDetallepresupuestoSet());
    }

    private void setearTablas() {
        //PEDIDO
        tblPedidos.setModel(new PedidoNoPlanificadoTableModel());
        tblPedidos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedidos.setShowHorizontalLines(false);
        tblPedidos.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblPedidos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //EMPLEADO
        tblEmpleado.setModel(new EmpleadoTableModel());
        tblEmpleado.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblEmpleado.setShowHorizontalLines(false);
        tblEmpleado.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblEmpleado.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //MAQUINAS
        tblMaquinas.setModel(new MaquinaTableModel());
        tblMaquinas.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblMaquinas.setShowHorizontalLines(false);
        tblMaquinas.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblMaquinas.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void setEnableHyperLink(boolean flag) {
        tskPanel.setEnabled(flag);
        hplAsignarEmpleado.setEnabled(flag);
        hplAsignarMaquinas.setEnabled(flag);
        hplVerDisponibilidad.setEnabled(flag);
        hplVerPlanificacion.setEnabled(flag);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionar = new metalsoft.beans.BtnSeleccionar();
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
        subirBajar = new metalsoft.beans.SubirBajar();
        pnlEmpleado = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnSeleccionarEmpleado = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEmpleado = new org.jdesktop.swingx.JXTable();
        pnlDispHoraria = new javax.swing.JPanel();
        btnAsignarEmpleado = new javax.swing.JButton();
        pnlMaquinas = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAsignarMaquina = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMaquinas = new org.jdesktop.swingx.JXTable();
        pnlDisponibilidad = new javax.swing.JPanel();
        beanBtnGuardar = new metalsoft.beans.BtnGuardar();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();

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

        jScrollPane1.setViewportView(tblPedidos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beanBtnSeleccionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addGroup(pnlTreeTableLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subirBajar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTreeTableLayout.setVerticalGroup(
            pnlTreeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreeTableLayout.createSequentialGroup()
                .addGroup(pnlTreeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addGroup(pnlTreeTableLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(subirBajar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnl.add(pnlTreeTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 340));

        pnlEmpleado.setName("pnlEmpleado"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));

        btnSeleccionarEmpleado.setText("Ver Disponibilidad");
        btnSeleccionarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarEmpleadoActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(tblEmpleado);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionarEmpleado)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnSeleccionarEmpleado))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGap(0, 117, Short.MAX_VALUE)
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

        jLabel2.setText("Filtro:");

        btnAsignarMaquina.setText("Asignar");
        btnAsignarMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarMaquinaActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(tblMaquinas);

        javax.swing.GroupLayout pnlMaquinasLayout = new javax.swing.GroupLayout(pnlMaquinas);
        pnlMaquinas.setLayout(pnlMaquinasLayout);
        pnlMaquinasLayout.setHorizontalGroup(
            pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaquinasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMaquinasLayout.createSequentialGroup()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(pnlMaquinasLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(409, 409, 409)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMaquinasLayout.createSequentialGroup()
                        .addComponent(btnAsignarMaquina)
                        .addContainerGap())))
        );
        pnlMaquinasLayout.setVerticalGroup(
            pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaquinasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 831, Short.MAX_VALUE)
                        .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hplAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarEmpleadoActionPerformed
        if (validarEtapaSeleccionada()) {
            setVisiblePanel(pnlEmpleado.getName());
            lstEmpleados = gestor.obtenerEmpleados();
            tblEmpleado.updateUI();
        }

    }//GEN-LAST:event_hplAsignarEmpleadoActionPerformed

    private boolean validarEtapaSeleccionada() {
        //obtengo el nodo seleccionado
        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();
        //el nodo seleccionado tiene que ser uno de etapaproduccion
        if (obj instanceof EtapaProduccionNode) {
            return true;
        }
        return false;
    }
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
        empleadoSeleccionado = (entity.Empleado) lstEmpleados.get(tblEmpleado.getSelectedRow());
        //validar que disp horaria no sea null
        try {
//            lstDisponibilidad.setListData(empleadoSeleccionado.getDisponibilidadhorarias().toArray());
            Set<entity.Disponibilidadhoraria> disp = empleadoSeleccionado.getDisponibilidadhorariaSet();
            TaskSeriesCollection dataset = new TaskSeriesCollection();
            TaskSeries unavailable = new TaskSeries("Disponibilidad");

            for (entity.Disponibilidadhoraria dispHoraria : disp) {
                //creo una nueva tarea
                Date fecha = dispHoraria.getFecha();
                Task task = new Task(Fecha.parseToString(fecha),
                        new GregorianCalendar(2010, 11, 1, 0, 0, 0).getTime(),
                        new GregorianCalendar(2010, 11, 1, Jornada.HORAS_JORNADA, 0, 0).getTime());
                //agrego la tarea la serie de taras
                unavailable.add(task);

                task.addSubtask(new Task("",
                        new GregorianCalendar(2010, 11, 1, 0, 0, 0).getTime(),
                        new GregorianCalendar(2010, 11, 1, Jornada.HORAS_JORNADA - dispHoraria.getTiempodisponible().getHours(), 60 - dispHoraria.getTiempodisponible().getMinutes(), 0).getTime()));
            }

            dataset.add(unavailable);

// title, domain axis, range axis, dataset, legend, tooltip, urls
            JFreeChart chart = ChartFactory.createGanttChart("", "Días", "Horas", dataset, true, true, false);
            ChartPanel chartPanel = new ChartPanel(chart);
            chart.setBorderVisible(true);
            chartPanel.setBounds(10, 15, pnlDispHoraria.getWidth() - 20, pnlDispHoraria.getHeight() - 30);
            pnlDispHoraria.removeAll();
            pnlDispHoraria.add(chartPanel);
            pnlDispHoraria.updateUI();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("entro en el catch de lstDisponibilidad");
        }
    }//GEN-LAST:event_btnSeleccionarEmpleadoActionPerformed

    private void hplAsignarMaquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarMaquinasActionPerformed
        if (validarEtapaSeleccionada()) {
            setVisiblePanel(pnlMaquinas.getName());
            lstMaquinas = gestor.obtenerMaquinas();
            tblMaquinas.updateUI();
        }

    }//GEN-LAST:event_hplAsignarMaquinasActionPerformed

    private void btnAsignarMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarMaquinaActionPerformed
        maquinaSeleccionada = (entity.Maquina) lstMaquinas.get(tblMaquinas.getSelectedRow());
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

    private void limpiarCampos() {
        trtDetalleProcProd.removeAll();
        iniciarTreeTable();
        tblEmpleado.removeAll();
        tblMaquinas.removeAll();
        txtValorBusqueda.setText("");
        setEnableHyperLink(false);
        setVisiblePanel(pnlTreeTable.getName());
    }
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

                Task subTask = new Task(detalle.getIdetapaproduccion().getNombre(),
                        inicio.getTime(),
                        fin.getTime());
                task.addSubtask(subTask);
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

    private void cargarDatosTreeTable(Set<entity.Detallepresupuesto> detallepresupuestos) {
        DefaultMutableTreeTableNode raiz = new DefaultMutableTreeTableNode(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido()));
        trtDetalleProcProd.removeAll();
        trtDetalleProcProd.setTreeTableModel(new DefaultTreeTableModel(raiz, listColumnNamesTreeTable));
        Iterator<entity.Detallepresupuesto> it = detallepresupuestos.iterator();
        entity.Detallepresupuesto dp = null;
        ProductoNode prod = null;
        while (it.hasNext()) {
            dp = it.next();
            prod = new ProductoNode(dp.getIdproducto());
            raiz.add(prod);
            Set<entity.Detalleproductopresupuesto> setDetProPre = dp.getDetalleproductopresupuestoSet();
            Iterator<entity.Detalleproductopresupuesto> itDetProPre = setDetProPre.iterator();
            PiezaNode pieza = null;
            entity.Detalleproductopresupuesto detProPre = null;
            while (itDetProPre.hasNext()) {
                detProPre = itDetProPre.next();
                pieza = new PiezaNode(detProPre.getIdpieza());
                prod.add(pieza);
                Set<entity.Detallepiezapresupuesto> setDetPiPre = detProPre.getDetallepiezapresupuestoSet();
                entity.Detallepiezapresupuesto detPiPre = null;
                Iterator<entity.Detallepiezapresupuesto> itDetPiPre = setDetPiPre.iterator();
                EtapaProduccionNode etapaProd = null;
                while (itDetPiPre.hasNext()) {
                    detPiPre = itDetPiPre.next();
                    etapaProd = new EtapaProduccionNode(detPiPre.getIdetapa());
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
    private metalsoft.beans.BtnGuardar beanBtnGuardar;
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionar;
    private javax.swing.JButton btnAsignarEmpleado;
    private javax.swing.JButton btnAsignarMaquina;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnlDispHoraria;
    private javax.swing.JPanel pnlDisponibilidad;
    private javax.swing.JPanel pnlEmpleado;
    private javax.swing.JPanel pnlMaquinas;
    private javax.swing.JPanel pnlTreeTable;
    private metalsoft.beans.SubirBajar subirBajar;
    private org.jdesktop.swingx.JXTable tblEmpleado;
    private org.jdesktop.swingx.JXTable tblMaquinas;
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

    class EmpleadoTableModel extends AbstractTableModel {

        private String[] columnNames = {
            "Legajo",
            "Nombre",
            "Apellido"
        };

        public int getRowCount() {
            if (lstEmpleados != null) {
                return lstEmpleados.size();
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
            entity.Empleado empleado = lstEmpleados.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return empleado.getLegajo();
                case 1:
                    return empleado.getNombre();
                case 2:
                    return empleado.getApellido();
                default:
                    return null;
            }
        }
    }

    class MaquinaTableModel extends AbstractTableModel {

        private String[] columnNames = {
            "Nombre",
            "Marca",
            "Descripcion",
            "Estado"
        };

        public int getRowCount() {
            if (lstMaquinas != null) {
                return lstMaquinas.size();
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
            entity.Maquina maquina = lstMaquinas.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return maquina.getNombre();
                case 1:
                    return maquina.getMarca().getNombre();
                case 2:
                    return maquina.getDescripcion();
                case 3:
                    return maquina.getEstado().getNombre();
                default:
                    return null;
            }
        }
    }

    class ProductoNode extends AbstractMutableTreeTableNode {

        private entity.Producto producto;

        public ProductoNode(entity.Producto producto) {
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

        private entity.Pieza pieza;

        public PiezaNode(entity.Pieza pieza) {
            this.pieza = pieza;
        }

        public List<MutableTreeTableNode> getChildren() {
            return children;
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

        public entity.Pieza getPieza() {
            return pieza;
        }
    }

    class EtapaProduccionNode extends AbstractMutableTreeTableNode {

        private entity.Etapadeproduccion etapa;
        private entity.Maquina maquina;
        private entity.Empleado empleado;

        public EtapaProduccionNode(entity.Etapadeproduccion etapa) {
            this.etapa = etapa;
        }

        public entity.Empleado getEmpleado() {
            return empleado;
        }

        public void setEmpleado(entity.Empleado empleado) {
            this.empleado = empleado;
        }

        public entity.Etapadeproduccion getEtapa() {
            return etapa;
        }

        public void setEtapa(entity.Etapadeproduccion etapa) {
            this.etapa = etapa;
        }

        public entity.Maquina getMaquina() {
            return maquina;
        }

        public void setMaquina(entity.Maquina maquina) {
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
