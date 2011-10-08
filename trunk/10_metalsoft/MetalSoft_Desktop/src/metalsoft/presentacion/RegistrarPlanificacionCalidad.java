/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarPlanificacionCalidad.java
 *
 * Created on 28/08/2011, 16:48:26
 */
package metalsoft.presentacion;

import java.awt.Color;
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
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Disponibilidadhoraria;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Maquina;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Procesocalidad;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.negocio.gestores.GestorRegistrarPlanificacionCalidad;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewPedidoConPlanificacionProduccion;
import metalsoft.presentacion.RegistrarPlanificacionProduccion.EtapaProduccionNode;
import metalsoft.util.Calculos;
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
public class RegistrarPlanificacionCalidad extends javax.swing.JDialog {

    private LinkedList<ViewPedidoConPlanificacionProduccion> filasPedidosConPlanificacionProduccion;
    private GestorRegistrarPlanificacionCalidad gestor;
    private metalsoft.datos.jpa.entity.Presupuesto presupuesto;
    private int columnCountTreeTable = 5;
    private ViewPedidoConPlanificacionProduccion viewPedidoSeleccionado;
    private ArrayList<String> listColumnNamesTreeTable;
    private HashMap<String, JPanel> hashPanels;
    private List<metalsoft.datos.jpa.entity.Empleado> lstEmpleados;
    private List<metalsoft.datos.jpa.entity.Maquina> lstMaquinas;
    private List<metalsoft.datos.jpa.entity.Planificacioncalidad> lstPlanificacionCalidad;
    private metalsoft.datos.jpa.entity.Maquina maquinaSeleccionada;
    private metalsoft.datos.jpa.entity.Empleado empleadoSeleccionado;
    private Task taskActual;
    private HashMap<Long, metalsoft.datos.jpa.entity.Empleado> hashEmpleadoNoDisponible;
    private HashMap<Long, Maquina> hashMaquinasNoDisponible;
    private final int EMPLEADO = 1;
    private final int MAQUINA = 2;
    private final int EMPLEADO_MAQUINA = 3;
    private Date fechaFinPrevista;
    private Date horaFinPrevista;
    private Map<Long, List<ProcesoCalidadNode>> mapAsignacionActualEmpleados;

