/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AbrirSesion.java
 *
 * Created on 11/05/2010, 00:14:34
 */
package metalsoft.presentacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import metalsoft.Main;
import metalsoft.MetalsoftDispatcher;
import metalsoft.negocio.gestores.GestorIniciarSesion;

/**
 *
 * @author Nino
 */
public class AbrirSesion extends javax.swing.JFrame {

    private String user;
    private String pass;

    /** Creates new form AbrirSesion */
    public AbrirSesion() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/metalsoft/presentacion/img/LogoMS7.png")).getImage());
        addListenerBtnIniciar();
        addListenerBtnSalir();
        addListenerTxt();
        iniciarSesion.getTxtUsuario().setText("admin");
        iniciarSesion.getTxtClave().setText("admin");
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private void addListenerBtnIniciar() {
        iniciarSesion.getBtnIniciar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnSalir() {
        iniciarSesion.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        GestorIniciarSesion g = new GestorIniciarSesion();
        g.setUser(iniciarSesion.getTxtUsuario().getText());
        g.setPass(iniciarSesion.getTxtClave().getText());
        metalsoft.datos.dbobject.UsuarioDB usuario = g.buscarUsuario();
        if (usuario != null) {
            try {
                this.dispose();
                MetalsoftDispatcher.dispatchUser(usuario);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al iniciar sesion", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(AbrirSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            iniciarSesion.getLblMensaje().setForeground(Color.RED);
            iniciarSesion.getLblMensaje().setText("*Usuario o clave incorrecta");
        }


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        iniciarSesion = new metalsoft.beans.IniciarSesion();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar Sesión");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        iniciarSesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                iniciarSesionKeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(iniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased

    }//GEN-LAST:event_formKeyReleased

    private void iniciarSesionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_iniciarSesionKeyReleased

    }//GEN-LAST:event_iniciarSesionKeyReleased
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.IniciarSesion iniciarSesion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    private void addListenerTxt() {
        iniciarSesion.getTxtUsuario().addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }

            private void txtUsuarioKeyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnIniciarActionPerformed(null);
                }
            }
        });


        iniciarSesion.getTxtClave().addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClaveKeyReleased(evt);
            }

            private void txtClaveKeyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnIniciarActionPerformed(null);
                }
            }
        });
    }    
}
