/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AsignarMateriaPrimaAProduccion.java
 *
 * Created on 17/10/2010, 04:56:29
 */
package metalsoft.presentacion;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import metalsoft.negocio.gestores.ViewPlanificacion;
import metalsoft.util.Fecha;

/**
 *
 * @author Vicky
 */
public class AsignarMateriaPrimaAProduccion extends javax.swing.JFrame {

    private LinkedList<ViewPlanificacion> filasPedidos;

    /** Creates new form AsignarMateriaPrimaAProduccion */
    public AsignarMateriaPrimaAProduccion() {
        initComponents();
        buscarPedidosConMPAsignada();
    }

    public void buscarPedidosConMPAsignada() {
        filasPedidos = gestor.buscarPedidosConDetalleProcesoCalidad();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos sin Materia Prima asignada"));

        tblPedidos.setModel(new PedidoTableModel());
        jScrollPane3.setViewportView(tblPedidos);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        ViewPlanificacion viewPedido = filasPedidos.get(tblPedidos.getSelectedRow());
        idPedido = viewPedido.getIdpedido();
        long nroPresupuesto = gestor.buscarNroPresupuesto(idPedido);
        lblNroPresupuesto.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRESUPUESTO, nroPresupuesto));
        lblHoraJornada.setText(Jornada.HORAS_JORNADA + " horas)");
        setFechas();
        cargarTablas();
        calcularTotales();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AsignarMateriaPrimaAProduccion().setVisible(true);
            }
        });
    }

    class PedidoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nro",
            "Pedido Cotiz Cliente",
            "Cliente",
            "Prioridad",
            "Fecha de Entrega",
            "Presupuesto"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewPlanificacion view = filasPedidos.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNropedido();
                case 1:
                    return view.getNropedidocotizacioncliente();
                case 2:
                    return view.getRazonsocial();
                case 3:
                    return view.getPrioridad();
                case 4:
                    if (view.getFechaentregaestipulada() == null) {
                        return "";
                    } else {
                        return Fecha.parseToString(view.getFechaentregaestipulada().getTime());
                    }
                case 5:
                    return view.getIdpresupuesto();

                default:
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
            if (filasPedidos != null) {
                return filasPedidos.size();
            }
            return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */
        public String getColumnName(int column) {
            return columnNames[column];

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables
}
