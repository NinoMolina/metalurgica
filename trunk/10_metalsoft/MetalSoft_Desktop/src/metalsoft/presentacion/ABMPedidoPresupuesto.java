/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMPedidoCotizacion.java
 *
 * Created on May 10, 2010, 10:13:14 PM
 */
package metalsoft.presentacion;

import javax.swing.JList;
import metalsoft.util.Fecha;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.ClienteDB;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.negocio.gestores.GestorPedidoCotizacion;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.util.Combo;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.JXBusyLabel;

/**
 *
 * @author Vicky
 */
public class ABMPedidoPresupuesto extends javax.swing.JDialog implements IBuscador {

    private static Timer timer;
    private GestorPedidoCotizacion gestor;
    private HiloBuscarCliente hiloBuscarCliente;
    private LinkedList<ViewDetallePedidoCotizacion> filas = new LinkedList<ViewDetallePedidoCotizacion>();
    private static Cliente[] clientes;
    private ArrayList<ViewDetallePedidoCotizacion> view;
    private EnumOpcionesABM opcion;
    private ArrayList<ViewDetallePedidoCotizacion> arlDetallePedCotAEliminar;
    private ArrayList arlIdsProductoDetallePedido;

    /** Creates new form ABMPedidoCotizacion */
    public ABMPedidoPresupuesto() {
        initComponents();
        addListeners();
        gestor = new GestorPedidoCotizacion();
        timer = new Timer();
        bsyBuscar.setVisible(false);
        bsyBuscarProducto.setVisible(false);
        cargarComboPrioridad();
        cargarComboEstado();
        tblDetallePedidoCotizacion.updateUI();
        setEnableComponents(false);
    }

    private void addListeners() {
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        addListenerBtnAgregar();
        addListenerBtnQuitar();
    }

    private void addListenerBtnNuevo() {
        beanBotones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.NUEVO;
        int nro = obtenerNuevoNroPedidoCotizacion();
        if (nro > 0) {
            limpiarCampos();
            Combo.setItemComboSeleccionado(cmbEstado, 1);
            Combo.setItemComboSeleccionado(cmbPrioridad, 3);
            lblNroPedido.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, nro));
            dccPedidoCotizacion.setDate(Fecha.fechaActualDate());
            setEnableComponents(true);
            dccPedidoCotizacion.setEnabled(false);
            beanBotones.getBtnEliminar().setEnabled(false);
            beanBotones.getBtnModificar().setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo generar el nro de pedido.");
        }
    }

    private void addListenerBtnGuardar() {
        beanBotones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (dccFechaReqCotizacion.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar la fecha requerida de cotización");
            return;
        }
        //tomo los datos de la ventana
        long idCli = Long.parseLong(((ItemCombo) cmbResultadoBusqueda.getSelectedItem()).getId());
        int nroPedidoCliente = Integer.parseInt(txtNroPedidoCliente.getText());
        int nroPedido = NumerosAMostrar.getNumeroInt(lblNroPedido.getText());
        int nroFactura = -1;

        if (txtNroFactura.getText().compareTo("") != 0) {
            nroFactura = Integer.parseInt(txtNroFactura.getText());
        }

        String motivoCancelacion = txtMotivoCancelacion.getText();

        Date fechaCancelacion = null;
        if (dccCancelacion.getDate() != null) {
            fechaCancelacion = dccCancelacion.getDate();
        }
        Date fechaConfirmacionPedido = null;
        if (dccConfirmacionPedido.getDate() != null) {
            fechaConfirmacionPedido = dccConfirmacionPedido.getDate();
        }
        Date fechaEntregaReal = null;
        if (dccEntregaReal.getDate() != null) {
            fechaEntregaReal = dccEntregaReal.getDate();
        }
        Date fechaEntregaEstipulada = null;
        if (dccEntregaEstipulada.getDate() != null) {
            fechaEntregaEstipulada = dccEntregaEstipulada.getDate();
        }

        Date fechaRequeridaCotizacion = dccFechaReqCotizacion.getDate();
        Date fechaPedidoCotizacion = dccPedidoCotizacion.getDate();
        long idEstado = Long.parseLong(((ItemCombo) cmbEstado.getSelectedItem()).getId());
        long idPrioridad = Long.parseLong(((ItemCombo) cmbPrioridad.getSelectedItem()).getId());
        //seteo los valores necesarios en el gestor para guardar el pedido
        gestor.setNroPedidoCliente(nroPedidoCliente);
        gestor.setNroPedido(nroPedido);
        gestor.setNroFactura(nroFactura);
        gestor.setMotivoCancelacion(motivoCancelacion);
        gestor.setFechaCancelacion(fechaCancelacion);
        gestor.setConfirmacionPedido(fechaConfirmacionPedido);
        gestor.setFechaEntregaReal(fechaEntregaReal);
        gestor.setFechaEntregaEstipulada(fechaEntregaEstipulada);
        gestor.setFechaRequeridaCotizacion(fechaRequeridaCotizacion);
        gestor.setFechaPedidoCotizacion(fechaPedidoCotizacion);
        gestor.setIdEstado(idEstado);
        gestor.setIdPrioridad(idPrioridad);
        gestor.setIdCliente(idCli);
        //paso la coleccion de ViewDetallePedidoCotizacion que representan
        //el detalle del pedido
        gestor.setListaDetalle(filas);

        long result = -1;
        if (opcion == EnumOpcionesABM.NUEVO) {
            result = gestor.registrarPedidoCotizacion();
        }
        if (opcion == EnumOpcionesABM.MODIFICAR) {
//            gestor.setDetalleAEliminar(arlDetallePedCotAEliminar);
//            gestor.setIdProducto(idProducto);
//            result=gestor.modificarProducto();
            setEnableComponents(false);
        }
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "El Pedido Nro: " + nroPedido + " se guardó correctamente.");
            setEnableComponents(false);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "El Pedido Nro: " + nroPedido + " no se pudo guardar.");
        }
    }
