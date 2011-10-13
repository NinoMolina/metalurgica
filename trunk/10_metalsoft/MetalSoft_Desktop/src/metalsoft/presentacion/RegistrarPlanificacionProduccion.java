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

import java.awt.Color;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.entity.Detallepiezapresupuesto;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import metalsoft.datos.jpa.entity.Producto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Disponibilidadhoraria;
import metalsoft.negocio.gestores.GestorRegistrarPlanificacionProduccion;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewPedidoNoPlanificado;
import metalsoft.util.Calculos;
import metalsoft.util.Decimales;
import metalsoft.util.Fecha;
import metalsoft.util.Jornada;
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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.joda.time.Instant;
import org.joda.time.Interval;

/**
 *
 * @author Nino
 */
public class RegistrarPlanificacionProduccion extends javax.swing.JDialog {

    /** Creates new form RegistrarPlanificacionProduccion */
    private LinkedList<ViewPedidoNoPlanificado> filasPedidosNoPlanificados;
    private GestorRegistrarPlanificacionProduccion gestor;
    private metalsoft.datos.jpa.entity.Presupuesto presupuesto;
    private int columnCountTreeTable = 5;
    private ViewPedidoNoPlanificado viewPedidoSeleccionado;
    private ArrayList<String> listColumnNamesTreeTable;
    private HashMap<String, JPanel> hashPanels;
    private List<metalsoft.datos.jpa.entity.Empleado> lstEmpleados;
    private List<metalsoft.datos.jpa.entity.Maquina> lstMaquinas;
    private List<metalsoft.datos.jpa.entity.Planificacionproduccion> lstPlanificacionProduccion;
    private metalsoft.datos.jpa.entity.Maquina maquinaSeleccionada;
    private metalsoft.datos.jpa.entity.Empleado empleadoSeleccionado;
    private HashMap<Long, metalsoft.datos.jpa.entity.Empleado> hashEmpleadoNoDisponible;
    private HashMap<Long, Maquina> hashMaquinasNoDisponible;
    private final int EMPLEADO = 1;
    private final int MAQUINA = 2;
    private final int EMPLEADO_MAQUINA = 3;
    private Map<Long, List<EtapaProduccionNode>> mapAsignacionActualEmpleados;

