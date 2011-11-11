/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarLanzamientoCalidad.java
 *
 * Created on 17/09/2011, 20:45:38
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.negocio.gestores.GestorLanzarCalidad;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewPedidosConProduccionFinalizada;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Nino
 */
public class RegistrarLanzamientoCalidad extends javax.swing.JDialog {

    /** Creates new form RegistrarLanzamientoCalidad */
    private GestorLanzarCalidad gestor;
    private LinkedList<ViewPedidosConProduccionFinalizada> filasPedidosConProduccionFinalizada = new LinkedList<ViewPedidosConProduccionFinalizada>();
    private ViewPedidosConProduccionFinalizada viewPedidoSeleccionado;
    private Date fechaActual;
    private Date fechaFinRecalculada;

    public RegistrarLanzamientoCalidad() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorLanzarCalidad();
        limpiarCampos();
        setearEnabledComponents(false);
        jdcFehaFiltro.setDate(Fecha.fechaActualDate());
        buscarPedidosConProduccionFinalizada();
        setearTablaPedidos();
        addListeners();
    }

    private void setearTablaPedidos() {
        tblPedidos.setModel(new PedidoNoLanzadoTableModel());
        tblPedidos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedidos.setShowHorizontalLines(false);
        tblPedidos.setShowVerticalLines(false);
        tblPedidos.setHorizontalScrollEnabled(true);
        /* On dit de surligner une ligne sur deux */
        tblPedidos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

    }

    private void setearEnabledComponents(boolean b) {
        btnLanzarCalidad.setEnabled(b);
        btnVerObservaciones.setEnabled(b);
    }

    private void limpiarCampos() {
        lblFechaFinPrevista.setText("...");
        lblFechaFinRecalculada.setText("...");
        lblFechaInicioPrevista.setText("...");
        lblNroPedido.setText("...");
        lblNroPlanifProduccion.setText("...");
        txtFechaInicioReal.setText("...");
        jdcFehaFiltro.setDate(null);
    }

    private void addListeners() {
        addListenerBtnSeleccionarPedido();
        addListenerBtnSalir();
        addListenerJdcFehaFiltro();
    }

    private void addListenerJdcFehaFiltro() {
        jdcFehaFiltro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jdcFechaFiltroActionPerformed(e);
            }
        });

    }

    private void jdcFechaFiltroActionPerformed(ActionEvent e) {
        Date fechaSeleccionada = jdcFehaFiltro.getDate();
        limpiarCampos();
        if (fechaSeleccionada.compareTo(Fecha.fechaActualDate()) > 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha menor o igual a la fecha actual (" + Fecha.fechaActual() + ")", "Información", JOptionPane.WARNING_MESSAGE);
            jdcFehaFiltro.setDate(Fecha.fechaActualDate());
            return;
        }
        jdcFehaFiltro.setDate(fechaSeleccionada);
        buscarPedidosConProduccionFinalizada();
    }

    private void addListenerBtnSeleccionarPedido() {
        beanBtnSeleccionarPedido.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPedidoActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarPedidoActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblPedidos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido..!");
            return;
        }
        viewPedidoSeleccionado = filasPedidosConProduccionFinalizada.get(tblPedidos.getSelectedRow());
        setearDatosPedidoSeleccionado();
        setearEnabledComponents(true);
    }

    private void setearDatosPedidoSeleccionado() {
        lblFechaFinPrevista.setText(Fecha.parseToString(viewPedidoSeleccionado.getFechafinprevista()));
        lblFechaInicioPrevista.setText(Fecha.parseToString(viewPedidoSeleccionado.getFechainicioprevista()));
        lblNroPedido.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido()));
        lblNroPlanifProduccion.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PLANIF_CALIDAD, viewPedidoSeleccionado.getNroplanificacionproduccion()));
        txtFechaInicioReal.setText(Fecha.fechaActual());
        fechaFinRecalculada = gestor.calcularFechaFin(Fecha.fechaActualDate(), viewPedidoSeleccionado.getFechainicioprevista(), viewPedidoSeleccionado.getFechafinprevista());
        lblFechaFinRecalculada.setText(Fecha.parseToString(fechaFinRecalculada));
    }

    private void addListenerBtnSalir() {
        beanBtnSalir.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new org.jdesktop.swingx.JXTable();
        jLabel1 = new javax.swing.JLabel();
        beanBtnSeleccionarPedido = new metalsoft.beans.BtnSeleccionar();
        jdcFehaFiltro = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNroPedido = new javax.swing.JLabel();
        lblNroPlanifProduccion = new javax.swing.JLabel();
        lblFechaInicioPrevista = new javax.swing.JLabel();
        lblFechaFinPrevista = new javax.swing.JLabel();
        btnVerObservaciones = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblFechaFinRecalculada = new javax.swing.JLabel();
        txtFechaInicioReal = new javax.swing.JLabel();
        btnLanzarCalidad = new javax.swing.JButton();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();
        jLabel15 = new javax.swing.JLabel(){

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
        setTitle("Registrar Ejecución Procesos de Calidad");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos listos para Ejecutar"));

        jScrollPane1.setViewportView(tblPedidos);

        jLabel1.setText("Fecha prevista de inicio:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFehaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(beanBtnSeleccionarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jdcFehaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnSeleccionarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedido/Planificación Seleccionado"));

        jLabel2.setText("Nro Pedido:");

        jLabel3.setText("Nro Planificación:");

        jLabel4.setText("Fecha Inicio Prevista:");

        jLabel5.setText("Fecha Fin Prevista:");

        lblNroPedido.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblNroPedido.setText("...");

        lblNroPlanifProduccion.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblNroPlanifProduccion.setText("...");

        lblFechaInicioPrevista.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFechaInicioPrevista.setText("...");

        lblFechaFinPrevista.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFechaFinPrevista.setText("...");

        btnVerObservaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detalle factura.png"))); // NOI18N
        btnVerObservaciones.setText("Ver Observaciones");
        btnVerObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerObservacionesActionPerformed(evt);
            }
        });

        jLabel6.setText("Fecha Inicio Real:");

        jLabel7.setText("Fecha Fin Recalculada:");

        lblFechaFinRecalculada.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFechaFinRecalculada.setForeground(new java.awt.Color(102, 0, 0));
        lblFechaFinRecalculada.setText("...");

        txtFechaInicioReal.setFont(new java.awt.Font("Tahoma", 1, 12));
        txtFechaInicioReal.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFechaInicioPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroPlanifProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(3, 3, 3)
                                .addComponent(txtFechaInicioReal, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFechaFinPrevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFechaFinRecalculada, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnVerObservaciones)
                        .addContainerGap(577, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblNroPedido))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNroPlanifProduccion)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(lblFechaFinPrevista)))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblFechaInicioPrevista)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(lblFechaFinRecalculada)
                        .addComponent(txtFechaInicioReal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(btnVerObservaciones))
        );

        btnLanzarCalidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save1.png"))); // NOI18N
        btnLanzarCalidad.setText("Ejecutar Calidad");
        btnLanzarCalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarCalidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLanzarCalidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 554, Short.MAX_VALUE)
                .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLanzarCalidad))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnVerObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerObservacionesActionPerformed
    JTextArea txtObs = new JTextArea(viewPedidoSeleccionado.getObservaciones());
    txtObs.setEditable(false);
    Object[] obj = {"Observaciones:", txtObs};
    JOptionPane.showMessageDialog(this, obj, "Observaciones Planificación", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_btnVerObservacionesActionPerformed

private void btnLanzarCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarCalidadActionPerformed
    try {
        Ejecucionplanificacioncalidad ejecucionPlanificacionCalidad = new Ejecucionplanificacioncalidad();
        ejecucionPlanificacionCalidad.setFechainicio(Fecha.fechaActualDate());
        ejecucionPlanificacionCalidad.setHorainicio(Fecha.fechaActualDate());
        long nroejecucion = gestor.generarNvoNroEjecucionPlanificacionCalidad();
        ejecucionPlanificacionCalidad.setNroejecucionplanificacioncalidad(BigInteger.valueOf(nroejecucion));
        /*
         * ######### CREAR EJECUCION DE CALIDAD ##########
         */
        long result = gestor.guardarEjecucionPlanificacion(ejecucionPlanificacionCalidad, viewPedidoSeleccionado.getIdplanificacioncalidad());
        /*
         * ######### ACTUALIZAR ESTADO DEL PEDIDO ##########
         */
        gestor.actualizarEstadoPedido(viewPedidoSeleccionado.getIdpedido());

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Se ha registrado el comienzo del control de calidad!\nLos datos se guardaron CORRECTAMENTE!");
//            gestor.imprimirHojaDePieza(viewPedidoSeleccionado.getIdpedido());
            filasPedidosConProduccionFinalizada.remove(tblPedidos.getSelectedRow());
            setearEnabledComponents(false);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar!!!");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar!!!\n" + ex.getMessage());
        ex.printStackTrace();
    }
}//GEN-LAST:event_btnLanzarCalidadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrarLanzamientoCalidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarLanzamientoCalidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarLanzamientoCalidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarLanzamientoCalidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarLanzamientoCalidad().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarPedido;
    private javax.swing.JButton btnLanzarCalidad;
    private javax.swing.JButton btnVerObservaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jdcFehaFiltro;
    private javax.swing.JLabel lblFechaFinPrevista;
    private javax.swing.JLabel lblFechaFinRecalculada;
    private javax.swing.JLabel lblFechaInicioPrevista;
    private javax.swing.JLabel lblNroPedido;
    private javax.swing.JLabel lblNroPlanifProduccion;
    private org.jdesktop.swingx.JXTable tblPedidos;
    private javax.swing.JLabel txtFechaInicioReal;
    // End of variables declaration//GEN-END:variables

    private void buscarPedidosConProduccionFinalizada() {
        filasPedidosConProduccionFinalizada = gestor.buscarPedidosConProduccionFinalizada(jdcFehaFiltro.getDate());
        tblPedidos.updateUI();
        tblPedidos.packAll();
    }

    class PedidoNoLanzadoTableModel extends AbstractTableModel {

        private String[] columnNames = {
            "Nro. Pedido",
            "Nro. Planif. Calidad",
            "Fecha Creación",
            "Fecha Inicio Prevista",
            "Fecha Fin Prevista",
            "Observaciones"
        };

        public int getRowCount() {
            if (filasPedidosConProduccionFinalizada != null) {
                return filasPedidosConProduccionFinalizada.size();
            } else {
                return 0;
            }
        }

        public String[] getColumnNames() {
            return columnNames;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            ViewPedidosConProduccionFinalizada view = filasPedidosConProduccionFinalizada.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, view.getNropedido());
                case 1:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PLANIF_CALIDAD, view.getNroplanificacionproduccion());
                case 2:
                    return Fecha.parseToString(view.getFechacreacion());
                case 3:
                    return Fecha.parseToString(view.getFechainicioprevista());
                case 4:
                    return Fecha.parseToString(view.getFechafinprevista());
                case 5:
                    return view.getObservaciones();
                default:
                    return null;
            }
        }
    }
}
