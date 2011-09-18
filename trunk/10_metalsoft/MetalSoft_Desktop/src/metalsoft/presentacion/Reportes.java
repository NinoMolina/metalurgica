/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Reportes.java
 *
 * Created on 04/09/2011, 22:54:13
 */

package metalsoft.presentacion;

import metalsoft.negocio.gestores.GestorReportes;

/**
 *
 * @author Lorreine Prescott
 */
public class Reportes extends javax.swing.JFrame {

    private GestorReportes gestor= new GestorReportes();

    /** Creates new form Reportes */
    public Reportes() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReportesClientes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reportes");

        btnReportesClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_impresora.png"))); // NOI18N
        btnReportesClientes.setText("Clientes");
        btnReportesClientes.setToolTipText("Imprimir factura seleccionada");
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
                .addGap(23, 23, 23)
                .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btnReportesClientes)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportesClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesClientesActionPerformed
        gestor.ReporteClientes();
}//GEN-LAST:event_btnReportesClientesActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReportesClientes;
    // End of variables declaration//GEN-END:variables

}
