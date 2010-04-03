/*
 * PllaModificarCantidades.java
 *
 * Created on 23 de junio de 2008, 15:51
 */

package pkgCasosDeUso.pkgCompras.RegistrarRecepcionPedidoCombustible;


import java.util.Date;
import pkgClasesDeNegocio.Compras.DetallePedidoAProveedor;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.Ventas.TipoFactura;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.Pantalla;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author  Fer
 */
public class PllaModificarCantidades extends javax.swing.JFrame {
    GestorRegistarRecepcionPedidoCombustible gestorRegistarRecepcionPedidoCombustible;
    /** Creates new form PllaModificarCantidades */
    public PllaModificarCantidades() {
        initComponents();
    }

    public PllaModificarCantidades(GestorRegistarRecepcionPedidoCombustible gestorRegistarRecepcionPedidoCombustible) {
        initComponents();
        this.gestorRegistarRecepcionPedidoCombustible=gestorRegistarRecepcionPedidoCombustible;
    }

  

    void abrirVentana(Lista lstDetallePedidoAProveedor, Proveedor proveedorSeleccionado,Lista lstTipoFactura) {
          
      
        Pantalla.centrarEnPantallaJFrame(this,4,8);
          Pantalla.insertarIcono(this,"D:/Facultad/5A�oK1/ProyectoFinal/Iteracion1/4. 1� Iteraci�n/Interfaces/PrjModuloCompras/Imagenes/logoestacion.gif");
        txtProveedor.setText(proveedorSeleccionado.getRazonSocial().toString());
            Iterador itDetallePedidos=lstDetallePedidoAProveedor.crearIterador();
     int i=0;
     buttonGroup1.add(rbtnContado);
     buttonGroup1.add(rbtnCuentaCorriente);
     rbtnContado.setSelected(true);
        cmbCuotas.setEnabled(false);
        cmbInteres.setEnabled(false);
        jCdarChvencimiento1.setEnabled(false);
        jCdarChvencimiento2.setEnabled(false);
        lblCantidadCuotas.setEnabled(false);
        lblFechaVencimiento.setEnabled(false);
        lblFechaVencimiento2.setEnabled(false);
        lblInteres.setEnabled(false);
             jCdarChFechaFactura.setEnabled(false);
             cmbTipoFactura.setEnabled(false);
             txtMontoTotal.setEnabled(false);
     
     
     
  mostrarTipoFactura(lstTipoFactura);
        
     while(itDetallePedidos.siguiente())
     {
        
         DetallePedidoAProveedor detallPedidoAProveedor=(DetallePedidoAProveedor) itDetallePedidos.getElementoActual(); 
         double aux=Double.parseDouble( detallPedidoAProveedor.getAttribute("cantidadPorRecibir").toString());
     this.tblCantidadesProductos.setValueAt(detallPedidoAProveedor.getEstado().getNombre(), i, 0);
     this.tblCantidadesProductos.setValueAt(detallPedidoAProveedor.getProducto().getNombre(), i, 1);
     this.tblCantidadesProductos.setValueAt(detallPedidoAProveedor.getCantidad(), i, 2);
     this.tblCantidadesProductos.setValueAt(aux, i, 3);
     this.tblCantidadesProductos.setValueAt(detallPedidoAProveedor.getPrecioActual(), i, 4);
     this.tblCantidadesProductos.setValueAt(detallPedidoAProveedor.getPrecioTotal(), i, 5);
     }
     
     this.setVisible(true);
    }
private void mostrarTipoFactura(Lista lstTipoFactura)
{    Iterador iteradorTipoFactura=lstTipoFactura.crearIterador();
        
        while(iteradorTipoFactura.siguiente()){
         TipoFactura tipoFactura=(TipoFactura) iteradorTipoFactura.getElementoActual();
          
          this.cmbTipoFactura.addItem(tipoFactura.getNombre());
         
        }


}
  

    private void tomarConfirmacionNuevaRecepcionPedido(String respuesta) {
         
        this.tomarIngresoCantidadProductoRecibido();
         this.tomarIngresoNumeroFactuaRemitoRecibido();
         this.tomarSeleccionFormaPago();
        gestorRegistarRecepcionPedidoCombustible.tomarConfirmacion(respuesta);
       
    }
    private void tomarIngresoCantidadProductoRecibido()
    {
     
                for(int i=0;i<tblCantidadesProductos.getRowCount();i++)
                {

                String producto=(String)tblCantidadesProductos.getValueAt(i,1);
                
                               
                if(producto==null)
                {
                    i=tblCantidadesProductos.getRowCount();
                }
                else
                {
                    Double cantidad=(Double)tblCantidadesProductos.getValueAt(i,3);
                    gestorRegistarRecepcionPedidoCombustible.tomarCantidadProductoRecibido(producto,cantidad);
                }

                }
       
    }
    
