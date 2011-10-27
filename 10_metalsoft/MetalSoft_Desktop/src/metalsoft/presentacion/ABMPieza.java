/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMPieza.java
 *
 * Created on 27/06/2010, 20:09:48
 */
package metalsoft.presentacion;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorPieza;
import metalsoft.util.Combo;
import metalsoft.util.EnumOpcionesABM;

/**
 *
 * @author Vicky
 */
public class ABMPieza extends javax.swing.JDialog {

    private GestorPieza gestorPieza;
    private metalsoft.datos.dbobject.PiezaDB piezaDB;
    private long idPieza = -1;
    private EnumOpcionesABM opcion;

    public long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(long idPieza) {
        this.idPieza = idPieza;
    }

    public JLabel getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(JLabel idpieza) {
        this.idpieza = idpieza;
    }

    public JComboBox getCmbMateriaPrima() {
        return cmbMateriaPrima;
    }

    public void setCmbMateriaPrima(JComboBox cmbMateriaPrima) {
        this.cmbMateriaPrima = cmbMateriaPrima;
    }

    public JComboBox getCmbMatriz() {
        return cmbMatriz;
    }

    public void setCmbMatriz(JComboBox cmbMatriz) {
        this.cmbMatriz = cmbMatriz;
    }

    public JComboBox getCmbTipoMaterial() {
        return cmbTipoMaterial;
    }

