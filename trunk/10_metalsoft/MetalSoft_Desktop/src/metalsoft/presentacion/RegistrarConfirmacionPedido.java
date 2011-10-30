/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarConfirmacionPedido.java
 *
 * Created on 07/10/2010, 20:03:59
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.negocio.gestores.GestorRegistrarPedidoConfirmado;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewPedidoNoConfirmado;
import metalsoft.util.Decimales;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Nino
 */
public class RegistrarConfirmacionPedido extends javax.swing.JDialog {

    /** Creates new form RegistrarConfirmacionPedido */
    private LinkedList<ViewPedidoNoConfirmado> filasPedidosNoConfirmados;
    private ViewPedidoNoConfirmado viewPedidoSeleccionado;
    private GestorRegistrarPedidoConfirmado gestor;
    public RegistrarConfirmacionPedido() {
        super(Principal.getVtnPrincipal());
        initComponents();
        addListeners();
        setearTablas();
        setEnabledComponents(false);
        limpiarCampos();
        gestor=new GestorRegistrarPedidoConfirmado();
        buscarPedidosNoConfirmados();
        tblPedidos.updateUI();
    }

    private void limpiarCampos(){
        lblCliente.setText("...");
        lblEstado.setText("...");
        lblFechaPedido.setText("...");
        lblFechaVencimientoPresupuesto.setText("...");
        lblMontoTotal.setText("...");
        lblNroPedido.setText("...");
        lblNroPedCliente.setText("...");
        lblNroPresupuesto.setText("...");
        txtValorBusqueda.setText("");
    }

    private void setEnabledComponents(boolean b){
        btnRegistrarConfirmacion.setEnabled(b);
        this.btnListaMP.setEnabled(b);
    }
    private void setearTablas(){
        tblPedidos.setModel(new PedidoNoConfirmadoTableModel());
        tblPedidos.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedidos.setShowHorizontalLines(false);
        tblPedidos.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblPedidos.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListeners(){
        addListenerBtnSalir();
        addListenerBtnSeleccionarPedido();
    }


    private void addListenerBtnSeleccionarPedido() {
        beanBtnSeleccionar.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPedidoActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarPedidoActionPerformed(java.awt.event.ActionEvent evt) {
        if(tblPedidos.getSelectedRow()<0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido...");
            return;
        }
        viewPedidoSeleccionado = filasPedidosNoConfirmados.get(tblPedidos.getSelectedRow());
        setDatosPedidoSeleccionado();
        btnRegistrarConfirmacion.setEnabled(true);
        btnListaMP.setEnabled(true);
    }
    private void addListenerBtnSalir() {
        beanBtnSalir.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void buscarPedidosNoConfirmados(){
        filasPedidosNoConfirmados=gestor.buscarPedidosNoConfirmados();
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
        jPanel1 = new javax.swing.JPanel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtValorBusqueda = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblNroPresupuesto = new javax.swing.JLabel();
        lblMontoTotal = new javax.swing.JLabel();
        lblFechaVencimientoPresupuesto = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblFechaPedido = new javax.swing.JLabel();
        lblNroPedCliente = new javax.swing.JLabel();
        lblNroPedido = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblCliente = new javax.swing.JLabel();
        btnRegistrarConfirmacion = new javax.swing.JButton();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();
        beanBtnSeleccionar = new metalsoft.beans.BtnSeleccionar();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPedidos = new org.jdesktop.swingx.JXTable();
        btnListaMP = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel(){

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
        setTitle("Confirmar Pedido");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar Datos"));

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setSelected(true);
        jRadioButton3.setText("Nro Pedido");

        jLabel1.setText("Valor de Búsqueda:");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Fecha Pedido");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton3)
                .addComponent(jRadioButton2)
                .addComponent(jRadioButton1)
                .addComponent(jLabel1)
                .addComponent(txtValorBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Pedido Seleccionado"));

        jLabel2.setText("Nro Pedido:");

        jLabel3.setText("Nro Pedido Cliente:");

        jLabel4.setText("Fecha Pedido:");

        jLabel5.setText("Estado:");

        jLabel6.setText("Nro Presupuesto:");

        jLabel7.setText("Monto Total: $");

        jLabel8.setText("Vencimiento Presupuesto:");

        jLabel9.setText("Cliente:");

        lblNroPresupuesto.setText("...");

        lblMontoTotal.setText("...");

        lblFechaVencimientoPresupuesto.setText("...");

        lblEstado.setText("...");

        lblFechaPedido.setText("...");

        lblNroPedCliente.setText("...");

        lblNroPedido.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroPedido.setText("...");

        lblCliente.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCliente.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNroPedCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNroPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaVencimientoPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNroPedido)
                    .addComponent(lblFechaPedido)
                    .addComponent(jLabel4)
                    .addComponent(lblNroPedCliente)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblNroPresupuesto)
                    .addComponent(lblFechaVencimientoPresupuesto)
                    .addComponent(jLabel7)
                    .addComponent(lblMontoTotal)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblCliente)))
        );

        btnRegistrarConfirmacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save1.png"))); // NOI18N
        btnRegistrarConfirmacion.setText("Registrar Confirmación");
        btnRegistrarConfirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarConfirmacionActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(tblPedidos);

        btnListaMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/beans/stock.jpg"))); // NOI18N
        btnListaMP.setText("Verificar Stock Materia Prima");
        btnListaMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaMPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegistrarConfirmacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListaMP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(beanBtnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnListaMP, btnRegistrarConfirmacion});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarConfirmacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListaMP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarConfirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarConfirmacionActionPerformed
        int result=JOptionPane.showConfirmDialog(this, "Está por modificar el pedido Nro '"+NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido())+"' como CONFIRMADO \nDesea Continuar?","Aviso",JOptionPane.OK_CANCEL_OPTION);
        boolean guardo=false;
        if(result==JOptionPane.OK_OPTION){
            guardo=gestor.registrarPedidoConfirmado(viewPedidoSeleccionado.getIdpedido());
            if(guardo){
                JOptionPane.showMessageDialog(this, "El pedido Nro '"+NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido())+"' se registró CORRECTAMENTE!");
                filasPedidosNoConfirmados.remove(viewPedidoSeleccionado);
                tblPedidos.updateUI();
                limpiarCampos();
                setEnabledComponents(false);
            }
        }
    }//GEN-LAST:event_btnRegistrarConfirmacionActionPerformed

    private void btnListaMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaMPActionPerformed
         try {
            JFrameManager.crearVentana(ConsultarListadoMateriaPrimaAComprar.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListaMPActionPerformed


    private void setDatosPedidoSeleccionado(){
        lblCliente.setText(viewPedidoSeleccionado.getRazonsocial());
        lblEstado.setText(viewPedidoSeleccionado.getEstado());
        lblFechaPedido.setText(Fecha.parseToString(viewPedidoSeleccionado.getFechapedido()));
        lblFechaVencimientoPresupuesto.setText(Fecha.parseToString(viewPedidoSeleccionado.getVencimientopresupuesto()));
        lblMontoTotal.setText(String.valueOf(viewPedidoSeleccionado.getMontototal()));
        lblNroPedCliente.setText(String.valueOf(viewPedidoSeleccionado.getNropedcotcli()));
        lblNroPedido.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, viewPedidoSeleccionado.getNropedido()));
        lblNroPresupuesto.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRESUPUESTO,viewPedidoSeleccionado.getNropresupuesto()));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarConfirmacionPedido().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionar;
    private javax.swing.JButton btnListaMP;
    private javax.swing.JButton btnRegistrarConfirmacion;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaPedido;
    private javax.swing.JLabel lblFechaVencimientoPresupuesto;
    private javax.swing.JLabel lblMontoTotal;
    private javax.swing.JLabel lblNroPedCliente;
    private javax.swing.JLabel lblNroPedido;
    private javax.swing.JLabel lblNroPresupuesto;
    private org.jdesktop.swingx.JXTable tblPedidos;
    private javax.swing.JTextField txtValorBusqueda;
    // End of variables declaration//GEN-END:variables

    class PedidoNoConfirmadoTableModel extends AbstractTableModel {

        private String[] columnNames = {
            "Nro.Ped",
            "Nro.Ped.Cli",
            "Fecha Pedido",
            "Estado",
            "Nro.Pres",
            "Monto",
            "Venc.Pres",
            "Cliente"
        };

        public int getRowCount() {
            if (filasPedidosNoConfirmados != null) {
                return filasPedidosNoConfirmados.size();
            } else {
                return 0;
            }
        }

        public String[] getColumnNames() {
            return columnNames;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        
        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            ViewPedidoNoConfirmado view = filasPedidosNoConfirmados.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, view.getNropedido());
                case 1:
                    return view.getNropedcotcli();
                case 2:
                    return Fecha.parseToString(view.getFechapedido());
                case 3:
                    return view.getEstado();
                case 4:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRESUPUESTO, view.getNropresupuesto());
                case 5:
                    return Decimales.con2Decimales(view.getMontototal());
                case 6:
                    return Fecha.parseToString(view.getVencimientopresupuesto());
                case 7:
                    return view.getRazonsocial();
                default:
                    return null;
            }
        }
    }
}