//    private ArrayList crearDetallePedido(Pedido p)
//    {
//        ArrayList arlDetalle=new ArrayList();
//        Iterator<ViewDetallePedidoCotizacion> iter=filas.iterator();
//        arlIdsProductoDetallePedido=new ArrayList(filas.size());
//        ViewDetallePedidoCotizacion datos=null;
//        while(iter.hasNext())
//        {
//            datos=iter.next();
//            arlIdsProductoDetallePedido.add(datos.getIdProducto());
//            int cant=datos.getCantidad();
//            double precio=datos.getPrecio();
//            arlDetalle.add(p.crearDetallePedido(cant, precio));
//        }
//        if(!arlDetalle.isEmpty())return arlDetalle;
//        else return null;
//    }

//    private void setListaDetalleGestor()
//    {
//        ArrayList arlIds,arlDatos;
//        arlDatos=new ArrayList(filas.size());
//        arlIds=new ArrayList(filas.size());
////        Iterator<Object[]> iter=filas.iterator();
//        Iterator<ViewDetalleProducto> iter=filas.iterator();
//        ViewDetalleProducto datoFila=null;
//        while(iter.hasNext())
//        {
//            datoFila=iter.next();
//            int cant=Integer.parseInt(String.valueOf(datoFila.getCantidad()));
//            String desc=String.valueOf(datoFila.getDescripcion());
//            arlDatos.add(new Object[]{cant, desc});
//            arlIds.add(datoFila.getIdPieza());
//        }
//        gestor.setListaDetalle(arlDatos,arlIds);
//    }
    private void addListenerBtnModificar() {
        beanBotones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.MODIFICAR;
//        arlDetProdAEliminar=new ArrayList<ViewDetalleProducto>();
//        botones.getBtnModificar().setEnabled(false);
        setEnableComponents(true);
    }

    private void addListenerBtnBuscar() {
        beanBotones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
//        opcion=EnumOpcionesABM.BUSCAR;
//        ABMProducto_Buscar buscar=null;
//        try {
//            buscar=(ABMProducto_Buscar) JFrameManager.crearVentana(ABMProducto_Buscar.class.getName());
//            buscar.setVentana(this);
//            buscar.setGestor(gestor);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void addListenerBtnSalir() {
        beanBotones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void addListenerBtnAgregar() {
        beanBtnAgregar.getBtnAgregar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        ItemCombo item = (ItemCombo) lstResultadoBusqueda.getSelectedValue();
        long idProducto = Long.parseLong(item.getId());
        ViewDetallePedidoCotizacion v = new ViewDetallePedidoCotizacion();

        JTextField txtCant = new JTextField("1");
        Object[] obj = {"Cantidad", txtCant};
        int result = JOptionPane.showConfirmDialog(null, obj, "Ingresar Cantidad", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            ProductoDB db = gestor.buscarProductoDB(idProducto);
            v.setIdProducto(idProducto);
            v.setNombreProducto(db.getNombre());
            v.setPrecio(db.getPreciounitario());
            v.setDescripcion(db.getDescripcion());
            v.setNumeroProducto((int) db.getNroproducto());

            int cantPiezas = gestor.obtenerCantidadPiezasXProducto(idProducto);
            v.setCantidadPiezas(cantPiezas);
            String cant = txtCant.getText();
            v.setCantidad(Integer.parseInt(cant));
            agregarFila(v);
            tblDetallePedidoCotizacion.updateUI();
        }
    }

    private void addListenerBtnQuitar() {
        beanBtnQuitar.getBtnQuitar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
    }

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblDetallePedidoCotizacion.getSelectedRow();
        if (opcion == EnumOpcionesABM.MODIFICAR) {
            arlDetallePedCotAEliminar.add(filas.get(selectedRow));
        }
        filas.remove(selectedRow);
        tblDetallePedidoCotizacion.updateUI();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivoCancelacion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        cmbResultadoBusqueda = new javax.swing.JComboBox();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnNuevoCliente = new javax.swing.JButton();
        bsyBuscar = new org.jdesktop.swingx.JXBusyLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblCuit = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNroPedidoCliente = new javax.swing.JTextField();
        cmbPrioridad = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtNroFactura = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblNroPedido = new javax.swing.JLabel();
        dccFechaReqCotizacion = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dccEntregaReal = new com.toedter.calendar.JDateChooser();
        dccCancelacion = new com.toedter.calendar.JDateChooser();
        dccPedidoCotizacion = new com.toedter.calendar.JDateChooser();
        dccEntregaEstipulada = new com.toedter.calendar.JDateChooser();
        dccConfirmacionPedido = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstResultadoBusqueda = new javax.swing.JList();
        txtValorBusqueda = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        rbtNombre = new javax.swing.JRadioButton();
        btnNuevoProducto = new javax.swing.JButton();
        beanBtnAgregar = new metalsoft.beans.BtnAgregar();
        bsyBuscarProducto = new org.jdesktop.swingx.JXBusyLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetallePedidoCotizacion = new javax.swing.JTable();
        beanBtnQuitar = new metalsoft.beans.BtnQuitar();
        beanBotones = new metalsoft.beans.ABM_Botones();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Pedidos de Cotización");
        setMinimumSize(new java.awt.Dimension(640, 580));

        jLabel14.setText("Motivo de Cancelación:");

        txtMotivoCancelacion.setColumns(20);
        txtMotivoCancelacion.setRows(5);
        jScrollPane2.setViewportView(txtMotivoCancelacion);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección de Cliente"));

        cmbResultadoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbResultadoBusquedaActionPerformed(evt);
            }
        });

        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyTyped(evt);
            }
        });

        jLabel10.setText("Razón Social:");

        jLabel15.setText("Cliente:");

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new1.png"))); // NOI18N
        btnNuevoCliente.setText("Nuevo Cliente");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        jLabel16.setText("Teléfono:");

        jLabel17.setText("CUIT:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRazonSocial)
                            .addComponent(cmbResultadoBusqueda, 0, 274, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(bsyBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103))
                            .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCuit, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRazonSocial)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bsyBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbResultadoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17))
                    .addComponent(lblCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setText("Nro. Pedido Cliente:");

        jLabel11.setText("Nro.Pedido:");

        jLabel7.setText("Prioridad:");

        jLabel8.setText("Estado:");

        jLabel9.setText("Nro. Factura:");

        jLabel12.setText("Fecha Req de Cotización:");

        lblNroPedido.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroPedido.setText("...");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbPrioridad, 0, 136, Short.MAX_VALUE)
                            .addComponent(cmbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNroFactura)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNroPedidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(lblNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNroPedidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setText("Fecha de Confirmación de Pedido:");

        jLabel4.setText("Fecha de Entrega Estipulada:");

        jLabel2.setText("Fecha Pedido Cotización:");

        jLabel5.setText("Fecha de Cancelación:");

        jLabel6.setText("Fecha de Entrega Real:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccConfirmacionPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dccEntregaEstipulada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dccCancelacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dccEntregaReal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(dccConfirmacionPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(dccEntregaEstipulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dccCancelacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(dccEntregaReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Producto"));

        lstResultadoBusqueda.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstResultadoBusquedaValueChanged(evt);
            }
        });
        lstResultadoBusqueda.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                lstResultadoBusquedaCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                lstResultadoBusquedaInputMethodTextChanged(evt);
            }
        });
        jScrollPane4.setViewportView(lstResultadoBusqueda);

        txtValorBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorBusquedaKeyReleased(evt);
            }
        });

        jLabel18.setText("Valor de Búsqueda:");

        rbtNombre.setSelected(true);
        rbtNombre.setText("Nombre");

        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new1.png"))); // NOI18N
        btnNuevoProducto.setText("Nuevo Producto");
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rbtNombre)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(11, 11, 11)
                        .addComponent(txtValorBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoProducto)
                    .addComponent(bsyBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtValorBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbtNombre)
                        .addComponent(jLabel18))
                    .addComponent(bsyBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(beanBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevoProducto))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        tblDetallePedidoCotizacion.setModel(new DetallePedidoCotizacionTableModel());
        jScrollPane3.setViewportView(tblDetallePedidoCotizacion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addComponent(beanBtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(beanBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbResultadoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResultadoBusquedaActionPerformed
//        if(cmbResultadoBusqueda.getSelectedIndex()>=0)
//        {
//            lblCondIva.setText(clientes[cmbResultadoBusqueda.getSelectedIndex()].getIva().getNombre());
//            lblCuit.setText(clientes[cmbResultadoBusqueda.getSelectedIndex()].getCUIT());
//        }
    }//GEN-LAST:event_cmbResultadoBusquedaActionPerformed

    private void txtRazonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyTyped
    }

    public static Cliente[] getClientes() {
        return clientes;
    }

    public static void setClientes(Cliente[] clientes) {
        ABMPedidoPresupuesto.clientes = clientes;
    }

    public JLabel getLblCondIva() {
        return lblTelefono;
    }

    public JLabel getLblCuit() {
        return lblCuit;
    }

    public void setLblCuit(JLabel lblCuit) {
        this.lblCuit = lblCuit;
    }

    public JXBusyLabel getBsyBuscar() {
        return bsyBuscar;
    }

    public void setBsyBuscar(JXBusyLabel bsyBuscar) {
        this.bsyBuscar = bsyBuscar;
    }

    public JComboBox getCmbResultadoBusqueda() {
        return cmbResultadoBusqueda;
    }

    public void setCmbResultadoBusqueda(JComboBox cmbResultadoBusqueda) {
        this.cmbResultadoBusqueda = cmbResultadoBusqueda;
    }

    public JTextField getTxtRazonSocial() {
        return txtRazonSocial;
    }

    public void setTxtRazonSocial(JTextField txtRazonSocial) {
        this.txtRazonSocial = txtRazonSocial;
    }//GEN-LAST:event_txtRazonSocialKeyTyped

    private void txtRazonSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyReleased
        bsyBuscar.setVisible(true);
        bsyBuscar.setBusy(true);
        if (txtRazonSocial.getText().compareTo("") != 0) {
            final ABMPedidoPresupuesto ventana = this;
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    hiloBuscarCliente = new HiloBuscarCliente();
                    hiloBuscarCliente.setVentana(ventana);
                    hiloBuscarCliente.setValor(txtRazonSocial.getText());
                    hiloBuscarCliente.start();
                }
            }, 1500);
        } else {
            bsyBuscar.setVisible(false);
            bsyBuscar.setBusy(false);
        }
    }//GEN-LAST:event_txtRazonSocialKeyReleased

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        try {
            JFrameManager.crearVentana(ABMCliente.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void txtValorBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorBusquedaKeyReleased
        bsyBuscarProducto.setVisible(true);
        bsyBuscarProducto.setBusy(true);
        if (txtValorBusqueda.getText().compareTo("") != 0) {
            final ABMPedidoPresupuesto abm = this;
            timer = new Timer();
            timer.schedule(new TimerTask() {

                private HiloBuscarProducto hiloBuscarProducto;

                @Override
                public void run() {
                    hiloBuscarProducto = new HiloBuscarProducto();
                    hiloBuscarProducto.setVentana(abm);
                    hiloBuscarProducto.setValor(txtValorBusqueda.getText());
                    hiloBuscarProducto.start();
                }
            }, 1500);
        } else {
            bsyBuscarProducto.setVisible(false);
            bsyBuscarProducto.setBusy(false);
        }
}//GEN-LAST:event_txtValorBusquedaKeyReleased

    public void agregarFila(ViewDetallePedidoCotizacion v) {
        filas.addLast(v);
    }
    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        try {
            JFrameManager.crearVentana(ABMProducto.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMPedidoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void lstResultadoBusquedaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lstResultadoBusquedaCaretPositionChanged
    }//GEN-LAST:event_lstResultadoBusquedaCaretPositionChanged

    private void lstResultadoBusquedaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lstResultadoBusquedaInputMethodTextChanged
    }//GEN-LAST:event_lstResultadoBusquedaInputMethodTextChanged

    private void lstResultadoBusquedaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstResultadoBusquedaValueChanged
    }//GEN-LAST:event_lstResultadoBusquedaValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMPedidoPresupuesto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones beanBotones;
    private metalsoft.beans.BtnAgregar beanBtnAgregar;
    private metalsoft.beans.BtnQuitar beanBtnQuitar;
    private org.jdesktop.swingx.JXBusyLabel bsyBuscar;
    private org.jdesktop.swingx.JXBusyLabel bsyBuscarProducto;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JComboBox cmbPrioridad;
    private javax.swing.JComboBox cmbResultadoBusqueda;
    private com.toedter.calendar.JDateChooser dccCancelacion;
    private com.toedter.calendar.JDateChooser dccConfirmacionPedido;
    private com.toedter.calendar.JDateChooser dccEntregaEstipulada;
    private com.toedter.calendar.JDateChooser dccEntregaReal;
    private com.toedter.calendar.JDateChooser dccFechaReqCotizacion;
    private com.toedter.calendar.JDateChooser dccPedidoCotizacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblCuit;
    private javax.swing.JLabel lblNroPedido;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JList lstResultadoBusqueda;
    private javax.swing.JRadioButton rbtNombre;
    private javax.swing.JTable tblDetallePedidoCotizacion;
    private javax.swing.JTextArea txtMotivoCancelacion;
    private javax.swing.JTextField txtNroFactura;
    private javax.swing.JTextField txtNroPedidoCliente;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtValorBusqueda;
    // End of variables declaration//GEN-END:variables

    public GestorPedidoCotizacion getGestor() {
        return gestor;
    }

    public JList getList(String className) {
        if (className.compareTo(HiloBuscarProducto.class.getName()) == 0) {
            return lstResultadoBusqueda;
        }
        return null;
    }

    public void setBusqueda(Object[] obj) {
        if (obj instanceof ProductoDB[]) {
        }
        if (obj instanceof ClienteDB[]) {
            if (obj.length > 0) {
                ClienteDB cli = (ClienteDB) obj[0];
                lblTelefono.setText(cli.getTelefono());
                lblCuit.setText(cli.getCuit());
            } else {
                lblCuit.setText("");
                lblTelefono.setText("");
            }

        }
        bsyBuscar.setBusy(false);
        bsyBuscar.setVisible(false);
        bsyBuscarProducto.setBusy(false);
        bsyBuscarProducto.setVisible(false);
    }

    public JComboBox getCombo(String className) {
        if (className.compareTo(HiloBuscarCliente.class.getName()) == 0) {
            return cmbResultadoBusqueda;
        }
        return null;
    }

    private void cargarComboPrioridad() {
        cmbPrioridad.removeAllItems();
        gestor.obtenerPrioridades(cmbPrioridad);
    }

    private void cargarComboEstado() {
        cmbEstado.removeAllItems();
        gestor.obtenerEstados(cmbEstado);
    }

    private void setEnableComponents(boolean b) {
        txtMotivoCancelacion.setEnabled(b);
        txtNroFactura.setEnabled(b);
        txtNroPedidoCliente.setEnabled(b);
        txtRazonSocial.setEnabled(b);
        txtValorBusqueda.setEnabled(b);
        beanBtnAgregar.setEnabled(b);
        btnNuevoCliente.setEnabled(b);
        btnNuevoProducto.setEnabled(b);
        beanBtnQuitar.setEnabled(b);
        tblDetallePedidoCotizacion.setEnabled(b);
        cmbEstado.setEnabled(b);
        cmbPrioridad.setEnabled(b);
        cmbResultadoBusqueda.setEnabled(b);
        dccCancelacion.setEnabled(b);
        dccConfirmacionPedido.setEnabled(b);
        dccEntregaEstipulada.setEnabled(b);
        dccEntregaReal.setEnabled(b);
        dccFechaReqCotizacion.setEnabled(b);
        dccPedidoCotizacion.setEnabled(b);
        rbtNombre.setEnabled(b);
        beanBotones.getBtnEliminar().setEnabled(b);
        beanBotones.getBtnModificar().setEnabled(b);
        beanBotones.getBtnGuardar().setEnabled(b);
    }

    private int obtenerNuevoNroPedidoCotizacion() {
        return gestor.generarNumeroPedido();
    }

    private void limpiarCampos() {
        txtMotivoCancelacion.setText("");
        txtNroFactura.setText("");
        txtNroPedidoCliente.setText("");
        txtRazonSocial.setText("");
        txtValorBusqueda.setText("");

        filas.clear();
        tblDetallePedidoCotizacion.updateUI();

        cmbEstado.setSelectedIndex(-1);
        cmbPrioridad.setSelectedIndex(-1);
        cmbResultadoBusqueda.setSelectedIndex(-1);

        dccCancelacion.setDate(null);
        dccConfirmacionPedido.setDate(null);
        dccEntregaEstipulada.setDate(null);
        dccEntregaReal.setDate(null);
        dccFechaReqCotizacion.setDate(null);
        dccPedidoCotizacion.setDate(null);

        lblTelefono.setText("");
        lblCuit.setText("");
        lblNroPedido.setText("");

        lstResultadoBusqueda.setListData(new Vector<Object>());
    }

    class DetallePedidoCotizacionTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Cantidad",
            "Producto",
            "Descripción",
            "Cant. Piezas"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetallePedidoCotizacion view = filas.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
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
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filas != null) {
                return filas.size();
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
