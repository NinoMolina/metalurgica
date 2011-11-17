/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMEmpresaMetalurgica_Buscar.java
 *
 * Created on 10/04/2011, 21:22:12
 */

package metalsoft.presentacion;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.negocio.gestores.GestorEmpresaMetalurgica;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class ABMEmpresaMetalurgica_Buscar extends javax.swing.JDialog implements IBuscador {
    private GestorEmpresaMetalurgica gestor=null;
    private ABMEmpresaMetalurgica ventana=null;
    private Timer timer;
    
    /** Creates new form ABMProveedor_Buscar */
    public ABMEmpresaMetalurgica_Buscar(JDialog owner) {
        super(owner);
        initComponents();
    }
    public GestorEmpresaMetalurgica getGestor() {
        return gestor;
    }

    public void setGestor(GestorEmpresaMetalurgica gestor) {
        this.gestor = gestor;
    }

    public ABMEmpresaMetalurgica getVentana() {
        return ventana;
    }

    public void setVentana(ABMEmpresaMetalurgica ventana) {
        this.ventana = ventana;
    }
    /** Creates new form ABMEmpresaMetalurgica_Buscar */
    

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
        jLabel7 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();

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

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Todos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jRadioButton1)
                .addGap(73, 73, 73)
                .addComponent(jRadioButton2)
                .addContainerGap(292, Short.MAX_VALUE))
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(376, Short.MAX_VALUE)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        long id=Long.parseLong(((ItemCombo)lstLista.getSelectedValue()).getId());
        ventana.setIdEmpresa(id);
        ventana.empresaSeleccionado();
}//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if(txtValor.getText().compareTo("")!=0) {
            final ABMEmpresaMetalurgica_Buscar abm=this;
            timer=new Timer();
            timer.schedule(new TimerTask() {
                private HiloBuscarEmpresaMetalurgica hiloBuscarEmpresa;
                @Override
                public void run() {
                    hiloBuscarEmpresa=new HiloBuscarEmpresaMetalurgica();
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

    void setVentanaEmpresa(ABMEmpresaMetalurgica abm) {
        ventana=abm;
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