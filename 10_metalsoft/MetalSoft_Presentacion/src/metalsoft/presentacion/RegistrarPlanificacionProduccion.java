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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import metalsoft.negocio.gestores.GestorRegistrarPlanificacionProduccion;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewPedidoNoPlanificado;
import metalsoft.util.Decimales;
import metalsoft.util.Fecha;
import org.apache.xml.utils.Hashtree2Node;
import org.hibernate.engine.jdbc.ColumnNameCache;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;
import org.jdesktop.swingx.decorator.BorderHighlighter;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlightPredicate.ColumnHighlightPredicate;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.FileSystemModel;
import org.jdesktop.swingx.treetable.SimpleFileSystemModel;
import org.jdesktop.swingx.treetable.TreeTableModel;
import pojos.Detallepiezapresupuesto;
import pojos.Detallepresupuesto;
import pojos.Detalleproductopresupuesto;
import pojos.Empleado;
import pojos.Etapadeproduccion;
import pojos.Maquina;
import pojos.Pieza;
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
    private HashMap<String,JPanel> hashPanels;

    public RegistrarPlanificacionProduccion() {
        initComponents();
        iniciarPaneles();
        setEnableHyperLink(false);
        listColumnNamesTreeTable=new ArrayList<String>();
        listColumnNamesTreeTable.add("Detalle");
        listColumnNamesTreeTable.add("Empleado");
        listColumnNamesTreeTable.add("Máquinas");
        setearTablaPedidos();
        gestor = new GestorRegistrarPlanificacionProduccion();
        filasPedidosNoPlanificados = new LinkedList<ViewPedidoNoPlanificado>();
        buscarPedidosNoPlanificados();
        setearTreeTable();
        tblPedidos.updateUI();
    }

    private void setEnableHyperLink(boolean flag){
        tskPanel.setEnabled(flag);
        hplAsignarEmpleado.setEnabled(flag);
        hplAsignarMaquinas.setEnabled(flag);
    }
    private void iniciarPaneles(){
        hashPanels=new HashMap<String, JPanel>();
        hashPanels.put(pnlTreeTable.getName(), pnlTreeTable);
        hashPanels.put(pnlEmpleado.getName(), pnlEmpleado);
        hashPanels.put(pnlMaquinas.getName(), pnlMaquinas);
        setVisiblePanel(pnlTreeTable.getName());
    }

    private void setVisiblePanel(String namePanel){
        Collection<JPanel> collection=hashPanels.values();
        for (JPanel jPanel : collection) {
            jPanel.setVisible(false);
        }
        hashPanels.get(namePanel).setVisible(true);
    }
    private void setearTreeTable() {
        DefaultMutableTreeTableNode raiz = new DefaultMutableTreeTableNode("Pedido");
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
        pnl = new javax.swing.JPanel();
        pnlTreeTable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        trtDetalleProcProd = new org.jdesktop.swingx.JXTreeTable();
        pnlEmpleado = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEmpleados = new org.jdesktop.swingx.JXTable();
        pnlMaquinas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        tskPanel.getContentPane().add(hplAsignarMaquinas);

        jXTaskPaneContainer1.add(tskPanel);

        pnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl.add(pnlTreeTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane3.setViewportView(tblEmpleados);

        javax.swing.GroupLayout pnlEmpleadoLayout = new javax.swing.GroupLayout(pnlEmpleado);
        pnlEmpleado.setLayout(pnlEmpleadoLayout);
        pnlEmpleadoLayout.setHorizontalGroup(
            pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );
        pnlEmpleadoLayout.setVerticalGroup(
            pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpleadoLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pnl.add(pnlEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 330));

        javax.swing.GroupLayout pnlMaquinasLayout = new javax.swing.GroupLayout(pnlMaquinas);
        pnlMaquinas.setLayout(pnlMaquinasLayout);
        pnlMaquinasLayout.setHorizontalGroup(
            pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        pnlMaquinasLayout.setVerticalGroup(
            pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        pnl.add(pnlMaquinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 330));

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        viewPedidoSeleccionado=filasPedidosNoPlanificados.get(tblPedidos.getSelectedRow());
        presupuesto = gestor.buscarPresupuesto(viewPedidoSeleccionado.getIdpresupuesto());
        setVisiblePanel(pnlTreeTable.getName());
        setEnableHyperLink(true);
        cargarDatosTreeTable(presupuesto.getDetallepresupuestos());
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void hplAsignarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hplAsignarEmpleadoActionPerformed
        pnlTreeTable.setVisible(false);
        pnlMaquinas.setVisible(false);
        pnlEmpleado.setVisible(true);
    }//GEN-LAST:event_hplAsignarEmpleadoActionPerformed

    private void cargarDatosTreeTable(Set<Detallepresupuesto> detallepresupuestos) {
        DefaultMutableTreeTableNode raiz=new DefaultMutableTreeTableNode(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido()));
        trtDetalleProcProd.removeAll();
        trtDetalleProcProd.setTreeTableModel(new DefaultTreeTableModel(raiz,listColumnNamesTreeTable));
        Iterator<Detallepresupuesto> it=detallepresupuestos.iterator();
        Detallepresupuesto dp=null;
        ProductoNode prod=null;
        while(it.hasNext()){
            dp=it.next();
            prod=new ProductoNode(dp.getProducto());
            raiz.add(prod);
            Set<Detalleproductopresupuesto> setDetProPre=dp.getDetalleproductopresupuestos();
            Iterator<Detalleproductopresupuesto> itDetProPre=setDetProPre.iterator();
            PiezaNode pieza=null;
            Detalleproductopresupuesto detProPre=null;
            while(itDetProPre.hasNext()){
                detProPre=itDetProPre.next();
                pieza=new PiezaNode(detProPre.getPieza());
                prod.add(pieza);
                Set<Detallepiezapresupuesto> setDetPiPre=detProPre.getDetallepiezapresupuestos();
                Detallepiezapresupuesto detPiPre=null;
                Iterator<Detallepiezapresupuesto> itDetPiPre=setDetPiPre.iterator();
                EtapaProduccionNode etapaProd=null;
                while(itDetPiPre.hasNext()){
                    detPiPre=itDetPiPre.next();
                    etapaProd=new EtapaProduccionNode(detPiPre.getEtapadeproduccion());
                    pieza.add(etapaProd);
                }
            }
        }
        trtDetalleProcProd.updateUI();
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
    private javax.swing.JButton btnSeleccionar;
    private org.jdesktop.swingx.JXHyperlink hplAsignarEmpleado;
    private org.jdesktop.swingx.JXHyperlink hplAsignarMaquinas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnlEmpleado;
    private javax.swing.JPanel pnlMaquinas;
    private javax.swing.JPanel pnlTreeTable;
    private org.jdesktop.swingx.JXTable tblEmpleados;
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
                return "null";
            }
            return "";
        }

        public int getColumnCount() {
            return columnCountTreeTable;
        }
    }
}
