/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AsignarMateriaPrimaAProduccion.java
 *
 * Created on 17/10/2010, 04:56:29
 */
package metalsoft.presentacion;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.Planificacionproduccion;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.negocio.gestores.GestorCodigoBarra;
import metalsoft.negocio.gestores.GestorMateriaPrima;
import metalsoft.negocio.gestores.GestorPlanificacion;
import metalsoft.negocio.gestores.GestorPresupuesto;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewMateriaPrimaXPiezaPresupuesto;
import metalsoft.negocio.gestores.ViewPlanificacion;
import metalsoft.negocio.gestores.GestorPiezaReal;
import metalsoft.negocio.produccion.CodigoDeBarra;
import metalsoft.negocio.produccion.PiezaReal;
import metalsoft.negocio.ventas.Pieza;
import metalsoft.util.Combo;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class AsignarMateriaPrimaAProduccion extends javax.swing.JFrame {

    private LinkedList<ViewPlanificacion> filasPedidos;
    private LinkedList<ViewMateriaPrimaXPiezaPresupuesto> filasMateriaPrimaXPiezaPresupuesto;
    private long idPedido;
    private GestorPlanificacion gestor;
    private GestorPresupuesto gestorPresupuesto;
    private GestorMateriaPrima gestorMateriaPrima;
    private GestorPiezaReal gestorPiezaReal;
    private GestorCodigoBarra gestorCodigoBarra;
    private MateriaprimaDB materiaPrima;

    /** Creates new form AsignarMateriaPrimaAProduccion */
    public AsignarMateriaPrimaAProduccion() {
        initComponents();
        lblNroPresupuesto.setVisible(false);
        gestor = new GestorPlanificacion();
        gestorPresupuesto = new GestorPresupuesto();
        gestorMateriaPrima = new GestorMateriaPrima();
        gestorPiezaReal = new GestorPiezaReal();
        gestorCodigoBarra = new GestorCodigoBarra();
        cargarComboTipoMaterial();
        cargarComboUnidadMedida();
        buscarPedidosConMPAsignada();
        setEnableFalse();
        btnSeleccionarProveedor.setEnabled(false);
        btnAsignarMP.setEnabled(false);
    }

    public void buscarPedidosConMPAsignada() {
        filasPedidos = gestor.buscarPlanificacionConRecursosAsignados();
        tblPedidos.updateUI();
    }

    private void cargarTablaMatPrima() {
        filasMateriaPrimaXPiezaPresupuesto = gestorPresupuesto.buscarMatPrimaXPiezaPresupuesto();
        tblMatPrimaXPieza.updateUI();

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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        lblNroPresupuesto = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblMatPrimaXPieza = new javax.swing.JTable();
        btnSeleccionarProveedor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dccFechaAlta = new datechooser.beans.DateChooserCombo();
        jLabel8 = new javax.swing.JLabel();
        dccFechaBaja = new datechooser.beans.DateChooserCombo();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        cmbUnidadMedida = new javax.swing.JComboBox();
        dimensiones1 = new metalsoft.beans.Dimensiones();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblNroMateriaPrima = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCodBarra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoMaterial = new javax.swing.JComboBox();
        btnAsignarMP = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos sin Materia Prima asignada"));

        tblPedidos.setModel(new PedidoTableModel());
        jScrollPane3.setViewportView(tblPedidos);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Materia Prima"));

        tblMatPrimaXPieza.setModel(new MateriaPrimaXPiezaPresupuestoTableModel());
        jScrollPane5.setViewportView(tblMatPrimaXPieza);

        btnSeleccionarProveedor.setText("Seleccionar");
        btnSeleccionarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarProveedor))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionarProveedor))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lblNroPresupuesto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar)
                    .addComponent(lblNroPresupuesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Materia Prima"));

        jLabel7.setText("Fecha Alta:");

        jLabel8.setText("Fecha Baja:");

        try {
            dccFechaBaja.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }

        jLabel4.setText("Descripcion:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel5.setText("Unidad de Medida:");

        dimensiones1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dimensiones"));

        jLabel2.setText("Nombre:");

        jLabel1.setText("Nro. Materia Prima:");

        lblNroMateriaPrima.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroMateriaPrima.setText("...");

        jLabel11.setText("Cod. Barra:");

        jLabel9.setText("Stock:");

        jLabel6.setText("Tipo de Material:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dimensiones1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNroMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCodBarra, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblNroMateriaPrima))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtCodBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cmbTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dimensiones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btnAsignarMP.setText("Asignar Materia Prima");
        btnAsignarMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarMPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAsignarMP))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAsignarMP))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        ViewPlanificacion viewPedido = filasPedidos.get(tblPedidos.getSelectedRow());
        idPedido = viewPedido.getIdpedido();

        long nroPresupuesto = gestorPresupuesto.buscarNroPresupuesto(idPedido);
        lblNroPresupuesto.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRESUPUESTO, nroPresupuesto));
        cargarTablaMatPrima();
        btnSeleccionarProveedor.setEnabled(true);

    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnSeleccionarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProveedorActionPerformed
        ViewMateriaPrimaXPiezaPresupuesto view = filasMateriaPrimaXPiezaPresupuesto.get(tblMatPrimaXPieza.getSelectedRow());
        long idMatPrima = view.getIdmateriaprima();
        materiaPrima = gestorMateriaPrima.buscarPorId(idMatPrima);
        mostrarDatosMateriaPrima(materiaPrima);
        btnAsignarMP.setEnabled(true);

}//GEN-LAST:event_btnSeleccionarProveedorActionPerformed

    private void btnAsignarMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarMPActionPerformed
        // TODO add your handling code here:
        long result = -1;
        long resultCB = -1;
        long idDetalleMPAsignada = -1;
        long idMPAsigXPieza = -1;
        int cont = 0;
        ViewMateriaPrimaXPiezaPresupuesto view = filasMateriaPrimaXPiezaPresupuesto.get(tblMatPrimaXPieza.getSelectedRow());
        long idMP = view.getIdmateriaprima();
        if (gestor.mpPermitidaAAsignar(idPedido, idMP) > 0) //consulta si esa materia prima esta asignada del todo
            //Antes ver si hay la cantidad de Mat Prima Suficiente
            materiaPrima.setStock(materiaPrima.getStock() - view.getCantmateriaprima());

            PiezaReal piezaReal = new PiezaReal();
            piezaReal.setNroPieza((int) view.getIdpieza());
            CodigoDeBarra cb = new CodigoDeBarra();
            cb.setDescripcion(view.getNombrepieza());

            Planificacionproduccion plan = gestor.buscarPlanificacionPorPedido(idPedido);
            long[] piezasReales = new long[view.getCantpieza()];
            for (int i = 0; i < view.getCantpieza(); i++) {
                result = gestorPiezaReal.guardar(piezaReal, view.getIdpieza(), 1, -1);
                piezasReales[i] = result;
                if (result > -1) {
                    resultCB = gestorCodigoBarra.guardarCodPieza(cb, result);
                }
                if (resultCB > -1) {
                    cont++;
                }
            }
            if (cont > 0) {
                long idmateria = gestorMateriaPrima.modificarMateriaPrimaDB(materiaPrima);
                idDetalleMPAsignada = gestor.guardarDetalleAsignacionMP(plan.getIdplanificacionproduccion(), idMP, cont);
                if (idDetalleMPAsignada > -1) {
                    for (int i = 0; i < piezasReales.length; i++) {
                        gestor.guardarMPAsignadaXPieza(piezasReales[i], idDetalleMPAsignada);
                    }
                }
            }
        }
        if (result > -1 && cont > 0) {
            JOptionPane.showMessageDialog(this, "Se guardaron los datos Correctamente");
            setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudieron guardar los datos");
        }


    }//GEN-LAST:event_btnAsignarMPActionPerformed
    private void mostrarDatosMateriaPrima(MateriaprimaDB mp) {

        txtCodBarra.setText(String.valueOf(mp.getCodbarra()));
        txtDescripcion.setText(mp.getDescripcion());
        txtNombre.setText(mp.getNombre());
        lblNroMateriaPrima.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MATERIAPRIMA, mp.getNromateriaprima()));
        //txtPrecio.setText(String.valueOf(materiaPrima.g)
        txtStock.setText(String.valueOf(mp.getStock()));

        if (mp.getFechaalta() == null) {
            dccFechaAlta.setSelectedDate(null);
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(mp.getFechaalta());
            dccFechaAlta.setSelectedDate(gc);
        }

        if (mp.getFechabaja() == null) {
            dccFechaBaja.setSelectedDate(null);
        } else {
            GregorianCalendar gcb = new GregorianCalendar();
            gcb.setTime(mp.getFechabaja());
            dccFechaBaja.setSelectedDate(gcb);
        }
        dimensiones1.getTxtAlto().setText(String.valueOf(mp.getAlto()));
        dimensiones1.getTxtAncho().setText(String.valueOf(mp.getAncho()));
        dimensiones1.getTxtLargo().setText(String.valueOf(mp.getLargo()));

        if (mp.getTipomaterial() < 1) {
            Combo.setItemComboSeleccionado(cmbTipoMaterial, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbTipoMaterial, mp.getTipomaterial());
        }
        if (mp.getUnidaddemedida() < 1) {
            Combo.setItemComboSeleccionado(cmbUnidadMedida, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbUnidadMedida, mp.getUnidaddemedida());
        }
    }

    private void cargarComboTipoMaterial() {
        cmbTipoMaterial.removeAllItems();
        gestorMateriaPrima.obtenerTipoMateriales(cmbTipoMaterial);
    }

    private void cargarComboUnidadMedida() {
        cmbUnidadMedida.removeAllItems();
        gestorMateriaPrima.obternerUnidadMedida(cmbUnidadMedida);
    }

    public void limpiarCampos() {
        txtCodBarra.setText("");
        txtDescripcion.setText("");
        txtNombre.setText("");
        lblNroMateriaPrima.setText("");
        //txtPrecio.setText(String.valueOf(materiaPrima.g)
        txtStock.setText("");

        dccFechaAlta.setSelectedDate(null);
        dccFechaBaja.setSelectedDate(null);

        dimensiones1.getTxtAlto().setText("");
        dimensiones1.getTxtAncho().setText("");
        dimensiones1.getTxtLargo().setText("");

        cmbUnidadMedida.setSelectedIndex(-1);
        cmbTipoMaterial.setSelectedIndex(-1);
    }

    public void setEnableFalse() {
        txtCodBarra.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtNombre.setEnabled(false);
        lblNroMateriaPrima.setEnabled(false);
        //txtPrecio.setText(String.valueOf(materiaPrima.g)
        txtStock.setEnabled(false);

        dccFechaAlta.setEnabled(false);
        dccFechaBaja.setEnabled(false);

        dimensiones1.getTxtAlto().setEnabled(false);
        dimensiones1.getTxtAncho().setEnabled(false);
        dimensiones1.getTxtLargo().setEnabled(false);

        cmbUnidadMedida.setEnabled(false);
        cmbTipoMaterial.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AsignarMateriaPrimaAProduccion().setVisible(true);
            }
        });
    }

    class PedidoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nro",
            "Pedido Cotiz Cliente",
            "Cliente",
            "Prioridad",
            "Fecha de Entrega",
            "Presupuesto"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewPlanificacion view = filasPedidos.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNropedido();
                case 1:
                    return view.getNropedidocotizacioncliente();
                case 2:
                    return view.getRazonsocial();
                case 3:
                    return view.getPrioridad();
                case 4:
                    if (view.getFechaentregaestipulada() == null) {
                        return "";
                    } else {
                        return Fecha.parseToString(view.getFechaentregaestipulada().getTime());
                    }
                case 5:
                    return view.getIdpresupuesto();

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
            if (filasPedidos != null) {
                return filasPedidos.size();
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

    class MateriaPrimaXPiezaPresupuestoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nro Prod",
            "Nombre",
            "Cant Prod",
            "Nombre Pieza",
            "Cant Pieza",
            "Mat Prima",
            "Precio",
            "Cant MP",
            "Cant Total",
            "Precio Total"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewMateriaPrimaXPiezaPresupuesto view = null;
            view = filasMateriaPrimaXPiezaPresupuesto.get(rowIndex);
//            try
//            {
//                view=filasMateriaPrimaXPiezaPresupuesto.get(rowIndex);
//            }
//            catch(Exception ex)
//            {
//                return "";
//            }
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNroproducto();
                case 1:
                    return view.getNombreproducto();
                case 2:
                    return view.getCantproducto();
                case 3:
                    return view.getNombrepieza();
                case 4:
                    return view.getCantpieza();
                case 5:
                    return view.getNombremateriaprima();
                case 6:
                    return view.getPreciomateriaprima();
                case 7:
                    return view.getCantmateriaprima();
                case 8:
                    return view.getCanttotal();
                case 9:
                    return view.getPreciototal();
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
            if (filasMateriaPrimaXPiezaPresupuesto != null) {
                return filasMateriaPrimaXPiezaPresupuesto.size();
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarMP;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton btnSeleccionarProveedor;
    private javax.swing.JComboBox cmbTipoMaterial;
    private javax.swing.JComboBox cmbUnidadMedida;
    private datechooser.beans.DateChooserCombo dccFechaAlta;
    private datechooser.beans.DateChooserCombo dccFechaBaja;
    private metalsoft.beans.Dimensiones dimensiones1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblNroMateriaPrima;
    private javax.swing.JLabel lblNroPresupuesto;
    private javax.swing.JTable tblMatPrimaXPieza;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtCodBarra;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
