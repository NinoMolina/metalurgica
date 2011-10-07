/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarPresupuest_VerDetalle.java
 *
 * Created on 07/11/2010, 02:39:41
 */
package metalsoft.presentacion;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
import metalsoft.negocio.gestores.ViewProductoPresupuesto;

/**
 *
 * @author Nino
 */
public class RegistrarPresupuest_VerDetalle extends javax.swing.JDialog {
        private LinkedList<ViewProductoPresupuesto> filasProductoPresupuesto;
    /** Creates new form RegistrarPresupuest_VerDetalle */
    public RegistrarPresupuest_VerDetalle(LinkedList<ViewProductoPresupuesto> list) {
        initComponents();
        filasProductoPresupuesto=list;
        addListeners();
        tblProdPresupuesto.setModel(new ProductoPresupuestoTableModel());
        tblProdPresupuesto.updateUI();
    }

    private void addListeners() {
        addListenerBtnSalir();
    }

    private void addListenerBtnSalir() {
        beanBtnSalir.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdPresupuesto = new org.jdesktop.swingx.JXTable();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalle Presupuesto");

        jScrollPane1.setViewportView(tblProdPresupuesto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable tblProdPresupuesto;
    // End of variables declaration//GEN-END:variables
class ProductoPresupuestoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Cantidad",
            "Producto",
            "Pre. Unit.",
            "Importe"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewProductoPresupuesto view = null;
            view = filasProductoPresupuesto.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return view.getCantidadproducto();
                case 1:
                    return view.getNombreproducto();
                case 2:
                    return "$ "+view.getPreciounitario();
                case 3:
                    return "$ "+view.getImporte();
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
            if (filasProductoPresupuesto != null) {
                return filasProductoPresupuesto.size();
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
