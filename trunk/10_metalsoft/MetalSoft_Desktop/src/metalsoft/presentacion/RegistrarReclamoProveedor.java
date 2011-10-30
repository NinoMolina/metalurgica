/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarReclamoProveedor.java
 *
 * Created on 30/08/2011, 18:59:57
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.DetallereclamoproveedorDB;
import metalsoft.datos.dbobject.EmpresametalurgicaDB;
import metalsoft.datos.dbobject.ProveedorDB;
import metalsoft.datos.dbobject.ReclamoproveedorDB;
import metalsoft.datos.exception.DetallereclamoproveedorException;
import metalsoft.negocio.gestores.GestorReclamo;
import metalsoft.negocio.gestores.ViewDetalleReclamo;
import metalsoft.negocio.gestores.ViewProveedorXMateriaPrima;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Mariana
 */
public class RegistrarReclamoProveedor extends javax.swing.JDialog {

    private GestorReclamo gestor;
    private ViewProveedorXMateriaPrima view;
    private LinkedList<ViewDetalleReclamo> filas = new LinkedList<ViewDetalleReclamo>();
    private long idreclamo;

    /** Creates new form RegistrarReclamoProveedor */
    public RegistrarReclamoProveedor() {
        super(Principal.getVtnPrincipal());
        initComponents();
        this.cmbProveedor.removeAllItems();
        this.cmbReclamo.removeAllItems();
        addListenerBtnSalir();
        addListenerBtnGuardar();
        view = new ViewProveedorXMateriaPrima();
        gestor = new GestorReclamo();
        cargarComboProveedores();
        this.tblDetalleReclamo.setEnabled(false);
        tblDetalleReclamo.setModel(new DetalleReclamoTableModel());
        tblDetalleReclamo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalleReclamo.updateUI();
        this.txtMotivo.setEnabled(false);
        txtMotivo.setText("");
    }

    private void cargarComboProveedores() {
        cmbProveedor.removeAllItems();
        view.cargarComboProveedor(cmbProveedor);
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
            boolean result = false;
            result = gestor.modificarReclamo(idreclamo, motivo);
            this.txtMotivo.setEnabled(false);
            this.txtMotivo.setText("");
            this.cmbProveedor.setSelectedIndex(0);
            if (result) {
                JOptionPane.showMessageDialog(this, "El reclamo fue enviado al Proveedor!", "Envio", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El reclamo no pudo ser enviado al Proveedor", "Envio", JOptionPane.ERROR_MESSAGE);
            }
    }

    private ProveedorDB searchProveedorById(long id) {
        ProveedorDB proveedor = gestor.getProveedorById(id);
        return proveedor;
    }

    public boolean validarEntidad() {
        String prove = cmbProveedor.getSelectedItem().toString();


        if (prove == ("--Seleccionar--")) {
            JOptionPane.showMessageDialog(this, "Debe Ingresar un Proveedor", "Atención", JOptionPane.INFORMATION_MESSAGE);

            return false;
        }
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbProveedor = new javax.swing.JComboBox();
        lblEntidad = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbReclamo = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleReclamo = new org.jdesktop.swingx.JXTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        btnGuardar1 = new metalsoft.beans.BtnGuardar();
        jLabel25 = new javax.swing.JLabel(){

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
        setTitle("Enviar Reclamo Proveedor");

        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });

        lblEntidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEntidad.setText("Proveedor:");

        jLabel5.setText("Reclamo:");

        cmbReclamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReclamoActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Reclamo"));

        tblDetalleReclamo.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblDetalleReclamo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setText("Observaciones:");

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        jScrollPane2.setViewportView(txtMotivo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblEntidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbReclamo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbProveedor, cmbReclamo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEntidad)
                    .addComponent(cmbReclamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
        limpiarTabla();
        txtMotivo.setText("");
        this.txtMotivo.setEnabled(false);
        this.cmbReclamo.removeAllItems();
        this.cmbReclamo.setEnabled(true);
        if (this.cmbProveedor.getSelectedIndex() != 0 && this.cmbProveedor.getSelectedIndex() != -1) {
            Object prov = this.cmbProveedor.getSelectedItem();
            String nombreProveedor = prov.toString();
            long idProveedor = this.gestor.getIdProveedorByName(nombreProveedor);
            gestor.cargarComboReclamoProveedor(cmbReclamo, idProveedor);
        }
}//GEN-LAST:event_cmbProveedorActionPerformed

    private void cmbReclamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReclamoActionPerformed
        if (this.cmbReclamo.getSelectedIndex() != 0 && this.cmbReclamo.getSelectedIndex() != -1) {
            this.txtMotivo.setEnabled(true);
            Object reclamo = this.cmbReclamo.getSelectedItem();
            long nroReclamo = Long.parseLong(reclamo.toString());
            ReclamoproveedorDB rp = gestor.getReclamoByNroReclamo(nroReclamo);
            idreclamo = rp.getIdreclamo();
            try {
                DetallereclamoproveedorDB[] detalle = this.gestor.getDetalleByIdReclamo(rp.getIdreclamo());
                for (int i = 0; i < detalle.length; i++) {
                    String nombreMateriaPrima = detalle[i].getDescripcion();
                    int cantidad = detalle[i].getCantidad();
                    long idDetalle = detalle[i].getIddetalle();
                    String motivo = detalle[i].getMotivo();
                    agregarFila(nombreMateriaPrima, cantidad, idDetalle, motivo);
                }
            } catch (DetallereclamoproveedorException ex) {
                Logger.getLogger(GenerarSolicitudReclamo.class.getName()).log(Level.SEVERE, null, ex);
            }
            tblDetalleReclamo.updateUI();
            cmbReclamo.setEnabled(false);
            txtMotivo.setText("");
    }//GEN-LAST:event_cmbReclamoActionPerformed
    }
    public void agregarFila(String nombreArticulo, int cant, long idDetalle, String motivo) {
        //vector de tipo Object que contiene los datos de una fila
        ViewDetalleReclamo datosFila = new ViewDetalleReclamo();

        datosFila.setNombreMateriaPrima(nombreArticulo);
        datosFila.setCantidad(cant);
        datosFila.setMotivo(motivo);
        datosFila.setIdDetalle(idDetalle);
        

        filas.addLast(datosFila);
    }

    private void limpiarTabla() {
        int cantidadFilas = tblDetalleReclamo.getRowCount();


        for (int i = 0; i
                < cantidadFilas; i++) {
            filas.remove(0);
            tblDetalleReclamo.updateUI();


        }
    }

    private void setearTablas() {
        //DETALLE Reclamo
        tblDetalleReclamo.setModel(new DetalleReclamoTableModel());
        tblDetalleReclamo.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleReclamo.setShowHorizontalLines(false);
        tblDetalleReclamo.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleReclamo.setHighlighters(new UIColorHighlighter(HighlightPredicate.ODD));
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarReclamoProveedor().setVisible(true);


            }
        });


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnGuardar btnGuardar1;
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbReclamo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEntidad;
    private org.jdesktop.swingx.JXTable tblDetalleReclamo;
    private javax.swing.JTextArea txtMotivo;
    // End of variables declaration//GEN-END:variables

    public class DetalleReclamoTableModel extends AbstractTableModel {

        String[] columnNames = {"Nombre", "Cantidad", "Motivo"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetalleReclamo view = filas.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return view.getNombreMateriaPrima();
                case 1:
                    return String.valueOf(view.getCantidad());
                case 2:
                    return String.valueOf(view.getMotivo());
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
