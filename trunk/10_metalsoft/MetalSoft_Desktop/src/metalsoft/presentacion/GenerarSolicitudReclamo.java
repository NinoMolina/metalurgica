/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GenerarSolicitudReclamo.java
 *
 * Created on 11/06/2011, 15:47:52
 */
package metalsoft.presentacion;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.negocio.gestores.GestorReclamo;
import metalsoft.negocio.gestores.ViewDetalleReclamo;
import metalsoft.negocio.gestores.ViewProveedorXMateriaPrima;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Mariana
 */
public class GenerarSolicitudReclamo extends javax.swing.JFrame {

    private ViewProveedorXMateriaPrima view;
    private GestorReclamo gestor;
    /** tipo=0 --> Materia Prima // tipo=1 --> Pieza  */
    private int tipo;
    private LinkedList<ViewDetalleReclamo> filas = new LinkedList<ViewDetalleReclamo>();

    /** Creates new form GenerarSolicitudReclamo */
    public GenerarSolicitudReclamo() {
        initComponents();
        addListenerBtnSalir();
        addListenerBtnGuardar();
        view = new ViewProveedorXMateriaPrima();
        gestor = new GestorReclamo();
        disableComponents();
        tblDetalleReclamo.setModel(new DetalleOrdenTableModel());
        tblDetalleReclamo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalleReclamo.updateUI();

    }

    private void setearTablas() {
        //DETALLE PRODUCTO
        tblDetalleReclamo.setModel(new DetalleOrdenTableModel());
        tblDetalleReclamo.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleReclamo.setShowHorizontalLines(false);
        tblDetalleReclamo.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleReclamo.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void cargarComboMateriaprima() {
        cmbArticulo.removeAllItems();
        view.cargarComboMateriaprima(cmbArticulo);
    }

    private void cargarComboProveedores() {
        cmbEntidad.removeAllItems();
        view.cargarComboProveedor(cmbEntidad);
    }

    private void cargarComboPieza() {
        cmbArticulo.removeAllItems();
        gestor.cargarComboPieza(cmbArticulo);
    }

    private void cargarComboEmpresa() {
        cmbEntidad.removeAllItems();
        gestor.cargarComboEmpresa(cmbEntidad);
    }

    private void disableComponents() {
        this.tblDetalleReclamo.setEnabled(false);
        this.cmbArticulo.setEnabled(false);
        this.cmbEntidad.setEnabled(false);
        this.btnAgregarPieza.setEnabled(false);
        this.btnGuardar1.setEnabled(false);
        this.btnQuitar.setEnabled(false);
        this.txtMotivo.setEnabled(false);
    }

    private void enableComponents() {
        this.tblDetalleReclamo.setEnabled(true);
        this.cmbArticulo.setEnabled(true);
        this.cmbEntidad.setEnabled(true);
        this.btnAgregarPieza.setEnabled(true);
        this.btnGuardar1.setEnabled(true);
        this.btnQuitar.setEnabled(true);
        this.txtMotivo.setEnabled(true);
    }

    private void addListenerBtnSalir() {
        this.btnSalirr1.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnGuardar() {
        this.btnGuardar1.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        boolean validar = this.validarEntidad();
        if (!validar) {
            return;
        }
        String motivo = txtMotivo.getText();

        if (tipo == 0) {

            long idProv = Long.parseLong(((ItemCombo) cmbEntidad.getSelectedItem()).getId());
            Proveedor Prov = searchProveedorById(idProv);
            gestor.setProv(Prov);
        }

        if (tipo == 1) {

            long idEmpMet = Long.parseLong(((ItemCombo) cmbEntidad.getSelectedItem()).getId());
            Empresametalurgica empMet = searchEmpresaById(idEmpMet);
            gestor.setEmpMet(empMet);
        }
        gestor.setMotivo(motivo);
        gestor.setListaDetalle(filas);

        boolean result = false;
        result = gestor.registrarReclamo(tipo);

        if (result) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente..!", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            disableComponents();
        } else {
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar.", "Guardar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Proveedor searchProveedorById(long id) {
        for (Proveedor p : gestor.getProveedores()) {
            if (p.getIdproveedor() == id) {
                return p;
            }
        }
        return null;
    }


    private Empresametalurgica searchEmpresaById(long idEmpMet) {
         for (Empresametalurgica e : gestor.getEmpresa()) {
            if (e.getIdempresametalurgica() == idEmpMet) {
                return e;
            }
        }
        return null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbMateriaPrima = new javax.swing.JRadioButton();
        rbPieza = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbEntidad = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleReclamo = new org.jdesktop.swingx.JXTable();
        btnQuitar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbArticulo = new javax.swing.JComboBox();
        btnAgregarPieza = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        btnGuardar1 = new metalsoft.beans.BtnGuardar();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccion Entidad"));

        buttonGroup1.add(rbMateriaPrima);
        rbMateriaPrima.setText("Materia Prima");
        rbMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMateriaPrimaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbPieza);
        rbPieza.setText("Pieza");
        rbPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPiezaActionPerformed(evt);
            }
        });

