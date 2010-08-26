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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        addListenerBtnIniciar();
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
    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        GestorIniciarSesion g=new GestorIniciarSesion();
        g.setUser(iniciarSesion.getTxtUsuario().getText());
        g.setPass(iniciarSesion.getTxtClave().getText());
        long idUsuario=-1L;
        idUsuario=g.buscarUsuario();
        if(idUsuario>0)
        {
            try {
                Principal p=new Principal(idUsuario);
                this.dispose();
                p.setVisible(true);
                p.setLocationRelativeTo(null);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al iniciar sesion", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(AbrirSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
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

        iniciarSesion = new metalsoft.beans.IniciarSesion();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar Sesión");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.IniciarSesion iniciarSesion;
    // End of variables declaration//GEN-END:variables

    

}
