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

import java.math.BigInteger;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.EmpresametalurgicaDB;
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.negocio.gestores.GestorEmpresaMetalurgica;
import metalsoft.negocio.gestores.GestorTrabajoTercerizado;
import metalsoft.negocio.gestores.IBuscador;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewPedidoCotizacion;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.Fecha;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class RegistrarPedidoCotizacionDeTrabajo extends javax.swing.JDialog implements IBuscador {

    private static Timer timer;
    private EmpresametalurgicaDB[] empresas;
    private HiloBuscarEmpresaMetalurgica hiloBuscarEmpresaMetalurgica;
    private Empresametalurgica[] empresasMetalurgicas;
    private EnumOpcionesABM opcion;
    private GestorTrabajoTercerizado gestor;
    private List<Pedido> filasPedidos;
    private LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido;
    private LinkedList<ViewDetalleProducto> filasDetalleProducto;
    private LinkedList<ViewPedidoCotizacion> filasPedidoCotizacion;
    private TableCellRender tcrTblDetallePedido;
    private long idPedidoSeleccionado, idProductoSeleccionado, idPiezaSeleccionada;
    private Etapadeproduccion etapa;

    public Empresametalurgica[] getEmpresasMetalurgicas() {
        return empresasMetalurgicas;
    }

    public void setEmpresasMetalurgicas(Empresametalurgica[] empresasMetalurgicas) {
        this.empresasMetalurgicas = empresasMetalurgicas;
    }

    /** Creates new form RegistrarPedidoCotizacionDeTrabajo */
    public RegistrarPedidoCotizacionDeTrabajo() {
        super(Principal.getVtnPrincipal());
        initComponents();
        filasPedidoCotizacion = new LinkedList<ViewPedidoCotizacion>();
        gestor = new GestorTrabajoTercerizado();
        tcrTblDetallePedido = new TableCellRender();
        //tblDetallePedido.setDefaultRenderer(Object.class, tcrTblDetallePedido);
        buscarPedidosNoFinalizados();
        addListeners();
        setearTablas();
        setEnabledComponents(false);
        beanTblPedidos.getBtnSeleccionarPedido().setEnabled(false);
        beanBtnGuardar.getBtnGuardar().setEnabled(false);
        beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(false);
        beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(false);
        btnQuitar.setEnabled(false);
        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        tblPedidoCotizacion.updateUI();
        timer = new Timer();
        bsyBuscar.setVisible(false);
        bsyBuscar1.setVisible(false);
        etapa = null;
        dccPedidoCotizacion.setDate(Fecha.fechaActualDate());
        long nro = gestor.generarNumeroPedido();
        lblNroTrabajo.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_TRABAJO_TERCERIZADO, nro));

    }

    public void setEtapa(Etapadeproduccion et) {
        etapa = et;
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

        //PEDIDO DE COTIZACION
        tblPedidoCotizacion.setModel(new PedidoCotizacionTableModel());
        tblPedidoCotizacion.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblPedidoCotizacion.setShowHorizontalLines(false);
        tblPedidoCotizacion.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblPedidoCotizacion.setHighlighters(
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
        for (Pedido pedido : filasPedidos) {
            view = new ViewPedidoEnListadoProcedimientos();

            view.setCliente(pedido.getCliente().getRazonsocial());
            view.setEspedidoweb(pedido.getEspedidoweb());
            view.setEstado(pedido.getEstado().getNombre());
            view.setFechaentregaestipulada(pedido.getFechaentregaestipulada());
            view.setFechapedidocotizacion(pedido.getFechapedidocotizacion());
            view.setFecharequeridacotizacion(pedido.getFecharequeridacotizacion());
            view.setIdestado(pedido.getEstado().getIdestado());
            view.setIdpedido(pedido.getIdpedido());
            view.setNropedido((int) pedido.getNropedido());
            if(pedido.getNropedidocotizacioncliente()!=null)
                view.setNropedidocotizacioncliente(pedido.getNropedidocotizacioncliente());
            else 
                view.setNropedidocotizacioncliente(0);
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
        Trabajotercerizado trabajo = new Trabajotercerizado();
        trabajo.setEmpresa(gestor.buscarEmpresa(Long.parseLong(((ItemCombo) cmbResultadoBusqueda.getSelectedItem()).getId())));
        if (dccPedidoCotizacion.getDate() != null) {
            trabajo.setFechapedidocotizacion(dccPedidoCotizacion.getDate());
        }
        trabajo.setNrotrabajotercerizado(BigInteger.valueOf(gestor.generarNumeroPedido()));
        trabajo.setPedido(filasPedidos.get(beanTblPedidos.getTblPedidos().getSelectedRow()));

        result = gestor.nuevoPedido(trabajo, filasPedidoCotizacion);
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
        if (filasPedidoCotizacion != null) {
            filasPedidoCotizacion.clear();
        }

        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        tblPedidoCotizacion.updateUI();

        txtPedidoCotizacion.setText("");
        lblTelefono.setText("");
        lblCuit.setText("");
        lblNroEmpresa.setText("");
        lblResponsableEmpresa.setText("");
        lblcondicionIva.setText("");
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
        SeleccionarEtapa buscar = null;

        buscar = new SeleccionarEtapa();
        buscar.setVentana(this);
        buscar.setGestorTrabajo(gestor);
        buscar.setLocationRelativeTo(null);
        buscar.setVisible(true);


    }

    public void cargarListaPedidos() {
        if (etapa != null) {
            ViewDetalleProducto v = (ViewDetalleProducto) filasDetalleProducto.get(tblDetalleProducto.getSelectedRow());

            ViewPedidoCotizacion pe = new ViewPedidoCotizacion();
            pe.setAlto(v.getAlto());
            pe.setAncho(v.getAncho());
            pe.setCantidad(v.getCantidadPieza());
            pe.setDescripcion(v.getDescripcion());
            pe.setEtapa(etapa.getNombre());
            pe.setIdDetalle(v.getIdDetalle());
            pe.setIdPieza(v.getIdPieza());
            pe.setIdProducto(v.getIdProducto());
            pe.setIdetapa(etapa.getIdetapaproduccion());
            pe.setLargo(v.getLargo());
            pe.setNombrePieza(v.getNombrePieza());
            pe.setNombreTipoMaterial(v.getNombreTipoMaterial());
            filasPedidoCotizacion.add(pe);
            etapa = null;

            tblPedidoCotizacion.updateUI();
            btnQuitar.setEnabled(true);
            beanBtnGuardar.getBtnGuardar().setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proceso para las piezas!");
        }

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
        btnNuevaEmpresa = new javax.swing.JButton();
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
        lblNroTrabajo = new javax.swing.JLabel();
        dccPedidoCotizacion = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtPedidoCotizacion = new javax.swing.JTextField();
        beanTblPedidos = new metalsoft.beans.PedidosSinAlgEtapaProd();
        bsyBuscar1 = new org.jdesktop.swingx.JXBusyLabel();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidoCotizacion = new org.jdesktop.swingx.JXTable();
        btnQuitar = new javax.swing.JButton();
        NuevoPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Pedido Cotización");

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

        btnNuevaEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new1.png"))); // NOI18N
        btnNuevaEmpresa.setText("Nueva Empresa");
        btnNuevaEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaEmpresaActionPerformed(evt);
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
                    .addComponent(btnNuevaEmpresa))
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
                                .addComponent(lblResponsableEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcondicionIva, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
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
                        .addComponent(btnNuevaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblcondicionIva, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(lblCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jLabel11.setText("Nro.Trabajo Tercerizado:");

        lblNroTrabajo.setFont(new java.awt.Font("Tahoma", 1, 11));
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
                .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(559, 559, 559))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(lblNroTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(63, 63, 63))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Actuales"));

        jLabel13.setText("Nro. de Pedido de Cotización:");

        txtPedidoCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPedidoCotizacionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanTblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPedidoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bsyBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Producto"));

        jScrollPane4.setViewportView(tblDetalleProducto);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addComponent(beanBtnSeleccionarPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnSeleccionarPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pedido"));

        jScrollPane5.setViewportView(tblDetallePedido);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedido de Cotizacion de Trabajo"));

        jScrollPane1.setViewportView(tblPedidoCotizacion);

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/quitar.png"))); // NOI18N
        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitar)
                .addContainerGap(338, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuitar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap())
        );

        NuevoPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/new1.png"))); // NOI18N
        NuevoPedido.setToolTipText("Nuevo Pedido");
        NuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(NuevoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 894, Short.MAX_VALUE)
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
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NuevoPedido))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbResultadoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResultadoBusquedaActionPerformed

        if (cmbResultadoBusqueda.getItemCount() > 0) {
            if (Integer.parseInt(((ItemCombo) cmbResultadoBusqueda.getSelectedItem()).getId()) > 0) {
                int id = Integer.parseInt(((ItemCombo) cmbResultadoBusqueda.getSelectedItem()).getId());
                GestorEmpresaMetalurgica gestorEmpresa = new GestorEmpresaMetalurgica();
                Empresametalurgica emp = gestorEmpresa.buscarEmpresaMetalurgica(id);

                lblTelefono.setText(emp.getTelefono());
                lblCuit.setText(emp.getCuit());
                lblNroEmpresa.setText(String.valueOf(emp.getNroempresametalurgica()));
                Responsable responsable = emp.getResponsable();
                lblResponsableEmpresa.setText(responsable.getNombre() + " " + responsable.getApellido());
                Condicioniva condIva = emp.getCondicioniva();
                lblcondicionIva.setText(condIva.getNombre());
                beanTblPedidos.getBtnSeleccionarPedido().setEnabled(true);
            } else {
                beanTblPedidos.getBtnSeleccionarPedido().setEnabled(false);
                beanBtnGuardar.getBtnGuardar().setEnabled(false);
                beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(false);
                beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(false);
                btnQuitar.setEnabled(false);
                limpiarCampos();
            }
        }
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
            limpiarCampos();
            cmbResultadoBusqueda.removeAllItems();
            beanTblPedidos.getBtnSeleccionarPedido().setEnabled(false);
            beanBtnGuardar.getBtnGuardar().setEnabled(false);
            beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(false);
            beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(false);
            btnQuitar.setEnabled(false);
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

    private void btnNuevaEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaEmpresaActionPerformed
        try {
            JFrameManager.crearVentana(ABMEmpresaMetalurgica.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarPedidoCotizacionDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RegistrarPedidoCotizacionDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RegistrarPedidoCotizacionDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnNuevaEmpresaActionPerformed

    private void txtPedidoCotizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedidoCotizacionKeyReleased
        // TODO add your handling code here:
        bsyBuscar1.setVisible(true);
        bsyBuscar1.setBusy(true);
        if (txtPedidoCotizacion.getText().compareTo("") != 0) {
            filasPedidos = gestor.buscarPedidosNoFinalizadosLIKE(txtPedidoCotizacion.getText());

        } else {
            filasPedidos = gestor.buscarPedidosNoFinalizados();
        }
        LinkedList<ViewPedidoEnListadoProcedimientos> lstView = new LinkedList<ViewPedidoEnListadoProcedimientos>();
        ViewPedidoEnListadoProcedimientos view = null;
        for (Pedido pedido : filasPedidos) {
            view = new ViewPedidoEnListadoProcedimientos();

            view.setCliente(pedido.getCliente().getRazonsocial());
            view.setEspedidoweb(pedido.getEspedidoweb());
            view.setEstado(pedido.getEstado().getNombre());
            view.setFechaentregaestipulada(pedido.getFechaentregaestipulada());
            view.setFechapedidocotizacion(pedido.getFechapedidocotizacion());
            view.setFecharequeridacotizacion(pedido.getFecharequeridacotizacion());
            view.setIdestado(pedido.getEstado().getIdestado());
            view.setIdpedido(pedido.getIdpedido());
            view.setNropedido((int) pedido.getNropedido());
            view.setNropedidocotizacioncliente(pedido.getNropedidocotizacioncliente());
            view.setPrioridad(pedido.getPrioridad().getNombre());

            lstView.addLast(view);
        }
        beanTblPedidos.setFilasPedidos(lstView);
        beanTblPedidos.updateTblPedidos();
        bsyBuscar1.setVisible(false);
        bsyBuscar1.setBusy(false);

    }//GEN-LAST:event_txtPedidoCotizacionKeyReleased

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:

        filasPedidoCotizacion.remove(tblPedidoCotizacion.getSelectedRow());
        tblPedidoCotizacion.updateUI();
        if (tblPedidoCotizacion.getRowCount() <= 0) {
            btnQuitar.setEnabled(false);
            beanBtnGuardar.getBtnGuardar().setEnabled(false);
        }

    }//GEN-LAST:event_btnQuitarActionPerformed

    private void NuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoPedidoActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        long nro = gestor.generarNumeroPedido();
        lblNroTrabajo.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_TRABAJO_TERCERIZADO, nro));
        beanTblPedidos.getBtnSeleccionarPedido().setEnabled(false);
        beanBtnGuardar.getBtnGuardar().setEnabled(false);
        beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(false);
        beanBtnSeleccionarProducto.getBtnSeleccionar().setEnabled(false);
        setEnabledComponents(false);
        btnQuitar.setEnabled(false);
        txtRazonSocial.setText("");
        cmbResultadoBusqueda.removeAllItems();
    }//GEN-LAST:event_NuevoPedidoActionPerformed

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
    private javax.swing.JButton NuevoPedido;
    private metalsoft.beans.BtnGuardar beanBtnGuardar;
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarPieza;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarProducto;
    private metalsoft.beans.PedidosSinAlgEtapaProd beanTblPedidos;
    private org.jdesktop.swingx.JXBusyLabel bsyBuscar;
    private org.jdesktop.swingx.JXBusyLabel bsyBuscar1;
    private javax.swing.JButton btnNuevaEmpresa;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox cmbResultadoBusqueda;
    private com.toedter.calendar.JDateChooser dccPedidoCotizacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
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
    private org.jdesktop.swingx.JXTable tblPedidoCotizacion;
    private javax.swing.JTextField txtPedidoCotizacion;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables

    public JComboBox getCombo(String className) {
        if (className.compareTo(HiloBuscarEmpresaMetalurgica.class.getName()) == 0) {
            return cmbResultadoBusqueda;
        }
        return null;
    }

    public JList getList(String className) {
        if (className.compareTo(HiloBuscarProducto.class.getName()) == 0) {
            return (JList) filasPedidos;
        }
        return null;
    }

    public void setBusqueda(Object[] obj) {
        GestorEmpresaMetalurgica gestorEmpresa = new GestorEmpresaMetalurgica();

        if (obj instanceof EmpresametalurgicaDB[]) {
            empresas = (EmpresametalurgicaDB[]) obj;
            if (obj.length > 0) {
//                EmpresametalurgicaDB cli = (EmpresametalurgicaDB) obj[0];
//                lblTelefono.setText(cli.getTelefono());
//                lblCuit.setText(cli.getCuit());
//                lblNroEmpresa.setText(String.valueOf(cli.getNroempresametalurgica()));
//                Responsable responsable = (gestorEmpresa.buscarEmpresaMetalurgica(cli.getIdempresametalurgica())).getResponsable();
//                lblResponsableEmpresa.setText(responsable.getNombre() + " " + responsable.getApellido());
//                Condicioniva condIva = (gestorEmpresa.buscarEmpresaMetalurgica(cli.getIdempresametalurgica())).getCondicioniva();
//                lblcondicionIva.setText(condIva.getNombre());
            } else {
                lblTelefono.setText("");
                lblCuit.setText("");
                lblNroEmpresa.setText("");
                lblResponsableEmpresa.setText("");
                lblcondicionIva.setText("");
            }

        }
        bsyBuscar.setBusy(false);
        bsyBuscar.setVisible(false);
    }

    private long obtenerNuevoNroPedidoCotizacion() {
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

            if (!filasPedidos.isEmpty()) {
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

    class PedidoCotizacionTableModel extends AbstractTableModel {

        String[] columnNames = {"Pieza",
            "Descripcion",
            "Cantidad",
            "Dimensiones",
            "Material",
            "Proceso a Realizar"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewPedidoCotizacion view = filasPedidoCotizacion.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNombrePieza();
                case 1:
                    return view.getDescripcion();
                case 2:
                    return String.valueOf(view.getCantidad());
                case 3:
                    return "Alto: " + view.getAlto() + "\n Ancho: " + view.getAncho() + "\n Largo: " + view.getLargo();
                case 4:
                    return view.getNombreTipoMaterial();
                case 5:
                    return view.getEtapa();
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
            if (filasPedidoCotizacion != null) {
                return filasPedidoCotizacion.size();
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
