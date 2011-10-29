/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultarEnvioMantenimientoCorrectivo.java
 *
 * Created on 16/10/2011, 21:20:40
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo;
import metalsoft.datos.jpa.entity.Mantenimientocorrectivo;
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
public class ConsultarEnvioMantenimientoCorrectivo extends javax.swing.JDialog {

    /** Creates new form ConsultarEnvioMantenimientoCorrectivo */
    private List<Detallemantenimientocorrectivo> listaDetalle;
    private List<Mantenimientocorrectivo> listaMantenimientos;
    private GestorMantenimiento gestor;

    public ConsultarEnvioMantenimientoCorrectivo() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorMantenimiento();
        txtFechaAlta.setDate(Fecha.fechaActualDate());
        txtFechaBaja.setDate(Fecha.fechaActualDate());
        addListeners();
        setearTablas();
        btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
        txtFechaAlta.setEnabled(false);
        txtFechaBaja.setEnabled(false);
        txtNro.setEnabled(true);
        txtNro.setText("");
        cmbProveedores.setEnabled(false);
        cmbmaquina.setEnabled(false);
        rbNro.setSelected(true);
        cargarComboMaquinas();
        cargarComboProveedor();
    }

    public void cargarComboProveedor() {
        cmbProveedores.removeAllItems();
        gestor.obtenerProveedoresMantenimiento(cmbProveedores);
    }

    public void cargarComboMaquinas() {
        cmbmaquina.removeAllItems();
        gestor.obtenerMaquinas(cmbmaquina);
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
            Mantenimientocorrectivo man = listaMantenimientos.get(tblMantenimientos.getSelectedRow());
            int sumaTotal = 0;
            listaDetalle = gestor.obtenerDetalleDeMantenimientoCorrectivo(String.valueOf(man.getIdmantenimientocorrectivo()));
            for (Detallemantenimientocorrectivo de : listaDetalle) {
                sumaTotal += de.getDuracion();
            }
            lblDuracionTotal.setText(String.valueOf(sumaTotal));
            lblMaquina.setText(gestor.obtenerMaquina(man.getMaquina().longValue()).getNombre());
            lblNroMantenimiento.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MANTENIMIENTO_CORRECTIVO, man.getNromantenimientocorrectivo().longValue()));
            dccFechaEnvio.setDate(man.getFechaenviomantenimiento());
            
            tblDetalleMantenimiento.updateUI();
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        rbNro = new javax.swing.JRadioButton();
        txtNro = new javax.swing.JTextField();
        rbFecha = new javax.swing.JRadioButton();
        txtFechaAlta = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
        txtFechaBaja = new org.jdesktop.swingx.JXDatePicker();
        rbMaquina = new javax.swing.JRadioButton();
        cmbmaquina = new javax.swing.JComboBox();
        rbProveedor = new javax.swing.JRadioButton();
        cmbProveedores = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMantenimientos = new org.jdesktop.swingx.JXTable();
        btnSeleccionar1 = new metalsoft.beans.BtnSeleccionar();
        jLabel2 = new javax.swing.JLabel();
        lblDuracionTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMaquina = new javax.swing.JLabel();
        lblNroMantenimiento = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dccFechaEnvio = new org.jdesktop.swingx.JXDatePicker();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleMantenimiento = new org.jdesktop.swingx.JXTable();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        jLabel14 = new javax.swing.JLabel(){

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
        setTitle("Consulta de Mantenimientos correctivos enviados");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Mantenimientos Correctivos enviados:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        rbNro.setText("Nro. Mantenimiento:");
        rbNro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNroActionPerformed(evt);
            }
        });

        txtNro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroActionPerformed(evt);
            }
        });
        txtNro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNroKeyReleased(evt);
            }
        });

        rbFecha.setText("Fecha de envío entre:");
        rbFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFechaActionPerformed(evt);
            }
        });

        txtFechaAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaAltaActionPerformed(evt);
            }
        });

        jLabel8.setText("y");

        txtFechaBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaBajaActionPerformed(evt);
            }
        });

        rbMaquina.setText("Máquina:");
        rbMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMaquinaActionPerformed(evt);
            }
        });

        cmbmaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmaquinaActionPerformed(evt);
            }
        });

        rbProveedor.setText("Empresa de Mantenimiento:");
        rbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbProveedorActionPerformed(evt);
            }
        });

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbNro)
                    .addComponent(rbFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbMaquina)
                    .addComponent(rbProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbmaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtFechaAlta, txtFechaBaja});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbProveedores, cmbmaquina, txtNro});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(rbMaquina))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cmbmaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(rbProveedor)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(rbNro)
                            .addGap(3, 3, 3)
                            .addComponent(rbFecha))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mantenimientos Correctivos Encontrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblMantenimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMantenimientosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMantenimientos);

        jLabel2.setText("Seleccionar Mantenimiento a enviar:");

        jLabel6.setText("Duración total:");

        jLabel3.setText("Máquina:");

        jLabel1.setText("Nro. de Mantenimiento:");

        jLabel7.setText("Fecha de envío a Empresa:");

        dccFechaEnvio.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDuracionTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dccFechaEnvio, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSeleccionar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE))
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
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblNroMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblDuracionTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de Mantenimiento Correctivo seleccionado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(761, Short.MAX_VALUE)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void rbNroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNroActionPerformed
// TODO add your handling code here:
    txtFechaAlta.setEnabled(false);
    txtFechaBaja.setEnabled(false);
    txtNro.setEnabled(true);
    txtNro.setText("");
    cmbProveedores.setEnabled(false);
    cmbmaquina.setEnabled(false);
}//GEN-LAST:event_rbNroActionPerformed

