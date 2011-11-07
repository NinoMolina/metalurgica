/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMServicioMaquina_Buscar.java
 *
 * Created on 15/05/2011, 17:05:06
 */

package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.negocio.mantenimiento.ServicioMaquina;

/**
 *
 * @author Lorreine Prescott
 */
public class ABMServicioMaquina_Buscar extends javax.swing.JDialog {

      private static Timer timer;
    private HiloBuscarServicioMaquina hiloBuscarServicioMaquina;
    private ServicioMaquina[] sm;
    private ABMServicioDeMaquina ventana;

    /** Creates new form ABMRotura_Buscar */
     public ABMServicioMaquina_Buscar(JDialog owner) {
        super(owner);
        initComponents();
    }

    public ServicioMaquina[] getServicioMaquina() {
        return sm;
    }

    public void setServicioMaquina(ServicioMaquina[] sm) {
        this.sm = sm;
    }


    /** Creates new form ABMServicioMaquina_Buscar */
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstRotura = new javax.swing.JTextArea();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstServicio = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel(){

            @Override
            public void paint(Graphics g) {
                try {
                    g.drawImage(ImageIO.read(getClass().getResource("/img/fondopantallas2.png")), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                }
                super.paint(g);
            }
        }
        ;

        lstRotura.setColumns(20);
        lstRotura.setRows(5);
        jScrollPane1.setViewportView(lstRotura);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Servicio de Máquina");

        jRadioButton1.setText("Nombre");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Todos");

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

        lstServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lstServicioKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(lstServicio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addComponent(jRadioButton2)
                        .addGap(228, 228, 228))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
          ServicioMaquina x=sm[lstServicio.getSelectedIndex()];
        ventana.setServicioMaquina(x);
        ventana.getTxtNombre().setText(x.getNombre());
        ventana.getTxtDescripcion().setText(x.getDescripcion());
        this.dispose();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void lstServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstServicioKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_lstServicioKeyReleased

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        // TODO add your handling code here:

          if(txtValor.getText().compareTo("")!=0)
        {
            final ABMServicioMaquina_Buscar abm=this;
            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hiloBuscarServicioMaquina=new HiloBuscarServicioMaquina();
                    hiloBuscarServicioMaquina.setVentana(abm);
                    hiloBuscarServicioMaquina.setValor(txtValor.getText());
                    hiloBuscarServicioMaquina.start();
                }
            }, 1500);
        }
    }//GEN-LAST:event_txtValorKeyReleased

     public JList getLstServicioMaquina() {
        return lstServicio;
    }

    public void setLstServicioMaquina(JList lstServicio) {
        this.lstServicio = lstServicio;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(JTextField txtValor) {
        this.txtValor = txtValor;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea lstRotura;
    private javax.swing.JList lstServicio;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

       void setVentanaServicioMaquina(ABMServicioDeMaquina aThis) {
        ventana=aThis;
    }


}
