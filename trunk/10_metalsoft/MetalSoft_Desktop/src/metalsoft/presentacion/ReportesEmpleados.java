/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportesEmpleados.java
 *
 * Created on 02/10/2011, 00:23:07
 */

package metalsoft.presentacion;

import javax.swing.JOptionPane;
import metalsoft.negocio.gestores.GestorReportes;

/**
 *
 * @author Lorreine Prescott
 */
public class ReportesEmpleados extends javax.swing.JFrame {

     private GestorReportes gestor = new GestorReportes();

    /** Creates new form ReportesEmpleados */
    public ReportesEmpleados() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnReportes = new javax.swing.JButton();
        btnEmpleadosCompleto = new javax.swing.JRadioButton();
        btnEmpleadosXCargo = new javax.swing.JRadioButton();
        btnEmpleadosXCategoria = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Empleados");

        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_impresora.png"))); // NOI18N
        btnReportes.setText("Imprimir Reporte");
        btnReportes.setToolTipText("Imprimir reporte seleccionado");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnEmpleadosCompleto);
        btnEmpleadosCompleto.setText("Listado completo de Empleados");

        buttonGroup1.add(btnEmpleadosXCargo);
        btnEmpleadosXCargo.setText("Informe de Empleados por Cargo");
        btnEmpleadosXCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosXCargoActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnEmpleadosXCategoria);
        btnEmpleadosXCategoria.setText("Informe de Empleados por Categoría");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEmpleadosCompleto)
                            .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEmpleadosXCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(btnEmpleadosXCargo)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmpleadosXCargo)
                    .addComponent(btnEmpleadosXCategoria))
                .addGap(30, 30, 30)
                .addComponent(btnEmpleadosCompleto)
                .addGap(42, 42, 42)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        if (!btnEmpleadosCompleto.isSelected() && !btnEmpleadosXCargo.isSelected()&& !btnEmpleadosXCategoria.isSelected()){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un criterio para la generación del reporte!");
            return;
        }
           if (btnEmpleadosCompleto.isSelected())
            gestor.ReporteEmpleadosCompleto();
           else
                if (btnEmpleadosXCargo.isSelected())
                gestor.ReporteEmpleadosXCargo();
                else
            gestor.ReporteEmpleadosXCategoria();
}//GEN-LAST:event_btnReportesActionPerformed

    private void btnEmpleadosXCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosXCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEmpleadosXCargoActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportesEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnEmpleadosCompleto;
    private javax.swing.JRadioButton btnEmpleadosXCargo;
    private javax.swing.JRadioButton btnEmpleadosXCategoria;
    private javax.swing.JButton btnReportes;
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration//GEN-END:variables

}
