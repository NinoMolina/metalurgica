/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultarListadoMateriaPrimaAComprar.java
 *
 * Created on 04/09/2011, 13:17:12
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.negocio.gestores.GestorListadoMateriaPrimaAComprar;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class ConsultarListadoMateriaPrimaAComprar extends javax.swing.JDialog {

    /** Creates new form ConsultarListadoMateriaPrimaAComprar */
    private List<Pedido> filasPedidos;
    private GestorListadoMateriaPrimaAComprar gestor;
    private Pedido pedido;
    private Map<Long, FilaTabla> mapFilasTabla;
    private List<FilaTabla> filasTablaVista;

    public ConsultarListadoMateriaPrimaAComprar() {
        super(Principal.getVtnPrincipal());
        initComponents();
        mapFilasTabla = new HashMap<Long, FilaTabla>();
        addListeners();
        filasTablaVista = new LinkedList<FilaTabla>();
        filasPedidos = new LinkedList<Pedido>();
        setearTablas();
        gestor = new GestorListadoMateriaPrimaAComprar();
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);
    }

    private void setearTablas() {
        //Pedido
        tblPedido.setModel(new PedidoTableModel());
        tblPedido.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedido.setShowHorizontalLines(false);
        tblPedido.setShowVerticalLines(false);
        tblPedido.setHorizontalScrollEnabled(true);
        /* On dit de surligner une ligne sur deux */
        tblPedido.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        //Listado Materia Prima
        tblListadoMP.setModel(new ListadoMateriaPrimaTableModel());
        tblListadoMP.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblListadoMP.setShowHorizontalLines(false);
        tblListadoMP.setShowVerticalLines(false);
        tblListadoMP.setHorizontalScrollEnabled(true);
        /* On dit de surligner une ligne sur deux */
        tblListadoMP.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListeners() {
        addListenerBtnSalirr();
        addListenerBtnSeleccionarProducto();
    }

    private void addListenerBtnSeleccionarProducto() {
        beanBtnSeleccionarProducto.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductoBeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarProductoBeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblPedido.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido!");
            return;
        }
        if (!mapFilasTabla.isEmpty() || !filasTablaVista.isEmpty()) {
            mapFilasTabla.clear();
            filasTablaVista.clear();
            tblListadoMP.updateUI();
            tblListadoMP.packAll();
        }
        List<Detalleproductopresupuesto> lst = gestor.buscarListadoMPByPedido(String.valueOf(pedido.getIdpedido()));

        FilaTabla fila = null;
        for (Detalleproductopresupuesto de : lst) {

            if (mapFilasTabla.containsKey(de.getIdmateriaprima().getIdmateriaprima())) {
                fila = mapFilasTabla.get(de.getIdmateriaprima().getIdmateriaprima());
            } else {
                mapFilasTabla.put(de.getIdmateriaprima().getIdmateriaprima(), new FilaTabla());
                fila = mapFilasTabla.get(de.getIdmateriaprima().getIdmateriaprima());
                fila.setMateriaPrima(de.getIdmateriaprima());
            }

            fila.addCantidadProducto(de.getIddetallepresupuesto().getCantidad());
            fila.addCantidadMateriaPrima(de.getCantmateriaprima());
            fila.recalcularMPTotal();
            fila.calcularMpAComprar();
        }
        filasTablaVista.addAll(mapFilasTabla.values());
        tblListadoMP.updateUI();
        tblListadoMP.packAll();
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

        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtPedidoCotizacion = new javax.swing.JTextField();
        bsyBuscar1 = new org.jdesktop.swingx.JXBusyLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPedido = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionarProducto = new metalsoft.beans.BtnSeleccionar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListadoMP = new org.jdesktop.swingx.JXTable();
        btnSalirr1 = new metalsoft.beans.BtnSalirr();
        btnImprimir1 = new javax.swing.JButton();
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
        setTitle("Listado de Materia Prima a Comprar");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Pedido"));

        jLabel13.setText("Nro. de Pedido de Cotización:");

        txtPedidoCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPedidoCotizacionKeyReleased(evt);
            }
        });

        tblPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPedido);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bsyBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(344, Short.MAX_VALUE))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE))
                    .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bsyBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Materia Prima del Pedido Seleccionado"));

        jScrollPane1.setViewportView(tblListadoMP);

        btnImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_impresora.png"))); // NOI18N
        btnImprimir1.setToolTipText("Imprimir factura seleccionada");
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(574, Short.MAX_VALUE)
                .addComponent(btnImprimir1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalirr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnImprimir1, btnSalirr1});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnImprimir1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalirr1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnImprimir1, btnSalirr1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel7});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPedidoCotizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedidoCotizacionKeyReleased
        // TODO add your handling code here:
        bsyBuscar1.setVisible(true);
        bsyBuscar1.setBusy(true);
        if (txtPedidoCotizacion.getText().compareTo("") != 0) {
            filasPedidos = gestor.buscarPedidosByNroLIKE(txtPedidoCotizacion.getText());
            tblPedido.updateUI();
            tblPedido.packAll();
        }
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);
    }//GEN-LAST:event_txtPedidoCotizacionKeyReleased

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        gestor.imprimirFactura(pedido.getIdpedido());
}//GEN-LAST:event_btnImprimir1ActionPerformed

    private void tblPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidoMouseClicked
        if (tblPedido.getSelectedRow() >= 0) {
            pedido = filasPedidos.get(tblPedido.getSelectedRow());
            beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(true);
        }
    }//GEN-LAST:event_tblPedidoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ConsultarListadoMateriaPrimaAComprar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarProducto;
    private org.jdesktop.swingx.JXBusyLabel bsyBuscar1;
    private javax.swing.JButton btnImprimir1;
    private metalsoft.beans.BtnSalirr btnSalirr1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private org.jdesktop.swingx.JXTable tblListadoMP;
    private org.jdesktop.swingx.JXTable tblPedido;
    private javax.swing.JTextField txtPedidoCotizacion;
    // End of variables declaration//GEN-END:variables

    class PedidoTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro Pedido",
            "Prioridad",
            "Cliente",
            "Ped Cotizacion",
            "Cot Req Para",
            "Entrega Estipulada",
            "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (filasPedidos != null) {
                Pedido view = filasPedidos.get(rowIndex);
                //      Object[] df=filas.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return "PEDI-" + String.valueOf(view.getNropedido());
                    case 1:
                        return view.getPrioridad().getNombre();
                    case 2:
                        return view.getCliente().getRazonsocial();
                    case 3:
                        return Fecha.parseToString(view.getFechapedidocotizacion().getTime());
                    case 4:
                        return Fecha.parseToString(view.getFecharequeridacotizacion().getTime());
                    case 5:
                        if (view.getFechaentregaestipulada() != null) {
                            return Fecha.parseToString(view.getFechaentregaestipulada().getTime());
                        } else {
                            return "";
                        }
                    case 6:
                        return view.getEstado().getNombre();
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
            if (filasPedidos != null) {
                return filasPedidos.size();
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

    class ListadoMateriaPrimaTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro. Mat. Prima",
            "Materia Prima",
            "Mat. Prima Necesaria",
            "Stock de Mat. Prima",
            "Cant. Recomendada a Comprar"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (filasTablaVista != null) {
                FilaTabla view = filasTablaVista.get(rowIndex);
                //      Object[] df=filas.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return "MP-" + String.valueOf(view.getMateriaPrima().getNromateriaprima());
                    case 1:
                        return view.getMateriaPrima().getNombre();
                    case 2:

                        return String.valueOf(view.getCantMpTotal());
                    case 3:
                        return String.valueOf(view.getMateriaPrima().getStock());
                    case 4:
                        return String.valueOf(view.getCantMpRecomendada());
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
            if (filasTablaVista != null) {
                return filasTablaVista.size();
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

    class FilaTabla {

        private int cantMp = 0;
        private int cantProd = 0;
        private int cantMpTotal = 0;
        private Materiaprima materiaPrima;
        private int cantMpRecomendada = 0;

        private void addCantidadProducto(Integer cantidad) {
            cantProd += cantidad;
        }

        private void addCantidadMateriaPrima(Integer cantmateriaprima) {
            cantMp += cantmateriaprima;
        }

        private void recalcularMPTotal() {
            cantMpTotal = cantProd * cantMp;
        }

        private void calcularMpAComprar() {
            this.cantMpRecomendada = this.materiaPrima.getStock().intValue() - this.cantMpTotal;
            if (this.cantMpRecomendada < 0) {
                this.cantMpRecomendada = Math.abs(this.cantMpRecomendada);
            } else {
                this.cantMpRecomendada = 0;
            }
        }

        public int getCantMp() {
            return cantMp;
        }

        public void setCantMp(int cantMp) {
            this.cantMp = cantMp;
        }

        public int getCantMpTotal() {
            return cantMpTotal;
        }

        public void setCantMpTotal(int cantMpTotal) {
            this.cantMpTotal = cantMpTotal;
        }

        public int getCantProd() {
            return cantProd;
        }

        public void setCantProd(int cantProd) {
            this.cantProd = cantProd;
        }

        public Materiaprima getMateriaPrima() {
            return materiaPrima;
        }

        public void setMateriaPrima(Materiaprima materiaPrima) {
            this.materiaPrima = materiaPrima;
        }

        public int getCantMpRecomendada() {
            return cantMpRecomendada;
        }

        public void setCantMpRecomendada(int cantMpRecomendada) {
            this.cantMpRecomendada = cantMpRecomendada;
        }
    }
}
