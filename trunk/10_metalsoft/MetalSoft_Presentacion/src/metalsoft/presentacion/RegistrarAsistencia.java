/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarAsistencia.java
 *
 * Created on 09/10/2010, 15:28:11
 */
package metalsoft.presentacion;

/**
 *
 * @author Vicky
 */
import java.sql.Time;
import java.util.ArrayList;
import metalsoft.datos.dbobject.EmpleadoDB;
import metalsoft.util.Fecha;
import metalsoft.util.EnumOpcionesABM;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorEmpleado;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.rrhh.Empleado;
import metalsoft.util.Combo;
import java.util.LinkedList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.AsistenciaDB;

public class RegistrarAsistencia extends javax.swing.JFrame {

    private GestorEmpleado gestor;
    private Empleado empleado;
    private metalsoft.datos.dbobject.EmpleadoDB empleadoDB;
    private EnumOpcionesABM opcion;
    private long idEmpleado;
    private LinkedList<AsistenciaDB> asistenciaDB;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoDB getEmpleadoDB() {
        return empleadoDB;
    }

    public void setEmpleadoDB(EmpleadoDB empleadoDB) {
        this.empleadoDB = empleadoDB;
    }

    public GestorEmpleado getGestor() {
        return gestor;
    }

    public void setGestor(GestorEmpleado gestor) {
        this.gestor = gestor;
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /** Creates new form RegistrarAsistencia */
    public RegistrarAsistencia() {
        initComponents();
        btnIngreso.setVisible(false);
        btnEgreso.setVisible(false);
        gestor = new GestorEmpleado();
        cargarComboCategoria();
        cargarComboCargo();
        asistenciaDB = new LinkedList<AsistenciaDB>();

        AsistenciaDB[] asis = gestor.buscarAsistenciaDelDia(Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        for (int i = 0; i < asis.length; i++) {
            agregarFila(asis[i].getEmpleado(), asis[i].getFechaingreso(), asis[i].getHoraingreso(), asis[i].getHoraegreso(), asis[i].getObservaciones());
        }

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        dccFechaConsulta.setSelectedDate(gc);

        jtbAsistencia.setModel(new AsistenciaTableModel());
        jtbAsistencia.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbAsistencia.updateUI();

    }

    private void cargarComboCategoria() {
        cmbCategoria.removeAllItems();
        gestor.obtenerCategorias(cmbCategoria);
    }

    private void cargarComboCargo() {
        cmbCargo.removeAllItems();
        gestor.obtenerCargos(cmbCargo);
    }

    public void empleadoSeleccionado() {
        empleadoDB = gestor.buscarEmpleadoDB(idEmpleado);
        txtNombreApellido.setText(empleadoDB.getApellido() + ", " + empleadoDB.getNombre());
        txtLegajo.setText(String.valueOf((int) empleadoDB.getLegajo()));
        cargarComboCategoria();
        cargarComboCargo();
        if (empleadoDB.getCargo() < 1) {
            Combo.setItemComboSeleccionado(cmbCargo, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbCargo, empleadoDB.getCargo());
        }
        if (empleadoDB.getCategoria() < 1) {
            Combo.setItemComboSeleccionado(cmbCategoria, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbCategoria, empleadoDB.getCategoria());
        }
        AsistenciaDB[] asis = gestor.buscarAsistencia(idEmpleado, Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        if (asis.length > 0) {
            btnEgreso.setVisible(true);
            btnIngreso.setVisible(false);
        } else {
            btnEgreso.setVisible(false);
            btnIngreso.setVisible(true);
        }
////ESTO SACARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
//        String observaciones = "Ninguna";
//
//        AsistenciaDB[] asis = gestor.buscarAsistencia(idEmpleado);
//        if (asis.length > 0) {
//            agregarFila(asis[0].getEmpleado(), asis[0].getFechaingreso(), asis[0].getHoraingreso(), Fecha.parseToTimeSQL(Fecha.parseToDate(Fecha.fechaHoraMinutoSegundoActual())), observaciones);
//        }
//
//        agregarFila(idEmpleado, Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())), Fecha.parseToTimeSQL(Fecha.parseToDate(Fecha.fechaHoraMinutoSegundoActual())), null, observaciones);
//        jtbAsistencia.updateUI();

    }

    public void agregarFila(long idEmpleado, java.sql.Date fechaIngreso, Time horaIngreso, Time horaEgreso, String observaciones) {
        //vector de tipo Object que contiene los datos de una fila
        AsistenciaDB datosFila = new AsistenciaDB();
        datosFila.setEmpleado(idEmpleado);
        datosFila.setFechaingreso(fechaIngreso);
        datosFila.setHoraingreso(horaIngreso);
        datosFila.setHoraegreso(horaEgreso);
        datosFila.setObservaciones(observaciones);

        asistenciaDB.addLast(datosFila);
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
        txtNombreApellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLegajo = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmbCargo = new javax.swing.JComboBox();
        btnIngreso = new javax.swing.JButton();
        btnEgreso = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbAsistencia = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        dccFechaConsulta = new datechooser.beans.DateChooserCombo();
        btnConsultar = new javax.swing.JButton();
        btnBuscarEmpleado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Empleado:");

        txtNombreApellido.setEditable(false);

        jLabel2.setText("Legajo:");

        txtLegajo.setEditable(false);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel8.setText("Categoria:");

        cmbCategoria.setEnabled(false);

        jLabel4.setText("Cargo:");

        cmbCargo.setEnabled(false);

        btnIngreso.setText("Registrar Ingreso");
        btnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoActionPerformed(evt);
            }
        });

        btnEgreso.setText("Registrar Egreso");
        btnEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEgresoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Asistencia del día"));

        jtbAsistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbAsistencia);

