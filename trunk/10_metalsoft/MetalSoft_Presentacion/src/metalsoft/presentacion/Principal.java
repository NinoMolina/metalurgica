/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 12/06/2010, 02:19:22
 */

package metalsoft.presentacion;

import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.adminusuarios.Rol;
import metalsoft.negocio.adminusuarios.Usuario;




/**
 *
 * @author Nino
 */
public class Principal extends javax.swing.JFrame {

    private long idUsuario;
    private Rol[] roles;
    /** Creates new form Principal */
    public Principal(long idUsuario) {
        this.idUsuario=idUsuario;
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/img/m.jpg")).getImage());
        
        obtenerRolUsuario(idUsuario);
        //this.getContentPane().setBackground();
    }
    public Principal() {
        initComponents();

    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImagen = new javax.swing.JLabel();
        mbrMenu = new javax.swing.JMenuBar();
        mnuInicio = new javax.swing.JMenu();
        mnuCompras = new javax.swing.JMenu();
        mniMateriaPrima = new javax.swing.JMenuItem();
        mniProveedor = new javax.swing.JMenuItem();
        mniRegistrarPresupuesto = new javax.swing.JMenuItem();
        mnuVentas = new javax.swing.JMenu();
        mniCliente = new javax.swing.JMenuItem();
        mniPieza = new javax.swing.JMenuItem();
        mniProducto = new javax.swing.JMenuItem();
        mniPresupuesto = new javax.swing.JMenuItem();
        mniEtapaDeProduccion = new javax.swing.JMenuItem();
        mnuProduccion = new javax.swing.JMenu();
        mniTipoMaterial = new javax.swing.JMenuItem();
        mniMatriz = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        mniGenerarDetalleMateriaPrima = new javax.swing.JMenuItem();
        mnuCalidad = new javax.swing.JMenu();
        mniGenerarDetalleProcedimientosCalidad = new javax.swing.JMenuItem();
        mnuFinanzas = new javax.swing.JMenu();
        mnuRRHH = new javax.swing.JMenu();
        mniListadoClientes = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(240, 240, 240));

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/background.jpg"))); // NOI18N

        mnuInicio.setText("Inicio");
        mbrMenu.add(mnuInicio);

        mnuCompras.setText("Compras");

