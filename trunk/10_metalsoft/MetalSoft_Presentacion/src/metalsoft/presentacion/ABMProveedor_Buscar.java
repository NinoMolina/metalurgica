/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMProveedor_Buscar.java
 *
 * Created on 30/08/2010, 02:42:44
 */

package metalsoft.presentacion;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorProveedor;
import metalsoft.negocio.gestores.IBuscador;

/**
 *
 * @author Vicky
 */
public class ABMProveedor_Buscar extends javax.swing.JFrame  implements IBuscador{
    private GestorProveedor gestor=null;
    private ABMProveedor ventana=null;
    private Timer timer;
    /** Creates new form ABMProveedor_Buscar */
    public ABMProveedor_Buscar() {
        initComponents();
    }
    public GestorProveedor getGestor() {
        return gestor;
    }

    public void setGestor(GestorProveedor gestor) {
        this.gestor = gestor;
    }

    public ABMProveedor getVentana() {
        return ventana;
    }

    public void setVentana(ABMProveedor ventana) {
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

        jRadioButton1 = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstLista = new javax.swing.JList();
        btnSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton1.setText("Nombre");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
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

        jScrollPane1.setViewportView(lstLista);

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
            .addGap(0, 294, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(81, 81, 81))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSeleccionar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
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

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jRadioButton1ActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if(txtValor.getText().compareTo("")!=0) {
            final ABMProveedor_Buscar abm=this;
            timer=new Timer();
            timer.schedule(new TimerTask() {
                private HiloBuscarProveedor hiloBuscarProveedor;
                @Override
                public void run() {
                    hiloBuscarProveedor=new HiloBuscarProveedor();
                    hiloBuscarProveedor.setVentana(abm);
                    hiloBuscarProveedor.setValor(txtValor.getText());
                    hiloBuscarProveedor.start();
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
        long id=Long.parseLong(((ItemCombo)lstLista.getSelectedValue()).getId());
        ventana.setIdProveedor(id);
        ventana.proveedorSeleccionado();
}//GEN-LAST:event_btnSeleccionarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMProveedor_Buscar().setVisible(true);
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
    void setVentanaProveedor(ABMProveedor abm) {
        ventana=abm;
    }

    public JList getList(String className) {
        return lstLista;
    }

    public JComboBox getCombo(String className) {
        return null;
    }

    public void setBusqueda(Object[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
