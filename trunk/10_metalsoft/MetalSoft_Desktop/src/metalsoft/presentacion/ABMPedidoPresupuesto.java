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

import java.awt.Graphics;
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
import javax.imageio.ImageIO;
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
        super(Principal.getVtnPrincipal());
        initComponents();
        addListeners();
        gestor = new GestorPedidoCotizacion();
        timer = new Timer();
        bsyBuscar.setVisible(false);
        bsyBuscarProducto.setVisible(false);
        cargarComboPrioridad();
        cargarComboEstado();
        tblDetallePedidoCotizacion.setModel(new DetallePedidoCotizacionTableModel());
        tblDetallePedidoCotizacion.updateUI();
        tblDetallePedidoCotizacion.packAll();
        jLabel12.setText("Fecha Req. de Pedido: ");
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
            //setEnableComponents(true);
            setEnableComponentsNuevo(true);
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

//        if (txtNroFactura.getText().compareTo("") != 0) {
//            nroFactura = Integer.parseInt(txtNroFactura.getText());
//        }

        String motivoCancelacion = txtMotivoCancelacion.getText();

        Date fechaCancelacion = null;
        if (dccCancelacion.getDate() != null) {
            fechaCancelacion = dccCancelacion.getDate();
        }
//        Date fechaConfirmacionPedido = null;
//        if (dccConfirmacionPedido.getDate() != null) {
//            fechaConfirmacionPedido = dccConfirmacionPedido.getDate();
//        }
//        Date fechaEntregaReal = null;
//        if (dccEntregaReal.getDate() != null) {
//            fechaEntregaReal = dccEntregaReal.getDate();
//        }
//        Date fechaEntregaEstipulada = null;
//        if (dccEntregaEstipulada.getDate() != null) {
//            fechaEntregaEstipulada = dccEntregaEstipulada.getDate();
//        }

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
        //gestor.setConfirmacionPedido(fechaConfirmacionPedido);
        //gestor.setFechaEntregaReal(fechaEntregaReal);
        //gestor.setFechaEntregaEstipulada(fechaEntregaEstipulada);
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
            String cant = txtCant.getText();
            for (ViewDetallePedidoCotizacion view : filas) {
                if (view.getIdProducto() == idProducto) {
                    view.setCantidad(view.getCantidad() + Integer.parseInt(cant));
                    tblDetallePedidoCotizacion.updateUI();
                    tblDetallePedidoCotizacion.packAll();
                    return;
                }
            }
            ProductoDB db = gestor.buscarProductoDB(idProducto);
            v.setIdProducto(idProducto);
            v.setNombreProducto(db.getNombre());
            v.setPrecio(db.getPreciounitario());
            v.setDescripcion(db.getDescripcion());
            v.setNumeroProducto((int) db.getNroproducto());

            int cantPiezas = gestor.obtenerCantidadPiezasXProducto(idProducto);
            v.setCantidadPiezas(cantPiezas);
            v.setCantidad(Integer.parseInt(cant));
            agregarFila(v);
            tblDetallePedidoCotizacion.updateUI();
            tblDetallePedidoCotizacion.packAll();
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
        tblDetallePedidoCotizacion.packAll();
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
        jLabel19 = new javax.swing.JLabel(){

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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNroPedidoCliente = new javax.swing.JTextField();
        cmbPrioridad = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        lblNroPedido = new javax.swing.JLabel();
        dccFechaReqCotizacion = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dccPedidoCotizacion = new com.toedter.calendar.JDateChooser();
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstResultadoBusqueda = new javax.swing.JList();
        txtValorBusqueda = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnNuevoProducto = new javax.swing.JButton();
        beanBtnAgregar = new metalsoft.beans.BtnAgregar();
        bsyBuscarProducto = new org.jdesktop.swingx.JXBusyLabel();
        jPanel2 = new javax.swing.JPanel();
        beanBtnQuitar = new metalsoft.beans.BtnQuitar();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetallePedidoCotizacion = new org.jdesktop.swingx.JXTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dccCancelacion = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivoCancelacion = new javax.swing.JTextArea();
        beanBotones = new metalsoft.beans.ABM_Botones();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Pedidos de Cotización");
        setMinimumSize(new java.awt.Dimension(640, 580));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Pedido"));

        jLabel1.setText("Nro. Pedido Cliente:");

        jLabel11.setText("Nro.Pedido:");

        jLabel7.setText("Prioridad:");

        jLabel8.setText("Estado:");

        jLabel12.setText("Fecha Req de Cotización:");

        lblNroPedido.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroPedido.setText("...");

        jLabel2.setText("Fecha Pedido Cotización:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(lblNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(285, 285, 285))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(252, 252, 252)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtNroPedidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(29, 29, 29)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel7)
                        .addGap(28, 28, 28)
                        .addComponent(cmbPrioridad, 0, 179, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(lblNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(txtNroPedidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

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
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbResultadoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(bsyBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bsyBuscar, 0, 0, Short.MAX_VALUE)
                    .addComponent(txtRazonSocial)
                    .addComponent(jLabel10))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cmbResultadoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(lblCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel18.setText("Nombre de Producto:");

        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new1.png"))); // NOI18N
        btnNuevoProducto.setText("Nuevo Producto");
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        jScrollPane3.setViewportView(tblDetallePedidoCotizacion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
                    .addComponent(beanBtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtValorBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bsyBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoProducto)
                            .addComponent(beanBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bsyBuscarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txtValorBusqueda)))
                        .addGap(63, 63, 63))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(beanBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNuevoProducto))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Cancelación Pedido"));

        jLabel5.setText("Fecha de Cancelación:");

        jLabel14.setText("Motivo de Cancelación:");

        txtMotivoCancelacion.setColumns(20);
        txtMotivoCancelacion.setRows(5);
        jScrollPane2.setViewportView(txtMotivoCancelacion);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addComponent(dccCancelacion, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dccCancelacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beanBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void lstResultadoBusquedaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstResultadoBusquedaValueChanged
}//GEN-LAST:event_lstResultadoBusquedaValueChanged

    private void lstResultadoBusquedaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lstResultadoBusquedaCaretPositionChanged
}//GEN-LAST:event_lstResultadoBusquedaCaretPositionChanged

    private void lstResultadoBusquedaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lstResultadoBusquedaInputMethodTextChanged
}//GEN-LAST:event_lstResultadoBusquedaInputMethodTextChanged

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

    public void agregarFila(ViewDetallePedidoCotizacion v) {
        filas.addLast(v);
    }

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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblCuit;
    private javax.swing.JLabel lblNroPedido;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JList lstResultadoBusqueda;
    private org.jdesktop.swingx.JXTable tblDetallePedidoCotizacion;
    private javax.swing.JTextArea txtMotivoCancelacion;
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
        //txtNroFactura.setEnabled(b);
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
        //dccConfirmacionPedido.setEnabled(b);
        //dccEntregaEstipulada.setEnabled(b);
        //dccEntregaReal.setEnabled(b);
        dccFechaReqCotizacion.setEnabled(b);
        dccPedidoCotizacion.setEnabled(b);
