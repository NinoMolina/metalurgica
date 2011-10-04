/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarArmadoPedido.java
 *
 * Created on 12/09/2011, 18:37:19
 */
package metalsoft.presentacion;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.negocio.gestores.GestorArmadoPedido;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewDetallePedidoReal;
import metalsoft.negocio.gestores.ViewPedidoConCalidad;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Mariana
 */
public class RegistrarArmadoPedido extends javax.swing.JFrame implements IBuscador {

    private List<Pedido> filasPedidos;
    private GestorArmadoPedido gestor;
    private TableCellRender tcrTblDetallePedido;
    private LinkedList<ViewDetallePedidoReal> filasDetallePedido;


    /** Creates new form RegistrarArmadoPedido */
    public RegistrarArmadoPedido() {
        initComponents();
        gestor = new GestorArmadoPedido();
        tcrTblDetallePedido = new TableCellRender();
        cargarPedidosNoArmados();
        addListeners();
        setearTablas();

    }

    private void addListeners() {
        addListenerBtnSeleccionarPedido();
        addListenerBtnSalir();
        addListenerBtnGuardar();
    }

    public void cargarPedidosNoArmados() {
        if (txtPedido.getText().compareTo("") != 0) {
            filasPedidos = gestor.buscarPedidosNoArmadosLIKE(txtPedido.getText());

        } else {
            filasPedidos = gestor.buscarPedidosNoArmados();
        }
        LinkedList<ViewPedidoConCalidad> lstView = new LinkedList<ViewPedidoConCalidad>();
        ViewPedidoConCalidad view = null;
        for (Pedido pedido : filasPedidos) {

            view = new ViewPedidoConCalidad();
            view.setEspedidoweb(pedido.getEspedidoweb());
            view.setFechaentregaestipulada(pedido.getFechaentregaestipulada());
            view.setFechapedidocotizacion(pedido.getFechapedidocotizacion());
            view.setFecharequeridacotizacion(pedido.getFecharequeridacotizacion());
            view.setIdestado(pedido.getEstado().getIdestado());
            view.setIdpedido(pedido.getIdpedido());
            view.setNropedido((int) pedido.getNropedido());
            view.setNropedidocotizacioncliente(pedido.getNropedidocotizacioncliente());

            lstView.addLast(view);
        }
        pedidosSinArmar1.setFilasPedidos(lstView);
        pedidosSinArmar1.updateTblPedidos();
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);

    }

    private void addListenerBtnSalir() {
        btnSalirr1.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

        private void addListenerBtnGuardar() {
        btnGuardar1.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

   private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
            if (pedidosSinArmar1.getTblPedidos().getSelectedRow() > -1) {
                long result = 0;
                Pedido ped = filasPedidos.get(pedidosSinArmar1.getTblPedidos().getSelectedRow());                
                result = gestor.modificarPedido(ped, filasDetallePedido);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "El armado del Pedido se ha finalizado correctamente");
                    filasDetallePedido.clear();
                    this.tblDetallePedido.updateUI();
//                    filasPedidos.remove(pedidosSinArmar1.getTblPedidos().getSelectedRow());
                    pedidosSinArmar1.getFilasPedidos().remove(pedidosSinArmar1.getTblPedidos().getSelectedRow());
                    pedidosSinArmar1.updateTblPedidos();
                    this.tblDetallePedido.setEnabled(false);

                } else {
                    JOptionPane.showMessageDialog(this, "El armado del Pedido NO se ha finalizado correctamente");
                }
            }
    }

    private void addListenerBtnSeleccionarPedido() {
        pedidosSinArmar1.getBtnSeleccionarPedido().addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPedidoBeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarPedidoBeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (pedidosSinArmar1.getTblPedidos().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido!");
            return;
        }
        limpiarCampos();
        Pedido v = filasPedidos.get(pedidosSinArmar1.getTblPedidos().getSelectedRow());
        long idPed = v.getIdpedido();
        filasDetallePedido = gestor.buscarDetallePedido(idPed);
        tblDetallePedido.updateUI();        
    }

    private void setearTablas() {
        //DETALLE PEDIDO
        tblDetallePedido.setModel(new DetallePedidoConCalidadTableModel());
        tblDetallePedido.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetallePedido.setShowHorizontalLines(false);
        tblDetallePedido.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetallePedido.setHighlighters(
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

        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        bsyBuscar1 = new org.jdesktop.swingx.JXBusyLabel();
        pedidosSinArmar1 = new metalsoft.beans.PedidosSinArmar();
        txtPedido = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDetallePedido = new org.jdesktop.swingx.JXTable();
        btnGuardar1 = new metalsoft.beans.BtnGuardar();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Armado Pedido");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Actuales"));

        jLabel13.setText("Nro. de Pedido de Cotización:");

        txtPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPedidoActionPerformed(evt);
            }
        });
        txtPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPedidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPedidoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bsyBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pedidosSinArmar1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bsyBuscar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pedidosSinArmar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pedido"));

        jScrollPane5.setViewportView(tblDetallePedido);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 625, Short.MAX_VALUE)
                        .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPedidoActionPerformed

    private void txtPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedidoKeyTyped
    }//GEN-LAST:event_txtPedidoKeyTyped

    private void txtPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedidoKeyReleased
        // TODO add your handling code here:
        bsyBuscar1.setVisible(true);
        bsyBuscar1.setBusy(true);
        txtPedido.isCursorSet();
        if (txtPedido.getText().compareTo("") != 0) {
            filasPedidos = gestor.buscarPedidosNoArmadosLIKE(txtPedido.getText());

        } else {
            filasPedidos = gestor.buscarPedidosNoArmados();
        }
        LinkedList<ViewPedidoConCalidad> lstView = new LinkedList<ViewPedidoConCalidad>();
        ViewPedidoConCalidad view = null;
        for (Pedido pedido : filasPedidos) {
            view = new ViewPedidoConCalidad();

            view.setEspedidoweb(pedido.getEspedidoweb());
            view.setFechaentregaestipulada(pedido.getFechaentregaestipulada());
            view.setFechapedidocotizacion(pedido.getFechapedidocotizacion());
            view.setFecharequeridacotizacion(pedido.getFecharequeridacotizacion());
            view.setIdestado(pedido.getEstado().getIdestado());
            view.setIdpedido(pedido.getIdpedido());
            view.setNropedido((int) pedido.getNropedido());
            view.setNropedidocotizacioncliente(pedido.getNropedidocotizacioncliente());

            lstView.addLast(view);
        }

        pedidosSinArmar1.setFilasPedidos(lstView);
        pedidosSinArmar1.updateTblPedidos();
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);
    }//GEN-LAST:event_txtPedidoKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarArmadoPedido().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXBusyLabel bsyBuscar1;
    private metalsoft.beans.BtnGuardar btnGuardar1;
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane5;
    private metalsoft.beans.PedidosSinArmar pedidosSinArmar1;
    private org.jdesktop.swingx.JXTable tblDetallePedido;
    private javax.swing.JTextField txtPedido;
    // End of variables declaration//GEN-END:variables

    @Override
    public JList getList(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JComboBox getCombo(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBusqueda(Object[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void limpiarCampos() {
        this.tblDetallePedido.setEnabled(true);
        this.tblDetallePedido.updateUI();
    }

    class DetallePedidoConCalidadTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Producto",
            "Descripción",
            "Cantidad",
            "Codigo Barra"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetallePedidoReal view = filasDetallePedido.get(rowIndex);

            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRODUCTO, view.getNumeroProducto());
                case 1:
                    return view.getNumeroProducto();
                case 2:
                    return view.getNombreProducto();
                case 3:
                    return view.getCantidad();
                case 4:
                    return view.getCodigoBarra();
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
            if (filasDetallePedido != null) {
                return filasDetallePedido.size();
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