    private void tomarSeleccionFormaPago()
    {
        if(rbtnContado.isSelected())
        {
            
        gestorRegistarRecepcionPedidoCombustible.tomarFormaPago("Contado");
        }
        else
        {
            int cantCuotas=Integer.parseInt(cmbCuotas.getSelectedItem().toString());
            Date fechaVencimientoPrimera=jCdarChvencimiento1.getDate();
            Date fechaVencimientoSegunda=jCdarChvencimiento2.getDate();
            String interesPorVencimiento=cmbInteres.getSelectedItem().toString();
            gestorRegistarRecepcionPedidoCombustible.tomarFormaPago("Cuenta Corriente");
        }

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCantidadesProductos = new javax.swing.JTable();
        lblRecibirTotal = new javax.swing.JLabel();
        lblProveedor = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        btnConfirmarRecepcion = new javax.swing.JButton();
        lblFormaPago = new javax.swing.JLabel();
        rbtnContado = new javax.swing.JRadioButton();
        rbtnCuentaCorriente = new javax.swing.JRadioButton();
        jPPlanDePago = new javax.swing.JPanel();
        lblCantidadCuotas = new javax.swing.JLabel();
        cmbCuotas = new javax.swing.JComboBox();
        lblFechaVencimiento = new javax.swing.JLabel();
        jCdarChvencimiento1 = new com.toedter.calendar.JDateChooser();
        jCdarChvencimiento2 = new com.toedter.calendar.JDateChooser();
        lblFechaVencimiento2 = new javax.swing.JLabel();
        cmbInteres = new javax.swing.JComboBox();
        lblInteres = new javax.swing.JLabel();
        jPFacturaRecibida = new javax.swing.JPanel();
        txtNumeroFactura = new javax.swing.JTextField();
        lblComprobantesRecibidos = new javax.swing.JLabel();
        jCdarChFechaFactura = new com.toedter.calendar.JDateChooser();
        lblComprobantesRecibidos1 = new javax.swing.JLabel();
        lblComprobantesRecibidos2 = new javax.swing.JLabel();
        cmbTipoFactura = new javax.swing.JComboBox();
        lblMontoTotal = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCantidadesProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Estado de Recepción", "Producto", "Cantidad Pedida", "Cantidad Recibida", "Precio", "Total", "Numero Remito"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCantidadesProductos);

        lblRecibirTotal.setText("Verifique las Cantidades Recibidas");

        lblProveedor.setText("Proveedor:");

        txtProveedor.setEditable(false);

        btnConfirmarRecepcion.setText("Confirmar Recepcion");
        btnConfirmarRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarRecepcionActionPerformed(evt);
            }
        });

        lblFormaPago.setText("Forma de Pago:");

        rbtnContado.setSelected(true);
        rbtnContado.setText("Contado");
        rbtnContado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbtnContadoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbtnContadoMouseReleased(evt);
            }
        });
        rbtnContado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnContadoStateChanged(evt);
            }
        });
        rbtnContado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtnContadoItemStateChanged(evt);
            }
        });
        rbtnContado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnContadoActionPerformed(evt);
            }
        });
        rbtnContado.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                rbtnContadoPropertyChange(evt);
            }
        });

        rbtnCuentaCorriente.setText("Cuenta Corriente");
        rbtnCuentaCorriente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnCuentaCorrienteStateChanged(evt);
            }
        });
        rbtnCuentaCorriente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCuentaCorrienteActionPerformed(evt);
            }
        });

        jPPlanDePago.setBorder(javax.swing.BorderFactory.createTitledBorder("Plan de Pago"));
        jPPlanDePago.setEnabled(false);

        lblCantidadCuotas.setText("Cantidad de Cuotas");

        cmbCuotas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        cmbCuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCuotasActionPerformed(evt);
            }
        });

        lblFechaVencimiento.setText("Fecha Vencimiento Proxima Cuota");

        lblFechaVencimiento2.setText("Segunda Fecha Vencimiento ");

        cmbInteres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0%", "1%", "2%", "3%", "4%", "5%", "6%" }));

        lblInteres.setText("Interés por Vencimiento");

        javax.swing.GroupLayout jPPlanDePagoLayout = new javax.swing.GroupLayout(jPPlanDePago);
        jPPlanDePago.setLayout(jPPlanDePagoLayout);
        jPPlanDePagoLayout.setHorizontalGroup(
            jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPlanDePagoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaVencimiento)
                    .addComponent(lblCantidadCuotas)
                    .addGroup(jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPPlanDePagoLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(lblInteres))
                        .addComponent(lblFechaVencimiento2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCdarChvencimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCdarChvencimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInteres, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPPlanDePagoLayout.setVerticalGroup(
            jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPlanDePagoLayout.createSequentialGroup()
                .addGroup(jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidadCuotas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCdarChvencimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaVencimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCdarChvencimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaVencimiento2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPPlanDePagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbInteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInteres)))
        );

        jPFacturaRecibida.setBorder(javax.swing.BorderFactory.createTitledBorder("Factura Recibida"));

        txtNumeroFactura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroFacturaFocusLost(evt);
            }
        });
        txtNumeroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroFacturaKeyReleased(evt);
            }
        });

        lblComprobantesRecibidos.setText("Numero");

        jCdarChFechaFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCdarChFechaFacturaKeyReleased(evt);
            }
        });

        lblComprobantesRecibidos1.setText("Fecha");

        lblComprobantesRecibidos2.setText("Tipo Factura");

        cmbTipoFactura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione el Tipo" }));
        cmbTipoFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoFacturaKeyReleased(evt);
            }
        });

        lblMontoTotal.setText("Monto Factura");

        txtMontoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoTotalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPFacturaRecibidaLayout = new javax.swing.GroupLayout(jPFacturaRecibida);
        jPFacturaRecibida.setLayout(jPFacturaRecibidaLayout);
        jPFacturaRecibidaLayout.setHorizontalGroup(
            jPFacturaRecibidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFacturaRecibidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFacturaRecibidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMontoTotal)
                    .addComponent(lblComprobantesRecibidos2)
                    .addComponent(lblComprobantesRecibidos)
                    .addComponent(lblComprobantesRecibidos1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFacturaRecibidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMontoTotal)
                    .addComponent(txtNumeroFactura)
                    .addComponent(jCdarChFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTipoFactura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPFacturaRecibidaLayout.setVerticalGroup(
            jPFacturaRecibidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFacturaRecibidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFacturaRecibidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComprobantesRecibidos)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFacturaRecibidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblComprobantesRecibidos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCdarChFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFacturaRecibidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComprobantesRecibidos2, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(cmbTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFacturaRecibidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMontoTotal)
                    .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jLabel1.setText("Total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(lblProveedor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtProveedor))
                        .addComponent(lblRecibirTotal, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(btnConfirmarRecepcion)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPFacturaRecibida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPPlanDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblFormaPago)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbtnContado, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbtnCuentaCorriente))))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProveedor)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRecibirTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtnCuentaCorriente)
                            .addComponent(rbtnContado)
                            .addComponent(lblFormaPago))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPPlanDePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPFacturaRecibida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btnConfirmarRecepcion)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnContadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnContadoActionPerformed
     

}//GEN-LAST:event_rbtnContadoActionPerformed

    private void btnConfirmarRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarRecepcionActionPerformed
  String mensaje=new String();
  mensaje=this.validar();
        if (mensaje.compareTo("")==0)
            
        {
          this.tomarConfirmacionNuevaRecepcionPedido("Si");
            Mensaje.mensaje_Estandar("Recepcion Registrada", "Mensaje", Mensaje.TIPO_AVISO);
        }
        else
        {
             Mensaje.mensaje_Estandar(mensaje,"Mensaje", Mensaje.TIPO_AVISO);
        }
    }//GEN-LAST:event_btnConfirmarRecepcionActionPerformed

    private void rbtnCuentaCorrienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCuentaCorrienteActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_rbtnCuentaCorrienteActionPerformed

    private void rbtnContadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtnContadoItemStateChanged
    
      
           // TODO add your handling code here:
    }//GEN-LAST:event_rbtnContadoItemStateChanged

    private void rbtnContadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnContadoStateChanged
    if(rbtnContado.isSelected())
    {
        cmbCuotas.setEnabled(false);
        cmbInteres.setEnabled(false);
        jCdarChvencimiento1.setEnabled(false);
        jCdarChvencimiento2.setEnabled(false);
        lblCantidadCuotas.setEnabled(false);
        lblFechaVencimiento.setEnabled(false);
        lblFechaVencimiento2.setEnabled(false);
        lblInteres.setEnabled(false);

    }
    
             // TODO add your handling code here:
    }//GEN-LAST:event_rbtnContadoStateChanged

    private void rbtnContadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnContadoMouseClicked
          // TODO add your handling code here:
    }//GEN-LAST:event_rbtnContadoMouseClicked

    private void rbtnContadoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_rbtnContadoPropertyChange
       
    }//GEN-LAST:event_rbtnContadoPropertyChange

    private void rbtnContadoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbtnContadoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnContadoMouseReleased

    private void rbtnCuentaCorrienteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnCuentaCorrienteStateChanged
     if(rbtnCuentaCorriente.isSelected())
    {
        cmbCuotas.setEnabled(true);
        cmbInteres.setEnabled(true);
        jCdarChvencimiento1.setEnabled(true);
        jCdarChvencimiento2.setEnabled(true);
        lblCantidadCuotas.setEnabled(true);
        lblFechaVencimiento.setEnabled(true);
        lblFechaVencimiento2.setEnabled(true);
        lblInteres.setEnabled(true);

    }
    }//GEN-LAST:event_rbtnCuentaCorrienteStateChanged

    private void cmbCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCuotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCuotasActionPerformed

    private void txtNumeroFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFacturaKeyReleased
