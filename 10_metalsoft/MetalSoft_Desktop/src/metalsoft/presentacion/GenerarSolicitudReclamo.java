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

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.CompraDB;
import metalsoft.datos.dbobject.DetallecompraDB;
import metalsoft.datos.dbobject.DetalletrabajotercerizadoDB;
import metalsoft.datos.dbobject.EmpresametalurgicaDB;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.ProveedorDB;
import metalsoft.datos.exception.DetallecompraException;
import metalsoft.datos.exception.DetalletrabajotercerizadoException;
import metalsoft.datos.jpa.entity.Compra;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.negocio.gestores.GestorReclamo;
import metalsoft.negocio.gestores.ViewDetalleReclamo;
import metalsoft.negocio.gestores.ViewProveedorXMateriaPrima;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.negocio.gestores.ViewDetalleCompra;
import metalsoft.negocio.gestores.ViewDetalleTrabajoTercerizado;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Mariana
 */
public class GenerarSolicitudReclamo extends javax.swing.JDialog {

    private ViewProveedorXMateriaPrima view;
    private GestorReclamo gestor;
    /** tipo=0 --> Materia Prima // tipo=1 --> Pieza  */
    private int tipo;
    private LinkedList<ViewDetalleReclamo> filas = new LinkedList<ViewDetalleReclamo>();
    private LinkedList<ViewDetalleCompra> filasCompra = new LinkedList<ViewDetalleCompra>();
    private LinkedList<ViewDetalleTrabajoTercerizado> filasTrabajo = new LinkedList<ViewDetalleTrabajoTercerizado>();
    long idComp;
    long idTrabajo;

    /** Creates new form GenerarSolicitudReclamo */
    public GenerarSolicitudReclamo() {
        super(Principal.getVtnPrincipal());
        initComponents();
        addListenerBtnSalir();
        addListenerBtnGuardar();
        view = new ViewProveedorXMateriaPrima();
        gestor = new GestorReclamo();
        disableComponents();
        tblDetalleReclamo.setModel(new DetalleReclamoTableModel());
        tblDetalleReclamo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalleReclamo.updateUI();
        tblDetalleReclamo.packAll();
        tblDetalleTransaccion.setModel(new DetalleTransaccionTableModel());
        tblDetalleTransaccion.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalleTransaccion.updateUI();
        tblDetalleTransaccion.packAll();

    }

