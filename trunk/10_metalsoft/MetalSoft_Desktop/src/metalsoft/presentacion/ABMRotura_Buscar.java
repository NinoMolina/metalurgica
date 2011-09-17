/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMRotura_Buscar.java
 *
 * Created on 03/05/2011, 23:54:21
 */

package metalsoft.presentacion;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.negocio.mantenimiento.Rotura;

/**
 *
 * @author Lorreine Prescott
 */
public class ABMRotura_Buscar extends javax.swing.JFrame {

    private static Timer timer;
    private HiloBuscarRotura hiloBuscarRotura;
    private Rotura[] rot;
    private ABMRotura ventana;
    /** Creates new form ABMRotura_Buscar */
     public ABMRotura_Buscar() {
        initComponents();
    }

    public Rotura[] getRot() {
        return rot;
    }

    public void setRot(Rotura[] rot) {
        this.rot = rot;
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
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstRotura = new javax.swing.JList();
        txtValor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscar Rotura");

        jRadioButton1.setText("Nombre");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Descripción");

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lstRotura);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(34, 34, 34)
                        .addComponent(jRadioButton2))
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(4, 4, 4)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnSeleccionar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
          Rotura x=rot[lstRotura.getSelectedIndex()];
        ventana.setRotura(x);
        ventana.getTxtNombre().setText(x.getNombre());
        ventana.getTxtDescripcion().setText(x.getDescripcion());
        this.dispose();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        // TODO add your handling code here:
          if(txtValor.getText().compareTo("")!=0)
        {
            final ABMRotura_Buscar abm=this;
            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hiloBuscarRotura=new HiloBuscarRotura();
                    hiloBuscarRotura.setVentana(abm);
                    hiloBuscarRotura.setValor(txtValor.getText());
                    hiloBuscarRotura.start();
                }
            }, 1500);
        }
    }//GEN-LAST:event_txtValorKeyReleased

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed


    public JList getLstRotura() {
        return lstRotura;
    }

    public void setLstRotura(JList lstRotura) {
        this.lstRotura = lstRotura;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(JTextField txtValor) {
        this.txtValor = txtValor;
    }


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMRotura_Buscar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstRotura;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

       void setVentanaRotura(ABMRotura aThis) {
        ventana=aThis;
    }
}
