/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GenerarListadoProcedimientosCotización.java
 *
 * Created on May 11, 2010, 2:48:45 AM
 */
package metalsoft.presentacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.PiezaxetapadeproduccionDB;
import metalsoft.negocio.gestores.GestorDetalleProcedimientos;
import metalsoft.negocio.gestores.IBuscadorView;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.PiezaXEtapas;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewEtapaDeProduccion;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class GenerarDetalleEtapasProduccion extends javax.swing.JDialog implements IBuscadorView {

    /** Creates new form GenerarListadoProcedimientosCotización */
    private LinkedList<ViewPedidoEnListadoProcedimientos> filasPedidos;
    private LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido;
    private LinkedList<ViewDetalleProducto> filasDetalleProducto;
    private LinkedList<ViewEtapaDeProduccion> filasEtapaProduccion;
    private LinkedList<ViewEtapaDeProduccion> filasEtapaProduccionSeleccionada;
    private GestorDetalleProcedimientos gestor;
    private Timer timer;
    private TableCellRender tcrTblDetallePedido;
    private long idPedidoSeleccionado, idProductoSeleccionado, idPiezaSeleccionada;

    public GenerarDetalleEtapasProduccion() {
        super(BotonRapidoPresupuesto.getVtnBotonRapido());
        initComponents();
        tcrTblDetallePedido = new TableCellRender();
        tblDetallePedido.setDefaultRenderer(Object.class, tcrTblDetallePedido);
        gestor = new GestorDetalleProcedimientos();
        buscarPedidosGenerados();
        addListeners();
        setearTablas();
        setEnabledComponents(false);
        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        tblEtapa.updateUI();
        filasEtapaProduccionSeleccionada = new LinkedList<ViewEtapaDeProduccion>();
        tblEtapaSeleccionada.updateUI();
    }

    private void setEnabledComponents(boolean b) {
        beanAgregarQuitar.setEnabled(b);
        beanBtnGuardar.getBtnGuardar().setEnabled(b);
        beanBtnSeleccionarPieza.setEnabled(b);
        beanBtnSeleccionarProducto.setEnabled(b);
        txtEtapaProduccion.setEnabled(b);
        btnAsignar.setEnabled(b);
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

        tblEtapa.setModel(new EtapaTableModel());
        tblEtapa.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblEtapa.setShowHorizontalLines(false);
        tblEtapa.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblEtapa.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        tblEtapaSeleccionada.setModel(new EtapaSeleccionadaTableModel());
        tblEtapaSeleccionada.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblEtapaSeleccionada.setShowHorizontalLines(false);
        tblEtapaSeleccionada.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblEtapaSeleccionada.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListeners() {
        addListenerBtnAgregar();
        addListenerBtnQuitar();
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
        boolean result = gestor.guardarEtapasPiezaPresupuesto();
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
        if (filasEtapaProduccion != null) {
            filasEtapaProduccion.clear();
        }
        if (filasEtapaProduccionSeleccionada != null) {
            filasEtapaProduccionSeleccionada.clear();
        }
        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        tblEtapa.updateUI();
        tblEtapaSeleccionada.updateUI();
        txtEtapaProduccion.setText("");
        txtPedidoCotizacion.setText("");
        lblPedidoSeleccionado.setText("...");
        lblPiezaSeleccionada.setText("...");
        lblProductoSeleccionado.setText("...");
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
        filasEtapaProduccion = gestor.obtenerEtapasDeProduccion();
        filasEtapaProduccionSeleccionada.clear();
        PiezaxetapadeproduccionDB[] pxeDB = gestor.buscarEtapasDePieza(v.getIdPieza());
        separarEtapasDeProduccion(pxeDB);
        tblEtapa.updateUI();
        tblEtapaSeleccionada.updateUI();
        idPiezaSeleccionada = v.getIdPieza();
        lblPiezaSeleccionada.setText(v.getNombrePieza());
        beanAgregarQuitar.getBtnAgregar().setEnabled(true);
        beanAgregarQuitar.getBtnQuitar().setEnabled(true);
        btnAsignar.setEnabled(true);
        txtEtapaProduccion.setEnabled(true);
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
        idProductoSeleccionado = idPro;
        lblProductoSeleccionado.setText(v.getNombreProducto());
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
        ViewPedidoEnListadoProcedimientos v = filasPedidos.get(beanTblPedidos.getTblPedidos().getSelectedRow());
        long idPed = v.getIdpedido();
        filasDetallePedido = gestor.buscarDetallePedido(idPed);
        tblDetallePedido.updateUI();
        idPedidoSeleccionado = idPed;
        lblPedidoSeleccionado.setText(String.valueOf(v.getNropedido()));
        setEnabledComponents(false);
        beanBtnSeleccionarProducto.setEnabled(true);
    }

    private void addListenerBtnAgregar() {
        beanAgregarQuitar.getBtnAgregar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeanAgregarActionPerformed(evt);
            }
        });
    }

    private void btnBeanAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblEtapa.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proceso de calidad!");
            return;
        }
        if (filasEtapaProduccionSeleccionada.isEmpty()) {
            beanAgregarQuitar.getBtnQuitar().setEnabled(true);
        }
        ViewEtapaDeProduccion v = filasEtapaProduccion.remove(tblEtapa.getSelectedRow());
        filasEtapaProduccionSeleccionada.add(v);
        tblEtapa.updateUI();
        tblEtapaSeleccionada.updateUI();
        if (filasEtapaProduccion.isEmpty()) {
            beanAgregarQuitar.getBtnAgregar().setEnabled(false);
        }
    }

    private void addListenerBtnQuitar() {
        beanAgregarQuitar.getBtnQuitar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeanQuitarActionPerformed(evt);
            }
        });
    }

    private void btnBeanQuitarActionPerformed(java.awt.event.ActionEvent evt) {
        if (filasEtapaProduccionSeleccionada.isEmpty()) {
            return;
        }
        if (filasEtapaProduccion.isEmpty()) {
            beanAgregarQuitar.getBtnAgregar().setEnabled(true);
        }
        ViewEtapaDeProduccion v = filasEtapaProduccionSeleccionada.remove(tblEtapaSeleccionada.getSelectedRow());
        filasEtapaProduccion.add(v);
        tblEtapa.updateUI();
        tblEtapaSeleccionada.updateUI();
        if (filasEtapaProduccionSeleccionada.isEmpty()) {
            beanAgregarQuitar.getBtnQuitar().setEnabled(false);
        }
    }

    private void buscarPedidosGenerados() {
        filasPedidos = gestor.buscarPedidosGenerados();
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
        txtPedidoCotizacion = new javax.swing.JTextField();
        beanTblPedidos = new metalsoft.beans.PedidosSinAlgEtapaProd();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetallePedido = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionarProducto = new metalsoft.beans.BtnSeleccionar();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleProducto = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionarPieza = new metalsoft.beans.BtnSeleccionar();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPiezaSeleccionada = new javax.swing.JLabel();
        txtEtapaProduccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        beanAgregarQuitar = new metalsoft.beans.AgregarQuitar();
        jLabel4 = new javax.swing.JLabel();
        lblProductoSeleccionado = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPedidoSeleccionado = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAsignar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEtapa = new org.jdesktop.swingx.JXTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEtapaSeleccionada = new org.jdesktop.swingx.JXTable();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();
        beanBtnGuardar = new metalsoft.beans.BtnGuardar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar Detalle Etapas de Producción");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Generados No Cotizados"));

        jLabel1.setText("Nro. de Pedido de Cotización:");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtPedidoCotizacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(562, 562, 562))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(beanTblPedidos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(txtPedidoCotizacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(beanTblPedidos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(8, 8, 8))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pedido"));

        jScrollPane1.setViewportView(tblDetallePedido);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, beanBtnSeleccionarProducto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 9, Short.MAX_VALUE)
                .add(beanBtnSeleccionarProducto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Producto"));

        jScrollPane2.setViewportView(tblDetalleProducto);

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, beanBtnSeleccionarPieza, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 9, Short.MAX_VALUE)
                .add(beanBtnSeleccionarPieza, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pieza Seleccionada"));

        jLabel2.setText("Pieza:");

        lblPiezaSeleccionada.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblPiezaSeleccionada.setText("....");

        txtEtapaProduccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEtapaProduccionKeyReleased(evt);
            }
        });

        jLabel3.setText("Nombre Etapa Producción:");

        jLabel4.setText("Producto:");

        lblProductoSeleccionado.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblProductoSeleccionado.setText("....");

        jLabel5.setText("Nro. Pedido:");

        lblPedidoSeleccionado.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblPedidoSeleccionado.setText("....");

        btnAsignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/48-badge-check.png"))); // NOI18N
        btnAsignar.setText("Asignar");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(tblEtapa);

        jScrollPane4.setViewportView(tblEtapaSeleccionada);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblPedidoSeleccionado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblProductoSeleccionado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(lblPiezaSeleccionada, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtEtapaProduccion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 378, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(btnAsignar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(beanAgregarQuitar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 417, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(lblPedidoSeleccionado)
                    .add(jLabel2)
                    .add(lblPiezaSeleccionada)
                    .add(jLabel4)
                    .add(lblProductoSeleccionado))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(16, 16, 16)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtEtapaProduccion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(beanAgregarQuitar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnAsignar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(26, 26, 26))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(beanBtnGuardar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 822, Short.MAX_VALUE)
                        .add(beanBtnSalir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(beanBtnSalir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(beanBtnGuardar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void separarEtapasDeProduccion(PiezaxetapadeproduccionDB[] pxeDB) {
        ViewEtapaDeProduccion v = null;
        PiezaxetapadeproduccionDB db = null;
        for (int i = 0; i < filasEtapaProduccion.size(); i++) {
            v = filasEtapaProduccion.get(i);
            for (int j = 0; j < pxeDB.length; j++) {
                db = pxeDB[j];
                if (v.getIdetapa() == db.getIdetapaproduccion()) {
                    filasEtapaProduccionSeleccionada.add(filasEtapaProduccion.remove(i));
                }
            }

        }
    }

    private void txtEtapaProduccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEtapaProduccionKeyReleased
        if (txtEtapaProduccion.getText().compareTo("") != 0) {
            final GenerarDetalleEtapasProduccion abm = this;
            timer = new Timer();
            timer.schedule(new TimerTask() {

                private HiloViewEtapaDeProduccion hilo;

                @Override
                public void run() {
                    hilo = new HiloViewEtapaDeProduccion();
                    hilo.setClient(abm);
                    hilo.setValor(txtEtapaProduccion.getText());
                    hilo.start();
                }
            }, 1500);
        }
    }//GEN-LAST:event_txtEtapaProduccionKeyReleased

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        if (filasEtapaProduccionSeleccionada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ningún proceso de producción para asignar!");
            return;
        }

        PiezaXEtapas pxe = new PiezaXEtapas();

        ViewDetalleProducto viewDetPro = filasDetalleProducto.get(tblDetalleProducto.getSelectedRow());
        ViewDetallePedidoCotizacion viewDetPed = filasDetallePedido.get(tblDetallePedido.getSelectedRow());
        long idPi = viewDetPro.getIdPieza();
        double ancho = viewDetPro.getAncho();
        double alto = viewDetPro.getAlto();
        double largo = viewDetPro.getLargo();
        long idProd = viewDetPro.getIdProducto();
        double precioProd = viewDetPed.getPrecio();
        int cantProd = viewDetPed.getCantidad();
        int cantPiezas = viewDetPro.getCantidadPieza();
        long idDetPedido = viewDetPed.getIdDetalle();
        ViewPedidoEnListadoProcedimientos viewPed = filasPedidos.get(beanTblPedidos.getTblPedidos().getSelectedRow());
        long idPed = viewPed.getIdpedido();

        pxe.setAlto(alto);
        pxe.setAncho(ancho);
        pxe.setLargo(largo);
        pxe.setPrecioProducto(precioProd);
        pxe.setCantProductos(cantProd);
        pxe.setCantPiezas(cantPiezas);
        pxe.setIdPieza(idPi);
        pxe.setIdProducto(idProd);
        pxe.setIdDetallePedido(idDetPedido);
        pxe.setIdPedido(idPed);
        pxe.setEtapas(new LinkedList<ViewEtapaDeProduccion>(filasEtapaProduccionSeleccionada));

        int result = gestor.addPiezaXEtapas(pxe);
        mostrarMensajeAsignar(result, viewDetPro.getNombrePieza());
        beanBtnGuardar.getBtnGuardar().setEnabled(true);
    }//GEN-LAST:event_btnAsignarActionPerformed

    /*
     * 0: no se pudo agregar
     * -1: se agrego correctamente
     * 1: se modifico correctamente
     */
    private void mostrarMensajeAsignar(int result, String nombrePieza) {
        switch (result) {
            case 1:
                JOptionPane.showMessageDialog(this, "Se modificó la pre-asignación de etapas para la pieza '" + nombrePieza + "'");
                break;
            case 0:
                JOptionPane.showMessageDialog(this, "NO se pudo pre-asignar etapas para la pieza '" + nombrePieza + "'");
                break;
            case -1:
                JOptionPane.showMessageDialog(this, "Se pre-asignaron etapas para la pieza '" + nombrePieza + "'");
                break;
            default:
                JOptionPane.showMessageDialog(this, "NO se pudo pre-asignar etapas para la pieza '" + nombrePieza + "'");

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GenerarDetalleEtapasProduccion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.AgregarQuitar beanAgregarQuitar;
    private metalsoft.beans.BtnGuardar beanBtnGuardar;
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarPieza;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarProducto;
    private metalsoft.beans.PedidosSinAlgEtapaProd beanTblPedidos;
    private javax.swing.JButton btnAsignar;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblPedidoSeleccionado;
    private javax.swing.JLabel lblPiezaSeleccionada;
    private javax.swing.JLabel lblProductoSeleccionado;
    private org.jdesktop.swingx.JXTable tblDetallePedido;
    private org.jdesktop.swingx.JXTable tblDetalleProducto;
    private org.jdesktop.swingx.JXTable tblEtapa;
    private org.jdesktop.swingx.JXTable tblEtapaSeleccionada;
    private javax.swing.JTextField txtEtapaProduccion;
    private javax.swing.JTextField txtPedidoCotizacion;
    // End of variables declaration//GEN-END:variables

    public JTable getTable(String className) {
        if (className.compareTo(HiloViewEtapaDeProduccion.class.getName()) == 0) {
            return tblEtapa;
        }
        return null;
    }

    public LinkedList getFilas(String className) {
        if (className.compareTo(HiloViewEtapaDeProduccion.class.getName()) == 0) {
            return filasEtapaProduccion;
        }
        return null;
    }

    private ArrayList<Integer> buscarProductosSinAlgunaEtapa() {
        Iterator<ViewDetallePedidoCotizacion> i = filasDetallePedido.iterator();
        ViewDetallePedidoCotizacion v = null;
        ArrayList<Integer> rows = new ArrayList<Integer>();
        int contador = 0;
        while (i.hasNext()) {
            v = i.next();
            long idProd = v.getIdProducto();
            boolean es = gestor.esProductoSinAlgunaEtapa(idProd);
            if (es) {
                rows.add(contador);
            }
            contador++;
        }
        return rows;
    }
    // End of variables declaration

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

            ViewPedidoEnListadoProcedimientos view = filasPedidos.get(rowIndex);
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

    class EtapaTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Nombre"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewEtapaDeProduccion view = filasEtapaProduccion.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_ETAPA_PRODUCCION, view.getNumero());
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
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasEtapaProduccion != null) {
                return filasEtapaProduccion.size();
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

    class EtapaSeleccionadaTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Nombre"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewEtapaDeProduccion view = filasEtapaProduccionSeleccionada.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_ETAPA_PRODUCCION, view.getNumero());
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
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasEtapaProduccionSeleccionada != null) {
                return filasEtapaProduccionSeleccionada.size();
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
