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
public class BtnAgregar extends javax.swing.JPanel implements java.beans.Customizer {

    private Object bean;

    /** Creates new customizer BtnAgregar */
    public BtnAgregar() {
        initComponents();
    }

    public void setObject(Object bean) {
        this.bean = bean;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(JButton btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        btnAgregar.setEnabled(enabled);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("Agregar");
        add(btnAgregar, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    // End of variables declaration//GEN-END:variables

}