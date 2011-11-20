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

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.negocio.gestores.GestorArmadoPedido;
import metalsoft.negocio.gestores.GestorPedidoCotizacion;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewDetallePedidoReal;
import metalsoft.negocio.gestores.ViewPedidoConCalidad;
import metalsoft.util.Combo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Mariana
 */
public class RegistrarArmadoPedido extends javax.swing.JDialog implements IBuscador {

    private List<Pedido> filasPedidos;
    private GestorArmadoPedido gestor;
    private TableCellRender tcrTblDetallePedido;
    private LinkedList<ViewDetallePedidoReal> filasDetallePedido;
    private GestorPedidoCotizacion gestorPedido;

    /** Creates new form RegistrarArmadoPedido */
    public RegistrarArmadoPedido() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorArmadoPedido();
        gestorPedido = new GestorPedidoCotizacion();
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
                this.tblDetallePedido.packAll();
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

    private void cargarComboPrioridad1() {
        cmbPrioridad1.removeAllItems();
        gestorPedido.obtenerPrioridades(cmbPrioridad1);
    }

    private void cargarComboEstado1() {
        cmbEstado1.removeAllItems();
        gestorPedido.obtenerEstados(cmbEstado1);
    }

    private void btnSeleccionarPedidoBeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (pedidosSinArmar1.getTblPedidos().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido!");
            return;
        }
        cargarComboEstado1();
        cargarComboPrioridad1();
        limpiarCampos();
        Pedido ped = filasPedidos.get(pedidosSinArmar1.getTblPedidos().getSelectedRow());
        long idPed = ped.getIdpedido();

        if (ped.getFactura() != null) {
            txtNroFactura.setText(String.valueOf(ped.getFactura().getNrofactura()));
        }
        txtNroPedidoCliente.setText(String.valueOf(ped.getNropedidocotizacioncliente()));
        if (ped.getEstado().getIdestado() < 1) {
            Combo.setItemComboSeleccionado(cmbEstado1, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbEstado1, ped.getEstado().getIdestado());
        }
        if (ped.getPrioridad().getIdprioridad() < 1) {
            Combo.setItemComboSeleccionado(cmbPrioridad1, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbPrioridad1, ped.getPrioridad().getIdprioridad());
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
//        if (ped.getFechaentregareal() == null) {
//            dccEntregaReal.setDate(null);
//        } else {
//            dccEntregaReal.setDate(ped.getFechaentregareal());
//        }
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



        filasDetallePedido = gestor.buscarDetallePedido(idPed);
        tblDetallePedido.updateUI();
        tblDetallePedido.packAll();
    }

    private void setearTablas() {
        //DETALLE PEDIDO
        tblDetallePedido.setModel(new DetallePedidoConCalidadTableModel());
        tblDetallePedido.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetallePedido.setShowHorizontalLines(false);
        tblDetallePedido.setShowVerticalLines(false);
        tblDetallePedido.setHorizontalScrollEnabled(true);
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
        btnGuardar1 = new metalsoft.beans.BtnGuardar();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        jLabel14 = new javax.swing.JLabel(){

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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDetallePedido = new org.jdesktop.swingx.JXTable();
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
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dccConfirmacionPedido = new com.toedter.calendar.JDateChooser();
        dccEntregaEstipulada = new com.toedter.calendar.JDateChooser();
        dccPedidoCotizacion = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        dccFechaReqCotizacion = new com.toedter.calendar.JDateChooser();

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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bsyBuscar1, 0, 0, Short.MAX_VALUE)
                    .addComponent(txtPedido)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pedidosSinArmar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Pedido"));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pedido"));

        jScrollPane5.setViewportView(tblDetallePedido);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPrioridad1, 0, 132, Short.MAX_VALUE)
                            .addComponent(cmbEstado1, 0, 132, Short.MAX_VALUE)
                            .addComponent(txtNroFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
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
                .addGap(24, 24, 24))
        );

        jPanel6.setEnabled(false);

        jLabel4.setText("Fecha de Confirmación de Pedido:");

        jLabel5.setText("Fecha de Entrega Estipulada:");

        jLabel15.setText("Fecha Pedido Cotización:");

        dccConfirmacionPedido.setEnabled(false);

        dccEntregaEstipulada.setEnabled(false);

        dccPedidoCotizacion.setEnabled(false);

        jLabel17.setText("Fecha Requerida de Pedido:");

        dccFechaReqCotizacion.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel15))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(dccEntregaEstipulada, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(dccConfirmacionPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccConfirmacionPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccEntregaEstipulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 625, Short.MAX_VALUE)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
    private javax.swing.JComboBox cmbEstado1;
    private javax.swing.JComboBox cmbPrioridad1;
    private com.toedter.calendar.JDateChooser dccConfirmacionPedido;
    private com.toedter.calendar.JDateChooser dccEntregaEstipulada;
    private com.toedter.calendar.JDateChooser dccFechaReqCotizacion;
    private com.toedter.calendar.JDateChooser dccPedidoCotizacion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblNroPedido;
    private metalsoft.beans.PedidosSinArmar pedidosSinArmar1;
    private org.jdesktop.swingx.JXTable tblDetallePedido;
    private javax.swing.JTextField txtNroFactura;
    private javax.swing.JTextField txtNroPedidoCliente;
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
        this.tblDetallePedido.packAll();
    }

    class DetallePedidoConCalidadTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro. Producto",
            "Producto",
            "Descripción",
            "Cantidad"};

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
