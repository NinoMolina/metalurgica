/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMOrden_Buscar.java
 *
 * Created on 25/05/2011, 09:30:50
 */

package metalsoft.presentacion;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.CompraDB;
import metalsoft.negocio.gestores.GestorCompra;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.util.ItemCombo;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metalsoft.negocio.access.AccessCompra;

/**
 *
 * @author Mariana
 */
public class ABMOrden_Buscar extends javax.swing.JDialog implements IBuscador{
    private static Timer timer;
    private ABMOrdenDeCompra ventana;
    private GestorCompra gestor;
    private RegistrarIngresoMateriaPrima ventana2;
   
    /** Creates new form ABMOrden_Buscar */
    public ABMOrden_Buscar() {
        super(Principal.getVtnPrincipal());
        initComponents();
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
        btnSeleccionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese el Numero de Orden de Compra a buscar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccionar))
                    .addComponent(jLabel1))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void setGestor(GestorCompra gestor) {
        this.gestor=gestor;
    }

    public GestorCompra getGestor()
    {
        return gestor;
    }


    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        String valor = txtValor.getText();
        if (valor != "")
        {
            PostgreSQLManager pg=null;
            Connection cn=null;
            pg=new PostgreSQLManager();
            try {
                cn = pg.concectGetCn();
            } catch (Exception ex) {
                Logger.getLogger(HiloBuscarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            CompraDB[] compraDB=AccessCompra.findByNumeroILIKE(valor,cn);
            if (compraDB.length==0)
                JOptionPane.showMessageDialog(this, "No se encontro ninguna compra con ese numero", "Atención", JOptionPane.INFORMATION_MESSAGE);
            else
            {
                ventana.setIdOrden(compraDB[0].getIdcompra());
                ventana.ordenSeleccionada();
                this.dispose();
            }
        }
}//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(JTextField txtValor) {
        this.txtValor = txtValor;
}//GEN-LAST:event_txtValorKeyReleased

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtValorActionPerformed

    public void setVentana(ABMOrdenDeCompra v) {
        ventana=v;
    }

    public void setVentana2(RegistrarIngresoMateriaPrima v2) {
        ventana2=v2;
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMOrden_Buscar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    
   public JComboBox getCombo(String className) {
        return null;
    }

    public void setBusqueda(Object[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JList getList(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