        mniMateriaPrima.setText("Materia Prima");
        mniMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMateriaPrimaActionPerformed(evt);
            }
        });
        mnuCompras.add(mniMateriaPrima);

        mniProveedor.setText("Proveedor");
        mniProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProveedorActionPerformed(evt);
            }
        });
        mnuCompras.add(mniProveedor);

        mniRegistrarPresupuesto.setText("Presupuestar");
        mniRegistrarPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRegistrarPresupuestoActionPerformed(evt);
            }
        });
        mnuCompras.add(mniRegistrarPresupuesto);

        mbrMenu.add(mnuCompras);

        mnuVentas.setText("Ventas");
        mnuVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVentasActionPerformed(evt);
            }
        });

        mniCliente.setText("Cliente");
        mniCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniClienteActionPerformed(evt);
            }
        });
        mnuVentas.add(mniCliente);

        mniPieza.setText("Pieza");
        mniPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPiezaActionPerformed(evt);
            }
        });
        mnuVentas.add(mniPieza);

        mniProducto.setText("Producto");
        mniProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProductoActionPerformed(evt);
            }
        });
        mnuVentas.add(mniProducto);

        mniPresupuesto.setText("Pedido");
        mniPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPresupuestoActionPerformed(evt);
            }
        });
        mnuVentas.add(mniPresupuesto);

        mniEtapaDeProduccion.setText("Etapa de Producción");
        mniEtapaDeProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEtapaDeProduccionActionPerformed(evt);
            }
        });
        mnuVentas.add(mniEtapaDeProduccion);

        mbrMenu.add(mnuVentas);

        mnuProduccion.setText("Producción");

        mniTipoMaterial.setText("Tipo Material");
        mniTipoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTipoMaterialActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniTipoMaterial);

        mniMatriz.setText("Matriz");
        mniMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMatrizActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniMatriz);

        jMenuItem1.setText("Generar Listado Procedimientos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuProduccion.add(jMenuItem1);

        mniGenerarDetalleMateriaPrima.setText("Generar Detalle Materia Prima");
        mniGenerarDetalleMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGenerarDetalleMateriaPrimaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mniGenerarDetalleMateriaPrima);

        mbrMenu.add(mnuProduccion);

        mnuCalidad.setText("Calidad");

        mniGenerarDetalleProcedimientosCalidad.setText("Generar Detalle Procedimientos Calidad");
        mniGenerarDetalleProcedimientosCalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGenerarDetalleProcedimientosCalidadActionPerformed(evt);
            }
        });
        mnuCalidad.add(mniGenerarDetalleProcedimientosCalidad);

        mbrMenu.add(mnuCalidad);

        mnuFinanzas.setText("Finanzas");
        mbrMenu.add(mnuFinanzas);

        mnuRRHH.setText("RRHH");

        mniListadoClientes.setText("Listado Clientes");
        mniListadoClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniListadoClientesActionPerformed(evt);
            }
        });
        mnuRRHH.add(mniListadoClientes);

        mbrMenu.add(mnuRRHH);

        mnuAyuda.setText("Ayuda");
        mbrMenu.add(mnuAyuda);

        setJMenuBar(mbrMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 610, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniListadoClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniListadoClientesActionPerformed


    }//GEN-LAST:event_mniListadoClientesActionPerformed

    private void mniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniClienteActionPerformed
        try {
            JFrameManager.crearVentana(ABMCliente.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniClienteActionPerformed

    private void mniProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProductoActionPerformed
        try {
            JFrameManager.crearVentana(ABMProducto.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniProductoActionPerformed

    private void mnuVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuVentasActionPerformed

    private void mniPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPresupuestoActionPerformed
        try {
            JFrameManager.crearVentana(ABMPedidoCotizacion.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniPresupuestoActionPerformed

    private void mniPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPiezaActionPerformed
        try {
            JFrameManager.crearVentana(ABMPieza.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniPiezaActionPerformed

    private void mniEtapaDeProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEtapaDeProduccionActionPerformed
        try {
            JFrameManager.crearVentana(ABMEtapaDeProduccion.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniEtapaDeProduccionActionPerformed

    private void mniTipoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTipoMaterialActionPerformed
        try {
            JFrameManager.crearVentana(ABMTipoMaterial.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniTipoMaterialActionPerformed

    private void mniMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMatrizActionPerformed
        try {
            JFrameManager.crearVentana(ABMMatriz.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniMatrizActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            JFrameManager.crearVentana(GenerarDetalleProcedimientosCotización.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mniMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMateriaPrimaActionPerformed
        try {
            JFrameManager.crearVentana(ABMMateriaPrima.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniMateriaPrimaActionPerformed

    private void mniGenerarDetalleProcedimientosCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGenerarDetalleProcedimientosCalidadActionPerformed
        try {
            JFrameManager.crearVentana(GenerarDetalleProcesosCalidad.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniGenerarDetalleProcedimientosCalidadActionPerformed

    private void mniGenerarDetalleMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGenerarDetalleMateriaPrimaActionPerformed
        try {
            JFrameManager.crearVentana(GenerarDetalleMateriaPrima.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniGenerarDetalleMateriaPrimaActionPerformed

    private void mniProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProveedorActionPerformed
         try {
            JFrameManager.crearVentana(ABMProveedor.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniProveedorActionPerformed

    private void mniRegistrarPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRegistrarPresupuestoActionPerformed
        try {
            JFrameManager.crearVentana(RegistrarPresupuesto.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniRegistrarPresupuestoActionPerformed

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JMenuBar mbrMenu;
    private javax.swing.JMenuItem mniCliente;
    private javax.swing.JMenuItem mniEtapaDeProduccion;
    private javax.swing.JMenuItem mniGenerarDetalleMateriaPrima;
    private javax.swing.JMenuItem mniGenerarDetalleProcedimientosCalidad;
    private javax.swing.JMenuItem mniListadoClientes;
    private javax.swing.JMenuItem mniMateriaPrima;
    private javax.swing.JMenuItem mniMatriz;
    private javax.swing.JMenuItem mniPieza;
    private javax.swing.JMenuItem mniPresupuesto;
    private javax.swing.JMenuItem mniProducto;
    private javax.swing.JMenuItem mniProveedor;
    private javax.swing.JMenuItem mniRegistrarPresupuesto;
    private javax.swing.JMenuItem mniTipoMaterial;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenu mnuCalidad;
    private javax.swing.JMenu mnuCompras;
    private javax.swing.JMenu mnuFinanzas;
    private javax.swing.JMenu mnuInicio;
    private javax.swing.JMenu mnuProduccion;
    private javax.swing.JMenu mnuRRHH;
    private javax.swing.JMenu mnuVentas;
    // End of variables declaration//GEN-END:variables

    public void obtenerRolUsuario(long idUsuario) {
        roles=Usuario.obtenerRoles(idUsuario);
        setTitle("METALSOFT - INICIO [Rol: "+roles[0].getRol()+"]");
    }

}
