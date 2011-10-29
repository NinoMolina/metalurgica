/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMMateriaPrima_Buscar.java
 *
 * Created on 27/08/2010, 16:16:37
 */
package metalsoft.presentacion;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.negocio.gestores.GestorMateriaPrima;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class ABMMateriaPrima_Buscar extends javax.swing.JDialog {

    private ABMMateriaPrima ventana;
    private Timer timer;
    private GestorMateriaPrima gestor = null;
    private MateriaprimaDB[] materiasPrimasDB;

    /** Creates new form ABMMateriaPrima_Buscar */
    public ABMMateriaPrima_Buscar(JDialog owner) {
        super(owner);
        initComponents();
    }

    public GestorMateriaPrima getGestor() {
        return gestor;
    }

    public ABMMateriaPrima getVentana() {
        return ventana;
    }

    /** Creates new form ABMEtapaDeProduccion_Buscar */
    void setVentana(ABMMateriaPrima ventana) {
        this.ventana = ventana;
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
        jRadioButton1 = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstLista = new javax.swing.JList();
        btnSeleccionar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Materia Prima");

        buttonGroup1.add(jRadioButton1);
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

        jScrollPane1.setViewportView(lstLista);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Todos");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 500, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(389, Short.MAX_VALUE)
                .add(btnSeleccionar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jRadioButton1)
                        .add(107, 107, 107)
                        .add(jRadioButton2)
                        .add(227, 227, 227))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, txtValor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButton1)
                    .add(jRadioButton2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(txtValor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnSeleccionar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jRadioButton1ActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if (txtValor.getText().compareTo("") != 0) {
            final ABMMateriaPrima_Buscar abm = this;
            timer = new Timer();
            timer.schedule(new TimerTask() {

                private HiloBuscarMateriaPrima hiloBuscar;

                @Override
                public void run() {
                    hiloBuscar = new HiloBuscarMateriaPrima();
                    hiloBuscar.setVentana(abm);
                    hiloBuscar.setValor(txtValor.getText());
                    hiloBuscar.start();
                }
            }, 1500);
        }
}//GEN-LAST:event_txtValorKeyReleased
    public JList getLstLista() {
        return lstLista;
    }

    public void setLstLista(JList lstLista) {
        this.lstLista = lstLista;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(JTextField txtValor) {
        this.txtValor = txtValor;
    }
    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        long id = Long.parseLong(((ItemCombo) lstLista.getSelectedValue()).getId());
        if (ventana != null) {
            ventana.setIdEtapa(id);
            ventana.etapaSeleccionada();
        }
        dispose();
}//GEN-LAST:event_btnSeleccionarActionPerformed

    public JList getList(String className) {
        return lstLista;
    }

    public void setBusqueda(Object[] obj) {
        materiasPrimasDB = (MateriaprimaDB[]) obj;
    }

    public JComboBox getCombo(String className) {
        return null;
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstLista;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
