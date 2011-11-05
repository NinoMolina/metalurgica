/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMEtapaDeProduccion.java
 *
 * Created on 21/08/2010, 02:06:05
 */

package metalsoft.presentacion;

import java.awt.Color;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor.STRING;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorEtapaDeProduccion;
import metalsoft.util.Combo;
import metalsoft.negocio.ventas.EtapaDeProduccion;
import metalsoft.datos.dbobject.EtapadeproduccionDB;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.Fecha;

/**
 *
 * @author Vicky
 */
public class ABMEtapaDeProduccion extends javax.swing.JDialog {

    private GestorEtapaDeProduccion gestor;
    private EtapadeproduccionDB etapaDeProduccionDB;
    private long idEtapaDeProduccion=-1;
    private EnumOpcionesABM opcion;
    private long nroEtapa;
    /** Creates new form ABMEtapaDeProduccion */
    public ABMEtapaDeProduccion() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor=new GestorEtapaDeProduccion();
        cargarComboMaquina();
        cargarComboUnidadMedida();
        setComponentes(false);
        dccFecha.setEnabled(false);
        lblFormat.setEnabled(false);
    }

    public void etapaSeleccionada() {
        etapaDeProduccionDB=gestor.buscarEtapaDeProduccionId(idEtapaDeProduccion);
        mostrarDatosEtapaDeProduccion(etapaDeProduccionDB);
    }

    public void setIdEtapa(long id) {
        idEtapaDeProduccion=id;
    }

    private void cargarComboMaquina() {
        cmbmaquinas.removeAllItems();
        gestor.obtenerMaquinas(cmbmaquinas);
    }
    private void cargarComboUnidadMedida()
    {
        cmbUnidadMedida.removeAllItems();
        gestor.obternerUnidadMedida(cmbUnidadMedida);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbmaquinas = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtduracion = new javax.swing.JTextField();
        cmbUnidadMedida = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNroEtapa = new javax.swing.JLabel();
        dccFecha = new com.toedter.calendar.JDateChooser();
        lblFormat = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Etapa de Producción");

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/new1.png"))); // NOI18N
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/save1.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/mod1.png"))); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/exit1.png"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/sea1.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Etapa de Producción"));

        jLabel8.setText("Número Etapa:");

        jLabel1.setText("Nombre Etapa:");

        jLabel2.setText("Máquina:");

        jLabel5.setText("Duración estimada por Unidad de Medida:");

        jLabel7.setText("Unidad de Medida:");

        jLabel6.setText("Fecha de Creación:");

        lblNroEtapa.setText("...");

        lblFormat.setText("hh:mm:ss");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel7)
                        .add(8, 8, 8)
                        .add(cmbUnidadMedida, 0, 333, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel8)
                            .add(jLabel1)
                            .add(jLabel2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, lblNroEtapa)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, txtnombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, cmbmaquinas, 0, 352, Short.MAX_VALUE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(txtduracion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(lblFormat))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(dccFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(lblNroEtapa))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(txtnombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(cmbmaquinas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(txtduracion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblFormat))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(cmbUnidadMedida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel6)
                    .add(dccFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 482, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(btnnuevo)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnguardar)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnModificar)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 153, Short.MAX_VALUE)
                .add(btnBuscar)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnSalir)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btnnuevo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnguardar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnModificar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnSalir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnBuscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarDatosEtapaDeProduccion(EtapadeproduccionDB ep) {
        long fecha=ep.getFechacreacion().getTime();
        Date fechaCreacion=Fecha.parseToDate(fecha);
        dccFecha.setDate(fechaCreacion);
        lblNroEtapa.setText(String.valueOf(ep.getNroetapaproduccion()));
        txtduracion.setText(String.valueOf(ep.getDuracionestimada()));
//        txthorashombre.setText(String.valueOf(ep.getHorashombre()));
//        txthorasmaquina.setText(String.valueOf(ep.getHorasmaquina()));
        txtnombre.setText(String.valueOf(ep.getNombre()));
        
        if(ep.getTipomaquina()<1) Combo.setItemComboSeleccionado(cmbmaquinas, -1);
        else Combo.setItemComboSeleccionado(cmbmaquinas, ep.getTipomaquina());
        if(ep.getUnidadmedida()<1) Combo.setItemComboSeleccionado(cmbUnidadMedida, -1);
        else Combo.setItemComboSeleccionado(cmbUnidadMedida, ep.getUnidadmedida());
    }

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        opcion=EnumOpcionesABM.NUEVO;
        limpiarCampos();
        nroEtapa = gestor.generarNvoNroEtapa();
        lblNroEtapa.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_ETAPA_PRODUCCION, nroEtapa));
        dccFecha.setDate(Fecha.fechaActualDate());
        setComponentes(true);
        Combo.setItemComboSeleccionado(cmbUnidadMedida, 2);
        Combo.setItemComboSeleccionado(cmbmaquinas, -1);
}//GEN-LAST:event_btnnuevoActionPerformed

    private void setComponentes(boolean b)
    {        
        txtduracion.setEnabled(b);
//        txthorashombre.setEnabled(b);
//        txthorasmaquina.setEnabled(b);
        txtnombre.setEnabled(b);
        cmbUnidadMedida.setEnabled(b);
        cmbmaquinas.setEnabled(b);
    }


    public void limpiarCampos()
{
    dccFecha.setDate(null);
    txtduracion.setText("");
//    txthorashombre.setText("");
//    txthorasmaquina.setText("");
    txtnombre.setText("");
    cmbUnidadMedida.setSelectedIndex(-1);
    cmbmaquinas.setSelectedIndex(-1);
}
    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        EtapaDeProduccion ep=new EtapaDeProduccion();
        ep.setDuracionEstimadaXUnidMed(Fecha.parseToDate(txtduracion.getText(),"hh:mm:ss"));
        ep.setFechaCreacion(dccFecha.getDate());
