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
public class SubirBajar extends javax.swing.JPanel implements java.beans.Customizer {

    private Object bean;

    /** Creates new customizer SubirBajar */
    public SubirBajar() {
        initComponents();
    }

    public void setObject(Object bean) {
        this.bean = bean;
    }

    public JButton getBtnBajar() {
        return btnBajar;
    }

    public void setBtnBajar(JButton btnBajar) {
        this.btnBajar = btnBajar;
    }

    public JButton getBtnSubir() {
        return btnSubir;
    }

    public void setBtnSubir(JButton btnSubir) {
        this.btnSubir = btnSubir;
    }

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSubir = new javax.swing.JButton();
        btnBajar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/up.gif"))); // NOI18N
        btnSubir.setText("Subir");
        add(btnSubir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnBajar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/down.gif"))); // NOI18N
        btnBajar.setText("Bajar");
        add(btnBajar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 35, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        btnBajar.setEnabled(enabled);
        btnSubir.setEnabled(enabled);
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBajar;
    private javax.swing.JButton btnSubir;
    // End of variables declaration//GEN-END:variables

}
