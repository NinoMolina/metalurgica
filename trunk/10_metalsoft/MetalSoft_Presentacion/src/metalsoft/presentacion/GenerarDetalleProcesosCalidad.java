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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.negocio.gestores.GestorDetalleProcedimientos;
import metalsoft.negocio.gestores.GestorDetalleProcesosCalidad;
import metalsoft.negocio.gestores.GestorGenerarPresupuesto;
import metalsoft.negocio.gestores.IBuscadorView;
import metalsoft.negocio.gestores.PiezaXProcesosCalidad;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewEtapaDeProduccion;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.negocio.gestores.ViewProcesoCalidad;
import metalsoft.util.Fecha;

/**
 *
 * @author Vicky
 */
public class GenerarDetalleProcesosCalidad extends javax.swing.JFrame implements IBuscadorView{
    private LinkedList<ViewPedidoEnListadoProcedimientos> filasPedidos;
    private LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido;
    private LinkedList<ViewDetalleProducto> filasDetalleProducto;
    private LinkedList<ViewProcesoCalidad> filasProcesoCalidad;
    private LinkedList<ViewProcesoCalidad> filasProcesoCalidadSeleccionado;
    private GestorDetalleProcesosCalidad gestor;
    private Timer timer;
    private TableCellRender tcrTblDetallePedido;
    private long idPedidoSeleccionado,idProductoSeleccionado,idPiezaSeleccionada;

