/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMMatriz.java
 *
 * Created on 27/06/2010, 18:28:24
 */
package metalsoft.presentacion;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import metalsoft.datos.jpa.entity.Matriz;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorMatriz;

import metalsoft.util.EnumOpcionesABM;

/**
 *
 * @author Mariana
 */
public class ABMMatriz extends javax.swing.JDialog {

    private Matriz matriz;
    private GestorMatriz gestor;
    private EnumOpcionesABM opcion;

    /** Creates new form ABMTipoMaterial */
    /** Creates new form ABMMatriz */
    public ABMMatriz() {
        initComponents();
        addListeners();
        gestor = new GestorMatriz();
        gestor.buscarTipoMaterial(cmbTipoMaterial);
        gestor.buscarMateriaPrima(cmbMateriaPrima);
        setEnableComponents(false);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void addListeners() {
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        addListenerBtnEliminar();
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnEliminar() {
        botones.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnGuardar() {
        botones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnModificar() {
        botones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnBuscar() {
        botones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnSalir() {
        botones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        opcion = EnumOpcionesABM.NUEVO;
        limpiarCampos();
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtNombre.setText("");
        cmbMateriaPrima.setSelectedIndex(0);
        cmbTipoMaterial.setSelectedIndex(0);
    }

    public void setEnableComponents(boolean b) {
        txtCodigo.setEnabled(b);
        txtDescripcion.setEnabled(b);
        txtNombre.setEnabled(b);
        cmbMateriaPrima.setEnabled(b);
        cmbTipoMaterial.setEnabled(b);
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.MODIFICAR;
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnModificar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(false);
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        ABMMatriz_Buscar buscar = null;
        try {
            buscar = (ABMMatriz_Buscar) JFrameManager.crearVentana(ABMMatriz_Buscar.class.getName());
            buscar.setVentanaMatriz(this);
            buscar.setGestor(gestor);
            botones.getBtnModificar().setEnabled(true);
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (opcion == EnumOpcionesABM.NUEVO) {
            try {
                String indexMateriaPrima = ((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId();
                String indexTipoMaterial = ((ItemCombo) cmbTipoMaterial.getSelectedItem()).getId();

                int id = gestor.guardarMatriz(Long.parseLong(txtCodigo.getText()), txtNombre.getText(), txtDescripcion.getText(), Integer.parseInt(indexMateriaPrima), Integer.parseInt(indexTipoMaterial));
                if (id > -1) {
                    JOptionPane.showMessageDialog(this, "Se Guardó la siguiente Matriz: " + txtNombre.getText());
                    botones.getBtnGuardar().setEnabled(false);
                    botones.getBtnModificar().setEnabled(false);
                    botones.getBtnEliminar().setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
            }
            setEnableComponents(false);
        }

        if (opcion == EnumOpcionesABM.MODIFICAR) {
            setEnableComponents(true);
            String indexMateriaPrima = ((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId();
            String indexTipoMaterial = ((ItemCombo) cmbTipoMaterial.getSelectedItem()).getId();
            boolean ok = gestor.modificarMatriz(matriz, Long.parseLong(txtCodigo.getText()), txtNombre.getText(), txtDescripcion.getText(), Integer.parseInt(indexMateriaPrima), Integer.parseInt(indexTipoMaterial));
            if (ok) {
                JOptionPane.showMessageDialog(this, "Modificación Realizada!");
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "La modificación NO se pudo realizar..");
            }
        }



    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.ELIMINAR;

        boolean ok = gestor.eliminarMatriz(matriz);
        setEnableComponents(false);
        limpiarCampos();
        if (ok) {
            JOptionPane.showMessageDialog(this, "Eliminación Realizada");
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "La eliminación NO se pudo realizar..");
        }
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        dispose();
    }

    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(JTextArea txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JComboBox getcmbMateriaPrima() {
        return cmbMateriaPrima;
    }

    public void setcmbMateriaPrima(JComboBox cmbMateriaPrima) {
        this.cmbMateriaPrima = cmbMateriaPrima;
    }

    public JComboBox getcmbTipoMaterial() {
        return cmbTipoMaterial;
    }

    public void setcmbTipoMaterial(JComboBox cmbTipoMaterial) {
        this.cmbTipoMaterial = cmbTipoMaterial;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbMateriaPrima = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoMaterial = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        botones = new metalsoft.beans.ABM_Botones();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Matriz");

        jLabel1.setText("Nombre:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel2.setText("Descripcion:");

        jLabel3.setText("Materia Prima:");

        jLabel4.setText("Tipo Material:");

        jButton1.setText("Ingresar Plano");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Código:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbTipoMaterial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jSeparator1)))
                    .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cmbTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(cmbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMMatriz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JComboBox cmbMateriaPrima;
    private javax.swing.JComboBox cmbTipoMaterial;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
