/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AsignarMateriaPrimaAProveedor.java
 *
 * Created on 02/04/2011, 15:47:41
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Proveedorxmateriaprima;
import metalsoft.negocio.gestores.ViewProveedorXMateriaPrima;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Mariana
 */
public class AsignarMateriaPrimaAProveedor extends javax.swing.JDialog {

    private ViewProveedorXMateriaPrima view;

    /** Creates new form AsignarMateriaPrimaAProveedor */
    public AsignarMateriaPrimaAProveedor() {
        super(Principal.getVtnPrincipal());
        initComponents();
        view = new ViewProveedorXMateriaPrima();
        cargarComboMateriaprima();
        cargarComboProveedores();
        addListeners();
    }

    private void addListeners() {
        btnSalir.getBtnSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnGuardar.getBtnGuardar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformed(e);
            }
        });

    }

    private void btnGuardarActionPerformed(ActionEvent e) {
        long idMateriaprima = Long.parseLong(((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId());
        long idProveedor = Long.parseLong(((ItemCombo) cmbProveedor.getSelectedItem()).getId());
        double precio = Double.parseDouble(txtPrecio.getText());
        Proveedorxmateriaprima pxm = new Proveedorxmateriaprima();
        pxm.setPrecio(precio);
        long idMPxP = view.asignarMPProveedor(pxm, idMateriaprima, idProveedor);
        if (idMPxP > 0) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente!");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar...");
        }
    }

    private void cargarComboMateriaprima() {
        cmbMateriaPrima.removeAllItems();
        view.cargarComboMateriaprima(cmbMateriaPrima);
    }

    private void cargarComboProveedores() {
        cmbProveedor.removeAllItems();
        view.cargarComboProveedor(cmbProveedor);
    }

        public void limpiar() {
        cmbMateriaPrima.setSelectedIndex(0);
        cmbProveedor.setSelectedIndex(0);
        txtPrecio.setText("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMateriaPrima = new javax.swing.JLabel();
        lblProveedor = new javax.swing.JLabel();
        cmbMateriaPrima = new javax.swing.JComboBox();
        cmbProveedor = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnSalir = new metalsoft.beans.BtnSalirr();
        btnGuardar = new metalsoft.beans.BtnGuardar();
        jLabel2 = new javax.swing.JLabel();
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
        setTitle("Asignar Materia Prima a Proveedor");

        lblMateriaPrima.setText("Materia Prima:");

        lblProveedor.setText("Proveedor:");

        cmbMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMateriaPrimaActionPerformed(evt);
            }
        });

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Precio:");

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        jLabel2.setText("$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMateriaPrima)
                                .addComponent(lblProveedor))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbProveedor, 0, 328, Short.MAX_VALUE)
                                .addComponent(cmbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(45, 45, 45))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbMateriaPrima, cmbProveedor});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMateriaPrima))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMateriaPrimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMateriaPrimaActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AsignarMateriaPrimaAProveedor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnGuardar btnGuardar;
    private metalsoft.beans.BtnSalirr btnSalir;
    private javax.swing.JComboBox cmbMateriaPrima;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblMateriaPrima;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
