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
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.Fecha;

/**
 *
 * @author Vicky
 */
public class ABMEtapaDeProduccion extends javax.swing.JFrame {

    private GestorEtapaDeProduccion gestor;
    private EtapadeproduccionDB etapaDeProduccionDB;
    private long idEtapaDeProduccion=-1;
    private EnumOpcionesABM opcion;
    /** Creates new form ABMEtapaDeProduccion */
    public ABMEtapaDeProduccion() {
        initComponents();
        gestor=new GestorEtapaDeProduccion();
        cargarComboMaquina();

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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txthorasmaquina = new javax.swing.JTextField();
        txthorashombre = new javax.swing.JTextField();
        txtduracion = new javax.swing.JTextField();
        txtunidadmedida = new javax.swing.JTextField();
        txtFechaCreacion = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        cmbmaquinas = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtNroEtapa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre Etapa:");

        jLabel2.setText("Máquina:");

        jLabel3.setText("Horas Máquina:");

        jLabel4.setText("Horas Hombre:");

        jLabel5.setText("Duracón estimada por unidad de medida:");

        jLabel6.setText("Fecha de Creación:");

        jLabel7.setText("Unidad de Medida:");

        txtunidadmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtunidadmedidaActionPerformed(evt);
            }
        });

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel8.setText("Numero Etapa:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(btnnuevo)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnModificar)
                        .add(8, 8, 8)
                        .add(btnBuscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 100, Short.MAX_VALUE)
                        .add(btnguardar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnSalir))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txthorasmaquina, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txthorashombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtduracion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtunidadmedida, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtFechaCreacion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                            .add(jLabel8)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(txtNroEtapa))
                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                            .add(jLabel2)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(cmbmaquinas, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                            .add(jLabel1)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(txtnombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(txtNroEtapa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(txtnombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(cmbmaquinas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(txthorasmaquina, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(txthorashombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(txtduracion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(txtunidadmedida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(txtFechaCreacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnSalir)
                    .add(btnguardar)
                    .add(btnnuevo)
                    .add(btnModificar)
                    .add(btnBuscar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarDatosEtapaDeProduccion(EtapadeproduccionDB ep) {
        txtFechaCreacion.setText(String.valueOf(ep.getFechacreacion()));
        txtNroEtapa.setText(String.valueOf(ep.getNroetapaproduccion()));
        txtduracion.setText(String.valueOf(ep.getDuracionestimada()));
        txthorashombre.setText(String.valueOf(ep.getHorashombre()));
        txthorasmaquina.setText(String.valueOf(ep.getHorasmaquina()));
        txtnombre.setText(String.valueOf(ep.getNombre()));
        txtunidadmedida.setText(String.valueOf(ep.getDuracionestimada()));
        Combo.setItemComboSeleccionado(cmbmaquinas, ep.getMaquina());
    }

    private void txtunidadmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtunidadmedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtunidadmedidaActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        opcion=EnumOpcionesABM.NUEVO;
        limpiarCampos();
}//GEN-LAST:event_btnnuevoActionPerformed
public void limpiarCampos()
{
    txtFechaCreacion.setText("");
    txtNroEtapa.setText("");
    txtduracion.setText("");
    txthorashombre.setText("");
    txthorasmaquina.setText("");
    txtnombre.setText("");
    txtunidadmedida.setText("");
    cmbmaquinas.setSelectedIndex(-1);
}
    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        EtapaDeProduccion ep=new EtapaDeProduccion();
        ep.setDuracionEstimadaXUnidMed(Fecha.parseToDate(txtduracion.getText(),"hh:mm:ss"));
        ep.setFechaCreacion(Fecha.parseToDate(txtFechaCreacion.getText()));
        ep.setHorasHombre(Fecha.parseToDate(txthorashombre.getText(),"hh:mm:ss"));
        ep.setHorasMaquina(Fecha.parseToDate(txtduracion.getText(),"hh:mm:ss"));
        ep.setNombre(txtnombre.getText());
        ep.setNumeroEtapa(Long.parseLong(txtNroEtapa.getText()));
        long id;
        if(opcion==EnumOpcionesABM.NUEVO)
        {   
            id=gestor.guardarEtapaDeProduccion(ep,((ItemCombo)cmbmaquinas.getSelectedItem()).getId());
            if(id>-1)JOptionPane.showMessageDialog(this, "Se Guardó la siguiente Etapa de Produccion: "+txtnombre.getText());
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
        }

        if(opcion==EnumOpcionesABM.MODIFICAR)
        {
            id=gestor.modificarEtapaDeProduccion(ep,idEtapaDeProduccion,((ItemCombo)cmbmaquinas.getSelectedItem()).getId());
            if(id>-1)JOptionPane.showMessageDialog(this, "Se modifico la siguiente Etapa de Produccion: "+txtnombre.getText());
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron modificar");
        }
        limpiarCampos();
}//GEN-LAST:event_btnguardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
    opcion=EnumOpcionesABM.MODIFICAR;
}//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ABMEtapaDeProduccion_Buscar buscar=null;
        try {
            buscar=(ABMEtapaDeProduccion_Buscar) JFrameManager.crearVentana(ABMEtapaDeProduccion_Buscar.class.getName());
            buscar.setVentana(this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

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
    private javax.swing.JComboBox cmbmaquinas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtFechaCreacion;
    private javax.swing.JTextField txtNroEtapa;
    private javax.swing.JTextField txtduracion;
    private javax.swing.JTextField txthorashombre;
    private javax.swing.JTextField txthorasmaquina;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtunidadmedida;
    // End of variables declaration//GEN-END:variables

}