private void txtNroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtNroActionPerformed

private void txtNroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroKeyReleased
// TODO add your handling code here:
    listaMantenimientos = gestor.buscarCorrectivosEnviadosPorNro(txtNro.getText());
    tblMantenimientos.updateUI();
}//GEN-LAST:event_txtNroKeyReleased

private void rbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFechaActionPerformed
// TODO add your handling code here:
    txtFechaAlta.setEnabled(true);
    txtFechaBaja.setEnabled(true);
    txtNro.setEnabled(false);
    txtNro.setText("");
    cmbProveedores.setEnabled(false);
    cmbmaquina.setEnabled(false);
    if (txtFechaAlta.getDate() != null && txtFechaBaja.getDate() != null) {
        String inicio = Fecha.parseToString(txtFechaAlta.getDate(), "dd/MM/yyyy");
        String fin = Fecha.parseToString(txtFechaBaja.getDate(), "dd/MM/yyyy");
        listaMantenimientos = gestor.buscarCorrectivosEnviadosEntreFechas(inicio, fin);
        tblMantenimientos.updateUI();
    }
}//GEN-LAST:event_rbFechaActionPerformed

private void txtFechaAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaAltaActionPerformed
// TODO add your handling code here:
    String inicio = Fecha.parseToString(txtFechaAlta.getDate(), "dd/MM/yyyy");
    String fin = Fecha.parseToString(txtFechaBaja.getDate(), "dd/MM/yyyy");
    listaMantenimientos = gestor.buscarCorrectivosEnviadosEntreFechas(inicio, fin);
    tblMantenimientos.updateUI();
}//GEN-LAST:event_txtFechaAltaActionPerformed

private void txtFechaBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaBajaActionPerformed
// TODO add your handling code here:
    String inicio = Fecha.parseToString(txtFechaAlta.getDate(), "dd/MM/yyyy");
    String fin = Fecha.parseToString(txtFechaBaja.getDate(), "dd/MM/yyyy");
    listaMantenimientos = gestor.buscarCorrectivosEnviadosEntreFechas(inicio, fin);
    tblMantenimientos.updateUI();
}//GEN-LAST:event_txtFechaBajaActionPerformed

private void rbMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMaquinaActionPerformed
// TODO add your handling code here:
    txtFechaAlta.setEnabled(false);
    txtFechaBaja.setEnabled(false);
    txtNro.setEnabled(false);
    txtNro.setText("");
    cmbProveedores.setEnabled(false);
    cmbmaquina.setEnabled(true);
}//GEN-LAST:event_rbMaquinaActionPerformed