jCdarChFechaFactura.setEnabled(true);
cmbTipoFactura.setEnabled(true);
txtMontoTotal.setEnabled(true);
   /*   if(evt.getKeyCode()==10)
     {
     if(txtNumeroFactura.getText().compareTo("")==0)
     {
     rbtnContado.requestFocus();
     
     }
     else
     {
    if(esEntero(txtNumeroFactura.getText()))
     {
        jCdarChFechaFactura.requestFocus();
     }
     else
     {
       Mensaje.mensaje_Estandar("Valor no valido para el Numero de Factura", "Mensaje", Mensaje.TIPO_AVISO);
       txtNumeroFactura.requestFocus();
     }
     }
     
     
     }*/
     
    }//GEN-LAST:event_txtNumeroFacturaKeyReleased

    private void jCdarChFechaFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCdarChFechaFacturaKeyReleased
           if(evt.getKeyCode()==10)
            {
                cmbTipoFactura.requestFocus();
           
            }
    }//GEN-LAST:event_jCdarChFechaFacturaKeyReleased

    private void cmbTipoFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoFacturaKeyReleased
    if(evt.getKeyCode()==10)
        {
            txtMontoTotal.requestFocus();
    
        }
    }//GEN-LAST:event_cmbTipoFacturaKeyReleased

    private void txtMontoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoTotalKeyReleased
           if(evt.getKeyCode()==10)
             {
                if(txtMontoTotal.getText().compareTo("")==0)
                {
                rbtnContado.requestFocus();
                }
                else
                {
                if(esDouble(txtMontoTotal.getText())==false)
                {
                Mensaje.mensaje_Estandar("Valor no valido para el monto Total de La Factura", "Mensaje", Mensaje.TIPO_AVISO);
                }
                else
                {
                  rbtnContado.requestFocus();
                }
                }
           
           
             }
    }//GEN-LAST:event_txtMontoTotalKeyReleased

    private void txtNumeroFacturaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroFacturaFocusLost
     
        if(txtNumeroFactura.getText().compareTo("")==0)
        {   
            jCdarChFechaFactura.setEnabled(false);
            cmbTipoFactura.setEnabled(false);
            txtMontoTotal.setEnabled(false);
        }
        else
        {
         jCdarChFechaFactura.setEnabled(true);
            cmbTipoFactura.setEnabled(true);
            txtMontoTotal.setEnabled(true);
        
        }
        
    }//GEN-LAST:event_txtNumeroFacturaFocusLost
 
    /**
     * @param args the command line arguments
     */
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmarRecepcion;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbCuotas;
    private javax.swing.JComboBox cmbInteres;
    private javax.swing.JComboBox cmbTipoFactura;
    private com.toedter.calendar.JDateChooser jCdarChFechaFactura;
    private com.toedter.calendar.JDateChooser jCdarChvencimiento1;
    private com.toedter.calendar.JDateChooser jCdarChvencimiento2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPFacturaRecibida;
    private javax.swing.JPanel jPPlanDePago;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCantidadCuotas;
    private javax.swing.JLabel lblComprobantesRecibidos;
    private javax.swing.JLabel lblComprobantesRecibidos1;
    private javax.swing.JLabel lblComprobantesRecibidos2;
    private javax.swing.JLabel lblFechaVencimiento;
    private javax.swing.JLabel lblFechaVencimiento2;
    private javax.swing.JLabel lblFormaPago;
    private javax.swing.JLabel lblInteres;
    private javax.swing.JLabel lblMontoTotal;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblRecibirTotal;
    private javax.swing.JRadioButton rbtnContado;
    private javax.swing.JRadioButton rbtnCuentaCorriente;
    private javax.swing.JTable tblCantidadesProductos;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void tomarIngresoNumeroFactuaRemitoRecibido() {
        for(int i=0;i<tblCantidadesProductos.getRowCount();i++)
    { 
      String producto=new String();
      int numeroRemito;
           if(this.tblCantidadesProductos.getValueAt(i, 1)!=null)
           {
               producto=(String)this.tblCantidadesProductos.getValueAt(i, 1);
             if(this.tblCantidadesProductos.getValueAt(i,6)!=null)
               {
               Integer aux= (Integer)this.tblCantidadesProductos.getValueAt(i,6);
               numeroRemito=aux.intValue(); 
               gestorRegistarRecepcionPedidoCombustible.tomarNumeroRemitoRecibido(producto,numeroRemito);
               }
           
           }
           else
           {
           i=tblCantidadesProductos.getRowCount(); 
           }
            
    }
        if(txtNumeroFactura.getText().compareTo("")!=0)
        {
        Double montoTotalFactura=ValText.getDouble(txtMontoTotal.getText());
        int numeroFactura=ValText.getEntero(txtNumeroFactura.getText());
        int fk_tipoFactura=cmbTipoFactura.getSelectedIndex();
                gestorRegistarRecepcionPedidoCombustible.tomarNumeroFacturaRecibido(numeroFactura,jCdarChFechaFactura.getDate(),montoTotalFactura,fk_tipoFactura);
        }
        else
        {
         //gestorRegistarRecepcionPedidoCombustible.tomarNumeroFacturaRecibido(0,null,null);
        
        }
     }

   public void mostrarMensajePantalla(String mensaje,int tipoMensaje){
     Mensaje.mensaje_Estandar(mensaje,"Mensaje", tipoMensaje);
    }

    private String validar() {
    
       for(int i=0;i<this.tblCantidadesProductos.getRowCount();i++){
        // String aux=this.tblCantidadesProductos.getValueAt(i, 1).toString();
          
           if(this.tblCantidadesProductos.getValueAt(i, 1)!=null)
           {
               if(this.tblCantidadesProductos.getValueAt(i,6)==null)
               {
                  tblCantidadesProductos.editCellAt(i, 6);
               return "Complete los numeros de los remitos recibidos";
               }
           }
           
       }
     
     if(txtNumeroFactura.getText().compareTo("")!=0)
     {
         if(this.esEntero(txtNumeroFactura.getText())==false)
            {
            
                 txtNumeroFactura.requestFocus();
                 return "Numero de factura no valido";
            }
         if(jCdarChFechaFactura.getDate()==null)
             {
                
                 jCdarChFechaFactura.requestFocus();
                 return "Complete la fecha de la factura recibida";
             }
         if(cmbTipoFactura.getSelectedIndex()==0)
             {
                cmbTipoFactura.requestFocus();
                return "Seleccione el tipo de la factura recibida";
                
             }
         if(txtMontoTotal.getText().compareTo("")==0 )
             {
                 
                 txtMontoTotal.requestFocus();
                 return "Complete el monto de la factura recibida";
             }
         else
             {
                 if(esDouble(txtMontoTotal.getText())==false)
                     {
                        
                         txtMontoTotal.requestFocus();
                         return "formato incorrecto del monto total";
                     }

             }
     }
       if(rbtnCuentaCorriente.isSelected())
      {
      if(jCdarChvencimiento1.getDate()==null)
      {
       
          jCdarChvencimiento1.requestFocus();
          return "Complete la fecha de vencimiento del proximo pago,";
      }
      
      
      }
    
        
        
        return "";
      
    }
     public boolean esEntero(String texto)
     {
     try
     {
     int aux=ValText.getEntero(texto);
     return true;
     }
     catch(Exception e)
     {
      return false;   
     }
     
     }
    public boolean esEntero(Object object)
     {
     try
     {
     int aux=ValText.getInt_Integer(object);
     return true;
     }
     catch(Exception e)
     {
      return false;   
     }
     
     }
    public boolean esDouble(Object object)
    {
    try
    {
        String texto=(String)object;
        Double aux=ValText.getDouble(texto);
        return true;
    }
    catch(Exception e)
    {
    return false;
    }
    
    }
     public boolean esDouble(String texto)
    {
    try
    {
        String aux=(String)texto;
        return true;
    }
    catch(Exception e)
    {
    return false;
    }
    
    }
}
