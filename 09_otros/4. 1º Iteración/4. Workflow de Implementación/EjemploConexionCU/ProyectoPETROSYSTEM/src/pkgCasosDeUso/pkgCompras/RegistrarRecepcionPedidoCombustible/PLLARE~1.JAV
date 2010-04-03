/*
 * PllaRecepcionPedidoCombustible.java
 *
 * Created on 23 de junio de 2008, 15:25
 */

package pkgCasosDeUso.pkgCompras.RegistrarRecepcionPedidoCombustible;

import java.util.Date;
import pkgClasesDeNegocio.AdministracionDePersonal.Empleado;
import pkgClasesDeNegocio.Compras.DetallePedidoAProveedor;
import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.Pantalla;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author  Fer
 */
public class PllaRecepcionPedidoCombustible extends javax.swing.JFrame {
    int banderaSeleccionPedido=0;
    GestorRegistarRecepcionPedidoCombustible gestorRegistarRecepcionPedidoCombustible=new GestorRegistarRecepcionPedidoCombustible();
    /** Creates new form PllaRecepcionPedidoCombustible */
    public PllaRecepcionPedidoCombustible() {
        initComponents();
    }
    
    
        public void opcionNuevaRecepcionPedidoCombustible()
    {
    this.abrirVentana();
    }
 private void abrirVentana() {
        
          Pantalla.centrarEnPantallaJFrame(this,4,8);
          Pantalla.insertarIcono(this,"D:/Facultad/5A�oK1/ProyectoFinal/Iteracion1/4. 1� Iteraci�n/Interfaces/PrjModuloCompras/Imagenes/logoestacion.gif");

    Date fecha=new Date();
   
    fecha.setTime(System.currentTimeMillis());
    this.jCdarChFecha.setDate(fecha);
    
    this.jSpinFieldHora.setMinimum(0);
    this.jSpinFieldHora.setMaximum(23);
    this.jSpinFieldMinutos.setMinimum(0);
    this.jSpinFieldMinutos.setMaximum(59);
    this.jSpinFieldHora.setValue(fecha.getHours());
    this.jSpinFieldMinutos.setValue(fecha.getMinutes());
    
    gestorRegistarRecepcionPedidoCombustible.nuevaRecepcionPedidoCombustible(this);

    this.setVisible(true);
   // this.gestor.nuevaRecepcionPedidoCombustible(this);
    
   
    }

   public void mostrarResponsables(Lista Responsables) {
      
       Iterador iteradorResponsables=Responsables.crearIterador();
        
        while(iteradorResponsables.siguiente()){
          Empleado empleado=(Empleado) iteradorResponsables.getElementoActual();
          
          this.cmbResponsable.addItem(empleado.getNombre());
         
        }
    
    }

    void mostrarDetallePedidoSeleccionado(Lista lstDetallePedido) {
         Iterador itDetallePedidos=lstDetallePedido.crearIterador();
     int i=0;
     
     limpiarTablaProductoPedidos();
        
     while(itDetallePedidos.siguiente())
     {
        
         DetallePedidoAProveedor detallPedidoAProveedor=(DetallePedidoAProveedor) itDetallePedidos.getElementoActual(); 
         Double aux=(Double) detallPedidoAProveedor.getAttribute("cantidadPorRecibir");
     this.tblProductosPedido.setValueAt(detallPedidoAProveedor.getEstado().getNombre(), i, 0);
     this.tblProductosPedido.setValueAt(detallPedidoAProveedor.getProducto().getNombre(), i, 1);
     this.tblProductosPedido.setValueAt(detallPedidoAProveedor.getCantidad(), i, 2);
     this.tblProductosPedido.setValueAt(aux.doubleValue(), i, 3);
     i++;
     }
     banderaSeleccionPedido=1;
    }
 private void limpiarTablaPedidos(){
  
     for(int i=0;i<tblPedidosProveedor.getRowCount();i++){
      this.tblPedidosProveedor.setValueAt("",i,0);
      this.tblPedidosProveedor.setValueAt("", i, 1);
     this.tblPedidosProveedor.setValueAt("", i, 2);
     
     }
     this.tblPedidosProveedor.setShowGrid(true);
 
    }
  private void limpiarTablaProductoPedidos(){
  
     for(int i=0;i<tblProductosPedido.getRowCount();i++){
      this.tblProductosPedido.setValueAt("",i,0);
      this.tblProductosPedido.setValueAt("", i, 1);
     this.tblProductosPedido.setValueAt("", i, 2);
     this.tblProductosPedido.setValueAt("", i, 3);
     }
     this.tblProductosPedido.setShowGrid(true);
 banderaSeleccionPedido=0;
    }
    