//        ep.setHorasHombre(Fecha.parseToDate(txthorashombre.getText(),"hh:mm:ss"));
//        ep.setHorasMaquina(Fecha.parseToDate(txthorasmaquina.getText(),"hh:mm:ss"));
        ep.setNombre(txtnombre.getText());
        ep.setNumeroEtapa(nroEtapa);
        long id;
        if(opcion==EnumOpcionesABM.NUEVO)
        {   
            id=gestor.guardarEtapaDeProduccion(ep,((ItemCombo)cmbmaquinas.getSelectedItem()).getId(),((ItemCombo)cmbUnidadMedida.getSelectedItem()).getId());
            if(id>-1)
            {
                JOptionPane.showMessageDialog(this, "Se Guardó la siguiente Etapa de Produccion: "+txtnombre.getText());
                setComponentes(false);
                limpiarCampos();
            }
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
        }

        if(opcion==EnumOpcionesABM.MODIFICAR)
        {
            id=gestor.modificarEtapaDeProduccion(ep,idEtapaDeProduccion,((ItemCombo)cmbmaquinas.getSelectedItem()).getId(),((ItemCombo)cmbUnidadMedida.getSelectedItem()).getId());
            if(id>-1)
            {
                JOptionPane.showMessageDialog(this, "Se modifico la siguiente Etapa de Produccion: "+txtnombre.getText());
                setComponentes(false);
                limpiarCampos();
            }
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron modificar");
        }
        limpiarCampos();
}//GEN-LAST:event_btnguardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
    opcion=EnumOpcionesABM.MODIFICAR;
}//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       ABMEtapaDeProduccion_Buscar buscar=null;
        buscar = new ABMEtapaDeProduccion_Buscar(this);
        buscar.setVentana(this);
        JFrameManager.centrarYMostrarVentana(buscar);buscar.setVentana(this);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMEtapaDeProduccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JComboBox cmbUnidadMedida;
    private javax.swing.JComboBox cmbmaquinas;
    private com.toedter.calendar.JDateChooser dccFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFormat;
    private javax.swing.JLabel lblNroEtapa;
    private javax.swing.JTextField txtduracion;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables

}
