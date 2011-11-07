/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BotonRapidoReportes.java
 *
 * Created on 31/10/2011, 00:28:08
 */

package metalsoft.presentacion;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorreine Prescott
 */
public class BotonRapidoReportes extends javax.swing.JDialog {

      private static BotonRapidoReportes vtnBotonRapido = null;

    /** Creates new form BotonRapidoReportes */
    public BotonRapidoReportes() {

         super(Principal.getVtnPrincipal());
        initComponents();
        vtnBotonRapido = this;
    }

     public static BotonRapidoReportes getVtnBotonRapido() {
        return vtnBotonRapido;
    }

    public static void setVtnBotonRapido(BotonRapidoReportes vtnBotonRapido) {
        BotonRapidoReportes.vtnBotonRapido = vtnBotonRapido;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnReporteAusentismo = new javax.swing.JButton();
        btnReporteCobro = new javax.swing.JButton();
        btnReporteCliente = new javax.swing.JButton();
        btnReporteMateriaPrima = new javax.swing.JButton();
        btnReporteMantenimiento = new javax.swing.JButton();
        btnReporteEmpleado = new javax.swing.JButton();
        btnReporteTrabajo = new javax.swing.JButton();
        btnReporteReclamo = new javax.swing.JButton();
        btnReporteProveedor = new javax.swing.JButton();
        btnReporteDefecto = new javax.swing.JButton();
        btnReporteProduccion = new javax.swing.JButton();
        btnReportePedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes");

        btnReporteAusentismo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteAusentismo.setText("Reporte Ausentismo");
        btnReporteAusentismo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteAusentismo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteAusentismo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteAusentismoActionPerformed(evt);
            }
        });

        btnReporteCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteCobro.setText("Reportes Cobros");
        btnReporteCobro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteCobro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteCobroActionPerformed(evt);
            }
        });

        btnReporteCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteCliente.setText("Reportes Clientes");
        btnReporteCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteClienteActionPerformed(evt);
            }
        });

        btnReporteMateriaPrima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteMateriaPrima.setText("Reportes Materias Primas");
        btnReporteMateriaPrima.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteMateriaPrima.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteMateriaPrimaActionPerformed(evt);
            }
        });

        btnReporteMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteMantenimiento.setText("Reportes Mantenimientos");
        btnReporteMantenimiento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteMantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteMantenimientoActionPerformed(evt);
            }
        });

        btnReporteEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteEmpleado.setText("Reportes Empleados");
        btnReporteEmpleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteEmpleadoActionPerformed(evt);
            }
        });

        btnReporteTrabajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteTrabajo.setText("Reportes Trabajos Tercerizados");
        btnReporteTrabajo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteTrabajo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteTrabajoActionPerformed(evt);
            }
        });

        btnReporteReclamo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteReclamo.setText("Reportes Reclamos");
        btnReporteReclamo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteReclamo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteReclamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteReclamoActionPerformed(evt);
            }
        });

        btnReporteProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteProveedor.setText("Reportes Proveedores");
        btnReporteProveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteProveedor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteProveedorActionPerformed(evt);
            }
        });

        btnReporteDefecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteDefecto.setText("Reportes Defectos");
        btnReporteDefecto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteDefecto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnReporteProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReporteProduccion.setText("Reportes Producción");
        btnReporteProduccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReporteProduccion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnReportePedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/canstock7137940.jpg"))); // NOI18N
        btnReportePedido.setText("Reportes Pedidos");
        btnReportePedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReportePedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReportePedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportePedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReporteAusentismo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReporteProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportePedido, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteReclamo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteTrabajo))
                .addGap(18, 18, 18))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnReporteAusentismo, btnReporteCliente, btnReporteCobro, btnReporteDefecto, btnReporteEmpleado, btnReporteMantenimiento, btnReporteMateriaPrima, btnReportePedido, btnReporteProduccion, btnReporteProveedor, btnReporteReclamo, btnReporteTrabajo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteAusentismo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportePedido, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteReclamo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReporteTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteAusentismoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteAusentismoActionPerformed
        try {
            JFrameManager.crearVentana(ReporteAusentismo.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteAusentismoActionPerformed

    private void btnReporteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteClienteActionPerformed
       try {
            JFrameManager.crearVentana(Reportes.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteClienteActionPerformed

    private void btnReporteCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteCobroActionPerformed
        try {
            JFrameManager.crearVentana(ReportesCobros.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteCobroActionPerformed

    private void btnReporteEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteEmpleadoActionPerformed
       try {
            JFrameManager.crearVentana(ReportesEmpleados.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteEmpleadoActionPerformed

    private void btnReporteMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteMantenimientoActionPerformed
        try {
            JFrameManager.crearVentana(ReportesMaquinas.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteMantenimientoActionPerformed

    private void btnReporteMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteMateriaPrimaActionPerformed
        try {
            JFrameManager.crearVentana(ReportesMateriasPrimas.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteMateriaPrimaActionPerformed

    private void btnReporteTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteTrabajoActionPerformed
        try {
            JFrameManager.crearVentana(ReportesTrabajosTercerizados.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteTrabajoActionPerformed

    private void btnReporteReclamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteReclamoActionPerformed
      try {
            JFrameManager.crearVentana(ReportesReclamos.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteReclamoActionPerformed

    private void btnReporteProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteProveedorActionPerformed
        try {
            JFrameManager.crearVentana(ReportesProveedores.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteProveedorActionPerformed

    private void btnReportePedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportePedidoActionPerformed
        try {
            JFrameManager.crearVentana(ReportesPedidos.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReportePedidoActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BotonRapidoReportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporteAusentismo;
    private javax.swing.JButton btnReporteCliente;
    private javax.swing.JButton btnReporteCobro;
    private javax.swing.JButton btnReporteDefecto;
    private javax.swing.JButton btnReporteEmpleado;
    private javax.swing.JButton btnReporteMantenimiento;
    private javax.swing.JButton btnReporteMateriaPrima;
    private javax.swing.JButton btnReportePedido;
    private javax.swing.JButton btnReporteProduccion;
    private javax.swing.JButton btnReporteProveedor;
    private javax.swing.JButton btnReporteReclamo;
    private javax.swing.JButton btnReporteTrabajo;
    private javax.swing.JLabel jLabel14;
    // End of variables declaration//GEN-END:variables

}
