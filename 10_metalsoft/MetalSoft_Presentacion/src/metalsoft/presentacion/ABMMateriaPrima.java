/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMMateriaPrima.java
 *
 * Created on 26/08/2010, 18:42:07
 */

package metalsoft.presentacion;

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
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.negocio.gestores.GestorMateriaPrima;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.util.Combo;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class ABMMateriaPrima extends javax.swing.JFrame {
    private GestorMateriaPrima gestor;
    private MateriaprimaDB materiaPrimaDB;
    private long idMateriaPrima=-1;
    private EnumOpcionesABM opcion;
    /** Creates new form ABMMateriaPrima */
    public ABMMateriaPrima() {
        initComponents();
        gestor=new GestorMateriaPrima();
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        cargarComboTipoMaterial();
        cargarComboUnidadMedida();
    }
    public void etapaSeleccionada() {
        materiaPrimaDB=gestor.buscarPorId(idMateriaPrima);
        mostrarDatosMateriaPrima(materiaPrimaDB);
    }

    public void setIdEtapa(long id) {
        idMateriaPrima=id;
    }

    private void cargarComboTipoMaterial() {
        cmbTipoMaterial.removeAllItems();
        gestor.obtenerTipoMateriales(cmbTipoMaterial);
    }
    private void cargarComboUnidadMedida()
    {
        cmbUnidadMedida.removeAllItems();
        gestor.obternerUnidadMedida(cmbUnidadMedida);
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
        MateriaPrima ep=new MateriaPrima();
        Date fechaAlta=null;
        if(dccFechaAlta.getSelectedDate()!=null)
            fechaAlta=dccFechaAlta.getSelectedDate().getTime();
        Date fechaBaja=null;
        if(dccFechaBaja.getSelectedDate()!=null)
            fechaBaja=dccFechaBaja.getSelectedDate().getTime();
        //ep.setFechaAlta(Fecha.parseToDate(txt.getText()));
        //ep.setFechaAlta(Fecha.parseToDate(txt.getText()));
        ep.setAlto(Double.parseDouble(dimensiones1.getTxtAlto().getText()));
        ep.setAncho(Double.parseDouble(dimensiones1.getTxtAncho().getText()));
        ep.setLargo(Double.parseDouble(dimensiones1.getTxtLargo().getText()));
        //ep.setCodBarra(txt);
        String idCodBarra=txtCodBarra.getText();
        ep.setCodProducto(Integer.parseInt(txtNroProducto.getText()));
        ep.setDescripcion(txtDescripcion.getText());
        ep.setNombre(txtNombre.getText());
        
        ep.setStock(Integer.parseInt(txtStock.getText()));
        
        long id;
        if(opcion==EnumOpcionesABM.NUEVO)
        {
            id=gestor.guardar(ep,((ItemCombo)cmbTipoMaterial.getSelectedItem()).getId(),((ItemCombo)cmbUnidadMedida.getSelectedItem()).getId(),idCodBarra);
            if(id>-1)JOptionPane.showMessageDialog(this, "Se Guardó la siguiente Materia Prima: "+txtNombre.getText());
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
        }

        if(opcion==EnumOpcionesABM.MODIFICAR)
        {
            id=gestor.modificarEtapaDeProduccion(ep,idMateriaPrima,((ItemCombo)cmbTipoMaterial.getSelectedItem()).getId(),((ItemCombo)cmbUnidadMedida.getSelectedItem()).getId(),idCodBarra);
            if(id>-1)JOptionPane.showMessageDialog(this, "Se modifico la siguiente Materia Prima: "+txtNombre.getText());
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
        ABMMateriaPrima_Buscar buscar=null;
        try {
            buscar=(ABMMateriaPrima_Buscar) JFrameManager.crearVentana(ABMMateriaPrima_Buscar.class.getName());
            buscar.setVentana(this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dimensiones1 = new metalsoft.beans.Dimensiones();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNroProducto = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        dccFechaBaja = new datechooser.beans.DateChooserCombo();
        dccFechaAlta = new datechooser.beans.DateChooserCombo();
        cmbTipoMaterial = new javax.swing.JComboBox();
        cmbUnidadMedida = new javax.swing.JComboBox();
        botones = new metalsoft.beans.ABM_Botones();
        jLabel11 = new javax.swing.JLabel();
        txtCodBarra = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nro. Producto:");

        jLabel2.setText("Nombre:");

        jLabel4.setText("Descripcion:");

        jLabel5.setText("Unidad de Medida:");

        dimensiones1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dimensiones"));

        jLabel6.setText("Tipo de Material:");

        jLabel7.setText("Fecha Alta:");

        jLabel8.setText("Fecha Baja:");

        jLabel9.setText("Stock:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        dccFechaAlta.setFormat(2);

        jLabel11.setText("Cod. Barra:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNroProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodBarra, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipoMaterial, 0, 331, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dccFechaBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(dimensiones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNroProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtCodBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(dimensiones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                new ABMMateriaPrima().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodBarra;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroProducto;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatosMateriaPrima(MateriaprimaDB mp) {

        txtCodBarra.setText(String.valueOf(mp.getCodbarra()));
        txtDescripcion.setText(mp.getDescripcion());
        txtNombre.setText(mp.getNombre());
        txtNroProducto.setText(String.valueOf(mp.getCodproducto()));
        //txtPrecio.setText(String.valueOf(mp.g)
        txtStock.setText(String.valueOf(mp.getStock()));
        //GregorianCalendar gc=new GregorianCalendar();
        //gc.setTime(mp.getFechaalta());
        dccFechaAlta.setSelectedDate(Fecha.parseToCalendar(mp.getFechaalta()));

        //gc.setTime(mp.getFechabaja());
        dccFechaBaja.setSelectedDate(Fecha.parseToCalendar(mp.getFechabaja()));

        dimensiones1.getTxtAlto().setText(String.valueOf(mp.getAlto()));
        dimensiones1.getTxtAncho().setText(String.valueOf(mp.getAncho()));
        dimensiones1.getTxtLargo().setText(String.valueOf(mp.getLargo()));

        if(mp.getTipomaterial()<1) Combo.setItemComboSeleccionado(cmbTipoMaterial, -1);
        else Combo.setItemComboSeleccionado(cmbTipoMaterial, mp.getTipomaterial());
        if(mp.getUnidaddemedida()<1) Combo.setItemComboSeleccionado(cmbUnidadMedida, -1);
        else Combo.setItemComboSeleccionado(cmbUnidadMedida, mp.getUnidaddemedida());
    }
    public void limpiarCampos()
    {
        txtCodBarra.setText("");
        txtDescripcion.setText("");
        txtNombre.setText("");
        txtNroProducto.setText("");
        //txtPrecio.setText(String.valueOf(mp.g)
        txtStock.setText("");
        
        dccFechaAlta.setSelectedDate(Fecha.fechaActualCalendar());
        dccFechaBaja.setSelectedDate(null);

        dimensiones1.getTxtAlto().setText("");
        dimensiones1.getTxtAncho().setText("");
        dimensiones1.getTxtLargo().setText("");

        cmbUnidadMedida.setSelectedIndex(-1);
        cmbTipoMaterial.setSelectedIndex(-1);
    }

}
