/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMMaquina.java
 *
 * Created on 10/10/2010, 17:17:47
 */
package metalsoft.presentacion;

import java.sql.Time;
import metalsoft.negocio.gestores.IBuscador;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;
import metalsoft.datos.dbobject.MaquinaDB;
import metalsoft.negocio.gestores.GestorMaquina;
import metalsoft.negocio.mantmaquinarias.Maquina;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.Combo;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class ABMMaquina extends javax.swing.JDialog {

    private GestorMaquina gestor;
    private MaquinaDB maquinaDB;
    private long idMaquina = -1;
    private EnumOpcionesABM opcion;

    /** Creates new form ABMMaquina */
    public ABMMaquina() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorMaquina();
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        cargarComboTipoMaquina();
        cargarComboMarca();
        cargarComboEstado();
        cargarComboUnidadMedida();
        setEnableComponents(false);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void cargarComboEstado() {
        cmbEstado.removeAllItems();
        gestor.obternerEstadoMaquina(cmbEstado);
    }

    private void cargarComboMarca() {
        cmbMarca.removeAllItems();
        gestor.obtenerMarca(cmbMarca);
    }

    public void maquinaSeleccionada() {
        maquinaDB = gestor.buscarPorId(idMaquina);
        mostrarDatosMaquina(maquinaDB);
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);
    }

    public void setIdMaquina(long id) {
        idMaquina = id;
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.NUEVO;
        limpiarCampos();
        dccFechaAlta.setDate(Fecha.fechaActualDate());
        Combo.setItemComboSeleccionado(cmbUnidadMedida, 2);
        long nroMaQUINA = gestor.generarNvoNroMaquina();
        lblnroMaquina.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MAQUINA, nroMaQUINA));
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
        Maquina ep = new Maquina();
        Date fechaAlta = null;
        if (dccFechaAlta.getDate() != null) {
            fechaAlta = dccFechaAlta.getDate();
        }
        Date fechaBaja = null;
        if (dccFechaBaja.getDate() != null) {
            fechaBaja = dccFechaBaja.getDate();
        }
        //ep.setFechaAlta(Fecha.parseToDate(txt.getText()));
        //ep.setFechaAlta(Fecha.parseToDate(txt.getText()));
        ep.setFechaAlta(fechaAlta);
        ep.setFechaBaja(fechaBaja);

        ep.setNroMaquina(NumerosAMostrar.getNumeroLong(lblnroMaquina.getText()));
        ep.setDescripcion(txtDescripcion.getText());
        ep.setNombre(txtNombre.getText());
        if (txtTiempoProduccion.getText().compareTo("") != 0) {
            ep.setTiempoCapacidadProduccion(Fecha.parseToTimeSQL(Fecha.parseToHourMinuteSecond(txtTiempoProduccion.getText())));
        } else {
            ep.setTiempoCapacidadProduccion(null);
        }
        long idTipoMaquina = Long.parseLong(((ItemCombo) cmbTipoMaquina.getSelectedItem()).getId());
        long idMarca = Long.parseLong(((ItemCombo) cmbMarca.getSelectedItem()).getId());
        long idUnidadMedida = Long.parseLong(((ItemCombo) cmbUnidadMedida.getSelectedItem()).getId());
        long idEstado = Long.parseLong(((ItemCombo) cmbEstado.getSelectedItem()).getId());


        long id;
        if (opcion == EnumOpcionesABM.NUEVO) {
            id = gestor.guardar(ep, idTipoMaquina, idUnidadMedida, idMarca, idEstado);
            if (id > -1) {
                JOptionPane.showMessageDialog(this, "Se Guardó la siguiente Maquina: " + txtNombre.getText());
                setEnableComponents(false);
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
            }
        }

        if (opcion == EnumOpcionesABM.MODIFICAR) {
            id = gestor.modificar(ep, idMaquina, idTipoMaquina, idUnidadMedida, idMarca, idEstado);
            if (id > -1) {
                JOptionPane.showMessageDialog(this, "Se modifico la siguiente Maquina: " + txtNombre.getText());
                setEnableComponents(false);
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Los datos no se pudieron modificar");
            }
        }
    //limpiarCampos();
    }

    private void addListenerBtnModificar() {
        botones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.MODIFICAR;
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
        ABMMaquina_Buscar buscar = null;
        buscar = new ABMMaquina_Buscar(this);
        buscar.setVentana(this);
        buscar.setGestor(gestor);
        JFrameManager.centrarYMostrarVentana(buscar);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox();
        cmbTipoMaquina = new javax.swing.JComboBox();
        cmbMarca = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cmbUnidadMedida = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtTiempoProduccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblnroMaquina = new javax.swing.JLabel();
        dccFechaAlta = new com.toedter.calendar.JDateChooser();
        dccFechaBaja = new com.toedter.calendar.JDateChooser();
        botones = new metalsoft.beans.ABM_Botones();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Máquina");

        jLabel1.setText("Nombre:");

        jLabel2.setText("Marca:");

        jLabel3.setText("Tipo de Máquina:");

        jLabel4.setText("Fecha de Alta:");

        jLabel5.setText("Fecha de Baja:");

        jLabel6.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbTipoMaquina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Capacidad de Producción"));

        jLabel7.setText("Produce 1 (un) ");

        cmbUnidadMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("en");

        jLabel9.setText("HH:MM:SS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(26, 26, 26)
                .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtTiempoProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(txtTiempoProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel10.setText("Descripción:");

        jLabel11.setText("Número de Máquina:");

        lblnroMaquina.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblnroMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addContainerGap(204, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbTipoMaquina, 0, 120, Short.MAX_VALUE)
                                    .addComponent(dccFechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(cmbMarca, 0, 174, Short.MAX_VALUE)
                                    .addComponent(txtNombre))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dccFechaBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblnroMaquina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5)
                    .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbMarca, txtNombre});

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarComboTipoMaquina() {
        cmbTipoMaquina.removeAllItems();
        gestor.obtenerTipoMaquina(cmbTipoMaquina);
    }

    private void cargarComboUnidadMedida() {
        cmbUnidadMedida.removeAllItems();
        gestor.obternerUnidadMedida(cmbUnidadMedida);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMMaquina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JComboBox cmbMarca;
    private javax.swing.JComboBox cmbTipoMaquina;
    private javax.swing.JComboBox cmbUnidadMedida;
    private com.toedter.calendar.JDateChooser dccFechaAlta;
    private com.toedter.calendar.JDateChooser dccFechaBaja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblnroMaquina;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTiempoProduccion;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatosMaquina(MaquinaDB mp) {

        txtDescripcion.setText(mp.getDescripcion());
        txtNombre.setText(mp.getNombre());
        lblnroMaquina.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MATERIAPRIMA, mp.getIdmaquina()));
        txtTiempoProduccion.setText(String.valueOf(mp.getTiempoCapacidadProduccion()));

        if (mp.getFechaAlta() == null) {
            dccFechaAlta.setDate(null);
        } else {
            dccFechaAlta.setDate(mp.getFechaAlta());
        }

        if (mp.getFechaBaja() == null) {
            dccFechaBaja.setDate(null);
        } else {
            dccFechaBaja.setDate(mp.getFechaBaja());
        }

        if (mp.getEstado() < 1) {
            Combo.setItemComboSeleccionado(cmbEstado, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbEstado, mp.getEstado());
        }
        if (mp.getUnidadMedida() < 1) {
            Combo.setItemComboSeleccionado(cmbUnidadMedida, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbUnidadMedida, mp.getUnidadMedida());
        }
        if (mp.getMarca() < 1) {
            Combo.setItemComboSeleccionado(cmbMarca, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbMarca, mp.getMarca());
        }
        if (mp.getTipomaquina() < 1) {
            Combo.setItemComboSeleccionado(cmbTipoMaquina, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbTipoMaquina, mp.getTipomaquina());
        }
    }

    public void limpiarCampos() {
        txtDescripcion.setText("");
        txtNombre.setText("");
        lblnroMaquina.setText("");
        ;
        txtTiempoProduccion.setText("");

        dccFechaAlta.setDate(null);
        dccFechaBaja.setDate(null);

        cmbUnidadMedida.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        cmbMarca.setSelectedIndex(0);
        cmbTipoMaquina.setSelectedIndex(0);
    }

    private void setEnableComponents(boolean b) {
        txtDescripcion.setEnabled(b);
        txtNombre.setEnabled(b);
        lblnroMaquina.setEnabled(b);
        txtTiempoProduccion.setEnabled(b);

        dccFechaAlta.setEnabled(b);
        dccFechaBaja.setEnabled(b);
        cmbUnidadMedida.setEnabled(b);
        cmbEstado.setEnabled(b);
        cmbMarca.setEnabled(b);
        cmbTipoMaquina.setEnabled(b);
    }
}
