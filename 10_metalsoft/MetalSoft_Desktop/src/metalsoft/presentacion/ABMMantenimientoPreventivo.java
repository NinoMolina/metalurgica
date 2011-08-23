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
import javax.swing.*;
import metalsoft.negocio.mantmaquinarias.MantenimientoPreventivo;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;
import metalsoft.negocio.gestores.GestorMantenimientoPreventivo;
import metalsoft.negocio.mantenimiento.ServicioMaquina;
import metalsoft.negocio.mantmaquinarias.TipoMaquina;
import metalsoft.negocio.mantmaquinarias.Maquina;
import metalsoft.util.Combo;


/**
 *
 * @author Lorreine Prescott
 */
public class ABMMantenimientoPreventivo extends javax.swing.JFrame {


    private MantenimientoPreventivo mantenimientop;
    private GestorMantenimientoPreventivo gestor;
    private Maquina maquina;
    private ServicioMaquina servicio;
    private EnumOpcionesABM opcion;



    /** Creates new form ABMMantenimientoPreventivo */
    public ABMMantenimientoPreventivo() {
        initComponents();
        gestor = new GestorMantenimientoPreventivo();
        cargarComboTipoMaquina();
        cargarComboServicios();
        addListeners();
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);

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
        btnTipoMaquina = new javax.swing.JButton();
        btnMaquina = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        cmbServicio = new javax.swing.JComboBox();
        btnServicio = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtPeriodo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtfechaMantenimiento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleMantenimientoPreventivo = new javax.swing.JTable();
        txtDuracion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnQuitar = new javax.swing.JButton();
        beanBtnSeleccionar = new metalsoft.beans.BtnSeleccionar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mantenimiento Preventivo");

        jLabel1.setText("Tipos de Máquina:");

        jLabel2.setText("Máquinas:");

        cmbTipoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoMaquinaActionPerformed(evt);
            }
        });

        btnTipoMaquina.setText("Agregar");

        btnMaquina.setText("Agregar");

        jLabel3.setText("Servicios de Máquina:");

        btnServicio.setText("Consultar");

        jLabel4.setText("Ingrese Período:");

        jLabel5.setText("días");

        jLabel6.setText("Fecha Próximo Mantenimiento:");

        jLabel7.setText("Nro. de Mantenimiento:");

        jLabel8.setText("...");

        jLabel9.setText("Descripción:");

        tblDetalleMantenimientoPreventivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDetalleMantenimientoPreventivo);

        jLabel10.setText("Ingrese Duración:");

        jLabel11.setText("horas");

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4)
                        .addGap(40, 40, 40)
                        .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6)
                        .addGap(13, 13, 13)
                        .addComponent(txtfechaMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addGap(36, 36, 36))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(137, 137, 137)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnQuitar))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(beanBtnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(btnMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(beanBtnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuitar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtfechaMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
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
/*
        boolean ok = gestor.eliminarMantenimientoPreventivo(mantenimientop);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Eliminación Realizada");
        } else {
            JOptionPane.showMessageDialog(this, "La eliminación NO se pudo realizar..");
        }
 
 */
    }


    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
/*
        mantenimientop = new MantenimientoPreventivo();
        opcion = EnumOpcionesABM.NUEVO;
        long nroCli = gestor.generarNvoNroEmpresa();
        lblNroCliente.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_EMPRESA_METALURGICA, nroCli));
        txtFechaAlta.setDate(Fecha.fechaActualDate());
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);

 */
    }

    private void addListenerBtnGuardar() {
        /*
        botones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

         */
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

    private void cmbTipoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoMaquinaActionPerformed
        cmbMaquina.removeAllItems();
        if (cmbTipoMaquina.getSelectedIndex() > 0) {
            String indexString = ((ItemCombo) cmbTipoMaquina.getSelectedItem()).getId();
            int index = Integer.parseInt(indexString);
            gestor.obtenerMaquinas(cmbMaquina, index);
        }
    }//GEN-LAST:event_cmbTipoMaquinaActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarActionPerformed

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
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionar;
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JButton btnMaquina;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnServicio;
    private javax.swing.JButton btnTipoMaquina;
    private javax.swing.JComboBox cmbMaquina;
    private javax.swing.JComboBox cmbServicio;
    private javax.swing.JComboBox cmbTipoMaquina;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblDetalleMantenimientoPreventivo;
    private javax.swing.JTextField txtDuracion;
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
        /*
        if ((ItemCombo)cmbServicio.getSelectedItem().getId().equals("-1"))
            JOptionPane.showMessageDialog(this, "Debe seleccionar un servicio..!");
            return;
        }
    
        viewPedidoSeleccionado = filasPedidosNoPlanificados.get(tblPedidos.getSelectedRow());
        presupuesto = gestor.buscarPresupuesto(viewPedidoSeleccionado.getIdpresupuesto());
        setVisiblePanel(pnlTreeTable.getName());
        setEnabledComponents(true);
        cargarDatosTreeTable(presupuesto.getDetallepresupuestoList());
 
     */
    }

}