    public RegistrarPlanificacionProduccion() {
        super(Principal.getVtnPrincipal());
        initComponents();
        addListeners();
        iniciarPaneles();
        hashEmpleadoNoDisponible = new HashMap<Long, Empleado>();
        hashMaquinasNoDisponible = new HashMap<Long, Maquina>();
        setEnabledComponents(false);
        listColumnNamesTreeTable = new ArrayList<String>();
        listColumnNamesTreeTable.add("Detalle");
        listColumnNamesTreeTable.add("Inicio");
        listColumnNamesTreeTable.add("Fin");
        listColumnNamesTreeTable.add("Empleado");
        listColumnNamesTreeTable.add("Máquinas");
        setearTablas();
        gestor = new GestorRegistrarPlanificacionProduccion();
        mapAsignacionActualEmpleados = new HashMap<Long, List<EtapaProduccionNode>>();
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
//            System.out.println("Node: "+node.getInicioEtapa()+" "+node.getFinEtapa());
//            System.out.println("NodeAnterior: "+nodeAnterior.getInicioEtapa()+" "+nodeAnterior.getFinEtapa());
            Date fin = recalcularFechaFin(node.getInicioEtapa(), nodeAnterior.getInicioEtapa(), node.getFinEtapa());
//            System.out.println("Node NvoFin: "+fin);
            node.setFinEtapa(fin);
            node.setInicioEtapa(nodeAnterior.getInicioEtapa());
//            Date d=null;
//            if(node.getFinEtapa().getMinutes()!=0){
//                d=(Date) node.getFinEtapa().clone();
//                d.setHours(d.getHours()+1);
//                d.setMinutes(0);
//                d.setSeconds(0);
//            }
//            else{
//                d=node.getFinEtapa();
//            }
            GregorianCalendar fechaNuevoInicio = new GregorianCalendar();
            fechaNuevoInicio.setTime(node.getFinEtapa());
            fechaNuevoInicio.add(Calendar.MINUTE, Jornada.MINUTOS_ENTRE_ETAPAS);
            fin = recalcularFechaFin(nodeAnterior.getInicioEtapa(), fechaNuevoInicio.getTime(), nodeAnterior.getFinEtapa());
//            System.out.println("NodeAnterior NvoFin: "+fin);
            nodeAnterior.setFinEtapa(fin);
            nodeAnterior.setInicioEtapa(fechaNuevoInicio.getTime());
            int indexNode = node.getParent().getIndex(node);
            int indexNodeAnterior = nodeAnterior.getParent().getIndex(nodeAnterior);
            PiezaNode parent = (PiezaNode) nodeAnterior.getParent();
            parent.getChildren().set(indexNode, nodeAnterior);
            parent.getChildren().set(indexNodeAnterior, node);
        }
        trtDetalleProcProd.updateUI();
    }

    private Date recalcularFechaFin(Date inicioActual, Date inicioNuevo, Date fin) {
        Long dif = fin.getTime() - inicioActual.getTime();
        Date finNuevo = new Date(inicioNuevo.getTime() + dif);
        return finNuevo;
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
                Date fin = recalcularFechaFin(nodeSiguiente.getInicioEtapa(), node.getInicioEtapa(), nodeSiguiente.getFinEtapa());
//            System.out.println("Node NvoFin: "+fin);
                nodeSiguiente.setFinEtapa(fin);
                nodeSiguiente.setInicioEtapa(node.getInicioEtapa());
                GregorianCalendar fechaNuevoInicio = new GregorianCalendar();
                fechaNuevoInicio.setTime(nodeSiguiente.getFinEtapa());
                fechaNuevoInicio.add(Calendar.MINUTE, Jornada.MINUTOS_ENTRE_ETAPAS);
                fin = recalcularFechaFin(node.getInicioEtapa(), fechaNuevoInicio.getTime(), node.getFinEtapa());
//            System.out.println("NodeAnterior NvoFin: "+fin);
                node.setFinEtapa(fin);
                node.setInicioEtapa(fechaNuevoInicio.getTime());
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
        int respuesta = JOptionPane.showConfirmDialog(this, "Está por guardar la planificación\nDesea Continuar?", "Atención", JOptionPane.OK_CANCEL_OPTION);
        if (respuesta == JOptionPane.CANCEL_OPTION) {
            return;
        }
        int orden = 1;
        ProductoNode prodNodeAnterior = null;
        PiezaNode piezaNodeAnterior = null;
        List<Detalleplanificacionproduccion> detalle = new ArrayList<Detalleplanificacionproduccion>();
        Planificacionproduccion plan = new Planificacionproduccion();
        Date menor = null, mayor = null;
        Detalleplanificacionproduccion detAnterior = null;
        boolean setDetAnterior = false;
        /*
         * recorro los nodos del arbol jtree
         */
        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            TreePath tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();
            /*
             * obtengo cada una de las etapas de produccion
             */
            if (obj instanceof EtapaProduccionNode) {
                EtapaProduccionNode node = (EtapaProduccionNode) obj;
                PiezaNode piezaNode = (PiezaNode) node.getParent();
                ProductoNode productoNode = (ProductoNode) piezaNode.getParent();

                /*
                 * Veo si pase al siguiente producto, si pase reinicio el orden
                 * pero esto se tendria que hacer si se hace produccion en paralelo,
                 * para lo cual hay que ver si no se ha asignado el mismo empleado o maquina
                 * para una etapa de produccion que se ejecute en paralelo con otra
                 */
                if (prodNodeAnterior != productoNode) {

                    orden = 1;
                    piezaNodeAnterior = piezaNode;
                    prodNodeAnterior = productoNode;
                    setDetAnterior = true;

                } else {
                    /*
                     * el orden deberia ser por pieza y no por producto porque un producto
                     * puede tener 2 piezas que se pueden ejecutar en paralelo.
                     * Tendria que reiniciar el orden si estoy en el mismo producto y misma pieza.
                     * agregar if (piezaNodoAnterior == piezaNodo)
                     */
                    if (piezaNodeAnterior != piezaNode) {
                        orden = 1;
                        piezaNodeAnterior = piezaNode;
                        setDetAnterior = true;
                    }
                }
                /*
                 * Validacion de fechas
                 */
                if (menor == null && mayor == null) {
                    menor = node.getInicioEtapa();
                    mayor = node.getFinEtapa();
                } else {
                    if (menor.compareTo(node.getInicioEtapa()) > 0) {
                        menor = node.getInicioEtapa();
                    }
                    if (mayor.compareTo(node.getFinEtapa()) < 0) {
                        mayor = node.getFinEtapa();
                    }
                }


                Empleado emp = node.getEmpleado();
                Maquina maq = node.getMaquina();

                Producto prod = productoNode.getProducto();
                Pieza pieza = piezaNode.getPieza();
                Etapadeproduccion etapa = node.getEtapa();

                /*
                 * por cada etapa de produccion se crea un detalle de planificacion produccion
                 */
                Detalleplanificacionproduccion dpp = new Detalleplanificacionproduccion();
                dpp.setFechafin(node.getFinEtapa());
                dpp.setFechainicio(node.getInicioEtapa());
                dpp.setHorafin(node.getFinEtapa());
                dpp.setHorainicio(node.getInicioEtapa());
                dpp.setIdempleado(emp);
                dpp.setIdetapaproduccion(etapa);
                dpp.setIdmaquina(maq);
                dpp.setIdpieza(pieza);
                dpp.setIdproducto(prod);
                dpp.setIdplanificacionproduccion(plan);
                dpp.setOrden(orden);
                dpp.setIndexproducto(productoNode.getIndexProducto());

                if (setDetAnterior) {
                    dpp.setDetalleanterior(null);
                    setDetAnterior = false;
                } else {
                    dpp.setDetalleanterior(detAnterior.getId());
                }
                detAnterior = dpp;

                detalle.add(dpp);


                orden++;
            }
        }

        plan.setFechacreacion(Fecha.fechaActualDate());
        plan.setFechafinprevista(mayor);
        plan.setFechainicioprevista(menor);
        PedidoJpaController ctrlPedido = new PedidoJpaController(JpaUtil.getEntityManagerFactory());
        Pedido ped = ctrlPedido.findPedido(viewPedidoSeleccionado.getIdpedido());
        plan.setPedido(ped);
        plan.setObservaciones(txtObservaciones.getText());
        boolean result = gestor.guardarPlanificacionProduccion(plan, detalle);
        if (result) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente!");
            filasPedidosNoPlanificados.remove(viewPedidoSeleccionado);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar!");
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
        if (tblPedidos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido..!");
            return;
        }
        viewPedidoSeleccionado = filasPedidosNoPlanificados.get(tblPedidos.getSelectedRow());
        presupuesto = gestor.buscarPresupuesto(viewPedidoSeleccionado.getIdpresupuesto());
        setVisiblePanel(pnlTreeTable.getName());
        setEnabledComponents(true);
        cargarDatosTreeTable(presupuesto.getDetallepresupuestoList());
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

    private void setEnabledComponents(boolean flag) {
        tskPanel.setEnabled(flag);
        hplAsignarEmpleado.setEnabled(flag);
        hplAsignarMaquinas.setEnabled(flag);
        hplVerDisponibilidad.setEnabled(flag);
        hplVerPlanificacion.setEnabled(flag);
        hplObservaciones.setEnabled(flag);
        subirBajar.setEnabled(flag);
        beanBtnGuardar.getBtnGuardar().setEnabled(flag);
    }

    private void iniciarPaneles() {
        hashPanels = new HashMap<String, JPanel>();
        hashPanels.put(pnlTreeTable.getName(), pnlTreeTable);
        hashPanels.put(pnlEmpleado.getName(), pnlEmpleado);
        hashPanels.put(pnlMaquinas.getName(), pnlMaquinas);
        hashPanels.put(pnlDisponibilidad.getName(), pnlDisponibilidad);
        hashPanels.put(pnlObservaciones.getName(), pnlObservaciones);
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

        TreeTableModel treeTableModel = new TablaPlanificacionModel(raiz, listColumnNamesTreeTable);

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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        hplObservaciones = new org.jdesktop.swingx.JXHyperlink();
        pnl = new javax.swing.JPanel();
        pnlTreeTable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        trtDetalleProcProd = new org.jdesktop.swingx.JXTreeTable();
        subirBajar = new metalsoft.beans.SubirBajar();
        pnlEmpleado = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnVerDisponibilidad = new javax.swing.JButton();
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
        pnlObservaciones = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();
        beanBtnGuardar = new metalsoft.beans.BtnGuardar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Planificación");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Confirmados"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar Datos"));

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setSelected(true);
        jRadioButton3.setText("Nro Pedido");

        jLabel1.setText("Valor de Búsqueda:");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Fecha Pedido");

        buttonGroup1.add(jRadioButton1);
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
                .addComponent(txtValorBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
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

        hplObservaciones.setText("Agregar Observación");
        hplObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hplObservacionesActionPerformed(evt);
            }
        });
        tskPanel.getContentPane().add(hplObservaciones);

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
                .addGap(18, 18, 18)
                .addComponent(subirBajar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        pnlTreeTableLayout.setVerticalGroup(
            pnlTreeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreeTableLayout.createSequentialGroup()
                .addComponent(subirBajar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        pnl.add(pnlTreeTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 340));

        pnlEmpleado.setName("pnlEmpleado"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));

        btnVerDisponibilidad.setText("Ver Disponibilidad");
        btnVerDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDisponibilidadActionPerformed(evt);
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
                .addComponent(btnVerDisponibilidad)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnVerDisponibilidad))
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

        pnlObservaciones.setName("pnlObservaciones"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane5.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlObservacionesLayout = new javax.swing.GroupLayout(pnlObservaciones);
        pnlObservaciones.setLayout(pnlObservacionesLayout);
        pnlObservacionesLayout.setHorizontalGroup(
            pnlObservacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlObservacionesLayout.setVerticalGroup(
            pnlObservacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlObservacionesLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(76, 76, 76))
        );

        pnl.add(pnlObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 340));

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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 841, Short.MAX_VALUE)
                        .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hplAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarEmpleadoActionPerformed
        if (validarEtapaSeleccionada()) {
            setVisiblePanel(pnlEmpleado.getName());
            lstEmpleados = gestor.obtenerEmpleados();
            empleadosNoDisponibles(
                    lstEmpleados);
            tblEmpleado.updateUI();
        }
    }//GEN-LAST:event_hplAsignarEmpleadoActionPerformed

    private void empleadosNoDisponibles(List<Empleado> list) {
        if (hashEmpleadoNoDisponible == null) {
            hashEmpleadoNoDisponible = new HashMap<Long, Empleado>();
        }
        for (Empleado empleado : list) {
            TreePath path = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
            EtapaProduccionNode node = (EtapaProduccionNode) path.getLastPathComponent();
            List<Detalleplanificacionproduccion> set = empleado.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalle : set) {
                GregorianCalendar detalleInicio = new GregorianCalendar();
                detalleInicio.setTime(detalle.getFechainicio());
                detalleInicio.set(Calendar.HOUR_OF_DAY, detalle.getHorainicio().getHours());
                detalleInicio.set(Calendar.MINUTE, detalle.getHorainicio().getMinutes());
                detalleInicio.set(Calendar.SECOND, detalle.getHorainicio().getSeconds());
                GregorianCalendar detalleFin = new GregorianCalendar();
                detalleFin.setTime(detalle.getFechafin());
                detalleFin.set(Calendar.HOUR_OF_DAY, detalle.getHorafin().getHours());
                detalleFin.set(Calendar.MINUTE, detalle.getHorafin().getMinutes());
                detalleFin.set(Calendar.SECOND, detalle.getHorafin().getSeconds());
                if (node.getInicioEtapa() != null) {
                    if (node.getInicioEtapa().compareTo(detalleInicio.getTime()) >= 0 && node.getFinEtapa().compareTo(detalleFin.getTime()) < 0) {
                        hashEmpleadoNoDisponible.put(empleado.getIdempleado(), empleado);
                    } else if (node.getFinEtapa().compareTo(detalleFin.getTime()) <= 0 && node.getFinEtapa().compareTo(detalleInicio.getTime()) > 0) {
                        hashEmpleadoNoDisponible.put(empleado.getIdempleado(), empleado);
                    } else if (node.getInicioEtapa().compareTo(detalleInicio.getTime()) <= 0 && node.getFinEtapa().compareTo(detalleFin.getTime()) >= 0) {
                        hashEmpleadoNoDisponible.put(empleado.getIdempleado(), empleado);
                    }
                }
            }
        }
    }

    private boolean validarEtapaSeleccionada() {
        //obtengo el nodo seleccionado
        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        if (tp == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Etapa de Producción\npara poder asignar");
            return false;
        }
        Object obj = tp.getLastPathComponent();
        //el nodo seleccionado tiene que ser uno de etapaproduccion
        if (obj instanceof EtapaProduccionNode) {

            EtapaProduccionNode node = (EtapaProduccionNode) obj;
            TreeNode parent = node.getParent();
            int indexNode = parent.getIndex(node);
            int indexNodeAnt = indexNode - 1;

            if (indexNode == 0) {
                return true;
            } else {
                EtapaProduccionNode nodeAnt = (EtapaProduccionNode) parent.getChildAt(indexNodeAnt);
                if (nodeAnt.getInicioEtapa() != null) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Primero debe seleccionar un empleado para la etapa anterior (" + nodeAnt.getEtapa().getNombre() + ").");
                    return false;
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Etapa de Producción\npara poder asignar");
        }
        return false;
    }
    private void btnAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarEmpleadoActionPerformed
        if (tblEmpleado.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Empleado!");
            return;
        }

        empleadoSeleccionado = lstEmpleados.get(tblEmpleado.getSelectedRow());

        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();

        EtapaProduccionNode node = null;

        if (obj instanceof EtapaProduccionNode) {
            node = (EtapaProduccionNode) obj;


            /*
             * validar que el empleado seleccionado no este asignado en el mismo horario de la etapa actual
             */

            node.setEmpleado(empleadoSeleccionado);

            TreeNode parent = node.getParent();

            int indexNodeActual = parent.getIndex(node);
            int indexNodeAnterior = indexNodeActual - 1;

            TreeNode anterior = null;

            Date fechaInicial = Calculos.calcularFechaInicio(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, Fecha.fechaActualCalendar()).getTime();

            if (indexNodeActual > 0) {
                anterior = parent.getChildAt(indexNodeAnterior);
                if (anterior instanceof EtapaProduccionNode) {
                    EtapaProduccionNode nodeAnt = (EtapaProduccionNode) anterior;
                    if (nodeAnt.getInicioEtapa() != null) {
                        fechaInicial = nodeAnt.getFinEtapa();
                    }
                }
            }

            int childCount = parent.getChildCount();
            /*
             * si tengo mas de un elemento y no es el ultimo nodo
             */
            if (childCount > 1 && (indexNodeActual + 1) != childCount) {
                /*
                 * recorro a partir del siguiente nodo y me fijo si tiene el mismo empleado.
                 * si lo tiene lo saco
                 */
                for (int i = indexNodeActual + 1; i < childCount; i++) {
                    EtapaProduccionNode nodeSig = (EtapaProduccionNode) parent.getChildAt(i);
                    if (nodeSig.getEmpleado() != null) {
//                        if (nodeSig.getEmpleado().getIdempleado() == node.getEmpleado().getIdempleado()) {
                        if (mapAsignacionActualEmpleados.containsKey(nodeSig.getEmpleado().getIdempleado())) {
                            mapAsignacionActualEmpleados.get(nodeSig.getEmpleado().getIdempleado()).remove(nodeSig);
                        }
                        nodeSig.setEmpleado(null);
                        nodeSig.setInicioEtapa(null);
                        nodeSig.setFinEtapa(null);
//                        }
                    }
                }
            }


//            Date fechaInicioDispBaseDeDatos = null;
            Date fechaFinDispBaseDeDatos = null;
//            Date fechaInicioDispSuperposicion = null;
            Date fechaFinDispSuperposicion = null;

            boolean continuarBuscando = true;

            while (continuarBuscando) {

                setInicioFinEtapaProduccion(node, fechaInicial);
//                fechaInicioDispBaseDeDatos = node.getInicioEtapa();
                fechaFinDispBaseDeDatos = node.getFinEtapa();

                boolean superposicion = haySuperposicionAsignacion(node, EMPLEADO);
                if (superposicion) {

                    Date fechaDispEmpleado = obtenerFechaDisponibilidadEmpleadoAsignacionActual(empleadoSeleccionado, node);
                    node.setInicioEtapa(fechaDispEmpleado);
                    Calendar inicioEtapa = new GregorianCalendar();
                    inicioEtapa.setTime(fechaDispEmpleado);
                    int horas = node.getDetallePiezaPresupuesto().getDuracionpiezaxetapa().getHours();
                    int minutos = node.getDetallePiezaPresupuesto().getDuracionpiezaxetapa().getMinutes();
                    node.setFinEtapa(Calculos.calcularFechaFin(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, inicioEtapa, horas, minutos).getTime());

//                    fechaInicioDispSuperposicion = node.getInicioEtapa();
                    fechaFinDispSuperposicion = node.getFinEtapa();
                    /*
                     * si la fecha de superposicion final es mayor a la fecha fin de disp de base de datos
                     * tendria que buscar otro intervalo en la base.
                     */
                    if (fechaFinDispSuperposicion.compareTo(fechaFinDispBaseDeDatos) <= 0) {
                        continuarBuscando = false;
                    } else {
                        fechaInicial = fechaFinDispSuperposicion;
                    }
                } else {
                    continuarBuscando = false;
                }

            }

            if (mapAsignacionActualEmpleados.containsKey(empleadoSeleccionado.getIdempleado())) {
                mapAsignacionActualEmpleados.get(empleadoSeleccionado.getIdempleado()).add(node);
            } else {
                List<EtapaProduccionNode> lst = new ArrayList<EtapaProduccionNode>();
                lst.add(node);
                mapAsignacionActualEmpleados.put(empleadoSeleccionado.getIdempleado(), lst);
            }

            eliminarNodeDeOtrosEmpleados(mapAsignacionActualEmpleados, empleadoSeleccionado, node);

        }

        setVisiblePanel(pnlTreeTable.getName());
    }//GEN-LAST:event_btnAsignarEmpleadoActionPerformed
    private void btnVerDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDisponibilidadActionPerformed
        if (tblEmpleado.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione el Empleado para el cual\nse desea ver su disponibilidad");
            return;
        }
        empleadoSeleccionado = (metalsoft.datos.jpa.entity.Empleado) lstEmpleados.get(tblEmpleado.getSelectedRow());
        //validar que disp horaria no sea null
        try {
//            lstDisponibilidad.setListData(empleadoSeleccionado.getDisponibilidadhorarias().toArray());
            List<metalsoft.datos.jpa.entity.Disponibilidadhoraria> disp = JpaUtil.getDisponibilidadEmpleado(empleadoSeleccionado);
            TaskSeriesCollection dataset = new TaskSeriesCollection();
            TaskSeries unavailable = new TaskSeries("Ocupación");

            Calendar fechaActual = Fecha.fechaActualCalendar();

            for (metalsoft.datos.jpa.entity.Disponibilidadhoraria dispHoraria : disp) {
                //creo una nueva tarea
                Date fecha = dispHoraria.getFecha();

                Task task = unavailable.get(Fecha.parseToString(fecha));
                if (task == null) {
                    task = new Task(Fecha.parseToString(fecha),
                            new GregorianCalendar(fechaActual.get(Calendar.YEAR),
                            fechaActual.get(Calendar.MONTH),
                            fechaActual.get(Calendar.DAY_OF_MONTH), Jornada.HORA_INICIO_JORNADA, 0, 0).getTime(),
                            new GregorianCalendar(fechaActual.get(Calendar.YEAR),
                            fechaActual.get(Calendar.MONTH),
                            fechaActual.get(Calendar.DAY_OF_MONTH), Jornada.HORA_FIN_JORNADA, 0, 0).getTime());
                    //agrego la tarea la serie de taras
                    unavailable.add(task);
                }

                task.addSubtask(new Task("",
                        new GregorianCalendar(fechaActual.get(Calendar.YEAR),
                        fechaActual.get(Calendar.MONTH),
                        fechaActual.get(Calendar.DAY_OF_MONTH),
                        dispHoraria.getHorainicio().getHours(),
                        dispHoraria.getHorainicio().getMinutes(),
                        dispHoraria.getHorainicio().getSeconds()).getTime(),
                        new GregorianCalendar(fechaActual.get(Calendar.YEAR),
                        fechaActual.get(Calendar.MONTH),
                        fechaActual.get(Calendar.DAY_OF_MONTH), dispHoraria.getHorafin().getHours(),
                        dispHoraria.getHorafin().getMinutes(),
                        dispHoraria.getHorafin().getSeconds()).getTime()));
            }

//            if(unavailable.isEmpty()){
//                Task task = new Task("", new GregorianCalendar(fechaActual.get(Calendar.YEAR),
//                            fechaActual.get(Calendar.MONTH),
//                            fechaActual.get(Calendar.DAY_OF_MONTH), Jornada.HORA_INICIO_JORNADA, 0, 0).getTime(),
//                            new GregorianCalendar(fechaActual.get(Calendar.YEAR),
//                            fechaActual.get(Calendar.MONTH),
//                            fechaActual.get(Calendar.DAY_OF_MONTH), Jornada.HORA_FIN_JORNADA, 0, 0).getTime());
//                task.setPercentComplete(1);
//                unavailable.add(task);
//            }

            dataset.add(unavailable);
// title, domain axis, range axis, dataset, legend, tooltip, urls
            JFreeChart chart = ChartFactory.createGanttChart("", "Días", "Horas", dataset, true, true, false);
            
            final CategoryPlot plot = (CategoryPlot) chart.getPlot();
            final CategoryItemRenderer renderer = plot.getRenderer();
            renderer.setSeriesPaint(0, Color.ORANGE);
            
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
    }//GEN-LAST:event_btnVerDisponibilidadActionPerformed

    private void eliminarNodeDeOtrosEmpleados(Map<Long, List<EtapaProduccionNode>> mapAsignacionActualEmpleados, Empleado empleadoSeleccionado, EtapaProduccionNode node) {

        Set<Long> keys = mapAsignacionActualEmpleados.keySet();
        List<EtapaProduccionNode> lstNode = null;
        for (Long key : keys) {
            if (key != empleadoSeleccionado.getIdempleado()) {
                lstNode = mapAsignacionActualEmpleados.get(key);
                if (lstNode.contains(node)) {
                    lstNode.remove(node);
                }
            }
        }
    }

    private boolean haySuperposicionEmpleado(EtapaProduccionNode nodeActual) {


        TreePath tp = null;
        EtapaProduccionNode node = null;
        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();

            if (obj instanceof EtapaProduccionNode) {
                node = (EtapaProduccionNode) obj;

                if (nodeActual.equals(node) || node.getEmpleado() == null) {
                    continue;
                }
                if (node.getEmpleado().getIdempleado() == nodeActual.getEmpleado().getIdempleado()) {

                    if (superposicion(node, nodeActual)) {
                        return true;
                    }

                }
            }
        }

        return false;

    }

    private boolean haySuperposicionMaquina(EtapaProduccionNode nodeActual) {


        TreePath tp = null;
        EtapaProduccionNode node = null;
        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();

            if (obj instanceof EtapaProduccionNode) {
                node = (EtapaProduccionNode) obj;

                if (nodeActual.equals(node) || node.getMaquina() == null) {
                    continue;
                }
                if (node.getMaquina().getIdmaquina() == nodeActual.getMaquina().getIdmaquina()) {

                    if (superposicion(node, nodeActual)) {
                        return true;
                    }

                }
            }
        }

        return false;

    }

    private boolean haySuperposicionEmpleadoMaquina(EtapaProduccionNode nodeActual) {

        TreePath tp = null;
        EtapaProduccionNode node = null;
        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();

            if (obj instanceof EtapaProduccionNode) {
                node = (EtapaProduccionNode) obj;

                if (nodeActual.equals(node)) {
                    continue;
                }
                if (node.getMaquina() != null && nodeActual.getMaquina() != null) {
                    if (node.getMaquina().getIdmaquina() == nodeActual.getMaquina().getIdmaquina()) {

                        if (superposicion(node, nodeActual)) {
                            return true;
                        }

                    }
                }

                if (node.getEmpleado() != null && nodeActual.getEmpleado() != null) {
                    if (node.getEmpleado().getIdempleado() == nodeActual.getEmpleado().getIdempleado()) {

                        if (superposicion(node, nodeActual)) {
                            return true;
                        }

                    }
                }
            }
        }

        return false;

    }

    private boolean superposicion(EtapaProduccionNode node, EtapaProduccionNode nodeActual) {
        Date finNode = node.getFinEtapa();
        Date inicioNode = node.getInicioEtapa();
        Date inicioNodeActual = nodeActual.getInicioEtapa();
        Date finNodeActual = nodeActual.getFinEtapa();

        return Fecha.superposicion(inicioNode, finNode, inicioNodeActual, finNodeActual);

    }

    private boolean haySuperposicionAsignacion(EtapaProduccionNode node, int tipo) {

        switch (tipo) {
            case EMPLEADO:
                return haySuperposicionEmpleado(node);
            case MAQUINA:
                return haySuperposicionMaquina(node);
            case EMPLEADO_MAQUINA:
                return haySuperposicionEmpleadoMaquina(node);
            default:
                return false;
        }
    }

    private void hplAsignarMaquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarMaquinasActionPerformed
        if (validarEtapaSeleccionada()) {
            setVisiblePanel(pnlMaquinas.getName());
            lstMaquinas = gestor.obtenerMaquinas();
            maquinasNoDisponibles(
                    lstMaquinas);
            tblMaquinas.updateUI();
        }
    }//GEN-LAST:event_hplAsignarMaquinasActionPerformed

    private void maquinasNoDisponibles(List<Maquina> list) {
        if (hashMaquinasNoDisponible == null) {
            hashMaquinasNoDisponible = new HashMap<Long, Maquina>();
        }
        for (Maquina maquina : list) {
            TreePath path = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
            EtapaProduccionNode node = (EtapaProduccionNode) path.getLastPathComponent();
            List<Detalleplanificacionproduccion> set = maquina.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalle : set) {
                GregorianCalendar detalleInicio = new GregorianCalendar();
                detalleInicio.setTime(detalle.getFechainicio());
                detalleInicio.set(Calendar.HOUR_OF_DAY, detalle.getHorainicio().getHours());
                detalleInicio.set(Calendar.MINUTE, detalle.getHorainicio().getMinutes());
                detalleInicio.set(Calendar.SECOND, detalle.getHorainicio().getSeconds());
                GregorianCalendar detalleFin = new GregorianCalendar();
                detalleFin.setTime(detalle.getFechafin());
                detalleFin.set(Calendar.HOUR_OF_DAY, detalle.getHorafin().getHours());
                detalleFin.set(Calendar.MINUTE, detalle.getHorafin().getMinutes());
                detalleFin.set(Calendar.SECOND, detalle.getHorafin().getSeconds());
                if (node.getInicioEtapa().compareTo(detalleInicio.getTime()) >= 0 && node.getFinEtapa().compareTo(detalleFin.getTime()) < 0) {
                    hashMaquinasNoDisponible.put(maquina.getIdmaquina(), maquina);
                } else if (node.getFinEtapa().compareTo(detalleFin.getTime()) <= 0 && node.getFinEtapa().compareTo(detalleInicio.getTime()) > 0) {
                    hashMaquinasNoDisponible.put(maquina.getIdmaquina(), maquina);
                } else if (node.getInicioEtapa().compareTo(detalleInicio.getTime()) <= 0 && node.getFinEtapa().compareTo(detalleFin.getTime()) >= 0) {
                    hashMaquinasNoDisponible.put(maquina.getIdmaquina(), maquina);
                }
            }
        }
    }

    private void btnAsignarMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarMaquinaActionPerformed
        maquinaSeleccionada = (metalsoft.datos.jpa.entity.Maquina) lstMaquinas.get(tblMaquinas.getSelectedRow());
        //obtengo el nodo seleccionado
        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();
        //el nodo seleccionado tiene que ser uno de etapaproduccion
        if (obj instanceof EtapaProduccionNode) {
            EtapaProduccionNode node = (EtapaProduccionNode) obj;
            node.setMaquina(maquinaSeleccionada);
            /*
             * validar que el empleado seleccionado no este asignado en el mismo horario de la etapa actual
             */

            boolean superposicion = haySuperposicionAsignacion(node, MAQUINA);
            if (superposicion) {
                node.setMaquina(null);
                JOptionPane.showMessageDialog(this, "La máquina ya esta asignada a otra etapa de produccion dentro del horario de la etapa actual");
                return;
            }
        }
        setVisiblePanel(pnlTreeTable.getName());
    }//GEN-LAST:event_btnAsignarMaquinaActionPerformed

    private void limpiarCampos() {
        trtDetalleProcProd.removeAll();
        iniciarTreeTable();
        tblEmpleado.removeAll();
        tblMaquinas.removeAll();
        pnlDispHoraria.removeAll();
        txtValorBusqueda.setText("");
        txtObservaciones.setText("");
        setEnabledComponents(false);
        setVisiblePanel(pnlTreeTable.getName());
        tblPedidos.updateUI();
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        gestor.limpiarSessionHibernate();
    }//GEN-LAST:event_formWindowClosing

    private void hplVerDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplVerDisponibilidadActionPerformed
        pnlDisponibilidad.removeAll();
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries unavailable = new TaskSeries("Etapas Producción");

        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            TreePath tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();
            // si es una pieza entonces genero una nueva tarea para que se muestre en el grafico
            if (obj instanceof PiezaNode) {
                PiezaNode piezaNode = (PiezaNode) obj;

                Date minDatePieza = null;
                Date maxDatePieza = null;

                Task taskActual = null;

                List<MutableTreeTableNode> lstChildrens = piezaNode.getChildren();
                List<Task> lstSubTasks = new ArrayList<Task>();
                for (MutableTreeTableNode mutableTreeTableNode : lstChildrens) {
                    /*
                     * para cada pieza agrego subtareas que en este caso serian las etapas de produccion
                     */
                    if (mutableTreeTableNode instanceof EtapaProduccionNode) {
                        EtapaProduccionNode node = (EtapaProduccionNode) mutableTreeTableNode;

                        if (node.getInicioEtapa() != null) {

                            Task subTaskActual = null;

                            int difDias = Fecha.diferenciaEnDias(node.getInicioEtapa(), node.getFinEtapa());

                            if (difDias != 0) {
                                Date fechaFin = (Date) node.getInicioEtapa().clone();
                                fechaFin.setHours(Jornada.HORA_FIN_JORNADA);
                                fechaFin.setMinutes(0);
                                fechaFin.setSeconds(0);

                                subTaskActual = new Task(node.getEtapa().getNombre(),
                                        node.getInicioEtapa(),
                                        fechaFin);

                                lstSubTasks.add(subTaskActual);

                                for (int j = 0; j < difDias; j++) {

                                    Calendar calendar = new GregorianCalendar();
                                    calendar.setTime(node.getInicioEtapa());

                                    Date newFechaInicio = Fecha.addDias(calendar, j + 1).getTime();
                                    newFechaInicio.setHours(Jornada.HORA_INICIO_JORNADA);
                                    newFechaInicio.setMinutes(0);
                                    newFechaInicio.setSeconds(0);

                                    Date newFechaFin = (Date) newFechaInicio.clone();

                                    if ((j + 1) == difDias) {
                                        newFechaFin = node.getFinEtapa();
                                    } else {
                                        newFechaFin.setHours(Jornada.HORA_FIN_JORNADA);
                                        newFechaFin.setMinutes(0);
                                        newFechaFin.setSeconds(0);
                                    }

                                    subTaskActual = new Task(node.getEtapa().getNombre(), newFechaInicio, newFechaFin);

                                    lstSubTasks.add(subTaskActual);
                                }
                            } else {
                                subTaskActual = new Task(node.getEtapa().getNombre(), node.getInicioEtapa(), node.getFinEtapa());
                                lstSubTasks.add(subTaskActual);
                            }

//                            lstSubTasks.add(subTaskActual);

                            if (minDatePieza == null) {
                                minDatePieza = node.getInicioEtapa();
                            } else {
                                if (minDatePieza.compareTo(node.getInicioEtapa()) > 0) {
                                    minDatePieza = node.getInicioEtapa();
                                }
                            }
                            if (maxDatePieza == null) {
                                maxDatePieza = node.getFinEtapa();
                            } else {
                                if (maxDatePieza.compareTo(node.getFinEtapa()) < 0) {
                                    maxDatePieza = node.getFinEtapa();
                                }
                            }
                        }
                    }

                }


                if (minDatePieza != null && maxDatePieza != null) {
                    taskActual = new Task(piezaNode.getPieza().getNombre(), minDatePieza, maxDatePieza);
                    for (Task task : lstSubTasks) {
                        taskActual.addSubtask(task);
                    }
                } else {
                    taskActual = new Task(piezaNode.getPieza().getNombre(), Fecha.fechaActualDate(), Fecha.fechaActualDate());
                }
                //agrego la tarea la serie de taras
                unavailable.add(taskActual);
            }//fin if piezaNode
        }

        dataset.add(unavailable);
