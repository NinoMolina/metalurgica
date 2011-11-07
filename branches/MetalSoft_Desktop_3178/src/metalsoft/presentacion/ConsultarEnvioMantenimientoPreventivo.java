/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultarEnvioMantenimientoPreventivo.java
 *
 * Created on 13/10/2011, 20:36:59
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import metalsoft.datos.jpa.entity.Proveedormantenimientomaquina;
import metalsoft.negocio.gestores.GestorMantenimiento;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.presentacion.RegistrarEnvioMantenimientoPreventivo.MantenimientoPreventivoTableModel;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class ConsultarEnvioMantenimientoPreventivo extends javax.swing.JDialog {

    /** Creates new form ConsultarEnvioMantenimientoPreventivo */
    private List<Detallemantenimientopreventivo> listaDetalle;
    private List<Mantenimientopreventivo> listaMantenimientos;
    private GestorMantenimiento gestor;

    public ConsultarEnvioMantenimientoPreventivo() {
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
        tblDetalleMantenimiento.setHorizontalScrollEnabled(true);
        /* On dit de surligner une ligne sur deux */
        tblDetalleMantenimiento.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //Trabajos 
        tblMantenimientos.setModel(new MantenimientoPreventivoTableModel());
        tblMantenimientos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblMantenimientos.setShowHorizontalLines(false);
        tblMantenimientos.setShowVerticalLines(false);
        tblMantenimientos.setHorizontalScrollEnabled(true); 
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
            dccFechaEnvio.setDate(man.getFechaenviomantenimiento());
            dccFechaPrevista.setDate(man.getFechamantenimientoprevisto());
            lblPeriodo.setText(String.valueOf(man.getPeriodo() + " días"));
            listaDetalle = gestor.obtenerDetalleDeMantenimiento(String.valueOf(man.getIdmantenimientopreventivo()));
            tblDetalleMantenimiento.updateUI();
            tblDetalleMantenimiento.packAll();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleMantenimiento = new org.jdesktop.swingx.JXTable();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
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
        setTitle("Mantenimientos Preventivos enviados");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mantenimientos Preventivos Encontrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        jLabel7.setText("Fecha de envío a Empresa:");

        jLabel9.setText("Período:");

        dccFechaPrevista.setEnabled(false);

        dccFechaEnvio.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblNroMantenimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dccFechaPrevista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaquina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(22, 22, 22)
                                .addComponent(lblDuracionTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(lblPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(695, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNroMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(lblPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(lblDuracionTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(dccFechaPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel7))
                                    .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Mantenimientos Preventivos enviados:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        buttonGroup1.add(rbNro);
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

        buttonGroup1.add(rbFecha);
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

        buttonGroup1.add(rbMaquina);
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

        buttonGroup1.add(rbProveedor);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtNro, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbMaquina)
                    .addComponent(rbProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbmaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbProveedores, cmbmaquina, txtNro});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbMaquina)
                        .addComponent(cmbmaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbNro)
                            .addComponent(txtNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbFecha)
                            .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(rbProveedor)
                            .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(750, 750, 750)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel2, jPanel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tblMantenimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMantenimientosMouseClicked
    // TODO add your handling code here:
    if (tblMantenimientos.getSelectedRow() >= 0) {
        btnSeleccionar1.getBtnSeleccionar().setEnabled(true);
    }

}//GEN-LAST:event_tblMantenimientosMouseClicked

private void tblDetalleMantenimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMantenimientoMouseClicked
}//GEN-LAST:event_tblDetalleMantenimientoMouseClicked

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
    listaMantenimientos = gestor.buscarEnviadosPorNro(txtNro.getText());
    tblMantenimientos.updateUI();
    tblMantenimientos.packAll();
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
        listaMantenimientos = gestor.buscarEnviadosEntreFechas(inicio, fin);
        tblMantenimientos.updateUI();
        tblMantenimientos.packAll();
    }
}//GEN-LAST:event_rbFechaActionPerformed

private void txtFechaAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaAltaActionPerformed
// TODO add your handling code here:
    String inicio = Fecha.parseToString(txtFechaAlta.getDate(), "dd/MM/yyyy");
    String fin = Fecha.parseToString(txtFechaBaja.getDate(), "dd/MM/yyyy");
    listaMantenimientos = gestor.buscarEnviadosEntreFechas(inicio, fin);
    tblMantenimientos.updateUI();
    tblMantenimientos.packAll();
}//GEN-LAST:event_txtFechaAltaActionPerformed

private void txtFechaBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaBajaActionPerformed
// TODO add your handling code here:
    String inicio = Fecha.parseToString(txtFechaAlta.getDate(), "dd/MM/yyyy");
    String fin = Fecha.parseToString(txtFechaBaja.getDate(), "dd/MM/yyyy");
    listaMantenimientos = gestor.buscarEnviadosEntreFechas(inicio, fin);
    tblMantenimientos.updateUI();
    tblMantenimientos.packAll();
}//GEN-LAST:event_txtFechaBajaActionPerformed

private void cmbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedoresActionPerformed
// TODO add your handling code here:
    if (cmbProveedores.getSelectedIndex() > 0) {
        String nro = ((ItemCombo) cmbProveedores.getSelectedItem()).getId();
        if (!nro.equals("-1")) {
            listaMantenimientos = gestor.buscarEnviadosPorProveedor(nro);
            tblMantenimientos.updateUI();
            tblMantenimientos.packAll();
        }
    }
}//GEN-LAST:event_cmbProveedoresActionPerformed

private void rbMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMaquinaActionPerformed
// TODO add your handling code here:
    txtFechaAlta.setEnabled(false);
    txtFechaBaja.setEnabled(false);
    txtNro.setEnabled(false);
    txtNro.setText("");
    cmbProveedores.setEnabled(false);
    cmbmaquina.setEnabled(true);
}//GEN-LAST:event_rbMaquinaActionPerformed

private void rbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbProveedorActionPerformed
// TODO add your handling code here:
    txtFechaAlta.setEnabled(false);
    txtFechaBaja.setEnabled(false);
    txtNro.setEnabled(false);
    txtNro.setText("");
    cmbProveedores.setEnabled(true);
    cmbmaquina.setEnabled(false);
}//GEN-LAST:event_rbProveedorActionPerformed

private void cmbmaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmaquinaActionPerformed
    if (cmbmaquina.getSelectedIndex() > 0){
        String nro = ((ItemCombo) cmbmaquina.getSelectedItem()).getId();
        if (!nro.equals("-1")) {
            listaMantenimientos = gestor.buscarEnviadosPorMaquina(nro);
            tblMantenimientos.updateUI();
            tblMantenimientos.packAll();
        }
    }
}//GEN-LAST:event_cmbmaquinaActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarEnvioMantenimientoPreventivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarEnvioMantenimientoPreventivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarEnvioMantenimientoPreventivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarEnvioMantenimientoPreventivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ConsultarEnvioMantenimientoPreventivo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private metalsoft.beans.BtnSeleccionar btnSeleccionar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbProveedores;
    private javax.swing.JComboBox cmbmaquina;
    private org.jdesktop.swingx.JXDatePicker dccFechaEnvio;
    private org.jdesktop.swingx.JXDatePicker dccFechaPrevista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
