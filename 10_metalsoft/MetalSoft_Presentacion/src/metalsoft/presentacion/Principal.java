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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        obtenerRolUsuario(idUsuario);
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

        mbrMenu = new javax.swing.JMenuBar();
        mnuInicio = new javax.swing.JMenu();
        mnuCompras = new javax.swing.JMenu();
        mnuVentas = new javax.swing.JMenu();
        mniCliente = new javax.swing.JMenuItem();
        mniProducto = new javax.swing.JMenuItem();
        mnuProduccion = new javax.swing.JMenu();
        mnuCalidad = new javax.swing.JMenu();
        mnuFinanzas = new javax.swing.JMenu();
        mnuRRHH = new javax.swing.JMenu();
        mniListadoClientes = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mnuInicio.setText("Inicio");
        mbrMenu.add(mnuInicio);

        mnuCompras.setText("Compras");
        mbrMenu.add(mnuCompras);

        mnuVentas.setText("Ventas");

        mniCliente.setText("Cliente");
        mniCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniClienteActionPerformed(evt);
            }
        });
        mnuVentas.add(mniCliente);

        mniProducto.setText("Producto");
        mniProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProductoActionPerformed(evt);
            }
        });
        mnuVentas.add(mniProducto);

        mbrMenu.add(mnuVentas);

        mnuProduccion.setText("Producción");
        mbrMenu.add(mnuProduccion);

        mnuCalidad.setText("Calidad");
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
            .addGap(0, 411, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
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

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mbrMenu;
    private javax.swing.JMenuItem mniCliente;
    private javax.swing.JMenuItem mniListadoClientes;
    private javax.swing.JMenuItem mniProducto;
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
        this.setTitle("METALSOFT - INICIO [Rol: "+roles[0].getRol()+"]");
    }

}
