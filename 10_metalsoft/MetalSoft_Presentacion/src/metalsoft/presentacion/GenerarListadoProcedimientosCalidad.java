/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GenerarListadoProcedimientosCalidad.java
 *
 * Created on 28/08/2010, 22:46:21
 */

package metalsoft.presentacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import metalsoft.negocio.gestores.GestorDetalleProcedimientos;
import metalsoft.negocio.gestores.GestorGenerarPresupuesto;
import metalsoft.negocio.gestores.IBuscadorView;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewEtapaDeProduccion;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.util.Fecha;

/**
 *
 * @author Vicky
 */
public class GenerarListadoProcedimientosCalidad extends javax.swing.JFrame implements IBuscadorView{
    private LinkedList<ViewPedidoEnListadoProcedimientos> filasPedidos;
    private LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido;
    private LinkedList<ViewDetalleProducto> filasDetalleProducto;
    private LinkedList<ViewEtapaDeProduccion> filasEtapaProduccion;
    private LinkedList<ViewEtapaDeProduccion> filasEtapaProduccionSeleccionada;
    private GestorDetalleProcedimientos gestor;
    private Timer timer;
    private TableCellRender tcrTblDetallePedido;
    private long idPedidoSeleccionado,idProductoSeleccionado,idPiezaSeleccionada;

    /** Creates new form GenerarListadoProcedimientosCalidad */
    public GenerarListadoProcedimientosCalidad() {
        initComponents();
        tcrTblDetallePedido=new TableCellRender();
        tblDetallePedido.setDefaultRenderer(Object.class,tcrTblDetallePedido);
        gestor=new GestorDetalleProcedimientos();
        buscarPedidosGenerados();
        addListeners();
        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        tblEtapa.updateUI();
        filasEtapaProduccionSeleccionada=new LinkedList<ViewEtapaDeProduccion>();
        tblEtapaSeleccionada.updateUI();
    }

    private void addListeners()
    {
        addListenerBtnAgregar();
        addListenerBtnQuitar();
        addListenerBtnSeleccionarPedido();
    }

