/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarEnvioMantenimientoPreventivo.java
 *
 * Created on 12/10/2011, 23:33:31
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.negocio.gestores.GestorMantenimiento;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class RegistrarEnvioMantenimientoPreventivo extends javax.swing.JDialog {

    /** Creates new form RegistrarEnvioMantenimientoPreventivo */
    private List<Detallemantenimientopreventivo> listaDetalle;
    private List<Mantenimientopreventivo> listaMantenimientos;
    private GestorMantenimiento gestor;

    public RegistrarEnvioMantenimientoPreventivo() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorMantenimiento();
        listaMantenimientos = gestor.buscarMantenimientosHastaFechaActual();
        tblMantenimientos.updateUI();
        cargarComboProveedor();
        addListeners();
        setearTablas();
        btnconfirmar.setEnabled(false);
        btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
    }

    private void setearTablas() {
        //Detalle 
        tblDetalleMantenimiento.setModel(new DetalleTableModel());
        tblDetalleMantenimiento.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleMantenimiento.setShowHorizontalLines(false);
        tblDetalleMantenimiento.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleMantenimiento.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //Trabajos 
        tblMantenimientos.setModel(new MantenimientoPreventivoTableModel());
        tblMantenimientos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblMantenimientos.setShowHorizontalLines(false);
        tblMantenimientos.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblMantenimientos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListeners() {
        addListenerBtnSeleccionarPedido();
        addListenerBtnSalir();
    }

    private void addListenerBtnSeleccionarPedido() {
        btnSeleccionar1.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionar1BeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionar1BeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblMantenimientos.getSelectedRow() > -1) {
            Mantenimientopreventivo man = listaMantenimientos.get(tblMantenimientos.getSelectedRow());
            lblDuracionTotal.setText(String.valueOf(man.getDuraciontotal()));
            lblMaquina.setText(gestor.obtenerMaquina(man.getMaquina().longValue()).getNombre());
            lblNroMantenimiento.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MANTENIMIENTO_PREVENTIVO, man.getNromantenimietno().longValue()));
            dccFechaEnvio.setDate(null);
            dccFechaPrevista.setDate(man.getFechamantenimientoprevisto());
            lblPeriodo.setText(String.valueOf(man.getPeriodo() + " días"));
            listaDetalle = gestor.obtenerDetalleDeMantenimiento(String.valueOf(man.getIdmantenimientopreventivo()));
            tblDetalleMantenimiento.updateUI();
            btnconfirmar.setEnabled(false);
        } else {
            btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
        }
    }

    private void addListenerBtnSalir() {
        btnSalirr1.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public void cargarComboProveedor() {
        cmbProveedores.removeAllItems();
        gestor.obtenerProveedoresMantenimiento(cmbProveedores);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleMantenimiento = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMantenimientos = new org.jdesktop.swingx.JXTable();
        btnSeleccionar1 = new metalsoft.beans.BtnSeleccionar();
        jLabel2 = new javax.swing.JLabel();
        lblDuracionTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMaquina = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNroMantenimiento = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        dccFechaPrevista = new org.jdesktop.swingx.JXDatePicker();
        dccFechaEnvio = new org.jdesktop.swingx.JXDatePicker();
        btnconfirmar = new javax.swing.JButton();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cmbProveedores = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel(){

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Envío a Mantenimiento Preventivo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de Mantenimiento Preventivo seleccionado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblDetalleMantenimiento.setEnabled(false);
        tblDetalleMantenimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMantenimientoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleMantenimiento);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mantenimientos Preventivos registrados para la fecha actual o anterior", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblMantenimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMantenimientosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMantenimientos);

        jLabel2.setText("Seleccionar Mantenimiento a enviar:");

        jLabel6.setText("Duración total:");

        jLabel3.setText("Máquina:");

        jLabel4.setText("Fecha de Mantenimiento Previsto:");

        jLabel1.setText("Nro. Mantenimiento:");

        jLabel7.setText("Fecha de Envío a Empresa:");

        jLabel9.setText("Período:");

        dccFechaPrevista.setEnabled(false);

        dccFechaEnvio.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dccFechaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblDuracionTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblNroMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lblMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(dccFechaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(lblPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(12, 12, 12)
                            .addComponent(lblDuracionTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnconfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/seleccionar.png"))); // NOI18N
        btnconfirmar.setText("Registrar Envío");
        btnconfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconfirmarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar Empresa proveedora del Servicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel5.setText("Empresa de Mantenimiento:");

        cmbProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnconfirmar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 454, Short.MAX_VALUE)
                                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel2, jPanel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconfirmar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tblDetalleMantenimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMantenimientoMouseClicked
}//GEN-LAST:event_tblDetalleMantenimientoMouseClicked

private void tblMantenimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMantenimientosMouseClicked
    // TODO add your handling code here:
    if (tblMantenimientos.getSelectedRow() > -1) {
        btnSeleccionar1.getBtnSeleccionar().setEnabled(true);
        if(!((ItemCombo)cmbProveedores.getSelectedItem()).getId().equals("-1")){
            btnconfirmar.setEnabled(true);
        }
    }
}//GEN-LAST:event_tblMantenimientosMouseClicked

private void btnconfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmarActionPerformed
    if (tblMantenimientos.getSelectedRow() > -1) {
        long result = 0;
        Mantenimientopreventivo man = listaMantenimientos.get(tblMantenimientos.getSelectedRow());
        man.setFechaenviomantenimiento(Fecha.parseToDate(Fecha.parseToString(Fecha.fechaActualDate(), "dd/MM/yyyy")));
        man.setEstado(gestor.buscarEstadoByID(2));
        Date hms=Fecha.fechaActualDate();
            
        man.setHoraenviomantenimiento(hms);
        Proveedormantenimientomaquina pro=gestor.getProveedorById(Long.parseLong(((ItemCombo)cmbProveedores.getSelectedItem()).getId()));
        man.setProveedormantenimiento(pro);
        result = gestor.registrarEnvioMantenimientoPreventivo(man);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "El envío se ha registrado correctamente");
            dccFechaEnvio.setDate(Fecha.fechaActualDate());
            listaMantenimientos = gestor.buscarMantenimientosHastaFechaActual();
            tblMantenimientos.updateUI();
            listaDetalle.clear();
            tblDetalleMantenimiento.updateUI();
            btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
            btnconfirmar.setEnabled(false);

        } else {
            JOptionPane.showMessageDialog(this, "El envío no ha podido ser enviado");
        }
    }
}//GEN-LAST:event_btnconfirmarActionPerformed