    /** Creates new form GenerarListadoProcedimientosCalidad */
    public GenerarDetalleProcesosCalidad() {
        initComponents();
        tcrTblDetallePedido=new TableCellRender();
        tblDetallePedido.setDefaultRenderer(Object.class,tcrTblDetallePedido);
        gestor=new GestorDetalleProcesosCalidad();
        buscarPedidosConDetalleMateriaPrima();
        addListeners();
        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        tblProcesoCalidad.updateUI();
        filasProcesoCalidadSeleccionado=new LinkedList<ViewProcesoCalidad>();
        tblProcesoCalidadSeleccionado.updateUI();
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
        int cantProcesos=-1;
        JTextField txtCant=new JTextField("1");
        Object[] obj={"Cantidad",txtCant};
        int result = JOptionPane.showConfirmDialog(this, obj, "Ingresar Cantidad de Procesos de Calidad", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            cantProcesos = Integer.parseInt(txtCant.getText());
            if(filasProcesoCalidadSeleccionado.isEmpty())beanAgregarQuitar.getBtnQuitar().setEnabled(true);
            ViewProcesoCalidad v=filasProcesoCalidad.remove(tblProcesoCalidad.getSelectedRow());
            v.setCantProcesos(cantProcesos);
            filasProcesoCalidadSeleccionado.add(v);
            tblProcesoCalidad.updateUI();
            tblProcesoCalidadSeleccionado.updateUI();
            if(filasProcesoCalidad.isEmpty())beanAgregarQuitar.getBtnAgregar().setEnabled(false);
        }
        
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
        if(filasProcesoCalidad.isEmpty())beanAgregarQuitar.getBtnAgregar().setEnabled(true);
        ViewProcesoCalidad v=filasProcesoCalidadSeleccionado.remove(tblProcesoCalidadSeleccionado.getSelectedRow());
        filasProcesoCalidad.add(v);
        tblProcesoCalidad.updateUI();
        tblProcesoCalidadSeleccionado.updateUI();
        if(filasProcesoCalidadSeleccionado.isEmpty())beanAgregarQuitar.getBtnQuitar().setEnabled(false);
    }
    private void buscarPedidosConDetalleMateriaPrima()
    {
        filasPedidos=gestor.buscarPedidosConDetalleMateriaPrima();
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
        txtProcesoCalidad = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProcesoCalidad = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblProcesoCalidadSeleccionado = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblProductoSeleccionado = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPedidoSeleccionado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        beanAgregarQuitar = new metalsoft.beans.AgregarQuitar();
        btnAsignar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Generados No Cotizados"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
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

        txtProcesoCalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProcesoCalidadActionPerformed(evt);
            }
        });
        txtProcesoCalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcesoCalidadKeyReleased(evt);
            }
        });

        tblProcesoCalidad.setModel(new ProcesoCalidadTableModel());
        jScrollPane4.setViewportView(tblProcesoCalidad);

        jLabel3.setText("Nombre Proceso de Calidad:");

        tblProcesoCalidadSeleccionado.setModel(new ProcesoCalidadSeleccionadoTableModel());
        jScrollPane5.setViewportView(tblProcesoCalidadSeleccionado);

        jLabel4.setText("Producto:");

        lblProductoSeleccionado.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblProductoSeleccionado.setText("....");

        jLabel5.setText("Nro. Pedido:");

        lblPedidoSeleccionado.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblPedidoSeleccionado.setText("....");

        btnAsignar.setText("Asignar");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(beanAgregarQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAsignar)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(beanAgregarQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btnAsignar))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPedidoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblProductoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtProcesoCalidad, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(lblPiezaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProcesoCalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addContainerGap(22, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 778, Short.MAX_VALUE)
                        .addComponent(btnSalir))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir))
                .addContainerGap())
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
        filasProcesoCalidad=gestor.obtenerProcesosCalidad();
        filasProcesoCalidadSeleccionado.clear();
        tblProcesoCalidad.updateUI();
        tblProcesoCalidadSeleccionado.updateUI();
        idPiezaSeleccionada=v.getIdPieza();
        lblPiezaSeleccionada.setText(v.getNombrePieza());
        beanAgregarQuitar.getBtnAgregar().setEnabled(true);
        beanAgregarQuitar.getBtnQuitar().setEnabled(true);
}//GEN-LAST:event_btnSeleccionarPiezaActionPerformed

    private void txtProcesoCalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcesoCalidadKeyReleased
        if(txtProcesoCalidad.getText().compareTo("")!=0) {
            final GenerarDetalleProcesosCalidad abm=this;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                private HiloViewEtapaDeProduccion hilo;
                @Override
                public void run() {
                    hilo=new HiloViewEtapaDeProduccion();
                    hilo.setClient(abm);
                    hilo.setValor(txtProcesoCalidad.getText());
                    hilo.start();
                }
            }, 1500);
        }
}//GEN-LAST:event_txtProcesoCalidadKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        boolean result=gestor.guardarPiezaXProcesoCalidad();
        if(result)JOptionPane.showMessageDialog(this, "Los datos se guardaron Correctamente..!");
        else JOptionPane.showMessageDialog(this, "NO se pudieron guardar los datos de la pieza");
}//GEN-LAST:event_btnGuardarActionPerformed

    private void txtProcesoCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProcesoCalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProcesoCalidadActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
}//GEN-LAST:event_btnSalirActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        ViewDetalleProducto viewDetPro=filasDetalleProducto.get(tblDetalleProducto.getSelectedRow());
        ViewDetallePedidoCotizacion viewDetPed=filasDetallePedido.get(tblDetallePedido.getSelectedRow());
        long idPi=viewDetPro.getIdPieza();
//        double ancho=viewDetPro.getAncho();
//        double alto=viewDetPro.getAlto();
//        double largo=viewDetPro.getLargo();
        long idProd=viewDetPro.getIdProducto();
