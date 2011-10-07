/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportesMateriasPrimas.java
 *
 * Created on 01/10/2011, 00:36:45
 */

package metalsoft.presentacion;

import javax.swing.JOptionPane;
import metalsoft.negocio.gestores.GestorReportes;

/**
 *
 * @author Lorreine Prescott
 */
public class ReportesMateriasPrimas extends javax.swing.JDialog {

     private GestorReportes gestor =new GestorReportes();
     private String materiaprima;

    /** Creates new form ReportesMateriasPrimas */
    public ReportesMateriasPrimas() {
        initComponents();
        jPanel1.setVisible(false);
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
        btnMPsXProveedor = new javax.swing.JRadioButton();
        btnMPenStock = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMateriaPrima = new javax.swing.JTextField();
        btnReportesClientes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Materias Primas");

        buttonGroup1.add(btnMPsXProveedor);
        btnMPsXProveedor.setText("Informe Materia Prima por Proveedor");
        btnMPsXProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMPsXProveedorActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnMPenStock);
        btnMPenStock.setText("Listado de Materias Primas en Stock");
        btnMPenStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMPenStockActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Materia Prima"));
        jPanel1.setName("Período"); // NOI18N

        jLabel3.setText("Nombre:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        btnReportesClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_impresora.png"))); // NOI18N
        btnReportesClientes.setText("Imprimir Reporte");
        btnReportesClientes.setToolTipText("Imprimir reporte seleccionado");
        btnReportesClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnMPenStock)
                        .addGap(18, 18, 18)
                        .addComponent(btnMPsXProveedor)))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMPenStock)
                    .addComponent(btnMPsXProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportesClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesClientesActionPerformed
        if (!btnMPenStock.isSelected() && !btnMPsXProveedor.isSelected()){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un criterio para la generación del reporte!");
            return;
        }
        if (btnMPenStock.isSelected()){

           

            gestor.ReporteMateriasPrimas();
        } else
            if (btnMPsXProveedor.isSelected()){

                if (txtMateriaPrima.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de alguna Materia Prima!");
                    return;
                } else
                    materiaprima= txtMateriaPrima.getText();
                
                gestor.ReporteMateriaPrimaXProveedor(materiaprima);
            }
}//GEN-LAST:event_btnReportesClientesActionPerformed

    private void btnMPenStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMPenStockActionPerformed
     jPanel1.setVisible(false);
    }//GEN-LAST:event_btnMPenStockActionPerformed

    private void btnMPsXProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMPsXProveedorActionPerformed
       jPanel1.setVisible(true);
    }//GEN-LAST:event_btnMPsXProveedorActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportesMateriasPrimas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnMPenStock;
    private javax.swing.JRadioButton btnMPsXProveedor;
    private javax.swing.JButton btnReportesClientes;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMateriaPrima;
    // End of variables declaration//GEN-END:variables

}
