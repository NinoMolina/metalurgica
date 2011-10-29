/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMTipoDocumento_Buscar.java
 *
 * Created on 29/08/2011, 11:27:16
 */

package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.negocio.rrhh.TipoDocumento;


/**
 *
 * @author Lorreine Prescott
 */
public class ABMTipoDocumento_Buscar extends javax.swing.JDialog {

     private static Timer timer;
    private HiloBuscarTipoDocumento hiloBuscarTipoDocumento;
    private TipoDocumento[] td;
    private ABMTipoDocumento ventana;

    /** Creates new form ABMTipoDocumento_Buscar */
    public ABMTipoDocumento_Buscar(JDialog owner) {
        super(owner);
        initComponents();
    }


      public TipoDocumento[] getTipoDocumento() {
        return td;
    }

    public void setTipoDocumento(TipoDocumento[] td) {
        this.td = td;
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
        jRadioButton2 = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTipoDoc = new javax.swing.JList();
        btnSeleccionar = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Tipo de Documento");

        jRadioButton1.setText("Tipo");

        jRadioButton2.setText("Todos");

        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(lstTipoDoc);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, txtValor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jRadioButton1)
                        .add(152, 152, 152)
                        .add(jRadioButton2))
                    .add(btnSeleccionar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                .add(23, 23, 23))
            .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 28, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButton1)
                    .add(jRadioButton2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(txtValor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(btnSeleccionar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
         TipoDocumento x=td[lstTipoDoc.getSelectedIndex()];
        ventana.setTipoDocumento(x);
        ventana.getTxtTipo().setText(x.getTipo());
        ventana.getTxtNombre().setText(x.getNombre());
        this.dispose();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
           if(txtValor.getText().compareTo("")!=0)
        {
            final ABMTipoDocumento_Buscar abm=this;
            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hiloBuscarTipoDocumento=new HiloBuscarTipoDocumento();
                    hiloBuscarTipoDocumento.setVentana(abm);
                    hiloBuscarTipoDocumento.setValor(txtValor.getText());
                    hiloBuscarTipoDocumento.start();
                }
            }, 1500);
        }
    }//GEN-LAST:event_txtValorKeyReleased

     public JList getLstTipoDocumento() {
        return lstTipoDoc;
    }

    public void setLstTipoDocumento(JList lstTipoDoc) {
        this.lstTipoDoc = lstTipoDoc;
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
    private javax.swing.JList lstTipoDoc;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables


     void setVentanaTipoDocumento(ABMTipoDocumento aThis) {
        ventana=aThis;
    }
}
