/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMCondicionIva_Buscar.java
 *
 * Created on 23/08/2011, 23:32:02
 */

package metalsoft.presentacion;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JList;
import javax.swing.JTextField;
import metalsoft.negocio.ventas.CondicionIva;


/**
 *
 * @author Lorreine Prescott
 */
public class ABMCondicionIva_Buscar extends javax.swing.JDialog {

     private static Timer timer;
    private HiloBuscarCondicionIva hiloBuscarCondicionIva;
    private CondicionIva[] ci;
    private ABMCondicionIva ventana;


    /** Creates new form ABMCondicionIva_Buscar */
    public ABMCondicionIva_Buscar() {
        super(Principal.getVtnPrincipal());
        initComponents();
    }

       public CondicionIva[] getCondicionIva() {
        return ci;
    }

    public void setCondicionIva(CondicionIva[] ci) {
        this.ci = ci;
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
        lstCondicion = new javax.swing.JList();
        btnSeleccionar = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscar Condición Iva");

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

        lstCondicion.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstCondicion);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Nombre");

        jRadioButton2.setText("Todos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(17, 17, 17)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
          CondicionIva x=ci[lstCondicion.getSelectedIndex()];
        ventana.setCondicionIva(x);
        ventana.getTxtNombre().setText(x.getNombre());
        ventana.getTxtDescripcion().setText(x.getDescripcion());
        this.dispose();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
              if(txtValor.getText().compareTo("")!=0)
        {
            final ABMCondicionIva_Buscar abm=this;
            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hiloBuscarCondicionIva=new HiloBuscarCondicionIva();
                    hiloBuscarCondicionIva.setVentana(abm);
                    hiloBuscarCondicionIva.setValor(txtValor.getText());
                    hiloBuscarCondicionIva.start();
                }
            }, 1500);
        }
    }//GEN-LAST:event_txtValorKeyReleased

       public JList getLstCondicionIva() {
        return lstCondicion;
    }

    public void setLstCondicionIva(JList lstCondicion) {
        this.lstCondicion = lstCondicion;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(JTextField txtValor) {
        this.txtValor = txtValor;
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMCondicionIva_Buscar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstCondicion;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables


       void setVentanaCondicionIva(ABMCondicionIva aThis) {
        ventana=aThis;
    }
}