    void mostrarDatosPedido(Lista lstPedidos) {
          
     
        Iterador itPedidos=lstPedidos.crearIterador();
     int i=0;
     
     limpiarTablaPedidos();
        
     while(itPedidos.siguiente())
     {
  
     PedidoAProveedor pedido=(PedidoAProveedor)itPedidos.getElementoActual();
     tblPedidosProveedor.setValueAt(pedido.getAttribute("id_PedidoAProveedor"),i,0);
     tblPedidosProveedor.setValueAt(pedido.getFechaRealizacion(), i, 1);
     tblPedidosProveedor.setValueAt(pedido.getMontoTotal(), i, 2);
      i++;
     
     }
    }

    void mostrarProveedoresCombustible(Lista lstProveedoresCombustible) {
       Iterador iteradorProveedores=lstProveedoresCombustible.crearIterador();
        
        while(iteradorProveedores.siguiente()){
          Proveedor proveedor=(Proveedor) iteradorProveedores.getElementoActual();
          
          this.cmbProveedor.addItem(proveedor.getRazonSocial());
         
        }
    
    }
    private void tomaIngresoFechaHoraRecepcion()
    {
        Date fechaRecepcion=this.jCdarChFecha.getDate();
        String  horaRecepcion=this.jSpinFieldHora.getValue()+":"+this.jSpinFieldMinutos.getValue()+":00";        
    gestorRegistarRecepcionPedidoCombustible.tomarFechaHoraRecepcion(fechaRecepcion,horaRecepcion);
    
    
    }
            
       /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPRecepcionCombustible = new javax.swing.JPanel();
        jCdarChFecha = new com.toedter.calendar.JDateChooser();
        lblFecha = new javax.swing.JLabel();
        lblResponsable = new javax.swing.JLabel();
        cmbResponsable = new javax.swing.JComboBox();
        lblProveedor = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        jSpinFieldHora = new com.toedter.components.JSpinField();
        jSpinFieldMinutos = new com.toedter.components.JSpinField();
        lblFecha1 = new javax.swing.JLabel();
        lblFecha2 = new javax.swing.JLabel();
        jScrollPanePedidos = new javax.swing.JScrollPane();
        tblPedidosProveedor = new javax.swing.JTable();
        lblPedidodsDelProveedor = new javax.swing.JLabel();
        jScrollPaneProductos = new javax.swing.JScrollPane();
        tblProductosPedido = new javax.swing.JTable();
        btnRecibirTodo = new javax.swing.JButton();
        btnRecibirParcial = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPRecepcionCombustible.setBorder(javax.swing.BorderFactory.createTitledBorder("Recepción Combustible"));

        lblFecha.setText("Fecha");

        lblResponsable.setText("Responsable");

