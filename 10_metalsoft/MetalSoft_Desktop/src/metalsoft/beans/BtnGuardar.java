/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.beans;

import java.awt.Color;
import javax.swing.JButton;


/**
 *
 * @author Nino
 */
public class BtnGuardar extends javax.swing.JPanel implements java.beans.Customizer {

    private Object bean;

    /** Creates new customizer BtnGuardar */
    public BtnGuardar() {
        initComponents();
    }

    public void setObject(Object bean) {
        this.bean = bean;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        btnGuardar.setForeground(new java.awt.Color(51, 153, 0));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/save1.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    // End of variables declaration//GEN-END:variables

}
