/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarConfirmacionTrabajoTercerizado.java
 *
 * Created on 28/08/2011, 17:37:35
 */
package metalsoft.presentacion;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.negocio.gestores.GestorTrabajoTercerizado;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class RegistrarConfirmacionTrabajoTercerizado extends javax.swing.JFrame {

    /** Creates new form RegistrarConfirmacionTrabajoTercerizado */
    private List<Detalletrabajotercerizado> listaDetalle;
    private List<Trabajotercerizado> listaTrabajos;
    private GestorTrabajoTercerizado gestor;

    public RegistrarConfirmacionTrabajoTercerizado() {
        initComponents();
        gestor = new GestorTrabajoTercerizado();
        listaTrabajos = gestor.obtenerTrabajosPresupuestados();
        tblTrabajosTercerizados.updateUI();
        addListeners();
        setearTablas();
        btnconfirmar.setEnabled(false);
        btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
    }

    private void setearTablas() {
        //Detalle Trabajos
        tblDetalleTrabajoTercerizado.setModel(new DetalleTableModel());
        tblDetalleTrabajoTercerizado.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleTrabajoTercerizado.setShowHorizontalLines(false);
        tblDetalleTrabajoTercerizado.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleTrabajoTercerizado.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //Trabajos 
        tblTrabajosTercerizados.setModel(new TrabajoTercerizadoTableModel());
        tblTrabajosTercerizados.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblTrabajosTercerizados.setShowHorizontalLines(false);
        tblTrabajosTercerizados.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblTrabajosTercerizados.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListeners() {
        addListenerBtnSeleccionarPedido();
        addListenerBtnSalir();
    }

    private void addListenerBtnSeleccionarPedido() {
        btnSeleccionar1.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionar1BeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionar1BeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblTrabajosTercerizados.getSelectedRow() > -1) {
            Trabajotercerizado trab = listaTrabajos.get(tblTrabajosTercerizados.getSelectedRow());
            lblEmpresa.setText(trab.getEmpresa().getRazonsocial());
            lblEstado.setText(trab.getEstado().getNombre());
            lblNroPedido.setText("PED-" + String.valueOf(trab.getPedido().getNropedido()));
            lblNroTrabajoTercerizado.setText("TRAB-" + String.valueOf(trab.getNrotrabajotercerizado()));
            dccFechaEnvio.setDate(trab.getFechaenvioaempresa());
            dccFechaPedido.setDate(trab.getFechapedidocotizacion());
            dccFechaIngreso.setDate(trab.getFechadelingresocotizacion());
            lblmontoTotal.setText(String.valueOf(trab.getMontototal()));
            listaDetalle = gestor.buscarDetalleTrabajoTercerizado(trab.getIdtrabajo());
            tblDetalleTrabajoTercerizado.updateUI();
            btnconfirmar.setEnabled(true);
        }else{
            btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
        }
    }

    private void addListenerBtnSalir() {
        btnSalirr1.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTrabajosTercerizados = new org.jdesktop.swingx.JXTable();
        btnSeleccionar1 = new metalsoft.beans.BtnSeleccionar();
        jLabel2 = new javax.swing.JLabel();
        lblEmpresa = new javax.swing.JLabel();
        dccFechaPedido = new com.toedter.calendar.JDateChooser();
        lblEstado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNroPedido = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dccFechaEnvio = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        lblNroTrabajoTercerizado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dccFechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        lblmontoTotal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleTrabajoTercerizado = new org.jdesktop.swingx.JXTable();
        btnconfirmar = new javax.swing.JButton();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar el ingreso de los Pedidos de Cotizacion de Trabajo enviados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblTrabajosTercerizados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrabajosTercerizadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTrabajosTercerizados);

        jLabel2.setText("Seleccionar Pedido de Cotizacion de Trabajo Generado:");

        dccFechaPedido.setEnabled(false);

        jLabel6.setText("Estado:");

        jLabel3.setText("Nro. Pedido:");

        jLabel4.setText("Fecha de Pedido Cotización:");

        dccFechaEnvio.setEnabled(false);

        jLabel5.setText("Empresa:");

        jLabel1.setText("Nro. Trabajo Tercerizado:");

        jLabel7.setText("Fecha de Envio a Empresa:");

        jLabel8.setText("Fecha del Ingreso Cotización:");

        dccFechaIngreso.setEnabled(false);

        jLabel9.setText("Monto Total Cotización:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNroTrabajoTercerizado, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dccFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblmontoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblNroTrabajoTercerizado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(lblNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(dccFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(dccFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(lblmontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de Trabajo Tercerizado seleccionado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblDetalleTrabajoTercerizado.setEnabled(false);
        tblDetalleTrabajoTercerizado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleTrabajoTercerizadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleTrabajoTercerizado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnconfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/seleccionar.png"))); // NOI18N
        btnconfirmar.setText("Confirmar Cotización");
        btnconfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnconfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconfirmar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTrabajosTercerizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrabajosTercerizadosMouseClicked
        // TODO add your handling code here:
        btnSeleccionar1.getBtnSeleccionar().setEnabled(true);
}//GEN-LAST:event_tblTrabajosTercerizadosMouseClicked

    private void tblDetalleTrabajoTercerizadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleTrabajoTercerizadoMouseClicked
        
}//GEN-LAST:event_tblDetalleTrabajoTercerizadoMouseClicked

    private void btnconfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmarActionPerformed
        if (tblTrabajosTercerizados.getSelectedRow() > -1) {
            long result=0;
            Trabajotercerizado trab = listaTrabajos.get(tblTrabajosTercerizados.getSelectedRow());
            trab.setFechaconfirmaciontrabajo(Fecha.fechaActualDate());
            trab.setEstado(gestor.buscarEstadoConfirmado());
            result=gestor.modificarTrabajoTercerizado(trab);
            if(result>0){
                JOptionPane.showMessageDialog(this, "El trabajo tercerizado se ha cancelado correctamente");
                listaTrabajos=gestor.obtenerTrabajosPresupuestados();
                tblTrabajosTercerizados.updateUI();
                listaDetalle.clear();
                tblDetalleTrabajoTercerizado.updateUI();
                btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
                btnconfirmar.setEnabled(false);

            }else{
                JOptionPane.showMessageDialog(this, "El trabajo tercerizado NO se ha cancelar");
            }
        }

    }//GEN-LAST:event_btnconfirmarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarConfirmacionTrabajoTercerizado().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private metalsoft.beans.BtnSeleccionar btnSeleccionar1;
    private javax.swing.JButton btnconfirmar;
    private com.toedter.calendar.JDateChooser dccFechaEnvio;
    private com.toedter.calendar.JDateChooser dccFechaIngreso;
    private com.toedter.calendar.JDateChooser dccFechaPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNroPedido;
    private javax.swing.JLabel lblNroTrabajoTercerizado;
    private javax.swing.JLabel lblmontoTotal;
    private org.jdesktop.swingx.JXTable tblDetalleTrabajoTercerizado;
    private org.jdesktop.swingx.JXTable tblTrabajosTercerizados;
    // End of variables declaration//GEN-END:variables

    class TrabajoTercerizadoTableModel extends AbstractTableModel {

        String[] columnNames = {"nro. Trab tercerizado",
            "Fecha Pedido Cotizacion",
            "Empresa",
            "Pedido",
            "Fecha de Envio A Empresa",
            "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (!listaTrabajos.isEmpty()) {
                Trabajotercerizado trab = listaTrabajos.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return String.valueOf(trab.getNrotrabajotercerizado());
                    case 1:
                        return String.valueOf(trab.getFechapedidocotizacion());
                    case 2:
                        return trab.getEmpresa().getRazonsocial();
                    case 3:
                        return String.valueOf(trab.getPedido().getNropedido());
                    case 4:
                        return String.valueOf(trab.getFechaenvioaempresa());
                    case 5:
                        return trab.getEstado().getNombre();
                    default:
                        return null;
                }
            } else {
                return null;
            }


        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (listaTrabajos != null) {
                return listaTrabajos.size();
            }
            return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];

        }
    }

    class DetalleTableModel extends AbstractTableModel {

        String[] columnNames = {"Detalle nro",
            "Monto Parcial",
            "Pieza",
            "Proceso",
            "cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (!listaDetalle.isEmpty()) {
                Detalletrabajotercerizado trab = listaDetalle.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return String.valueOf(trab.getIddetalle());
                    case 1:
                        return String.valueOf(trab.getMontoparcial());
                    case 2:
                        return gestor.buscarPieza(trab.getPieza().longValue()).getNombre();
                    case 3:
                        return trab.getProceso().getNombre();
                    case 4:
                        return String.valueOf(trab.getCantidad());
                    case 5:
                        return trab.getEstado().getNombre();
                    default:
                        return null;
                }
            } else {
                return null;
            }


        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (listaDetalle != null) {
                return listaDetalle.size();
            }
            return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];

        }
    }
}