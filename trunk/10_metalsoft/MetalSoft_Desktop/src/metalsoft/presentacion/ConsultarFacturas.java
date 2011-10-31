/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultarFacturas.java
 *
 * Created on 29/08/2011, 20:22:13
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.Factura;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.negocio.gestores.GestorFactura;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class ConsultarFacturas extends javax.swing.JDialog {

    /** Creates new form ConsultarFacturas */
    private List<Factura> filasFactura;
    private List<Detallefactura> filasDetalle;
    private GestorFactura gestor;
    private Pedido pedido;
    private Double monto=0d;

    public ConsultarFacturas() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorFactura();
        addListeners();
        setearTablas();
        btnImprimir.setEnabled(false);
        btnVerDetalle.setEnabled(false);
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);
        dccFechaEmision.setEnabled(false);
        dccFechaVencimiento.setEnabled(false);
        txtNroFactura.setEnabled(false);
    }

    private void setearTablas() {
        //DETALLE FACTURA
        tblDetalleFactura.setModel(new DetalleFacturaTableModel());
        tblDetalleFactura.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleFactura.setShowHorizontalLines(false);
        tblDetalleFactura.setShowVerticalLines(false);
        tblDetalleFactura.setHorizontalScrollEnabled(true); 
        /* On dit de surligner une ligne sur deux */
        tblDetalleFactura.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //FACTURA
        tblFacturas.setModel(new FacturaTableModel());
        tblFacturas.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblFacturas.setShowHorizontalLines(false);
        tblFacturas.setShowVerticalLines(false);
        tblFacturas.setHorizontalScrollEnabled(true); 
        /* On dit de surligner une ligne sur deux */
        tblFacturas.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListeners() {
        addListenerBtnSalirr();
    }

    private void addListenerBtnSalirr() {
        btnSalirr1.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFacturas = new org.jdesktop.swingx.JXTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDetalleFactura = new org.jdesktop.swingx.JXTable();
        btnImprimir = new javax.swing.JButton();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        btnVerDetalle = new javax.swing.JButton();
        rbNroFactura = new javax.swing.JRadioButton();
        txtNroFactura = new javax.swing.JTextField();
        rbFechaEmision = new javax.swing.JRadioButton();
        rbFechaVto = new javax.swing.JRadioButton();
        bsyBuscar1 = new org.jdesktop.swingx.JXBusyLabel();
        dccFechaEmision = new org.jdesktop.swingx.JXDatePicker();
        dccFechaVencimiento = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        lblmonto = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Facturas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Facturas"));

        tblFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFacturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFacturas);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Factura Seleccionada"));

        jScrollPane5.setViewportView(tblDetalleFactura);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_impresora.png"))); // NOI18N
        btnImprimir.setToolTipText("Imprimir factura seleccionada");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnVerDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/detalle.gif"))); // NOI18N
        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbNroFactura);
        rbNroFactura.setText("Nro. Factura:");
        rbNroFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNroFacturaActionPerformed(evt);
            }
        });

        txtNroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNroFacturaKeyReleased(evt);
            }
        });

        buttonGroup1.add(rbFechaEmision);
        rbFechaEmision.setText("Fecha de Emisión:");
        rbFechaEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFechaEmisionActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbFechaVto);
        rbFechaVto.setText("Fecha de Vto:");
        rbFechaVto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFechaVtoActionPerformed(evt);
            }
        });

        dccFechaEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dccFechaEmisionActionPerformed(evt);
            }
        });

        dccFechaVencimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dccFechaVencimientoActionPerformed(evt);
            }
        });

        jLabel1.setText("Monto Total Factura:");

        lblmonto.setFont(new java.awt.Font("Tahoma", 1, 12));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbNroFactura)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbFechaEmision)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dccFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbFechaVto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dccFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(165, 165, 165)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                                        .addComponent(bsyBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnVerDetalle))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnImprimir)
                                .addGap(35, 35, 35)
                                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnImprimir, btnSalirr1});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbNroFactura)
                        .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbFechaEmision)
                        .addComponent(dccFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbFechaVto)
                        .addComponent(dccFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerDetalle)
                            .addComponent(bsyBuscar1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnImprimir, btnSalirr1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbFechaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFechaEmisionActionPerformed
        String fecha = String.valueOf(Fecha.fechaActual());
        filasFactura = gestor.buscarFacturasByFechaEmision(fecha);
        tblFacturas.updateUI();
        tblFacturas.packAll();
        txtNroFactura.setEnabled(false);
        dccFechaEmision.setEnabled(true);
        dccFechaVencimiento.setEnabled(false);
        txtNroFactura.setText("");
        dccFechaEmision.setDate(Fecha.fechaActualDate());
        dccFechaVencimiento.setDate(null);
    }//GEN-LAST:event_rbFechaEmisionActionPerformed

    private void txtNroFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFacturaKeyReleased

        bsyBuscar1.setVisible(true);
        bsyBuscar1.setBusy(true);
        if (txtNroFactura.getText().compareTo("") != 0) {
            filasFactura = gestor.buscarFacturasByNroLike(txtNroFactura.getText());
            tblFacturas.updateUI();
            tblFacturas.packAll();
        }
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);
    }//GEN-LAST:event_txtNroFacturaKeyReleased

    private void rbNroFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNroFacturaActionPerformed
        filasFactura = new LinkedList<Factura>();
        tblFacturas.updateUI();
        tblFacturas.packAll();
        txtNroFactura.setEnabled(true);
        dccFechaEmision.setEnabled(false);
        dccFechaVencimiento.setEnabled(false);
        dccFechaEmision.setDate(null);
        dccFechaVencimiento.setDate(null);
    }//GEN-LAST:event_rbNroFacturaActionPerformed

    private void rbFechaVtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFechaVtoActionPerformed
        String fecha = String.valueOf(Fecha.fechaActual());
        filasFactura = gestor.buscarFacturasByFechaVto(fecha);
        tblFacturas.updateUI();
        tblFacturas.packAll();
        txtNroFactura.setEnabled(false);
        dccFechaEmision.setEnabled(false);
        dccFechaVencimiento.setEnabled(true);
        txtNroFactura.setText("");
        dccFechaEmision.setDate(null);
        dccFechaVencimiento.setDate(Fecha.fechaActualDate());
    }//GEN-LAST:event_rbFechaVtoActionPerformed

    private void dccFechaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dccFechaEmisionActionPerformed
        // TODO add your handling code here:
        bsyBuscar1.setVisible(true);
        bsyBuscar1.setBusy(true);
        if (dccFechaEmision.getDate() != null) {
            String fecha = String.valueOf(dccFechaEmision.getDate());
            filasFactura = gestor.buscarFacturasByFechaEmision(fecha);
            tblFacturas.updateUI();
            tblFacturas.packAll();
        }
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);
    }//GEN-LAST:event_dccFechaEmisionActionPerformed

    private void dccFechaVencimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dccFechaVencimientoActionPerformed
        bsyBuscar1.setVisible(true);
        bsyBuscar1.setBusy(true);
        if (dccFechaEmision.getDate() != null) {
            String fecha = String.valueOf(dccFechaEmision.getDate());
            filasFactura = gestor.buscarFacturasByFechaVto(fecha);
            tblFacturas.updateUI();
            tblFacturas.packAll();
        }
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);
    }//GEN-LAST:event_dccFechaVencimientoActionPerformed

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        if (tblFacturas.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Factura!");
            return;
        }
        Factura v = filasFactura.get(tblFacturas.getSelectedRow());
        filasDetalle = gestor.buscarDetalleFacturaByFactura(v.getIdfactura());
        tblDetalleFactura.updateUI();
        tblDetalleFactura.packAll();
        
        for(Detallefactura de : filasDetalle){
            monto+=de.getMontoparcial();
            pedido=de.getIdpedido();
        }
        lblmonto.setText(String.valueOf(monto));
        btnVerDetalle.setEnabled(false);
        btnImprimir.setEnabled(true);
    }//GEN-LAST:event_btnVerDetalleActionPerformed

    private void tblFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFacturasMouseClicked
        if (tblFacturas.getSelectedRow() >= 0) {
            btnVerDetalle.setEnabled(true);
        }
    }//GEN-LAST:event_tblFacturasMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        gestor.imprimirFactura(pedido.getIdpedido(),monto);
    }//GEN-LAST:event_btnImprimirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ConsultarFacturas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXBusyLabel bsyBuscar1;
    private javax.swing.JButton btnImprimir;
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.ButtonGroup buttonGroup1;
    private org.jdesktop.swingx.JXDatePicker dccFechaEmision;
    private org.jdesktop.swingx.JXDatePicker dccFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblmonto;
    private javax.swing.JRadioButton rbFechaEmision;
    private javax.swing.JRadioButton rbFechaVto;
    private javax.swing.JRadioButton rbNroFactura;
    private org.jdesktop.swingx.JXTable tblDetalleFactura;
    private org.jdesktop.swingx.JXTable tblFacturas;
    private javax.swing.JTextField txtNroFactura;
    // End of variables declaration//GEN-END:variables

    class DetalleFacturaTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro Detalle",
            "Nro Pedido",
            "Producto",
            "Cantidad",
            "Monto parcial"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            Detallefactura view = filasDetalle.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return String.valueOf(view.getIddetallepedido());
                case 1:
                    return String.valueOf(view.getIdpedido().getNropedido());
                case 2:
                    return gestor.buscarDetallePedido(view.getIddetallepedido().longValue()).getProducto().getNombre();
                case 3:
                    return String.valueOf(view.getCantidad());
                case 4:
                    return String.valueOf(view.getMontoparcial());
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

    class FacturaTableModel extends AbstractTableModel {

        String[] columnNames = {"nrofactura",
            "tipofactura",
            "fechaemision",
            "tipoiva",
            "estado",
            "fechavencimiento",
            "fecharealcobro",
            "formapago"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            Factura view = filasFactura.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return String.valueOf(view.getNrofactura());
                case 1:
                    return view.getTipofactura();
                case 2:
                    return String.valueOf(view.getFechaemision());
                case 3:
                    if (view.getTipoiva() == null) {
                        return "";
                    } else {
                        return view.getTipoiva().getNombre();
                    }
                case 4:
                    if (view.getEstado() == null) {
                        return "";
                    } else {
                        return view.getEstado().getNombre();
                    }
                case 5:
                    return String.valueOf(view.getFechavencimiento());
                case 6:
                    return String.valueOf(view.getFecharealcobro());
                case 7:
                    if (view.getFormapago() == null) {
                        return "";
                    } else {
                        return view.getFormapago().getNombre();
                    }
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
            if (filasFactura != null) {
                return filasFactura.size();
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