    public void setCmbTipoMaterial(JComboBox cmbTipoMaterial) {
        this.cmbTipoMaterial = cmbTipoMaterial;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public metalsoft.datos.dbobject.PiezaDB getPieza() {
        return piezaDB;
    }

    public void setPieza(metalsoft.datos.dbobject.PiezaDB pieza) {
        this.piezaDB = pieza;
    }

    /** Creates new form ABMPieza */
    public ABMPieza() {
        super(Principal.getVtnPrincipal());
        initComponents();
        addListeners();
        gestorPieza = new GestorPieza();
        cargarComboMateriaPrima();
        cargarComboMatriz();
        cargarComboTipoMaterial();
        idpieza.setVisible(false);
        idpieza.setText("");
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
        limpiar();
        setEnableComponents(true);
        Combo.setItemComboSeleccionado(cmbMatriz, -1);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    public void setEnableComponents(boolean b) {
        cmbMateriaPrima.setEnabled(b);
        cmbMatriz.setEnabled(b);
        cmbTipoMaterial.setEnabled(b);
        dimensiones1.getTxtAlto().setEnabled(b);
        dimensiones1.getTxtAncho().setEnabled(b);
        dimensiones1.getTxtLargo().setEnabled(b);
        txtNombre.setEnabled(b);
        idpieza.setEnabled(b);
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (opcion == EnumOpcionesABM.NUEVO) {
            int sol = -1;
            long idTipoMaterial = Long.parseLong(((ItemCombo) cmbTipoMaterial.getSelectedItem()).getId());
            long idMateriaPrima = Long.parseLong(((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId());
            long idMatriz = Long.parseLong(((ItemCombo) cmbMatriz.getSelectedItem()).getId());
            Double alto = Double.parseDouble(dimensiones1.getTxtAlto().getText());
            Double ancho = Double.parseDouble(dimensiones1.getTxtAncho().getText());
            Double largo = Double.parseDouble(dimensiones1.getTxtLargo().getText());
            sol = gestorPieza.guardar(txtNombre.getText(), alto, ancho, largo, idTipoMaterial, idMateriaPrima, idMatriz);
            if (sol > 0) {
                JOptionPane.showMessageDialog(rootPane, "Los datos se guardaron correctamente");
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Los datos NO se pudieron guardar");
            }
        }

        if (opcion == EnumOpcionesABM.MODIFICAR) {
            if (idpieza.getText().compareTo("") != 0) {
                String indexTipoMaterial = ((ItemCombo) cmbTipoMaterial.getSelectedItem()).getId();
                String indexMateriaPrima = ((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId();
                String indexMatriz = ((ItemCombo) cmbMatriz.getSelectedItem()).getId();
                Double alto = Double.parseDouble(dimensiones1.getTxtAlto().getText());
                Double ancho = Double.parseDouble(dimensiones1.getTxtAncho().getText());
                Double largo = Double.parseDouble(dimensiones1.getTxtLargo().getText());
                boolean result = gestorPieza.modificarPieza(Long.parseLong(idpieza.getText()), txtNombre.getText(), alto, ancho, largo, Long.parseLong(indexTipoMaterial), Long.parseLong(indexMateriaPrima), Long.parseLong(indexMatriz));
                if (result == true) {
                    JOptionPane.showMessageDialog(rootPane, "Los datos han sido guardados");
                    botones.getBtnGuardar().setEnabled(false);
                    botones.getBtnModificar().setEnabled(false);
                    botones.getBtnEliminar();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Los datos NO se pudieron guardar");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un pieza primero (buscarla)");
            }
        }

    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // TODO add your handling code here:
            ABMPieza_Buscar frmBuscarPieza = null;
            frmBuscarPieza = (ABMPieza_Buscar) JFrameManager.crearVentana(ABMPieza_Buscar.class.getName());
            frmBuscarPieza.setGestor(gestorPieza);
            frmBuscarPieza.setVentana(this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMPieza.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMPieza.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMPieza.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        opcion = EnumOpcionesABM.MODIFICAR;
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnModificar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(false);
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        opcion = EnumOpcionesABM.ELIMINAR;
        if (idpieza.getText().compareTo("") != 0) {
            boolean result = gestorPieza.eliminarPieza(Long.parseLong(idpieza.getText()));
            if (result == true) {
                JOptionPane.showMessageDialog(rootPane, "Se ha eliminado la pieza");
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(rootPane, "La pieza NO ha podido ser eliminada");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un pieza primero (buscarla)");
        }
        limpiar();
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public void cargarComboMateriaPrima() {
        gestorPieza.obtenerMateriaPrima(cmbMateriaPrima);
    }

    public void cargarComboMatriz() {
        gestorPieza.obtenerMatriz(cmbMatriz);
    }

    public void cargarComboTipoMaterial() {
        gestorPieza.obtenerTipoMaterial(cmbTipoMaterial);
    }

    public void piezaSeleccionada() {
        piezaDB = gestorPieza.buscarPieza(idPieza);
        mostrarDatosProducto();
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        cmbTipoMaterial = new javax.swing.JComboBox();
        cmbMateriaPrima = new javax.swing.JComboBox();
        cmbMatriz = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        dimensiones1 = new metalsoft.beans.Dimensiones();
        idpieza = new javax.swing.JLabel();
        botones = new metalsoft.beans.ABM_Botones();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Pieza");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrar Pieza"));

        jLabel1.setText("Nombre:");

        jLabel7.setText("Tipo de Material:");

        jLabel8.setText("Materia Prima:");

        jLabel11.setText("Matriz:");

        cmbMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMatrizActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dimensiones"));

        idpieza.setText("jLabel2");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(dimensiones1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(4, 4, 4)
                .add(idpieza)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dimensiones1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(idpieza))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel11)
                            .add(jLabel1)
                            .add(jLabel7)
                            .add(jLabel8))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, txtNombre)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, cmbMateriaPrima, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, cmbTipoMaterial, 0, 160, Short.MAX_VALUE))
                            .add(cmbMatriz, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 155, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {cmbMateriaPrima, cmbMatriz, cmbTipoMaterial, txtNombre}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(txtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel7)
                            .add(cmbTipoMaterial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(52, 52, 52)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel8)
                            .add(cmbMateriaPrima, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(cmbMatriz, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 500, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(botones, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jLabel13)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 20, Short.MAX_VALUE)
                .add(botones, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void limpiar() {
        cmbMateriaPrima.setSelectedIndex(0);
        cmbMatriz.setSelectedIndex(0);
        cmbTipoMaterial.setSelectedIndex(0);
        dimensiones1.getTxtAlto().setText("");
        dimensiones1.getTxtAncho().setText("");
        dimensiones1.getTxtLargo().setText("");
        txtNombre.setText("");
        idpieza.setText("");
    }
    private void cmbMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMatrizActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMatrizActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMPieza().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JComboBox cmbMateriaPrima;
    private javax.swing.JComboBox cmbMatriz;
    private javax.swing.JComboBox cmbTipoMaterial;
    private metalsoft.beans.Dimensiones dimensiones1;
    private javax.swing.JLabel idpieza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatosProducto() {
        txtNombre.setText(piezaDB.getNombre());
        dimensiones1.getTxtAlto().setText(String.valueOf(piezaDB.getAlto()));
        dimensiones1.getTxtAncho().setText(String.valueOf(piezaDB.getAncho()));
        dimensiones1.getTxtLargo().setText(String.valueOf(piezaDB.getLargo()));
        idpieza.setText(String.valueOf(piezaDB.getIdpieza()));

        Combo.setItemComboSeleccionado(cmbTipoMaterial, piezaDB.getTipomaterial());
        Combo.setItemComboSeleccionado(cmbMateriaPrima, piezaDB.getMateriaprima());
        Combo.setItemComboSeleccionado(cmbMatriz, piezaDB.getMatriz());

    }
}