        jLabel3.setText("Consultar Asistencia Fecha:");

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dccFechaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(dccFechaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        btnBuscarEmpleado.setText("Buscar Empleado");
        btnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIngreso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEgreso))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbCategoria, 0, 247, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(22, 22, 22)
                                .addComponent(cmbCargo, 0, 247, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLegajo, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(btnBuscarEmpleado))))
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnBuscarEmpleado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngreso)
                    .addComponent(btnEgreso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
}//GEN-LAST:event_btnSalirActionPerformed

    private void btnEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEgresoActionPerformed
        // TODO add your handling code here:
        long result = -1;
        
        AsistenciaDB[] asistencia = gestor.buscarAsistencia(idEmpleado, Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        JTextArea txtDesc = new JTextArea("");
        JScrollPane scroll = new JScrollPane(txtDesc);
        Object[] obj = {"Descripcion", scroll};
        int observ = JOptionPane.showConfirmDialog(null, obj, "Dessea Agregar alguna Observacion?", JOptionPane.OK_CANCEL_OPTION);

        for (int i = 0; i < asistencia.length; i++) {
            if (asistencia[i].getHoraegreso() == null) {
                if (result == JOptionPane.OK_OPTION) {
                    String observaciones = txtDesc.getText();
                    asistencia[i].setObservaciones(observaciones);
                }
                result = gestor.registrarEgreso(asistencia[i]);
                break;
            }
        }

        AsistenciaDB[] asis = gestor.buscarAsistenciaDelDia(Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        asistenciaDB.clear();
        for (int i = 0; i < asis.length; i++) {
            agregarFila(asis[i].getEmpleado(), asis[i].getFechaingreso(), asis[i].getHoraingreso(), asis[i].getHoraegreso(), asis[i].getObservaciones());
        }

        jtbAsistencia.updateUI();
        if (result > 0) {
            txtLegajo.setText("");
            txtNombreApellido.setText("");
            cmbCargo.setSelectedIndex(0);
            cmbCategoria.setSelectedIndex(0);
            btnIngreso.setVisible(false);
            btnEgreso.setVisible(false);
            JOptionPane.showMessageDialog(this, "Se registro el egreso del empleado correctamente");
            
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar el egreso del empleado");
        }
}//GEN-LAST:event_btnEgresoActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
        Date fechaConsulta = null;
        if (dccFechaConsulta.getSelectedDate() != null) {
            fechaConsulta = dccFechaConsulta.getSelectedDate().getTime();
        }
        asistenciaDB.clear();
        AsistenciaDB[] asis = gestor.buscarAsistenciaDelDia(Fecha.parseToDateSQL(fechaConsulta));
        for (int i = 0; i < asis.length; i++) {
            agregarFila(asis[i].getEmpleado(), asis[i].getFechaingreso(), asis[i].getHoraingreso(), asis[i].getHoraegreso(), asis[i].getObservaciones());
        }

        jtbAsistencia.updateUI();
}//GEN-LAST:event_btnConsultarActionPerformed

    private void btnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
        opcion = EnumOpcionesABM.BUSCAR;
        ABMEmpleado_Buscar buscar = null;
        try {
            buscar = (ABMEmpleado_Buscar) JFrameManager.crearVentana(ABMEmpleado_Buscar.class.getName());
            buscar.setVentanaAsistencia(this);
            buscar.setGestor(gestor);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    private void btnIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoActionPerformed

        long result = -1;
        btnIngreso.setVisible(false);
        btnEgreso.setVisible(false);
        AsistenciaDB asistencia = new AsistenciaDB();
        JTextArea txtDesc = new JTextArea("");
        JScrollPane scroll = new JScrollPane(txtDesc);
        Object[] obj = {"Descripcion", scroll};
        int observ = JOptionPane.showConfirmDialog(null, obj, "Dessea Agregar alguna Observacion?", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String observaciones = txtDesc.getText();
            asistencia.setObservaciones(observaciones);
        }
        asistencia.setEmpleado(idEmpleado);
        asistencia.setFechaingreso(Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        asistencia.setHoraingreso(Fecha.parseToTimeSQL(Fecha.parseToDate(Fecha.fechaHoraMinutoSegundoActual())));
        result = gestor.registrarIngresoAsistencia(asistencia);

        AsistenciaDB[] asis = gestor.buscarAsistenciaDelDia(Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        asistenciaDB.clear();
        for (int i = 0; i < asis.length; i++) {
            agregarFila(asis[i].getEmpleado(), asis[i].getFechaingreso(), asis[i].getHoraingreso(), asis[i].getHoraegreso(), asis[i].getObservaciones());
        }

        jtbAsistencia.updateUI();
        if (result > 0) {
            txtLegajo.setText("");
            txtNombreApellido.setText("");
            cmbCargo.setSelectedIndex(0);
            cmbCategoria.setSelectedIndex(0);
            btnIngreso.setVisible(false);
            btnEgreso.setVisible(false);
            JOptionPane.showMessageDialog(this, "Se registro la asistencia correctamente");

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar la asistencia");
        }

    }//GEN-LAST:event_btnIngresoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarAsistencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarEmpleado;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEgreso;
    private javax.swing.JButton btnIngreso;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbCargo;
    private javax.swing.JComboBox cmbCategoria;
    private datechooser.beans.DateChooserCombo dccFechaConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbAsistencia;
    private javax.swing.JTextField txtLegajo;
    private javax.swing.JTextField txtNombreApellido;
    // End of variables declaration//GEN-END:variables

    public class AsistenciaTableModel extends AbstractTableModel {

        String[] columnNames = {"Empleado",
            "Legajo",
            "Fecha",
            "Hora Ingreso",
            "Hora Egreso",
            "Observaciones"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            AsistenciaDB view = (AsistenciaDB) asistenciaDB.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            empleadoDB = gestor.buscarEmpleadoDB(view.getEmpleado());
            switch (columnIndex) {
                case 0:
                    return empleadoDB.getApellido() + ", " + empleadoDB.getNombre();
                case 1:
                    return empleadoDB.getLegajo();
                case 2:
                    return String.valueOf(view.getFechaingreso());
                case 3:
                    return String.valueOf(view.getHoraingreso());
                case 4:
                    return String.valueOf(view.getHoraegreso());
                case 5:
                    return view.getObservaciones();
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
            if (asistenciaDB != null) {
                return asistenciaDB.size();
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