        jLabel1.setText("Seleccione el articulo que desea reclamoar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(rbMateriaPrima)
                        .addGap(54, 54, 54)
                        .addComponent(rbPieza))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMateriaPrima)
                    .addComponent(rbPieza))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Entidad:");

        cmbEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEntidadActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Reclamo"));

        tblDetalleReclamo.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblDetalleReclamo);

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnQuitar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnQuitar))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Articulo"));

        btnAgregarPieza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregarPieza.setText("Agregar");
        btnAgregarPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPiezaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cmbArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(btnAgregarPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarPieza)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel2.setText("Motivo:");

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        jScrollPane2.setViewportView(txtMotivo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEntidad, 0, 191, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMateriaPrimaActionPerformed
        cargarComboMateriaprima();
        cargarComboProveedores();
        enableComponents();
        limpiarTabla();
        this.txtMotivo.setText("");
        tipo = 0;
    }//GEN-LAST:event_rbMateriaPrimaActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int selectedRow = tblDetalleReclamo.getSelectedRow();
        filas.remove(selectedRow);
        tblDetalleReclamo.updateUI();
}//GEN-LAST:event_btnQuitarActionPerformed

    private void btnAgregarPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPiezaActionPerformed
        agregarDetalleReclamo();
}//GEN-LAST:event_btnAgregarPiezaActionPerformed

    private void rbPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPiezaActionPerformed
        cargarComboPieza();
        cargarComboEmpresa();
        enableComponents();
        limpiarTabla();
        this.txtMotivo.setText("");
        tipo = 1;
    }//GEN-LAST:event_rbPiezaActionPerformed

    private void cmbEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEntidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEntidadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GenerarSolicitudReclamo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPieza;
    private metalsoft.beans.BtnGuardar btnGuardar1;
    private javax.swing.JButton btnQuitar;
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbArticulo;
    private javax.swing.JComboBox cmbEntidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbMateriaPrima;
    private javax.swing.JRadioButton rbPieza;
    private org.jdesktop.swingx.JXTable tblDetalleReclamo;
    private javax.swing.JTextArea txtMotivo;
    // End of variables declaration//GEN-END:variables

    private void agregarDetalleReclamo() {
        if (tipo == 0) {

            long idMateriaprima = Long.parseLong(((ItemCombo) this.cmbArticulo.getSelectedItem()).getId());
            String nombreMateriaPrima = (((ItemCombo) cmbArticulo.getSelectedItem()).toString());
            JTextField txtCant = new JTextField("1");
            Object[] obj = {"Cantidad", txtCant};

            int result = JOptionPane.showConfirmDialog(null, obj, "Ingresar Cantidad", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                int cant = Integer.parseInt(txtCant.getText());
                agregarFila(tipo, idMateriaprima, nombreMateriaPrima, cant);
                tblDetalleReclamo.updateUI();
            }
        } else {
            long idPieza = Long.parseLong(((ItemCombo) this.cmbArticulo.getSelectedItem()).getId());
            String nombrePieza = (((ItemCombo) cmbArticulo.getSelectedItem()).toString());
            JTextField txtCant = new JTextField("1");
            Object[] obj = {"Cantidad", txtCant};

            int result = JOptionPane.showConfirmDialog(null, obj, "Ingresar Cantidad", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                int cant = Integer.parseInt(txtCant.getText());
                agregarFila(tipo, idPieza, nombrePieza, cant);
                tblDetalleReclamo.updateUI();
            }
        }
    }

    public void agregarFila(int tipo, long idArticulo, String nombreArticulo, int cant) {
        //vector de tipo Object que contiene los datos de una fila
        ViewDetalleReclamo datosFila = new ViewDetalleReclamo();
        if (tipo == 0) {
            datosFila.setNombreMateriaPrima(nombreArticulo);
            datosFila.setIdMateriaPrima(idArticulo);
            datosFila.setCantidad(cant);
        } else {
            datosFila.setNombrePieza(nombreArticulo);
            datosFila.setIdPieza(idArticulo);
            datosFila.setCantidad(cant);
        }
        filas.addLast(datosFila);
    }

    private void limpiarTabla() {
        int cantidadFilas = tblDetalleReclamo.getRowCount();
        for (int i = 0; i < cantidadFilas; i++) {
            filas.remove(0);
            tblDetalleReclamo.updateUI();
        }
    }

    public boolean validarEntidad() {
        String entidad = cmbEntidad.getSelectedItem().toString();
        if (entidad == ("--Seleccionar--")) {
            JOptionPane.showMessageDialog(this, "Debe Ingresar una Entidad", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }


    public class DetalleOrdenTableModel extends AbstractTableModel {

        String[] columnNames = {"Nombre", "Cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetalleReclamo view = filas.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            if (tipo == 0) {
                switch (columnIndex) {
                    case 0:
                        return view.getNombreMateriaPrima();
                    case 1:
                        return String.valueOf(view.getCantidad());
                    default:
                        return null;
                }
            } else {
                switch (columnIndex) {
                    case 0:
                        return view.getNombrePieza();
                    case 1:
                        return String.valueOf(view.getCantidad());
                    default:
                        return null;
                }
            }


        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filas != null) {
                return filas.size();
            }
            return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];

        }
    }
}