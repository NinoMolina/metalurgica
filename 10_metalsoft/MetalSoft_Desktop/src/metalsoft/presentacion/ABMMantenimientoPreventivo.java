/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMMantenimientoPreventivo.java
 *
 * Created on 15/08/2011, 18:17:30
 */
package metalsoft.presentacion;

import java.util.Date;
import metalsoft.util.Fecha;
import metalsoft.util.EnumOpcionesABM;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.util.ItemCombo;
import java.lang.Object;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detallemantenimientocorrectivo;
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import metalsoft.negocio.mantmaquinarias.MantenimientoPreventivo;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import metalsoft.datos.jpa.entity.Servicio;
import metalsoft.negocio.gestores.GestorMantenimientoPreventivo;
import metalsoft.negocio.gestores.GestorServicioMaquina;

import metalsoft.negocio.mantmaquinarias.TipoMaquina;
import metalsoft.negocio.mantmaquinarias.Maquina;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.Combo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Lorreine Prescott
 */
public class ABMMantenimientoPreventivo extends javax.swing.JFrame {

    private MantenimientoPreventivo mantenimientop;
    private GestorMantenimientoPreventivo gestor;
    private GestorServicioMaquina gestorserv;
    private Maquina maquina;
    private Servicio servicio;
    private EnumOpcionesABM opcion;
    private LinkedList<Detallemantenimientopreventivo> filasservicios;
    private long idMantenimientoPreventivo;

