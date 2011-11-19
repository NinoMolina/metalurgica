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
        super(Principal.getVtnPrincipal());
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
        String idCodBarra=null;
        
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
        buscar = new ABMMateriaPrima_Buscar(this);
        buscar.setVentana(this);
        JFrameManager.centrarYMostrarVentana(buscar);
    }
    private void enableComponents(boolean b)
    {
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
        txtNombre = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        cmbTipoMaterial = new javax.swing.JComboBox();
        cmbUnidadMedida = new javax.swing.JComboBox();
        lblNroMateriaPrima = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        botones = new metalsoft.beans.ABM_Botones();
        dccFechaBaja = new com.toedter.calendar.JDateChooser();
        dccFechaAlta = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Materia Prima");
        getContentPane().setLayout(null);

        jLabel1.setText("Nro. Materia Prima:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 36, 93, 14);

        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(251, 39, 41, 14);

        jLabel4.setText("Descripción:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 242, 58, 14);

        jLabel5.setText("Unidad de Medida:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 108, 89, 14);

        dimensiones1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dimensiones"));
        getContentPane().add(dimensiones1);
        dimensiones1.setBounds(83, 146, 290, 47);

        jLabel6.setText("Tipo de Material:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 77, 80, 14);

        jLabel7.setText("Fecha Alta:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 210, 55, 14);

        jLabel8.setText("Fecha Baja:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(281, 210, 57, 14);
        getContentPane().add(txtNombre);
        txtNombre.setBounds(296, 36, 200, 20);
        getContentPane().add(txtStock);
        txtStock.setBounds(350, 70, 71, 20);

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(69, 242, 427, 72);

        getContentPane().add(cmbTipoMaterial);
        cmbTipoMaterial.setBounds(108, 74, 159, 20);

        getContentPane().add(cmbUnidadMedida);
        cmbUnidadMedida.setBounds(103, 108, 164, 20);

        lblNroMateriaPrima.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroMateriaPrima.setText("...");
        getContentPane().add(lblNroMateriaPrima);
        lblNroMateriaPrima.setBounds(109, 39, 107, 14);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(10, 325, 504, 10);
        getContentPane().add(botones);
        botones.setBounds(10, 341, 504, 44);
        getContentPane().add(dccFechaBaja);
        dccFechaBaja.setBounds(356, 204, 140, 20);
        getContentPane().add(dccFechaAlta);
        dccFechaAlta.setBounds(75, 204, 140, 20);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(0, 0, 524, 25);

        jLabel3.setText("Stock:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(310, 74, 30, 20);

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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblNroMateriaPrima;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatosMateriaPrima(MateriaprimaDB mp) {

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
