/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarParadaDeMaquina.java
 *
 * Created on 04/09/2011, 20:14:17
 */
package metalsoft.presentacion;

import common.Logger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.negocio.gestores.GestorParadaDeMaquina;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class RegistrarParadaDeMaquina extends javax.swing.JDialog {

    /** Creates new form RegistrarParadaDeMaquina */
    private GestorParadaDeMaquina gestor;
    private List<Ejecucionplanificacionproduccion> listaPedidos;

    public RegistrarParadaDeMaquina() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorParadaDeMaquina();
        addListeners();
        setearTablas();
        cargarCombos();
        btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
        btnGuardar1.getBtnGuardar().setEnabled(false);
        listaPedidos = gestor.obtenerListaPedidos();
        lblnro.setText("...");
    }

    private void addListeners() {
        addListenerBtnSalir();
        addListenerBtnGuardar();
        addListenerBtnSeleccionar();
        addListenerBtnAgregar();
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
    private void addListenerBtnAgregar() {
        btnAgregar1.getBtnAgregar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JFrameManager.crearVentana(ABMRotura.class.getName());
            cmbRoturas.removeAllItems();
            gestor.cargarComboRoturas(cmbRoturas);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrarParadaDeMaquina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarParadaDeMaquina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarParadaDeMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void addListenerBtnGuardar() {
        btnGuardar1.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblPedidos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido!");
            return;
        }
        if (((ItemCombo) cmbMaquinas.getSelectedItem()).getId().equals("-1")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una máquina!");
            return;
        }
        if (((ItemCombo) cmbEmpleados.getSelectedItem()).getId().equals("-1")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Empleado Responsable!");
            return;
        }
        if (((ItemCombo) cmbRoturas.getSelectedItem()).getId().equals("-1")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una rotura!");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String novedad = Fecha.fechaHomaMinutoSegundoActualParaNovedades()
                + " --> PARADA DE MÁQUINA: Se ha registrado la parada de la máquina " + ((ItemCombo) cmbMaquinas.getSelectedItem()).getMostrar().toUpperCase()
                + " pudiendo ser la causa " + ((ItemCombo) cmbRoturas.getSelectedItem()).getMostrar().toUpperCase()
                + ", mientras el empleado " + ((ItemCombo) cmbEmpleados.getSelectedItem()).getMostrar().toUpperCase() + " se encontraba a cargo.";
        novedad += "\n\n";
        Ejecucionplanificacionproduccion v = listaPedidos.get(tblPedidos.getSelectedRow());
        if(v.getNovedades()==null)
            v.setNovedades(novedad);
        else
            v.setNovedades(v.getNovedades() + novedad);
        boolean result = gestor.guardadParadaDeMaquina(v);
        if (result) {
            JOptionPane.showMessageDialog(this, "Se ha registrado la parada de la máquina");
            cmbEmpleados.setSelectedIndex(0);
            cmbRoturas.setSelectedIndex(0);
            cmbMaquinas.setSelectedIndex(0);
            btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
            btnGuardar1.getBtnGuardar().setEnabled(false);
            lblnro.setText("...");
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido registrar la parada de la máquina");
            cmbEmpleados.setSelectedIndex(0);
            cmbRoturas.setSelectedIndex(0);
            cmbMaquinas.setSelectedIndex(0);
            btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
            btnGuardar1.getBtnGuardar().setEnabled(false);
            lblnro.setText("...");
        }
        //guardo las novedades en la ejecucion.
    }

    private void addListenerBtnSeleccionar() {
        btnSeleccionar1.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblPedidos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido!");
            return;
        }
        Ejecucionplanificacionproduccion v = listaPedidos.get(tblPedidos.getSelectedRow());
        lblnro.setText("EJEC-"+v.getNroejecucionplanificacion());
    }

    public void cargarCombos() {
        gestor.cargarComboEmpleado(cmbEmpleados);
        gestor.cargarComboMaquinas(cmbMaquinas);
        gestor.cargarComboRoturas(cmbRoturas);
    }

    private void setearTablas() {
        // PEDIDO
        tblPedidos.setModel(new PedidosEnProduccionTableModel());
        tblPedidos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedidos.setShowHorizontalLines(false);
        tblPedidos.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblPedidos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
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
        jLabel2 = new javax.swing.JLabel();
        cmbMaquinas = new javax.swing.JComboBox();
        cmbEmpleados = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbRoturas = new javax.swing.JComboBox();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        btnGuardar1 = new metalsoft.beans.BtnGuardar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new org.jdesktop.swingx.JXTable();
        btnSeleccionar1 = new metalsoft.beans.BtnSeleccionar();
        btnAgregar1 = new metalsoft.beans.BtnAgregar();
        jLabel4 = new javax.swing.JLabel();
        lblnro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Parada de Máquina");

        jLabel1.setText("Seleccionar Máquina:");

        jLabel2.setText("Seleccionar Empleado:");

        jLabel3.setText("Seleccione la Rotura:");

        btnGuardar1.setToolTipText("Guardar Parada de Maquina");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos en Producción"));

        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(496, Short.MAX_VALUE)
                .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Pedido Seleccionado:");

        lblnro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblnro.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEmpleados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbRoturas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblnro)
                        .addGap(88, 88, 88))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblnro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbRoturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
    btnSeleccionar1.getBtnSeleccionar().setEnabled(true);
    btnGuardar1.getBtnGuardar().setEnabled(true);
}//GEN-LAST:event_tblPedidosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarParadaDeMaquina().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnAgregar btnAgregar1;
    private metalsoft.beans.BtnGuardar btnGuardar1;
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private metalsoft.beans.BtnSeleccionar btnSeleccionar1;
    private javax.swing.JComboBox cmbEmpleados;
    private javax.swing.JComboBox cmbMaquinas;
    private javax.swing.JComboBox cmbRoturas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblnro;
    private org.jdesktop.swingx.JXTable tblPedidos;
    // End of variables declaration//GEN-END:variables

    class PedidosEnProduccionTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro. Ejecución",
            "Nro. Planificación",
            "Fecha Inicio",
            "Hora Inicio",
            "Fecha Fin",
            "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            Ejecucionplanificacionproduccion view = (Ejecucionplanificacionproduccion) listaPedidos.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return "EJEC-" + String.valueOf(view.getNroejecucionplanificacion());
                case 1:
                    return "PLAN-" + String.valueOf(view.getIdplanificacionproduccion().getNroplanificacion());
                case 2:
                    return Fecha.parseToString(view.getFechainicio());
                case 3:
                    return Fecha.parseToHourMinuteSecond(view.getHorainicio());
                case 4:
                    if (view.getFechafin() == null) {
                        return "";
                    } else {
                        return String.valueOf(view.getFechafin());
                    }
                case 5:
                    return view.getEstado().getNombre();
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
            if (listaPedidos != null) {
                return listaPedidos.size();
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
