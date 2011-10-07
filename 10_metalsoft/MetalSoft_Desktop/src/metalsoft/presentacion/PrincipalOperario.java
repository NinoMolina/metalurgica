/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PrincipalOperario.java
 *
 * Created on 20/08/2011, 18:23:09
 */
package metalsoft.presentacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import metalsoft.negocio.produccion.CodigoDeBarra;
import metalsoft.util.MetalsoftProperties;

/**
 *
 * @author Nino
 */
public class PrincipalOperario extends javax.swing.JDialog {

    /** Creates new form PrincipalOperario */
    public PrincipalOperario() {
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

        jLabel1 = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEstado = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Codigo de Barras:");

        txtEstado.setColumns(20);
        txtEstado.setEditable(false);
        txtEstado.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtEstado.setRows(5);
        txtEstado.setOpaque(false);
        jScrollPane1.setViewportView(txtEstado);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
                    .addComponent(btnEnviar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnEnviar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        Socket cliente = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            System.out.println("INFO: Conectando con el servidor...");
            
            String codigoBarra = txtCodigoBarras.getText();
            
            String parts[] = codigoBarra.split(Pattern.quote("-"));
            
            String ip = null;
            String puerto = null;
            
            if(parts[1].equals("4")){
                ip = MetalsoftProperties.getProperty(MetalsoftProperties.IP_FIN_PROCESO_CALIDAD);
                puerto = MetalsoftProperties.getProperty(MetalsoftProperties.PUERTO_FIN_PROCESO_CALIDAD);
            } else if(parts[1].equals("2")){
                ip = MetalsoftProperties.getProperty(MetalsoftProperties.IP_FIN_ETAPA);
                puerto = MetalsoftProperties.getProperty(MetalsoftProperties.PUERTO_FIN_ETAPA);
            }

            cliente = new Socket(ip, Integer.parseInt(puerto));

            oos = new ObjectOutputStream(cliente.getOutputStream());
            ois = new ObjectInputStream(cliente.getInputStream());

            System.out.println("INFO: Enviando datos");
            oos.writeObject(txtCodigoBarras.getText());

            System.out.println("INFO: Recibiendo respuesta");
            String respuesta = (String) ois.readObject();

            txtEstado.setText(respuesta);
            System.out.println("INFO: Fin envio de datos");

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (cliente != null) {
                    cliente.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PrincipalOperario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextArea txtEstado;
    // End of variables declaration//GEN-END:variables
}
