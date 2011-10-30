/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultarRemitos.java
 *
 * Created on 02/09/2011, 15:22:28
 */

package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detalleremito;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Remito;
import metalsoft.negocio.gestores.GestorRemito;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class ConsultarRemitos extends javax.swing.JDialog {

    private List<Remito> filasRemito;
    private List<Detalleremito> filasDetalle;
    private GestorRemito gestor;
    private Pedido pedido;
    /** Creates new form ConsultarRemitos */
    public ConsultarRemitos() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorRemito();
        addListeners();
        setearTablas();
        btnImprimir.setEnabled(false);
        btnVerDetalle.setEnabled(false);
        dccFechaEmision.setEnabled(false);
        txtNroFactura.setEnabled(false);
    }
    private void setearTablas() {
        //DETALLE REMITO
        tblDetalleRemito.setModel(new DetalleRemitoTableModel());
        tblDetalleRemito.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleRemito.setShowHorizontalLines(false);
        tblDetalleRemito.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleRemito.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //REMITO
        tblRemitos.setModel(new RemitoTableModel());
        tblRemitos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblRemitos.setShowHorizontalLines(false);
        tblRemitos.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblRemitos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListeners() {
        addListenerBtnSalirr();
    }

    private void addListenerBtnSalirr() {
        btnSalirr1.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
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
        tblRemitos = new org.jdesktop.swingx.JXTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDetalleRemito = new org.jdesktop.swingx.JXTable();
        btnImprimir = new javax.swing.JButton();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        btnVerDetalle = new javax.swing.JButton();
        rbNroFactura = new javax.swing.JRadioButton();
        txtNroFactura = new javax.swing.JTextField();
        rbFechaEmision = new javax.swing.JRadioButton();
        dccFechaEmision = new org.jdesktop.swingx.JXDatePicker();
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
        setTitle("Consultar Remito");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Remito"));

        tblRemitos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRemitosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRemitos);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Factura Seleccionada"));

        jScrollPane5.setViewportView(tblDetalleRemito);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_impresora.png"))); // NOI18N
        btnImprimir.setToolTipText("Imprimir factura seleccionada");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnVerDetalle.setBackground(new java.awt.Color(204, 255, 204));
        btnVerDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/detalle.gif"))); // NOI18N
        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });

        rbNroFactura.setText("Nro. Remito:");
        rbNroFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNroFacturaActionPerformed(evt);
            }
        });

        txtNroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNroFacturaKeyReleased(evt);
            }
        });

        rbFechaEmision.setText("Fecha de Emisión:");
        rbFechaEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFechaEmisionActionPerformed(evt);
            }
        });

        dccFechaEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dccFechaEmisionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbNroFactura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbFechaEmision)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                            .addComponent(btnVerDetalle, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnImprimir)
                        .addGap(30, 30, 30)
                        .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnImprimir, btnSalirr1});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbNroFactura)
                        .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbFechaEmision)
                        .addComponent(dccFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerDetalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImprimir)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnImprimir, btnSalirr1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRemitosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRemitosMouseClicked
        if (tblRemitos.getSelectedRow() >= 0) {
            btnVerDetalle.setEnabled(true);
        }
}//GEN-LAST:event_tblRemitosMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        gestor.imprimirRemito(pedido.getIdpedido());
}//GEN-LAST:event_btnImprimirActionPerformed

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        if (tblRemitos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Factura!");
            return;
        }
        Remito v = filasRemito.get(tblRemitos.getSelectedRow());
        filasDetalle = gestor.buscarDetallesRemitoByRemito(v.getIdremito());
        pedido=v.getPedido();
        tblDetalleRemito.updateUI();

        btnVerDetalle.setEnabled(false);
        btnImprimir.setEnabled(true);
}//GEN-LAST:event_btnVerDetalleActionPerformed

    private void rbNroFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNroFacturaActionPerformed
        filasRemito = new LinkedList<Remito>();
        tblRemitos.updateUI();
        txtNroFactura.setEnabled(true);
        dccFechaEmision.setEnabled(false);
        dccFechaEmision.setDate(null);
}//GEN-LAST:event_rbNroFacturaActionPerformed

    private void txtNroFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFacturaKeyReleased

        if (txtNroFactura.getText().compareTo("") != 0) {
            filasRemito = gestor.buscarRemitosByNroLIKE(txtNroFactura.getText());
            tblRemitos.updateUI();
        }
}//GEN-LAST:event_txtNroFacturaKeyReleased

    private void rbFechaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFechaEmisionActionPerformed
        String fecha = String.valueOf(Fecha.fechaActual());
        filasRemito = gestor.buscarRemitosByFechaEmision(fecha);
        tblRemitos.updateUI();
        txtNroFactura.setEnabled(false);
        dccFechaEmision.setEnabled(true);
        txtNroFactura.setText("");
        dccFechaEmision.setDate(Fecha.fechaActualDate());
}//GEN-LAST:event_rbFechaEmisionActionPerformed

    private void dccFechaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dccFechaEmisionActionPerformed
        
        if (dccFechaEmision.getDate() != null) {
            String fecha = String.valueOf(dccFechaEmision.getDate());
            filasRemito = gestor.buscarRemitosByFechaEmision(fecha);
            tblRemitos.updateUI();
        }
}//GEN-LAST:event_dccFechaEmisionActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarRemitos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private javax.swing.JButton btnVerDetalle;
    private org.jdesktop.swingx.JXDatePicker dccFechaEmision;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JRadioButton rbFechaEmision;
    private javax.swing.JRadioButton rbNroFactura;
    private org.jdesktop.swingx.JXTable tblDetalleRemito;
    private org.jdesktop.swingx.JXTable tblRemitos;
    private javax.swing.JTextField txtNroFactura;
    // End of variables declaration//GEN-END:variables
    class DetalleRemitoTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro Detalle",
            "Nro Remito",
            "Producto",
            "Cantidad",
            "descripcion"};
        public Object getValueAt(int rowIndex, int columnIndex) {

            Detalleremito view = filasDetalle.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return String.valueOf(view.getDetalleremitoPK().getIddetalle());
                case 1:
                    return String.valueOf(view.getRemito().getNroremito());
                case 2:
                    return view.getProducto().getNombre();
                case 3:
                    return String.valueOf(view.getCantidad());
                case 4:
                    return view.getDescripcion();
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
            if (filasDetalle != null) {
                return filasDetalle.size();
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

    class RemitoTableModel extends AbstractTableModel {

        String[] columnNames = {"nro Remito",
            "Nro Pedido",
            "Fecha de Emision",
            "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            Remito view = filasRemito.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return String.valueOf(view.getNroremito());
                case 1:
                    return String.valueOf(view.getPedido().getNropedido());
                case 2:
                    return String.valueOf(view.getFechaemision());
                case 3:
                    if (view.getEstado() == null) {
                        return "";
                    } else {
                        return view.getEstado().getNombre();
                    }
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
            if (filasRemito != null) {
                return filasRemito.size();
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