    /** Creates new form RegistrarPlanificacionCalidad */
    public RegistrarPlanificacionCalidad() {
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
        gestor = new GestorRegistrarPlanificacionCalidad();
        mapAsignacionActualEmpleados = new HashMap<Long, List<ProcesoCalidadNode>>();
        filasPedidosConPlanificacionProduccion = new LinkedList<ViewPedidoConPlanificacionProduccion>();
        buscarPedidosConPlanificacionProduccion();
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
        if (obj instanceof ProcesoCalidadNode && objAnterior instanceof ProcesoCalidadNode) {
            ProcesoCalidadNode node = (ProcesoCalidadNode) obj;
            ProcesoCalidadNode nodeAnterior = (ProcesoCalidadNode) objAnterior;
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
            if (obj instanceof ProcesoCalidadNode && objSiguiente instanceof ProcesoCalidadNode) {
                ProcesoCalidadNode node = (ProcesoCalidadNode) obj;
                ProcesoCalidadNode nodeSiguiente = (ProcesoCalidadNode) objSiguiente;
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
        List<Detalleplanificacioncalidad> detalle = new ArrayList<Detalleplanificacioncalidad>();
        Planificacioncalidad plan = new Planificacioncalidad();
        Date menor = null, mayor = null;
        /*
         * recorro los nodos del arbol jtree
         */
        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            TreePath tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();
            /*
             * obtengo cada una de las etapas de produccion
             */
            if (obj instanceof ProcesoCalidadNode) {
                ProcesoCalidadNode node = (ProcesoCalidadNode) obj;
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
                Procesocalidad procesoCalidad = node.getProcesoCalidad();

                /*
                 * por cada etapa de produccion se crea un detalle de planificacion produccion
                 */
                Detalleplanificacioncalidad dpp = new Detalleplanificacioncalidad();
                dpp.setFechafin(node.getFinEtapa());
                dpp.setFechainicio(node.getInicioEtapa());
                dpp.setHorafin(node.getFinEtapa());
                dpp.setHorainicio(node.getInicioEtapa());
                dpp.setEmpleado(emp);
                dpp.setProcesocalidad(procesoCalidad);
                dpp.setMaquina(maq);
                dpp.setPieza(pieza);
                dpp.setProducto(prod);
                dpp.setIdplanificacioncalidad(plan);
                dpp.setOrden(orden);
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
        boolean result = gestor.guardarPlanificacionCalidad(plan, detalle);
        if (result) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente!");
            filasPedidosConPlanificacionProduccion.remove(viewPedidoSeleccionado);
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
        viewPedidoSeleccionado = filasPedidosConPlanificacionProduccion.get(tblPedidos.getSelectedRow());
        presupuesto = gestor.buscarPresupuesto(viewPedidoSeleccionado.getIdpresupuesto());
        setVisiblePanel(pnlTreeTable.getName());
        setEnabledComponents(true);
        cargarDatosTreeTable(presupuesto.getDetallepresupuestoList());
    }

    private void buscarFechaHoraFinProduccionPrevista() {
        fechaFinPrevista = viewPedidoSeleccionado.getFechafinprevista();
        Long idPlanificacionProduccion = viewPedidoSeleccionado.getIdplanificacionproduccion();
        horaFinPrevista = gestor.obtenerHoraFinPrevista(idPlanificacionProduccion);
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

    private void buscarPedidosConPlanificacionProduccion() {
        filasPedidosConPlanificacionProduccion = gestor.buscarPedidosConPlanificacionProduccion();
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
        beanBtnGuardar = new metalsoft.beans.BtnGuardar();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Planificación de Calidad");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Confirmados"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar Datos"));

        jRadioButton3.setSelected(true);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 809, Short.MAX_VALUE)
                        .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void empleadosNoDisponibles(List<Empleado> list) {
        if (hashEmpleadoNoDisponible == null) {
            hashEmpleadoNoDisponible = new HashMap<Long, Empleado>();
        }
        for (Empleado empleado : list) {
            TreePath path = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
            ProcesoCalidadNode node = (ProcesoCalidadNode) path.getLastPathComponent();
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
        if (obj instanceof ProcesoCalidadNode) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Etapa de Producción\npara poder asignar");
        }
        return false;
    }

    private boolean haySuperposicionEmpleado(ProcesoCalidadNode nodeActual) {


        TreePath tp = null;
        ProcesoCalidadNode node = null;
        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();

            if (obj instanceof ProcesoCalidadNode) {
                node = (ProcesoCalidadNode) obj;

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

    private boolean haySuperposicionMaquina(ProcesoCalidadNode nodeActual) {


        TreePath tp = null;
        ProcesoCalidadNode node = null;
        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();

            if (obj instanceof ProcesoCalidadNode) {
                node = (ProcesoCalidadNode) obj;

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

    private boolean haySuperposicionEmpleadoMaquina(ProcesoCalidadNode nodeActual) {

        TreePath tp = null;
        ProcesoCalidadNode node = null;
        for (int i = 0; i < trtDetalleProcProd.getRowCount(); i++) {
            tp = trtDetalleProcProd.getPathForRow(i);
            Object obj = tp.getLastPathComponent();

            if (obj instanceof ProcesoCalidadNode) {
                node = (ProcesoCalidadNode) obj;

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

    private boolean superposicion(ProcesoCalidadNode node, ProcesoCalidadNode nodeActual) {
        Date finNode = node.getFinEtapa();
        Date inicioNode = node.getInicioEtapa();
        Date inicioNodeActual = nodeActual.getInicioEtapa();
        Date finNodeActual = nodeActual.getFinEtapa();

        /*
         * intervalo del nodo que se esta recorriendo
         */
        Interval interval = new Interval(inicioNode.getTime(), finNode.getTime());
        /*
         * instante inicial y final del nodo actual
         * si alguno de estos instantes es contenido por el nodo recorrido entonces
         * hay superposicion
         */

        Instant insInicial = new Instant(inicioNodeActual.getTime());
        Instant insFinal = new Instant(finNodeActual.getTime());

        if (interval.contains(insInicial)) {
            return true;
        }

        if (interval.contains(insFinal)) {
            return true;
        }

        /*
         * puede que el nodo actual contenga el nodo que se esta recorriendo con lo
         * cual la fecha inicio y fin del nodo actual no estaran dentro del intervalo del
         * nodo recorrido pero puede haber superposicion.
         * para eso veo si el intervalo del nodo actual contiene al del nodo que se esta
         * recorriendo
         */
        Interval intervalNodoActual = new Interval(nodeActual.getInicioEtapa().getTime(), nodeActual.getFinEtapa().getTime());
        if (intervalNodoActual.contains(interval)) {
            return true;
        }

        return false;
    }

    private boolean haySuperposicionAsignacion(ProcesoCalidadNode node, int tipo) {

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

    private void maquinasNoDisponibles(List<Maquina> list) {
        if (hashMaquinasNoDisponible == null) {
            hashMaquinasNoDisponible = new HashMap<Long, Maquina>();
        }
        for (Maquina maquina : list) {
            TreePath path = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
            ProcesoCalidadNode node = (ProcesoCalidadNode) path.getLastPathComponent();
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

    private void cargarDatosTreeTable(List<metalsoft.datos.jpa.entity.Detallepresupuesto> detallepresupuestos) {

        buscarFechaHoraFinProduccionPrevista();

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
        while (it.hasNext()) {
            dp = it.next();
            prod = new ProductoNode(dp.getIdproducto());
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
                List<metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto> setDetPiPre = detProPre.getDetallepiezacalidadpresupuestoList();
                metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto detPiPre = null;
                Iterator<metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto> itDetPiPre = setDetPiPre.iterator();
                ProcesoCalidadNode procesoCalidadNode = null;
                /*
                 * recorro las piezas de cada producto
                 */
                while (itDetPiPre.hasNext()) {
                    detPiPre = itDetPiPre.next();
                    procesoCalidadNode = new ProcesoCalidadNode(detPiPre.getIdprocesocalidad());
//                    etapaProd.setMaquina(detPiPre.getIdetapa().getMaquina());
                    procesoCalidadNode.setDetallePiezaCalidadPresupuesto(detPiPre);
//                    int horaInicioJornada = Jornada.HORA_INICIO_JORNADA;
//                    int horaFinJornada = Jornada.HORA_FIN_JORNADA;
//                    Date fechaInicio = null;
//                    if (finEtapaAnterior == null) {
//                        fechaInicio = fechaFinPrevista;
//                        fechaInicio = Fecha.setHoraMinutoSegundo(fechaInicio, horaFinPrevista);
//                    } else {
//                        fechaInicio = finEtapaAnterior;
//                    }
//                    Calendar inicio = new GregorianCalendar();
//                    inicio.setTime(fechaInicio);
////                    inicio.setTime(new Date());
//                    inicio.add(Calendar.MINUTE, Jornada.MINUTOS_ENTRE_ETAPAS);
//                    inicio = Calculos.calcularFechaInicio(horaInicioJornada, horaFinJornada, inicio);
//                    GregorianCalendar fin = Calculos.calcularFechaFin(horaInicioJornada, horaFinJornada, inicio, detPiPre.getDuracionxpieza().getHours(), detPiPre.getDuracionxpieza().getMinutes());
//                    procesoCalidadNode.setInicioEtapa(inicio.getTime());
//                    procesoCalidadNode.setFinEtapa(fin.getTime());
                    pieza.add(procesoCalidadNode);
//                    finEtapaAnterior = fin.getTime();
                }

                finEtapaAnterior = null;
            }
        }
        trtDetalleProcProd.expandAll();
        trtDetalleProcProd.setEditable(true);

    }

    private void hplAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarEmpleadoActionPerformed
        if (validarEtapaSeleccionada()) {
            setVisiblePanel(pnlEmpleado.getName());
            lstEmpleados = gestor.obtenerEmpleados();
            empleadosNoDisponibles(
                    lstEmpleados);
            tblEmpleado.updateUI();
        }
}//GEN-LAST:event_hplAsignarEmpleadoActionPerformed

    private void hplAsignarMaquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarMaquinasActionPerformed
        if (validarEtapaSeleccionada()) {
            setVisiblePanel(pnlMaquinas.getName());
            lstMaquinas = gestor.obtenerMaquinas();
            maquinasNoDisponibles(
                    lstMaquinas);
            tblMaquinas.updateUI();
        }
}//GEN-LAST:event_hplAsignarMaquinasActionPerformed

    private void hplVerDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplVerDisponibilidadActionPerformed


        pnlDisponibilidad.removeAll();
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries unavailable = new TaskSeries("Procesos de Calidad");

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
                        ProcesoCalidadNode node = (ProcesoCalidadNode) mutableTreeTableNode;

                        if (node.getInicioEtapa() != null) {

                            Task subTaskActual = null;

                            int difDias = Fecha.diferenciaEnDias(node.getInicioEtapa(), node.getFinEtapa());

                            if (difDias != 0) {
                                Date fechaFin = (Date) node.getInicioEtapa().clone();
                                fechaFin.setHours(Jornada.HORA_FIN_JORNADA);
                                fechaFin.setMinutes(0);
                                fechaFin.setSeconds(0);

                                subTaskActual = new Task(node.getProcesoCalidad().getNombre(),
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

                                    subTaskActual = new Task(node.getProcesoCalidad().getNombre(), newFechaInicio, newFechaFin);

                                    lstSubTasks.add(subTaskActual);
                                }
                            } else {
                                subTaskActual = new Task(node.getProcesoCalidad().getNombre(), node.getInicioEtapa(), node.getFinEtapa());
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
        JFreeChart chart = ChartFactory.createGanttChart("Control de Calidad", "Piezas", "Fecha", dataset, true, true, false);
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        //      plot.getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.green);

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
            for (metalsoft.datos.jpa.entity.Disponibilidadhoraria dispHoraria : disp) {
                //creo una nueva tarea
                Date fecha = dispHoraria.getFecha();

                Calendar c1 = new GregorianCalendar();
                c1.setTime(dispHoraria.getFecha());

                Task task = unavailable.get(Fecha.parseToString(fecha));
                if (task == null) {
                    task = new Task(Fecha.parseToString(fecha),
                            new GregorianCalendar(c1.get(Calendar.YEAR),
                            c1.get(Calendar.MONTH),
                            c1.get(Calendar.DAY_OF_MONTH), Jornada.HORA_INICIO_JORNADA, 0, 0).getTime(),
                            new GregorianCalendar(c1.get(Calendar.YEAR),
                            c1.get(Calendar.MONTH),
                            c1.get(Calendar.DAY_OF_MONTH), Jornada.HORA_FIN_JORNADA, 0, 0).getTime());
                    //agrego la tarea la serie de taras
                    unavailable.add(task);
                }

                task.addSubtask(new Task("",
                        new GregorianCalendar(c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH),
                        c1.get(Calendar.DAY_OF_MONTH),
                        dispHoraria.getHorainicio().getHours(),
                        dispHoraria.getHorainicio().getMinutes(),
                        dispHoraria.getHorainicio().getSeconds()).getTime(),
                        new GregorianCalendar(c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH),
                        c1.get(Calendar.DAY_OF_MONTH), dispHoraria.getHorafin().getHours(),
                        dispHoraria.getHorafin().getMinutes(),
                        dispHoraria.getHorafin().getSeconds()).getTime()));
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
}//GEN-LAST:event_btnVerDisponibilidadActionPerformed

    private void btnAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarEmpleadoActionPerformed
        if (tblEmpleado.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Empleado!");
            return;
        }

        empleadoSeleccionado = lstEmpleados.get(tblEmpleado.getSelectedRow());

        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();

        ProcesoCalidadNode node = null;

        if (obj instanceof ProcesoCalidadNode) {
            node = (ProcesoCalidadNode) obj;
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
                if (anterior instanceof ProcesoCalidadNode) {
                    ProcesoCalidadNode nodeAnt = (ProcesoCalidadNode) anterior;
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
                    ProcesoCalidadNode nodeSig = (ProcesoCalidadNode) parent.getChildAt(i);
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

            setInicioFinEtapaProduccion(node, fechaInicial);

            boolean superposicion = haySuperposicionAsignacion(node, EMPLEADO);
            if (superposicion) {

                Date fechaDispEmpleado = obtenerFechaDisponibilidadEmpleadoAsignacionActual(empleadoSeleccionado, node);
                node.setInicioEtapa(fechaDispEmpleado);
                Calendar inicioEtapa = new GregorianCalendar();
                inicioEtapa.setTime(fechaDispEmpleado);
                int horas = node.getDetallePiezaCalidadPresupuesto().getDuracionxpieza().getHours();
                int minutos = node.getDetallePiezaCalidadPresupuesto().getDuracionxpieza().getMinutes();
                node.setFinEtapa(Calculos.calcularFechaFin(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, inicioEtapa, horas, minutos).getTime());
            }


            if (mapAsignacionActualEmpleados.containsKey(empleadoSeleccionado.getIdempleado())) {
                mapAsignacionActualEmpleados.get(empleadoSeleccionado.getIdempleado()).add(node);
            } else {
                List<ProcesoCalidadNode> lst = new ArrayList<ProcesoCalidadNode>();
                lst.add(node);
                mapAsignacionActualEmpleados.put(empleadoSeleccionado.getIdempleado(), lst);
            }

            eliminarNodeDeOtrosEmpleados(mapAsignacionActualEmpleados, empleadoSeleccionado, node);

        }
        setVisiblePanel(pnlTreeTable.getName());
}//GEN-LAST:event_btnAsignarEmpleadoActionPerformed

    private void eliminarNodeDeOtrosEmpleados(Map<Long, List<ProcesoCalidadNode>> mapAsignacionActualEmpleados, Empleado empleadoSeleccionado, ProcesoCalidadNode node) {
        Set<Long> keys = mapAsignacionActualEmpleados.keySet();
        List<ProcesoCalidadNode> lstNode = null;
        for (Long key : keys) {
            if (key != empleadoSeleccionado.getIdempleado()) {
                lstNode = mapAsignacionActualEmpleados.get(key);
                if (lstNode.contains(node)) {
                    lstNode.remove(node);
                }
            }
        }
    }

    private Date obtenerFechaDisponibilidadEmpleadoAsignacionActual(Empleado empleado, ProcesoCalidadNode node) {
        Date resultado = null;

        int horas = node.getDetallePiezaCalidadPresupuesto().getDuracionxpieza().getHours();
        int minutos = node.getDetallePiezaCalidadPresupuesto().getDuracionxpieza().getMinutes();

        List<ProcesoCalidadNode> lstProcesoNode = mapAsignacionActualEmpleados.get(empleado.getIdempleado());

        List<Disponibilidadhoraria> lstDisp = crearListDisponibilidadHoraria(lstProcesoNode);

        resultado = RegistrarPlanificacionProduccion.obtenerFechaDisponibilidadEmpleado(lstDisp, horas, minutos, Fecha.fechaActualDate());

        return resultado;
    }

    private List<Disponibilidadhoraria> crearListDisponibilidadHoraria(List<ProcesoCalidadNode> lstNode) {

        List<Disponibilidadhoraria> lstDisp = new ArrayList<Disponibilidadhoraria>();

        Disponibilidadhoraria disponibilidadhoraria = null;

        for (ProcesoCalidadNode node : lstNode) {

            disponibilidadhoraria = new Disponibilidadhoraria();

            disponibilidadhoraria.setFecha(node.getInicioEtapa());
            disponibilidadhoraria.setHorainicio(node.getInicioEtapa());

            Date horaFinDisponibilidad = null;
            Date fechaInicio = node.getInicioEtapa();

            int difDias = Fecha.diferenciaEnDias(node.getInicioEtapa(), node.getFinEtapa());

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
                disponibilidadhoraria.setHorafin(node.getFinEtapa());
                lstDisp.add(disponibilidadhoraria);
            }
        }

        return (lstDisp.isEmpty() ? null : lstDisp);

    }

    private void setInicioFinEtapaProduccion(ProcesoCalidadNode node, Date fechaInicial) {
        Empleado empleado = node.getEmpleado();
        Maquina maquina = node.getMaquina();

        int horas = node.getDetallePiezaCalidadPresupuesto().getDuracionxpieza().getHours();
        int minutos = node.getDetallePiezaCalidadPresupuesto().getDuracionxpieza().getMinutes();

        List<Disponibilidadhoraria> lstDisponibilidad = JpaUtil.getDisponibilidadEmpleado(empleado);

        Date inicioEtapaEmpleado = RegistrarPlanificacionProduccion.obtenerFechaDisponibilidadEmpleado(lstDisponibilidad, horas, minutos, fechaInicial);

        node.setInicioEtapa(inicioEtapaEmpleado);
        Calendar inicioEtapa = new GregorianCalendar();
        inicioEtapa.setTime(inicioEtapaEmpleado);
        node.setFinEtapa(Calculos.calcularFechaFin(Jornada.HORA_INICIO_JORNADA, Jornada.HORA_FIN_JORNADA, inicioEtapa, horas, minutos).getTime());

    }

    private void btnAsignarMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarMaquinaActionPerformed
        maquinaSeleccionada = (metalsoft.datos.jpa.entity.Maquina) lstMaquinas.get(tblMaquinas.getSelectedRow());
        //obtengo el nodo seleccionado
        TreePath tp = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getSelectedRow());
        Object obj = tp.getLastPathComponent();
        //el nodo seleccionado tiene que ser uno de etapaproduccion
        if (obj instanceof ProcesoCalidadNode) {
            ProcesoCalidadNode node = (ProcesoCalidadNode) obj;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarPlanificacionCalidad().setVisible(true);
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

    class PedidoNoPlanificadoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nro",
            "Pedido Cotiz Cliente",
            "Cliente",
            "Prioridad",
            "Fecha de Entrega",
            "Presupuesto"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewPedidoConPlanificacionProduccion view = filasPedidosConPlanificacionProduccion.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, view.getNropedido());
                case 1:
                    return view.getNropedidocotizacioncliente();
                case 2:
                    return view.getRazonsocial();
                case 3:
                    return view.getPrioridad();
                case 4:
                    if (view.getFechaentregaestipulada() == null) {
                        return "";
                    } else {
                        return Fecha.parseToString(view.getFechaentregaestipulada().getTime());
                    }
                case 5:
                    return view.getIdpresupuesto();

                default:
                    return null;
            }

        }

        public int getRowCount() {
            if (filasPedidosConPlanificacionProduccion != null) {
                return filasPedidosConPlanificacionProduccion.size();
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
    }

    class EmpleadoTableModel extends AbstractTableModel {

        private String[] columnNames = {
            "Legajo",
            "Nombre",
            "Apellido",
            "Estado"
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
                case 3:
                    return disponibilidadEmpleado(empleado);
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

    class ProcesoCalidadNode extends AbstractMutableTreeTableNode {

        private metalsoft.datos.jpa.entity.Procesocalidad procesoCalidad;
        private metalsoft.datos.jpa.entity.Maquina maquina;
        private metalsoft.datos.jpa.entity.Empleado empleado;
        private metalsoft.datos.jpa.entity.Detallepiezacalidadpresupuesto detallePiezaCalidadPresupuesto;
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

        public ProcesoCalidadNode(metalsoft.datos.jpa.entity.Procesocalidad procesocalidad) {
            this.procesoCalidad = procesocalidad;
        }

        public Detallepiezacalidadpresupuesto getDetallePiezaCalidadPresupuesto() {
            return detallePiezaCalidadPresupuesto;
        }

        public void setDetallePiezaCalidadPresupuesto(Detallepiezacalidadpresupuesto detallePiezaCalidadPresupuesto) {
            this.detallePiezaCalidadPresupuesto = detallePiezaCalidadPresupuesto;
        }

        public Procesocalidad getProcesoCalidad() {
            return procesoCalidad;
        }

        public void setProcesoCalidad(Procesocalidad procesoCalidad) {
            this.procesoCalidad = procesoCalidad;
        }

        public metalsoft.datos.jpa.entity.Empleado getEmpleado() {
            return empleado;
        }

        public void setEmpleado(metalsoft.datos.jpa.entity.Empleado empleado) {
            this.empleado = empleado;
        }

        public metalsoft.datos.jpa.entity.Maquina getMaquina() {
            return maquina;
        }

        public void setMaquina(metalsoft.datos.jpa.entity.Maquina maquina) {
            this.maquina = maquina;
        }

        public Object getValueAt(int i) {
            try {
                switch (i) {
                    case 0:
                        return procesoCalidad.getNombre();
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

        @Override
        public void setValueAt(Object aValue, int column) {

            String fecha = null;
            Date fechaDate = null;
            GregorianCalendar inicio = null;
            TreePath treePath = trtDetalleProcProd.getPathForRow(trtDetalleProcProd.getEditingRow());
            Object obj = treePath.getLastPathComponent();
            if (obj instanceof ProcesoCalidadNode) {
                ProcesoCalidadNode node = (ProcesoCalidadNode) obj;

                switch (column) {
                    case 1:
                        Date fechaInicialAnterior = node.getInicioEtapa();
                        Date fechaFinalAnterior = node.getFinEtapa();

                        fecha = (String) aValue;
                        fechaDate = Fecha.parseToDateConHoraMinuto(fecha);

                        if (fechaDate == null) {
                            JOptionPane.showMessageDialog(null, "La fecha está mal formada. Por favor ingrese una fecha válida \nEjemplo: 19/02/2011 08:00");
                            return;
                        }
                        node.setInicioEtapa(fechaDate);
                        inicio = new GregorianCalendar();
                        inicio.setTime(node.getInicioEtapa());
                        node.setFinEtapa(Calculos.calcularFechaFin(Jornada.HORA_INICIO_JORNADA,
                                Jornada.HORA_FIN_JORNADA, inicio,
                                node.getDetallePiezaCalidadPresupuesto().getDuracionxpieza().getHours(),
                                node.getDetallePiezaCalidadPresupuesto().getDuracionxpieza().getMinutes()).getTime());
                        /*
                         * validar que la nueva fecha y hora no se superponga con alguna de las otras etapas que tenga
                         * la misma maquina y empleado
                         */

                        if (haySuperposicionAsignacion(node, EMPLEADO_MAQUINA)) {

                            node.setInicioEtapa(fechaInicialAnterior);
                            node.setFinEtapa(fechaFinalAnterior);
                            JOptionPane.showMessageDialog(null, "No se puede modificar la fecha porque se producen superposiciones de Empleados o Máquinas");

                        }

                        break;
                }
            }

        }

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
                case 1:
                    return true;
                default:
                    return super.isCellEditable(node, column);
            }
        }
    }
}