    private void addListenerBtnSeleccionarPedido()
    {
        beanTblPedidos.getBtnSeleccionarPedido().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPedidoBeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarPedidoBeanActionPerformed(java.awt.event.ActionEvent evt)
    {
        ViewPedidoEnListadoProcedimientos v=filasPedidos.get(beanTblPedidos.getTblPedidos().getSelectedRow());
        long idPed=v.getIdpedido();
        filasDetallePedido=gestor.buscarDetallePedido(idPed);
        tblDetallePedido.updateUI();
        idPedidoSeleccionado=idPed;
        lblPedidoSeleccionado.setText(String.valueOf(v.getNropedido()));
    }
    private void addListenerBtnAgregar()
    {
        beanAgregarQuitar.getBtnAgregar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeanAgregarActionPerformed(evt);
            }
        });
    }
    private void btnBeanAgregarActionPerformed(java.awt.event.ActionEvent evt)
    {
        if(filasEtapaProduccionSeleccionada.isEmpty())beanAgregarQuitar.getBtnQuitar().setEnabled(true);
        ViewEtapaDeProduccion v=filasEtapaProduccion.remove(tblEtapa.getSelectedRow());
        filasEtapaProduccionSeleccionada.add(v);
        tblEtapa.updateUI();
        tblEtapaSeleccionada.updateUI();
        if(filasEtapaProduccion.isEmpty())beanAgregarQuitar.getBtnAgregar().setEnabled(false);
    }
    private void addListenerBtnQuitar()
    {
        beanAgregarQuitar.getBtnQuitar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeanQuitarActionPerformed(evt);
            }
        });
    }
    private void btnBeanQuitarActionPerformed(java.awt.event.ActionEvent evt)
    {
        if(filasEtapaProduccion.isEmpty())beanAgregarQuitar.getBtnAgregar().setEnabled(true);
        ViewEtapaDeProduccion v=filasEtapaProduccionSeleccionada.remove(tblEtapaSeleccionada.getSelectedRow());
        filasEtapaProduccion.add(v);
        tblEtapa.updateUI();
        tblEtapaSeleccionada.updateUI();
        if(filasEtapaProduccionSeleccionada.isEmpty())beanAgregarQuitar.getBtnQuitar().setEnabled(false);
    }
    private void buscarPedidosGenerados()
    {
        filasPedidos=gestor.buscarPedidosGenerados();
        beanTblPedidos.setFilasPedidos(filasPedidos);
        beanTblPedidos.updateTblPedidos();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        beanTblPedidos = new metalsoft.beans.PedidosSinAlgEtapaProd();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetallePedido = new javax.swing.JTable();
        btnSeleccionarProducto = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalleProducto = new javax.swing.JTable();
        btnSeleccionarPieza = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPiezaSeleccionada = new javax.swing.JLabel();
        txtEtapaProduccion = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEtapa = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        beanAgregarQuitar = new metalsoft.beans.AgregarQuitar();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblEtapaSeleccionada = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblProductoSeleccionado = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPedidoSeleccionado = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Generados No Cotizados"));

        jLabel1.setText("Nro. de Pedido de Cotización:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(562, 562, 562))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(beanTblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pedido"));

        tblDetallePedido.setModel(new DetallePedidoCotizacionTableModel());
        jScrollPane1.setViewportView(tblDetallePedido);

        btnSeleccionarProducto.setText("Seleccionar");
        btnSeleccionarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarProducto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionarProducto))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Producto"));

        tblDetalleProducto.setModel(new DetalleProductoTableModel());
        jScrollPane3.setViewportView(tblDetalleProducto);

        btnSeleccionarPieza.setText("Seleccionar");
        btnSeleccionarPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPiezaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(btnSeleccionarPieza, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionarPieza))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pieza Seleccionada"));

        jLabel2.setText("Pieza:");

        lblPiezaSeleccionada.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblPiezaSeleccionada.setText("....");

        txtEtapaProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEtapaProduccionActionPerformed(evt);
            }
        });
        txtEtapaProduccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEtapaProduccionKeyReleased(evt);
            }
        });

        tblEtapa.setModel(new EtapaTableModel());
        jScrollPane4.setViewportView(tblEtapa);

        jLabel3.setText("Nombre Proceso de Calidad:");

        tblEtapaSeleccionada.setModel(new EtapaSeleccionadaTableModel());
        jScrollPane5.setViewportView(tblEtapaSeleccionada);

        jLabel4.setText("Producto:");

        lblProductoSeleccionado.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblProductoSeleccionado.setText("....");

        jLabel5.setText("Nro. Pedido:");

        lblPedidoSeleccionado.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblPedidoSeleccionado.setText("....");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEtapaProduccion))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beanAgregarQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPedidoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProductoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPiezaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblPedidoSeleccionado)
                    .addComponent(jLabel2)
                    .addComponent(lblPiezaSeleccionada)
                    .addComponent(jLabel4)
                    .addComponent(lblProductoSeleccionado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(beanAgregarQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEtapaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalir))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProductoActionPerformed
        ViewDetallePedidoCotizacion v=filasDetallePedido.get(tblDetallePedido.getSelectedRow());
        long idPro=v.getIdProducto();
        filasDetalleProducto=gestor.buscarDetalleProducto(idPro);
        tblDetalleProducto.updateUI();
        idProductoSeleccionado=idPro;
        lblProductoSeleccionado.setText(v.getNombreProducto());
}//GEN-LAST:event_btnSeleccionarProductoActionPerformed

    private void btnSeleccionarPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarPiezaActionPerformed

        ViewDetalleProducto v=(ViewDetalleProducto)filasDetalleProducto.get(tblDetalleProducto.getSelectedRow());
        filasEtapaProduccion=gestor.obtenerEtapasDeProduccion();
        tblEtapa.updateUI();
        idPiezaSeleccionada=v.getIdPieza();
        lblPiezaSeleccionada.setText(v.getNombrePieza());
}//GEN-LAST:event_btnSeleccionarPiezaActionPerformed

    private void txtEtapaProduccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEtapaProduccionKeyReleased
        if(txtEtapaProduccion.getText().compareTo("")!=0) {
            final GenerarListadoProcedimientosCalidad abm=this;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                private HiloViewEtapaDeProduccion hilo;
                @Override
                public void run() {
                    hilo=new HiloViewEtapaDeProduccion();
                    hilo.setClient(abm);
                    hilo.setValor(txtEtapaProduccion.getText());
                    hilo.start();
                }
            }, 1500);
        }
}//GEN-LAST:event_txtEtapaProduccionKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

}//GEN-LAST:event_btnGuardarActionPerformed

    private void txtEtapaProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEtapaProduccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEtapaProduccionActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerarListadoProcedimientosCalidad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.AgregarQuitar beanAgregarQuitar;
    private metalsoft.beans.PedidosSinAlgEtapaProd beanTblPedidos;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionarPieza;
    private javax.swing.JButton btnSeleccionarProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblPedidoSeleccionado;
    private javax.swing.JLabel lblPiezaSeleccionada;
    private javax.swing.JLabel lblProductoSeleccionado;
    private javax.swing.JTable tblDetallePedido;
    private javax.swing.JTable tblDetalleProducto;
    private javax.swing.JTable tblEtapa;
    private javax.swing.JTable tblEtapaSeleccionada;
    private javax.swing.JTextField txtEtapaProduccion;
    // End of variables declaration//GEN-END:variables

    public JTable getTable(String className) {
        if(className.compareTo(HiloViewEtapaDeProduccion.class.getName())==0)
        {
            return tblEtapa;
        }
        return null;
    }

    public LinkedList getFilas(String className) {
        if(className.compareTo(HiloViewEtapaDeProduccion.class.getName())==0)
        {
            return filasEtapaProduccion;
        }
        return null;
    }

    private ArrayList<Integer> buscarProductosSinAlgunaEtapa() {
        Iterator<ViewDetallePedidoCotizacion> i=filasDetallePedido.iterator();
        ViewDetallePedidoCotizacion v=null;
        ArrayList<Integer> rows=new ArrayList<Integer>();
        int contador=0;
        while(i.hasNext())
        {
            v=i.next();
            long idProd=v.getIdProducto();
            boolean es=gestor.esProductoSinAlgunaEtapa(idProd);
            if(es)rows.add(contador);
            contador++;
        }
        return rows;
    }
    // End of variables declaration

    class PedidoTableModel extends AbstractTableModel
    {
        String[] columnNames = {"Nro",
                        "Nro Ped Cliente",
                        "Prioridad",
                        "Cliente",
                        "Ped Cotizacion",
                        "Cot Req Para",
                        "Entrega Estipulada",
                        "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex)
        {

            ViewPedidoEnListadoProcedimientos view=filasPedidos.get(rowIndex);
    //      Object[] df=filas.get(rowIndex);
            switch(columnIndex)
            {
            case 0:
              return view.getNropedido();
            case 1:
              return view.getNropedidocotizacioncliente();
            case 2:
              return view.getPrioridad();
            case 3:
              return view.getCliente();
            case 4:
              return Fecha.parseToString(view.getFechapedidocotizacion().getTime());
            case 5:
              return Fecha.parseToString(view.getFecharequeridacotizacion().getTime());
            case 6:
              return Fecha.parseToString(view.getFechaentregaestipulada().getTime());
            case 7:
              return view.getEstado();
            default:
              return null;
            }

        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount()
        {
          return columnNames.length;
        }

        public int getRowCount()
        {
          if(filasPedidos!=null)
            return filasPedidos.size();
          return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */

        @Override
        public String getColumnName(int column)
        {
          return columnNames[column];

        }


    }

    class DetallePedidoCotizacionTableModel extends AbstractTableModel
    {
        String[] columnNames = {"Nro",
                        "Cantidad",
                        "Producto",
                        "Descripción",
                        "Cant. Piezas"};

        public Object getValueAt(int rowIndex, int columnIndex)
        {

            ViewDetallePedidoCotizacion view=filasDetallePedido.get(rowIndex);

    //      Object[] df=filas.get(rowIndex);
            switch(columnIndex)
            {
            case 0:
              return view.getNumeroProducto();
            case 1:
              return view.getCantidad();
            case 2:
              return view.getNombreProducto();
            case 3:
              return view.getDescripcion();
            case 4:
              return view.getCantidadPiezas();
            default:
              return null;
            }

        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount()
        {
          return columnNames.length;
        }

        public int getRowCount()
        {
          if(filasDetallePedido!=null)
            return filasDetallePedido.size();
          return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */

        @Override
        public String getColumnName(int column)
        {
          return columnNames[column];

        }
    }

    class DetalleProductoTableModel extends AbstractTableModel{
      String[] columnNames = {"Pieza",
                        "Descripcion",
                        "Cantidad",
                        "Dimensiones",
                        "Material"};

    public Object getValueAt(int rowIndex, int columnIndex)
    {

        ViewDetalleProducto view=filasDetalleProducto.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
        switch(columnIndex)
        {
        case 0:
          return view.getNombrePieza();
        case 1:
          return view.getDescripcion();
        case 2:
          return String.valueOf(view.getCantidad());
        case 3:
          return "Alto: "+view.getAlto()+"\n Ancho: "+view.getAncho()+"\n Largo: "+view.getLargo();
        case 4:
          return view.getNombreTipoMaterial();
        default:
          return null;
        }

    }

    /**
     * Retorna la cantidad de columnas que tiene la tabla
     * @return Numero de filas que contendra la tabla
     */
    public int getColumnCount()
    {
      return columnNames.length;
    }

    public int getRowCount()
    {
      if(filasDetalleProducto!=null)
        return filasDetalleProducto.size();
      return 0;
    }

    /**
     * Devuelve el nombre de las columnas para mostrar en el encabezado
     * @param column Numero de la columna cuyo nombre se quiere
     * @return Nombre de la columna
     */

    @Override
    public String getColumnName(int column)
    {
      return columnNames[column];

    }

  }

    class EtapaTableModel extends AbstractTableModel{
          String[] columnNames = {"Nro",
                            "Nombre"};

        public Object getValueAt(int rowIndex, int columnIndex)
        {

            ViewEtapaDeProduccion view=filasEtapaProduccion.get(rowIndex);
    //      Object[] df=filas.get(rowIndex);
            switch(columnIndex)
            {
            case 0:
              return view.getNumero();
            case 1:
              return view.getNombre();
            default:
              return null;
            }

        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount()
        {
          return columnNames.length;
        }

        public int getRowCount()
        {
          if(filasEtapaProduccion!=null)
            return filasEtapaProduccion.size();
          return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */

        @Override
        public String getColumnName(int column)
        {
          return columnNames[column];

        }
    }

    class EtapaSeleccionadaTableModel extends AbstractTableModel{
          String[] columnNames = {"Nro",
                            "Nombre"};

        public Object getValueAt(int rowIndex, int columnIndex)
        {

            ViewEtapaDeProduccion view=filasEtapaProduccionSeleccionada.get(rowIndex);
    //      Object[] df=filas.get(rowIndex);
            switch(columnIndex)
            {
            case 0:
              return view.getNumero();
            case 1:
              return view.getNombre();
            default:
              return null;
            }
        }

        /**
         * Retorna la cantidad de columnas que tiene la tabla
         * @return Numero de filas que contendra la tabla
         */
        public int getColumnCount()
        {
          return columnNames.length;
        }

        public int getRowCount()
        {
          if(filasEtapaProduccionSeleccionada!=null)
            return filasEtapaProduccionSeleccionada.size();
          return 0;
        }

        /**
         * Devuelve el nombre de las columnas para mostrar en el encabezado
         * @param column Numero de la columna cuyo nombre se quiere
         * @return Nombre de la columna
         */

        @Override
        public String getColumnName(int column)
        {
          return columnNames[column];

        }
    }

}