// title, domain axis, range axis, dataset, legend, tooltip, urls
        JFreeChart chart = ChartFactory.createGanttChart("Producción", "Piezas", "Fecha", dataset, true, true, false);
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        //      plot.getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.green);


        // //////////

//        StandardCategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator() {
//
//            public String generateLabel(CategoryDataset dataset,
//                    int series, int category) {
//                return dataset.getColumnKey(category).toString();
////                return "GENERATE LABEL !!!";
//            }
//        };
//        renderer.setItemLabelGenerator(generator);
//        renderer.setItemLabelsVisible(true);
//        renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator() {
//
//            public String generateToolTip(CategoryDataset dataset,
//                    int series, int category) {
//                Comparable com = dataset.getColumnKey(category);
//                DatasetGroup dg = dataset.getGroup();
//                return dataset.getColumnKey(category).toString();
////                return "GENERATE TOOLTIP !!!";
//            }
//        });

        // /////////


        // ///////////////////////////////////////////////

//        MyGanttRenderer renderer = new MyGanttRenderer();
//        plot.setRenderer(renderer);
//
//        renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {
//
//            public String generateLabel(CategoryDataset dataSet, int series, int categories) {
//                /* your code to get the label */
//                return "Label";
//            }
//
//            public String generateColumnLabel(CategoryDataset dataset, int categories) {
//                return dataset.getColumnKey(categories).toString();
//            }
//
//            public String generateRowLabel(CategoryDataset dataset, int series) {
//                return dataset.getRowKey(series).toString();
//            }
//        });
//
//        renderer.setSeriesPaint(0, Color.green);
//        renderer.setBaseItemLabelsVisible(true);
//        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_LEFT));

        // ///////////////////////////////////////////////

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);

        chart.setBorderVisible(true);
        chartPanel.setBounds(0, 0, pnlDisponibilidad.getWidth(), pnlDisponibilidad.getHeight());
        pnlDisponibilidad.add(chartPanel);

        setVisiblePanel(pnlDisponibilidad.getName());
    }//GEN-LAST:event_hplVerDisponibilidadActionPerformed
    private void hplVerPlanificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplVerPlanificacionActionPerformed
        setVisiblePanel(pnlTreeTable.getName());
    }//GEN-LAST:event_hplVerPlanificacionActionPerformed

    private void hplObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplObservacionesActionPerformed
        setVisiblePanel(pnlObservaciones.getName());
    }//GEN-LAST:event_hplObservacionesActionPerformed

    private void cargarDatosTreeTable(List<metalsoft.datos.jpa.entity.Detallepresupuesto> detallepresupuestos) {
        DefaultMutableTreeTableNode raiz = new DefaultMutableTreeTableNode(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido()));
        trtDetalleProcProd.removeAll();
        trtDetalleProcProd.setTreeTableModel(new TablaPlanificacionModel(raiz, listColumnNamesTreeTable));
        /*
         * obtengo el detalle del presupuesto
         */
        Iterator<metalsoft.datos.jpa.entity.Detallepresupuesto> it = detallepresupuestos.iterator();
        metalsoft.datos.jpa.entity.Detallepresupuesto dp = null;
        ProductoNode prod = null;
        Date finEtapaAnterior = null;
        /*
         * recorro el detalle para obtener cada uno de los productos con sus piezas y etapas
         */
        Map<Long, Integer> mapIndexProducto = new HashMap<Long, Integer>();

        while (it.hasNext()) {
            dp = it.next();

            if (!mapIndexProducto.containsKey(dp.getIdproducto().getIdproducto())) {
                mapIndexProducto.put(dp.getIdproducto().getIdproducto(), 0);
            } else {
                Integer index = mapIndexProducto.get(dp.getIdproducto().getIdproducto());
                mapIndexProducto.put(dp.getIdproducto().getIdproducto(), index + 1);
            }

            prod = new ProductoNode(dp.getIdproducto());
            prod.setIndexProducto(mapIndexProducto.get(dp.getIdproducto().getIdproducto()));
            raiz.add(prod);
            List<metalsoft.datos.jpa.entity.Detalleproductopresupuesto> setDetProPre = dp.getDetalleproductopresupuestoList();
            Iterator<metalsoft.datos.jpa.entity.Detalleproductopresupuesto> itDetProPre = setDetProPre.iterator();
            PiezaNode pieza = null;
            metalsoft.datos.jpa.entity.Detalleproductopresupuesto detProPre = null;
            /*
             * recorro los productos del detalle
             */
            while (itDetProPre.hasNext()) {
                detProPre = itDetProPre.next();
                prod.setDetalleProductoPresupuesto(detProPre);
                pieza = new PiezaNode(detProPre.getIdpieza());
                prod.add(pieza);
                List<metalsoft.datos.jpa.entity.Detallepiezapresupuesto> setDetPiPre = detProPre.getDetallepiezapresupuestoList();
                metalsoft.datos.jpa.entity.Detallepiezapresupuesto detPiPre = null;
                Iterator<metalsoft.datos.jpa.entity.Detallepiezapresupuesto> itDetPiPre = setDetPiPre.iterator();
                EtapaProduccionNode etapaProd = null;
                /*
                 * recorro las piezas de cada producto
                 */
                while (itDetPiPre.hasNext()) {
                    detPiPre = itDetPiPre.next();
                    etapaProd = new EtapaProduccionNode(detPiPre.getIdetapa());
                    etapaProd.setDetallePiezaPresupuesto(detPiPre);
//                    etapaProd.setMaquina(detPiPre.getIdetapa().getMaquina());
//                    int horaInicioJornada = Jornada.HORA_INICIO_JORNADA;
//                    int horaFinJornada = Jornada.HORA_FIN_JORNADA;
//                    Date fechaInicio = null;
//                    if (finEtapaAnterior == null) {
//                        fechaInicio = Fecha.fechaActualDate();
//                    } else {
//                        fechaInicio = finEtapaAnterior;
//                    }
//                    GregorianCalendar inicio = new GregorianCalendar();
//                    inicio.setTime(fechaInicio);
//                    inicio.add(Calendar.MINUTE, Jornada.MINUTOS_ENTRE_ETAPAS);
//                    inicio = Calculos.calcularFechaInicio(horaInicioJornada, horaFinJornada, inicio);
//                    GregorianCalendar fin = Calculos.calcularFechaFin(horaInicioJornada, horaFinJornada, inicio, detPiPre.getDuracionpiezaxetapa().getHours(), detPiPre.getDuracionpiezaxetapa().getMinutes());
//                    etapaProd.setInicioEtapa(inicio.getTime());
//                    etapaProd.setFinEtapa(fin.getTime());
                    pieza.add(etapaProd);
//                    finEtapaAnterior = fin.getTime();
                }

                finEtapaAnterior = null;
            }
        }
        trtDetalleProcProd.expandAll();
        trtDetalleProcProd.setEditable(true);

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
    private javax.swing.JButton btnVerDisponibilidad;
    private javax.swing.ButtonGroup buttonGroup1;
    private org.jdesktop.swingx.JXHyperlink hplAsignarEmpleado;
    private org.jdesktop.swingx.JXHyperlink hplAsignarMaquinas;
    private org.jdesktop.swingx.JXHyperlink hplObservaciones;
    private org.jdesktop.swingx.JXHyperlink hplVerDisponibilidad;
    private org.jdesktop.swingx.JXHyperlink hplVerPlanificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnlDispHoraria;
    private javax.swing.JPanel pnlDisponibilidad;
    private javax.swing.JPanel pnlEmpleado;
    private javax.swing.JPanel pnlMaquinas;
    private javax.swing.JPanel pnlObservaciones;
    private javax.swing.JPanel pnlTreeTable;
    private metalsoft.beans.SubirBajar subirBajar;
    private org.jdesktop.swingx.JXTable tblEmpleado;
    private org.jdesktop.swingx.JXTable tblMaquinas;
    private org.jdesktop.swingx.JXTable tblPedidos;
    private org.jdesktop.swingx.JXTreeTable trtDetalleProcProd;
    private org.jdesktop.swingx.JXTaskPane tskPanel;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtValorBusqueda;
    // End of variables declaration//GEN-END:variables

    private Date obtenerFechaDisponibilidadEmpleadoAsignacionActual(Empleado empleado, EtapaProduccionNode node) {
        Date resultado = null;

        int horas = node.getDetallePiezaPresupuesto().getDuracionpiezaxetapa().getHours();
        int minutos = node.getDetallePiezaPresupuesto().getDuracionpiezaxetapa().getMinutes();

        List<EtapaProduccionNode> lstEtapasNode = mapAsignacionActualEmpleados.get(empleado.getIdempleado());

        List<Disponibilidadhoraria> lstDisp = crearListDisponibilidadHoraria(lstEtapasNode);

        resultado = obtenerFechaDisponibilidadEmpleado(lstDisp, horas, minutos, Fecha.fechaActualDate());

        return resultado;
    }

    private List<Disponibilidadhoraria> crearListDisponibilidadHoraria(final List<EtapaProduccionNode> lstNode) {

        List<Disponibilidadhoraria> lstDisp = new ArrayList<Disponibilidadhoraria>();

        Disponibilidadhoraria disponibilidadhoraria = null;

        for (EtapaProduccionNode node : lstNode) {

            disponibilidadhoraria = new Disponibilidadhoraria();

            Date fechaInicio = (Date) node.getInicioEtapa().clone();
            Date fechaFin = (Date) node.getFinEtapa().clone();

            disponibilidadhoraria.setFecha(fechaInicio);
            disponibilidadhoraria.setHorainicio(fechaInicio);

            Date horaFinDisponibilidad = null;

            int difDias = Fecha.diferenciaEnDias(fechaInicio, fechaFin);

            if (difDias != 0) {
                horaFinDisponibilidad = fechaInicio;
                horaFinDisponibilidad.setHours(Jornada.HORA_FIN_JORNADA);
                horaFinDisponibilidad.setMinutes(0);
                horaFinDisponibilidad.setSeconds(0);

                disponibilidadhoraria.setHorafin(horaFinDisponibilidad);

                lstDisp.add(disponibilidadhoraria);

                for (int i = 0; i < difDias; i++) {
                    Disponibilidadhoraria disphoraria = new Disponibilidadhoraria();

                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(fechaInicio);

                    Date newFechaInicio = Fecha.addDias(calendar, i + 1).getTime();
                    Date newFechaFin = (Date) newFechaInicio.clone();

                    disphoraria.setFecha(newFechaInicio);

                    newFechaInicio.setHours(Jornada.HORA_INICIO_JORNADA);
                    newFechaInicio.setMinutes(0);
                    newFechaInicio.setSeconds(0);

                    disphoraria.setHorainicio(newFechaInicio);

                    if ((i + 1) == difDias) {
                        disphoraria.setHorafin(node.getFinEtapa());
                    } else {
                        newFechaFin.setHours(Jornada.HORA_FIN_JORNADA);
                        newFechaFin.setMinutes(0);
                        newFechaFin.setSeconds(0);

                        disphoraria.setHorafin(newFechaFin);
                    }

                    lstDisp.add(disphoraria);

                }
            } else {
                disponibilidadhoraria.setHorafin(fechaFin);
                lstDisp.add(disponibilidadhoraria);
            }
        }

        return (lstDisp.isEmpty() ? null : lstDisp);

    }

    private void setInicioFinEtapaProduccion(EtapaProduccionNode node, Date fechaInicial) {
        Empleado empleado = node.getEmpleado();
        Maquina maquina = node.getMaquina();

        int horas = node.getDetallePiezaPresupuesto().getDuracionpiezaxetapa().getHours();
        int minutos = node.getDetallePiezaPresupuesto().getDuracionpiezaxetapa().getMinutes();

        List<Disponibilidadhoraria> lstDisponibilidad = JpaUtil.getDisponibilidadEmpleado(empleado);

        Date inicioEtapaEmpleado = obtenerFechaDisponibilidadEmpleado(lstDisponibilidad, horas, minutos, fechaInicial);

        node.setInicioEtapa(inicioEtapaEmpleado);
        Calendar inicioEtapa = new GregorianCalendar();
        inicioEtapa.setTime(inicioEtapaEmpleado);
        node.setFinEtapa(Calculos.calcularFechaFin(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, inicioEtapa, horas, minutos).getTime());

    }

    public static Date obtenerFechaDisponibilidadEmpleado(List<Disponibilidadhoraria> lstDisponibilidad, int horas, int minutos, Date fechaActual) {

        Date resultado = null;

        /*
         * quiere decir que no esta ocupado
         */
        if (lstDisponibilidad == null || lstDisponibilidad.isEmpty()) {

//            return Calculos.calcularFechaInicio(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, Fecha.fechaActualCalendar()).getTime();
            return fechaActual;
        }


        Disponibilidadhoraria ultimaDisp = null;
        Date dif = null;
        boolean hayDisponibilidad = false;

//        Date fechaActual = Fecha.fechaActualDate();

        for (Disponibilidadhoraria disponibilidadhoraria : lstDisponibilidad) {

            Date fechaFinDisp = (Date) disponibilidadhoraria.getFecha().clone();
            fechaFinDisp.setHours(disponibilidadhoraria.getHorafin().getHours());
            fechaFinDisp.setMinutes(disponibilidadhoraria.getHorafin().getMinutes());

            Date fechaInicioDisp = (Date) disponibilidadhoraria.getFecha().clone();
            fechaInicioDisp.setHours(disponibilidadhoraria.getHorainicio().getHours());
            fechaInicioDisp.setMinutes(disponibilidadhoraria.getHorainicio().getMinutes());

            if (Fecha.esMismaFecha(fechaActual, fechaInicioDisp) && fechaActual.getHours() < fechaInicioDisp.getHours() && ultimaDisp == null) {
                Date difFechaActual = Fecha.diferenciaEnMinutosHoras(fechaActual, fechaInicioDisp);
                if (difFechaActual.getHours() > horas) {
                    resultado = Calculos.calcularFechaInicio(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, Fecha.fechaActualCalendar()).getTime();
                    hayDisponibilidad = true;
                    break;
                } else if (difFechaActual.getHours() == horas && difFechaActual.getMinutes() > fechaActual.getMinutes()) {
                    resultado = Calculos.calcularFechaInicio(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, Fecha.fechaActualCalendar()).getTime();
                    hayDisponibilidad = true;
                    break;
                }
            } else if (ultimaDisp != null) {

                if (Fecha.esMismaFecha(ultimaDisp.getFecha(), disponibilidadhoraria.getFecha())) {
                    /*
                     * comparar la hora de fin de la disponibilidad anterior con la 
                     * hora de inicio de la actual,
                     * si la diferencia es mayor a la que necesito entonces asigno 
                     * la hora fin de la anterior como inicio de etapa
                     */

                    Date dispAnt = ultimaDisp.getFecha();
                    dispAnt.setHours(ultimaDisp.getHorafin().getHours());
                    dispAnt.setMinutes(ultimaDisp.getHorafin().getMinutes());
                    dispAnt.setSeconds(0);

                    Date disp = disponibilidadhoraria.getFecha();
                    disp.setHours(disponibilidadhoraria.getHorainicio().getHours());
                    disp.setMinutes(disponibilidadhoraria.getHorainicio().getMinutes());
                    disp.setSeconds(0);

                    boolean calcularDif = false;
                    /*
                     * si la fecha fin anterior es mayor a la actual. calcular
                     */
                    if (dispAnt.compareTo(fechaActual) > 0) {

                        calcularDif = true;

                    } /*
                     * si la fecha fin anterior no es mayor a la fecha actual entonces ver si la
                     * fecha inicio disp actual es mayor a la fecha actual. si es asi asignar como fecha fin
                     * de disp anterior la fecha actual y calcular la dif
                     */ else if (disp.compareTo(fechaActual) > 0) {

                        calcularDif = true;
                        dispAnt = fechaActual;

                    }

                    /*
                     * si no se entro en alguno de los if anteriores es porque son fechas anteriores a la actual
                     * y no lo puedo asignar por mas que tenga disponibilidad.
                     */


                    if (calcularDif) {
                        dif = Fecha.diferenciaEnMinutosHoras(dispAnt, disp);

                        if (horas < dif.getHours()) {
                            resultado = dispAnt;
                            hayDisponibilidad = true;
                            break;
                        } else if (horas == dif.getHours() && minutos <= dif.getMinutes()) {
                            resultado = dispAnt;
                            hayDisponibilidad = true;
                            break;
                        }

                    }

                } else {
                    /*
                     * pase al dia siguiente, 
                     */

                    /* 
                     * ver si tengo la diferencia necesaria entre la hora de fin de la del dia
                     * anterior con respecto a la hora de fin de jornada. Si es asi asigno la hora
                     * de fin de la anterior como inicio etapa.
                     */

                    if (ultimaDisp.getHorafin().getHours() < Jornada.HORA_FIN_JORNADA) {
                        Date dispAnt = new Date();
                        dispAnt.setHours(ultimaDisp.getHorafin().getHours());
                        dispAnt.setMinutes(ultimaDisp.getHorafin().getMinutes());
                        dispAnt.setSeconds(0);

                        Date finJornada = new Date();
                        finJornada.setHours(Jornada.HORA_FIN_JORNADA);
                        finJornada.setMinutes(0);
                        finJornada.setSeconds(0);

                        dif = Fecha.diferenciaEnMinutosHoras(dispAnt, finJornada);

                        if (horas <= dif.getHours() && minutos <= dif.getMinutes()) {
                            resultado = dispAnt;
                            hayDisponibilidad = true;
                            break;
                        }
                    }
                    /* ver si tengo la diferencia necesaria entre la hora de
                     * inicio de jornada y la hora de inicio. si es asi asigno la hora de inicio de jornada como
                     * inicio de etapa.
                     */

                    /*
                     * si no encontre disponibilidad en la anterior
                     */
                    if (!hayDisponibilidad) {

                        if (fechaInicioDisp.getHours() > Jornada.HORA_INICIO_JORNADA) {
                            Date horaDisp = new Date();
                            horaDisp.setHours(fechaInicioDisp.getHours());
                            horaDisp.setMinutes(fechaInicioDisp.getMinutes());
                            horaDisp.setSeconds(0);

                            Date inicioJornada = new Date();
                            inicioJornada.setHours(Jornada.HORA_INICIO_JORNADA);
                            inicioJornada.setMinutes(0);
                            inicioJornada.setSeconds(0);

                            dif = Fecha.diferenciaEnMinutosHoras(inicioJornada, fechaInicioDisp);

                            if (horas <= dif.getHours() && minutos <= dif.getMinutes()) {
                                resultado = inicioJornada;
                                hayDisponibilidad = true;
                                break;
                            }
                        }
                    }
                }

            }

            ultimaDisp = disponibilidadhoraria;
        }//fin for disponibilidades

        if (!hayDisponibilidad) {
            /*
             * asignar la hora fin de la ultima disp.
             */
            Calendar calInicio = new GregorianCalendar();
            calInicio.setTime(Fecha.dateWithSpecificValues(ultimaDisp.getFecha(), ultimaDisp.getHorafin().getHours(), ultimaDisp.getHorafin().getMinutes(), 0));
            resultado = Calculos.calcularFechaInicio(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, calInicio).getTime();
        }

        return resultado;
    }

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
                    return "$ " + Decimales.con2Decimales(view.getMontototal());
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
            metalsoft.datos.jpa.entity.Empleado empleado = lstEmpleados.get(rowIndex);

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

        private String disponibilidadEmpleado(Empleado e) {
            if (hashEmpleadoNoDisponible.get(e.getIdempleado()) == null) {
                return "Disponible";
            }
            return "Ocupado";
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
            metalsoft.datos.jpa.entity.Maquina maquina = lstMaquinas.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return maquina.getNombre();
                case 1:
                    return maquina.getMarca().getNombre();
                case 2:
                    return maquina.getDescripcion();
                case 3:
                    return disponibilidadMaquina(maquina);
                default:
                    return null;
            }
        }

        private String disponibilidadMaquina(Maquina e) {
            if (hashMaquinasNoDisponible.get(e.getIdmaquina()) == null) {
                return "Disponible";
            }
            return "Ocupado";
        }
    }

    class ProductoNode extends AbstractMutableTreeTableNode {

        private metalsoft.datos.jpa.entity.Producto producto;
        private metalsoft.datos.jpa.entity.Detalleproductopresupuesto detalleProductoPresupuesto;
        private Integer indexProducto = 1;

        public ProductoNode(metalsoft.datos.jpa.entity.Producto producto) {
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

        public Detalleproductopresupuesto getDetalleProductoPresupuesto() {
            return detalleProductoPresupuesto;
        }

        public void setDetalleProductoPresupuesto(Detalleproductopresupuesto detalleProductoPresupuesto) {
            this.detalleProductoPresupuesto = detalleProductoPresupuesto;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public Integer getIndexProducto() {
            return indexProducto;
        }

        public void setIndexProducto(Integer indexProducto) {
            this.indexProducto = indexProducto;
        }
    }

    class PiezaNode extends AbstractMutableTreeTableNode {

        private metalsoft.datos.jpa.entity.Pieza pieza;

        public PiezaNode(metalsoft.datos.jpa.entity.Pieza pieza) {
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

        public metalsoft.datos.jpa.entity.Pieza getPieza() {
            return pieza;
        }
    }

    class EtapaProduccionNode extends AbstractMutableTreeTableNode {

        private metalsoft.datos.jpa.entity.Etapadeproduccion etapa;
        private metalsoft.datos.jpa.entity.Maquina maquina;
        private metalsoft.datos.jpa.entity.Empleado empleado;
        private metalsoft.datos.jpa.entity.Detallepiezapresupuesto detallePiezaPresupuesto;
        private Date inicioEtapa, finEtapa;

        public Date getFinEtapa() {
            return finEtapa;
        }

        public void setFinEtapa(Date finEtapa) {
            this.finEtapa = finEtapa;
        }

        public Date getInicioEtapa() {
            return inicioEtapa;
        }

        public void setInicioEtapa(Date inicioEtapa) {
            this.inicioEtapa = inicioEtapa;
        }

        public EtapaProduccionNode(metalsoft.datos.jpa.entity.Etapadeproduccion etapa) {
            this.etapa = etapa;
        }

        public Detallepiezapresupuesto getDetallePiezaPresupuesto() {
            return detallePiezaPresupuesto;
        }

        public void setDetallePiezaPresupuesto(Detallepiezapresupuesto detallePiezaPresupuesto) {
            this.detallePiezaPresupuesto = detallePiezaPresupuesto;
        }

        public metalsoft.datos.jpa.entity.Empleado getEmpleado() {
            return empleado;
        }

        public void setEmpleado(metalsoft.datos.jpa.entity.Empleado empleado) {
            this.empleado = empleado;
        }

        public metalsoft.datos.jpa.entity.Etapadeproduccion getEtapa() {
            return etapa;
        }

        public void setEtapa(metalsoft.datos.jpa.entity.Etapadeproduccion etapa) {
            this.etapa = etapa;
        }

        public metalsoft.datos.jpa.entity.Maquina getMaquina() {
            return maquina;
        }

        public void setMaquina(metalsoft.datos.jpa.entity.Maquina maquina) {
            this.maquina = maquina;
        }

        @Override
        public Object getValueAt(int i) {
            try {
                switch (i) {
                    case 0:
                        return etapa.getNombre();
                    case 1:
                        return Fecha.parseToStringFechaHora(getInicioEtapa());
                    case 2:
                        return Fecha.parseToStringFechaHora(getFinEtapa());
                    case 3:
                        return empleado.getNombre() + " " + empleado.getApellido();
                    case 4:
                        return maquina.getNombre();
                }
            } catch (NullPointerException ex) {
                return "----";
            }
            return "";
        }

//        @Override
//        public void setValueAt(Object aValue, int column) {
//
//            String fecha = null;
//            Date fechaDate = null;
//            GregorianCalendar inicio = null;
//            TreePath treePath = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getEditingRow());
//            Object obj = treePath.getLastPathComponent();
//            if (obj instanceof EtapaProduccionNode) {
//                EtapaProduccionNode node = (EtapaProduccionNode) obj;
//
//                switch (column) {
//                    case 1:
//                        Date fechaInicialAnterior = node.getInicioEtapa();
//                        Date fechaFinalAnterior = node.getFinEtapa();
//
//                        fecha = (String) aValue;
//                        fechaDate = Fecha.parseToDateConHoraMinuto(fecha);
//
//                        if (fechaDate == null) {
//                            JOptionPane.showMessageDialog(null, "La fecha está mal formada. Por favor ingrese una fecha válida \nEjemplo: 19/02/2011 08:00");
//                            return;
//                        }
//                        node.setInicioEtapa(fechaDate);
//                        inicio = new GregorianCalendar();
//                        inicio.setTime(node.getInicioEtapa());
//                        node.setFinEtapa(Calculos.calcularFechaFin(Jornada.HORA_INICIO_JORNADA,
//                                Jornada.HORA_FIN_JORNADA, inicio,
//                                node.getDetallePiezaPresupuesto().getDuracionpiezaxetapa().getHours(),
//                                node.getDetallePiezaPresupuesto().getDuracionpiezaxetapa().getMinutes()).getTime());
//                        /*
//                         * validar que la nueva fecha y hora no se superponga con alguna de las otras etapas que tenga
//                         * la misma maquina y empleado
//                         */
//
//                        if (haySuperposicionAsignacion(node, EMPLEADO_MAQUINA)) {
//
//                            node.setInicioEtapa(fechaInicialAnterior);
//                            node.setFinEtapa(fechaFinalAnterior);
//                            JOptionPane.showMessageDialog(null, "No se puede modificar la fecha porque se producen superposiciones de Empleados o Máquinas");
//
//                        }
//
//                        break;
//                }
//            }
//
//        }
        public int getColumnCount() {
            return columnCountTreeTable;
        }
    }

    class TablaPlanificacionModel extends DefaultTreeTableModel {

        private TablaPlanificacionModel(DefaultMutableTreeTableNode raiz, ArrayList<String> listColumnNamesTreeTable) {
            super(raiz, listColumnNamesTreeTable);
        }

        @Override
        public boolean isCellEditable(Object node, int column) {
            switch (column) {
                default:
                    return super.isCellEditable(node, column);
            }
        }
    }
}
