/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMCondicionIva.java
 *
 * Created on 23/08/2011, 23:31:30
 */

package metalsoft.presentacion;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import metalsoft.negocio.gestores.*;
import metalsoft.negocio.ventas.CondicionIva;
import metalsoft.presentacion.lookandfeel.LookAndFeelManager;
import metalsoft.presentacion.lookandfeel.Skins;

/**
 *
 * @author Lorreine Prescott
 */
public class ABMCondicionIva extends javax.swing.JDialog {

    private CondicionIva condicion;

    /** Creates new form ABMCondicionIva */
    public ABMCondicionIva() {
        initComponents();
        addListeners();
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
    
     private void addListenerBtnEliminar() {
        botones.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnEliminarActionPerformed(e);
            }
        });
    }

    private void btnEliminarActionPerformed(ActionEvent e) {
        // TODO add your handling code here:
        GestorCondicionIva gestor = new GestorCondicionIva();
        boolean ok = gestor.eliminarCondicionIva(condicion);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Eliminación Realizada");
        } else {
            JOptionPane.showMessageDialog(this, "La eliminación NO se pudo realizar..");
        }
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        GestorCondicionIva gestor = new GestorCondicionIva();

        boolean ok = gestor.modificarCondicionIva(condicion, txtNombre.getText(), txtDescripcion.getText());
        if (ok) {
            JOptionPane.showMessageDialog(this, "Modificación Realizada!");
            setEnableComponents(true);
            botones.getBtnGuardar().setEnabled(true);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "La modificación NO se pudo realizar..");
        }

    }

    private void addListenerBtnBuscar() {
        botones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        ABMCondicionIva_Buscar buscar = null;
        try {
            buscar = (ABMCondicionIva_Buscar) JFrameManager.crearVentana(ABMCondicionIva_Buscar.class.getName());
            buscar.setVentanaCondicionIva(this);
            botones.getBtnModificar().setEnabled(true);
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addListenerBtnSalir() {
        botones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        GestorCondicionIva gestor = new GestorCondicionIva();
        long id = gestor.guardarCondicionIva(txtNombre.getText(), txtDescripcion.getText());
        if (id > -1) {
            JOptionPane.showMessageDialog(this, "Se guardó la siguiente Condición Iva: " + txtNombre.getText());
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
            setEnableComponents(false);
        } else {
            JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
        }
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

    public CondicionIva getCondicionIva() {
        return condicion;
    }

    public void setCondicionIva(CondicionIva condicion) {
        this.condicion = condicion;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new metalsoft.beans.ABM_Botones();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Condición Iva");

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    try {
                    LookAndFeelManager.setLookAndFeel(Skins.WINDOWS);
                    JFrameManager.crearVentana(ABMCondicionIva.class.getName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ABMCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(ABMCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ABMCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void setEnableComponents(boolean b) {
        this.txtDescripcion.setEnabled(b);
        this.txtNombre.setEnabled(b);
    }
}
