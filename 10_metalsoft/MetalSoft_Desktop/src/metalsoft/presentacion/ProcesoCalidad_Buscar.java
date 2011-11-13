/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProcesoCalidad_Buscar.java
 *
 * Created on 13/11/2011, 18:10:22
 */
package metalsoft.presentacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Procesocalidad;
import metalsoft.negocio.gestores.GestorProcesoCalidad;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class ProcesoCalidad_Buscar extends javax.swing.JDialog {

    private static RegistrarProcesoCalidad ventana;
    private Timer timer;
    private GestorProcesoCalidad gestor = null;
    private List<Procesocalidad> lista;

    /** Creates new form ProcesoCalidad_Buscar */
    public ProcesoCalidad_Buscar(JDialog owner) {
        super(owner);
        initComponents();
        txtValor.setEnabled(false);
        txtValor.setText("");
        lista = new ArrayList<Procesocalidad>();

        table.setModel(new TableModel());
        table.setColumnControlVisible(true);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setHighlighters(new UIColorHighlighter(HighlightPredicate.ODD));
        table.setHorizontalScrollEnabled(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSeleccionar = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new org.jdesktop.swingx.JXTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/seleccionar.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Nombre");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        jRadioButton2.setText("Todos");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jRadioButton1)
                .addGap(93, 93, 93)
                .addComponent(jRadioButton2)
                .addContainerGap(279, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(7, 7, 7)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnSeleccionar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
    long id = lista.get(table.getSelectedRow()).getIdprocesocalidad();
    ventana.setProcesoModificar(gestor.getProcesoCalidad(id));
    ventana.cargarDatos();
    dispose();
}//GEN-LAST:event_btnSeleccionarActionPerformed

private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
    // TODO add your handling code here:
    txtValor.setEnabled(true);
}//GEN-LAST:event_jRadioButton1ActionPerformed

private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_txtValorActionPerformed

private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
    if (txtValor.getText().compareTo("") != 0) {
        lista.clear();
        lista = gestor.getListProcesoCalidadByName(txtValor.getText());
        table.updateUI();
        table.packAll();
    } else {
        lista.clear();
        table.updateUI();
        table.packAll();
    }
}//GEN-LAST:event_txtValorKeyReleased

private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
// TODO add your handling code here:
    txtValor.setEnabled(false);
    txtValor.setText("");
    lista.clear();
    lista=gestor.getListProcesoCalidad();
    table.updateUI();
    table.packAll();
}//GEN-LAST:event_jRadioButton2ActionPerformed

    public static RegistrarProcesoCalidad getVentana() {
        return ventana;
    }

    public static void setVentana(RegistrarProcesoCalidad ventana) {
        ProcesoCalidad_Buscar.ventana = ventana;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable table;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    class TableModel extends AbstractTableModel {

        String[] columnName = {"Nro. Proceso",
            "Nombre",
            "Especificacion",
            "Tolerancia",
            "Descripcion",
            "Herramienta"};

        public int getRowCount() {
            if (lista != null) {
                return lista.size();
            }
            return 0;
        }

        public int getColumnCount() {
            return columnName.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Procesocalidad detalle = lista.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return String.valueOf(detalle.getNroproceso());
                case 1:
                    return detalle.getNombre();
                case 2:
                    return detalle.getEspecificacion();
                case 3:
                    return detalle.getTolerancia();
                case 4:
                    return detalle.getTolerancia();
                case 5:
                    return detalle.getHerramienta();
                default:
                    return null;
            }

        }

        @Override
        public String getColumnName(int column) {
            return columnName[column];
        }
    }
}
