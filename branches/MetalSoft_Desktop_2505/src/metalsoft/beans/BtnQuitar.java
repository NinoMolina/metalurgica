/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.beans;

import javax.swing.JButton;


/**
 *
 * @author Nino
 */
public class BtnQuitar extends javax.swing.JPanel implements java.beans.Customizer {

    private Object bean;

    /** Creates new customizer BtnQuitar */
    public BtnQuitar() {
        initComponents();
    }

    public void setObject(Object bean) {
        this.bean = bean;
    }

    public JButton getBtnQuitar() {
        return btnQuitar;
    }

    public void setBtnQuitar(JButton btnQuitar) {
        this.btnQuitar = btnQuitar;
    }
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        btnQuitar.setEnabled(enabled);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnQuitar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/quitar.png"))); // NOI18N
        btnQuitar.setText("Quitar");
        btnQuitar.setToolTipText("Quitar");
        add(btnQuitar, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuitar;
    // End of variables declaration//GEN-END:variables

}