//        rbtNombre.setEnabled(b);
        beanBotones.getBtnEliminar().setEnabled(b);
        beanBotones.getBtnModificar().setEnabled(b);
        beanBotones.getBtnGuardar().setEnabled(b);
    }

    private void setEnableComponentsNuevo(boolean b) {
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
        dccFechaReqCotizacion.setEnabled(b);
        dccPedidoCotizacion.setEnabled(b);
//        rbtNombre.setEnabled(b);
        beanBotones.getBtnEliminar().setEnabled(b);
        beanBotones.getBtnModificar().setEnabled(b);
        beanBotones.getBtnGuardar().setEnabled(b);
    }

    private int obtenerNuevoNroPedidoCotizacion() {
        return gestor.generarNumeroPedido();
    }

    private void limpiarCampos() {
        txtMotivoCancelacion.setText("");
        //txtNroFactura.setText("");
        txtNroPedidoCliente.setText("");
        txtRazonSocial.setText("");
        txtValorBusqueda.setText("");

        filas.clear();
        tblDetallePedidoCotizacion.updateUI();
        tblDetallePedidoCotizacion.packAll();

        cmbEstado.setSelectedIndex(-1);
        cmbPrioridad.setSelectedIndex(-1);
        cmbResultadoBusqueda.setSelectedIndex(-1);

        dccCancelacion.setDate(null);
        //dccConfirmacionPedido.setDate(null);
        //dccEntregaEstipulada.setDate(null);
        //dccEntregaReal.setDate(null);
        dccFechaReqCotizacion.setDate(null);
        dccPedidoCotizacion.setDate(null);

        lblTelefono.setText("");
        lblCuit.setText("");
        lblNroPedido.setText("");

        lstResultadoBusqueda.setListData(new Vector<Object>());
    }

    class DetallePedidoCotizacionTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Producto",
            "Descripción",
            "Cant. Piezas",
            "Cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetallePedidoCotizacion view = filas.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRODUCTO, view.getNumeroProducto());
                case 1:
                    return view.getNombreProducto();
                case 2:
                    return view.getDescripcion();
                case 3:
                    return view.getCantidadPiezas();
                case 4:
                    return view.getCantidad();
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
