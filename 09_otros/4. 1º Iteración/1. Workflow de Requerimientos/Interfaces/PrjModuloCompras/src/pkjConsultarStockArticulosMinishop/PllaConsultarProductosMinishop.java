/*
 * PllaConsultarProductosMinishop.java
 *
 * Created on 8 de junio de 2008, 15:48
 */

package pkjConsultarStockArticulosMinishop;

import pkgSoporte.Pantalla;

/**
 *
 * @author  Fer
 */
public class PllaConsultarProductosMinishop extends javax.swing.JFrame {
    
    /** Creates new form PllaConsultarProductosMinishop */
    public PllaConsultarProductosMinishop() {
        initComponents();
          Pantalla.insertarIcono(this,"D:/Facultad/5A�oK1/ProyectoFinal/Iteracion1/4. 1� Iteraci�n/Interfaces/PrjModuloCompras/Imagenes/logoestacion.gif");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPMiniShop = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductosMinishop = new javax.swing.JTable();
        btnRealizarPedido = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Productos Minishop");
        setResizable(false);
        jPMiniShop.setBorder(javax.swing.BorderFactory.createTitledBorder("MiniShop"));
        lblNombre.setText("Nombre");

        lblCodigo.setText("Codigo");

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        tblProductosMinishop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo de Barras", "Nombre", "Stock Minimo", "Stock Actual", "Diferencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductosMinishop);

        btnRealizarPedido.setText("Realizar Pedido");

        javax.swing.GroupLayout jPMiniShopLayout = new javax.swing.GroupLayout(jPMiniShop);
        jPMiniShop.setLayout(jPMiniShopLayout);
        jPMiniShopLayout.setHorizontalGroup(
            jPMiniShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMiniShopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPMiniShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPMiniShopLayout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lblCodigo)
                        .addGap(14, 14, 14)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnConsultar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(btnRealizarPedido, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPMiniShopLayout.setVerticalGroup(
            jPMiniShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMiniShopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPMiniShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblCodigo)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnRealizarPedido))
        );

        btnCerrar.setText("Aceptar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPMiniShop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPMiniShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
tblProductosMinishop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"090723674", "Alfajor Bonobon", "10", "35","25"},
                {"643224832", "Obleas Opera", "5", "15", "10"},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo de Barras", "Nombre", "Stock Minimo", "Stock Actual", "Diferencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            }; public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed
    
   
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnRealizarPedido;
    private javax.swing.JPanel jPMiniShop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTable tblProductosMinishop;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
    
}
