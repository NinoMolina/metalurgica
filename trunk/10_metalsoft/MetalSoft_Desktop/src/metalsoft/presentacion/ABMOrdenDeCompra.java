/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMOrdenDeCompra.java
 *
 * Created on 21/04/2011, 10:05:43
 */
package metalsoft.presentacion;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.negocio.gestores.GestorCompra;
import metalsoft.negocio.gestores.ViewDetalleCompra;
import metalsoft.negocio.gestores.ViewProveedorXMateriaPrima;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Mariana
 */
public class ABMOrdenDeCompra extends javax.swing.JFrame {

    private ViewProveedorXMateriaPrima view;
    private GestorCompra gestor;
    private LinkedList<ViewDetalleCompra> filas = new LinkedList<ViewDetalleCompra>();
    private EnumOpcionesABM opcion;
    private ArrayList<ViewDetalleCompra> view2;
    private ArrayList<ViewDetalleCompra> arlDetMPAEliminar;

    /** Creates new form ABMOrdenDeCompra */
    public ABMOrdenDeCompra() {
        initComponents();
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        view = new ViewProveedorXMateriaPrima();
        cargarComboMateriaprima();
        cargarComboProveedores();
        setEnableComponents(false);
        tblDetalleOrden.setModel(new DetalleOrdenTableModel());
        tblDetalleOrden.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalleOrden.updateUI();
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void setearTablas() {
        //DETALLE PRODUCTO
        tblDetalleOrden.setModel(new DetalleOrdenTableModel());
        tblDetalleOrden.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleOrden.setShowHorizontalLines(false);
        tblDetalleOrden.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleOrden.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void cargarComboMateriaprima() {
        cmbMateriaPrima.removeAllItems();
        view.cargarComboMateriaprima(cmbMateriaPrima);
    }

    private void cargarComboProveedores() {
        cmbProveedor.removeAllItems();
        view.cargarComboProveedor(cmbProveedor);
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        String numero = txtNroOrden.getText();

        /*gestor.setDescripcionProducto(descripcion);
        gestor.setNombreProducto(nombre);
        gestor.setNumeroProducto(numero);
        gestor.setPrecioUnitarioProducto(precioUnitario);
        gestor.setListaDetalle(filas);
         * */

        long result = -1;
        if (opcion == EnumOpcionesABM.NUEVO) {
            //result = gestor.registrarProducto();
        }
        if (opcion == EnumOpcionesABM.MODIFICAR) {
            //gestor.setDetalleAEliminar(arlDetProdAEliminar);
            //gestor.setIdProducto(idProducto);
            //result = gestor.modificarProducto();
        }
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente..!", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar.", "Guardar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addListenerBtnModificar() {
        botones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO
        botones.getBtnModificar().setEnabled(false);
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnModificar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(false);
    }

    private void addListenerBtnBuscar() {
        botones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.BUSCAR;
        ABMOrden_Buscar buscar = null;
        //TODO
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        long idMateriaprima = Long.parseLong(((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId());
        Materiaprima mp = gestor.searchMateriaprimaById(idMateriaprima);
        JTextField txtCant = new JTextField("1");
        Object[] obj = {"Cantidad", txtCant};

        int result = JOptionPane.showConfirmDialog(null, obj, "Ingresar Cantidad", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int cant = Integer.parseInt(txtCant.getText());
            agregarFila(mp.getNombre(), cant);
            tblDetalleOrden.updateUI();
        }

    }

    public void agregarFila(String materiaPrima, int cant) {
            //vector de tipo Object que contiene los datos de una fila
            ViewDetalleCompra datosFila = new ViewDetalleCompra();
            datosFila.setNombreMateriaPrima(materiaPrima);
            datosFila.setCantidad(cant);
            filas.addLast(datosFila);
        }

        public void agregarFila(ViewDetalleCompra v) {
            filas.addLast(v);
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
        txtNroOrden = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbMateriaPrima = new javax.swing.JComboBox();
        btnAgregarPieza = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleOrden = new org.jdesktop.swingx.JXTable();
        btnQuitar = new javax.swing.JButton();
        botones = new metalsoft.beans.ABM_Botones();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Orden de Compra");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Orden de Compra"));
        jPanel1.setName("Orden de Compra"); // NOI18N

        txtNroOrden.setText("...");
        txtNroOrden.setEnabled(false);

        jLabel1.setText("Nro Orden: ");

        jLabel2.setText("Proveedor: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Materia Prima"));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(cmbMateriaPrima, 0, 206, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(btnAgregarPieza, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarPieza))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Orden de Compra"));

        tblDetalleOrden.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblDetalleOrden);
        tblDetalleOrden.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnQuitar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnQuitar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(botones, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
int selectedRow = tblDetalleOrden.getSelectedRow();
        filas.remove(selectedRow);
        if (opcion == EnumOpcionesABM.MODIFICAR) {
            arlDetMPAEliminar.add(view2.get(selectedRow));
        }
        tblDetalleOrden.updateUI();
}//GEN-LAST:event_btnQuitarActionPerformed

    private void btnAgregarPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPiezaActionPerformed
        String materiaPrima = ((ItemCombo) cmbMateriaPrima.getSelectedItem()).toString();
        //Materiaprima mp = gestor.searchMateriaprimaById(idMateriaprima);
        JTextField txtCant = new JTextField("1");
        Object[] obj = {"Cantidad", txtCant};

        int result = JOptionPane.showConfirmDialog(null, obj, "Ingresar Cantidad", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int cant = Integer.parseInt(txtCant.getText());
            agregarFila(materiaPrima, cant);
            //mp.getNombre()
            tblDetalleOrden.updateUI();
        }
    }//GEN-LAST:event_btnAgregarPiezaActionPerformed

    private void setEnableComponents(boolean b) {

        this.tblDetalleOrden.setEnabled(b);
        this.cmbMateriaPrima.setEnabled(b);
        this.cmbProveedor.setEnabled(b);
        this.btnQuitar.setEnabled(b);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMOrdenDeCompra().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JButton btnAgregarPieza;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox cmbMateriaPrima;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable tblDetalleOrden;
    private javax.swing.JTextField txtNroOrden;
    // End of variables declaration//GEN-END:variables
public class DetalleOrdenTableModel extends AbstractTableModel {

        String[] columnNames = {"Nombre", "Cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetalleCompra view = filas.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNombreMateriaPrima();
                case 1:
                    return String.valueOf(view.getCantidad());
                default:
                    return null;
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