    private void setearTablas() {
        //DETALLE Reclamo
        tblDetalleReclamo.setModel(new DetalleReclamoTableModel());
        tblDetalleReclamo.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleReclamo.setShowHorizontalLines(false);
        tblDetalleReclamo.setShowVerticalLines(false);
        tblDetalleReclamo.setHorizontalScrollEnabled(true); 
        /* On dit de surligner une ligne sur deux */
        tblDetalleReclamo.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        //DETALLE Transaccion
        tblDetalleTransaccion.setModel(new DetalleTransaccionTableModel());
        tblDetalleTransaccion.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleTransaccion.setShowHorizontalLines(false);
        tblDetalleTransaccion.setShowVerticalLines(false);
        tblDetalleTransaccion.setHorizontalScrollEnabled(true); 
        /* On dit de surligner une ligne sur deux */
        tblDetalleTransaccion.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void cargarComboProveedores() {
        cmbEntidad.removeAllItems();
        view.cargarComboProveedor(cmbEntidad);
    }

    private void cargarComboEmpresa() {
        cmbEntidad.removeAllItems();
        gestor.cargarComboEmpresa(cmbEntidad);
    }

    private void disableComponents() {
        this.tblDetalleReclamo.setEnabled(false);
        this.cmbTransaccion.setEnabled(false);
        this.cmbEntidad.setEnabled(false);
        this.btnAgregarPieza.setEnabled(false);
        this.btnGuardar1.setEnabled(false);
        this.btnQuitar.setEnabled(false);
        this.txtMotivo.setEnabled(false);
        this.tblDetalleTransaccion.setEnabled(false);
    }

    private void enableComponents() {
        this.tblDetalleReclamo.setEnabled(true);
        this.cmbTransaccion.setEnabled(true);
        this.cmbEntidad.setEnabled(true);
        this.btnAgregarPieza.setEnabled(true);
        this.btnGuardar1.setEnabled(true);
        this.btnQuitar.setEnabled(true);
        this.txtMotivo.setEnabled(true);
        this.tblDetalleTransaccion.setEnabled(true);
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
            ProveedorDB prov = searchProveedorById(idProv);
            gestor.setProv(prov);
            gestor.setIdTransaccion(idComp);
        }

        if (tipo == 1) {

            long idEmpMet = Long.parseLong(((ItemCombo) cmbEntidad.getSelectedItem()).getId());
            EmpresametalurgicaDB empMet = searchEmpresaById(idEmpMet);
            gestor.setEmpMet(empMet);
            gestor.setIdTransaccion(idTrabajo);
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

    private ProveedorDB searchProveedorById(long id) {
        ProveedorDB proveedor= gestor.getProveedorById(id);
           return proveedor;
    }

    private EmpresametalurgicaDB searchEmpresaById(long idEmpMet) {
        EmpresametalurgicaDB emp =gestor.getEmpresaById(idEmpMet);
                return emp;
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleReclamo = new org.jdesktop.swingx.JXTable();
        btnQuitar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAgregarPieza = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalleTransaccion = new org.jdesktop.swingx.JXTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        btnGuardar1 = new metalsoft.beans.BtnGuardar();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        jLabel5 = new javax.swing.JLabel();
        cmbTransaccion = new javax.swing.JComboBox();
        lblEntidad = new javax.swing.JLabel();
        cmbEntidad = new javax.swing.JComboBox();
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

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar Solicitud de Reclamo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección Entidad"));

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

        jLabel1.setText("Seleccione el artículo que desea reclamar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(rbMateriaPrima)
                .addGap(76, 76, 76)
                .addComponent(rbPieza)
                .addGap(153, 153, 153))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMateriaPrima)
                    .addComponent(jLabel1)
                    .addComponent(rbPieza))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Transacción"));

        btnAgregarPieza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregarPieza.setText("Agregar");
        btnAgregarPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPiezaActionPerformed(evt);
            }
        });

        tblDetalleTransaccion.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(tblDetalleTransaccion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addComponent(btnAgregarPieza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarPieza)
                .addGap(11, 11, 11))
        );

        jLabel2.setText("Observaciones:");

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        jScrollPane2.setViewportView(txtMotivo);

        jLabel5.setText("Nro. de Transacción:");

        cmbTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTransaccionActionPerformed(evt);
            }
        });

        lblEntidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEntidad.setText("Entidad:");

        cmbEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEntidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 536, Short.MAX_VALUE)
                        .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEntidad)
                    .addComponent(jLabel5)
                    .addComponent(cmbTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMateriaPrimaActionPerformed
        this.cmbTransaccion.removeAllItems();
        this.cmbEntidad.removeAllItems();
        cargarComboProveedores();
        enableComponents();
        limpiarTabla();
        limpiarTablaTransaccion();
        this.txtMotivo.setText("");
        tipo = 0;
        this.lblEntidad.setText("Proveedor:");
    }//GEN-LAST:event_rbMateriaPrimaActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int selectedRow = tblDetalleReclamo.getSelectedRow();
        filas.remove(selectedRow);
        tblDetalleReclamo.updateUI();
        tblDetalleReclamo.packAll();
}//GEN-LAST:event_btnQuitarActionPerformed

    private void btnAgregarPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPiezaActionPerformed
        agregarDetalleReclamo();
}//GEN-LAST:event_btnAgregarPiezaActionPerformed

    private void rbPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPiezaActionPerformed
        this.cmbTransaccion.removeAllItems();
        this.cmbEntidad.removeAllItems();
        cargarComboEmpresa();
        enableComponents();
        limpiarTabla();
        limpiarTablaTransaccion();
        this.txtMotivo.setText("");
        tipo = 1;
        this.lblEntidad.setText("Emp Metalurgica:");
    }//GEN-LAST:event_rbPiezaActionPerformed

    private void cmbEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEntidadActionPerformed
        this.cmbTransaccion.removeAllItems();
        if (this.rbMateriaPrima.isSelected() && this.cmbEntidad.getSelectedIndex() != 0 && this.cmbEntidad.getSelectedIndex() != -1) {
            Object prov = this.cmbEntidad.getSelectedItem();
            String nombreProveedor = prov.toString();
            long idProveedor = this.gestor.getIdProveedorByName(nombreProveedor);
            gestor.cargarComboOrdenCompra(cmbTransaccion, idProveedor);
        } else if (this.rbPieza.isSelected() && this.cmbEntidad.getSelectedIndex() != 0 && this.cmbEntidad.getSelectedIndex() != -1) {
            Object emp = this.cmbEntidad.getSelectedItem();
            String nombreEmpresa = emp.toString();
            long idEmpresa = this.gestor.getIdEmpresaByName(nombreEmpresa);
            gestor.cargarComboTrabajo(cmbTransaccion, idEmpresa);
        }
    }//GEN-LAST:event_cmbEntidadActionPerformed

    private void cmbTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTransaccionActionPerformed
        if (this.rbMateriaPrima.isSelected() && this.cmbTransaccion.getSelectedIndex() != 0 && this.cmbTransaccion.getSelectedIndex() != -1) {
            Object compra = this.cmbTransaccion.getSelectedItem();
            String nroCompra = compra.toString();
            idComp = this.gestor.getIdCompraByNumero(Long.parseLong(nroCompra));
            try {
                DetallecompraDB[] detalle = this.gestor.getDetalleByIdCompra(idComp);
                for (int i = 0; i < detalle.length; i++) {
                    long idMateriaPrima = detalle[i].getMateriaprima();
                    String nombreMateriaPrima = this.gestor.getMateriaprimaById(idMateriaPrima);
                    int cantidad = detalle[i].getCantidad();
                    long idDetalle = detalle[i].getIddetalle();
                    agregarDetalleTransaccion(idMateriaPrima, nombreMateriaPrima, cantidad, idDetalle);
                }
            } catch (DetallecompraException ex) {
                Logger.getLogger(GenerarSolicitudReclamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (this.rbPieza.isSelected() && this.cmbTransaccion.getSelectedIndex() != 0 && this.cmbTransaccion.getSelectedIndex() != -1) {
            Object trabajo = this.cmbTransaccion.getSelectedItem();
            String nroTrabajo = trabajo.toString();
            idTrabajo = this.gestor.getIdTrabajoByNumero(Long.parseLong(nroTrabajo));
            try {
                DetalletrabajotercerizadoDB[] detalle = this.gestor.getDetalleByIdTrabajo(idTrabajo);
                for (int i = 0; i < detalle.length; i++) {
                    long idPieza = detalle[i].getPieza();
                    String nombrePieza = this.gestor.getPiezaById(idPieza);
                    int cantidad = detalle[i].getCantidad();
                    long idDetalle = detalle[i].getIddetalle();
                    agregarDetalleTransaccion(idPieza, nombrePieza, cantidad, idDetalle);
                }
            } catch (DetalletrabajotercerizadoException ex) {
                Logger.getLogger(GenerarSolicitudReclamo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_cmbTransaccionActionPerformed

    private void agregarDetalleTransaccion(long id, String nombre, int cantidad, long idDetalle) {
        if (this.rbMateriaPrima.isSelected() && this.cmbTransaccion.getSelectedIndex() != 0 && this.cmbTransaccion.getSelectedIndex() != -1) {
            {
                ViewDetalleCompra datosFila = new ViewDetalleCompra();
                datosFila.setNombreMateriaPrima(nombre);
                datosFila.setIdMateriaPrima(id);
                datosFila.setCantidad(cantidad);
                datosFila.setIdDetalleCompra(idDetalle);
                filasCompra.addLast(datosFila);
            }

        } else if (this.rbPieza.isSelected() && this.cmbTransaccion.getSelectedIndex() != 0 && this.cmbTransaccion.getSelectedIndex() != -1) {
            ViewDetalleTrabajoTercerizado datosFila = new ViewDetalleTrabajoTercerizado();
            datosFila.setNombrePieza(nombre);
            datosFila.setIdPieza(id);
            datosFila.setCantidad(cantidad);
            filasTrabajo.addLast(datosFila);

        }
        this.tblDetalleTransaccion.updateUI();
        this.tblDetalleTransaccion.packAll();
    }

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
    private javax.swing.JComboBox cmbEntidad;
    private javax.swing.JComboBox cmbTransaccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblEntidad;
    private javax.swing.JRadioButton rbMateriaPrima;
    private javax.swing.JRadioButton rbPieza;
    private org.jdesktop.swingx.JXTable tblDetalleReclamo;
    private org.jdesktop.swingx.JXTable tblDetalleTransaccion;
    private javax.swing.JTextArea txtMotivo;
    // End of variables declaration//GEN-END:variables

    private void agregarDetalleReclamo() {
            int fila =  this.tblDetalleTransaccion.getSelectedRow();
            String nombre = this.tblDetalleTransaccion.getStringAt(fila, 0);
            String cantidad =this.tblDetalleTransaccion.getStringAt(fila, 1);
            JTextField txtCant = new JTextField(cantidad.toString());
            JTextField txtMotivodetalle = new JTextField();
            Object[] obj = {nombre, "", "Cantidad", txtCant, "Motivo", txtMotivodetalle, ""};
            int result = JOptionPane.showConfirmDialog(null, obj, "Detalle Reclamo", JOptionPane.OK_CANCEL_OPTION);
           
            if (result == JOptionPane.OK_OPTION) {
                int cant = Integer.parseInt(txtCant.getText());
                String motivoDetalle = txtMotivodetalle.getText();
                if (tipo == 0) {
                long idDetalleCompra = gestor.getIdDetalleByMPrimaCantidadAndIdCompra(idComp, nombre, Integer.parseInt(cantidad));
                agregarFila(nombre, cant, motivoDetalle, idDetalleCompra);
                }
                else{
                long idDetalleTrabajo = gestor.getIdDetalleByPiezaCantidadAndIdTrabajo(idTrabajo, nombre, Integer.parseInt(cantidad));
                agregarFila(nombre, cant, motivoDetalle, idDetalleTrabajo);
                }

        }
            tblDetalleReclamo.updateUI();
            tblDetalleReclamo.packAll();
    }

    public void agregarFila(String nombreArticulo, int cant, String motivo, long idDetalle) {
        //vector de tipo Object que contiene los datos de una fila
        ViewDetalleReclamo datosFila = new ViewDetalleReclamo();

        if (tipo == 0) {
            datosFila.setNombreMateriaPrima(nombreArticulo);
            datosFila.setCantidad(cant);
            datosFila.setMotivo(motivo);
            datosFila.setIdCompra(idComp);
            datosFila.setIdDetalle(idDetalle);

        } else {
            datosFila.setNombrePieza(nombreArticulo);
            datosFila.setCantidad(cant);
            datosFila.setMotivo(motivo);
            datosFila.setIdTrabajo(idTrabajo);
            datosFila.setIdDetalle(idDetalle);
        }
        filas.addLast(datosFila);
    }

    private void limpiarTabla() {
        int cantidadFilas = tblDetalleReclamo.getRowCount();
        for (int i = 0; i
                < cantidadFilas; i++) {
            filas.remove(0);
            tblDetalleReclamo.updateUI();
            tblDetalleReclamo.packAll();
        }
    }

    private void limpiarTablaTransaccion() {
        int cantidadFilas = tblDetalleTransaccion.getRowCount();
        if (this.rbPieza.isSelected()) {

            for (int i = 0; i < cantidadFilas; i++) {
                filasCompra.remove(0);
                tblDetalleTransaccion.updateUI();
                tblDetalleTransaccion.packAll();
            }
        } else {
            for (int i = 0; i < cantidadFilas; i++) {
                filasTrabajo.remove(0);
                tblDetalleTransaccion.updateUI();
                tblDetalleTransaccion.packAll();
            }
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

    public class DetalleTransaccionTableModel extends AbstractTableModel {

        String[] columnNames = {"Nombre", "Cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            if (tipo == 0) {
                ViewDetalleCompra view = filasCompra.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return view.getNombreMateriaPrima();
                    case 1:
                        return String.valueOf(view.getCantidad());
                    default:
                        return null;
                }
            } else {
                ViewDetalleTrabajoTercerizado view = filasTrabajo.get(rowIndex);
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

        public int getRowCount() {
            if (tipo == 0) {
                if (filasCompra != null) {
                    return filasCompra.size();
                }
                return 0;
            } else {
                if (filasTrabajo != null) {
                    return filasTrabajo.size();
                }
                return 0;
            }

        }

        public int getColumnCount() {
            return columnNames.length;
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

    public class DetalleReclamoTableModel extends AbstractTableModel {

        String[] columnNames = {"Nombre", "Cantidad", "Motivo"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetalleReclamo view = filas.get(rowIndex);

            if (tipo == 0) {
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
            } else {
                switch (columnIndex) {
                    case 0:
                        return view.getNombrePieza();
                    case 1:
                        return String.valueOf(view.getCantidad());
                    case 2:
                        return String.valueOf(view.getMotivo());
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
