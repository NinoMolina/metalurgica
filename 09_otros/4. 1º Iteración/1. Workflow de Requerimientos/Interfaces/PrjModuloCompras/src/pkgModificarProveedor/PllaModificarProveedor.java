/*
 * PllaModificarProveedor.java
 *
 * Created on 9 de junio de 2008, 0:32
 */

package pkgModificarProveedor;
import pkgSoporte.*;
/**
 *
 * @author  Armando
 */
public class PllaModificarProveedor extends javax.swing.JFrame {
    
    /** Creates new form PllaModificarProveedor */
    public PllaModificarProveedor() {
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
        jPProveedor2 = new javax.swing.JPanel();
        lblRazonSocial2 = new javax.swing.JLabel();
        txtRazonSocial2 = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jSPProveedores = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        btnModificarRepresentante = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnModificarAsignacionProductos = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Proveedor");
        setResizable(false);
        jPProveedor2.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor"));
        jPProveedor2.setEnabled(false);
        lblRazonSocial2.setText("Raz\u00f3n Social:");

        txtRazonSocial2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRazonSocial2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonSocial2KeyReleased(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CUIT", "Raz�n Social", "Domicilio ", "N� Ingreso Bruto", "Condici�n IVA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jSPProveedores.setViewportView(tblProveedores);

        btnModificarRepresentante.setText("Modificar Representantes");

        btnEliminarProveedor.setText("Modificar Proveedor");
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        btnModificarAsignacionProductos.setText("Modificar Productos");
        btnModificarAsignacionProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAsignacionProductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPProveedor2Layout = new javax.swing.GroupLayout(jPProveedor2);
        jPProveedor2.setLayout(jPProveedor2Layout);
        jPProveedor2Layout.setHorizontalGroup(
            jPProveedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPProveedor2Layout.createSequentialGroup()
                .addComponent(lblRazonSocial2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRazonSocial2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
            .addComponent(jSPProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPProveedor2Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(btnModificarAsignacionProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarRepresentante))
        );
        jPProveedor2Layout.setVerticalGroup(
            jPProveedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPProveedor2Layout.createSequentialGroup()
                .addGroup(jPProveedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRazonSocial2)
                    .addComponent(txtRazonSocial2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSPProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPProveedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarRepresentante)
                    .addComponent(btnEliminarProveedor)
                    .addComponent(btnModificarAsignacionProductos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPProveedor2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnAceptar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPProveedor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarAsignacionProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAsignacionProductosActionPerformed
          PllaModificarAsignacionProductos mAsigProd=new PllaModificarAsignacionProductos();
          Pantalla.centrarEnPantallaJFrame(mAsigProd);
          mAsigProd.setVisible(true);
    }//GEN-LAST:event_btnModificarAsignacionProductosActionPerformed

 

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
       PllaModificarDatosProveedor pMProveedor=new  PllaModificarDatosProveedor();
       Pantalla.centrarEnPantallaJFrame(pMProveedor);
       pMProveedor.setVisible(true);
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtRazonSocial2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocial2KeyReleased
        
    }//GEN-LAST:event_txtRazonSocial2KeyReleased

    private void txtRazonSocial2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocial2KeyPressed
        
    }//GEN-LAST:event_txtRazonSocial2KeyPressed
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnModificarAsignacionProductos;
    private javax.swing.JButton btnModificarRepresentante;
    private javax.swing.JPanel jPProveedor2;
    private javax.swing.JScrollPane jSPProveedores;
    private javax.swing.JLabel lblRazonSocial2;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTextField txtRazonSocial2;
    // End of variables declaration//GEN-END:variables
    
}
