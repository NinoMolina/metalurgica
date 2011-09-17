/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMMatriz_Buscar.java
 *
 * Created on 30/06/2010, 13:02:15
 */
package metalsoft.presentacion;

import com.sun.servicetag.RegistrationData;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.datos.jpa.entity.Matriz;
import metalsoft.negocio.gestores.GestorMatriz;


/**
 *
 * @author Mariana
 */
public class ABMMatriz_Buscar extends javax.swing.JFrame {

    private static Timer timer;
    private HiloBuscarMatriz hiloBuscarMatriz;
    private List<Matriz> m;
    private ABMMatriz ventana;
    private RegistrarPedidoMatriz ventanaPedido;
    private GestorMatriz gestor;

    /** Creates new form ABMMatriz_Buscar */
    public ABMMatriz_Buscar() {
        initComponents();
    }

    public List<Matriz> getMatriz() {
        return m;
    }

    public void setMatriz(List<Matriz> m) {
        this.m = m;
    }

    public GestorMatriz getGestor() {
        return gestor;
    }

    public void setGestor(GestorMatriz gestor) {
        this.gestor = gestor;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMatriz = new javax.swing.JList();
        btnSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jRadioButton1.setText("Nombre");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Descripción");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
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

        jScrollPane1.setViewportView(lstMatriz);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSeleccionar)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if (txtValor.getText().compareTo("") != 0) {
            final ABMMatriz_Buscar abm = this;
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    hiloBuscarMatriz = new HiloBuscarMatriz();
                    hiloBuscarMatriz.setVentana(abm);
                    hiloBuscarMatriz.setValor(txtValor.getText());
                    hiloBuscarMatriz.start();
                }
            }, 1500);
        }
    }

    public JList getLstMatriz() {
        return lstMatriz;
    }

    public void setLstMatriz(JList lstMatriz) {
        this.lstMatriz = lstMatriz;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(JTextField txtValor) {
        this.txtValor = txtValor;
}//GEN-LAST:event_txtValorKeyReleased

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        Matriz x = m.get(lstMatriz.getSelectedIndex());
        if (ventana != null) {
            ventana.setMatriz(x);
            ventana.getTxtNombre().setText(x.getNombre());
            ventana.getTxtDescripcion().setText(x.getDescripcion());
            ventana.getTxtCodigo().setText(String.valueOf(x.getCodmatriz()));
            //ventana.getcmbMateriaPrima().setSelectedItem(gestor.getItemMateriaPrima(x.getMateriaprima()));
            int indexMateriaPrima = gestor.getIndexMateriaPrima(x.getMateriaprima().getIdmateriaprima());
            ventana.getcmbMateriaPrima().setSelectedIndex(indexMateriaPrima);
            int indexTipoMaterial = gestor.getIndexTipoMaterial(x.getTipomaterial().getIdtipomaterial());
            ventana.getcmbTipoMaterial().setSelectedIndex(indexTipoMaterial);
        }
        if (ventanaPedido != null) {
            ventanaPedido.seleccionarMatriz(x);
        }
        this.dispose();
}//GEN-LAST:event_btnSeleccionarActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMMatriz_Buscar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstMatriz;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    void setVentanaMatriz(ABMMatriz aThis) {
        ventana = aThis;
    }

    void setVentanaPedidoMatriz(RegistrarPedidoMatriz aThis) {
        ventanaPedido = aThis;
    }
}
