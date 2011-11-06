/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProduccionDiaria.java
 *
 * Created on 06/11/2011, 02:51:15
 */
package metalsoft.presentacion;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metalsoft.datos.jpa.entity.Detallepiezapresupuesto;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.negocio.gestores.GestorProduccionDiaria;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 *
 * @author Nino
 */
public class ProduccionDiaria extends javax.swing.JDialog {

    private static Window owner;
    private ArrayList<String> listColumnNamesTreeTable;
    private GestorProduccionDiaria gestor;
    private List<Detalleplanificacionproduccion> lstDetallePlanificacion;

    /** Creates new form ProduccionDiaria */
    public ProduccionDiaria() {
        super(owner);
        gestor = new GestorProduccionDiaria();
        initComponents();
        iniciarTreeTable();
        buscarPedidosEnEjecucionOAEjecutar();
        cargarTreeTable();
    }

    public static void setOwner(Window owner) {
        ProduccionDiaria.owner = owner;
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
        trtDetalleDiario.setSelectionMode(SelectionMode.SINGLE_SELECTION.ordinal());
        trtDetalleDiario.setTreeTableModel(treeTableModel);
        trtDetalleDiario.setRootVisible(true);
        trtDetalleDiario.setHorizontalScrollEnabled(true);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        jScrollPane1.setViewportView(trtDetalleDiario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTreeTable trtDetalleDiario;
    // End of variables declaration//GEN-END:variables

    private void buscarPedidosEnEjecucionOAEjecutar() {
        lstDetallePlanificacion = gestor.buscarPlanificacionDiaria();
    }

    private void cargarTreeTable() {
        DefaultMutableTreeTableNode raiz = new DefaultMutableTreeTableNode("---");
        trtDetalleDiario.removeAll();
        trtDetalleDiario.setTreeTableModel(new TablaProduccionDiariaModel(raiz, listColumnNamesTreeTable));

        Map<Long, PedidoNode> mapPedido = new HashMap<Long, PedidoNode>();
        Map<String, ProductoNode> mapProducto = new HashMap<String, ProductoNode>();
        Map<String, PiezaNode> mapPieza = new HashMap<String, PiezaNode>();
        boolean nuevoPedido = false;
        for (Detalleplanificacionproduccion detalleplanificacion : lstDetallePlanificacion) {
            Pedido pedido = detalleplanificacion.getIdplanificacionproduccion().getPedido();
            Producto producto = detalleplanificacion.getIdproducto();
            Pieza pieza = detalleplanificacion.getIdpieza();
            Etapadeproduccion etapaproduccion = detalleplanificacion.getIdetapaproduccion();

            PedidoNode pedidoNode = null;
            if (mapPedido.containsKey(pedido.getIdpedido())) {
                pedidoNode = mapPedido.get(pedido.getIdpedido());
            } else {
                nuevoPedido = true;
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
                pedidoNode.add(productoNode);
                mapProducto.put(keyProducto, productoNode);
            }

            PiezaNode piezaNode = new PiezaNode(pieza);
            String keyPieza = String.valueOf(pedidoNode.getPedido().getIdpedido()) + String.valueOf(producto.getIdproducto()) + String.valueOf(detalleplanificacion.getIndexproducto()) + String.valueOf(pieza.getIdpieza());
            if (mapPieza.containsKey(keyPieza)) {
                piezaNode = mapPieza.get(keyPieza);
            } else {
                piezaNode = new PiezaNode(pieza);
                productoNode.add(piezaNode);
                mapPieza.put(keyPieza, piezaNode);
            }


            EtapaProduccionNode etapaProduccionNode = new EtapaProduccionNode(etapaproduccion);

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
            this.indexProducto = indexProducto;
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
            this.indexPieza = indexPieza;
        }

        public int getColumnCount() {
            return listColumnNamesTreeTable.size();
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
            switch (column) {
                default:
                    return super.isCellEditable(node, column);
            }
        }
    }
}