private void cmbmaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmaquinaActionPerformed
    if (cmbmaquina.getSelectedIndex() > 0) {
        String nro = ((ItemCombo) cmbmaquina.getSelectedItem()).getId();
        if (!nro.equals("-1")) {
            listaMantenimientos = gestor.buscarCorrectivosEnviadosPorMaquina(nro);
            tblMantenimientos.updateUI();
        }
    }
}//GEN-LAST:event_cmbmaquinaActionPerformed

private void rbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbProveedorActionPerformed
// TODO add your handling code here:
    txtFechaAlta.setEnabled(false);
    txtFechaBaja.setEnabled(false);
    txtNro.setEnabled(false);
    txtNro.setText("");
    cmbProveedores.setEnabled(true);
    cmbmaquina.setEnabled(false);
}//GEN-LAST:event_rbProveedorActionPerformed

private void cmbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedoresActionPerformed
// TODO add your handling code here:
    if (cmbProveedores.getSelectedIndex() > 0) {
        String nro = ((ItemCombo) cmbProveedores.getSelectedItem()).getId();
        if (!nro.equals("-1")) {
            listaMantenimientos = gestor.buscarCorrectivosEnviadosPorProveedor(nro);
            tblMantenimientos.updateUI();
        }
    }
}//GEN-LAST:event_cmbProveedoresActionPerformed

private void tblMantenimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMantenimientosMouseClicked
    // TODO add your handling code here:
    if (tblMantenimientos.getSelectedRow() >= 0) {
        btnSeleccionar1.getBtnSeleccionar().setEnabled(true);
    }
}//GEN-LAST:event_tblMantenimientosMouseClicked

private void tblDetalleMantenimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMantenimientoMouseClicked
}//GEN-LAST:event_tblDetalleMantenimientoMouseClicked

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
            java.util.logging.Logger.getLogger(ConsultarEnvioMantenimientoCorrectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarEnvioMantenimientoCorrectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarEnvioMantenimientoCorrectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarEnvioMantenimientoCorrectivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ConsultarEnvioMantenimientoCorrectivo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private metalsoft.beans.BtnSeleccionar btnSeleccionar1;
    private javax.swing.JComboBox cmbProveedores;
    private javax.swing.JComboBox cmbmaquina;
    private org.jdesktop.swingx.JXDatePicker dccFechaEnvio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDuracionTotal;
    private javax.swing.JLabel lblMaquina;
    private javax.swing.JLabel lblNroMantenimiento;
    private javax.swing.JRadioButton rbFecha;
    private javax.swing.JRadioButton rbMaquina;
    private javax.swing.JRadioButton rbNro;
    private javax.swing.JRadioButton rbProveedor;
    private org.jdesktop.swingx.JXTable tblDetalleMantenimiento;
    private org.jdesktop.swingx.JXTable tblMantenimientos;
    private org.jdesktop.swingx.JXDatePicker txtFechaAlta;
    private org.jdesktop.swingx.JXDatePicker txtFechaBaja;
    private javax.swing.JTextField txtNro;
    // End of variables declaration//GEN-END:variables
    class MantenimientoPreventivoTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro. Mantenimiento", "Fecha Envío", "Maquina", "Proveedor"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (!listaMantenimientos.isEmpty()) {
                Mantenimientocorrectivo detalle = listaMantenimientos.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return String.valueOf(detalle.getNromantenimientocorrectivo());
                    case 1:
                        return Fecha.parseToString(detalle.getFechaenviomantenimiento(), "dd/MM/yyyy");
                    case 2:
                        return gestor.obtenerMaquina(detalle.getMaquina().longValue()).getNombre();
                    case 3:
                        if (detalle.getProveedormantenimiento() != null) {
                            return detalle.getProveedormantenimiento().getRazonsocial();
                        } else {
                            return "";
                        }
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
                Detallemantenimientocorrectivo detalle = listaDetalle.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return detalle.getRotura().getNombre();
                    case 1:
                        return detalle.getRotura().getDescripcion();
                    case 2:
                        return String.valueOf(detalle.getDuracion());
                    case 3:
                        return detalle.getMotivorotura();
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
