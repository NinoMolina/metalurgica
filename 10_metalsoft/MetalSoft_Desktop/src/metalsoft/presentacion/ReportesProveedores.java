/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportesProveedores.java
 *
 * Created on 30/09/2011, 23:42:30
 */

package metalsoft.presentacion;

import java.util.Date;
import javax.swing.JOptionPane;
import metalsoft.negocio.gestores.GestorReportes;

/**
 *
 * @author Lorreine Prescott
 */
public class ReportesProveedores extends javax.swing.JFrame {

    private GestorReportes gestor = new GestorReportes();
    private Date fechaDesde;
    private Date fechaHasta;

    /** Creates new form ReportesProveedores */
    public ReportesProveedores() {
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
        btnProvMayorCantReclamos = new javax.swing.JRadioButton();
        btnProvMayNroCompras = new javax.swing.JRadioButton();
        btnReportesClientes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dccFechaDesde = new com.toedter.calendar.JDateChooser();
        dccFechaHasta = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes Proveedores");

        buttonGroup1.add(btnProvMayorCantReclamos);
        btnProvMayorCantReclamos.setText("Listado de Proveedores con mayor cantidad de Reclamos");
        btnProvMayorCantReclamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvMayorCantReclamosActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnProvMayNroCompras);
        btnProvMayNroCompras.setText("Informe de Proveedores con Mayor volumen de Compras");
        btnProvMayNroCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvMayNroComprasActionPerformed(evt);
            }
        });

        btnReportesClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_impresora.png"))); // NOI18N
        btnReportesClientes.setText("Imprimir Reporte");
        btnReportesClientes.setToolTipText("Imprimir reporte seleccionado");
        btnReportesClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesClientesActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Período"));
        jPanel1.setName("Período"); // NOI18N

        jLabel1.setText("Fecha desde:");

        jLabel2.setText("Fecha hasta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dccFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dccFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dccFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(dccFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProvMayNroCompras)
                            .addComponent(btnProvMayorCantReclamos)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnProvMayorCantReclamos)
                .addGap(18, 18, 18)
                .addComponent(btnProvMayNroCompras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProvMayorCantReclamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvMayorCantReclamosActionPerformed
        jPanel1.setVisible(false);
}//GEN-LAST:event_btnProvMayorCantReclamosActionPerformed

    private void btnProvMayNroComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvMayNroComprasActionPerformed
        jPanel1.setVisible(true);
}//GEN-LAST:event_btnProvMayNroComprasActionPerformed

    private void btnReportesClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesClientesActionPerformed
        if (!btnProvMayorCantReclamos.isSelected() && !btnProvMayNroCompras.isSelected()){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un criterio para la generación del reporte!");
            return;
        }
        if (btnProvMayorCantReclamos.isSelected()){



            gestor.ReporteVolumenReclamoXProveedor();
        } else
            if (btnProvMayNroCompras.isSelected()){

                if (dccFechaDesde.getDate()== null || dccFechaHasta.getDate()== null){
                JOptionPane.showMessageDialog(this, "Debe ingresar las fechas!");
                return;
                }
            else
                fechaDesde= dccFechaDesde.getDate();
                fechaHasta= dccFechaHasta.getDate();

                gestor.ReporteProveedoresMayorNroCompras(fechaDesde,fechaHasta);
            }
}//GEN-LAST:event_btnReportesClientesActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportesProveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnProvMayNroCompras;
    private javax.swing.JRadioButton btnProvMayorCantReclamos;
    private javax.swing.JButton btnReportesClientes;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dccFechaDesde;
    private com.toedter.calendar.JDateChooser dccFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
