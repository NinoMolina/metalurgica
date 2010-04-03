/*
 * PllaRecepcionPedidoCombustiblePrincipal.java
 *
 * Created on 7 de junio de 2008, 16:18
 */

package pkjRegistrarRecepcionPedidoProductosPlaya;

import pkgSoporte.Pantalla;

/**
 *
 * @author  Fer
 */
public class PllaRecepcionPedidoProductosPlaya extends javax.swing.JFrame {
    
    /** Creates new form PllaRecepcionPedidoCombustiblePrincipal */
    public PllaRecepcionPedidoProductosPlaya() {
        initComponents();
        tblPedidosPendientes.getColumn("Proveedor").setMinWidth(200);
       this.cmbTipoFactura.setEnabled(false);
        Pantalla.insertarIcono(this,"D:/Facultad/5A�oK1/ProyectoFinal/Iteracion1/4. 1� Iteraci�n/Interfaces/PrjModuloCompras/Imagenes/logoestacion.gif");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoComprobanteRecibido = new javax.swing.ButtonGroup();
        jPRecepcion = new javax.swing.JPanel();
        lblFechaRecepcion = new javax.swing.JLabel();
        jCdarChFecha = new com.toedter.calendar.JDateChooser();
        lblHoraRecepcion = new javax.swing.JLabel();
        jsfHoraRecepcion = new com.toedter.components.JSpinField();
        jsfMinutosRecepcion = new com.toedter.components.JSpinField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidosPendientes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleRecepcion = new javax.swing.JTable();
        lblDetalleRecepcion = new javax.swing.JLabel();
        lblDetalleRecepcion1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtNumeroFactura = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        cmbTipoFactura = new javax.swing.JComboBox();
        lblNumeroComprobanteRecibido1 = new javax.swing.JLabel();
        chbFactura = new javax.swing.JCheckBox();
        chbRemito = new javax.swing.JCheckBox();
        txtNumeroRemito = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Recepci�n Pedido Productos de Playa");
        setResizable(false);

        jPRecepcion.setBorder(javax.swing.BorderFactory.createTitledBorder("Recepci�n"));

        lblFechaRecepcion.setText("Fecha de Recepci�n:");

        lblHoraRecepcion.setText("Hora de Recepci�n:");

        jLabel1.setText(":");

        tblPedidosPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"01", "04/03/08", "Proveedor Playa ", "300"},
                {"02", "05/06/08", "Proveedor MiniShop", "200"},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N�mero", "Fecha", "Proveedor", "Monto Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPedidosPendientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblPedidosPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosPendientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidosPendientes);

        tblDetalleRecepcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane2.setViewportView(tblDetalleRecepcion);

        lblDetalleRecepcion.setText("Detalle Recepci�n");

        lblDetalleRecepcion1.setText("Pedidos  Pendientes");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Adicionales"));

        jLabel5.setText("Responsable");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Responsable", "Diego", "Cecilia", "Armando", "Fernando" }));

        cmbTipoFactura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tipo", "A", "B", "C" }));

        lblNumeroComprobanteRecibido1.setText("Tipo");

        chbFactura.setText("N� Factura:");
        chbFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFacturaActionPerformed(evt);
            }
        });

        chbRemito.setText("N� Remito:");
        chbRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRemitoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbRemito)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chbFactura)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNumeroRemito, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNumeroComprobanteRecibido1)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chbFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroComprobanteRecibido1)
                            .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbRemito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNumeroRemito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPRecepcionLayout = new javax.swing.GroupLayout(jPRecepcion);
        jPRecepcion.setLayout(jPRecepcionLayout);
        jPRecepcionLayout.setHorizontalGroup(
            jPRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPRecepcionLayout.createSequentialGroup()
                .addGroup(jPRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPRecepcionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFechaRecepcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCdarChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblHoraRecepcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsfHoraRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsfMinutosRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDetalleRecepcion)
                    .addComponent(lblDetalleRecepcion1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPRecepcionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPRecepcionLayout.setVerticalGroup(
            jPRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPRecepcionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPRecepcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jsfMinutosRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lblHoraRecepcion)
                    .addComponent(jsfHoraRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCdarChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaRecepcion))
                .addGap(19, 19, 19)
                .addComponent(lblDetalleRecepcion1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblDetalleRecepcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPRecepcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(428, Short.MAX_VALUE)
                        .addComponent(btnAceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
this.dispose();

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void tblPedidosPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosPendientesMouseClicked
if(tblPedidosPendientes.getSelectedRow()==0)
{ tblDetalleRecepcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Aceite Normal", "400", "5,00", "2000"},
                {"Aceite 2T", "200", "6,00", "1200"},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Total"
            }
        ));}
else
{
if(tblPedidosPendientes.getSelectedRow()==1)
{
 tblDetalleRecepcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Turron", "100", "0,25", "25"},
                {"Tutuca", "50", "1,00", "50"},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Total"
            }
        ));



}
else
{
 tblDetalleRecepcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
               {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Total"
            }
        ));

}
}      
    }//GEN-LAST:event_tblPedidosPendientesMouseClicked

    private void chbFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFacturaActionPerformed
        if(this.chbFactura.isSelected()){
            this.cmbTipoFactura.setEnabled(true);
        } else{
            this.cmbTipoFactura.setEnabled(false);
        }
    }//GEN-LAST:event_chbFacturaActionPerformed

    private void chbRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRemitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbRemitoActionPerformed
    
    /**
     * @param args the command line arguments
     */
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JCheckBox chbFactura;
    private javax.swing.JCheckBox chbRemito;
    private javax.swing.JComboBox cmbTipoFactura;
    private javax.swing.ButtonGroup grupoComprobanteRecibido;
    private com.toedter.calendar.JDateChooser jCdarChFecha;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPRecepcion;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.components.JSpinField jsfHoraRecepcion;
    private com.toedter.components.JSpinField jsfMinutosRecepcion;
    private javax.swing.JLabel lblDetalleRecepcion;
    private javax.swing.JLabel lblDetalleRecepcion1;
    private javax.swing.JLabel lblFechaRecepcion;
    private javax.swing.JLabel lblHoraRecepcion;
    private javax.swing.JLabel lblNumeroComprobanteRecibido1;
    private javax.swing.JTable tblDetalleRecepcion;
    private javax.swing.JTable tblPedidosPendientes;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtNumeroRemito;
    // End of variables declaration//GEN-END:variables
    
}