        cmbResponsable.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Responsable" }));
        cmbResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbResponsableActionPerformed(evt);
            }
        });

        lblProveedor.setText("Proveedor");

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Poveedor" }));
        cmbProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProveedorItemStateChanged(evt);
            }
        });
        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });

        lblFecha1.setText("Hora");

        lblFecha2.setText("Hora");

        javax.swing.GroupLayout jPRecepcionCombustibleLayout = new javax.swing.GroupLayout(jPRecepcionCombustible);
        jPRecepcionCombustible.setLayout(jPRecepcionCombustibleLayout);
        jPRecepcionCombustibleLayout.setHorizontalGroup(
            jPRecepcionCombustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPRecepcionCombustibleLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCdarChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(lblFecha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSpinFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinFieldMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(549, 549, 549))
            .addGroup(jPRecepcionCombustibleLayout.createSequentialGroup()
                .addGroup(jPRecepcionCombustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPRecepcionCombustibleLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblProveedor))
                    .addGroup(jPRecepcionCombustibleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblResponsable)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPRecepcionCombustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(536, Short.MAX_VALUE))
        );
        jPRecepcionCombustibleLayout.setVerticalGroup(
            jPRecepcionCombustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPRecepcionCombustibleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPRecepcionCombustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinFieldHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSpinFieldMinutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jCdarChFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPRecepcionCombustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPRecepcionCombustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        tblPedidosProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", ""},
                {"", "", ""},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Pedido Numero", "Fecha", "Monto Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPedidosProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosProveedorMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPedidosProveedorMouseReleased(evt);
            }
        });
        jScrollPanePedidos.setViewportView(tblPedidosProveedor);

        lblPedidodsDelProveedor.setText("Pedidos del Proveedor");

        tblProductosPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Estado de Recepción", "Producto", "Cantidad Pedida", "Cantidad Por Recibir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneProductos.setViewportView(tblProductosPedido);

        btnRecibirTodo.setText("Recibir Todo");
        btnRecibirTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecibirTodoActionPerformed(evt);
            }
        });

        btnRecibirParcial.setText("Recibir Parte");
        btnRecibirParcial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecibirParcialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblPedidodsDelProveedor, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneProductos, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPanePedidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRecibirTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRecibirParcial))
                    .addComponent(jPRecepcionCombustible, javax.swing.GroupLayout.Alignment.LEADING, 0, 724, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPRecepcionCombustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPedidodsDelProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanePedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRecibirParcial)
                    .addComponent(btnRecibirTodo))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResponsableActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cmbResponsableActionPerformed

    private void btnRecibirParcialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecibirParcialActionPerformed
     this.cmbProveedor.removeAllItems();
    }//GEN-LAST:event_btnRecibirParcialActionPerformed

    private void cmbProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProveedorItemStateChanged

    }//GEN-LAST:event_cmbProveedorItemStateChanged

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
  
 // Mensaje.mensaje_Estandar("entro en el item change");
        if(this.cmbProveedor.getSelectedIndex()==0)
        {
        limpiarTablaPedidos();
        limpiarTablaProductoPedidos();
        }
        else
        {
             limpiarTablaPedidos();
        limpiarTablaProductoPedidos();
                this.tomarSeleccionProveedor(cmbProveedor.getSelectedItem().toString().trim()); 
        }

      
      
      
    }//GEN-LAST:event_cmbProveedorActionPerformed

    private void tblPedidosProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosProveedorMouseClicked
    //int i=0;  
        
    // this.tomarSeleccionPedido(ValText.getInt_Integer(tblPedidosProveedor.getValueAt(tblPedidosProveedor.getSelectedRow(), 0)));
    }//GEN-LAST:event_tblPedidosProveedorMouseClicked

    private void tblPedidosProveedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosProveedorMouseReleased
  String aux=new String();
  aux=tblPedidosProveedor.getValueAt(tblPedidosProveedor.getSelectedRow(), 0).toString().trim();
  if(aux.compareTo("")!=0)
  {
       banderaSeleccionPedido=1;
       int auxInt=Integer.parseInt(aux);
       this.tomarSeleccionPedido(auxInt);
  }
  else
  {
  limpiarTablaProductoPedidos();
  }
       
    }//GEN-LAST:event_tblPedidosProveedorMouseReleased

    private void btnRecibirTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecibirTodoActionPerformed
   String mensaje="";
        if(this.cmbResponsable.getSelectedIndex()>0 && this.cmbResponsable.getSelectedIndex()>0 && banderaSeleccionPedido==1)
   {
       this.tomarSeleccionResponsableRecibirPedido();
       this.tomaIngresoFechaHoraRecepcion();
       this.tomarSeleccionEstadoRecepcionPedidoActual("Recibido total");
   }
   else
   {
       mensaje+="Seleccione:";
   if(this.cmbResponsable.getSelectedIndex()==0)
   {
       mensaje+=" el Responsable de la Recepcion,";

   }
   if(this.cmbProveedor.getSelectedIndex()==0)
   {
        mensaje+=" el Proveedor,";

   }
   if(banderaSeleccionPedido!=1)
   {
           mensaje+=" el Pedido";
   }
   }
   if (mensaje.compareTo("")!=0)
   {
  this.mostrarMensajePantalla(mensaje, Mensaje.TIPO_AVISO);
   }
   
        
    }//GEN-LAST:event_btnRecibirTodoActionPerformed
    private void tomarSeleccionEstadoRecepcionPedidoActual(String estado)
    {
        gestorRegistarRecepcionPedidoCombustible.tomarEstadoRecepcionPedidoActual(estado);
    }
    /**
     * @param args the command line arguments
     */
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRecibirParcial;
    private javax.swing.JButton btnRecibirTodo;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbResponsable;
    private com.toedter.calendar.JDateChooser jCdarChFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPRecepcionCombustible;
    private javax.swing.JScrollPane jScrollPanePedidos;
    private javax.swing.JScrollPane jScrollPaneProductos;
    private com.toedter.components.JSpinField jSpinFieldHora;
    private com.toedter.components.JSpinField jSpinFieldMinutos;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha1;
    private javax.swing.JLabel lblFecha2;
    private javax.swing.JLabel lblPedidodsDelProveedor;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblResponsable;
    private javax.swing.JTable tblPedidosProveedor;
    private javax.swing.JTable tblProductosPedido;
    // End of variables declaration//GEN-END:variables

    private void tomarSeleccionPedido(int id_Pedido) {
        gestorRegistarRecepcionPedidoCombustible.tomarPedido(id_Pedido);
    }
    // End of variables declaration

    private void tomarSeleccionProveedor(String razonSocial) {
     
      gestorRegistarRecepcionPedidoCombustible.tomarProveedor(razonSocial);
    }

    private void tomarSeleccionResponsableRecibirPedido() {
  int aux=this.cmbResponsable.getSelectedIndex();
        if(aux==0)
        {
            Mensaje.mensaje_Estandar("Seleccione el Responsable de la Recepcion", "Completar Datos", Mensaje.TIPO_AVISO);
            return;
        }
        else
        {
         String responsable=cmbResponsable.getSelectedItem().toString();
      gestorRegistarRecepcionPedidoCombustible.tomarResponsableRecibirPedido(responsable);
        }
     
    }
     public void mostrarMensajePantalla(String mensaje,int tipoMensaje){
     Mensaje.mensaje_Estandar(mensaje,"Mensaje", tipoMensaje);
    }
    
}
