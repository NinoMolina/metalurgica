/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportesPedidos.java
 *
 * Created on 19/09/2011, 10:46:57
 */

package metalsoft.presentacion;

import java.util.Date;
import javax.swing.JOptionPane;
import metalsoft.negocio.gestores.GestorReportes;


/**
 *
 * @author Lorreine Prescott
 */
public class ReportesPedidos extends javax.swing.JDialog {

    private GestorReportes gestor =new GestorReportes();
    private Date fechaDesde;
    private Date fechaHasta;

    /** Creates new form ReportesPedidos */
    public ReportesPedidos() {
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
        btnReportesClientes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dccFechaDesde = new com.toedter.calendar.JDateChooser();
        dccFechaHasta = new com.toedter.calendar.JDateChooser();
        btnCompletoPedidos = new javax.swing.JRadioButton();
        btnPedidosXEstados = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Pedidos");

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

        jLabel3.setText("Fecha desde:");

        jLabel4.setText("Fecha hasta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(dccFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addGap(141, 141, 141))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dccFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(dccFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        buttonGroup1.add(btnCompletoPedidos);
        btnCompletoPedidos.setText("Listado Completo de Pedidos");

        buttonGroup1.add(btnPedidosXEstados);
        btnPedidosXEstados.setText("Listado de Pedidos por Estados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnCompletoPedidos)
                        .addGap(18, 18, 18)
                        .addComponent(btnPedidosXEstados)))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompletoPedidos)
                    .addComponent(btnPedidosXEstados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnReportesClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportesClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesClientesActionPerformed
         if (!btnCompletoPedidos.isSelected() && !btnPedidosXEstados.isSelected()){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un criterio para la generación del reporte!");
            return;
        }
        if (btnCompletoPedidos.isSelected()){

        if (dccFechaDesde.getDate()== null || dccFechaHasta.getDate()== null){
            JOptionPane.showMessageDialog(this, "Debe ingresar las fechas!");
            return;
        }
        else
            fechaDesde= dccFechaDesde.getDate();
            fechaHasta= dccFechaHasta.getDate();

            gestor.ReportePedidos(fechaDesde,fechaHasta);
        }
        else
            if (btnPedidosXEstados.isSelected()){

            if (dccFechaDesde.getDate()== null || dccFechaHasta.getDate()== null){
            JOptionPane.showMessageDialog(this, "Debe ingresar las fechas!");
            return;
        }
        else
            fechaDesde= dccFechaDesde.getDate();
            fechaHasta= dccFechaHasta.getDate();

            gestor.ReportePedidosXEstados(fechaDesde,fechaHasta);
        }
}//GEN-LAST:event_btnReportesClientesActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportesPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnCompletoPedidos;
    private javax.swing.JRadioButton btnPedidosXEstados;
    private javax.swing.JButton btnReportesClientes;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dccFechaDesde;
    private com.toedter.calendar.JDateChooser dccFechaHasta;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
