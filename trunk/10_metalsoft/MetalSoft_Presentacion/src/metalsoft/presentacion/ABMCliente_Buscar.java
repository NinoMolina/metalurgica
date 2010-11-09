/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMCliente_Buscar.java
 *
 * Created on 01/07/2010, 12:07:47
 */
package metalsoft.presentacion;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorCliente;
import metalsoft.negocio.gestores.IBuscador;

/**
 *
 * @author Nino
 */
public class ABMCliente_Buscar extends javax.swing.JFrame implements IBuscador {

    private GestorCliente gestor = null;
    private ABMCliente ventana = null;
    private RegistrarEntregaPedido ventanaRegistrarEntregaPedido=null;
    private Timer timer;

    /** Creates new form ABMCliente_Buscar */
    public ABMCliente_Buscar() {
        initComponents();
    }

    public RegistrarEntregaPedido getVentanaRegistrarEntregaPedido() {
        return ventanaRegistrarEntregaPedido;
    }

    public void setVentanaRegistrarEntregaPedido(RegistrarEntregaPedido ventanaRegistrarEntregaPedido) {
        this.ventanaRegistrarEntregaPedido = ventanaRegistrarEntregaPedido;
    }
    public GestorCliente getGestor() {
        return gestor;
    }

    public void setGestor(GestorCliente gestor) {
        this.gestor = gestor;
    }

    public ABMCliente getVentana() {
        return ventana;
    }

    public void setVentana(ABMCliente ventana) {
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

        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstLista = new javax.swing.JList();
        btnSeleccionar = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Cliente");

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

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/seleccionar.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Nombre");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
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
                        .addGap(81, 81, 81))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSeleccionar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if (txtValor.getText().compareTo("") != 0) {
            final ABMCliente_Buscar abm = this;
            timer = new Timer();
            timer.schedule(new TimerTask() {

                private HiloBuscarCliente hiloBuscarCliente;

                @Override
                public void run() {
                    hiloBuscarCliente = new HiloBuscarCliente();
                    hiloBuscarCliente.setVentana(abm);
                    hiloBuscarCliente.setValor(txtValor.getText());
                    hiloBuscarCliente.start();
                }
            }, 1500);
        }
    }

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
}//GEN-LAST:event_txtValorKeyReleased

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        long id = Long.parseLong(((ItemCombo) lstLista.getSelectedValue()).getId());
        if (ventana != null) {
            ventana.setIdCliente(id);
            ventana.clienteSeleccionado();
        }
        if (ventanaRegistrarEntregaPedido != null) {
            ventanaRegistrarEntregaPedido.setIdCliente(id);
            ventanaRegistrarEntregaPedido.clienteSeleccionado();
        }

}//GEN-LAST:event_btnSeleccionarActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jRadioButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMCliente_Buscar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstLista;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    void setVentanaCliente(ABMCliente abm) {
        ventana = abm;
    }


    public JList getList(String className) {
        return lstLista;
    }

    public JComboBox getCombo(String className) {
        return null;
    }

    public void setBusqueda(Object[] obj) {
    }
}