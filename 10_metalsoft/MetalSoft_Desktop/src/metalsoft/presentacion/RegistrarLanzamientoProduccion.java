/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarLanzamientoProduccion.java
 *
 * Created on 18/10/2010, 07:33:44
 */
package metalsoft.presentacion;

import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.negocio.gestores.GestorRegistrarLanzamientoProduccion;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewPedidosConMPAsignada;
import metalsoft.negocio.produccion.EjecucionPlanificacionProduccion;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Nino
 */
public class RegistrarLanzamientoProduccion extends javax.swing.JFrame {

    /** Creates new form RegistrarLanzamientoProduccion */
    private LinkedList<ViewPedidosConMPAsignada> filasPedidosConMPAsignada = new LinkedList<ViewPedidosConMPAsignada>();
    private GestorRegistrarLanzamientoProduccion gestor;
    private ViewPedidosConMPAsignada viewPedidoSeleccionado;
    private Date fechaActual;
    private Date fechaFinRecalculada;

    public RegistrarLanzamientoProduccion() {
        initComponents();
        gestor = new GestorRegistrarLanzamientoProduccion();
        limpiarCampos();
        setearEnabledComponents(false);
        buscarPedidosConMPAsignada();
        setearTablaPedidos();
        addListeners();
    }

    private void setearEnabledComponents(boolean b){
        btnLanzarProduccion.setEnabled(b);
        btnVerObservaciones.setEnabled(b);
    }

    private void limpiarCampos(){
        lblFechaFinPrevista.setText("...");
        lblFechaFinRecalculada.setText("...");
        lblFechaInicioPrevista.setText("...");
        lblNroPedido.setText("...");
        lblNroPlanifProduccion.setText("...");
        jdcFechaInicioReal.setDate(null);
        jdcFehaFiltro.setDate(null);
    }

    private void addListeners(){
        addListenerBtnSeleccionarPedido();
        addListenerBtnSalir();
    }

    private void addListenerBtnSeleccionarPedido() {
        beanBtnSeleccionarPedido.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPedidoActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarPedidoActionPerformed(java.awt.event.ActionEvent evt) {
        if(tblPedidos.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido..!");
            return;
        }
        viewPedidoSeleccionado = filasPedidosConMPAsignada.get(tblPedidos.getSelectedRow());
        setearDatosPedidoSeleccionado();
        setearEnabledComponents(true);
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
    private void buscarPedidosConMPAsignada() {
        filasPedidosConMPAsignada = gestor.buscarPedidosConMPAsignada();
    }

    private void setearTablaPedidos() {
        tblPedidos.setModel(new PedidoNoLanzadoTableModel());
        tblPedidos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedidos.setShowHorizontalLines(false);
        tblPedidos.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblPedidos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        tblPedidos.updateUI();
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
        jdcFehaFiltro = new com.toedter.calendar.JDateChooser();
        beanBtnSeleccionarPedido = new metalsoft.beans.BtnSeleccionar();
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
        jdcFechaInicioReal = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        lblFechaFinRecalculada = new javax.swing.JLabel();
        btnLanzarProduccion = new javax.swing.JButton();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lanzar Producción");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos listos para lanzar"));

        jScrollPane1.setViewportView(tblPedidos);

        jLabel1.setText("Fecha prevista de lanzamiento:");

        jdcFehaFiltro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFehaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(beanBtnSeleccionarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jdcFehaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jdcFechaInicioReal.setEnabled(false);
        jdcFechaInicioReal.setFont(new java.awt.Font("Tahoma", 1, 12));

        jLabel7.setText("Fecha Fin Recalculada:");

        lblFechaFinRecalculada.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFechaFinRecalculada.setForeground(new java.awt.Color(102, 0, 0));
        lblFechaFinRecalculada.setText("...");

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
                                .addComponent(lblNroPlanifProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcFechaInicioReal, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
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
                        .addContainerGap(530, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblFechaInicioPrevista)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(lblFechaFinRecalculada))
                    .addComponent(jdcFechaInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerObservaciones))
        );

        btnLanzarProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save1.png"))); // NOI18N
        btnLanzarProduccion.setText("Lanzar Producción");
        btnLanzarProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarProduccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLanzarProduccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 497, Short.MAX_VALUE)
                        .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLanzarProduccion))
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

    private void btnLanzarProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarProduccionActionPerformed
        metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion jpa=new Ejecucionplanificacionproduccion();
        jpa.setFechainicio(fechaActual);
        jpa.setHorainicio(new Date());
//        EjecucionPlanificacionProduccion e = new EjecucionPlanificacionProduccion();
//        e.setFechaInicio(fechaActual);
//        e.setHoraInicio(new Date());
        long nroejecucion = gestor.generarNvoNroEjecucionPlanificacionProduccion();
        jpa.setNroejecucionplanificacion(BigInteger.valueOf(nroejecucion));
        long result=gestor.guardarEjecucionPlanificacion(jpa,viewPedidoSeleccionado.getIdplanificacionproduccion());
        long resultPedido=gestor.actualizarEstadoPedido(viewPedidoSeleccionado.getIdpedido());
        if(result>0){
            JOptionPane.showMessageDialog(this, "Ya se ha lanzado la Producción!\nLos datos se guardaron CORRECTAMENTE!");
            setearEnabledComponents(false);
            limpiarCampos();
        }
        else{
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar!!!");
        }
    }//GEN-LAST:event_btnLanzarProduccionActionPerformed
    private void setearDatosPedidoSeleccionado() {
        lblFechaFinPrevista.setText(Fecha.parseToString(viewPedidoSeleccionado.getFechafinprevista()));
        lblFechaInicioPrevista.setText(Fecha.parseToString(viewPedidoSeleccionado.getFechainicioprevista()));
        lblNroPedido.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido()));
        lblNroPlanifProduccion.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PLANIF_PRODUCCION, viewPedidoSeleccionado.getNroplanificacionproduccion()));
        fechaActual = Fecha.fechaActualCalendar().getTime();
        jdcFechaInicioReal.setDate(fechaActual);
        fechaFinRecalculada = gestor.calcularFechaFin(fechaActual, viewPedidoSeleccionado.getFechainicioprevista(), viewPedidoSeleccionado.getFechafinprevista());
        lblFechaFinRecalculada.setText(Fecha.parseToString(fechaFinRecalculada));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarLanzamientoProduccion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarPedido;
    private javax.swing.JButton btnLanzarProduccion;
    private javax.swing.JButton btnVerObservaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFechaInicioReal;
    private com.toedter.calendar.JDateChooser jdcFehaFiltro;
    private javax.swing.JLabel lblFechaFinPrevista;
    private javax.swing.JLabel lblFechaFinRecalculada;
    private javax.swing.JLabel lblFechaInicioPrevista;
    private javax.swing.JLabel lblNroPedido;
    private javax.swing.JLabel lblNroPlanifProduccion;
    private org.jdesktop.swingx.JXTable tblPedidos;
    // End of variables declaration//GEN-END:variables

    class PedidoNoLanzadoTableModel extends AbstractTableModel {

        private String[] columnNames = {
            "nropedido",
            "nroplanificacionproduccion",
            "fechacreacion",
            "fechainicioprevista",
            "fechafinprevista",
            "observaciones"
        };

        public int getRowCount() {
            if (filasPedidosConMPAsignada != null) {
                return filasPedidosConMPAsignada.size();
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
            ViewPedidosConMPAsignada view = filasPedidosConMPAsignada.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, view.getNropedido());
                case 1:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PLANIF_PRODUCCION, view.getNroplanificacionproduccion());
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