    /** Creates new form ABMMantenimientoPreventivo */
    public ABMMantenimientoPreventivo() {
        initComponents();
        gestorserv = new GestorServicioMaquina();
        gestor = new GestorMantenimientoPreventivo();
        cargarComboTipoMaquina();
        cargarComboServicios();
        addListeners();
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
        filasservicios= new LinkedList<Detallemantenimientopreventivo>();

        tblServicios.setModel(new ServicioTableModel());
        tblServicios.setColumnControlVisible(true);
        tblServicios.setShowHorizontalLines(false);
        tblServicios.setShowVerticalLines(false);
        tblServicios.setHighlighters(new UIColorHighlighter(HighlightPredicate.ODD));
        InhabilitarComponentes();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new metalsoft.beans.ABM_Botones();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbTipoMaquina = new javax.swing.JComboBox();
        cmbMaquina = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        cmbServicio = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtPeriodo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtfechaMantenimiento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblNroMantenimientoP = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        beanBtnSeleccionar = new metalsoft.beans.BtnSeleccionar();
        btnBuscarServicio = new javax.swing.JButton();
        btnMaquina = new javax.swing.JButton();
        btnTipoMaquina1 = new javax.swing.JButton();
        beanBtnQuitar = new metalsoft.beans.BtnQuitar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new org.jdesktop.swingx.JXTable();
        btnCalcularMantenimiento = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        txtDuracionTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mantenimiento Preventivo");

        jLabel1.setText("Tipos de Máquina:");

        jLabel2.setText("Máquinas:");

        cmbTipoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoMaquinaActionPerformed(evt);
            }
        });

        cmbMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaquinaActionPerformed(evt);
            }
        });

        jLabel3.setText("Servicios de Máquina:");

        jLabel4.setText("Ingrese Período:");

        jLabel5.setText("días");

        jLabel6.setText("Fecha Próximo Mantenimiento:");

        jLabel7.setText("Nro. de Mantenimiento:");

        lblNroMantenimientoP.setText("...");

        jLabel9.setText("Descripción:");

        jLabel10.setText("Ingrese Duración Servicio:");

        jLabel11.setText("horas");

        btnBuscarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sea1.png"))); // NOI18N
        btnBuscarServicio.setText("Consultar");
        btnBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarServicioActionPerformed(evt);
            }
        });

        btnMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new1.png"))); // NOI18N
        btnMaquina.setText("Agregar");
        btnMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaquinaActionPerformed(evt);
            }
        });

        btnTipoMaquina1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new1.png"))); // NOI18N
        btnTipoMaquina1.setText("Agregar");
        btnTipoMaquina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoMaquina1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tblServicios);

        btnCalcularMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/detalle.gif"))); // NOI18N
        btnCalcularMantenimiento.setText("Calcular Próx. Mantenimiento");
        btnCalcularMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularMantenimientoActionPerformed(evt);
            }
        });

        jLabel8.setText("Duración Total:");

        jLabel12.setText("Observaciones:");

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel7)
                .addGap(7, 7, 7)
                .addComponent(lblNroMantenimientoP, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(btnTipoMaquina1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addGap(74, 74, 74)
                .addComponent(cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(btnMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)
                        .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)
                        .addComponent(btnBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(beanBtnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(225, 225, 225)
                .addComponent(beanBtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addGap(21, 21, 21)
                .addComponent(btnCalcularMantenimiento))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel6)
                .addGap(13, 13, 13)
                .addComponent(txtfechaMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtDuracionTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblNroMantenimientoP))
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTipoMaquina1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnMaquina)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnBuscarServicio))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(beanBtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addComponent(btnCalcularMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtfechaMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtDuracionTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addListeners() {

        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        addListenerBtnEliminar();
        addListenerBtnSeleccionar();
        addListenerBtnQuitar();
        addListenerBtnCalcularMantenimiento();

    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnEliminar() {
        botones.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        
        boolean ok = gestor.eliminarMantenimientoPreventivo(mantenimientop);
        if (ok) {
        JOptionPane.showMessageDialog(this, "Eliminación Realizada");
        } else {
        JOptionPane.showMessageDialog(this, "La eliminación NO se pudo realizar..");
        }

         
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.NUEVO;
        long nro = obtenerNuevoNroMantenimientoPreventivo();
        if (nro > 0) {
            lblNroMantenimientoP.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_MANTENIMIENTO_PREVENTIVO, nro)); 
            mantenimientop = new MantenimientoPreventivo();
            lblFecha.setText(String.valueOf(Fecha.fechaActual()));
            HabilitarComponentes();
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo generar el nro de mantenimiento preventivo.");
        }
    }

    private void addListenerBtnGuardar() {
       
        botones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

        public void actionPerformed(java.awt.event.ActionEvent evt) {
         btnGuardarActionPerformed(evt);
        }
        });

         
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
        /*
        opcion = EnumOpcionesABM.BUSCAR;
        ABMEmpresaMetalurgica_Buscar buscar = null;
        try {
        buscar = (ABMEmpresaMetalurgica_Buscar) JFrameManager.crearVentana(ABMEmpresaMetalurgica_Buscar.class.getName());
        buscar.setVentana(this);
        buscar.setGestor(gestor);

        } catch (ClassNotFoundException ex) {
        Logger.getLogger(ABMEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
        Logger.getLogger(ABMEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
        Logger.getLogger(ABMEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }

         */
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

     private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (!esValido()) {
            JOptionPane.showMessageDialog(this, "No se han ingresado todos los datos requeridos");
            return;
        }

        idMantenimientoPreventivo = -1;

        switch (opcion) {
            case NUEVO:
                mantenimientop = nuevoMantenimientoPreventivo();
//                idMantenimientoPreventivo = gestor.guardarMantenimientoPreventivo(mantenimientop);
                break;
            case MODIFICAR:
//                mantenimientop = modificarMantenimientoPreventivo();
//                idMantenimientoPreventivo = gestor.modificarMantenimientoPreventivo(mantenimientop);
                break;
            default:
                break;
        }
        opcion = EnumOpcionesABM.GUARDAR;

        if (idMantenimientoPreventivo > 0) {
            JOptionPane.showMessageDialog(this, "El Mantenimiento Preventivo se guardó correctamente");
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
          

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el Mantenimiento Preventivo");
        }
    }


    private void cmbTipoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoMaquinaActionPerformed
        cmbMaquina.removeAllItems();
        if (cmbTipoMaquina.getSelectedIndex() > 0) {
            String indexString = ((ItemCombo) cmbTipoMaquina.getSelectedItem()).getId();
            int index = Integer.parseInt(indexString);
            gestor.obtenerMaquinas(cmbMaquina, index);
        }
    }//GEN-LAST:event_cmbTipoMaquinaActionPerformed

    private void btnBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarServicioActionPerformed
    }//GEN-LAST:event_btnBuscarServicioActionPerformed

    private void btnMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaquinaActionPerformed
        try {
            JFrameManager.crearVentana(ABMMaquina.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnMaquinaActionPerformed

    private void btnTipoMaquina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoMaquina1ActionPerformed
        try {
            JFrameManager.crearVentana(ABMTipoMaquina.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMMantenimientoPreventivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTipoMaquina1ActionPerformed

    private void btnCalcularMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularMantenimientoActionPerformed
        
        if (txtPeriodo.getText().compareTo("")== 0 ) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el período!");
            return;
         }
        if (txtDuracion.getText().compareTo("")== 0){
            JOptionPane.showMessageDialog(this, "Debe ingresar la duración!");
            return;
        }

        Calendar calendario= Calendar.getInstance();
        calendario.add(Calendar.DATE,Integer.parseInt(txtPeriodo.getText()) );
       
        txtfechaMantenimiento.setText(String.valueOf(calendario.getTime()));
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnNuevo().setEnabled(false);
        botones.getBtnBuscar().setEnabled(false);
}//GEN-LAST:event_btnCalcularMantenimientoActionPerformed

    private void cmbMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMaquinaActionPerformed

    private void cargarComboTipoMaquina() {
        cmbTipoMaquina.removeAllItems();
        gestor.obtenerTiposMaquinas(cmbTipoMaquina);
    }

    private void cargarComboServicios() {
        cmbServicio.removeAllItems();
        gestor.obtenerServicios(cmbServicio);
    }

    private void addListenerCmbTipoMaquina() {
        cmbTipoMaquina.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoMaquinaActionPerformed(evt);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMMantenimientoPreventivo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnQuitar beanBtnQuitar;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionar;
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JButton btnBuscarServicio;
    private javax.swing.JButton btnCalcularMantenimiento;
    private javax.swing.JButton btnMaquina;
    private javax.swing.JButton btnTipoMaquina1;
    private javax.swing.JComboBox cmbMaquina;
    private javax.swing.JComboBox cmbServicio;
    private javax.swing.JComboBox cmbTipoMaquina;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNroMantenimientoP;
    private org.jdesktop.swingx.JXTable tblServicios;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtDuracionTotal;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPeriodo;
    private javax.swing.JTextField txtfechaMantenimiento;
    // End of variables declaration//GEN-END:variables

    private void addListenerBtnSeleccionar() {
        beanBtnSeleccionar.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {
        long id = Long.parseLong(((ItemCombo) cmbServicio.getSelectedItem()).getId());
        if (id == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un servicio..!");
            return;
        }
        Detallemantenimientopreventivo detalle = new Detallemantenimientopreventivo();
        detalle.setServicio(gestor.buscarServicioSeleccionado(Long.parseLong(((ItemCombo)cmbServicio.getSelectedItem()).getId())));
        detalle.setDuracion(Fecha.parseToDate(txtDuracion.getText()));
        detalle.setObservaciones(txtObservaciones.getText());

        filasservicios.add(detalle);
        tblServicios.updateUI();
    }


    private long obtenerNuevoNroMantenimientoPreventivo() {
        return gestor.generarNvoNroMantenimientoPreventivo();
    }

    private void addListenerbeanBtnSeleccionar() {
        beanBtnSeleccionar.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
    }

    class ServicioTableModel extends AbstractTableModel {

        String[] columnName = {"Nombre", "Descripción", "Duración", "Observaciones"};

        public int getRowCount() {
          if (filasservicios != null)
              return filasservicios.size();
          return 0;
        }

        public int getColumnCount() {
           return columnName.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Detallemantenimientopreventivo detalle = filasservicios.get(rowIndex);
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

        }
        @Override
        public String getColumnName(int column)
        {
            return columnName[column];
        }
    }

    private void addListenerBtnQuitar() {
        beanBtnQuitar.getBtnQuitar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
    }

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {
        filasservicios.remove(tblServicios.getSelectedRow());
        tblServicios.updateUI();
        if (tblServicios.getRowCount()<= 0)
            beanBtnQuitar.getBtnQuitar().setEnabled(false);
    }

       private void addListenerBtnCalcularMantenimiento() {
        btnCalcularMantenimiento.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularMantenimientoActionPerformed(evt);
            }
        });
    }

        private boolean esValido() {
        boolean result = true;
        if (cmbTipoMaquina.getSelectedIndex()< 0) {
            result = false;
        }
        if (cmbMaquina.getSelectedIndex() <= 0) {
            result = false;
        }

        if (txtPeriodo.getText().compareTo("") == 0) {
            result = false;
        }
        if (txtDuracion.getText().compareTo("") == 0) {
            result = false;
        }
         if (txtfechaMantenimiento.getText().compareTo("") == 0) {
            result = false;
        }
        if (tblServicios.getRowCount()<= 0) {
            result = false;
        }

        return result;
    }


         public MantenimientoPreventivo nuevoMantenimientoPreventivo() {

        mantenimientop = new MantenimientoPreventivo();

        long nro = NumerosAMostrar.getNumeroLong(lblNroMantenimientoP.getText());
        long idMaquina = Long.parseLong(((ItemCombo) cmbMaquina.getSelectedItem()).getId());
        metalsoft.datos.jpa.entity.Maquina maq = gestor.obtenerMaquina(idMaquina);

        String periodo= txtPeriodo.getText();
        long duracion= Long.parseLong(txtDuracion.getText());
//        Date fechaProxMantenimiento= txtfechaMantenimiento.getDate();
//
//        ViewDetalleMantenimientoPreventivo view = filasservicios.get(tblServicios.getSelectedRow());
//        long idPed = view.getIdservicio();
//
//        mantenimientop.setNroMantenimientoPreventivo(BigInteger.valueOf(nro));
//        mantenimientop.setMaquina(maq);
//        mantenimientop.setPeriodo(periodo);
//        mantenimientop.serDuracion(duracion);
//        mantenimientop.setFechaProxMantenimiento(fechaProxMantenimiento);
//        pxe.setEtapas(new LinkedList<ViewEtapaDeProduccion>(filasEtapaProduccionSeleccionada));
        
        return mantenimientop;
    }


         private void InhabilitarComponentes(){
             cmbTipoMaquina.setEnabled(false);
             cmbMaquina.setEnabled(false);
             cmbServicio.setEnabled(false);
             btnBuscarServicio.setEnabled(false);
             btnTipoMaquina1.setEnabled(false);
             btnMaquina.setEnabled(false);
             txtDuracion.setEnabled(false);
             txtDuracionTotal.setEnabled(false);
             txtPeriodo.setEnabled(false);
             txtfechaMantenimiento.setEnabled(false);
             tblServicios.setEnabled(false);
             btnCalcularMantenimiento.setEnabled(false);
             beanBtnQuitar.setEnabled(false);
             beanBtnSeleccionar.setEnabled(false);
         }

           private void HabilitarComponentes(){
             cmbTipoMaquina.setEnabled(true);
             cmbMaquina.setEnabled(true);
             cmbServicio.setEnabled(true);
             btnBuscarServicio.setEnabled(true);
             btnTipoMaquina1.setEnabled(true);
             btnMaquina.setEnabled(true);
             txtDuracion.setEnabled(true);
             txtDuracionTotal.setEnabled(true);
             txtPeriodo.setEnabled(true);
             txtfechaMantenimiento.setEnabled(true);
             tblServicios.setEnabled(true);
             btnCalcularMantenimiento.setEnabled(true);
             beanBtnQuitar.setEnabled(true);
             beanBtnSeleccionar.setEnabled(true);
         }
}