/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarPedidoCotizacionDeTrabajo.java
 *
 * Created on 14/08/2011, 19:45:11
 */

package metalsoft.presentacion;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.negocio.gestores.GestorTrabajoTercerizado;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class RegistrarPedidoCotizacionDeTrabajo extends javax.swing.JFrame implements IBuscador {

    private static Timer timer;
    private HiloBuscarEmpresaMetalurgica hiloBuscarEmpresaMetalurgica;
    private Empresametalurgica[] empresasMetalurgicas;
    private EnumOpcionesABM opcion;
    private GestorTrabajoTercerizado gestor;
    private List<Pedido> filasPedidos;
    private LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido;
    private LinkedList<ViewDetalleProducto> filasDetalleProducto;
    private TableCellRender tcrTblDetallePedido;
        private long idPedidoSeleccionado, idProductoSeleccionado, idPiezaSeleccionada;

    public Empresametalurgica[] getEmpresasMetalurgicas() {
        return empresasMetalurgicas;
    }

    public void setEmpresasMetalurgicas(Empresametalurgica[] empresasMetalurgicas) {
        this.empresasMetalurgicas = empresasMetalurgicas;
    }

    /** Creates new form RegistrarPedidoCotizacionDeTrabajo */
    public RegistrarPedidoCotizacionDeTrabajo() {
        initComponents();
        addListeners();
        gestor = new GestorTrabajoTercerizado();
        tcrTblDetallePedido = new TableCellRender();
        //tblDetallePedido.setDefaultRenderer(Object.class, tcrTblDetallePedido);
        buscarPedidosNoFinalizados();
        addListeners();
        setearTablas();
        setEnabledComponents(false);
        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        timer = new Timer();
        bsyBuscar.setVisible(false);
    }
     private void setearTablas() {
        //DETALLE PEDIDO
        tblDetallePedido.setModel(new DetallePedidoCotizacionTableModel());
        tblDetallePedido.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetallePedido.setShowHorizontalLines(false);
        tblDetallePedido.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetallePedido.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //DETALLE PRODUCTO
        tblDetalleProducto.setModel(new DetalleProductoTableModel());
        tblDetalleProducto.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleProducto.setShowHorizontalLines(false);
        tblDetalleProducto.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleProducto.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        
    }
     private void setEnabledComponents(boolean b) {
        beanBtnGuardar.getBtnGuardar().setEnabled(b);
        beanBtnSeleccionarPieza.setEnabled(b);
        beanBtnSeleccionarProducto.setEnabled(b);
    }
    private void buscarPedidosNoFinalizados() {
        filasPedidos = gestor.buscarPedidosNoFinalizados();
        LinkedList<ViewPedidoEnListadoProcedimientos> lstView = new LinkedList<ViewPedidoEnListadoProcedimientos>();
        ViewPedidoEnListadoProcedimientos view = null;
        for(Pedido pedido: filasPedidos){
            view = new ViewPedidoEnListadoProcedimientos();

            view.setCliente(pedido.getCliente().getRazonsocial());
            view.setEspedidoweb(pedido.getEspedidoweb());
            view.setEstado(pedido.getEstado().getNombre());
            view.setFechaentregaestipulada(pedido.getFechaentregaestipulada());
            view.setFechapedidocotizacion(pedido.getFechapedidocotizacion());
            view.setFecharequeridacotizacion(pedido.getFecharequeridacotizacion());
            view.setIdestado(pedido.getEstado().getIdestado());
            view.setIdpedido(pedido.getIdpedido());
            view.setNropedido((int)pedido.getNropedido());
            view.setNropedidocotizacioncliente(pedido.getNropedidocotizacioncliente());
            view.setPrioridad(pedido.getPrioridad().getNombre());

            lstView.addLast(view);
        }
        beanTblPedidos.setFilasPedidos(lstView);
        beanTblPedidos.updateTblPedidos();
    }
    private void addListeners() {
        addListenerBtnSeleccionarPedido();
        addListenerBtnSeleccionarProducto();
        addListenerBtnSeleccionarPieza();
        addListenerBtnSalir();
        addListenerBtnGuardar();
    }

    private void addListenerBtnGuardar() {
        beanBtnGuardar.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        boolean result = false;//gestor.guardarEtapasPiezaPresupuesto();
        if (result) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron Correctamente..!");
            limpiarCampos();
            setEnabledComponents(false);
        } else {
            JOptionPane.showMessageDialog(this, "NO se pudieron guardar los datos de la pieza");
        }
    }

    private void limpiarCampos() {
        if (filasDetallePedido != null) {
            filasDetallePedido.clear();
        }
        if (filasDetalleProducto != null) {
            filasDetalleProducto.clear();
        }

        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();

        txtPedidoCotizacion.setText("");

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

    private void addListenerBtnSeleccionarPieza() {
        beanBtnSeleccionarPieza.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPiezaBeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarPiezaBeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblDetalleProducto.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una pieza!");
            return;
        }
        ViewDetalleProducto v = (ViewDetalleProducto) filasDetalleProducto.get(tblDetalleProducto.getSelectedRow());

    }

    private void addListenerBtnSeleccionarProducto() {
        beanBtnSeleccionarProducto.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductoBeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarProductoBeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblDetallePedido.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto!");
            return;
        }
        ViewDetallePedidoCotizacion v = filasDetallePedido.get(tblDetallePedido.getSelectedRow());
        long idPro = v.getIdProducto();
        filasDetalleProducto = gestor.buscarDetalleProducto(idPro);
        tblDetalleProducto.updateUI();

        beanBtnSeleccionarPieza.setEnabled(true);
    }

    private void addListenerBtnSeleccionarPedido() {
        beanTblPedidos.getBtnSeleccionarPedido().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPedidoBeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionarPedidoBeanActionPerformed(java.awt.event.ActionEvent evt) {
        if (beanTblPedidos.getTblPedidos().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido!");
            return;
        }
        limpiarCampos();
        Pedido v = filasPedidos.get(beanTblPedidos.getTblPedidos().getSelectedRow());
        long idPed = v.getIdpedido();
        filasDetallePedido = gestor.buscarDetallePedido(idPed);
        tblDetallePedido.updateUI();
        idPedidoSeleccionado = idPed;
        setEnabledComponents(false);
        beanBtnSeleccionarProducto.setEnabled(true);
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
        jLabel1 = new javax.swing.JLabel();
        lblNroEmpresa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblResponsableEmpresa = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblcondicionIva = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblNroTrabajo = new javax.swing.JLabel();
        dccFechaReqCotizacion = new com.toedter.calendar.JDateChooser();
        dccPedidoCotizacion = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtPedidoCotizacion = new javax.swing.JTextField();
        beanTblPedidos = new metalsoft.beans.PedidosSinAlgEtapaProd();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetalleProducto = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionarPieza = new metalsoft.beans.BtnSeleccionar();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDetallePedido = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionarProducto = new metalsoft.beans.BtnSeleccionar();
        beanBtnGuardar = new metalsoft.beans.BtnGuardar();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección de Empresa"));

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

        jLabel15.setText("Empresa:");

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new1.png"))); // NOI18N
        btnNuevoCliente.setText("Nueva Empresa");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        jLabel16.setText("Teléfono:");

        jLabel17.setText("CUIT:");

        jLabel1.setText("Nro. Empresa:");

        jLabel3.setText("Responsable:");

        jLabel5.setText("Condicion IVA:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRazonSocial)
                    .addComponent(cmbResultadoBusqueda, 0, 274, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bsyBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoCliente))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblResponsableEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcondicionIva, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblResponsableEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblNroEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtRazonSocial)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(bsyBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbResultadoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblcondicionIva, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(lblCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jLabel11.setText("Nro.Trabajo Tercerizado:");

        jLabel12.setText("Fecha Req de Cotización:");

        lblNroTrabajo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNroTrabajo.setText("...");

        jLabel2.setText("Fecha Pedido Cotización:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNroTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(378, 378, 378))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(dccFechaReqCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(lblNroTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Actuales"));

        jLabel13.setText("Nro. de Pedido de Cotización:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanTblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Producto"));

        jScrollPane4.setViewportView(tblDetalleProducto);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addComponent(beanBtnSeleccionarPieza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(beanBtnSeleccionarPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pedido"));

        jScrollPane5.setViewportView(tblDetallePedido);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 992, Short.MAX_VALUE)
                        .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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
            final RegistrarPedidoCotizacionDeTrabajo ventana = this;
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    hiloBuscarEmpresaMetalurgica = new HiloBuscarEmpresaMetalurgica();
                    hiloBuscarEmpresaMetalurgica.setVentana(ventana);
                    hiloBuscarEmpresaMetalurgica.setValor(txtRazonSocial.getText());
                    hiloBuscarEmpresaMetalurgica.start();
                }
            }, 1500);
        } else {
            bsyBuscar.setVisible(false);
            bsyBuscar.setBusy(false);
        }
}//GEN-LAST:event_txtRazonSocialKeyReleased

    private void txtRazonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyTyped
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
            JFrameManager.crearVentana(ABMEmpresaMetalurgica.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarPedidoCotizacionDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RegistrarPedidoCotizacionDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RegistrarPedidoCotizacionDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnNuevoClienteActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarPedidoCotizacionDeTrabajo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnGuardar beanBtnGuardar;
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarPieza;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarProducto;
    private metalsoft.beans.PedidosSinAlgEtapaProd beanTblPedidos;
    private org.jdesktop.swingx.JXBusyLabel bsyBuscar;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JComboBox cmbResultadoBusqueda;
    private com.toedter.calendar.JDateChooser dccFechaReqCotizacion;
    private com.toedter.calendar.JDateChooser dccPedidoCotizacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblCuit;
    private javax.swing.JLabel lblNroEmpresa;
    private javax.swing.JLabel lblNroTrabajo;
    private javax.swing.JLabel lblResponsableEmpresa;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblcondicionIva;
    private org.jdesktop.swingx.JXTable tblDetallePedido;
    private org.jdesktop.swingx.JXTable tblDetalleProducto;
    private javax.swing.JTextField txtPedidoCotizacion;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables

    public JList getList(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JComboBox getCombo(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBusqueda(Object[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
//    public JList getList(String className) {
//        if (className.compareTo(HiloBuscarProducto.class.getName()) == 0) {
//            return lstResultadoBusqueda;
//        }
//        return null;
//    }
//
//    public void setBusqueda(Object[] obj) {
//        if (obj instanceof ProductoDB[]) {
//        }
//        if (obj instanceof ClienteDB[]) {
//            if (obj.length > 0) {
//                ClienteDB cli = (ClienteDB) obj[0];
//                lblTelefono.setText(cli.getTelefono());
//                lblCuit.setText(cli.getCuit());
//            } else {
//                lblCuit.setText("");
//                lblTelefono.setText("");
//            }
//
//        }
//        bsyBuscar.setBusy(false);
//        bsyBuscar.setVisible(false);
//        bsyBuscarProducto.setBusy(false);
//        bsyBuscarProducto.setVisible(false);
//    }
//
//    public JComboBox getCombo(String className) {
//        if (className.compareTo(HiloBuscarCliente.class.getName()) == 0) {
//            return cmbResultadoBusqueda;
//        }
//        return null;
//    }
//
//    private void cargarComboPrioridad() {
//        cmbPrioridad.removeAllItems();
//        gestor.obtenerPrioridades(cmbPrioridad);
//    }
//
//    private void cargarComboEstado() {
//        cmbEstado.removeAllItems();
//        gestor.obtenerEstados(cmbEstado);
//    }
//
//    private void setEnableComponents(boolean b) {
//        txtMotivoCancelacion.setEnabled(b);
//        txtNroFactura.setEnabled(b);
//        txtNroPedidoCliente.setEnabled(b);
//        txtRazonSocial.setEnabled(b);
//        txtValorBusqueda.setEnabled(b);
//        beanBtnAgregar.setEnabled(b);
//        btnNuevoCliente.setEnabled(b);
//        btnNuevoProducto.setEnabled(b);
//        beanBtnQuitar.setEnabled(b);
//        tblDetallePedidoCotizacion.setEnabled(b);
//        cmbEstado.setEnabled(b);
//        cmbPrioridad.setEnabled(b);
//        cmbResultadoBusqueda.setEnabled(b);
//        dccCancelacion.setEnabled(b);
//        dccConfirmacionPedido.setEnabled(b);
//        dccEntregaEstipulada.setEnabled(b);
//        dccEntregaReal.setEnabled(b);
//        dccFechaReqCotizacion.setEnabled(b);
//        dccPedidoCotizacion.setEnabled(b);
//        rbtNombre.setEnabled(b);
//        beanBotones.getBtnEliminar().setEnabled(b);
//        beanBotones.getBtnModificar().setEnabled(b);
//        beanBotones.getBtnGuardar().setEnabled(b);
//    }

    private int obtenerNuevoNroPedidoCotizacion() {
        return gestor.generarNumeroPedido();
    }

    
    class PedidoTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Nro Ped Cliente",
            "Prioridad",
            "Cliente",
            "Ped Cotizacion",
            "Cot Req Para",
            "Entrega Estipulada",
            "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            Pedido view = filasPedidos.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PEDIDO, view.getNropedido());
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

    class DetallePedidoCotizacionTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Cantidad",
            "Producto",
            "Descripción",
            "Cant. Piezas"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetallePedidoCotizacion view = filasDetallePedido.get(rowIndex);

            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRODUCTO, view.getNumeroProducto());
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
            if (filasDetallePedido != null) {
                return filasDetallePedido.size();
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

    class DetalleProductoTableModel extends AbstractTableModel {

        String[] columnNames = {"Pieza",
            "Descripcion",
            "Cantidad",
            "Dimensiones",
            "Material"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetalleProducto view = filasDetalleProducto.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNombrePieza();
                case 1:
                    return view.getDescripcion();
                case 2:
                    return String.valueOf(view.getCantidadPieza());
                case 3:
                    return "Alto: " + view.getAlto() + "\n Ancho: " + view.getAncho() + "\n Largo: " + view.getLargo();
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
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasDetalleProducto != null) {
                return filasDetalleProducto.size();
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
