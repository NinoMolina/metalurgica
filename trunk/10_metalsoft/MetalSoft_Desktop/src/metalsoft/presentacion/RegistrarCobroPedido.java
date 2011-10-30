/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarCobroPedido.java
 *
 * Created on 31/10/2010, 17:19:03
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.negocio.gestores.GestorPedidoCotizacion;
import metalsoft.negocio.gestores.GestorPresupuesto;
import metalsoft.negocio.gestores.GestorRegistrarEntregaPedido;
import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.negocio.gestores.ViewPedidosClienteSegunEstado;
import metalsoft.negocio.gestores.ViewPresupuestoParaFactura;
import metalsoft.util.Combo;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class RegistrarCobroPedido extends javax.swing.JDialog {

    private LinkedList<ViewPedidoEnListadoProcedimientos> filasPedidos;
    private LinkedList<ViewPresupuestoParaFactura> filasDetalle;
    private long idPedido;
    private GestorRegistrarEntregaPedido gestor;
    private GestorPedidoCotizacion gestorPedido;
    private GestorPresupuesto gp;

    /** Creates new form RegistrarCobroPedido */
    public RegistrarCobroPedido() {
        super(Principal.getVtnPrincipal());
        initComponents();
        setearTablas();
        gestorPedido = new GestorPedidoCotizacion();
        gestor = new GestorRegistrarEntregaPedido();
        filasPedidos = gestor.buscarPedidosEntregados();
        tblPedidos.updateUI();
        btnRegistrarCobro.setEnabled(false);
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
        tblDetallePedidoCotizacion.setModel(new DetallePedidoCotizacionTableModel());
        tblDetallePedidoCotizacion.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetallePedidoCotizacion.setShowHorizontalLines(false);
        tblDetallePedidoCotizacion.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetallePedidoCotizacion.setHighlighters(
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

        jPanel3 = new javax.swing.JPanel();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new org.jdesktop.swingx.JXTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetallePedidoCotizacion = new org.jdesktop.swingx.JXTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        dccConfirmacionPedido = new com.toedter.calendar.JDateChooser();
        dccEntregaEstipulada = new com.toedter.calendar.JDateChooser();
        dccPedidoCotizacion = new com.toedter.calendar.JDateChooser();
        dccEntregaReal = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        dccFechaReqCotizacion = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNroPedidoCliente = new javax.swing.JTextField();
        cmbPrioridad1 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbEstado1 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtNroFactura = new javax.swing.JTextField();
        lblNroPedido = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnRegistrarCobro = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Cobro Pedido");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Entregados No Cobrados"));

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/48-check.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tblPedidos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Pedido"));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        jScrollPane2.setViewportView(tblDetallePedidoCotizacion);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
        );

        jPanel6.setEnabled(false);

        jLabel4.setText("Fecha de Confirmación de Pedido:");

        jLabel5.setText("Fecha de Entrega Estipulada:");

        jLabel14.setText("Fecha Pedido Cotización:");

        jLabel16.setText("Fecha de Entrega Real:");

        dccConfirmacionPedido.setEnabled(false);

        dccEntregaEstipulada.setEnabled(false);

        dccPedidoCotizacion.setEnabled(false);

        dccEntregaReal.setEnabled(false);

        jLabel13.setText("Fecha Requerida de Pedido:");

        dccFechaReqCotizacion.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(dccEntregaEstipulada, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(dccEntregaReal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(dccConfirmacionPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccConfirmacionPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dccEntregaEstipulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(dccEntregaReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setEnabled(false);

        jLabel2.setText("Nro. Pedido Cliente:");

        jLabel11.setText("Nro.Pedido:");

        txtNroPedidoCliente.setEnabled(false);

        cmbPrioridad1.setEnabled(false);

        jLabel9.setText("Prioridad:");

        jLabel10.setText("Estado:");

        cmbEstado1.setEnabled(false);

        jLabel12.setText("Nro. Factura:");

        txtNroFactura.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPrioridad1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbEstado1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNroFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNroPedidoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(lblNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNroPedidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbPrioridad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit1.png"))); // NOI18N
        btnSalir.setToolTipText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnRegistrarCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save1.png"))); // NOI18N
        btnRegistrarCobro.setText("Registrar Cobro");
        btnRegistrarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCobroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegistrarCobro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
                                .addComponent(btnSalir))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir)
                    .addComponent(btnRegistrarCobro))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        ViewPedidoEnListadoProcedimientos viewPedido = filasPedidos.get(tblPedidos.getSelectedRow());
        idPedido = viewPedido.getIdpedido();
        cargarComboEstado1();
        cargarComboPrioridad1();
        PedidoDB ped = gestor.buscarPedidoPorID(idPedido);
        txtNroFactura.setText(String.valueOf(ped.getFactura()));
        txtNroPedidoCliente.setText(String.valueOf(ped.getNropedidocotizacioncliente()));
        if (ped.getEstado() < 1) {
            Combo.setItemComboSeleccionado(cmbEstado1, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbEstado1, ped.getEstado());
        }
        if (ped.getPrioridad() < 1) {
            Combo.setItemComboSeleccionado(cmbPrioridad1, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbPrioridad1, ped.getPrioridad());
        }

//        if (ped.getFechacancelacion() == null) {
//            dccCancelacion.setDate(null);
//        } else {
//            dccCancelacion.setDate(ped.getFechacancelacion());
//        }
        if (ped.getFechaconfirmacionpedido() == null) {
            dccConfirmacionPedido.setDate(null);
        } else {
            dccConfirmacionPedido.setDate(ped.getFechaconfirmacionpedido());
        }
        if (ped.getFechaentregaestipulada() == null) {
            dccEntregaEstipulada.setDate(null);
        } else {
            dccEntregaEstipulada.setDate(ped.getFechaentregaestipulada());
        }
        if (ped.getFechaentregareal() == null) {
            dccEntregaReal.setDate(null);
        } else {
            dccEntregaReal.setDate(ped.getFechaentregareal());
        }
        if (ped.getFecharequeridacotizacion() == null) {
            dccFechaReqCotizacion.setDate(null);
        } else {
            dccFechaReqCotizacion.setDate(ped.getFecharequeridacotizacion());
        }
        if (ped.getFechapedidocotizacion() == null) {
            dccPedidoCotizacion.setDate(null);
        } else {
            dccPedidoCotizacion.setDate(ped.getFechapedidocotizacion());
        }
        lblNroPedido.setText("PED-" + String.valueOf(ped.getNropedido()));

        filasDetalle = gestor.buscarDetallePedidoSeleccionado(idPedido);
        tblDetallePedidoCotizacion.updateUI();
        btnRegistrarCobro.setEnabled(true);
    }//GEN-LAST:event_btnSeleccionarActionPerformed
    private void limpiarCamposPedido() {
        txtNroFactura.setText("");
        txtNroPedidoCliente.setText("");
        if (filasDetalle != null) {
            filasDetalle.clear();
            tblDetallePedidoCotizacion.updateUI();
        }

        cmbEstado1.setSelectedIndex(-1);
        cmbPrioridad1.setSelectedIndex(-1);

        //dccCancelacion.setDate(null);
        dccConfirmacionPedido.setDate(null);
        dccEntregaEstipulada.setDate(null);
        dccEntregaReal.setDate(null);
        dccFechaReqCotizacion.setDate(null);
        dccPedidoCotizacion.setDate(null);
        lblNroPedido.setText("");
    }

    private void pedidoSeleccionado(long idPedido) {
        this.idPedido = idPedido;
    }

    private void cargarComboPrioridad1() {
        cmbPrioridad1.removeAllItems();
        gestorPedido.obtenerPrioridades(cmbPrioridad1);
    }

    private void cargarComboEstado1() {
        cmbEstado1.removeAllItems();
        gestorPedido.obtenerEstados(cmbEstado1);
    }
    private void btnRegistrarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCobroActionPerformed

        PedidoDB db = gestor.buscarPedidoPorID(idPedido);
        int ok = -1;

        boolean flag = false;
        do {
            Double montoFactura = gestor.montoFactura(idPedido);
            Double diferenciaMontos = montoFactura - gestor.montoPagadoPorFactura(idPedido);
            JComboBox combo = new JComboBox();
            JTextField monto = new JTextField();
            JLabel saldo = new JLabel("$ "+String.valueOf(diferenciaMontos));
            gestor.obtenerFormasDePago(combo);
            Object[] obj = {"Forma de Pago:", combo, "Saldo:", saldo, "Monto a Cobrar:", monto};

            int res = JOptionPane.showConfirmDialog(null, obj, "Ingresar Forma de Pago y Monto a Cobrar", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION) {
                long formaPago = Long.parseLong(((ItemCombo) combo.getSelectedItem()).getId());
                if (monto.getText().compareTo("") != 0) {
                    Double montoPago = Double.parseDouble(monto.getText());


                    if (diferenciaMontos >= montoPago) {

                        ok = JOptionPane.showConfirmDialog(this, "Esta seguro que desea imprimir el comprobante de pago por $" + monto.getText() + "?");
                        if (ok == JOptionPane.OK_OPTION) {
                            gestor.imprimirComprobantePago(idPedido, montoPago, formaPago);
                            flag = false;
                            if (gestor.estaTodaPagada(idPedido)) {
                                gestor.registrarCobroTotal(idPedido);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "El monto de la ingresado supera lo requerido por pagar. Lo que resta por pagar son $" + diferenciaMontos);
                    }
                }
            } else {
                flag = true;
            }

        } while (flag);

        pedidoSeleccionado(idPedido);
        filasPedidos = gestor.buscarPedidosEntregados();
        tblPedidos.updateUI();
        btnRegistrarCobro.setEnabled(false);
}//GEN-LAST:event_btnRegistrarCobroActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
}//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarCobroPedido().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarCobro;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox cmbEstado1;
    private javax.swing.JComboBox cmbPrioridad1;
    private com.toedter.calendar.JDateChooser dccConfirmacionPedido;
    private com.toedter.calendar.JDateChooser dccEntregaEstipulada;
    private com.toedter.calendar.JDateChooser dccEntregaReal;
    private com.toedter.calendar.JDateChooser dccFechaReqCotizacion;
    private com.toedter.calendar.JDateChooser dccPedidoCotizacion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNroPedido;
    private org.jdesktop.swingx.JXTable tblDetallePedidoCotizacion;
    private org.jdesktop.swingx.JXTable tblPedidos;
    private javax.swing.JTextField txtNroFactura;
    private javax.swing.JTextField txtNroPedidoCliente;
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
                    return "PED-" + view.getNropedido();
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

    class DetallePedidoCotizacionTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Cantidad",
            "Producto",
            "Descripción"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewPresupuestoParaFactura view = filasDetalle.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNroproducto();
                case 1:
                    return view.getCantidad();
                case 2:
                    return view.getNombre();
                case 3:
                    return view.getDescripcion();
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
            if (filasDetalle != null) {
                return filasDetalle.size();
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