private void cmbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedoresActionPerformed
// TODO add your handling code here:
    if (tblMantenimientos.getSelectedRow() > -1) {
        if(!((ItemCombo)cmbProveedores.getSelectedItem()).getId().equals("-1")){
            btnconfirmar.setEnabled(true);
        }
    }
}//GEN-LAST:event_cmbProveedoresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrarEnvioMantenimientoPreventivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarEnvioMantenimientoPreventivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarEnvioMantenimientoPreventivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarEnvioMantenimientoPreventivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarEnvioMantenimientoPreventivo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private metalsoft.beans.BtnSeleccionar btnSeleccionar1;
    private javax.swing.JButton btnconfirmar;
    private javax.swing.JComboBox cmbProveedores;
    private org.jdesktop.swingx.JXDatePicker dccFechaEnvio;
    private org.jdesktop.swingx.JXDatePicker dccFechaPrevista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDuracionTotal;
    private javax.swing.JLabel lblMaquina;
    private javax.swing.JLabel lblNroMantenimiento;
    private javax.swing.JLabel lblPeriodo;
    private org.jdesktop.swingx.JXTable tblDetalleMantenimiento;
    private org.jdesktop.swingx.JXTable tblMantenimientos;
    // End of variables declaration//GEN-END:variables

    class MantenimientoPreventivoTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro. Mantenimiento", "Fecha Prevista", "Maquina", "Proveedor", "Período", "Duración horas"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (!listaMantenimientos.isEmpty()) {
                Mantenimientopreventivo detalle = listaMantenimientos.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return String.valueOf(detalle.getNromantenimietno());
                    case 1:
                        return Fecha.parseToString(detalle.getFechamantenimientoprevisto(), "dd/MM/yyyy");
                    case 2:
                        return gestor.obtenerMaquina(detalle.getMaquina().longValue()).getNombre();
                    case 3:
                        if (detalle.getProveedormantenimiento() != null) {
                            return detalle.getProveedormantenimiento().getRazonsocial();
                        } else {
                            return "";
                        }
                    case 4:
                        return String.valueOf(detalle.getPeriodo());
                    case 5:
                        return String.valueOf(detalle.getDuraciontotal());
                    default:
                        return null;
                }
            } else {
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
            if (listaMantenimientos != null) {
                return listaMantenimientos.size();
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

    class DetalleTableModel extends AbstractTableModel {

        String[] columnName = {"Nombre", "Descripción", "Duración", "Observaciones"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (!listaDetalle.isEmpty()) {
                Detallemantenimientopreventivo detalle = listaDetalle.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return detalle.getServicio().getNombre();
                    case 1:
                        return detalle.getServicio().getDescripcion();
                    case 2:
                        return String.valueOf(detalle.getDuracion());
                    case 3:
                        return detalle.getObservaciones();
                    default:
                        return null;
                }
            } else {
                return null;
            }


        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount() {
            return columnName.length;
        }

        public int getRowCount() {
            if (listaDetalle != null) {
                return listaDetalle.size();
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
            return columnName[column];

        }
    }
}
