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
public class ABMMaquina extends javax.swing.JFrame {

    private GestorMaquina gestor;
    private MaquinaDB maquinaDB;
    private long idMaquina=-1;
    private EnumOpcionesABM opcion;
    /** Creates new form ABMMaquina */
    public ABMMaquina() {
        initComponents();
        gestor=new GestorMaquina();
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
        maquinaDB=gestor.buscarPorId(idMaquina);
        mostrarDatosMaquina(maquinaDB);
    }

    public void setIdMaquina(long id) {
        idMaquina=id;
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt)
    {
        opcion=EnumOpcionesABM.NUEVO;
        limpiarCampos();
        dccFechaAlta.setSelectedDate(Fecha.fechaActualCalendar());
        Combo.setItemComboSeleccionado(cmbUnidadMedida, 2);
        long nroMaQUINA=gestor.generarNvoNroMaquina();
        lblnroMaquina.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MAQUINA, nroMaQUINA));
        setEnableComponents(true);
    }

    private void addListenerBtnGuardar() {
        botones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt)
    {
        Maquina ep=new Maquina();
        Date fechaAlta=null;
        if(dccFechaAlta.getSelectedDate()!=null)
            fechaAlta=dccFechaAlta.getSelectedDate().getTime();
        Date fechaBaja=null;
        if(dccFechaBaja.getSelectedDate()!=null)
            fechaBaja=dccFechaBaja.getSelectedDate().getTime();
        //ep.setFechaAlta(Fecha.parseToDate(txt.getText()));
        //ep.setFechaAlta(Fecha.parseToDate(txt.getText()));
        ep.setFechaAlta(fechaAlta);
        ep.setFechaBaja(fechaBaja);
        
        ep.setNroMaquina(NumerosAMostrar.getNumeroLong(lblnroMaquina.getText()));
        ep.setDescripcion(txtDescripcion.getText());
        ep.setNombre(txtNombre.getText());
        if(txtTiempoProduccion.getText().compareTo("")!=0)
            ep.setTiempoCapacidadProduccion(Fecha.parseToTimeSQL(Fecha.parseToHourMinuteSecond(txtTiempoProduccion.getText())));
        else ep.setTiempoCapacidadProduccion(null);
        long idTipoMaquina=Long.parseLong(((ItemCombo) cmbTipoMaquina.getSelectedItem()).getId());
        long idMarca=Long.parseLong(((ItemCombo) cmbMarca.getSelectedItem()).getId());
        long idUnidadMedida=Long.parseLong(((ItemCombo) cmbUnidadMedida.getSelectedItem()).getId());
        long idEstado=Long.parseLong(((ItemCombo) cmbEstado.getSelectedItem()).getId());
        

        long id;
        if(opcion==EnumOpcionesABM.NUEVO)
        {
            id=gestor.guardar(ep, idTipoMaquina, idUnidadMedida, idMarca, idEstado);
            if(id>-1){
                JOptionPane.showMessageDialog(this, "Se Guardó la siguiente Maquina: "+txtNombre.getText());
                setEnableComponents(false);
            }
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
        }

        if(opcion==EnumOpcionesABM.MODIFICAR)
        {
            id=gestor.modificar(ep, idMaquina, idTipoMaquina, idUnidadMedida, idMarca, idEstado);
            if(id>-1){
                JOptionPane.showMessageDialog(this, "Se modifico la siguiente Maquina: "+txtNombre.getText());
                setEnableComponents(false);
            }
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron modificar");
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
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt)
    {
        opcion=EnumOpcionesABM.MODIFICAR;
        setEnableComponents(true);
    }

    private void addListenerBtnBuscar() {
        botones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt)
    {
        ABMMaquina_Buscar buscar=null;
        try {
            buscar=(ABMMaquina_Buscar) JFrameManager.crearVentana(ABMMaquina_Buscar.class.getName());
            buscar.setVentana(this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMMaquina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMMaquina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addListenerBtnSalir() {
        botones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt)
    {
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
        dccFechaAlta = new datechooser.beans.DateChooserCombo();
        jLabel5 = new javax.swing.JLabel();
        dccFechaBaja = new datechooser.beans.DateChooserCombo();
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
        botones = new metalsoft.beans.ABM_Botones();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTiempoProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTiempoProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel10.setText("Descripcion:");

        jLabel11.setText("Nro. de Maquina:");

        lblnroMaquina.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(174, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblnroMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEstado, 0, 138, Short.MAX_VALUE))))
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
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarComboTipoMaquina() {
        cmbTipoMaquina.removeAllItems();
        gestor.obtenerTipoMaquina(cmbTipoMaquina);
    }
    private void cargarComboUnidadMedida()
    {
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
    private datechooser.beans.DateChooserCombo dccFechaAlta;
    private datechooser.beans.DateChooserCombo dccFechaBaja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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

        if(mp.getFechaAlta()==null)
            dccFechaAlta.setSelectedDate(null);
        else{
            GregorianCalendar gc=new GregorianCalendar();
            gc.setTime(mp.getFechaAlta());
            dccFechaAlta.setSelectedDate(gc);
        }

        if(mp.getFechaBaja()==null)
            dccFechaBaja.setSelectedDate(null);
        else
        {
            GregorianCalendar gcb=new GregorianCalendar();
            gcb.setTime(mp.getFechaBaja());
            dccFechaBaja.setSelectedDate(gcb);
        }
        
        if(mp.getEstado()<1) Combo.setItemComboSeleccionado(cmbEstado, -1);
        else Combo.setItemComboSeleccionado(cmbEstado, mp.getEstado());
        if(mp.getUnidadMedida()<1) Combo.setItemComboSeleccionado(cmbUnidadMedida, -1);
        else Combo.setItemComboSeleccionado(cmbUnidadMedida, mp.getUnidadMedida());
        if(mp.getMarca()<1) Combo.setItemComboSeleccionado(cmbMarca, -1);
        else Combo.setItemComboSeleccionado(cmbMarca, mp.getMarca());
        if(mp.getTipomaquina()<1) Combo.setItemComboSeleccionado(cmbTipoMaquina, -1);
        else Combo.setItemComboSeleccionado(cmbTipoMaquina, mp.getTipomaquina());
    }
    public void limpiarCampos()
    {
        txtDescripcion.setText("");
        txtNombre.setText("");
        lblnroMaquina.setText("");;
        txtTiempoProduccion.setText("");

        dccFechaAlta.setSelectedDate(null);
        dccFechaBaja.setSelectedDate(null);

        cmbUnidadMedida.setSelectedIndex(-1);
        cmbEstado.setSelectedIndex(-1);
        cmbMarca.setSelectedIndex(-1);
        cmbTipoMaquina.setSelectedIndex(-1);
    }
    private void setEnableComponents(boolean b)
    {
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
