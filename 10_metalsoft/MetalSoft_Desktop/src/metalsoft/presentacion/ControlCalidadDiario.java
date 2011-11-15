/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlCalidadDiario.java
 *
 * Created on 06/11/2011, 22:18:52
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Procesocalidad;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.negocio.gestores.GestorControlCalidadDiario;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 *
 * @author Nino
 */
public class ControlCalidadDiario extends javax.swing.JDialog {

    private static Window owner;
    private ArrayList<String> listColumnNamesTreeTable;
    private GestorControlCalidadDiario gestor;
    private List<Detalleplanificacioncalidad> lstDetallePlanificacion;

    /** Creates new form ControlCalidadDiario */
    public ControlCalidadDiario() {
        super(owner);
        gestor = new GestorControlCalidadDiario();
        initComponents();
        addListeners();
        jdcFehaFiltro.setFormats(Fecha.DD_MM_YYYY);
        jdcFehaFiltro.setDate(Fecha.fechaActualDate());
        iniciarTreeTable();
        buscarPedidosEnEjecucionOAEjecutar();
        cargarTreeTable();
    }

    private void addListeners() {
        addListenerBtnSalir();
        addListenerJdcFehaFiltro();
    }

    private void addListenerJdcFehaFiltro() {
        jdcFehaFiltro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jdcFechaFiltroActionPerformed(e);
            }
        });

    }

    private void jdcFechaFiltroActionPerformed(ActionEvent e) {
        Date fechaSeleccionada = jdcFehaFiltro.getDate();
        if (fechaSeleccionada.compareTo(Fecha.fechaActualDate()) > 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha menor o igual a la fecha actual (" + Fecha.fechaActual() + ")", "Información", JOptionPane.WARNING_MESSAGE);
            jdcFehaFiltro.setDate(Fecha.fechaActualDate());
            return;
        }
        jdcFehaFiltro.setDate(fechaSeleccionada);
        buscarPedidosEnEjecucionOAEjecutar();
    }

    private void addListenerBtnSalir() {
        btnSalirr1.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public static void setOwner(Window owner) {
        ControlCalidadDiario.owner = owner;
    }

    private void iniciarTreeTable() {
        listColumnNamesTreeTable = new ArrayList<String>();
        listColumnNamesTreeTable.add("Detalle");
        listColumnNamesTreeTable.add("Inicio Previsto");
        listColumnNamesTreeTable.add("Fin Previsto");
        listColumnNamesTreeTable.add("Inicio Real");
        listColumnNamesTreeTable.add("Fin Real");
        listColumnNamesTreeTable.add("Estado");
        listColumnNamesTreeTable.add("Empleado");
        listColumnNamesTreeTable.add("Máquina");

        DefaultMutableTreeTableNode raiz = new DefaultMutableTreeTableNode("");

        TreeTableModel treeTableModel = new TablaProduccionDiariaModel(raiz, listColumnNamesTreeTable);
        trtDetalleDiario.setColumnControlVisible(true);
        trtDetalleDiario.setAutoCreateRowSorter(true);
        trtDetalleDiario.setSelectionMode(SelectionMode.SINGLE_SELECTION.ordinal());
        trtDetalleDiario.setTreeTableModel(treeTableModel);
        trtDetalleDiario.setRootVisible(true);
        trtDetalleDiario.setHorizontalScrollEnabled(true);
        trtDetalleDiario.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        trtDetalleDiario = new org.jdesktop.swingx.JXTreeTable();
        jdcFehaFiltro = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fondopantallas2.png")), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;
        btnSalirr1 = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control de Calidad Diario");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        jScrollPane1.setViewportView(trtDetalleDiario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setText("Fecha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jdcFehaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(308, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(686, Short.MAX_VALUE)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jdcFehaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jdcFehaFiltro;
    private org.jdesktop.swingx.JXTreeTable trtDetalleDiario;
    // End of variables declaration//GEN-END:variables

    private void buscarPedidosEnEjecucionOAEjecutar() {
        lstDetallePlanificacion = gestor.buscarPlanificacionDiaria(jdcFehaFiltro.getDate());
        cargarTreeTable();
    }

    private void cargarTreeTable() {
        DefaultMutableTreeTableNode raiz = new DefaultMutableTreeTableNode(Fecha.parseToString(jdcFehaFiltro.getDate()));
        trtDetalleDiario.removeAll();
        trtDetalleDiario.setTreeTableModel(new TablaProduccionDiariaModel(raiz, listColumnNamesTreeTable));

        Map<Long, PedidoNode> mapPedido = new HashMap<Long, PedidoNode>();
        Map<String, ProductoNode> mapProducto = new HashMap<String, ProductoNode>();
        Map<String, PiezaNode> mapPieza = new HashMap<String, PiezaNode>();
        for (Detalleplanificacioncalidad detalleplanificacion : lstDetallePlanificacion) {

            if (detalleplanificacion.getIddetalleejecucionplanificacioncalidad() != null) {
                long idestado = detalleplanificacion.getIddetalleejecucionplanificacioncalidad().getEjecucionprocesocalidad().getEstado().getIdestado();
                if (idestado == 4 || idestado == 6) {
                    continue;
                }
            }

            Pedido pedido = detalleplanificacion.getIdplanificacioncalidad().getPedido();
            Producto producto = detalleplanificacion.getProducto();
            Pieza pieza = detalleplanificacion.getPieza();
            Procesocalidad procesocalidad = detalleplanificacion.getProcesocalidad();

            PedidoNode pedidoNode = null;
            if (mapPedido.containsKey(pedido.getIdpedido())) {
                pedidoNode = mapPedido.get(pedido.getIdpedido());
            } else {
                pedidoNode = new PedidoNode(pedido);
                raiz.add(pedidoNode);
                mapPedido.put(pedido.getIdpedido(), pedidoNode);
            }

            ProductoNode productoNode = null;
            String keyProducto = String.valueOf(pedidoNode.getPedido().getIdpedido()) + String.valueOf(producto.getIdproducto()) + String.valueOf(detalleplanificacion.getIndexproducto());
            if (mapProducto.containsKey(keyProducto)) {
                productoNode = mapProducto.get(keyProducto);
            } else {
                productoNode = new ProductoNode(producto);
                productoNode.setIndexProducto(detalleplanificacion.getIndexproducto());
                pedidoNode.add(productoNode);
                mapProducto.put(keyProducto, productoNode);
            }

            PiezaNode piezaNode = new PiezaNode(pieza);
            String keyPieza = String.valueOf(pedidoNode.getPedido().getIdpedido()) + String.valueOf(producto.getIdproducto()) + String.valueOf(detalleplanificacion.getIndexproducto()) + String.valueOf(pieza.getIdpieza() + String.valueOf(detalleplanificacion.getIndexpieza()));
            if (mapPieza.containsKey(keyPieza)) {
                piezaNode = mapPieza.get(keyPieza);
            } else {
                piezaNode = new PiezaNode(pieza);
                piezaNode.setIndexPieza(detalleplanificacion.getIndexpieza());
                productoNode.add(piezaNode);
                mapPieza.put(keyPieza, piezaNode);
            }


            ProcesoCalidadNode etapaProduccionNode = new ProcesoCalidadNode(detalleplanificacion, detalleplanificacion.getIddetalleejecucionplanificacioncalidad());

            piezaNode.add(etapaProduccionNode);

        }

        trtDetalleDiario.updateUI();
        trtDetalleDiario.expandAll();
        trtDetalleDiario.packAll();
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
                    return producto.getNombre() + "-" + indexProducto;
            }
            return "";
        }

        public int getColumnCount() {
            return listColumnNamesTreeTable.size();
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
            this.indexProducto += indexProducto;
        }
    }

    class PedidoNode extends AbstractMutableTreeTableNode {

        private metalsoft.datos.jpa.entity.Pedido pedido;
        private metalsoft.datos.jpa.entity.Detalleproductopresupuesto detalleProductoPresupuesto;
        private Integer indexProducto = 1;

        public PedidoNode(metalsoft.datos.jpa.entity.Pedido pedido) {
            this.pedido = pedido;
        }

        public Object getValueAt(int i) {
            switch (i) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, pedido.getNropedido());
            }
            return "";
        }

        public int getColumnCount() {
            return listColumnNamesTreeTable.size();
        }

        public Detalleproductopresupuesto getDetalleProductoPresupuesto() {
            return detalleProductoPresupuesto;
        }

        public void setDetalleProductoPresupuesto(Detalleproductopresupuesto detalleProductoPresupuesto) {
            this.detalleProductoPresupuesto = detalleProductoPresupuesto;
        }

        public Pedido getPedido() {
            return pedido;
        }

        public void setPedido(Pedido pedido) {
            this.pedido = pedido;
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
        private int indexPieza = 1;

        public PiezaNode(metalsoft.datos.jpa.entity.Pieza pieza) {
            this.pieza = pieza;
        }

        public List<MutableTreeTableNode> getChildren() {
            return children;
        }

        public Object getValueAt(int i) {
            switch (i) {
                case 0:
                    return pieza.getNombre() + "-" + indexPieza;
            }
            return "";
        }

        public int getIndexPieza() {
            return indexPieza;
        }

        public void setIndexPieza(int indexPieza) {
            this.indexPieza += indexPieza;
        }

        public int getColumnCount() {
            return listColumnNamesTreeTable.size();
        }

        public metalsoft.datos.jpa.entity.Pieza getPieza() {
            return pieza;
        }
    }

    class ProcesoCalidadNode extends AbstractMutableTreeTableNode {

        private Detalleplanificacioncalidad dpp;
        private Detalleejecucionplanificacioncalidad dep;

        public ProcesoCalidadNode(Detalleplanificacioncalidad dpp, Detalleejecucionplanificacioncalidad dep) {
            this.dpp = dpp;
            this.dep = dep;
        }

        @Override
        public Object getValueAt(int i) {
            try {
                switch (i) {
                    case 0:
                        return dpp.getProcesocalidad().getNombre();
                    case 1:
                        return Fecha.parseToStringFechaHora(Fecha.dateWithSpecificValues(dpp.getFechainicio(), dpp.getHorainicio().getHours(), dpp.getHorainicio().getMinutes(), dpp.getHorainicio().getSeconds()));
                    case 2:
                        return Fecha.parseToStringFechaHora(Fecha.dateWithSpecificValues(dpp.getFechafin(), dpp.getHorafin().getHours(), dpp.getHorafin().getMinutes(), dpp.getHorafin().getSeconds()));
                    case 3:
                        if (dep != null) {
                            return Fecha.parseToStringFechaHora(Fecha.dateWithSpecificValues(dep.getFechainicio(), dep.getHorainicio().getHours(), dep.getHorainicio().getMinutes(), dep.getHorainicio().getSeconds()));
                        } else {
                            return "";
                        }
                    case 4:
                        if (dep != null) {
                            return Fecha.parseToStringFechaHora(Fecha.dateWithSpecificValues(dep.getFechafin(), dep.getHorafin().getHours(), dep.getHorafin().getMinutes(), dep.getHorafin().getSeconds()));
                        } else {
                            return "";
                        }
                    case 5:
                        if (dep != null) {
                            return dep.getEjecucionprocesocalidad().getEstado().getNombre();
                        } else {
                            return "PLANIFICADO";
                        }
                    case 6:
                        if (dep != null) {
                            return dep.getEjecucionprocesocalidad().getEmpleado().getNombre() + " " + dep.getEjecucionprocesocalidad().getEmpleado().getApellido();
                        } else {
                            return dpp.getEmpleado().getNombre() + " " + dpp.getEmpleado().getApellido();
                        }
                    case 7:
                        if (dep != null && dep.getEjecucionprocesocalidad().getMaquina() != null) {
                            return dep.getEjecucionprocesocalidad().getMaquina().getNombre();
                        } else {
                            if (dpp.getMaquina() != null) {
                                return dpp.getMaquina().getNombre();
                            } else {
                                return "";
                            }
                        }
                }
            } catch (NullPointerException ex) {
                return "----";
            }
            return "";
        }

        public int getColumnCount() {
            return listColumnNamesTreeTable.size();
        }
    }

    class TablaProduccionDiariaModel extends DefaultTreeTableModel {

        private TablaProduccionDiariaModel(DefaultMutableTreeTableNode raiz, ArrayList<String> listColumnNamesTreeTable) {
            super(raiz, listColumnNamesTreeTable);
        }

        @Override
        public boolean isCellEditable(Object node, int column) {
            return false;
        }
    }
}
