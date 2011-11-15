/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMEmpresaMantenimiento_Buscar.java
 *
 * Created on 03/07/2011, 18:37:55
 */
package metalsoft.presentacion;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.negocio.gestores.GestorEmpresaMantenimiento;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class ABMEmpresaMantenimiento_Buscar extends javax.swing.JDialog {

    private GestorEmpresaMantenimiento gestor = null;
    private ABMEmpresaMantenimiento ventana = null;
    private Timer timer;
    private List<Proveedormantenimientomaquina> prov;

    /** Creates new form ABMProveedor_Buscar */
    public ABMEmpresaMantenimiento_Buscar(JDialog owner) {
        super(owner);
        initComponents();
    }

    public List<Proveedormantenimientomaquina> getProveedormantenimientomaquina() {
        return prov;
    }

    public void setProveedormantenimientomaquina(List<Proveedormantenimientomaquina> m) {
        this.prov = m;
    }

    public GestorEmpresaMantenimiento getGestor() {
        return gestor;
    }

    public void setGestor(GestorEmpresaMantenimiento gestor) {
        this.gestor = gestor;
    }

    public ABMEmpresaMantenimiento getVentana() {
        return ventana;
    }

    public void setVentana(ABMEmpresaMantenimiento ventana) {
        this.ventana = ventana;
    }

    /** Creates new form ABMEmpresaMantenimiento_Buscar */
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        btnSeleccionar = new javax.swing.JButton();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstLista = new javax.swing.JList();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Empresa");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Nombre");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
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

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Todos");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(80, 80, 80)
                        .addComponent(jRadioButton2))))
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(7, 7, 7)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        Proveedormantenimientomaquina x =prov.get(lstLista.getSelectedIndex());
        ventana.setIdEmpresa(x.getIdproveedormantenimiento());
        ventana.empresaSeleccionado();
}//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if (txtValor.getText().compareTo("") != 0) {
            final ABMEmpresaMantenimiento_Buscar abm = this;
            timer = new Timer();
            timer.schedule(new TimerTask() {

                private HiloBuscarEmpresaMantenimiento hiloBuscarEmpresa;

                @Override
                public void run() {
                    hiloBuscarEmpresa = new HiloBuscarEmpresaMantenimiento();
                    hiloBuscarEmpresa.setVentana(abm);
                    hiloBuscarEmpresa.setValor(txtValor.getText());
                    hiloBuscarEmpresa.start();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstLista;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}