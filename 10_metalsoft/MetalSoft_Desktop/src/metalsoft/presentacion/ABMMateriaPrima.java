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

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.negocio.gestores.GestorMateriaPrima;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.Combo;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class ABMMateriaPrima extends javax.swing.JDialog {
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
        enableComponents(false);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }
    public void etapaSeleccionada() {
        materiaPrimaDB=gestor.buscarPorId(idMateriaPrima);
        mostrarDatosMateriaPrima(materiaPrimaDB);
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);
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
        enableComponents(true);
        dccFechaAlta.setDate(Fecha.fechaActualDate());
        Combo.setItemComboSeleccionado(cmbUnidadMedida, 2);
        long nroMatPrima=gestor.generarNvoNroMateriaPrima();
        lblNroMateriaPrima.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MATERIAPRIMA, nroMatPrima));
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
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt)
    {
        MateriaPrima ep=new MateriaPrima();
        Date fechaAlta=null;
        if(dccFechaAlta.getDate()!=null)
            fechaAlta=dccFechaAlta.getDate();
        Date fechaBaja=null;
        if(dccFechaBaja.getDate()!=null)
            fechaBaja=dccFechaBaja.getDate();
        //ep.setFechaAlta(Fecha.parseToDate(txt.getText()));
        //ep.setFechaAlta(Fecha.parseToDate(txt.getText()));
        ep.setFechaAlta(fechaAlta);
        ep.setFechaBaja(fechaBaja);
        ep.setAlto(Double.parseDouble(dimensiones1.getTxtAlto().getText()));
        ep.setAncho(Double.parseDouble(dimensiones1.getTxtAncho().getText()));
        ep.setLargo(Double.parseDouble(dimensiones1.getTxtLargo().getText()));
        //ep.setCodBarra(txt);
        String idCodBarra=txtCodBarra.getText();
        //GENERAR EL CODIGO DE BARRA!!!!
        idCodBarra="1";
        //long idcdoB=gestor.guardarCodigoBarra(idCodBarra);
        ep.setCodProducto(0);
        ep.setNroMateriaPrima(NumerosAMostrar.getNumeroLong(lblNroMateriaPrima.getText()));
        ep.setDescripcion(txtDescripcion.getText());
        ep.setNombre(txtNombre.getText());
        
        ep.setStock(Integer.parseInt(txtStock.getText()));
        
        long id;
        if(opcion==EnumOpcionesABM.NUEVO)
        {
            id=gestor.guardar(ep,((ItemCombo)cmbTipoMaterial.getSelectedItem()).getId(),((ItemCombo)cmbUnidadMedida.getSelectedItem()).getId(),idCodBarra);
            if(id>-1){
                JOptionPane.showMessageDialog(this, "Se Guardó la siguiente Materia Prima: "+txtNombre.getText());
                enableComponents(false);
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar();
            }
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
        }

        if(opcion==EnumOpcionesABM.MODIFICAR)
        {
            id=gestor.modificar(ep,idMateriaPrima,((ItemCombo)cmbTipoMaterial.getSelectedItem()).getId(),((ItemCombo)cmbUnidadMedida.getSelectedItem()).getId(),idCodBarra);
            if(id>-1){
                JOptionPane.showMessageDialog(this, "Se modifico la siguiente Materia Prima: "+txtNombre.getText());
                enableComponents(false);
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar();
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
        enableComponents(true);
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
    private void enableComponents(boolean b)
    {
        txtCodBarra.setEnabled(b);
        txtDescripcion.setEnabled(b);
        txtNombre.setEnabled(b);
        lblNroMateriaPrima.setEnabled(b);
        //txtPrecio.setText(String.valueOf(mp.g)
        txtStock.setEnabled(b);

        dccFechaAlta.setEnabled(b);
        dccFechaBaja.setEnabled(b);

        dimensiones1.getTxtAlto().setEnabled(b);
        dimensiones1.getTxtAncho().setEnabled(b);
        dimensiones1.getTxtLargo().setEnabled(b);

        cmbUnidadMedida.setEnabled(b);
        cmbTipoMaterial.setEnabled(b);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dimensiones1 = new metalsoft.beans.Dimensiones();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        cmbTipoMaterial = new javax.swing.JComboBox();
        cmbUnidadMedida = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txtCodBarra = new javax.swing.JTextField();
        lblNroMateriaPrima = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        botones = new metalsoft.beans.ABM_Botones();
        dccFechaBaja = new com.toedter.calendar.JDateChooser();
        dccFechaAlta = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Materia Prima");

        jLabel1.setText("Nro. Materia Prima:");

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

        jLabel11.setText("Cod. Barra:");

        lblNroMateriaPrima.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroMateriaPrima.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dimensiones1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodBarra, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNroMateriaPrima))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dimensiones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private com.toedter.calendar.JDateChooser dccFechaAlta;
    private com.toedter.calendar.JDateChooser dccFechaBaja;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblNroMateriaPrima;
    private javax.swing.JTextField txtCodBarra;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatosMateriaPrima(MateriaprimaDB mp) {

        txtCodBarra.setText(String.valueOf(mp.getCodbarra()));
        txtDescripcion.setText(mp.getDescripcion());
        txtNombre.setText(mp.getNombre());
        lblNroMateriaPrima.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MATERIAPRIMA, mp.getNromateriaprima()));
        //txtPrecio.setText(String.valueOf(mp.g)
        txtStock.setText(String.valueOf(mp.getStock()));
        
        if(mp.getFechaalta()==null)
            dccFechaAlta.setDate(null);
        else{
            dccFechaAlta.setDate(mp.getFechaalta());
        }
        
        if(mp.getFechabaja()==null)
            dccFechaBaja.setDate(null);
        else
        {
            dccFechaBaja.setDate(mp.getFechabaja());
        }
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
        lblNroMateriaPrima.setText("");
        //txtPrecio.setText(String.valueOf(mp.g)
        txtStock.setText("");
        
        dccFechaAlta.setDate(null);
        dccFechaBaja.setDate(null);

        dimensiones1.getTxtAlto().setText("");
        dimensiones1.getTxtAncho().setText("");
        dimensiones1.getTxtLargo().setText("");

        cmbUnidadMedida.setSelectedIndex(-1);
        cmbTipoMaterial.setSelectedIndex(-1);
    }

}