//        double precioProd=viewDetPed.getPrecio();
//        int cantProd=viewDetPed.getCantidad();
//        int cantPiezas=viewDetPro.getCantidadPieza();
        long idDetPedido=viewDetPed.getIdDetalle();
        ViewPedidoEnListadoProcedimientos viewPed=filasPedidos.get(beanTblPedidos.getTblPedidos().getSelectedRow());
        long idPed=viewPed.getIdpedido();

        Iterator<ViewProcesoCalidad> iter=filasProcesoCalidadSeleccionado.iterator();
        ViewProcesoCalidad view=null;
        PiezaXProcesosCalidad pxpc=null;
        int result=-1;
        while(iter.hasNext())
        {
            pxpc=new PiezaXProcesosCalidad();
            view=iter.next();
            pxpc.setIdPieza(idPi);
            pxpc.setIdProducto(idProd);
            pxpc.setIdDetallePedido(idDetPedido);
            pxpc.setIdPedido(idPed);
            pxpc.setCantProcesoCalidad(view.getCantProcesos());
            pxpc.setProcesoCalidad(view);
            result=gestor.addPiezaXProcesoCalidad(pxpc);
        }
        mostrarMensajeAsignar(result,viewDetPro.getNombrePieza());
}//GEN-LAST:event_btnAsignarActionPerformed

    /*
     * 0: no se pudo agregar
     * -1: se agrego correctamente
     * 1: se modifico correctamente
     */
    private void mostrarMensajeAsignar(int result,String nombrePieza)
    {
        switch(result)
        {
            case 1:
                JOptionPane.showMessageDialog(this, "Se modificó la pre-asignación de Procesos de Calidad para la pieza '"+nombrePieza+"'");
                break;
            case 0:
                JOptionPane.showMessageDialog(this, "NO se pudo pre-asignar Procesos de Calidad para la pieza '"+nombrePieza+"'");
                break;
            case -1:
                JOptionPane.showMessageDialog(this, "Se pre-asignaron Procesos de Calidad para la pieza '"+nombrePieza+"'");
                break;
            default:
                JOptionPane.showMessageDialog(this, "NO se pudo pre-asignar Procesos de Calidad para la pieza '"+nombrePieza+"'");

        }
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerarDetalleProcesosCalidad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.AgregarQuitar beanAgregarQuitar;
    private metalsoft.beans.PedidosSinAlgEtapaProd beanTblPedidos;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionarPieza;
    private javax.swing.JButton btnSeleccionarProducto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblPedidoSeleccionado;
    private javax.swing.JLabel lblPiezaSeleccionada;
    private javax.swing.JLabel lblProductoSeleccionado;
    private javax.swing.JTable tblDetallePedido;
    private javax.swing.JTable tblDetalleProducto;
    private javax.swing.JTable tblProcesoCalidad;
    private javax.swing.JTable tblProcesoCalidadSeleccionado;
    private javax.swing.JTextField txtProcesoCalidad;
    // End of variables declaration//GEN-END:variables

    public JTable getTable(String className) {
        if(className.compareTo(HiloViewEtapaDeProduccion.class.getName())==0)
        {
            return tblProcesoCalidad;
        }
        return null;
    }

    public LinkedList getFilas(String className) {
        if(className.compareTo(HiloViewEtapaDeProduccion.class.getName())==0)
        {
            return filasProcesoCalidad;
        }
        return null;
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
          return String.valueOf(view.getCantidadPieza());
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

    class ProcesoCalidadTableModel extends AbstractTableModel{
          String[] columnNames = {"Nro",
                            "Nombre",
                            "Acción",
                            "Duración",
                            "Herramientas"};

        public Object getValueAt(int rowIndex, int columnIndex)
        {

            ViewProcesoCalidad view=filasProcesoCalidad.get(rowIndex);
    //      Object[] df=filas.get(rowIndex);
            switch(columnIndex)
            {
            case 0:
              return view.getNroproceso();
            case 1:
              return view.getNombreaccioncalidad();
            case 2:
              return view.getNombreaccioncalidad();
            case 3:
              return Fecha.parseToHourMinuteSecond(view.getDuracionestimada());
            case 4:
              return view.getHerramienta();
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
          if(filasProcesoCalidad!=null)
            return filasProcesoCalidad.size();
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

    class ProcesoCalidadSeleccionadoTableModel extends AbstractTableModel{
          String[] columnNames = {"Nro",
                            "Nombre",
                            "Acción",
                            "Duración",
                            "Herramientas",
                            "Cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex)
        {

            ViewProcesoCalidad view=filasProcesoCalidadSeleccionado.get(rowIndex);
    //      Object[] df=filas.get(rowIndex);
            switch(columnIndex)
            {
            case 0:
              return view.getNroproceso();
            case 1:
              return view.getNombreaccioncalidad();
            case 2:
              return view.getNombreaccioncalidad();
            case 3:
              return Fecha.parseToHourMinuteSecond(view.getDuracionestimada());
            case 4:
              return view.getHerramienta();
            case 5:
              return view.getCantProcesos();
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
          if(filasProcesoCalidadSeleccionado!=null)
            return filasProcesoCalidadSeleccionado.size();
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
