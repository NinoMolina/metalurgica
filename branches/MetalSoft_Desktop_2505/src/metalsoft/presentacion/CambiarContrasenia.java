/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CambiarContrasenia.java
 *
 * Created on 28/08/2011, 22:22:43
 */
package metalsoft.presentacion;

import javax.swing.JOptionPane;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.negocio.gestores.GestorNuevoUsuario;

/**
 *
 * @author Vicky
 */
public class CambiarContrasenia extends javax.swing.JFrame {

    /** Creates new form CambiarContrasenia */
    private Usuario usuario;
    private GestorNuevoUsuario gestor;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        lblusuario.setText(usuario.getUsuario());
    }

    public CambiarContrasenia() {
        initComponents();
        addListeners();
        gestor = new GestorNuevoUsuario();
        
    }

    private void addListeners() {
        addListenerBtnGuardar();
        addListenerBtnSalir();
    }

    private void addListenerBtnGuardar() {
        btnGuardar1.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarBeanActionPerformed(evt);
            }
        });
    }

    private void btnGuardarBeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (usuario != null) {
            if (txtcontrasenia.getText().compareTo(usuario.getClave()) == 0) {
                if (txtnvaContrasenia.getText().compareTo(txtRepetirContrasenia.getText()) == 0) {
                    long result = 0;
                    usuario.setClave(txtnvaContrasenia.getText());
                    result = gestor.modificarUsuario(usuario);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "Los datos han sido guardados correctamente");
                        txtRepetirContrasenia.setText("");
                        txtcontrasenia.setText("");
                        txtnvaContrasenia.setText("");
                    }else{
                        JOptionPane.showMessageDialog(this, "Los datos NO han sido guardados");
                        txtRepetirContrasenia.setText("");
                        txtcontrasenia.setText("");
                        txtnvaContrasenia.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "La nueva contraseña no coincide con la confirmación de la misma");
                    txtRepetirContrasenia.setText("");
                    txtnvaContrasenia.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "La contraseña ingresada no es la correcta");
                txtRepetirContrasenia.setText("");
                txtcontrasenia.setText("");
                txtnvaContrasenia.setText("");
            }
        }
    }

    private void addListenerBtnSalir() {
        btnSalirr1.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

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

        jLabel1 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnGuardar1 = new metalsoft.beans.BtnGuardar();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        txtcontrasenia = new javax.swing.JPasswordField();
        txtnvaContrasenia = new javax.swing.JPasswordField();
        txtRepetirContrasenia = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cambiar Contraseña");

        jLabel1.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jLabel4.setText("Nueva Contraseña:");

        jLabel5.setText("Confirmar Contraseña:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblusuario, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRepetirContrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnvaContrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcontrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtcontrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnvaContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRepetirContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CambiarContrasenia().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnGuardar btnGuardar1;
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JPasswordField txtRepetirContrasenia;
    private javax.swing.JPasswordField txtcontrasenia;
    private javax.swing.JPasswordField txtnvaContrasenia;
    // End of variables declaration//GEN-END:variables
}
