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

import javax.swing.JOptionPane;
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

        buttonGroup = new javax.swing.ButtonGroup();
        btnReportesClientes = new javax.swing.JButton();
        btnClientesCompleto = new javax.swing.JRadioButton();
        btnClientesMorosos = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(" Reportes Clientes");

        btnReportesClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_impresora.png"))); // NOI18N
        btnReportesClientes.setText("Imprimir Reporte");
        btnReportesClientes.setToolTipText("Imprimir reporte seleccionado");
        btnReportesClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesClientesActionPerformed(evt);
            }
        });

        buttonGroup.add(btnClientesCompleto);
        btnClientesCompleto.setText("Listado completo de Clientes");

        buttonGroup.add(btnClientesMorosos);
        btnClientesMorosos.setText("Listado de Clientes Morosos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnClientesCompleto)
                .addGap(63, 63, 63)
                .addComponent(btnClientesMorosos)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClientesMorosos)
                    .addComponent(btnClientesCompleto))
                .addGap(48, 48, 48)
                .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportesClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesClientesActionPerformed
       if (!btnClientesCompleto.isSelected() && !btnClientesMorosos.isSelected()){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un criterio para la generación del reporte!");
            return;
        }
        if (btnClientesCompleto.isSelected())
        gestor.ReporteClientes();
       else
        gestor.ReporteClientesMorosos();
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
    private javax.swing.JRadioButton btnClientesCompleto;
    private javax.swing.JRadioButton btnClientesMorosos;
    private javax.swing.JButton btnReportesClientes;
    private javax.swing.ButtonGroup buttonGroup;
    // End of variables declaration//GEN-END:variables

}
