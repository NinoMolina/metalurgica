/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AdministrarUsuario.java
 *
 * Created on 28/08/2011, 23:28:17
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.negocio.gestores.GestorNuevoUsuario;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class AdministrarUsuario extends javax.swing.JDialog {

    private GestorNuevoUsuario gestor;
    private List<Rol> filasRolesModificar;
    private List<Rol> filasRolesDelUsuario;
    private List<Rol> filasRolesEliminar;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /** Creates new form AdministrarUsuario */
    public AdministrarUsuario() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorNuevoUsuario();
        filasRolesEliminar = new LinkedList<Rol>();
        cargarComboUsuarios();
        setearTablas();
        addListeners();
    }

    private void addListeners() {
        btnSalir.getBtnSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAgregar.getBtnAgregar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnAgregarActionPerformed(e);
            }
        });

        btnQuitar.getBtnQuitar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnQuitarActionPerformed(e);
            }
        });

        btnGuardar.getBtnGuardar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformed(e);
            }
        });

    }

    private void btnAgregarActionPerformed(ActionEvent e) {
        if (cmbRoles.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un rol");
            return;
        }
        Rol rol = gestor.buscarRol(Long.parseLong(((ItemCombo) cmbRoles.getSelectedItem()).getId()));
        if (!filasRolesModificar.contains(rol)) {
            ItemCombo item = (ItemCombo) cmbRoles.getSelectedItem();
            filasRolesModificar.add(gestor.getRolSeleccionado(item.getId()));
            cmbRoles.removeItem(item);
            tblRoles.updateUI();
            tblRoles.packAll();
            if (filasRolesEliminar.contains(rol)) {
                filasRolesEliminar.remove(rol);
            }
        }
    }

    private void btnQuitarActionPerformed(ActionEvent e) {
        Rol rol = filasRolesModificar.remove(tblRoles.getSelectedRow());
        ItemCombo item = new ItemCombo(String.valueOf(rol.getIdrol()), rol.getRol());

        cmbRoles.addItem(item);
        tblRoles.updateUI();
        tblRoles.packAll();
        if (!filasRolesEliminar.contains(rol)&&filasRolesDelUsuario.contains(rol)) {
            filasRolesEliminar.add(rol);
        }
    }

    private void btnGuardarActionPerformed(ActionEvent e) {
        long idUsuario = gestor.modificarUsuarioXRol(usuario, filasRolesModificar);
        long id = gestor.eliminarUsuarioXRol(usuario, filasRolesEliminar);
        if (idUsuario > 0 && id > 0) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente!");
        } else {
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar...");
        }
    }

    private void setearTablas() {
        tblRoles.setModel(new RolTableModel());
        tblRoles.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblRoles.setShowHorizontalLines(false);
        tblRoles.setShowVerticalLines(false);
        tblRoles.setHorizontalScrollEnabled(true); 
        /* On dit de surligner une ligne sur deux */
        tblRoles.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void cargarComboRoles(long id) {
        cmbRoles.removeAllItems();
        gestor.cargarComboRoles(cmbRoles, id);
    }

    private void cargarComboUsuarios() {
        cmbUsuarios.removeAllItems();
        gestor.cargarComboUsuarios(cmbUsuarios);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbRoles = new javax.swing.JComboBox();
        btnAgregar = new metalsoft.beans.BtnAgregar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRoles = new org.jdesktop.swingx.JXTable();
        btnQuitar = new metalsoft.beans.BtnQuitar();
        lblUsuarioDisponible = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox();
        btnGuardar = new metalsoft.beans.BtnGuardar();
        btnSalir = new metalsoft.beans.BtnSalirr();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Usuarios");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Usuario:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Roles"));

        jScrollPane1.setViewportView(tblRoles);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbRoles, 0, 330, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnQuitar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cmbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsuarioDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        if (cmbUsuarios.getSelectedIndex() > 0) {
            usuario = gestor.buscarUsuario(Long.parseLong(((ItemCombo) cmbUsuarios.getSelectedItem()).getId()));
            filasRolesModificar = gestor.buscarRolesUsuario(usuario);
            filasRolesDelUsuario = gestor.buscarRolesUsuario(usuario);
            tblRoles.updateUI();
            tblRoles.packAll();
            cargarComboRoles(usuario.getIdusuario());
        }
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AdministrarUsuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnAgregar btnAgregar;
    private metalsoft.beans.BtnGuardar btnGuardar;
    private metalsoft.beans.BtnQuitar btnQuitar;
    private metalsoft.beans.BtnSalirr btnSalir;
    private javax.swing.JComboBox cmbRoles;
    private javax.swing.JComboBox cmbUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsuarioDisponible;
    private org.jdesktop.swingx.JXTable tblRoles;
    // End of variables declaration//GEN-END:variables

    class RolTableModel extends AbstractTableModel {

        String[] columnNames = {"Rol",
            "Descripcion"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            Rol rol = filasRolesModificar.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return rol.getRol();
                case 1:
                    return rol.getDescripcion();
                default:
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
            if (filasRolesModificar != null) {
                return filasRolesModificar.size();
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
}
